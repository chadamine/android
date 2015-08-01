package chadamine.com.databaselist.NutriSolver.NutriSolver;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chadamine.com.databaselist.R;

public class NutriSolverOverview extends Fragment {

    public static NutriSolverOverview newInstance(Bundle args) {
        NutriSolverOverview f = new NutriSolverOverview();
        f.setArguments(args);
        return f;
    }

    public NutriSolverOverview() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutrisolver_overview, container, false);
    }


}
