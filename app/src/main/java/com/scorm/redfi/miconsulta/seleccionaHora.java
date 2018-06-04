package com.scorm.redfi.miconsulta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class seleccionaHora extends AppCompatActivity {

    private static String SOAP_ACTION="http://ws/horaCita";
    private static String METHOD_NAME="horaCita";
    private static String NAMESPACE="http://ws/";
    private static String URL="http://192.168.43.223:8080/WSClinica/ClinicaWS?WSDL";

    String idPaciente;
    String sNomb;
    String sFecha;
    String smotivo;
    String shora;
    String sdoctor;
    String siddoctor;
    String datos[];
    String cadena="holi";
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,bt13,bt14,bt15,bt16,bt17,bt18;
    int tamarre=1;
    TextView txHora;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_hora);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        bt1=(Button)findViewById(R.id.btn1);
        bt2=(Button)findViewById(R.id.btn2);
        bt3=(Button)findViewById(R.id.btn3);
        bt4=(Button)findViewById(R.id.btn4);
        bt5=(Button)findViewById(R.id.btn5);
        bt6=(Button)findViewById(R.id.btn6);
        bt7=(Button)findViewById(R.id.btn7);
        bt8=(Button)findViewById(R.id.btn8);
        bt9=(Button)findViewById(R.id.btn9);
        bt10=(Button)findViewById(R.id.btn10);
        bt11=(Button)findViewById(R.id.btn11);
        bt12=(Button)findViewById(R.id.btn12);
        bt13=(Button)findViewById(R.id.btn13);
        bt14=(Button)findViewById(R.id.btn14);
        bt15=(Button)findViewById(R.id.btn15);
        bt16=(Button)findViewById(R.id.btn16);
        bt17=(Button)findViewById(R.id.btn17);
        bt18=(Button)findViewById(R.id.btn18);

        txHora=(TextView)findViewById(R.id.txtHoraSelec);

        bt1.setBackgroundColor(Color.GREEN);
        bt2.setBackgroundColor(Color.GREEN);
        bt3.setBackgroundColor(Color.GREEN);
        bt4.setBackgroundColor(Color.GREEN);
        bt5.setBackgroundColor(Color.GREEN);
        bt6.setBackgroundColor(Color.GREEN);
        bt7.setBackgroundColor(Color.GREEN);
        bt8.setBackgroundColor(Color.GREEN);
        bt9.setBackgroundColor(Color.GREEN);
        bt10.setBackgroundColor(Color.GREEN);
        bt11.setBackgroundColor(Color.GREEN);
        bt12.setBackgroundColor(Color.GREEN);
        bt13.setBackgroundColor(Color.GREEN);
        bt14.setBackgroundColor(Color.GREEN);
        bt15.setBackgroundColor(Color.GREEN);
        bt16.setBackgroundColor(Color.GREEN);
        bt17.setBackgroundColor(Color.GREEN);
        bt18.setBackgroundColor(Color.GREEN);







        if(bundle!=null)
        {
            idPaciente=(String)bundle.get("paci");
            sNomb=(String)bundle.get("pnombre");
            sFecha=(String)bundle.get("pfecha");
            smotivo=(String)bundle.get("pmotivo");
            shora=(String)bundle.get("phora");
            sdoctor=(String)bundle.get("ndoctor");
            siddoctor=(String)bundle.get("piddoctor");
        }

        new Thread()
        {
            public void run()
            {
                //Declaracion de los EditText del layout
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


                request.addProperty("hora",sFecha);


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
                            cadena=sresponse;

                            datos=sresponse.split(";");

                            tamarre=datos.length;

                            for (int i=0;i<tamarre;i++)
                            {
                                if(datos[i].equals(bt1.getText()))
                                {
                                    bt1.setEnabled(false);
                                    bt1.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt2.getText()))
                                {
                                    bt2.setEnabled(false);
                                    bt2.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt3.getText()))
                                {
                                    bt3.setEnabled(false);
                                    bt3.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt4.getText()))
                                {
                                    bt4.setEnabled(false);
                                    bt4.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt5.getText()))
                                {
                                    bt5.setEnabled(false);
                                    bt5.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt6.getText()))
                                {
                                    bt6.setEnabled(false);
                                    bt6.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt7.getText()))
                                {
                                    bt7.setEnabled(false);
                                    bt7.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt8.getText()))
                                {
                                    bt8.setEnabled(false);
                                    bt8.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt9.getText()))
                                {
                                    bt9.setEnabled(false);
                                    bt9.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt10.getText()))
                                {
                                    bt10.setEnabled(false);
                                    bt10.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt11.getText()))
                                {
                                    bt11.setEnabled(false);
                                    bt11.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt12.getText()))
                                {
                                    bt12.setEnabled(false);
                                    bt12.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt13.getText()))
                                {
                                    bt13.setEnabled(false);
                                    bt13.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt14.getText()))
                                {
                                    bt14.setEnabled(false);
                                    bt14.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt15.getText()))
                                {
                                    bt15.setEnabled(false);
                                    bt15.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt16.getText()))
                                {
                                    bt16.setEnabled(false);
                                    bt16.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt17.getText()))
                                {
                                    bt17.setEnabled(false);
                                    bt17.setBackgroundColor(Color.RED);
                                }
                                if(datos[i].equals(bt18.getText()))
                                {
                                    bt18.setEnabled(false);
                                    bt18.setBackgroundColor(Color.RED);
                                }
                            }
                        }


                    }));


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        }.start();


    }

    public void btnAceptar(View v)
    {
        int tamhora;
        tamhora=txHora.getText().length();

        if(tamhora<2)
        {
            Context context = getApplicationContext();
            CharSequence text ="Elije una hora para tu cita";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {
            Intent intent = new Intent(this,confirmaCita.class);
            intent.putExtra("pidpaciente",idPaciente);
            intent.putExtra("pnombre",sNomb);
            intent.putExtra("pfecha",sFecha);
            intent.putExtra("pmotivo",smotivo);
            intent.putExtra("phora",txHora.getText());
            intent.putExtra("pdoctor",sdoctor);
            intent.putExtra("piddoctor",siddoctor);
            startActivity(intent);

        }

    }

    public void onClickb1(View v)
    {
        txHora.setText(bt1.getText());
    }
    public void onClickb2(View v)
    {
        txHora.setText(bt2.getText());
    }
    public void onClickb3(View v)
    {
        txHora.setText(bt3.getText());
    }
    public void onClickb4(View v)
    {
        txHora.setText(bt4.getText());
    }
    public void onClickb5(View v)
    {
        txHora.setText(bt5.getText());
    }
    public void onClickb6(View v)
    {
        txHora.setText(bt6.getText());
    }
    public void onClickb7(View v)
    {
        txHora.setText(bt7.getText());
    }
    public void onClickb8(View v)
    {
        txHora.setText(bt8.getText());
    }
    public void onClickb9(View v)
    {
        txHora.setText(bt9.getText());
    }
    public void onClickb10(View v)
    {
        txHora.setText(bt10.getText());
    }
    public void onClickb11(View v)
    {
        txHora.setText(bt11.getText());
    }
    public void onClickb12(View v)
    {
        txHora.setText(bt12.getText());
    }
    public void onClickb13(View v)
    {
        txHora.setText(bt13.getText());
    }
    public void onClickb14(View v)
    {
        txHora.setText(bt14.getText());
    }
    public void onClickb15(View v)
    {
        txHora.setText(bt15.getText());
    }
    public void onClickb16(View v)
    {
        txHora.setText(bt16.getText());
    }
    public void onClickb17(View v)
    {
        txHora.setText(bt17.getText());
    }
    public void onClickb18(View v)
    {
        txHora.setText(bt18.getText());
    }
}
