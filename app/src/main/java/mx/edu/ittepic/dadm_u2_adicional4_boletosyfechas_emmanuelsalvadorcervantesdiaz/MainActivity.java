package mx.edu.ittepic.dadm_u2_adicional4_boletosyfechas_emmanuelsalvadorcervantesdiaz;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final int DATE_ID = 0;
    LinearLayout layo;
    int anio, mes, dia, anioActual, mesActual, diaActual;
    EditText edFecha;
    RadioButton rbVuelo, rbVueloHotel, rbVueloTransporte;
    Calendar calendario = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layo = findViewById(R.id.layin);

        TextView titulo = new TextView(this);
        titulo.setText("AEROMEXICO");
        titulo.setGravity(1);
        layo.addView(titulo);

        String[] listaSpinner = new String[]{"Guadalajara", "Tepic", "Monterrey", "CDMX", "Querétaro"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listaSpinner);

        LinearLayout llDestino = new LinearLayout(this);
        TextView tvDestino = new TextView(this);
        tvDestino.setText("Destino: ");
        final Spinner spDestino = new Spinner(this);
        spDestino.setAdapter(adaptador);
        llDestino.addView(tvDestino);
        llDestino.addView(spDestino);
        llDestino.setGravity(1);
        layo.addView(llDestino);

        LinearLayout llOrigen = new LinearLayout(this);
        TextView tvOrigen = new TextView(this);
        tvOrigen.setText("Origen: ");
        final Spinner spOrigen = new Spinner(this);
        spOrigen.setAdapter(adaptador);
        llOrigen.addView(tvOrigen);
        llOrigen.addView(spOrigen);
        llOrigen.setGravity(1);
        layo.addView(llOrigen);

        anioActual = calendario.get(Calendar.YEAR);
        mesActual = calendario.get(Calendar.MONTH);
        diaActual = calendario.get(Calendar.DAY_OF_MONTH);

        LinearLayout llFecha = new LinearLayout(this);
        TextView tvFecha = new TextView(this);
        tvFecha.setText("Fecha: ");
        edFecha = new EditText(this);
        edFecha.setEms(8);
        edFecha.setInputType(InputType.TYPE_NULL);
        llFecha.addView(tvFecha);
        llFecha.addView(edFecha);
        llFecha.setGravity(1);
        layo.addView(llFecha);
        edFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });

        TextView tvPaquete = new TextView(this);
        tvPaquete.setText("Elija un paquete");
        layo.addView(tvPaquete);

        rbVuelo= new RadioButton(this);
        rbVuelo.setText("Sólo vuelo");
        rbVueloHotel = new RadioButton(this);
        rbVueloHotel.setText("Viaje con reserva de hotel");
        rbVueloTransporte = new RadioButton(this);
        rbVueloTransporte.setText("Viaje con transportación");
        final RadioGroup vuelos = new RadioGroup(this);
        vuelos.addView(rbVuelo);
        vuelos.addView(rbVueloHotel);
        vuelos.addView(rbVueloTransporte);
        vuelos.setGravity(1);
        layo.addView(vuelos);

        LinearLayout llCotizar = new LinearLayout(this);
        Button cotizar = new Button(this);
        cotizar.setText("Cotizar");
        cotizar.setEms(8);
        cotizar.setTextColor(Color.parseColor("#FFFFFF"));
        cotizar.setBackgroundColor(getResources().getColor(R.color.fondoBoton));
        llCotizar.addView(cotizar);
        llCotizar.setGravity(1);
        layo.addView(llCotizar);

        final TextView cotizacion = new TextView(this);
        cotizacion.setText("");
        cotizacion.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        layo.addView(cotizacion);

        LinearLayout llComprar = new LinearLayout(this);
        Button comprar = new Button(this);
        comprar.setText("Comprar");
        comprar.setEms(10);
        comprar.setTextColor(Color.parseColor("#FFFFFF"));
        comprar.setBackgroundColor(getResources().getColor(R.color.fondoBoton));
        llComprar.addView(comprar);
        llComprar.setGravity(1);
        layo.addView(llComprar);

        cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbVuelo.isChecked()){
                    cotizacion.setText("El precio es de: $2000.00");
                }
                else if (rbVueloHotel.isChecked()){
                    cotizacion.setText("El precio es de: $7000.00\n$2000.00 del vuelo + $5000.00 de la reserva del hotel");
                }
                else if (rbVueloTransporte.isChecked()){
                    cotizacion.setText("El precio es de: $3500.00\n$2000.00 del vuelo + $1500.00 del transporte");
                }
                else {
                    Toast.makeText( MainActivity.this,"Selecciona una opcion para poder cotizar",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edFecha.length()>0) {
                    if (rbVuelo.isChecked() || rbVueloHotel.isChecked() || rbVueloTransporte.isChecked()) {
                        if (spDestino.getSelectedItem()!=spOrigen.getSelectedItem()) {
                            if (anio >= anioActual) {
                                if (mes >= mesActual) {
                                    if (dia > diaActual) {
                                        Toast.makeText(MainActivity.this, "El voleto ha sido comprado", Toast.LENGTH_SHORT).show();
                                    }
                                    else if (dia==diaActual){
                                        Toast.makeText(MainActivity.this, "No puedes viajar el mismo día en que compras tu boleto", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "No puedes comprar un voleto para una fecha anterior a la actual", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "No puedes comprar un voleto para una fecha anterior a la actual", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "No puedes comprar un voleto para una fecha anterior a la actual", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "No puedes viajar a la misma ciudad", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Llena todos los campos",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Llena todos los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    anio = year;
                    mes = monthOfYear;
                    dia = dayOfMonth;
                    edFecha.setText(anio + "/" + (mes+1) + "/" + dia);
                }
            };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, anioActual, mesActual, diaActual);
        }
        return null;
    }
}

