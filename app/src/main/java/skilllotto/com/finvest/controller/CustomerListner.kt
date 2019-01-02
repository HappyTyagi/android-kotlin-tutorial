package skilllotto.com.aepsindifi.controller

import skilllotto.com.finvest.Model.CustomerInformationModel
import skilllotto.com.finvest.Model.GranterInormationModel
import skilllotto.com.finvest.presenterView.MainView

/**
 * Created by deepak tyagi on 03-10-2017.
 */

interface CustomerListner : MainView {


    val customer_name: String
    val customer_fathername: String
    val customer_dob: String
    val customer_contactno: String
    val customer_adharno: String
    val customer_loanamt: String
    val customer_loanduration: String
    val customer_addre: String
    val customer_adress_type: String
    val customer_distric: String
    val customer_city: String
    val customer_state: String
    val customer_picode: String


    val granter_name: String
    val granter_fathername: String
    val granter_dob: String
    val granter_contactno: String
    val granter_adharno: String
    val granter_adress_type: String
    val granter_address: String
    val granter_distric: String
    val granter_city: String
    val granter_state: String
    val granter_picode: String

    fun validationMessage(message: String)

//    fun customerdetail(): CustomerInformationModel
//    fun fragmentdetail(): GranterInormationModel


}
