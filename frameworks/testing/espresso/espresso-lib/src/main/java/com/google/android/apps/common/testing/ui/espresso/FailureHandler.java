/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.common.testing.ui.espresso;

import android.view.View;

import org.hamcrest.Matcher;



/**
 * Handles failures that happen during test execution.
 */
public interface FailureHandler {

  /**
   * Handle the given error in a manner that makes sense to the environment in which the test is
   * executed (e.g. take a screenshot, output extra debug info, etc). Upon handling, most handlers
   * will choose to propagate the error.
   */
  public void handle(Throwable error, Matcher<View> viewMatcher);

}
