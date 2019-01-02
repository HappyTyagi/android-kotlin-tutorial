package skilllotto.com.finvest.view

import skilllotto.com.finvest.presenterView.MainView

/**
 * Created by Deepak Tyagi on 18-11-2017.
 */

interface TerminalView : MainView {
    val adminId: String
    val terminalId: String
    val shopId: String
    val ownerName: String
    val currentGT: String
    val gstin: String
    val shopType: String
    val userId: String
    val password: String
    val shopName: String
    val shopAddress: String
    val contactNumber: String
    val emailId: String
    val district: String
    val city: String
    val pincode: String
    val state: String
    val serialNo: String
    val isUpdate: Boolean
    fun validationMessage(message: String)
}
