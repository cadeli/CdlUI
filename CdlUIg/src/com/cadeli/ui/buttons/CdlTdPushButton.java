package com.cadeli.ui.buttons;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.cadeli.ui.CdlBaseButton;
import com.cadeli.ui.CdlPalette;
import com.cadeli.ui.CdlUtils;

public class CdlTdPushButton extends CdlBaseButton {

	private static final String TAG = "CdlTdPushButton";
	private static Rect bounds2 = new Rect();

	public CdlTdPushButton(String label, float sizeTxt) {
		super();
		setLabel(label);
		init(sizeTxt);
	}

	public CdlTdPushButton(float sizeTxt) {
		super();
		init(sizeTxt);
	}

	private void init(float sizeTxt) {
		setFlashCapable(true);
		size(sizeTxt);
	}

	public void draw(Canvas canvas) {
		if (isVisible()) {
			 super.draw(canvas);
			drawLabel(canvas,CdlPalette.getTdPaint());
		}
	}

	public void size(float sizeTxt) {
		// 10 char button
		String calibrationString = "XXXXXXXX";
		CdlPalette.getTdPaint(sizeTxt).getTextBounds(calibrationString, 0, calibrationString.length(), bounds2);
		CdlUtils.cdlLog(TAG, "size w="+ bounds2.width()+ "h=" + bounds2.height());
		setSize(bounds2.width(), bounds2.width() / 2);
	}
}
