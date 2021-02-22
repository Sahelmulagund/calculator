package com.algomath.mathfound


import android.accessibilityservice.GestureDescription
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import net.objecthunter.exp4j.ExpressionBuilder


class CalcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        val actionBar: ActionBar = supportActionBar!!
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true)
        }
        MobileAds.initialize(this){}

       val mAdView = findViewById<View>(R.id.ad) as AdView
     //  mAdView.adSize = AdSize.BANNER

      //  mAdView.setAdUnitId("ca-app-pub-2453344230085588/4796887729")
        val adRequest: AdRequest = AdRequest.Builder().build()

        mAdView.loadAd(adRequest)

        var tvOne = findViewById<View>(R.id.tvOne) as TextView
        var tvTwo = findViewById<View>(R.id.tvTwo) as TextView
        var tvThree = findViewById<View>(R.id.tvThree) as TextView
        var tvFour = findViewById<View>(R.id.tvFour) as TextView
        var tvFive = findViewById<View>(R.id.tvFive) as TextView
        var tvSix = findViewById<View>(R.id.tvSix) as TextView
        var tvSeven = findViewById<View>(R.id.tvSeven) as TextView
        var tvEight = findViewById<View>(R.id.tvEight) as TextView
        var tvNine = findViewById<View>(R.id.tvNine) as TextView
        var tvDot = findViewById<View>(R.id.tvDot) as TextView
        var tvBack = findViewById<View>(R.id.tvBack) as ImageView
        var tvClear = findViewById<View>(R.id.tvClear) as TextView
        var tvZero = findViewById<View>(R.id.tvZero) as TextView
        var tvPlus = findViewById<View>(R.id.tvPlus) as TextView
        var tvMinus = findViewById<View>(R.id.tvMinus) as TextView
        var tvMul = findViewById<View>(R.id.tvMul) as TextView
        var tvDivide = findViewById<View>(R.id.tvDivide) as TextView
        var tvOpen = findViewById<View>(R.id.tvOpen) as TextView
        var tvClose = findViewById<View>(R.id.tvClose) as TextView
        var tvExpression = findViewById<View>(R.id.tvExpression) as TextView
        var tvEquals = findViewById<View>(R.id.tvEquals) as TextView
        var tvResult = findViewById<View>(R.id.tvResult) as TextView



        tvOne.setOnClickListener { appendOnExpresstion("1", true) }
        tvTwo.setOnClickListener { appendOnExpresstion("2", true) }
        tvThree.setOnClickListener { appendOnExpresstion("3", true) }
        tvFour.setOnClickListener { appendOnExpresstion("4", true) }
        tvFive.setOnClickListener { appendOnExpresstion("5", true) }
        tvSix.setOnClickListener { appendOnExpresstion("6", true) }
        tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
        tvEight.setOnClickListener { appendOnExpresstion("8", true) }
        tvNine.setOnClickListener { appendOnExpresstion("9", true) }
        tvZero.setOnClickListener { appendOnExpresstion("0", true) }
        tvDot.setOnClickListener { appendOnExpresstion(".", true) }

        //Operators
        tvPlus.setOnClickListener { appendOnExpresstion("+", false) }
        tvMinus.setOnClickListener { appendOnExpresstion("-", false) }
        tvMul.setOnClickListener { appendOnExpresstion("*", false) }
        tvDivide.setOnClickListener { appendOnExpresstion("/", false) }
        tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
        tvClose.setOnClickListener { appendOnExpresstion(")", false) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener {
            try {

                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            }catch (e: Exception){
                Log.d("Exception", " message : " + e.message)
            }
        }

    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {
        var tvExpression = findViewById<View>(R.id.tvExpression) as TextView
        var tvResult = findViewById<View>(R.id.tvResult) as TextView

        if(tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }

        if (canClear) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}

