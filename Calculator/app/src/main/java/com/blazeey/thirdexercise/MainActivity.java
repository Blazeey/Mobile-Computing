package com.blazeey.thirdexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button number1,number2,number3,number4,number5,number6,number7,number8,number9,number0,sine,cos,tan,factorial;
    private Button add,subtract,multiply,divide,modulus;
    private ImageButton backSpace;
    private TextView expression,answer;

    private float a = 0,b = 0;
    private boolean aFlag = false, bFlag = false;
    private char operator;
    private String exp="";

    private Stack<Float> stack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+0;
                    exp+="0";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+0;
                    exp+="0";
                    expression.setText(exp);
                }

                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+1;
                    exp+="1";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+1;
                    exp+="1";
                    expression.setText(exp);
                }

                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+2;
                    exp+="2";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+2;
                    exp+="2";
                    expression.setText(exp);
                }

                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+3;
                    exp+="3";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+3;
                    exp+="3";
                    expression.setText(exp);
                }

                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+4;
                    exp+="4";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+4;
                    exp+="4";
                    expression.setText(exp);
                }
                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+5;
                    exp+="5";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+5;
                    exp+="5";
                    expression.setText(exp);
                }
                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+6;
                    exp+="6";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+6;
                    exp+="6";
                    expression.setText(exp);
                }
                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+7;
                    exp+="7";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+7;
                    exp+="7";
                    expression.setText(exp);
                }
                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+8;
                    exp+="8";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+8;
                    exp+="8";
                    expression.setText(exp);
                }
                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aFlag){
                    b = b*10+9;
                    exp+="9";
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
                else {
                    a = a*10+9;
                    exp+="9";
                    expression.setText(exp);
                }
                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator='+';
                exp+="+";
                aFlag=true;
                expression.setText(exp);
                Log.v("expression",exp);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator='-';
                exp+="-";
                aFlag=true;
                Log.v("expression",exp);
                expression.setText(exp);
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator='*';
                exp+="*";
                aFlag=true;
                Log.v("expression",exp);
                expression.setText(exp);
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator='/';
                exp+="/";
                aFlag=true;
                Log.v("expression",exp);
                expression.setText(exp);
            }
        });

        modulus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator='%';
                exp+="%";
                aFlag=true;
                Log.v("expression",exp);
                expression.setText(exp);
            }
        });

        backSpace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                expression.setText("");
                exp="";
                answer.setText("");
                a=0;b=0;aFlag=false;
                return false;
            }
        });

        backSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!exp.isEmpty()) {

                    if(exp.charAt(exp.length()-1)==operator) {
                        aFlag=false;
                    }
                    else if (aFlag) {
                        b = b / 10;
                    } else {
                        a = a / 10;
                    }
                    exp = exp.substring(0, exp.length() - 1);
                    expression.setText(exp);

                    answer.setText(String.valueOf(answer()));
                }

                Log.v("Value","a : "+a+" b : "+b);
            }
        });

        sine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!aFlag) {
                    aFlag=true;
                    operator = 's';
                    String ex = "sine(";
                    ex = ex.concat(exp);
                    exp = ex.concat(")");
                    Log.v("expression",exp);
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!aFlag) {
                    aFlag=true;
                    operator = 'c';
                    String ex = "cos(";
                    ex = ex.concat(exp);
                    exp = ex.concat(")");
                    Log.v("expression",exp);
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!aFlag) {
                    aFlag=true;
                    operator = 't';
                    String ex = "tan(";
                    ex = ex.concat(exp);
                    exp = ex.concat(")");
                    Log.v("expression",exp);
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
            }
        });

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!aFlag) {
                    aFlag=true;
                    operator = 'f';
                    exp+="!";
                    Log.v("expression",exp);
                    expression.setText(exp);
                    answer.setText(String.valueOf(answer()));
                }
            }
        });
    }

    float answer(){
        switch(operator){
            case '+':
                return a+b;

            case '-':
                return a-b;

            case '*':
                return a*b;

            case '/':
                if(b!=0)
                return a/b;

            case '%':
                return a%b;

            case 's':
                return (float) Math.sin(a);

            case 'c':
                return (float) Math.cos(a);

            case 't':
                return (float) Math.tan(a);

            case 'f':
                float x=1;
                for(int i=1;i<=a;i++){
                    x=x*i;
                }
                return x;
        }
        return 0;
    }

    private void initialize(){
        number0 = findViewById(R.id.num_0);
        number1 = findViewById(R.id.num_1);
        number2 = findViewById(R.id.num_2);
        number3 = findViewById(R.id.num_3);
        number4 = findViewById(R.id.num_4);
        number5 = findViewById(R.id.num_5);
        number6 = findViewById(R.id.num_6);
        number7 = findViewById(R.id.num_7);
        number8 = findViewById(R.id.num_8);
        number9 = findViewById(R.id.num_9);

        add = findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        backSpace = findViewById(R.id.backspace);
        modulus = findViewById(R.id.modulus);
        sine = findViewById(R.id.sine);
        cos = findViewById(R.id.cos);
        tan = findViewById(R.id.tan);
        factorial = findViewById(R.id.factorial);
        expression = findViewById(R.id.exp);
        answer = findViewById(R.id.answer);

    }
}
