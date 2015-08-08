package chadamine.com.databaselist.Cultivation.Chemicals;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chadamine.com.databaselist.R;

public class NewMoleculeFragment extends Fragment {

    private Bundle mBundle;

    public static NewMoleculeFragment newInstance(Bundle args) {
        NewMoleculeFragment f = new NewMoleculeFragment();
        f.setArguments(args);
        return f;
    }

    public NewMoleculeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBundle = getArguments();
        } else
            mBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_compound, container, false);
        return view;
    }
}
