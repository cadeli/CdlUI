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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class CdlUtils {
	private static Rect bounds = new Rect();
	
	private static final boolean DEVMODE = false;  // ATT XmlUtils.DEVMODE CdmUtils.DEVMODE CdmDef.DEVMODE UiUtil.DEVMODE CdlUtils.DEVMODE

	public static void cdlLog(String TAG, String msg) {
		if (DEVMODE == true) {
			Log.v(TAG, msg);
		}
	}
	
	public static void drawCenterText(Canvas canvas, String text, Paint paint, Rect rect) {
		paint.getTextBounds(text, 0, text.length(), bounds);
		int x = (int) (rect.left + rect.width() / 2 - bounds.centerX());
		int y = (int) (rect.top + rect.height() / 2 - bounds.centerY());
		//Log.v(TAG," x="+x + " y="+y + " "+ text);
		canvas.drawText(text, x,y, paint);
	}

}
