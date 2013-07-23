package com.cadeli.uiDemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlPalette;
import com.cadeli.ui.CdlView;
import com.cadeli.ui.R;
import com.cadeli.ui.buttons.CdlNStatesButton;
import com.cadeli.ui.buttons.CdlOnOffButton;
import com.cadeli.ui.controls.CdlFader;
import com.cadeli.ui.controls.CdlKnob;
import com.cadeli.ui.interfaces.OnLongPressCdlListener;
import com.cadeli.ui.interfaces.OnTapUpCdlListener;

public class FakeAudioMixerActivity extends Activity {

	private int colorIndexForBlack;

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
		
		//add black color to palette
		CdlPalette.addColor(Color.rgb(0, 0, 0));
		//retrieve color index
		 colorIndexForBlack = CdlPalette.getLastColorIndex();

		// Use View and init
		CdlView mCdlView = (CdlView) findViewById(R.id.cdlView2);
		mCdlView.setPadding(4);
		mCdlView.setGrid_nbCols(6); // define nb buttons per row
		mCdlView.setCdlLayout(CdlView.CDL_LAYOUT_GRID);

		// Create push buttons
		createStrips(mCdlView, onTapUpCdlListener, onLongPressCdlListener);

	}

	private void createStrips(CdlView mCdlView, OnTapUpCdlListener onTapUpCdlListener, OnLongPressCdlListener onLongPressCdlListener) {
		// create push button
		for (int i = 0; i < 6; i++) {
			CdlOnOffButton mCdlButton = new CdlOnOffButton("btn_" + (i + 1));
			mCdlButton.setBorder(false);
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(4);
		}
		// create fader button
		for (int i = 0; i < 6; i++) {
			CdlFader mCdlButton = new CdlFader("fader " + (i + 1));
			mCdlButton.setGridSize(1, 3 );
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(4);
		}		
		// create knob button
		for (int i = 0; i < 12; i++) {
			CdlKnob mCdlButton = new CdlKnob("knob " + (i + 1));
			mCdlButton.getValueControler().setValues(0, 1, (float)(i*1.3)/12.0f);
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(colorIndexForBlack);
		}
		for (int i = 0; i < 6; i++) {
			CdlNStatesButton mCdlButton = new CdlNStatesButton("aaa");
			mCdlButton.addState("st1");
			mCdlButton.addState("st2");
			mCdlButton.addState("st3");
			mCdlButton.addState("---");
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(colorIndexForBlack);
		}

	}

}
