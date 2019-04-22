package com.example.predator.lab4t1;

public class Competition {

    String name;
    int areaCode;

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Competition() {
    }

    public Competition(String name, int areaCode) {
        this.name = name;
        this.areaCode = areaCode;
    }
}