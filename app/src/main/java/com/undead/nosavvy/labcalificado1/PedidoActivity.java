package com.undead.nosavvy.labcalificado1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class PedidoActivity extends AppCompatActivity {
    private final int AMERICANA = 38;
    private final int PEPPERONI = 42;
    private final int HAWAIANA = 36;
    private final int MEATLOVER= 56;
    private String tipo_pizza;
    private final int MOZARELLA = 4;
    private final int JAMON = 8;
    private String dia;
    @BindView(R.id.masa_gruesa)
    RadioButton m_gruesa;
    @BindView(R.id.masa_delgada)
    RadioButton m_delgada;
    @BindView(R.id.masa_artesanal)
    RadioButton m_artesanal;
    @BindView(R.id.direccion)
    EditText direccion;
    @BindView(R.id.order)
    Button order;
    @BindView(R.id.check_uno)
    CheckBox queso_check;
    @BindView(R.id.check_dos)
    CheckBox jamon_check;
    @OnItemSelected(R.id.pizza_tipos)
    public void selected(AdapterView<?> parent, View view, int pos, long id){
        tipo_pizza = parent.getItemAtPosition(pos).toString();
    }
    private boolean moza = false;
    private boolean jam = false;
    private String masa;
  //  @OnCheckedChanged({R.id.check_uno,R.id.check_dos})
    public void onCheck(View view){
        CheckBox cb = (CheckBox) view;
        String txt = (String) cb.getText();
        if(txt.equals("Extra queso mozarella") && cb.isChecked()){
            moza=true;
        }else{
            moza=false;
        }
        if(txt.equals("Extra jamón") && cb.isChecked()){
            jam=true;
        }else{
            jam=false;
        }
    }

   // @OnCheckedChanged({R.id.masa_gruesa,R.id.masa_artesanal,R.id.masa_delgada})
    public void rbCheck(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.masa_gruesa:
                if (checked)
                masa = "GRUESA";
                break;
            case R.id.masa_delgada:
                if (checked)
                    masa = "DELGADA";
                break;
            case R.id.masa_artesanal:
                if (checked)
                    masa = "ARTESANAL";
                break;
        }


    }



    @OnClick(R.id.order)
    public void order(){
        double sobrecargo = 0 ;
        double pizza_precio = 0;
        double descuento = 0;
        if(moza){
            sobrecargo = sobrecargo + MOZARELLA;
        }
        if(jam){
            sobrecargo = sobrecargo + JAMON;
        }
        switch (tipo_pizza){
            case "HAWAIANA":
                pizza_precio = HAWAIANA;
                break;
            case "PEPPERONI":
                pizza_precio = PEPPERONI;
                break;
            case "AMERICANA":
                pizza_precio = AMERICANA;
                break;
            case "MEAT LOVER":
                pizza_precio = MEATLOVER;
                break;
        }
        if(dia.equals("VIERNES")){
            descuento = pizza_precio * 0.3;
        }
        String address = String.valueOf(direccion.getText());
        double total = pizza_precio + sobrecargo - descuento;
        Toast.makeText(this,"Precio de la pizza : "+String.valueOf(total)+" \nUna pizza "+tipo_pizza+" a la dirección "+address+" llegando pronto",Toast.LENGTH_SHORT).show();
        CountDownTimer waitTimer;
        waitTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent i = new Intent(getApplicationContext(),NotificacionActivity.class);
                startActivity(i);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        ButterKnife.bind(this);
        int day_num = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        dia = (day_num == 6) ? "VIERNES" : "";


    }
}
