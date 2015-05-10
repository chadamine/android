package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Adapters.SpinnerCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Objects.Plant;
import chadamine.com.databaselist.Objects.Substrate;
import chadamine.com.databaselist.R;

public class PlantNewFragment extends Fragment {

    private View mView;
    private Plant mPlant;
    private Substrate mSubstrate;
    private Context mContext;
    private Bundle mBundle;

    public PlantNewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_plant_new, container, false);
        mContext = getActivity();
        mSubstrate = new Substrate(getActivity());
        setUpButton();
        setSpinnerAgeUnits();
        setSpinnerHeightUnits();
        setSpinnerSubstrates();

        if(savedInstanceState != null)
            mBundle = savedInstanceState.getBundle("bundle");

        return mView;
    }

    private void setUpButton() {
        Button btnSavePlant = (Button) mView.findViewById(R.id.button_save_plant_new);
        btnSavePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlant();
                saveToDB();

                Fragment f = new PlantsFragment();
                f.setArguments(mBundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_nutrient_activity, f)
                        .commit();
            }
        });
    }

    private void setSpinnerAgeUnits() {

        List<String> ageUnits = new ArrayList<>();
        ageUnits.add("seconds");
        ageUnits.add("minutes");
        ageUnits.add("hours");
        ageUnits.add("days") ;
        ageUnits.add("weeks");
        ageUnits.add("months");
        ageUnits.add("years");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, ageUnits);

        Spinner spinner = (Spinner) mView.findViewById(R.id.spinner_plant_new_age_units);
        spinner.setAdapter(adapter);
        spinner.setSelection(3);

        //new SpinnerCursorAdapter(getActivity(), mProduct.getCursor()));
    }

    private void setSpinnerHeightUnits() {
        List<String> l = new ArrayList<String>();

        l.add("mm");
        l.add("cm");
        l.add("in");
        l.add("ft");
        l.add("m");
        l.add("yd");

        Spinner spinner = (Spinner) mView.findViewById(R.id.spinner_plant_new_height_units);
        spinner.setAdapter(new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item, l));
        spinner.setSelection(3);
    }

    private void setSpinnerSubstrates() {
        Spinner spinner = (Spinner) mView.findViewById(R.id.spinner_plant_new_substrate);
        spinner.setAdapter(new SpinnerCursorAdapter(getActivity(), mSubstrate.getCursor()));
    }

    private void savePlant() {
        mPlant = new Plant(mContext);
        mPlant.setName(((EditText) mView.findViewById(R.id.edittext_plant_new_name))
                .getText().toString());
        mPlant.setSpecies(((EditText) mView.findViewById(R.id.edittext_plant_new_species))
                .getText().toString());
        mPlant.setCultivar(((EditText) mView.findViewById(R.id.edittext_plant_new_cultivar))
                .getText().toString());
        mPlant.setStage(((EditText) mView.findViewById(R.id.edittext_plant_new_stage))
                .getText().toString());
        mPlant.setAge(((EditText) mView.findViewById(R.id.edittext_plant_new_age))
                .getText().toString());
        mPlant.setHeight(((EditText) mView.findViewById(R.id.edittext_plant_new_height_1))
                .getText().toString());
        // TODO: Create Substrate, Pot, Taxonomy classes
        /*plant.setSubstrate(((Spinner) mView.findViewById(R.id.spinner_plant_new_substrate))
                .getSelectedItem().toString());*/
        /*plant.setPotSize();*/
    }

    private void saveToDB() {
        getActivity().getContentResolver()
                .insert(DatabaseSchema.Plants.CONTENT_URI, mPlant.getValues());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("bundle", mBundle);
    }
}
