package tecnico.ulisboa.pt.smarthomeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainConsoleActivity extends AppCompatActivity {

    //UI REFERENCES
    private Button btn_addNewDevice;
    private Button btn_manageDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_console);

        btn_addNewDevice = findViewById(R.id.btnAddDevice);
        btn_manageDevices = findViewById(R.id.btnManageDevices);
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
    }

    public void openShowDevicesActivity() {
        Intent intent = new Intent(this, ShowDevicesActivity.class);
        startActivity(intent);
    }

    public void openAddNewDeviceActivity() {
        Intent intent = new Intent(this, AddNewDeviceActivity.class);
        startActivity(intent);
    }
}
