package com.easypageradapter.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * This is extension function of view pager which set the view pager to fragment state pager adapter
 * It takes two arguments [fa] and [fragmentList]
 *
 * @property fa instance of FragmentActivity
 *
 * @property fragmentList list of fragment which are displayed as pages in view pager
 *
 * @return instance of EasyFragmentStateAdapter class
 */
fun ViewPager2.setEasyFragmentStateAdapter(fa: FragmentActivity, fragmentList: List<Fragment>): EasyFragmentStateAdapter {
    val arrList = ArrayList<Fragment>(fragmentList.size)
    arrList.addAll(fragmentList)
    val easyFragmentStateAdapter = EasyFragmentStateAdapter(fa, arrList)
    this.adapter = easyFragmentStateAdapter

    return easyFragmentStateAdapter
}


/**
 * This class extends FragmentStateAdapter class and also have other utility methods
 * @property fa instance of FragmentActivity
 *
 * @property fragmentList list of fragment which are displayed as pages in view pager
 *
 * @constructor create instance of EasyFragmentStateAdapter
 */
class EasyFragmentStateAdapter(fa: FragmentActivity, fragmentList: ArrayList<Fragment>) : FragmentStateAdapter(fa) {
    private val mFragmentList = fragmentList

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment = mFragmentList[position]

}