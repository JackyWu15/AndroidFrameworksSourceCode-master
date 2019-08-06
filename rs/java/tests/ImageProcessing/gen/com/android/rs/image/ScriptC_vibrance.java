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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\vibrance.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.vibranceBitCode;

/**
 * @hide
 */
public class ScriptC_vibrance extends ScriptC {
    private static final String __rs_resource_name = "vibrance";
    // Constructor
    public  ScriptC_vibrance(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              vibranceBitCode.getBitCode32(),
              vibranceBitCode.getBitCode64());
        mExportVar_vibrance = 0.f;
        __F32 = Element.F32(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __F32;
    private Element __U8_4;
    private FieldPacker __rs_fp_F32;
    private final static int mExportVarIdx_vibrance = 0;
    private float mExportVar_vibrance;
    public synchronized void set_vibrance(float v) {
        setVar(mExportVarIdx_vibrance, v);
        mExportVar_vibrance = v;
    }

    public float get_vibrance() {
        return mExportVar_vibrance;
    }

    public Script.FieldID getFieldID_vibrance() {
        return createFieldID(mExportVarIdx_vibrance, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_vibranceKernel = 1;
    public Script.KernelID getKernelID_vibranceKernel() {
        return createKernelID(mExportForEachIdx_vibranceKernel, 35, null, null);
    }

    public void forEach_vibranceKernel(Allocation ain, Allocation aout) {
        forEach_vibranceKernel(ain, aout, null);
    }

    public void forEach_vibranceKernel(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
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

        forEach(mExportForEachIdx_vibranceKernel, ain, aout, null, sc);
    }

    private final static int mExportFuncIdx_prepareVibrance = 0;
    public Script.InvokeID getInvokeID_prepareVibrance() {
        return createInvokeID(mExportFuncIdx_prepareVibrance);
    }

    public void invoke_prepareVibrance() {
        invoke(mExportFuncIdx_prepareVibrance);
    }

}

