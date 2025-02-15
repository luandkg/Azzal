package libs.zetta;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Strings;
import libs.tempo.Calendario;
import libs.zetta.fazendario.Armazem;
import libs.zetta.fazendario.ArmazemIndiceSumario;
import libs.zetta.fazendario.ItemAlocado;

public class ZettaMemCached {

    private String mNome;
    private Armazem mDados;
    private ZettaSequenciador mSequenciador;
    private ArmazemIndiceSumario mIndice;

    public ZettaMemCached(String eNome, Armazem eArmazem, ZettaSequenciador eSequenciador, ArmazemIndiceSumario eIndice) {
        mNome = eNome;
        mDados = eArmazem;
        mSequenciador = eSequenciador;
        mIndice = eIndice;
    }

    public long getIdentificador() {
        return mDados.getIndice();
    }

    public String getNome() {
        return mNome;
    }

    public long contagem() {
        return mDados.getItensUtilizadosContagem();
    }


    public long alocados() {
        return mDados.getItensAlocadosContagem();
    }

    public void zerar() {
        mIndice.zerar();
        mSequenciador.zerar();
        mDados.zerar();
    }

    public Lista<Entidade> getItens() {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(e_item);
        }

        return lista;
    }


    public boolean chaveExiste(String chave) {

        boolean existe = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {

                existe = true;
                break;
            }
        }
        return existe;
    }

    public void publicar(String chave, String valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            throw new RuntimeException("Já existe esse item : " + chave);
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }

    public void publicar(String chave, int valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            throw new RuntimeException("Já existe esse item : " + chave);
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }

    public void publicar(String chave, double valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            throw new RuntimeException("Já existe esse item : " + chave);
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }

    public void publicar(String chave, long valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            throw new RuntimeException("Já existe esse item : " + chave);
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }

    public void atualizar(String chave, String valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {

                if (!e_item.at("Valor").contentEquals(valor)) {

                    e_item.at("@PTR", item.getPonteiroDados());

                    e_item.tornar_primeiro("@PTR");
                    e_item.tornar_primeiro("@ID");
                    e_item.at("Valor", valor);

                    e_item.at("DDM", Calendario.getTempoCompleto());

                    item.atualizarUTF8(ENTT.TO_DOCUMENTO(e_item));
                }


                atualizado = true;
                break;
            }
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }

    public void atualizar(String chave, int valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {

                if (e_item.atInt("Valor") != valor) {

                    e_item.at("@PTR", item.getPonteiroDados());

                    e_item.tornar_primeiro("@PTR");
                    e_item.tornar_primeiro("@ID");
                    e_item.at("Valor", valor);

                    e_item.at("DDM", Calendario.getTempoCompleto());

                    item.atualizarUTF8(ENTT.TO_DOCUMENTO(e_item));
                }


                atualizado = true;
                break;
            }
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }

    public void atualizar(String chave, double valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {

                e_item.at("@PTR", item.getPonteiroDados());

                if (e_item.atDouble("Valor") != valor) {

                    e_item.tornar_primeiro("@PTR");
                    e_item.tornar_primeiro("@ID");
                    e_item.at("Valor", valor);

                    e_item.at("DDM", Calendario.getTempoCompleto());

                    item.atualizarUTF8(ENTT.TO_DOCUMENTO(e_item));
                }


                atualizado = true;
                break;
            }
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }

    public void atualizar(String chave, long valor) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {

                e_item.at("@PTR", item.getPonteiroDados());

                if (e_item.atLong("Valor") != valor) {

                    e_item.tornar_primeiro("@PTR");
                    e_item.tornar_primeiro("@ID");
                    e_item.at("Valor", valor);

                    e_item.at("DDM", Calendario.getTempoCompleto());

                    item.atualizarUTF8(ENTT.TO_DOCUMENTO(e_item));

                }

                atualizado = true;
                break;
            }
        }

        if (!atualizado) {

            long proximo = mSequenciador.getProximo();

            Entidade novo = new Entidade();
            novo.at("@ID", proximo);
            novo.at("Chave", chave);
            novo.at("Valor", valor);
            novo.at("DDC", Calendario.getTempoCompleto());

            ItemAlocado item = mDados.item_adicionar(ENTT.TO_DOCUMENTO(novo));
            mIndice.setItem(proximo, item.getPonteiroDados());

        }

    }


    public void inteiro_aumentar(String chave, int aumentar) {

        boolean atualizado = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {

                e_item.at("@PTR", item.getPonteiroDados());

                e_item.tornar_primeiro("@PTR");
                e_item.tornar_primeiro("@ID");
                e_item.at("Valor", e_item.atInt("Valor") + aumentar);

                e_item.at("DDM", Calendario.getTempoCompleto());

                item.atualizarUTF8(ENTT.TO_DOCUMENTO(e_item));
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
            throw new RuntimeException("Item não encontrado : " + chave);
        }

    }

    public Opcional<String> obterValorOpcional(String chave) {

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                return Opcional.OK(e_item.at("Valor"));
            }
        }

        return Opcional.CANCEL();

    }

    public Opcional<Integer> obterInteiroOpcional(String chave) {

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                return Opcional.OK(e_item.atInt("Valor"));
            }
        }

        return Opcional.CANCEL();
    }

    public Opcional<Double> obterDoubleOpcional(String chave) {

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                return Opcional.OK(e_item.atDouble("Valor"));
            }
        }

        return Opcional.CANCEL();
    }

    public Opcional<Long> obterLongOpcional(String chave) {

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {
                return Opcional.OK(e_item.atLong("Valor"));
            }
        }

        return Opcional.CANCEL();
    }

    public void publicar_se_nao_existir(String chave, String valor) {
        if (!chaveExiste(chave)) {
            publicar(chave, valor);
        }
    }

    public void publicar_se_nao_existir(String chave, int valor) {
        if (!chaveExiste(chave)) {
            publicar(chave, valor);
        }
    }

    public void inteiro_aumentar_ou_criar_se_nao_existir(String chave, int valor, int aumentar) {

        if (chaveExiste(chave)) {
            inteiro_aumentar(chave, aumentar);
        } else {
            publicar(chave, valor);
        }

    }


    public boolean remover(String chave) {

        boolean removido = false;

        for (ItemAlocado item : mDados.getItensAlocados()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            if (e_item.is("Chave", chave)) {

                mIndice.remover(e_item.atLong("@ID"));
                item.remover();
                removido = true;
                break;
            }
        }

        return removido;

    }


    public Lista<Entidade> buscar_parametrizados(String r1 ) {
        Lista<Entidade> ret = new Lista<Entidade>();


        for (Entidade item : getItens()) {

            String item_nome = item.at("Chave");

            if (item_nome.startsWith(r1 + "(") & item_nome.endsWith(")") && Strings.contar(item_nome,"(")==1 && Strings.contar(item_nome,")")==1) {
                ret.adicionar(item);
            }

        }

        return ret;
    }

    public Lista<Entidade> buscar_parametrizados(String r1 ,String r2) {
        Lista<Entidade> ret = new Lista<Entidade>();


        for (Entidade item : getItens()) {

            String item_nome = item.at("Chave");

            if (item_nome.startsWith(r1 + "(") & item_nome.endsWith("]") && item_nome.contains(")->"+r2 +"[") && Strings.contar(item_nome,"(")==1 && Strings.contar(item_nome,")")==1 && Strings.contar(item_nome,"[")==1 && Strings.contar(item_nome,"]")==1) {
                ret.adicionar(item);
            }

        }

        return ret;
    }

    public Lista<Entidade> buscar_parametrizados(String r1 ,String r2,String r3) {
        Lista<Entidade> ret = new Lista<Entidade>();


        for (Entidade item : getItens()) {

            String item_nome = item.at("Chave");

            if (item_nome.startsWith(r1 + "(") & item_nome.endsWith("}") && item_nome.contains(")->"+r2 +"[")&& item_nome.contains("]::"+r3 +"{") && Strings.contar(item_nome,"(")==1 && Strings.contar(item_nome,")")==1 && Strings.contar(item_nome,"[")==1 && Strings.contar(item_nome,"]")==1&& Strings.contar(item_nome,"{")==1 && Strings.contar(item_nome,"}")==1) {
                ret.adicionar(item);
            }

        }

        return ret;
    }
}
