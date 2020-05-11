package tecnico.ulisboa.pt.smarthomeapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_DEVICE_ID;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_DEVICE_NAME;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_DEVICE_STATUS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_DEVICE_TYPE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_BRIGHTNESS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_COLORSETTING;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_ENERGYCONSUMED;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_MAX_BRIGHTNESS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_MAX_TEMPERATURE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_MIN_BRIGHTNESS;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_MIN_TEMPERATURE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_LIGHT_TEMPERATURE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_POWERPLUG_ENERGYCONSUMED;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_SENSOR_ENERGYCONSUMED;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_SENSOR_HUMIDITY;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_SENSOR_MAX_HUMIDITY;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_SENSOR_MAX_TEMPERATURE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_SENSOR_MIN_HUMIDITY;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_SENSOR_MIN_TEMPERATURE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.COLUMN_SENSOR_TEMPERATURE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.DEVICES_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.LIGHTS_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.POWERPLUGS_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SENSORS_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_CREATE_DEVICES_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_CREATE_LIGHTS_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_CREATE_POWER_PLUGS_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_CREATE_SENSORS_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_DELETE_ENTRIES_DEVICES_TABLE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_LAST_INSERTED_ROWID;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_ALL_DEVICES;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_BRIGHTNESS_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_COLORSETTING_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_ENERGY_CONSUMED_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_ENERGY_CONSUMED_POWER_PLUG;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_ENERGY_CONSUMED_SENSOR;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_HUMIDITY_SENSOR;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MAX_BRIGHTNESS_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MAX_HUMIDITY_SENSOR;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MAX_TEMPERATURE_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MAX_TEMPERATURE_SENSOR;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MIN_BRIGHTNESS_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MIN_HUMIDITY_SENSOR;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MIN_TEMPERATURE_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_MIN_TEMPERATURE_SENSOR;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_STATUS_DEVICE;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_TEMPERATURE_LIGHT;
import static tecnico.ulisboa.pt.smarthomeapplication.database.SQLConstants.SQL_SELECT_TEMPERATURE_SENSOR;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "app.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // called the first time database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DEVICES_TABLE);
        db.execSQL(SQL_CREATE_LIGHTS_TABLE);
        db.execSQL(SQL_CREATE_SENSORS_TABLE);
        db.execSQL(SQL_CREATE_POWER_PLUGS_TABLE);
    }

    // called if database version number changes. Prevents app to crashes when is made changes to database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_DEVICES_TABLE);
        onCreate(db);
    }

    //INSERT DB
    public boolean addDevice(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DEVICE_NAME, deviceModel.getDeviceName());
        cv.put(COLUMN_DEVICE_TYPE, deviceModel.getDeviceType());
        cv.put(COLUMN_DEVICE_STATUS, deviceModel.getDeviceStatus());

        long insert = db.insert(DEVICES_TABLE, null, cv);

        if (insert == -1) {
            db.close();
            return false;
        } else {
            db.close();
            int id = getDeviceID();
            if (id != -1) {
                return addDeviceToSpecificTable(deviceModel, id);
            } else {
                return false;
            }
        }
    }

    public boolean addDeviceToSpecificTable(DeviceModel deviceModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        long insert = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                LigthsDeviceModel ligthsDeviceModel = deviceModel.getLigthsDevice();
                cv.put(COLUMN_DEVICE_ID, id);
                cv.put(COLUMN_LIGHT_BRIGHTNESS, ligthsDeviceModel.getBrightness());
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, ligthsDeviceModel.getMinBrightness());
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, ligthsDeviceModel.getMaxBrightness());
                cv.put(COLUMN_LIGHT_COLORSETTING, ligthsDeviceModel.getColorSetting());
                cv.put(COLUMN_LIGHT_TEMPERATURE, ligthsDeviceModel.getTemperature());
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, ligthsDeviceModel.getMinTemperature());
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, ligthsDeviceModel.getMaxTemperature());
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, ligthsDeviceModel.getEnergyConsumed());
                insert = db.insert(LIGHTS_TABLE, null, cv);
                break;
            case "PowerPlug":
                PowerPlugModel powerPlugModel = deviceModel.getPowerPlugModel();
                cv.put(COLUMN_DEVICE_ID, id);
                cv.put(COLUMN_POWERPLUG_ENERGYCONSUMED, powerPlugModel.getEnergyConsumed());
                insert = db.insert(POWERPLUGS_TABLE, null, cv);
                break;
            case "Sensor":
                SensorModel sensorModel = deviceModel.getSensorModel();
                cv.put(COLUMN_DEVICE_ID, id);
                cv.put(COLUMN_SENSOR_HUMIDITY, sensorModel.getHumidity());
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, sensorModel.getMinHumidity());
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, sensorModel.getMaxHumidity());
                cv.put(COLUMN_SENSOR_TEMPERATURE, sensorModel.getTemperature());
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, sensorModel.getMinTemperature());
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, sensorModel.getMaxTemperature());
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, sensorModel.getEnergyConsumed());
                insert = db.insert(SENSORS_TABLE, null, cv);
                break;
            default:
                return false;
        }

        if (insert == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    //RETRIEVE FROM DB
    public List<DeviceModel> getDevices() {
        List<DeviceModel> returnList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(SQL_SELECT_ALL_DEVICES, null);

        if (cursor.moveToFirst()) {
            do {
                int deviceID = cursor.getInt(0);
                String deviceName = cursor.getString(1);
                String deviceType = cursor.getString(2);
                boolean deviceStatus = cursor.getInt(3) == 1;

                DeviceModel deviceModel = new DeviceModel(deviceID, deviceName, deviceType, deviceStatus);
                setDevice(deviceModel);

                returnList.add(deviceModel);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public void setDevice(DeviceModel deviceModel) {
        switch (deviceModel.getDeviceType()) {
            case "Light":
                LigthsDeviceModel ligthsDeviceModel = new LigthsDeviceModel(deviceModel.getDeviceId(), getBrightness(deviceModel), getMinBrightness(deviceModel), getMaxBrightness(deviceModel), getColorSetting(deviceModel), getTemperature(deviceModel), getMinTemperature(deviceModel), getMaxTemperature(deviceModel), getEnergyConsumed(deviceModel));
                deviceModel.setLigthsDevice(ligthsDeviceModel);
                break;

            case "Sensor":
                SensorModel sensorModel = new SensorModel(deviceModel.getDeviceId(), getHumidity(deviceModel), getMinHumidity(deviceModel), getMaxHumidity(deviceModel), getTemperature(deviceModel), getMinTemperature(deviceModel), getMaxTemperature(deviceModel), getEnergyConsumed(deviceModel));
                deviceModel.setSensorModel(sensorModel);
                break;
            case "PowerPlug":
                PowerPlugModel powerPlugModel = new PowerPlugModel(deviceModel.getDeviceId(), getEnergyConsumed(deviceModel));
                deviceModel.setPowerPlugModel(powerPlugModel);
            default:
        }
    }

    public int getDeviceID() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SQL_LAST_INSERTED_ROWID, null);

        int result = -1;
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public boolean getStatusDevice(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = SQL_SELECT_STATUS_DEVICE + deviceModel.getDeviceId();

        Cursor cursor = db.rawQuery(queryString, null);

        boolean result = false;
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0) == 1;
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getEnergyConsumed(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Light":
                queryString = SQL_SELECT_ENERGY_CONSUMED_LIGHT + deviceModel.getDeviceId();
                break;
            case "PowerPlug":
                queryString = SQL_SELECT_ENERGY_CONSUMED_POWER_PLUG + deviceModel.getDeviceId();
                break;
            case "Sensor":
                queryString = SQL_SELECT_ENERGY_CONSUMED_SENSOR + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getTemperature(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Light":
                queryString = SQL_SELECT_TEMPERATURE_LIGHT + deviceModel.getDeviceId();
                break;
            case "Sensor":
                queryString = SQL_SELECT_TEMPERATURE_SENSOR + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getMaxTemperature(DeviceModel deviceModel){
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                queryString = SQL_SELECT_MAX_TEMPERATURE_SENSOR + deviceModel.getDeviceId();
                break;
            case "Light":
                queryString = SQL_SELECT_MAX_TEMPERATURE_LIGHT + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getMinTemperature(DeviceModel deviceModel){
            SQLiteDatabase db = this.getReadableDatabase();

            String queryString = "";
            switch (deviceModel.getDeviceType()) {
                case "Sensor":
                    queryString = SQL_SELECT_MIN_TEMPERATURE_SENSOR + deviceModel.getDeviceId();
                    break;
                case "Light":
                    queryString = SQL_SELECT_MIN_TEMPERATURE_LIGHT + deviceModel.getDeviceId();
                    break;
                default:
                    return -1;
            }

            int result = -1;
            Cursor cursor = db.rawQuery(queryString, null);
            if (cursor.moveToFirst()) {
                do {
                    result = cursor.getInt(0);
                } while (cursor.moveToNext());
            } else {
                //failure
            }

            cursor.close();
            db.close();
            return result;
        }

    public int getBrightness(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Light":
                queryString = SQL_SELECT_BRIGHTNESS_LIGHT + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getMinBrightness(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Light":
                queryString = SQL_SELECT_MIN_BRIGHTNESS_LIGHT + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getMaxBrightness(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Light":
                queryString = SQL_SELECT_MAX_BRIGHTNESS_LIGHT + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getColorSetting(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Light":
                queryString = SQL_SELECT_COLORSETTING_LIGHT + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getHumidity(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                queryString = SQL_SELECT_HUMIDITY_SENSOR + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getMinHumidity(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                queryString = SQL_SELECT_MIN_HUMIDITY_SENSOR + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    public int getMaxHumidity(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "";
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                queryString = SQL_SELECT_MAX_HUMIDITY_SENSOR + deviceModel.getDeviceId();
                break;
            default:
                return -1;
        }

        int result = -1;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(0);
            } while (cursor.moveToNext());
        } else {
            //failure
        }

        cursor.close();
        db.close();
        return result;
    }

    //UPDATE DB
    public boolean updateStatusDevice(DeviceModel deviceModel, boolean status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DEVICE_NAME, deviceModel.getDeviceName());
        cv.put(COLUMN_DEVICE_TYPE, deviceModel.getDeviceType());
        cv.put(COLUMN_DEVICE_STATUS, status);

        int update = db.update(DEVICES_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateTemperatureDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, getBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, getMinBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, getMaxBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_COLORSETTING, getColorSetting(deviceModel));
                cv.put(COLUMN_LIGHT_TEMPERATURE, newValue);
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            case "Sensor":
                cv.put(COLUMN_SENSOR_HUMIDITY, getHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, getMinHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, getMaxHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_TEMPERATURE, newValue);
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            case "Sensor":
                update = db.update(SENSORS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateMinTemperatureDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, getBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, getMinBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, getMaxBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_COLORSETTING, getColorSetting(deviceModel));
                cv.put(COLUMN_LIGHT_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, newValue);
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            case "Sensor":
                cv.put(COLUMN_SENSOR_HUMIDITY, getHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, getMinHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, getMaxHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, newValue);
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            case "Sensor":
                update = db.update(SENSORS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateMaxTemperatureDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, getBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, getMinBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, getMaxBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_COLORSETTING, getColorSetting(deviceModel));
                cv.put(COLUMN_LIGHT_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, newValue);
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            case "Sensor":
                cv.put(COLUMN_SENSOR_HUMIDITY, getHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, getMinHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, getMaxHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, newValue);
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            case "Sensor":
                update = db.update(SENSORS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateBrightnessDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, newValue);
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, getMinBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, getMaxBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_COLORSETTING, getColorSetting(deviceModel));
                cv.put(COLUMN_LIGHT_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateMinBrightnessDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, getBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, newValue);
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, getMaxBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_COLORSETTING, getColorSetting(deviceModel));
                cv.put(COLUMN_LIGHT_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateMaxBrightnessDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, getBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, getMinBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, newValue);
                cv.put(COLUMN_LIGHT_COLORSETTING, getColorSetting(deviceModel));
                cv.put(COLUMN_LIGHT_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateHumidityDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                cv.put(COLUMN_SENSOR_HUMIDITY, newValue);
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, getMinHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, getMaxHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                update = db.update(SENSORS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateMinHumidityDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                cv.put(COLUMN_SENSOR_HUMIDITY, getHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, newValue);
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, getMaxHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                update = db.update(SENSORS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateMaxHumidityDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                cv.put(COLUMN_SENSOR_HUMIDITY, getHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, getMinHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, newValue);
                cv.put(COLUMN_SENSOR_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Sensor":
                update = db.update(SENSORS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateColorSettingDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, getBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, getMinBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, getMaxBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_COLORSETTING, newValue);
                cv.put(COLUMN_LIGHT_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, getEnergyConsumed(deviceModel));
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public boolean updateEnergyConsumedDevice(DeviceModel deviceModel, int newValue) {
        ContentValues cv = new ContentValues();
        switch (deviceModel.getDeviceType()) {
            case "Light":
                cv.put(COLUMN_LIGHT_BRIGHTNESS, getBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_BRIGHTNESS, getMinBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_BRIGHTNESS, getMaxBrightness(deviceModel));
                cv.put(COLUMN_LIGHT_COLORSETTING, getColorSetting(deviceModel));
                cv.put(COLUMN_LIGHT_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_LIGHT_ENERGYCONSUMED, newValue);
                break;
            case "Sensor":
                cv.put(COLUMN_SENSOR_HUMIDITY, getHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_HUMIDITY, getMinHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_HUMIDITY, getMaxHumidity(deviceModel));
                cv.put(COLUMN_SENSOR_TEMPERATURE, getTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MIN_TEMPERATURE, getMinTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_MAX_TEMPERATURE, getMaxTemperature(deviceModel));
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, newValue);
                break;
            case "PowerPlug":
                cv.put(COLUMN_SENSOR_ENERGYCONSUMED, newValue);
                break;
            default:
                return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        int update = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                update = db.update(LIGHTS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            case "Sensor":
                update = db.update(SENSORS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            case "PowerPlug":
                update = db.update(POWERPLUGS_TABLE, cv, COLUMN_DEVICE_ID + " = " + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (update == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public String updateDeviceByInput(JSONObject jsonObject) {
        int count = 0;
        String errors = "";
        try {
            for (DeviceModel deviceModel : getDevices()) {
                if (deviceModel.getDeviceId() == (int) jsonObject.get("deviceId")) {

                    if (jsonObject.has("deviceStatus")){
                        updateStatusDevice(deviceModel,(Boolean) jsonObject.get("deviceStatus"));
                        count++;
                    }

                    if (jsonObject.has("maxHumidity")){
                        updateMaxHumidityDevice(deviceModel, (int)jsonObject.get("maxHumidity"));
                        count++;
                    }
                    if (jsonObject.has("minHumidity")){
                        updateMinHumidityDevice(deviceModel, (int)jsonObject.get("minHumidity"));
                        count++;
                    }
                    if (jsonObject.has("humidity")){
                        if (((int)jsonObject.get("humidity") <= getMaxHumidity(deviceModel)) && (((int)jsonObject.get("humidity") >= getMinHumidity(deviceModel)))){
                            updateHumidityDevice(deviceModel, (int)jsonObject.get("humidity"));
                            count++;
                        }
                        else {
                            errors += "ERROR:New value of humidity is outside of existing boundaries established.\n";
                        }
                    }

                    if (jsonObject.has("maxTemperature")){
                        updateMaxTemperatureDevice(deviceModel, (int)jsonObject.get("maxTemperature"));
                        count++;
                    }
                    if (jsonObject.has("minTemperature")){
                        updateMinTemperatureDevice(deviceModel, (int)jsonObject.get("minTemperature"));
                        count++;
                    }
                    if (jsonObject.has("temperature")){
                        if (((int)jsonObject.get("temperature") <= getMaxTemperature(deviceModel)) && (((int)jsonObject.get("temperature") >= getMinTemperature(deviceModel)))){
                            updateTemperatureDevice(deviceModel, (int)jsonObject.get("temperature"));
                            count++;
                        }
                        else {
                            errors += "ERROR:New value of temperature is outside of existing boundaries established.\n";
                        }
                    }

                    if (jsonObject.has("maxBrightness")){
                        updateMaxBrightnessDevice(deviceModel, (int)jsonObject.get("maxBrightness"));
                        count++;
                    }
                    if (jsonObject.has("minBrightness")){
                        updateMinBrightnessDevice(deviceModel, (int)jsonObject.get("minBrightness"));
                        count++;
                    }
                    if (jsonObject.has("brightness")){
                        if (((int)jsonObject.get("brightness") <= getMaxBrightness(deviceModel)) && (((int)jsonObject.get("brightness") >= getMinBrightness(deviceModel)))){
                            updateBrightnessDevice(deviceModel, (int)jsonObject.get("brightness"));
                            count++;
                        }
                        else {
                            errors += "ERROR:New value of brightness is outside of existing boundaries established.\n";
                        }
                    }

                    if (jsonObject.has("colorSetting")){
                        updateColorSettingDevice(deviceModel, (int)jsonObject.get("colorSetting"));
                        count++;
                    }
                    if (jsonObject.has("energyConsumed")){
                        updateEnergyConsumedDevice(deviceModel, (int)jsonObject.get("energyConsumed"));
                        count++;
                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            if (count == jsonObject.length()-3){
                return "Update was successful.";
            }
            else {
                return errors;
            }
        }
    }

    //DELETE DB
    public boolean deleteDevice(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        int delete = db.delete(DEVICES_TABLE, COLUMN_DEVICE_ID + "=" + deviceModel.getDeviceId(), null);
        if (delete == 1) {
            db.close();
            return deleteDevice_TableSpecific(deviceModel);
        } else {
            db.close();
            return false;
        }
    }

    public boolean deleteDevice_TableSpecific(DeviceModel deviceModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        int delete = -1;
        switch (deviceModel.getDeviceType()) {
            case "Light":
                delete = db.delete(LIGHTS_TABLE, COLUMN_DEVICE_ID + "=" + deviceModel.getDeviceId(), null);
                break;
            case "PowerPlug":
                delete = db.delete(POWERPLUGS_TABLE, COLUMN_DEVICE_ID + "=" + deviceModel.getDeviceId(), null);
                break;
            case "Sensor":
                delete = db.delete(SENSORS_TABLE, COLUMN_DEVICE_ID + "=" + deviceModel.getDeviceId(), null);
                break;
            default:
                return false;
        }

        if (delete == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }
}

