package chadamine.com.databaselist.NutriSolver;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chadamine.com.databaselist.R;

public class NutriSolverCalculate extends Fragment {

    public static NutriSolverCalculate newInstance(Bundle args) {
        NutriSolverCalculate f = new NutriSolverCalculate();
        f.setArguments(args);
        return f;
    }

    public NutriSolverCalculate() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutrisolver_calculate, container, false);
    }


}
