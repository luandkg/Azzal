package libs.RhoBenchmark;

import azzal.Renderizador;
import azzal.Utils.Cor;
import libs.DKG.DKG;
import libs.DKG.DKGObjeto;
import libs.Imaginador.ImageUtils;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.AutoFonte;
import libs.Luan.RefBool;
import libs.Luan.RefLong;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RhoBenchmark {

    private String mArquivo;
    private int mLimiteAutoSalvar;

    private int mContando;
    private int mComecarAPartirDe;

    private boolean salvo = false;

    private ArrayList<RhoSlot> mSlots;

    public RhoBenchmark(String eArquivo, int eComecarAPartirDe, int eLimiteAutoSalvar) {

        mArquivo = eArquivo;
        mLimiteAutoSalvar = eLimiteAutoSalvar;
        mComecarAPartirDe = eComecarAPartirDe;

        mSlots = new ArrayList<RhoSlot>();

        mContando = 0;
    }

    public long get() {
        return System.nanoTime();
    }


    public void set(String eNome, long eInicio, long eFim) {

        if (mContando >= mComecarAPartirDe) {

            if (mSlots.size() >= mLimiteAutoSalvar) {

                if (!salvo) {
                    salvo = true;
                    //guardar();
                    auto_render();
                }

            } else {
                mSlots.add(new RhoSlot(eNome, eInicio, eFim));
                System.out.println("libs.RhoBenchmark :: " + mSlots.size());
            }


        } else {
            mContando += 1;
        }


    }

    public void guardar() {

        ArrayList<String> guardados = new ArrayList<String>();

        DKG eDKg = new DKG();
        DKGObjeto slots = eDKg.unicoObjeto("RhoBenchmark");

        for (RhoSlot slot : mSlots) {
            DKGObjeto objeto = slots.criarObjeto("RhoSlot");

            objeto.identifique("Nome", slot.getNome());
            objeto.identifique("Inicio", slot.getInicio());
            objeto.identifique("Fim", slot.getFim());

        }

        eDKg.salvar(mArquivo);

    }


    public void auto_render() {


        BufferedImage imagem = ImageUtils.criarEmBranco(1200, 800);
        Renderizador render = new Renderizador(imagem);

        Fonte escritor = new AutoFonte(render, new Cor(150, 150, 150), 15);


        ArrayList<String> tipos = new ArrayList<String>();

        for (RhoSlot slot : mSlots) {
            if (!tipos.contains(slot.getNome())) {
                tipos.add(slot.getNome());
            }
        }

        int py = 300;

        for (String tipo : tipos) {

            RefLong maior = new RefLong(0);
            RefLong menor = new RefLong(0);
            RefBool primeiro = new RefBool(true);

            for (RhoSlot slot : mSlots) {

                if (slot.isDoTipo(tipo)) {

                    long tempo = slot.getTempo();

                    if (primeiro.get()) {

                        primeiro.set(false);

                        maior.set(tempo);
                        menor.set(tempo);

                    } else {

                        if (tempo > maior.get()) {
                            maior.set(tempo);
                        }

                        if (tempo < menor.get()) {
                            menor.set(tempo);
                        }

                    }

                }

            }


            // DESENHAR

            System.out.println("MAIOR :: " + maior.get());
            System.out.println("MENOR :: " + menor.get());

            int delta = (int) (maior.get() - menor.get());

            int ALTURA_MAXIMA = 200;
            int ALTURA_MINIMA = 50;

            int altura_delta = ALTURA_MAXIMA - ALTURA_MINIMA;

            double tx = (double) altura_delta / (double) delta;

            Cor verde = Cor.getHexCor("#4caf50");

            int px = 50;

            int retirar = 0;
            boolean is_primeiro = true;

            for (RhoSlot slot : mSlots) {
                if (slot.isDoTipo(tipo)) {

                    long tempo = slot.getTempo();

                    double real = (double) tempo * tx;
                    int valor = (int) real;

                    if (is_primeiro) {
                        retirar = valor;
                        is_primeiro = false;
                    }else{
                        if (valor < retirar) {
                            retirar = valor;
                        }
                    }


                }
            }

            for (RhoSlot slot : mSlots) {

                if (slot.isDoTipo(tipo)) {

                    long tempo = slot.getTempo();

                    double real = (double) tempo * tx;
                    int valor = ((int) real - retirar) + 50;

                    render.drawRect_Pintado(px, py - valor, 5, valor, verde);

                    px += 5;

                }


            }

            escritor.escreva(800, py - 260, tipo);
            escritor.escreva(800, py - 240, "Maior :: " + maior.get() + " ns");
            escritor.escreva(800, py - 220, "Menor :: " + menor.get() + " ns");

            py += 300;

        }


        render.exportarSemAlfa(mArquivo.replace(".dkg", ".png"));
    }

}
