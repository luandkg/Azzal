package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_humanidade.Idioma;
import apps.app_humanidade.idiomas.*;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Linha;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;

public class AtzumSociedades {

    public static void INIT() {

        Renderizador mapa_de_contorno = new Renderizador(AtzumCreator.GET_MAPA_DE_CONTORNO());

        Lista<Entidade> sociedades = Atzum.GET_SOCIEDADES();

        Cores mCores = new Cores();

        for (Ponto cidade : Atzum.GET_CIDADES()) {
            mapa_de_contorno.drawCirculoCentralizado_Pintado(cidade.getX(), cidade.getY(), 5, mCores.getBranco());
        }


        for (Entidade so : sociedades) {
            Cor cor = Cor.getHexCor(so.at("Cor"));

            Lista<Ponto> locais = new Lista<Ponto>();
            Lista<Ponto> locais1 = new Lista<Ponto>();
            Lista<Ponto> locais2 = new Lista<Ponto>();

            for (String local : Strings.DIVIDIR_ESPACOS(so.at("Local"))) {
                int px = Integer.parseInt(Strings.GET_ATE(local, ":"));
                int py = Integer.parseInt(Strings.GET_DEPOIS(local, ":"));

                mapa_de_contorno.drawCirculoCentralizado_Pintado(px, py, 20, cor);

                fmt.print("{}--{}", px, py);
                locais.adicionar(new Ponto(px, py));
            }

            for (String local : Strings.DIVIDIR_ESPACOS(so.at("Local1"))) {
                int px = Integer.parseInt(Strings.GET_ATE(local, ":"));
                int py = Integer.parseInt(Strings.GET_DEPOIS(local, ":"));

                mapa_de_contorno.drawCirculoCentralizado_Pintado(px, py, 20, cor);

                fmt.print("{}--{}", px, py);
                locais1.adicionar(new Ponto(px, py));
            }

            for (String local : Strings.DIVIDIR_ESPACOS(so.at("Local2"))) {
                int px = Integer.parseInt(Strings.GET_ATE(local, ":"));
                int py = Integer.parseInt(Strings.GET_DEPOIS(local, ":"));

                mapa_de_contorno.drawCirculoCentralizado_Pintado(px, py, 20, cor);

                fmt.print("{}--{}", px, py);
                locais2.adicionar(new Ponto(px, py));
            }

            marcar_delimitadores_de_sociedade(mapa_de_contorno, cor, locais);
            marcar_delimitadores_de_sociedade(mapa_de_contorno, cor, locais1);
            marcar_delimitadores_de_sociedade(mapa_de_contorno, cor, locais2);


        }

        Imagem.exportar(mapa_de_contorno.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("sociedades/mapa_sociedades_v1.png"));

        MARCAR_CIDADES();
    }

