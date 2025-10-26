package apps.app_tozterum;



import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import apps.app_tozterum.stravamentos.StravaAtividadesClassificadas;
import apps.app_tozterum.stravamentos.StravaQ7;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;

import libs.luan.Lista;
import libs.luan.Opcional;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;
import libs.zetta.ZettaPasta;
import libs.zetta.ZettaPastas;
import libs.zetta.features.ZPFS;

import java.awt.image.BufferedImage;

public class BomDia {


    public static BufferedImage criarLuan(Lista<Entidade> previsao_do_tempo_brasilia_hoje, Lista<Entidade> previsao_do_tempo_sas_brasilia_hoje) {

        Tozte HOJE = Tronarko.getTozte();

        StravaAtividadesClassificadas strava_luan = StravaQ7.GET_LUAN_ATIVIDADES_CLASSIFICADAS_TRONARKO();


        Cor COR_FUNDO = Cor.getHexCor("#212121");
        Cor BRANCO = new Cor(255, 255, 255);

        Renderizador render = Renderizador.CONSTRUIR(500, 500, COR_FUNDO);
        render.drawRect_Pintado(0, 0, 500, 500, COR_FUNDO);

        Fonte ft_branca_80 = new FonteRunTime(new Cor(255, 255, 255), 80);
        ft_branca_80.setRenderizador(render);

        ft_branca_80.escrevaCentralizado(500, 20, Tronarko.getTozte().getHiperarko_Status().toString());

        Fonte ft_branca_30 = new FonteRunTime(new Cor(255, 255, 255), 30);
        ft_branca_30.setRenderizador(render);

        ft_branca_30.escrevaCentralizado(500, 130, Tronarko.getTozte().getTextoZerado());


        Lista<Cor> cores_marcadoras = new Lista<>();
        cores_marcadoras.adicionar(Cor.getHexCor("#76FF03"));
        cores_marcadoras.adicionar(Cor.getHexCor("#b71c1c"));
        cores_marcadoras.adicionar(Cor.getHexCor("#0d47a1"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ffd600"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ff6f00"));
        cores_marcadoras.adicionar(Cor.getHexCor("#bf360c"));

        int cor_marcador = 0;

        if (Tronarko.getTozte().getSuperarko() >= 1 && Tronarko.getTozte().getSuperarko() <= 10) {
            cor_marcador = 0;
        } else if (Tronarko.getTozte().getSuperarko() >= 11 && Tronarko.getTozte().getSuperarko() <= 20) {
            cor_marcador = 1;
        } else if (Tronarko.getTozte().getSuperarko() >= 21 && Tronarko.getTozte().getSuperarko() <= 30) {
            cor_marcador = 2;
        } else if (Tronarko.getTozte().getSuperarko() >= 31 && Tronarko.getTozte().getSuperarko() <= 40) {
            cor_marcador = 3;
        } else if (Tronarko.getTozte().getSuperarko() >= 41 && Tronarko.getTozte().getSuperarko() <= 50) {
            cor_marcador = 4;
        }

        int i = 0;
        int l = 0;

        int px_original = 30;

        int px = px_original;
        int py = 200;

        int QUAD = 40;
        int QUAD_EXPANDIDO = QUAD + 5;

        int superarko_numero = 1;

        while (i < Tronarko.SUPERARKOS_POR_HIPERARKO) {

            boolean teve_academia = false;
            boolean teve_corrida = false;
            boolean teve_aquatico = false;

            for (Entidade strava : strava_luan.getCorrida()) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == HOJE.getHiperarko() && eTozte.getTronarko() == HOJE.getTronarko()) {
                    teve_corrida = true;
                    break;
                }
            }

