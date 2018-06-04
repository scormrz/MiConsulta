package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class cambiarDatos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static String SOAP_ACTION="http://ws/cambiarDatos";
    private static String METHOD_NAME="cambiarDatos";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.43.223:8080/WSClinica/ClinicaWS?WSDL";

    private Spinner spEstados;
    private ArrayAdapter<String> adapter;
    private String estados[]={"Aguascaliente","Baja California","Baja California Sur","Campeche","Chiapas","Chihuahua",
            "Ciudad de mexico","Coahuila","Colima","Durango","Estado de México","Guanajuato","Guerrero","Hidalgo","Jalisco",
            "Michoacán","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa",
            "Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz","Yucatán","Zacatecas"};
    String idPaciente;
    String correoInfo ="InfoDrFrancisco@gmail.com";
    String contraseñaInfo = "Y*Y*Y*Y*";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_datos2);

        spEstados=(Spinner)findViewById(R.id.spEstados);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,estados);
        spEstados.setAdapter(adapter);
        spEstados.setOnItemSelectedListener(this);
        spEstados.setSelection(19);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {
            idPaciente=(String)bundle.get("paci");
        }
    }


    String scalle,scolonia,scp,sestado,stelefono,scorreo;


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sestado=spEstados.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClickActualizar(View v)
    {


        EditText etCalle=(EditText)findViewById(R.id.etCalle);
        EditText etNumero=(EditText)findViewById(R.id.etnumero);
        EditText etColonia=(EditText)findViewById(R.id.etColonia);
        EditText etCp=(EditText)findViewById(R.id.etCp);
        EditText etTelefono=(EditText)findViewById(R.id.etTelefono);
        EditText etCorreo=(EditText)findViewById(R.id.etCorreo);


        scalle=etCalle.getText().toString()+" #"+etNumero.getText().toString();
        if(scalle.equals(" #"))
        {
            scalle="*";
        }
        scolonia=etColonia.getText().toString();
        if(scolonia.length()<1)
        {
            scolonia="*";
        }
        scp=etCp.getText().toString();
        if(scp.length()<1)
        {
            scp="*";
        }
        stelefono=etTelefono.getText().toString();
        if(stelefono.length()<1)
        {
            stelefono="*";
        }
        scorreo=etCorreo.getText().toString();
        if(scorreo.length()<1)
        {
            scorreo="*";
        }

        new Thread()
        {
            public void run()
            {
                //Declaracion de los EditText del layout
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


                request.addProperty("id_paciente",idPaciente);
                request.addProperty("calle_numero",scalle);
                request.addProperty("colonia",scolonia);
                request.addProperty("codigo_postal",scp);
                request.addProperty("estado",sestado);
                request.addProperty("telefono",stelefono);
                request.addProperty("email", scorreo);



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
        CharSequence text ="Holi";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();






        cambiarDatos.this.finish();

    }
}
