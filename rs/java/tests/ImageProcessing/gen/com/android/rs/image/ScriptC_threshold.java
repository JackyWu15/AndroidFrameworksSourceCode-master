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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\threshold.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.thresholdBitCode;

/**
 * @hide
 */
public class ScriptC_threshold extends ScriptC {
    private static final String __rs_resource_name = "threshold";
    // Constructor
    public  ScriptC_threshold(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              thresholdBitCode.getBitCode32(),
              thresholdBitCode.getBitCode64());
        __I32 = Element.I32(rs);
        __ALLOCATION = Element.ALLOCATION(rs);
        mExportVar_MAX_RADIUS = 25;
        __U8_4 = Element.U8_4(rs);
        __F32_4 = Element.F32_4(rs);
    }

    private Element __ALLOCATION;
    private Element __F32_4;
    private Element __I32;
    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_height = 0;
    private int mExportVar_height;
    public synchronized void set_height(int v) {
        setVar(mExportVarIdx_height, v);
        mExportVar_height = v;
    }

    public int get_height() {
        return mExportVar_height;
    }

    public Script.FieldID getFieldID_height() {
        return createFieldID(mExportVarIdx_height, null);
    }

    private final static int mExportVarIdx_width = 1;
    private int mExportVar_width;
    public synchronized void set_width(int v) {
        setVar(mExportVarIdx_width, v);
        mExportVar_width = v;
    }

    public int get_width() {
        return mExportVar_width;
    }

    public Script.FieldID getFieldID_width() {
        return createFieldID(mExportVarIdx_width, null);
    }

    private final static int mExportVarIdx_InPixel = 2;
    private Allocation mExportVar_InPixel;
    public synchronized void set_InPixel(Allocation v) {
        setVar(mExportVarIdx_InPixel, v);
        mExportVar_InPixel = v;
    }

    public Allocation get_InPixel() {
        return mExportVar_InPixel;
    }

    public Script.FieldID getFieldID_InPixel() {
        return createFieldID(mExportVarIdx_InPixel, null);
    }

    private final static int mExportVarIdx_ScratchPixel1 = 3;
    private Allocation mExportVar_ScratchPixel1;
    public synchronized void set_ScratchPixel1(Allocation v) {
        setVar(mExportVarIdx_ScratchPixel1, v);
        mExportVar_ScratchPixel1 = v;
    }

    public Allocation get_ScratchPixel1() {
        return mExportVar_ScratchPixel1;
    }

    public Script.FieldID getFieldID_ScratchPixel1() {
        return createFieldID(mExportVarIdx_ScratchPixel1, null);
    }

    private final static int mExportVarIdx_ScratchPixel2 = 4;
    private Allocation mExportVar_ScratchPixel2;
    public synchronized void set_ScratchPixel2(Allocation v) {
        setVar(mExportVarIdx_ScratchPixel2, v);
        mExportVar_ScratchPixel2 = v;
    }

    public Allocation get_ScratchPixel2() {
        return mExportVar_ScratchPixel2;
    }

    public Script.FieldID getFieldID_ScratchPixel2() {
        return createFieldID(mExportVarIdx_ScratchPixel2, null);
    }

    private final static int mExportVarIdx_MAX_RADIUS = 5;
    private int mExportVar_MAX_RADIUS;
    public final static int const_MAX_RADIUS = 25;
    public int get_MAX_RADIUS() {
        return mExportVar_MAX_RADIUS;
    }

    public Script.FieldID getFieldID_MAX_RADIUS() {
        return createFieldID(mExportVarIdx_MAX_RADIUS, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_copyIn = 1;
    public Script.KernelID getKernelID_copyIn() {
        return createKernelID(mExportForEachIdx_copyIn, 35, null, null);
    }

    public void forEach_copyIn(Allocation ain, Allocation aout) {
        forEach_copyIn(ain, aout, null);
    }

    public void forEach_copyIn(Allocation ain, Allocation aout, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        // check aout
        if (!aout.getType().getElement().isCompatible(__F32_4)) {
            throw new RSRuntimeException("Type mismatch with F32_4!");
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

        forEach(mExportForEachIdx_copyIn, ain, aout, null, sc);
    }

    private final static int mExportForEachIdx_vert = 2;
    public Script.KernelID getKernelID_vert() {
        return createKernelID(mExportForEachIdx_vert, 58, null, null);
    }

    public void forEach_vert(Allocation aout) {
        forEach_vert(aout, null);
    }

    public void forEach_vert(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_vert, (Allocation) null, aout, null, sc);
    }

    private final static int mExportForEachIdx_horz = 3;
    public Script.KernelID getKernelID_horz() {
        return createKernelID(mExportForEachIdx_horz, 58, null, null);
    }

    public void forEach_horz(Allocation aout) {
        forEach_horz(aout, null);
    }

    public void forEach_horz(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__F32_4)) {
            throw new RSRuntimeException("Type mismatch with F32_4!");
        }
        forEach(mExportForEachIdx_horz, (Allocation) null, aout, null, sc);
    }

    private final static int mExportFuncIdx_setRadius = 0;
    public Script.InvokeID getInvokeID_setRadius() {
        return createInvokeID(mExportFuncIdx_setRadius);
    }

    public void invoke_setRadius(int rad) {
        FieldPacker setRadius_fp = new FieldPacker(4);
        setRadius_fp.addI32(rad);
        invoke(mExportFuncIdx_setRadius, setRadius_fp);
    }

}

