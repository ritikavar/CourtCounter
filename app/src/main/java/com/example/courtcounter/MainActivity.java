package com.example.courtcounter;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView scoreteamA, scoreteamB, timer,resultText;
    private Button startpause;
    boolean timmerRunning = false;
    CountDownTimer countDownTimer;
    long timeinmilisec = 1 * 60000;
    private int scoreA = 0, scoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreteamA = findViewById(R.id.teamA_score);
        scoreteamB = findViewById(R.id.teamB_score);
        timer = findViewById(R.id.text_timer);
        startpause = findViewById(R.id.btn_start_pause);
        resultText=findViewById(R.id.result);
        startpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startstop();
            }
        });
    }

    public void teamA_3point(View view) {
        scoreA += 3;
        scoreteamA.setText("" + scoreA);
    }

    public void teamA_2point(View view) {
        scoreA += 2;
        scoreteamA.setText("" + scoreA);
    }

    public void teamA_1point(View view) {
        scoreA++;
        scoreteamA.setText("" + scoreA);
    }

    public void teamB_3point(View view) {
        scoreB += 3;
        scoreteamB.setText("" + scoreB);
    }

    public void teamB_2point(View view) {
        scoreB += 2;
        scoreteamB.setText("" + scoreB);
    }

    public void teamB_1point(View view) {
        scoreB++;
        scoreteamB.setText("" + scoreB);
    }

    public void reset(View view) {
        scoreteamA.setText("0");
        scoreteamB.setText("0");
        timer.setText("1:00");
        countDownTimer.cancel();
        timmerRunning=false;
        startpause.setText("START");
        timeinmilisec=1*60000;
        resultText.setText("");
    }

    void startstop() {
        if (timmerRunning)
            stopTimer();
        else
            startTimer();
    }

    void stopTimer() {
        countDownTimer.cancel();
        timmerRunning = false;
        startpause.setText("START");
        findViewById(R.id.teamA_1point).setEnabled(true);
        findViewById(R.id.teamB_1point).setEnabled(true);
        findViewById(R.id.teamA_2point).setEnabled(true);
        findViewById(R.id.teamB_2point).setEnabled(true);
        findViewById(R.id.teamA_3point).setEnabled(true);
        findViewById(R.id.teamB_3point).setEnabled(true);
        timeinmilisec = 1 * 60000;
        display();

    }

    void startTimer() {
        countDownTimer = new CountDownTimer(timeinmilisec, 250) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeinmilisec = millisUntilFinished;
                display();
            }

            @Override
            public void onFinish() {
                findViewById(R.id.teamA_1point).setEnabled(false);
                findViewById(R.id.teamB_1point).setEnabled(false);
                findViewById(R.id.teamA_2point).setEnabled(false);
                findViewById(R.id.teamB_2point).setEnabled(false);
                findViewById(R.id.teamA_3point).setEnabled(false);
                findViewById(R.id.teamB_3point).setEnabled(false);
                result();
            }
        }.start();
        timmerRunning = true;
        startpause.setText("PAUSE");
    }

    void display() {
        int min = (int) timeinmilisec / 60000;
        int sec = (int) timeinmilisec % 60000 / 1000;
        String timeleft;
        timeleft = "" + min + ":";
        if (sec < 10)
            timeleft += "0";
        timeleft += sec;
        timer.setText(timeleft);
    }
    void result()
    {
        if(scoreA>scoreB)
            resultText.setText("TEAM A IS WINNER");
        else if(scoreB>scoreA)
            resultText.setText("TEAM B IS WINNER");
        else
            resultText.setText("MATCH TIE");
    }
}
