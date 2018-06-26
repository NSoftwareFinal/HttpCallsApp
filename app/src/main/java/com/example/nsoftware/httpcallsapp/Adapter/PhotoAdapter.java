package com.example.nsoftware.httpcallsapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nsoftware.httpcallsapp.Model.Photo;
import com.example.nsoftware.httpcallsapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class PhotoAdapter extends ArrayAdapter<Photo> {

    private Context context;
    private int resource;
    private List<Photo> photoList;

    public PhotoAdapter(@NonNull Context context,
                        int resource, @NonNull List<Photo> photoList) {
        super(context, resource, photoList);
        this.context = context;
        this.resource = resource;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // todo/inflate item resource.
        if (convertView == null) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (layoutInflater != null) {
                convertView = layoutInflater.inflate(resource, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
        }

        // todo/setting data.
        ViewHolder holder = null;
        if ((holder = (ViewHolder) convertView.getTag()) != null) {
            Photo photo = photoList.get(position);
            final ProgressBar progressBar = holder.progressBar;

            holder.tv_title.setText(photo.getTitle());
            ImageLoader.getInstance().displayImage(photo.getThumbnailUrl(), holder.iv_thumbnail, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        return convertView;
    }

    private class ViewHolder {

        TextView tv_title;
        ProgressBar progressBar;
        ImageView iv_thumbnail;

        ViewHolder(View view) {
            // todo/findViews.
            tv_title = view.findViewById(R.id.tv_title);
            progressBar = view.findViewById(R.id.progressBar);
            iv_thumbnail = view.findViewById(R.id.iv_thumbnail);
        }
    }
}
