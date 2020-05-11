package tecnico.ulisboa.pt.smarthomeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tecnico.ulisboa.pt.smarthomeapplication.adapters.CustomArrayAdapter;
import tecnico.ulisboa.pt.smarthomeapplication.database.DatabaseHelper;
import tecnico.ulisboa.pt.smarthomeapplication.database.DeviceModel;

public class ShowDevicesActivity extends AppCompatActivity {

    private ListView lst_devices;
    private DatabaseHelper databaseHelper;

    private ArrayList<String> mTitle = new ArrayList<String>();
    private ArrayList<String> mDescription = new ArrayList<String>();
    private ArrayList<Integer> images = new ArrayList<Integer>();

    public static String[] convertStringsArray(ArrayList<String> arr) {
        // declaration and initialise String Array
        String[] str = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    public static int[] convertIntegersArray(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_devices);

        lst_devices = findViewById(R.id.lst_devices);
        databaseHelper = new DatabaseHelper(ShowDevicesActivity.this);
        ShowDevicesOnListView();
        setClickListeners();
    }

    private void ShowDevicesOnListView() {
        getCurrentValues();

        String[] titles = convertStringsArray(mTitle);
        String[] descriptions = convertStringsArray(mDescription);
        int[] imagesC = convertIntegersArray(images);

        CustomArrayAdapter adapter = new CustomArrayAdapter(ShowDevicesActivity.this, titles, descriptions, imagesC);
        lst_devices.setAdapter(adapter);
    }

    public void setClickListeners() {
        lst_devices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<DeviceModel> devices = databaseHelper.getDevices();
                DeviceModel clickedDevice = devices.get(position);
                openDevicesDetailsActivity(clickedDevice);
            }
        });
    }

    public void openDevicesDetailsActivity(DeviceModel clickedDevice) {
        Intent intent = new Intent(this, DeviceDetailsActivity.class);
        intent.putExtra("clickedDevice", clickedDevice);
        startActivity(intent);
    }

    public void getCurrentValues() {
        List<DeviceModel> devices = databaseHelper.getDevices();
        for (DeviceModel device : devices) {
            switch (device.getDeviceType()) {
                case "Light":
                    mTitle.add(device.getDeviceName());
                    mDescription.add(device.getDeviceType());
                    images.add(R.drawable.smartlight);
                    break;
                case "PowerPlug":
                    mTitle.add(device.getDeviceName());
                    mDescription.add(device.getDeviceType());
                    images.add(R.drawable.smartpowerplug);
                    break;
                case "Sensor":
                    mTitle.add(device.getDeviceName());
                    mDescription.add(device.getDeviceType());
                    images.add(R.drawable.smartsensor);
                    break;
                default:
                    // when none of the cases is true.
            }
        }
    }
}

