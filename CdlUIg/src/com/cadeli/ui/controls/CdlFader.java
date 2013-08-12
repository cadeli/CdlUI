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

package com.cadeli.ui.controls;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlPalette;
import com.cadeli.ui.CdlUtils;

public class CdlFader extends CdlBaseButton {
	private static final String TAG = "OrFader";
	private String title = "notitle";
	private CdlValue valueControler;
	private RectF rect2 = new RectF();

	public CdlFader(String label) {
		super();
		setLabel(label);
		valueControler = new CdlValue(title);
	}

	public void draw(Canvas canvas) {
		int yMark = valueControler.computeYMarkFromValue(rect.top, rect.bottom);
		if (isVisible()) {
			super.draw(canvas);
			rect2.left = rect.left + padding;
			rect2.right = rect.right - padding;
			int wl = 8;
			if (yMark - wl < rect.top)
				yMark = rect.top + wl;
			if (yMark + wl > rect.bottom)
				yMark = rect.bottom - wl;
			rect2.top = yMark - wl;
			rect2.bottom = yMark + wl;
			if (isEnable()) {
				canvas.drawRoundRect(rect2, 5f, 5f, CdlPalette.getHilightPaint());

				int dispVal = 0;
				dispVal = (int) (valueControler.getValue() * 100);
				//
				String text = "" + dispVal;
				drawCenterText(canvas, text, CdlPalette.getTxtPaint(w * 2 - 2 * padding, h - 2 * padding));
				//
			}
		}
	}

	public void scroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if (!isEnable()) return;
		valueControler.setValueFromDistance(distanceY, rect.height());
		super.scroll(e1, e2, distanceX, distanceY);
	}

	public CdlValue getValueControler() {
		return valueControler;
	}

	public void setValueControler(CdlValue valueControler) {
		this.valueControler = valueControler;
	}

	public void singleTapUp(MotionEvent e) {
		if (!isEnable()) return;
		valueControler.setAbsValueFromDistance(getBottom() - e.getY(), rect.height());
		super.singleTapUp(e);
		return;
	}

	public void longPress(MotionEvent e) {
		CdlUtils.cdlLog(TAG, "longpress" + e);
		if (!isEnable()) return;
		valueControler.setValue(0.5f);
		super.longPress(e);
	}
}
