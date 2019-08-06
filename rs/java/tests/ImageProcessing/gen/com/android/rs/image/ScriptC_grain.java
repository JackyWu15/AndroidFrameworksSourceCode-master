/*___Generated_by_IDEA___*/

/*
 * Copyright (C) 2011-2014 The Android Open Source Project
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

/*
 * This file is auto-generated. DO NOT MODIFY!
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\grain.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.grainBitCode;

/**
 * @hide
 */
public class ScriptC_grain extends ScriptC {
    private static final String __rs_resource_name = "grain";
    // Constructor
    public  ScriptC_grain(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              grainBitCode.getBitCode32(),
              grainBitCode.getBitCode64());
        __I32 = Element.I32(rs);
        __ALLOCATION = Element.ALLOCATION(rs);
        __F32 = Element.F32(rs);
        __U8_4 = Element.U8_4(rs);
        __U8 = Element.U8(rs);
    }

    private Element __ALLOCATION;
    private Element __F32;
    private Element __I32;
    private Element __U8;
    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_gWMask = 0;
    private int mExportVar_gWMask;
    public synchronized void set_gWMask(int v) {
        setVar(mExportVarIdx_gWMask, v);
        mExportVar_gWMask = v;
    }

    public int get_gWMask() {
        return mExportVar_gWMask;
    }

    public Script.FieldID getFieldID_gWMask() {
        return createFieldID(mExportVarIdx_gWMask, null);
    }

    private final static int mExportVarIdx_gHMask = 1;
    private int mExportVar_gHMask;
    public synchronized void set_gHMask(int v) {
        setVar(mExportVarIdx_gHMask, v);
        mExportVar_gHMask = v;
    }

    public int get_gHMask() {
        return mExportVar_gHMask;
    }

    public Script.FieldID getFieldID_gHMask() {
        return createFieldID(mExportVarIdx_gHMask, null);
    }

    private final static int mExportVarIdx_gBlendSource = 2;
    private Allocation mExportVar_gBlendSource;
    public synchronized void set_gBlendSource(Allocation v) {
        setVar(mExportVarIdx_gBlendSource, v);
        mExportVar_gBlendSource = v;
    }

    public Allocation get_gBlendSource() {
        return mExportVar_gBlendSource;
    }

    public Script.FieldID getFieldID_gBlendSource() {
        return createFieldID(mExportVarIdx_gBlendSource, null);
    }

    private final static int mExportVarIdx_gNoiseStrength = 3;
    private float mExportVar_gNoiseStrength;
    public synchronized void set_gNoiseStrength(float v) {
        setVar(mExportVarIdx_gNoiseStrength, v);
        mExportVar_gNoiseStrength = v;
    }

    public float get_gNoiseStrength() {
        return mExportVar_gNoiseStrength;
    }

    public Script.FieldID getFieldID_gNoiseStrength() {
        return createFieldID(mExportVarIdx_gNoiseStrength, null);
    }

    private final static int mExportVarIdx_gNoise = 4;
    private Allocation mExportVar_gNoise;
    public synchronized void set_gNoise(Allocation v) {
        setVar(mExportVarIdx_gNoise, v);
        mExportVar_gNoise = v;
    }

    public Allocation get_gNoise() {
        return mExportVar_gNoise;
    }

    public Script.FieldID getFieldID_gNoise() {
        return createFieldID(mExportVarIdx_gNoise, null);
    }

    private final static int mExportForEachIdx_root = 0;
    public Script.KernelID getKernelID_root() {
        return createKernelID(mExportForEachIdx_root, 59, null, null);
    }

    public void forEach_root(Allocation ain, Allocation aout) {
        forEach_root(ain, aout, null);
    }

    public void forEach_root(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        Type t0, t1;        // Verify dimensions
        t0 = ain.getType();
        t1 = aout.getType();
        if ((t0.getCount() != t1.getCount()) ||
            (t0.getX() != t1.getX()) ||
            (t0.getY() != t1.getY()) ||
            (t0.getZ() != t1.getZ()) ||
            (t0.hasFaces()   != t1.hasFaces()) ||
            (t0.hasMipmaps() != t1.hasMipmaps())) {
            throw new RSRuntimeException("Dimension mismatch between parameters ain and aout!");
        }

        forEach(mExportForEachIdx_root, ain, aout, null, sc);
    }

    private final static int mExportForEachIdx_genRand = 1;
    public Script.KernelID getKernelID_genRand() {
        return createKernelID(mExportForEachIdx_genRand, 34, null, null);
    }

    public void forEach_genRand(Allocation aout) {
        forEach_genRand(aout, null);
    }

    public void forEach_genRand(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8)) {
            throw new RSRuntimeException("Type mismatch with U8!");
        }
        forEach(mExportForEachIdx_genRand, (Allocation) null, aout, null, sc);
    }

    private final static int mExportForEachIdx_blend9 = 2;
    public Script.KernelID getKernelID_blend9() {
        return createKernelID(mExportForEachIdx_blend9, 58, null, null);
    }

    public void forEach_blend9(Allocation aout) {
        forEach_blend9(aout, null);
    }

    public void forEach_blend9(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8)) {
            throw new RSRuntimeException("Type mismatch with U8!");
        }
        forEach(mExportForEachIdx_blend9, (Allocation) null, aout, null, sc);
    }

}

