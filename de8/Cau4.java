package com.example.de8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Cau4 extends AppCompatActivity {
    private EditText txt1, txt2, txt3;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1 = findViewById(R.id.ed1);
        txt2 = findViewById(R.id.ed2);
        txt3 = findViewById(R.id.ed3);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                B2so();
            }
        });
    }

    boolean isB2So(int n){
        return (n*n) % 3 ==0;
    }

    boolean isSNT(int n){
        if(n<2) return false;
        for (int i=2; i<=Math.sqrt(n); i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    boolean isSTN(int n){
        int tn = n;
        int lat = 0;
        while (n > 0){
            int tmp = n % 10;
            lat = lat * 10 + tmp;
            n /= 10;
        }
        return lat == tn;
    }

    boolean isCP(int n){
        int cp = (int) Math.sqrt(n);
        return cp * cp == n;
    }

    boolean hh(int n){
        if(n<2) return false;
        int sum = 1;
        for(int i=2; i<= Math.sqrt(n); i++){
            if(n%i ==0){
                sum += i;
                if(i!=n/i){
                    sum += n / i;
                }
            }
        }
        return sum == n;
    }

    int gcd(int a, int b){
        while (b > 0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    int lcm(int a, int b){
        return (a * b) / gcd(a, b);
    }

    List<Integer> fb(int a, int b){
        List<Integer> fbn = new ArrayList<>();
        int f1 = 0;
        int f2 = 1;
        while (f1 < b){
            if(f1 > a){
                fbn.add(f1);
            }
            int num = f1 + f2;
            f1 = f2;
            f2 = num;
        }
        return fbn;
    }

    List<Integer> fb2(int n){
        int f1 = 0;
        int f2 = 1;
        List<Integer> fbn = new ArrayList<>();
        while (f1 < n){
            fbn.add(f1);
            int num = f1 + f2;
            f1 = f2;
            f2 = num;
        }
        return fbn;
    }


    void B2so(){
        int a = Integer.parseInt(txt1.getText().toString());
        int b = Integer.parseInt(txt2.getText().toString());
        String str = "";
        for(int i=a; i<=b; i++){
           str = String.valueOf(fb2(b)) + " ";
        }
        txt3.setText(str);
    }
}