package com.example.de8;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Cau3 extends AppCompatActivity {
    private TextView txt1, txt2, txt3;
    private Button btn1, btn2;
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
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(txt1, txt2, txt3);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(txt3, txt1, txt2);
            }
        });
    }

    void changeColor(TextView text, TextView text1, TextView text2){
        Random random = new Random();
        ColorDrawable value1 = (ColorDrawable) text1.getBackground();
        ColorDrawable value2 = (ColorDrawable) text2.getBackground();
        int Color1 = value1.getColor();
        int Color2 = value2.getColor();
        int []Color = {Color1, Color2};
        text.setBackgroundColor(Color[random.nextInt(2)]);
    }
}