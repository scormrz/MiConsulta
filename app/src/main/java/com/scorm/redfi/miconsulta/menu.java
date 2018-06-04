package com.scorm.redfi.miconsulta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;


public class menu extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "guagua";

    TextView bien;
    String nombre;
    String idPaciente;
    String ap;
    String am;
    String correo;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bien=(TextView)findViewById(R.id.txtBienvenido);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {
            idPaciente=(String)bundle.get("IdPaciente");
            nombre=(String)bundle.get("Nombre");
            ap=(String)bundle.get("AP");
            am=(String)bundle.get("AM");
            correo=(String)bundle.get("Correo");
            pass=(String)bundle.get("Pas");

        }

        bien.setText(""+nombre);
    }

    public void onClickAgendar(View v)
    {
        Intent intent = new Intent(this,Agendar.class);
        intent.putExtra("paci",idPaciente);
        intent.putExtra("pnombre","Ejemplo");
        startActivity(intent);
    }
    public void onClickPosponer(View v)
    {
        Intent intent = new Intent(this,Posponer.class);
        intent.putExtra("paci",idPaciente);

        startActivity(intent);
    }
    public void onClickConsultar(View v)
    {
        Intent intent = new Intent(this,ConsultarCita.class);
        intent.putExtra("paci",idPaciente);
        startActivity(intent);
    }
    public void onClickCambiarCOntra(View v)
    {
        Intent intent = new Intent(this, cambiarContra.class);
        String message = correo;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickCambiarDatos(View v)
    {
        Intent intent = new Intent(this,cambiarDatos.class);
        intent.putExtra("paci",idPaciente);
        startActivity(intent);
    }
}
