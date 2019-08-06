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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ComputePerf\\src\\com\\example\\android\\rs\\computeperf\\mandelbrot.rs
 */

package com.example.android.rs.computeperf;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.example.android.rs.computeperf.mandelbrotBitCode;

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
        __I32 = Element.I32(rs);
        mExportVar_gDimX = 1024;
        mExportVar_gDimY = 1024;
        __U8_4 = Element.U8_4(rs);
    }

    private Element __I32;
    private Element __U8_4;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_gMaxIteration = 0;
    private int mExportVar_gMaxIteration;
    public final static int const_gMaxIteration = 500;
    public int get_gMaxIteration() {
        return mExportVar_gMaxIteration;
    }

    public Script.FieldID getFieldID_gMaxIteration() {
        return createFieldID(mExportVarIdx_gMaxIteration, null);
    }

    private final static int mExportVarIdx_gDimX = 1;
    private int mExportVar_gDimX;
    public final static int const_gDimX = 1024;
    public int get_gDimX() {
        return mExportVar_gDimX;
    }

    public Script.FieldID getFieldID_gDimX() {
        return createFieldID(mExportVarIdx_gDimX, null);
    }

    private final static int mExportVarIdx_gDimY = 2;
    private int mExportVar_gDimY;
    public final static int const_gDimY = 1024;
    public int get_gDimY() {
        return mExportVar_gDimY;
    }

    public Script.FieldID getFieldID_gDimY() {
        return createFieldID(mExportVarIdx_gDimY, null);
    }

    private final static int mExportForEachIdx_root = 0;
    public Script.KernelID getKernelID_root() {
        return createKernelID(mExportForEachIdx_root, 26, null, null);
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

