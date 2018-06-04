package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class nuevoPaciente extends AppCompatActivity {

    String nombre;
    String apaterno;
    String aMaterno;
    String correo;
    String contra;
    String reContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciente);
    }

    public void onClickSiguiente(View v)
    {

        int vali=0;
        int valcon=0;
        EditText etNombre=(EditText)findViewById(R.id.etNombre);
        EditText etApellidoPaterno=(EditText)findViewById(R.id.etApellidoPaterno);
        EditText etApellidoMaterno=(EditText)findViewById(R.id.etApellidoMaterno);
        EditText etCorreo=(EditText)findViewById(R.id.etCorreo);
        EditText etContrasena=(EditText)findViewById(R.id.etContrasena);
        EditText etReContrasena=(EditText)findViewById(R.id.etReContrasena);

        nombre=etNombre.getText().toString();
        apaterno=etApellidoPaterno.getText().toString();
        aMaterno=etApellidoMaterno.getText().toString();
        correo=etCorreo.getText().toString();
        contra=etContrasena.getText().toString();
        reContra=etReContrasena.getText().toString();

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        CharSequence inputStr = correo;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            vali=1;
        }
        else
        {
            vali=0;
        }

        if(contra.equals(reContra))
        {
            valcon=1;
        }
        else {
            valcon=0;
        }

        if(vali==1)
        {
            if(valcon==1)
            {
                Intent intent=new Intent(this,Siguiente.class);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("AP",apaterno);
                intent.putExtra("AM",aMaterno);
                intent.putExtra("Correo",correo);
                intent.putExtra("Pass",contra);
                startActivity(intent);
                nuevoPaciente.this.finish();
            }
            if(valcon==0)
            {
                Context context = getApplicationContext();
                CharSequence text = "No coinsiden las contraseñas.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        if(vali==0)
        {
            Context context = getApplicationContext();
            CharSequence text = "Formato de Email Incorrecto";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
