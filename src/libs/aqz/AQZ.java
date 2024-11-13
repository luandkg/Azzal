package libs.aqz;

import libs.aqz.extincao.AZInternamenteAntigamente;
import libs.aqz.extincao.ItemDoBancoEmExtincao;
import libs.armazenador.ParticaoEmExtincao;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;

import java.nio.charset.StandardCharsets;

public class AQZ {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 04
    // ATUALIZADO : 2024 01 04

    public static void EXIBIR_COLECAO_PRIMARIA(String arquivo_banco, String colecao_nome) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoEmExtincao item : aqz.primarios_colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }


    public static Lista<Entidade> COLECAO_ENTIDADES(String arquivo_banco, String colecao_nome) {
        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);
        Lista<Entidade> dados = aqz.colecao_orgarnizar_e_obter(colecao_nome).getObjetos();
        aqz.fechar();

        return dados;
    }

    public static void AUTO_ANALISAR(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        aqz.auto_analisar();

        aqz.fechar();

    }

    public static void ANALISAR(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        aqz.analisar();

        aqz.fechar();

    }


    public static void EXIBIR_TUDO(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        for (ParticaoEmExtincao particaoEmExtincao : aqz.particoes_primarias()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBancoEmExtincao item : particaoEmExtincao.getItens()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "INTERNO - " + particaoEmExtincao.getNome());

        }


        for (ParticaoEmExtincao particaoEmExtincao : aqz.colecoes_listar()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBancoEmExtincao item : particaoEmExtincao.getItens()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "PUBLICO - " + particaoEmExtincao.getNome());

        }


        aqz.fechar();

    }


    public static void EXIBIR_ESTRUTURA_INTERNA(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> entidades_exibir = new Lista<Entidade>();

        for (ParticaoEmExtincao particao_primaria : aqz.particoes_primarias()) {
            Entidade particao = new Entidade();

            particao.at("Nome", particao_primaria.getNome());
            particao.at("Itens", particao_primaria.getItensContagem());
            particao.at("Usabilidade", particao_primaria.getUsabilidade());
            particao.at("Disponibilidade", particao_primaria.getDisponibilidade());
            particao.at("Tamanho", fmt.formatar_tamanho(particao_primaria.getTamanho()));

            entidades_exibir.adicionar(particao);
        }


        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(entidades_exibir, "ESTRUTURA INTERNA - PARTIÇÕES PRIMÁRIAS");


    }

    public static void EXIBIR_ESTRUTURA_PUBLICA(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (ParticaoEmExtincao item : aqz.colecoes_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "ESTRUTURA PÚBLICA");


    }

    public static void EXIBIR_ESTRUTURA_PUBLICA_DETALHADA(String arquivo_banco) {

        AZInternamenteAntigamente aqz = new AZInternamenteAntigamente(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();


        for (ParticaoEmExtincao particaoEmExtincao : aqz.colecoes_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", particaoEmExtincao.getNome());
            banco_item.at("Itens", particaoEmExtincao.getItensContagem());
            banco_item.at("Usabilidade", particaoEmExtincao.getUsabilidade());
            banco_item.at("Disponibilidade", particaoEmExtincao.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(particaoEmExtincao.getTamanho()));

            boolean isPrimeiro = true;
            long menor = 0;
            long maior = 0;

            for (ItemDoBancoEmExtincao item : particaoEmExtincao.getItens()) {
                String item_dados = item.lerTexto();
                if (isPrimeiro) {
                    long tam = item_dados.getBytes(StandardCharsets.UTF_8).length;
                    menor = tam;
                    maior = tam;
                } else {
                    long tam = item_dados.getBytes(StandardCharsets.UTF_8).length;
                    if (tam < menor) {
                        menor = tam;
                    }
                    if (tam > maior) {
                        maior = tam;
                    }
                }
                isPrimeiro = false;
            }

            banco_item.at("Item.menor.tamanho", menor);
            banco_item.at("Item.menor.tamanho_formatado", fmt.formatar_tamanho_precisao_dupla(menor));
            banco_item.at("Item.maior.tamanho", maior);
            banco_item.at("Item.maior.tamanho_formatado", fmt.formatar_tamanho_precisao_dupla(maior));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "ESTRUTURA PÚBLICA");


    }


}