            for (Entidade strava : strava_luan.getAcademia()) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == HOJE.getHiperarko() && eTozte.getTronarko() == HOJE.getTronarko()) {
                    teve_academia = true;
                    break;
                }
            }

            for (Entidade strava : strava_luan.getAquaticos()) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == HOJE.getHiperarko() && eTozte.getTronarko() == HOJE.getTronarko()) {
                    teve_aquatico = true;
                    break;
                }
            }

            if (l == Tronarko.SUPERARKOS_POR_MEGARKO) {
                l = 0;
                px = px_original;
                py += QUAD_EXPANDIDO;
            }

            if (superarko_numero <= HOJE.getSuperarko()) {
                render.drawRect_Pintado(px, py, QUAD, QUAD, cores_marcadoras.get(cor_marcador));

                if (teve_corrida && !teve_academia) {

                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12, BRANCO);
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));

                } else if (!teve_corrida && teve_academia) {

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 6, 20, BRANCO);


                } else if (teve_corrida && teve_academia) {

                    int py_acima = py - 5;
                    int py_abaixo = py + 14;

                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 12, BRANCO);
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 6, 20, BRANCO);

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py_abaixo + (QUAD / 2), 20, 6, BRANCO);

                } else if (teve_aquatico) {

                    int py_acima = py - 10;
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 5, BRANCO);

                    render.drawRectCentralizado_Pintado(px + 20, py + 22, QUAD - 10, 5, BRANCO);
                    render.drawRectCentralizado_Pintado(px + 20, py + 30, QUAD - 10, 5, BRANCO);

                }


                if (teve_corrida) {
                    //  render.drawCirculo(px + (QUAD / 3), py + (QUAD / 3), QUAD / 3, QUAD / 3, BRANCO);
                    //  render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12, BRANCO);
                    //    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));
                }

                if (teve_academia) {
                    //    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 6, 20, BRANCO);
                    // render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12,6, BRANCO);
                }

            } else {
                render.drawRect_Pintado(px, py, QUAD, QUAD, BRANCO);
            }

            px += QUAD_EXPANDIDO;

            l += 1;
            i += 1;
            superarko_numero += 1;
        }

        int info_y = 450;


        Fonte f3 = new FonteRunTime(new Cor(255, 255, 255), 15);
        f3.setRenderizador(render);

        String sol_nascer = "";
        String sol_por = "";


        if (previsao_do_tempo_brasilia_hoje.getQuantidade() > 0) {
            Entidade previsao_do_tempo = previsao_do_tempo_brasilia_hoje.get(0);

            String nascer = previsao_do_tempo.at("Sol.nascer").replace("h", ":") + ":00";
            String por = previsao_do_tempo.at("Sol.por").replace("h", ":") + ":00";

            sol_nascer = Tronarko.getHora(nascer).getTextoSemUzzonZerado();
            sol_por = Tronarko.getHora(por).getTextoSemUzzonZerado();


        } else {

            if (previsao_do_tempo_sas_brasilia_hoje.getQuantidade() > 0) {
                Entidade previsao_do_tempo = previsao_do_tempo_sas_brasilia_hoje.get(0);

                sol_nascer = previsao_do_tempo.at("Nascer");
                sol_por = previsao_do_tempo.at("Por");

            }

        }

        f3.escreva(50, info_y, sol_nascer);
        f3.escreva(400, info_y, sol_por);

        // MARGENS

        render.drawRect_Pintado(0, 0, render.getLargura(), 10, BRANCO);
        render.drawRect_Pintado(0, render.getAltura() - 10, render.getLargura(), 10, BRANCO);

        render.drawRect_Pintado(0, 0, 10, render.getAltura(), BRANCO);
        render.drawRect_Pintado(render.getLargura() - 10, 0, 10, render.getAltura(), BRANCO);


        ZettaPastas zetta_pastas = new ZettaPastas(TelegramTozterum.GET_ARQUIVO_TOZTERUM());
        ZettaPasta tempo_icones = zetta_pastas.getPastaSempre("@TozterumArquivos::Icones");


        Opcional<BufferedImage> op_nascer = ZPFS.GET_IMAGEM_INTERNA(tempo_icones, "INMET_SOL.IM");
        Opcional<BufferedImage> op_por = ZPFS.GET_IMAGEM_INTERNA(tempo_icones, "INMET_LUA.IM");

        zetta_pastas.fechar();

        if (op_nascer.isOK()) {
            render.drawImagemComAlfa(25, 450, op_nascer.get());
        }
        if (op_por.isOK()) {
            render.drawImagemComAlfa(450, 450, op_por.get());
        }


        return render.toImagemSemAlfa();


    }




    public static BufferedImage criarGG(Lista<Entidade> previsao_do_tempo_brasilia_hoje, Lista<Entidade> previsao_do_tempo_sas_brasilia_hoje) {

        Tozte HOJE = Tronarko.getTozte();
        Lista<Entidade> strava_objetos_academia = StravaQ7.GET_GG_ACADEMIA_TRONARKO();
        Lista<Entidade> strava_objetos_corrida = StravaQ7.GET_GG_CORRIDA_TRONARKO();

        Cor COR_FUNDO = Cor.getHexCor("#212121");
        Cor BRANCO = new Cor(255, 255, 255);

        Renderizador render =  Renderizador.CONSTRUIR(500, 500, COR_FUNDO);
        render.drawRect_Pintado(0, 0, 500, 500, COR_FUNDO);

        Fonte ft_branca_80 = new FonteRunTime(new Cor(255, 255, 255), 80);
        ft_branca_80.setRenderizador(render);

        ft_branca_80.escrevaCentralizado(500, 20, Tronarko.getTozte().getHiperarko_Status().toString());

        Fonte ft_branca_30 = new FonteRunTime(new Cor(255, 255, 255), 30);
        ft_branca_30.setRenderizador(render);

        ft_branca_30.escrevaCentralizado(500, 130, Tronarko.getTozte().getTextoZerado());


        Lista<Cor> cores_marcadoras = new Lista<>();
        cores_marcadoras.adicionar(Cor.getHexCor("#76FF03"));
        cores_marcadoras.adicionar(Cor.getHexCor("#b71c1c"));
        cores_marcadoras.adicionar(Cor.getHexCor("#0d47a1"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ffd600"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ff6f00"));
        cores_marcadoras.adicionar(Cor.getHexCor("#bf360c"));

        int cor_marcador = 0;

        if (Tronarko.getTozte().getSuperarko() >= 1 && Tronarko.getTozte().getSuperarko() <= 10) {
            cor_marcador = 0;
        } else if (Tronarko.getTozte().getSuperarko() >= 11 && Tronarko.getTozte().getSuperarko() <= 20) {
            cor_marcador = 1;
        } else if (Tronarko.getTozte().getSuperarko() >= 21 && Tronarko.getTozte().getSuperarko() <= 30) {
            cor_marcador = 2;
        } else if (Tronarko.getTozte().getSuperarko() >= 31 && Tronarko.getTozte().getSuperarko() <= 40) {
            cor_marcador = 3;
        } else if (Tronarko.getTozte().getSuperarko() >= 41 && Tronarko.getTozte().getSuperarko() <= 50) {
            cor_marcador = 4;
        }

        int i = 0;
        int l = 0;

        int px_original = 30;

        int px = px_original;
        int py = 200;

        int QUAD = 40;
        int QUAD_EXPANDIDO = QUAD + 5;

        int superarko_numero = 1;

        while (i < 50) {

            boolean teve_academia = false;
            boolean teve_corrida = false;

            for (Entidade strava : strava_objetos_corrida) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == HOJE.getHiperarko() && eTozte.getTronarko() == HOJE.getTronarko()) {
                    teve_corrida = true;
                    break;
                }
            }

            for (Entidade strava : strava_objetos_academia) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == HOJE.getHiperarko() && eTozte.getTronarko() == HOJE.getTronarko()) {
                    teve_academia = true;
                    break;
                }
            }

            if (l == 10) {
                l = 0;
                px = px_original;
                py += QUAD_EXPANDIDO;
            }

            if (superarko_numero <= HOJE.getSuperarko()) {
                render.drawRect_Pintado(px, py, QUAD, QUAD, cores_marcadoras.get(cor_marcador));

                if (teve_corrida && !teve_academia) {

                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12, BRANCO);
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));

                } else if (!teve_corrida && teve_academia) {

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 6, 20, BRANCO);

                } else if (teve_corrida && teve_academia) {

                    int py_acima = py - 5;
                    int py_abaixo = py + 14;

                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 12, BRANCO);
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 6, 20, BRANCO);

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py_abaixo + (QUAD / 2), 20, 6, BRANCO);

                }


                if (teve_corrida) {
                    //  render.drawCirculo(px + (QUAD / 3), py + (QUAD / 3), QUAD / 3, QUAD / 3, BRANCO);
                    //  render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12, BRANCO);
                    //    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));
                }

                if (teve_academia) {
                    //    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 6, 20, BRANCO);
                    // render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12,6, BRANCO);
                }

            } else {
                render.drawRect_Pintado(px, py, QUAD, QUAD, BRANCO);
            }

            px += QUAD_EXPANDIDO;

            l += 1;
            i += 1;
            superarko_numero += 1;
        }

        int info_y = 450;


        Fonte f3 = new FonteRunTime(new Cor(255, 255, 255), 15);
        f3.setRenderizador(render);


        String sol_nascer = "";
        String sol_por = "";


        if (previsao_do_tempo_brasilia_hoje.getQuantidade() > 0) {
            Entidade previsao_do_tempo = previsao_do_tempo_brasilia_hoje.get(0);

            String nascer = previsao_do_tempo.at("Sol.nascer").replace("h", ":") + ":00";
            String por = previsao_do_tempo.at("Sol.por").replace("h", ":") + ":00";

            sol_nascer = Tronarko.getHora(nascer).getTextoSemUzzonZerado();
            sol_por = Tronarko.getHora(por).getTextoSemUzzonZerado();


        } else {

            if (previsao_do_tempo_sas_brasilia_hoje.getQuantidade() > 0) {
                Entidade previsao_do_tempo = previsao_do_tempo_sas_brasilia_hoje.get(0);

                sol_nascer = previsao_do_tempo.at("Nascer");
                sol_por = previsao_do_tempo.at("Por");

            }

        }

        f3.escreva(50, info_y, sol_nascer);
        f3.escreva(400, info_y, sol_por);


        // MARGENS

        render.drawRect_Pintado(0, 0, render.getLargura(), 10, BRANCO);
        render.drawRect_Pintado(0, render.getAltura() - 10, render.getLargura(), 10, BRANCO);

        render.drawRect_Pintado(0, 0, 10, render.getAltura(), BRANCO);
        render.drawRect_Pintado(render.getLargura() - 10, 0, 10, render.getAltura(), BRANCO);


        ZettaPastas zetta_pastas = new ZettaPastas(TelegramTozterum.GET_ARQUIVO_TOZTERUM());
        ZettaPasta tempo_icones = zetta_pastas.getPastaSempre("@TozterumArquivos::Icones");


        Opcional<BufferedImage> op_nascer = ZPFS.GET_IMAGEM_INTERNA(tempo_icones, "INMET_SOL.IM");
        Opcional<BufferedImage> op_por = ZPFS.GET_IMAGEM_INTERNA(tempo_icones, "INMET_LUA.IM");

        zetta_pastas.fechar();

        if (op_nascer.isOK()) {
            render.drawImagemComAlfa(25, 450, op_nascer.get());
        }
        if (op_por.isOK()) {
            render.drawImagemComAlfa(450, 450, op_por.get());
        }


        return render.toImagemSemAlfa();


    }


    public static Lista<String> GET_BOM_DIAS() {

        Lista<String> bom_dias = new Lista<String>();
        bom_dias.adicionar("Bom diaaaaaa");
        bom_dias.adicionar("Hora de acordar Luan");
        bom_dias.adicionar("Bora para mais um dia");
        bom_dias.adicionar("Solzão raiou, bom dia");
        bom_dias.adicionar("Tenha um excelente dia");
        bom_dias.adicionar("Hora de levantar da cama");
        bom_dias.adicionar("Bora para mais um dia");
        bom_dias.adicionar("Simbora levantar");
        bom_dias.adicionar("Deixa de preguiça, hora de lenvantar");
        bom_dias.adicionar("Esse soninho tá gostoso ein, mas tá na hora de acordar");
        bom_dias.adicionar("Tchutchuquinho, bora acordar");
        bom_dias.adicionar("Wake up, caralho");

        return bom_dias;
    }

    public static Lista<String> GET_BOM_DIAS_GG() {

        Lista<String> bom_dias = new Lista<String>();
        bom_dias.adicionar("Bom diaaaaaa");
        bom_dias.adicionar("Hora de acordar GG");
        bom_dias.adicionar("Bora para mais um dia");
        bom_dias.adicionar("Solzão raiou, bom dia");
        bom_dias.adicionar("Tenha um excelente dia");
        bom_dias.adicionar("Hora de levantar da cama");
        bom_dias.adicionar("Bora para mais um dia");
        bom_dias.adicionar("Simbora levantar");
        bom_dias.adicionar("Deixa de preguiça, hora de levantar");
        bom_dias.adicionar("Esse soninho tá gostoso ein, mas tá na hora de acordar");
        bom_dias.adicionar("Tchutchuquinho, bora acordar");
        bom_dias.adicionar("Wake up, caralho");

        return bom_dias;
    }


    public static BufferedImage RETROSPECTIVA(Tozte eTozteMarcado) {

        Lista<Entidade> strava_objetos_academia = StravaQ7.GET_LUAN_ACADEMIA_TRONARKO();
        Lista<Entidade> strava_objetos_corrida = StravaQ7.GET_LUAN_CORRIDA_TRONARKO();
        Lista<Entidade> strava_objetos_agua = StravaQ7.GET_LUAN_AQUATICO_TRONARKO();


        ENTT.EXIBIR_TABELA_COM_NOME(strava_objetos_academia, "ACADEMIA");
        ENTT.EXIBIR_TABELA_COM_NOME(strava_objetos_corrida, "CORRIDA");
        ENTT.EXIBIR_TABELA_COM_NOME(strava_objetos_agua, "AQUATICO");


        Cor COR_FUNDO = Cor.getHexCor("#212121");
        Cor BRANCO = new Cor(255, 255, 255);

        Renderizador render = Renderizador.CONSTRUIR(500, 500, COR_FUNDO);
        render.drawRect_Pintado(0, 0, 500, 500, COR_FUNDO);

        Fonte ft_branca_80 = new FonteRunTime(new Cor(255, 255, 255), 80);
        ft_branca_80.setRenderizador(render);

        ft_branca_80.escrevaCentralizado(500, 20, eTozteMarcado.getHiperarko_Status().toString());

        Fonte ft_branca_30 = new FonteRunTime(new Cor(255, 255, 255), 30);
        ft_branca_30.setRenderizador(render);

        ft_branca_30.escrevaCentralizado(500, 130, "#RETROSPECTIVA");


        Lista<Cor> cores_marcadoras = new Lista<>();
        cores_marcadoras.adicionar(Cor.getHexCor("#76FF03"));
        cores_marcadoras.adicionar(Cor.getHexCor("#b71c1c"));
        cores_marcadoras.adicionar(Cor.getHexCor("#0d47a1"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ffd600"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ff6f00"));
        cores_marcadoras.adicionar(Cor.getHexCor("#bf360c"));

        cores_marcadoras.adicionar(Cor.getHexCor("#76FF03"));
        cores_marcadoras.adicionar(Cor.getHexCor("#b71c1c"));
        cores_marcadoras.adicionar(Cor.getHexCor("#0d47a1"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ffd600"));
        cores_marcadoras.adicionar(Cor.getHexCor("#ff6f00"));
        cores_marcadoras.adicionar(Cor.getHexCor("#bf360c"));

        int cor_marcador = eTozteMarcado.getHiperarko() - 1;


        int i = 0;
        int l = 0;

        int px_original = 30;

        int px = px_original;
        int py = 200;

        int QUAD = 40;
        int QUAD_EXPANDIDO = QUAD + 5;

        int superarko_numero = 1;

        while (i < 50) {

            boolean teve_academia = false;
            boolean teve_corrida = false;
            boolean teve_aquatico = false;


            for (Entidade strava : strava_objetos_corrida) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == eTozteMarcado.getHiperarko() && eTozte.getTronarko() == eTozteMarcado.getTronarko()) {
                    teve_corrida = true;
                    break;
                }
            }

            for (Entidade strava : strava_objetos_academia) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == eTozteMarcado.getHiperarko() && eTozte.getTronarko() == eTozteMarcado.getTronarko()) {
                    teve_academia = true;
                    break;
                }
            }

            for (Entidade strava : strava_objetos_agua) {
                Tozte eTozte = StringTronarko.PARSER_TOZTE(strava.at("Tozte"));
                if (eTozte.getSuperarko() == superarko_numero && eTozte.getHiperarko() == eTozteMarcado.getHiperarko() && eTozte.getTronarko() == eTozteMarcado.getTronarko()) {
                    teve_aquatico = true;
                    break;
                }
            }

            if (l == 10) {
                l = 0;
                px = px_original;
                py += QUAD_EXPANDIDO;
            }

            if (superarko_numero <= eTozteMarcado.getSuperarko()) {
                render.drawRect_Pintado(px, py, QUAD, QUAD, cores_marcadoras.get(cor_marcador));

                if (teve_corrida && !teve_academia) {

                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12, BRANCO);
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));

                } else if (!teve_corrida && teve_academia) {

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 6, 20, BRANCO);

                } else if (teve_corrida && teve_academia) {

                    int py_acima = py - 5;
                    int py_abaixo = py + 14;

                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 12, BRANCO);
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 6, 20, BRANCO);

                    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py_abaixo + (QUAD / 2), 20, 6, BRANCO);

                } else if (teve_aquatico) {

                    int py_acima = py - 10;
                    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py_acima + (QUAD / 2), 5, BRANCO);

                    render.drawRectCentralizado_Pintado(px + 20, py + 22, QUAD - 10, 5, BRANCO);
                    render.drawRectCentralizado_Pintado(px + 20, py + 30, QUAD - 10, 5, BRANCO);

                }


                if (teve_corrida) {
                    //  render.drawCirculo(px + (QUAD / 3), py + (QUAD / 3), QUAD / 3, QUAD / 3, BRANCO);
                    //  render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12, BRANCO);
                    //    render.drawCirculoCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 8, cores_marcadoras.get(cor_marcador));
                }

                if (teve_academia) {
                    //    render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 6, 20, BRANCO);
                    // render.drawRectCentralizado_Pintado(px + (QUAD / 2), py + (QUAD / 2), 12,6, BRANCO);
                }

            } else {
                //   render.drawRect_Pintado(px, py, QUAD, QUAD, BRANCO);
            }

            px += QUAD_EXPANDIDO;

            l += 1;
            i += 1;
            superarko_numero += 1;
        }

        int info_y = 450;


        Fonte f3 = new FonteRunTime(new Cor(255, 255, 255), 15);
        f3.setRenderizador(render);

