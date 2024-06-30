package com.example.de1;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cau1 extends AppCompatActivity {
    private TextView txt1, txt2, txt3, txt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1 = findViewById(R.id.textView1);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);
        txt4 = findViewById(R.id.textView4);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = (TextView) v;
                txt.setText(String.valueOf(randDigital()));
                String text1 = txt1.getText().toString();
                String text2 = txt2.getText().toString();
                String text3 = txt3.getText().toString();
                txt4.setText(String.valueOf(count(text1) + count(text2) + count(text3)));
            }
        };
        txt1.setOnClickListener(listener);
        txt2.setOnClickListener(listener);
        txt3.setOnClickListener(listener);
        f("input.txt", "output.txt");
    }

    int randDigital(){
        Random random = new Random();
        int dg = random.nextInt(900) + 100;
        return dg;
    }

    int count(String str){
        int c = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '1'){
                c++;
            }
        }
        return c;
    }

    public void f(String inputFileName, String outputFileName){
        List<Integer> numbers = readFile(inputFileName);
        List<Integer> cp = getChinhPhuong(numbers);
        writeFile(cp, outputFileName);
    }

    private List<Integer> readFile(String fileName){
        AssetManager assetManager = getAssets(); // lay file input.txt tu folder assets
        List<Integer> numbers = new ArrayList<>();
        try(InputStream inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ){
            String line;
            while ((line = reader.readLine()) != null){
                String []parts = line.split("\\s+");
                for(String part : parts){
                    int number = Integer.parseInt(part.toString().toString());
                    numbers.add(number);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return numbers;
    }

    private void writeFile(List<Integer> numbers, String fileName){
        try(FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE)){
            for(int number : numbers){
                fos.write((number + " ").getBytes());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    boolean cp(int n){
        if(n < 0) return false;
        int sqrt = (int) Math.sqrt(n);
        if(sqrt * sqrt != n) return false;
        return n % 2 == 0;
    }
    private List<Integer> getChinhPhuong(List<Integer> numbers){
        List<Integer> cp = new ArrayList<>();
        for(int number : numbers){
            if(cp(number)){
                cp.add(number);
            }
        }
        return cp;
    }
}