package chadamine.com.databaselist;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by chadamine on 8/19/2015.
 */
public class CustomViewPager extends ViewPager {

    private boolean mEnabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mEnabled = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev) && mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }
}
