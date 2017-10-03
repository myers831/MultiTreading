package com.example.admin.multitreading;

/**
 * Created by Admin on 10/3/2017.
 */

public class HelloEvent {
    String Data;

    public HelloEvent(String data) {
        Data = data;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
