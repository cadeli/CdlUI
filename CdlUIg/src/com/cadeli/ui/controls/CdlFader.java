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
		float yMark = valueControler.computeYMarkFromValue(rect.top + 2 * padding, rect.bottom - 2 * padding);
		if (isVisible()) {
			super.draw(canvas);
			rect2.left = rect.left + padding;
			rect2.right = rect.right - padding;
			float wl = CdlPalette.computeStrockWidth(0.2f, rect.width(), rect.height()); // hum TODO

			if (yMark - wl < rect.top) {
				yMark = rect.top + wl;
			}
			if (yMark + wl > rect.bottom) {
				yMark = rect.bottom - wl;
			}
			rect2.top = yMark - wl;
			rect2.bottom = yMark + wl;
			if (isEnable()) {
//				CdlPalette.getFaderPaintLarge().setColor(CdlPalette.computeHiColor(getBackgroundPaint().getColor()));
				CdlPalette.getFaderPaintLarge().setColor(getHilightColor());
				canvas.drawRect(rect2, CdlPalette.getFaderPaintLarge());
				double dispVal = valueControler.getValue();
				if (valueControler.isNormalized()) {
					dispVal *= 100;
				}
				String text = "" + (int) dispVal;
				drawCenterText(canvas, text, CdlPalette.getTxtPaint(text.length(), w * 1 - 2 * padding, h - 2 * padding));
			}
		}
	}

	public void scroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if (!isEnable())
			return;
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
		if (!isEnable())
			return;
		CdlUtils.cdlLog(TAG, "singleTapUp" + e);
		valueControler.setAbsValueFromDistance(getBottom() - e.getY(), rect.height());
		super.singleTapUp(e);
		return;
	}

	public void longPress(MotionEvent e) {
		if (!isEnable())
			return;
		CdlUtils.cdlLog(TAG, "longpress" + e);
		float val = (float) ((valueControler.maxVal - valueControler.minVal) / 2 + valueControler.minVal);
		valueControler.setValues(valueControler.minVal, valueControler.maxVal, val);
		super.longPress(e);
	}
}
