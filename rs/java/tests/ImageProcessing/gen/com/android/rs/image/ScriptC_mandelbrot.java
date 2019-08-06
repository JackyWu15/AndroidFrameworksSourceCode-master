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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\mandelbrot.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.mandelbrotBitCode;

/**
 * @hide
 */
public class ScriptC_mandelbrot extends ScriptC {
    private static final String __rs_resource_name = "mandelbrot";
    // Constructor
    public  ScriptC_mandelbrot(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              mandelbrotBitCode.getBitCode32(),
              mandelbrotBitCode.getBitCode64());
        mExportVar_gMaxIteration = 500;
        __U32 = Element.U32(rs);
        mExportVar_gDimX = 1024;
        mExportVar_gDimY = 1024;
        mExportVar_lowerBoundX = -2.f;
        __F32 = Element.F32(rs);
        mExportVar_lowerBoundY = -2.f;
        mExportVar_scaleFactor = 4.f;
        __U8_4 = Element.U8_4(rs);
    }

    private Element __F32;
    private Element __U32;
    private Element __U8_4;
    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_U32;
    private final static int mExportVarIdx_gMaxIteration = 0;
    private long mExportVar_gMaxIteration;
    public synchronized void set_gMaxIteration(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_gMaxIteration, __rs_fp_U32);
        mExportVar_gMaxIteration = v;
    }

    public long get_gMaxIteration() {
        return mExportVar_gMaxIteration;
    }

    public Script.FieldID getFieldID_gMaxIteration() {
        return createFieldID(mExportVarIdx_gMaxIteration, null);
    }

    private final static int mExportVarIdx_gDimX = 1;
    private long mExportVar_gDimX;
    public synchronized void set_gDimX(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_gDimX, __rs_fp_U32);
        mExportVar_gDimX = v;
    }

    public long get_gDimX() {
        return mExportVar_gDimX;
    }

    public Script.FieldID getFieldID_gDimX() {
        return createFieldID(mExportVarIdx_gDimX, null);
    }

    private final static int mExportVarIdx_gDimY = 2;
    private long mExportVar_gDimY;
    public synchronized void set_gDimY(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_gDimY, __rs_fp_U32);
        mExportVar_gDimY = v;
    }

    public long get_gDimY() {
        return mExportVar_gDimY;
    }

    public Script.FieldID getFieldID_gDimY() {
        return createFieldID(mExportVarIdx_gDimY, null);
    }

    private final static int mExportVarIdx_lowerBoundX = 3;
    private float mExportVar_lowerBoundX;
    public synchronized void set_lowerBoundX(float v) {
        setVar(mExportVarIdx_lowerBoundX, v);
        mExportVar_lowerBoundX = v;
    }

    public float get_lowerBoundX() {
        return mExportVar_lowerBoundX;
    }

    public Script.FieldID getFieldID_lowerBoundX() {
        return createFieldID(mExportVarIdx_lowerBoundX, null);
    }

    private final static int mExportVarIdx_lowerBoundY = 4;
    private float mExportVar_lowerBoundY;
    public synchronized void set_lowerBoundY(float v) {
        setVar(mExportVarIdx_lowerBoundY, v);
        mExportVar_lowerBoundY = v;
    }

    public float get_lowerBoundY() {
        return mExportVar_lowerBoundY;
    }

    public Script.FieldID getFieldID_lowerBoundY() {
        return createFieldID(mExportVarIdx_lowerBoundY, null);
    }

    private final static int mExportVarIdx_scaleFactor = 5;
    private float mExportVar_scaleFactor;
    public synchronized void set_scaleFactor(float v) {
        setVar(mExportVarIdx_scaleFactor, v);
        mExportVar_scaleFactor = v;
    }

    public float get_scaleFactor() {
        return mExportVar_scaleFactor;
    }

    public Script.FieldID getFieldID_scaleFactor() {
        return createFieldID(mExportVarIdx_scaleFactor, null);
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

    private final static int mExportForEachIdx_rootD = 1;
    public Script.KernelID getKernelID_rootD() {
        return createKernelID(mExportForEachIdx_rootD, 58, null, null);
    }

    public void forEach_rootD(Allocation aout) {
        forEach_rootD(aout, null);
    }

    public void forEach_rootD(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_rootD, (Allocation) null, aout, null, sc);
    }

}

