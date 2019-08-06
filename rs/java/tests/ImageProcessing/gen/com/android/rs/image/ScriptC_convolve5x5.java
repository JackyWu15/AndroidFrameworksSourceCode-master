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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\convolve5x5.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.convolve5x5BitCode;

/**
 * @hide
 */
public class ScriptC_convolve5x5 extends ScriptC {
    private static final String __rs_resource_name = "convolve5x5";
    // Constructor
    public  ScriptC_convolve5x5(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              convolve5x5BitCode.getBitCode32(),
              convolve5x5BitCode.getBitCode64());
        __I32 = Element.I32(rs);
        __ALLOCATION = Element.ALLOCATION(rs);
        __F32 = Element.F32(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __ALLOCATION;
    private Element __F32;
    private Element __I32;
    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_gWidth = 0;
    private int mExportVar_gWidth;
    public synchronized void set_gWidth(int v) {
        setVar(mExportVarIdx_gWidth, v);
        mExportVar_gWidth = v;
    }

    public int get_gWidth() {
        return mExportVar_gWidth;
    }

    public Script.FieldID getFieldID_gWidth() {
        return createFieldID(mExportVarIdx_gWidth, null);
    }

    private final static int mExportVarIdx_gHeight = 1;
    private int mExportVar_gHeight;
    public synchronized void set_gHeight(int v) {
        setVar(mExportVarIdx_gHeight, v);
        mExportVar_gHeight = v;
    }

    public int get_gHeight() {
        return mExportVar_gHeight;
    }

    public Script.FieldID getFieldID_gHeight() {
        return createFieldID(mExportVarIdx_gHeight, null);
    }

    private final static int mExportVarIdx_gIn = 2;
    private Allocation mExportVar_gIn;
    public synchronized void set_gIn(Allocation v) {
        setVar(mExportVarIdx_gIn, v);
        mExportVar_gIn = v;
    }

    public Allocation get_gIn() {
        return mExportVar_gIn;
    }

    public Script.FieldID getFieldID_gIn() {
        return createFieldID(mExportVarIdx_gIn, null);
    }

    private final static int mExportVarIdx_gCoeffs = 3;
    private float[] mExportVar_gCoeffs;
    public synchronized void set_gCoeffs(float[] v) {
        mExportVar_gCoeffs = v;
        FieldPacker fp = new FieldPacker(100);
        for (int ct1 = 0; ct1 < 25; ct1++) {
            fp.addF32(v[ct1]);
        }

        int []__dimArr = new int[1];
        __dimArr[0] = 25;
        setVar(mExportVarIdx_gCoeffs, fp, __F32, __dimArr);
    }

    public float[] get_gCoeffs() {
        return mExportVar_gCoeffs;
    }

    public Script.FieldID getFieldID_gCoeffs() {
        return createFieldID(mExportVarIdx_gCoeffs, null);
    }

    private final static int mExportForEachIdx_root = 0;
    public Script.KernelID getKernelID_root() {
        return createKernelID(mExportForEachIdx_root, 58, null, null);
    }

    public void forEach_root(Allocation aout) {
        forEach_root(aout, null);
    }

    public void forEach_root(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_root, (Allocation) null, aout, null, sc);
    }

}

