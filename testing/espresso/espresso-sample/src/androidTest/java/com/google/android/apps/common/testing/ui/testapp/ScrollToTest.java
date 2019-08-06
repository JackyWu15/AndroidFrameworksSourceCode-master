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

package com.google.android.apps.common.testing.ui.testapp;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.scrollTo;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;


/**
 * Demonstrates the usage of
 * {@link com.google.android.apps.common.testing.ui.espresso.action.ViewActions#scrollTo()}.
 */
@LargeTest
public class ScrollToTest extends ActivityInstrumentationTestCase2<ScrollActivity> {

  @SuppressWarnings("deprecation")
  public ScrollToTest() {
    // This constructor was deprecated - but we want to support lower API levels.
    super("com.google.android.apps.common.testing.ui.testapp", ScrollActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    // Espresso will not launch our activity for us, we must launch it via getActivity().
    getActivity();
  }

  // You can pass more than one action to perform. This is useful if you are performing two actions
  // back-to-back on the same view.
  // Note - scrollTo is a no-op if the view is already displayed on the screen.
  public void testScrollToInScrollView() {
    onView(withId(is(R.id.bottom_left)))
      .perform(scrollTo(), click());
  }
}
