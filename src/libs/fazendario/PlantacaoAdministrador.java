package libs.fazendario;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class PlantacaoAdministrador {

    private Arquivador mArquivador;
    private Fazendario mFazendario;
    private long mIndice;
    private long mPonteiroPlantacao;

    public PlantacaoAdministrador(Arquivador eArquivador, Fazendario eFazendario, long eIndice, long ePonteiroPlantacao) {

        mArquivador = eArquivador;
        mFazendario = eFazendario;
        mIndice = eIndice;
        mPonteiroPlantacao = ePonteiroPlantacao;


    }


    public Lista<Long> adicionar(byte[] bytes) {

        Lista<Long> blocos_alocados = new Lista<Long>();

        DeslizadorEstrutural<PlantacaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PlantacaoDeslizante>(new PlantacaoDeslizante(mArquivador, mPonteiroPlantacao));


        int blocos = (int) ((long) bytes.length / Matematica.KB(16));
        long bytes_alocados = (long) blocos * Fazendario.TAMANHO_SETOR_ITEM;
        if (bytes_alocados < (long) bytes.length) {
            blocos += 1;
        }

        VERIFICADOR.MENOR_OU_IGUAL(blocos, 1000);


        int blocos_necessarios = blocos;
        int blocos_obtidos_contagem = 0;


        boolean item_adicionado = false;

        boolean saiu = false;

        while (portoes_deslizantes.temProximo()) {

            PlantacaoDeslizante plantacao_corrente = portoes_deslizantes.get();

            //  fmt.print("\t ++ Adicionar Item : ArmazemPortao :: {} ->> {} ", mArmazemIndice, local_ponteiro_sumario);

            Lista<Long> blocos_obtidos = plantacao_corrente.obter_ate(blocos_necessarios);

            int blocos_conseguidos = blocos_obtidos.getQuantidade();


            blocos_obtidos_contagem += blocos_conseguidos;
            for (Long bloco_obtido : blocos_obtidos) {
                blocos_alocados.adicionar(bloco_obtido);
            }

            if (blocos_conseguidos >= blocos_necessarios) {
                break;
            }

            if (plantacao_corrente.temProximo()) {
                portoes_deslizantes.setProximo(new PlantacaoDeslizante(mArquivador, plantacao_corrente.getProximo()));
                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);
            } else {
                portoes_deslizantes.finalizar();
                saiu = true;
            }

        }

        fmt.print("Blocos Alocados :: {} -- {} -- {}", blocos_obtidos_contagem, blocos_necessarios, saiu);

        if (saiu) {
            if (blocos_obtidos_contagem < blocos_necessarios) {

                long ponteiro_plantacao = mFazendario.CRIAR_PLANTACAO(mIndice);

                portoes_deslizantes.get().setProximo(ponteiro_plantacao);

                PlantacaoDeslizante plantacao_corrente = new PlantacaoDeslizante(mArquivador, ponteiro_plantacao);
                Lista<Long> blocos_obtidos = plantacao_corrente.obter_ate(blocos_necessarios);

                for (Long bloco_obtido : blocos_obtidos) {
                    blocos_alocados.adicionar(bloco_obtido);
                }

            }

        }


        int bytes_escrevendo = 0;
        int bytes_escrevendo_ate = (int) Fazendario.TAMANHO_AREA_ITEM;

        if (bytes_escrevendo_ate > bytes.length) {
            bytes_escrevendo_ate = bytes.length;
        }


        for (Long bloco_a : blocos_alocados) {
            fmt.print("Alocado :: {}", bloco_a);

            mArquivador.setPonteiro(bloco_a);

            int bloco_status = mArquivador.get_u8();

            if (bloco_status == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO) {

                mArquivador.ir_para_o_fim();
                long ponteiro_dados = mArquivador.getPonteiro();

                mArquivador.set_u8_em_bloco(Fazendario.TAMANHO_AREA_ITEM, (byte) 0);

                mArquivador.setPonteiro(bloco_a);
                mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);
                long pular = mArquivador.get_u64();
                mArquivador.set_u64(ponteiro_dados);

            }


            mArquivador.setPonteiro(bloco_a);

            int bloco_status_aqui = mArquivador.get_u8();
            long ponteiro_eu_mesmo = mArquivador.get_u64();
            long ponteiro_dados_aqui = mArquivador.get_u64();

            mArquivador.setPonteiro(bloco_a);
            mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);


            int i = bytes_escrevendo;
            int o = bytes_escrevendo_ate;


            if (i < o) {

                byte[] bloco_aqui = new byte[o - i];

                int a = 0;

                while (i < o) {
                    bloco_aqui[a] = bytes[i];
                    i += 1;
                    a += 1;
                }

                mArquivador.setPonteiro(ponteiro_dados_aqui);
                mArquivador.set_u8_vector(bloco_aqui);

            }


            bytes_escrevendo += (int) Fazendario.TAMANHO_AREA_ITEM;
            bytes_escrevendo_ate += (int) Fazendario.TAMANHO_AREA_ITEM;

            if (bytes_escrevendo_ate > bytes.length) {
                bytes_escrevendo_ate = bytes.length;
            }
        }


        return blocos_alocados;
    }


    public void zerar() {

        DeslizadorEstrutural<PlantacaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PlantacaoDeslizante>(new PlantacaoDeslizante(mArquivador, mPonteiroPlantacao));

        while (portoes_deslizantes.temProximo()) {

            PlantacaoDeslizante plantacao_corrente = portoes_deslizantes.get();

            plantacao_corrente.zerar();

            fmt.print("Plantacao Zerando :: {}", plantacao_corrente.getPonteiro());

            if (plantacao_corrente.temProximo()) {
                portoes_deslizantes.setProximo(new PlantacaoDeslizante(mArquivador, plantacao_corrente.getProximo()));
                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);
            } else {
                portoes_deslizantes.finalizar();
            }

        }


    }


    public Lista<Entidade> getDump() {

        DeslizadorEstrutural<PlantacaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PlantacaoDeslizante>(new PlantacaoDeslizante(mArquivador, mPonteiroPlantacao));


        Lista<Entidade> plantacoes = new Lista<Entidade>();


        while (portoes_deslizantes.temProximo()) {

            PlantacaoDeslizante plantacao_corrente = portoes_deslizantes.get();


            Entidade e_plantacao = ENTT.CRIAR_EM_SEQUENCIALMENTE(plantacoes,"PlantacaoID");
            e_plantacao.at("Ponteiro", plantacao_corrente.getPonteiro());
            e_plantacao.at("Quantidade", plantacao_corrente.getQuantidade());
            e_plantacao.at("Alocados", plantacao_corrente.getAlocados());
            e_plantacao.at("Disponiveis", plantacao_corrente.getDisponiveis());
            e_plantacao.at("Ocupados", plantacao_corrente.getOcupados());


            e_plantacao.at("TemProximo", Portugues.VALIDAR(plantacao_corrente.temProximo(), "SIM", "NÃO"));
            e_plantacao.at("PonteiroProximo", plantacao_corrente.getProximo());

            if (plantacao_corrente.temProximo()) {
                portoes_deslizantes.setProximo(new PlantacaoDeslizante(mArquivador, plantacao_corrente.getProximo()));
                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);
            } else {
                portoes_deslizantes.finalizar();
            }

        }

        return plantacoes;
    }


    public int getPlantacoesQuantidade() {

        DeslizadorEstrutural<PlantacaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PlantacaoDeslizante>(new PlantacaoDeslizante(mArquivador, mPonteiroPlantacao));


       int quantidade = 0;

        while (portoes_deslizantes.temProximo()) {

            PlantacaoDeslizante plantacao_corrente = portoes_deslizantes.get();

            quantidade+=1;

            if (plantacao_corrente.temProximo()) {
                portoes_deslizantes.setProximo(new PlantacaoDeslizante(mArquivador, plantacao_corrente.getProximo()));
                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);
            } else {
                portoes_deslizantes.finalizar();
            }

        }

        return quantidade;
    }

    public int getPlantacoesEspacos() {

        DeslizadorEstrutural<PlantacaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PlantacaoDeslizante>(new PlantacaoDeslizante(mArquivador, mPonteiroPlantacao));


        int quantidade = 0;

        while (portoes_deslizantes.temProximo()) {

            PlantacaoDeslizante plantacao_corrente = portoes_deslizantes.get();

            quantidade+=plantacao_corrente.getQuantidade();

            if (plantacao_corrente.temProximo()) {
                portoes_deslizantes.setProximo(new PlantacaoDeslizante(mArquivador, plantacao_corrente.getProximo()));
                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);
            } else {
                portoes_deslizantes.finalizar();
            }

        }

        return quantidade;
    }

    public int getPlantacoesAlocados() {

        DeslizadorEstrutural<PlantacaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PlantacaoDeslizante>(new PlantacaoDeslizante(mArquivador, mPonteiroPlantacao));


        int quantidade = 0;

        while (portoes_deslizantes.temProximo()) {

            PlantacaoDeslizante plantacao_corrente = portoes_deslizantes.get();

            quantidade+=plantacao_corrente.getAlocados();

            if (plantacao_corrente.temProximo()) {
                portoes_deslizantes.setProximo(new PlantacaoDeslizante(mArquivador, plantacao_corrente.getProximo()));
                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);
            } else {
                portoes_deslizantes.finalizar();
            }

        }

        return quantidade;
    }


    public int getPlantacoesOcupados() {

        DeslizadorEstrutural<PlantacaoDeslizante> portoes_deslizantes = new DeslizadorEstrutural<PlantacaoDeslizante>(new PlantacaoDeslizante(mArquivador, mPonteiroPlantacao));


        int quantidade = 0;

        while (portoes_deslizantes.temProximo()) {

            PlantacaoDeslizante plantacao_corrente = portoes_deslizantes.get();

            quantidade+=plantacao_corrente.getOcupados();

            if (plantacao_corrente.temProximo()) {
                portoes_deslizantes.setProximo(new PlantacaoDeslizante(mArquivador, plantacao_corrente.getProximo()));
                //    fmt.print("\t ++ Mudando de ArmazemPortao :: {} ->> {} ", local_ponteiro_sumario_anterior, local_ponteiro_sumario);
            } else {
                portoes_deslizantes.finalizar();
            }

        }

        return quantidade;
    }
}
