package ru.mail.track.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var isCurrentButtonStart = true
    var currTime = 0L
    val MAX_TIME = 999L
    val mlsec = 1000L
    lateinit var timer : CountDownTimer



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("INFO", "onCreate\ttime:$currTime\tstart:$isCurrentButtonStart")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currTime = savedInstanceState?.get("currTime") as Long? ?: 0L
        isCurrentButtonStart = savedInstanceState?.get("isCurrentButtonStart") as Boolean? ?: true
        counter_view.text = savedInstanceState?.get("textview") as String? ?: ""
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.i("INFO", "onSaveIS\ttime:$currTime\tstart:$isCurrentButtonStart")
        super.onSaveInstanceState(outState)


        outState?.putBoolean("isCurrentButtonStart", isCurrentButtonStart)
        outState?.putLong("currTime", currTime)
        outState?.putString("textview", counter_view.text.toString())
    }

    override fun onPause() {
        super.onPause()
        if (!isCurrentButtonStart)
            timer.cancel()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.i("INFO", "onReStor\ttime:$currTime\tstart:$isCurrentButtonStart")

        super.onRestoreInstanceState(savedInstanceState)
        currTime = savedInstanceState?.get("currTime") as Long? ?: 0L
        isCurrentButtonStart = savedInstanceState?.get("isCurrentButtonStart") as Boolean? ?: true
        counter_view.text = savedInstanceState?.get("textview") as String? ?: ""
        bt_timer.text = when(isCurrentButtonStart) {
            true -> "START"
            false -> "STOP"
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isCurrentButtonStart)
            timerStart()
    }


    private fun timerStart() {
        timer = object : CountDownTimer((MAX_TIME - currTime) * mlsec, mlsec) {

            override fun onTick(millisUntilFinished: Long) {
                currTime++
                updateText()
            }

            override fun onFinish() {
                counter_view.setText("Тысяча!")
                currTime = 0
                isCurrentButtonStart = true
                bt_timer.text = "START"
            }
        }.start()
    }


    fun onButtomClick(view: View) {
        if (isCurrentButtonStart) {
            timerStart()
            isCurrentButtonStart = false
            bt_timer.text = "STOP"

        } else {
            timer.cancel()
            isCurrentButtonStart = true
            bt_timer.text = "START"
        }

    }

    fun updateText() {
        counter_view.text = convertTime()
    }

    fun convertTime() : String {
        var tmp = currTime
        var tmp1 = currTime / 100L
        var tmp2 = (currTime % 100L) / 10L
        var tmp3 = (currTime % 100L) % 10L

        // Log.i("INFO", "convert USed with \t$tmp1\t$tmp2\t$tmp3, ")
        var number = ""
        number += when(tmp1) {
            1L -> "сто "
            2L-> "двести "
            3L -> "триста "
            4L -> "четыреста "
            5L -> "пятьсот "
            6L -> "шестьсот "
            7L -> "семьсот "
            8L -> "восемьсот "
            9L -> "девятьсот "
            else -> ""
        }
        number += when(tmp2) {
            2L -> "двадцать "
            3L -> "тридцать "
            4L -> "сорок "
            5L -> "пятьдесят "
            6L -> "шестьдесят "
            7L -> "семьдесят "
            8L -> "восемьдесят "
            9L -> "девятьдесят "
            0L -> ""
            else -> when(tmp3) {
                1L -> "одиннадцать"
                2L -> "двенадцать"
                3L -> "тринадцать"
                4L -> "четырнадцать"
                5L -> "пятнцадцать"
                6L -> "шестнадцать"
                7L -> "семнадцать"
                8L -> "восемнадцать"
                9L -> "девятнадцать"
                else -> "десять"
            }
        }
        if (!(tmp2).equals(1L)) {
            number += when(tmp3) {
                1L -> "один"
                2L -> "два"
                3L -> "три"
                4L -> "четыре"
                5L -> "пять"
                6L -> "шесть"
                7L -> "семь"
                8L -> "восемь"
                9L -> "девять"
                else -> ""
            }
        }
        return number
    }
}
