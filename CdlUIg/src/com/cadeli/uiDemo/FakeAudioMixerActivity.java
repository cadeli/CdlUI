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
import com.cadeli.ui.interfaces.OnScrollCdlListener;
import com.cadeli.ui.interfaces.OnTapUpCdlListener;

public class FakeAudioMixerActivity extends Activity {

	private int colorIndexForBlack;
	private OnScrollCdlListener onScrollCdlFaderListener;
	private CdlOnOffButton mStatusButton;
	private OnScrollCdlListener onScrollCdlKnobListener;
	private CdlView mCdlView;
	private OnTapUpCdlListener onTapUpCdlListener;

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
		onTapUpCdlListener = new OnTapUpCdlListener() {
			@Override
			public void tapUp(CdlBaseButton cdlBaseButton, MotionEvent e) {
				stateButtonPressed((CdlNStatesButton) cdlBaseButton);
			}
		};

		OnLongPressCdlListener onLongPressCdlListener = new OnLongPressCdlListener() {
			@Override
			public void longPress(CdlBaseButton cdlBaseButton, MotionEvent e) {
				Toast.makeText(getApplicationContext(), "You long clicked the button" + cdlBaseButton.getLabel(), Toast.LENGTH_SHORT).show();
			}
		};

		onScrollCdlFaderListener = new OnScrollCdlListener() {
			@Override
			public void scroll(CdlBaseButton cdlBaseButton, MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
				faderValueChanged((CdlFader) cdlBaseButton);
			}
		};

		onScrollCdlKnobListener = new OnScrollCdlListener() {
			@Override
			public void scroll(CdlBaseButton cdlBaseButton, MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
				knobValueChanged((CdlKnob) cdlBaseButton);
			}
		};

		// add black color to palette
		CdlPalette.addColor(Color.rgb(0, 128, 0));
		// retrieve color index
		colorIndexForBlack = CdlPalette.getLastColorIndex();

		// Use View and init
		mCdlView = (CdlView) findViewById(R.id.cdlView2);
		mCdlView.setPadding(4);
		mCdlView.setGrid_nbCols(6); // define nb buttons per row
		mCdlView.setCdlLayout(CdlView.CDL_LAYOUT_GRID);

		// Create push buttons
		createStrips();
	}

	protected void stateButtonPressed(CdlNStatesButton cdlNStatesButton) {
		String s = cdlNStatesButton.getLabel() + " set to label " + cdlNStatesButton.getStateTxt() + " state #" + cdlNStatesButton.getState();
		mStatusButton.setLabel(s);
		mStatusButton.setBackgroundColor(4);
		// trigg action
		if (cdlNStatesButton.getId() == 25 && cdlNStatesButton.getState() == 3) {
			for (int id = 7; id <= 12; id++) {
				CdlFader cdlFader = (CdlFader) mCdlView.getButtonFromId(id);
				cdlFader.getValueControler().setValue((id - 7) / 6.0);
			}
		}
		// trigg action
		if (cdlNStatesButton.getId() == 25 && cdlNStatesButton.getState() == 2) {
			for (int id = 7; id <= 12; id++) {
				CdlFader cdlFader = (CdlFader) mCdlView.getButtonFromId(id);
				cdlFader.getValueControler().setValue((12-id ) / 6.0);
			}
		}
		// trigg action
		if (cdlNStatesButton.getId() == 25 && cdlNStatesButton.getState() == 1) {
			for (int id = 7; id <= 12; id++) {
				CdlFader cdlFader = (CdlFader) mCdlView.getButtonFromId(id);
				cdlFader.getValueControler().setValue(0.4);
			}
		}
	}

	protected void faderValueChanged(CdlFader cdlFader) {
		String s = cdlFader.getLabel() + " set to " + cdlFader.getValueControler().getValue();
		mStatusButton.setLabel(s);
		mStatusButton.setBackgroundColor(3);
	}

	private void knobValueChanged(CdlKnob cdlKnob) {
		String s = cdlKnob.getLabel() + " set to " + cdlKnob.getValueControler().getValue();
		mStatusButton.setLabel(s);
		mStatusButton.setBackgroundColor(4);
	}

	private void createStrips() {
		// create push button
		{
			mStatusButton = new CdlOnOffButton("");
			mStatusButton.setBorder(false);
			mCdlView.addCdlBaseButton(mStatusButton);
			mStatusButton.setBackgroundColor(1);
			mStatusButton.setGridSize(6, 1);
		}
		// create push button
		for (int i = 0; i < 6; i++) {
			CdlOnOffButton mCdlButton = new CdlOnOffButton("btn_" + (i + 1));
			mCdlButton.setBorder(false);
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(1);
		}
		// create fader button
		for (int i = 0; i < 6; i++) {
			CdlFader mCdlButton = new CdlFader("fader_" + (i + 1));
			mCdlButton.setGridSize(1, 3);
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(0);
			mCdlButton.setOnScrollCdlListener(onScrollCdlFaderListener);
		}
		// create knob button
		for (int i = 0; i < 12; i++) {
			CdlKnob mCdlButton = new CdlKnob("knob_" + (i + 1));
			mCdlButton.getValueControler().setValues(0, 1, (float) (i * 1.3) / 12.0f);
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(0);
			mCdlButton.setOnScrollCdlListener(onScrollCdlKnobListener);
		}
		// create multistates buttons
		for (int i = 0; i < 6; i++) {
			CdlNStatesButton mCdlButton = new CdlNStatesButton("aaa");
			mCdlButton.addState("st1");
			mCdlButton.addState("st2");
			mCdlButton.addState("st3");
			mCdlButton.addState("---");
			mCdlView.addCdlBaseButton(mCdlButton);
			mCdlButton.setBackgroundColor(colorIndexForBlack);
			mCdlButton.setOnTapUpCdlListener(onTapUpCdlListener);
		}
	}
}
