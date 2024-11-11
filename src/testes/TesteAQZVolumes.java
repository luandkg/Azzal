package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.aqz.AQZ;
import libs.aqz.volume.AQZArquivoInternamente;
import libs.aqz.AQZPasta;
import libs.aqz.volume.AQZPastas;
import libs.aqz.volume.AQZVolumes;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.meta_functional.AcaoAlfa;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;
import libs.tronarko.utils.TronarkoAleatorium;


public class TesteAQZVolumes {

    public static void init() {

        String arquivo_banco = "/home/luan/assets/Migratorium.az";

        //  AQZ.VOLUMES_ZERAR(arquivo_banco);

        AQZ.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco);

        AQZVolumes.VOLUMES_DUMP(arquivo_banco);
        fmt.print("Volume Blocos Livres :: {}", AQZVolumes.VOLUME_BLOCOS_LIVRES(arquivo_banco));

        AQZPastas.EXIBIR_PASTAS(arquivo_banco);

        AQZPasta dados_textos_objetos = new AQZPasta(arquivo_banco, "TextoObjetos");
        //   dados_assets.limpar_refs();

        // dados_textos_objetos.limpar();

        for (int indice = 1; indice <= 3; indice++) {

            Lista<Entidade> dados_do_objeto = CRIAR_CONJUNTO_DE_PESSOAS(indice);
            ENTT.EXIBIR_TABELA(dados_do_objeto);

            String nome_local = "@Cache/Objeto_" + fmt.zerado(indice, 10) + ".txt";

            byte[] arquivo_dados = ENTT.TO_DOCUMENTO_BYTES(dados_do_objeto);
            fmt.print("Criando arquivo indexado : {} -->> {}", nome_local, fmt.formatar_tamanho_precisao_dupla(arquivo_dados.length));
            boolean adicionado_indice = dados_textos_objetos.adicionar_ou_atualizar(nome_local, arquivo_dados);

        }


        //  dados_assets.dump();
        //  dados_assets.analisar_integridade();


        //Lista<Entidade> arquivos_dados = dados_assets.getTabelaDeArquivos();


        //dados_assets.remover("@Cache/Objeto_" + fmt.zerado(5, 10) + ".txt");

        // dados_assets.analisar_corrompidos();

        // String dados_1 = dados_assets.getTexto("@Cache/Objeto_0000000001.txt");
        // fmt.print("{}", dados_1);

        //  dados_assets.dump_estrutura_arquivo("@Cache/Objeto_0000000001.txt");

        dados_textos_objetos.fechar();


        AQZPasta dados_assets = new AQZPasta(arquivo_banco, "ASSETS");

        String imagem_grande = "/home/luan/Imagens/Screenshot_20240408_193134.png";
        dados_assets.adicionar_ou_atualizar("@Imagem/FundoVerde.png", Arquivador.GET_BYTES(imagem_grande));

        Opcional<Par<Entidade, AQZArquivoInternamente>> arq_fundo_verde = dados_assets.procurarArquivoInternamenteComInformacoes("@Imagem/FundoVerde.png");

        if (arq_fundo_verde.isOK()) {
            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(arq_fundo_verde.get().getChave()));

            //fmt.print("Nome : {} ->> {}", arq_mob.get().getNome(), fmt.formatar_tamanho_precisao_dupla(arq_mob.get().getTamanho()));

