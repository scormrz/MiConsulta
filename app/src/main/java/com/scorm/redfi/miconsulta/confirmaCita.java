package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Text;

public class confirmaCita extends AppCompatActivity {


    //Datos para la conexion con el servicio web
    private static String SOAP_ACTION="http://ws/agregarCita";
    private static String METHOD_NAME="agregarCita";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.43.223:8080/WSClinica/ClinicaWS?WSDL";

    String sidPaciente;
    String sNombre;
    String sMotivo;
    String sDoctor;
    String sHora;
    String sFecha;
    String sIdDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_cita);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {
            sidPaciente=(String)bundle.get("pidpaciente");
            sNombre=(String)bundle.get("pnombre");
            sMotivo=(String)bundle.get("pmotivo");
            sDoctor=(String)bundle.get("pdoctor");
            sHora=(String)bundle.get("phora");
            sFecha=(String)bundle.get("pfecha");
            sIdDoctor=(String)bundle.get("piddoctor");

        }



        TextView txtFecha=(TextView)findViewById(R.id.txtFecha);
        TextView txtHora=(TextView)findViewById(R.id.txtHora);
        TextView txtMotivo=(TextView)findViewById(R.id.txtMotivo);
        TextView txtDoctor=(TextView)findViewById(R.id.txtDoctor);


        txtFecha.setText(sFecha);
        txtHora.setText(sHora);
        txtMotivo.setText(sMotivo);
        txtDoctor.setText(sDoctor);
    }




    public void onClickAgregarCita(View v)
    {

        new Thread()
        {
            public void run()
            {
                //Declaracion de los EditText del layout
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


                request.addProperty("fecha",sFecha);
                request.addProperty("hora",sHora);
                request.addProperty("motivo",sMotivo);
                request.addProperty("idpaciente",sidPaciente);
                request.addProperty("idmedico",sIdDoctor);


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



        confirmaCita.this.finish();

    }

}
