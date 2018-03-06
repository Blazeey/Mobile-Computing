package com.blazeey.sms;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText phoneNumber,sms;
    Button send;
    SmsManager smsManager;
    private SMSReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.phone_number);
        sms = findViewById(R.id.sms);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smsManager.sendTextMessage(phoneNumber.getEditableText().toString(),null,sms.getEditableText().toString(),null,null);
            }
        });

        checkPermissions();
        smsManager = SmsManager.getDefault();
        final IntentFilter smsFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        smsFilter.setPriority(1000);
        this.smsReceiver = new SMSReceiver();
        this.registerReceiver(this.smsReceiver, smsFilter);
    }

    void checkPermissions(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)!=PackageManager.PERMISSION_GRANTED){

            String[] permissions = {Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS};

            ActivityCompat.requestPermissions(this,permissions,123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 123 && grantResults[0]==PackageManager.PERMISSION_GRANTED&& grantResults[1]==PackageManager.PERMISSION_GRANTED&& grantResults[2]==PackageManager.PERMISSION_GRANTED)
            Toast.makeText(this, "Got the permissions", Toast.LENGTH_SHORT).show();
    }
}
