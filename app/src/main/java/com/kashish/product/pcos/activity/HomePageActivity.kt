package com.kashish.product.pcos.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.kashish.product.pcos.R
import kotlinx.android.synthetic.main.app_bar_main.*

class HomePageActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var mDrawerToggle: ActionBarDrawerToggle
    var mToolBarNavigationListenerIsRegistered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        //supportActionBar!!.setIcon(R.drawable.logo)
        supportActionBar!!.title = ""

        val navBar = findViewById<View>(R.id.nav_view) as NavigationView
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        mDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
        enableViews(drawerLayout.isDrawerOpen(GravityCompat.START))

        start_button.setOnClickListener {
            startActivity(Intent(this@HomePageActivity,QuestionOne::class.java))
        }
    }

    private fun enableViews(enable: Boolean) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            //You may not want to open the drawer on swipe from the left in this case
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            // Remove hamburger
            mDrawerToggle.isDrawerIndicatorEnabled = false
            // Show back button
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.toolbarNavigationClickListener = object : View.OnClickListener {
                    override fun onClick(v: View) {
                        // Doesn't have to be onBackPressed
                        onBackPressed()
                    }
                }

                mToolBarNavigationListenerIsRegistered = true
            }

        } else {
            //You must regain the power of swipe for the drawer.
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

            // Remove back button
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            // Show hamburger
            mDrawerToggle.isDrawerIndicatorEnabled = true
            // Remove the/any drawer toggle listener
            mDrawerToggle.toolbarNavigationClickListener = null
            mToolBarNavigationListenerIsRegistered = false
        }

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
    }
}