package ru.mail.track.timer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashScreenAtivity : AppCompatActivity() {
    var currTime = 0L
    val MAX_TIME = 2L
    val mlsec = 1000L
    lateinit var timer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_ativity)

        currTime = savedInstanceState?.get("currTime") as Long? ?: 0L
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong("currTime", currTime)
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        currTime = savedInstanceState?.get("currTime") as Long? ?: 0L
    }

    override fun onResume() {
        super.onResume()
        timerStart()
    }

    private fun timerStart() {
        timer = object : CountDownTimer((MAX_TIME - currTime) * mlsec, mlsec) {

            override fun onTick(millisUntilFinished: Long) {
                currTime++
            }

            override fun onFinish() {
                val myIntent = Intent(applicationContext, MainActivity::class.java)
                finish()
                startActivity(myIntent)
            }
        }.start()
    }
}
