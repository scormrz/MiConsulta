package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    //Datos para la conexion con el servicio web
    private static String SOAP_ACTION="http://ws/buscarEmail";
    private static String METHOD_NAME="buscarEmail";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.43.223:8080/WSClinica/ClinicaWS?WSDL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

    }

    public void onClickOlvide(View v)
    {


        Intent intent = new Intent(this,Olvide.class);
        startActivity(intent);
    }

    public void onClickRegistrar(View v)
    {
        Intent intent=new Intent(this,nuevoPaciente.class);
        startActivity(intent);
    }


    int eo=0;
    String idpaciente;
    String nombre;
    String apaterno;
    String amaterno;
    String email;
    String contra;


    public void onClickIniciar(View v)
    {


        final EditText etEmail=(EditText)findViewById(R.id.txtEmail);
        final EditText etPass=(EditText)findViewById(R.id.txtContra);

        new Thread()
        {

            public void run()
            {
                eo=0;
                //Declaracion de los EditText del layout
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);




                request.addProperty("email",etEmail.getText().toString());


                SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                envolpe.setOutputSoapObject(request);
                try {
                    HttpTransportSE http = new HttpTransportSE(URL);
                    http.call(SOAP_ACTION, envolpe);
                    final SoapPrimitive response = (SoapPrimitive) envolpe.getResponse();

                    runOnUiThread((new Runnable() {
                        @Override
                        public void run() {
                            String sresponse=""+response;
                            String datos[]=sresponse.split(",");
                            idpaciente=datos[0];
                            nombre=datos[1];
                            apaterno=datos[2];
                            amaterno=datos[5];
                            email=datos[3];
                            contra=datos[4];

                            String lopass=etPass.getText().toString();

                            if(lopass.equals(contra))
                            {

                                eo=1;

                            }
                            else
                            {

                                eo=0;
                            }


                        }
                    }));

                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        }.start();
        if(eo==1)
        {
            etEmail.setText("");
            etPass.setText("");
            Intent intent = new Intent(this,menu.class);
            intent.putExtra("IdPaciente",idpaciente);
            intent.putExtra("Nombre",nombre);
            intent.putExtra("AP",apaterno);
            intent.putExtra("AM",amaterno);
            intent.putExtra("Correo",email);
            intent.putExtra("Pass",contra);
            startActivity(intent);



        }

    }
}
