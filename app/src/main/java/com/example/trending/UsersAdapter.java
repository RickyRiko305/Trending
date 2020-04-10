package com.example.trending;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<Items> mData;
    private Context mContext;

    public UsersAdapter(List<Items> nData, Context mContext) {
        this.mData = nData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);//viewGroup.getContext()
        view = mInflater.inflate(R.layout.all_users_display_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Items item = mData.get(i);

        myViewHolder.textViewHead.setText(item.getName());
        myViewHolder.description.setText(item.getDescriptions());
        //myViewHolder.userImage.image(R.drawable.default_profile_image);
        Picasso.get().load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.shutterstock.com%2Fsearch%2Fno%2Bprofile%2Bpicture&psig=AOvVaw10rRmRAmprGVM0tc0aStOg&ust=1586591926975000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKD3x9Kx3egCFQAAAAAdAAAAABAD").placeholder(R.drawable.default_profile_image).into(myViewHolder.userImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView description;
        public CircleImageView userImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.head);
            description = (TextView) itemView.findViewById(R.id.description);
            userImage = (CircleImageView) itemView.findViewById(R.id.all_users_profile_image);
        }
    }
}
