package com.example.loginpassport.model;

public class DoctorModelTest {
    private String dt_name;
    private String dt_email;

    public DoctorModelTest(String dt_name,String dt_email) {
        super();
        this.dt_email=dt_email;
        this.dt_name=dt_name;
    }

    public String getDt_email() {
        return dt_email;
    }

    public String getDt_name() {
        return dt_name;
    }

    public void setDt_email(String dt_email) {
        this.dt_email = dt_email;
    }

    public void setDt_name(String dt_name) {
        this.dt_name = dt_name;
    }
}
