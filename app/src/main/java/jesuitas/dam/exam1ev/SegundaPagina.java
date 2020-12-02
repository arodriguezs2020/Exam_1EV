package jesuitas.dam.exam1ev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaPagina extends AppCompatActivity {

    public static EditText nuevoNombre;
    public static EditText nuevoEmail;
    public static EditText nuevoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pagina);

        nuevoNombre = findViewById(R.id.nombreNuevo);
        nuevoEmail = findViewById(R.id.emailNuevo);
        nuevoTelefono = findViewById(R.id.telefonoNuevo);
    }

    public void volver(View view) {

        if(nuevoNombre.getText().toString().isEmpty() || nuevoEmail.getText().toString().isEmpty() || nuevoTelefono.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.fill_all_data,
                    Toast.LENGTH_SHORT).show();
        } else{
            AlertDialog.Builder myAlertBuilder = new
                    AlertDialog.Builder(SegundaPagina.this);

            myAlertBuilder.setTitle(R.string.confirm);
            myAlertBuilder.setMessage(R.string.are_you_sure);

            myAlertBuilder.setPositiveButton(R.string.yes, new
                    DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked OK button.
                            Intent intent = new Intent();
                            intent.putExtra(getString(R.string.nuevoNombre), nuevoNombre.getText().toString());
                            intent.putExtra(getString(R.string.nuevoEmail), nuevoEmail.getText().toString());
                            intent.putExtra(getString(R.string.nuevoTelefono), nuevoTelefono.getText().toString());
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
            myAlertBuilder.setNegativeButton(R.string.no, new
                    DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User cancelled the dialog.
                            Toast.makeText(getApplicationContext(), R.string.correct_data,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

            myAlertBuilder.show();
        }




    }


}