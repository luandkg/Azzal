package apps.app_tozterum;

import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CNU {


    public static Lista<Entidade> OBTER_CRONOGRAMA(){

        Opcional<String> cnu_cronograma = Internet.GET_PAGINA_HTML_TIMEOUT("https://www.gov.br/gestao/pt-br/concursonacional/cronograma");

        Lista<Entidade> cronograma = ENTT.CRIAR_LISTA();

        if(cnu_cronograma.isOK()){

            Lista<String> linhas =  Strings.dividir_linhas_normalizadas(cnu_cronograma.get());

            for(Indexado<String> linha : Indexamento.indexe(linhas)){
                if(linha.index()>4 && linha.index()<linhas.getQuantidade()-5){

                    if(linhas.get(linha.index()-4).contains("/td")){

                        // fmt.print("----------------------");

                        String nome = Strings.GET_DEPOIS(linha.get(),">");
                        nome =  Strings.GET_ATE(nome,"<");
                        // fmt.print("\t ++ {}",nome);

                        String data = linhas.get(linha.index()+3).replace("<p class=\"dou-paragraph\"><","");
                        data = Strings.GET_DEPOIS(data,">");
                        data = data.replace("</strong>","");
                        data = data.replace("<strong>","");
                        data =  Strings.GET_ATE(data,"<");

                        // fmt.print("\t ++ {}",data);

                        if(!nome.isEmpty()){
                            Entidade e = ENTT.GET_SEMPRE(cronograma,"Evento",nome);
                            if(!data.isEmpty()){
                                e.at("Data",data);
                            }
                        }

                    }


                }
            }

        }

        //    ENTT.EXIBIR_TABELA(cronograma);

        return cronograma;
    }

    public static Lista<Entidade> GET_EVENTOS_HOJE(String evento_nessa_data){



        Lista<Entidade> cronograma = OBTER_CRONOGRAMA();

        Lista<Entidade> cronograma_v2 = ENTT.CRIAR_LISTA();

        Data hoje = Calendario.getDataHoje();


        for(Entidade e : cronograma){

            Entidade o = ENTT.CRIAR_EM(cronograma_v2,"ID",ENTT.CONTAGEM(cronograma_v2)+1);
            o.at("Data",e.at("Data"));
            o.at("DataInicio","");
            o.at("DataFim","");
            o.at("DataUnica","");
            o.at("Tozte","");
            o.at("Status","");

            o.at("Evento",e.at("Evento"));

            if(!o.at("Data").contains(" e ") && !o.at("Data").contains(" a ")) {

                String data_corrente  =Calendario.GET_DATA_DE_PORTUGUES(o.at("Data"));

                o.at("DataUnica", data_corrente);


            }else if(o.at("Data").contains(" a ")||o.at("Data").contains(" e ") ){

                String depois_de_a = Strings.GET_DEPOIS_DE_CONTAGEM(o.at("Data")," ",2);

                String data_corrente  =Calendario.GET_DATA_DE_PORTUGUES(depois_de_a);
                o.at("DataUnica", data_corrente);



                if(o.at("Data").contains(" e ")) {

                    String primeiro = Strings.GET_ATE(o.at("Data"), " ").trim();
                    String segundo = Strings.GET_ATE(Strings.GET_DEPOIS_DE_CONTAGEM(o.at("Data"), " ", 2), " ").trim();

                    String resto = Strings.GET_DEPOIS_DE_CONTAGEM(o.at("Data"), " ", 3);

                    o.at("DataInicio", primeiro);
                    o.at("DataFim", segundo);

                    String data_corrente1 = Calendario.GET_DATA_DE_PORTUGUES(primeiro + " " + resto);

                    Entidade e1 =o;
                    e1.at("Data", e.at("Data"));
                    e1.at("DataInicio", "");
                    e1.at("DataFim", "");

                    e1.at("DataUnica", "");
                    e1.at("Tozte", "");
                    e1.at("Evento", e.at("Evento"));
                    e1.at("DataUnica", data_corrente1);

                    String data_corrente2 = Calendario.GET_DATA_DE_PORTUGUES(segundo + " " + resto);

                    Entidade e2 = ENTT.CRIAR_EM(cronograma_v2);
                    e2.at("Data", e.at("Data"));
                    e2.at("DataInicio", "");
                    e2.at("DataFim", "");
                    e2.at("DataUnica", "");
                    e2.at("Tozte", "");

                    e2.at("Evento", e.at("Evento"));
                    e2.at("DataUnica", data_corrente2);

                    e1.at("DataInicio", data_corrente1);
                    e1.at("DataFim", data_corrente2);

                    e2.at("DataInicio", data_corrente1);
                    e2.at("DataFim", data_corrente2);


                }else                 if(o.at("Data").contains(" a ")){

                    String primeiro = Strings.GET_ATE(o.at("Data"), " ").trim();
                    String segundo = Strings.GET_ATE(Strings.GET_DEPOIS_DE_CONTAGEM(o.at("Data"), " ", 2), " ").trim();

                    String resto = Strings.GET_DEPOIS_DE_CONTAGEM(o.at("Data"), " ", 3);

                    String data_corrente1 = Calendario.GET_DATA_DE_PORTUGUES(primeiro + " " + resto);
                    String data_corrente2 = Calendario.GET_DATA_DE_PORTUGUES(segundo + " " + resto);

                    o.at("DataInicio", data_corrente1);
                    o.at("DataFim", data_corrente2);

                }


            }

        }

        ENTT.SEQUENCIAR(cronograma_v2,"ID",1);

        for(Entidade o : cronograma_v2) {
            Data data_essa = Data.toData(o.at("DataUnica"));
            if (data_essa.isMenor(hoje)) {
                o.at("Status", "PASSOU");
            } else if (data_essa.isIgual(hoje)) {
                o.at("Status", "HOJE");
            }
        }

            for(Entidade e : cronograma_v2) {
            Tozte tozte = Tronarko.getData(e.at("DataUnica"));
           e.at("Tozte",tozte.getTextoZerado());
        }

        ENTT.EXIBIR_TABELA(cronograma_v2);




        Lista<Entidade> eventos_hoje = new Lista<Entidade>();



            for (Entidade e : cronograma_v2){
                if (e.is("DataUnica",evento_nessa_data) || e.is("DataInicio",evento_nessa_data)|| e.is("DataFim",evento_nessa_data)){

                    eventos_hoje.adicionar(e);


                }
            }





        return eventos_hoje;
    }


    public static BufferedImage CRIAR_IMAGEM(String data,Entidade cnu){



        Cor COR_FUNDO = Cor.getHexCor("#66BB6A");
        Cor BRANCO = new Cor(255, 255, 255);

        Renderizador render =  Renderizador.CONSTRUIR(500, 500,COR_FUNDO);
        render.drawRect_Pintado(0, 0, 500, 500, COR_FUNDO);

        Fonte ft_branca_80 = new FonteRunTime(new Cor(255, 255, 255), 80);
        ft_branca_80.setRenderizador(render);

        ft_branca_80.escrevaCentralizado(500, 20, "CNU");

        Fonte ft_branca_30 = new FonteRunTime(new Cor(255, 255, 255), 30);
        ft_branca_30.setRenderizador(render);

        ft_branca_30.escrevaCentralizado(500, 130, Tronarko.getData(data).getTextoZerado());


        Fonte ft_branca_20 = new FonteRunTime(new Cor(255, 255, 255), 18);
        ft_branca_20.setRenderizador(render);

        int linhas = apps.app_tozterum.TextoJustificado.escrever(ft_branca_20,30,200,440,cnu.at("Evento"));

        fmt.print("L = {}",linhas);

        if ( linhas<4){

            BufferedImage img = Imagem.getImagem("BRASIL_LOGO.png");

            img = Imagem.redimensionador(img,3);
            img = img.getSubimage(100, 130,400, 100);

            render.drawImagemComAlfa(35,370,img);

        }

        // MARGENS

        render.drawRect_Pintado(0, 0, render.getLargura(), 10, BRANCO);
        render.drawRect_Pintado(0, render.getAltura() - 10, render.getLargura(), 10, BRANCO);

        render.drawRect_Pintado(0, 0, 10, render.getAltura(), BRANCO);
        render.drawRect_Pintado(render.getLargura() - 10, 0, 10, render.getAltura(), BRANCO);


     //   Imagem.exportar(render.toImagemSemAlfa(), GPOFE.GET_PASTA_DOCUMENTOS("coisas").getArquivo("CNU.png"));

        return render.toImagemSemAlfa();
    }

}
