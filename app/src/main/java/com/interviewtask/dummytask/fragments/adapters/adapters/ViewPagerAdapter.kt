package com.example.maptask.adapters

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.interviewtask.dummytask.fragments.ListFragment
import com.interviewtask.dummytask.fragments.LoginFragment
import com.interviewtask.dummytask.fragments.MapFragment


@Suppress("DEPRECATION")

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = when (position) {
        0 -> MapFragment()
        1 -> ListFragment()
        2 -> LoginFragment()
        else -> MapFragment()
    }
}