package com.example.egorov.helloworld;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    Button myButton;
    TextView number;
    CountDownTimer timer;
    Thread myThread;
    long millisUntilFinishedGlobal;
    long millisInFuture;
    long countDownInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        millisInFuture = 1000000;
        countDownInterval = 1000;
        if (savedInstanceState == null) {
            millisUntilFinishedGlobal = millisInFuture;
        }
        setContentView(R.layout.activity_second);
        myButton = (Button) findViewById(R.id.myButton);
        number = (TextView) findViewById(R.id.number);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(myButton.getText().toString(), getString(R.string.button_state_start))) {
                    myThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            timer = new CountDownTimer(millisUntilFinishedGlobal, countDownInterval) {
                                public void onTick(long millisUntilFinished) {
                                    final long millisUntilFinishedFinal = millisUntilFinished;
                                    millisUntilFinishedGlobal = millisUntilFinished;
                                    number.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            number.setText(getStringFromInt((int)((millisInFuture - millisUntilFinishedFinal)/countDownInterval)));
                                        }
                                    });
                                }
                                public void onFinish() {
                                    myButton.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            myButton.setText(R.string.button_state_start);
                                        }
                                    });
                                }
                            };
                            myButton.setText(R.string.button_state_stop);
                            timer.start();
                        }
                    });
                    myThread.run();
                } else {
                    myButton.setText(R.string.button_state_start);
                    millisUntilFinishedGlobal = millisInFuture;
                    timer.cancel();  // вроде гонка, но не могу сделать timer synchronized
                }
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //myButton.setText(savedInstanceState.getString("myButton")); // при повороте экрана таймер ставиться на паузу
        number.setText(savedInstanceState.getString("number"));
        millisUntilFinishedGlobal = savedInstanceState.getLong("millisUntilFinished");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("myButton", myButton.getText().toString());
        outState.putString("number", number.getText().toString());
        outState.putLong("millisUntilFinished", millisUntilFinishedGlobal);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myThread.interrupt();  //не понятно что происходит с потоком без этого вызова
        //если не вызывать у потока прерывание, то после поворота экрана он продолжает жить?
        // Если это так, то почему элементы на экране не продолжают обновляться, а просто замирают?
    }
    
    private String getStringFromInt(int number) {
        StringBuilder stringBuffer = new StringBuilder();
        if (number / 1000 == 1)
            stringBuffer.append("тысяча ");
        number %= 1000;
        switch (number / 100) {
            case 0:
                break;
            case 1:
                stringBuffer.append("cто ");
                break;
            case 2:
                stringBuffer.append("двести ");
                break;
            case 3:
                stringBuffer.append("триста ");
                break;
            case 4:
                stringBuffer.append("четыреста ");
                break;
            case 5:
                stringBuffer.append("пятьсот ");
                break;
            case 6:
                stringBuffer.append("шестьсот ");
                break;
            case 7:
                stringBuffer.append("семьсот ");
                break;
            case 8:
                stringBuffer.append("восемьсот ");
                break;
            case 9:
                stringBuffer.append("девятьсот ");
                break;
        }
        number %= 100;
        switch (number / 10) {
            case 0:
                break;
            case 1:
                number %= 10;
                switch (number) {
                    case 0:
                        stringBuffer.append("десять ");
                        break;
                    case 1:
                        stringBuffer.append("одиннадцать ");
                        break;
                    case 2:
                        stringBuffer.append("двенадцать ");
                        break;
                    case 3:
                        stringBuffer.append("тринадцать ");
                        break;
                    case 4:
                        stringBuffer.append("четырнадцать ");
                        break;
                    case 5:
                        stringBuffer.append("пятнадцать ");
                        break;
                    case 6:
                        stringBuffer.append("шестнадцать ");
                        break;
                    case 7:
                        stringBuffer.append("семнадцать ");
                        break;
                    case 8:
                        stringBuffer.append("восемнадцать ");
                        break;
                    case 9:
                        stringBuffer.append("девятнадцать ");
                        break;
                }
                break;
            case 2:
                stringBuffer.append("двадцать ");
                break;
            case 3:
                stringBuffer.append("тридцать ");
                break;
            case 4:
                stringBuffer.append("сорок ");
                break;
            case 5:
                stringBuffer.append("пятьдесят ");
                break;
            case 6:
                stringBuffer.append("шестьдесят ");
                break;
            case 7:
                stringBuffer.append("семьдесят ");
                break;
            case 8:
                stringBuffer.append("восемьдесят ");
                break;
            case 9:
                stringBuffer.append("девяносто ");
                break;
        }
        if (number >= 10) {
            number %= 10;
            switch (number) {
                case 0:
                    break;
                case 1:
                    stringBuffer.append("один ");
                    break;
                case 2:
                    stringBuffer.append("два ");
                    break;
                case 3:
                    stringBuffer.append("три ");
                    break;
                case 4:
                    stringBuffer.append("четыре ");
                    break;
                case 5:
                    stringBuffer.append("пять ");
                    break;
                case 6:
                    stringBuffer.append("шесть ");
                    break;
                case 7:
                    stringBuffer.append("семь ");
                    break;
                case 8:
                    stringBuffer.append("восемь ");
                    break;
                case 9:
                    stringBuffer.append("девять ");
                    break;
            }
        }
        return stringBuffer.toString();
    }


}