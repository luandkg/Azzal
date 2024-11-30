package libs.zetta_monitorum;

import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.tronarko.Tronarko;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaColecao;
import libs.zetta.fazendario.IndiceLocalizado;

public class ZettaColecaoMonitorum {

    private ZettaColecao mZettaColecao;
    private ZettaColecao mLogs;

    public ZettaColecaoMonitorum(ZettaColecao eLogs, ZettaColecao eZettaColecao) {
        mLogs = eLogs;
        mZettaColecao = eZettaColecao;
    }

    public void zerar() {
        mZettaColecao.zerar();
        logs_atualizar(false, false, false, false, true,false,false);
    }


    public long adicionar(Entidade e) {
        long id = mZettaColecao.adicionar(e);

        logs_atualizar(true, false, false, false, false,false,false);

        return id;
    }


    public void logs_atualizar(boolean adicao, boolean atualizao, boolean exclusao, boolean listagem, boolean zeramento,boolean indice_procurar,boolean e_contagem) {

        String s_tron = Tronarko.getTronAgora().getTextoZerado();
        String s_momento = Tronarko.getTozte().getTextoInversoZerado() + " :: " + fmt.zerado(Tronarko.getHazde().getArco(), 2);

        boolean editado = false;

        for (ItemColecionavel ic : mLogs.getItensEditaveis()) {
            if (ic.get().is("ColecaoNome", mZettaColecao.getNome()) && ic.get().is("Momento", s_momento)) {

                if (adicao) {
                    ic.get().at("Adicoes", ic.get().atInt("Adicoes") + 1);
                    ic.get().at("Adicao", s_tron);
                }

                if (atualizao) {
                    ic.get().at("Atualizacoes", ic.get().atInt("Atualizacoes") + 1);
                    ic.get().at("Atualizacao", s_tron);
                }

                if (exclusao) {
                    ic.get().at("Exclusoes", ic.get().atInt("Exclusoes") + 1);
                    ic.get().at("Exclusao", s_tron);
                }

                if (listagem) {
                    ic.get().at("Listagens", ic.get().atInt("Listagens") + 1);
                    ic.get().at("Listagem", s_tron);
                }

                if (zeramento) {
                    ic.get().at("Zeramentos", ic.get().atInt("Zeramentos") + 1);
                    ic.get().at("Zeramento", s_tron);
                }


                if (indice_procurar) {
                    ic.get().at("Indice.Procuras", ic.get().atInt("Indice.Procuras") + 1);
                    ic.get().at("Indice.Procura", s_tron);
                }

                if (e_contagem) {
                    ic.get().at("Contagens", ic.get().atInt("Contagens") + 1);
                    ic.get().at("Contagem", s_tron);
                }

                ic.atualizar();
                editado = true;
                break;
            }
        }

        if (!editado) {
            Entidade novo_log = new Entidade();
            novo_log.at("ColecaoNome", mZettaColecao.getNome());
            novo_log.at("Momento", s_momento);

            novo_log.at("Adicoes", 0);
            novo_log.at("Adicao", "");

            novo_log.at("Atualizacoes", 0);
            novo_log.at("Atualizacao", "");

            novo_log.at("Exclusoes", 0);
            novo_log.at("Exclusao", "");

            novo_log.at("Contagens", 0);
            novo_log.at("Contagem", "");

            novo_log.at("Listagens", 0);
            novo_log.at("Listagem", "");

            novo_log.at("Zeramentos", 0);
            novo_log.at("Zeramento", "");


            novo_log.at("Indice.Procuras", 0);
            novo_log.at("Indice.Procura", "");

            if (adicao) {
                novo_log.at("Adicoes", novo_log.atInt("Adicoes") + 1);
                novo_log.at("Adicao", s_tron);
            }

            if (atualizao) {
                novo_log.at("Atualizacoes", novo_log.atInt("Atualizacoes") + 1);
                novo_log.at("Atualizacao", s_tron);
            }

            if (exclusao) {
                novo_log.at("Exclusoes", novo_log.atInt("Exclusoes") + 1);
                novo_log.at("Exclusao", s_tron);
            }

            if (zeramento) {
                novo_log.at("Zeramentos", novo_log.atInt("Zeramentos") + 1);
                novo_log.at("Zeramento", s_tron);
            }

            if (listagem) {
                novo_log.at("Listagens", novo_log.atInt("Listagens") + 1);
                novo_log.at("Listagem", s_tron);
            }

            if (e_contagem) {
                novo_log.at("Contagens", novo_log.atInt("Contagens") + 1);
                novo_log.at("Contagem", s_tron);
            }


            if (indice_procurar) {
                novo_log.at("Indice.Procuras", novo_log.atInt("Indice.Procuras") + 1);
                novo_log.at("Indice.Procura", s_tron);
            }

            mLogs.adicionar(novo_log);
        }

    }


    public Lista<ItemColecionavelMonitorum> getItensEditaveis() {
        Lista<ItemColecionavelMonitorum> lista = new Lista<ItemColecionavelMonitorum>();

        for (ItemColecionavel item : mZettaColecao.getItensEditaveis()) {
            lista.adicionar(new ItemColecionavelMonitorum(mLogs, this, item));
        }

        logs_atualizar(false, false, false, true, false,false,false);

        return lista;
    }

    public Lista<Entidade> getItens() {

        logs_atualizar(false, false, false, true, false,false,false);

        return mZettaColecao.getItens();
    }

    public long contagem() {

        logs_atualizar(false, false, false, false, false,false,true);

        return mZettaColecao.contagem();
    }

    public void exibir_colecao() {
        mZettaColecao.exibir_colecao();
    }

    public Opcional<IndiceLocalizado> getIndiceMaior( ) {

        logs_atualizar(false, false, false, false, false,true,false);

        return mZettaColecao.getIndiceMaior();
    }

    public Opcional<IndiceLocalizado> procurar_indice(long indice) {

        logs_atualizar(false, false, false, false, false,true,false);

        return mZettaColecao.procurar_indice(indice);
    }

    public Opcional<Entidade> procurar_item_por_indice(long indice) {

        logs_atualizar(false, false, false, false, false,true,false);

        return mZettaColecao.procurar_item_por_indice(indice);
    }

}
