package libs.tempo;

import libs.luan.Lista;
import libs.tempo.Calendario;
import libs.tempo.Data;

import java.util.ArrayList;

public class LapsoTemporal {

    private Lista<Data> mDatas;

    public LapsoTemporal(String sInicio, String sFim) {
        mDatas = new Lista<Data>();
        mDatas = Calendario.listar_datas_entre(sInicio, sFim);
    }

    public Lista<Data> getDatas() {
        return mDatas;
    }

    public int getIndex(Data eData) {
        int index = 0;
        for (Data d : mDatas) {
            if (d.isIgual(eData)) {
                break;
            }
            index += 1;
        }
        return index;
    }

    public Data getData(Data eData) {
        Data ret = null;
        for (Data d : mDatas) {
            if (d.isIgual(eData)) {
                ret = d;
                break;
            }
        }
        return ret;
    }


    public Data get(int i) {
        return mDatas.get(i);
    }
}