package com.adicoding.foodmarket.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentHomeNewTasteBinding
import com.adicoding.foodmarket.model.dummy.HomeVerticalModel
import com.adicoding.foodmarket.model.response.home.Data
import com.adicoding.foodmarket.ui.detail.DetailActivity
import com.adicoding.foodmarket.ui.home.newtaste.HomeNewTasteAdapter

class HomeRecomendedFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

    private var _binding: FragmentHomeNewTasteBinding? = null

    private val binding get() = _binding!!
    private var recomendedList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeNewTasteBinding.inflate(inflater, container, false)
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

        recomendedList = arguments?.getParcelableArrayList("data")

//        initDataDummy()
        var adapter = HomeNewTasteAdapter(recomendedList!!, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter
    }

//    private fun initDataDummy(){
//        foodlist = ArrayList()
//        foodlist.add(HomeVerticalModel("Cherry Healthy","10000", src = "",5f))
//        foodlist.add(HomeVerticalModel("Burger Tamayo","20000", src = "",4f))
//        foodlist.add(HomeVerticalModel("Bakhwan Cihuy","30000", src = "",4.5f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }
}