    public static void MARCAR_CIDADES() {

        Renderizador mapa_de_cidades = new Renderizador(AtzumCreator.GET_MAPA_DE_CONTORNO());

        Lista<Entidade> sociedades = Atzum.GET_SOCIEDADES();

        Cores mCores = new Cores();


        Lista<Entidade> e_cidades = new Lista<Entidade>();

        for (Ponto cidade : Atzum.GET_CIDADES()) {
            mapa_de_cidades.drawCirculoCentralizado_Pintado(cidade.getX(), cidade.getY(), 5, mCores.getBranco());

            boolean tem_proximo = false;
            double ponto_proximo = 0.0;
            Cor cor_proximo = null;
            String sociedade_proximo = "";


            for (Entidade so : sociedades) {
                Cor cor = Cor.getHexCor(so.at("Cor"));

                Lista<Ponto> locais = new Lista<Ponto>();
                Lista<Ponto> locais1 = new Lista<Ponto>();
                Lista<Ponto> locais2 = new Lista<Ponto>();

                for (String local : Strings.DIVIDIR_ESPACOS(so.at("Local"))) {
                    int px = Integer.parseInt(Strings.GET_ATE(local, ":"));
                    int py = Integer.parseInt(Strings.GET_DEPOIS(local, ":"));

                    //  mapa_de_cidades.drawCirculoCentralizado_Pintado(px, py, 20, cor);

                    //  fmt.print("{}--{}", px, py);
                    locais.adicionar(new Ponto(px, py));
                }

                for (String local : Strings.DIVIDIR_ESPACOS(so.at("Local1"))) {
                    int px = Integer.parseInt(Strings.GET_ATE(local, ":"));
                    int py = Integer.parseInt(Strings.GET_DEPOIS(local, ":"));

                    //  mapa_de_cidades.drawCirculoCentralizado_Pintado(px, py, 20, cor);

                    // fmt.print("{}--{}", px, py);
                    locais1.adicionar(new Ponto(px, py));
                }

                for (String local : Strings.DIVIDIR_ESPACOS(so.at("Local2"))) {
                    int px = Integer.parseInt(Strings.GET_ATE(local, ":"));
                    int py = Integer.parseInt(Strings.GET_DEPOIS(local, ":"));

                    //   mapa_de_cidades.drawCirculoCentralizado_Pintado(px, py, 20, cor);

                    // fmt.print("{}--{}", px, py);
                    locais2.adicionar(new Ponto(px, py));
                }

                Lista<Ponto> lpontos = new Lista<Ponto>();
                lpontos.adicionar_varios(obter_delimitadores_de_sociedade(locais));
                lpontos.adicionar_varios(obter_delimitadores_de_sociedade(locais1));
                lpontos.adicionar_varios(obter_delimitadores_de_sociedade(locais2));

                for (Ponto marcador_proximo : lpontos) {

                    double distancia = Espaco2D.distancia_entre_pontos(cidade, marcador_proximo);

                    if (tem_proximo) {
                        if (distancia < ponto_proximo) {
                            tem_proximo = true;
                            ponto_proximo = distancia;
                            cor_proximo = cor;
                            sociedade_proximo = so.at("Nome");
                        }
                    } else {
                        tem_proximo = true;
                        ponto_proximo = distancia;
                        cor_proximo = cor;
                        sociedade_proximo = so.at("Nome");
                    }

                }


            }

            Entidade e_cidade = ENTT.CRIAR_EM(e_cidades);
            e_cidade.at("X", cidade.getX());
            e_cidade.at("Y", cidade.getY());
            e_cidade.at("CidadeNome", "");


            if (tem_proximo) {
                mapa_de_cidades.drawCirculoCentralizado_Pintado(cidade.getX(), cidade.getY(), 5, cor_proximo);
                e_cidade.at("Sociedade", sociedade_proximo);
            }
        }

        Lista<Entidade> cidades_dados = Atzum.GET_CIDADES_NOMES();

        Unico<String> cidades_nomes_validados = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade e_cidade : e_cidades) {
            Opcional<Entidade> op_cidade = ENTT.GET_OPCIONAL(cidades_dados, "Cidade", e_cidade.at("X") + "::" + e_cidade.at("Y"));
            if (op_cidade.isOK()) {
                e_cidade.at("CidadeNome", op_cidade.get().at("Nome"));
            }

            Lista<Idioma> idiomas = new Lista<Idioma>();

            idiomas.adicionar(new IdiomaTraddes());
            idiomas.adicionar(new IdiomaMokkom());
            idiomas.adicionar(new IdiomaRequiz());
            idiomas.adicionar(new IdiomaPlaque());
            idiomas.adicionar(new IdiomaDommus());
            idiomas.adicionar(new IdiomaAlkoz());
            idiomas.adicionar(new IdiomaInmeb());
            idiomas.adicionar(new IdiomaUppuma());
            idiomas.adicionar(new IdiomaUttunque());


            if (e_cidade.atributo_valor_valido("Sociedade") && e_cidade.atributo_valor_valido("CidadeNome")) {

                for (Idioma id : idiomas) {
                    if (id.getNome().contentEquals(e_cidade.at("Sociedade"))) {
                        e_cidade.at("TemIdioma", "SIM");
                        //  e_cidade.at("PalavraValida",id.pertence_frase(e_cidade.at("CidadeNome"))+"");

                        String nome = id.getUnico(cidades_nomes_validados);
                        e_cidade.at("NovoNome", nome);

                        while (nome.length() > 10) {
                            nome = id.getUnico(cidades_nomes_validados);
                            e_cidade.at("NovoNome", nome);
                        }

                    }
                }

                e_cidade.at("NovoNome", Strings.Capitalizar(e_cidade.at("NovoNome")));

            }

        }

