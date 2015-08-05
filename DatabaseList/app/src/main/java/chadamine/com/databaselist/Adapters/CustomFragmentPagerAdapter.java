package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import chadamine.com.databaselist.Plants.PlantHistoriesFragment;
import chadamine.com.databaselist.Plants.PlantNewFragment;
import chadamine.com.databaselist.Plants.PlantOverviewFragment;
import chadamine.com.databaselist.Plants.PlantViewFragment;

/**
 * Created by chadamine on 6/7/2015.
 */
public class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    static final int NUM_ITEMS = 3;
    private final FragmentManager mFragmentManager;
    private Fragment mFragmentAtPos0;
    private Bundle mBundle;
    public FirstPageListener mListener = new FirstPageListener();

    public CustomFragmentPagerAdapter(FragmentManager fm, Context context, Bundle args) {
        super(fm);
        mContext = context;
        mFragmentManager = fm;
        mBundle = args;
    }

    private final class FirstPageListener implements FirstPageFragmentListener {

        @Override
        public void onSwitchToNewFragment(Bundle bundle) {
            mBundle = bundle;
            mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();

            if(mFragmentAtPos0 instanceof PlantViewFragment)
                mFragmentAtPos0 = PlantNewFragment.newInstance(mBundle, mListener);
            else
                mFragmentAtPos0 = PlantViewFragment.newInstance(mBundle, mListener);

            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {

        //mBundle.putInt("position", position);
        mBundle.putInt("page_position", position + 1);

        boolean isNew = false;

        if(mBundle.containsKey("isNew"))
            isNew = mBundle.getBoolean("isNew");

        switch(position) {
            case 0:
                if (mFragmentAtPos0 == null) {
                    if(isNew)
                        mFragmentAtPos0 = PlantNewFragment.newInstance(mBundle, mListener);
                    else
                        mFragmentAtPos0 = PlantViewFragment.newInstance(mBundle, mListener);
                }

                //(new PlantOverviewFragment()).changeMenu();
                return mFragmentAtPos0;

            case 1:
                return PlantHistoriesFragment.newInstance(mBundle);
            case 2:
                return PlantHistoriesFragment.newInstance(mBundle);
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
            default:
                return "Page " + (position + 1);
        }

    }

    public interface FirstPageFragmentListener
    {
        void onSwitchToNewFragment(Bundle bundle);
    }

    @Override
    public int getItemPosition(Object object) {
        /*if(object instanceof PlantsFragment && mFragmentAtPos0 instanceof PlantViewFragment)
            return POSITION_NONE;
        if(object instanceof PlantViewFragment && mFragmentAtPos0 instanceof PlantsFragment)
            return POSITION_NONE;
        if(object instanceof PlantsFragment && mFragmentAtPos0 instanceof PlantNewFragment)
            return POSITION_NONE;
        if(object instanceof PlantNewFragment && mFragmentAtPos0 instanceof PlantsFragment)
            return POSITION_NONE;*/
        if(object instanceof PlantViewFragment && mFragmentAtPos0 instanceof PlantNewFragment)
            return POSITION_NONE;
        if(object instanceof PlantNewFragment && mFragmentAtPos0 instanceof PlantViewFragment)
            return POSITION_NONE;

        return POSITION_UNCHANGED;
    }
}


