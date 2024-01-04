package libs.entt;

import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;

public class ENTT {

    private Lista<Entidade> mEntts;

    public ENTT() {
        mEntts = new Lista<Entidade>();
    }

    public Lista<Entidade> get() {
        return mEntts;
    }

    public Entidade getUnico(String eNome, String eValor) {
        Entidade ret = null;

        for (Entidade e : mEntts) {
            if (e.at(eNome).contentEquals(eValor)) {
                ret = e;
                break;
            }
        }

        return ret;
    }

    public Entidade getMenorInteiro(String eNome) {
        Entidade ret = null;

        int menor = mEntts.get(0).atInt(eNome);

        for (Entidade e : mEntts) {
            if (e.atInt(eNome) < menor) {
                ret = e;
                menor=e.atInt(eNome);
            }
        }

        return ret;
    }

    public Entidade getMaiorInteiro(String eNome) {
        Entidade ret = null;

        int maior = mEntts.get(0).atInt(eNome);

        for (Entidade e : mEntts) {
            if (e.atInt(eNome) > maior) {
                ret = e;
                maior=e.atInt(eNome);
            }
        }

        return ret;
    }


    public void adicionar(Entidade novo) {
        mEntts.adicionar(novo);
    }


    // TEXTO
    public Entidade adicionar(String a1, String v1) {
        Entidade novo = new Entidade();

        novo.at(a1, v1);

        mEntts.adicionar(novo);
        return novo;
    }

    public Entidade adicionar(String a1, String v1, String a2, String v2) {
        Entidade novo = new Entidade();

        novo.at(a1, v1);
        novo.at(a2, v2);

        mEntts.adicionar(novo);
        return novo;
    }

    public Entidade adicionar(String a1, String v1, String a2, String v2, String a3, String v3) {
        Entidade novo = new Entidade();

        novo.at(a1, v1);
        novo.at(a2, v2);
        novo.at(a3, v3);

        mEntts.adicionar(novo);
        return novo;
    }

    // INTEIRO
    public Entidade adicionar(String a1, int v1) {
        Entidade novo = new Entidade();

        novo.atInt(a1, v1);

        mEntts.adicionar(novo);
        return novo;
    }

    public Entidade adicionar(String a1, String v1, String a2, int v2) {
        Entidade novo = new Entidade();

        novo.at(a1, v1);
        novo.atInt(a2, v2);

        mEntts.adicionar(novo);
        return novo;
    }


    public Entidade adicionar(String a1, int v1, String a2, int v2) {
        Entidade novo = new Entidade();

        novo.atInt(a1, v1);
        novo.atInt(a2, v2);

        mEntts.adicionar(novo);
        return novo;
    }

    public Entidade adicionar(String a1, String v1, String a2, int v2, String a3, int v3) {
        Entidade novo = new Entidade();

        novo.at(a1, v1);
        novo.atInt(a2, v2);
        novo.atInt(a3, v3);

        mEntts.adicionar(novo);
        return novo;
    }

    public Entidade adicionar(String a1, int v1, String a2, int v2, String a3, int v3) {
        Entidade novo = new Entidade();

        novo.atInt(a1, v1);
        novo.atInt(a2, v2);
        novo.atInt(a3, v3);

        mEntts.adicionar(novo);
        return novo;
    }


    // FUNCOES ESPECIAIS


    public int agregar_somatorio(String nome) {
        int somatorio = 0;

        for (Entidade e : mEntts) {
            somatorio += e.atInt(nome);
        }

        return somatorio;
    }

    public int agregar_somatorios(String a1, String a2) {
        int t1 = agregar_somatorio(a1);
        int t2 = agregar_somatorio(a2);

        return t1 + t2;
    }

    public void exportar_dkg(String arquivo) {

        DKG documento = new DKG();
        DKGObjeto raiz = documento.unicoObjeto("ENTT");

        for (Entidade e : mEntts) {

            DKGObjeto objeto = raiz.criarObjeto("E");
            for (Tag tag : e.tags()) {
                objeto.identifique(tag.getNome(), tag.getValor());
            }

        }

        documento.salvar(arquivo);

    }

}
