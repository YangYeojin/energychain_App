package org.techtown.energychain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private  List<historyData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView his_cellerid;
        public TextView his_location;
        public TextView his_recipientid;
        public TextView his_transferAmount;
        public TextView his_second;


        public MyViewHolder(View v) {
            super(v);
            his_cellerid = v.findViewById(R.id.his_cellerid);
            his_location = v.findViewById(R.id.his_location);
            his_recipientid = v.findViewById(R.id.his_recipientid);
            his_transferAmount = v.findViewById(R.id.his_transferAmount);
            his_second = v.findViewById(R.id.his_second);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter( List<historyData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) { //하나의 글에대한 여기
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    private static String getTimestampToDate(String timestampStr){
        long timestamp = Long.parseLong(timestampStr);
        Date date = new java.util.Date(timestamp*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        historyData his = mDataset.get(position);
        holder.his_cellerid.setText(his.getCellerid());
        holder.his_location.setText(his.getLocation());
        holder.his_recipientid.setText(his.getRecipientid());
        holder.his_transferAmount.setText(String.valueOf(his.getTransferAmount()));//여기
        String realtime = getTimestampToDate(String.valueOf(his.getSecond()));
        holder.his_second.setText(realtime);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
