package skilllotto.com.finvest.presenter

import skilllotto.com.aepsindifi.controller.CustomerListner
import skilllotto.com.finvest.Model.CustomerInformationModel
import skilllotto.com.finvest.Model.GranterInormationModel
import skilllotto.com.finvest.interactor.LoadListener
import skilllotto.com.finvest.interactor.MainInteractor


class CustGranterPresenterimpl(private var custGranterView: CustomerListner?) : MainPresenter<CustomerListner>, LoadListener<Any> {
    override fun onSuccess(t: Any?) {
        custGranterView!!.hideLoadingLayout()
        custGranterView!!.showSuccess(t)
    }

    private var customer_data: CustomerInformationModel? = null
    private val garanter_data: GranterInormationModel? = null
    private val mainInteractor: MainInteractor? = null

    private val isValid: Boolean
        get() {

            if (custGranterView!!.customer_name.length == 0) {
                custGranterView!!.validationMessage("Please enter  customer name")
                return false
            } else if (custGranterView!!.customer_fathername.length == 0) {
                custGranterView!!.validationMessage("Please enter customer father name")
                return false
            } else if (custGranterView!!.customer_dob.length == 0) {
                custGranterView!!.validationMessage("Please enter customer DOB")
                return false
            } else if (custGranterView!!.customer_contactno.length > 9) {
                custGranterView!!.validationMessage("Please enter customer Mobile no")
                return false
            } else if (custGranterView!!.customer_addre.length == 0) {
                custGranterView!!.validationMessage("Please enter customer address")
                return false
            } else if (custGranterView!!.customer_loanduration.length == 0) {
                custGranterView!!.validationMessage("Please enter customer loan duration")
                return false
            } else if (custGranterView!!.customer_loanamt.length == 0) {
                custGranterView!!.validationMessage("Please enter customer loan amount")
                return false
            } else if (custGranterView!!.customer_addre.length == 0) {
                custGranterView!!.validationMessage("Please enter customer address")
                return false
            } else if (custGranterView!!.customer_adress_type.length == 0) {
                custGranterView!!.validationMessage("Please chosse customer address type")
                return false
            } else if (custGranterView!!.customer_distric.length == 0) {
                custGranterView!!.validationMessage("Please enter customer district")
                return false
            } else if (custGranterView!!.customer_city.length == 0) {
                custGranterView!!.validationMessage("Please enter customer city")
                return false
            } else if (custGranterView!!.customer_state.length == 0) {
                custGranterView!!.validationMessage("Please enter customer state")
                return false
            } else if (custGranterView!!.customer_picode.length > 5) {
                custGranterView!!.validationMessage("Please enter customer PinCode")
                return false
            } else {

                customer_data = CustomerInformationModel()
                customer_data!!.rno = custGranterView!!.customer_addre
                customer_data!!.cname = custGranterView!!.customer_name
                customer_data!!.cfname = custGranterView!!.customer_fathername
                customer_data!!.cdateofbirth = custGranterView!!.customer_dob
                customer_data!!.ccontact = custGranterView!!.customer_contactno
                customer_data!!.cadhar = custGranterView!!.customer_adharno
                customer_data!!.cloanamount = custGranterView!!.customer_loanamt
                customer_data!!.loanduration = custGranterView!!.customer_loanduration
                customer_data!!.caddress = custGranterView!!.customer_loanduration
                customer_data!!.caddrestype = custGranterView!!.customer_adress_type
                customer_data!!.cdistrict = custGranterView!!.customer_distric
                customer_data!!.ccity = custGranterView!!.customer_city
                customer_data!!.cstate = custGranterView!!.customer_state
                customer_data!!.cpincode = custGranterView!!.customer_picode
                return true
            }


            if (custGranterView!!.granter_name.length == 0) {
                custGranterView!!.validationMessage("Please enter  gartner name")
                return false
            } else if (custGranterView!!.granter_fathername.length == 0) {
                custGranterView!!.validationMessage("Please enter gartner father name")
                return false
            } else if (custGranterView!!.granter_dob.length == 0) {
                custGranterView!!.validationMessage("Please enter gartner DOB")
                return false
            } else if (custGranterView!!.granter_contactno.length > 9) {
                custGranterView!!.validationMessage("Please enter gartner Mobile no")
                return false
            } else if (custGranterView!!.granter_address.length == 0) {
                custGranterView!!.validationMessage("Please enter gartner address")
                return false
            } else if (custGranterView!!.granter_adress_type.length == 0) {
                custGranterView!!.validationMessage("Please chosse gartner address type")
                return false
            } else if (custGranterView!!.granter_distric.length == 0) {
                custGranterView!!.validationMessage("Please enter gartner district")
                return false
            } else if (custGranterView!!.granter_city.length == 0) {
                custGranterView!!.validationMessage("Please enter gartner city")
                return false
            } else if (custGranterView!!.granter_state.length == 0) {
                custGranterView!!.validationMessage("Please enter gartner state")
                return false
            } else if (custGranterView!!.granter_picode.length > 5) {
                custGranterView!!.validationMessage("Please enter gartner PinCode")
                return false
            } else {


                garanter_data!!.gnam = custGranterView!!.granter_name
                garanter_data!!.gfname = custGranterView!!.granter_fathername
                garanter_data!!.gdatebirth = custGranterView!!.granter_dob
                garanter_data!!.gcontact = custGranterView!!.granter_contactno
                garanter_data!!.gadhar = custGranterView!!.granter_adharno
                garanter_data!!.gaddress = custGranterView!!.granter_address
                garanter_data!!.gatype = custGranterView!!.granter_adress_type
                garanter_data!!.gdistrict = custGranterView!!.granter_distric
                garanter_data!!.gcity = custGranterView!!.granter_city
                garanter_data!!.gstate = custGranterView!!.granter_state
                garanter_data!!.gpincode = custGranterView!!.granter_picode
                return true
            }

        }


    override fun onFailure(errorMessage: String) {
        custGranterView!!.hideLoadingLayout()
        custGranterView!!.showFailure(errorMessage)
    }

    override fun init() {
        custGranterView!!.showLoadingLayout()
        if (isValid) {
//                    mainInteractor = new LogInPresenter(param);
            mainInteractor = LogInPresenter("")
            mainInteractor!!.loadItems(this)
        }

    }

    override fun subscribe(custGranterView: CustomerListner) {
        this.custGranterView = custGranterView
    }

    override fun unSubscribe() {
        this.custGranterView = null
    }


}
