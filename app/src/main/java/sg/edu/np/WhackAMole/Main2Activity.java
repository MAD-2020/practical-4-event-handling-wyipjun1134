package sg.edu.np.WhackAMole;


import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


import java.util.Random;

public class Main2Activity extends AppCompatActivity{
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */
    final String TAG = "Whack-A-Mole-2.0";
    CountDownTimer ReadyCountDown;
    CountDownTimer MoleCountDOwn;
    private String advscore;
    private TextView score;
    private final int[] scores = {0};

    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        ReadyCountDown = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                Toast.makeText(getApplicationContext(), "get ready in " + millisUntilFinished/1000 ,Toast.LENGTH_SHORT).show();

            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "GO!",Toast.LENGTH_SHORT).show();
                setNewMole();
                ReadyCountDown.cancel();


            }
        };

        ReadyCountDown.start();
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */

        MoleCountDOwn = new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
                setNewMole();
            }

            public void onFinish() {
                MoleCountDOwn.start();


            }
        };

        MoleCountDOwn.start();
    }
    private static final int[] BUTTON_IDS = {
        /* HINT:
            Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.button10,
            R.id.button11,
            R.id.button12,


    };
    private OnClickListener click = new OnClickListener() {
        public void onClick(View v) {
            for(int id = 0; id < BUTTON_IDS.length; id++) {
                if (v.getId() == BUTTON_IDS[id]) {
                    doCheck((Button) findViewById(BUTTON_IDS[id]));
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */
        Intent advWAM = getIntent();
        advscore = advWAM.getStringExtra("score");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        score = (TextView) findViewById(R.id.score2);
        score.setText(advscore);
        Log.v(TAG, "Current User Score: " + String.valueOf(advscore));
        scores[0] = Integer.parseInt(advscore);

        for(int id = 0; id < BUTTON_IDS.length; id++){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
            Button butt = findViewById(BUTTON_IDS[id]);
            butt.setOnClickListener(click);
        }
        readyTimer();
        placeMoleTimer();

    }

    @Override
    protected void onStart(){
        super.onStart();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText().equals("*")) {
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

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */


        for(int id = 0; id < BUTTON_IDS.length ;id++){
            Button butt = findViewById(BUTTON_IDS[id]);
            butt.setText("0");
        }

        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        Button moleButton = findViewById(BUTTON_IDS[randomLocation]);
        moleButton.setText("*");
        Log.v(TAG, "New mole location!");


    }
}

