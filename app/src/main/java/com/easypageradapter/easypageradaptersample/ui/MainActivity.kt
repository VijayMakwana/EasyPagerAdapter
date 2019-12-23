package com.easypageradapter.easypageradaptersample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easypageradapter.easypageradaptersample.R
import com.easypageradapter.easypageradaptersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // init Views
        initViews()
    }

    private fun initViews() {
        // set click listeners
        mBinding.btnKotlinDemo.setOnClickListener {
            startActivity(Intent(this, KotlinDemoActivity::class.java))
        }
        mBinding.btnJavaDemo.setOnClickListener {
            startActivity(Intent(this, JavaDemoActivity::class.java))
        }
        mBinding.btnFragmentDemo.setOnClickListener {
            startActivity(Intent(this, FragmentPagerActivity::class.java))
        }
        mBinding.btnViewPager2.setOnClickListener {
            startActivity(Intent(this, ViewPager2Activity::class.java))
        }
    }
}
