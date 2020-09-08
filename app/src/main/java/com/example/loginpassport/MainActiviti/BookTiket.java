package com.example.loginpassport.MainActiviti;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookTiket extends AppCompatActivity {
    EditText date;
    EditText time;
    DatePickerDialog pickerdate;
    TimePickerDialog pickertime;
    //find xml
    private int id_doctor;
    private EditText s_fullname;
    private EditText s_age;
    private EditText s_sex;
    private EditText s_phone;
    // Get data
    private String d_date;
    private  String d_time;
    private  String d_fullname;
    private  int d_age;
    private String d_sex;
    private int d_phone;
    TokenManager tokenManager;
    private Button btn_book;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.book_ticket);
        hideActionbar();
        findXml();
        Intent intent = this.getIntent();
        id_doctor= intent.getIntExtra("id_doctor",0);
        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                pickerdate = new DatePickerDialog(BookTiket.this,
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
                pickertime = new TimePickerDialog(BookTiket.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pickertime.show();
            }
        });
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataText();
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
            d_fullname=s_fullname.getText().toString();
            d_age=Integer.parseInt(s_age.getText().toString());
            d_sex=s_sex.getText().toString();
            d_phone=Integer.parseInt(s_phone.getText().toString());


            }
            public void findXml() {

                date = findViewById(R.id.edt_date);
                time= findViewById(R.id.edtTime);
                s_fullname= findViewById(R.id.edt_fullname);
                s_age=findViewById(R.id.edt_age);
                s_sex=findViewById(R.id.edt_sex);
                s_phone=findViewById(R.id.edt_phoneb);
                btn_book=findViewById(R.id.btn_booknow);
            }
            public void Book() {
                Log.d("AC", "Chay ham nay ");
        getDataText();
                tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
                ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
                Call<String> call = service.bookticket(id_doctor,d_fullname,d_age,d_sex,d_phone,d_date,d_time);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String content = response.body();
                            Toast.makeText(BookTiket.this, content, Toast.LENGTH_SHORT).show();
                            Toast.makeText(BookTiket.this, "Bạn đã book vé thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BookTiket.this,LoadNavi.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(BookTiket.this, "Lỗi rồi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("TAG", "onFailure: Tell me why");
                    }
                });

            }

    }

