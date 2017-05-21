package com.example.leonardescorcia.apartamentos;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Reportes extends AppCompatActivity {
    private ListView lsReportes;
    private String[] opc_reportes;
    private ArrayAdapter adapter;
    private Intent i;
    private Resources res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        res=this.getResources();
        lsReportes = (ListView)findViewById(R.id.lstReportes);
        opc_reportes = getResources().getStringArray(R.array.opciones_reportes);
        adapter = new ArrayAdapter(this, R.layout.spinner_item_opciones,opc_reportes);
        lsReportes.setAdapter(adapter);

        lsReportes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        new AlertDialog.Builder(Reportes.this).setMessage(contarSB()).setCancelable(true).show();
                        break;
                    case 1:
                        new AlertDialog.Builder(Reportes.this).setMessage(aptoMasCaro()).setCancelable(true).show();
                        break;
                    case 2:
                        new AlertDialog.Builder(Reportes.this).setMessage(aptoMayorTamano()).setCancelable(true).show();
                        break;
                    case 3:
                        new AlertDialog.Builder(Reportes.this).setMessage(promedio()).setCancelable(true).show();
                }
            }
        });
    }

    public String contarSB(){
        ArrayList<Apartamento> apartamentos;
        apartamentos=Datos.traerApartamento(getApplicationContext());
        String mensaje,sombra,balcon,con_bl,con_sm;
        int contb=0,conts=0,cont=0;

        for (int i=0; i<apartamentos.size();i++){
            con_bl=res.getString(R.string.estado_si);
            con_sm=res.getString(R.string.estado_si);

            if (apartamentos.get(i).getBalcon().contains(con_bl)){
                contb=contb+1;
            }
            if (apartamentos.get(i).getSombra().contains(con_sm)){
                conts=conts+1;
            }
            if (apartamentos.get(i).getBalcon().contains(con_bl) && apartamentos.get(i).getSombra().contains(con_sm)){
                cont=cont+1;
            }

        }

        balcon = res.getString(R.string.msj_apto_con_balcon)+": "+ String.valueOf(contb);
        sombra = res.getString(R.string.msj_apto_con_sombra)+": "+ String.valueOf(conts);
        mensaje = balcon + "\n"
                + sombra + "\n"
                + res.getString(R.string.msj_apto_con_sombraYbalcon)+": " + String.valueOf(cont);
        return mensaje;
    }

    public String aptoMasCaro(){
        String mensaje,piso;
        int precio1,precio2;
        ArrayList<Apartamento> apartamentos;
        apartamentos = Datos.traerApartamento(getApplicationContext());
        precio1=Integer.parseInt(apartamentos.get(0).getPrecio());
        piso=apartamentos.get(0).getPiso();

        for (int i = 0; i < apartamentos.size(); i++){
            precio2 = Integer.parseInt(apartamentos.get(i).getPrecio());
            if (precio1 < precio2){
                piso=apartamentos.get(i).getPiso();
            }
        }

        mensaje = res.getString(R.string.msj_apto_mas_caro) + ": " + piso;
        return mensaje;
    }

    public String aptoMayorTamano(){
        String mensaje, nomen, piso;
        int tam1, tam2;
        ArrayList<Apartamento> apartamentos;
        apartamentos = Datos.traerApartamento(getApplicationContext());
        tam1=Integer.parseInt(apartamentos.get(0).getTamano());
        nomen=apartamentos.get(0).getNomenclatura();
        piso = apartamentos.get(0).getPiso();

        for (int i = 0; i < apartamentos.size(); i++){
            tam2 = Integer.parseInt(apartamentos.get(i).getTamano());
            if (tam1 < tam2){
                tam1 = tam2;
                nomen=apartamentos.get(i).getNomenclatura();
                piso = apartamentos.get(i).getPiso();
            }
        }

        mensaje = res.getString(R.string.msj_apto_mayor_tamano) + ": " + nomen + piso + " - " + tam1 + " m2";
        return mensaje;
    }

    public String promedio(){
        String mensaje;
        int tam=0,promedio;
        ArrayList<Apartamento> a=Datos.traerApartamento(getApplicationContext());
        for (int i=0;i<a.size();i++){
            tam=tam+Integer.parseInt(a.get(i).getTamano());
        }
        promedio=tam/a.size();
        mensaje=res.getString(R.string.msj_tamano_promedio)+": " + String.valueOf(promedio) + " m2";
        return mensaje;
    }

}