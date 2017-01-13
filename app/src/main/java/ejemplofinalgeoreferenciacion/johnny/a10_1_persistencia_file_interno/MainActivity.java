package ejemplofinalgeoreferenciacion.johnny.a10_1_persistencia_file_interno;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText txtMsj;
    String nombreArchivo = "notas.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsj = (EditText) findViewById(R.id.txtMsj);
        String[] archivos = fileList();
        if (existeArchivo(archivos, nombreArchivo)) {
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(nombreArchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txtMsj.setText(todo);
            } catch (IOException e) {
                Toast.makeText(this, "Error cargando el archivo",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean existeArchivo(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++) {
            if (archbusca.equals(archivos[f])) {
                return true;
            }
        }
        return false;
    }

    public void grabar(View view) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    nombreArchivo, Activity.MODE_PRIVATE));// Creamos un archivo
            // en la interna del
            // dispositivo
            archivo.write(txtMsj.getText().toString());// Almacenamos el texto
            // en el archivo
            archivo.flush();// Aceptamos la operacion (Pasar datos del buffer al
            // archivo)
            archivo.close();// Cerramos el archivo

            Toast.makeText(this, "Mensaje guardado satisfactoriamente",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error almacenando la informacion",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void salir(View view) {
        finish();// Cerramos la actividad
    }

}
