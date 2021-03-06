package net.colindodd.gradientlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class GradientFrameLayout extends FrameLayout {

    private final GradientBackground gradientBackground;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GradientFrameLayout( final Context context,
                                 final AttributeSet attrs,
                                 final int defStyleAttr,
                                 final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.gradientBackground = new GradientBackground(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public GradientFrameLayout( final Context context,
                                 final AttributeSet attrs,
                                 final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.gradientBackground = new GradientBackground(context, attrs);
    }

    public GradientFrameLayout(final Context context,
                                final AttributeSet attrs) {
        super(context, attrs);
        this.gradientBackground = new GradientBackground(context, attrs);
    }

    /**
     * <p>Configure the gradient background for this layout.</p>
     * @param startColor The start color for the gradient
     * @param endColor The end color for the gradient
     * @param orientation The orientation for the gradient {@link  android.graphics.drawable.GradientDrawable.Orientation  GradientDrawable.Orientation}
     */
    public void setGradientBackgroundConfig(final int startColor, final int endColor, final GradientDrawable.Orientation orientation) {
        this.gradientBackground
                .setStartColor(startColor)
                .setEndColor(endColor)
                .setOrientation(orientation)
                .generate();
    }

    @Override
    protected void onLayout(final boolean changed,
                            final int left,
                            final int top,
                            final int right,
                            final int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setBackground();
    }

    private void setBackground() {
        final GradientDrawable background = this.gradientBackground.generate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(background);
        } else {
            this.setBackgroundDrawable(background);
        }
    }
}
