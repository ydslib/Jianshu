package com.yds.jianshu.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yds
 * on 2019/9/24.
 */
public class BluetoothUtil {

    /**
     * Weather the device support bluetooth
     * 判断是否支持蓝牙
     *
     * @return
     */
    public static boolean isSupportBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            return false;
        }
        return true;
    }

    /**
     * Weather the bluetooth is open
     * 判断蓝牙是否已打开
     *
     * @return
     */
    public static boolean isOpenBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (isSupportBluetooth()) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    /**
     * Focus open the bluetooth
     * 强制打开蓝牙
     *
     * @return
     */
    public static boolean focusOpenBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter.isEnabled()) {
            return true;
        }
        return bluetoothAdapter.enable();
    }

    /**
     * Get the list of bond devices
     * 获取已配对设备列表
     * @return
     */
    public List<BluetoothDevice> checkDevice() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        List<BluetoothDevice> bondDeviceList = new ArrayList<>();
        Set<BluetoothDevice> bluetoothDeviceSet = bluetoothAdapter.getBondedDevices();
        if (bluetoothDeviceSet != null && bluetoothDeviceSet.size() > 0) {
            for (BluetoothDevice device : bluetoothDeviceSet) {
                bondDeviceList.add(device);
            }
        }
        return bondDeviceList;
    }

}
