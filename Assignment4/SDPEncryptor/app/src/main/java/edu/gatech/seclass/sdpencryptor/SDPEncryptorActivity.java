package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class SDPEncryptorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText messageInput;
    EditText shiftNumber;
    TextView cipherText;
    Button encryptButton;

    String newString,inputString;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = findViewById(R.id.messageInput);
        shiftNumber = findViewById(R.id.shiftNumber);
        cipherText = findViewById(R.id.cipherText);
        encryptButton = findViewById(R.id.encryptButton);

        final Spinner spinner = findViewById(R.id.targetAlphabet);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alphabets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = Integer.parseInt(shiftNumber.getText().toString());
                inputString = messageInput.getText().toString();

                if(spinner.getSelectedItem().toString().equals("Normal")){
                    newString = encryptor(inputString,key);
                } else if (spinner.getSelectedItem().toString().equals("Reverse")) {
                    newString = decryptor(inputString, key);
                }
                //else if (spinner.getSelectedItem().toString().equals("QWERTY")) {
                  //  newString = qwerty(inputString,key);
                cipherText.setText(newString);

                if (messageInput.getText().toString().equals("")){
                    messageInput.setError("Missing Message");
                    messageInput.requestFocus();
                } else if (shiftNumber.getText().toString().equals(26)) {
                    shiftNumber.setError("Invalid Shift Number");
                }
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    protected String encryptor(String inputString, int key){
        StringBuffer output;
        Character cha;
        int previousAscii,newAscii;
        output = new StringBuffer();

        for(int i=0;i<inputString.length();i++){
            cha = inputString.charAt(i);
            if(cha.equals(' ')){
                output.append(Character.toString(cha));
                continue;
            }
            previousAscii = (int)cha;
            newAscii = previousAscii + key;
            if(newAscii > 90 && Character.isUpperCase(cha) || newAscii > 122){
                newAscii -= 26;
            }
            output.append(Character.toString((char)newAscii));
        }
        return String.valueOf(output);
    }

    protected String decryptor(String inputString, int key){
        StringBuffer output;
        Character cha;
        int previousAscii,newAscii;
        output = new StringBuffer();

        for(int i=0;i<inputString.length();i++){
            cha = inputString.charAt(i);
            if(cha.equals(' ')){
                output.append(Character.toString(cha));

                continue;
            }
            previousAscii = (int)cha;
            newAscii = previousAscii - key;
            if(newAscii < 65 && Character.isUpperCase(cha) || newAscii < 97){
                newAscii += 26;
            }
            output.append(Character.toString((char)newAscii));

        }
        return String.valueOf(output);
    }



}

