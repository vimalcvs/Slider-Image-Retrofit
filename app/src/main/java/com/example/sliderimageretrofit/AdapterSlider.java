package com.example.sliderimageretrofit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import retrofit2.Response;

public class AdapterSlider extends SliderViewAdapter<AdapterSlider.SliderAdapterVH> {

    public Response<List<Slider>> response;

    public AdapterSlider(Response<List<Slider>> response) {
        this.response = response;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        if (response.body() != null) {
            try {
                Picasso.get()
                        .load(Constant.IMAGE_URL + response.body().get(position).image_url)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(viewHolder.imageView);
            } catch (Exception e) {
                Picasso.get()
                        .load(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(viewHolder.imageView);
            }
        }
    }

    @Override
    public int getCount() {
        if (response.body() != null) {
            return response.body().size();
        }
        return 0;
    }

    public static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_slider);
        }
    }
}