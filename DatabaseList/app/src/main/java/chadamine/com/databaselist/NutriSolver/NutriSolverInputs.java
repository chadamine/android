package chadamine.com.databaselist.NutriSolver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chadamine.com.databaselist.R;

public class NutriSolverInputs extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static NutriSolverInputs newInstance(Bundle args) {
        NutriSolverInputs f = new NutriSolverInputs();
        f.setArguments(args);
        return f;
    }

    public NutriSolverInputs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (getArguments() != null) {}


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutrisolver_inputs, container, false);
    }
}
