package com.easypageradapter.easypageradaptersample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easypageradapter.easypageradaptersample.R
import com.easypageradapter.easypageradaptersample.databinding.ActivityFragmentPagerBinding
import com.easypageradapter.setEasyFragmentPagerAdapter
import com.easypageradapter.setEasyFragmentStatePagerAdapter

class FragmentPagerActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFragmentPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_fragment_pager)
        initViews()
    }

    private fun initViews() {
        // Kotlin code
        //For Fragment Pager
        setFragmentPagerAdapterKotlinCode()

        //For Fragment State Pager
        /*setFragmentStatePagerAdapterKotlinCode()*/

        // JAVA code, Below code is not work here (because this is kotlin file) You need to use this code in Java file
        //For Fragment Pager
        /*new EasyFragmentPagerAdapter(supportFragmentManager, arrayListOf(FragmentA(), FragmentB())).into(mBinding.viewPager);*/

        //For Fragment State Pager
        /*new EasyFragmentStatePagerAdapter(supportFragmentManager, arrayListOf(FragmentA(), FragmentB())).into(mBinding.viewPager);*/

        /*If You are working with TabLayout then pageTitle() method is available to add tab name*/
        /*If You want to change page width then pageWidth() method is available*/
    }

    private fun setFragmentStatePagerAdapterKotlinCode() {
        mBinding.viewPager.setEasyFragmentStatePagerAdapter(supportFragmentManager, arrayListOf(FragmentA(), FragmentB()))
    }

    private fun setFragmentPagerAdapterKotlinCode() {
        mBinding.viewPager.setEasyFragmentPagerAdapter(supportFragmentManager, arrayListOf(FragmentA(), FragmentB()))
    }
}