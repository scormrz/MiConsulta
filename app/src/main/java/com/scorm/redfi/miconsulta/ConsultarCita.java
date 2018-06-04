package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ConsultarCita extends AppCompatActivity {

    private static String SOAP_ACTION="http://ws/buscarCita";
    private static String METHOD_NAME="buscarCita";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.43.223:8080/WSClinica/ClinicaWS?WSDL";


    String idPaciente;
    String datos[];
    String sfecha[];
    String shora[];
    String snombre[];
    String sap[];
    String sam[];

    String datos3[];
    int io=0;
    String [] valores;
    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cita);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {
            idPaciente=(String)bundle.get("paci");
        }










        /////////




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



                        }

                    }));


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();

        /////////


    }

    public void consul(View v)
    {

        try
        {
            int tamano= datos.length;








            listView=(ListView)findViewById(R.id.lista);

            //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,valores);
            adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,datos);
            listView.setAdapter(adapter);
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
