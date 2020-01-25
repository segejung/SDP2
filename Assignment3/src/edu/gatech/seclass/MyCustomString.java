package edu.gatech.seclass;
import java.io.*;
public class MyCustomString implements MyCustomStringInterface{

    private String str;

    @Override
    public String getString() {
        if (str != null) {
            return str;
        }
        return null;
    }

    @Override
    public void setString(String string) {

        this.str = string;
    }

    @Override
    public char mostCommonChar() {
        int ASCII_SIZE = 256;

            if (str == null || str.isEmpty()) {
                throw new NullPointerException("String must be non-empty value");
            }

                int count[] = new int[ASCII_SIZE];
                int len = str.length();
                for (int i=0; i<len; i++)
                    count[str.charAt(i)]++;
                int max = -1;
                char result = ' ';
                for (int i = 0; i < len; i++) {
                    if (max < count[str.charAt(i)]) {
                        max = count[str.charAt(i)];
                        result = str.charAt(i);
                    }
                }
                return result;
            }

        }




