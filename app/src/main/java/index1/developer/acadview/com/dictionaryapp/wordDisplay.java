package index1.developer.acadview.com.dictionaryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class wordDisplay extends AppCompatActivity {

    private TextSwitcher tx;
    ArrayList<String> wordcombimelist;
    ArrayList<String> meancombimelist;
    LinkedHashMap<String, String> namelist;
    DatabaseHelper db;
    public static ArrayList<DictObjectModel> data;
    ImageButton right;
    ImageButton left;

    int j = 1;
     int currentindex;
    long messageCount;

    private ViewSwitcher.ViewFactory viewFactory = new ViewSwitcher.ViewFactory()
    {
        public View makeView()
        {
            LayoutInflater inflater = LayoutInflater.from(wordDisplay.this);

            TextView textView = (TextView) inflater.inflate(R.layout.wordmeaning,null);

            return textView;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);

        tx = (TextSwitcher) findViewById(R.id.textSwitcher);
        right = (ImageButton) findViewById(R.id.rightbut);
        left = (ImageButton) findViewById(R.id.leftbut);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        data = new ArrayList<DictObjectModel>();
        fetchData();
        messageCount = data.size();


        tx.setFactory(viewFactory);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        tx.setAnimation(in);
        tx.setAnimation(out);
        final String i = b.getString("wd");
        currentindex = j;

        tx.setText((CharSequence) data.get(currentindex).getWord()+"\n "+" - "+ (CharSequence)data.get(currentindex).getMeaning());

        while (currentindex < messageCount) {
            if (i.equalsIgnoreCase(data.get(currentindex).getWord())) {
                tx.setText((CharSequence) data.get(currentindex).getWord()+"\n "+"meaning - "+ (CharSequence)data.get(currentindex).getMeaning());
                break;
            }
            currentindex++;

        }

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentindex--;
                if (currentindex == -1) {
                    currentindex = 0;
                    Toast.makeText(wordDisplay.this, "Reached the first word", Toast.LENGTH_SHORT).show();
                }
                tx.setText((CharSequence) data.get(currentindex).getWord()+"\n "+" meaning - "+ (CharSequence)data.get(currentindex).getMeaning());
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentindex++;
                if (currentindex == messageCount)
                    currentindex = 0;

                tx.setText((CharSequence) data.get(currentindex).getWord()+"\n "+"meaning -"+(CharSequence) data.get(currentindex).getMeaning());
            }
        });


    }

    public void fetchData() {
        db = new DatabaseHelper(this);
        try {

            db.createDataBase();
            db.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }


        namelist = new LinkedHashMap<>();
        int ii;
        SQLiteDatabase sd = db.getReadableDatabase();
        Cursor cursor = sd.query("Dictionary1", null, null, null, null, null, null);
        ii = cursor.getColumnIndex("word");
        wordcombimelist = new ArrayList<String>();
        meancombimelist = new ArrayList<String>();
        while (cursor.moveToNext()) {
            namelist.put(cursor.getString(ii), cursor.getString(cursor.getColumnIndex("definition")));
        }
        Iterator entries = namelist.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            wordcombimelist.add(String.valueOf(thisEntry.getKey()));
            meancombimelist.add("- " + String.valueOf(thisEntry.getValue()));
        }

        for (int i = 0; i < wordcombimelist.size(); i++) {
            data.add(new DictObjectModel(wordcombimelist.get(i), meancombimelist.get(i)));
        }
    }
}


