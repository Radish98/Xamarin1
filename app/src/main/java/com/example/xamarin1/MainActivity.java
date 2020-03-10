package com.example.xamarin1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {

    EditText codeEnterET;
    Button translateButton;
    TextView translatedPhoneWord;
    private String gotText;
    Test test = new Test() {
        @Override
        public void sum(int a, int b) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test.sum(4,5);
        codeEnterET =findViewById(R.id.codeEnterET);
        translateButton = findViewById(R.id.translateButton);
        translatedPhoneWord = findViewById(R.id.translatedPhoneWord);
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        TextView points = findViewById(R.id.points);

        points.setText("All points: " + point.x + " " + point.y);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotText = String.valueOf(codeEnterET.getText());
                final String translatedText =Translater(gotText);
                final Runnable run1 = new Runnable() {
                    @Override
                    public void run() {
                        translatedPhoneWord.setText(translatedText);
                        translatedPhoneWord.setTextColor(ContextCompat.getColor(getApplication(),R.color.colorPrimaryDark));
                    }
                };
                final Runnable run2 = new Runnable() {
                    @Override
                    public void run() {
                        translatedPhoneWord.setText(invert(translatedText));
                    }
                };

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            runOnUiThread(run1);
                            TimeUnit.SECONDS.sleep(2);
                            runOnUiThread(run2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        });
    }

    protected String Translater(String string){
        String translatedString = null;
        for(int i = 0; i < string.length(); i++){
            String str = String.valueOf(string.charAt(i));
            if("abc".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "2");
            else if("def".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "3");
            else if("ghi".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "4");
            else if("jkl".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "5");
            else if("mno".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "6");
            else if("pqrs".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "7");
            else if("tuv".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "8");
            else if("wxyz".contains(str.toLowerCase()))
                translatedString = ifNull(translatedString, "9");
            else
                translatedString = ifNull(translatedString, str);
        }
        return translatedString;
    }



    protected String invert (final String string){
        String newString = null;
        for(int i=string.length()-1; i >= 0;i--)
            newString =  ifNull(newString, String.valueOf(string.charAt(i)));
        return newString;
    }

    protected String ifNull(String string, String str){
        if(string == null)
            return str;
        else
            return string = string + str;
    }

}
