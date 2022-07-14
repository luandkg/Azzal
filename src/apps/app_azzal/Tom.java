package apps.app_azzal;

public class Tom {

    public int a;
    public int b;
    public int c;
    public int d;

    public Tom() {

        a = 0;
        b = 0;
        c = 0;
        d = 0;

    }

    public int getInt(){

        int v = ((a & 0xFF) << 24) |
                ((b & 0xFF) << 16) |
                ((c & 0xFF) << 8) |
                ((d & 0xFF) << 0);

        return v;
    }
}
