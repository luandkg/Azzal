package libs.grafique;

import libs.azzal.geometria.Linha;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.Posicionador;
import libs.luan.Lista;

import java.awt.*;

public class GrafiqueBarras {

    private Lista<Integer> mValores;

    private int mLimite;
    private int mMinimo;
    private int mMaximo;
    private int mEspaco;

    private int mBase;
    private Cor mCorFundo;
    private Cor mCorBarra;

    private Cor mCorMaiorBarra;
    private boolean mMaiorDoGrupo;

    private Cor mCorMenorBarra;
    private boolean mMenorDoGrupo;


    public GrafiqueBarras() {

        mValores = new Lista<Integer>();
        mLimite = 10;

        mMinimo = 0;
        mMaximo = 100;
        mEspaco = 100;
        mBase = 0;

        mCorBarra = Cor.getRGB(Color.BLUE);
        mCorFundo = Cor.getRGB(Color.RED);

        mCorMaiorBarra = Cor.getRGB(Color.YELLOW);
        mMaiorDoGrupo = false;

        mCorMenorBarra = Cor.getRGB(Color.RED);
        mMenorDoGrupo = false;

    }

    public void limitar(int eLimite) {
        if (eLimite < 1) {
            throw new IllegalArgumentException("O limitador nao pode ser menor que 2");
        }
        mLimite = eLimite;
    }

    public void definirBase(int eBase) {

        mBase = eBase;
    }

    public void setMaiorDoGrupo(boolean eMaiorMaior) {
        mMaiorDoGrupo = eMaiorMaior;
    }

    public void setMenorDoGrupo(boolean eMenorDoGrupo) {
        mMenorDoGrupo = eMenorDoGrupo;
    }

    public void nivelar(int eMin, int eMax) {

        if (eMin >= eMax) {
            throw new IllegalArgumentException("O minimo deve ser menor que o maximo ");
        }


        mMinimo = eMin;
        mMaximo = eMax;
        mEspaco = mMaximo - mMinimo;

    }

    public void adicionar(int eValor) {
        mValores.adicionar(eValor);
        if (mValores.getQuantidade() > mLimite) {
            while (mValores.getQuantidade() > mLimite) {
                mValores.remover_indice(0);
            }
        }
    }

    public int obter(int eIndice) {
        return mValores.get(eIndice);
    }

    public int getQuantidade() {
        return mValores.getQuantidade();
    }

    public int getEspaco() {
        return mEspaco;
    }

    public void setCorBarra(Cor eCorBarra) {
        mCorBarra = eCorBarra;
    }

    public void setCorFundo(Cor eCorFundo) {
        mCorFundo = eCorFundo;
    }

    public void setCorMaiorBarra(Cor eCorMaiorBarra) {
        mCorMaiorBarra = eCorMaiorBarra;
    }

    public void setCorMenorBarra(Cor eCorMenorBarra) {
        mCorMenorBarra = eCorMenorBarra;
    }


    public void onDraw(Renderizador g, int eX, int eY, int eLargura, int eAltura) {

        // g.setColor(mCorFundo);
        g.drawRect_Pintado(new Retangulo(eX, eY, eLargura, eAltura), (mCorFundo));

        int l = eLargura / mLimite;
        int a = eAltura / mEspaco;


        int ls = 0;

        if (getQuantidade() > 0) {


            int eMaior = obter(0);
            int eMenor = obter(0);

            for (int i = getQuantidade() - 1; i >= 0; i--) {
                if (obter(i) > eMaior) {
                    eMaior = obter(i);
                } else if (obter(i) < eMenor) {
                    eMenor = obter(i);
                }
            }


            for (int i = getQuantidade() - 1; i >= 0; i--) {
                int eValor = obter(i);

                int eOrginal = eValor;

                if (eValor < mMinimo) {
                    eValor = mMinimo;
                } else if (eValor > mMaximo) {
                    eValor = mMaximo;
                } else {

                    eValor -= mMinimo;

                }

                int eTamanho = (a * eValor);
                int comecar = eY + eAltura - eTamanho;

                //  g.setColor(mCorBarra);

                Cor eCorSelecionada = (mCorBarra);

                if (mMaiorDoGrupo) {
                    if (eOrginal == eMaior) {
                        //   g.setColor(mCorMaiorBarra);
                        eCorSelecionada = (mCorMaiorBarra);
                    }
                }

                if (mMenorDoGrupo) {
                    if (eOrginal == eMenor) {
                        //   g.setColor(mCorMenorBarra);
                        eCorSelecionada = (mCorMenorBarra);

                    }
                }

                // g.fillRect((eX + eLargura) - ls - l, comecar, l - 1, eTamanho);

                g.drawRect_Pintado(new Retangulo((eX + eLargura) - ls - l, comecar, l - 1, eTamanho), (eCorSelecionada));

                // g.drawString(eOrginal + "", (eX + eLargura) - ls - (l / 2), eY + eAltura + 20);

                ls += l;
            }
        }

        // g.setColor(mCorBarra);
        Cor eCorFundo = (mCorBarra);

        // g.fillRect(eX, eY + eAltura + 30, eLargura, 10);
        g.drawRect_Pintado(new Retangulo(eX, eY + eAltura + 30, eLargura, 10), (eCorFundo));


    }