//        if (previsao_do_tempo_brasilia_hoje.getQuantidade() > 0) {
//            Entidade previsao_do_tempo = previsao_do_tempo_brasilia_hoje.get(0);
//
//
//            String nascer = previsao_do_tempo.at("Sol.nascer").replace("h", ":") + ":00";
//            String por = previsao_do_tempo.at("Sol.por").replace("h", ":") + ":00";
//
//
//            f3.escreva(50, info_y, Tronarko.getHora(nascer).getTextoSemUzzonZerado());
//            f3.escreva(400, info_y, Tronarko.getHora(por).getTextoSemUzzonZerado());
//
//        }


        // MARGENS

        render.drawRect_Pintado(0, 0, render.getLargura(), 10, BRANCO);
        render.drawRect_Pintado(0, render.getAltura() - 10, render.getLargura(), 10, BRANCO);

        render.drawRect_Pintado(0, 0, 10, render.getAltura(), BRANCO);
        render.drawRect_Pintado(render.getLargura() - 10, 0, 10, render.getAltura(), BRANCO);


        ZettaPastas zetta_pastas = new ZettaPastas(TelegramTozterum.GET_ARQUIVO_TOZTERUM());
        ZettaPasta tempo_icones = zetta_pastas.getPastaSempre("@TozterumArquivos::Icones");


        Opcional<BufferedImage> op_nascer = ZPFS.GET_IMAGEM_INTERNA(tempo_icones, "INMET_SOL.IM");
        Opcional<BufferedImage> op_por = ZPFS.GET_IMAGEM_INTERNA(tempo_icones, "INMET_LUA.IM");

        zetta_pastas.fechar();

        if(op_nascer.isOK()){
            render.drawImagemComAlfa(25, 450, op_nascer.get());
        }

        if(op_por.isOK()){
            render.drawImagemComAlfa(450, 450, op_por.get());
        }



        return render.toImagemSemAlfa();


    }


}
