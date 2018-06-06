package com.scorm.redfi.miconsulta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Posponer extends AppCompatActivity {

    String datos[];

    String idPaciente;

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
            int tamano= datos.length;
            listView=(ListView)findViewById(R.id.lista);

            adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,datos);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                    int posicion=myItemInt+1;
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


}
