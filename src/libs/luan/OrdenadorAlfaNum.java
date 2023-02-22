package libs.luan;

import java.util.Comparator;

public class OrdenadorAlfaNum implements Comparator {

    private int GetValueInt(String s){
        int value = 0;
        for (char c : s.toCharArray()) {
            if (isNumber(c)){
                value = value * 10 + (c - '0');
            }
        }
        return value;
    }
    private boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }



    @Override
    public int compare(Object o1, Object o2) {
        return GetValueInt(o1.toString()) - GetValueInt(o2.toString());
    }
}