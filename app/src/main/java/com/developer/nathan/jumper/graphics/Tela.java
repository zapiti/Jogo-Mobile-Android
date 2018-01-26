package com.developer.nathan.jumper.graphics;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by natha on 25/12/2017.
 */

public class Tela {
    private final Display display;
    private final DisplayMetrics metrics;

    public Tela(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }


    public int getAltura() {
        return metrics.heightPixels;
    }
    public int getLargura() {
        return metrics.widthPixels;
    }
}
