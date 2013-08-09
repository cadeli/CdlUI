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

	public CdlValue(String name) {
		super();
		this.name = name;
	}

	public void setValues(double  minVal, double  maxVal, double  value) {
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
		setValue(value);
	}

	public double getValue() {
		return value;
	}

	// fader scroll
	public double setValueFromDistance(double distance, int deviceHeigth) {
		return setValueFromDistance(distance, deviceHeigth,1);
	}
	
	// fader scroll
	public double setValueFromDistance(double distance, int deviceHeigth,double  coef) {
		double  incr = 0;
		incr = (double ) ((double ) (distance) / (double ) (deviceHeigth*coef));
		double newVal = value + incr;
		//CdlUtils.cdlLog(TAG, "setValueFromDistance " + distance + "/" + deviceHeigth + " incr=" + incr + " newVal=" + newVal + " val=" + value);
		setValue(newVal);
		return newVal;
	}

	// fader tap
	public double  setAbsValueFromDistance(double  distance, int deviceHeigth) {
		double  incr = 0;
		incr = (double ) ((double ) (distance) / (double ) deviceHeigth);
		double  newVal =  incr;
		//CdlUtils.cdlLog(TAG, "setAbsValueFromDistance " + distance + "/" + deviceHeigth + " incr=" + incr + " newVal=" + newVal + " val=" + value);
		setValue(newVal);
		return newVal;
	}

	protected int computeYMarkFromValue(double  top, double  bottom) {
		double y = bottom - ((bottom - top) * (value - minVal)) / interval;
		// XmlUtil.myLog(TAG, "computeYMarkFromValue" + value + " -> " + y + " t=" + top + " b=" + bottom + " max:" + maxVal + " mi:" + minVal+ " "+ name);
		return (int) y;
	}

	protected double computeValueFromYMark(double  y, double  top, double  bottom) {
		double newVal = minVal + (interval) * (bottom - y) / (bottom - top);
		// XmlUtil.myLog(TAG, "computeValueFromYMark y=" + y + " val-> " + newVal + " t=" + top + " b=" + bottom + " max:" + maxVal + " mi:" + minVal);
		setValue(newVal);
		return newVal;
	}

	public double computeAlphaFromVal(int pitch, int top, int bottom) {
		if ((maxVal - minVal) == 0) {
			CdlUtils.cdlLog(TAG, "*** ERREUR " + " min=" + minVal + " max=" + maxVal);
			return 0;
		}
		double ret = bottom - ((bottom - top) * value) / (maxVal - minVal);
		return ret;
	}

	public double getMaxVal() {
		return maxVal;
	}

	public void setValue(double newVal) {
		//CdlUtils.cdlLog(TAG, "1setvalue: " + newVal + " max=" + maxVal + " min=" + minVal + " " + name);
		if (newVal > maxVal)
			newVal = maxVal;
		if (newVal < minVal)
			newVal = minVal;
		//CdlUtils.cdlLog(TAG, "2setvalue: " + newVal + " max=" + maxVal + " min=" + minVal + " " + name);
		value = newVal;
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
	
	public static double  computeNormalizedVal(int val, int min, int max) {
		double  intervalle = (max - min);
		double  ret = (double ) (val-min)/intervalle;
		return ret;
	}

	public static double  computeExternalVal(double  val, int min, int max) {
		double  intervalle = (max - min);
		double  ret = (double ) ((val*intervalle)+min);
		return ret;
	}



}