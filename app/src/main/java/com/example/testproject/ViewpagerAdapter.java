package com.example.testproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ViewpagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> viewpagerModelList;

    public ViewpagerAdapter(Context context, ArrayList<String> viewpagerModelList) {
        this.context=context;
        this.viewpagerModelList = viewpagerModelList;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.slider_layout,container,false);
        ImageView banner=view.findViewById(R.id.banner_slide);
        Glide.with(context).load(viewpagerModelList.get(position)).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_cloud_queue_24)).into(banner);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherIntent=new Intent(Intent.ACTION_VIEW);
                otherIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=in.edu.extraclass.student"));
                context.startActivity(otherIntent);
            }
        });
        container.addView(view,0);

        return view;
    }

    @Override
    public int getCount() {
        return viewpagerModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
