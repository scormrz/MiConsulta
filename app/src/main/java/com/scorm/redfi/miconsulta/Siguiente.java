package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class Siguiente extends AppCompatActivity {

    private static String SOAP_ACTION="http://ws/nuevoPaciente";
    private static String METHOD_NAME="nuevoPaciente";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.1.72:8080/WSClinica/ClinicaWS?WSDL";

    RadioButton rdo1;
    RadioButton rdo2;
    Spinner spano;
    Spinner smes;
    Spinner sdia;

    String [] anos={"1900","1901","1902","1903","1904","1905","1906","1907","1908","1909","1910"
            ,"1911","1912","1913","1914","1915","1916","1917","1918","1919","1920"
            ,"1921","1922","1923","1924","1925","1926","1927","1928","1929","1930"
            ,"1931","1932","1933","1934","1935","1936","1937","1938","1939","1940"
            ,"1941","1942","1943","1944","1945","1946","1947","1948","1949","1950"
            ,"1951","1952","1953","1954","1955","1956","1957","1958","1959","1960"
            ,"1961","1962","1963","1964","1965","1966","1967","1968","1969","1970"
            ,"1971","1972","1973","1974","1975","1976","1977","1978","1979","1980"
            ,"1981","1982","1983","1984","1985","1986","1987","1988","1989","1990"
            ,"1991","1992","1993","1994","1995","1996","1997","1998","1999","2000"
            ,"2001","2002","2003","2004","2005","2006","2007","2008","2009","2010"
            ,"2011","2012","2013","2014","2015","2016","2017"};
    String [] meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    String [] dias={"1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};

    String fNombre;
    String fAPaterno;
    String fAMaterno;
    String fCorreo;
    String fContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siguiente);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {

            fNombre=(String)bundle.get("Nombre");
            fAPaterno=(String)bundle.get("AP");
            fAMaterno=(String)bundle.get("AM");
            fCorreo=(String)bundle.get("Correo");
            fContrasena=(String)bundle.get("Pass");
        }


        rdo1=(RadioButton)findViewById(R.id.rbFemenino);
        rdo2=(RadioButton)findViewById(R.id.rbMasculino);

        spano=(Spinner)findViewById(R.id.spAno);
        smes=(Spinner)findViewById(R.id.spMes);
        sdia=(Spinner)findViewById(R.id.spDia);

        ArrayAdapter<String> adapterAnio=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,anos);
        ArrayAdapter<String> adapterMes=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,meses);
        ArrayAdapter<String> adapterDias=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dias);

        spano.setAdapter(adapterAnio);
        smes.setAdapter(adapterMes);
        sdia.setAdapter(adapterDias);




    }

    String stanio;
    int tmes;
    String stmes;
    int tdia;
    String stdia;
    String sgenero;
    String fcalle;
    String correoInfo ="InfoDrFrancisco@gmail.com";
    String contraseñaInfo = "Y*Y*Y*Y*";
    String ffecha;

    public void onClickRegitrar(View v)
    {

        stanio=spano.getSelectedItem().toString();
        tmes=smes.getSelectedItemPosition()+1;
        stmes=""+tmes;
        tdia=sdia.getSelectedItemPosition()+1;
        stdia=""+tdia;

        if(rdo1.isChecked())
        {
            sgenero="F";
        }
        if(rdo2.isChecked())
        {
            sgenero="M";
        }

        EditText etCalle=(EditText)findViewById(R.id.etCalle);
        EditText etNumero=(EditText)findViewById(R.id.etNum);
        final EditText etColonia=(EditText)findViewById(R.id.etColonia);
        final EditText etCP=(EditText)findViewById(R.id.etCP);
        final EditText etEstado=(EditText)findViewById(R.id.etEstado);
        final EditText etTelefono=(EditText)findViewById(R.id.etTelefono);


        fcalle=etCalle.getText().toString()+" #"+etNumero.getText().toString();
        ffecha=stanio+"-"+stmes+"-"+stdia;



        new Thread()
        {
            public void run()
            {
                //Declaracion de los EditText del layout
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


                request.addProperty("nombre",fNombre);
                request.addProperty("apellido_Paterno",fAPaterno);
                request.addProperty("apellido_materno",fAMaterno);
                request.addProperty("genero",sgenero);
                request.addProperty("calle_numero",fcalle);
                request.addProperty("colonia",etColonia.getText().toString());
                request.addProperty("codigo_postal", etCP.getText().toString());
                request.addProperty("estado",etEstado.getText().toString());
                request.addProperty("telefono",etTelefono.getText().toString());
                request.addProperty("fecha_nacimiento",ffecha);
                request.addProperty("email",fCorreo);
                request.addProperty("contrasena",fContrasena);


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
        CharSequence text ="Registro con Exito.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        //envio de correo

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");



        try{
            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correoInfo,contraseñaInfo);
                        }
                    });
            if(session!=null){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correoInfo));
                //aqui va el asunto del correo
                message.setSubject("Hola "+fNombre+" Bienvenido a la Clinica Dental Dr. Francisco: ");
                //aqui va el mensaje a enviar
                String mensaje="Registro Exitoso con los siguientes datos: " +
                        "Direccion de correo: "+fCorreo+"\nContraseña: "+ fContrasena +"\n, Clinica Dental Dr. Francisco les Recuerda cuidar su salud.";
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(fCorreo));
                message.setContent(mensaje,"text/html; charset=utf-8");
                javax.mail.Transport.send(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }



        Siguiente.this.finish();

    }
}
