package com.truc3651.thietkeungdung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // global variable
    ImageView btnPlay;
    TextView score;
    RadioGroup group;
    RadioButton rdGiraffe;
    RadioButton rdTiger;
    RadioButton rdPig;
    SeekBar giraffe;
    SeekBar tiger;
    SeekBar pig;
    int mark = 100;
    int chosen = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        disableSkb();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mark == 0){
                    Toast.makeText(MainActivity.this, "You have no score to bet", Toast.LENGTH_SHORT).show();
                }
                else if(chosen != 0){
                    timer.start();
                    diabledRd();
                    resetSkb();
                    btnPlay.setVisibility(View.INVISIBLE);
                }
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rdGiraffe)
                    chosen = 1;
                else if(checkedId == R.id.rdTiger)
                    chosen = 2;
                else
                    chosen = 3;
            }
        });
    }


    CountDownTimer timer = new CountDownTimer(60000, 300) {
        @Override
        public void onTick(long millisUntilFinished) {
            Random random = new Random();
            int one = random.nextInt(5);
            int two = random.nextInt(5);
            int three = random.nextInt(5);

            int giraffeValue = giraffe.getProgress();
            int tigerValue = tiger.getProgress();
            int pigValue = pig.getProgress();

            giraffe.setProgress(giraffeValue + one);
            tiger.setProgress(tigerValue + two);
            pig.setProgress(pigValue + three);

            if (giraffeValue == giraffe.getMax()){
                this.cancel();
                btnPlay.setVisibility(View.VISIBLE);
                enabledRd();
                if(chosen == 1){
                    mark += 10;
                    score.setText(String.valueOf(mark));
                    Toast.makeText(MainActivity.this, "You win", Toast.LENGTH_LONG).show();
                }else{
                    mark -= 10;
                    score.setText(String.valueOf(mark));
                    Toast.makeText(MainActivity.this, "You lost", Toast.LENGTH_LONG).show();
                }
            }
            else if (tigerValue == tiger.getMax()){
                this.cancel();
                btnPlay.setVisibility(View.VISIBLE);
                enabledRd();
                if(chosen == 2){
                    mark += 10;
                    score.setText(String.valueOf(mark));
                    Toast.makeText(MainActivity.this, "You win", Toast.LENGTH_LONG).show();
                }else{
                    mark -= 10;
                    score.setText(String.valueOf(mark));
                    Toast.makeText(MainActivity.this, "You lost", Toast.LENGTH_LONG).show();
                }
            }
            else if (pigValue == pig.getMax()){
                this.cancel();
                btnPlay.setVisibility(View.VISIBLE);
                enabledRd();
                if(chosen == 3){
                    mark += 10;
                    score.setText(String.valueOf(mark));
                    Toast.makeText(MainActivity.this, "You win", Toast.LENGTH_LONG).show();
                }else{
                    mark -= 10;
                    score.setText(String.valueOf(mark));
                    Toast.makeText(MainActivity.this, "You lost", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFinish() {
        }
    };

    private void diabledRd(){
        rdGiraffe.setEnabled(false);
        rdTiger.setEnabled(false);
        rdPig.setEnabled(false);
    }

    private void enabledRd(){
        rdGiraffe.setEnabled(true);
        rdTiger.setEnabled(true);
        rdPig.setEnabled(true);
    }

    private void disableSkb(){
        giraffe.setEnabled(false);
        tiger.setEnabled(false);
        pig.setEnabled(false);
    }

    private void resetSkb(){
        giraffe.setProgress(0);
        tiger.setProgress(0);
        pig.setProgress(0);
    }

    private void mapping(){
        btnPlay = (ImageView) findViewById(R.id.btnPlay);
        score = (TextView) findViewById(R.id.score);
        group = (RadioGroup) findViewById(R.id.group);
        rdGiraffe = (RadioButton) findViewById(R.id.rdGiraffe);
        rdTiger = (RadioButton) findViewById(R.id.rdTiger);
        rdPig = (RadioButton) findViewById(R.id.rdPig);
        giraffe = (SeekBar) findViewById(R.id.giraffe);
        tiger = (SeekBar) findViewById(R.id.tiger);
        pig = (SeekBar) findViewById(R.id.pig);
    }
}






