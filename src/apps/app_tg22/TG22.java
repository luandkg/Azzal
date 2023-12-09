package apps.app_tg22;

import libs.arquivos.IO;
import libs.arquivos.PDF;
import libs.azzal.Renderizador;
import libs.luan.Lista;
import libs.luan.fmt;
import libs.dkg.DKG;
import libs.tronarko.Tronarko;
import libs.dkg.DKGObjeto;

import java.util.ArrayList;

import libs.tronarko.utils.IntTronarko;

import libs.tronarko.Tozte;

public class TG22 {

    public static void init() {

        Lista<Ficha> projeto = iniciar_projeto();
        Lista<Tozte> interrompidos = new Lista<Tozte>();
        Lista<Tozte> personal = new Lista<Tozte>();

        periodo(Tronarko.getData(15, 5, 2022), Tronarko.getData(29, 5, 2022), projeto, interrompidos);

        periodo(Tronarko.getData(20, 6, 2022), Tronarko.getData(22, 10, 2022), projeto, personal);

        cadastrar("43/10/7002", 1.60, 68.0, projeto);

        cadastrar("32/07/7002", 1.60, 71.0, projeto);

        cadastrar("44/06/7002", 1.60, 70.0, projeto);
        cadastrar("33/06/7002", 1.60, 70.0, projeto);
        cadastrar("21/06/7002", 1.60, 71.0, projeto);
        cadastrar("15/06/7002", 1.60, 71.0, projeto);
        cadastrar("05/06/7002", 1.60, 71.0, projeto);
        cadastrar("48/05/7002", 1.60, 72.0, projeto);
        cadastrar("41/05/7002", 1.60, 72.0, projeto);
        cadastrar("32/05/7002", 1.60, 74.0, projeto);
        cadastrar("20/05/7002", 1.60, 75.0, projeto);
        cadastrar("15/05/7002", 1.60, 76.0, projeto);
        cadastrar("06/05/7002", 1.60, 77.0, projeto);


        double META_PESO = Corpo.getPeso(60.0);
        double META_ALTURA = 1.60;


        mostrar(projeto, META_ALTURA, META_PESO);

        registrar(projeto, META_ALTURA, META_PESO);

        TronarkoTG22 t = new TronarkoTG22();

        Renderizador rr = Renderizador.construir(1600, 1300);


        t.draw(rr, toFicharios(projeto), interrompidos, personal, META_ALTURA, META_PESO);

        rr.exportarSemAlfa("/home/luan/Imagens/TG22.png");
        rr.exportarSemAlfa("/home/luan/Dropbox/TG22.png");

        IO.remova_se_existir("/home/luan/Dropbox/TG22.pdf");

        PDF.pngToPDF("/home/luan/Dropbox/TG22.png", "/home/luan/Dropbox/TG22.pdf");

        IO.remova_se_existir("/home/luan/Dropbox/TG22.png");


    }


    public static void cadastrar(String eTozte, double eAltura, double ePeso, Lista<Ficha> historico) {
        historico.adicionar(new Ficha(eTozte, Corpo.getAltura(eAltura), Corpo.getPeso(ePeso)));
    }

    public static Lista<Ficha> toFicharios(Lista<Ficha> historico) {
        Lista<Ficha> ret = new Lista<Ficha>();
        for (Ficha f : historico) {
            if (f.isFichario()) {
                ret.adicionar(f);
            }
        }
        return ret;
    }

    public static Lista<Ficha> toInterrompidos(Lista<Ficha> historico) {
        Lista<Ficha> ret = new Lista<Ficha>();
        for (Ficha f : historico) {
            if (!f.isFichario()) {
                ret.adicionar(f);
            }
        }
        return ret;
    }

    public static void periodo(Tozte eTozte, Tozte eFim, Lista<Ficha> historico, Lista<Tozte> interrompido) {

        Tozte passando = eTozte.getCopia();

        while (passando.isMenorIgualQue(eFim)) {
            historico.adicionar(new Ficha(passando.getTextoZerado()));
            interrompido.adicionar(passando.getCopia());
            passando = passando.adicionar_Superarko(1);
        }

    }


    public static Lista<Ficha> iniciar_projeto() {
        return new Lista<Ficha>();
    }

