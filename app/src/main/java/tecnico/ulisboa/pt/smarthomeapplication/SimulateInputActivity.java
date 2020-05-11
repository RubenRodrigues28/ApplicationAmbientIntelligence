package tecnico.ulisboa.pt.smarthomeapplication;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import tecnico.ulisboa.pt.smarthomeapplication.database.DatabaseHelper;

public class SimulateInputActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView txt_resultInput1;
    private TextView txt_resultInput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_input);
        txt_resultInput1 = findViewById(R.id.txt_resultInput1);
        txt_resultInput2 = findViewById(R.id.txt_resultInput2);
        databaseHelper = new DatabaseHelper(SimulateInputActivity.this);
        readFile(R.raw.inputsensorupdate);
//        readFile(R.raw.inputsensorupdateerror);
    }

    public void readFile(int resource) {
        try {
            InputStream file = this.getResources().openRawResource(resource);
            InputStreamReader input = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(input);
            String response = new String();
            for (String line; (line = reader.readLine()) != null; response += line);
            JSONObject jsonObject = new JSONObject(response);

            if ("update".equals((String) jsonObject.get("action"))){
                String result = databaseHelper.updateDeviceByInput(jsonObject);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                txt_resultInput1.setText(result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
