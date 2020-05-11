package tecnico.ulisboa.pt.smarthomeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tecnico.ulisboa.pt.smarthomeapplication.database.DatabaseHelper;

public class MainConsoleActivity extends AppCompatActivity {

    //UI REFERENCES
    private Button btn_addNewDevice;
    private Button btn_manageDevices;
    private Button btn_simulateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_console);

        btn_addNewDevice = findViewById(R.id.btn_AddDevice);
        btn_manageDevices = findViewById(R.id.btn_ManageDevices);
        btn_simulateInput = findViewById(R.id.btn_SimulateInput);

        setClickListeners();
    }

    private void setClickListeners() {
        btn_addNewDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewDeviceActivity();
            }
        });

        btn_manageDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShowDevicesActivity();
            }
        });

        btn_simulateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSimulateInputActivity();
            }
        });
    }

    public void openShowDevicesActivity() {
        Intent intent = new Intent(this, ShowDevicesActivity.class);
        startActivity(intent);
    }

    public void openAddNewDeviceActivity() {
        Intent intent = new Intent(this, AddNewDeviceActivity.class);
        startActivity(intent);
    }

    public void openSimulateInputActivity() {
        Intent intent = new Intent(this, SimulateInputActivity.class);
        startActivity(intent);
    }

}
