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

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;

public class CdlPalette {
	private static final String TAG = "CdlPalette";

	public static final int COLORSCHEME1 = 10;
	public static final int COLORSCHEME2 = 20;
	public static final int COLORSCHEME3 = 30;
	public static final int COLORSCHEME4 = 40;
	public static final int COLORSCHEME5 = 50;

	static List<Paint> colorList = new ArrayList<Paint>();
	static Paint txtPaint;
	static Paint txtInversePaint;
	static Paint borderPaint;
	static Paint hilightPaint;
	static Paint hilightPaintLarge;
	static Paint blackPaint;
	static Paint backgroundPaint;
	static Paint knobPaintLarge;
	static Paint faderPaintLarge;
	static int txtPaintColor = Color.WHITE;
	static int txtPaintInverseColor = Color.BLACK;
	private static Paint flashPaint;
	private static int hilightColor = Color.rgb(20, 192, 120);
	private static float borderSize = 2;
	private static int defaultAlpha = 255;
	private static float defaulStrokeWidth = 12;
	private static boolean isGradient = false;
	private static Typeface typeface = null;
	private static Typeface typefaceLight = null;
	private static Paint tdPaint;

	private static int backgroundColor = Color.rgb(0, 0, 0);

	private static Paint dialogPanelPaint;
	private Context context;

	// protected static final int ACTIVETEXT_COLOR = 0xFFFFFFFF;
	// private static final int INACTIVETEXT_COLOR = 0xFF808080;

	public static void addColor(int color) {
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setAlpha(defaultAlpha);
		colorList.add(paint);
		CdlUtils.cdlLog(TAG, "AddColor:" + Integer.toHexString(paint.getColor()));
	}

	public static Paint getPaint(int i) {
		return getPaint(i, 0, 0, 100, 100);
	}

	public static Paint getPaint(int nb, int x, int y, int w, int h) {
		int len = colorList.size();
		Paint p = colorList.get(0);
		if (nb >= 0) {
			int index = nb % len;
			p =  colorList.get(index);
			int color = p.getColor();
			//CdlUtils.cdlLog(TAG, "Returncolor for "+nb + "="+ index+ " c="+ color);
		}	
		return p;
	}

	public static void createDefaultColors() {
		setColorScheme(COLORSCHEME2);
	}

	public static void setColorScheme(int colorscheme) {
		CdlUtils.cdlLog(TAG, "setColorScreme :" + colorscheme);
		colorList.clear();
		switch (colorscheme) {
		case COLORSCHEME1:
			addColor(0x006BC2);
			addColor(0x39CC00);
			addColor(0xC6E400);
			addColor(0xF3DF00);
			addColor(0xF3AF00);
			break;
		case COLORSCHEME2:
			addColor(0x737791);
			addColor(0xDDC71A);
			addColor(0xD32643);
			addColor(0x0505D0);
			// addColor(0x0A3560);
			break;
		case COLORSCHEME3:
			addColor(Color.rgb(137, 247, 142));
			addColor(Color.rgb(190, 151, 233));
			addColor(Color.rgb(252, 248, 132));
			addColor(Color.rgb(255, 0, 0));
			addColor(Color.rgb(255, 191, 223));
			break;
		case COLORSCHEME4:
			addColor(Color.rgb(255, 64, 64));
			addColor(Color.rgb(106, 72, 215));
			addColor(Color.rgb(255, 222, 64));
			addColor(Color.rgb(57, 230, 57));
			break;
		case COLORSCHEME5:
			addColor(0xE19595);// Color.rgb(225, 149, 149));//0xE19595
			addColor(0x87E4EF);// Color.rgb(135, 228, 239));//0x87E4EF
			addColor(Color.rgb(149, 255, 149));
			addColor(Color.rgb(228, 239, 135));
			break;
		default:
			addColor(0xfff9b3);
			addColor(0xefa0ff);
			addColor(0xade1af);
			addColor(0x94cbff);
			addColor(0xffd9a3);
			addColor(0xffc6b3);
			addColor(0xc4fcca);
			addColor(0xdeea86);
		}
	}

//	public static Paint getTxtPaint(int len, int size) {
//		return getTxtPaint(len, size, size);
//	}

	public static Paint getTxtPaint(int len, int w, int h) {
		if (txtPaint == null) {
			txtPaint = new Paint();
			txtPaint.setAntiAlias(true);
			txtPaint.setDither(true);
		}
		int size = 22;
		if (w < h) {
			size = (int) (float) (w / 2.5f);
		} else {
			size = (int) (float) (h / 2.5f);
		}

		if (size > 28) {
			if (typefaceLight != null) {
				txtPaint.setTypeface(typefaceLight);
			}
		} else {
			if (typeface != null) {
				txtPaint.setTypeface(typeface);
			}
		}
		if (len < 4) {
			size *= 1.1f;
		}
		txtPaint.setTextSize(size);

		txtPaint.setColor(txtPaintColor);
		return txtPaint;
	}

	public static Paint getTxtInversePaint(int size) {
		return getTxtInversePaint(size, size);
	}

