package com.wladytb.storewladytb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class principal extends AppCompatActivity {
Button btnVerificar, btnRealizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().hide();
        btnRealizar=(Button) findViewById(R.id.btnRealizar);
        btnVerificar=(Button) findViewById(R.id.btnVerificar);
        btnRealizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(principal.this, MainActivity.class);
                principal.this.startActivity(intent);
            }
        });
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(principal.this, verificar.class);
                principal.this.startActivity(intent);
            }
        });
    }
}