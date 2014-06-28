/*
Copyright 2013 Cadeli
Contact the authors at cadeli.drummachine@yahoo.com
See updates at http://github.com/cadeli/CdlUI

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.cadeli.uiDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlView;
import com.cadeli.ui.R;
import com.cadeli.ui.buttons.CdlOnOffButton;
import com.cadeli.ui.interfaces.OnLongPressCdlListener;
import com.cadeli.ui.interfaces.OnTapUpCdlListener;

public class SimpleRowOfVariousButtonsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_row_demo);
		// ---
		createCdlGrid();
		// ---
	}

	private void createCdlGrid() {
		// Create callback
		OnTapUpCdlListener onTapUpCdlListener = new OnTapUpCdlListener() {
			@Override
			public void tapUp(CdlBaseButton cdlBaseButton, MotionEvent e) {
				Toast.makeText(getApplicationContext(), "You clicked the button" + cdlBaseButton.getLabel(), Toast.LENGTH_SHORT).show();
			}
		};

		OnLongPressCdlListener onLongPressCdlListener = new OnLongPressCdlListener() {
			@Override
			public void longPress(CdlBaseButton cdlBaseButton, MotionEvent e) {
				Toast.makeText(getApplicationContext(), "You long clicked the button" + cdlBaseButton.getLabel(), Toast.LENGTH_SHORT).show();
			}
		};

		// Use View and init
		CdlView mCdlView = (CdlView) findViewById(R.id.cdlView1);
		mCdlView.setPadding(4);
		mCdlView.setGrid_nbCols(6); // define nb buttons per row
		mCdlView.setCdlLayoutType(CdlView.CDL_LAYOUT_FLOW);

		// Create push buttons
		for (int i = 0; i < 12; i++) {
			createCdlButton(i, mCdlView, onTapUpCdlListener, onLongPressCdlListener);
		}
	}

	private void createCdlButton(int i, CdlView mCdlView, OnTapUpCdlListener onTapUpCdlListener, OnLongPressCdlListener onLongPressCdlListener) {
		// create push button
		CdlOnOffButton mCdlButton = new CdlOnOffButton("btn " + (i + 1));
		// in this sample, we use same listeners for all buttons
		mCdlButton.setOnTapUpCdlListener(onTapUpCdlListener);
		mCdlButton.setOnLongPressCdlListener(onLongPressCdlListener);
		// add to the view
		mCdlView.addCdlBaseButton(mCdlButton,0);
		// change color
		int colorPaletteIndex = 0;
		mCdlButton.setBackgroundColor(colorPaletteIndex); // same color for all the buttons
	}

}
