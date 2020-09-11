package com.example.pluralsight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class LearningSkillIQAdapter extends RecyclerView.Adapter<LearningSkillIQAdapter.LearningViewHolder>  {

    private ArrayList<SkillIQLeaders> mLearnerList;
    private Context mContext;

    LearningSkillIQAdapter(Context context, ArrayList<SkillIQLeaders> learnerData) {
        this.mLearnerList = learnerData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public LearningSkillIQAdapter.LearningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.learning_learner_item, parent, false);
        return new LearningViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningSkillIQAdapter.LearningViewHolder holder, int position) {
        //Get the current learner
        SkillIQLeaders currentLearner = mLearnerList.get(position);
        //Bind the data to the views
        holder.bindTo(currentLearner);
    }

    @Override
    public int getItemCount() {
        return mLearnerList.size();
    }
    static class LearningViewHolder extends RecyclerView.ViewHolder{

        //Member Variables for the holder data
        private TextView mNameText, mDetailText;
        private ImageView mImageView;
        Context mCont;

        LearningViewHolder(Context context, View itemView) {
            super(itemView);
            //Initialize the views
            mImageView = (ImageView) itemView.findViewById(R.id.badge_image);
            mNameText = (TextView) itemView.findViewById(R.id.learning_name);
            mDetailText = (TextView) itemView.findViewById(R.id.learning_detail);
            mCont = context;
            //Set the OnClickListener to the whole view
        }
        void bindTo(SkillIQLeaders currentLearner) {
            //Populate the imageview with image
            Glide.with(mCont)
                    .load(currentLearner.getbadgeUrl())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(mImageView);
            //Populate the textviews with data
            mNameText.setText(currentLearner.getName());
            mDetailText.setText(currentLearner.getDetail());
        }
    }
}
