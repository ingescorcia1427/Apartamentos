package com.example.leonardescorcia.apartamentos;

import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity {
    private EditText cajaNomenclatura, cajaTamano, cajaPrecio;
    private CheckBox chbBalcon, chbSombra;
    private Spinner opc_piso;
    private String[] opc;
    private ArrayAdapter adapter;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaNomenclatura = (EditText) findViewById(R.id.txtNomenclatura);
        cajaTamano = (EditText) findViewById(R.id.txtTamano);
        cajaPrecio = (EditText) findViewById(R.id.txtPrecio);
        chbBalcon = (CheckBox) findViewById(R.id.chbBalcon);
        chbSombra = (CheckBox) findViewById(R.id.chbSombra);

        opc_piso = (Spinner) findViewById(R.id.spnPiso);
        opc = getResources().getStringArray(R.array.opc_nomenclatura);
        adapter = new ArrayAdapter(this, R.layout.spinner_item_opciones, opc);
        opc_piso.setAdapter(adapter);
    }

    public boolean validartodo() {
        if (cajaNomenclatura.getText().toString().isEmpty()) {
            cajaNomenclatura.setError(this.getResources().getString(R.string.validar_nomenclatura));
            cajaNomenclatura.requestFocus();
            return false;
        }
        if (cajaTamano.getText().toString().isEmpty()) {
            cajaTamano.setError(this.getResources().getString(R.string.validar_tamano));
            cajaTamano.requestFocus();
            return false;
        }
        if (cajaPrecio.getText().toString().isEmpty()) {
            cajaPrecio.setError(this.getResources().getString(R.string.validar_precio));
            cajaPrecio.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarNomenclatura(View v) {
        if (cajaNomenclatura.getText().toString().isEmpty()) {
            cajaNomenclatura.setError(this.getResources().getString(R.string.validar_nomenclatura));
            cajaNomenclatura.requestFocus();
            return false;
        }
        return true;
    }

    public boolean nomenclaturaExistente(View v) {
        ArrayList<Apartamento> apartamentos;
        apartamentos = Datos.traerApartamento(getApplicationContext());
        for (int i = 0; i < apartamentos.size(); i++) {
            if (apartamentos.get(i).getNomenclatura().equalsIgnoreCase(cajaNomenclatura.getText().toString()) &&
                    apartamentos.get(i).getPiso().equalsIgnoreCase(opc_piso.getSelectedItem().toString().trim())) {
                new AlertDialog.Builder(this).setMessage(R.string.msj_nomenclatura_existe).show();
                cajaNomenclatura.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void limpiar() {
        cajaNomenclatura.setText("");
        opc_piso.setSelection(0);
        cajaTamano.setText("");
        cajaPrecio.setText("");
        chbBalcon.setChecked(false);
        chbSombra.setChecked(false);
        cajaNomenclatura.requestFocus();
    }

    public void borrar(View v) {
        limpiar();
    }

    public void guardar(View v) {
        if (nomenclaturaExistente(v)) {
            if (validartodo()) {
                String nomenclatura, piso, tamano, sombra="", balcon="", precio;
                Apartamento ap;
                nomenclatura = cajaNomenclatura.getText().toString();
                piso = opc_piso.getSelectedItem().toString().trim();
                tamano = cajaTamano.getText().toString();
                precio = cajaPrecio.getText().toString();

                if (chbBalcon.isChecked()) balcon = getResources().getString(R.string.estado_si);
                if (!chbBalcon.isChecked()) balcon = getResources().getString(R.string.estado_no);
                if (chbSombra.isChecked()) sombra = getResources().getString(R.string.estado_si);
                if (!chbSombra.isChecked()) sombra = getResources().getString(R.string.estado_no);

                ap = new Apartamento(nomenclatura, piso, tamano, sombra, balcon, precio);
                ap.guardar(getApplicationContext());
                new AlertDialog.Builder(this).setMessage(R.string.registro_exitoso).show();
                limpiar();
            }
        }
    }

    public void buscar(View v) {
        ArrayList<Apartamento> apartamentos;
        apartamentos = Datos.traerApartamento(getApplicationContext());
        if (validarNomenclatura(v)) {
            for (int i = 0; i < apartamentos.size(); i++) {
                if (apartamentos.get(i).getNomenclatura().equalsIgnoreCase(cajaNomenclatura.getText().toString()) &&
                        apartamentos.get(i).getPiso().equalsIgnoreCase(opc_piso.getSelectedItem().toString().trim())) {
                    cajaTamano.setText(apartamentos.get(i).getTamano());
                    cajaPrecio.setText(apartamentos.get(i).getPrecio());
                }
            }
        }
    }

    public void modificar(View v) {
        new AlertDialog.Builder(this).setMessage(R.string.msj_mantenimiento).setCancelable(true).show();
    }

    public void eliminar(View v) {
        new AlertDialog.Builder(this).setMessage(R.string.msj_mantenimiento).setCancelable(true).show();
    }

    //Codigo de modificar
        /*Apartamento a,a2;
        String piso,tam,precio,balcon,sombra;
        if (validarNomenclatura()){
            a=Datos.buscarApartamentos(getApplicationContext(),cajaNomenclatura.getText().toString());
            if (a!=null){
                piso=opc_piso.getSelectedItem().toString().trim();
                tam=cajaTamano.getText().toString();
                precio=cajaPrecio.getText().toString();

                if (chbBalcon.isChecked()) balcon=res.getString(R.string.balcon);
                else balcon=res.getString(R.string.no_tiene_balcon);

                if (chbSombra.isChecked())sombra=res.getString(R.string.sombra);
                else sombra=res.getString(R.string.no_encuentra_sombra);

                a2=new Apartamento(a.getNomenclatura(),piso,tam,precio,a.getCaracteristica());
                a2.(getApplicationContext());

                Toast.makeText(getApplicationContext(),res.getString(R.string.modificado_exitoso),
                        Toast.LENGTH_SHORT).show();
                limpiar();
            }*/

}