package chadamine.com.databaselist.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Objects.Nutrient;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 5/9/2015.
 */
public class NutrientNewFragment extends Fragment {

    private Nutrient mNutrient;
    private View mView;
    private int mPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mNutrient = new Nutrient(getActivity());
        setHasOptionsMenu(true);
        mView = inflater.inflate(R.layout.fragment_nutrient_new, container, false);

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_nutrient_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // TODO: SET SQL PARAMETERS
        String selection = "";
        String[] selectionArgs = { };
        String sortOrder = "";

        Bundle bundle = new Bundle();

        bundle.putBoolean("isListItem", false);
        bundle.putInt("position", mPosition);
        bundle.putString("selection", selection);
        bundle.putStringArray("selectionArgs", selectionArgs);
        bundle.putString("sortOrder", sortOrder);

        switch(item.getItemId()) {
            case R.id.save_nutrient:
                mNutrient.saveFields(mView, bundle);
                getFragmentManager().popBackStack();
                break;
        }

        return true;
    }

}
