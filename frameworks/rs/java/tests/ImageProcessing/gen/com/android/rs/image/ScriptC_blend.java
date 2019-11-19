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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ImageProcessing\\src\\com\\android\\rs\\image\\blend.rs
 */

package com.android.rs.image;

import com.android.rs.image.blendBitCode;

/**
 * @hide
 */
public class ScriptC_blend extends ScriptC {
    private static final String __rs_resource_name = "blend";
    // Constructor
    public  ScriptC_blend(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              blendBitCode.getBitCode32(),
              blendBitCode.getBitCode64());
        mExportVar_alpha = 0;
        __U8 = Element.U8(rs);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __U8;
    private Element __U8_4;
    private FieldPacker __rs_fp_U8;
    private final static int mExportVarIdx_alpha = 0;
    private short mExportVar_alpha;
    public synchronized void set_alpha(short v) {
        if (__rs_fp_U8!= null) {
            __rs_fp_U8.reset();
        } else {
            __rs_fp_U8 = new FieldPacker(1);
        }
        __rs_fp_U8.addU8(v);
        setVar(mExportVarIdx_alpha, __rs_fp_U8);
        mExportVar_alpha = v;
    }

    public short get_alpha() {
        return mExportVar_alpha;
    }

    public Script.FieldID getFieldID_alpha() {
        return createFieldID(mExportVarIdx_alpha, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_setImageAlpha = 1;
    public Script.KernelID getKernelID_setImageAlpha() {
        return createKernelID(mExportForEachIdx_setImageAlpha, 26, null, null);
    }

    public void forEach_setImageAlpha(Allocation aout) {
        forEach_setImageAlpha(aout, null);
    }

    public void forEach_setImageAlpha(Allocation aout, Script.LaunchOptions sc) {
        // check aout
        if (!aout.getType().getElement().isCompatible(__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        forEach(mExportForEachIdx_setImageAlpha, (Allocation) null, aout, null, sc);
    }

}

