package com.f.healthmonitoringdoctor.ui.SeeAllPatient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeeAllPatientViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SeeAllPatientViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Doctor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}