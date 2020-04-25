package com.f.healthmonitoringdoctor.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.f.healthmonitoringdoctor.R;
import com.f.healthmonitoringdoctor.Adapter.DoctorsListAdapter;
import com.f.healthmonitoringdoctor.Model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    private List<Doctor> doctors;
    private SearchView searchView;
    private DoctorsListAdapter list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        searchView = (SearchView)root.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                list.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.getFilter().filter(newText);


                return false;
            }
        });
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        doctors = new ArrayList<>();
        doctors.add(new Doctor("ali","surgen","03075445663"));
        doctors.add(new Doctor("hassan","surgen","03075445663"));
        doctors.add(new Doctor("raza","surgen","03075445663"));
        doctors.add(new Doctor("asad","surgen","03075445663"));
        doctors.add(new Doctor("awais","surgen","03075445663"));
        doctors.add(new Doctor("haseeb","surgen","03075445663"));
        doctors.add(new Doctor("faisal","surgen","03075445663"));
        doctors.add(new Doctor("babar","surgen","03075445663"));
        doctors.add(new Doctor("ubaid","surgen","03075445663"));
        doctors.add(new Doctor("moiz","surgen","03075445663"));
        doctors.add(new Doctor("hamza","surgen","03075445663"));
        doctors.add(new Doctor("hamza","surgen","03075445663"));

        list = new DoctorsListAdapter(doctors, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(list );

        return root;

    }
}
