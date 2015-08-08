package chadamine.com.databaselist.BaseFragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.R;

public class BaseOverviewFragment extends Fragment {

    public Bundle mBundle;
    public @MenuRes int mMenu;
    public @IdRes int mEdit;

    public BaseOverviewFragment() {}

    public static BaseOverviewFragment newInstance(Bundle args) {
        BaseOverviewFragment f = new BaseOverviewFragment();
        if(args != null)
            f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    public View inflateView(@LayoutRes int layout, LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null)
            mBundle = savedInstanceState;
        return prepareView(inflater.inflate(layout, container, false));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private View prepareView(View view) {

        CustomFragmentPagerAdapter customFragmentPagerAdapter;

        // TODO: USE URI INSTEAD
        mBundle.putString("type", "plant");
        customFragmentPagerAdapter =
                new CustomFragmentPagerAdapter(getFragmentManager(), getActivity(), mBundle);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.pager_sliding_tab_strip);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(customFragmentPagerAdapter);

        tabs.setViewPager(viewPager);

        return view;
    }
}
