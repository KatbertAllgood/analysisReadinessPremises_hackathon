package com.example.samolethackaton.screens

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.data.models.SendVideoParamsRequestData
import com.example.data.models.SendVideoParamsResponseData
import com.example.data.network.api.ServerApi
import com.example.domain.utils.Constants
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), SendVideoParamsRequestData.UploadCallback {
    lateinit var binding : ActivityMainBinding
    private val viewModel: MainActivityVM by viewModels()

    private val TAG = MainActivity::class.simpleName

    private var selectedVideoUri: Uri? = null


    private val executorService = Executors.newSingleThreadScheduledExecutor()
    private val delay = 5L
    private val period = 5L

    private val task = Runnable {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.authFragment -> {
                   binding.bottomNavigationBar.visibility = View.GONE
                }
                else -> {
                    binding.root.setBackgroundResource(R.color.fragment_background)
                    binding.bottomNavigationBar.visibility = View.VISIBLE
//                    binding.bottomNavigationBar.menu.getItem(0).isChecked = true
                }
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when(requestCode) {
                Constants.VIDEO_RECORD_CODE -> {

                    selectedVideoUri = data?.data

                    uploadVideo()
                }
            }
        }
    }

    private fun uploadVideo() {

        Log.d(TAG, "UPLOADING")

        if (selectedVideoUri == null){

            binding.root.snackbar("SNACKBAR")
            return
        }

        val parcelFileDesctiptor = contentResolver.openFileDescriptor(
            selectedVideoUri!!, "r", null
        ) ?: return

        val inputStream = FileInputStream(parcelFileDesctiptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedVideoUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        binding.progressBar.progress = 0
        val body = SendVideoParamsRequestData(file, "video", this)

        ServerApi().uploadVideo(MultipartBody.Part.createFormData(
            "video",
            file.name,
            body
        ),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json")
        ).enqueue(object : Callback<SendVideoParamsResponseData>{
            override fun onResponse(
                call: Call<SendVideoParamsResponseData>,
                response: Response<SendVideoParamsResponseData>
            ) {
                Log.d(TAG, response.toString())
                response.body()?.let {
                    Log.d(TAG, it.toString())
                    binding.root.snackbar("Видео успешно загружено!")
                    binding.progressBar.progress = 100



                }
            }

            override fun onFailure(call: Call<SendVideoParamsResponseData>, t: Throwable) {
                binding.root.snackbar(t.message!!)
                binding.progressBar.progress = 0
            }

        })
    }



    private fun ContentResolver.getFileName(selectedVideoUri : Uri): String {

        var name = ""
        val returnCursor = this.query(selectedVideoUri, null, null, null, null)
        if (returnCursor != null){
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex)
            returnCursor.close()
        }

        return name
    }

    private fun View.snackbar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).also {
            snackbar -> snackbar.setAction("OK") {
                snackbar.dismiss()
        }
        }.show()
    }

    override fun onPogressUpdate(percentage: Int) {

        binding.progressBar.progress = percentage
    }

}