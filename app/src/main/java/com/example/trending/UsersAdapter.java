package com.example.trending;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<Items> mData;
    private Context mContext;

    public UsersAdapter(List<Items> mData, Context mContext) {
        this.mData = mData;
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

        myViewHolder.author.setText(item.getAuthor());
        myViewHolder.projectName.setText(item.getProject_name());
        myViewHolder.description.setText(item.getDescriptions());
        myViewHolder.star.setText(item.getStars());
        myViewHolder.language.setText(item.getLanguage());
        myViewHolder.fork.setText(item.getForks());
        Picasso.get().load(mData.get(i).getProfile_image()).placeholder(R.drawable.default_profile_image).into(myViewHolder.userImage);

        boolean isExpanded = mData.get(i).isExpanded();
        myViewHolder.expansionLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView author;
        public TextView projectName;
        public TextView description;
        public TextView star;
        public TextView language;
        public TextView fork;
        public CircleImageView userImage;
        LinearLayout expansionLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            author = (TextView) itemView.findViewById(R.id.author);
            projectName = (TextView) itemView.findViewById(R.id.project_name);
            description = (TextView) itemView.findViewById(R.id.description);
            star = (TextView) itemView.findViewById(R.id.stars_count);
            language = (TextView) itemView.findViewById(R.id.language_name);
            fork = (TextView) itemView.findViewById(R.id.fork_count);
            userImage = (CircleImageView) itemView.findViewById(R.id.all_users_profile_image);
            expansionLayout = (LinearLayout) itemView.findViewById(R.id.expandableLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Items item = mData.get(getAdapterPosition());
                    item.setExpanded(!item.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
