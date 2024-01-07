package libs.entt;

import libs.luan.Lista;

public class CorpoENTT {

    private Lista<Entidade> mEntts;

    public CorpoENTT() {
        mEntts = new Lista<Entidade>();
    }

    public Lista<Entidade> get() {
        return mEntts;
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

    public Entidade getUnico(String nome, String eValor) {
        return ENTT.GET_UNICO(mEntts, nome, eValor);
    }

    public int agregar_somatorio(String nome) {
        return ENTT.ATRIBUTO_SOMAR(mEntts, nome);
    }

    public int agregar_somatorios(String a1, String a2) {
        return ENTT.ATRIBUTO_SOMAR_E_AGREGAR(mEntts, a1, a2);
    }

    public Entidade getMenorInteiro(String eNome) {
        return ENTT.ATRIBUTO_INTEIRO_MENOR(mEntts, eNome);
    }

    public Entidade getMaiorInteiro(String eNome) {
        return ENTT.ATRIBUTO_INTEIRO_MAIOR(mEntts, eNome);
    }

    public void exportar_dkg(String arquivo) {
        ENTT.EXPORTAR_DKG(mEntts, arquivo);
    }

}
