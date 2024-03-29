package otokatari.com.otokatari.View.Player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicDiscPager extends ViewPager
{
    private boolean CanScroll = true;

    public MusicDiscPager(@NonNull Context context)
    {
        super(context);
    }

    public MusicDiscPager(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return CanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        return CanScroll && super.onTouchEvent(ev);
    }
}
