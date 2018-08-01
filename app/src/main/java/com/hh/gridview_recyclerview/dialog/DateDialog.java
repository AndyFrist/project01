package com.hh.gridview_recyclerview.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.NumberPickerView;

/**
 * create by xuxiaopeng
 * on 2018/7/31
 * 描述：
 */
public class DateDialog extends Dialog implements NumberPickerView.OnValueChangeListener ,View.OnClickListener{
    private NumberPickerView picker_year, picker_month, picker_day;
    private TextView date_cancel, date_sure;

    private String[] year_data = null;
    private String[] month_data = null;
    private String[] day_data = null;

    public DateDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public DateDialog(Context context) {
        this(context, R.style.transparentFrameWindowStyle);
        View view = getLayoutInflater().inflate(R.layout.choose_dialog, null);
        picker_year = (NumberPickerView) view.findViewById(R.id.picker_year);
        picker_month = (NumberPickerView) view.findViewById(R.id.picker_month);
        picker_day = (NumberPickerView) view.findViewById(R.id.picker_day);
        date_cancel = (TextView) view.findViewById(R.id.date_cancel);
        date_sure = (TextView) view.findViewById(R.id.date_sure);
        picker_year.setOnValueChangedListener(this);
        picker_month.setOnValueChangedListener(this);
        picker_day.setOnValueChangedListener(this);
        date_cancel.setOnClickListener(this);
        date_sure.setOnClickListener(this);

        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        onWindowAttributesChanged(wl);
        setCanceledOnTouchOutside(true);
        show();
    }


    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {

    }

    public void setDate() {
        picker_year.setValue(year_data.length - 1);
        picker_month.setValue(month_data.length - 1);
        picker_day.setValue(day_data.length - 1);
    }

    public void setData(String[] year_data, String[] month_data, String[] day_data) {
        this.year_data = year_data;
        this.month_data = month_data;
        this.day_data = day_data;

        picker_year.refreshByNewDisplayedValues(year_data);
        picker_month.refreshByNewDisplayedValues(month_data);
        picker_day.refreshByNewDisplayedValues(day_data);
        setDate();
    }


    @Override
    public void onClick(View view) {
        if (date_sure == view) {
            int yearValue = picker_year.getValue();
            int monthValue = picker_month.getValue();
            int dayValue = picker_day.getValue();

            dateListen.getDate(year_data[yearValue] + month_data[monthValue] + day_data[dayValue]);

            dismiss();
        }

        if (date_cancel == view) {
            dismiss();
        }
    }

    public interface DateListen{
        void getDate(String date);
    }

    private DateListen dateListen;

    public void setDateListen(DateListen dateListen) {
        this.dateListen = dateListen;
    }
}
