package apps.app_atzum.utils;

import libs.azzal.geometria.Ponto;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Igualavel;
import libs.luan.Lista;
import libs.luan.Par;
import libs.luan.Unico;

public class AtzumPontosInteiro {

    public static Igualavel<Par<Ponto, Integer>> PAR_PONTO_INTEGER_IGUALAVEL() {
        return new Igualavel<Par<Ponto, Integer>>() {
            @Override
            public boolean is(Par<Ponto, Integer> a, Par<Ponto, Integer> b) {
                return a.getChave().isIgual(b.getChave());
            }
        };
    }

    public static void SALVAR(Lista<Par<Ponto, Integer>> pontos, String arquivo) {
        DKG documento = new DKG();
        DKGObjeto documento_raiz = documento.unicoObjeto("Pontos");

        for (Par<Ponto, Integer> ponto : pontos) {
            DKGObjeto obj_ponto = documento_raiz.criarObjeto("Ponto");
            obj_ponto.identifique("X").setInteiro(ponto.getChave().getX());
            obj_ponto.identifique("Y").setInteiro(ponto.getChave().getY());
            obj_ponto.identifique("Valor").setInteiro(ponto.getValor());
        }

        documento.salvar(arquivo);
    }

    public static Lista<Par<Ponto, Integer>> ABRIR(String arquivo) {

        Lista<Par<Ponto, Integer>> pontos = new Lista<Par<Ponto, Integer>>();

        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);
        DKGObjeto documento_raiz = documento.unicoObjeto("Pontos");

        for (DKGObjeto obj_ponto : documento_raiz.getObjetos()) {
            Ponto ponto = new Ponto(obj_ponto.identifique("X").getInteiro(), obj_ponto.identifique("Y").getInteiro());
            pontos.adicionar(new Par<Ponto, Integer>(ponto, obj_ponto.identifique("Valor").getInteiro()));
        }

        return pontos;
    }

    public static Lista<Par<Ponto, Integer>> ABRIR_ZERADO(String arquivo) {

        Lista<Par<Ponto, Integer>> pontos = new Lista<Par<Ponto, Integer>>();

        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);
        DKGObjeto documento_raiz = documento.unicoObjeto("Pontos");

        for (DKGObjeto obj_ponto : documento_raiz.getObjetos()) {
            Ponto ponto = new Ponto(obj_ponto.identifique("X").getInteiro(), obj_ponto.identifique("Y").getInteiro());
            pontos.adicionar(new Par<Ponto, Integer>(ponto, 0));
        }

        return pontos;
    }

    public static Unico<Par<Ponto, Integer>> UNICOS(Lista<Par<Ponto, Integer>> lista) {
        Unico<Par<Ponto, Integer>> unicos = new Unico<Par<Ponto, Integer>>(PAR_PONTO_INTEGER_IGUALAVEL());

        for (Par<Ponto, Integer> p : lista) {
            unicos.item(p);
        }

        return unicos;
    }

    public static void TRANSFORMAR_VALOR(Lista<Par<Ponto,Integer>> pontos_de_zona,int valor){

        for(Par<Ponto,Integer> ponto_valor : pontos_de_zona) {
            ponto_valor.setValor(valor);
        }
    }
}
