package skilllotto.com.finvest.view.Fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle;
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*
import skilllotto.com.aepsindifi.controller.DatePickerController
import skilllotto.com.finvest.Model.EmiData
import skilllotto.com.finvest.R
import skilllotto.com.finvest.Utils.CustomDatePicker
import skilllotto.com.finvest.Utils.ProjectUtil
import skilllotto.com.finvest.view.MainActivity


class EmiFrg : Fragment(), DatePickerController {
    override fun getSelectedDate(type: Int, date: String) {
        if (type == CASE_DATE) {
            due_date?.setText(date)
        }
        if (type == DUE_DATE) {
            case_date?.setText(date)
        }
    }

    var due_date: EditText? = null
    var case_date: EditText? = null
    var total_cost: EditText? = null
    var total_emi: EditText? = null
    var installment_amount: EditText? = null
    var cheque_no: EditText? = null
    var cheque_layout: LinearLayout? = null


    var pay_type: RadioGroup? = null

    var cheque_enable = false;
    val CASE_DATE = 101;
    val DUE_DATE = 102;
    private var loadItems: MainActivity? = null
    var submit: Button? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.emi_frg, container, false)
        loadItems = context as MainActivity
        loadItems!!.invisiableFloatingButton();

        due_date = view?.findViewById(R.id.due_date) as EditText
        case_date = view.findViewById(R.id.case_date) as EditText
        submit = view.findViewById(R.id.emi_genreate) as Button
        total_cost = view?.findViewById(R.id.total_cost) as EditText
        total_emi = view.findViewById(R.id.total_emi) as EditText
        installment_amount = view?.findViewById(R.id.installment_amount) as EditText
        cheque_no = view?.findViewById(R.id.cheque_no) as EditText
        pay_type = view?.findViewById(R.id.pay_type) as RadioGroup
        cheque_layout = view?.findViewById(R.id.cheque_layout) as LinearLayout



        total_cost?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (total_cost?.getText().toString().equals("") || total_emi?.getText().toString().equals("")) {
                    installment_amount?.setText("" + ProjectUtil.getDecimalFormated(0.0))
                } else {
                    var install_amount = 0.0
                    install_amount = java.lang.Double.parseDouble(total_cost?.getText().toString()) / java.lang.Double.parseDouble(total_emi?.getText().toString())
                    installment_amount?.setText("" + ProjectUtil.getDecimalFormated(install_amount))
                }

            }
        })


        total_emi?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (total_cost?.getText().toString().equals("") || total_emi?.getText().toString().equals("")) {
//                    var install_amount = 0.0
//                    install_amount = java.lang.Double.parseDouble(total_cost?.getText().toString()) / 1
//                    installment_amount?.setText("" + ProjectUtil.getDecimalFormated(install_amount))
                    installment_amount?.setText("" + ProjectUtil.getDecimalFormated(0.0))
                } else {
                    var install_amount = 0.0
                    install_amount = java.lang.Double.parseDouble(total_cost?.getText().toString()) / java.lang.Double.parseDouble(total_emi?.getText().toString())
                    installment_amount?.setText("" + ProjectUtil.getDecimalFormated(install_amount))
                }
            }
        })




        due_date!!.setOnClickListener { view ->


            val customDatePicker = CustomDatePicker.getCustomDatePicker(CASE_DATE)
            customDatePicker.show(getChildFragmentManager(), "datepick")
        }

        case_date!!.setOnClickListener { view ->
            val customDatePicker = CustomDatePicker.getCustomDatePicker(DUE_DATE)
            customDatePicker.show(getChildFragmentManager(), "datepick")
        }

        pay_type?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.Cash) {
                cheque_enable = true
                cheque_layout?.visibility = View.GONE
            } else {
                cheque_enable = false
                cheque_layout?.visibility = View.VISIBLE
            }
        })




        submit!!.setOnClickListener { view ->

            if (total_emi?.getText().toString().equals("") || Integer.parseInt(total_emi?.getText().toString()) < 0) {
                ProjectUtil.customToast(activity, "Invalid EMI selected !")
            } else if (due_date?.getText().toString().equals("")) {
                ProjectUtil.customToast(activity, "Select due-date first ?")
            } else if (case_date?.getText().toString().equals("")) {
                ProjectUtil.customToast(activity, "Select case-date first ?")
            } else if (total_emi?.getText().toString().equals("")) {
                ProjectUtil.customToast(activity, "Invalid EMI selected !")
            } else if (total_cost?.getText().toString().equals("") || Integer.parseInt(total_cost?.getText().toString()) < 0) {
                ProjectUtil.customToast(activity, "Invalid total amount!")
            } else if (total_cost?.getText().toString().equals("") || Integer.parseInt(total_cost?.getText().toString()) < 0) {
                ProjectUtil.customToast(activity, "Select any payment mode")
            } else if (cheque_enable == true && cheque_no?.getText().toString().length < 5) {
                ProjectUtil.customToast(activity, "Enter valid cheque No.")
            } else {
                loadItems!!.emi_no(0, generateData());
            }


        }


        return view;
    }

    private fun generateData(): EmiData {
        var user: EmiData = EmiData(case_date?.getText().toString(), total_cost?.getText().toString(), total_emi?.getText().toString(),
                installment_amount?.getText().toString(), due_date?.getText().toString(), "CASH")

        return user
    }

}


