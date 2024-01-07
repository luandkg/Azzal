package apps.app_attuz.Viagem;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_attuz.Ferramentas.GPS;
import apps.app_attuz.Ferramentas.Local;
import libs.azzal.geometria.Ponto;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.tronarko.utils.ContadorTronarko;

import java.util.ArrayList;
import java.util.Random;

public class Viagem {

    private ArrayList<Local> mProximos;
    private ArrayList<Ponto> mPercurso;

    private int mTempo = 0;
    int diametro = 0;
    int raio = 0;

    public Viagem(int eRaio) {
        mTempo = 0;
        mProximos = new ArrayList<Local>();
        mPercurso = new ArrayList<Ponto>();

        raio = eRaio;
        diametro = eRaio * 2;
    }

    public int getTempo() {
        return mTempo / 10;
    }

    public ArrayList<Local> getProximos() {
        return mProximos;
    }

    public ArrayList<Ponto> getPercurso() {
        return mPercurso;
    }

    public void viajar(Viajante EU, Lista<Local> mLocais) {

        mTempo += 1;

        EU.mudar();

        if (EU.estouPensando()) {
            mProximos.clear();

            for (Local ePonto : mLocais) {

                if (ePonto.getX() == EU.getX() && EU.getY() == ePonto.getY()) {

                } else if (Espaco2D.isDentro((EU.getX()) - raio, (EU.getY()) - raio, diametro, diametro, ePonto.getX(), ePonto.getY())) {
                    mProximos.add(ePonto);
                }
            }

            if (mProximos.size() > 0) {

                int estou_x = EU.getX();
                int estou_y = EU.getY();

                Random eSorte = new Random();
                int escolhe = eSorte.nextInt(mProximos.size());

                Local escolhido = mProximos.get(escolhe);

                int ir_x = escolhido.getX();
                int ir_y = escolhido.getY();

                ArrayList<Ponto> rota = GPS.criarRota(estou_x, estou_y, ir_x, ir_y);

                EU.obterRota(rota);
                EU.setIndoPara(escolhido.getNome());

                // System.out.println("SAINDO DE :: " + EU.getX() + "," + EU.getY() + " --->> " + getNomeDe(EU.getX(), +EU.getY()));
                // System.out.println("IR PARA   :: " + ir_x + "," + ir_y + " -->> " + escolhido.getNome());
                // System.out.println("DURACAO   :: " + EU.getRotaTamanho());


            }


        } else if (EU.estouIndo()) {

            if (EU.temMaisRota()) {
                // if (!existePontoNoPercurso(EU.getEstou())) {
                mPercurso.add(EU.getEstou());
                // }
            }

        }

    }

    public boolean existePontoNoPercurso(Ponto p) {

        boolean existe = false;

        for (Ponto ePonto : mPercurso) {
            if (ePonto.getX() == p.getX() && ePonto.getY() == p.getY()) {
                existe = true;
                break;
            }
        }

        return existe;

    }

    public void abrir(String eArquivo) {

        DKG eDKC = new DKG();
        eDKC.abrir(eArquivo);

        DKGObjeto eViagem = eDKC.unicoObjeto("Viagem");

        mPercurso.clear();
        mTempo = 0;

        for (DKGObjeto ePonto : eViagem.getObjetos()) {

            mPercurso.add(new Ponto(ePonto.identifique("x").getInteiro(), ePonto.identifique("y").getInteiro()));


        }

    }

    public void salvar(String eArquivo) {

        DKG eDKC = new DKG();

        DKGObjeto eViagem = eDKC.unicoObjeto("Viagem");

        for (Ponto ePonto : mPercurso) {
            DKGObjeto o = eViagem.criarObjeto("Ponto");

            o.identifique("x", ePonto.getX());
            o.identifique("y", ePonto.getY());

        }

        System.out.println("------- SALVANDO -------");
        eDKC.salvar(eArquivo);
        System.out.println("------- SALVO ------- ");

    }

