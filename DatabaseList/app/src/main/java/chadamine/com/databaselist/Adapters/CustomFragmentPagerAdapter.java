package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Cultivation.Plants.PlantHistoriesFragment;
import chadamine.com.databaselist.Cultivation.Plants.PlantNewFragment;
import chadamine.com.databaselist.Cultivation.Plants.PlantViewFragment;
import chadamine.com.databaselist.Database.DatabaseSchema;

/**
 * Created by chadamine on 6/7/2015.
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private Bundle mBundle;
    private int mNumItems;
    private final FragmentManager mFragmentManager;
    private Fragment mFragmentAtPos0;
    private List<String> mTitles;
    private FirstPageListener mListener = new FirstPageListener();
    private boolean mIsNew;
    private Context mContext;

    public CustomFragmentPagerAdapter(FragmentManager fm, Context cx, Uri uri,
                                      int numItems, List<String> titles,
                                      boolean isNew) {
        super(fm);
        mFragmentManager = fm;
        mContext = cx;
        mNumItems = numItems;
        mTitles = titles;
        mIsNew = isNew;
        mBundle = new Bundle();
    }

    private final class FirstPageListener implements FirstPageFragmentListener {

        @Override
        public void onSwitchToNewFragment(Bundle bundle) {

            mIsNew = bundle.getBoolean("is_new");
            setFragmentAtPos0();
            notifyDataSetChanged();
        }
    }

    private void setFragmentAtPos0(){
        mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();

        if (mFragmentAtPos0 instanceof PlantViewFragment) {
            mFragmentAtPos0 = PlantNewFragment.newInstance(mBundle, mListener);
        }
        else {
            mFragmentAtPos0 = PlantViewFragment.newInstance(mBundle, mListener);
        }
    }
    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            setFragmentAtPos0();

            return mFragmentAtPos0;
        }

        if(position == 1)
            return PlantHistoriesFragment.newInstance(mBundle);

        if(position == 2)
            return PlantHistoriesFragment.newInstance(mBundle);
        else
            return null;
    }

    @Override
    public int getCount() {
        return mNumItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mTitles.get(position);
    }

    public interface FirstPageFragmentListener
    {
        void onSwitchToNewFragment(Bundle bundle);
    }

    @Override
    public int getItemPosition(Object object) {

        if((object instanceof PlantViewFragment && mFragmentAtPos0 instanceof PlantNewFragment) ||
                (object instanceof  PlantNewFragment) && mFragmentAtPos0 instanceof PlantViewFragment)
            return POSITION_NONE;

        return POSITION_UNCHANGED;
    }
}


