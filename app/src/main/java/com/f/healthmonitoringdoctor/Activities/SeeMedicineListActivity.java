package com.f.healthmonitoringdoctor.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.f.healthmonitoringdoctor.Adapter.MedicineListAdapter;
import com.f.healthmonitoringdoctor.Model.AllMedicineList;
import com.f.healthmonitoringdoctor.Model.AssignData;
import com.f.healthmonitoringdoctor.R;
import com.f.healthmonitoringdoctor.api_response.ApiClient;
import com.f.healthmonitoringdoctor.api_response.ApiInterface;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeMedicineListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<AssignData> doctors;
    private AllMedicineList allMedicineList;
    private ProgressBar progressBar;

    private SearchView searchView;
    private MedicineListAdapter list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_medicine_prescriptions);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_m);
        searchView = (SearchView)findViewById(R.id.search_m);

        progressBar=(ProgressBar)findViewById(R.id.progress_doctor);
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
        prepareMovieData();


    }

    private void prepareMovieData() {



        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        progressBar.setVisibility(View.VISIBLE);

        /**
         GET List Resources
         **/
        SharedPreferences prefs = getApplication().getSharedPreferences("login", MODE_PRIVATE);
        String token = prefs.getString("Token", null);//"No name defined" is the default value.
//        String _id = prefs.getString("id", null);//"No name defined" is the default value.
        Call<AllMedicineList> call = apiInterface.getAssignMedicineData("Bearer "+token,getIntent().getStringExtra("id"));
        call.enqueue(new Callback<AllMedicineList>() {
            private Context context;

            public void setContext(Context context) {
                this.context = context;
            }

            public Context getContext() {
                return context;
            }

            @Override
            public void onResponse(Call<AllMedicineList> call, Response<AllMedicineList> response) {
                progressBar.setVisibility(View.INVISIBLE);

                if (response.isSuccessful()) {

                    list = new MedicineListAdapter(response.body().getResponse(), getApplication());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplication());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(list);
                } else {

                    Toast.makeText(getApplication(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            private Intent getIntent() {
                return null;
            }

            @Override
            public void onFailure(Call<AllMedicineList> call, Throwable t) {
                call.cancel();
                progressBar.setVisibility(View.GONE);

                Log.e("gfgf",t.getMessage());
            }
        });

    }


    public SharedPreferences getSharedPreferences(String login, int modePrivate) {
        return null;
    }
}


