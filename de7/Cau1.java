package com.example.de7;

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

public class Cau1 extends AppCompatActivity {
    private TextView txt1, txt2, txt3;
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
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = (TextView) v;
                int so = Integer.parseInt(txt.getText().toString());
                so++;
                txt.setText(String.valueOf(so));
                bringFront();
            }
        };
        txt1.setOnClickListener(listener);
        txt2.setOnClickListener(listener);
        txt3.setOnClickListener(listener);
        f("input.txt", "output.txt");
    }

    void bringFront(){
        int value1 = Integer.parseInt(txt1.getText().toString());
        int value2 = Integer.parseInt(txt2.getText().toString());
        int value3 = Integer.parseInt(txt3.getText().toString());
        txt1.setZ(0);
        txt2.setZ(0);
        txt3.setZ(0);
        if(value1 >= value2 && value1 >= value3){
            txt1.bringToFront();
            if(value2 >= value3){
                txt2.setZ(txt1.getZ() - 1);
                txt3.setZ(txt1.getZ() - 2);
            }else{
                txt3.setZ(txt1.getZ() - 1);
                txt2.setZ(txt1.getZ() - 2);
            }
        }else if(value2 >= value1 && value2 >= value3){
            txt2.bringToFront();
            if(value1 >= value3){
                txt1.setZ(txt2.getZ() - 1);
                txt3.setZ(txt2.getZ() - 2);
            }else{
                txt3.setZ(txt2.getZ() - 1);
                txt1.setZ(txt2.getZ() - 2);
            }
        }else if(value3 >= value1 && value3 >= value2){
            txt3.bringToFront();
            if(value1 >= value2){
                txt1.setZ(txt3.getZ() - 1);
                txt2.setZ(txt3.getZ() - 2);
            }else{
                txt2.setZ(txt3.getZ() - 1);
                txt1.setZ(txt3.getZ() - 2);
            }
        }
    }

    public void f(String input, String output){
        List<Integer> numbers = readFile(input);
        List<Integer> cp1 = cp(numbers);
        writeFile(cp1, output);
    }

    private List<Integer> readFile(String fileName){
        AssetManager assetManager = getAssets();
        List<Integer> numbers = new ArrayList<>();
        try(InputStream inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ){
            String line;
            while ((line = reader.readLine()) != null){
                String []parts = line.split("\\s+");
                for(String part : parts){
                    int number = Integer.parseInt(part);
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

    boolean isChinhPhuong(int n){
        int cp = (int)Math.sqrt(n);
        if(cp * cp != n) return false;
        return n % 2 == 0;
    }
    private List<Integer> cp(List<Integer> numbers){
        List<Integer> soCP = new ArrayList<>();
        for (int number : numbers){
            if(isChinhPhuong(number)){
                soCP.add(number);
            }
        }
        return soCP;
    }

}