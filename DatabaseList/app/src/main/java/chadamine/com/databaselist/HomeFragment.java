package chadamine.com.databaselist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import chadamine.com.databaselist.Journals.JournalsActivity;
import chadamine.com.databaselist.NutriSolver.NutriSolverActivity;
import chadamine.com.databaselist.Plants.PlantsActivity;
import chadamine.com.databaselist.Nutrients.NutrientsActivity;

import chadamine.com.databaselist.Products.ProductsActivity;
import chadamine.com.databaselist.Schedules.ScheduleActivity;

/**
 * Created by chadamine on 4/10/2015.
 */
public class HomeFragment extends Fragment {

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mContext = getActivity();



        Button btnJournals = (Button) rootView.findViewById(R.id.button_tojournals);
        btnJournals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container, new JournalsFragment()).commit();
                startActivity(new Intent(mContext, JournalsActivity.class));
            }
        });

        Button btnProducts = (Button) rootView.findViewById(R.id.button_tomaterials);
        btnProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ProductsActivity.class));
                //getFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container, new ProductsFragment()).commit();
            }
        });
        Button btnPlants = (Button) rootView.findViewById(R.id.button_toplants);
        btnPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlantsActivity.class);
                startActivity(intent);
            }
        });

        Button btnNutrients = (Button) rootView.findViewById(R.id.button_tonutrients);
        btnNutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NutrientsActivity.class);
                startActivity(intent);
            }
        });

        Button btnSchedule = (Button) rootView.findViewById(R.id.button_toschedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        Button btnNutriSolver = (Button) rootView.findViewById(R.id.button_to_nutrisolver);
        btnNutriSolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NutriSolverActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
