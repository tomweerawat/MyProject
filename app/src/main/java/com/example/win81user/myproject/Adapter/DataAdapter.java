package com.example.win81user.myproject.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win81user.myproject.Model.Feed;
import com.example.win81user.myproject.Model.ItemModel;
import com.example.win81user.myproject.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Win81 User on 28/9/2559.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Viewholder>{
    private ItemModel itemModel;
   /////////////////add/////////


    public DataAdapter(ItemModel item) {
        itemModel=item;
    }

    ////////////////////////////
    @Override
    public DataAdapter.Viewholder onCreateViewHolder(ViewGroup viewgroup, int viewType) {
        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.single_item, viewgroup, false);
        //return new Viewholder(view);
        Viewholder vh = new Viewholder (view);
        return vh;
    }
    public Feed getItem(int position) {
        return itemModel.getFeed()[position];
    }

    @Override
    public void onBindViewHolder(DataAdapter.Viewholder holder, int position) {

        holder.tvData.setText(getItem(position).getName());
        holder.txtStatusMsg.setText(getItem(position).getStatus());
        holder.txtUrl.setText(getItem(position).getUrl());
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(getItem(position).getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timeAgo);
        Picasso.with(holder.feedImage1.getContext()).load(getItem(position).getImage()).into(holder.feedImage1);
        Picasso.with(holder.profilePic.getContext()).load(getItem(position).getProfilePic()).into(holder.profilePic);

    }

    @Override
    public int getItemCount() {
        //return 10;
        return itemModel.getFeed().length;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        ImageView profilePic,feedImage1;
        TextView  status_text,txtStatusMsg,txtUrl,tvData,timestamp;

        public Viewholder(View itemView) {
            super(itemView);

            //status_text = (TextView)itemView.findViewById(R.id.status_text);
            txtStatusMsg = (TextView)itemView.findViewById(R.id.txtStatusMsg);
            txtUrl = (TextView)itemView.findViewById(R.id.txtUrl);
            tvData = (TextView)itemView.findViewById(R.id.tvData);
            timestamp = (TextView)itemView.findViewById(R.id.timestamp);
            profilePic = (ImageView)itemView.findViewById(R.id.profilePic);
            feedImage1 = (ImageView)itemView.findViewById(R.id.feedImage1);


        }


    }
}
