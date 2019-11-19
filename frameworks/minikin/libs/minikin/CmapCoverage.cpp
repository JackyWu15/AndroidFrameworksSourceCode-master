/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Determine coverage of font given its raw "cmap" OpenType table

#ifdef PRINTF_DEBUG
#include <stdio.h>
#endif

#include <vector>
using std::vector;

#include <minikin/SparseBitSet.h>
#include <minikin/CmapCoverage.h>

namespace android {

// These could perhaps be optimized to use __builtin_bswap16 and friends.
static uint32_t readU16(const uint8_t* data, size_t offset) {
    return data[offset] << 8 | data[offset + 1];
}

static uint32_t readU32(const uint8_t* data, size_t offset) {
    return data[offset] << 24 | data[offset + 1] << 16 | data[offset + 2] << 8 | data[offset + 3];
}

static void addRange(vector<uint32_t> &coverage, uint32_t start, uint32_t end) {
#ifdef PRINTF_DEBUG
    printf("adding range %d-%d\n", start, end);
#endif
    if (coverage.empty() || coverage.back() < start) {
        coverage.push_back(start);
        coverage.push_back(end);
    } else {
        coverage.back() = end;
    }
}

// Get the coverage information out of a Format 12 subtable, storing it in the coverage vector
static bool getCoverageFormat4(vector<uint32_t>& coverage, const uint8_t* data, size_t size) {
    const size_t kSegCountOffset = 6;
    const size_t kEndCountOffset = 14;
    const size_t kHeaderSize = 16;
    const size_t kSegmentSize = 8;  // total size of array elements for one segment
    if (kEndCountOffset > size) {
        return false;
    }
    size_t segCount = readU16(data, kSegCountOffset) >> 1;
    if (kHeaderSize + segCount * kSegmentSize > size) {
        return false;
    }
    for (size_t i = 0; i < segCount; i++) {
        int end = readU16(data, kEndCountOffset + 2 * i);
        int start = readU16(data, kHeaderSize + 2 * (segCount + i));
        int rangeOffset = readU16(data, kHeaderSize + 2 * (3 * segCount + i));
        if (rangeOffset == 0) {
            int delta = readU16(data, kHeaderSize + 2 * (2 * segCount + i));
            if (((end + delta) & 0xffff) > end - start) {
                addRange(coverage, start, end + 1);
            } else {
                for (int j = start; j < end + 1; j++) {
                    if (((j + delta) & 0xffff) != 0) {
                        addRange(coverage, j, j + 1);
                    }
                }
            }
        } else {
            for (int j = start; j < end + 1; j++) {
                uint32_t actualRangeOffset = kHeaderSize + 6 * segCount + rangeOffset +
                    (i + j - start) * 2;
                if (actualRangeOffset + 2 > size) {
                    return false;
                }
                int glyphId = readU16(data, actualRangeOffset);
                if (glyphId != 0) {
                    addRange(coverage, j, j + 1);
                }
            }
        }
    }
    return true;
}

// Get the coverage information out of a Format 12 subtable, storing it in the coverage vector
static bool getCoverageFormat12(vector<uint32_t>& coverage, const uint8_t* data, size_t size) {
    const size_t kNGroupsOffset = 12;
    const size_t kFirstGroupOffset = 16;
    const size_t kGroupSize = 12;
    const size_t kStartCharCodeOffset = 0;
    const size_t kEndCharCodeOffset = 4;
    if (kFirstGroupOffset > size) {
        return false;
    }
    uint32_t nGroups = readU32(data, kNGroupsOffset);
    if (kFirstGroupOffset + nGroups * kGroupSize > size) {
        return false;
    }
    for (uint32_t i = 0; i < nGroups; i++) {
        uint32_t groupOffset = kFirstGroupOffset + i * kGroupSize;
        uint32_t start = readU32(data, groupOffset + kStartCharCodeOffset);
        uint32_t end = readU32(data, groupOffset + kEndCharCodeOffset);
        addRange(coverage, start, end + 1);  // file is inclusive, vector is exclusive
    }
    return true;
}

bool CmapCoverage::getCoverage(SparseBitSet& coverage, const uint8_t* cmap_data, size_t cmap_size) {
    vector<uint32_t> coverageVec;
    const size_t kHeaderSize = 4;
    const size_t kNumTablesOffset = 2;
    const size_t kTableSize = 8;
    const size_t kPlatformIdOffset = 0;
    const size_t kEncodingIdOffset = 2;
    const size_t kOffsetOffset = 4;
    const int kMicrosoftPlatformId = 3;
    const int kUnicodeBmpEncodingId = 1;
    const int kUnicodeUcs4EncodingId = 10;
    if (kHeaderSize > cmap_size) {
        return false;
    }
    int numTables = readU16(cmap_data, kNumTablesOffset);
    if (kHeaderSize + numTables * kTableSize > cmap_size) {
        return false;
    }
    int bestTable = -1;
    for (int i = 0; i < numTables; i++) {
        uint16_t platformId = readU16(cmap_data, kHeaderSize + i * kTableSize + kPlatformIdOffset);
        uint16_t encodingId = readU16(cmap_data, kHeaderSize + i * kTableSize + kEncodingIdOffset);
        if (platformId == kMicrosoftPlatformId && encodingId == kUnicodeUcs4EncodingId) {
            bestTable = i;
            break;
        } else if (platformId == kMicrosoftPlatformId && encodingId == kUnicodeBmpEncodingId) {
            bestTable = i;
        }
    }
#ifdef PRINTF_DEBUG
    printf("best table = %d\n", bestTable);
#endif
    if (bestTable < 0) {
        return false;
    }
    uint32_t offset = readU32(cmap_data, kHeaderSize + bestTable * kTableSize + kOffsetOffset);
    if (offset + 2 > cmap_size) {
        return false;
    }
    uint16_t format = readU16(cmap_data, offset);
    bool success = false;
    const uint8_t* tableData = cmap_data + offset;
    const size_t tableSize = cmap_size - offset;
    if (format == 4) {
        success = getCoverageFormat4(coverageVec, tableData, tableSize);
    } else if (format == 12) {
        success = getCoverageFormat12(coverageVec, tableData, tableSize);
    }
    if (success) {
        coverage.initFromRanges(&coverageVec.front(), coverageVec.size() >> 1);
    }
#ifdef PRINTF_DEBUG
    for (int i = 0; i < coverageVec.size(); i += 2) {
        printf("%x:%x\n", coverageVec[i], coverageVec[i + 1]);
    }
#endif
    return success;
}

}  // namespace android
