package skilllotto.com.finvest.DataBase


interface IDBHelper : ISQLFields {

    companion object {

        var CREATE_CUsTOMERDTAILS_TABLE = ("CREATE TABLE "
                + ISQLFields.TABLE_CUSTOMERINFO + "(" + ISQLFields.KEY_AUTOID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + ISQLFields.KEY_CUSTOMERNAME + " TEXT  NOT NULL UNIQUE,"
                + ISQLFields.KEY_CUSTOMER_FATHER_NAME + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_CDOB + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_CONTACT_NO + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_ADHAARNO + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_LOAN_AMOUNT + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_LOAN_DURATION + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_ADDRESS_TYPE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_ADDRESS + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_DISTRICT + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_CITY + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_STATE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_PINCODE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_CUSTOMERID + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_CUSTOMER_REGDATE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_CUSTOMER_REGTIME + " TEXT DEFAULT \"\"" + ")")



        /*create table masterinfo*/

        var CREATE_GARANTERDETAIL_TABLE = ("CREATE TABLE "
                + ISQLFields.TABLE_GARANTERINFO + "(" + ISQLFields.KEY_GAUTOID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + ISQLFields.KEY_GARANTERNAME + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GARANTER_FATHER_NAME + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GDOB + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GCONTACT_NO + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GADHAARNO + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GADDRESS_TYPE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GADDRESS + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GDISTRICT + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GCITY + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GSTATE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GPINCODE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GARANTERID + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GARANTER_REGDATE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GARANTER_REGTIME + " TEXT DEFAULT \"\"" + ")")


        var CREATE_GARANTER_CUSTOMERDETAIL_TABLE = ("CREATE TABLE "
                + ISQLFields.TABLE_GARANTERINFO + "(" + ISQLFields.KEY_GAUTOID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + ISQLFields.KEY_CUSTOMERID + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GARANTERID + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GARANTER_REGDATE + " TEXT DEFAULT \"\","
                + ISQLFields.KEY_GARANTER_REGTIME + " TEXT DEFAULT \"\"" + ")")


    }

}