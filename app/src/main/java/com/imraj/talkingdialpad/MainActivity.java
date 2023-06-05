package com.imraj.talkingdialpad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telecom.TelecomManager;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    
    private TextToSpeech tts;
    EditText inputText;


    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonStar,button0,buttonHash,buttonCall;

    ImageButton Delete;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        tts = new TextToSpeech(this, this);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonStar = findViewById(R.id.buttonStar);
        buttonHash = findViewById(R.id.buttonHash);
        buttonCall = findViewById(R.id.buttonCall);
        inputText = findViewById(R.id.my_text);
        Delete = findViewById(R.id.delete_button);



       // inputText.setCursorVisible(false);
        inputText.setFocusable(true);
        inputText.setFocusableInTouchMode(true);
        inputText.setInputType(InputType.TYPE_NULL);
        inputText.requestFocus();

 //       inputText.setSelection(inputText.getText().length());


//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(inputText.getWindowToken(), 0);






        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("0");

                inputText.setText(inputText.getText().toString() + "0");
            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("1");
                inputText.setText(inputText.getText().toString() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("2");
                inputText.setText(inputText.getText().toString() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("3");
                inputText.setText(inputText.getText().toString() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("4");
                inputText.setText(inputText.getText().toString() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("5");
                inputText.setText(inputText.getText().toString() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("6");
                inputText.setText(inputText.getText().toString() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("7");
                inputText.setText(inputText.getText().toString() + "7");
            }
        });


        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("8");
                inputText.setText(inputText.getText().toString() + "8");
            }
        });


        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("9");
                inputText.setText(inputText.getText().toString() + "9");
            }
        });

        buttonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("star");
                inputText.setText(inputText.getText().toString() + "*");
            }
        });

        buttonHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("#");
                inputText.setText(inputText.getText().toString() + "#");
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = inputText.getText().toString();
                if (phoneNumber.length() > 0) {
                    phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
                    inputText.setText(phoneNumber);
                }
            }
        });

        Delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Delete all characters
                inputText.setText("");
                return true;
            }
        });




        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak("Calling");

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    // permission has already been granted
                    String dial = "tel:" + inputText.getText().toString()                                ;
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
                    startActivity(callIntent);
                }
            }
        });









        // Repeat for other buttons
    }

    private void speak(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            //checking

            float speechRate = 2.0f;
            tts.setSpeechRate(speechRate);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language not supported");
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        } else {
            Log.e("TTS", "Initialization failed");
            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

}
