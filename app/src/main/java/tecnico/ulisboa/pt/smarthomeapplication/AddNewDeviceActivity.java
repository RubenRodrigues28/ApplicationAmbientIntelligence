package tecnico.ulisboa.pt.smarthomeapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import tecnico.ulisboa.pt.smarthomeapplication.database.DatabaseHelper;
import tecnico.ulisboa.pt.smarthomeapplication.database.DeviceModel;

public class AddNewDeviceActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button btnSubmit;
    private EditText deviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_device);

        initializeSpinner();
        addListenerOnButton();
    }

    public void initializeSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setSelection(0);
        spinner.setPrompt("Choose the device type:");
    }

    public void addListenerOnButton() {
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDeviceName()) {
                    JSONObject deviceInfo = getDeviceInformation();

                    DeviceModel newDevice;
                    DatabaseHelper databaseHelper = new DatabaseHelper(AddNewDeviceActivity.this);
                    try {
                        newDevice = new DeviceModel((String) deviceInfo.get("deviceName"), (String) deviceInfo.get("deviceType"));

                        if (databaseHelper.addDevice(newDevice)) {
                            Toast.makeText(getApplicationContext(), "Your device was registered successfully.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Some error occurred during registration on database.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Exception occurred: " + e.toString(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean checkDeviceName() {
        deviceName = findViewById(R.id.inputDeviceName);
        String result = deviceName.getText().toString();
        if (result.matches("")) {
            Toast.makeText(this, "You must enter the device name.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public JSONObject getDeviceInformation() {
        deviceName = findViewById(R.id.inputDeviceName);
        spinner = findViewById(R.id.spinner);

        JSONObject obj = new JSONObject();
        try {
            obj.put("deviceName", deviceName.getText().toString());
            obj.put("deviceType", spinner.getSelectedItem().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return obj;
        }
    }

}
