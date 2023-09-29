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

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private Context context;
    // Add a new ArrayList for the image column
    private ArrayList description_id;
    private ArrayList<byte[]> image_id;


    // Constructor
    public ReviewAdapter(Context context, ArrayList<byte[]> image_id, ArrayList description_id) {
        this.context = context;
        this.image_id = image_id;
        this.description_id = description_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reviewlist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // Set the image using the imageBytes array
        byte[] imageBytes = image_id.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.image_id.setImageBitmap(bitmap);
        holder.description_id.setText(String.valueOf(description_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return description_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_id;
        TextView description_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_id = itemView.findViewById(R.id.viewReview);
            description_id = itemView.findViewById(R.id.textDescription);
        }
    }
}