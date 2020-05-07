package tecnico.ulisboa.pt.smarthomeapplication;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import tecnico.ulisboa.pt.smarthomeapplication.database.DatabaseHelper;
import tecnico.ulisboa.pt.smarthomeapplication.database.DeviceModel;

public class MainConsoleActivity extends AppCompatActivity {

    //UI REFERENCES
    private Button btn_addNewDevice;
    private Button btn_manageDevices;
    private Button btn_readSensors;
    private TextView textOuput;
    private ListView lst_devices;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_console);

        btn_addNewDevice = findViewById(R.id.btnAddDevice);
        btn_manageDevices = findViewById(R.id.btnManageDevices);
        btn_readSensors = findViewById(R.id.btnReadSensors);
        textOuput = findViewById(R.id.textOuput);

        lst_devices = findViewById(R.id.lst_devices);
        databaseHelper = new DatabaseHelper(MainConsoleActivity.this);

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

        btn_readSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
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

    public void readFile() {
        List<DeviceModel> devices = databaseHelper.getDevices();
        for (DeviceModel device : devices) {
            Toast.makeText(getApplicationContext(), device.getDeviceId(), Toast.LENGTH_LONG).show();
        }

        ActionBar.Tab displayText = null;
        try {
            InputStream file = this.getResources().openRawResource(R.raw.inputsensor);
            InputStreamReader input = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(input);

            StringBuffer string = new StringBuffer();
            String lines = "";
            while ((lines = reader.readLine()) != null) {
                string.append(lines + "\n");
                if(lines.contains("temperature:")){
                    String[] linha = lines.split("temperature:");
                    //tempValue = Integer.parseInt(linha[1]);
                    //if (tempValue < 0)
                        //Toast.makeText(getApplicationContext(), linha[1], Toast.LENGTH_LONG).show();
                }
            }
            textOuput.setText(string);
            reader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
