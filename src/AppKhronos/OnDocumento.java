package AppKhronos;

import Letrum.Fonte;

import java.util.ArrayList;

public class OnDocumento {

    private String mTexto;
    private ArrayList<String> mLinhas;

    private int sel_x;
    private int sel_y;

    private int cur_x;
    private int cur_y;


    public OnDocumento() {

        mTexto = "";
        mLinhas = new ArrayList<String>();

        sel_x = 0;
        sel_y = 0;

        cur_x = 0;
        cur_y = 0;

    }

    public void removaOUltimoEVolte() {


        if (mTexto.length() > 0) {

            organizarLinhas();

            // System.out.println("mod 1  :: " + mais);
            System.out.println("cursor :: " + cur_y + " : " + cur_x);

            int pos = irAteCursor() - 1;
            if (pos > -1) {
                System.out.println("rem pos :: " + pos);

                mTexto = remover(mTexto, pos);
                cur_x -= 1;
                if (cur_x < 0) {
                    cur_y -= 1;
                    if (cur_y >= 0 && cur_y < mLinhas.size()) {
                        cur_x = mLinhas.get(cur_y).length();
                    }
                }
                if (cur_y < 0) {
                    cur_y = 0;
                }
            }

        }


    }

    public int irAteCursor() {
        int pos = 0;

        int ay = 0;
        if (cur_y < mLinhas.size()) {

            int ultima = mLinhas.size() - 1;

            while (ay < cur_y + 1) {
                if (ay < mLinhas.size()) {
                    if (ay == ultima) {
                        pos += mLinhas.get(ay).length();
                    } else {
                        pos += mLinhas.get(ay).length() + 1;
                    }

                    System.out.println("\t Linha(" + ay + ") :: " + (mLinhas.get(ay).length()));

                    ay += 1;
                } else {
                    break;
                }

            }
        }


        return pos;
    }

    public int div(int a, int b) {
        int s = 0;

        if (a >= b) {
            while (a > b) {
                a -= b;
                s += 1;
            }
        }

        return s;
    }

    public void clicar(int initlinha, int px, int py, boolean ePrecionado, int mX, int mY, Fonte mLetramento) {
        if (ePrecionado) {
            //System.out.println("X : " + px + " Y : " + py);

            sel_x = px - 6;
            sel_y = py - 8;

            if (sel_x > mX && sel_y > (mY)) {

                cur_x = 0;
                cur_y = 0;

                int y_nivel = 4;


                int diff_y = (sel_y - (mY + y_nivel));

                int linha = initlinha + div(diff_y, mLetramento.getAltura());

                int coluna = (sel_x - mX);


                //System.out.println(" Y : " + (diff_y) + " Linha :: " + linha);

                if (linha < mLinhas.size()) {

                    cur_y = linha;

                    String eLinha = mLinhas.get(linha);

                    int acc = 0;
                    int i = 0;
                    int o = eLinha.length();

                    while (i < o) {
                        String l = String.valueOf(eLinha.charAt(i));
                        int t = mLetramento.getLarguraDe(l);
                        if ((acc + t) < coluna) {
                            acc += t;
                        } else {
                            break;
                        }
                        i += 1;
                    }

                    cur_x = i;

                    System.out.println(" Cur (Y,X) ->> " + cur_y + " ::" + cur_x);

                }

            }

        }
    }

    public void setCursorFim() {

        cur_x = 0;
        cur_y = 0;

        if (mLinhas.size() > 0) {
            cur_y = mLinhas.size() - 1;
            int tmp = mLinhas.get(mLinhas.size() - 1).length() - 1;
            if (tmp > 0) {
                cur_x = tmp;
            }
        }

    }

    public void irDireita() {

        int limite = 0;
        if (mLinhas.size() > 0 && cur_y < mLinhas.size()) {
            limite = mLinhas.get(cur_y).length() - 1;
        }
        if (cur_x <= limite) {
            cur_x += 1;
        }

    }

    public void irEsquerda() {

        if (cur_x > 0) {
            cur_x -= 1;
        }

    }

    public void irSubir() {

        if (cur_y > 0) {
            cur_y -= 1;
        }

        int limite = 0;
        if (mLinhas.size() > 0 && cur_y < mLinhas.size()) {
            limite = mLinhas.get(cur_y).length();
        }

        if (cur_x >= limite) {
            cur_x = limite;
        }

    }

    public void irDescer() {

        if (cur_y < mLinhas.size() - 1) {
            cur_y += 1;
        }

        int limite = 0;
        if (mLinhas.size() > 0 && cur_y < mLinhas.size()) {
            limite = mLinhas.get(cur_y).length();
        }

        if (cur_x >= limite) {
            cur_x = limite;
        }
    }

    public void organizarLinhas() {
        mLinhas.clear();

        int i = 0;
        int o = mTexto.length();
        String linha = "";

        while (i < o) {
            String letra = String.valueOf(mTexto.charAt(i));
            if (letra.contentEquals("\n")) {
                mLinhas.add(linha);
                linha = "";
            } else {
                linha += letra;
            }
            i += 1;
        }

        if (linha.length() > 0) {
            mLinhas.add(linha);
        }
    }

    public ArrayList<String> getLinhas() {
        return mLinhas;
    }

    public int getCurX() {
        return cur_x;
    }

    public int getCurY() {
        return cur_y;
    }

    public void setCurX(int x) {
        cur_x = x;
    }

    public void setCurY(int y) {
        cur_y = y;
    }

    public void setTexto(String eTexto) {
        mTexto = eTexto;
    }

    public String getTexto() {
        return mTexto;
    }

    public String inserir(String target, int position, String insert) {
        final int targetLen = target.length();

        if (targetLen == 0) {
            return insert;
        }

        System.out.println("Texto   :: " + target);

        System.out.println("Tamanho :: " + targetLen);
        System.out.println("Pos     :: " + position);
        System.out.println("Inserir :: " + insert);


        if (position < 0 || position > targetLen) {
            throw new IllegalArgumentException("position=" + position);
        }
        if (insert.isEmpty()) {
            return target;
        }
        if (position == 0) {
            return insert.concat(target);
        } else if (position == targetLen) {
            return target.concat(insert);
        }
        final int insertLen = insert.length();
        final char[] buffer = new char[targetLen + insertLen];
        target.getChars(0, position, buffer, 0);
        insert.getChars(0, insertLen, buffer, position);
        target.getChars(position, targetLen, buffer, position + insertLen);
        return new String(buffer);
    }

    public String remover(String s, int pos) {

        StringBuilder sb = new StringBuilder(s);
        if (sb.length() > 0) {
            sb.deleteCharAt(pos);
        }

        return sb.toString();
    }
}
