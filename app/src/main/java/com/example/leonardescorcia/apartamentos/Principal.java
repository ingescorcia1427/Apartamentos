package com.example.leonardescorcia.apartamentos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Principal extends AppCompatActivity {
    private ListView lstprincipal;
    private String [] opc;
    private ArrayAdapter adapter;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        lstprincipal = (ListView)findViewById(R.id.lstprincipal);
        opc = getResources().getStringArray(R.array.opciones_principal);
        adapter = new ArrayAdapter(this, R.layout.spinner_item_opciones, opc);
        lstprincipal.setAdapter(adapter);

        lstprincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        i = new Intent(Principal.this, Registrar.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(Principal.this, Listado_Apartamentos.class);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(Principal.this, Reportes.class);
                        startActivity(i);
                }
            }
        });

    }
}
