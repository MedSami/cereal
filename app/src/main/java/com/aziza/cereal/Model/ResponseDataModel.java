package com.aziza.cereal.Model;

import java.util.List;

public class ResponseDataModel {


    String  code, message;
    List<DataModel> result;

    public List<DataModel> getResult() {
        return result;
    }

    public void setResult(List<DataModel> result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
