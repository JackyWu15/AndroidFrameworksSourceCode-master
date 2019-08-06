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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\bwfilter.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.bwfilterBitCode;

/**
 * @hide
 */
public class ScriptC_bwfilter extends ScriptC {
    private static final String __rs_resource_name = "bwfilter";
    // Constructor
    public  ScriptC_bwfilter(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              bwfilterBitCode.getBitCode32(),
              bwfilterBitCode.getBitCode64());
        __U8_4 = Element.U8_4(rs);
    }

    private Element __U8_4;
    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_bwFilterKernel = 1;
    public Script.KernelID getKernelID_bwFilterKernel() {
        return createKernelID(mExportForEachIdx_bwFilterKernel, 3, null, null);
    }

    public void forEach_bwFilterKernel(Allocation ain, Allocation aout) {
        forEach_bwFilterKernel(ain, aout, null);
    }

    public void forEach_bwFilterKernel(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
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

        forEach(mExportForEachIdx_bwFilterKernel, ain, aout, null, sc);
    }

    private final static int mExportFuncIdx_prepareBwFilter = 0;
    public Script.InvokeID getInvokeID_prepareBwFilter() {
        return createInvokeID(mExportFuncIdx_prepareBwFilter);
    }

    public void invoke_prepareBwFilter(long rw, long gw, long bw) {
        FieldPacker prepareBwFilter_fp = new FieldPacker(12);
        prepareBwFilter_fp.addU32(rw);
        prepareBwFilter_fp.addU32(gw);
        prepareBwFilter_fp.addU32(bw);
        invoke(mExportFuncIdx_prepareBwFilter, prepareBwFilter_fp);
    }

}

