// Adapter for disply items in ADMIN dashboard

package com.ousl.clothify;

// import packages
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id_id, item_id, category_id, price_id, description_id;
    // Add a new ArrayList for the image column
    private ArrayList<byte[]> image_id;


    // Constructor
    public MyAdapter(Context context, ArrayList id_id, ArrayList item_id, ArrayList category_id, ArrayList price_id, ArrayList description_id, ArrayList<byte[]> image_id) {
        this.context = context;
        this.id_id = id_id;
        this.item_id = item_id;
        this.category_id = category_id;
        this.price_id = price_id;
        this.description_id = description_id;
        this.image_id = image_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemlist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_id.setText(String.valueOf(id_id.get(position)));
        holder.item_id.setText(String.valueOf(item_id.get(position)));
        holder.category_id.setText(String.valueOf(category_id.get(position)));
        holder.price_id.setText(String.valueOf(price_id.get(position)));
        holder.description_id.setText(String.valueOf(description_id.get(position)));
        // Set the image using the imageBytes array

        byte[] imageBytes = image_id.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.image_id.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_id, item_id, category_id, price_id, description_id;
        ImageView image_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_id = itemView.findViewById(R.id.textId);
            item_id = itemView.findViewById(R.id.textItem);
            category_id = itemView.findViewById(R.id.textCategory);
            price_id = itemView.findViewById(R.id.textPrice);
            description_id = itemView.findViewById(R.id.textDescription);
            image_id = itemView.findViewById(R.id.viewItem);
        }
    }
}