package com.easypageradapter.easypageradaptersample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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