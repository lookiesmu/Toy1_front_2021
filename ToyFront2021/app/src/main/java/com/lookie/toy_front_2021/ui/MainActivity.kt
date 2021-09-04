package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    // https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko
    private val model : MainViewModel by viewModels()

    // https://material.io/components/bottom-navigation/android#using-bottom-navigation
    private lateinit var bottomNav : BottomNavigationView

    private lateinit var nestedScrollView : NestedScrollView

    private lateinit var navController : NavController

    private lateinit var loading : View

    private lateinit var topBar : androidx.appcompat.widget.Toolbar

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
        loading = findViewById(R.id.main_loading)
        topBar = findViewById(R.id.toolbar)
    }


    /*
    뷰에 대한 리스너를 설정하기 위한 핸들러를 만드는 공간 입니다.
     */
    private fun viewHandler() {
        NavigationUI.setupWithNavController(bottomNav, navController)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.question, R.id.login, R.id.join, R.id.profile, R.id.mypage, R.id.answerList, R.id.loading))
        findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout).apply {
            setupWithNavController(topBar, navController)
        }
        NavigationUI.setupWithNavController(topBar, navController, appBarConfiguration)
        topBar.setOnMenuItemClickListener {
            it.onNavDestinationSelected(navController)
        }

        bottomNav.setOnItemSelectedListener {
            it.onNavDestinationSelected(navController)
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
        val visibility = if (on) View.VISIBLE else View.GONE
        bottomNav.visibility = visibility
    }

    fun toggleLoading(on : Boolean) {
        val visibility = if (on) View.VISIBLE else View.GONE
        loading.visibility = visibility
    }

    fun stop(after : () -> Unit) {
        MaterialAlertDialogBuilder(loading.context)
            .setMessage("죄송합니다. 현재 이용 블가능 합니다.")
            .setPositiveButton("예") { _, _ ->
                after()
            }
            .setOnCancelListener {
                after()
            }
            .show()
    }

    fun toggleCalendar(on: Boolean) {
        topBar.menu[0].isVisible = on
    }
}