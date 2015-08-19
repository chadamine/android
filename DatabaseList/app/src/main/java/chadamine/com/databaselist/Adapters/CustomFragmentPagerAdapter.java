package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import chadamine.com.databaselist.NutriSolver.NutriSolverCalculate;
import chadamine.com.databaselist.NutriSolver.NutriSolverInputs;
import chadamine.com.databaselist.Cultivation.Plants.HistoryFragment;
import chadamine.com.databaselist.Cultivation.Plants.PlantNewFragment;
import chadamine.com.databaselist.Cultivation.Plants.PlantViewFragment;

/**
 * Created by chadamine on 6/7/2015.
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    Context mContext;
    private static final int NUM_ITEMS_PLANTS = 3;
    private static final int NUM_ITEMS_NUTRISOLVER = 2;
    private final FragmentManager mFragmentManager;
    private Fragment mFragmentAtPos0;
    private Bundle mBundle;
    public FirstPageListener mListener = new FirstPageListener();
    private static final String KEY_TYPE = "type";
    private static final String KEY_IS_NEW = "is_new";
    //private static final String
    private String mType;
    private boolean mIsNew;

    public CustomFragmentPagerAdapter(FragmentManager fm, Context context, Bundle args) {
        super(fm);
        mContext = context;
        mFragmentManager = fm;

        if(args != null) {
            mBundle = args;

            if (mBundle.containsKey(KEY_TYPE))
                mType = mBundle.getString(KEY_TYPE);
            if (mBundle.containsKey(KEY_IS_NEW))
                mIsNew = mBundle.getBoolean(KEY_IS_NEW);
        }
        else
            mBundle = new Bundle();
    }

    private final class FirstPageListener implements FirstPageFragmentListener {

        @Override
        public void onSwitchToNewFragment(Bundle bundle) {

            mBundle = bundle;

            if(mBundle.containsKey(KEY_TYPE))
                mType = mBundle.getString(KEY_TYPE);

            mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();

            if(mType == "plant") {
                if (mFragmentAtPos0 instanceof PlantViewFragment)
                    mFragmentAtPos0 = PlantNewFragment.newInstance(mBundle, mListener);
                else
                    mFragmentAtPos0 = PlantViewFragment.newInstance(mBundle, mListener);

                notifyDataSetChanged();
            }
        }
    }

    @Override
    public Fragment getItem(int position) {

        mBundle.putInt("page_position", position + 1);

        //if (mType == "plant") {
            switch (position) {
                case 0:
                    if (mFragmentAtPos0 == null) {
                        if (mIsNew)
                            mFragmentAtPos0 = PlantNewFragment.newInstance(mBundle, mListener);
                        else
                            mFragmentAtPos0 = PlantViewFragment.newInstance(mBundle, mListener);
                    }
                    return mFragmentAtPos0;
                case 1:
                    return PlantViewFragment.newInstance(mBundle, mListener);
                case 2:
                    return HistoryFragment.newInstance(mBundle);
                default:
                    return null;
            }
        /*} else
            switch (position) {
                case 0:
                    return NutriSolverInputs.newInstance(mBundle);
                case 1:
                    return NutriSolverCalculate.newInstance(mBundle);
                default:
                    return null;
            }*/
    }

    @Override
    public int getCount() {
        //if(mType == "plant")
            return NUM_ITEMS_PLANTS;
        /*else
            return NUM_ITEMS_NUTRISOLVER;*/
    }

    @Override
    public CharSequence getPageTitle(int position) {

        //if(mType == "plant") {
            switch (position) {
                case 0:
                    return "Information";
                case 1:
                    return "Measurements";
                case 2:
                    return "History";
                default:
                    return "Page " + (position + 1);
            }
        /*}
        else
            switch(position) {
                case 0:
                    return "Inputs";
                case 1:
                    return "Calculation";
                default:
                    return "Page " + (position + 1);

            }*/
    }

    public interface FirstPageFragmentListener
    {
        void onSwitchToNewFragment(Bundle bundle);
    }

    @Override
    public int getItemPosition(Object object) {

        if(object instanceof PlantViewFragment && mFragmentAtPos0 instanceof PlantNewFragment)
            return POSITION_NONE;
        if(object instanceof PlantNewFragment && mFragmentAtPos0 instanceof PlantViewFragment)
            return POSITION_NONE;

        return POSITION_UNCHANGED;
    }
}


