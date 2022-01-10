package TG22;

import AppAzzal.TTY;
import DKG.DKG;
import Tronarko.Tronarko;
import Tronarko.StringTronarko;
import Tronarko.Intervalos.Tozte_Intervalo;
import Tronarko.Tronarko.Tozte;
import DKG.DKGObjeto;
import DKG.DKG;

import java.util.ArrayList;

public class TG22 {

    public static void init() {

        ArrayList<Ficha> projeto = iniciar_projeto();


        cadastrar("06/05/7002", 1.60, 77.0, projeto);


        mostrar(projeto);

        registrar(projeto);

    }


    public static void cadastrar(String eTozte, double eAltura, double ePeso, ArrayList<Ficha> historico) {
        historico.add(new Ficha(eTozte, Corpo.getAltura(eAltura), Corpo.getPeso(ePeso)));
    }

    public static String getDif(String t1, String t2) {

        StringTronarko st = new StringTronarko();

        Tozte r1 = new Tozte(Integer.parseInt(st.getSuperarko(t1)), Integer.parseInt(st.getHiperarko(t1)), Integer.parseInt(st.getTronarko(t1)));
        Tozte r2 = new Tozte(Integer.parseInt(st.getSuperarko(t2)), Integer.parseInt(st.getHiperarko(t2)), Integer.parseInt(st.getTronarko(t2)));

        Tozte_Intervalo intervalo = new Tozte_Intervalo("", r1, r2);

        return "" + intervalo.getSuperarkos();
    }

    public static ArrayList<Ficha> iniciar_projeto() {
        return new ArrayList<Ficha>();
    }

    public static void mostrar(ArrayList<Ficha> projeto) {

        Tronarko eTronarko = new Tronarko();

        String comecou = eTronarko.getData("03/01/2022").toString();
        double ideal = Corpo.getPeso(60.0);
        double ALTURA = 1.60;

        System.out.println("");
        System.out.println("#################### EVOLUÇÃO ######################");
        System.out.println("");
        System.out.println("\t - META :: " + TTY.espacar_antes("" + TTY.f2zerado(ideal), 6) + " moz em ( " + TTY.doubleNumC2(Corpo.getNivel(ideal, Corpo.getAltura(ALTURA))) + " UP ) ");

        double ultimo = projeto.get(0).getPeso();

        System.out.println("\t - HOJE :: " + TTY.espacar_antes("" + ultimo, 6) + " moz em ( " + TTY.doubleNumC2(Corpo.getNivel(ultimo, Corpo.getAltura(ALTURA))) + " UP ) ");

        System.out.println("");

        double agora = ultimo;

        if (agora > ideal) {
            System.out.println("\t - OBJETIVO :: FALTA " + TTY.doubleNumC2((agora - ideal)) + " moz !");
            System.out.println("\t - DURACAO  :: " + getDif(comecou, eTronarko.getTozte().toString()) + " superarkos !");
        } else {
            System.out.println("\t - OBJETIVO :: CONCLUIDO COM SUCESSO !");
        }

        System.out.println("");

        System.out.println("#################### ######### ######################");

        System.out.println("");

        System.out.println("HOJE :: " + eTronarko.getTozte().toString());
        System.out.println("");

        for (Ficha eFicha : projeto) {

            System.out.println("TOZTE -->> " + eFicha.getTozte() + " :: " + getDif(eFicha.getTozte(), eTronarko.getTozte().toString()) + " superarkos atrás");
            System.out.println("\t - PESO   :: " + TTY.espacar_antes(TTY.doubleNumC2(eFicha.getPeso()), 10) + " moz");
            System.out.println("\t - ALTURA :: " + TTY.espacar_antes(TTY.doubleNumC2(eFicha.getAltura()), 10) + " tgz");
            System.out.println("\t - NIVEL  :: " + TTY.espacar_antes(TTY.doubleNumC2(Corpo.getNivel(eFicha.getPeso(), eFicha.getAltura())), 10) + " UP");
            System.out.println("");

        }
    }

    public static void registrar(ArrayList<Ficha> projeto) {

        DKG eDKG = new DKG();

        DKGObjeto pontos = eDKG.unicoObjeto("TG22");

        for (Ficha eCorrente : projeto) {
            DKGObjeto objeto = pontos.criarObjeto("Ficha");

            objeto.identifique("Tozte", eCorrente.getTozte());
            objeto.identifique("Altura", eCorrente.getAltura());
            objeto.identifique("Peso", eCorrente.getPeso());

        }

        eDKG.salvar("res/TG22.dkg");

    }

}
