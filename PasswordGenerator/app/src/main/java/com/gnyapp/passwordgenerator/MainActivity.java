package com.gnyapp.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gnyapp.passwordgenerator.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton buttonPassGen, buttonCopy;
    private TextView textPass;
    private EditText editPassLen;
    private CheckBox checkUpperCase, checkLowerCase, checkDigits, checkSpecialChars;

    private char[] upperCaseArr = "ABCDEFGHIJKLMNOPRSTUVWXYZ".toCharArray();
    private char[] lowerCaseArr = "ABCDEFGHIJKLMNOPRSTUVWXYZ".toLowerCase().toCharArray();
    private char[] digitsArr = "0123456789".toCharArray();
    private char[] specialCharArr = ".,!'^+%&/()?*{}[]<>-#$".toCharArray();

    private ArrayList<Character> mainPassArray = new ArrayList<Character>();
    private ArrayList<Character> password = new ArrayList<Character>();

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVars();
        buttonCopy.setVisibility(View.INVISIBLE);

        buttonPassGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonCopy.setVisibility(View.INVISIBLE);

                if(mainPassArray.size() != 0) {
                    mainPassArray.clear();
                }
                if(password.size() != 0) {
                    password.clear();
                }
                count = 0;

                if(TextUtils.isEmpty(editPassLen.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter password size!!", Toast.LENGTH_LONG).show();
                    return;
                }
                int passLen = Integer.parseInt(editPassLen.getText().toString());
                if(passLen < 6){
                    Toast.makeText(MainActivity.this, "Password size must be greater than 5!!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(passLen > 20){
                    Toast.makeText(MainActivity.this, "Password size must be lower than 20!!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!checkUpperCase.isChecked() && !checkLowerCase.isChecked() && !checkDigits.isChecked() && !checkSpecialChars.isChecked()){
                    Toast.makeText(MainActivity.this, "Select at least one password content type!!", Toast.LENGTH_LONG).show();
                    return;
                }

               if(mainPassArray.size() == 0) {

                    if (checkUpperCase.isChecked()) {
                        Random rnd = new Random();
                        char c = upperCaseArr[rnd.nextInt(upperCaseArr.length)];
                        password.add(c);
                        count++;
                        for (int i = 0; i < upperCaseArr.length; i++) {
                            mainPassArray.add(upperCaseArr[i]);
                        }
                    }
                    if (checkLowerCase.isChecked()) {
                        Random rnd = new Random();
                        char c = lowerCaseArr[rnd.nextInt(lowerCaseArr.length)];
                        password.add(c);
                        count++;
                        for (int i = 0; i < lowerCaseArr.length; i++) {
                            mainPassArray.add(lowerCaseArr[i]);
                        }
                    }
                    if (checkDigits.isChecked()) {
                        Random rnd = new Random();
                        char c = digitsArr[rnd.nextInt(digitsArr.length)];
                        password.add(c);
                        count++;
                        for (int i = 0; i < digitsArr.length; i++) {
                            mainPassArray.add(digitsArr[i]);
                        }
                    }
                    if (checkSpecialChars.isChecked()) {
                        Random rnd = new Random();
                        char c = specialCharArr[rnd.nextInt(specialCharArr.length)];
                        password.add(c);
                        count++;
                        for (int i = 0; i < specialCharArr.length; i++) {
                            mainPassArray.add(specialCharArr[i]);
                        }
                    }

                    Random rn = new Random();
                    StringBuilder sb = new StringBuilder();
                    for(int i = count; i < passLen; i++){
                        char c = mainPassArray.get(rn.nextInt(mainPassArray.size()));
                        password.add(c);
                    }
                   Collections.shuffle(password);
                   Collections.shuffle(password);
                   Collections.shuffle(password);

                   for(int i = 0; i < password.size(); i++){
                      sb.append(password.get(i));
                   }

                    textPass.setText("");
                    textPass.setText(sb.toString());
                    buttonCopy.setVisibility(View.VISIBLE);


               }
               else{
                    Toast.makeText(MainActivity.this, "Something gone wrong!!", Toast.LENGTH_LONG).show();
               }
            }
        });

        buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText("password", textPass.getText().toString()));
                Toast.makeText(MainActivity.this, "Password copied!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    void initVars(){
        buttonPassGen = findViewById(R.id.buttonGen);
        buttonCopy = findViewById(R.id.buttonCopy);
        textPass = findViewById(R.id.textPass);
        editPassLen = findViewById(R.id.editPassLen);
        checkUpperCase = findViewById(R.id.checkUpper);
        checkLowerCase = findViewById(R.id.checkLower);
        checkDigits = findViewById(R.id.checkDigits);
        checkSpecialChars = findViewById(R.id.checkSpecialChars);
    }

}