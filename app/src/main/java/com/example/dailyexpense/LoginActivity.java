package com.example.dailyexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    Firebase mFirebaseRef;


    EditText email, password;
    Button login, signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(LoginActivity.this);

        mFirebaseRef = new Firebase("https://fiery-heat-7352.firebaseio.com/users/username");

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();
                mFirebaseRef.authWithPassword(useremail,userpassword, authResultHandler);
               // mFirebaseRef.authWithPassword();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }


    Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
        @Override
        public void onAuthenticated(AuthData authData) {

            Intent i = new Intent(LoginActivity.this, ExpenseList.class);
            i.putExtra("USER",email.getText().toString());
            startActivity(i);
            // Authenticated successfully with payload authData
        }
        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            Toast.makeText(getApplicationContext(),firebaseError.toString(),Toast.LENGTH_LONG).show();
        }
    };


//    private class AuthResultHandler implements Firebase.AuthResultHandler {
//
//
//
//        public AuthResultHandler(String provider) {
//            this.provider = provider;
//        }
//
//        @Override
//        public void onAuthenticated(AuthData authData) {
//             //setAuthenticatedUser(authData);
//
//        }
//
//        @Override
//        public void onAuthenticationError(FirebaseError firebaseError) {
//
//            Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_LONG).show();
//             //showErrorDialog(firebaseError.toString());
//        }
//    }

}
