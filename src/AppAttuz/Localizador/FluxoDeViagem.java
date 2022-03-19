package AppAttuz.Localizador;

import DKG.DKGObjeto;
import Luan.STTY;
import Luan.TTY;
import Tronarko.IntTronarko;
import Tronarko.StringTronarko;
import Tronarko.Tozte;

import java.util.ArrayList;

public class FluxoDeViagem {

    public String viagem_comecou = "";
    public String viagem_terminou = "";

    public String viagem_comecou_posicao = "";
    public String viagem_terminou_posicao = "";


    public String passeio_comecou = "";
    public String passeio_terminou = "";

    public boolean jaInicouViagem = false;

    public ArrayList<String> viagem = new ArrayList<String>();
    public ArrayList<String> acampamentos = new ArrayList<String>();
    public ArrayList<String> viagem_hoje = new ArrayList<String>();

    public String viagem_anterior = "";

    public int andando_tempo = 0;
    public int acampamento_tempo = 0;
    public int andando_tempo_pos_acampamento = 0;

    public String cidade_anterior = "";
    public String desde = "";

    public void mostrarViagem() {

        System.out.println("\t      Terminar Viagem :: " + viagem_terminou + " -->> " + viagem_terminou_posicao);
        mostrarViagemAtual();

    }

    public void mostrarViagemAtual() {

        int viagem_percurso = getPercurso(viagem);
        int passos_percurso = getPassos(viagem);


        int hoje_percurso = getPercurso(viagem_hoje);
        int passos_hoje = getPassos(viagem_hoje);


        System.out.println("\t      Viagem Duracao      -->> " + IntTronarko.intervalo(viagem_comecou, viagem_terminou));
        System.out.println("\t      Viagem Pontos       -->> " + viagem.size());
        System.out.println("\t      Acampamentos        -->> " + acampamentos.size());
        System.out.println("\t      Percurso            -->> " + viagem_percurso + " Stgz");
        System.out.println("\t      Passos Percurso     -->> " + passos_percurso + " passos");

        System.out.println("\t      Percurso HOJE       -->> " + hoje_percurso + " Stgz");
        System.out.println("\t      Passos HOJE         -->> " + passos_hoje + " passos");

        System.out.println("\t      Tempo Andando       -->> " + andando_tempo + " ittas");
        System.out.println("\t      Tempo Descansando   -->> " + acampamento_tempo + " ittas");
        System.out.println("\t      Tempo da Viagem     -->> " + (acampamento_tempo + andando_tempo) + " ittas");

        if (andando_tempo > 0) {
            double velocidade = (double) viagem_percurso / (double) andando_tempo;
            System.out.println("\t      Velocidade          -->> " + STTY.doubleNumC3(velocidade) + " Stgz/ittas");

            double velocidade_porArko = (double) viagem_percurso / (double) (andando_tempo / 100.0);
            System.out.println("\t      Velocidade          -->> " + STTY.doubleNumC3(velocidade_porArko) + " Stgz/Arko");

        }
    }

    public void mostrarPasseio() {

        System.out.println("\t      Passeio Inicio :: " + passeio_comecou);
        System.out.println("\t      Passeio Fim    :: " + passeio_terminou);

        System.out.println("\t      Passeio Duracao -->> " + IntTronarko.intervalo(passeio_comecou, passeio_terminou));

    }


