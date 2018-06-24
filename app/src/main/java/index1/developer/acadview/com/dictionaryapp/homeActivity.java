package index1.developer.acadview.com.dictionaryapp;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class homeActivity extends AppCompatActivity {

    public static ArrayList<DictObjectModel> data;
    EditText et;
    Button b1;
    DatabaseHelper db;
    ArrayList<String> wordcombimelist;
    ArrayList<String> meancombimelist;
    LinkedHashMap<String, String> namelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DatabaseHelper(this);
        et = (EditText) findViewById(R.id.et1);
        b1 = (Button) findViewById(R.id.but1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = et.getText().toString();
                Intent go = new Intent(homeActivity.this, wordDisplay.class);
                go.putExtra("wd", st);
                startActivity(go);
            }
        });
    }
}
