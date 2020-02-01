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
    EditText inputText,keyText;
    TextView tOut;
    Button btn;

    String newString,inputString;
    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.messageInput);
        keyText = findViewById(R.id.shiftNumber);
        tOut = findViewById(R.id.cipherText);
        btn = findViewById(R.id.encryptButton);

        final Spinner spinner = findViewById(R.id.targetAlphabet);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alphabets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = Integer.parseInt(keyText.getText().toString());
                inputString = inputText.getText().toString();

                if(spinner.getSelectedItem().toString().equals("Normal")){
                    newString = encryptor(inputString,key);
                } else if (spinner.getSelectedItem().toString().equals("Reverse")) {
                    newString = decryptor(inputString, key);
                } else if (inputText.length()==0){
                    inputText.setError("Missing Message");
                } else if (keyText.)//else if (spinner.getSelectedItem().toString().equals("QWERTY")) {
                  //  newString = qwerty(inputString,key);
                tOut.setText(newString);
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
        Character charac;
        int previousAscii,newAscii;
        output = new StringBuffer();

        for(int i=0;i<inputString.length();i++){
            charac = inputString.charAt(i);
            if(charac.equals(' ')){
                output.append(Character.toString(charac));
                continue;
            }
            previousAscii = (int)charac;
            newAscii = previousAscii + key;
            if(newAscii > 90 && Character.isUpperCase(charac) || newAscii > 122){
                newAscii -= 26;
            }
            output.append(Character.toString((char)newAscii));
        }
        return String.valueOf(output);
    }

    protected String decryptor(String inputString, int key){
        StringBuffer output;
        Character charac;
        int previousAscii,newAscii;
        output = new StringBuffer();

        for(int i=0;i<inputString.length();i++){
            charac = inputString.charAt(i);
            if(charac.equals(' ')){
                output.append(Character.toString(charac));
                continue;
            }
            previousAscii = (int)charac;
            newAscii = previousAscii - key;
            if(newAscii < 65 && Character.isUpperCase(charac) || newAscii < 97){
                newAscii += 26;
            }
            output.append(Character.toString((char)newAscii));
        }
        return String.valueOf(output);
    }

    protected String qwerty(String inputString, int key){
        StringBuffer output;
        Character charac;
        String char[] newqwerty = {'a'};
        char[] previoiusqwerty = {'b'};
        output = new StringBuffer();

        for(int i=0;i<inputString.length();i++){
            charac = inputString.charAt(i);
            if(charac.equals(' ')){
                output.append(Character.toString(charac));
                continue;
            }
            previoiusqwerty = (int)charac;
            newqwerty = previoiusqwerty - key;
            if(newqwerty < 65 && Character.isUpperCase(charac) || newqwerty < 97){
                newqwerty += 26;
            }
            output.append(Character.toString((char)newqwerty));
        }
        return String.valueOf(output);
    }

}

