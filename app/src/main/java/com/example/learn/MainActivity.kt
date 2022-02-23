package com.example.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.learn.databinding.ActivityMainBinding
import java.lang.Exception
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private var flag = false //переменная для проверки на возможность ввода символов(+-/x)
    private var resultString = ""
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberIds = listOf(binding.zero, binding.one, binding.two, binding.three, binding.four, binding.five, binding.six, binding.seven, binding.eight, binding.nine)
        val actionsIds = listOf(binding.minus,binding.division,binding.divisionWithRemainder,binding.plus,binding.multiplication,binding.comma)
        //Обработка цифр и символов
        numberIds.forEach { btn-> btn.setOnClickListener { addNum(btn.text.toString()) } }
        actionsIds.forEach { btn-> btn.setOnClickListener { addAction(btn.text.toString()) } }

        // Обработка кнопки равно
        binding.equals.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.text.text.toString()).build()
                val ans = ex.evaluate()
                val ansLong = ans.toLong()
                if(ans ==ansLong.toDouble()){
                    binding.text.text = ansLong.toString()
                    resultString = binding.text.text.toString()
                }
                else{
                    binding.text.text = ans.toString()
                    resultString = binding.text.text.toString()
                }
            }
            catch (e:Exception){
                val toast = Toast.makeText(
                    this,
                    "Что-то не так! Попробуйте изменить ваше выражение", Toast.LENGTH_LONG)
                toast.show()
            }
        }

        //All clean
        binding.AC.setOnClickListener {
            binding.text.text = ""
            resultString = ""
        }

        //plus/minus
        binding.plusMinus.setOnClickListener {
            if(resultString.isNotEmpty()){
                val result = binding.text.text.toString().toDouble()
                binding.text.text = (-result).toString()
                resultString = binding.text.text.toString()}
        }
    }

    override fun onSaveInstanceState(outState:Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", resultString)
    }
    override fun onRestoreInstanceState(savedInstanceState:Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        binding.text.text  = savedInstanceState.getString("result")
        resultString = binding.text.text.toString()
    }

    private fun addNum(append: String){
        if(resultString ==""){
            resultString += append
            binding.text.text = ""
            binding.text.append(resultString)
            flag = true
        }
        else {
            resultString += append
            binding.text.text = ""
            binding.text.append(resultString)
            flag = true
        }
    }
    private fun addAction(append: String){
        if(resultString.isNotEmpty() && flag){
            resultString += append
            binding.text.text = ""
            binding.text.append(resultString)
            flag = false
        }
    }
}