    public void onDrawRect(Renderizador g, int eX, int eY, int eLargura, int eAltura) {


        g.drawRect_Pintado(new Retangulo(eX, eY, eLargura, eAltura), (mCorFundo));

        int l = eLargura / mLimite;
        int a = eAltura / mEspaco;

        int ls = 0;

        if (getQuantidade() > 0) {


            int eMaior = obter(0);
            int eMenor = obter(0);

            for (int i = getQuantidade() - 1; i >= 0; i--) {
                if (obter(i) > eMaior) {
                    eMaior = obter(i);
                } else if (obter(i) < eMenor) {
                    eMenor = obter(i);
                }
            }


            for (int i = getQuantidade() - 1; i >= 0; i--) {
                int eValor = obter(i);

                int eOrginal = eValor;

                if (eValor < mMinimo) {
                    eValor = mMinimo;
                } else if (eValor > mMaximo) {
                    eValor = mMaximo;
                } else {

                    eValor -= mMinimo;

                }

                int eTamanho = (a * eValor);

                if (eTamanho < 10) {
                    eTamanho = 10;
                }

                int comecar = eY + eAltura - eTamanho;

                if (eTamanho < 10) {

                } else if (eTamanho > 10) {
                    eTamanho = 10;
                }

                Cor mCorSelecionada = mCorBarra;


                if (mMaiorDoGrupo) {
                    if (eOrginal == eMaior) {
                        mCorSelecionada = (mCorMaiorBarra);
                    }
                }

                if (mMenorDoGrupo) {
                    if (eOrginal == eMenor) {
                        mCorSelecionada = (mCorMenorBarra);
                    }
                }

                g.drawRect_Pintado(new Retangulo((eX + eLargura) - ls - l, comecar, l - 1, eTamanho), (mCorSelecionada));

                //   g.drawString(eOrginal + "", (eX + eLargura) - ls - (l / 2), eY + eAltura + 20);

                ls += l;
            }
        }

        g.drawRect_Pintado(new Retangulo(eX, eY + eAltura + 30, eLargura, 10), (mCorBarra));

    }

