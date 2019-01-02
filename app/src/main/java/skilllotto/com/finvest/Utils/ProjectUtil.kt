package skilllotto.com.finvest.Utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import skilllotto.com.aepsindifi.Util.VerhoeffAlgorithm
import skilllotto.com.finvest.R
import java.util.regex.Pattern
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*


object ProjectUtil {

    fun validateAadharNumber(aadharNumber: String): Boolean {
        val aadharPattern = Pattern.compile("\\d{12}")
        var isValidAadhar = aadharPattern.matcher(aadharNumber).matches()
        if (isValidAadhar) {
            isValidAadhar = VerhoeffAlgorithm.validateVerhoeff(aadharNumber)
        }
        return isValidAadhar
    }

    fun customToast(context: Context, message: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_tost, null)
        val txt_msg = view.findViewById<View>(R.id.txt_message) as TextView
        txt_msg.text = message
        //Creating the Toast object
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.view = view//setting the view of custom toast simple_spinner
        toast.show()
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        //activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        inputMethodManager!!.toggleSoftInputFromWindow(view.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0)
    }

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        return simpleDateFormat.format(calendar.getTime()).toString()
    }

    fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("hh:mm")
        return simpleDateFormat.format(calendar.getTime()).toString()
    }

    fun getRoundOff(data: Double): String {

        return Math.round(data).toString()
    }

    fun getDecimalFormated(data: Double): String {
        return String.format("%.2f", data)
    }
}