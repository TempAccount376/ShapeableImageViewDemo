package com.example.myshapeable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                select(requireNotNull(tab))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                select(requireNotNull(tab))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
        })

        tablayout.getTabAt(0)?.select()
    }

    private fun select(tab: TabLayout.Tab) {
        when (tab.position) {
            TAB_SHAPES -> replaceFragment(ShapesFragment())
            TAB_CUSTOMIZE -> replaceFragment(CustomizeFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.container, fragment)
        }
    }

    companion object {
        private const val TAB_SHAPES = 0
        private const val TAB_CUSTOMIZE = 1
    }

}