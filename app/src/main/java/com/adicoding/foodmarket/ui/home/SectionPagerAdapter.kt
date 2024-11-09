package com.adicoding.foodmarket.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.adicoding.foodmarket.ui.home.newtaste.HomeNewTasteFragment
import com.adicoding.foodmarket.ui.home.popular.HomePopularFragment
import com.adicoding.foodmarket.ui.home.recommended.HomeRecomendedFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)  {

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "New Taste"
            1 -> "Popular"
            2 -> "Recommended"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        val fragment : Fragment
        return when(position) {
            0 -> {
                fragment = HomeNewTasteFragment()
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                return fragment
            }
            2 -> {
                fragment = HomeRecomendedFragment()
                return fragment
            }
            else -> {
                fragment = HomeNewTasteFragment()
                return fragment
            }
        }
    }
}