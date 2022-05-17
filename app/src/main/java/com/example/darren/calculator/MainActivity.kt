package com.example.darren.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var tvInput: TextView? = null
    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        tvInput?.text = ""
        lastNumeric = false
        lastDot = true
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){

        tvInput?.text?.let {
            if(it.toString() == "" && (view as Button).text == "-" ){
                tvInput?.append((view as Button).text)
            }
             if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }


    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        } else{
            value.contains("+")
                    ||value.contains("-")
                    ||value.contains("*")
                    ||value.contains("/")
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try{
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val spiltValue = tvValue.split("-")
                    var firstOperand = spiltValue[0].toDouble()
                    var secondOperand = spiltValue[1].toDouble()

                    if(prefix == "-"){
                        firstOperand = 0 - firstOperand
                    }

                    var calculateResult = (firstOperand - secondOperand).toString()

                    tvInput?.text = removeDotZero(calculateResult)
                }

                if (tvValue.contains("+")){
                    val spiltValue = tvValue.split("+")
                    var firstOperand = spiltValue[0].toDouble()
                    var secondOperand = spiltValue[1].toDouble()

                    if(prefix == "-"){
                        firstOperand = 0 - firstOperand
                    }

                    var calculateResult = (firstOperand + secondOperand).toString()

                    tvInput?.text = removeDotZero(calculateResult)
                }

                if (tvValue.contains("/")){
                    val spiltValue = tvValue.split("/")
                    var firstOperand = spiltValue[0].toDouble()
                    var secondOperand = spiltValue[1].toDouble()

                    if(prefix == "-"){
                        firstOperand = 0 - firstOperand
                    }

                    var calculateResult = (firstOperand / secondOperand).toString()

                    tvInput?.text = removeDotZero(calculateResult)
                }

                if (tvValue.contains("*")){
                    val spiltValue = tvValue.split("*")
                    var firstOperand = spiltValue[0].toDouble()
                    var secondOperand = spiltValue[1].toDouble()

                    if(prefix == "-"){
                        firstOperand = 0 - firstOperand
                    }

                    var calculateResult = (firstOperand * secondOperand).toString()

                    tvInput?.text = removeDotZero(calculateResult)
                }


            } catch (e: ArithmeticException){

            }

        }
    }

    private fun removeDotZero(str: String): String{
        var myStr = str
        if(myStr.endsWith(".0")){
            myStr = str.substring(0, str.length-2)
        }
        return myStr
    }
}