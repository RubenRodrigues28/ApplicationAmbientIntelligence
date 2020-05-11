package tecnico.ulisboa.pt.smarthomeapplication.database;

import java.io.Serializable;

public class LigthsDeviceModel implements Serializable {
    private int DeviceID;
    private int Brightness;
    private int MinBrightness;
    private int MaxBrightness;
    private int ColorSetting;
    private int Temperature;
    private int MinTemperature;
    private int MaxTemperature;
    private int EnergyConsumed;

    //for registration
    public LigthsDeviceModel(int brightness, int minBrightness, int maxBrightness, int colorSetting, int temperature, int minTemperature, int maxTemperature, int energyConsumed) {
        Brightness = brightness;
        MinBrightness = minBrightness;
        MaxBrightness = maxBrightness;
        ColorSetting = colorSetting;
        Temperature = temperature;
        MinTemperature = minTemperature;
        MaxTemperature = maxTemperature;
        EnergyConsumed = energyConsumed;
    }

    //for access to database
    public LigthsDeviceModel(int deviceID, int brightness, int minBrightness, int maxBrightness, int colorSetting, int temperature, int minTemperature, int maxTemperature, int energyConsumed) {
        DeviceID = deviceID;
        Brightness = brightness;
        MinBrightness = minBrightness;
        MaxBrightness = maxBrightness;
        ColorSetting = colorSetting;
        Temperature = temperature;
        MinTemperature = minTemperature;
        MaxTemperature = maxTemperature;
        EnergyConsumed = energyConsumed;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    public int getColorSetting() {
        return ColorSetting;
    }

    public void setColorSetting(int colorSetting) {
        ColorSetting = colorSetting;
    }

    public int getBrightness() {
        return Brightness;
    }

    public void setBrightness(int brightness) {
        Brightness = brightness;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public int getEnergyConsumed() {
        return EnergyConsumed;
    }

    public void setEnergyConsumed(int energyConsumed) {
        EnergyConsumed = energyConsumed;
    }

    public int getMinBrightness() {
        return MinBrightness;
    }

    public void setMinBrightness(int minBrightness) {
        MinBrightness = minBrightness;
    }

    public int getMaxBrightness() {
        return MaxBrightness;
    }

    public void setMaxBrightness(int maxBrightness) {
        MaxBrightness = maxBrightness;
    }

    public int getMinTemperature() {
        return MinTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        MinTemperature = minTemperature;
    }

    public int getMaxTemperature() {
        return MaxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        MaxTemperature = maxTemperature;
    }

    @Override
    public String toString() {
        return "LigthsDeviceModel{" +
                "DeviceID=" + DeviceID +
                ", Brightness=" + Brightness +
                ", MinBrightness=" + MinBrightness +
                ", MaxBrightness=" + MaxBrightness +
                ", ColorSetting=" + ColorSetting +
                ", Temperature=" + Temperature +
                ", MinTemperature=" + MinTemperature +
                ", MaxTemperature=" + MaxTemperature +
                ", EnergyConsumed=" + EnergyConsumed +
                '}';
    }
}
