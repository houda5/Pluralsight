package com.example.pluralsight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);

        TabLayout tabs = findViewById(R.id.tabs);

        tablayoutAdapter adapter = new tablayoutAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);

        // the button that take us to Submission Activity
        Button bt = findViewById(R.id.go_to_submission_activity);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submissionIntent = new Intent(MainActivity.this, submissionActivity.class);
                startActivity(submissionIntent);
            }
        });
    }
}