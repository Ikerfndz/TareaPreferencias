package com.example.tareapreferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    Button botonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        seekBar.setMax(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(seekBar.getProgress() < 5){
                            Toast t = Toast.makeText(MainActivity2.this, "Abajo", Toast.LENGTH_LONG);
                            t.show();
                        }else if(seekBar.getProgress() == 5){
                            Toast t = Toast.makeText(MainActivity2.this, "Medio", Toast.LENGTH_LONG);
                            t.show();

                        }else if(seekBar.getProgress() > 5){
                            Toast t = Toast.makeText(MainActivity2.this, "Arriba", Toast.LENGTH_LONG);
                            t.show();
                        }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        EditText txt = (EditText) findViewById(R.id.editText);
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast t = Toast.makeText(MainActivity2.this, txt.getText(), Toast.LENGTH_LONG);
                t.show();
            }
        });

        botonSalir = (Button) findViewById(R.id.button);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


}