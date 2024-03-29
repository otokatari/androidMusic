package otokatari.com.otokatari.View.Player;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class PlayerRootView extends RelativeLayout
{

    private Drawable DefaultBackgroundDrawable;

    private LayerDrawable BackgroundChangedLayer;

    public PlayerRootView(Context context)
    {
        super(context);
    }

    public PlayerRootView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public PlayerRootView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        DefaultBackgroundDrawable = getBackground();
        BackgroundChangedLayer = new LayerDrawable(new Drawable[]{DefaultBackgroundDrawable, DefaultBackgroundDrawable});
        BackgroundChangedLayer.setId(0,0);
        BackgroundChangedLayer.setId(1,1);
    }

    public void SetPlayerBackground(Drawable NewPlayerBackground)
    {

        Drawable NewPlayerBackgroundCopy = NewPlayerBackground.mutate().getConstantState().newDrawable();

        ValueAnimator va = ValueAnimator.ofInt(0,255);
        va.setDuration(500);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                int alpha = (Integer) valueAnimator.getAnimatedValue();
                BackgroundChangedLayer.getDrawable(1).setAlpha(alpha);
                setBackground(BackgroundChangedLayer);
            }
        });

        va.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                BackgroundChangedLayer.setDrawableByLayerId(0,NewPlayerBackground);
            }

            @Override
            public void onAnimationStart(Animator animation)
            {
                super.onAnimationStart(animation);
                BackgroundChangedLayer.setDrawableByLayerId(1,NewPlayerBackgroundCopy);
                BackgroundChangedLayer.getDrawable(1).setAlpha(0);
            }
        });
        va.start();
    }
}
