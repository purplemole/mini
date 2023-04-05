package com.clobot.mini.util

import android.os.StatFs
import android.os.Environment
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
//
//private data class VolumeStats(
//    val mStorageVolume: StorageVolume,
//    var mTotalSpace: Long = 0,
//    var mUsedSpace: Long = 0
//)
//
//class DeviceStorage2(private val context: Context) {
//    private val mStorageVolumesByPath = HashMap<String, VolumeStats>()
//
//    private fun getVolumeStats() {
//        val mStorageManager = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
//        val mStorageVolumes = mStorageManager.storageVolumes
//        mStorageVolumesByPath.clear()
//
//        var totalSpace: Long
//        var usedSpace: Long
//        mStorageVolumes.forEach { storageVolume ->
//            val path: String = storageVolume.getStorageVolumePath()
//            if (storageVolume.isPrimary) {
//                val uuid = StorageManager.UUID_DEFAULT
//                val storageStatsManager =
//                    context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
//                totalSpace = storageStatsManager.getTotalBytes(uuid)
//                usedSpace = totalSpace - storageStatsManager.getFreeBytes(uuid)
//            } else {
//                val stats = Os.statvfs(storageVolume.getStorageVolumePath())
//                val blockSize = stats.f_bsize
//                totalSpace = stats.f_blocks * blockSize
//                usedSpace = totalSpace - stats.f_bavail * blockSize
//            }
//            mStorageVolumesByPath[path] = VolumeStats(storageVolume, totalSpace, usedSpace)
//        }
//    }
//
//    private fun StorageVolume.getStorageVolumePath(): String {
//        return try {
//            javaClass
//                .getMethod("getPath")
//                .invoke(this) as String
//        } catch (e: Exception) {
//            e.printStackTrace()
//            ""
//        }
//    }
//
//    fun showVolumeStates(): Triple<Int, Int, Int> {
//        getVolumeStats()
//
//        var usedSpace = 0
//        var totalSpace = 0
//        var usagePer = 0
//        mStorageVolumesByPath.forEach { (_, volumeStats) ->
//            val usedToShift = getShiftUnits(volumeStats.mUsedSpace)
//            usedSpace = (volumeStats.mUsedSpace / usedToShift.first).roundToInt()
//            val totalToShift = getShiftUnits(volumeStats.mTotalSpace)
//            totalSpace = (volumeStats.mTotalSpace / totalToShift.first).roundToInt()
//            usagePer = (usedSpace.toDouble() / totalSpace.toDouble() * 100.0).roundToInt()
//        }
//        return Triple(totalSpace, usedSpace, usagePer)
//    }
//
//    val KB = 1_000f
//    val MB = 1_000_000f
//    val GB = 1_000_000_000f
//
//    private fun getShiftUnits(x: Long): Pair<Float, String> {
//        val usedSpaceUnits: String
//        val shift =
//            when {
//                x < KB -> {
//                    usedSpaceUnits = "Bytes"
//                    1f
//                }
//                x < MB -> {
//                    usedSpaceUnits = "KB"
//                    KB
//                }
//                x < GB -> {
//                    usedSpaceUnits = "MB"
//                    MB
//                }
//                else -> {
//                    usedSpaceUnits = "GB"
//                    GB
//                }
//            }
//        return Pair(shift, usedSpaceUnits)
//    }
//}