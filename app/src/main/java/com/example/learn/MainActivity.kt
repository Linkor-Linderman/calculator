package com.example.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.Arrays.toString

class MainActivity : AppCompatActivity() {
    private var flag = false //переменная для проверки на возможность ввода символов(+-/x)
    private var resultString = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberIds = listOf(zero, one, two, three, four, five, six, seven, eight, nine)
        val actionsIds = listOf(minus,division,divisionWithRemainder,plus,multiplication,comma)
        //Обработка цифр и символов
        numberIds.forEach { btn-> btn.setOnClickListener { addNumOrAction(btn.text.toString(), true) } }
        actionsIds.forEach { btn-> btn.setOnClickListener { addNumOrAction(btn.text.toString(), false) } }

        // Обработка кнопки равно
        equals.setOnClickListener {
            try {
                val ex = ExpressionBuilder(text.text.toString()).build()
                val ans = ex.evaluate()
                var ansLong = ans.toLong()
                if(ans ==ansLong.toDouble()){
                    text.text = ansLong.toString()
                    resultString = text.text.toString()
                }
                else{
                    text.text = ans.toString()
                    resultString = text.text.toString()
                }
            }
            catch (e:Exception){
                val toast = Toast.makeText(getApplicationContext(),
                    "Что-то не так! Попробуйте изменить ваше выражение", Toast.LENGTH_LONG)
                toast.show()
            }
        }

        //All clean
        AC.setOnClickListener {
            text.text = ""
            resultString = ""
        }
        //plus/minus
        plusMinus.setOnClickListener {
            if(resultString.isNotEmpty()){
            var result = text.text.toString().toDouble()
            text.text = (-result).toString()
            resultString = text.text.toString()}
        }
    }

    override fun onSaveInstanceState(outState:Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", resultString)
    }
    override fun onRestoreInstanceState(savedInstanceState:Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        text.text  = savedInstanceState.getString("result")
        resultString = text.text.toString()
    }

    private fun addNumOrAction(append: String, isNum:Boolean){
        if(resultString =="" && isNum){
            resultString += append
            text.text = ""
            text.append(resultString)
            flag = true
        }
        else if(isNum){
            resultString += append
            text.text = ""
            text.append(resultString)
            flag = true
        }
        else if(resultString.isNotEmpty() && flag && !isNum){
            resultString += append
            text.text = ""
            text.append(resultString)
            flag = false
        }
    }
}
