package me.armandosalazar.dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutResponse;
    private int day, month, year, hour, minute;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflateViews();

        setEvents();
    }

    private void inflateViews() {
        calendar = Calendar.getInstance(TimeZone.getTimeZone("CDT"));
        // calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        textInputLayoutResponse = findViewById(R.id.textInputLayoutResponse);
    }

    private void setEvents() {
        findViewById(R.id.btnAlert).setOnClickListener(v -> showAlertDialog());
        findViewById(R.id.btnDate).setOnClickListener(v -> showDatePickerDialog());
        findViewById(R.id.btnTime).setOnClickListener(v -> showTimePickerDialog());
    }


    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensaje de advertencia");
        builder.setMessage("¿Borrar todo el contenido el contenido del archivo?");
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            textInputLayoutResponse.getEditText().setText("Se aceptó");
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            textInputLayoutResponse.getEditText().setText("Se canceló");
            dialog.dismiss();
        });
        builder.show();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            this.day = dayOfMonth;
            this.month = month + 1;
            this.year = year;
            textInputLayoutResponse.getEditText().setText(this.day + "/" + this.month + "/" + this.year);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            this.hour = hourOfDay;
            this.minute = minute;
            textInputLayoutResponse.getEditText().setText(this.hour + ":" + this.minute);
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }
}