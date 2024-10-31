package com.adicoding.foodmarket.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentSignupAddressBinding
import com.adicoding.foodmarket.databinding.FragmentSignupBinding
import com.adicoding.foodmarket.ui.auth.AuthActivity

class SignupAddressFragment : Fragment() {

    private var _binding: FragmentSignupAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnSignupNow.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_signup_success, null)

            (activity as AuthActivity).toolbarSignUpSuccess()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}