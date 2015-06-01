package chadamine.com.databaselist.CustomWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by chadamine on 5/31/2015.
 */

public class CustomSpinner extends Spinner {

    private OnItemSelectedListener listener;

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);

        if(position == getSelectedItemPosition())
            listener.onItemSelected(null, null, position, 0);
        /*if (listener != null)
            listener.onItemSelected(null, null, position, 0);*/
    }

    @Override
    public void setSelection(int position, boolean animate) {
        super.setSelection(position);
        if (listener != null)
            listener.onItemSelected(null, null, position, 0);
    }

    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener l) {
        listener = l;
    }
}