        ENTT.EXIBIR_TABELA(e_cidades);

        ENTT.ATRIBUTO_REMOVER(e_cidades, "CidadeNome");
        ENTT.ATRIBUTO_REMOVER(e_cidades, "TemIdioma");

        ENTT.AT_ALTERAR_NOME(e_cidades, "NovoNome", "CidadeNome");

        ENTT.EXIBIR_TABELA(e_cidades);

        Imagem.exportar(mapa_de_cidades.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("sociedades/mapa_sociedades_v2.png"));
        ENTT.GUARDAR(e_cidades, AtzumCreator.LOCAL_GET_ARQUIVO("sociedades/cidades.entts"));

    }


    public static void marcar_delimitadores_de_sociedade(Renderizador mapa_de_contorno, Cor cor, Lista<Ponto> pontos_locais) {

        for (Ponto a : pontos_locais) {
            for (Ponto b : pontos_locais) {
                mapa_de_contorno.drawLinha(a.getX(), a.getY(), b.getX(), b.getY(), cor);


                Lista<Ponto> linha = new Linha(a.getX(), a.getY(), b.getX(), b.getY()).getPontos_A();

                if (linha.possuiObjetos()) {
                    int div = linha.getQuantidade() / 4;

                    mapa_de_contorno.drawCirculoCentralizado_Pintado(linha.get(div).getX(), linha.get(div).getY(), 5, cor);
                    mapa_de_contorno.drawCirculoCentralizado_Pintado(linha.get(div * 2).getX(), linha.get(div * 2).getY(), 5, cor);
                    mapa_de_contorno.drawCirculoCentralizado_Pintado(linha.get(div * 3).getX(), linha.get(div * 3).getY(), 5, cor);

                }

            }
        }

    }

    public static Lista<Ponto> obter_delimitadores_de_sociedade(Lista<Ponto> pontos_locais) {

        Lista<Ponto> pontos_esses = new Lista<Ponto>();

        for (Ponto a : pontos_locais) {
            for (Ponto b : pontos_locais) {

                pontos_esses.adicionar(a);

                Lista<Ponto> linha = new Linha(a.getX(), a.getY(), b.getX(), b.getY()).getPontos_A();

                if (linha.possuiObjetos()) {
                    int div = linha.getQuantidade() / 4;

                    pontos_esses.adicionar(linha.get(div));
                    pontos_esses.adicionar(linha.get(div * 2));
                    pontos_esses.adicionar(linha.get(div * 3));

                }

            }
        }

        return pontos_esses;
    }

    public static void VER_CIDADES() {

        Lista<Entidade> e_cidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("sociedades/cidades.entts"));
        Lista<Entidade> p_cidades = Atzum.GET_CIDADES_NOMES();

        ENTT.EXIBIR_TABELA(p_cidades);
        ENTT.EXIBIR_TABELA(e_cidades);

        for (Entidade e : e_cidades) {
            e.at("CidadeXY", e.at("X") + "::" + e.at("Y"));
        }

        for (Entidade e : p_cidades) {
            e.at("Nome", ENTT.GET_SEMPRE(e_cidades, "CidadeXY", e.at("X") + "::" + e.at("Y")).at("CidadeNome"));
            e.at("Sociedade", ENTT.GET_SEMPRE(e_cidades, "CidadeXY", e.at("X") + "::" + e.at("Y")).at("Sociedade"));
        }

        ENTT.EXIBIR_TABELA(p_cidades);

        ENTT.GUARDAR(p_cidades, Atzum.GET_LOCAL() + "parametros/CIDADES_NOMES.entts");

    }
}
