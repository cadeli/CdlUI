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

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlPalette;
import com.cadeli.ui.CdlUtils;

public class CdlOnOffButton extends CdlBaseButton {
	private static final String TAG = "CdlOnOffButton";
	boolean state = false;

	public CdlOnOffButton(String label) {
		super();
		setLabel(label);
		init();
	}

	public CdlOnOffButton() {
		super();
		init();
	}

	private void init() {
		setFlashCapable(false);
	}

	public void draw(Canvas canvas) {
		// CdlUtils.cdlLog(TAG, "draw "+ getLabel() + "= "+ isState());
		if (isVisible()) {
			super.draw(canvas); // just for compute size (TODO...indirect backColor?)
			rectf.set(rect.left + padding, rect.top + padding, rect.right - padding, rect.bottom - padding);
			drawLabel(canvas);
			if (state) {
				canvas.drawRoundRect(rectf, round_w, round_h, CdlPalette.getHilightPaint());
			}
		}
	}

	public void singleTapUp(MotionEvent e) {
		CdlUtils.cdlLog(TAG, "tap on cdl: " + label + "state=" + state);
		if (!isEnable()) return;
		setState(!state);
		super.singleTapUp(e);
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
