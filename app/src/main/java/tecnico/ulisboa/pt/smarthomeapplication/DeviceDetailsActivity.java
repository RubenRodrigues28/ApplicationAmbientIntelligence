package tecnico.ulisboa.pt.smarthomeapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import tecnico.ulisboa.pt.smarthomeapplication.database.DatabaseHelper;
import tecnico.ulisboa.pt.smarthomeapplication.database.DeviceModel;
import yuku.ambilwarna.AmbilWarnaDialog;

public class DeviceDetailsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private DeviceModel clickedDevice;
    private Switch aSwitch;
    private TextView txt_deviceName;
    private TextView txt_energyConsumed;
    private TextView txt_temperature;
    private TextView txt_brightness;
    private TextView txt_humidity;
    private SeekBar seekBar_temperature;
    private SeekBar seekBar_2;
    private TextView txt_seekBar_Temperature;
    private TextView txt_seekBar2;

    private int mDefaultColor;
    private Button btn_ColorSetting;
    private ImageView img_Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);
        databaseHelper = new DatabaseHelper(DeviceDetailsActivity.this);
        clickedDevice = (DeviceModel) getIntent().getSerializableExtra("clickedDevice");

        aSwitch = findViewById(R.id.switch1);
        txt_deviceName = findViewById(R.id.txt_DeviceName);
        txt_energyConsumed = findViewById(R.id.txt_EnergyConsumed);
        txt_temperature = findViewById(R.id.txt_Temperature);
        txt_brightness = findViewById(R.id.txt_Brightness);
        txt_humidity = findViewById(R.id.txt_Humidity);
        seekBar_temperature = findViewById(R.id.seekBar_Temperature);
        seekBar_2 = findViewById(R.id.seekBar2);
        txt_seekBar_Temperature = findViewById(R.id.txt_seekBar_Temperature);
        txt_seekBar2 = findViewById(R.id.txt_seekBar2);
        btn_ColorSetting = findViewById(R.id.btn_ColorSetting);
        img_Delete = findViewById(R.id.img_Delete);

        ShowDeviceDetails();
        setClickListeners();
    }

    public void ShowDeviceDetails() {
        txt_deviceName.setText(clickedDevice.getDeviceName());

        boolean currentStatusDevice = databaseHelper.getStatusDevice(clickedDevice);
        aSwitch.setChecked(currentStatusDevice);

        switch (clickedDevice.getDeviceType()) {
            case "Light":
                txt_energyConsumed.setText(databaseHelper.getEnergyConsumed(clickedDevice) + " watts");

                seekBar_temperature.setMax(databaseHelper.getMaxTemperature(clickedDevice));
                seekBar_temperature.setProgress(databaseHelper.getTemperature(clickedDevice));
                txt_seekBar_Temperature.setText(databaseHelper.getTemperature(clickedDevice) + "/" + seekBar_temperature.getMax());

                seekBar_2.setMax(databaseHelper.getMaxBrightness(clickedDevice));
                seekBar_2.setProgress(databaseHelper.getBrightness(clickedDevice));
                txt_seekBar2.setText(databaseHelper.getBrightness(clickedDevice) + "%/" + seekBar_2.getMax() + "%");

                mDefaultColor = databaseHelper.getColorSetting(clickedDevice);
                txt_humidity.setVisibility(View.INVISIBLE);
                break;
            case "PowerPlug":
                Toast.makeText(getApplicationContext(), String.valueOf(databaseHelper.getEnergyConsumed(clickedDevice)), Toast.LENGTH_LONG).show();
                txt_energyConsumed.setText(databaseHelper.getEnergyConsumed(clickedDevice) + " watts");
                txt_temperature.setVisibility(View.INVISIBLE);
                seekBar_temperature.setVisibility(View.INVISIBLE);
                txt_brightness.setVisibility(View.INVISIBLE);
                txt_humidity.setVisibility(View.INVISIBLE);
                seekBar_2.setVisibility(View.INVISIBLE);
                txt_seekBar_Temperature.setVisibility(View.INVISIBLE);
                txt_seekBar2.setVisibility(View.INVISIBLE);
                btn_ColorSetting.setVisibility(View.INVISIBLE);
                break;
            case "Sensor":
                txt_energyConsumed.setText(databaseHelper.getEnergyConsumed(clickedDevice) + " watts");

                seekBar_temperature.setMax(databaseHelper.getMaxTemperature(clickedDevice));
                seekBar_temperature.setProgress(databaseHelper.getTemperature(clickedDevice));
                txt_seekBar_Temperature.setText(databaseHelper.getTemperature(clickedDevice) + "/" + seekBar_temperature.getMax());

                seekBar_2.setMax(databaseHelper.getMaxHumidity(clickedDevice));
                seekBar_2.setProgress(databaseHelper.getHumidity(clickedDevice));
                txt_seekBar2.setText(databaseHelper.getHumidity(clickedDevice) + "%/" + seekBar_2.getMax() + "%");

                txt_brightness.setVisibility(View.INVISIBLE);
                btn_ColorSetting.setVisibility(View.INVISIBLE);
                break;
            default:
                // when none of the cases is true.
        }
    }

    public void setClickListeners() {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                clickedDevice.setDeviceStatus(isChecked);
                boolean result = databaseHelper.updateStatusDevice(clickedDevice, isChecked);

                if (result) {
                    Toast.makeText(getApplicationContext(), "Update was successful.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred during update.", Toast.LENGTH_LONG).show();
                }
            }
        });

        seekBar_temperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt_seekBar_Temperature.setText(seekBar.getProgress() + "/" + seekBar.getMax());
                boolean result = databaseHelper.updateTemperatureDevice(clickedDevice, seekBar.getProgress());

                if (result) {
                    Toast.makeText(getApplicationContext(), "Update was successful.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred during update.", Toast.LENGTH_LONG).show();
                }
            }
        });

        seekBar_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt_seekBar2.setText(seekBar.getProgress() + "%/" + seekBar.getMax() + "%");

                boolean result = false;
                switch (clickedDevice.getDeviceType()) {
                    case "Light":
                        result = databaseHelper.updateBrightnessDevice(clickedDevice, seekBar.getProgress());
                        break;
                    case "Sensor":
                        result = databaseHelper.updateHumidityDevice(clickedDevice, seekBar.getProgress());
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Some error occurred during update.", Toast.LENGTH_LONG).show();
                }

                if (result) {
                    Toast.makeText(getApplicationContext(), "Update was successful.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred during update.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_ColorSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        img_Delete.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.delete_device_title))
                .setMessage(getResources().getString(R.string.delete_device_question))
                .setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert))
                .setPositiveButton(getResources().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (databaseHelper.deleteDevice(clickedDevice)) {
                                    Toast.makeText(getApplicationContext(), "Device was deleted sucessfully.", Toast.LENGTH_LONG).show();
                                    openShowDevicesActivity();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Some error occurred during deletion.", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                .setNegativeButton(
                        getResources().getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Deleting device was cancelled.", Toast.LENGTH_LONG).show();
                            }
                        }).show();
    }

    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(DeviceDetailsActivity.this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(getApplicationContext(), "Changing color was cancelled.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                boolean result = databaseHelper.updateColorSettingDevice(clickedDevice, color);

                if (result) {
                    Toast.makeText(getApplicationContext(), "Update was successful.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred during update.", Toast.LENGTH_LONG).show();
                }
            }
        });
        colorPicker.show();
    }

    public void openShowDevicesActivity() {
        finish();
        Intent intent = new Intent(this, MainConsoleActivity.class);
        startActivity(intent);
    }
}
