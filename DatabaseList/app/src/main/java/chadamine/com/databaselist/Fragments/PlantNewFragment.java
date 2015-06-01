package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Adapters.SpinnerCursorAdapter;
import chadamine.com.databaselist.CustomWidgets.CustomSpinner;
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
    private long mId;
    private final String NEW = "New...";

    public PlantNewFragment() {}

    public static PlantNewFragment newInstance(Bundle savedInstanceState) {
        PlantNewFragment f = new PlantNewFragment();
        f.setArguments(savedInstanceState);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlant = new Plant(mContext);

        mSubstrate = new Substrate(mContext);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_plant_new, container, false);
        mContext = getActivity();

        setSpinnerAgeUnits();
        setSpinnerHeightUnits();
        setSpinnerSubstrates();

        if(savedInstanceState != null) {
            mBundle = savedInstanceState;

        } else if(getArguments() != null) {

            mBundle = getArguments();

            if(mBundle.containsKey("position"))
                mPosition = mBundle.getInt("position");

            if(mBundle.containsKey("sortOrder"))
                mSortOrder = mBundle.getString("sortOrder");

            if(mBundle.containsKey("id"))
                mId = mBundle.getLong("id");

        } else
            mBundle = new Bundle();

        String name = "";

        if(mPosition >= 0) {
            Cursor cursor = mContext.getContentResolver()
                    .query(mPlant.getUri(), mPlant.getKeyIdArray(),
                            null, null, mSortOrder);

            cursor.moveToPosition(mPosition);

            name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseSchema.Plants.KEY_NAME));

            ((EditText) mView.findViewById(R.id.edittext_plant_new_name))
                    .setText(name == null ? "" : name);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_plant_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.save_plant:

                if(mPosition >= 0) {
                    mPlant.setName(
                            ((EditText) mView.findViewById(R.id.edittext_plant_new_name)).getText()
                                    .toString());

                    mPlant.update(mView, mId);
                }
                else
                    mPlant.saveFields(mView, false);

                getFragmentManager().popBackStack();

                hideKeyboard();
                break;
        }

        return true;
    }

    private void hideKeyboard() {
        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
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
        CustomSpinner spinner = (CustomSpinner) mView.findViewById(R.id.spinner_plant_new_substrate);

        spinner.setAdapter(
                new SpinnerCursorAdapter(mContext, getMergedCursor(mSubstrate.getCursor()))
        );

        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                Toast.makeText(mContext, String.valueOf(position) + " " + String.valueOf(id),
                        Toast.LENGTH_SHORT).show();

                if(position == 0) {
                    getFragmentManager().beginTransaction().replace(R.id.frame_plant_activity,
                            new SubstrateNewFragment()).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Cursor getMergedCursor(Cursor c) {

        String[] projection = new String[] {"_id", "name" };
        MatrixCursor extras = new MatrixCursor(projection);
        //extras.addRow(new String[]{"-1", "Select Journal..."});
        extras.addRow(new String[]{"-1", NEW});
        Cursor[] cursors = { extras, c };
        Cursor extendedCursor = new MergeCursor(cursors);

        return extendedCursor;
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

        outState = mBundle;
    }
}
