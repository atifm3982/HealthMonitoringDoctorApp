package com.f.healthmonitoringdoctor.ui.AlertNotification;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlertNotificationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AlertNotificationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Alert Notification fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}