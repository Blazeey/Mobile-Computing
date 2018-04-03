package com.example.vsundar17697.attendance_manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    Button submit , clear;
    EditText name , dept , ph;
    RadioGroup gender;
    RadioButton button;

    public void clearData(){
        name.setText("");
        dept.setText("");
        ph.setText("");
        gender.clearCheck();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        name = findViewById(R.id.name);
        dept = findViewById(R.id.dept);
        ph = findViewById(R.id.ph);
        gender = findViewById(R.id.gender);
        gender.clearCheck();

        final DatabaseHandler db = new DatabaseHandler(this);

        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v){
                if (v == submit){
                    try {
                        button = findViewById(gender.getCheckedRadioButtonId());
                        String val_name = String.valueOf(name.getText());
                        String val_dept = String.valueOf(dept.getText());
                        String val_gender = String.valueOf(button.getText());
                        String val_ph = String.valueOf(ph.getText());


                        int updated = db.updateStudent(new student(val_name, val_gender, val_ph, val_dept));
                        clearData();

                        if (updated <= 0) {
                            Toast.makeText(UpdateActivity.this, "No such record found, update failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                        }


                        List<student> students = db.getAllStudents();
                        for (student cn : students) {
                            String log = cn.get_name();
                            Log.d("Name ", log);
                        }
                    }
                    catch (Exception ex){
                        Log.v("ex" , ex.getMessage());
                    }
                }
                else if(v == clear){
                    clearData();
                }
            }
        };

        submit.setOnClickListener(handler);
        clear.setOnClickListener(handler);
    }
}
