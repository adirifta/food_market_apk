package com.adicoding.foodmarket.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.adicoding.foodmarket.FoodMarket
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.ui.MainActivity
import com.adicoding.foodmarket.databinding.FragmentSigninBinding
import com.adicoding.foodmarket.model.response.login.LoginResponse
import com.adicoding.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson

class SigninFragment : Fragment(), SigninContract.View {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    lateinit var presenter: SigninPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SigninPresenter(this)

        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        initView()
        initDummy()

        binding.btnSignup.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", 2)
            startActivity(signup)
        }

        binding.btnSignin.setOnClickListener{
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            if (email.isNullOrEmpty()) {
                binding.etEmail.error = "Silahkan masukkan email Anda"
                binding.etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                binding.etPassword.error = "Silahkan masukkan password Anda"
                binding.etPassword.requestFocus()
            } else{
                presenter.submitLogin(email, password)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initDummy() {
        binding.etEmail.setText("jennie.kim@blackpink.com")
        binding.etPassword.setText("12345678")
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun showError(message: String?) {
        TODO("Not yet implemented")
    }

    override fun showMessage(message: String?) {
        TODO("Not yet implemented")
    }
}
