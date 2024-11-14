package com.adicoding.foodmarket.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentSignupBinding
import com.adicoding.foodmarket.model.request.RegisterRequest
import com.adicoding.foodmarket.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    var filePath: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummy()
        initListener()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListener() {
        binding.ivProfil.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        binding.btnContinue.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            if (fullName.isEmpty()) {
                binding.etFullName.error = "Silahkan masukkan Full Name"
                binding.etFullName.requestFocus()
            }else if (email.isEmpty()) {
                binding.etEmail.error = "Silahkan masukkan Email"
                binding.etEmail.requestFocus()
            }else if (pass.isEmpty()) {
                binding.etPassword.error = "Silahkan masukkan Password"
                binding.etPassword.requestFocus()
            } else {
                var data = RegisterRequest(
                    fullName,
                    email,
                    pass,
                    pass,
                    "",
                    "",
                    "",
                    "",
                    filePath
                )

                val bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_address, bundle)

                (activity as AuthActivity).toolbarSignUpAddress()
            }

        }
    }

    private fun initDummy() {
        binding.etFullName.setText("Jennie Kim White")
        binding.etEmail.setText("jennie.kim@blackpink.com")
        binding.etPassword.setText("12345678")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfil)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}