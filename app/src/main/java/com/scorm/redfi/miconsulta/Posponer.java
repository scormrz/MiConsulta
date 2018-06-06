package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Posponer extends AppCompatActivity {

    String datos[];

    String idPaciente;
    int subindice=0;

    TextView etCita;

    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posponer);

        etCita=(TextView) findViewById(R.id.txtNoCita);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {
            datos=bundle.getStringArray("datos");
        }






        try
        {
            listView=(ListView)findViewById(R.id.lista);

            adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,datos);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                    int posicion=myItemInt+1;
                    subindice=myItemInt;
                    etCita.setText(posicion+"");
                }
            });
        }catch (Exception e)
        {
            String [] meses={"Aun no tienes citas."};

            listView=(ListView)findViewById(R.id.lista);

            //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,valores);
            adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,meses);
            listView.setAdapter(adapter);
        }
    }

    public void pospo(View v)
    {
        int tama=etCita.getText().length();

        if(tama==0)
        {
            Context context = getApplicationContext();
            CharSequence text ="Elija una cita a posponer.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if(tama>0)
        {
            Intent intent = new Intent(this,Agendar.class);
            intent.putExtra("paci",idPaciente);

            startActivity(intent);
        }
    }


    public void ejem(View v)
    {
        String elejido[];
        String sresul=datos[subindice];
        elejido=sresul.split(": ");
        String citaFecha=elejido[2];
        String citaHora=elejido[3];

        String fCitaFecha="";
        for(int i=0;i<11;i++)
        {
            fCitaFecha=fCitaFecha+citaFecha.charAt(i);
        }

        String finalCitaHora="";
        for(int i=0;i<5;i++)
        {
            finalCitaHora=finalCitaHora+citaHora.charAt(i);
        }

        Context context = getApplicationContext();
        CharSequence text =finalCitaHora;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
