package chadamine.com.databaselist.Plants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter.FirstPageFragmentListener;
import chadamine.com.databaselist.Objects.Plant;
import chadamine.com.databaselist.R;

public class PlantViewFragment extends Fragment {

    private View mView;
    private Context mContext;
    private Plant mPlant;
    private List<View> mFields;
    private int mCursorPosition;
    private static FirstPageFragmentListener mListener;

    private String mSortOrder;
    private Bundle mBundle;
    private boolean mIsNew;

    public PlantViewFragment() {}

    public static PlantViewFragment newInstance(Bundle args) {
        PlantViewFragment f = new PlantViewFragment();
        if(args != null)
            f.setArguments(args);
        return f;
    }

    public static PlantViewFragment newInstance(Bundle args,
                                                FirstPageFragmentListener listener) {
        PlantViewFragment f = new PlantViewFragment();
        if(args != null)
            f.setArguments(args);
        mListener = listener;
        return f;
    }

    public static PlantViewFragment newInstance(FirstPageFragmentListener listener) {
        PlantViewFragment f = new PlantViewFragment();
        mListener = listener;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_plant_view, container, false);
        mContext = getActivity();
        mPlant = new Plant(mContext);

        if(getArguments() != null) {
            mBundle = getArguments();

            if(mBundle.containsKey("sortOrder"))
                mSortOrder = mBundle.getString("sortOrder");

            if(mBundle.containsKey("position"))
                mCursorPosition = mBundle.getInt("position");

            if(mBundle.containsKey("isNew"))
                mIsNew = mBundle.getBoolean("isNew");

            mPlant.setViewItemContent(mView, mCursorPosition, mSortOrder);
        }

        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();

        mFields = new ArrayList<>();

        mFields.add(mView.findViewById(R.id.textview_plant_view_info_name));
        mFields.add(mView.findViewById(R.id.textview_plant_view_info_species));
        mFields.add(mView.findViewById(R.id.textview_plant_view_info_cultivar));
        mFields.add(mView.findViewById(R.id.textview_plant_view_info_stage));
        mFields.add(mView.findViewById(R.id.textview_plant_view_info_age));

        for(View view : mFields)
            ((TextView) view).setText("");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_plant_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*int id = -1;
        if(mBundle.containsKey("id"))
                id = mBundle.getInt("id");*/

        switch (item.getItemId()) {
            case R.id.edit_plant:
                //mBundle.putInt("id", mPlant.getId() + 1);
                mListener.onSwitchToNewFragment(mBundle);
        }

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState = mBundle;
    }
}
