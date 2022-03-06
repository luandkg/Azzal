package TG22;

import Azzal.Renderizador;
import Luan.STTY;
import DKG.DKG;
import Tronarko.Tronarko;
import Tronarko.StringTronarko;
import Tronarko.Intervalos.Tozte_Intervalo;
import Tronarko.Tozte;
import DKG.DKGObjeto;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import Tronarko.IntTronarko;

public class TG22 {

    public static void init() {

        ArrayList<Ficha> projeto = iniciar_projeto();

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

        BufferedImage meu_projeto = new BufferedImage(1600, 1100, BufferedImage.TYPE_INT_RGB);
        Renderizador rr = new Renderizador(meu_projeto);

        t.draw(rr, projeto, META_ALTURA, META_PESO);

        rr.exportarSemAlfa("/home/luan/Imagens/TG22.png");
        rr.exportarSemAlfa("/home/luan/Dropbox/TG22.png");

        pngToPDF("/home/luan/Dropbox/TG22.png", "/home/luan/Dropbox/TG22.pdf");

        if (new File("/home/luan/Dropbox/TG22.png").exists()) {
            new File("/home/luan/Dropbox/TG22.png").delete();
        }

    }

    private static String as_string(String a) {
        return "'" + a + "'";
    }

    private static void pngToPDF(String eFonte, String eDestino) {

        String eComando = "img2pdf " + as_string(eFonte) + " -o " + as_string(eDestino);

        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(eComando);

        BufferedReader br = null;
        String saida = "";

        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process;
            process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                saida += "Retorno do comando = [" + line + "]\n";
            }
        } catch (IOException ioe) {

        } finally {

        }

    }

    public static void cadastrar(String eTozte, double eAltura, double ePeso, ArrayList<Ficha> historico) {
        historico.add(new Ficha(eTozte, Corpo.getAltura(eAltura), Corpo.getPeso(ePeso)));
    }


    public static ArrayList<Ficha> iniciar_projeto() {
        return new ArrayList<Ficha>();
    }

    public static void mostrar(ArrayList<Ficha> projeto, double META_ALTURA, double META_PESO) {

        Tronarko eTronarko = new Tronarko();

        String comecou = eTronarko.getData("03/01/2022").toString();

        System.out.println("");
        System.out.println("#################### EVOLUÇÃO ######################");
        System.out.println("");
        System.out.println("\t - META :: " + STTY.espacar_antes("" + STTY.f2zerado(META_PESO), 6) + " moz em ( " + STTY.doubleNumC2(Corpo.getNivel(META_PESO, Corpo.getAltura(META_ALTURA))) + " fuzz ) ");

        if (projeto.size() > 0) {

            double peso_atual = projeto.get(0).getPeso();
            double peso_primeiro = projeto.get(projeto.size() - 1).getPeso();

            System.out.println("\t - HOJE :: " + STTY.espacar_antes("" + peso_atual, 6) + " moz em ( " + STTY.doubleNumC2(Corpo.getNivel(peso_atual, Corpo.getAltura(META_ALTURA))) + " fuzz ) ");

            System.out.println("");


            if (peso_atual > META_PESO) {
                System.out.println("\t - OBJETIVO  :: FALTA " + STTY.doubleNumC2((peso_atual - META_PESO)) + " moz !");

                double diferenca = 0.0;
                String status = "";

                if (peso_primeiro > peso_atual) {
                    diferenca = peso_primeiro - peso_atual;
                    status = "DIMINUIU";
                } else if (peso_primeiro < peso_atual) {
                    diferenca = peso_atual - peso_primeiro;
                    status = "AUMENTOU";
                }

                System.out.println("\t - PROGRESSO :: " + status + " " + STTY.doubleNumC2(diferenca) + " moz !");

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

            double pesoInicio = projeto.get(projeto.size() - 1).getPeso();

            for (Ficha eFicha : projeto) {

                long superarkos = IntTronarko.getSuperarkosDiferenca(eFicha.getTozte(), eTronarko.getTozte().toString());

                String faixa_temporal = getComConcordancia(superarkos, "superarko atrás", "superarkos atrás");


                System.out.println("TOZTE -->> " + eFicha.getTozte() + " :: " + faixa_temporal);
                System.out.println("\t - PESO        :: " + STTY.espacar_antes(STTY.doubleNumC2(eFicha.getPeso()) + " moz", ESPACAMENTO_RESULTADO));
                System.out.println("\t - ALTURA      :: " + STTY.espacar_antes(STTY.doubleNumC2(eFicha.getAltura()) + " tgz", ESPACAMENTO_RESULTADO));
                System.out.println("\t - NIVEL       :: " + STTY.espacar_antes(STTY.doubleNumC2(Corpo.getNivel(eFicha.getPeso(), eFicha.getAltura())) + " fuzz", ESPACAMENTO_RESULTADO));

                double agora_ficha = eFicha.getPeso();

                if (agora_ficha > META_PESO) {
                    System.out.println("\t - META        :: " + STTY.espacar_antes("FALTA " + STTY.doubleNumC2((agora_ficha - META_PESO)) + " moz", ESPACAMENTO_RESULTADO));


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
                        System.out.println("\t - PROGRESSO   :: " + STTY.espacar_antes(status + " " + STTY.doubleNumC2(diferenca) + " moz", ESPACAMENTO_RESULTADO));
                    }


                }

                System.out.println("");

            }

        }


    }

    public static void registrar(ArrayList<Ficha> projeto, double META_ALTURA, double META_PESO) {

        DKG eDKG = new DKG();

        DKGObjeto pontos = eDKG.unicoObjeto("TG22");

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
