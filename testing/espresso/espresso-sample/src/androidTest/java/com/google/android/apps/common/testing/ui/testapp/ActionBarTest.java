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
import static com.google.android.apps.common.testing.ui.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.openContextualActionModeOverflowMenu;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates Espresso with action bar and contextual action mode. 
 * {@link openActionBarOverflowOrOptionsMenu()} opens the overflow menu from an action bar.
 * {@link openContextualActionModeOverflowMenu()} opens the overflow menu from an contextual action
 * mode.
 */
@LargeTest
public class ActionBarTest extends ActivityInstrumentationTestCase2<ActionBarTestActivity> {
  @SuppressWarnings("deprecation")
  public ActionBarTest() {
    // This constructor was deprecated - but we want to support lower API levels.
    super("com.google.android.apps.common.testing.ui.testapp", ActionBarTestActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    // Espresso will not launch our activity for us, we must launch it via getActivity().
    getActivity();
  }

  @SuppressWarnings("unchecked")
  public void testClickActionBarItem() {
    onView(withId(R.id.hide_contextual_action_bar))
      .perform(click());

    onView(withId(R.id.action_save))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("Save")));
  }

  @SuppressWarnings("unchecked")
  public void testClickActionModeItem() {
    onView(withId(R.id.show_contextual_action_bar))
      .perform(click());

    onView((withId(R.id.action_lock)))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("Lock")));
  }


  @SuppressWarnings("unchecked")
  public void testActionBarOverflow() {
    onView(withId(R.id.hide_contextual_action_bar))
      .perform(click());

    // Open the overflow menu from action bar
    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(withText("World"))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("World")));
  }

  @SuppressWarnings("unchecked")
  public void testActionModeOverflow() {
    onView(withId(R.id.show_contextual_action_bar))
      .perform(click());

    // Open the overflow menu from contextual action mode.
    openContextualActionModeOverflowMenu();

    onView(withText("Key"))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("Key")));
  }
}
