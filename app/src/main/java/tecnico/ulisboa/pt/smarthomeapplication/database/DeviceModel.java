package tecnico.ulisboa.pt.smarthomeapplication.database;

import java.io.Serializable;

import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_BRIGHTNESS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_COLOR_SETTING;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_ENERGY_CONSUMED;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_MAX_BRIGHTNESS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_MAX_TEMPERATURE_KELVIN;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_MIN_BRIGHTNESS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_MIN_TEMPERATURE_KELVIN;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_LIGHT_TEMPERATURE_KELVIN;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_POWERPLUG_ENERGY_CONSUMED;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_SENSOR_ENERGY_CONSUMED;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_SENSOR_HUMIDITY_PERCENT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_SENSOR_MAX_HUMIDITY_PERCENT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_SENSOR_MAX_TEMPERATURE_CELSIUS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_SENSOR_MIN_HUMIDITY_PERCENT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_SENSOR_MIN_TEMPERATURE_CELSIUS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.DevicesConstants.DEFAULT_SENSOR_TEMPERATURE_CELSIUS;

public class DeviceModel implements Serializable {
    private int DeviceId;
    private String DeviceName;
    private String DeviceType;
    private boolean DeviceStatus;
    private LigthsDeviceModel ligthsDevice = null;
    private PowerPlugModel powerPlugModel = null;
    private SensorModel sensorModel = null;

    // for registration
    public DeviceModel(String deviceName, String deviceType) {
        DeviceName = deviceName;
        DeviceType = deviceType;
        DeviceStatus = true; //turn ON devices by default

        switch (deviceType) {
            case "Light":
                ligthsDevice = new LigthsDeviceModel(DEFAULT_LIGHT_BRIGHTNESS, DEFAULT_LIGHT_MIN_BRIGHTNESS, DEFAULT_LIGHT_MAX_BRIGHTNESS, DEFAULT_LIGHT_COLOR_SETTING, DEFAULT_LIGHT_TEMPERATURE_KELVIN, DEFAULT_LIGHT_MIN_TEMPERATURE_KELVIN, DEFAULT_LIGHT_MAX_TEMPERATURE_KELVIN, DEFAULT_LIGHT_ENERGY_CONSUMED);
                break;
            case "PowerPlug":
                powerPlugModel = new PowerPlugModel(DEFAULT_POWERPLUG_ENERGY_CONSUMED);
                break;
            case "Sensor":
                sensorModel = new SensorModel(DEFAULT_SENSOR_HUMIDITY_PERCENT, DEFAULT_SENSOR_MIN_HUMIDITY_PERCENT, DEFAULT_SENSOR_MAX_HUMIDITY_PERCENT, DEFAULT_SENSOR_TEMPERATURE_CELSIUS, DEFAULT_SENSOR_MIN_TEMPERATURE_CELSIUS, DEFAULT_SENSOR_MAX_TEMPERATURE_CELSIUS, DEFAULT_SENSOR_ENERGY_CONSUMED);
                break;
            default:
                // when none of the cases is true.
        }
    }

    //for access to database
    public DeviceModel(int deviceID, String deviceName, String deviceType, boolean deviceStatus) {
        DeviceId = deviceID;
        DeviceName = deviceName;
        DeviceType = deviceType;
        DeviceStatus = deviceStatus;
    }

    public DeviceModel() {
    }

    public LigthsDeviceModel getLigthsDevice() {
        return ligthsDevice;
    }

    public void setLigthsDevice(LigthsDeviceModel ligthsDevice) {
        this.ligthsDevice = ligthsDevice;
    }

    public PowerPlugModel getPowerPlugModel() {
        return powerPlugModel;
    }

    public void setPowerPlugModel(PowerPlugModel powerPlugModel) {
        this.powerPlugModel = powerPlugModel;
    }

    public SensorModel getSensorModel() {
        return sensorModel;
    }

    public void setSensorModel(SensorModel sensorModel) {
        this.sensorModel = sensorModel;
    }

    public int getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(int deviceId) {
        DeviceId = deviceId;
    }

    public boolean getDeviceStatus() {
        return DeviceStatus;
    }

    public void setDeviceStatus(boolean status) {
        DeviceStatus = status;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "DeviceName='" + DeviceName + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", DeviceStatus=" + DeviceStatus +
                '}';
    }
}
