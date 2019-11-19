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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\resize.rs
 */

package com.android.rs.image;

import com.android.rs.image.resizeBitCode;

/**
 * @hide
 */
public class ScriptC_resize extends ScriptC {
    private static final String __rs_resource_name = "resize";
    // Constructor
    public  ScriptC_resize(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              resizeBitCode.getBitCode32(),
              resizeBitCode.getBitCode64());
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
    private final static int mExportVarIdx_gWidthIn = 0;
    private int mExportVar_gWidthIn;
    public synchronized void set_gWidthIn(int v) {
        setVar(mExportVarIdx_gWidthIn, v);
        mExportVar_gWidthIn = v;
    }

    public int get_gWidthIn() {
        return mExportVar_gWidthIn;
    }

    public Script.FieldID getFieldID_gWidthIn() {
        return createFieldID(mExportVarIdx_gWidthIn, null);
    }

    private final static int mExportVarIdx_gHeightIn = 1;
    private int mExportVar_gHeightIn;
    public synchronized void set_gHeightIn(int v) {
        setVar(mExportVarIdx_gHeightIn, v);
        mExportVar_gHeightIn = v;
    }

    public int get_gHeightIn() {
        return mExportVar_gHeightIn;
    }

    public Script.FieldID getFieldID_gHeightIn() {
        return createFieldID(mExportVarIdx_gHeightIn, null);
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

    private final static int mExportVarIdx_scale = 3;
    private float mExportVar_scale;
    public synchronized void set_scale(float v) {
        setVar(mExportVarIdx_scale, v);
        mExportVar_scale = v;
    }

    public float get_scale() {
        return mExportVar_scale;
    }

    public Script.FieldID getFieldID_scale() {
        return createFieldID(mExportVarIdx_scale, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_nearest = 1;
    public Script.KernelID getKernelID_nearest() {
        return createKernelID(mExportForEachIdx_nearest, 58, null, null);
    }

    public void forEach_nearest(Allocation aout) {
        forEach_nearest(aout, null);
    }

    public void forEach_nearest(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_nearest, (Allocation) null, aout, null, sc);
    }

    private final static int mExportForEachIdx_bicubic = 2;
    public Script.KernelID getKernelID_bicubic() {
        return createKernelID(mExportForEachIdx_bicubic, 58, null, null);
    }

    public void forEach_bicubic(Allocation aout) {
        forEach_bicubic(aout, null);
    }

    public void forEach_bicubic(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_bicubic, (Allocation) null, aout, null, sc);
    }

}

