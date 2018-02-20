package com.easypageradapter.easypageradaptersample.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easypageradapter.easypageradaptersample.R
import com.easypageradapter.easypageradaptersample.databinding.FragmentABinding

/**
 * Created by HP on 29-01-2018.
 */
class FragmentA : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = DataBindingUtil.inflate<FragmentABinding>(inflater, R.layout.fragment_a, container, false)
        return fragmentBinding.root
    }
}