    public void onDrawTower(Renderizador g, int eX, int eY, int eLargura, int eAltura) {


        g.drawRect_Pintado(new Retangulo(eX, eY, eLargura, eAltura), (mCorFundo));

        int l = eLargura / mLimite;
        int a = eAltura / mEspaco;

        int ls = 0;
        if (getQuantidade() > 0) {


            int eMaior = obter(0);
            int eMenor = obter(0);

            for (int i = getQuantidade() - 1; i >= 0; i--) {
                if (obter(i) > eMaior) {
                    eMaior = obter(i);
                } else if (obter(i) < eMenor) {
                    eMenor = obter(i);
                }
            }


            for (int i = getQuantidade() - 1; i >= 0; i--) {
                int eValor = obter(i);

                int eOrginal = eValor;

                if (eValor < mMinimo) {
                    eValor = mMinimo;
                } else if (eValor > mMaximo) {
                    eValor = mMaximo;
                } else {

                    eValor -= mMinimo;

                }

                int eTamanho = (a * eValor);

                if (eTamanho < 10) {
                    eTamanho = 10;
                }

                int comecar = eY + eAltura - eTamanho;

                int aAntes = eTamanho;

                if (eTamanho < 10) {

                } else if (eTamanho > 10) {
                    eTamanho = 10;
                }

                Cor mCorSelecionada = mCorBarra;

                if (mMaiorDoGrupo) {
                    if (eOrginal == eMaior) {
                        mCorSelecionada = mCorMaiorBarra;
                    }
                }

                if (mMenorDoGrupo) {
                    if (eOrginal == eMenor) {
                        mCorSelecionada = mCorMenorBarra;

                    }
                }


                g.drawRect_Pintado(new Retangulo((eX + eLargura) - ls - l, comecar, l - 1, eTamanho), (mCorSelecionada));
                g.drawRect_Pintado(new Retangulo(((eX + eLargura) - ls) - (l / 2) - 5, comecar, 10, aAntes), (mCorSelecionada));

                //   g.drawString(eOrginal + "", (eX + eLargura) - ls - (l / 2), comecar - 10);

                ls += l;
            }


        }


        g.drawRect_Pintado(new Retangulo(eX, eY + eAltura + 30, eLargura, 10), (mCorBarra));

    }

    public void onDrawQuad(Renderizador g, int eX, int eY, int eLargura, int eAltura) {

        g.drawRect_Pintado(new Retangulo(eX, eY, eLargura, eAltura), (mCorFundo));

        int l = eLargura / mLimite;
        int a = eAltura / mEspaco;

        Posicionador ePos = new Posicionador();

        int ls = 0;
        if (getQuantidade() > 0) {


            int eMaior = obter(0);
            int eMenor = obter(0);

            for (int i = getQuantidade() - 1; i >= 0; i--) {
                if (obter(i) > eMaior) {
                    eMaior = obter(i);
                } else if (obter(i) < eMenor) {
                    eMenor = obter(i);
                }
            }


            boolean primeiro = true;
            Ponto ePonto = new Ponto();

            for (int i = getQuantidade() - 1; i >= 0; i--) {
                int eValor = obter(i);

                int eOrginal = eValor;

                if (eValor < mMinimo) {
                    eValor = mMinimo;
                } else if (eValor > mMaximo) {
                    eValor = mMaximo;
                } else {

                    eValor -= mMinimo;

                }

                int eTamanho = (a * eValor);

                if (eTamanho < 10) {
                    eTamanho = 10;
                }

                int comecar = eY + eAltura - eTamanho;

                int aAntes = eTamanho;

                if (eTamanho < 10) {

                } else if (eTamanho > 10) {
                    eTamanho = 10;
                }

                Cor mCorSelecionada = mCorBarra;


                if (mMaiorDoGrupo) {
                    if (eOrginal == eMaior) {
                        mCorSelecionada = (mCorMaiorBarra);
                    }
                }

                if (mMenorDoGrupo) {
                    if (eOrginal == eMenor) {
                        mCorSelecionada = (mCorMenorBarra);
                    }
                }


                if (primeiro) {
                    primeiro = false;
                } else {

                    g.drawLinha(new Linha(ePonto.getX(), ePonto.getY(), ((eX + eLargura) - ls - l) + 5, comecar + 5), mCorBarra);

                }

                //  g.drawRect_Pintado(new Retangulo(((eX + eLargura) - ls - l), comecar, 10, 10), (mCorSelecionada));
                g.drawCirculo(ePos.getCirculo(((eX + eLargura) - ls - l), comecar, 5), (mCorSelecionada));

                ePonto.setPos((eX + eLargura) - ls - l, comecar);

                if (mMaiorDoGrupo) {
                    if (eOrginal == eMaior) {
                        mCorSelecionada = (mCorMaiorBarra);
                    }
                }

                if (mMenorDoGrupo) {
                    if (eOrginal == eMenor) {
                        mCorSelecionada = (mCorMenorBarra);
                    }
                }

                //  g.drawString(eOrginal + "", (eX + eLargura) - ls - (l / 2), eY + eAltura + 20);

                ls += l;
            }


        }


        g.drawRect_Pintado(new Retangulo(eX, eY + eAltura + 30, eLargura, 10), (mCorBarra));

    }


}