	public static Paint getTxtInversePaint(int w, int h) {
		if (txtInversePaint == null) {
			txtInversePaint = new Paint();
			txtInversePaint.setAntiAlias(true);
			txtInversePaint.setDither(true);
		}
		if (w < h) {
			txtInversePaint.setTextSize((int) (float) (w / 2.5f));
		} else {
			txtInversePaint.setTextSize((int) (float) (h / 2.5f));
		}
		txtInversePaint.setColor(txtPaintInverseColor);
		return txtInversePaint;
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
			flashPaint.setAlpha(170);
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

	public static Paint getBlackPaint() {
		if (blackPaint == null) {
			blackPaint = new Paint();
			blackPaint.setColor(Color.BLACK);
			blackPaint.setAntiAlias(true);
			blackPaint.setDither(true);
		}
		return blackPaint;
	}

	public static void setBackgroundColor(int c) {
		backgroundColor = c;
	}

	public static Paint getBackgroundPaint() {
		if (backgroundPaint == null) {
			backgroundPaint = new Paint();
			backgroundPaint.setColor(backgroundColor);
			backgroundPaint.setAlpha(255);
			backgroundPaint.setAntiAlias(true);
			backgroundPaint.setDither(true);
		}
		return backgroundPaint;
	}

	public static Paint getHilightPaintLarge() {
		if (hilightPaintLarge == null) {
			hilightPaintLarge = new Paint();
			hilightPaintLarge.setStyle(Style.FILL);
			// hilightPaintLarge.setStrokeWidth(defaulStrokeWidth);
			hilightPaintLarge.setColor(hilightColor);
			hilightPaintLarge.setAntiAlias(true);
			hilightPaintLarge.setDither(true);
			hilightPaintLarge.setAlpha(255);
		}
		return hilightPaintLarge;
	}

	public static Paint getKnobPaintLarge() {
		if (knobPaintLarge == null) {
			knobPaintLarge = new Paint();
			knobPaintLarge.setStyle(Style.STROKE);
			knobPaintLarge.setStrokeWidth(defaulStrokeWidth);
			knobPaintLarge.setColor(Color.DKGRAY);
			knobPaintLarge.setAntiAlias(true);
			knobPaintLarge.setDither(true);
			knobPaintLarge.setAlpha(255);
		}
		return knobPaintLarge;
	}

	public static Paint getFaderPaintLarge() {
		if (faderPaintLarge == null) {
			faderPaintLarge = new Paint();
			faderPaintLarge.setStyle(Style.FILL);
			faderPaintLarge.setStrokeWidth(defaulStrokeWidth);
			faderPaintLarge.setColor(Color.DKGRAY);
			faderPaintLarge.setAntiAlias(true);
			faderPaintLarge.setDither(true);
			faderPaintLarge.setAlpha(255);
		}
		return faderPaintLarge;
	}
	
	public static Paint getDialogPanelPaint() {
		if (dialogPanelPaint == null) {
			dialogPanelPaint = new Paint();
			dialogPanelPaint.setStyle(Style.FILL);
			dialogPanelPaint.setColor(Color.DKGRAY);
			dialogPanelPaint.setAntiAlias(true);
			dialogPanelPaint.setDither(true);
			dialogPanelPaint.setAlpha(127);
		}
		return dialogPanelPaint;
	}

	public static void setHilightColor(int hilightColor) {
		CdlPalette.hilightColor = hilightColor;
		hilightPaint = null;
	}

	public static int getLastColorIndex() {
		return colorList.size() - 1;
	}
	
	public static Paint getTdPaint(float size) {
		if (tdPaint == null) {
			tdPaint = new Paint();
			tdPaint.setAntiAlias(true);
			tdPaint.setDither(true);
			CdlUtils.cdlLog(TAG, "typeface = " + typeface);
			// if (typeface != null) {
			// tdPaint.setTypeface(typeface);
			// CdlUtils.cdlLog(TAG, "typeface bold= " + typeface.isBold());
			// }
//			if (size > 14) {
				if (size > 28) {
				if (typefaceLight != null) {
					tdPaint.setTypeface(typefaceLight);
				}
			} else {
				if (typeface != null) {
					tdPaint.setTypeface(typeface);
				}
			}
			tdPaint.setTextSize(size);
			//tdPaint.setColor(0x506060);
			//tdPaint.setShadowLayer(6, 2, 2, 0xF0F0F0);
			tdPaint.setAlpha(defaultAlpha);
		} else {
			tdPaint.setTextSize(size);
		}
		return tdPaint;
	}

	public static Paint getTdPaint() {
		if (tdPaint == null) {
			return getTdPaint(12);
		}
		return tdPaint;
	}

	public static void setTypeFace(Typeface typeface) {
		CdlUtils.cdlLog(TAG, "setTypeFace = " + typeface);
		CdlPalette.typeface = typeface;
	}

	public static void setTypeFaceLight(Typeface typeface) {
		CdlUtils.cdlLog(TAG, "setTypeFaceLight = " + typeface);
		CdlPalette.typefaceLight = typeface;
	}

	static float[] hsv = new float[3];

	/**
	 * 
	 * @param color
	 * @return
	 */
	public static int computeLowColor(int color) {
		Color.colorToHSV(color, hsv);
		hsv[2] *= 0.5f;
		color = Color.HSVToColor(hsv);
		return color;
	}

	/**
	 * 
	 * @param color
	 * @return
	 */
	public static int computeHiColor(int color) {
		Color.colorToHSV(color, hsv);
		hsv[2] = 1.0f - 0.2f * (1.0f - hsv[2]);
		color = Color.HSVToColor(hsv);
		return color;
	}

	/**
	 * 
	 * @param coef
	 * @param w
	 * @param h
	 */
	public static float computeStrockWidth(float coef, float w, float h) {
		float strockWidth = 1;
		if (w < h) {
			strockWidth = w / 5f;
		} else {
			strockWidth = h / 5f;
		}
		return strockWidth * coef;
	}

	public static int getDefaultHilightColor() {
		return hilightColor;
	}


}
