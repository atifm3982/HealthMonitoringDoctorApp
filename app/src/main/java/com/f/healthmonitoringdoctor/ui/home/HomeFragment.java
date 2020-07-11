package com.f.healthmonitoringdoctor.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.f.healthmonitoringdoctor.R;
import com.f.healthmonitoringdoctor.Adapter.PatientsListAdapter;
import com.f.healthmonitoringdoctor.Model.Patient;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel seeAllPatientViewModel;
    RecyclerView recyclerView;
    private List<Patient> patients;
    private SearchView searchView;
    private PatientsListAdapter list;
    Button save;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        seeAllPatientViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_see_all_patient, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");
//        save = (Button) root.findViewById(R.id.save);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getContext(), AddPatientActivity.class);
//                startActivity(intent);
//            }
//        });


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
        patients = new ArrayList<>();
        patients.add(new Patient("ali","umer"));
        patients.add(new Patient("hassan","usman"));
        patients.add(new Patient("raza","munir"));
        patients.add(new Patient("asad","uzair"));
        patients.add(new Patient("awais","uzair"));
        patients.add(new Patient("haseeb","adnan"));
        patients.add(new Patient("faisal","umer"));
        patients.add(new Patient("babar","azam"));
        patients.add(new Patient("ubaid","fazal"));
        patients.add(new Patient("moiz","kamran"));
        patients.add(new Patient("hamza","aftab"));
        patients.add(new Patient("hamza","khalid"));
        ;


        list = new PatientsListAdapter(patients, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(list );

        return root;

    }

}
