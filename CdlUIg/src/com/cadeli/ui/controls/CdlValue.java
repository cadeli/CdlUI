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

import com.cadeli.ui.CdlUtils;

public class CdlValue {
	private static final String TAG = "CdlValue";
	protected double maxVal = 1f;
	protected double minVal = 0f;
	protected double value = 0.5f;
	protected double interval = 1f;
	private String name;
	private boolean normalized = true;

	public CdlValue(String name) {
		super();
		this.name = name;
	}

	public void setValues(double minVal, double maxVal, double value) {
		this.minVal = minVal;
		this.maxVal = maxVal;
		if (maxVal >= 0 && minVal >= 0) {
			interval = maxVal - minVal;
		}
		if (maxVal >= 0 && minVal <= 0) {
			interval = Math.abs(maxVal) + Math.abs(minVal);
		}
		if (maxVal <= 0 && minVal <= 0) {
			interval = Math.abs(maxVal - minVal);
		}
		if (minVal == 0 && maxVal == 1) {
			normalized = true;
		} else {
			normalized = false;
		}
		setValue(value);
	}

	private void setValue(double newVal) {
		// CdlUtils.cdlLog(TAG, "1setvalue: " + newVal + " max=" + maxVal + " min=" + minVal + " " + name);
		if (newVal > maxVal)
			newVal = maxVal;
		if (newVal < minVal)
			newVal = minVal;
		if (!isNormalized()) {
			value = computeNormalizedVal(newVal);
		} else {
			value = newVal;
		}
		//CdlUtils.cdlLog(TAG, "3setvalue: " + newVal + " max=" + maxVal + " min=" + minVal + " " + name);
	}

	public double getValue() {
		if (isNormalized()) {
			return value;
		} else {
			return computeExternalVal(value);
		}
	}

	// fader scroll
	public void setValueFromDistance(double distance, int deviceHeigth) {
		setValueFromDistance(distance, deviceHeigth, 1);
	}

	// fader scroll
	public void setValueFromDistance(double distance, int deviceHeigth, double coef) {
		double incr = 0;
		incr = (double) ((double) (distance) / (double) (deviceHeigth * coef));
		double newVal = value + incr;
		// CdlUtils.cdlLog(TAG, "setValueFromDistance " + distance + "/" + deviceHeigth + " incr=" + incr + " newVal=" + newVal + " val=" + value);
		value = inRange(newVal);
	}

	// fader tap
	public void setAbsValueFromDistance(double distance, int deviceHeigth) {
		double incr = 0;
		incr = (double) ((double) (distance) / (double) deviceHeigth);
		double newVal = incr;
		//CdlUtils.cdlLog(TAG, "setAbsValueFromDistance " + distance + "/" + deviceHeigth + " incr=" + incr + " newVal=" + newVal + " val=" + value);
		value = inRange(newVal);
	}

	protected int computeYMarkFromValue(double top, double bottom) {
		double y = bottom - (bottom - top) * value ;
//		CdlUtils.cdlLog(TAG, "computeYMarkFromValue" + value + " -> " + y + " t=" + top + " b=" + bottom + " max:" + maxVal + " mi:" + minVal + " " + name);
		return (int) y;
	}

	protected double computeValueFromYMark(double y, double top, double bottom) {
		double newVal = (bottom - y) / (bottom - top);
//		CdlUtils.cdlLog(TAG, "computeValueFromYMark y=" + y + " val-> " + newVal + " t=" + top + " b=" + bottom + " max:" + maxVal + " mi:" + minVal);
		value = inRange(newVal);
		return newVal;
	}

	private double inRange(double newVal) {
		if (newVal > 1)
			newVal = 1;
		if (newVal < 0)
			newVal = 0;
		return newVal;
	}

	public double computeAlphaFromVal(int pitch, int top, int bottom) {
		if ((maxVal - minVal) == 0) {
			CdlUtils.cdlLog(TAG, "*** ERROR " + " min=" + minVal + " max=" + maxVal);
			return 0;
		}
		double ret = bottom - ((bottom - top) * value) ;
		return ret;
	}

	public double getMaxVal() {
		return maxVal;
	}

	public double getValueFromDistance(double distance, int w) {
		double newVal = value - ((distance * 4) / w);
		if (newVal > maxVal)
			newVal = maxVal;
		if (newVal < minVal)
			newVal = minVal;
		value = (int) newVal;
		return newVal;
	}

	public String getName() {
		return name;
	}

	private double computeNormalizedVal(double val) {
		double intervalle = (maxVal - minVal);
		double ret = (double) (val - minVal) / intervalle;
		return ret;
	}

	private double computeExternalVal(double val) {
		double intervalle = (maxVal - minVal);
		double ret = (double) ((val * intervalle) + minVal);
		return ret;
	}

	public boolean isNormalized() {
		return normalized;
	}

}