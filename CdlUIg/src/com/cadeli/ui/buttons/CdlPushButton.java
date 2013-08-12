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

import com.cadeli.ui.CdlBaseButton;

public class CdlPushButton extends CdlBaseButton {

	private static final String TAG = "CdlPushButton";

	public CdlPushButton(String label) {
		super();
		setLabel(label);
		init();
	}

	public CdlPushButton() {
		super();
		init();
	}

	private void init() {
		setFlashCapable(true);
	}

	public void draw(Canvas canvas) {
		if (isVisible()) {
			super.draw(canvas);
			drawLabel(canvas);
		}
	}
}
