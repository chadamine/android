package chadamine.com.databaselist.Plants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlantOverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlantOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantOverviewFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;

    private Context mContext;
    private View mView;
    private Bundle mBundle;
    private CustomFragmentPagerAdapter.FirstPageFragmentListener mListener;
    private boolean mIsNew;
    private PagerAdapter mCustomFragmentPagerAdapter;
    private ViewPager mViewPager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlantOverviewFragment.
     */
/*    public static PlantOverviewFragment newInstance(Bundle args) {
        PlantOverviewFragment f = new PlantOverviewFragment();

        if(args != null)
            f.setArguments(args);

        return f;
    }*/

    public static PlantOverviewFragment newInstance(Bundle args) {
        PlantOverviewFragment f = new PlantOverviewFragment();

        if(args != null)
            f.setArguments(args);

        return f;
    }

    public PlantOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBundle = new Bundle();
        mContext = getActivity();

        mView = inflater.inflate(R.layout.fragment_plant_overview, container, false);

        if(getArguments() != null) {
            mBundle = getArguments();
            if(mBundle.containsKey("isNew"))
                mIsNew = mBundle.getBoolean("isNew");
        }

        if(savedInstanceState != null) {
            mBundle = savedInstanceState;
            if(mBundle.containsKey("isNew"))
                mIsNew = mBundle.getBoolean("isNew");
        }

        mBundle.putString("type", "plant");
        mCustomFragmentPagerAdapter =
                new CustomFragmentPagerAdapter(getFragmentManager(), mContext, mBundle);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.pager_sliding_tab_strip);

        mViewPager = (ViewPager) mView.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomFragmentPagerAdapter);

        tabs.setViewPager(mViewPager);

        /*if(mIsNew) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_plant_overview_fragment,
                            new PlantNewFragment())
                    .addToBackStack("newPlant")
                    .commit();
            //mListener.onSwitchToNewFragment(mBundle);

        }
        else
            getFragmentManager().beginTransaction()
                    .replace((R.id.frame_plant_overview_fragment),
                            new PlantViewFragment())
                    .addToBackStack("fragmentAddedToOverview")
                    .commit();*/
        return mView;
    }



   /* // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

}
