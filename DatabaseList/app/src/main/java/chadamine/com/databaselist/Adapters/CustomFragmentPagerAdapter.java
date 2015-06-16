package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import chadamine.com.databaselist.Fragments.PlantNewFragment;
import chadamine.com.databaselist.Fragments.PlantViewFragment;
import chadamine.com.databaselist.Fragments.PlantsFragment;

/**
 * Created by chadamine on 6/7/2015.
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    Context mContext;
    static final int NUM_ITEMS = 3;
    private final FragmentManager mFragmentManager;
    private Fragment mFragmentAtPos0;
    private Bundle mBundle;

    public CustomFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new PlantsFragment();
        mBundle = new Bundle();
        mBundle.putInt("page_position", position + 1);
        fragment.setArguments(mBundle);

        if (position == 0) {
            if (mFragmentAtPos0 == null) {
                mFragmentAtPos0 = PlantsFragment.newInstance(mBundle, new FirstPageFragmentListener() {
                    public void onSwitchToNextFragment() {
                        mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();
                        mFragmentAtPos0 = PlantNewFragment.newInstance(mBundle);
                        notifyDataSetChanged();
                    }
                });
            }
        } else
            return PlantViewFragment.newInstance(mBundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + (position + 1);
    }

    public interface FirstPageFragmentListener
    {
        void onSwitchToNextFragment();
    }
}


