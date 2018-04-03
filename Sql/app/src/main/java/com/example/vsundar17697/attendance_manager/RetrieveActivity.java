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

public class RetrieveActivity extends AppCompatActivity {


    Button submit , clear;
    EditText name , dept , ph;
    EditText gender;
    List<student> students;
    int listIndex = 0;

    public void nextData(student s){
        name.setText(s.get_name());
        dept.setText(s.get_dept());
        ph.setText(s.get_ph_no());
        gender.setText(s.get_id());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        name = findViewById(R.id.name);
        dept = findViewById(R.id.dept);
        ph = findViewById(R.id.ph);
        gender = findViewById(R.id.gender);

        final DatabaseHandler db = new DatabaseHandler(this);

        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v){
                if (v == submit){
                    String val_dept = String.valueOf(dept.getText());
                    String val_ph = String.valueOf(ph.getText());

                    if(!val_dept.equals("")){
                        students = db.getDeptStudents(val_dept);
                        listIndex = 0;
                        try{
                            nextData(students.get(listIndex++));
                        }
                        catch(Exception ex){
                            Toast.makeText(RetrieveActivity.this, "Retrieved all data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else if(!val_ph.equals("")){
                        student s = db.getStudent(val_ph);
                        nextData(s);
                        listIndex = -1;

                    }
                    else{
                        students = db.getAllStudents();
                        listIndex = 0;
                        try{
                        nextData(students.get(listIndex++));
                        }
                        catch(Exception ex){
                            Toast.makeText(RetrieveActivity.this, "Retrieved all data", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else if(v == clear){
                    if (listIndex != -1) {
                        try {
                            nextData(students.get(listIndex++));
                        } catch (Exception ex) {
                            Toast.makeText(RetrieveActivity.this, "Retrieved all data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };

        submit.setOnClickListener(handler);
        clear.setOnClickListener(handler);
    }
}

