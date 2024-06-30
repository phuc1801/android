package com.example.de8;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
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
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Cau1 extends AppCompatActivity {
    private TextView txt1, txt2, txt3;
    private HashSet<Integer> useColor = new HashSet<>();
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
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = (TextView) v;
                int color = randColor();
                txt.setBackgroundColor(color);
                useColor.add(color);
            }
        };
        txt1.setOnClickListener(listener);
        txt2.setOnClickListener(listener);
        txt3.setOnClickListener(listener);
        f("input.txt", "output.txt");
    }

    int randColor(){
        Random random = new Random();
        int color;
        do{
            color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }while (useColor.contains(color));
        return color;
    }

    public void f(String input, String output){
        List<Integer> numbers = readFile(input);
        List<Integer> haiChuSo = haiChuSo(numbers);
        writeFile(haiChuSo, output);
    }
    private List<Integer> readFile(String fileName){
        AssetManager assetManager = getAssets();
        List<Integer> numbers = new ArrayList<>();
        try(InputStream inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("\\s++");
                for (String part : parts){
                    int number = Integer.parseInt(part);
                    numbers.add(number);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return numbers;
    }
    void writeFile(List<Integer> numbers, String fileName){
        try(FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE)){
            for (int number : numbers){
                fos.write((number + " ").getBytes());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    boolean checkHaiChuSo(int n){
        int c = 0;
        while (n > 0){
            c++;
            n /= 10;
        }
        return c == 2;
    }

    boolean daoNguoc(int n){
       int fchu = n / 10;
       int echu = n % 10;
       return fchu == echu;
    }
    private List<Integer> haiChuSo(List<Integer> numbers){
        List<Integer> hcs = new ArrayList<>();
        for (int number : numbers){
            if(checkHaiChuSo(number) && daoNguoc(number)){
                hcs.add(number);
            }
        }
        return hcs;
    }
}