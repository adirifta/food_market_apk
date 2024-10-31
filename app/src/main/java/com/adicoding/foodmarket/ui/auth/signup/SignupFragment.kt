package com.adicoding.foodmarket.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentSignupBinding
import com.adicoding.foodmarket.ui.auth.AuthActivity

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

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

        binding.btnContinue.setOnClickListener {
           Navigation.findNavController(it)
               .navigate(R.id.action_signup_address, null)

            (activity as AuthActivity).toolbarSignUpAddress()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}