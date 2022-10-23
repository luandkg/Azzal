package apps.app_attuz.Assessorios;

import libs.Arquivos.QTT;

public class DadosQTT {

    private String LOCAL;

    public DadosQTT(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    public int getAltura(int x, int y) {
        return QTT.pegar(LOCAL + "dados/relevo.qtt", x, y);
    }

    public int getDistanciaDoMar(int x, int y) {
        return QTT.pegar(LOCAL + "dados/mar_distancia.qtt", x, y);
    }

    public int getDistanciaDaAgua(int x, int y) {
        return QTT.pegar(LOCAL + "dados/agua_distancia.qtt", x, y);
    }

    public int getUmidade(int x, int y) {
        return QTT.pegar(LOCAL + "dados/umidade.qtt", x, y);
    }

    public int getPreciptacao(int x, int y) {
        return QTT.pegar(LOCAL + "dados/preciptacao.qtt", x, y);
    }

    public int getTemperatura_vi(int x, int y) {
        return QTT.pegar(LOCAL + "dados/temperatura_vi.qtt", x, y);
    }

    public int getTemperatura_iv(int x, int y) {
        return QTT.pegar(LOCAL + "dados/temperatura_iv.qtt", x, y);
    }
}
