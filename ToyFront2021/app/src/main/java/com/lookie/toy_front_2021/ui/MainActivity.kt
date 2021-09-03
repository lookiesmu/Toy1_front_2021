package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    // https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko
    private val model : MainViewModel by viewModels()

    // https://material.io/components/bottom-navigation/android#using-bottom-navigation
    private lateinit var bottomNav : BottomNavigationView

    private lateinit var nestedScrollView : NestedScrollView

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        viewBinding()
        viewHandler()
    }

    /*
    뷰에 대한 참조를 얻는 공간 입니다.
     */
    private fun viewBinding() {
        bottomNav = findViewById(R.id.bottom_nav)
//        appbar = findViewById(R.id.app_bar)
        nestedScrollView = findViewById(R.id.nestedScrollView)
    }


    /*
    뷰에 대한 리스너를 설정하기 위한 핸들러를 만드는 공간 입니다.
     */
    private fun viewHandler() {
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener {
            it.onNavDestinationSelected(navController)
            true
        }
    }


    /*
    아래 3 메소드는 바텀 네비게이션에 뱃지를 달기 위한 함수 입니다.
    https://material.io/develop/android/components/badging
     */
    private fun setBadgeInvisible(@IdRes menuItemId : Int) {
        val badgeDrawable = bottomNav.getBadge(menuItemId)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun setBadge(@IdRes menuItemId : Int) {
        val badge = bottomNav.getOrCreateBadge(menuItemId)
        badge.isVisible = true
        badge.number = 99
    }

    private fun removeBadge(@IdRes menuItemId : Int) {
        bottomNav.removeBadge(menuItemId)
    }

    fun toggleBar(on : Boolean) {
        val visibility = if (on) View.VISIBLE else View.INVISIBLE
        bottomNav.visibility = visibility
    }

}