            Arquivador.CONSTRUIR_ARQUIVO("/home/luan/assets/tronarkum_arquivo_dentro.png", arq_fundo_verde.get().getValor().getBytes());
            // arq_mob.get().remover();

        }

        dados_assets.fechar();


        //  AQZ.VOLUMES_DUMP(arquivo_banco);
        fmt.print("Volume Blocos Livres :: {}", AQZVolumes.VOLUME_BLOCOS_LIVRES(arquivo_banco));


        AQZVolumes.VOLUMES_DUMP(arquivo_banco);
        //   AQZ.ARQUIVO_DUMP(arquivo_banco);

        //    AQZ.VOLUME_ANALISAR_INTEGRIDADE(arquivo_banco);


        //    ENTT.ORDENAR_LONG(arquivos_dados, "Tamanho");

        // ENTT.EXIBIR_TABELA_COM_TITULO(arquivos_dados, "TABELA DE ARQUIVOS");

    }

    public static void ver() {

        String arquivo_banco = "/home/luan/assets/tronarkum.az";

        //  AQZ.VOLUMES_ZERAR(arquivo_banco);

        AQZVolumes.VOLUMES_DUMP(arquivo_banco);
        fmt.print("Volume Blocos Livres :: {}", AQZVolumes.VOLUME_BLOCOS_LIVRES(arquivo_banco));


        AQZPasta dados_assets = new AQZPasta(arquivo_banco, "TextoObjetos");
        dados_assets.fechar();

        AQZVolumes.VOLUMES_DUMP(arquivo_banco);
        AQZVolumes.ARQUIVO_DUMP(arquivo_banco);

    }


    public static Lista<Entidade> CRIAR_CONJUNTO_DE_PESSOAS(int indice) {

        RefInt ref_indice = new RefInt(indice);


        Lista<Entidade> dados_do_objeto = ENTT.CRIAR_LISTA();

        Matematica.PARA_CADA(1, 100, new AcaoAlfa<Integer>() {
            @Override
            public void fazer(Integer numero) {
                Entidade e_objeto = ENTT.CRIAR_EM(dados_do_objeto, "ID", numero);
                e_objeto.at("Nome", PessoaNomeadorDeAkkax.get());
                e_objeto.at("DDN", new TronarkoAleatorium(Tronarko.CRIAR_TRON_HAZDE_ZERADO(1, 1, 6000), Tronarko.CRIAR_TRON_HAZDE_ZERADO(1, 1, 6500)).getTozte().getTextoZerado());
                e_objeto.at("DDM", StringTronarko.PARSER_TOZTE(e_objeto.at("DDN")).adicionar_Tronarko(Aleatorio.aleatorio_entre(1, 120)).getTextoZerado());
                e_objeto.at("Idade", Tronarko.TOZTE_DIFERENCA(StringTronarko.PARSER_TOZTE(e_objeto.at("DDM")), StringTronarko.PARSER_TOZTE(e_objeto.at("DDN"))) / 500);
                e_objeto.at("Olhos", Aleatorio.escolha_um(Vetor.CRIAR("Castanho", "Preto", "Amarelo", "Verde", "Azul", "Laranja", "Vermelho", "Violeta", "Rosa", "Branco")));

                Entidade obj_habilidades = ENTT.GET_SEMPRE(e_objeto.getEntidades(), "Status", "Habilidades");

                for (int habilidade = 1; habilidade <= Aleatorio.aleatorio(10); habilidade++) {

                    Entidade e_habilidade = ENTT.CRIAR_EM_SEQUENCIALMENTE(obj_habilidades.getEntidades(), "HabilidadeID");
                    e_habilidade.at("Med", Aleatorio.aleatorio(100));
                    e_habilidade.at("Min", Matematica.ate_zero(e_habilidade.atInt("Med") - Aleatorio.aleatorio(30)));
                    e_habilidade.at("Max", e_habilidade.atInt("Med") + Aleatorio.aleatorio(30));

                }


                Entidade obj_fatos = ENTT.GET_SEMPRE(e_objeto.getEntidades(), "Status", "Fatos");

                for (int fato = 1; fato <= Aleatorio.aleatorio(100); fato++) {
                    Entidade e_fato = ENTT.CRIAR_EM_SEQUENCIALMENTE(obj_fatos.getEntidades(), "FatoID");

                }

                Entidade obj_curiosidades = ENTT.GET_SEMPRE(e_objeto.getEntidades(), "Status", "Curiosidades");

                if (e_objeto.atInt("Idade") > 60) {

                    for (int fato = 1; fato <= 200 + Aleatorio.aleatorio(300); fato++) {
                        Entidade e_curiosidade = ENTT.CRIAR_EM_SEQUENCIALMENTE(obj_curiosidades.getEntidades(), "CuriosidadeID");
                        e_curiosidade.at("Valor", Aleatorio.escolha_um(Vetor.CRIAR("ALFA", "BETA", "GAMA", "DELTA", "ZETA", "OMEGA")) + " - " + Aleatorio.aleatorio(100));
                    }

                }


                if (ref_indice.get() % 3 == 0) {
                    obj_fatos.getEntidades().limpar();
                }

                if (ref_indice.get() % 4 == 0) {

                    obj_habilidades.getEntidades().limpar();
                    obj_fatos.getEntidades().limpar();
                    obj_curiosidades.getEntidades().limpar();
                }

                if (ref_indice.get() % 5 == 0) {
                    obj_curiosidades.getEntidades().limpar();
                }


                e_objeto.at("Habilidades", ENTT.CONTAGEM(obj_habilidades.getEntidades()));
                e_objeto.at("Fatos", ENTT.CONTAGEM(obj_fatos.getEntidades()));
                e_objeto.at("Curiosidades", ENTT.CONTAGEM(obj_curiosidades.getEntidades()));


            }
        });


        return dados_do_objeto;
    }
}
