package com.easypageradapter.easypageradaptersample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easypageradapter.easypageradaptersample.R
import com.easypageradapter.easypageradaptersample.databinding.ActivityViewPager2Binding
import com.easypageradapter.viewpager2.setEasyFragmentStateAdapter

class ViewPager2Activity : AppCompatActivity() {
    private lateinit var mBinding: ActivityViewPager2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_pager2)

        mBinding.viewPager2.setEasyFragmentStateAdapter(this, listOf(FragmentA(), FragmentB(),
                FragmentC(), FragmentD()))

    }
}
