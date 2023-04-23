package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {
     companion object{
         private const val METRIC_UNITS_VIEW ="METRIC_UNIT_VIEW"
         private const val IND_UNITS_VIEW= "IND_UNITS_VIEW"
     }
     private var currentVisibleView:String= METRIC_UNITS_VIEW // A variable to hold a value to make a selected view visible
    var binding:ActivityBmiBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.bmiToolbar)

        if (supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }
        binding?.bmiToolbar?.setNavigationOnClickListener{
           onBackPressed()
        }
        makeVisibleMetricUnitsView()
        binding?.rgUnits?.setOnCheckedChangeListener{ _, checkedId:Int ->
            if (checkedId==R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleIndMetricUnitsView()
            }
        }
        binding?.btnCalculateUnits?.setOnClickListener{
          calculateUsUnits()
        }

    }
    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.metricUnitWeight?.visibility=View.VISIBLE
        binding?.metricUnitHeight?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.GONE
        binding?.usMetricUnitWeight?.visibility=View.GONE

        binding?.unitWeight?.text!!.clear()
        binding?.unitHeight?.text!!.clear()
        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
    }
    private fun makeVisibleIndMetricUnitsView(){
        currentVisibleView= IND_UNITS_VIEW
        binding?.metricUnitHeight?.visibility=View.INVISIBLE
        binding?.metricUnitWeight?.visibility=View.INVISIBLE
        binding?.usMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.VISIBLE

        binding?.usUnitWeight?.text!!.clear()
        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
    }




    private fun displayBmiResult(bmi:Float){
        val bmiLabel:String
        val bmiDescription:String

        if (bmi.compareTo(15f)<=0){
            bmiLabel = "Very severely underweight"
            bmiDescription="Oops! You really need to take better care of yourself! Eat more!"
        }else if (bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmiLabel="Severely underweight"
            bmiDescription="Oops! You really need to take better care of yourself! Eat more! "
        }else if (bmi.compareTo(16f)>0 && bmi.compareTo(18f)<=0){
            bmiLabel="Underweight"
            bmiDescription="Oops! You really need to take better care of yourself! Eat more! "
        }
        else if (bmi.compareTo(18f)>0 && bmi.compareTo(25f)<=0){
            bmiLabel="Normal"
            bmiDescription="Congratulation! you are in a good shape! "
        } else if (bmi.compareTo(25f)>0 && bmi.compareTo(30f)<=0){
            bmiLabel="Overweight"
            bmiDescription="Oops! You really need to take care of your yourself! Workout hard "
        } else if (bmi.compareTo(30f)>0 && bmi.compareTo(35f)<=0){
            bmiLabel="Obese Class | (Moderately obese)"
            bmiDescription="Oops! You really need to take care of your yourself! Workout hard "
        }else if (bmi.compareTo(35f)>0 && bmi.compareTo(40f)<=0){
            bmiLabel="Obese Class || (Severely obese)"
            bmiDescription="OMG! You are in a very dangerous condition! Act now!"
        }else{
            bmiLabel="Obese Class ||| (Very Severely obese)"
            bmiDescription="OMG! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBMIResult?.visibility= View.VISIBLE
        binding?.tvBMIValue?.text=bmiValue
        binding?.tvBMIType?.text=bmiLabel
        binding?.tvBMIDescription?.text=bmiDescription

    }

    private fun validateMetricUnits():Boolean{
        var isValid=true
        if (binding?.unitWeight?.text.toString().isEmpty()){
            isValid=false
        }else if (binding?.unitHeight?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid

    }

    private fun calculateUsUnits(){
        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val heightValue: Float = binding?.unitHeight?.text.toString().toFloat() / 100
                val weightValue: Float = binding?.unitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue * heightValue)
                displayBmiResult(bmi)

            } else {
                Toast.makeText(this@BmiActivity, "Please enter valid values.", Toast.LENGTH_SHORT)
                    .show()
            }
        }else{
            if (validateUSUnits()){
                val usUnitHeightValueFeet:String= binding?.etUsMetricUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch:String= binding?.etUsMetricUnitHeightInch?.text.toString()
                val usUnitWeightValue:Float=binding?.usUnitWeight?.text.toString().toFloat()

                val heightValue=usUnitHeightValueInch.toFloat()+usUnitHeightValueFeet.toFloat() *12
                val bmi = 703*(usUnitWeightValue/(heightValue*heightValue))
                displayBmiResult(bmi)
            }else{
                Toast.makeText(this@BmiActivity, "Please enter valid values.", Toast.LENGTH_SHORT)
                    .show()

            }
        }

    }
    private fun validateUSUnits():Boolean{
        var isValid=true
        if (binding?.usUnitWeight?.text.toString().isEmpty()){
            isValid=false
        }else if (binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()){
            isValid=false
        }
        else if (binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid

    }
}