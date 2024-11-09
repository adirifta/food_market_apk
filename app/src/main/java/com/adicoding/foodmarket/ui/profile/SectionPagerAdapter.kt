package com.adicoding.foodmarket.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.adicoding.foodmarket.ui.home.newtaste.HomeNewTasteFragment
import com.adicoding.foodmarket.ui.home.popular.HomePopularFragment
import com.adicoding.foodmarket.ui.home.recommended.HomeRecomendedFragment
import com.adicoding.foodmarket.ui.profile.account.ProfileAccountFragment
import com.adicoding.foodmarket.ui.profile.foodmarket.ProfileFoodMarketFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)  {

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Account"
            1 -> "FoodMarket"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        val fragment : Fragment
        return when(position) {
            0 -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
            1 -> {
                fragment = ProfileFoodMarketFragment()
                return fragment
            }
            else -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
        }
    }
}