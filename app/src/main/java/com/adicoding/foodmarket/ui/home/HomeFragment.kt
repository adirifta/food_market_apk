package com.adicoding.foodmarket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adicoding.foodmarket.databinding.FragmentHomeBinding
import com.adicoding.foodmarket.model.dummy.HomeModel

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private var foodlist : ArrayList<HomeModel> = ArrayList()

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

        initDataDummy()
        var adapter = HomeAdapter(foodlist, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun initDataDummy(){
        foodlist = ArrayList()
        foodlist.add(HomeModel("Cherry Healthy","",5f))
        foodlist.add(HomeModel("Burger Tamayo","",4f))
        foodlist.add(HomeModel("Bakhwan Cihuy","",4.5f))
    }

    override fun onClick(v: View, data: HomeModel) {
       Toast.makeText(context, "test click", Toast.LENGTH_SHORT).show()
    }
}