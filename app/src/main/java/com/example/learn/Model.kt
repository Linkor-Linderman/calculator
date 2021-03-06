package com.example.learn
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class Model {
    private var flag = false //переменная для проверки на возможность ввода символов(+-/x)
    var resultString = ""

     fun calculate(resultString:String):String{
        val expression = ExpressionBuilder(resultString).build()
        val answer = expression.evaluate()
        val answerLong = answer.toLong()

        return if (answer==answerLong.toDouble()){
            answerLong.toString()
        } else {
            answer.toString()
        }
    }
     fun signChange(resultString:String):String {
         try {
         if (resultString.isNotEmpty()) {
             return (-resultString.toInt()).toString()
         }
         return resultString
         }
         catch (e: Exception){
             return (-resultString.toDouble()).toString()
         }
     }
    fun addNum(append: String):String{
        return if(resultString.isEmpty()){
            resultString += append
            flag = true
            resultString
        } else{
            resultString += append
            flag = true
            resultString
        }
    }
    fun addAction(append: String):String{
        if(resultString.isNotEmpty() && flag){
            resultString += append
            flag = false
            return resultString
        }
        return ""
    }
    fun clean():String{
        resultString = ""
        return resultString
    }
}