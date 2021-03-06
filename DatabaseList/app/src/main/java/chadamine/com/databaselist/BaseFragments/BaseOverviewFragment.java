package chadamine.com.databaselist.BaseFragments;

import android.content.Context;
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
    public Context mContext;

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

        if(getArguments() != null)
            mBundle = getArguments();
        else if(savedInstanceState != null)
            mBundle = savedInstanceState;
        else
            mBundle = new Bundle();

        View view = inflater.inflate(layout, container, false);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}