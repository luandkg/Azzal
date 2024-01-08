package apps.app_attuz.Ferramentas;

import apps.app_attuz.Assessorios.Massas;
import libs.arquivos.QTT;

public class MassaToQTT {

    public static void salvar(Massas eMassa, String eArquivoQTT) {

        QTT DADOS_QTT = QTT.criar(eMassa.getLargura(), eMassa.getAltura());

        for (int y = 0; y < DADOS_QTT.getAltura(); y++) {
            for (int x = 0; x < DADOS_QTT.getLargura(); x++) {
                DADOS_QTT.setValor(x, y, eMassa.getValor(x, y));
            }
        }

        QTT.guardar(eArquivoQTT, DADOS_QTT);

    }

    public static void salvarTerra(Massas tectonica, Massas eMassa, String eArquivoQTT) {

        QTT DADOS_QTT = QTT.criar(eMassa.getLargura(), eMassa.getAltura());

        for (int y = 0; y < DADOS_QTT.getAltura(); y++) {
            for (int x = 0; x < DADOS_QTT.getLargura(); x++) {
                if (tectonica.isTerra(x, y)) {
                    DADOS_QTT.setValor(x, y, eMassa.getValor(x, y));
                } else {
                    DADOS_QTT.setValor(x, y, 0);
                }
            }
        }

        QTT.guardar(eArquivoQTT, DADOS_QTT);

    }
}
