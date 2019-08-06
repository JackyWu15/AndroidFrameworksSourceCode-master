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

package com.google.android.apps.common.testing.ui.espresso.action;

import android.view.View;

/**
 * Interface to implement calculation of Coordinates.
 */
public interface CoordinatesProvider {
  
  /**
   * Calculates coordinates of given view.
   * 
   * @param view the View which is used for the calculation. 
   * @return a float[] with x and y values of the calculated coordinates.  
   */
  public float[] calculateCoordinates(View view);
}
