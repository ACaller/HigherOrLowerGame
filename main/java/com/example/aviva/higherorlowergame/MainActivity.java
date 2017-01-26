package com.example.aviva.higherorlowergame;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    int points = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void Button1Click(View view) {
        checkNumbers(num1, num2);
        roll();

    }

    public void Button2Click(View view) {
        checkNumbers(num2, num1);
        roll();
    }

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
        Random r = new Random();
        num1 = r.nextInt(9);
        num2 = r.nextInt(9);

        //Ensures the 2 numbers are not the same
        while (num1 == num2) {
            num2 = r.nextInt(9);
        }

        Button left = (Button) findViewById(R.id.button1Left);
        left.setText("" + num1);
        Button right = (Button) findViewById(R.id.button2Right);
        right.setText("" + num2);
    }

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
