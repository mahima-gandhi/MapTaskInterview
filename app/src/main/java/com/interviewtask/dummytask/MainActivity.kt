package com.interviewtask.dummytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.interviewtask.dummytask.databinding.ActivityMainBinding
import com.interviewtask.dummytask.fragments.MapFragment
import com.interviewtask.dummytask.fragments.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mPagerAdapter: ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDetails()
    }

    fun setDetails()
    {
        setUpViewPager(binding.viewPager)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.mapFragment -> {
                  binding.viewPager.currentItem = 0
                  binding.tvTitle.text = resources.getString(R.string.lbt_map)
                }
                R.id.listFragment -> {
                    binding.viewPager.currentItem = 1
                    binding.tvTitle.text = resources.getString(R.string.list)

                }
                R.id.loginFragment -> {
                    binding.viewPager.currentItem = 2
                    binding.tvTitle.text = resources.getString(R.string.login)

                }

            }
            true
        }
    }
    fun setUpViewPager(viewPager: ViewPager2)
    {
        mPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = mPagerAdapter
        viewPager.isUserInputEnabled = false
        viewPager.offscreenPageLimit = 2
    }
}