    public static void mostrar(Lista<Ficha> projeto, double META_ALTURA, double META_PESO) {

        Tronarko eTronarko = new Tronarko();

        String comecou = eTronarko.getData("03/01/2022").toString();

        System.out.println("");
        System.out.println("#################### EVOLUÇÃO ######################");
        System.out.println("");
        System.out.println("\t - META :: " + fmt.espacar_antes("" + fmt.f2zerado(META_PESO), 6) + " moz em ( " + fmt.doubleNumC2(Corpo.getNivel(META_PESO, Corpo.getAltura(META_ALTURA))) + " fuzz ) ");

        if (projeto.getQuantidade() > 0) {

            double peso_atual = projeto.get(0).getPeso();
            double peso_primeiro = projeto.get(projeto.getQuantidade() - 1).getPeso();

            System.out.println("\t - HOJE :: " + fmt.espacar_antes("" + peso_atual, 6) + " moz em ( " + fmt.doubleNumC2(Corpo.getNivel(peso_atual, Corpo.getAltura(META_ALTURA))) + " fuzz ) ");

            System.out.println("");


            if (peso_atual > META_PESO) {
                System.out.println("\t - OBJETIVO  :: FALTA " + fmt.doubleNumC2((peso_atual - META_PESO)) + " moz !");

                double diferenca = 0.0;
                String status = "";

                if (peso_primeiro > peso_atual) {
                    diferenca = peso_primeiro - peso_atual;
                    status = "DIMINUIU";
                } else if (peso_primeiro < peso_atual) {
                    diferenca = peso_atual - peso_primeiro;
                    status = "AUMENTOU";
                }

                System.out.println("\t - PROGRESSO :: " + status + " " + fmt.doubleNumC2(diferenca) + " moz !");

                System.out.println("\t - DURACAO   :: " + IntTronarko.getSuperarkosDiferenca(comecou, eTronarko.getTozte().toString()) + " superarkos !");

            } else {
                System.out.println("\t - OBJETIVO :: CONCLUIDO COM SUCESSO !");
            }

            System.out.println("");

            System.out.println("#################### ######### ######################");

            System.out.println("");

            System.out.println("HOJE :: " + eTronarko.getTozte().toString());
            System.out.println("");

            int ESPACAMENTO_RESULTADO = 22;

            double pesoInicio = projeto.get(projeto.getQuantidade() - 1).getPeso();

            for (Ficha eFicha : projeto) {

                long superarkos = IntTronarko.getSuperarkosDiferenca(eFicha.getTozte(), eTronarko.getTozte().toString());

                String faixa_temporal = getComConcordancia(superarkos, "superarko atrás", "superarkos atrás");


                System.out.println("TOZTE -->> " + eFicha.getTozte() + " :: " + faixa_temporal);
                System.out.println("\t - PESO        :: " + fmt.espacar_antes(fmt.doubleNumC2(eFicha.getPeso()) + " moz", ESPACAMENTO_RESULTADO));
                System.out.println("\t - ALTURA      :: " + fmt.espacar_antes(fmt.doubleNumC2(eFicha.getAltura()) + " tgz", ESPACAMENTO_RESULTADO));
                System.out.println("\t - NIVEL       :: " + fmt.espacar_antes(fmt.doubleNumC2(Corpo.getNivel(eFicha.getPeso(), eFicha.getAltura())) + " fuzz", ESPACAMENTO_RESULTADO));

                double agora_ficha = eFicha.getPeso();

                if (agora_ficha > META_PESO) {
                    System.out.println("\t - META        :: " + fmt.espacar_antes("FALTA " + fmt.doubleNumC2((agora_ficha - META_PESO)) + " moz", ESPACAMENTO_RESULTADO));


                    double diferenca = 0.0;
                    String status = "";

                    if (pesoInicio > agora_ficha) {
                        diferenca = pesoInicio - agora_ficha;
                        status = "MELHOROU";
                    } else if (pesoInicio < agora_ficha) {
                        diferenca = agora_ficha - pesoInicio;
                        status = "PIOROU";
                    }

                    if (diferenca > 0) {
                        System.out.println("\t - PROGRESSO   :: " + fmt.espacar_antes(status + " " + fmt.doubleNumC2(diferenca) + " moz", ESPACAMENTO_RESULTADO));
                    }


                }

                System.out.println("");

            }

        }


    }

    public static void registrar(Lista<Ficha> projeto, double META_ALTURA, double META_PESO) {

        DKG eDKG = new DKG();

        DKGObjeto pontos = eDKG.unicoObjeto("appTG22");

        pontos.identifique("META_ALTURA", META_ALTURA);
        pontos.identifique("META_PESO", META_PESO);

        for (Ficha eCorrente : projeto) {
            DKGObjeto objeto = pontos.criarObjeto("Ficha");

            objeto.identifique("Tozte", eCorrente.getTozte());
            objeto.identifique("Altura", eCorrente.getAltura());
            objeto.identifique("Peso", eCorrente.getPeso());

        }

        eDKG.salvar("res/TG22.dkg");

    }

    public static String getComConcordancia(long v, String singular, String plural) {
        String ret = "";
        if (v <= 1) {
            ret = v + " " + singular;
        } else {
            ret = v + " " + plural;
        }
        return ret;
    }

}
