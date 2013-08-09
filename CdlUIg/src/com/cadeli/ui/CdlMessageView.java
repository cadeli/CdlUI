package com.cadeli.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;

public class CdlMessageView extends CdlView {
	public static int MESSAGETYPE_INFO = 1;
	public static int MESSAGETYPE_WARNING = 2;

	private Handler handler = new Handler();
	private Rect bounds = new Rect();
	private RectF rectf = new RectF();

	private static final long MESSAGE_DURATION = 500;
	private static final String TAG = "MessageView";

	protected int timerCountMessage;
	private String messageString = "";
	private int messageType;

	private Runnable runnableMessage = new Runnable() {

		public void run() {
			// XmlUtil.myLog(TAG, "timer " + timerCount);
			timerCountMessage--;
			if (timerCountMessage <= 0) {
				messageString = "";
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
		CdlUtils.cdlLog(TAG, "displayMessage::" + messageString);
		this.messageType = messageType;
		this.messageString = messageString;
		invalidate();
		// invalidate((int) bounds2.left - 8, (int) bounds2.top - 8, (int) bounds2.right + 8, (int) bounds2.bottom + 8);
		timerCountMessage = 2;
		runnableMessage.run();
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawMessage(canvas);
	}

	private void drawMessage(Canvas canvas) {
		if (messageString.length() > 0) {
			// CdlUtils.cdlLog(TAG, "message is: >" + messageString + "<");
			CdlBaseButton.getRectf().set(0, 0, getWidth(), getHeight());
			if (messageType == CdlMessageView.MESSAGETYPE_WARNING) {
				drawMessage(canvas, messageString, CdlPalette.getHilightPaint());
			} else {
				drawMessage(canvas, messageString, CdlPalette.getTxtPaint(getWidth() / 4, getHeight() * 3 / 4));
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
}
