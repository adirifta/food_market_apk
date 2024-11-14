package com.adicoding.foodmarket.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.adicoding.foodmarket.FoodMarket
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentSignupAddressBinding
import com.adicoding.foodmarket.model.request.RegisterRequest
import com.adicoding.foodmarket.model.response.login.LoginResponse
import com.adicoding.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson

class SignupAddressFragment : Fragment(), SignupContract.View {

    private var _binding: FragmentSignupAddressBinding? = null
    private val binding get() = _binding!!

    private lateinit var data: RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressDialog: Dialog? = null

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

        presenter = SignupPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initDummy()
        initListener()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDummy() {
        binding.etPhoneNumber.setText("081234567890")
        binding.etAddress.setText("Jalan Soekarno Hatta")
        binding.etHouseNumber.setText("10")
        binding.etCity.setText("Bandung")
    }

    private fun initListener(){
        binding.btnSignupNow.setOnClickListener {
            var phone = binding.etPhoneNumber.text.toString()
            var address = binding.etAddress.text.toString()
            var house = binding.etHouseNumber.text.toString()
            var city = binding.etCity.text.toString()

            data.let {
                it.address = address
                it.city = city
                it.houseNumber = house
                it.phoneNumber = phone
            }

            if (phone.isNullOrEmpty()) {
                binding.etPhoneNumber.error = "Silahkan masukkan nomor telepon"
                binding.etPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()) {
                binding.etAddress.error = "Silahkan masukkan alamat"
                binding.etAddress.requestFocus()
            } else if (house.isNullOrEmpty()) {
                binding.etHouseNumber.error = "Silahkan masukkan nomor rumah"
                binding.etHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()) {
                binding.etCity.error = "Silahkan masukkan nama kota"
                binding.etCity.requestFocus()
            } else {
                presenter.submitRegister(data, it)
            }

        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_signup_success, null)

            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)

        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initView(){
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