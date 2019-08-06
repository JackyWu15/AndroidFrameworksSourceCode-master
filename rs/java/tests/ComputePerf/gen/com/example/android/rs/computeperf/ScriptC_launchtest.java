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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ComputePerf\\src\\com\\example\\android\\rs\\computeperf\\launchtest.rs
 */

package com.example.android.rs.computeperf;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.example.android.rs.computeperf.launchtestBitCode;

/**
 * @hide
 */
public class ScriptC_launchtest extends ScriptC {
    private static final String __rs_resource_name = "launchtest";
    // Constructor
    public  ScriptC_launchtest(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              launchtestBitCode.getBitCode32(),
              launchtestBitCode.getBitCode64());
        mExportVar_dim = 2048;
        __I32 = Element.I32(rs);
        __ALLOCATION = Element.ALLOCATION(rs);
        __U8 = Element.U8(rs);
    }

    private Element __ALLOCATION;
    private Element __I32;
    private Element __U8;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_dim = 0;
    private int mExportVar_dim;
    public final static int const_dim = 2048;
    public int get_dim() {
        return mExportVar_dim;
    }

    public Script.FieldID getFieldID_dim() {
        return createFieldID(mExportVarIdx_dim, null);
    }

    private final static int mExportVarIdx_gBuf = 1;
    private Allocation mExportVar_gBuf;
    public synchronized void set_gBuf(Allocation v) {
        setVar(mExportVarIdx_gBuf, v);
        mExportVar_gBuf = v;
    }

    public Allocation get_gBuf() {
        return mExportVar_gBuf;
    }

    public Script.FieldID getFieldID_gBuf() {
        return createFieldID(mExportVarIdx_gBuf, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_k_x = 1;
    public Script.KernelID getKernelID_k_x() {
        return createKernelID(mExportForEachIdx_k_x, 41, null, null);
    }

    public void forEach_k_x(Allocation ain) {
        forEach_k_x(ain, null);
    }

    public void forEach_k_x(Allocation ain, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__U8)) {
            throw new RSRuntimeException("Type mismatch with U8!");
        }
        forEach(mExportForEachIdx_k_x, ain, null, null, sc);
    }

    private final static int mExportForEachIdx_k_xy = 2;
    public Script.KernelID getKernelID_k_xy() {
        return createKernelID(mExportForEachIdx_k_xy, 58, null, null);
    }

    public void forEach_k_xy(Allocation aout) {
        forEach_k_xy(aout, null);
    }

    public void forEach_k_xy(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8)) {
            throw new RSRuntimeException("Type mismatch with U8!");
        }
        forEach(mExportForEachIdx_k_xy, (Allocation) null, aout, null, sc);
    }

}

