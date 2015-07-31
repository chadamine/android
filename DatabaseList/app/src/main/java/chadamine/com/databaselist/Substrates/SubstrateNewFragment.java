package chadamine.com.databaselist.Substrates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chadamine.com.databaselist.R;

public class SubstrateNewFragment extends Fragment {

    public static SubstrateNewFragment newInstance(Bundle args) {
        SubstrateNewFragment fragment = new SubstrateNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SubstrateNewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_substrate_new, container, false);
    }

}
