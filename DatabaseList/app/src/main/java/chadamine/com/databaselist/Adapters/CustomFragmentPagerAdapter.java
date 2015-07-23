package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import chadamine.com.databaselist.Fragments.PlantNewFragment;
import chadamine.com.databaselist.Fragments.PlantViewFragment;
import chadamine.com.databaselist.Fragments.PlantsFragment;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 6/7/2015.
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    Context mContext;
    static final int NUM_ITEMS = 3;
    private final FragmentManager mFragmentManager;
    private Fragment mFragmentAtPos0;
    private Bundle mBundle;
    private FirstPageListener mListener = new FirstPageListener();

    public CustomFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mFragmentManager = fm;
        mBundle = new Bundle();
    }



    private final class FirstPageListener implements FirstPageFragmentListener {
        @Override
        public void onSwitchToNextFragment(Bundle bundle) {
            mBundle = bundle;
            mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();

            if(mFragmentAtPos0 instanceof PlantsFragment)
                mFragmentAtPos0 = PlantViewFragment.newInstance(mBundle);
            else
                mFragmentAtPos0 = PlantsFragment.newInstance(mBundle, mListener);

            notifyDataSetChanged();
        }

        public void onSwitchToNew(Bundle bundle) {
            mBundle = bundle;
            mFragmentManager
                    .beginTransaction()
                    .remove(mFragmentAtPos0)
                    //.addToBackStack("plant fragment removed")
                    .commit();

            if(mFragmentAtPos0 instanceof PlantsFragment)
                mFragmentAtPos0 = PlantNewFragment.newInstance(mBundle, mListener);
            else
                mFragmentAtPos0 = PlantsFragment.newInstance(mBundle, mListener);

            notifyDataSetChanged();
        }

        public Fragment getFragmentAtPos0() {
            return mFragmentAtPos0;
        }
    }

    @Override
    public Fragment getItem(int position) {

        //mBundle.putInt("position", position);
        mBundle.putInt("page_position", position + 1);

        switch(position) {
            case 0:
                if (mFragmentAtPos0 == null) {
                    mFragmentAtPos0 = PlantsFragment.newInstance(mBundle, mListener);
                }
                return mFragmentAtPos0;

            case 1:
                return PlantViewFragment.newInstance(mBundle);
            case 2:
                return PlantViewFragment.newInstance(mBundle);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch(position) {
            case 0:
                return "Information";
            case 1:
                return "Measurements";
            case 2:
                return "History";
        }

        return "Page " + (position + 1);
    }

    public interface FirstPageFragmentListener
    {
        void onSwitchToNextFragment(Bundle bundle);
        void onSwitchToNew(Bundle bundle);
        Fragment getFragmentAtPos0();
    }

    @Override
    public int getItemPosition(Object object) {
        if(object instanceof PlantsFragment && mFragmentAtPos0 instanceof PlantViewFragment)
            return POSITION_NONE;
        if(object instanceof PlantViewFragment && mFragmentAtPos0 instanceof PlantsFragment)
            return POSITION_NONE;
        if(object instanceof PlantsFragment && mFragmentAtPos0 instanceof PlantNewFragment)
            return POSITION_NONE;
        if(object instanceof PlantNewFragment && mFragmentAtPos0 instanceof PlantsFragment)
            return POSITION_NONE;

        return POSITION_UNCHANGED;
    }
}


