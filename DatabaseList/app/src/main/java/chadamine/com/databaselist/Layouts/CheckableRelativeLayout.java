package chadamine.com.databaselist.Layouts;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calipinski on 4/12/2015.
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable {

    private CheckableRelativeLayout checkableRelativeLayout;
    private CheckedTextView checkable;

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    /**
     * Finalize inflating a view from XML.  This is called as the last phase
     * of inflation, after all child views have been added.
     * <p/>
     * <p>Even if the subclass overrides onFinishInflate, they should always be
     * sure to call the super method, so that we get called.
     */

    private boolean isChecked;
    private List<Checkable> checkableViews;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final int childCount = this.getChildCount();

        for(int i = 0; i < childCount; ++i) {
            findCheckableChildren(this.getChildAt(i));
        }

    }

    private void findCheckableChildren(View v) {

        checkableViews = new ArrayList<>();

        if(v instanceof Checkable) {
            checkableViews.add((Checkable) v);

        }

        if(v instanceof ViewGroup) {

            final ViewGroup vg = (ViewGroup) v;
            final int childCount = vg.getChildCount();

            for(int i = 0; i < childCount; ++i) {
                findCheckableChildren(vg.getChildAt(i));
            }
        }
    }


    /**
     * Change the checked state of the view
     *
     * @param checked The new checked state
     */


    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        for(Checkable c : checkableViews) {
            c.setChecked(checked);
            ((CheckableRelativeLayout)c).setBackgroundColor(Color.RED);

        }
    }

    /**
     * @return The current checked state of the view
     */
    @Override
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Change the checked state of the view to the inverse of its current state
     */
    @Override
    public void toggle() {
        isChecked = !isChecked;
        for(Checkable c : checkableViews) {
            c.toggle();
        }
    }
}
