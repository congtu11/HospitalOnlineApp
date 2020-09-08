package com.example.loginpassport.MainActiviti;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookVideoCall extends AppCompatActivity {
    EditText date;
    EditText time;
    DatePickerDialog pickerdate;
    TimePickerDialog pickertime;
    private String d_date;
    private  String d_time;
    int id_doctor;
    TokenManager tokenManager;
    private Button bookvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_video_call);
        hideActionbar();
        findXml();
        Intent intent = this.getIntent();
        id_doctor= intent.getIntExtra("id_doctor",0);
        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);
        bookvideo= findViewById(R.id.btn_bookvideocalls);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                pickerdate = new DatePickerDialog(BookVideoCall.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                pickerdate.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                pickertime = new TimePickerDialog(BookVideoCall.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pickertime.show();
            }
        });
        bookvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book();
            }
        });
    }
    public void hideActionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); //Ẩn ActionBar nếu muốn
    }
    public void getDataText() {
        d_date= date.getText().toString();
        d_time=time.getText().toString();

    }
    public void findXml() {

        date = findViewById(R.id.edt_date_call);
        time= findViewById(R.id.edt_time_call);
    }
    public void Book() {
        getDataText();
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
        Call<String> call = service.bookvideocall(id_doctor,d_date,d_time);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String content = response.body();
                    Toast.makeText(BookVideoCall.this, content, Toast.LENGTH_SHORT).show();
                    Toast.makeText(BookVideoCall.this, "Bạn đã book vé video call thành công", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(BookVideoCall.this,LoadNavi.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("TAG", "onFailure: Tell me why");
            }
        });

    }
}
