package chadamine.com.databaselist.NutriSolver.NutriSolver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.R;

public class NutriSolverOverview extends Fragment {

    Bundle mBundle;

    public static NutriSolverOverview newInstance(Bundle args) {
        NutriSolverOverview f = new NutriSolverOverview();
        f.setArguments(args);
        return f;
    }

    public NutriSolverOverview() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (getArguments() != null) {
            mBundle = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nutrisolver_overview, container, false);
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getFragmentManager(), getActivity(), mBundle);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.pager_sliding_tab_strip);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

        return view;
    }
}
