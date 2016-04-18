package com.example.dailyexpense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class AddExpenses extends AppCompatActivity {

    Firebase mFirebaseRef;
    EditText expName, amount, date;
    Button datepicker, addExp;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        Firebase.setAndroidContext(this);

        if(getIntent()!=null)
        {
            currentUser = getIntent().getStringExtra("USER");
        }
        mFirebaseRef = new Firebase("https://fiery-heat-7352.firebaseio.com/users/expenses");
        expName = (EditText)findViewById(R.id.expName);
        amount = (EditText)findViewById(R.id.amount);
        date = (EditText)findViewById(R.id.date);
        datepicker = (Button)findViewById(R.id.calendarView);
        addExp = (Button)findViewById(R.id.addexpense);
        final Spinner catagory = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catagory.setAdapter(adapter);

        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> newExp = new HashMap<String, String>();
                newExp.put("amount",amount.getText().toString());
                newExp.put("catagory", catagory.getSelectedItem().toString());
                newExp.put("date", date.getText().toString());
                newExp.put("name", expName.getText().toString());
                newExp.put("user",currentUser);
                mFirebaseRef.push().setValue(newExp);


            }
        });



    }
}
