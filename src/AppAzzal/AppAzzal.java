package AppAzzal;

import Azzal.Windows;

public class AppAzzal {

   public static void main(String[] args){


       Windows mWindows = new Windows("AppAzzal", 1500, 1020);

       Alpha mAlpha = new Alpha();

       mWindows.CriarCenarioAplicavel(mAlpha);

       Thread mThread = new Thread(mWindows);
       mThread.start();

   }

}
