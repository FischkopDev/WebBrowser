package de.home_skrobanek.browser.utils;

public class Utils {

    public native int intMethod(int i);
    public Utils(){
        System.out.println(intMethod(5));
    }

}
