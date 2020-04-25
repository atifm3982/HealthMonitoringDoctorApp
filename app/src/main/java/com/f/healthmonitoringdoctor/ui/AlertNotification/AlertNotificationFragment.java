package com.f.healthmonitoringdoctor.ui.AlertNotification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.f.healthmonitoringdoctor.R;
import com.f.healthmonitoringdoctor.ui.AlertNotification.AlertNotificationViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class AlertNotificationFragment extends Fragment {

    private AlertNotificationViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(AlertNotificationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alert_notification, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Alert Notification");

        final TextView textView = root.findViewById(R.id.text_alertnotification);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
