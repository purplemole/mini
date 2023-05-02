package com.clobot.mini.util

import android.os.Environment
import android.os.StatFs
import android.util.Log

class DeviceStorage() {
    fun getTotalCapacity(): Long {
        val externalDir = Environment.getExternalStorageDirectory()
        val statFs = StatFs(externalDir.path)
        val totalBytes = statFs.blockSizeLong * statFs.blockCountLong
        val totalGB = totalBytes / (1024 * 1024 * 1024) // Convert bytes to GB
        Log.i("DeviceStorage", "totalBytes is $totalBytes, totalGB is $totalGB")
        return totalGB
    }

    fun getCapacityInUse(): Long {
        val externalDir = Environment.getExternalStorageDirectory()
        val statFs = StatFs(externalDir.path)
        val freeBytes = statFs.blockSizeLong * statFs.availableBlocksLong
        val totalBytes = statFs.blockSizeLong * statFs.blockCountLong
        val capacityInUseBytes = totalBytes - freeBytes
        val capacityInUseGB = capacityInUseBytes / (1024 * 1024 * 1024) // Convert bytes to GB
        Log.i("DeviceStorage", "inUseBytes: $capacityInUseBytes, inUseGB: $capacityInUseGB")
        return capacityInUseGB
    }
}