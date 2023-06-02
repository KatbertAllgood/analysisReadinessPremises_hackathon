package com.example.samolethackaton.screens.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.data.network.api.NetworkService
import com.example.data.network.api.ServerApi
import com.example.domain.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class CameraFragmentVM : ViewModel() {

    private val TAG = CameraFragmentVM::class.simpleName

    fun isCameraPresentInPhone(activity: Activity) : Boolean {

        return activity.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    fun getCameraPermission(
        activity: Activity,
        context: Context
    ) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.CAMERA),
                Constants.CAMERA_PERMISSION_CODE
            )
        }
    }

    fun recordVideo(activity: Activity) {

        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also {
//            it.type = "video/*"
            val mimeTypes = arrayOf("video/mp4", "video/wav")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            activity.startActivityForResult(it, Constants.VIDEO_RECORD_CODE)
        }
    }

    fun selectVideoFromGallery(activity: Activity) {

        val intent : Intent
        if(android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED)) {
            intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        } else {
            intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.INTERNAL_CONTENT_URI)
        }
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("return-data", true)
        activity.startActivityForResult(intent, Constants.VIDEO_RECORD_CODE)
    }

    fun downloadFile(
        context: Context
    ) {

        val call : Call<ResponseBody> = ServerApi.invoke().downloadFile()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {

                    Log.d(TAG, "server contacted and has file")

                    val writtenToDisk : Boolean = writeResponseBodyToDisk(response.body()!!, context)

                    Log.d(TAG, "file  downloaded was success? $writtenToDisk")
                } else {
                    Log.d(TAG, "server contact failed")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "ERROR")
            }

        })
    }

    fun writeResponseBodyToDisk(
        body: ResponseBody,
        context: Context
    ) : Boolean {

        try {

            val file : File = File(context.getExternalFilesDir(null), File.separator + "Скор-карта и оценка решений.xls")

            var inputStream : InputStream? = null
            var outputStream : OutputStream? = null

            try {
                val fileReader : ByteArray = byteArrayOf()

                val fileSize = body.contentLength()
                var fileSizeDownloaded = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(file)

                while(true) {

                    val read : Int = inputStream.read(fileReader)

                    if (read == -1) {
                        break
                    }

                    outputStream.write(fileReader, 0, read)

                    fileSizeDownloaded += read

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize)
                }

                outputStream.flush()

                return true
            } catch (e : IOException) {
                return false
            } finally {

                if (inputStream != null) {
                    inputStream.close()
                }

                if (outputStream != null) {
                    outputStream.close()
                }
            }
        } catch (e: IOException) {
            return false
        }
    }

}