package skilllotto.com.finvest.view.Fragment

import android.os.Bundle;
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.customer_regitration.*
import skilllotto.com.finvest.R

import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.app_bar_main.*
import skilllotto.com.aepsindifi.controller.CustomerListner

import skilllotto.com.aepsindifi.controller.DatePickerController
import skilllotto.com.finvest.Model.CustomerInformationModel
import skilllotto.com.finvest.Model.GranterInormationModel
import skilllotto.com.finvest.Utils.ProjectUtil
import skilllotto.com.finvest.view.MainActivity
import skilllotto.com.finvest.Utils.CustomDatePicker
import skilllotto.com.finvest.Utils.NativeHelper
import skilllotto.com.finvest.presenter.CustGranterPresenterimpl
import skilllotto.com.finvest.presenter.MainPresenter


class CustomerInformation : Fragment(), DatePickerController, CustomerListner {

    override fun validationMessage(message: String) {
        ProjectUtil.customToast(activity, message)
    }

    override fun showLoadingLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSuccess(`object`: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFailure(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    override fun customerdetail(): CustomerInformationModel {
////        val customerDetail = CustomerInformationModel()
////        return null
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun fragmentdetail(): GranterInormationModel {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }


    var customer_layout: LinearLayout? = null
    var guranter_layout: LinearLayout? = null

    val TXT_DATE = 101;
    val GTXT_DATE = 102;
    private var presenter: MainPresenter<*>? = null
    var dob: EditText? = null
    var gdate_of_birth: EditText? = null
    var aadhar_no: EditText? = null
    var customer_information: TextView? = null
    var gurntor_informationg: TextView? = null
    var customer_namr: EditText? = null
    var cust_fathername: EditText? = null
    var cust_mobile_no: EditText? = null
    var cust_address: EditText? = null
    var district: EditText? = null
    var cust_city: EditText? = null
    var cust_pincode: EditText? = null
    var loan_amount: EditText? = null
    var loan_duration: EditText? = null


    var garanter_name: EditText? = null
    var garanter_fname: EditText? = null
    var granter_mobileno: EditText? = null
    var gaadhar_no: EditText? = null
    var grant_address: EditText? = null
    var granter_district: EditText? = null
    var grant_city: EditText? = null
    var grantre_pincode: EditText? = null


    var select_gender: Spinner? = null
    var gselect_gender: Spinner? = null
    var address_type: Spinner? = null
    var gaddress_type: Spinner? = null
    var gstate_type: Spinner? = null
    var state_type: Spinner? = null
    var submit: Button? = null


    internal lateinit var fab_open: Animation
    internal lateinit var fab_close: Animation
    internal lateinit var rotate_forward: Animation
    internal lateinit var rotate_backward: Animation
    private var isFirstOpen: Boolean? = false
    private var isSecondOpen: Boolean? = false

    private var loadItems: MainActivity? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.customer_regitration, container, false)
        ButterKnife.bind(this, view!!);
        ProjectUtil.hideKeyboard(activity);
        val scroll_view = view.findViewById(R.id.scroll_view) as ScrollView
        loadItems = context as MainActivity
        gurntor_informationg?.clearAnimation()
        customer_information?.clearAnimation()

        fab_open = AnimationUtils.loadAnimation(activity, R.anim.slide_up)
        fab_close = AnimationUtils.loadAnimation(activity, R.anim.slide_down)
        rotate_forward = AnimationUtils.loadAnimation(activity, R.anim.slide_down)
        rotate_backward = AnimationUtils.loadAnimation(activity, R.anim.slide_up)

        customer_layout = view.findViewById(R.id.customer_layout) as LinearLayout
        guranter_layout = view.findViewById(R.id.guranter_layout) as LinearLayout

        select_gender = view.findViewById(R.id.select_gender) as Spinner
        gselect_gender = view.findViewById(R.id.gselect_gender) as Spinner
        address_type = view.findViewById(R.id.address_type) as Spinner
        gaddress_type = view.findViewById(R.id.gaddress_type) as Spinner
        gstate_type = view.findViewById(R.id.gstate_type) as Spinner
        state_type = view.findViewById(R.id.state_type) as Spinner

        loan_duration = view.findViewById(R.id.loan_duration) as EditText
        cust_fathername = view.findViewById(R.id.cust_fathername) as EditText
        customer_namr = view.findViewById(R.id.customer_namr) as EditText
        cust_mobile_no = view.findViewById(R.id.cust_mobile_no) as EditText
        loan_amount = view.findViewById(R.id.loan_amount) as EditText
        cust_address = view.findViewById(R.id.customer_addre) as EditText
        district = view.findViewById(R.id.district) as EditText
        cust_city = view.findViewById(R.id.customer_city) as EditText
        cust_pincode = view.findViewById(R.id.cust_pincode) as EditText
        dob = view.findViewById(R.id.date_of_birth) as EditText
        gdate_of_birth = view.findViewById(R.id.gdate_of_birth) as EditText
        aadhar_no = view.findViewById(R.id.aadhar_no) as EditText
        customer_information = view.findViewById(R.id.customer_information) as TextView
        gurntor_informationg = view.findViewById(R.id.gurntor_information) as TextView
        submit = view.findViewById(R.id.submit) as Button




        garanter_name = view.findViewById(R.id.garanter_name) as EditText
        garanter_fname = view.findViewById(R.id.garanter_fname) as EditText
        granter_mobileno = view.findViewById(R.id.granter_mobileno) as EditText
        gaadhar_no = view.findViewById(R.id.gaadhar_no) as EditText
        grant_address = view.findViewById(R.id.granter_address) as EditText
        granter_district = view.findViewById(R.id.granter_district) as EditText
        grant_city = view.findViewById(R.id.granter_city) as EditText
        grantre_pincode = view.findViewById(R.id.grantre_pincode) as EditText


        dob?.setText(ProjectUtil.getCurrentDate());
        gdate_of_birth?.setText(ProjectUtil.getCurrentDate());

        dob!!.setOnClickListener { view ->
            val customDatePicker = CustomDatePicker.getCustomDatePicker(TXT_DATE)
            customDatePicker.show(getChildFragmentManager(), "datepick")
        }

        gdate_of_birth!!.setOnClickListener { view ->
            val customDatePicker = CustomDatePicker.getCustomDatePicker(TXT_DATE)
            customDatePicker.show(getChildFragmentManager(), "datepick")
        }


        submit!!.setOnClickListener { view ->
            if (NativeHelper.isConnectionAvailable(activity)) run {
//
//                presenter = CustGranterPresenterimpl(this@LogIn)
//                presenter?.init()
            }

        }



        customer_information!!.setOnClickListener { view -> animateCustomer(); }
        gurntor_informationg!!.setOnClickListener { view -> animateGranter(); }


        gselect_gender?.adapter = ArrayAdapter(activity, R.layout.drop_down, resources.getStringArray(R.array.gender))
        select_gender?.adapter = ArrayAdapter(activity, R.layout.drop_down, resources.getStringArray(R.array.gender))
        address_type?.adapter = ArrayAdapter(activity, R.layout.drop_down, resources.getStringArray(R.array.address_type))
        gaddress_type?.adapter = ArrayAdapter(activity, R.layout.drop_down, resources.getStringArray(R.array.address_type))
        state_type?.adapter = ArrayAdapter(activity, R.layout.drop_down, resources.getStringArray(R.array.states))
        gstate_type?.adapter = ArrayAdapter(activity, R.layout.drop_down, resources.getStringArray(R.array.states))

//        if (ProjectUtil.validateAadharNumber(aadhar_no!!.getText().toString()) == false) {
//            ProjectUtil.customToast(activity, "Please Enter Valid Aadhar Number")
//        }

        scroll_view.getViewTreeObserver().addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            val scrollY = scroll_view.getScrollY() //for verticalScrollView
            if (scrollY == 0)
                loadItems!!.visiableFloatingButton();
            else
                loadItems!!.invisiableFloatingButton();
        })

