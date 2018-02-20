package com.easypageradapter.easypageradaptersample.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
    }
}
