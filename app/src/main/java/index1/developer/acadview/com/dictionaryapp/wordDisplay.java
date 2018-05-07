package index1.developer.acadview.com.dictionaryapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import java.util.ArrayList;


public class wordDisplay extends AppCompatActivity {

    private TextSwitcher tx;
    private ArrayList<DictObjectModel> dataset;
    ImageButton right;
    ImageButton left;

    int j = 1;
    int currentindex;
    int messageCount;


    public wordDisplay(ArrayList<DictObjectModel> data) {
        this.dataset = data;
        this.messageCount = dataset.size();
    }

    public wordDisplay() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);

        Bundle b = getIntent().getExtras();

        tx = (TextSwitcher) findViewById(R.id.textSwitcher);
        right = (ImageButton) findViewById(R.id.rightbut);
        left = (ImageButton) findViewById(R.id.leftbut);


        tx.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textdis = new TextView(wordDisplay.this);
                textdis.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL);
                textdis.setTextSize(24);
                textdis.setTextColor(Color.BLUE);
                return textdis;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        tx.setAnimation(in);
        tx.setAnimation(out);
        final String i = b.getString("wd");
        currentindex = j;

        tx.setText((CharSequence) dataset.get(currentindex).getWord());

        /* while (currentindex < messageCount) {
            if (i.equalsIgnoreCase(dataset.get(currentindex).getWord())) {
                tx.setText((CharSequence) dataset.get(currentindex).getWord());
                break;
            }
            currentindex++;

           }
           * */
    }
}
        /*left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentindex--;
                if (currentindex == -1) {
                    currentindex = 0;
                    Toast.makeText(wordDisplay.this, "Reached the first word", Toast.LENGTH_SHORT).show();
                }
                tx.setText((CharSequence) dataset.get(currentindex).getWord());
                tx.setText((CharSequence) dataset.get(currentindex).getMeaning());
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentindex++;
                if (currentindex == messageCount)
                    currentindex = 0;

                tx.setText((CharSequence) dataset.get(currentindex).getWord());
                tx.setText((CharSequence) dataset.get(currentindex).getMeaning());
            }
        });
        * */




