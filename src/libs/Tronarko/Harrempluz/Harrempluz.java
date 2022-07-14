package libs.Tronarko.Harrempluz;

import java.util.ArrayList;


public class Harrempluz {

    private ArrayList<Harrem> mHarrems;

    public Harrempluz() {

        mHarrems = new ArrayList<Harrem>();

    }

    public void adicionar(Harrem eHarrem) {
        mHarrems.add(eHarrem);
    }

    public ArrayList<Harrem> getHarrems() {
        return mHarrems;
    }

}
