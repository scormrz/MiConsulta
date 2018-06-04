package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class cambiarContra extends AppCompatActivity {

    private static String SOAP_ACTION="http://ws/cambiarContra";
    private static String METHOD_NAME="cambiarContra";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.43.223:8080/WSClinica/ClinicaWS?WSDL";

    String gcorreo="gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contra);

        Intent intent = getIntent();
        gcorreo = intent.getStringExtra(menu.EXTRA_MESSAGE);


    }


    public void onClickCambiarPass(View v)
    {

        new Thread()
        {
            public void run()
            {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                final EditText txtPass=(EditText)findViewById(R.id.etPass);


                String sPass=txtPass.getText().toString();


                request.addProperty("emailcontra",gcorreo);
                request.addProperty("contrase",sPass);


                SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                envolpe.setOutputSoapObject(request);
                try {
                    HttpTransportSE http = new HttpTransportSE(URL);
                    http.call(SOAP_ACTION, envolpe);
                    final SoapPrimitive response = (SoapPrimitive) envolpe.getResponse();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();

        Context context = getApplicationContext();
        CharSequence text="Contraseña Modificada con exito: ";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        cambiarContra.this.finish();
    }
}
