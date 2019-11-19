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

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Displays a counter using fragments.
 */
public class FragmentStack extends Activity {
  int stackLevel = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_stack);

    // Watch for button clicks.
    Button button = (Button) findViewById(R.id.new_fragment);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        addFragmentToStack();
      }
    });

    if (savedInstanceState == null) {
      // Do first time initialization -- add initial fragment.
      Fragment newFragment = CountingFragment.newInstance(stackLevel);
      FragmentTransaction ft = getFragmentManager().beginTransaction();
      ft.add(R.id.simple_fragment, newFragment).commit();
    } else {
      stackLevel = savedInstanceState.getInt("level");
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("level", stackLevel);
  }


  void addFragmentToStack() {
    stackLevel++;

    // Instantiate a new fragment.
    Fragment newFragment = CountingFragment.newInstance(stackLevel);

    // Add the fragment to the activity, pushing this transaction
    // on to the back stack.
    FragmentTransaction ft = getFragmentManager().beginTransaction();
    ft.replace(R.id.simple_fragment, newFragment);
    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    ft.addToBackStack(null);
    ft.commit();
  }



  /**
   * A fragment that displays a number.
   */
  public static class CountingFragment extends Fragment {
    int counter;

    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    static CountingFragment newInstance(int num) {
      CountingFragment f = new CountingFragment();

      // Supply num input as an argument.
      Bundle args = new Bundle();
      args.putInt("num", num);
      f.setArguments(args);

      return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      counter = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      TextView text = new TextView(getActivity());
      text.setText("Fragment #" + counter);
      return text;
    }
  }

}
