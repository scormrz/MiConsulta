package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.Transport;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class Olvide extends AppCompatActivity {

    //Datos para la conexion con el servicio web
    private static String SOAP_ACTION="http://ws/buscarEmail";
    private static String METHOD_NAME="buscarEmail";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.137.15:8080/WSClinica/ClinicaWS?WSDL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide);
    }

    int eo=0;
    String idpaciente;
    String nombre;
    String apaterno;
    String amaterno;
    String email;
    String contra;
    String correoInfo ="InfoDrFrancisco@gmail.com";
    String contraseñaInfo = "Y*Y*Y*Y*";
    public void onClickRecuperar(View v)
    {

        Context context = getApplicationContext();
        CharSequence text ="Espere un momento, Recuperando contraseña.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        new Thread()
        {
            public void run()
            {
                //Declaracion de los EditText del layout
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                final EditText etEmail=(EditText)findViewById(R.id.etEmail);

                request.addProperty("email",etEmail.getText().toString());


                SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                envolpe.setOutputSoapObject(request);
                try {
                    eo=0;
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
                            String mensaje="ESTIMADO USUARIO \n le recordamos que sus datos de inicio de secion son los siguientes: \n " +
                                    "Direccion de correo: "+email+"\nContraseña: "+contra+"\n recuerde cuidar su salud";

                            if(sresponse.length()>0)
                            {

                                Context context = getApplicationContext();
                                CharSequence text ="Usuario y contraseña enviado a su correo electronico.";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                eo=1;


                                //envio de correo de recuperacion de contraseña

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
                                        message.setSubject("RECUPERACION DE CONTRASEÑA: ");
                                        //aqui va el mensaje a enviar
                                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
                                        message.setContent(mensaje,"text/html; charset=utf-8");
                                        javax.mail.Transport.send(message);
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                                Olvide.this.finish();






                            }
                            if(eo==0)
                            {
                                Context context = getApplicationContext();
                                CharSequence text = sresponse;
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
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


        }

    }
}
