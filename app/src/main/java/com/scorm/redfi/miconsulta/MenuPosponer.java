package com.scorm.redfi.miconsulta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MenuPosponer extends AppCompatActivity {

    private static String SOAP_ACTION="http://ws/buscarCitaPosponer";
    private static String METHOD_NAME="buscarCitaPosponer";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.137.15:8080/WSClinica/ClinicaWS?WSDL";


    String idPaciente;
    String datos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_posponer);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {
            idPaciente=(String)bundle.get("paci");
        }

        new Thread()
        {
            public void run()
            {
                //Declaracion de los EditText del layout
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("id_paciente",idPaciente);

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
                            datos=sresponse.split(";");
                            guagua();
                            MenuPosponer.this.finish();
                        }

                    }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void guagua()
    {
        Intent intent = new Intent(this,Posponer.class);
        intent.putExtra("datos",datos);
        intent.putExtra("id_paciente",idPaciente);
        startActivity(intent);
    }
}
