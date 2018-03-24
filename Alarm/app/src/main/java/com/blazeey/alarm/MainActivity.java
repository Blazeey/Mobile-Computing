package com.blazeey.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Context context;
    private Calendar calendar;
    private TimePickerDialog timePickerDialog;
    private TextView alarmTime;

    private Button setAlarm,cancel,setTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance();
        context = this;
        timePickerDialog = new TimePickerDialog(this,this,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);

        setAlarm = findViewById(R.id.start);
        cancel = findViewById(R.id.cancel);
        setTime = findViewById(R.id.set_time);
        alarmTime = findViewById(R.id.alarm_time);
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,123,intent,0);

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager
                        .setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                alarmTime.setText(calendar.toString());
                Toast.makeText(context, "alarm set!", Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alarmManager!=null) {
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(context, "Cancelled Alarm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Log.v("Calender",calendar.get(Calendar.HOUR_OF_DAY)+","+calendar.get(Calendar.MINUTE));
    }
}
