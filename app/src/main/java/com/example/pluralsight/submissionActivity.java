package com.example.pluralsight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class submissionActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private AlertDialog alertDialog;
    EditText firstname ,lastname,email,link ;
    Button buttonsubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        firstname =  findViewById(R.id.First_Name);
        lastname =  findViewById(R.id.Last_Name);
        email =  findViewById(R.id.Email_address);
        link = findViewById(R.id.link);

        buttonsubmit = findViewById(R.id.button_submit);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(submissionActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_view, viewGroup, false);
                builder.setView(dialogView);
                alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                }
        });

    }

    public void returnHome(View view) {
        // When clicking the arrow button
        Intent homeIntent = new Intent(submissionActivity.this, MainActivity.class);
        startActivity(homeIntent);
    }
    public void OkButtonClicked(View view) {
        //When clicking the yes button
        SendProjctLink();
        alertDialog.dismiss();
    }
    public void CancelButtonClicked(View view) {
        //When clicking the cancel button
        Toast.makeText(getBaseContext(), "The submission of the project canceled", Toast.LENGTH_LONG).show();
        alertDialog.dismiss();
    }


    private void SendProjctLink() {
    }

    private void SubmitMethode() {
                String fname=firstname.getText().toString();
                String lname=lastname.getText().toString();
                String email_text =email.getText().toString();
                String link_text =link.getText().toString();
        // make sure that all field is written
        if (fname.isEmpty() || lname.isEmpty() || email_text.isEmpty() || link_text.isEmpty()){
            Toast.makeText(getBaseContext(), "You have to complete all the field!", Toast.LENGTH_LONG).show();
            return;
        }
        apiInterface taskService = BuilderAPi.buildeService( apiInterface.class);
        Call<Void> call = taskService.sendProject(BASE_URL, fname, lname, email_text, link_text);

    // send the data
        call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> request, Response<Void> response) {
            //Show the succuseful dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(submissionActivity.this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.success_custom_view, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog succussalertDialog = builder.create();
            succussalertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            succussalertDialog.show();
        }

        @Override
        public void onFailure(Call<Void> request, Throwable t) {
            //Show the failed dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(submissionActivity.this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.failed_custom_view, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog failedalertDialog = builder.create();
            failedalertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            failedalertDialog.show();
        }
    });
    }
    }
