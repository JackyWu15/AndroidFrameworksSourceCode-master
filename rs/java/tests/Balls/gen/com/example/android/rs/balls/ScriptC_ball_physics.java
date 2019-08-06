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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\Balls\\src\\com\\example\\android\\rs\\balls\\ball_physics.rs
 */

package com.example.android.rs.balls;

import android.os.Build;
import android.os.Process;
import java.lang.reflect.Field;
import android.renderscript.*;
import com.example.android.rs.balls.ball_physicsBitCode;

/**
 * @hide
 */
public class ScriptC_ball_physics extends ScriptC {
    private static final String __rs_resource_name = "ball_physics";
    // Constructor
    public  ScriptC_ball_physics(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              ball_physicsBitCode.getBitCode32(),
              ball_physicsBitCode.getBitCode64());
        mExportVar_gGravityVector = new Float2();
        mExportVar_gGravityVector.x = 0.f;
        mExportVar_gGravityVector.y = 9.80000019f;
        __F32_2 = Element.F32_2(rs);
        mExportVar_gMinPos = new Float2();
        mExportVar_gMinPos.x = 0.f;
        mExportVar_gMinPos.y = 0.f;
        mExportVar_gMaxPos = new Float2();
        mExportVar_gMaxPos.x = 1280.f;
        mExportVar_gMaxPos.y = 700.f;
        __ALLOCATION = Element.ALLOCATION(rs);
        mExportVar_gScale = 1.f;
        __F32 = Element.F32(rs);
        __ScriptField_Ball = ScriptField_Ball.createElement(rs);
    }

    private Element __ALLOCATION;
    private Element __F32;
    private Element __F32_2;
    private Element __ScriptField_Ball;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_F32;
    private FieldPacker __rs_fp_F32_2;
    private final static int mExportVarIdx_balls = 0;
    private ScriptField_Ball mExportVar_balls;
    public void bind_balls(ScriptField_Ball v) {
        mExportVar_balls = v;
        if (v == null) bindAllocation(null, mExportVarIdx_balls);
        else bindAllocation(v.getAllocation(), mExportVarIdx_balls);
    }

    public ScriptField_Ball get_balls() {
        return mExportVar_balls;
    }

    private final static int mExportVarIdx_gGravityVector = 1;
    private Float2 mExportVar_gGravityVector;
    public synchronized void set_gGravityVector(Float2 v) {
        mExportVar_gGravityVector = v;
        FieldPacker fp = new FieldPacker(8);
        fp.addF32(v);
        int []__dimArr = new int[1];
        __dimArr[0] = 1;
        setVar(mExportVarIdx_gGravityVector, fp, __F32_2, __dimArr);
    }

    public Float2 get_gGravityVector() {
        return mExportVar_gGravityVector;
    }

    public Script.FieldID getFieldID_gGravityVector() {
        return createFieldID(mExportVarIdx_gGravityVector, null);
    }

    private final static int mExportVarIdx_gMinPos = 2;
    private Float2 mExportVar_gMinPos;
    public synchronized void set_gMinPos(Float2 v) {
        mExportVar_gMinPos = v;
        FieldPacker fp = new FieldPacker(8);
        fp.addF32(v);
        int []__dimArr = new int[1];
        __dimArr[0] = 1;
        setVar(mExportVarIdx_gMinPos, fp, __F32_2, __dimArr);
    }

    public Float2 get_gMinPos() {
        return mExportVar_gMinPos;
    }

    public Script.FieldID getFieldID_gMinPos() {
        return createFieldID(mExportVarIdx_gMinPos, null);
    }

    private final static int mExportVarIdx_gMaxPos = 3;
    private Float2 mExportVar_gMaxPos;
    public synchronized void set_gMaxPos(Float2 v) {
        mExportVar_gMaxPos = v;
        FieldPacker fp = new FieldPacker(8);
        fp.addF32(v);
        int []__dimArr = new int[1];
        __dimArr[0] = 1;
        setVar(mExportVarIdx_gMaxPos, fp, __F32_2, __dimArr);
    }

    public Float2 get_gMaxPos() {
        return mExportVar_gMaxPos;
    }

    public Script.FieldID getFieldID_gMaxPos() {
        return createFieldID(mExportVarIdx_gMaxPos, null);
    }

    private final static int mExportVarIdx_gGrid = 4;
    private Allocation mExportVar_gGrid;
    public synchronized void set_gGrid(Allocation v) {
        setVar(mExportVarIdx_gGrid, v);
        mExportVar_gGrid = v;
    }

    public Allocation get_gGrid() {
        return mExportVar_gGrid;
    }

    public Script.FieldID getFieldID_gGrid() {
        return createFieldID(mExportVarIdx_gGrid, null);
    }

    private final static int mExportVarIdx_gGridCache = 5;
    private Allocation mExportVar_gGridCache;
    public synchronized void set_gGridCache(Allocation v) {
        setVar(mExportVarIdx_gGridCache, v);
        mExportVar_gGridCache = v;
    }

    public Allocation get_gGridCache() {
        return mExportVar_gGridCache;
    }

    public Script.FieldID getFieldID_gGridCache() {
        return createFieldID(mExportVarIdx_gGridCache, null);
    }

    private final static int mExportVarIdx_gBalls = 6;
    private Allocation mExportVar_gBalls;
    public synchronized void set_gBalls(Allocation v) {
        setVar(mExportVarIdx_gBalls, v);
        mExportVar_gBalls = v;
    }

    public Allocation get_gBalls() {
        return mExportVar_gBalls;
    }

    public Script.FieldID getFieldID_gBalls() {
        return createFieldID(mExportVarIdx_gBalls, null);
    }

    private final static int mExportVarIdx_gScale = 7;
    private float mExportVar_gScale;
    public synchronized void set_gScale(float v) {
        setVar(mExportVarIdx_gScale, v);
        mExportVar_gScale = v;
    }

    public float get_gScale() {
        return mExportVar_gScale;
    }

    public Script.FieldID getFieldID_gScale() {
        return createFieldID(mExportVarIdx_gScale, null);
    }

    private final static int mExportForEachIdx_root = 0;
    public Script.KernelID getKernelID_root() {
        return createKernelID(mExportForEachIdx_root, 10, null, null);
    }

    public void forEach_root(Allocation aout) {
        forEach_root(aout, null);
    }

    public void forEach_root(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__ScriptField_Ball)) {
            throw new RSRuntimeException("Type mismatch with ScriptField_Ball!");
        }
        forEach(mExportForEachIdx_root, (Allocation) null, aout, null, sc);
    }

    private final static int mExportFuncIdx_touch = 0;
    public Script.InvokeID getInvokeID_touch() {
        return createInvokeID(mExportFuncIdx_touch);
    }

    public void invoke_touch(float x, float y, float pressure, int id) {
        FieldPacker touch_fp = new FieldPacker(16);
        touch_fp.addF32(x);
        touch_fp.addF32(y);
        touch_fp.addF32(pressure);
        touch_fp.addI32(id);
        invoke(mExportFuncIdx_touch, touch_fp);
    }

}

