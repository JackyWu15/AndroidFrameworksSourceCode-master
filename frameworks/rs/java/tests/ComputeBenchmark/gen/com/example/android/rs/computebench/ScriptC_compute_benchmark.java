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
 * The source Renderscript file: K:\\AndroidFrameworksSourceCode-master\\rs\\java\\tests\\ComputeBenchmark\\src\\com\\example\\android\\rs\\computebench\\compute_benchmark.rs
 */

package com.example.android.rs.computebench;

import com.example.android.rs.computebench.compute_benchmarkBitCode;

/**
 * @hide
 */
public class ScriptC_compute_benchmark extends ScriptC {
    private static final String __rs_resource_name = "compute_benchmark";
    // Constructor
    public  ScriptC_compute_benchmark(RenderScript rs) {
        super(rs,
              __rs_resource_name,
              compute_benchmarkBitCode.getBitCode32(),
              compute_benchmarkBitCode.getBitCode64());
        mExportVar_priming_runs = 1000000;
        __U32 = Element.U32(rs);
        mExportVar_timing_runs = 5000000;
    }

    private Element __U32;
    private FieldPacker __rs_fp_U32;
    private final static int mExportVarIdx_priming_runs = 0;
    private long mExportVar_priming_runs;
    public synchronized void set_priming_runs(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_priming_runs, __rs_fp_U32);
        mExportVar_priming_runs = v;
    }

    public long get_priming_runs() {
        return mExportVar_priming_runs;
    }

    public Script.FieldID getFieldID_priming_runs() {
        return createFieldID(mExportVarIdx_priming_runs, null);
    }

    private final static int mExportVarIdx_timing_runs = 1;
    private long mExportVar_timing_runs;
    public synchronized void set_timing_runs(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_timing_runs, __rs_fp_U32);
        mExportVar_timing_runs = v;
    }

    public long get_timing_runs() {
        return mExportVar_timing_runs;
    }

    public Script.FieldID getFieldID_timing_runs() {
        return createFieldID(mExportVarIdx_timing_runs, null);
    }

    private final static int mExportFuncIdx_bench = 0;
    public Script.InvokeID getInvokeID_bench() {
        return createInvokeID(mExportFuncIdx_bench);
    }

    public void invoke_bench() {
        invoke(mExportFuncIdx_bench);
    }

}

