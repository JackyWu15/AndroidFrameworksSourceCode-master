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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\SampleTest\\src\\com\\android\\rs\\sample\\sample.rs
 */

package com.android.rs.sample;

import android.os.Build;
import android.os.Process;

import com.android.rs.sample.sampleBitCode;

import java.lang.reflect.Field;

/**
 * @hide
 */
public class ScriptC_sample extends ScriptC {
    private static final String __rs_resource_name = "sample";
    // Constructor
    public  ScriptC_sample(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              sampleBitCode.getBitCode32(),
              sampleBitCode.getBitCode64());
        __U8_4 = Element.U8_4(rs);
    }

    private Element __U8_4;
    private final static int mExportForEachIdx_root = 0;
    public Script.KernelID getKernelID_root() {
        return createKernelID(mExportForEachIdx_root, 26, null, null);
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

    private final static int mExportFuncIdx_setSampleData = 0;
    public Script.InvokeID getInvokeID_setSampleData() {
        return createInvokeID(mExportFuncIdx_setSampleData);
    }

    public void invoke_setSampleData(Allocation dest, Allocation source, Sampler sampler) {
        FieldPacker setSampleData_fp = new FieldPacker(sIs64Bit ? 96 : 12);
        setSampleData_fp.addObj(dest);
        setSampleData_fp.addObj(source);
        setSampleData_fp.addObj(sampler);
        invoke(mExportFuncIdx_setSampleData, setSampleData_fp);
    }

    private static boolean sIs64Bit;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sIs64Bit = Process.is64Bit();
        }

        else {
            try {
                Field f = RenderScript.class.getDeclaredField("sPointerSize");
                f.setAccessible(true);
                sIs64Bit = (f.getInt(null) == 8);
            }

            catch (Throwable e) {
                sIs64Bit = false;
            }

        }

    }

}

