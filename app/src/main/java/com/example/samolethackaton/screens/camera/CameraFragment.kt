package com.example.samolethackaton.screens.camera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.utils.Constants
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentCameraBinding


class CameraFragment : Fragment(R.layout.fragment_camera) {
    private lateinit var binding : FragmentCameraBinding
    private val viewModel : CameraFragmentVM by viewModels()

    private val TAG = CameraFragment::class.simpleName

//    private lateinit var mediaPlayer: MediaPlayer
//    private lateinit var libVlc : LibVLC


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        libVlc = LibVLC(requireContext())
//        mediaPlayer = MediaPlayer(libVlc)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentCameraTvHintAboutTable.visibility = View.GONE
        binding.fragmentCameraButtonOpenTable.visibility = View.GONE

        if (viewModel.isCameraPresentInPhone(requireActivity())){
            Log.d(TAG, "Camera is detected")
            viewModel.getCameraPermission(requireActivity(), requireContext())
        } else {
            Log.d(TAG, "Camera is NOT detected")
        }

        binding.fragmentCameraButtonScanFloor.setOnClickListener {

            viewModel.recordVideo(requireActivity())

            binding.fragmentCameraTvHintAboutTable.visibility = View.VISIBLE
            binding.fragmentCameraButtonOpenTable.visibility = View.VISIBLE

        }

        binding.fragmentCameraButtonUploadVideo.setOnClickListener {

            viewModel.selectVideoFromGallery(requireActivity())

            binding.fragmentCameraTvHintAboutTable.visibility = View.VISIBLE
            binding.fragmentCameraButtonOpenTable.visibility = View.VISIBLE

        }

        binding.fragmentCameraButtonDownloadFile.setOnClickListener {

            viewModel.downloadFile(requireContext())

        }

        binding.fragmentCameraButtonOpenTable.setOnClickListener {

            val url = Constants.TABLE_URL
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

    }

    override fun onStart() {
        super.onStart()

//        mediaPlayer.attachViews(binding.rtspVideo, null, false, false)
//
//        val media = Media(libVlc, Uri.parse(Constants.RTSP_STREAM))
//        media.setHWDecoderEnabled(true, false)
//        media.addOption(":network-caching=600")
//
//        mediaPlayer.media = media
//        media.release()
//        mediaPlayer.play()
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onStop() {
        super.onStop()

//        mediaPlayer.stop()
//        mediaPlayer.detachViews()
    }

    override fun onDestroy() {
        super.onDestroy()

//        mediaPlayer.release()
//        libVlc.release()
    }

}