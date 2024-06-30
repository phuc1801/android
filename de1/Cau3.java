package com.example.de1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Cau3 extends AppCompatActivity {
    private TextView txt1, txt2, txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1 = findViewById(R.id.textView1);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = (TextView) v;
                txt.setText(String.valueOf(rand()));
                bgColor();
            }
        };
        txt1.setOnClickListener(listener);
        txt2.setOnClickListener(listener);
        txt3.setOnClickListener(listener);
        bgColor();
    }

    void bgColor(){
        int value1 = Integer.parseInt(txt1.getText().toString());
        int value2 = Integer.parseInt(txt2.getText().toString());
        int value3 = Integer.parseInt(txt3.getText().toString());
        int max = Math.max(value1, Math.max(value2, value3));
        int min = Math.min(value1, Math.min(value2, value3));
        setBackgroudColor(txt1, value1, max, min);
        setBackgroudColor(txt2, value2, max, min);
        setBackgroudColor(txt3, value3, max, min);
    }

    private void setBackgroudColor(TextView txt, int value, int max, int min){
        if(value == max){
            txt.setBackgroundColor(Color.rgb(240, 240, 240));
        }else if(value == min){
            txt.setBackgroundColor(Color.rgb(50, 50, 50));
        }else{
            txt.setBackgroundColor(Color.rgb(128, 128, 128));
        }
    }
    private int rand(){
        Random random = new Random();
        int rand = random.nextInt();
        return rand;
    }
}