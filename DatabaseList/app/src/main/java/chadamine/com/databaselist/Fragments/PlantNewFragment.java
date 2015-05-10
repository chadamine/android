package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
    private int mPosition;
    private String mSortOrder;

    public PlantNewFragment() {}

    public static PlantNewFragment newInstance(Bundle savedInstanceState) {
        PlantNewFragment f = new PlantNewFragment();

        f.setArguments(savedInstanceState);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_plant_new, container, false);
        mContext = getActivity();
        mPlant = new Plant(mContext);

        mSubstrate = new Substrate(getActivity());
        setUpButton();
        setSpinnerAgeUnits();
        setSpinnerHeightUnits();
        setSpinnerSubstrates();

        if(savedInstanceState != null) {
            //mBundle = savedInstanceState.getBundle("bundle");
            mBundle = savedInstanceState;

            mPosition = mBundle.getInt("position");
            mSortOrder = mBundle.getString("sortOrder");

            Cursor cursor = mContext.getContentResolver()
                    .query(mPlant.getUri(), mPlant.getKeyIdArray(), null, null, mSortOrder);
            cursor.moveToPosition(mPosition);

            String name = cursor
                    .getString(cursor.getColumnIndexOrThrow(DatabaseSchema.Plants.KEY_NAME));
            ((EditText) mView.findViewById(R.id.edittext_plant_new_name))
                    .setText(name.isEmpty() ? "" : name);

        }

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_plant_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Fragment f;

        switch(item.getItemId()) {
            case R.id.save_plant:
                //f = new PlantsFragment();
                //f.setArguments(mBundle);

                mPlant.saveFields(mView, false);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_plant_activity, PlantsFragment.newInstance(mBundle)).commit();
                break;
        }

        return true;    }

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
