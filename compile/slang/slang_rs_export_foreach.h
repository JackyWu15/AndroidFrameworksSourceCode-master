/*
 * Copyright 2011-2012, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef _FRAMEWORKS_COMPILE_SLANG_SLANG_RS_EXPORT_FOREACH_H_  // NOLINT
#define _FRAMEWORKS_COMPILE_SLANG_SLANG_RS_EXPORT_FOREACH_H_

#include "llvm/ADT/StringRef.h"
#include "llvm/ADT/SmallVector.h"
#include "llvm/Support/raw_ostream.h"

#include "clang/AST/Decl.h"

#include "slang_assert.h"
#include "slang_rs_context.h"
#include "slang_rs_exportable.h"
#include "slang_rs_export_type.h"

namespace clang {
  class FunctionDecl;
}  // namespace clang

namespace slang {

// Base class for reflecting control-side forEach (currently for root()
// functions that fit appropriate criteria)
class RSExportForEach : public RSExportable {
 public:

  typedef llvm::SmallVectorImpl<const clang::ParmVarDecl*> InVec;
  typedef llvm::SmallVectorImpl<const RSExportType*> InTypeVec;

  typedef InVec::const_iterator InIter;
  typedef InTypeVec::const_iterator InTypeIter;

 private:
  std::string mName;
  RSExportRecordType *mParamPacketType;
  llvm::SmallVector<const RSExportType*, 16> mInTypes;
  RSExportType *mOutType;
  size_t numParams;

  unsigned int mSignatureMetadata;

  llvm::SmallVector<const clang::ParmVarDecl*, 16> mIns;
  const clang::ParmVarDecl *mOut;
  const clang::ParmVarDecl *mUsrData;
  const clang::ParmVarDecl *mX;
  const clang::ParmVarDecl *mY;

  clang::QualType mResultType;  // return type (if present).
  bool mHasReturnType;  // does this kernel have a return type?
  bool mIsKernelStyle;  // is this a pass-by-value kernel?

  bool mDummyRoot;

  // TODO(all): Add support for LOD/face when we have them
  RSExportForEach(RSContext *Context, const llvm::StringRef &Name)
    : RSExportable(Context, RSExportable::EX_FOREACH),
      mName(Name.data(), Name.size()), mParamPacketType(NULL),
      mOutType(NULL), numParams(0), mSignatureMetadata(0),
      mOut(NULL), mUsrData(NULL), mX(NULL), mY(NULL),
      mResultType(clang::QualType()), mHasReturnType(false),
      mIsKernelStyle(false), mDummyRoot(false) {
  }

  bool validateAndConstructParams(RSContext *Context,
                                  const clang::FunctionDecl *FD);

  bool validateAndConstructOldStyleParams(RSContext *Context,
                                          const clang::FunctionDecl *FD);

  bool validateAndConstructKernelParams(RSContext *Context,
                                        const clang::FunctionDecl *FD);

  bool validateIterationParameters(RSContext *Context,
                                   const clang::FunctionDecl *FD,
                                   size_t *IndexOfFirstIterator);

  bool setSignatureMetadata(RSContext *Context,
                            const clang::FunctionDecl *FD);
 public:
  static RSExportForEach *Create(RSContext *Context,
                                 const clang::FunctionDecl *FD);

  static RSExportForEach *CreateDummyRoot(RSContext *Context);

  inline const std::string &getName() const {
    return mName;
  }

  inline size_t getNumParameters() const {
    return numParams;
  }

  inline bool hasIns() const {
    return (!mIns.empty());
  }

  inline bool hasOut() const {
    return (mOut != NULL);
  }

  inline bool hasUsrData() const {
    return (mUsrData != NULL);
  }

  inline bool hasReturn() const {
    return mHasReturnType;
  }

  inline const InVec& getIns() const {
    return mIns;
  }

  inline const InTypeVec& getInTypes() const {
    return mInTypes;
  }

  inline const RSExportType *getOutType() const {
    return mOutType;
  }

  inline const RSExportRecordType *getParamPacketType() const {
    return mParamPacketType;
  }

  inline unsigned int getSignatureMetadata() const {
    return mSignatureMetadata;
  }

  inline bool isDummyRoot() const {
    return mDummyRoot;
  }

  typedef RSExportRecordType::const_field_iterator const_param_iterator;

  inline const_param_iterator params_begin() const {
    slangAssert((mParamPacketType != NULL) &&
                "Get parameter from export foreach having no parameter!");
    return mParamPacketType->fields_begin();
  }

  inline const_param_iterator params_end() const {
    slangAssert((mParamPacketType != NULL) &&
                "Get parameter from export foreach having no parameter!");
    return mParamPacketType->fields_end();
  }

  inline static bool isInitRSFunc(const clang::FunctionDecl *FD) {
    if (!FD) {
      return false;
    }
    const llvm::StringRef Name = FD->getName();
    static llvm::StringRef FuncInit("init");
    return Name.equals(FuncInit);
  }

  inline static bool isRootRSFunc(const clang::FunctionDecl *FD) {
    if (!FD) {
      return false;
    }
    const llvm::StringRef Name = FD->getName();
    static llvm::StringRef FuncRoot("root");
    return Name.equals(FuncRoot);
  }

  inline static bool isDtorRSFunc(const clang::FunctionDecl *FD) {
    if (!FD) {
      return false;
    }
    const llvm::StringRef Name = FD->getName();
    static llvm::StringRef FuncDtor(".rs.dtor");
    return Name.equals(FuncDtor);
  }

  static bool isGraphicsRootRSFunc(unsigned int targetAPI,
                                   const clang::FunctionDecl *FD);

  static bool isRSForEachFunc(unsigned int targetAPI, slang::RSContext *Context,
                              const clang::FunctionDecl *FD);

  inline static bool isSpecialRSFunc(unsigned int targetAPI,
                                     const clang::FunctionDecl *FD) {
    return isGraphicsRootRSFunc(targetAPI, FD) || isInitRSFunc(FD) ||
           isDtorRSFunc(FD);
  }

  static bool validateSpecialFuncDecl(unsigned int targetAPI,
                                      slang::RSContext *Context,
                                      const clang::FunctionDecl *FD);
};  // RSExportForEach

}  // namespace slang

#endif  // _FRAMEWORKS_COMPILE_SLANG_SLANG_RS_EXPORT_FOREACH_H_  NOLINT
