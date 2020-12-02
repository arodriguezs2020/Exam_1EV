package jesuitas.dam.exam1ev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;

    public static TextView nombre;
    public static TextView email;
    public static TextView telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = findViewById(R.id.nombre);
        email = findViewById(R.id.email);
        telefono = findViewById(R.id.telefono);
    }

    public void displayToast(String text) {
        Toast.makeText(getApplicationContext(), text,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_siguiente:
                Intent intent = new Intent(this, SegundaPagina.class);
                intent.putExtra(getString(R.string.nombre), nombre.getText().toString());
                intent.putExtra(getString(R.string.email), email.getText().toString());
                intent.putExtra(getString(R.string.telefono), telefono.getText().toString());

                startActivityForResult(intent, TEXT_REQUEST);

                return true;
            case R.id.action_email:

                String[] correo = {email.getText().toString()};
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse(getString(R.string.mailto)));
                sendIntent.putExtra(Intent.EXTRA_EMAIL, correo);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.aviso));

                if(nombre.getText().toString().isEmpty() || email.getText().toString().isEmpty() || telefono.getText().toString().isEmpty()){
                    displayToast(getString(R.string.no_se_ha_encontrado_el_email));
                } else{
                    startActivity(sendIntent);
                }
                return true;
             case R.id.action_telefono:
                 if(nombre.getText().toString().isEmpty() || email.getText().toString().isEmpty() || telefono.getText().toString().isEmpty()){
                     displayToast(getString(R.string.no_se_ha_encontrado_el_numero_de_telefono));
                 } else{
                     Intent telIntent = new Intent();
                     telIntent.setAction(Intent.ACTION_DIAL);
                     telIntent.setData(Uri.parse(getString(R.string.tel)+ telefono.getText().toString()));
                     startActivity(telIntent);

                 }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {

                String nuevoNombre = data.getStringExtra(getString(R.string.nuevoNombre));
                String nuevoEmail = data.getStringExtra(getString(R.string.nuevoEmail));
                String nuevoTelefono = data.getStringExtra(getString(R.string.nuevoTelefono));

                nombre.setText(nuevoNombre);
                email.setText(nuevoEmail);
                telefono.setText(nuevoTelefono);
            }
        }
    }

}