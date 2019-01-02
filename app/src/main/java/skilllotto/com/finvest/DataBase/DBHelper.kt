package skilllotto.com.finvest.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import java.io.File

class DBHelper(private val context: Context)//  super(context, DATABASE_NAME, null, 1);
    : SQLiteOpenHelper(context, Environment.getExternalStorageDirectory().toString() + File.separator + DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(IDBHelper.CREATE_CUsTOMERDTAILS_TABLE)
        db.execSQL(IDBHelper.CREATE_GARANTERDETAIL_TABLE)
        db.execSQL(IDBHelper.CREATE_GARANTER_CUSTOMERDETAIL_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + ISQLFields.TABLE_CUSTOMERINFO)
        db?.execSQL("DROP TABLE IF EXISTS " + ISQLFields.TABLE_GARANTERINFO)
        //        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOID);
        onCreate(db!!)
    }


    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_PATH = "/Explorer/sdcard/"
        private val DATABASE_NAME = "UPHARFINVEST.db"
    }
}