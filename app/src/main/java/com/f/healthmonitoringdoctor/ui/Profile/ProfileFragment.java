package com.f.healthmonitoringdoctor.ui.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.f.healthmonitoringdoctor.Activities.AddMedicineActivity;
import com.f.healthmonitoringdoctor.Activities.CheckEcgActivity;
import com.f.healthmonitoringdoctor.Activities.CheckHeartbeatActivity;
import com.f.healthmonitoringdoctor.Activities.CheckTemperatureActivity;
import com.f.healthmonitoringdoctor.Activities.ProfileActivity;
import com.f.healthmonitoringdoctor.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends Fragment {

    private ProfileViewModel slideshowViewModel;
    Button temp,heartbeat,ecg,medicine;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");

        final TextView textView = root.findViewById(R.id.text_alertnotification);
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}
