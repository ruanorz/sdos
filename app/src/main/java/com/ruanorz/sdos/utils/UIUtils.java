package com.ruanorz.sdos.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by ruano on 12/02/2018.
 */

public class UIUtils {

    public static int convertDpToPx(int dp){
        return Math.round(dp*(Resources.getSystem().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));

    }

    public static int convertPxToDp(int px){
        return Math.round(px/(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
    }
}
