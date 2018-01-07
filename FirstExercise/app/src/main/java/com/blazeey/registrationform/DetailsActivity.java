package com.blazeey.firstexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    public TextView name,age,address,number,gender,smoking,alcohol,spinner,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initialize();
        Bundle bundle = getIntent().getBundleExtra("data");
        name.setText(bundle.getString("name"));
        address.setText(bundle.getString("address"));
        number.setText(bundle.getString("number"));
        gender.setText(bundle.getString("gender"));
        age.setText(bundle.getString("age"));
        smoking.setText(bundle.getString("smoking"));
        alcohol.setText(bundle.getString("alcohol"));
        spinner.setText(bundle.getString("spinner"));
        time.setText(bundle.getString("date"));

    }

    void initialize(){
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        number = findViewById(R.id.number);
        gender = findViewById(R.id.gender);
        smoking = findViewById(R.id.smoking);
        alcohol = findViewById(R.id.alcohol);
        spinner = findViewById(R.id.spinner);
        time = findViewById(R.id.time);
    }
}
