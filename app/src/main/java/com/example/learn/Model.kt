package com.example.learn
import net.objecthunter.exp4j.ExpressionBuilder

class Model {

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
         if (resultString.isNotEmpty()) {
             return (-resultString.toInt()).toString()
         }
         return resultString
     }
}