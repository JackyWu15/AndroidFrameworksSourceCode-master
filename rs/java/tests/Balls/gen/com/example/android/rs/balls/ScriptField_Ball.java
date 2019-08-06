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
public class ScriptField_Ball extends android.renderscript.Script.FieldBase {
    static public class Item {

        Float2 delta;
        Float2 position;
        Short4 color;
        float pressure;
        int next;

        Item() {
            delta = new Float2();
            position = new Float2();
            color = new Short4();
        }

    }

    private Item mItemArray[];
    private FieldPacker mIOBuffer;
    private static java.lang.ref.WeakReference<Element> mElementCache = new java.lang.ref.WeakReference<Element>(null);
    public static Element createElement(RenderScript rs) {
        Element.Builder eb = new Element.Builder(rs);
        eb.add(Element.F32_2(rs), "delta");
        eb.add(Element.F32_2(rs), "position");
        eb.add(Element.U8_4(rs), "color");
        eb.add(Element.F32(rs), "pressure");
        eb.add(Element.I32(rs), "next");
        return eb.create();
    }

    private  ScriptField_Ball(RenderScript rs) {
        mItemArray = null;
        mIOBuffer = null;
        mElement = createElement(rs);
    }

    public  ScriptField_Ball(RenderScript rs, int count) {
        mItemArray = null;
        mIOBuffer = null;
        mElement = createElement(rs);
        init(rs, count);
    }

    public  ScriptField_Ball(RenderScript rs, int count, int usages) {
        mItemArray = null;
        mIOBuffer = null;
        mElement = createElement(rs);
        init(rs, count, usages);
    }

    public static ScriptField_Ball create1D(RenderScript rs, int dimX, int usages) {
        ScriptField_Ball obj = new ScriptField_Ball(rs);
        obj.mAllocation = Allocation.createSized(rs, obj.mElement, dimX, usages);
        return obj;
    }

    public static ScriptField_Ball create1D(RenderScript rs, int dimX) {
        return create1D(rs, dimX, Allocation.USAGE_SCRIPT);
    }

    public static ScriptField_Ball create2D(RenderScript rs, int dimX, int dimY) {
        return create2D(rs, dimX, dimY, Allocation.USAGE_SCRIPT);
    }

    public static ScriptField_Ball create2D(RenderScript rs, int dimX, int dimY, int usages) {
        ScriptField_Ball obj = new ScriptField_Ball(rs);
        Type.Builder b = new Type.Builder(rs, obj.mElement);
        b.setX(dimX);
        b.setY(dimY);
        Type t = b.create();
        obj.mAllocation = Allocation.createTyped(rs, t, usages);
        return obj;
    }

    public static Type.Builder createTypeBuilder(RenderScript rs) {
        Element e = createElement(rs);
        return new Type.Builder(rs, e);
    }

    public static ScriptField_Ball createCustom(RenderScript rs, Type.Builder tb, int usages) {
        ScriptField_Ball obj = new ScriptField_Ball(rs);
        Type t = tb.create();
        if (t.getElement() != obj.mElement) {
            throw new RSIllegalArgumentException("Type.Builder did not match expected element type.");
        }
        obj.mAllocation = Allocation.createTyped(rs, t, usages);
        return obj;
    }

    private void copyToArrayLocal(Item i, FieldPacker fp) {
        fp.addF32(i.delta);
        fp.addF32(i.position);
        fp.addU8(i.color);
        fp.addF32(i.pressure);
        fp.addI32(i.next);
    }

