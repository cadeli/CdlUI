package com.cadeli.uiDemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlView;
import com.cadeli.ui.R;
import com.cadeli.ui.buttons.CdlNStatesButton;
import com.cadeli.ui.interfaces.OnLongPressCdlListener;
import com.cadeli.ui.interfaces.OnScrollCdlListener;
import com.cadeli.ui.interfaces.OnTapUpCdlListener;

public class NstatesBtnDemo extends Activity {

	Context context;
	private CdlView mCdlView;
	private OnTapUpCdlListener onTapUpCdlListener;
	private OnScrollCdlListener onScrollCdlListener;
	private OnLongPressCdlListener onLongPressCdlListener;

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
		onTapUpCdlListener = new OnTapUpCdlListener() {
			@Override
			public void tapUp(CdlBaseButton cdlBaseButton, MotionEvent e) {
				stateButtonPressed((CdlNStatesButton) cdlBaseButton);
			}
		};

		onLongPressCdlListener = new OnLongPressCdlListener() {
			@Override
			public void longPress(CdlBaseButton cdlBaseButton, MotionEvent e) {
				Toast.makeText(getApplicationContext(), "You long clicked the button" + cdlBaseButton.getLabel(), Toast.LENGTH_SHORT).show();
			}
		};

		onScrollCdlListener = new OnScrollCdlListener() {
			@Override
			public void scroll(CdlBaseButton cdlBaseButton, MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
				Toast.makeText(getApplicationContext(), "You scroll the button" + cdlBaseButton.getLabel(), Toast.LENGTH_SHORT).show();
			}
		};

		onTapUpCdlListener = new OnTapUpCdlListener() {
			@Override
			public void tapUp(CdlBaseButton cdlBaseButton, MotionEvent e) {
				Toast.makeText(getApplicationContext(), "You tap up the button" + cdlBaseButton.getLabel(), Toast.LENGTH_SHORT).show();
			}
		};

		// Use View and init
		mCdlView = (CdlView) findViewById(R.id.cdlView1);
		mCdlView.setPadding(4);
		mCdlView.setGrid_nbCols(4); // define nb buttons per row
		mCdlView.setCdlLayoutType(CdlView.CDL_LAYOUT_GRID);
		
		
		List<TestElement> stateValuesForButtons = new ArrayList<TestElement>();
		stateValuesForButtons.add(new TestElement("st1"));
		stateValuesForButtons.add(new TestElement("st2"));
		stateValuesForButtons.add(new TestElement("st3"));
		stateValuesForButtons.add(new TestElement("---"));

		CdlNStatesButton cdlNStatesButton = new CdlNStatesButton("testlist");
		mCdlView.addCdlBaseButton(cdlNStatesButton);
		cdlNStatesButton.setList((List<Object> )(List<?>)stateValuesForButtons);
		cdlNStatesButton.setGridSize(4, 1);
		cdlNStatesButton.setDisplayMode(CdlBaseButton.DISPLAYMODE_WITH_ARROW_BTN);
		cdlNStatesButton.setRound(0.08f);
	}

	protected void stateButtonPressed(CdlNStatesButton cdlNStatesButton) {
		String s = cdlNStatesButton.getLabel() + " set to label " + cdlNStatesButton.getStateTxt() + " state #" + cdlNStatesButton.getState();
	}
}
