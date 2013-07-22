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

package com.cadeli.ui.buttons;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlPalette;

public class CdlNStatesButton extends CdlBaseButton {
	private static final String TAG = "CdlNStatesButton";
	private List<String> stateValues = new ArrayList<String>();
	private String stateTxt;
	private int state;
	private int defaultState = 0;

	public CdlNStatesButton(String title) {
		super();
		init(title);
	}

	public CdlNStatesButton() {
		super();
		init("default_title");
	}

	private void init(String label) {
		setSize(10, 10, 20, 20);
		defaultState = 0;
		state = defaultState;
		this.label = label;
		this.stateTxt = "no_value";
	}

	public void draw(Canvas canvas) {
		if (isVisible()) {
			super.draw(canvas);
			if (displayMode == CdlBaseButton.DISPLAYMODE_COMPACT) {
				drawCompact(canvas);
			}
			if (displayMode == CdlBaseButton.DISPLAYMODE_LIST) {
				drawList(canvas);
			}
			if (displayMode == CdlBaseButton.DISPLAYMODE_EXPANDED) {
				drawExpanded(canvas);
			}
		}
	}

	private void drawList(Canvas canvas) {
		if (stateValues.size() == 0)
			return;
		int h_case = h / stateValues.size();
		for (int i = 0; i < stateValues.size(); i++) {
			rectf.set(padding + rect.left, 2 * padding + rect.top + i * h_case, rect.right - padding, (i + 1) * h_case + rect.top);
			if (i == getState()) {
				canvas.drawRoundRect(rectf, round_w, round_h, CdlPalette.getHilightPaint());
			}
			drawCenterTextInrectCase(canvas, stateValues.get(i), CdlPalette.getTxtPaint(w-2*padding, h-2*padding));
		}
	}

	private void drawExpanded(Canvas canvas) {
		if (stateValues.size() == 0)
			return;
		int w_case = (w -2*padding)/ stateValues.size();
		for (int i = 0; i < stateValues.size(); i++) {
			rectf.set(i * w_case + padding, rect.top, i * w_case + w_case, rect.bottom);
			if (i == getState()) {
				canvas.drawRect(rectf, CdlPalette.getHilightPaint());
			} else {
				canvas.drawRect(rectf, CdlPalette.getPaint(backgroundColor,getLeft(),getTop(),w,h));
			}
			drawCenterTextInrectCase(canvas, stateValues.get(i), CdlPalette.getTxtPaint(w_case, h-2*padding));
		}
	}

	private void drawCompact(Canvas canvas) {
		rectf.set(rect.left + 2 * padding, rect.top + 2 * padding, rect.right - 2 * padding, rect.centerY() - 2 * padding);
		// CdlUtils.cdlLog(TAG, rectf.toString());
		canvas.drawRoundRect(rectf, round_w, round_h, CdlPalette.getHilightPaint());
		drawCenterTextUp(canvas, label, CdlPalette.getTxtPaint(w-2*padding, h-2*padding));
		drawCenterTextDn(canvas, stateTxt, CdlPalette.getTxtPaint(w-2*padding, h-2*padding));
	}

	public void addState(String stateTxt) {
		stateValues.add(stateTxt);
		if (stateValues.size() > defaultState) {
			this.stateTxt = stateValues.get(state);
		}
	}

	public void setStateTxt(String s) {
		this.stateTxt = s;
	}

	public void singleTapUp(MotionEvent e) {
		gotToNextState();
		super.singleTapUp(e);
	}

	public void longPress(MotionEvent e) {
		gotToDefaultState();
		super.longPress(e);
	}

	public void gotToDefaultState() {
		int newState = defaultState;
		if (newState >= stateValues.size() || newState < 0) {
			newState = 0;
		}
		setState(newState);
	}

	public void gotToNextState() {
		int newState = state + 1;
		if (newState >= stateValues.size()) {
			newState = 0;
		}
		setState(newState);
	}

	public void setState(int newState) {
		if (newState < stateValues.size() && newState >= 0) {
			this.state = newState;
			this.stateTxt = stateValues.get(newState);
		}
	}

	public int getState() {
		return state;
	}

	public String getStateTxt() {
		return stateTxt;
	}

	public void setDefaultState(int defaultState) {
		this.defaultState = defaultState;
	}

}
