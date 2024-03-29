package com.example.loginpassport.entities;
import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiError {

    String message;
    Map<String, List<String>> errors;

    public String getMessage() {
        return message;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
