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


import com.f.healthmonitoringdoctor.Model.AssignData;
import com.f.healthmonitoringdoctor.R;
import com.f.healthmonitoringdoctor.Activities.ProfilePatientActivity;
import com.f.healthmonitoringdoctor.Activities.SendMassageActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PatientsListAdapter extends RecyclerView.Adapter<PatientsListAdapter.MyViewHolder> implements Filterable {

    private List<AssignData> patientList;
    private List<AssignData> filterpatientlist;
    private Context context;
public Button profiles,send;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView patientname, fathername;

        public MyViewHolder(View view) {
            super(view);
            patientname = (TextView) view.findViewById(R.id.pname);
            fathername = (TextView) view.findViewById(R.id.fname);
            send = (Button) view.findViewById(R.id.send);

send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(context, SendMassageActivity.class);
        context.startActivity(intent);
    }
});

        }
    }


    public PatientsListAdapter(List<AssignData> moviesList, Context context) {
        this.patientList = moviesList;
        this.filterpatientlist = moviesList;
        this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         final AssignData movie = filterpatientlist.get(position);
        holder.patientname.setText(movie.getPatientData().getPatientname());
        holder.fathername.setText(movie.getPatientData().getFathername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent =new Intent(context, ProfilePatientActivity.class);
                mainIntent.putExtra("Patient",(Serializable) movie);

                context.startActivity(mainIntent);
            }
        });


    }
//    public void showNotification(Context context, String title, String body, Intent intent) {
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
                    filterpatientlist = patientList;
                } else {
                    List<AssignData> filteredList = new ArrayList<>();
                    for (AssignData row : patientList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPatientData().getPatientname().toLowerCase().contains(charString.toLowerCase()) ||(row.getPatientData().getFathername().toLowerCase()).contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filterpatientlist = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterpatientlist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterpatientlist = (ArrayList<AssignData>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return filterpatientlist.size();
    }
}
