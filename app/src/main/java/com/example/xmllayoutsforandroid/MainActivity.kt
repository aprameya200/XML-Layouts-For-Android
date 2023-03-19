//package com.example.xmllayoutsforandroid
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//
///**
// * TODO
// * MainActivity class
// */
//class MainActivity : AppCompatActivity() {
//
//
//    /**
//     * TODO
//     *OnCreate method that runs when app is created
//     *
//     * @param savedInstanceState
//     */
//    @SuppressLint("SuspiciousIndentation")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        //total cost of service
//        val cost_of_service : EditText = findViewById(R.id.cost_of_service) as EditText
//
//
//        //toggle switch
//        val round_up_switch : Switch = findViewById(R.id.round_up_switch) as Switch
//
//        //calculate button
//        val calculate_button :Button = findViewById(R.id.calculate_button) as Button
//
//        //selection value for radio button
//        var radioSelection: Int = 0
//
//        //sel3eceted button from radio group
//        var selectedBtn: RadioGroup = findViewById(R.id.tip_options) as RadioGroup
//
//        //boolean value for round off switch
//        var isPressed: Boolean = true
//
//        /**
//         * if round off switch is pressed
//         */
//        round_up_switch.setOnCheckedChangeListener { compoundButton, b ->
//            isPressed = b
//        }
//
//        //variable to save tip amount
//        val tipAmount = findViewById(R.id.tipAmount) as TextView
//
//        /**
//         * setOnCheckedChangeListener that runs when one radio button is selected
//         */
//        selectedBtn.setOnCheckedChangeListener { group, checkedId ->
//
//            //getting checked radio button
//            val radioButton = findViewById<RadioButton>(checkedId)
//
//            //variable to save string present in 3 radio buttons
//            val textBtn1 : String = radioButton.text.toString();
//
//            if (textBtn1 == "Amazing (20%)"){
//                radioSelection = 20
//            }
//            else if (textBtn1 == "Good (18%)"){
//                radioSelection = 18
//            }
//            else if (textBtn1 == "Okay (15%)" ){
//                radioSelection = 15
//            }
//
//        }
//
//        /**
//         * setOnCheckedChangeListener that runs when calculate button is pressed
//         */
//        calculate_button.setOnClickListener {
//
//        var textMessage: String = "Please Enter total amount"
//
//
//            // handling empty string and unselected radio button errors
//            try {
//                val totalAmtString: String = cost_of_service.text.toString()
//                val finalValue: Double = totalAmtString.toDouble()
//
//                if (radioSelection == 0){
//                    textMessage = "Please select tip percentage"
//                    throw NumberFormatException()
//                }
//
//                //calculate and return the tip amount
//                var tipAmt: Double = onCalculate(finalValue, radioSelection, isPressed)
//
//                tipAmount.text = tipAmt.toString()
//            }catch (e : NumberFormatException){
//
//                //error message
//                Toast.makeText(applicationContext,textMessage,Toast.LENGTH_LONG).show()
//                tipAmount.text = "0"
//            }
//
//        }
//
//
//    }
//
//    /**
//     * TODO
//     *
//     * onCalculate to calculate the total tip amount
//     *
//     * @param total cost of service
//     * @param percentage tip percentage
//     * @param roundOff boolean value to specify if tip amount must be rounded off
//     * @return
//     */
//    fun onCalculate(total: Double,percentage: Int,roundOff: Boolean): Double {
//        val tipAmt : Double = total * percentage/100
//        var returnAmount : Double
//
//        if (roundOff){
//            returnAmount = Math.round(tipAmt * 1.0) / 1.0
//
//        }else{
//            returnAmount = tipAmt
//        }
//
//        return returnAmount
//    }
//}


package com.example.xmllayoutsforandroid

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.xmllayoutsforandroid.R


