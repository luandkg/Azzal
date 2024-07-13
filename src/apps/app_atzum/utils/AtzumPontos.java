package apps.app_atzum.utils;

import libs.azzal.geometria.Ponto;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.Par;

public class AtzumPontos {

    public static Lista<Ponto> ABRIR(String arquivo) {

        Lista<Ponto> pontos = new Lista<Ponto>();

        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);
        DKGObjeto documento_raiz = documento.unicoObjeto("Pontos");

        for (DKGObjeto obj_ponto : documento_raiz.getObjetos()) {
            Ponto ponto = new Ponto(obj_ponto.identifique("X").getInteiro(), obj_ponto.identifique("Y").getInteiro());
            pontos.adicionar(ponto);
        }

        return pontos;
    }

    public static void SALVAR(Lista<Ponto> pontos, String arquivo) {
        DKG documento = new DKG();
        DKGObjeto documento_raiz = documento.unicoObjeto("Pontos");

        for (Ponto ponto : pontos) {
            DKGObjeto obj_ponto = documento_raiz.criarObjeto("Ponto");
            obj_ponto.identifique("X").setInteiro(ponto.getX());
            obj_ponto.identifique("Y").setInteiro(ponto.getY());
        }

        documento.salvar(arquivo);
    }
}
