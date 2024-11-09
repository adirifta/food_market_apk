package com.adicoding.foodmarket.ui.profile.foodmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentProfileAccountBinding
import com.adicoding.foodmarket.databinding.FragmentProfileFoodMarketBinding
import com.adicoding.foodmarket.model.dummy.ProfileMenuModel
import com.adicoding.foodmarket.ui.profile.ProfileMenuAdapter

class ProfileFoodMarketFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private var _binding: FragmentProfileFoodMarketBinding? = null
    private val binding get() = _binding!!
    private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileFoodMarketBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        var adapter = ProfileMenuAdapter(menuArrayList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.adapter = adapter
        binding.rcList.layoutManager = layoutManager
    }

    fun initDataDummy() {
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Rate Apps"))
        menuArrayList.add(ProfileMenuModel("Help Center"))
        menuArrayList.add(ProfileMenuModel("Privacy & Policy"))
        menuArrayList.add(ProfileMenuModel("Term & Condition"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, "Ini menu yang kamu klik "+data.title, Toast.LENGTH_SHORT).show()
    }
}