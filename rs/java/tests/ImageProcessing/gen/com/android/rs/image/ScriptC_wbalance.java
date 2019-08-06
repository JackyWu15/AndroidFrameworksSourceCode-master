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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\wbalance.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.wbalanceBitCode;

/**
 * @hide
 */
public class ScriptC_wbalance extends ScriptC {
    private static final String __rs_resource_name = "wbalance";
    // Constructor
    public  ScriptC_wbalance(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              wbalanceBitCode.getBitCode32(),
              wbalanceBitCode.getBitCode64());
        __ALLOCATION = Element.ALLOCATION(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __ALLOCATION;
    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private final static int mExportVarIdx_histogramValues = 0;
    private Allocation mExportVar_histogramValues;
    public synchronized void set_histogramValues(Allocation v) {
        setVar(mExportVarIdx_histogramValues, v);
        mExportVar_histogramValues = v;
    }

    public Allocation get_histogramValues() {
        return mExportVar_histogramValues;
    }

    public Script.FieldID getFieldID_histogramValues() {
        return createFieldID(mExportVarIdx_histogramValues, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_whiteBalanceKernel = 1;
    public Script.KernelID getKernelID_whiteBalanceKernel() {
        return createKernelID(mExportForEachIdx_whiteBalanceKernel, 35, null, null);
    }

    public void forEach_whiteBalanceKernel(Allocation ain, Allocation aout) {
        forEach_whiteBalanceKernel(ain, aout, null);
    }

    public void forEach_whiteBalanceKernel(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
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

        forEach(mExportForEachIdx_whiteBalanceKernel, ain, aout, null, sc);
    }

    private final static int mExportFuncIdx_prepareWhiteBalance = 0;
    public Script.InvokeID getInvokeID_prepareWhiteBalance() {
        return createInvokeID(mExportFuncIdx_prepareWhiteBalance);
    }

    public void invoke_prepareWhiteBalance() {
        invoke(mExportFuncIdx_prepareWhiteBalance);
    }

}

