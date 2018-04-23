package com.shahal.assignmentproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shahal.assignmentproject.R;
import com.shahal.assignmentproject.listener.OnAnnouncementClickListener;
import com.shahal.assignmentproject.model.AnnouncementData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by shahal on 23-04-2018.
 */

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {
    private OnAnnouncementClickListener announcementClickListener;
    ArrayList<AnnouncementData> announcementData = new ArrayList<>();
    private Context context ;

    public AnnouncementAdapter(OnAnnouncementClickListener announcementClickListener, ArrayList<AnnouncementData> announcementData, Context context) {
        this.announcementClickListener = announcementClickListener;
        this.announcementData = announcementData;
        this.context = context;
    }

    public void updateData(ArrayList<AnnouncementData> announcementData){
        this.announcementData = announcementData;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_announcements, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(announcementData.get(position).getAnnouncementTitle().getValue());
        if (TextUtils.isEmpty(announcementData.get(position).getAnnouncementImage().getValue())){
            holder.ivImage.setVisibility(View.INVISIBLE);
        }else{
//            Picasso.get().load(announcementData.get(position).getAnnouncementImage().getValue()).into(holder.ivImage);
            Glide
                    .with(context)
                    .load(announcementData.get(position).getAnnouncementImage().getValue())
                    .into(holder.ivImage);
        }


    }

    @Override
    public int getItemCount() {
        return announcementData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_image)
        ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    announcementClickListener.onAnnouncementClicked(announcementData.get(getAdapterPosition()).getAnnouncementTitle().getValue(),announcementData.get(getAdapterPosition()).getAnnouncementHtml().getValue());
                }
            });
        }
    }
}
