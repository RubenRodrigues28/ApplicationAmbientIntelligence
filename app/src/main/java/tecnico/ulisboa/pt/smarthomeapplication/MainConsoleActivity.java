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
        try {
            InputStream file = this.getResources().openRawResource(R.raw.inputsensor);
            InputStreamReader input = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(input);

            StringBuffer string = new StringBuffer();
            String lines = "";



            List<DeviceModel> devices = databaseHelper.getDevices();

            int maxTemp = -11;
            int devideId = 0;
            DeviceModel deviceM = null;
            while ((lines = reader.readLine()) != null) {
                string.append(lines + "\n");

                if(lines.contains("deviceId")){
                    String[] sensorId = lines.split("deviceId:");
                    devideId = Integer.parseInt(sensorId[1]);
                    }

                if(lines.contains("deviceType")) {
                    String[] deviceType = lines.split("deviceType:");
                    switch (deviceType[1]) {
                        case "Light":
                            while ((lines = reader.readLine()) != null) {
                                if (lines.contains("deviceStatus:")) {
                                    String[] sensorStatus = lines.split("deviceStatus:");
                                    if (sensorStatus[1].contains("Off")) {
                                        deviceM.setDeviceStatus(false);
                                        databaseHelper.updateStatusDevice(deviceM);
                                    } else {
                                        deviceM.setDeviceStatus(true);
                                        databaseHelper.updateStatusDevice(deviceM);
                                    }
                                }
                            }
                            break;
                        case "PowerPlug":
                            while ((lines = reader.readLine()) != null) {
                                for (DeviceModel device : devices) {
                                    if (device.getDeviceId() == devideId) {
                                        maxTemp = databaseHelper.getMaxTemperature(device);
                                        deviceM = device;
                                        break;
                                    }
                                }
                                if (lines.contains("deviceStatus:")) {
                                    String[] sensorStatus = lines.split("deviceStatus:");

                                    if (sensorStatus[1].contains("Off")) {
                                        deviceM.setDeviceStatus(false);
                                        databaseHelper.updateStatusDevice(deviceM);
                                    } else {
                                        deviceM.setDeviceStatus(true);
                                        databaseHelper.updateStatusDevice(deviceM);
                                    }
                                }
                                if (lines.contains("power")) {
                                    String[] sensorPower = lines.split("power:");
                                    deviceM.getPowerPlugModel().setEnergyConsumed(Integer.parseInt(sensorPower[1]));
                                    databaseHelper.updatePowerDevice(deviceM, Integer.parseInt(sensorPower[1]));
                                }
                            }
                            break;
                        case "Sensor":
                            while ((lines = reader.readLine()) != null) {

                                for (DeviceModel device : devices) {
                                    if (device.getDeviceId() == devideId) {
                                        maxTemp = databaseHelper.getMaxTemperature(device);
                                        deviceM = device;
                                        break;
                                    }
                                }
                                if (lines.contains("maxTemperature:")) {
                                    String[] sensorMaxTemp = lines.split("maxTemperature:");
                                    if (Integer.parseInt(sensorMaxTemp[1]) > maxTemp) {
                                        Toast.makeText(getApplicationContext(), "MAX TEMPERATURE REACHED", Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                }
                                if (lines.contains("deviceStatus:")) {
                                    String[] sensorStatus = lines.split("deviceStatus:");
                                    if (sensorStatus[1].contains("Off")) {
                                        deviceM.setDeviceStatus(false);
                                        databaseHelper.updateStatusDevice(deviceM);
                                    } else {
                                        deviceM.setDeviceStatus(true);
                                        databaseHelper.updateStatusDevice(deviceM);
                                    }
                                }
                            }
                            break;
                        default:
                            // when none of the cases is true.
                    }
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
