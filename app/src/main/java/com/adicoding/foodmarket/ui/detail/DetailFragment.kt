package com.adicoding.foodmarket.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.adicoding.foodmarket.R
import com.adicoding.foodmarket.databinding.FragmentDetailBinding
import com.adicoding.foodmarket.databinding.FragmentHomeBinding
import com.adicoding.foodmarket.model.response.home.Data
import com.adicoding.foodmarket.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }

        binding.btnOrderNow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_payment)
        }
    }

    private fun initView(data: Data?) {
        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(binding.ivPoster)

        binding.tvTitle.text = data?.name
        binding.tvDesc.text = data?.description
        binding.tvIngredients.text = data?.ingredients

        binding.tvTotal.formatPrice(data?.price.toString())
    }
}