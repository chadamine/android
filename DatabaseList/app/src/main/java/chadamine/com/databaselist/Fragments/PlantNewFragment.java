package chadamine.com.databaselist.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Objects.Plant;
import chadamine.com.databaselist.R;

public class PlantNewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";*/

    // TODO: Rename and change types of parameters
    /*private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;*/

    View mView;
    Plant mPlant;

    // TODO: Rename and change types and number of parameters
    public static PlantNewFragment newInstance(/*String param1, String param2*/) {
        PlantNewFragment fragment = new PlantNewFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    public PlantNewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_plant_new_info, container, false);

        setUpButton();

        return mView;
    }


    private void setUpButton() {
        Button btnSavePlant = (Button) mView.findViewById(R.id.button_save_plant_new_info);
        btnSavePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPlant();
                saveToDB();

                getFragmentManager().popBackStack();

            }
        });
    }

    private void createPlant() {
        mPlant = new Plant();
        mPlant.setName(((EditText) mView.findViewById(R.id.edittext_plant_new_info_name))
                .getText().toString());
        mPlant.setSpecies(((EditText) mView.findViewById(R.id.edittext_plant_new_info_species))
                .getText().toString());
        mPlant.setCultivar(((EditText) mView.findViewById(R.id.edittext_plant_new_info_cultivar))
                .getText().toString());
        mPlant.setStage(((EditText) mView.findViewById(R.id.edittext_plant_new_info_stage))
                .getText().toString());
        mPlant.setAge(((EditText) mView.findViewById(R.id.edittext_plant_new_info_age))
                .getText().toString());
        mPlant.setHeight(((EditText) mView.findViewById(R.id.edittext_plant_new_info_height_1))
                .getText().toString());
        // TODO: Create Substrate, Pot, Taxonomy classes
        /*plant.setSubstrate(((Spinner) mView.findViewById(R.id.spinner_plant_new_info_substrate))
                .getSelectedItem().toString());*/
        /*plant.setPotSize();*/
    }

    private void saveToDB() {
        getActivity().getContentResolver()
                .insert(DatabaseContract.Plants.CONTENT_URI, mPlant.getValues());
    }



    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    /*@Override
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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

}
