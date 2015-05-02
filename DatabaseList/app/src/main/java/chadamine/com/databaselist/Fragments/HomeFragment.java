package chadamine.com.databaselist.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import chadamine.com.databaselist.PlantsActivity;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/10/2015.
 */
public class HomeFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnProducts = (Button) rootView.findViewById(R.id.button_tomaterials);
        btnProducts.setOnClickListener(new View.OnClickListener() {
           @Override
        public void onClick(View v) {
               getFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container, new ProductsFragment()).commit();
           }
        });

        Button btnJournals = (Button) rootView.findViewById(R.id.button_tojournals);
        btnJournals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.container, new JournalsFragment()).commit();
            }
        });

        Button btnPlants = (Button) rootView.findViewById(R.id.button_toplants);
        btnPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlantsActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
