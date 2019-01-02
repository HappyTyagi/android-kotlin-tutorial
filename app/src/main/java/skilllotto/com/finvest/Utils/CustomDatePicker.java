package skilllotto.com.finvest.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import skilllotto.com.aepsindifi.controller.DatePickerController;


/**
 * Created by Vishwajeet Verma on 03-10-2017.
 */

public class CustomDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    public static String TYPE       = "type";
    public static String MAX_DATE   = "max_date";
    public static String MIN_DATE   = "min_date";
    private static long min_date = 0;
    private static long max_date = 0;
    private int type;
    private DatePickerController mListener;


    public static CustomDatePicker getCustomDatePickerWithMaxMin(int type,long max_date,long min_date)
    {
        CustomDatePicker customDatePicker = new CustomDatePicker();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE,type);
        bundle.putLong(MAX_DATE,max_date);
        bundle.putLong(MIN_DATE,min_date);
        customDatePicker.setArguments(bundle);
        return customDatePicker;
    }
    public CustomDatePicker(){
    }
    public static CustomDatePicker getCustomDatePickerWithMax(int type,long max_date)
    {
        CustomDatePicker customDatePicker = getCustomDatePickerWithMaxMin(type,max_date,min_date);
        return customDatePicker;
    }

    public static CustomDatePicker getCustomDatePickerWithMin(int type,long min_date)
    {
        CustomDatePicker customDatePicker = getCustomDatePickerWithMaxMin(type,max_date,min_date);
        return customDatePicker;
    }
    public  static CustomDatePicker getCustomDatePicker(int type)
    {
        CustomDatePicker customDatePicker = getCustomDatePickerWithMaxMin(type,max_date,min_date);
        return customDatePicker;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mListener = (DatePickerController) getParentFragment();
            if(mListener== null)
                mListener = (DatePickerController)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement Callback interface");
        }
        Bundle bundle = getArguments();
        min_date = bundle.getLong(MIN_DATE);
        max_date = bundle.getLong(MAX_DATE);
        type     = bundle.getInt(TYPE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year  = c .get(Calendar.YEAR);
        int month = c .get(Calendar.MONTH);
        int day   = c .get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, day, month, year);
            dialog.getDatePicker().init(year, month, day, null);
           if(min_date!=0 && max_date!=0){
               dialog.getDatePicker().setMaxDate(max_date);
               dialog.getDatePicker().setMinDate(min_date);
           }else if(min_date !=0)
           {
               dialog.getDatePicker().setMinDate(min_date);
           }else if(max_date!=0){
               dialog.getDatePicker().setMinDate(max_date);
           }
         return dialog;




    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
       // String date = dayOfMonth+"/"+(month+1)+"/"+year;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, dayOfMonth);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        mListener.getSelectedDate(type,sdf.format(date));
    }

}
