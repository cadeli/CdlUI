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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlView;
import com.cadeli.ui.R;
import com.cadeli.ui.buttons.CdlPushButton;
import com.cadeli.ui.interfaces.OnLongPressCdlListener;
import com.cadeli.ui.interfaces.OnTapUpCdlListener;

public class AnotherGridDemo extends Activity {

	Context context;
	private int screenId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_grid_demo);
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
				if (cdlBaseButton.getLabel().contains("3")) {
					((Activity) context).startActivityForResult(new Intent(context, SimpleRowOfVariousButtonsActivity.class), 0);
				}
				if (cdlBaseButton.getLabel().contains("4")) {
					((Activity) context).startActivityForResult(new Intent(context, FakeAudioMixerActivity.class), 0);
				}
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
		context = mCdlView.getContext();
		mCdlView.setPadding(4);
		mCdlView.setGrid_nbCols(4); // define nb buttons per row
		mCdlView.setCdlLayoutType(CdlView.CDL_LAYOUT_GRID);

		// define colorscheme
		// CdlPalette.setColorScheme(CdlPalette.COLORSCHEME4);

		// create push button
		CdlPushButton mCdlPushButton = new CdlPushButton("push1 ");
		// define size for grid
		mCdlPushButton.setGridSize(2, 1);
		mCdlPushButton.setBorder(false);
		mCdlPushButton.setRound(0);
		// add to the view
		mCdlView.addCdlBaseButton(mCdlPushButton,0);

		// create push button
		mCdlPushButton = new CdlPushButton("push2 push2");
		// define size for grid
		mCdlPushButton.setGridSize(2, 1);
		mCdlPushButton.setBorder(false);
		mCdlPushButton.setRound(0);
		// add to the view
		mCdlView.addCdlBaseButton(mCdlPushButton,0);

		// create push button
		mCdlPushButton = new CdlPushButton("push3");
		// define size for grid
		mCdlPushButton.setGridSize(4, 1);
		mCdlPushButton.setBorder(false);
		mCdlPushButton.setRound(0);
		// add to the view
		mCdlView.addCdlBaseButton(mCdlPushButton,0);

		// create push button
		mCdlPushButton = new CdlPushButton("push4");
		// define size for grid
		mCdlPushButton.setGridSize(1, 1);
		// add to the view
		mCdlPushButton.setBorder(false);
		mCdlPushButton.setRound(0);
		mCdlView.addCdlBaseButton(mCdlPushButton,0);

		// create push button
		mCdlPushButton = new CdlPushButton("push5");
		// define size for grid
		mCdlPushButton.setGridSize(3, 1);
		// add to the view
		mCdlPushButton.setBorder(false);
		mCdlPushButton.setRound(0);
		mCdlView.addCdlBaseButton(mCdlPushButton,0);

	}

}
