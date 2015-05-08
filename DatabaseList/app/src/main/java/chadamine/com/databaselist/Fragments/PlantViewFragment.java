package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Objects.Plant;
import chadamine.com.databaselist.R;

public class PlantViewFragment extends Fragment {

    private View mView;
    private Context mContext;
    private Plant mPlant;
    private List<View> mFields;
    private static int mPosition;

    private TextView mTextViewName;
    private static String mSortOrder;

    public PlantViewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static PlantViewFragment newInstance(int position, String sortOrder) {

        mPosition = position;
        PlantViewFragment f = new PlantViewFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        mSortOrder = sortOrder;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_plant_view, container, false);
        mContext = getActivity();
        mPlant = new Plant(mContext);



        if(savedInstanceState != null)
            mPlant.setViewItemContent(mView, savedInstanceState.getInt("position"), mSortOrder);
        else
            mPlant.setViewItemContent(mView, mPosition, mSortOrder);

        return mView;
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
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
}
