package com.blazeey.multithreading;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Random random = new Random();

    Runnable circularRunnable = new Runnable() {
        @Override
        public void run() {

            int x = random.nextInt(100);
            progressBar.setProgress(x);
            if(x!=100) {
                textView.setText("Im doing some random work");
                status.setText("Progress : "+x);
                handler.postDelayed(circularRunnable, 1000);
            }
            else {
                textView.setText("Im finished the random work");
                Toast.makeText(MainActivity.this, "I finished the random work", Toast.LENGTH_SHORT).show();
            }
        }
    };

    ProgressBar progressBar;
    TextView textView,status,websiteData;
    EditText websiteURL;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        textView = findViewById(R.id.progress_text);
        status = findViewById(R.id.status);
        websiteData = findViewById(R.id.website_data);
        websiteURL = findViewById(R.id.website);
        button = findViewById(R.id.goURL);
        handler.postDelayed(circularRunnable,500);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = websiteURL.getEditableText().toString();
                BackTask backTask = new BackTask(getApplicationContext());
                backTask.execute(str);
            }
        });
    }

    public class BackTask extends AsyncTask<String,Void,String>{
        Context context;
        public BackTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context, "Connecting", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context, "Fetched data", Toast.LENGTH_SHORT).show();
            websiteData.setText(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL("https://"+params[0]);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null)
                    stringBuilder.append(line);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }
    }
}