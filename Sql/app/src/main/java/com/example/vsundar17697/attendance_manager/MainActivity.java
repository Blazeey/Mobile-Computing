package com.example.vsundar17697.attendance_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button create , insert , update , delete , retrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHandler db = new DatabaseHandler(this);

        create = findViewById(R.id.Create);
        insert = findViewById(R.id.Insert);
        update = findViewById(R.id.Update);
        delete = findViewById(R.id.Delete);
        retrieve = findViewById(R.id.Retrieve);

        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v){
                if(v == create){


                    db.deleteDatabase();
                    Toast.makeText(MainActivity.this, "Database has been freshly made", Toast.LENGTH_SHORT).show();
                }


                if(v == insert){
                    Intent intent_main = new Intent(MainActivity.this , InsertActivity.class );
                    MainActivity.this.startActivity(intent_main);
                    Log.v("Content", "InsertActivity");
                }

                else if(v == update){
                    Intent intent_main = new Intent(MainActivity.this , UpdateActivity.class );
                    MainActivity.this.startActivity(intent_main);
                    Log.v("Content", "UpdateActivity");
                }
                else if(v == delete){
                    Intent intent_main = new Intent(MainActivity.this , DeleteActivity.class );
                    MainActivity.this.startActivity(intent_main);
                    Log.v("Content", "DeleteActivity");
                }
                else if(v == retrieve){
                    Intent intent_main = new Intent(MainActivity.this , RetrieveActivity.class );
                    MainActivity.this.startActivity(intent_main);
                    Log.v("Content", "RetrieveActivity");
                }

            }
        };

        create.setOnClickListener(handler);
        insert.setOnClickListener(handler);
        update.setOnClickListener(handler);
        delete.setOnClickListener(handler);
        retrieve.setOnClickListener(handler);


    }
}
