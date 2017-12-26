package com.blazeey.firstexercise;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    private DatePickerDialog dialog;
    private TimePickerDialog timePickerDialog;
    private Context context;
    private Calendar calendar = Calendar.getInstance();
    public int year,month,dayOfMonth,hourOfDay,minute;

    public EditText name,address,age,number;
    public RadioGroup gender;
    public Spinner spinner;
    public CheckBox smoking,alcohol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        dialog = new DatePickerDialog(context,MainActivity.this,2017,0,1);
        timePickerDialog = new TimePickerDialog(context,this,0,0,true);
        FloatingActionButton button = (FloatingActionButton)findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        Button submit = findViewById(R.id.submit);
        Button reset = findViewById(R.id.reset);

        initialize();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);

                calendar.set(year,month,dayOfMonth,hourOfDay,minute);
                Bundle bundle = new Bundle();
                bundle.putString("name",String.valueOf(name.getText()));
                bundle.putString("address",String.valueOf(address.getText()));
                bundle.putString("age",String.valueOf(age.getText()));
                bundle.putString("number",String.valueOf(number.getText()));

                RadioButton radioButton = findViewById(gender.getCheckedRadioButtonId());
                bundle.putString("gender",String.valueOf(radioButton.getText()));
                bundle.putString("spinner",String.valueOf(spinner.getSelectedItem().toString()));
                bundle.putString("alcohol",String.valueOf(alcohol.isChecked()));
                bundle.putString("smoking",String.valueOf(smoking.isChecked()));
                bundle.putString("date",calendar.getTime().toString());

                intent.putExtra("data",bundle);
                startActivity(intent);
                finish();

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    void initialize(){
        name = findViewById(R.id.reg_name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        number = findViewById(R.id.number);

        gender = (RadioGroup) findViewById(R.id.gender);
        spinner = findViewById(R.id.spinner);

        smoking = findViewById(R.id.smoking);
        alcohol = findViewById(R.id.alcohol);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        this.year=year;
        this.month=month;
        this.dayOfMonth=dayOfMonth;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        this.hourOfDay=hourOfDay;
        this.minute=minute;

    }
}
