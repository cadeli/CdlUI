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

package com.cadeli.ui;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class CdlPalette {
	private static final String TAG = "CdlPalette";
	public static final int COLORSCHEME1 = 10;
	public static final int COLORSCHEME2 = 20;
	public static final int COLORSCHEME3 = 30;
	public static final int COLORSCHEME4 = 40;

	static List<Paint> colorList = new ArrayList<Paint>();
	static Paint txtPaint;
	static Paint borderPaint;
	static Paint hilightPaint;
	static Paint hilightPaintLarge;
	static Paint blackPaint;
	static Paint blackPaintLarge;
	static int txtPaintColor = Color.WHITE;
	private static Paint flashPaint;
	private static int hilightColor = Color.GREEN;
	private static float borderSize = 2;
	private static int defaultAlpha = 128;
	private static float defaulStrokeWidth = 12;
	private static boolean isGradient = false;

	// protected static final int ACTIVETEXT_COLOR = 0xFFFFFFFF;
	// private static final int INACTIVETEXT_COLOR = 0xFF808080;

	public static void addColor(int color) {
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setDither(true);
		colorList.add(paint);
	}

	public static Paint getPaint(int i, int x, int y, int w, int h) {
		int size = colorList.size();
		if (i >= 0 && size > 0) {
			Paint p = (Paint) colorList.get(i % size);
			if (isGradient) { // TODO avoid new
				// p.setShader(new LinearGradient(x, y, x, y+h,
				// p.getColor(),
				// Color.parseColor("#FF000000"), Shader.TileMode.REPEAT));
				// Shader s = p.getShader();
				// LinearGradient lg = s.getLocalMatrix(localM);
				CdlUtils.cdlLog(TAG, "new Grdient");
			}
			return p;
		}
		return (Paint) colorList.get(0);
	}

	public static void createDefaultColors() {
		setColorScreme(COLORSCHEME3);
	}

	public static void setColorScreme(int colorscheme) {
		colorList.clear();

		addColor(Color.rgb(0, 0, 0));
		if (colorscheme == COLORSCHEME1) {
			addColor(Color.rgb(255, 0, 0));
			addColor(Color.rgb(57, 20, 175));
			addColor(Color.rgb(255, 211, 0));
			addColor(Color.rgb(0, 204, 0));

		} else if (colorscheme == COLORSCHEME2) {
			addColor(Color.rgb(191, 48, 48));
			addColor(Color.rgb(65, 44, 132));
			addColor(Color.rgb(191, 167, 48));
			addColor(Color.rgb(38, 153, 38));

		} else if (colorscheme == COLORSCHEME3) {
			addColor(Color.rgb(166, 0, 0));
			addColor(Color.rgb(32, 7, 114));
			addColor(Color.rgb(166, 137, 0));
			addColor(Color.rgb(0, 133, 0));

		} else if (colorscheme == COLORSCHEME4) {
			addColor(Color.rgb(255, 64, 64));
			addColor(Color.rgb(106, 72, 215));
			addColor(Color.rgb(255, 222, 64));
			addColor(Color.rgb(57, 230, 57));

		} else {
			addColor(Color.rgb(255, 115, 115));
			addColor(Color.rgb(135, 110, 215));
			addColor(Color.rgb(255, 231, 115));
			addColor(Color.rgb(103, 230, 103));
		}
	}

	public static Paint getTxtPaint(int w, int h) {
		if (txtPaint == null) {
			txtPaint = new Paint();
			txtPaint.setAntiAlias(true);
			txtPaint.setDither(true);
		}
		if (w < h) {
			txtPaint.setTextSize((int)(float)(w / 2.5f));
		} else {
			txtPaint.setTextSize((int)(float)(h / 2.5f));
		}
		txtPaint.setColor(txtPaintColor);
		return txtPaint;
	}

	public static Paint getBorderPaint() {
		if (borderPaint == null) {
			borderPaint = new Paint();
			borderPaint.setAntiAlias(true);
			borderPaint.setDither(true);
			borderPaint.setAlpha(defaultAlpha);
			borderPaint.setStyle(Style.STROKE);
			borderPaint.setStrokeWidth(borderSize);
		}

		borderPaint.setColor(txtPaintColor);
		return borderPaint;
	}

	public static Paint getFlashPaint() {
		if (flashPaint == null) {
			flashPaint = new Paint();
			flashPaint.setColor(Color.GRAY);
			flashPaint.setAntiAlias(true);
			flashPaint.setDither(true);
		}
		return flashPaint;
	}

	public static Paint getHilightPaint() {
		if (hilightPaint == null) {
			hilightPaint = new Paint();
			hilightPaint.setColor(hilightColor);
			hilightPaint.setAntiAlias(true);
			hilightPaint.setDither(true);
			hilightPaint.setAlpha(defaultAlpha);
		}
		return hilightPaint;
	}

	public static Paint getHilightPaintLarge() {
		if (hilightPaintLarge == null) {
			hilightPaintLarge = new Paint();
			hilightPaintLarge.setStyle(Style.STROKE);
			hilightPaintLarge.setStrokeWidth(defaulStrokeWidth);
			hilightPaintLarge.setColor(hilightColor);
			hilightPaintLarge.setAlpha(defaultAlpha);
			hilightPaintLarge.setAntiAlias(true);
			hilightPaintLarge.setDither(true);
		}
		return hilightPaintLarge;
	}

	public static Paint getBlackPaint() {
		if (blackPaint == null) {
			blackPaint = new Paint();
			blackPaint.setColor(Color.BLACK);
			blackPaint.setAntiAlias(true);
			blackPaint.setDither(true);
		}
		return blackPaint;
	}

	public static Paint getBlackPaintLarge() {
		if (blackPaintLarge == null) {
			blackPaintLarge = new Paint();
			blackPaintLarge.setColor(Color.DKGRAY);
			blackPaintLarge.setStyle(Style.STROKE);
			blackPaintLarge.setStrokeWidth(defaulStrokeWidth);
			blackPaintLarge.setAntiAlias(true);
			blackPaintLarge.setDither(true);
		}
		return blackPaintLarge;
	}

	public static void setHilightColor(int hilightColor) {
		CdlPalette.hilightColor = hilightColor;
	}

	public static int getLastColorIndex() {
		return colorList.size() - 1;
	}

}
