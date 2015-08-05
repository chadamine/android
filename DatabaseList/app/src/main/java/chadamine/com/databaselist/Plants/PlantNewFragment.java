package chadamine.com.databaselist.Plants;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter.FirstPageFragmentListener;
import chadamine.com.databaselist.Adapters.SpinnerCursorAdapter;
import chadamine.com.databaselist.Widgets.CustomSpinner;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Dialogs.PlantAgeDialogFragment;
import chadamine.com.databaselist.Pots.PotsizesNewFragment;
import chadamine.com.databaselist.Substrates.SubstrateNewFragment;
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
    private boolean mIsNew;
    private Cursor mCursor;

    private EditText mHeightValue;
    private TextView mHeightExtraUnit;

    private static FirstPageFragmentListener mListener;

    public PlantNewFragment() {}

    public static PlantNewFragment newInstance(Bundle savedInstanceState) {
        PlantNewFragment f = new PlantNewFragment();
        f.setArguments(savedInstanceState);
        return f;
    }

    public static PlantNewFragment newInstance(Bundle savedInstanceState,
                                               FirstPageFragmentListener listener) {
        PlantNewFragment f = new PlantNewFragment();
        mListener = listener;
        f.setArguments(savedInstanceState);
        return f;
    }

    public static PlantNewFragment newInstance(FirstPageFragmentListener listener) {
        PlantNewFragment f = new PlantNewFragment();
        mListener = listener;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_plant_new, container, false);
        setMembers();
        setCalculatorImages();

        setSpinnerAgeUnits();
        setSpinnerHeightUnits();
        setSpinnerSubstrates();
        setSpinnerPotSizes();

        hideExtraUnits();

        if(getArguments() != null) {

            mBundle = getArguments();

            if(mBundle.containsKey("position"))
                mPosition = mBundle.getInt("position");

            if(mBundle.containsKey("sortOrder"))
                mSortOrder = mBundle.getString("sortOrder");

            if(mBundle.containsKey("id"))
                mId = mBundle.getLong("id");

            if(mBundle.containsKey("isNew"))
                mIsNew =  mBundle.getBoolean("isNew");

        } else
            mBundle = new Bundle();

        String name = "";

        mCursor = mContext.getContentResolver()
                .query(mPlant.getUri(), mPlant.getKeyIdArray(),
                        null, null, mSortOrder);

        if(!mIsNew) {
            mCursor.moveToPosition(mPosition);

            name = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseSchema.Plants.KEY_NAME));

            ((EditText) mView.findViewById(R.id.edittext_plant_new_name))
                    .setText(name == null ? "" : name);
            mCursor.close();
        }

        return mView;
    }

    private void setMembers() {
        mContext = getActivity();
        mPlant = new Plant(mContext);
        mSubstrate = new Substrate(mContext);
        mHeightValue = (EditText) mView.findViewById(R.id.edittext_plant_new_height_2);
        mHeightExtraUnit = (TextView) mView.findViewById(R.id.textview_plant_new_height_2_unit);
    }

    private void setCalculatorImages() {
        ImageView calc = (ImageView) mView.findViewById(R.id.imageview_plant_new_age_calculator);
        registerForContextMenu(calc);
        calc.setImageResource(R.drawable.calculator);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().openContextMenu(v);
                PlantAgeDialogFragment dialog = new PlantAgeDialogFragment();
                dialog.show(getFragmentManager(), "plantAgeDialog");
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        //inflater.inflate(R.menu.menu_plant_age_calculator, menu);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if(menu.size() > 0) {
            menu.removeItem(R.id.add_plant);
            menu.removeItem(R.id.save_plant);
        }

        inflater.inflate(R.menu.menu_plant_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.save_plant:
                if(!mIsNew) {
                    mPlant.setName(
                            ((EditText) mView.findViewById(R.id.edittext_plant_new_name)).getText()
                                    .toString());
                    mPlant.update(mView, mId);
                    //mBundle.putInt("position", mCursor.getCount() - 1);
                }
                else {
                    mPlant.saveFields(mView, false);
                    mBundle.putInt("position", mCursor.getCount());
                }

                mBundle.putBoolean("isNew", false);

                hideKeyboard();
                //mListener.onSwitchToNewFragment(mBundle);
                //getActivity().supportInvalidateOptionsMenu();
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }

        return false;
    }

    private void hideKeyboard() {
        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setSpinnerAgeUnits() {

        List<String> ageUnits = new ArrayList<>();
        ageUnits.add("sec");
        ageUnits.add("min");
        ageUnits.add("hr");
        ageUnits.add("day");
        ageUnits.add("wk");
        ageUnits.add("mo");
        ageUnits.add("yr");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, ageUnits);

        Spinner spinner = (Spinner) mView.findViewById(R.id.spinner_plant_new_age_units);
        spinner.setAdapter(adapter);
        spinner.setSelection(3);

        //new SpinnerCursorAdapter(getActivity(), mProduct.getCursor()));
    }

    private void hideExtraUnits() {
        mHeightValue.setVisibility(View.INVISIBLE);
        mHeightExtraUnit.setVisibility(View.INVISIBLE);
    }

    private void showExtraUnits() {
        mHeightValue.setVisibility(View.VISIBLE);
        mHeightExtraUnit.setVisibility(View.VISIBLE);
    }

    private void setSpinnerHeightUnits() {
        List<String> list = new ArrayList<String>();

        list.add("mm");
        list.add("cm");
        list.add("in");
        list.add("ft");
        list.add("m");
        list.add("yd");

        Spinner spinner = (Spinner) mView.findViewById(R.id.spinner_plant_new_height_units);
        spinner.setAdapter(new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item, list));
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) {
                    showExtraUnits();
                } else {
                    hideExtraUnits();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpinnerSubstrates() {
        CustomSpinner spinner = (CustomSpinner) mView.findViewById(R.id.spinner_plant_new_substrate);

        SpinnerCursorAdapter adapter = new SpinnerCursorAdapter(mContext, getMergedCursor(mSubstrate.getCursor()));
        spinner.setAdapter(adapter);

        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_plant_activity, new SubstrateNewFragment())
                            .addToBackStack("newSubstrate")
                            .commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpinnerPotSizes() {
        CustomSpinner spinner = (CustomSpinner) mView.findViewById(R.id.spinner_plant_new_potsize);

        SpinnerCursorAdapter adapter = new SpinnerCursorAdapter(mContext, getMergedCursor(mSubstrate.getCursor()));
        spinner.setAdapter(adapter);

        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_plant_activity, new PotsizesNewFragment())
                            .addToBackStack("newPotsize")
                            .commit();
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

    public void backPressed() {
        mListener.onSwitchToNewFragment(mBundle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(mBundle);
        // outState = mBundle;

        Toast.makeText(mContext, "Plant Saved", Toast.LENGTH_SHORT).show();
    }
}
