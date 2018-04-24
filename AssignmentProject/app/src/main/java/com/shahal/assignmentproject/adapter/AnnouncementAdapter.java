package com.shahal.assignmentproject.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.shahal.assignmentproject.R;
import com.shahal.assignmentproject.listener.OnAnnouncementClickListener;
import com.shahal.assignmentproject.model.AnnouncementData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by shahal on 23-04-2018.
 */

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {
    private OnAnnouncementClickListener announcementClickListener;
    ArrayList<AnnouncementData> announcementData = new ArrayList<>();
    private Boolean isThumb = true;
    private Context context;

    public AnnouncementAdapter(OnAnnouncementClickListener announcementClickListener, ArrayList<AnnouncementData> announcementData, Context context) {
        this.announcementClickListener = announcementClickListener;
        this.announcementData = announcementData;
        this.context = context;
        this.isThumb = true;
    }

    public void updateData(ArrayList<AnnouncementData> announcementData, Boolean isThumb) {
        this.announcementData = announcementData;
        this.isThumb = isThumb;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_announcements, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(announcementData.get(position).getAnnouncementTitle().getValue());
        String url = isThumb ? announcementData.get(position).getAnnouncementImageThumbnail().getValue() : announcementData.get(position).getAnnouncementImage().getValue();
        if (TextUtils.isEmpty(url)) {
            holder.ivImage.setVisibility(View.INVISIBLE);
            holder.progressBar.setVisibility(View.GONE);
        } else {
            Glide.with(context)
                    .load(url)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    )
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
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
        @BindView(R.id.progressbar)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    announcementClickListener.onAnnouncementClicked(announcementData.get(getAdapterPosition()).getAnnouncementTitle().getValue(), announcementData.get(getAdapterPosition()).getAnnouncementHtml().getValue());
                }
            });
        }
    }
}
