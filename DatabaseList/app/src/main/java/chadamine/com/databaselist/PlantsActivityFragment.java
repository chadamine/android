package chadamine.com.databaselist;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chadamine.com.databaselist.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlantsActivityFragment extends Fragment {

    public PlantsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plants, container, false);
    }
}
