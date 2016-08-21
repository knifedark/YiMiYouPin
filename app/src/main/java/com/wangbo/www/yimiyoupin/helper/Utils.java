package com.wangbo.www.yimiyoupin.helper;


import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {

	public float convertPixelsToDp(Context ctx, float px) {
		DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;

	}

	public static int convertDpToPixelInt(Context context, float dp) {

		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int px = (int) (dp * (metrics.densityDpi / 160f));
		return px;
	}
	
	public static float convertDpToPixel(Context context, float dp) {

		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float px = (float) (dp * (metrics.densityDpi / 160f));
		return px;
	}
	
}
