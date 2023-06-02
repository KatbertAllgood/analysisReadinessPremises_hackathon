package com.example.samolethackaton.screens.auth

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.LoginParamsDomain
import com.example.domain.models.RegistrationParamsDomain
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private lateinit var binding : FragmentAuthBinding
    private val viewModel : AuthFragmentVM by viewModels()

    private val TAG = AuthFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            listOf(
                fragmentAuthEtFio,
                fragmentAuthEtEmail,
                fragmentAuthEtRegLogin,
                fragmentAuthEtRegPassword,
                fragmentAuthEtRepeatPassword,
                fragmentAuthButtonSingUp
            ).forEach {
                it.visibility = View.GONE
            }

        }

        binding.fragmentAuthTvButtonSingIn.setOnClickListener {

            binding.fragmentAuthTvButtonSingIn.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.samolet_blue))

            binding.fragmentAuthTvButtonSingUp.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.samolet_grey))

            binding.apply {

                listOf(
                    fragmentAuthClLogin,
                    fragmentAuthClPassword,
                    fragmentAuthButtonSingIn
                ).forEach {
                    it.visibility = View.VISIBLE
                }

                listOf(
                    fragmentAuthEtFio,
                    fragmentAuthEtEmail,
                    fragmentAuthEtRegLogin,
                    fragmentAuthEtRegPassword,
                    fragmentAuthEtRepeatPassword,
                    fragmentAuthButtonSingUp
                ).forEach {
                    it.visibility = View.GONE
                }

            }
        }

        binding.fragmentAuthTvButtonSingUp.setOnClickListener {

            binding.fragmentAuthTvButtonSingIn.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.samolet_grey))

            binding.fragmentAuthTvButtonSingUp.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.samolet_blue))

            binding.apply {

                listOf(
                    fragmentAuthClLogin,
                    fragmentAuthClPassword,
                    fragmentAuthButtonSingIn
                ).forEach {
                    it.visibility = View.GONE
                }

                listOf(
                    fragmentAuthEtFio,
                    fragmentAuthEtEmail,
                    fragmentAuthEtRegLogin,
                    fragmentAuthEtRegPassword,
                    fragmentAuthEtRepeatPassword,
                    fragmentAuthButtonSingUp
                ).forEach {
                    it.visibility = View.VISIBLE
                }

            }
        }

        binding.fragmentAuthEtLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.fragmentAuthIvLogin.setImageResource(R.drawable.ic_login_selected)
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.fragmentAuthEtLogin.text.toString() == "") {
                    binding.fragmentAuthIvLogin.setImageResource(R.drawable.ic_login_unselected)
                }
            }

        })

        binding.fragmentAuthEtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.fragmentAuthIvPassword.setImageResource(R.drawable.ic_password_selected)
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.fragmentAuthEtPassword.text.toString() == "") {
                    binding.fragmentAuthIvPassword.setImageResource(R.drawable.ic_password_unselected)
                }
            }

        })

        binding.fragmentAuthButtonSingUp.setOnClickListener {

            if(
                (binding.fragmentAuthEtFio.text.toString() != "") &&
                (binding.fragmentAuthEtRegLogin.text.toString() != "") &&
                (binding.fragmentAuthEtEmail.text.toString() != "") &&
                (binding.fragmentAuthEtRegPassword.text.toString() != "") &&
                (binding.fragmentAuthEtRegPassword.text.toString() != "")
            ){
                val request = RegistrationParamsDomain(
                    binding.fragmentAuthEtFio.text.toString(),
                    binding.fragmentAuthEtRegLogin.text.toString(),
                    binding.fragmentAuthEtRegPassword.text.toString(),
                    binding.fragmentAuthEtEmail.text.toString(),
                )
                Log.d(TAG, "REQUEST: $request")
                viewModel.registerUser(request, requireContext())
                binding.apply {
                    listOf(
                        fragmentAuthEtFio,
                        fragmentAuthEtEmail,
                        fragmentAuthEtRegLogin,
                        fragmentAuthEtRegPassword,
                        fragmentAuthEtRepeatPassword,
                    ).forEach {
                        it.setText("")
                    }
                    listOf(
                        fragmentAuthEtFio,
                        fragmentAuthEtEmail,
                        fragmentAuthEtRegLogin,
                        fragmentAuthEtRegPassword,
                        fragmentAuthEtRepeatPassword,
                        fragmentAuthButtonSingUp
                    ).forEach {
                        it.visibility = View.GONE
                    }
                    listOf(
                        fragmentAuthClLogin,
                        fragmentAuthClPassword,
                        fragmentAuthButtonSingIn
                    ).forEach {
                        it.visibility = View.VISIBLE
                    }
                    fragmentAuthEtLogin.setText(request.login)
                    fragmentAuthEtPassword.setText(request.password)
                }
            }
        }

        binding.fragmentAuthButtonSingIn.setOnClickListener {

            if(
                (binding.fragmentAuthEtLogin.text.toString() != "") &&
                (binding.fragmentAuthEtPassword.text.toString() != "")
            ){
                viewModel.loginUser(
                    LoginParamsDomain(
                        binding.fragmentAuthEtLogin.text.toString(),
                        binding.fragmentAuthEtPassword.text.toString()
                    ),
                    requireContext()
                )
            }
        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.getOnSuccessLogin().observe(viewLifecycleOwner) {

            findNavController().navigate(R.id.action_authFragment_to_profileFragment)
        }
    }

}