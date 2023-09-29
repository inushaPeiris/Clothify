// Adapter for disply items in ADMIN dashboard

package com.ousl.clothify;

// import packages
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id_id, name_id, phone_id, email_id;


    // Constructor
    public CustomerAdapter(Context context, ArrayList id_id, ArrayList name_id, ArrayList phone_id, ArrayList email_id) {
        this.context = context;
        this.id_id = id_id;
        this.name_id = name_id;
        this.phone_id = phone_id;
        this.email_id = email_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customerlist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_id.setText(String.valueOf(id_id.get(position)));
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.phone_id.setText(String.valueOf(phone_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));

        holder.buttonViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Google_map.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_id, name_id, phone_id, email_id;
        Button buttonViewLocation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_id = itemView.findViewById(R.id.textId);
            name_id = itemView.findViewById(R.id.textName);
            phone_id = itemView.findViewById(R.id.textPhone);
            email_id = itemView.findViewById(R.id.textEmail);
            buttonViewLocation = itemView.findViewById(R.id.buttonViewLocation);

        }
    }
}