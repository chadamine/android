package chadamine.com.databaselist.Cultivation.Nutrients;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 5/9/2015.
 */
public class NewNutrientFragment extends Fragment {

    private Nutrient mNutrient;
    private View mView;

    private int mPosition;
    //private String mSortOrder;
    //private int mSortSelection;
    private Bundle mBundle;
    private Context mContext;

    public static NewNutrientFragment newInstance(Bundle args) {
        NewNutrientFragment f = new NewNutrientFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mNutrient = new Nutrient(getActivity());

        if(savedInstanceState != null) {
            mBundle = savedInstanceState;
        }

        mView = inflater.inflate(R.layout.fragment_nutrient_new, container, false);

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
            //mSortOrder = mBundle.getString("sortOrder");
            //mSortSelection = mBundle.getInt("sortSelection");
        } else
            mBundle = new Bundle();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_nutrient_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mBundle.putBoolean("isListItem", false);

        Fragment f;

        switch(item.getItemId()) {
            case R.id.save_nutrient:
                mNutrient.saveFields(mView, false);
                getFragmentManager().popBackStack();
                hideKeyboard();
                break;
        }

        return true;
    }

    private void hideKeyboard() {
        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
