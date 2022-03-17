package com.example.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.learn.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var flag = false //переменная для проверки на возможность ввода символов(+-/x)
    private var resultString = ""
    private lateinit var binding: ActivityMainBinding
    var myModel: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myModel = Model()

        val numberIds = binding.groupOfNumbers.getReferencedIds()
        val actionsIds = binding.groupOfAction.getReferencedIds()

        //Обработка цифр и символов
        numberIds.forEach { btn-> (findViewById<Button>(btn)).setOnClickListener { addNum((findViewById<Button>(btn)).text.toString()) } }
        actionsIds.forEach { btn-> (findViewById<Button>(btn)).setOnClickListener { addAction((findViewById<Button>(btn)).text.toString()) } }

        // Обработка кнопки равно
        binding.equals.setOnClickListener {
            try {
                val answer = myModel!!.calculate(binding.text.text.toString())
                binding.text.text = answer
                resultString = binding.text.text.toString()
            }
            catch (e:Exception){
                val warningMessage: String = getString(R.string.warning)
                val toast = Toast.makeText(
                    this, warningMessage, Toast.LENGTH_LONG)
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
            binding.text.text = myModel!!.signChange( binding.text.text.toString())
            resultString = binding.text.text.toString()
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
        if(resultString.isEmpty()){
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
