package com.f.healthmonitoringdoctor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.f.healthmonitoringdoctor.Activities.ProfileActivityDoctor;
import com.f.healthmonitoringdoctor.R;
import com.f.healthmonitoringdoctor.Activities.ProfileActivity;
import com.f.healthmonitoringdoctor.Model.Doctor;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DoctorsListAdapter extends RecyclerView.Adapter<DoctorsListAdapter.MyViewHolder> implements Filterable {

    private List<Doctor> doctorsList;
    private List<Doctor> filterdoctorsList;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView doctorname, specialization, phonenumber;
public Button profile;
        public MyViewHolder(View view) {
            super(view);
            doctorname = (TextView) view.findViewById(R.id.dname);
            specialization = (TextView) view.findViewById(R.id.spec);
            phonenumber = (TextView) view.findViewById(R.id.phone);


        }
    }


    public DoctorsListAdapter(List<Doctor> moviesList, Context context) {
        this.doctorsList = moviesList;
        this.filterdoctorsList = moviesList;
        this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         final Doctor movie = filterdoctorsList.get(position);
        holder.doctorname.setText(movie.getDoctorname());
        holder.specialization.setText(movie.getSpecialization());
        holder.phonenumber.setText(movie.getPhonenumber());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivityDoctor.class );
                context.startActivity(intent);
//                showNotification(context,"zaad","asdad",intent);
            }
        });

    }
// intent   public void showNotification(Context context, String title, String body, Intent intent) {
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        int notificationId = 1;
//        String channelId = "channel-01";
//        String channelName = "Channel Name";
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel mChannel = new NotificationChannel(
//                    channelId, channelName, importance);
//            AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                    .build();
//            mChannel.setSound(alarmSound, audioAttributes);
//            notificationManager.createNotificationChannel(mChannel);
//
//        }
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setSound(alarmSound)
//                .setContentText(body);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
//                0,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//        mBuilder.setContentIntent(resultPendingIntent);
//
//        notificationManager.notify(notificationId, mBuilder.build());
//    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterdoctorsList = doctorsList;
                } else {
                    List<Doctor> filteredList = new ArrayList<>();
                    for (Doctor row : doctorsList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getDoctorname().toLowerCase().contains(charString.toLowerCase()) ||(row.getSpecialization().toLowerCase()).contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filterdoctorsList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterdoctorsList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterdoctorsList = (ArrayList<Doctor>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return filterdoctorsList.size();
    }
}
