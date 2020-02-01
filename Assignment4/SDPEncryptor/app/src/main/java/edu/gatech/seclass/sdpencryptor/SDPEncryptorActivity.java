package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SDPEncryptorActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    EditText inputText,keyText;
    TextView tOut;
    Button btn;

    String newString,inputString;
    int key;

    Switch sw1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.targetAlphabet);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alphabets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        inputText = findViewById(R.id.messageInput);
        keyText = findViewById(R.id.shiftNumber);
        tOut = findViewById(R.id.cipherText);
        btn = findViewById(R.id.encryptButton);
        sw1 = findViewById(R.id.switch1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = Integer.parseInt(keyText.getText().toString());
                inputString = inputText.getText().toString();

                if(sw1.isChecked()){
                    newString = encrypter(inputString,key);
                }else{
                    newString = decrypter(inputString,key);
                }

                tOut.setText(newString);
            }
        });

    }

    protected String encrypter(String inputString, int key){
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

    protected String decrypter(String inputString, int key){
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

