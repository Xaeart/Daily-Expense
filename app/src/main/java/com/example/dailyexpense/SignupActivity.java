package com.example.dailyexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    Firebase mFirebaseRef;


    EditText name, email, password;
    Button cancel, signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Firebase.setAndroidContext(this);

        mFirebaseRef = new Firebase("https://fiery-heat-7352.firebaseio.com/users/username");

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        cancel = (Button)findViewById(R.id.cancel);
        signup = (Button)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFirebaseRef.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));

                Map<String, String> newUser = new HashMap<String, String>();
                newUser.put("name",name.getText().toString());
                newUser.put("email", email.getText().toString());
                newUser.put("password",password.getText().toString());
                mFirebaseRef.push().setValue(newUser);

                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });


            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
