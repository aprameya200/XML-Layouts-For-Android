package com.example.xmllayoutsforandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * TODO
 * MainActivity class
 */
class MainActivity : AppCompatActivity() {


    /**
     * TODO
     *OnCreate method that runs when app is created
     *
     * @param savedInstanceState
     */
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //total cost of service
        val cost_of_service : EditText = findViewById(R.id.cost_of_service) as EditText


        //toggle switch
        val round_up_switch : Switch = findViewById(R.id.round_up_switch) as Switch

        //calculate button
        val calculate_button :Button = findViewById(R.id.calculate_button) as Button

        //selection value for radio button
        var radioSelection: Int = 0

        //sel3eceted button from radio group
        var selectedBtn: RadioGroup = findViewById(R.id.tip_options) as RadioGroup

        //boolean value for round off switch
        var isPressed: Boolean = true

        /**
         * if round off switch is pressed
         */
        round_up_switch.setOnCheckedChangeListener { compoundButton, b ->
            isPressed = b
        }

        //variable to save tip amount
        val tipAmount = findViewById(R.id.tipAmount) as TextView

        /**
         * setOnCheckedChangeListener that runs when one radio button is selected
         */
        selectedBtn.setOnCheckedChangeListener { group, checkedId ->

            //getting checked radio button
            val radioButton = findViewById<RadioButton>(checkedId)

            //variable to save string present in 3 radio buttons
            val textBtn1 : String = radioButton.text.toString();

            if (textBtn1 == "Amazing (20%)"){
                radioSelection = 20
            }
            else if (textBtn1 == "Good (18%)"){
                radioSelection = 18
            }
            else if (textBtn1 == "Okay (15%)" ){
                radioSelection = 15
            }

        }

        /**
         * setOnCheckedChangeListener that runs when calculate button is pressed
         */
        calculate_button.setOnClickListener {

        var textMessage: String = "Please Enter total amount"


            // handling empty string and unselected radio button errors
            try {
                val totalAmtString: String = cost_of_service.text.toString()
                val finalValue: Double = totalAmtString.toDouble()

                if (radioSelection == 0){
                    textMessage = "Please select tip percentage"
                    throw NumberFormatException()
                }

                //calculate and return the tip amount
                var tipAmt: Double = onCalculate(finalValue, radioSelection, isPressed)

                tipAmount.text = tipAmt.toString()
            }catch (e : NumberFormatException){

                //error message
                Toast.makeText(applicationContext,textMessage,Toast.LENGTH_LONG).show()
                tipAmount.text = "0"
            }

        }


    }

    /**
     * TODO
     *
     * onCalculate to calculate the total tip amount
     *
     * @param total cost of service
     * @param percentage tip percentage
     * @param roundOff boolean value to specify if tip amount must be rounded off
     * @return
     */
    fun onCalculate(total: Double,percentage: Int,roundOff: Boolean): Double {
        val tipAmt : Double = total * percentage/100
        var returnAmount : Double

        if (roundOff){
            returnAmount = Math.round(tipAmt * 1.0) / 1.0

        }else{
            returnAmount = tipAmt
        }

        return returnAmount
    }
}