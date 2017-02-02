package com.example.aviva.higherorlowergame;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num1 = 0;
    int num2 = 1;
    int points = 0; //How many points the user has scored
    int range = 10; //The range for the random numbers
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void Button1Click(View view) {
        Button button = (Button)findViewById(R.id.button1Left);
        button.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        checkNumbers(num1, num2);
        roll();
        //moveThem();

    }

    public void Button2Click(View view) {
        Button button = (Button)findViewById(R.id.button2Right);
        button.getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
        checkNumbers(num2, num1);
        roll();
    }

    //Checks if the user chose the bigger number or not
    private void checkNumbers(int a, int b) {
        if (a > b) {
            points++;
            Toast.makeText(this, "Correct! You're so smart.", Toast.LENGTH_SHORT).show();
        }
        else {
            points--;
            Toast.makeText(this, "Wrong! You so dumb.", Toast.LENGTH_SHORT).show();
        }
        TextView pointsView = (TextView)findViewById(R.id.Points);
        pointsView.setText("Score: " +points);
    }

    //Function that generates new random numbers
    private void roll() {

        //Increasing difficulty with increasing gaps between difficulty jumps
        if (range==points){
            range+=range;
        }

        Random r = new Random();
        num1 = r.nextInt(range);
        num2 = r.nextInt(range);

        //Ensures the 2 numbers are not the same
        while (num1 == num2) {
            num2 = r.nextInt(range);
        }

        Button left = (Button) findViewById(R.id.button1Left);
        left.setText("" + num1);
        Button right = (Button) findViewById(R.id.button2Right);
        right.setText("" + num2);
    }


    /*
    //Function to move the buttons around the screen
    private void moveThem(){
        //Find the maximum height the squares can move to. Don't want them above subtitle.
        TextView Subtitle = (TextView)findViewById(R.id.subtitle);
        int topmost = Subtitle.getBottom();

        //Find width and height of screen
        int maxwidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int maxheight = Resources.getSystem().getDisplayMetrics().heightPixels;
        //Get width of button
        Button left = (Button)findViewById(R.id.button1Left);

        Random r = new Random();
        int leftSide = r.nextInt(maxwidth-left.getWidth());
        //Between the bottom of the subtitle and the bottom of the screen, but leaving space so the whole box fits
        int topSide = r.nextInt(topmost-left.getHeight()-maxheight+1)+maxheight;

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