/**
 * TODO
 *
 * MainActivity class
 */
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var conversionUnits: Array<String> = arrayOf("Cup", "Milliliter", "Litre","Gram","Kilogram")

    var SelectedString1: String = ""
    var SelectedString2: String = ""

    var num : Int = 0

    /**
     * TODO
     *
     * onCreate method of app
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val unitsOne : Spinner = findViewById(R.id.spinner2)
        val unitsTwo : Spinner = findViewById(R.id.spinner)

        /**
         * setOnItemSelectedListener for when single item from spinner is selected
         */
        unitsOne.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                SelectedString1 = conversionUnits[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        })
        unitsTwo.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                SelectedString2 = conversionUnits[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        })



        val addToSpinner: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            conversionUnits)

        addToSpinner.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)


        unitsOne.adapter = addToSpinner
        unitsTwo.adapter = addToSpinner

        val buttonConvert: Button = findViewById(R.id.button)

        val Show: TextView = findViewById(R.id.textView4)


        val boxString: EditText = findViewById(R.id.amount)


        /**
         * btn to click to convert units
         */
        buttonConvert.setOnClickListener({
            val amount = boxString.text.toString().toDouble()
            Show.text = convertUnits(SelectedString1,SelectedString2,amount);
        })

    }

    /**
     * TODO
     *
     * method that is used to convert between units
     *
     * @param unit1
     * @param unit2
     * @param value
     * @return converted value as concatenated string for appropriate output
     */
    fun convertUnits(unit1: String,unit2: String,value: Double): String {

        var convertedValue: Double = 0.0


        convertedValue =  Math.round(fromUnits(unit1,unit2,value) * 100.0) / 100.0

        return "${value.toString()} $unit1 is equal to ${convertedValue.toString()} $unit2"


    }

//    "Gram","Kilogram","Pound","Ounce"

    /**
     * TODO
     *
     * Method containing all the if-else logic for unit conversion
     *
     * @param unit1
     * @param unit2
     * @param value
     * @return converted value
     */
    private fun fromUnits(unit1: String,unit2: String,value: Double): Double {

        var newValue : Double = 0.0

        if (unit1 == "Cup"){
            if (unit2 == "Milliliter"){
                newValue = value * 236.6
            }else if (unit2 == "Litre"){
                newValue = value /  4.227
            }else if (unit2 == "Gram"){
                newValue = value *  240
            }
            else if (unit2 == "Kilogram"){
                newValue = value *  0.2
            }
            else{
                newValue = value
            }
        }

        else if (unit1 == "Milliliter"){
            if (unit2 == "Cup"){
                newValue = value / 236.6
            }else if (unit2 == "Litre"){
                newValue = value /  1000
            }else if (unit2 == "Gram"){
                newValue = value
            }
            else if (unit2 == "Kilogram"){
                newValue = value /  1000
            }else{
                newValue = value
            }
        }


        else if (unit1 == "Litre"){
            if (unit2 == "Cup"){
                newValue = value * 4.227
            }else if (unit2 == "Milliliter"){
                newValue = value *  1000
            }else if (unit2 == "Gram"){
                newValue = value * 1000
            }
            else if (unit2 == "Kilogram"){
                newValue = value
            }else{
                newValue = value
            }
        }

        else if (unit1 == "Gram"){
            if (unit2 == "Cup"){
                newValue = value / 240
            }else if (unit2 == "Milliliter"){
                newValue = value
            }else if (unit2 == "Litre"){
                newValue = value * 0.001
            }
            else if (unit2 == "Kilogram"){
                newValue = value * 0.001
            }else{
                newValue = value
            }
        }

        else if (unit1 == "Kilogram"){
            if (unit2 == "Cup"){
                newValue = value * 4.23
            }else if (unit2 == "Milliliter"){
                newValue = value *  1000
            }else if (unit2 == "Gram"){
                newValue = value * 1000
            }
            else if (unit2 == "Litre"){
                newValue = value
            }else{
                newValue = value
            }
        }


        return newValue
    }


    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View, position: Int,
                                id: Long) {


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}



}