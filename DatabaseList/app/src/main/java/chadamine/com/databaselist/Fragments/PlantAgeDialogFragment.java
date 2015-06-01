package chadamine.com.databaselist.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 6/1/2015.
 */
public class PlantAgeDialogFragment extends DialogFragment {


    private String mToUnit;
    private String mFromUnit;
    private EditText mFromValue;
    private Spinner mFromUnits;
    private Spinner mToUnits;
    private Double mValue;
    private TextView mAnswer;
    private View mView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.fragment_dialog_plant_age, null);
        mAnswer = (TextView) mView.findViewById(R.id.textview_dialog_plant_age_convert_result);

        mFromUnits =
                (Spinner) mView.findViewById(R.id.spinner_dialog_plant_age_convert_from_unit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.lengths, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFromUnits.setAdapter(adapter);

        mFromValue =
                (EditText) mView.findViewById(R.id.edittext_dialog_plant_age_convert_from_value);
        mFromValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateResult();
            }
        });



        mToUnits = (Spinner) mView.findViewById(R.id.spinner_dialog_plant_age_convert_to_unit);

        mToUnits.setAdapter(adapter);
        mToUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mToUnit = parent.getSelectedItem().toString();
                updateResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(mView)
                // Add action buttons
                .setPositiveButton("Use Value", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PlantAgeDialogFragment.this.getDialog().cancel();
                    }
                })
                .setTitle("Age Calculator");
        return builder.create();
    }

    private final double _mm2in = 0.0393701;
    private double convert(double value, String fromUnit, String toUnit) {

        Double answer = 0.0;

        mToUnit = mToUnits.getSelectedItem().toString();

        if(mFromUnit.equalsIgnoreCase("mm")) {
            if(mToUnit.equalsIgnoreCase("mm"))
                answer = value;
            if(mToUnit.equalsIgnoreCase("cm"))
                answer = value / 10;
            if(mToUnit.equalsIgnoreCase("in"))
                answer = value * _mm2in;
            if(mToUnit.equalsIgnoreCase("m"))
                answer = value / 1000;
            if(mToUnit.equalsIgnoreCase("ft"))
                answer = value * 0.00328084;
            if(mToUnit.equalsIgnoreCase("yd"))
                answer = value * 0.00109361;
        }

        return answer;
    }

    private void updateResult() {

        mFromUnit = mFromUnits.getSelectedItem().toString();
        mToUnit = mToUnits.getSelectedItem().toString();

        if(mFromValue.getText().length() > 0)
            mValue = Double.valueOf(mFromValue.getText().toString());
        else
            mValue = 0.0;

        double result = convert(mValue, mFromUnit, mToUnit);

        DecimalFormat format = new DecimalFormat("0.#####");

        //if(result == (long) result)
            mAnswer.setText(format.format(result));
       // else
           // mAnswer.setText(String.format("%.5f", result));
    }
}
