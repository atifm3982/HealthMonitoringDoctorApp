package com.f.healthmonitoringdoctor.api_response;

import com.f.healthmonitoringdoctor.Model.AllAssignPatient;
import com.f.healthmonitoringdoctor.Model.AllMedicineList;
import com.f.healthmonitoringdoctor.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiInterface {


    @FormUrlEncoded
    @POST("loginDoctor")
    Call<LoginResponse> login(@Field("phonenumber") String phonenumber, @Field("password") String password);

//    @GET("getAllDoctor")
//    Call<Doctor> getAllDoctor(@Header("token") String token);
@FormUrlEncoded
@POST("getAssignPatient")
Call<AllAssignPatient> getAssignPatient(@Header("Authorization")String token, @Field("doctor_id") String doctor_id);
@FormUrlEncoded
@POST("getAssignMedicineData")
Call<AllMedicineList> getAssignMedicineData(@Header("Authorization")String token, @Field("patient_id") String patient_id);

}
