package Letrum.Maker;

import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Letra;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FonteRunTime implements Fonte {

    private BufferedImage mImagem;
    private ArrayList<Letra> mLetras;

    public int LARGURA = 0;
    public int ALTURA = 0;
    public int FONTE = 15;
    private int ESCREVA_COR = new Color(0, 0, 0).getRGB();
    private Renderizador mRenderizador;
    private Cor mCor;

    private final Letra LETRA_00 = new Letra("a", 0, 0, 0, 0);
    private final Letra LETRA_01 = new Letra("b", 0, 0, 0, 0);
    private final Letra LETRA_02 = new Letra("c", 0, 0, 0, 0);
    private final Letra LETRA_03 = new Letra("d", 0, 0, 0, 0);
    private final Letra LETRA_04 = new Letra("e", 0, 0, 0, 0);
    private final Letra LETRA_05 = new Letra("f", 0, 0, 0, 0);
    private final Letra LETRA_06 = new Letra("g", 0, 0, 0, 0);
    private final Letra LETRA_07 = new Letra("h", 0, 0, 0, 0);
    private final Letra LETRA_08 = new Letra("i", 0, 0, 0, 0);
    private final Letra LETRA_09 = new Letra("j", 0, 0, 0, 0);
    private final Letra LETRA_10 = new Letra("k", 0, 0, 0, 0);
    private final Letra LETRA_11 = new Letra("l", 0, 0, 0, 0);
    private final Letra LETRA_12 = new Letra("m", 0, 0, 0, 0);
    private final Letra LETRA_13 = new Letra("n", 0, 0, 0, 0);
    private final Letra LETRA_14 = new Letra("o", 0, 0, 0, 0);
    private final Letra LETRA_15 = new Letra("p", 0, 0, 0, 0);
    private final Letra LETRA_16 = new Letra("q", 0, 0, 0, 0);
    private final Letra LETRA_17 = new Letra("r", 0, 0, 0, 0);
    private final Letra LETRA_18 = new Letra("s", 0, 0, 0, 0);
    private final Letra LETRA_19 = new Letra("t", 189, 38, 198, 59);
    private final Letra LETRA_20 = new Letra("u", 211, 38, 224, 59);
    private final Letra LETRA_21 = new Letra("v", 233, 38, 244, 59);
    private final Letra LETRA_22 = new Letra("w", 13, 73, 28, 94);
    private final Letra LETRA_23 = new Letra("x", 35, 73, 46, 94);
    private final Letra LETRA_24 = new Letra("y", 57, 73, 68, 94);
    private final Letra LETRA_25 = new Letra("z", 79, 73, 89, 94);
    private final Letra LETRA_26 = new Letra("A", 101, 73, 115, 94);
    private final Letra LETRA_27 = new Letra("B", 123, 73, 138, 94);
    private final Letra LETRA_28 = new Letra("C", 145, 73, 160, 94);
    private final Letra LETRA_29 = new Letra("D", 167, 73, 182, 94);
    private final Letra LETRA_30 = new Letra("E", 189, 73, 203, 94);
    private final Letra LETRA_31 = new Letra("F", 211, 73, 224, 94);
    private final Letra LETRA_32 = new Letra("G", 233, 73, 248, 94);
    private final Letra LETRA_33 = new Letra("H", 13, 108, 29, 129);
    private final Letra LETRA_34 = new Letra("I", 35, 108, 43, 129);
    private final Letra LETRA_35 = new Letra("J", 57, 108, 66, 129);
    private final Letra LETRA_36 = new Letra("K", 79, 108, 94, 129);
    private final Letra LETRA_37 = new Letra("L", 101, 108, 114, 129);
    private final Letra LETRA_38 = new Letra("M", 123, 108, 141, 129);
    private final Letra LETRA_39 = new Letra("N", 145, 108, 161, 129);
    private final Letra LETRA_40 = new Letra("O", 167, 108, 183, 129);
    private final Letra LETRA_41 = new Letra("P", 189, 108, 202, 129);
    private final Letra LETRA_42 = new Letra("Q", 211, 108, 227, 129);
    private final Letra LETRA_43 = new Letra("R", 233, 108, 247, 129);
    private final Letra LETRA_44 = new Letra("S", 13, 143, 26, 164);
    private final Letra LETRA_45 = new Letra("T", 35, 143, 47, 164);
    private final Letra LETRA_46 = new Letra("U", 57, 143, 72, 164);
    private final Letra LETRA_47 = new Letra("V", 79, 143, 93, 164);
    private final Letra LETRA_48 = new Letra("W", 101, 143, 120, 164);
    private final Letra LETRA_49 = new Letra("X", 123, 143, 137, 164);
    private final Letra LETRA_50 = new Letra("Y", 145, 143, 158, 164);
    private final Letra LETRA_51 = new Letra("Z", 167, 143, 180, 164);
    private final Letra LETRA_52 = new Letra("_", 189, 143, 199, 164);
    private final Letra LETRA_53 = new Letra("0", 211, 143, 223, 164);
    private final Letra LETRA_54 = new Letra("1", 233, 143, 245, 164);
    private final Letra LETRA_55 = new Letra("2", 13, 178, 25, 199);
    private final Letra LETRA_56 = new Letra("3", 35, 178, 47, 199);
    private final Letra LETRA_57 = new Letra("4", 57, 178, 69, 199);
    private final Letra LETRA_58 = new Letra("5", 79, 178, 91, 199);
    private final Letra LETRA_59 = new Letra("6", 101, 178, 113, 199);
    private final Letra LETRA_60 = new Letra("7", 123, 178, 135, 199);
    private final Letra LETRA_61 = new Letra("8", 145, 178, 157, 199);
    private final Letra LETRA_62 = new Letra("9", 167, 178, 179, 199);
    private final Letra LETRA_63 = new Letra("-", 189, 178, 197, 199);
    private final Letra LETRA_64 = new Letra("<", 211, 178, 226, 199);
    private final Letra LETRA_65 = new Letra(">", 233, 178, 248, 199);
    private final Letra LETRA_66 = new Letra(".", 13, 213, 19, 234);
    private final Letra LETRA_67 = new Letra(",", 35, 213, 42, 234);
    private final Letra LETRA_68 = new Letra(":", 57, 213, 65, 234);
    private final Letra LETRA_69 = new Letra(";", 79, 213, 87, 234);
    private final Letra LETRA_70 = new Letra("/", 101, 213, 108, 234);
    private final Letra LETRA_71 = new Letra("\\", 123, 213, 130, 234);
    private final Letra LETRA_72 = new Letra("+", 145, 213, 160, 234);
    private final Letra LETRA_73 = new Letra("-", 167, 213, 175, 234);
    private final Letra LETRA_74 = new Letra("*", 189, 213, 199, 234);
    private final Letra LETRA_75 = new Letra("=", 211, 213, 226, 234);
    private final Letra LETRA_76 = new Letra("(", 233, 213, 242, 234);
    private final Letra LETRA_77 = new Letra(")", 13, 248, 22, 269);
    private final Letra LETRA_78 = new Letra("[", 35, 248, 44, 269);
    private final Letra LETRA_79 = new Letra("]", 57, 248, 66, 269);
    private final Letra LETRA_80 = new Letra("{", 79, 248, 91, 269);
    private final Letra LETRA_81 = new Letra("}", 101, 248, 113, 269);
    private final Letra LETRA_82 = new Letra("!", 123, 248, 132, 269);
    private final Letra LETRA_83 = new Letra("@", 145, 248, 162, 269);
    private final Letra LETRA_84 = new Letra("#", 167, 248, 182, 269);
    private final Letra LETRA_85 = new Letra("$", 189, 248, 201, 269);
    private final Letra LETRA_86 = new Letra("%", 211, 248, 227, 269);
    private final Letra LETRA_87 = new Letra("ç", 233, 248, 244, 269);
    private final Letra LETRA_88 = new Letra("Ç", 13, 283, 28, 304);
    private final Letra LETRA_89 = new Letra("á", 35, 283, 47, 304);
    private final Letra LETRA_90 = new Letra("à", 57, 283, 69, 304);
    private final Letra LETRA_91 = new Letra("â", 79, 283, 91, 304);
    private final Letra LETRA_92 = new Letra("ã", 101, 283, 113, 304);
    private final Letra LETRA_93 = new Letra("ä", 123, 283, 135, 304);
    private final Letra LETRA_94 = new Letra("é", 145, 283, 156, 304);
    private final Letra LETRA_95 = new Letra("è", 167, 283, 178, 304);
    private final Letra LETRA_96 = new Letra("ê", 189, 283, 200, 304);
    private final Letra LETRA_97 = new Letra("ẽ", 211, 283, 222, 304);
    private final Letra LETRA_98 = new Letra("ë", 233, 283, 244, 304);
    private final Letra LETRA_99 = new Letra("í", 13, 318, 20, 339);
    private final Letra LETRA_100 = new Letra("ì", 35, 318, 42, 339);
    private final Letra LETRA_101 = new Letra("î", 57, 318, 64, 339);
    private final Letra LETRA_102 = new Letra("ĩ", 79, 318, 86, 339);
    private final Letra LETRA_103 = new Letra("ï", 101, 318, 108, 339);
    private final Letra LETRA_104 = new Letra("ó", 123, 318, 134, 339);
    private final Letra LETRA_105 = new Letra("ò", 145, 318, 156, 339);
    private final Letra LETRA_106 = new Letra("ô", 167, 318, 178, 339);
    private final Letra LETRA_107 = new Letra("õ", 189, 318, 200, 339);
    private final Letra LETRA_108 = new Letra("ö", 211, 318, 222, 339);
    private final Letra LETRA_109 = new Letra("ú", 233, 318, 246, 339);
    private final Letra LETRA_110 = new Letra("ù", 13, 353, 26, 374);
    private final Letra LETRA_111 = new Letra("û", 35, 353, 48, 374);
    private final Letra LETRA_112 = new Letra("ũ", 57, 353, 70, 374);
    private final Letra LETRA_113 = new Letra("ü", 79, 353, 92, 374);
    private final Letra LETRA_114 = new Letra("Á", 101, 353, 115, 374);
    private final Letra LETRA_115 = new Letra("À", 123, 353, 137, 374);
    private final Letra LETRA_116 = new Letra("Â", 145, 353, 159, 374);
    private final Letra LETRA_117 = new Letra("Ã", 167, 353, 181, 374);
    private final Letra LETRA_118 = new Letra("Ä", 189, 353, 203, 374);
    private final Letra LETRA_119 = new Letra("É", 211, 353, 225, 374);
    private final Letra LETRA_120 = new Letra("È", 233, 353, 247, 374);
    private final Letra LETRA_121 = new Letra("Ê", 13, 388, 27, 409);
    private final Letra LETRA_122 = new Letra("Ẽ", 35, 388, 49, 409);
    private final Letra LETRA_123 = new Letra("Ë", 57, 388, 71, 409);
    private final Letra LETRA_124 = new Letra("Í", 79, 388, 87, 409);
    private final Letra LETRA_125 = new Letra("Ì", 101, 388, 109, 409);
    private final Letra LETRA_126 = new Letra("Î", 123, 388, 131, 409);
    private final Letra LETRA_127 = new Letra("Ĩ", 145, 388, 153, 409);
    private final Letra LETRA_128 = new Letra("Ï", 167, 388, 175, 409);
    private final Letra LETRA_129 = new Letra("Ó", 189, 388, 205, 409);
    private final Letra LETRA_130 = new Letra("Ò", 211, 388, 227, 409);
    private final Letra LETRA_131 = new Letra("Ô", 233, 388, 249, 409);
    private final Letra LETRA_132 = new Letra("Õ", 13, 423, 29, 444);
    private final Letra LETRA_133 = new Letra("Ö", 35, 423, 51, 444);
    private final Letra LETRA_134 = new Letra("Ú", 57, 423, 72, 444);
    private final Letra LETRA_135 = new Letra("Ù", 79, 423, 94, 444);
    private final Letra LETRA_136 = new Letra("Û", 101, 423, 116, 444);
    private final Letra LETRA_137 = new Letra("Ũ", 123, 423, 138, 444);
    private final Letra LETRA_138 = new Letra("Ü", 145, 423, 160, 444);
    private final Letra LETRA_140 = new Letra("º", 145, 423, 160, 444);
    private final Letra LETRA_141 = new Letra("ª", 145, 423, 160, 444);


    public FonteRunTime(Cor eCor, int eTamanho) {

        mCor = eCor;
        ESCREVA_COR = mCor.getValor();

        mLetras = new ArrayList<Letra>();
        mLetras.add(LETRA_00);
        mLetras.add(LETRA_01);
        mLetras.add(LETRA_02);
        mLetras.add(LETRA_03);
        mLetras.add(LETRA_04);
        mLetras.add(LETRA_05);
        mLetras.add(LETRA_06);
        mLetras.add(LETRA_07);
        mLetras.add(LETRA_08);
        mLetras.add(LETRA_09);
        mLetras.add(LETRA_10);
        mLetras.add(LETRA_11);
        mLetras.add(LETRA_12);
        mLetras.add(LETRA_13);
        mLetras.add(LETRA_14);
        mLetras.add(LETRA_15);
        mLetras.add(LETRA_16);
        mLetras.add(LETRA_17);
        mLetras.add(LETRA_18);
        mLetras.add(LETRA_19);
        mLetras.add(LETRA_20);
        mLetras.add(LETRA_21);
        mLetras.add(LETRA_22);
        mLetras.add(LETRA_23);
        mLetras.add(LETRA_24);
        mLetras.add(LETRA_25);
        mLetras.add(LETRA_26);
        mLetras.add(LETRA_27);
        mLetras.add(LETRA_28);
        mLetras.add(LETRA_29);
        mLetras.add(LETRA_30);
        mLetras.add(LETRA_31);
        mLetras.add(LETRA_32);
        mLetras.add(LETRA_33);
        mLetras.add(LETRA_34);
        mLetras.add(LETRA_35);
        mLetras.add(LETRA_36);
        mLetras.add(LETRA_37);
        mLetras.add(LETRA_38);
        mLetras.add(LETRA_39);
        mLetras.add(LETRA_40);
        mLetras.add(LETRA_41);
        mLetras.add(LETRA_42);
        mLetras.add(LETRA_43);
        mLetras.add(LETRA_44);
        mLetras.add(LETRA_45);
        mLetras.add(LETRA_46);
        mLetras.add(LETRA_47);
        mLetras.add(LETRA_48);
        mLetras.add(LETRA_49);
        mLetras.add(LETRA_50);
        mLetras.add(LETRA_51);
        mLetras.add(LETRA_52);
        mLetras.add(LETRA_53);
        mLetras.add(LETRA_54);
        mLetras.add(LETRA_55);
        mLetras.add(LETRA_56);
        mLetras.add(LETRA_57);
        mLetras.add(LETRA_58);
        mLetras.add(LETRA_59);
        mLetras.add(LETRA_60);
        mLetras.add(LETRA_61);
        mLetras.add(LETRA_62);
        mLetras.add(LETRA_63);
        mLetras.add(LETRA_64);
        mLetras.add(LETRA_65);
        mLetras.add(LETRA_66);
        mLetras.add(LETRA_67);
        mLetras.add(LETRA_68);
        mLetras.add(LETRA_69);
        mLetras.add(LETRA_70);
        mLetras.add(LETRA_71);
        mLetras.add(LETRA_72);
        mLetras.add(LETRA_73);
        mLetras.add(LETRA_74);
        mLetras.add(LETRA_75);
        mLetras.add(LETRA_76);
        mLetras.add(LETRA_77);
        mLetras.add(LETRA_78);
        mLetras.add(LETRA_79);
        mLetras.add(LETRA_80);
        mLetras.add(LETRA_81);
        mLetras.add(LETRA_82);
        mLetras.add(LETRA_83);
        mLetras.add(LETRA_84);
        mLetras.add(LETRA_85);
        mLetras.add(LETRA_86);
        mLetras.add(LETRA_87);
        mLetras.add(LETRA_88);
        mLetras.add(LETRA_89);
        mLetras.add(LETRA_90);
        mLetras.add(LETRA_91);
        mLetras.add(LETRA_92);
        mLetras.add(LETRA_93);
        mLetras.add(LETRA_94);
        mLetras.add(LETRA_95);
        mLetras.add(LETRA_96);
        mLetras.add(LETRA_97);
        mLetras.add(LETRA_98);
        mLetras.add(LETRA_99);
        mLetras.add(LETRA_100);
        mLetras.add(LETRA_101);
        mLetras.add(LETRA_102);
        mLetras.add(LETRA_103);
        mLetras.add(LETRA_104);
        mLetras.add(LETRA_105);
        mLetras.add(LETRA_106);
        mLetras.add(LETRA_107);
        mLetras.add(LETRA_108);
        mLetras.add(LETRA_109);
        mLetras.add(LETRA_110);
        mLetras.add(LETRA_111);
        mLetras.add(LETRA_112);
        mLetras.add(LETRA_113);
        mLetras.add(LETRA_114);
        mLetras.add(LETRA_115);
        mLetras.add(LETRA_116);
        mLetras.add(LETRA_117);
        mLetras.add(LETRA_118);
        mLetras.add(LETRA_119);
        mLetras.add(LETRA_120);
        mLetras.add(LETRA_121);
        mLetras.add(LETRA_122);
        mLetras.add(LETRA_123);
        mLetras.add(LETRA_124);
        mLetras.add(LETRA_125);
        mLetras.add(LETRA_126);
        mLetras.add(LETRA_127);
        mLetras.add(LETRA_128);
        mLetras.add(LETRA_129);
        mLetras.add(LETRA_130);
        mLetras.add(LETRA_131);
        mLetras.add(LETRA_132);
        mLetras.add(LETRA_133);
        mLetras.add(LETRA_134);
        mLetras.add(LETRA_135);
        mLetras.add(LETRA_136);
        mLetras.add(LETRA_137);
        mLetras.add(LETRA_138);
        mLetras.add(LETRA_140);
        mLetras.add(LETRA_141);


        String sequencia = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZçÇ_0123456789-<>.,:;/\\+-*=()[]{}!@#$%ºª";
        sequencia += "áàâãäéèêẽëíìîĩïóòôõöúùûũü";
        sequencia += "ÁÀÂÃÄÉÈÊẼËÍÌÎĨÏÓÒÔÕÖÚÙÛŨÜ";

        int eLinhas = 1;

        int tam = sequencia.length();
        while (tam > 10) {
            eLinhas += 1;
            tam -= 10;
        }

        int mLargura = (12 * (eTamanho + (eTamanho / 2)));
        int mAltura = (eTamanho + 20) * eLinhas;

        LARGURA = mLargura;
        ALTURA = mAltura;
        FONTE = eTamanho;

        mImagem = new BufferedImage(mLargura, mAltura, BufferedImage.TYPE_INT_ARGB);

        Graphics g = mImagem.getGraphics();

        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, mLargura, mAltura);

        int i = 0;
        int e = 0;
        int o = sequencia.length();

        int x = eTamanho;
        int y = eTamanho + 5;

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, eTamanho));

        Color QuadroCor = new Color(0, 0, 0);

        while (i < o) {
            String l = String.valueOf(sequencia.charAt(i));

            String iString = String.valueOf(i);
            while (iString.length() < 2) {
                iString = "0" + iString;
            }

            g.setColor(QuadroCor);
            g.drawString(l, x, y);

            int w = g.getFontMetrics().stringWidth(l);

            g.setColor(Color.RED);

            int x1 = x - 2;
            int x2 = (x) + w;
            int y1 = y - eTamanho - 2;
            int y2 = y + (eTamanho / 2) - (eTamanho / 4);

            mLetras.get(i).redefinir(l, x1, y1, x2, y2);

            x += eTamanho + (eTamanho / 2);

            i += 1;
            e += 1;
            if (e > 10) {
                e = 0;
                y += (eTamanho + 20);
                x = eTamanho;
            }


        }

    }

    public void setRenderizador(Renderizador eRenderizador) {
        mRenderizador = eRenderizador;
    }


    public int getAltura() {
        return FONTE;
    }

    public void escreveLinha(int y, int x1, int x2, String eTexto1, String eTexto2) {
        escreva(x1, y, eTexto1);
        escreva(x2, y, eTexto2);
    }


    public void escreva(int x, int y, String frase) {


        int i = 0;
        int o = frase.length();
        while (i < o) {
            String l = String.valueOf(frase.charAt(i));
            if (l.contentEquals(" ")) {
                x += 10;
            } else if (l.contentEquals("\t")) {
                x += 15;
            } else {
                boolean enc = false;
                Letra letraSelecionada = null;
                if (LETRA_00.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_00;
                } else if (LETRA_01.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_01;
                } else if (LETRA_02.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_02;
                } else if (LETRA_03.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_03;
                } else if (LETRA_04.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_04;
                } else if (LETRA_05.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_05;
                } else if (LETRA_06.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_06;
                } else if (LETRA_07.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_07;
                } else if (LETRA_08.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_08;
                } else if (LETRA_09.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_09;
                } else if (LETRA_10.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_10;
                } else if (LETRA_11.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_11;
                } else if (LETRA_12.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_12;
                } else if (LETRA_13.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_13;
                } else if (LETRA_14.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_14;
                } else if (LETRA_15.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_15;
                } else if (LETRA_16.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_16;
                } else if (LETRA_17.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_17;
                } else if (LETRA_18.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_18;
                } else if (LETRA_19.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_19;
                } else if (LETRA_20.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_20;
                } else if (LETRA_21.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_21;
                } else if (LETRA_22.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_22;
                } else if (LETRA_23.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_23;
                } else if (LETRA_24.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_24;
                } else if (LETRA_25.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_25;
                } else if (LETRA_26.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_26;
                } else if (LETRA_27.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_27;
                } else if (LETRA_28.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_28;
                } else if (LETRA_29.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_29;
                } else if (LETRA_30.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_30;
                } else if (LETRA_31.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_31;
                } else if (LETRA_32.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_32;
                } else if (LETRA_33.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_33;
                } else if (LETRA_34.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_34;
                } else if (LETRA_35.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_35;
                } else if (LETRA_36.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_36;
                } else if (LETRA_37.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_37;
                } else if (LETRA_38.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_38;
                } else if (LETRA_39.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_39;
                } else if (LETRA_40.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_40;
                } else if (LETRA_41.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_41;
                } else if (LETRA_42.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_42;
                } else if (LETRA_43.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_43;
                } else if (LETRA_44.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_44;
                } else if (LETRA_45.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_45;
                } else if (LETRA_46.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_46;
                } else if (LETRA_47.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_47;
                } else if (LETRA_48.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_48;
                } else if (LETRA_49.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_49;
                } else if (LETRA_50.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_50;
                } else if (LETRA_51.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_51;
                } else if (LETRA_52.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_52;
                } else if (LETRA_53.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_53;
                } else if (LETRA_54.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_54;
                } else if (LETRA_55.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_55;
                } else if (LETRA_56.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_56;
                } else if (LETRA_57.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_57;
                } else if (LETRA_58.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_58;
                } else if (LETRA_59.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_59;
                } else if (LETRA_60.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_60;
                } else if (LETRA_61.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_61;
                } else if (LETRA_62.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_62;
                } else if (LETRA_63.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_63;
                } else if (LETRA_64.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_64;
                } else if (LETRA_65.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_65;
                } else if (LETRA_66.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_66;
                } else if (LETRA_67.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_67;
                } else if (LETRA_68.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_68;
                } else if (LETRA_69.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_69;
                } else if (LETRA_70.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_70;
                } else if (LETRA_71.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_71;
                } else if (LETRA_72.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_72;
                } else if (LETRA_73.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_73;
                } else if (LETRA_74.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_74;
                } else if (LETRA_75.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_75;
                } else if (LETRA_76.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_76;
                } else if (LETRA_77.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_77;
                } else if (LETRA_78.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_78;
                } else if (LETRA_79.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_79;
                } else if (LETRA_80.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_80;
                } else if (LETRA_81.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_81;
                } else if (LETRA_82.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_82;
                } else if (LETRA_83.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_83;
                } else if (LETRA_84.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_84;
                } else if (LETRA_85.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_85;
                } else if (LETRA_86.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_86;
                } else if (LETRA_87.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_87;
                } else if (LETRA_88.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_88;
                } else if (LETRA_89.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_89;
                } else if (LETRA_90.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_90;
                } else if (LETRA_91.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_91;
                } else if (LETRA_92.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_92;
                } else if (LETRA_93.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_93;
                } else if (LETRA_94.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_94;
                } else if (LETRA_95.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_95;
                } else if (LETRA_96.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_96;
                } else if (LETRA_97.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_97;
                } else if (LETRA_98.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_98;
                } else if (LETRA_99.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_99;
                } else if (LETRA_100.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_100;
                } else if (LETRA_101.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_101;
                } else if (LETRA_102.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_102;
                } else if (LETRA_103.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_103;
                } else if (LETRA_104.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_104;
                } else if (LETRA_105.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_105;
                } else if (LETRA_106.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_106;
                } else if (LETRA_107.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_107;
                } else if (LETRA_108.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_108;
                } else if (LETRA_109.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_109;
                } else if (LETRA_110.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_110;
                } else if (LETRA_111.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_111;
                } else if (LETRA_112.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_112;
                } else if (LETRA_113.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_113;
                } else if (LETRA_114.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_114;
                } else if (LETRA_115.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_115;
                } else if (LETRA_116.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_116;
                } else if (LETRA_117.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_117;
                } else if (LETRA_118.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_118;
                } else if (LETRA_119.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_119;
                } else if (LETRA_120.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_120;
                } else if (LETRA_121.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_121;
                } else if (LETRA_122.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_122;
                } else if (LETRA_123.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_123;
                } else if (LETRA_124.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_124;
                } else if (LETRA_125.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_125;
                } else if (LETRA_126.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_126;
                } else if (LETRA_127.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_127;
                } else if (LETRA_128.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_128;
                } else if (LETRA_129.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_129;
                } else if (LETRA_130.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_130;
                } else if (LETRA_131.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_131;
                } else if (LETRA_132.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_132;
                } else if (LETRA_133.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_133;
                } else if (LETRA_134.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_134;
                } else if (LETRA_135.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_135;
                } else if (LETRA_136.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_136;
                } else if (LETRA_137.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_137;
                } else if (LETRA_138.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_138;
                }
                if (enc) {
                    int ox = x;
                    int yc = y;
                    for (int y1 = letraSelecionada.getY1(); y1 < letraSelecionada.getY2(); y1++) {
                        int xc = ox;
                        for (int xi = letraSelecionada.getX1(); xi < letraSelecionada.getX2(); xi++) {
                            int v = mImagem.getRGB(xi, y1);
                            if (v != 0) {
                                mRenderizador.drawPixelBruto(xc, yc, ESCREVA_COR);
                            }
                            xc += 1;
                        }
                        yc += 1;
                    }
                    x += (letraSelecionada.getX2() - letraSelecionada.getX1());
                }
            }
            i += 1;
        }
    }


    public int getLarguraDe(String frase) {
        int x = 0;
        int i = 0;
        int o = frase.length();
        while (i < o) {
            String l = String.valueOf(frase.charAt(i));
            if (l.contentEquals(" ")) {
                x += 20;
            } else if (l.contentEquals("\t")) {
                x += 30;
            } else {
                boolean enc = false;
                Letra letraSelecionada = null;
                if (LETRA_00.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_00;
                } else if (LETRA_01.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_01;
                } else if (LETRA_02.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_02;
                } else if (LETRA_03.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_03;
                } else if (LETRA_04.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_04;
                } else if (LETRA_05.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_05;
                } else if (LETRA_06.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_06;
                } else if (LETRA_07.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_07;
                } else if (LETRA_08.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_08;
                } else if (LETRA_09.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_09;
                } else if (LETRA_10.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_10;
                } else if (LETRA_11.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_11;
                } else if (LETRA_12.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_12;
                } else if (LETRA_13.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_13;
                } else if (LETRA_14.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_14;
                } else if (LETRA_15.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_15;
                } else if (LETRA_16.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_16;
                } else if (LETRA_17.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_17;
                } else if (LETRA_18.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_18;
                } else if (LETRA_19.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_19;
                } else if (LETRA_20.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_20;
                } else if (LETRA_21.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_21;
                } else if (LETRA_22.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_22;
                } else if (LETRA_23.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_23;
                } else if (LETRA_24.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_24;
                } else if (LETRA_25.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_25;
                } else if (LETRA_26.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_26;
                } else if (LETRA_27.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_27;
                } else if (LETRA_28.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_28;
                } else if (LETRA_29.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_29;
                } else if (LETRA_30.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_30;
                } else if (LETRA_31.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_31;
                } else if (LETRA_32.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_32;
                } else if (LETRA_33.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_33;
                } else if (LETRA_34.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_34;
                } else if (LETRA_35.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_35;
                } else if (LETRA_36.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_36;
                } else if (LETRA_37.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_37;
                } else if (LETRA_38.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_38;
                } else if (LETRA_39.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_39;
                } else if (LETRA_40.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_40;
                } else if (LETRA_41.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_41;
                } else if (LETRA_42.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_42;
                } else if (LETRA_43.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_43;
                } else if (LETRA_44.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_44;
                } else if (LETRA_45.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_45;
                } else if (LETRA_46.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_46;
                } else if (LETRA_47.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_47;
                } else if (LETRA_48.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_48;
                } else if (LETRA_49.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_49;
                } else if (LETRA_50.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_50;
                } else if (LETRA_51.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_51;
                } else if (LETRA_52.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_52;
                } else if (LETRA_53.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_53;
                } else if (LETRA_54.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_54;
                } else if (LETRA_55.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_55;
                } else if (LETRA_56.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_56;
                } else if (LETRA_57.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_57;
                } else if (LETRA_58.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_58;
                } else if (LETRA_59.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_59;
                } else if (LETRA_60.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_60;
                } else if (LETRA_61.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_61;
                } else if (LETRA_62.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_62;
                } else if (LETRA_63.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_63;
                } else if (LETRA_64.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_64;
                } else if (LETRA_65.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_65;
                } else if (LETRA_66.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_66;
                } else if (LETRA_67.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_67;
                } else if (LETRA_68.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_68;
                } else if (LETRA_69.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_69;
                } else if (LETRA_70.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_70;
                } else if (LETRA_71.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_71;
                } else if (LETRA_72.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_72;
                } else if (LETRA_73.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_73;
                } else if (LETRA_74.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_74;
                } else if (LETRA_75.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_75;
                } else if (LETRA_76.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_76;
                } else if (LETRA_77.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_77;
                } else if (LETRA_78.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_78;
                } else if (LETRA_79.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_79;
                } else if (LETRA_80.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_80;
                } else if (LETRA_81.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_81;
                } else if (LETRA_82.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_82;
                } else if (LETRA_83.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_83;
                } else if (LETRA_84.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_84;
                } else if (LETRA_85.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_85;
                } else if (LETRA_86.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_86;
                } else if (LETRA_87.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_87;
                } else if (LETRA_88.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_88;
                } else if (LETRA_89.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_89;
                } else if (LETRA_90.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_90;
                } else if (LETRA_91.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_91;
                } else if (LETRA_92.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_92;
                } else if (LETRA_93.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_93;
                } else if (LETRA_94.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_94;
                } else if (LETRA_95.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_95;
                } else if (LETRA_96.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_96;
                } else if (LETRA_97.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_97;
                } else if (LETRA_98.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_98;
                } else if (LETRA_99.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_99;
                } else if (LETRA_100.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_100;
                } else if (LETRA_101.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_101;
                } else if (LETRA_102.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_102;
                } else if (LETRA_103.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_103;
                } else if (LETRA_104.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_104;
                } else if (LETRA_105.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_105;
                } else if (LETRA_106.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_106;
                } else if (LETRA_107.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_107;
                } else if (LETRA_108.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_108;
                } else if (LETRA_109.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_109;
                } else if (LETRA_110.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_110;
                } else if (LETRA_111.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_111;
                } else if (LETRA_112.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_112;
                } else if (LETRA_113.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_113;
                } else if (LETRA_114.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_114;
                } else if (LETRA_115.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_115;
                } else if (LETRA_116.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_116;
                } else if (LETRA_117.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_117;
                } else if (LETRA_118.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_118;
                } else if (LETRA_119.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_119;
                } else if (LETRA_120.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_120;
                } else if (LETRA_121.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_121;
                } else if (LETRA_122.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_122;
                } else if (LETRA_123.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_123;
                } else if (LETRA_124.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_124;
                } else if (LETRA_125.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_125;
                } else if (LETRA_126.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_126;
                } else if (LETRA_127.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_127;
                } else if (LETRA_128.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_128;
                } else if (LETRA_129.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_129;
                } else if (LETRA_130.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_130;
                } else if (LETRA_131.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_131;
                } else if (LETRA_132.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_132;
                } else if (LETRA_133.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_133;
                } else if (LETRA_134.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_134;
                } else if (LETRA_135.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_135;
                } else if (LETRA_136.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_136;
                } else if (LETRA_137.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_137;
                } else if (LETRA_138.igual(l)) {
                    enc = true;
                    letraSelecionada = LETRA_138;
                }
                if (enc) {
                    x += (letraSelecionada.getX2() - letraSelecionada.getX1());
                }
            }
            i += 1;
        }
        return x;
    }

}
