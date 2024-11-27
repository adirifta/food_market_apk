package com.adicoding.foodmarket.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adicoding.foodmarket.FoodMarket
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentHomeBinding
import com.adicoding.foodmarket.model.dummy.HomeModel
import com.adicoding.foodmarket.model.response.home.Data
import com.adicoding.foodmarket.model.response.home.HomeResponse
import com.adicoding.foodmarket.model.response.login.User
import com.adicoding.foodmarket.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var newStateList : ArrayList<Data> = ArrayList()
    private var popularList : ArrayList<Data> = ArrayList()
    private var recomendedList : ArrayList<Data> = ArrayList()

    private lateinit var presenter: HomePresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = HomePresenter(this)
        presenter.getHome()

//        initDataDummy()
    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        val user = FoodMarket.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)

        if (userResponse.profile_photo_url.isNotEmpty()){

            Glide.with(requireActivity())
                .load(userResponse.profile_photo_url)
                .into(binding.ivProfile)
        }
    }

//    private fun initDataDummy(){
//        foodlist = ArrayList()
//        foodlist.add(HomeModel("Cherry Healthy","",5f))
//        foodlist.add(HomeModel("Burger Tamayo","",4f))
//        foodlist.add(HomeModel("Bakhwan Cihuy","",4.5f))
//    }

    override fun onClick(v: View, data: Data) {
       val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        for (a in homeResponse.data.indices){
            var items:List<String> = homeResponse.data[a].types?.split(",") ?: ArrayList()
            for (x in items.indices){
                if (items[x].equals("new_food",true)){
                    newStateList?.add(homeResponse.data[a])
                } else if (items[x].equals("recommended",true)){
                    recomendedList?.add(homeResponse.data[a])
                } else if (items[x].equals("popular",true)){
                    popularList?.add(homeResponse.data[a])
                }
            }

        }

        var adapter = HomeAdapter(homeResponse.data, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(newStateList, popularList, recomendedList)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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