    private void copyToArray(Item i, int index) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(mElement.getBytesSize() * getType().getX()/* count */);
        mIOBuffer.reset(index * mElement.getBytesSize());
        copyToArrayLocal(i, mIOBuffer);
    }

    public synchronized void set(Item i, int index, boolean copyNow) {
        if (mItemArray == null) mItemArray = new Item[getType().getX() /* count */];
        mItemArray[index] = i;
        if (copyNow)  {
            copyToArray(i, index);
            FieldPacker fp = new FieldPacker(mElement.getBytesSize());
            copyToArrayLocal(i, fp);
            mAllocation.setFromFieldPacker(index, fp);
        }

    }

    public synchronized Item get(int index) {
        if (mItemArray == null) return null;
        return mItemArray[index];
    }

    public synchronized void set_delta(int index, Float2 v, boolean copyNow) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(mElement.getBytesSize() * getType().getX()/* count */);
        if (mItemArray == null) mItemArray = new Item[getType().getX() /* count */];
        if (mItemArray[index] == null) mItemArray[index] = new Item();
        mItemArray[index].delta = v;
        if (copyNow)  {
            mIOBuffer.reset(index * mElement.getBytesSize());
            mIOBuffer.addF32(v);
            FieldPacker fp = new FieldPacker(8);
            fp.addF32(v);
            mAllocation.setFromFieldPacker(index, 0, fp);
        }

    }

    public synchronized void set_position(int index, Float2 v, boolean copyNow) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(mElement.getBytesSize() * getType().getX()/* count */);
        if (mItemArray == null) mItemArray = new Item[getType().getX() /* count */];
        if (mItemArray[index] == null) mItemArray[index] = new Item();
        mItemArray[index].position = v;
        if (copyNow)  {
            mIOBuffer.reset(index * mElement.getBytesSize() + 8);
            mIOBuffer.addF32(v);
            FieldPacker fp = new FieldPacker(8);
            fp.addF32(v);
            mAllocation.setFromFieldPacker(index, 1, fp);
        }

    }

    public synchronized void set_color(int index, Short4 v, boolean copyNow) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(mElement.getBytesSize() * getType().getX()/* count */);
        if (mItemArray == null) mItemArray = new Item[getType().getX() /* count */];
        if (mItemArray[index] == null) mItemArray[index] = new Item();
        mItemArray[index].color = v;
        if (copyNow)  {
            mIOBuffer.reset(index * mElement.getBytesSize() + 16);
            mIOBuffer.addU8(v);
            FieldPacker fp = new FieldPacker(4);
            fp.addU8(v);
            mAllocation.setFromFieldPacker(index, 2, fp);
        }

    }

    public synchronized void set_pressure(int index, float v, boolean copyNow) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(mElement.getBytesSize() * getType().getX()/* count */);
        if (mItemArray == null) mItemArray = new Item[getType().getX() /* count */];
        if (mItemArray[index] == null) mItemArray[index] = new Item();
        mItemArray[index].pressure = v;
        if (copyNow)  {
            mIOBuffer.reset(index * mElement.getBytesSize() + 20);
            mIOBuffer.addF32(v);
            FieldPacker fp = new FieldPacker(4);
            fp.addF32(v);
            mAllocation.setFromFieldPacker(index, 3, fp);
        }

    }

    public synchronized void set_next(int index, int v, boolean copyNow) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(mElement.getBytesSize() * getType().getX()/* count */);
        if (mItemArray == null) mItemArray = new Item[getType().getX() /* count */];
        if (mItemArray[index] == null) mItemArray[index] = new Item();
        mItemArray[index].next = v;
        if (copyNow)  {
            mIOBuffer.reset(index * mElement.getBytesSize() + 24);
            mIOBuffer.addI32(v);
            FieldPacker fp = new FieldPacker(4);
            fp.addI32(v);
            mAllocation.setFromFieldPacker(index, 4, fp);
        }

    }

    public synchronized Float2 get_delta(int index) {
        if (mItemArray == null) return null;
        return mItemArray[index].delta;
    }

    public synchronized Float2 get_position(int index) {
        if (mItemArray == null) return null;
        return mItemArray[index].position;
    }

    public synchronized Short4 get_color(int index) {
        if (mItemArray == null) return null;
        return mItemArray[index].color;
    }

    public synchronized float get_pressure(int index) {
        if (mItemArray == null) return 0;
        return mItemArray[index].pressure;
    }

    public synchronized int get_next(int index) {
        if (mItemArray == null) return 0;
        return mItemArray[index].next;
    }

    public synchronized void copyAll() {
        for (int ct = 0; ct < mItemArray.length; ct++) copyToArray(mItemArray[ct], ct);
        mAllocation.setFromFieldPacker(0, mIOBuffer);
    }

    public synchronized void resize(int newSize) {
        if (mItemArray != null)  {
            int oldSize = mItemArray.length;
            int copySize = Math.min(oldSize, newSize);
            if (newSize == oldSize) return;
            Item ni[] = new Item[newSize];
            System.arraycopy(mItemArray, 0, ni, 0, copySize);
            mItemArray = ni;
        }

        mAllocation.resize(newSize);
        if (mIOBuffer != null) mIOBuffer = new FieldPacker(mElement.getBytesSize() * getType().getX()/* count */);
    }

}

