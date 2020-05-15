package sg.edu.np.WhackAMole;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.app.AlertDialog;
import android.widget.Toast;

import java.util.Random;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Whack-A-Mole" ;
    /* Hint
            - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
            - Feel free to modify the function to suit your program.
    */

    private Button button1;
    private Button button2;
    private Button button3;
    private TextView score;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        score = (TextView) findViewById(R.id.score);
        builder = new AlertDialog.Builder(this);


        Log.v(TAG, "Finished Pre-Initialisation!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting Whack-A-Mole!");
        final int[] scores = {0};
        score.setText(scores[0] + "");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Left Clicked!");
                if (button1.getText().equals("*")) {
                    scores[0]++;
                    Log.v(TAG, "Hit,score added!");
                }
                else {
                    scores[0]--;
                    Log.v(TAG, "Missed,score deducted!");
                }
                score.setText(scores[0] + "");
                setNewMole();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Middle Clicked!");
                if (button2.getText().equals("*")) {
                    scores[0]++;
                    Log.v(TAG, "Hit,score added!");
                }
                else {
                    scores[0]--;
                    Log.v(TAG, "Missed,score deducted!");
                }
                score.setText(scores[0] + "");
                setNewMole();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "Button Right Clicked!");
                if (button3.getText().equals("*")) {
                    scores[0]++;
                    Log.v(TAG, "Hit,score added!");
                }
                else {
                    scores[0]--;
                    Log.v(TAG, "Missed,score deducted!");
                }
                score.setText(scores[0] + "");
                setNewMole();
            }
        });



    }



    public void setNewMole() {

        if (score.getText().toString().isEmpty()) {
        }
        else{

            final int scores = Integer.parseInt((String) score.getText());
            if ((scores % 10 == 0) && (scores != 0)) {

                builder.setMessage("Would you like to advance to the advanced mode ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent normalWAM = new Intent(MainActivity.this, Main2Activity.class);
                                normalWAM.putExtra("score",score.getText().toString());
                                startActivity(normalWAM);
                                Log.v(TAG, "User Accepts!");
                                Log.v(TAG, "current score : " + score.getText().toString());







                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Log.v(TAG, "User declined!");
                            }
                        });

                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Warning! Insane Whack-A-Mole incoming");
                alert.show();
            }
        }
        final Button button1 = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        button1.setText("O");
        button2.setText("O");
        button3.setText("O");
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        if (randomLocation == 0) {
            button1.setText("*");
        } else if (randomLocation == 1) {
            button2.setText("*");
        } else {
            button3.setText("*");
        }
    }

    protected void onPause(){
        super.onPause();
        Log.v(TAG,"Whack-A-Mole Paused");
    }

    protected void onStop(){
        super.onStop();
        Log.v(TAG,"Whack-A-Mole Stopped");
        finish();
    }
}

