package com.contacts;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by apjoe on 10/3/2017.
 */

public class Utilities {

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

}
