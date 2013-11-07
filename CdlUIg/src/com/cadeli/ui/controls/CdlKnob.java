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

public class CdlKnob extends CdlBaseButton {
	private static final String TAG = "CdlKnob";
	private CdlValue valueControler;

	public CdlKnob(String label) {
		super();
		setLabel(label);
		valueControler = new CdlValue(label);
	}

	public void draw(Canvas canvas) {
		if (isVisible() ) {
			super.draw(canvas);
			int wl = (int) CdlPalette.getBlackPaintLarge().getStrokeWidth();
			double dispVal = valueControler.getValue();
			if (valueControler.isNormalized()) {
				dispVal *= 100;
			}
			String text = "" + (int) dispVal;
			// XmlUtil.myLog(TAG," drawFader " + frameCursorRect);
			RectF oval2 = new RectF(rect.left + wl, rect.top + wl, rect.right - wl, rect.bottom - wl);
			float maxAngle = 300f;
			float minAngle = 120f;
			float alpha = (float) getValueControler().computeAlphaFromVal((int) dispVal, (int) maxAngle, 0);
			canvas.drawArc(oval2, minAngle, maxAngle, false, CdlPalette.getBlackPaintLarge());
			canvas.drawArc(oval2, minAngle, alpha, false, CdlPalette.getHilightPaintLarge());
			if (isEnable()) {
				drawCenterText(canvas, text, CdlPalette.getTxtPaint(w - 2 * padding, h - 2 * padding));
			}
		}
	}

	public void scroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if (!isEnable())
			return;
		valueControler.setValueFromDistance(distanceY, rect.height(), 2);
		super.scroll(e1, e2, distanceX, distanceY);
	}

	public CdlValue getValueControler() {
		return valueControler;
	}

	public void longPress(MotionEvent e) {
		CdlUtils.cdlLog(TAG, "longpress" + e);
		if (!isEnable())
			return;
		valueControler.setValues(0f, 1f, 0.5f);
		super.longPress(e);
	}



}
