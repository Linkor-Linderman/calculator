package com.example.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.learn.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var myModel: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myModel = Model()

        val numberIds = binding.groupOfNumbers.referencedIds
        val actionsIds = binding.groupOfAction.referencedIds

        //Обработка цифр и символов
        numberIds.forEach { btn-> (findViewById<Button>(btn)).setOnClickListener {
            myModel!!.addNum((findViewById<Button>(btn)).text.toString())
            binding.text.text = ""
            binding.text.append(myModel!!.resultString) }}

        actionsIds.forEach { btn-> (findViewById<Button>(btn)).setOnClickListener {
            myModel!!.addAction((findViewById<Button>(btn)).text.toString())
            binding.text.text = ""
            binding.text.append(myModel!!.resultString)
        } }

        // Обработка кнопки равно
        binding.equals.setOnClickListener {
            try {
                val answer = myModel!!.calculate(binding.text.text.toString())
                binding.text.text = answer
                myModel!!.resultString = binding.text.text.toString()
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
            myModel!!.resultString = ""
        }

        //plus/minus
        binding.plusMinus.setOnClickListener {
            binding.text.text = myModel!!.signChange( binding.text.text.toString())
            myModel!!.resultString = binding.text.text.toString()
        }
    }

    override fun onSaveInstanceState(outState:Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", myModel!!.resultString)
    }
    override fun onRestoreInstanceState(savedInstanceState:Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        binding.text.text  = savedInstanceState.getString("result")
        myModel!!.resultString = binding.text.text.toString()
    }
}