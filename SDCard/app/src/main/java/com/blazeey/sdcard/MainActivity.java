package com.blazeey.sdcard;
import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    EditText content , file_name;
    Button write , read ;
    private String filepath = "MyStorage/";
    File myFile;


    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);
        file_name = findViewById(R.id.file_name);

        write = findViewById(R.id.write);
        read = findViewById(R.id.read);

        checkPermissions();
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            write.setEnabled(false);
        }
        else {

//            myFile = new File(getExternalFilesDir(filepath) , "sample.txt");
        }

        View.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == write){

                    try {

                        String gg = file_name.getText().toString();
                        Log.v("g",gg);
                        myFile = new File(getExternalFilesDir(filepath) , gg);
//                        File myFile = new File("/sdcard/"+file_name.getText());
                        //myFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + file_name.getText());
                        myFile.createNewFile();
                        FileOutputStream fout = new FileOutputStream(myFile);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(fout);
                        myOutWriter.append(content.getText());
                        myOutWriter.close();
                        Toast.makeText(MainActivity.this, "Written", Toast.LENGTH_SHORT).show();
                        Log.v("Written ? :" , "Yes");
                    } catch (Exception e) {
                        Log.v("Written ? :" , "No");
                        e.printStackTrace();
                    }

                }

                else if(view == read){
                    try {
//                        File myFile = new File("/sdcard/"+file_name.getText());
                        // myFile = new File(getExternalFilesDir("sdcard") , file_name.getText());
                        String gg = file_name.getText().toString();
                        Log.v("g",gg);
                        myFile = new File(getExternalFilesDir(filepath) , gg);
                        FileInputStream fin = new FileInputStream(myFile);
                        BufferedReader myReader = new BufferedReader(new InputStreamReader(fin));
                        String data = "";
                        String buffer = "";
                        while((data = myReader.readLine()) != null){
                            buffer += data + "\n";
                        }
                        content.setText(buffer);
                        Log.v("Written ? :" , "Yes");
                    } catch (Exception e) {
                        Log.v("Written ? :" , "No");
                        e.printStackTrace();
                    }
                }
            }
        };

        write.setOnClickListener(handler);
        read.setOnClickListener(handler);
    }

    private void checkPermissions() {
        if((ActivityCompat.checkSelfPermission(this,permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) || (ActivityCompat.checkSelfPermission(this,permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
            String[] permissions = {permission.READ_EXTERNAL_STORAGE, permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,permissions,123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==123 && grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED)
            Toast.makeText(this, "Got the permissions", Toast.LENGTH_SHORT).show();
    }
}
