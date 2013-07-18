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

public class CdlKnob extends CdlBaseButton {
	private CdlValue valueControler;

	public CdlKnob(String label) {
		super();
		setLabel(label);
		valueControler = new CdlValue(label);
	}

	public void draw(Canvas canvas) {
		if (isVisible()) {
			super.draw(canvas);
			int wl = 12;
			int dispVal = (int) (valueControler.getValue() * 100);
			String text = "" + dispVal;
			// XmlUtil.myLog(TAG," drawFader " + frameCursorRect);
			RectF oval2 = new RectF(rect.left + wl, rect.top + wl, rect.right - wl, rect.bottom - wl);
			canvas.drawArc(oval2, 100f, 340f, false, CdlPalette.getBlackPaintLarge());
			float alpha = getValueControler().computeAlphaFromVal(dispVal, 340, 0);
			canvas.drawArc(oval2, 100f, alpha, false, CdlPalette.getHilightPaintLarge());
			drawCenterText(canvas, text, CdlPalette.getTxtPaint(w, h));
		}
	}

	public void scroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		super.scroll(e1, e2, distanceX, distanceY);
		valueControler.setValueFromDistance(distanceY, rect.height(),2);
	}

	public CdlValue getValueControler() {
		return valueControler;
	}

}
