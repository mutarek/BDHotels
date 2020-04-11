package com.techtrickbd.bdhotels.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techtrickbd.bdhotels.R;
import com.techtrickbd.bdhotels.interfaces.HotelsClick;
import com.techtrickbd.bdhotels.models.Facilities;
import com.techtrickbd.bdhotels.models.HotelModel;

import java.util.ArrayList;
import java.util.List;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelsViewHolder> {
    private HotelsClick hotelsClick;
    private List<HotelModel> hotelModels;
    private Context context;

    public HotelsAdapter(HotelsClick hotelsClick, List<HotelModel> hotelModels, Context context) {
        this.hotelsClick = hotelsClick;
        this.hotelModels = hotelModels;
        this.context = context;
    }

    @NonNull
    @Override
    public HotelsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View hotelsView = layoutInflater.inflate(R.layout.samplehotellayout, parent, false);
        final HotelsViewHolder hotelsViewHolder = new HotelsViewHolder(hotelsView);
        hotelsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotelsClick.OnHotelsClick(parent, hotelsViewHolder.getAdapterPosition());
            }
        });

        return hotelsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelsViewHolder holder, int position) {
        if (hotelModels !=null)
        {
            ArrayList<String> arrayList = hotelModels.get(position).getHotelimages();
            holder.hotelName.setText(hotelModels.get(position).getName());
            if (arrayList != null) {
                Picasso.get().load(arrayList.get(0)).into(holder.hotelImage);
            }

        }
        else
        {
            Log.d("nulldata","Null Data".toString());
        }

    }

    @Override
    public int getItemCount() {
        return hotelModels.size();
    }

    public class HotelsViewHolder extends RecyclerView.ViewHolder {

        private ImageView hotelImage;
        private TextView hotelName;
        private TextView hotelPrice;

        public HotelsViewHolder(@NonNull View itemView) {
            super(itemView);

            hotelImage = itemView.findViewById(R.id.hotel_list_image);
            hotelName = itemView.findViewById(R.id.hotel_list_name);
            hotelPrice = itemView.findViewById(R.id.hotel_list_cost);
        }
    }
}
