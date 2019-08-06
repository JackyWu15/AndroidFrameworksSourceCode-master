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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\histogram.rs
 */

package com.android.rs.image;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.android.rs.image.histogramBitCode;

/**
 * @hide
 */
public class ScriptC_histogram extends ScriptC {
    private static final String __rs_resource_name = "histogram";
    // Constructor
    public  ScriptC_histogram(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              histogramBitCode.getBitCode32(),
              histogramBitCode.getBitCode64());
        __ALLOCATION = Element.ALLOCATION(rs);
        __I32 = Element.I32(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __ALLOCATION;
    private Element __I32;
    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_gSrc = 0;
    private Allocation mExportVar_gSrc;
    public synchronized void set_gSrc(Allocation v) {
        setVar(mExportVarIdx_gSrc, v);
        mExportVar_gSrc = v;
    }

    public Allocation get_gSrc() {
        return mExportVar_gSrc;
    }

    public Script.FieldID getFieldID_gSrc() {
        return createFieldID(mExportVarIdx_gSrc, null);
    }

    private final static int mExportVarIdx_gDest = 1;
    private Allocation mExportVar_gDest;
    public synchronized void set_gDest(Allocation v) {
        setVar(mExportVarIdx_gDest, v);
        mExportVar_gDest = v;
    }

    public Allocation get_gDest() {
        return mExportVar_gDest;
    }

    public Script.FieldID getFieldID_gDest() {
        return createFieldID(mExportVarIdx_gDest, null);
    }

    private final static int mExportVarIdx_gSums = 2;
    private Allocation mExportVar_gSums;
    public synchronized void set_gSums(Allocation v) {
        setVar(mExportVarIdx_gSums, v);
        mExportVar_gSums = v;
    }

    public Allocation get_gSums() {
        return mExportVar_gSums;
    }

    public Script.FieldID getFieldID_gSums() {
        return createFieldID(mExportVarIdx_gSums, null);
    }

    private final static int mExportVarIdx_gSum = 3;
    private Allocation mExportVar_gSum;
    public synchronized void set_gSum(Allocation v) {
        setVar(mExportVarIdx_gSum, v);
        mExportVar_gSum = v;
    }

    public Allocation get_gSum() {
        return mExportVar_gSum;
    }

    public Script.FieldID getFieldID_gSum() {
        return createFieldID(mExportVarIdx_gSum, null);
    }

    private final static int mExportVarIdx_gWidth = 4;
    private int mExportVar_gWidth;
    public synchronized void set_gWidth(int v) {
        setVar(mExportVarIdx_gWidth, v);
        mExportVar_gWidth = v;
    }

    public int get_gWidth() {
        return mExportVar_gWidth;
    }

    public Script.FieldID getFieldID_gWidth() {
        return createFieldID(mExportVarIdx_gWidth, null);
    }

    private final static int mExportVarIdx_gHeight = 5;
    private int mExportVar_gHeight;
    public synchronized void set_gHeight(int v) {
        setVar(mExportVarIdx_gHeight, v);
        mExportVar_gHeight = v;
    }

    public int get_gHeight() {
        return mExportVar_gHeight;
    }

    public Script.FieldID getFieldID_gHeight() {
        return createFieldID(mExportVarIdx_gHeight, null);
    }

    private final static int mExportVarIdx_gStep = 6;
    private int mExportVar_gStep;
    public synchronized void set_gStep(int v) {
        setVar(mExportVarIdx_gStep, v);
        mExportVar_gStep = v;
    }

    public int get_gStep() {
        return mExportVar_gStep;
    }

    public Script.FieldID getFieldID_gStep() {
        return createFieldID(mExportVarIdx_gStep, null);
    }

    private final static int mExportVarIdx_gSteps = 7;
    private int mExportVar_gSteps;
    public synchronized void set_gSteps(int v) {
        setVar(mExportVarIdx_gSteps, v);
        mExportVar_gSteps = v;
    }

    public int get_gSteps() {
        return mExportVar_gSteps;
    }

    public Script.FieldID getFieldID_gSteps() {
        return createFieldID(mExportVarIdx_gSteps, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_pass1 = 1;
    public Script.KernelID getKernelID_pass1() {
        return createKernelID(mExportForEachIdx_pass1, 57, null, null);
    }

    public void forEach_pass1(Allocation ain) {
        forEach_pass1(ain, null);
    }

    public void forEach_pass1(Allocation ain, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__I32)) {
            throw new RSRuntimeException("Type mismatch with I32!");
        }
        forEach(mExportForEachIdx_pass1, ain, null, null, sc);
    }

    private final static int mExportForEachIdx_pass2 = 2;
    public Script.KernelID getKernelID_pass2() {
        return createKernelID(mExportForEachIdx_pass2, 42, null, null);
    }

    public void forEach_pass2(Allocation aout) {
        forEach_pass2(aout, null);
    }

    public void forEach_pass2(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__I32)) {
            throw new RSRuntimeException("Type mismatch with I32!");
        }
        forEach(mExportForEachIdx_pass2, (Allocation) null, aout, null, sc);
    }

    private final static int mExportForEachIdx_clear = 3;
    public Script.KernelID getKernelID_clear() {
        return createKernelID(mExportForEachIdx_clear, 34, null, null);
    }

    public void forEach_clear(Allocation aout) {
        forEach_clear(aout, null);
    }

    public void forEach_clear(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_clear, (Allocation) null, aout, null, sc);
    }

    private final static int mExportForEachIdx_draw = 4;
    public Script.KernelID getKernelID_draw() {
        return createKernelID(mExportForEachIdx_draw, 58, null, null);
    }

    public void forEach_draw(Allocation aout) {
        forEach_draw(aout, null);
    }

    public void forEach_draw(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_draw, (Allocation) null, aout, null, sc);
    }

    private final static int mExportFuncIdx_rescale = 0;
    public Script.InvokeID getInvokeID_rescale() {
        return createInvokeID(mExportFuncIdx_rescale);
    }

    public void invoke_rescale() {
        invoke(mExportFuncIdx_rescale);
    }

}

