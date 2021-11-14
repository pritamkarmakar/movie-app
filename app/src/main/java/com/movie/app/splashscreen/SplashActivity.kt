package com.movie.app.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.movie.app.R
import com.movie.app.landingscreen.view.MovieListActivity

class SplashActivity : AppCompatActivity() {
    private val splashTime = 1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // start the main activity
        Handler().postDelayed({
            launchMainActivity()
        }, splashTime)
    }

    private fun launchMainActivity() {
        val intent = Intent(this, MovieListActivity::class.java)
        startActivity(intent)
    }
}
