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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\LivePreview\\src\\com\\android\\rs\\livepreview\\yuv.rs
 */

package com.android.rs.livepreview;

import com.android.rs.livepreview.yuvBitCode;

/**
 * @hide
 */
public class ScriptC_yuv extends ScriptC {
    private static final String __rs_resource_name = "yuv";
    // Constructor
    public  ScriptC_yuv(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              yuvBitCode.getBitCode32(),
              yuvBitCode.getBitCode64());
        mExportVar_temp = 0.200000003f;
        __F32 = Element.F32(rs);
        __I32_2 = Element.I32_2(rs);
        mExportVar_vignetteScale = 0.5f;
        mExportVar_vignetteShade = 0.850000023f;
        __U8_4 = Element.U8_4(rs);
    }

    private Element __F32;
    private Element __I32_2;
    private Element __U8_4;
    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_I32_2;
    private final static int mExportVarIdx_temp = 0;
    private float mExportVar_temp;
    public synchronized void set_temp(float v) {
        setVar(mExportVarIdx_temp, v);
        mExportVar_temp = v;
    }

    public float get_temp() {
        return mExportVar_temp;
    }

    public Script.FieldID getFieldID_temp() {
        return createFieldID(mExportVarIdx_temp, null);
    }

    private final static int mExportVarIdx_vignette_half_dims = 1;
    private Int2 mExportVar_vignette_half_dims;
    public synchronized void set_vignette_half_dims(Int2 v) {
        mExportVar_vignette_half_dims = v;
        FieldPacker fp = new FieldPacker(8);
        fp.addI32(v);
        int []__dimArr = new int[1];
        __dimArr[0] = 1;
        setVar(mExportVarIdx_vignette_half_dims, fp, __I32_2, __dimArr);
    }

    public Int2 get_vignette_half_dims() {
        return mExportVar_vignette_half_dims;
    }

    public Script.FieldID getFieldID_vignette_half_dims() {
        return createFieldID(mExportVarIdx_vignette_half_dims, null);
    }

    private final static int mExportVarIdx_vignetteScale = 2;
    private float mExportVar_vignetteScale;
    public synchronized void set_vignetteScale(float v) {
        setVar(mExportVarIdx_vignetteScale, v);
        mExportVar_vignetteScale = v;
    }

    public float get_vignetteScale() {
        return mExportVar_vignetteScale;
    }

    public Script.FieldID getFieldID_vignetteScale() {
        return createFieldID(mExportVarIdx_vignetteScale, null);
    }

    private final static int mExportVarIdx_vignetteShade = 3;
    private float mExportVar_vignetteShade;
    public synchronized void set_vignetteShade(float v) {
        setVar(mExportVarIdx_vignetteShade, v);
        mExportVar_vignetteShade = v;
    }

    public float get_vignetteShade() {
        return mExportVar_vignetteShade;
    }

    public Script.FieldID getFieldID_vignetteShade() {
        return createFieldID(mExportVarIdx_vignetteShade, null);
    }

    private final static int mExportForEachIdx_root = 0;
    public Script.KernelID getKernelID_root() {
        return createKernelID(mExportForEachIdx_root, 27, null, null);
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

    private final static int mExportFuncIdx_setSize = 0;
    public Script.InvokeID getInvokeID_setSize() {
        return createInvokeID(mExportFuncIdx_setSize);
    }

    public void invoke_setSize(int w, int h) {
        FieldPacker setSize_fp = new FieldPacker(8);
        setSize_fp.addI32(w);
        setSize_fp.addI32(h);
        invoke(mExportFuncIdx_setSize, setSize_fp);
    }

}

