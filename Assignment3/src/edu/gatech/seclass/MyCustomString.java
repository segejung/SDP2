package edu.gatech.seclass;

public class MyCustomString implements MyCustomStringInterface{

    private String string;

    @Override
    public String getString() {
        if (string != null) {
            return string;
        }
        return null;
    }

    @Override
    public void setString(String string) {
        // TODO Auto-generated method stub
        this.string = string;
    }

}