    public void marcarCidades(String eArquivo, Lista<Local> mLocais) {

        System.out.println("------- ABRINDO -------");

        DKG eDKC = new DKG();
        eDKC.abrir(eArquivo);

        System.out.println("------- ABERTO ------- ");

        DKGObjeto eViagem = eDKC.unicoObjeto("Viagem");

        DKG sDKC = new DKG();
        DKGObjeto sViagem = sDKC.unicoObjeto("Viagem");

        ContadorTronarko tg = new ContadorTronarko(46, 4, 7002);

        Random r = new Random();

        int acampamento = 0;

        for (DKGObjeto ePonto : eViagem.getObjetos()) {

            int px = ePonto.identifique("x").getInteiro();
            int py = ePonto.identifique("y").getInteiro();


            boolean enc = false;

            for (Local eLocal : mLocais) {
                if (eLocal.getX() == px && eLocal.getY() == py) {


                    int ficar = r.nextInt(35) + 20;
                    for (int f = 0; f < ficar; f++) {

                        DKGObjeto novo = sViagem.criarObjeto("Ponto");
                        novo.identifique("x", px);
                        novo.identifique("y", py);
                        novo.identifique("Cidade", eLocal.getNome());

                        tg.proximo(40);
                        novo.identifique("Tozte", tg.getTozteComArkoIttas());

                        if (f == 0) {
                            novo.identifique("Momento", "Chegando");

                            System.out.println("Chegando em " + eLocal.getNome() + " em " + tg.getTronarko());

                        } else if (f == ficar - 1) {
                            novo.identifique("Momento", "Saindo");
                        }
                    }

                    acampamento = 0;

                    enc = true;
                    break;
                }
            }

            if (!enc) {

                if (acampamento == 10) {

                    DKGObjeto novo = sViagem.criarObjeto("Ponto");
                    novo.identifique("x", px);
                    novo.identifique("y", py);
                    novo.identifique("Tozte", tg.getTozteComArkoIttas());
                    novo.identifique("Momento", "Acampamento");

                    acampamento = 0;
                    tg.proximo(40);

                }

                ePonto.identifique("Tozte", tg.getTozteComArkoIttas());

                sViagem.getObjetos().adicionar(ePonto);
                acampamento += 1;

            }

            tg.proximo(40);
        }

        sDKC.salvar(eArquivo);


        System.out.println("Superarkos :: " + tg.getSuperarkos());

    }


    public void separar(String eArquivo, String qual) {


        System.out.println("------- ABRINDO -------");
        DKG eDKC = new DKG();
        eDKC.abrir(eArquivo);
        System.out.println("------- ABERTO ------- ");

        DKGObjeto eViagem = eDKC.unicoObjeto("Viagem");

        mPercurso.clear();
        mTempo = 0;

        DKG t2 = new DKG();
        DKGObjeto v2 = t2.unicoObjeto("Viagem");


        for (DKGObjeto ePonto : eViagem.getObjetos()) {

            mPercurso.add(new Ponto(ePonto.identifique("x").getInteiro(), ePonto.identifique("y").getInteiro()));

            String tozte = ePonto.identifique("Tozte").getValor();

            System.out.println("Arko :: " + tozte + " -->> " + getArko(tozte));
            System.out.println("Ittas :: " + tozte + " -->> " + getIttas(tozte));

            System.out.println("Superarko :: " + tozte + " -->> " + getSuperarko(tozte));
            System.out.println("Hiperarko :: " + tozte + " -->> " + getHiperarko(tozte));
            System.out.println("libs.Tronarko :: " + tozte + " -->> " + getTronarko(tozte));

            if (getTronarko(tozte).contentEquals(qual)) {
                v2.getObjetos().adicionar(ePonto);

                int ittas = Integer.parseInt(getIttas(tozte));
                int arkos = Integer.parseInt(getArko(tozte));
                int sup = Integer.parseInt(getSuperarko(tozte));
                int hip = Integer.parseInt(getHiperarko(tozte));

                int valor = (hip * 50 * 10 * 100) + (sup * 10 * 100) + (arkos * 100) + ittas;

                ePonto.identifique("Valor", valor);
            }

        }

        t2.salvar("/home/luan/Documentos/t" + qual + ".txt");


    }

    public String getSuperarko(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 0;

        while (i < 2 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getHiperarko(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 3;

        while (i < 5 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getTronarko(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 6;

        while (i < 10 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getArko(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 14;

        while (i < 16 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

    public String getIttas(String tozte) {
        String ret = "";

        int o = tozte.length();
        int i = 17;

        while (i < 19 && i < o) {
            ret += String.valueOf(tozte.charAt(i));
            i += 1;
        }

        return ret;
    }

}
