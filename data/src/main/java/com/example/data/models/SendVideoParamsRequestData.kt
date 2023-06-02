package com.example.data.models

import android.os.Handler
import android.os.Looper
import com.google.gson.annotations.SerializedName
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

data class SendVideoParamsRequestData(

    private val file: File,
    private val contentType: String,
    private val callback: UploadCallback

//    @SerializedName("video")
//    var video : File
) : RequestBody() {
    override fun contentType() = "$contentType/*".toMediaTypeOrNull()

    override fun contentLength() = file.length()

    override fun writeTo(sink: BufferedSink) {

        val length = file.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
        var uploaded = 0L
        fileInputStream.use {
            inputStram ->

            var read : Int
            val handler = Handler(Looper.getMainLooper())
            while (inputStram.read(buffer).also {
                read = it
                }!= -1){
                uploaded += read
                sink.write(buffer, 0, read)
            }
        }
    }

    interface UploadCallback {

        fun onPogressUpdate(percentage: Int)

    }

    inner class ProgressUpdater(
        private val uploaded: Long,
        private val total: Long
    ) : Runnable {

        override fun run() {
            callback.onPogressUpdate((100*uploaded/total).toInt())
        }

    }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 2048
    }

}
