package ru.mail.track.timer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenAtivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_ativity)

        scheduleSplashScreen()
    }
    private fun scheduleSplashScreen() {
        val splashScreenDuration = 2000L
        Handler().postDelayed(
            {
                val myIntent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(myIntent)
            },
            splashScreenDuration
        )
    }
}
