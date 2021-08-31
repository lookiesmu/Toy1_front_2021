package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationBarView
import com.lookie.toy_front_2021.R

class MainActivity : AppCompatActivity() {

    // https://material.io/components/bottom-navigation/android#using-bottom-navigation
    lateinit var bottomNav: NavigationBarView

//    // https://material.io/components/app-bars-top/android#regular-top-app-bar
//    lateinit var appbar: AppBarLayout

    lateinit var nestedScrollView: NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    // page_1 이 선택 되었을 때 반응
                    Log.d("MainActivity", "page1이 선택되었습니다.")
                    // bottomNav.selectedItemId = R.id.page_1

                }
                R.id.page_2 -> {
                    // page_1 이 선택 되었을 때 반응
                    Log.d("MainActivity", "page2이 선택되었습니다.")
                }
                R.id.page_3 -> {
                    // page_1 이 선택 되었을 때 반응
                    Log.d("MainActivity", "page3이 선택되었습니다.")
                }
                R.id.page_4 -> {
                    // page_1 이 선택 되었을 때 반응
                    Log.d("MainActivity", "page4이 선택되었습니다.")
                }
            }
            true
        }
    }


    /*
    아래 3 메소드는 바텀 네비게이션에 뱃지를 달기 위한 함수 입니다.
    https://material.io/develop/android/components/badging
     */
    private fun setBadgeInvisible(@IdRes menuItemId: Int) {
        val badgeDrawable = bottomNav.getBadge(menuItemId)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun setBadge(@IdRes menuItemId: Int) {
        val badge = bottomNav.getOrCreateBadge(menuItemId)
        badge.isVisible = true
        badge.number = 99
    }

    private fun removeBadge(@IdRes menuItemId: Int) {
        bottomNav.removeBadge(menuItemId)
    }

    fun toggleBar(on: Boolean) {
        val visibility = if (on) View.VISIBLE else View.INVISIBLE
        bottomNav.visibility = visibility
    }

}