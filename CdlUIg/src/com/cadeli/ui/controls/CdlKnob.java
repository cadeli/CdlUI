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
import android.graphics.Color;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlPalette;
import com.cadeli.ui.CdlUtils;

public class CdlKnob extends CdlBaseButton {
	private static final String TAG = "CdlKnob";
	private CdlValue valueControler;
	private boolean clickOnIncr=false;

	public CdlKnob(String label) {
		super();
		setLabel(label);
		valueControler = new CdlValue(label);
		setHilightColor(Color.rgb(255, 0, 0));
	}

	public void draw(Canvas canvas) {
		if (isVisible() ) {
			//super.draw(canvas);
			CdlPalette.getKnobPaintLarge().setStrokeWidth(CdlPalette.computeStrockWidth(1,rect.width(),rect.height()));
			int wl = (int) CdlPalette.getKnobPaintLarge().getStrokeWidth();
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
			
			CdlPalette.getKnobPaintLarge().setColor(CdlPalette.computeLowColor(getHilightColor()));
			canvas.drawArc(oval2, minAngle, maxAngle, false, CdlPalette.getKnobPaintLarge());
			CdlPalette.getKnobPaintLarge().setColor(getHilightColor());
			canvas.drawArc(oval2, minAngle, alpha, false, CdlPalette.getKnobPaintLarge());
			
			if (isEnable()) {
				drawCenterText(canvas, text, CdlPalette.getTxtPaint(text.length(),w - 2 * padding, h - 2 * padding));
			}
			if (getLabel()!=null) {
				drawBottomText(canvas, getLabel(), CdlPalette.getTxtPaint(getLabel().length(),w/2 - 2 * padding, h/2 - 2 * padding));
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
		valueControler.setValuesToMiddle();
		super.longPress(e);
	}
	
	public void singleTapUp(MotionEvent e){
		if (e.getX() < rect.centerX()) {
			clickOnIncr=false;
		} else {
			clickOnIncr=true;
		}
		super.singleTapUp(e);
	}

    public boolean isClickOnIncr() {
    	return clickOnIncr;
    }

}
