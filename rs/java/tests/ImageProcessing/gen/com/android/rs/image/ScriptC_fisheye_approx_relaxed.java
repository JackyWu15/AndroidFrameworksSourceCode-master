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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\fisheye_approx_relaxed.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.fisheye_approx_relaxedBitCode;

/**
 * @hide
 */
public class ScriptC_fisheye_approx_relaxed extends ScriptC {
    private static final String __rs_resource_name = "fisheye_approx_relaxed";
    // Constructor
    public  ScriptC_fisheye_approx_relaxed(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              fisheye_approx_relaxedBitCode.getBitCode32(),
              fisheye_approx_relaxedBitCode.getBitCode64());
        __ALLOCATION = Element.ALLOCATION(rs);
        __SAMPLER = Element.SAMPLER(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __ALLOCATION;
    private Element __SAMPLER;
    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_SAMPLER;
    private final static int mExportVarIdx_in_alloc = 0;
    private Allocation mExportVar_in_alloc;
    public synchronized void set_in_alloc(Allocation v) {
        setVar(mExportVarIdx_in_alloc, v);
        mExportVar_in_alloc = v;
    }

    public Allocation get_in_alloc() {
        return mExportVar_in_alloc;
    }

    public Script.FieldID getFieldID_in_alloc() {
        return createFieldID(mExportVarIdx_in_alloc, null);
    }

    private final static int mExportVarIdx_sampler = 1;
    private Sampler mExportVar_sampler;
    public synchronized void set_sampler(Sampler v) {
        setVar(mExportVarIdx_sampler, v);
        mExportVar_sampler = v;
    }

    public Sampler get_sampler() {
        return mExportVar_sampler;
    }

    public Script.FieldID getFieldID_sampler() {
        return createFieldID(mExportVarIdx_sampler, null);
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

    private final static int mExportFuncIdx_init_filter = 0;
    public Script.InvokeID getInvokeID_init_filter() {
        return createInvokeID(mExportFuncIdx_init_filter);
    }

    public void invoke_init_filter(long dim_x, long dim_y, float center_x, float center_y, float k) {
        FieldPacker init_filter_fp = new FieldPacker(20);
        init_filter_fp.addU32(dim_x);
        init_filter_fp.addU32(dim_y);
        init_filter_fp.addF32(center_x);
        init_filter_fp.addF32(center_y);
        init_filter_fp.addF32(k);
        invoke(mExportFuncIdx_init_filter, init_filter_fp);
    }

}

