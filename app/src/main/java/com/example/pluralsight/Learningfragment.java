package com.example.pluralsight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Learningfragment extends Fragment {

    private ArrayList<LearningLeaders> mLearner;
    private RecyclerView mRecyclerView;
    private LearningAdapter mAdapter;
    private ProgressBar mProgressBar;

    public Learningfragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        mRecyclerView =  view.findViewById(R.id.learning_recycler);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLearner = new ArrayList<LearningLeaders>();
        mAdapter= new LearningAdapter(getActivity(), mLearner);
        mRecyclerView.setAdapter(mAdapter);
        printTheListOfTopIqSkillesLearners();

        // Inflate the layout for this fragment
        return view;
    }

    private void printTheListOfTopIqSkillesLearners() {
        apiInterface taskService = BuilderAPi.buildeService(apiInterface.class);
        Call<ArrayList<LearningLeaders>> call = taskService.getTopLearnersList();

        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        call.enqueue(new Callback<ArrayList<LearningLeaders>>() {

            @Override
            public void onResponse(Call<ArrayList<LearningLeaders>> call, Response<ArrayList<LearningLeaders>> response) {
                if (response.isSuccessful()) {
                    mLearner = response.body();
                    Collections.sort(mLearner, new Comparator<LearningLeaders>() {
                        @Override
                        public int compare(LearningLeaders t1, LearningLeaders t2) {
                            return  Integer.parseInt(t2.getHours())-Integer.parseInt(t1.getHours());
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                    mAdapter= new LearningAdapter(getActivity(), mLearner);
                    mRecyclerView.setAdapter(mAdapter);

                }
                else if(response.code() == 401){
                    Toast.makeText(getContext(),"Your session has expired",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<LearningLeaders>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                if(t instanceof IOException){
                    Toast.makeText(getContext(),"A connection error occured",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Failed to retrieve items",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        printTheListOfTopIqSkillesLearners();
    }
}