    public void emFluxo(DKGObjeto ePonto, Tozte eHoje) {

        if (ePonto.existeIdentificador("Cidade")) {

            if (!cidade_anterior.contentEquals(ePonto.identifique("Cidade").getValor())) {

                if (jaInicouViagem) {

                    viagem_terminou = ePonto.identifique("Tozte").getValor();
                    viagem_terminou_posicao = ePonto.identifique("x").getValor() + "::" + ePonto.identifique("y").getValor();

                    mostrarViagem();

                    viagem.clear();
                    viagem_hoje.clear();

                    acampamentos.clear();

                }

                cidade_anterior = ePonto.identifique("Cidade").getValor();
                System.out.println("\t -->> Passando em :: " + ePonto.identifique("Tozte").getValor() + " :: " + ePonto.identifique("Cidade").getValor());
                passeio_comecou = ePonto.identifique("Tozte").getValor();
            }


        } else {

            if (cidade_anterior.length() > 0) {

                if (passeio_comecou.length() > 0) {
                    passeio_terminou = ePonto.identifique("Tozte").getValor();

                    mostrarPasseio();

                }

                viagem_comecou = ePonto.identifique("Tozte").getValor();
                viagem_comecou_posicao = ePonto.identifique("x").getValor() + "::" + ePonto.identifique("y").getValor();

                System.out.println("\t -->> Iniciar Viagem :: " + viagem_comecou + " -->> " + viagem_comecou_posicao);
                jaInicouViagem = true;

                viagem.clear();
                viagem_hoje.clear();

                acampamentos.clear();

                viagem_anterior = "";
                acampamento_tempo = 0;
                andando_tempo = 0;
                desde = "";

            }

            viagem.add(ePonto.identifique("x").getValor() + "::" + ePonto.identifique("y").getValor());

            StringTronarko st = new StringTronarko();

            String pTozte = st.getTozte(ePonto.identifique("Tozte").getValor()).getTexto();

            if (pTozte.contentEquals(eHoje.getTexto())) {
                viagem_hoje.add(ePonto.identifique("x").getValor() + "::" + ePonto.identifique("y").getValor());
            }

            if (ePonto.identifique("Momento").isValor("Acampamento")) {
                acampamentos.add(ePonto.identifique("x").getValor() + "::" + ePonto.identifique("y").getValor());

                if (viagem_anterior.length() > 0) {
                    int com = IntTronarko.intervaloIttas(viagem_anterior, ePonto.identifique("Tozte").getValor());
                    acampamento_tempo += com;

                    // System.out.println(" ::-->> " +viagem_anterior + " ate " + ePonto.identifique("Tozte").getValor() + " com + " + com + " :: " + andando_tempo );

                    int viagem_percurso = getPercursoDesde(viagem, desde);

                    if (andando_tempo_pos_acampamento > 0 && desde.length() > 0) {
                        double velocidade = (double) viagem_percurso / (double) andando_tempo_pos_acampamento;
                        // System.out.println("\t   :: " + viagem_percurso + "    Velocidade          -->> " + STTY.doubleNumC3(velocidade) + " Stgz/ittas");

                        double velocidade_porArko = (double) viagem_percurso / (double) (andando_tempo_pos_acampamento / 100.0);
                        //  System.out.println("\t   :: " + viagem_percurso + "    Velocidade          -->> " + STTY.doubleNumC3(velocidade_porArko) + " Stgz/Arko");

                    }

                    andando_tempo_pos_acampamento = 0;
                    desde = ePonto.identifique("x").getValor() + "::" + ePonto.identifique("y").getValor();
                }

            } else {

                if (viagem_anterior.length() > 0) {
                    int com = IntTronarko.intervaloIttas(viagem_anterior, ePonto.identifique("Tozte").getValor());
                    andando_tempo += com;
                    andando_tempo_pos_acampamento += com;

                    //   System.out.println(" ::-->> " +viagem_anterior + " ate " + ePonto.identifique("Tozte").getValor() + " com + " + com + " :: " + andando_tempo );
                }

            }

            viagem_anterior = ePonto.identifique("Tozte").getValor();

            cidade_anterior = "";

        }

    }

    public static int getPercurso(ArrayList<String> pontos) {
        int p = 0;

        int i = 0;
        int o = pontos.size();

        String anterior = "";

        while (i < o) {
            String corrente = pontos.get(i);

            if (!corrente.contentEquals(anterior)) {
                anterior = corrente;
                p += 1;
            }
            i += 1;
        }


        return p * 10;
    }

    public static int getPassos(ArrayList<String> pontos) {

        int contando_passos = (getPercurso(pontos) * 100) / 3;

        int passos_2_tercos = contando_passos * 2;
        int passos_1_terco = contando_passos;

        int passos = (passos_2_tercos * 3) + (passos_1_terco * 2);


        return passos;
    }

    public static int getPercursoDesde(ArrayList<String> pontos, String eDesde) {
        int p = 0;

        int i = 0;
        int o = pontos.size();

        String anterior = "";
        boolean contar = false;

        while (i < o) {
            String corrente = pontos.get(i);

            if (eDesde.contentEquals(corrente)) {
                contar = true;
            }

            if (contar && !eDesde.contentEquals(corrente)) {
                if (!corrente.contentEquals(anterior)) {
                    anterior = corrente;
                    p += 1;
                }
            }

            i += 1;
        }


        return p * 10;
    }

}