        return view;
    }

    fun animateCustomer() {
        if (this!!.isFirstOpen!!) {
            customer_information?.setBackgroundResource(R.drawable.ic_remove_circle_outline_black_24dp);
            gurntor_informationg?.setBackgroundResource(R.drawable.ic_control_point_black_24dp);
            customer_layout?.startAnimation(fab_close)
            guranter_layout?.startAnimation(fab_open)
            customer_layout?.setClickable(false)
            isFirstOpen = false
            Log.d("Raj", "close")
            customer_layout?.visibility = View.VISIBLE
            guranter_layout?.visibility = View.GONE

        } else {
            customer_information?.setBackgroundResource(R.drawable.ic_control_point_black_24dp);
            gurntor_informationg?.setBackgroundResource(R.drawable.ic_remove_circle_outline_black_24dp);
            customer_layout?.startAnimation(fab_open)
            guranter_layout?.startAnimation(fab_close)
            customer_layout?.setClickable(true)
            isFirstOpen = true
            Log.d("Raj", "open")
            customer_layout?.visibility = View.GONE
            guranter_layout?.visibility = View.VISIBLE
        }
    }


    fun animateGranter() {
        if (this!!.isSecondOpen!!) {
            gurntor_informationg?.setBackgroundResource(R.drawable.ic_control_point_black_24dp);
            customer_information?.setBackgroundResource(R.drawable.ic_remove_circle_outline_black_24dp);
            guranter_layout?.startAnimation(fab_open)
            customer_layout?.startAnimation(fab_close)
            guranter_layout?.setClickable(false)
            isSecondOpen = false
            Log.d("Raj", "close")
            customer_layout?.visibility = View.VISIBLE
            guranter_layout?.visibility = View.GONE

        } else {
            gurntor_informationg?.setBackgroundResource(R.drawable.ic_remove_circle_outline_black_24dp);
            customer_information?.setBackgroundResource(R.drawable.ic_control_point_black_24dp);
            guranter_layout?.startAnimation(fab_close)
            customer_layout?.startAnimation(fab_open)
            guranter_layout?.setClickable(true)
            isSecondOpen = true
            Log.d("Raj", "open")
            customer_layout?.visibility = View.GONE
            guranter_layout?.visibility = View.VISIBLE

        }
    }



    override val customer_name: String
        get() = select_gender?.selectedItem.toString() + " " + customer_namr?.getText().toString()


    override val customer_fathername: String
        get() = cust_fathername?.getText().toString()


    override val customer_dob: String
        get() = dob?.getText().toString()


    override val customer_contactno: String
        get() = cust_mobile_no?.getText().toString()


    override val customer_adharno: String
        get() = aadhar_no?.getText().toString()


    override val customer_loanamt: String
        get() = loan_amount?.getText().toString()


    override val customer_loanduration: String
        get() = loan_duration?.getText().toString()

    override val customer_addre: String
        get() = cust_address?.getText().toString()


    override val customer_adress_type: String
        get() = address_type?.selectedItem.toString()


    override val customer_distric: String
        get() = district?.getText().toString()


    override val customer_city: String
        get() = cust_city?.getText().toString()


    override val customer_state: String
        get() = state_type?.selectedItem.toString()


    override val customer_picode: String
        get() = cust_pincode?.getText().toString()


    override val granter_name: String
        get() = gselect_gender?.selectedItem.toString() + " " + garanter_name?.getText().toString()


    override val granter_fathername: String
        get() = garanter_fname?.getText().toString()

    override val granter_dob: String
        get() = gdate_of_birth?.getText().toString()


    override val granter_contactno: String
        get() = granter_mobileno?.getText().toString()


    override val granter_adharno: String
        get() = gaadhar_no?.getText().toString()


    override val granter_address: String
        get() = grant_address?.getText().toString()


    override val granter_adress_type: String
        get() = gaddress_type?.selectedItem.toString()


    override val granter_distric: String
        get() = granter_district?.getText().toString()


    override val granter_city: String
        get() = grant_city?.getText().toString()


    override val granter_state: String
        get() = gstate_type?.selectedItem.toString()


    override val granter_picode: String
        get() = grantre_pincode?.getText().toString()


    override fun getSelectedDate(type: Int, date: String) {

        if (type == TXT_DATE) {
            dob?.setText(date)
        }
        if (type == GTXT_DATE) {
            gdate_of_birth?.setText(date)
        }
    }
}

