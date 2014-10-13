package com.cadeli.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;

public class CdlMessageView extends CdlView {
	public static int MESSAGETYPE_INFO = 1;
	public static int MESSAGETYPE_WARNING = 2;

	private Handler handler = new Handler();

	private static final long MESSAGE_DURATION = 500;
	private static final String TAG = "MessageView";

	protected int timerCountMessage;
	private String messageString = "";
	private int messageType;
	private int progressVal;
	private String progressMessage = "";

	private Runnable runnableMessage = new Runnable() {
		public void run() {
			// XmlUtil.myLog(TAG, "timer " + timerCount);
			timerCountMessage--;
			if (timerCountMessage <= 0) {
				messageString = "";
				CdlUtils.cdlLog(TAG, " display message (end)");
				invalidate();
				// invalidate((int) bounds2.left - 8, (int) bounds2.top - 8, (int) bounds2.right + 8, (int) bounds2.bottom + 8);
			} else {
				handler.postDelayed(this, MESSAGE_DURATION);
			}
		}
	};

	public CdlMessageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init2(context);
	}

	public CdlMessageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init2(context);
	}

	public CdlMessageView(Context context) {
		super(context);
		init2(context);
	}

	private void init2(Context context) {
	}

	public void displayMessage(String messageString, int messageType) {
		// CdlUtils.cdlLog(TAG, "displayMessage::" + messageString);
		this.messageType = messageType;
		this.messageString = messageString;
		invalidate();
		timerCountMessage = 2;
		runnableMessage.run();
	}

	protected void onDraw(Canvas canvas) {
		draw(canvas, 0); // compatibilty (hem)
	}

	public void draw(Canvas canvas, int screenId) {
		super.draw(canvas, screenId);
		if (messageString.length() > 0) {
			drawMessage(canvas);
		}
		drawProgress(canvas);
	}

	protected void drawProgress(Canvas canvas) { // TODO simplify
		if (progressMessage.length() < 1)
			return;
		int round_h = getWidth() / 100;
		int round_w = getWidth() / 100;
		int pad = getWidth()/4;
		Paint txtPaintTitle = CdlPalette.getTxtPaint(progressMessage.length(), getWidth()/6 ,getHeight());
		txtPaintTitle.getTextBounds(progressMessage, 0, progressMessage.length(), bounds);
		int padding = bounds.height()/2;
	    int x1 = (int) (getWidth() / 4);
	    int x2 = (int) (getRight() - getWidth()/4);
		int y1 = (int) ((getHeight() / 2) - bounds.height());
		int y2 = (int) (y1+bounds.height()+padding);
		int y3 = (int) (y2+bounds.height());
		
		rectf.set(x1-padding,y1-padding,x2+padding,y3+2*padding);
		canvas.drawRect(rectf,  CdlPalette.getFlashPaint());
		
		urect.set(x1, y2, x2, y3+padding);
		canvas.drawRoundRect(urect, round_w, round_h, CdlPalette.getBlackPaint());
		urect.set(x1, y2, x1+(x2-x1)* progressVal / 100, y3+padding);
		canvas.drawRoundRect(urect, round_w, round_h, CdlPalette.getHilightPaint());
		
		rectf.set(x1,y1,x2,y2);
		drawCenterTextInrectCase(canvas, progressMessage, txtPaintTitle);

		String progressValStr = "" + progressVal + " " + "%";
		rectf.set(x1,y2+padding,x2,y3);
		drawCenterTextInrectCase(canvas, progressValStr, txtPaintTitle);
		
		rectf.set(x1-padding,y1-padding,x2+padding,y3+2*padding);
		canvas.drawRoundRect(rectf, round_w, round_h, CdlPalette.getBorderPaint());

	}


	protected void drawMessage(Canvas canvas) {
		// CdlUtils.cdlLog(TAG, "message is: >" + messageString + "<");
		if (messageString.length() > 0) {
			CdlBaseButton.getRectf().set(0, 0, getWidth(), getHeight());
			if (messageType == CdlMessageView.MESSAGETYPE_WARNING) {
				drawMessage(canvas, messageString, CdlPalette.getHilightPaint());
			} else {
				drawMessage(canvas, messageString, CdlPalette.getTxtPaint(messageString.length(),getWidth() / 8, getHeight() / 8));
			}
		}
	}

	private void drawMessage(Canvas canvas, String text, Paint paint) {
		paint.getTextBounds(text, 0, text.length(), bounds);
		int x = (int) (getWidth() / 2 - bounds.centerX());
		int y = (int) (getHeight() / 2 - bounds.centerY());

		int round_h = getWidth() / 100;
		int round_w = getWidth() / 100;
		int padding = round_h * 3;
		int dx = (getWidth() / 2 - bounds.centerX());
		int dy = (int) (getHeight() / 2 - bounds.centerY());
		rectf.set(bounds.left + dx - padding, bounds.top + dy - padding, bounds.right + dx + padding, bounds.bottom + dy + padding);
		canvas.drawRoundRect(rectf, round_w, round_h, CdlPalette.getFlashPaint());
		canvas.drawText(text, x, y, paint);
		canvas.drawRoundRect(rectf, round_w, round_h, CdlPalette.getBorderPaint());
	}

	public void setProgress(float min, float max, float val, String progressMessage) {
		// CdlUtils.cdlLog(TAG, "setProgress =" + val + "="+ progressMessage);
		this.progressMessage = progressMessage;
		float interval = (max - min);
		progressVal = (int) ((val * 100) / interval + min);
		if (getHandler() == null)
			return;
		getHandler().post(new Runnable() {
			public void run() {
				invalidate();
			}
		});
	}
}
