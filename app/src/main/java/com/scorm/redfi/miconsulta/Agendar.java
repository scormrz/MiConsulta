package com.scorm.redfi.miconsulta;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar.*;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Agendar extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {



    Button btnFecha,btnSiguiente;
    EditText txtFecha,txtHora;

    String nombre;
    String idPaciente;
    String snombre;
    String ap;
    String am;
    String correo;
    String pass;
    String motivo;
    String sminuto;
    String shora;
    int doitor;
    int ihora;
    int iminuto;
    String nDoc;
    String sNomb;
    String fechaAct;
    String fechaSel="2018-05-27";
    String datos[];




    Spinner spmotivos,sphora,spminutos;
    ArrayAdapter<String> aaMotivos,aahora,aaminutos;
    String [] opcMotivos=new String[]{"Consulta General","Consulta Medica de Ortodoncia","Consulta con Medico Cirujano Dental","Consulta con medico Endodoncista"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        spmotivos=(Spinner)findViewById(R.id.spinner);

        spmotivos.setOnItemSelectedListener(this);

        aaMotivos=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcMotivos);

        spmotivos.setAdapter(aaMotivos);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null)
        {
            idPaciente=(String)bundle.get("paci");
            snombre=(String)bundle.get("pnombre");
        }


        btnFecha=(Button)findViewById(R.id.btnFecha);
        btnSiguiente=(Button)findViewById(R.id.btnSiguiente);
        btnSiguiente.setEnabled(false);


        txtFecha=(EditText)findViewById(R.id.txtFecha);

        btnFecha.setOnClickListener(this);

    }

    public void onCLickPrueba(View v)
    {
        Context context = getApplicationContext();
        CharSequence text="Fecha: "+fechaSel;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onClickHora(View v)
    {
        EditText txtFecha=(EditText)findViewById(R.id.txtFecha);



        Intent intent = new Intent(this,seleccionaHora.class);
        intent.putExtra("paci",idPaciente);
        intent.putExtra("pnombre",sNomb);
        intent.putExtra("pfecha",txtFecha.getText().toString());
        intent.putExtra("pmotivo",motivo);
        intent.putExtra("phora",shora+":"+sminuto);
        intent.putExtra("ndoctor",nDoc);
        intent.putExtra("piddoctor",doitor+"");
        startActivity(intent);
        Agendar.this.finish();
    }





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        if(v==btnFecha){
            final Calendar c= Calendar.getInstance();
            int dia=c.get(Calendar.DAY_OF_MONTH);
            int mes=c.get(Calendar.MONTH);
            int ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view,int year,int monthOfYear,int dayOfMonth) {
                    txtFecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                    btnSiguiente.setEnabled(true);
                }
            }
                    ,ano,mes,dia);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()+
                    100000);
            datePickerDialog.show();

            ihora=c.get(Calendar.HOUR_OF_DAY);
            iminuto=c.get(Calendar.MINUTE);
            int dme=mes+1;
            fechaAct=dia+"/"+dme+"/"+ano;
            fechaSel=txtFecha.getText().toString();

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId())
        {
            case R.id.spinner:
                motivo=spmotivos.getSelectedItem().toString();
                if(spmotivos.getSelectedItemPosition()==0)
                {
                    doitor=501;
                    nDoc="Dr. Francisco Santiago";
                }
                if(spmotivos.getSelectedItemPosition()==1)
                {
                    doitor=500;
                    nDoc="Dr Juan Carlos Soto";
                }
                if(spmotivos.getSelectedItemPosition()==2)
                {
                    doitor=501;
                    nDoc="Dr Francisco Santiago";
                }
                if(spmotivos.getSelectedItemPosition()==3)
                {
                    doitor=502;
                    nDoc="Dr Marcos Solano";
                }
            break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
