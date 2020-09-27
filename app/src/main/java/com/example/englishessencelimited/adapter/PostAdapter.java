package com.example.englishessencelimited.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishessencelimited.R;
import com.example.englishessencelimited.model.post.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PostAdapter extends FirebaseRecyclerAdapter<
        Post, PostAdapter.postViewholder> {


    Context context;

    public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options,Context context) {
        super(options);
        this.context=context;

    }

    @Override
    protected void onBindViewHolder(@NonNull postViewholder holder, int position, @NonNull Post model) {
        holder.des.setText(model.getDescription());
        holder.deviceid.setText("Device:"+model.getDeviceId());
        Log.d("aaaaaaaa",model.getImage());
        Glide.with(context)
                .load(model.getImage())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);



    }

    @NonNull
    @Override
    public postViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_adapter, parent, false);
        return new PostAdapter.postViewholder(view);
    }

    class postViewholder extends RecyclerView.ViewHolder {
        TextView des,deviceid;
        ImageView imageView;
        public postViewholder(@NonNull View itemView)
        {
            super(itemView);

            des = itemView.findViewById(R.id.postDes);
            deviceid=itemView.findViewById(R.id.deviceID);
            imageView=itemView.findViewById(R.id.imageFirebase);

        }
    }
}
