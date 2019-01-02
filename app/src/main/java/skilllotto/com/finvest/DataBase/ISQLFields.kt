package skilllotto.com.finvest.DataBase

 open interface ISQLFields {
     companion object {
         /* customer_info table*/

         var TABLE_CUSTOMERINFO = "customerdetails"
         var KEY_AUTOID = "_id"
         var KEY_CUSTOMERNAME = "customer_name"
         var KEY_CUSTOMER_FATHER_NAME = "cust_father_name"
         var KEY_CDOB = "dob"
         var KEY_CONTACT_NO = "contact_no"
         var KEY_ADHAARNO = "adhaar_no"
         var KEY_LOAN_AMOUNT = "loan_amt"
         var KEY_LOAN_DURATION = "loan_duration"
         var KEY_ADDRESS_TYPE = "address_type"
         var KEY_ADDRESS = "address"
         var KEY_DISTRICT = "district"
         var KEY_CITY = "city"
         var KEY_STATE = "state"
         var KEY_PINCODE = "pin_code"
         var KEY_CUSTOMERID = "cust_id"
         var KEY_CUSTOMER_REGDATE = "custreg_date"
         var KEY_CUSTOMER_REGTIME = "custreg_time"

         /* garnter info table*/

         var TABLE_GARANTERINFO = "garanterdetails"
         var KEY_GAUTOID = "_id"
         var KEY_GARANTERNAME = "garanter_name"
         var KEY_GARANTER_FATHER_NAME = "gra_father_name"
         var KEY_GDOB = "dob"
         var KEY_GCONTACT_NO = "contact_no"
         var KEY_GADHAARNO = "adhaar_no"
         var KEY_GADDRESS_TYPE = "address_type"
         var KEY_GADDRESS = "address"
         var KEY_GDISTRICT = "district"
         var KEY_GCITY = "city"
         var KEY_GSTATE = "state"
         var KEY_GPINCODE = "pin_code"
         var KEY_GARANTERID = "gra_id"
         var KEY_GARANTER_REGDATE = "gra_reg_date"
         var KEY_GARANTER_REGTIME = "gra_reg_time"
     }
 }