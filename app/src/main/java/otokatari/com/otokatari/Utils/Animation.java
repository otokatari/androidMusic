package otokatari.com.otokatari.Utils;

import android.content.Context;
import android.util.TypedValue;

public class Animation {
    public static int dpToPx(int value, Context context)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,context.getResources().getDisplayMetrics() );
    }

//像素转换成dp
    public static int pxToDp(int value,Context context)
    {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value,context.getResources().getDisplayMetrics() );
    }

}
