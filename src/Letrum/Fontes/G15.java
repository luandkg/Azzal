
package Letrum.Fontes;

import Azzal.Renderizador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Letra;


public class G15 implements Fonte {


    public final int LARGURA = 264;
    public final int ALTURA = 490;
    public final int FONTE = 15;
    private int COR_FONTE = new Color(0, 0, 0).getRGB();
    private int COR_FUNDO = 0;


    private BufferedImage mImagem;
    private Renderizador mRenderizador;
    private ArrayList<Letra> mLetras;
    private final Letra LETRA_00 = new Letra("a", 13, 3, 25, 24);
    private final Letra LETRA_01 = new Letra("b", 35, 3, 47, 24);
    private final Letra LETRA_02 = new Letra("c", 57, 3, 68, 24);
    private final Letra LETRA_03 = new Letra("d", 79, 3, 91, 24);
    private final Letra LETRA_04 = new Letra("e", 101, 3, 112, 24);
    private final Letra LETRA_05 = new Letra("f", 123, 3, 131, 24);
    private final Letra LETRA_06 = new Letra("g", 145, 3, 157, 24);
    private final Letra LETRA_07 = new Letra("h", 167, 3, 180, 24);
    private final Letra LETRA_08 = new Letra("i", 189, 3, 196, 24);
    private final Letra LETRA_09 = new Letra("j", 211, 3, 218, 24);
    private final Letra LETRA_10 = new Letra("k", 233, 3, 245, 24);
    private final Letra LETRA_11 = new Letra("l", 13, 38, 20, 59);
    private final Letra LETRA_12 = new Letra("m", 35, 38, 52, 59);
    private final Letra LETRA_13 = new Letra("n", 57, 38, 70, 59);
    private final Letra LETRA_14 = new Letra("o", 79, 38, 90, 59);
    private final Letra LETRA_15 = new Letra("p", 101, 38, 113, 59);
    private final Letra LETRA_16 = new Letra("q", 123, 38, 136, 59);
    private final Letra LETRA_17 = new Letra("r", 145, 38, 155, 59);
    private final Letra LETRA_18 = new Letra("s", 167, 38, 177, 59);
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


    public G15() {
        mImagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
        mLetras = new ArrayList<Letra>();
        IMontadora( );
        init_data();
    }

    public G15(Cor eCor) {
        mImagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
        mLetras = new ArrayList<Letra>();
        COR_FONTE = new Color(eCor.getRed(), eCor.getGreen(), eCor.getBlue()).getRGB();
        IMontadora();
        init_data();
    }

    private void IMontadora() {

        Graphics g = mImagem.getGraphics();
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, mImagem.getWidth(), mImagem.getHeight());

    }

    private void mapear(int x, int y, int q) {
        for (int xi = x; xi < (x + q); xi++) {
            mImagem.setRGB(xi, y, COR_FONTE);
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
                    int ox = x;
                    int yc = y;
                    for (int y1 = letraSelecionada.getY1(); y1 < letraSelecionada.getY2(); y1++) {
                        int xc = ox;
                        for (int xi = letraSelecionada.getX1(); xi < letraSelecionada.getX2(); xi++) {
                            int v = mImagem.getRGB(xi, y1);
                            if (v != 0) {
                                mRenderizador.drawPixelBruto(xc, yc, v);
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

    private void colocar(int x, int y, int q) {
        for (int xi = x; xi < (x + q); xi++) {
            mImagem.setRGB(xi, y, COR_FONTE);
        }
    }

    private void init_data() {
        area_0();
        area_1();
        area_2();
        area_3();
        area_4();
        area_5();
        area_6();
        area_7();
        area_8();
        area_9();
        area_10();
        area_11();
        area_12();
        area_13();
        area_14();
        area_15();
        area_16();
        area_17();
        area_18();
        area_19();
        area_20();
        area_21();
        area_22();
        area_23();
        area_24();
        area_25();
        area_26();
        area_27();
        area_28();
        area_29();
        area_30();
        area_31();
        area_32();
        area_33();
        area_34();
        area_35();
        area_36();
        area_37();
        area_38();
        area_39();
        area_40();
        area_41();
        area_42();
        area_43();
        area_44();
        area_45();
    }

    private void area_0() {
      mapear(38, 9, 3);
      mapear(87, 9, 3);
      mapear(128, 9, 5);
      mapear(170, 9, 3);
      mapear(193, 9, 2);
      mapear(215, 9, 2);
      mapear(236, 9, 3);
      mapear(39, 10, 2);
      mapear(88, 10, 2);
      mapear(127, 10, 2);
      mapear(132, 10, 1);
      mapear(171, 10, 2);
      mapear(193, 10, 2);
      mapear(215, 10, 2);
      mapear(237, 10, 2);
      mapear(39, 11, 2);
      mapear(88, 11, 2);
      mapear(127, 11, 2);
      mapear(171, 11, 2);
      mapear(237, 11, 2);
      mapear(18, 12, 5);
      mapear(39, 12, 2);
      mapear(42, 12, 3);
      mapear(62, 12, 5);
      mapear(84, 12, 3);
      mapear(88, 12, 2);
      mapear(106, 12, 4);
      mapear(126, 12, 5);
      mapear(150, 12, 3);
      mapear(154, 12, 3);
      mapear(171, 12, 2);
      mapear(174, 12, 4);
      mapear(192, 12, 3);
      mapear(214, 12, 3);
      mapear(237, 12, 2);
      mapear(241, 12, 4);
      mapear(17, 13, 1);
      mapear(22, 13, 2);
      mapear(39, 13, 3);
      mapear(44, 13, 2);
      mapear(61, 13, 2);
      mapear(66, 13, 2);
      mapear(83, 13, 2);
      mapear(87, 13, 3);
      mapear(105, 13, 1);
      mapear(110, 13, 1);
      mapear(127, 13, 2);
      mapear(149, 13, 2);
      mapear(153, 13, 3);
      mapear(171, 13, 3);
    }

    private void area_1() {
      mapear(177, 13, 2);
      mapear(193, 13, 2);
      mapear(215, 13, 2);
      mapear(237, 13, 2);
      mapear(242, 13, 1);
      mapear(22, 14, 2);
      mapear(39, 14, 2);
      mapear(45, 14, 2);
      mapear(60, 14, 2);
      mapear(67, 14, 1);
      mapear(82, 14, 2);
      mapear(88, 14, 2);
      mapear(104, 14, 2);
      mapear(110, 14, 2);
      mapear(127, 14, 2);
      mapear(148, 14, 2);
      mapear(154, 14, 2);
      mapear(171, 14, 2);
      mapear(177, 14, 2);
      mapear(193, 14, 2);
      mapear(215, 14, 2);
      mapear(237, 14, 2);
      mapear(241, 14, 1);
      mapear(17, 15, 7);
      mapear(39, 15, 2);
      mapear(45, 15, 2);
      mapear(60, 15, 2);
      mapear(82, 15, 2);
      mapear(88, 15, 2);
      mapear(104, 15, 8);
      mapear(127, 15, 2);
      mapear(148, 15, 2);
      mapear(154, 15, 2);
      mapear(171, 15, 2);
      mapear(177, 15, 2);
      mapear(193, 15, 2);
      mapear(215, 15, 2);
      mapear(237, 15, 5);
      mapear(16, 16, 2);
      mapear(22, 16, 2);
      mapear(39, 16, 2);
      mapear(45, 16, 2);
      mapear(60, 16, 2);
      mapear(82, 16, 2);
      mapear(88, 16, 2);
      mapear(104, 16, 2);
      mapear(127, 16, 2);
      mapear(148, 16, 2);
      mapear(154, 16, 2);
      mapear(171, 16, 2);
    }

    private void area_2() {
      mapear(177, 16, 2);
      mapear(193, 16, 2);
      mapear(215, 16, 2);
      mapear(237, 16, 2);
      mapear(240, 16, 3);
      mapear(16, 17, 2);
      mapear(22, 17, 2);
      mapear(39, 17, 2);
      mapear(45, 17, 2);
      mapear(60, 17, 2);
      mapear(67, 17, 1);
      mapear(82, 17, 2);
      mapear(88, 17, 2);
      mapear(104, 17, 2);
      mapear(111, 17, 1);
      mapear(127, 17, 2);
      mapear(148, 17, 2);
      mapear(154, 17, 2);
      mapear(171, 17, 2);
      mapear(177, 17, 2);
      mapear(193, 17, 2);
      mapear(215, 17, 2);
      mapear(237, 17, 2);
      mapear(241, 17, 3);
      mapear(16, 18, 2);
      mapear(22, 18, 2);
      mapear(39, 18, 3);
      mapear(44, 18, 2);
      mapear(61, 18, 2);
      mapear(66, 18, 1);
      mapear(83, 18, 2);
      mapear(87, 18, 3);
      mapear(105, 18, 2);
      mapear(110, 18, 1);
      mapear(127, 18, 2);
      mapear(149, 18, 2);
      mapear(153, 18, 3);
      mapear(171, 18, 2);
      mapear(177, 18, 2);
      mapear(193, 18, 2);
      mapear(215, 18, 2);
      mapear(237, 18, 2);
      mapear(242, 18, 3);
      mapear(17, 19, 8);
      mapear(38, 19, 3);
      mapear(42, 19, 3);
      mapear(62, 19, 4);
      mapear(84, 19, 3);
      mapear(88, 19, 3);
      mapear(106, 19, 4);
    }

    private void area_3() {
      mapear(126, 19, 5);
      mapear(150, 19, 3);
      mapear(154, 19, 2);
      mapear(170, 19, 4);
      mapear(176, 19, 4);
      mapear(192, 19, 4);
      mapear(215, 19, 2);
      mapear(236, 19, 4);
      mapear(242, 19, 4);
      mapear(149, 20, 1);
      mapear(154, 20, 2);
      mapear(215, 20, 2);
      mapear(149, 21, 2);
      mapear(154, 21, 2);
      mapear(212, 21, 1);
      mapear(215, 21, 2);
      mapear(149, 22, 5);
      mapear(212, 22, 4);
      mapear(16, 44, 3);
      mapear(17, 45, 2);
      mapear(193, 45, 2);
      mapear(17, 46, 2);
      mapear(193, 46, 2);
      mapear(17, 47, 2);
      mapear(38, 47, 3);
      mapear(42, 47, 3);
      mapear(47, 47, 3);
      mapear(60, 47, 3);
      mapear(64, 47, 4);
      mapear(84, 47, 4);
      mapear(104, 47, 3);
      mapear(108, 47, 3);
      mapear(128, 47, 3);
      mapear(132, 47, 3);
      mapear(148, 47, 3);
      mapear(152, 47, 3);
      mapear(171, 47, 6);
      mapear(192, 47, 5);
      mapear(214, 47, 3);
      mapear(220, 47, 3);
      mapear(235, 47, 3);
      mapear(241, 47, 3);
      mapear(17, 48, 2);
      mapear(39, 48, 3);
      mapear(44, 48, 3);
      mapear(49, 48, 2);
      mapear(61, 48, 3);
      mapear(67, 48, 2);
      mapear(83, 48, 2);
      mapear(87, 48, 2);
    }

    private void area_4() {
      mapear(105, 48, 3);
      mapear(110, 48, 2);
      mapear(127, 48, 2);
      mapear(131, 48, 3);
      mapear(149, 48, 3);
      mapear(154, 48, 1);
      mapear(170, 48, 2);
      mapear(175, 48, 2);
      mapear(193, 48, 2);
      mapear(215, 48, 2);
      mapear(221, 48, 2);
      mapear(236, 48, 2);
      mapear(242, 48, 1);
      mapear(17, 49, 2);
      mapear(39, 49, 2);
      mapear(44, 49, 2);
      mapear(49, 49, 2);
      mapear(61, 49, 2);
      mapear(67, 49, 2);
      mapear(82, 49, 2);
      mapear(88, 49, 2);
      mapear(105, 49, 2);
      mapear(111, 49, 2);
      mapear(126, 49, 2);
      mapear(132, 49, 2);
      mapear(149, 49, 2);
      mapear(170, 49, 2);
      mapear(176, 49, 1);
      mapear(193, 49, 2);
      mapear(215, 49, 2);
      mapear(221, 49, 2);
      mapear(236, 49, 2);
      mapear(241, 49, 2);
      mapear(17, 50, 2);
      mapear(39, 50, 2);
      mapear(44, 50, 2);
      mapear(49, 50, 2);
      mapear(61, 50, 2);
      mapear(67, 50, 2);
      mapear(82, 50, 2);
      mapear(88, 50, 2);
      mapear(105, 50, 2);
      mapear(111, 50, 2);
      mapear(126, 50, 2);
      mapear(132, 50, 2);
      mapear(149, 50, 2);
      mapear(170, 50, 6);
      mapear(193, 50, 2);
      mapear(215, 50, 2);
      mapear(221, 50, 2);
    }

    private void area_5() {
      mapear(237, 50, 2);
      mapear(241, 50, 1);
      mapear(17, 51, 2);
      mapear(39, 51, 2);
      mapear(44, 51, 2);
      mapear(49, 51, 2);
      mapear(61, 51, 2);
      mapear(67, 51, 2);
      mapear(82, 51, 2);
      mapear(88, 51, 2);
      mapear(105, 51, 2);
      mapear(111, 51, 2);
      mapear(126, 51, 2);
      mapear(132, 51, 2);
      mapear(149, 51, 2);
      mapear(171, 51, 6);
      mapear(193, 51, 2);
      mapear(215, 51, 2);
      mapear(221, 51, 2);
      mapear(237, 51, 2);
      mapear(241, 51, 1);
      mapear(17, 52, 2);
      mapear(39, 52, 2);
      mapear(44, 52, 2);
      mapear(49, 52, 2);
      mapear(61, 52, 2);
      mapear(67, 52, 2);
      mapear(82, 52, 2);
      mapear(88, 52, 2);
      mapear(105, 52, 2);
      mapear(111, 52, 2);
      mapear(126, 52, 2);
      mapear(132, 52, 2);
      mapear(149, 52, 2);
      mapear(170, 52, 1);
      mapear(175, 52, 2);
      mapear(193, 52, 2);
      mapear(215, 52, 2);
      mapear(221, 52, 2);
      mapear(237, 52, 2);
      mapear(240, 52, 1);
      mapear(17, 53, 2);
      mapear(39, 53, 2);
      mapear(44, 53, 2);
      mapear(49, 53, 2);
      mapear(61, 53, 2);
      mapear(67, 53, 2);
      mapear(83, 53, 2);
      mapear(87, 53, 2);
      mapear(105, 53, 3);
    }

    private void area_6() {
      mapear(110, 53, 2);
      mapear(127, 53, 2);
      mapear(131, 53, 3);
      mapear(149, 53, 2);
      mapear(170, 53, 2);
      mapear(175, 53, 2);
      mapear(193, 53, 2);
      mapear(197, 53, 1);
      mapear(215, 53, 2);
      mapear(220, 53, 3);
      mapear(238, 53, 3);
      mapear(16, 54, 4);
      mapear(38, 54, 4);
      mapear(43, 54, 4);
      mapear(48, 54, 4);
      mapear(60, 54, 4);
      mapear(66, 54, 4);
      mapear(84, 54, 4);
      mapear(105, 54, 2);
      mapear(108, 54, 3);
      mapear(128, 54, 3);
      mapear(132, 54, 2);
      mapear(148, 54, 4);
      mapear(170, 54, 6);
      mapear(194, 54, 3);
      mapear(216, 54, 4);
      mapear(221, 54, 3);
      mapear(238, 54, 3);
      mapear(105, 55, 2);
      mapear(132, 55, 2);
      mapear(105, 56, 2);
      mapear(132, 56, 2);
      mapear(104, 57, 4);
      mapear(131, 57, 4);
      mapear(108, 79, 2);
      mapear(126, 79, 9);
      mapear(151, 79, 7);
      mapear(170, 79, 8);
      mapear(192, 79, 10);
      mapear(214, 79, 9);
      mapear(239, 79, 7);
      mapear(107, 80, 3);
      mapear(127, 80, 2);
      mapear(133, 80, 3);
      mapear(150, 80, 2);
      mapear(156, 80, 3);
      mapear(171, 80, 2);
      mapear(177, 80, 3);
      mapear(193, 80, 2);
      mapear(201, 80, 1);
    }

    private void area_7() {
      mapear(215, 80, 2);
      mapear(222, 80, 1);
      mapear(238, 80, 2);
      mapear(244, 80, 3);
      mapear(107, 81, 1);
      mapear(109, 81, 2);
      mapear(127, 81, 2);
      mapear(134, 81, 2);
      mapear(149, 81, 2);
      mapear(157, 81, 2);
      mapear(171, 81, 2);
      mapear(178, 81, 2);
      mapear(193, 81, 2);
      mapear(215, 81, 2);
      mapear(237, 81, 2);
      mapear(245, 81, 2);
      mapear(15, 82, 4);
      mapear(21, 82, 2);
      mapear(25, 82, 3);
      mapear(37, 82, 4);
      mapear(42, 82, 4);
      mapear(59, 82, 4);
      mapear(65, 82, 3);
      mapear(82, 82, 7);
      mapear(107, 82, 1);
      mapear(109, 82, 2);
      mapear(127, 82, 2);
      mapear(134, 82, 2);
      mapear(148, 82, 2);
      mapear(158, 82, 1);
      mapear(171, 82, 2);
      mapear(179, 82, 2);
      mapear(193, 82, 2);
      mapear(215, 82, 2);
      mapear(236, 82, 2);
      mapear(246, 82, 1);
      mapear(16, 83, 2);
      mapear(21, 83, 2);
      mapear(26, 83, 1);
      mapear(38, 83, 3);
      mapear(43, 83, 1);
      mapear(60, 83, 2);
      mapear(66, 83, 1);
      mapear(82, 83, 1);
      mapear(86, 83, 3);
      mapear(106, 83, 1);
      mapear(109, 83, 3);
      mapear(127, 83, 2);
      mapear(133, 83, 3);
      mapear(148, 83, 2);
    }

    private void area_8() {
      mapear(171, 83, 2);
      mapear(179, 83, 2);
      mapear(193, 83, 2);
      mapear(199, 83, 1);
      mapear(215, 83, 2);
      mapear(220, 83, 1);
      mapear(236, 83, 2);
      mapear(16, 84, 2);
      mapear(21, 84, 3);
      mapear(26, 84, 1);
      mapear(39, 84, 2);
      mapear(42, 84, 2);
      mapear(61, 84, 2);
      mapear(65, 84, 2);
      mapear(85, 84, 3);
      mapear(106, 84, 1);
      mapear(110, 84, 2);
      mapear(127, 84, 8);
      mapear(148, 84, 2);
      mapear(171, 84, 2);
      mapear(179, 84, 2);
      mapear(193, 84, 7);
      mapear(215, 84, 6);
      mapear(236, 84, 2);
      mapear(17, 85, 2);
      mapear(20, 85, 1);
      mapear(22, 85, 2);
      mapear(25, 85, 1);
      mapear(40, 85, 3);
      mapear(61, 85, 2);
      mapear(65, 85, 1);
      mapear(85, 85, 2);
      mapear(105, 85, 1);
      mapear(110, 85, 2);
      mapear(127, 85, 2);
      mapear(134, 85, 3);
      mapear(148, 85, 2);
      mapear(171, 85, 2);
      mapear(179, 85, 2);
      mapear(193, 85, 2);
      mapear(199, 85, 1);
      mapear(215, 85, 2);
      mapear(220, 85, 1);
      mapear(236, 85, 2);
      mapear(244, 85, 3);
      mapear(17, 86, 2);
      mapear(20, 86, 1);
      mapear(22, 86, 2);
      mapear(25, 86, 1);
      mapear(40, 86, 3);
    }

    private void area_9() {
      mapear(61, 86, 3);
      mapear(65, 86, 1);
      mapear(84, 86, 2);
      mapear(105, 86, 8);
      mapear(127, 86, 2);
      mapear(135, 86, 2);
      mapear(148, 86, 2);
      mapear(171, 86, 2);
      mapear(179, 86, 2);
      mapear(193, 86, 2);
      mapear(215, 86, 2);
      mapear(236, 86, 2);
      mapear(245, 86, 2);
      mapear(17, 87, 2);
      mapear(20, 87, 1);
      mapear(23, 87, 3);
      mapear(39, 87, 1);
      mapear(41, 87, 3);
      mapear(62, 87, 3);
      mapear(83, 87, 3);
      mapear(104, 87, 2);
      mapear(111, 87, 2);
      mapear(127, 87, 2);
      mapear(135, 87, 2);
      mapear(149, 87, 2);
      mapear(158, 87, 1);
      mapear(171, 87, 2);
      mapear(178, 87, 2);
      mapear(193, 87, 2);
      mapear(215, 87, 2);
      mapear(237, 87, 2);
      mapear(245, 87, 2);
      mapear(18, 88, 2);
      mapear(23, 88, 2);
      mapear(38, 88, 2);
      mapear(42, 88, 3);
      mapear(62, 88, 3);
      mapear(82, 88, 3);
      mapear(88, 88, 1);
      mapear(104, 88, 1);
      mapear(112, 88, 2);
      mapear(127, 88, 2);
      mapear(134, 88, 3);
      mapear(150, 88, 2);
      mapear(157, 88, 1);
      mapear(171, 88, 2);
      mapear(177, 88, 3);
      mapear(193, 88, 2);
      mapear(201, 88, 1);
      mapear(215, 88, 2);
    }

    private void area_10() {
      mapear(238, 88, 2);
      mapear(245, 88, 2);
      mapear(18, 89, 2);
      mapear(23, 89, 2);
      mapear(37, 89, 3);
      mapear(42, 89, 4);
      mapear(63, 89, 2);
      mapear(82, 89, 7);
      mapear(103, 89, 3);
      mapear(111, 89, 4);
      mapear(126, 89, 9);
      mapear(151, 89, 6);
      mapear(170, 89, 8);
      mapear(192, 89, 10);
      mapear(214, 89, 4);
      mapear(239, 89, 7);
      mapear(63, 90, 1);
      mapear(60, 91, 1);
      mapear(63, 91, 1);
      mapear(60, 92, 3);
      mapear(16, 114, 4);
      mapear(24, 114, 4);
      mapear(38, 114, 4);
      mapear(61, 114, 4);
      mapear(82, 114, 4);
      mapear(87, 114, 6);
      mapear(104, 114, 4);
      mapear(126, 114, 4);
      mapear(137, 114, 3);
      mapear(148, 114, 3);
      mapear(157, 114, 3);
      mapear(173, 114, 6);
      mapear(192, 114, 8);
      mapear(217, 114, 6);
      mapear(236, 114, 8);
      mapear(17, 115, 2);
      mapear(25, 115, 2);
      mapear(39, 115, 2);
      mapear(62, 115, 2);
      mapear(83, 115, 2);
      mapear(89, 115, 2);
      mapear(105, 115, 2);
      mapear(127, 115, 4);
      mapear(136, 115, 3);
      mapear(149, 115, 3);
      mapear(158, 115, 1);
      mapear(172, 115, 2);
      mapear(178, 115, 2);
      mapear(193, 115, 2);
      mapear(199, 115, 2);
    }

    private void area_11() {
      mapear(216, 115, 2);
      mapear(222, 115, 2);
      mapear(237, 115, 2);
      mapear(243, 115, 3);
      mapear(17, 116, 2);
      mapear(25, 116, 2);
      mapear(39, 116, 2);
      mapear(62, 116, 2);
      mapear(83, 116, 2);
      mapear(88, 116, 1);
      mapear(105, 116, 2);
      mapear(127, 116, 1);
      mapear(129, 116, 2);
      mapear(136, 116, 3);
      mapear(149, 116, 4);
      mapear(158, 116, 1);
      mapear(171, 116, 2);
      mapear(179, 116, 2);
      mapear(193, 116, 2);
      mapear(200, 116, 2);
      mapear(215, 116, 2);
      mapear(223, 116, 2);
      mapear(237, 116, 2);
      mapear(244, 116, 2);
      mapear(17, 117, 2);
      mapear(25, 117, 2);
      mapear(39, 117, 2);
      mapear(62, 117, 2);
      mapear(83, 117, 2);
      mapear(86, 117, 2);
      mapear(105, 117, 2);
      mapear(127, 117, 1);
      mapear(129, 117, 3);
      mapear(135, 117, 1);
      mapear(137, 117, 2);
      mapear(149, 117, 1);
      mapear(151, 117, 3);
      mapear(158, 117, 1);
      mapear(170, 117, 2);
      mapear(180, 117, 2);
      mapear(193, 117, 2);
      mapear(200, 117, 2);
      mapear(214, 117, 2);
      mapear(224, 117, 2);
      mapear(237, 117, 2);
      mapear(244, 117, 2);
      mapear(17, 118, 2);
      mapear(25, 118, 2);
      mapear(39, 118, 2);
      mapear(62, 118, 2);
    }

    private void area_12() {
      mapear(83, 118, 4);
      mapear(105, 118, 2);
      mapear(127, 118, 1);
      mapear(130, 118, 2);
      mapear(135, 118, 1);
      mapear(137, 118, 2);
      mapear(149, 118, 1);
      mapear(152, 118, 3);
      mapear(158, 118, 1);
      mapear(170, 118, 2);
      mapear(180, 118, 2);
      mapear(193, 118, 2);
      mapear(199, 118, 2);
      mapear(214, 118, 2);
      mapear(224, 118, 2);
      mapear(237, 118, 2);
      mapear(243, 118, 3);
      mapear(17, 119, 10);
      mapear(39, 119, 2);
      mapear(62, 119, 2);
      mapear(83, 119, 5);
      mapear(105, 119, 2);
      mapear(127, 119, 1);
      mapear(130, 119, 3);
      mapear(134, 119, 1);
      mapear(137, 119, 2);
      mapear(149, 119, 1);
      mapear(153, 119, 3);
      mapear(158, 119, 1);
      mapear(170, 119, 2);
      mapear(180, 119, 2);
      mapear(193, 119, 7);
      mapear(214, 119, 2);
      mapear(224, 119, 2);
      mapear(237, 119, 7);
      mapear(17, 120, 2);
      mapear(25, 120, 2);
      mapear(39, 120, 2);
      mapear(62, 120, 2);
      mapear(83, 120, 2);
      mapear(86, 120, 3);
      mapear(105, 120, 2);
      mapear(127, 120, 1);
      mapear(131, 120, 2);
      mapear(134, 120, 1);
      mapear(137, 120, 2);
      mapear(149, 120, 1);
      mapear(154, 120, 3);
      mapear(158, 120, 1);
      mapear(170, 120, 2);
    }

    private void area_13() {
      mapear(180, 120, 2);
      mapear(193, 120, 2);
      mapear(214, 120, 2);
      mapear(224, 120, 2);
      mapear(237, 120, 2);
      mapear(242, 120, 3);
      mapear(17, 121, 2);
      mapear(25, 121, 2);
      mapear(39, 121, 2);
      mapear(62, 121, 2);
      mapear(83, 121, 2);
      mapear(87, 121, 3);
      mapear(105, 121, 2);
      mapear(127, 121, 1);
      mapear(131, 121, 3);
      mapear(137, 121, 2);
      mapear(149, 121, 1);
      mapear(155, 121, 4);
      mapear(170, 121, 2);
      mapear(180, 121, 2);
      mapear(193, 121, 2);
      mapear(214, 121, 2);
      mapear(224, 121, 2);
      mapear(237, 121, 2);
      mapear(243, 121, 2);
      mapear(17, 122, 2);
      mapear(25, 122, 2);
      mapear(39, 122, 2);
      mapear(62, 122, 2);
      mapear(83, 122, 2);
      mapear(88, 122, 3);
      mapear(105, 122, 2);
      mapear(112, 122, 1);
      mapear(127, 122, 1);
      mapear(132, 122, 2);
      mapear(137, 122, 2);
      mapear(149, 122, 1);
      mapear(156, 122, 3);
      mapear(171, 122, 2);
      mapear(179, 122, 2);
      mapear(193, 122, 2);
      mapear(215, 122, 2);
      mapear(223, 122, 2);
      mapear(237, 122, 2);
      mapear(244, 122, 2);
      mapear(17, 123, 2);
      mapear(25, 123, 2);
      mapear(39, 123, 2);
      mapear(62, 123, 2);
      mapear(83, 123, 2);
    }

    private void area_14() {
      mapear(89, 123, 3);
      mapear(105, 123, 2);
      mapear(112, 123, 1);
      mapear(127, 123, 1);
      mapear(137, 123, 2);
      mapear(149, 123, 1);
      mapear(157, 123, 2);
      mapear(172, 123, 2);
      mapear(178, 123, 2);
      mapear(193, 123, 2);
      mapear(216, 123, 2);
      mapear(222, 123, 3);
      mapear(237, 123, 2);
      mapear(244, 123, 2);
      mapear(16, 124, 4);
      mapear(24, 124, 4);
      mapear(38, 124, 4);
      mapear(62, 124, 2);
      mapear(82, 124, 4);
      mapear(90, 124, 4);
      mapear(104, 124, 9);
      mapear(126, 124, 3);
      mapear(136, 124, 4);
      mapear(148, 124, 3);
      mapear(158, 124, 1);
      mapear(173, 124, 6);
      mapear(192, 124, 4);
      mapear(217, 124, 6);
      mapear(236, 124, 4);
      mapear(245, 124, 3);
      mapear(62, 125, 2);
      mapear(220, 125, 2);
      mapear(58, 126, 1);
      mapear(62, 126, 2);
      mapear(221, 126, 4);
      mapear(59, 127, 4);
      mapear(222, 127, 3);
      mapear(18, 149, 5);
      mapear(37, 149, 10);
      mapear(60, 149, 4);
      mapear(68, 149, 3);
      mapear(81, 149, 4);
      mapear(90, 149, 3);
      mapear(103, 149, 4);
      mapear(111, 149, 3);
      mapear(117, 149, 3);
      mapear(125, 149, 5);
      mapear(133, 149, 3);
      mapear(148, 149, 5);
      mapear(155, 149, 3);
    }

    private void area_15() {
      mapear(170, 149, 10);
      mapear(216, 149, 5);
      mapear(239, 149, 2);
      mapear(17, 150, 1);
      mapear(22, 150, 2);
      mapear(37, 150, 1);
      mapear(41, 150, 2);
      mapear(46, 150, 1);
      mapear(61, 150, 2);
      mapear(69, 150, 1);
      mapear(82, 150, 2);
      mapear(91, 150, 1);
      mapear(104, 150, 2);
      mapear(111, 150, 3);
      mapear(118, 150, 1);
      mapear(126, 150, 3);
      mapear(133, 150, 2);
      mapear(149, 150, 3);
      mapear(156, 150, 1);
      mapear(170, 150, 1);
      mapear(177, 150, 3);
      mapear(215, 150, 2);
      mapear(220, 150, 2);
      mapear(237, 150, 4);
      mapear(16, 151, 2);
      mapear(23, 151, 1);
      mapear(37, 151, 1);
      mapear(41, 151, 2);
      mapear(46, 151, 1);
      mapear(61, 151, 2);
      mapear(69, 151, 1);
      mapear(83, 151, 2);
      mapear(90, 151, 1);
      mapear(104, 151, 2);
      mapear(111, 151, 3);
      mapear(117, 151, 1);
      mapear(127, 151, 3);
      mapear(133, 151, 1);
      mapear(150, 151, 2);
      mapear(155, 151, 1);
      mapear(170, 151, 1);
      mapear(176, 151, 3);
      mapear(215, 151, 1);
      mapear(221, 151, 1);
      mapear(237, 151, 1);
      mapear(239, 151, 2);
      mapear(16, 152, 2);
      mapear(41, 152, 2);
      mapear(61, 152, 2);
      mapear(69, 152, 1);
    }

    private void area_16() {
      mapear(83, 152, 2);
      mapear(90, 152, 1);
      mapear(105, 152, 2);
      mapear(110, 152, 1);
      mapear(112, 152, 2);
      mapear(117, 152, 1);
      mapear(128, 152, 2);
      mapear(132, 152, 1);
      mapear(150, 152, 3);
      mapear(154, 152, 2);
      mapear(175, 152, 3);
      mapear(214, 152, 2);
      mapear(221, 152, 2);
      mapear(239, 152, 2);
      mapear(16, 153, 5);
      mapear(41, 153, 2);
      mapear(61, 153, 2);
      mapear(69, 153, 1);
      mapear(83, 153, 3);
      mapear(90, 153, 1);
      mapear(105, 153, 2);
      mapear(110, 153, 1);
      mapear(113, 153, 2);
      mapear(117, 153, 1);
      mapear(129, 153, 3);
      mapear(151, 153, 2);
      mapear(154, 153, 1);
      mapear(175, 153, 2);
      mapear(214, 153, 2);
      mapear(221, 153, 2);
      mapear(239, 153, 2);
      mapear(17, 154, 7);
      mapear(41, 154, 2);
      mapear(61, 154, 2);
      mapear(69, 154, 1);
      mapear(84, 154, 2);
      mapear(89, 154, 1);
      mapear(105, 154, 2);
      mapear(110, 154, 1);
      mapear(113, 154, 2);
      mapear(116, 154, 1);
      mapear(129, 154, 4);
      mapear(151, 154, 3);
      mapear(174, 154, 2);
      mapear(214, 154, 2);
      mapear(221, 154, 2);
      mapear(239, 154, 2);
      mapear(21, 155, 4);
      mapear(41, 155, 2);
      mapear(61, 155, 2);
    }

    private void area_17() {
      mapear(69, 155, 1);
      mapear(84, 155, 2);
      mapear(89, 155, 1);
      mapear(106, 155, 2);
      mapear(109, 155, 1);
      mapear(113, 155, 2);
      mapear(116, 155, 1);
      mapear(129, 155, 1);
      mapear(131, 155, 2);
      mapear(152, 155, 2);
      mapear(173, 155, 2);
      mapear(214, 155, 2);
      mapear(221, 155, 2);
      mapear(239, 155, 2);
      mapear(23, 156, 2);
      mapear(41, 156, 2);
      mapear(61, 156, 2);
      mapear(69, 156, 1);
      mapear(85, 156, 2);
      mapear(88, 156, 1);
      mapear(106, 156, 2);
      mapear(109, 156, 1);
      mapear(114, 156, 3);
      mapear(128, 156, 2);
      mapear(132, 156, 2);
      mapear(152, 156, 2);
      mapear(172, 156, 3);
      mapear(214, 156, 2);
      mapear(221, 156, 2);
      mapear(239, 156, 2);
      mapear(16, 157, 1);
      mapear(23, 157, 2);
      mapear(41, 157, 2);
      mapear(61, 157, 2);
      mapear(69, 157, 1);
      mapear(85, 157, 2);
      mapear(88, 157, 1);
      mapear(106, 157, 2);
      mapear(109, 157, 1);
      mapear(114, 157, 3);
      mapear(128, 157, 1);
      mapear(132, 157, 3);
      mapear(152, 157, 2);
      mapear(171, 157, 3);
      mapear(179, 157, 1);
      mapear(215, 157, 1);
      mapear(221, 157, 1);
      mapear(239, 157, 2);
      mapear(16, 158, 2);
      mapear(23, 158, 2);
    }

    private void area_18() {
      mapear(41, 158, 2);
      mapear(62, 158, 2);
      mapear(68, 158, 1);
      mapear(85, 158, 4);
      mapear(107, 158, 2);
      mapear(114, 158, 2);
      mapear(127, 158, 1);
      mapear(133, 158, 3);
      mapear(152, 158, 2);
      mapear(170, 158, 3);
      mapear(179, 158, 1);
      mapear(215, 158, 2);
      mapear(220, 158, 2);
      mapear(239, 158, 2);
      mapear(17, 159, 6);
      mapear(40, 159, 4);
      mapear(63, 159, 5);
      mapear(86, 159, 2);
      mapear(107, 159, 2);
      mapear(115, 159, 1);
      mapear(125, 159, 4);
      mapear(132, 159, 5);
      mapear(151, 159, 4);
      mapear(170, 159, 10);
      mapear(216, 159, 5);
      mapear(237, 159, 6);
      mapear(191, 163, 8);
      mapear(17, 184, 5);
      mapear(39, 184, 5);
      mapear(64, 184, 3);
      mapear(83, 184, 6);
      mapear(107, 184, 5);
      mapear(126, 184, 8);
      mapear(150, 184, 5);
      mapear(172, 184, 5);
      mapear(16, 185, 2);
      mapear(21, 185, 2);
      mapear(38, 185, 2);
      mapear(43, 185, 3);
      mapear(63, 185, 4);
      mapear(83, 185, 6);
      mapear(105, 185, 2);
      mapear(110, 185, 2);
      mapear(126, 185, 8);
      mapear(148, 185, 2);
      mapear(155, 185, 2);
      mapear(171, 185, 2);
      mapear(176, 185, 2);
      mapear(16, 186, 1);
      mapear(22, 186, 2);
    }

    private void area_19() {
      mapear(38, 186, 1);
      mapear(44, 186, 2);
      mapear(63, 186, 1);
      mapear(65, 186, 2);
      mapear(83, 186, 1);
      mapear(105, 186, 1);
      mapear(111, 186, 1);
      mapear(126, 186, 1);
      mapear(132, 186, 2);
      mapear(148, 186, 2);
      mapear(155, 186, 2);
      mapear(170, 186, 2);
      mapear(177, 186, 2);
      mapear(22, 187, 2);
      mapear(44, 187, 2);
      mapear(62, 187, 1);
      mapear(65, 187, 2);
      mapear(83, 187, 1);
      mapear(104, 187, 2);
      mapear(132, 187, 1);
      mapear(148, 187, 2);
      mapear(155, 187, 2);
      mapear(170, 187, 2);
      mapear(177, 187, 2);
      mapear(223, 187, 1);
      mapear(237, 187, 1);
      mapear(22, 188, 2);
      mapear(43, 188, 3);
      mapear(62, 188, 1);
      mapear(65, 188, 2);
      mapear(83, 188, 5);
      mapear(104, 188, 2);
      mapear(107, 188, 4);
      mapear(131, 188, 2);
      mapear(148, 188, 2);
      mapear(155, 188, 2);
      mapear(170, 188, 2);
      mapear(177, 188, 2);
      mapear(220, 188, 4);
      mapear(237, 188, 4);
      mapear(21, 189, 2);
      mapear(41, 189, 3);
      mapear(61, 189, 1);
      mapear(65, 189, 2);
      mapear(83, 189, 1);
      mapear(87, 189, 2);
      mapear(104, 189, 3);
      mapear(110, 189, 2);
      mapear(131, 189, 1);
      mapear(150, 189, 5);
    }

    private void area_20() {
      mapear(171, 189, 2);
      mapear(176, 189, 3);
      mapear(217, 189, 5);
      mapear(239, 189, 5);
      mapear(20, 190, 3);
      mapear(43, 190, 3);
      mapear(60, 190, 2);
      mapear(65, 190, 2);
      mapear(88, 190, 2);
      mapear(104, 190, 2);
      mapear(111, 190, 2);
      mapear(130, 190, 2);
      mapear(148, 190, 2);
      mapear(155, 190, 2);
      mapear(172, 190, 4);
      mapear(177, 190, 2);
      mapear(192, 190, 5);
      mapear(215, 190, 4);
      mapear(242, 190, 4);
      mapear(19, 191, 2);
      mapear(44, 191, 2);
      mapear(60, 191, 9);
      mapear(88, 191, 2);
      mapear(104, 191, 2);
      mapear(111, 191, 2);
      mapear(130, 191, 1);
      mapear(148, 191, 2);
      mapear(155, 191, 2);
      mapear(177, 191, 2);
      mapear(192, 191, 5);
      mapear(215, 191, 4);
      mapear(242, 191, 4);
      mapear(17, 192, 2);
      mapear(23, 192, 1);
      mapear(38, 192, 1);
      mapear(44, 192, 2);
      mapear(65, 192, 2);
      mapear(82, 192, 1);
      mapear(88, 192, 2);
      mapear(104, 192, 2);
      mapear(111, 192, 2);
      mapear(129, 192, 2);
      mapear(148, 192, 2);
      mapear(155, 192, 2);
      mapear(171, 192, 1);
      mapear(177, 192, 1);
      mapear(217, 192, 5);
      mapear(239, 192, 5);
      mapear(16, 193, 8);
      mapear(38, 193, 2);
    }

    private void area_21() {
      mapear(43, 193, 3);
      mapear(65, 193, 2);
      mapear(82, 193, 2);
      mapear(87, 193, 2);
      mapear(105, 193, 2);
      mapear(110, 193, 2);
      mapear(129, 193, 1);
      mapear(148, 193, 2);
      mapear(155, 193, 2);
      mapear(171, 193, 2);
      mapear(176, 193, 2);
      mapear(220, 193, 4);
      mapear(237, 193, 4);
      mapear(16, 194, 8);
      mapear(39, 194, 5);
      mapear(63, 194, 6);
      mapear(83, 194, 5);
      mapear(106, 194, 5);
      mapear(128, 194, 2);
      mapear(150, 194, 5);
      mapear(171, 194, 5);
      mapear(223, 194, 1);
      mapear(237, 194, 1);
      mapear(107, 219, 1);
      mapear(125, 219, 2);
      mapear(194, 219, 1);
      mapear(239, 219, 1);
      mapear(106, 220, 2);
      mapear(125, 220, 2);
      mapear(152, 220, 2);
      mapear(191, 220, 1);
      mapear(194, 220, 1);
      mapear(197, 220, 1);
      mapear(238, 220, 1);
      mapear(106, 221, 2);
      mapear(126, 221, 2);
      mapear(152, 221, 2);
      mapear(192, 221, 5);
      mapear(237, 221, 2);
      mapear(106, 222, 1);
      mapear(126, 222, 2);
      mapear(152, 222, 2);
      mapear(192, 222, 5);
      mapear(215, 222, 9);
      mapear(237, 222, 1);
      mapear(60, 223, 2);
      mapear(83, 223, 2);
      mapear(105, 223, 2);
      mapear(126, 223, 2);
      mapear(152, 223, 2);
    }

    private void area_22() {
      mapear(191, 223, 2);
      mapear(194, 223, 1);
      mapear(196, 223, 2);
      mapear(215, 223, 9);
      mapear(236, 223, 2);
      mapear(60, 224, 2);
      mapear(83, 224, 2);
      mapear(105, 224, 2);
      mapear(127, 224, 1);
      mapear(148, 224, 10);
      mapear(194, 224, 1);
      mapear(236, 224, 2);
      mapear(105, 225, 1);
      mapear(127, 225, 2);
      mapear(148, 225, 10);
      mapear(170, 225, 5);
      mapear(236, 225, 2);
      mapear(104, 226, 2);
      mapear(127, 226, 2);
      mapear(152, 226, 2);
      mapear(170, 226, 5);
      mapear(215, 226, 9);
      mapear(236, 226, 2);
      mapear(104, 227, 2);
      mapear(128, 227, 1);
      mapear(152, 227, 2);
      mapear(215, 227, 9);
      mapear(236, 227, 2);
      mapear(16, 228, 2);
      mapear(39, 228, 2);
      mapear(60, 228, 2);
      mapear(83, 228, 2);
      mapear(104, 228, 2);
      mapear(128, 228, 2);
      mapear(152, 228, 2);
      mapear(236, 228, 2);
      mapear(16, 229, 2);
      mapear(39, 229, 2);
      mapear(60, 229, 2);
      mapear(83, 229, 2);
      mapear(103, 229, 2);
      mapear(128, 229, 2);
      mapear(152, 229, 2);
      mapear(237, 229, 1);
      mapear(39, 230, 2);
      mapear(83, 230, 2);
      mapear(103, 230, 2);
      mapear(129, 230, 1);
      mapear(237, 230, 2);
      mapear(38, 231, 2);
    }

    private void area_23() {
      mapear(82, 231, 2);
      mapear(238, 231, 1);
      mapear(38, 232, 1);
      mapear(82, 232, 1);
      mapear(239, 232, 1);
      mapear(16, 254, 1);
      mapear(39, 254, 4);
      mapear(60, 254, 4);
      mapear(86, 254, 3);
      mapear(105, 254, 3);
      mapear(127, 254, 2);
      mapear(152, 254, 6);
      mapear(174, 254, 2);
      mapear(178, 254, 1);
      mapear(195, 254, 1);
      mapear(214, 254, 4);
      mapear(222, 254, 2);
      mapear(17, 255, 1);
      mapear(39, 255, 2);
      mapear(62, 255, 2);
      mapear(85, 255, 2);
      mapear(107, 255, 2);
      mapear(127, 255, 2);
      mapear(150, 255, 3);
      mapear(157, 255, 2);
      mapear(174, 255, 1);
      mapear(177, 255, 2);
      mapear(195, 255, 1);
      mapear(213, 255, 2);
      mapear(217, 255, 2);
      mapear(222, 255, 1);
      mapear(17, 256, 2);
      mapear(39, 256, 2);
      mapear(62, 256, 2);
      mapear(85, 256, 2);
      mapear(107, 256, 2);
      mapear(127, 256, 2);
      mapear(149, 256, 2);
      mapear(159, 256, 1);
      mapear(171, 256, 10);
      mapear(193, 256, 6);
      mapear(213, 256, 2);
      mapear(217, 256, 2);
      mapear(221, 256, 1);
      mapear(18, 257, 1);
      mapear(39, 257, 2);
      mapear(62, 257, 2);
      mapear(85, 257, 2);
      mapear(107, 257, 2);
      mapear(127, 257, 2);
    }

    private void area_24() {
      mapear(149, 257, 2);
      mapear(153, 257, 5);
      mapear(159, 257, 2);
      mapear(171, 257, 10);
      mapear(192, 257, 2);
      mapear(195, 257, 1);
      mapear(198, 257, 2);
      mapear(213, 257, 2);
      mapear(217, 257, 2);
      mapear(221, 257, 1);
      mapear(238, 257, 5);
      mapear(18, 258, 2);
      mapear(39, 258, 2);
      mapear(62, 258, 2);
      mapear(85, 258, 2);
      mapear(107, 258, 2);
      mapear(127, 258, 2);
      mapear(148, 258, 2);
      mapear(152, 258, 2);
      mapear(156, 258, 2);
      mapear(160, 258, 1);
      mapear(173, 258, 2);
      mapear(177, 258, 1);
      mapear(192, 258, 2);
      mapear(195, 258, 1);
      mapear(199, 258, 1);
      mapear(213, 258, 2);
      mapear(217, 258, 2);
      mapear(220, 258, 1);
      mapear(237, 258, 2);
      mapear(242, 258, 2);
      mapear(18, 259, 2);
      mapear(39, 259, 2);
      mapear(62, 259, 2);
      mapear(85, 259, 2);
      mapear(107, 259, 2);
      mapear(127, 259, 2);
      mapear(148, 259, 2);
      mapear(152, 259, 2);
      mapear(156, 259, 2);
      mapear(160, 259, 1);
      mapear(173, 259, 1);
      mapear(176, 259, 2);
      mapear(192, 259, 4);
      mapear(214, 259, 4);
      mapear(219, 259, 2);
      mapear(222, 259, 4);
      mapear(236, 259, 2);
      mapear(243, 259, 1);
      mapear(18, 260, 2);
    }

    private void area_25() {
      mapear(39, 260, 2);
      mapear(62, 260, 2);
      mapear(83, 260, 3);
      mapear(108, 260, 3);
      mapear(127, 260, 2);
      mapear(148, 260, 2);
      mapear(152, 260, 2);
      mapear(156, 260, 2);
      mapear(160, 260, 1);
      mapear(173, 260, 1);
      mapear(176, 260, 2);
      mapear(193, 260, 6);
      mapear(219, 260, 1);
      mapear(221, 260, 2);
      mapear(225, 260, 2);
      mapear(236, 260, 2);
      mapear(18, 261, 2);
      mapear(39, 261, 2);
      mapear(62, 261, 2);
      mapear(85, 261, 2);
      mapear(107, 261, 2);
      mapear(127, 261, 2);
      mapear(148, 261, 2);
      mapear(152, 261, 2);
      mapear(156, 261, 2);
      mapear(160, 261, 1);
      mapear(170, 261, 10);
      mapear(195, 261, 5);
      mapear(218, 261, 1);
      mapear(221, 261, 2);
      mapear(225, 261, 2);
      mapear(236, 261, 2);
      mapear(18, 262, 2);
      mapear(39, 262, 2);
      mapear(62, 262, 2);
      mapear(85, 262, 2);
      mapear(107, 262, 2);
      mapear(148, 262, 2);
      mapear(152, 262, 2);
      mapear(156, 262, 2);
      mapear(159, 262, 1);
      mapear(170, 262, 10);
      mapear(192, 262, 1);
      mapear(195, 262, 1);
      mapear(198, 262, 2);
      mapear(218, 262, 1);
      mapear(221, 262, 2);
      mapear(225, 262, 2);
      mapear(236, 262, 2);
      mapear(243, 262, 1);
    }

    private void area_26() {
      mapear(18, 263, 2);
      mapear(39, 263, 2);
      mapear(62, 263, 2);
      mapear(85, 263, 2);
      mapear(107, 263, 2);
      mapear(127, 263, 2);
      mapear(149, 263, 2);
      mapear(153, 263, 6);
      mapear(172, 263, 2);
      mapear(176, 263, 1);
      mapear(192, 263, 2);
      mapear(195, 263, 1);
      mapear(198, 263, 2);
      mapear(217, 263, 1);
      mapear(221, 263, 2);
      mapear(225, 263, 2);
      mapear(237, 263, 2);
      mapear(242, 263, 1);
      mapear(18, 264, 1);
      mapear(39, 264, 2);
      mapear(62, 264, 2);
      mapear(85, 264, 2);
      mapear(107, 264, 2);
      mapear(127, 264, 2);
      mapear(149, 264, 2);
      mapear(172, 264, 1);
      mapear(175, 264, 2);
      mapear(193, 264, 6);
      mapear(216, 264, 2);
      mapear(222, 264, 4);
      mapear(238, 264, 4);
      mapear(17, 265, 2);
      mapear(39, 265, 2);
      mapear(62, 265, 2);
      mapear(85, 265, 2);
      mapear(107, 265, 2);
      mapear(150, 265, 3);
      mapear(195, 265, 1);
      mapear(240, 265, 1);
      mapear(17, 266, 1);
      mapear(39, 266, 4);
      mapear(60, 266, 4);
      mapear(86, 266, 3);
      mapear(105, 266, 3);
      mapear(152, 266, 6);
      mapear(195, 266, 1);
      mapear(240, 266, 1);
      mapear(16, 267, 1);
      mapear(238, 267, 3);
      mapear(43, 288, 2);
    }

    private void area_27() {
      mapear(61, 288, 2);
      mapear(85, 288, 2);
      mapear(105, 288, 3);
      mapear(109, 288, 1);
      mapear(127, 288, 2);
      mapear(130, 288, 2);
      mapear(153, 288, 2);
      mapear(171, 288, 2);
      mapear(195, 288, 2);
      mapear(216, 288, 3);
      mapear(220, 288, 1);
      mapear(237, 288, 2);
      mapear(240, 288, 2);
      mapear(19, 289, 7);
      mapear(42, 289, 2);
      mapear(62, 289, 2);
      mapear(84, 289, 4);
      mapear(105, 289, 1);
      mapear(107, 289, 3);
      mapear(127, 289, 2);
      mapear(130, 289, 2);
      mapear(152, 289, 2);
      mapear(172, 289, 2);
      mapear(194, 289, 4);
      mapear(216, 289, 1);
      mapear(218, 289, 3);
      mapear(237, 289, 2);
      mapear(240, 289, 2);
      mapear(18, 290, 2);
      mapear(24, 290, 3);
      mapear(41, 290, 2);
      mapear(63, 290, 2);
      mapear(83, 290, 2);
      mapear(87, 290, 2);
      mapear(151, 290, 2);
      mapear(173, 290, 2);
      mapear(193, 290, 2);
      mapear(197, 290, 2);
      mapear(17, 291, 2);
      mapear(25, 291, 2);
      mapear(16, 292, 2);
      mapear(26, 292, 1);
      mapear(40, 292, 5);
      mapear(62, 292, 5);
      mapear(84, 292, 5);
      mapear(106, 292, 5);
      mapear(128, 292, 5);
      mapear(150, 292, 4);
      mapear(172, 292, 4);
      mapear(194, 292, 4);
    }

    private void area_28() {
      mapear(216, 292, 4);
      mapear(238, 292, 4);
      mapear(16, 293, 2);
      mapear(39, 293, 1);
      mapear(44, 293, 2);
      mapear(61, 293, 1);
      mapear(66, 293, 2);
      mapear(83, 293, 1);
      mapear(88, 293, 2);
      mapear(105, 293, 1);
      mapear(110, 293, 2);
      mapear(127, 293, 1);
      mapear(132, 293, 2);
      mapear(149, 293, 1);
      mapear(154, 293, 1);
      mapear(171, 293, 1);
      mapear(176, 293, 1);
      mapear(193, 293, 1);
      mapear(198, 293, 1);
      mapear(215, 293, 1);
      mapear(220, 293, 1);
      mapear(237, 293, 1);
      mapear(242, 293, 1);
      mapear(16, 294, 2);
      mapear(44, 294, 2);
      mapear(66, 294, 2);
      mapear(88, 294, 2);
      mapear(110, 294, 2);
      mapear(132, 294, 2);
      mapear(148, 294, 2);
      mapear(154, 294, 2);
      mapear(170, 294, 2);
      mapear(176, 294, 2);
      mapear(192, 294, 2);
      mapear(198, 294, 2);
      mapear(214, 294, 2);
      mapear(220, 294, 2);
      mapear(236, 294, 2);
      mapear(242, 294, 2);
      mapear(16, 295, 2);
      mapear(39, 295, 7);
      mapear(61, 295, 7);
      mapear(83, 295, 7);
      mapear(105, 295, 7);
      mapear(127, 295, 7);
      mapear(148, 295, 8);
      mapear(170, 295, 8);
      mapear(192, 295, 8);
      mapear(214, 295, 8);
      mapear(236, 295, 8);
    }

    private void area_29() {
      mapear(16, 296, 2);
      mapear(38, 296, 2);
      mapear(44, 296, 2);
      mapear(60, 296, 2);
      mapear(66, 296, 2);
      mapear(82, 296, 2);
      mapear(88, 296, 2);
      mapear(104, 296, 2);
      mapear(110, 296, 2);
      mapear(126, 296, 2);
      mapear(132, 296, 2);
      mapear(148, 296, 2);
      mapear(170, 296, 2);
      mapear(192, 296, 2);
      mapear(214, 296, 2);
      mapear(236, 296, 2);
      mapear(17, 297, 2);
      mapear(26, 297, 1);
      mapear(38, 297, 2);
      mapear(44, 297, 2);
      mapear(60, 297, 2);
      mapear(66, 297, 2);
      mapear(82, 297, 2);
      mapear(88, 297, 2);
      mapear(104, 297, 2);
      mapear(110, 297, 2);
      mapear(126, 297, 2);
      mapear(132, 297, 2);
      mapear(148, 297, 2);
      mapear(155, 297, 1);
      mapear(170, 297, 2);
      mapear(177, 297, 1);
      mapear(192, 297, 2);
      mapear(199, 297, 1);
      mapear(214, 297, 2);
      mapear(221, 297, 1);
      mapear(236, 297, 2);
      mapear(243, 297, 1);
      mapear(18, 298, 2);
      mapear(25, 298, 1);
      mapear(38, 298, 2);
      mapear(44, 298, 2);
      mapear(60, 298, 2);
      mapear(66, 298, 2);
      mapear(82, 298, 2);
      mapear(88, 298, 2);
      mapear(104, 298, 2);
      mapear(110, 298, 2);
      mapear(126, 298, 2);
      mapear(132, 298, 2);
    }

    private void area_30() {
      mapear(149, 298, 2);
      mapear(154, 298, 1);
      mapear(171, 298, 2);
      mapear(176, 298, 1);
      mapear(193, 298, 2);
      mapear(198, 298, 1);
      mapear(215, 298, 2);
      mapear(220, 298, 1);
      mapear(237, 298, 2);
      mapear(242, 298, 1);
      mapear(19, 299, 6);
      mapear(39, 299, 8);
      mapear(61, 299, 8);
      mapear(83, 299, 8);
      mapear(105, 299, 8);
      mapear(127, 299, 8);
      mapear(150, 299, 4);
      mapear(172, 299, 4);
      mapear(194, 299, 4);
      mapear(216, 299, 4);
      mapear(238, 299, 4);
      mapear(22, 300, 1);
      mapear(22, 301, 1);
      mapear(20, 302, 3);
      mapear(19, 323, 2);
      mapear(37, 323, 2);
      mapear(61, 323, 2);
      mapear(81, 323, 3);
      mapear(85, 323, 1);
      mapear(103, 323, 2);
      mapear(106, 323, 2);
      mapear(131, 323, 2);
      mapear(149, 323, 2);
      mapear(173, 323, 2);
      mapear(193, 323, 3);
      mapear(197, 323, 1);
      mapear(215, 323, 2);
      mapear(218, 323, 2);
      mapear(241, 323, 2);
      mapear(18, 324, 2);
      mapear(38, 324, 2);
      mapear(60, 324, 4);
      mapear(81, 324, 1);
      mapear(83, 324, 3);
      mapear(103, 324, 2);
      mapear(106, 324, 2);
      mapear(130, 324, 2);
      mapear(150, 324, 2);
      mapear(172, 324, 4);
      mapear(193, 324, 1);
    }

    private void area_31() {
      mapear(195, 324, 3);
      mapear(215, 324, 2);
      mapear(218, 324, 2);
      mapear(240, 324, 2);
      mapear(17, 325, 2);
      mapear(39, 325, 2);
      mapear(59, 325, 2);
      mapear(63, 325, 2);
      mapear(129, 325, 2);
      mapear(151, 325, 2);
      mapear(171, 325, 2);
      mapear(175, 325, 2);
      mapear(239, 325, 2);
      mapear(16, 327, 3);
      mapear(38, 327, 3);
      mapear(60, 327, 3);
      mapear(82, 327, 3);
      mapear(104, 327, 3);
      mapear(128, 327, 4);
      mapear(150, 327, 4);
      mapear(172, 327, 4);
      mapear(194, 327, 4);
      mapear(216, 327, 4);
      mapear(236, 327, 3);
      mapear(242, 327, 3);
      mapear(17, 328, 2);
      mapear(39, 328, 2);
      mapear(61, 328, 2);
      mapear(83, 328, 2);
      mapear(105, 328, 2);
      mapear(127, 328, 2);
      mapear(131, 328, 2);
      mapear(149, 328, 2);
      mapear(153, 328, 2);
      mapear(171, 328, 2);
      mapear(175, 328, 2);
      mapear(193, 328, 2);
      mapear(197, 328, 2);
      mapear(215, 328, 2);
      mapear(219, 328, 2);
      mapear(237, 328, 2);
      mapear(243, 328, 2);
      mapear(17, 329, 2);
      mapear(39, 329, 2);
      mapear(61, 329, 2);
      mapear(83, 329, 2);
      mapear(105, 329, 2);
      mapear(126, 329, 2);
      mapear(132, 329, 2);
      mapear(148, 329, 2);
    }

    private void area_32() {
      mapear(154, 329, 2);
      mapear(170, 329, 2);
      mapear(176, 329, 2);
      mapear(192, 329, 2);
      mapear(198, 329, 2);
      mapear(214, 329, 2);
      mapear(220, 329, 2);
      mapear(237, 329, 2);
      mapear(243, 329, 2);
      mapear(17, 330, 2);
      mapear(39, 330, 2);
      mapear(61, 330, 2);
      mapear(83, 330, 2);
      mapear(105, 330, 2);
      mapear(126, 330, 2);
      mapear(132, 330, 2);
      mapear(148, 330, 2);
      mapear(154, 330, 2);
      mapear(170, 330, 2);
      mapear(176, 330, 2);
      mapear(192, 330, 2);
      mapear(198, 330, 2);
      mapear(214, 330, 2);
      mapear(220, 330, 2);
      mapear(237, 330, 2);
      mapear(243, 330, 2);
      mapear(17, 331, 2);
      mapear(39, 331, 2);
      mapear(61, 331, 2);
      mapear(83, 331, 2);
      mapear(105, 331, 2);
      mapear(126, 331, 2);
      mapear(132, 331, 2);
      mapear(148, 331, 2);
      mapear(154, 331, 2);
      mapear(170, 331, 2);
      mapear(176, 331, 2);
      mapear(192, 331, 2);
      mapear(198, 331, 2);
      mapear(214, 331, 2);
      mapear(220, 331, 2);
      mapear(237, 331, 2);
      mapear(243, 331, 2);
      mapear(17, 332, 2);
      mapear(39, 332, 2);
      mapear(61, 332, 2);
      mapear(83, 332, 2);
      mapear(105, 332, 2);
      mapear(126, 332, 2);
      mapear(132, 332, 2);
    }

    private void area_33() {
      mapear(148, 332, 2);
      mapear(154, 332, 2);
      mapear(170, 332, 2);
      mapear(176, 332, 2);
      mapear(192, 332, 2);
      mapear(198, 332, 2);
      mapear(214, 332, 2);
      mapear(220, 332, 2);
      mapear(237, 332, 2);
      mapear(243, 332, 2);
      mapear(17, 333, 2);
      mapear(39, 333, 2);
      mapear(61, 333, 2);
      mapear(83, 333, 2);
      mapear(105, 333, 2);
      mapear(127, 333, 2);
      mapear(131, 333, 2);
      mapear(149, 333, 2);
      mapear(153, 333, 2);
      mapear(171, 333, 2);
      mapear(175, 333, 2);
      mapear(193, 333, 2);
      mapear(197, 333, 2);
      mapear(215, 333, 2);
      mapear(219, 333, 2);
      mapear(237, 333, 2);
      mapear(242, 333, 3);
      mapear(16, 334, 4);
      mapear(38, 334, 4);
      mapear(60, 334, 4);
      mapear(82, 334, 4);
      mapear(104, 334, 4);
      mapear(128, 334, 4);
      mapear(150, 334, 4);
      mapear(172, 334, 4);
      mapear(194, 334, 4);
      mapear(216, 334, 4);
      mapear(238, 334, 4);
      mapear(243, 334, 3);
      mapear(108, 356, 2);
      mapear(128, 356, 2);
      mapear(150, 356, 4);
      mapear(172, 356, 3);
      mapear(176, 356, 1);
      mapear(194, 356, 2);
      mapear(197, 356, 2);
      mapear(219, 356, 2);
      mapear(239, 356, 2);
      mapear(107, 357, 2);
      mapear(129, 357, 2);
    }

    private void area_34() {
      mapear(149, 357, 2);
      mapear(153, 357, 2);
      mapear(172, 357, 1);
      mapear(174, 357, 3);
      mapear(194, 357, 2);
      mapear(197, 357, 2);
      mapear(218, 357, 2);
      mapear(240, 357, 2);
      mapear(17, 358, 2);
      mapear(41, 358, 2);
      mapear(61, 358, 3);
      mapear(65, 358, 1);
      mapear(83, 358, 2);
      mapear(86, 358, 2);
      mapear(18, 359, 2);
      mapear(40, 359, 4);
      mapear(61, 359, 1);
      mapear(63, 359, 3);
      mapear(83, 359, 2);
      mapear(86, 359, 2);
      mapear(108, 359, 2);
      mapear(130, 359, 2);
      mapear(152, 359, 2);
      mapear(174, 359, 2);
      mapear(196, 359, 2);
      mapear(214, 359, 10);
      mapear(236, 359, 10);
      mapear(19, 360, 2);
      mapear(39, 360, 2);
      mapear(43, 360, 2);
      mapear(107, 360, 3);
      mapear(129, 360, 3);
      mapear(151, 360, 3);
      mapear(173, 360, 3);
      mapear(195, 360, 3);
      mapear(215, 360, 2);
      mapear(223, 360, 1);
      mapear(237, 360, 2);
      mapear(245, 360, 1);
      mapear(107, 361, 1);
      mapear(109, 361, 2);
      mapear(129, 361, 1);
      mapear(131, 361, 2);
      mapear(151, 361, 1);
      mapear(153, 361, 2);
      mapear(173, 361, 1);
      mapear(175, 361, 2);
      mapear(195, 361, 1);
      mapear(197, 361, 2);
      mapear(215, 361, 2);
    }

    private void area_35() {
      mapear(237, 361, 2);
      mapear(16, 362, 3);
      mapear(22, 362, 3);
      mapear(38, 362, 3);
      mapear(44, 362, 3);
      mapear(60, 362, 3);
      mapear(66, 362, 3);
      mapear(82, 362, 3);
      mapear(88, 362, 3);
      mapear(107, 362, 1);
      mapear(109, 362, 2);
      mapear(129, 362, 1);
      mapear(131, 362, 2);
      mapear(151, 362, 1);
      mapear(153, 362, 2);
      mapear(173, 362, 1);
      mapear(175, 362, 2);
      mapear(195, 362, 1);
      mapear(197, 362, 2);
      mapear(215, 362, 2);
      mapear(237, 362, 2);
      mapear(17, 363, 2);
      mapear(23, 363, 2);
      mapear(39, 363, 2);
      mapear(45, 363, 2);
      mapear(61, 363, 2);
      mapear(67, 363, 2);
      mapear(83, 363, 2);
      mapear(89, 363, 2);
      mapear(106, 363, 1);
      mapear(109, 363, 3);
      mapear(128, 363, 1);
      mapear(131, 363, 3);
      mapear(150, 363, 1);
      mapear(153, 363, 3);
      mapear(172, 363, 1);
      mapear(175, 363, 3);
      mapear(194, 363, 1);
      mapear(197, 363, 3);
      mapear(215, 363, 2);
      mapear(221, 363, 1);
      mapear(237, 363, 2);
      mapear(243, 363, 1);
      mapear(17, 364, 2);
      mapear(23, 364, 2);
      mapear(39, 364, 2);
      mapear(45, 364, 2);
      mapear(61, 364, 2);
      mapear(67, 364, 2);
      mapear(83, 364, 2);
    }

    private void area_36() {
      mapear(89, 364, 2);
      mapear(106, 364, 1);
      mapear(110, 364, 2);
      mapear(128, 364, 1);
      mapear(132, 364, 2);
      mapear(150, 364, 1);
      mapear(154, 364, 2);
      mapear(172, 364, 1);
      mapear(176, 364, 2);
      mapear(194, 364, 1);
      mapear(198, 364, 2);
      mapear(215, 364, 7);
      mapear(237, 364, 7);
      mapear(17, 365, 2);
      mapear(23, 365, 2);
      mapear(39, 365, 2);
      mapear(45, 365, 2);
      mapear(61, 365, 2);
      mapear(67, 365, 2);
      mapear(83, 365, 2);
      mapear(89, 365, 2);
      mapear(105, 365, 1);
      mapear(110, 365, 2);
      mapear(127, 365, 1);
      mapear(132, 365, 2);
      mapear(149, 365, 1);
      mapear(154, 365, 2);
      mapear(171, 365, 1);
      mapear(176, 365, 2);
      mapear(193, 365, 1);
      mapear(198, 365, 2);
      mapear(215, 365, 2);
      mapear(221, 365, 1);
      mapear(237, 365, 2);
      mapear(243, 365, 1);
      mapear(17, 366, 2);
      mapear(23, 366, 2);
      mapear(39, 366, 2);
      mapear(45, 366, 2);
      mapear(61, 366, 2);
      mapear(67, 366, 2);
      mapear(83, 366, 2);
      mapear(89, 366, 2);
      mapear(105, 366, 8);
      mapear(127, 366, 8);
      mapear(149, 366, 8);
      mapear(171, 366, 8);
      mapear(193, 366, 8);
      mapear(215, 366, 2);
      mapear(237, 366, 2);
    }

    private void area_37() {
      mapear(17, 367, 2);
      mapear(23, 367, 2);
      mapear(39, 367, 2);
      mapear(45, 367, 2);
      mapear(61, 367, 2);
      mapear(67, 367, 2);
      mapear(83, 367, 2);
      mapear(89, 367, 2);
      mapear(104, 367, 2);
      mapear(111, 367, 2);
      mapear(126, 367, 2);
      mapear(133, 367, 2);
      mapear(148, 367, 2);
      mapear(155, 367, 2);
      mapear(170, 367, 2);
      mapear(177, 367, 2);
      mapear(192, 367, 2);
      mapear(199, 367, 2);
      mapear(215, 367, 2);
      mapear(237, 367, 2);
      mapear(17, 368, 2);
      mapear(22, 368, 3);
      mapear(39, 368, 2);
      mapear(44, 368, 3);
      mapear(61, 368, 2);
      mapear(66, 368, 3);
      mapear(83, 368, 2);
      mapear(88, 368, 3);
      mapear(104, 368, 1);
      mapear(112, 368, 2);
      mapear(126, 368, 1);
      mapear(134, 368, 2);
      mapear(148, 368, 1);
      mapear(156, 368, 2);
      mapear(170, 368, 1);
      mapear(178, 368, 2);
      mapear(192, 368, 1);
      mapear(200, 368, 2);
      mapear(215, 368, 2);
      mapear(223, 368, 1);
      mapear(237, 368, 2);
      mapear(245, 368, 1);
      mapear(18, 369, 4);
      mapear(23, 369, 3);
      mapear(40, 369, 4);
      mapear(45, 369, 3);
      mapear(62, 369, 4);
      mapear(67, 369, 3);
      mapear(84, 369, 4);
      mapear(89, 369, 3);
    }

    private void area_38() {
      mapear(103, 369, 3);
      mapear(111, 369, 4);
      mapear(125, 369, 3);
      mapear(133, 369, 4);
      mapear(147, 369, 3);
      mapear(155, 369, 4);
      mapear(169, 369, 3);
      mapear(177, 369, 4);
      mapear(191, 369, 3);
      mapear(199, 369, 4);
      mapear(214, 369, 10);
      mapear(236, 369, 10);
      mapear(19, 391, 4);
      mapear(41, 391, 3);
      mapear(45, 391, 1);
      mapear(63, 391, 2);
      mapear(66, 391, 2);
      mapear(84, 391, 2);
      mapear(104, 391, 2);
      mapear(126, 391, 4);
      mapear(148, 391, 3);
      mapear(152, 391, 1);
      mapear(170, 391, 2);
      mapear(173, 391, 2);
      mapear(197, 391, 2);
      mapear(217, 391, 2);
      mapear(239, 391, 4);
      mapear(18, 392, 2);
      mapear(22, 392, 2);
      mapear(41, 392, 1);
      mapear(43, 392, 3);
      mapear(63, 392, 2);
      mapear(66, 392, 2);
      mapear(83, 392, 2);
      mapear(105, 392, 2);
      mapear(125, 392, 2);
      mapear(129, 392, 2);
      mapear(148, 392, 1);
      mapear(150, 392, 3);
      mapear(170, 392, 2);
      mapear(173, 392, 2);
      mapear(196, 392, 2);
      mapear(218, 392, 2);
      mapear(238, 392, 2);
      mapear(242, 392, 2);
      mapear(16, 394, 10);
      mapear(38, 394, 10);
      mapear(60, 394, 10);
      mapear(82, 394, 4);
      mapear(104, 394, 4);
    }

    private void area_39() {
      mapear(126, 394, 4);
      mapear(148, 394, 4);
      mapear(170, 394, 4);
      mapear(195, 394, 6);
      mapear(217, 394, 6);
      mapear(239, 394, 6);
      mapear(17, 395, 2);
      mapear(25, 395, 1);
      mapear(39, 395, 2);
      mapear(47, 395, 1);
      mapear(61, 395, 2);
      mapear(69, 395, 1);
      mapear(83, 395, 2);
      mapear(105, 395, 2);
      mapear(127, 395, 2);
      mapear(149, 395, 2);
      mapear(171, 395, 2);
      mapear(194, 395, 2);
      mapear(200, 395, 2);
      mapear(216, 395, 2);
      mapear(222, 395, 2);
      mapear(238, 395, 2);
      mapear(244, 395, 2);
      mapear(17, 396, 2);
      mapear(39, 396, 2);
      mapear(61, 396, 2);
      mapear(83, 396, 2);
      mapear(105, 396, 2);
      mapear(127, 396, 2);
      mapear(149, 396, 2);
      mapear(171, 396, 2);
      mapear(193, 396, 2);
      mapear(201, 396, 2);
      mapear(215, 396, 2);
      mapear(223, 396, 2);
      mapear(237, 396, 2);
      mapear(245, 396, 2);
      mapear(17, 397, 2);
      mapear(39, 397, 2);
      mapear(61, 397, 2);
      mapear(83, 397, 2);
      mapear(105, 397, 2);
      mapear(127, 397, 2);
      mapear(149, 397, 2);
      mapear(171, 397, 2);
      mapear(192, 397, 2);
      mapear(202, 397, 2);
      mapear(214, 397, 2);
      mapear(224, 397, 2);
      mapear(236, 397, 2);
    }

    private void area_40() {
      mapear(246, 397, 2);
      mapear(17, 398, 2);
      mapear(23, 398, 1);
      mapear(39, 398, 2);
      mapear(45, 398, 1);
      mapear(61, 398, 2);
      mapear(67, 398, 1);
      mapear(83, 398, 2);
      mapear(105, 398, 2);
      mapear(127, 398, 2);
      mapear(149, 398, 2);
      mapear(171, 398, 2);
      mapear(192, 398, 2);
      mapear(202, 398, 2);
      mapear(214, 398, 2);
      mapear(224, 398, 2);
      mapear(236, 398, 2);
      mapear(246, 398, 2);
      mapear(17, 399, 7);
      mapear(39, 399, 7);
      mapear(61, 399, 7);
      mapear(83, 399, 2);
      mapear(105, 399, 2);
      mapear(127, 399, 2);
      mapear(149, 399, 2);
      mapear(171, 399, 2);
      mapear(192, 399, 2);
      mapear(202, 399, 2);
      mapear(214, 399, 2);
      mapear(224, 399, 2);
      mapear(236, 399, 2);
      mapear(246, 399, 2);
      mapear(17, 400, 2);
      mapear(23, 400, 1);
      mapear(39, 400, 2);
      mapear(45, 400, 1);
      mapear(61, 400, 2);
      mapear(67, 400, 1);
      mapear(83, 400, 2);
      mapear(105, 400, 2);
      mapear(127, 400, 2);
      mapear(149, 400, 2);
      mapear(171, 400, 2);
      mapear(192, 400, 2);
      mapear(202, 400, 2);
      mapear(214, 400, 2);
      mapear(224, 400, 2);
      mapear(236, 400, 2);
      mapear(246, 400, 2);
      mapear(17, 401, 2);
    }

    private void area_41() {
      mapear(39, 401, 2);
      mapear(61, 401, 2);
      mapear(83, 401, 2);
      mapear(105, 401, 2);
      mapear(127, 401, 2);
      mapear(149, 401, 2);
      mapear(171, 401, 2);
      mapear(192, 401, 2);
      mapear(202, 401, 2);
      mapear(214, 401, 2);
      mapear(224, 401, 2);
      mapear(236, 401, 2);
      mapear(246, 401, 2);
      mapear(17, 402, 2);
      mapear(39, 402, 2);
      mapear(61, 402, 2);
      mapear(83, 402, 2);
      mapear(105, 402, 2);
      mapear(127, 402, 2);
      mapear(149, 402, 2);
      mapear(171, 402, 2);
      mapear(193, 402, 2);
      mapear(201, 402, 2);
      mapear(215, 402, 2);
      mapear(223, 402, 2);
      mapear(237, 402, 2);
      mapear(245, 402, 2);
      mapear(17, 403, 2);
      mapear(25, 403, 1);
      mapear(39, 403, 2);
      mapear(47, 403, 1);
      mapear(61, 403, 2);
      mapear(69, 403, 1);
      mapear(83, 403, 2);
      mapear(105, 403, 2);
      mapear(127, 403, 2);
      mapear(149, 403, 2);
      mapear(171, 403, 2);
      mapear(194, 403, 2);
      mapear(200, 403, 2);
      mapear(216, 403, 2);
      mapear(222, 403, 2);
      mapear(238, 403, 2);
      mapear(244, 403, 2);
      mapear(16, 404, 10);
      mapear(38, 404, 10);
      mapear(60, 404, 10);
      mapear(82, 404, 4);
      mapear(104, 404, 4);
      mapear(126, 404, 4);
    }

    private void area_42() {
      mapear(148, 404, 4);
      mapear(170, 404, 4);
      mapear(195, 404, 6);
      mapear(217, 404, 6);
      mapear(239, 404, 6);
      mapear(19, 426, 3);
      mapear(23, 426, 1);
      mapear(41, 426, 2);
      mapear(44, 426, 2);
      mapear(66, 426, 2);
      mapear(86, 426, 2);
      mapear(108, 426, 4);
      mapear(130, 426, 3);
      mapear(134, 426, 1);
      mapear(152, 426, 2);
      mapear(155, 426, 2);
      mapear(19, 427, 1);
      mapear(21, 427, 3);
      mapear(41, 427, 2);
      mapear(44, 427, 2);
      mapear(65, 427, 2);
      mapear(87, 427, 2);
      mapear(107, 427, 2);
      mapear(111, 427, 2);
      mapear(130, 427, 1);
      mapear(132, 427, 3);
      mapear(152, 427, 2);
      mapear(155, 427, 2);
      mapear(19, 429, 6);
      mapear(41, 429, 6);
      mapear(60, 429, 4);
      mapear(68, 429, 3);
      mapear(82, 429, 4);
      mapear(90, 429, 3);
      mapear(104, 429, 4);
      mapear(112, 429, 3);
      mapear(126, 429, 4);
      mapear(134, 429, 3);
      mapear(148, 429, 4);
      mapear(156, 429, 3);
      mapear(18, 430, 2);
      mapear(24, 430, 2);
      mapear(40, 430, 2);
      mapear(46, 430, 2);
      mapear(61, 430, 2);
      mapear(69, 430, 1);
      mapear(83, 430, 2);
      mapear(91, 430, 1);
      mapear(105, 430, 2);
      mapear(113, 430, 1);
    }

    private void area_43() {
      mapear(127, 430, 2);
      mapear(135, 430, 1);
      mapear(149, 430, 2);
      mapear(157, 430, 1);
      mapear(17, 431, 2);
      mapear(25, 431, 2);
      mapear(39, 431, 2);
      mapear(47, 431, 2);
      mapear(61, 431, 2);
      mapear(69, 431, 1);
      mapear(83, 431, 2);
      mapear(91, 431, 1);
      mapear(105, 431, 2);
      mapear(113, 431, 1);
      mapear(127, 431, 2);
      mapear(135, 431, 1);
      mapear(149, 431, 2);
      mapear(157, 431, 1);
      mapear(16, 432, 2);
      mapear(26, 432, 2);
      mapear(38, 432, 2);
      mapear(48, 432, 2);
      mapear(61, 432, 2);
      mapear(69, 432, 1);
      mapear(83, 432, 2);
      mapear(91, 432, 1);
      mapear(105, 432, 2);
      mapear(113, 432, 1);
      mapear(127, 432, 2);
      mapear(135, 432, 1);
      mapear(149, 432, 2);
      mapear(157, 432, 1);
      mapear(16, 433, 2);
      mapear(26, 433, 2);
      mapear(38, 433, 2);
      mapear(48, 433, 2);
      mapear(61, 433, 2);
      mapear(69, 433, 1);
      mapear(83, 433, 2);
      mapear(91, 433, 1);
      mapear(105, 433, 2);
      mapear(113, 433, 1);
      mapear(127, 433, 2);
      mapear(135, 433, 1);
      mapear(149, 433, 2);
      mapear(157, 433, 1);
      mapear(16, 434, 2);
      mapear(26, 434, 2);
      mapear(38, 434, 2);
      mapear(48, 434, 2);
    }

    private void area_44() {
      mapear(61, 434, 2);
      mapear(69, 434, 1);
      mapear(83, 434, 2);
      mapear(91, 434, 1);
      mapear(105, 434, 2);
      mapear(113, 434, 1);
      mapear(127, 434, 2);
      mapear(135, 434, 1);
      mapear(149, 434, 2);
      mapear(157, 434, 1);
      mapear(16, 435, 2);
      mapear(26, 435, 2);
      mapear(38, 435, 2);
      mapear(48, 435, 2);
      mapear(61, 435, 2);
      mapear(69, 435, 1);
      mapear(83, 435, 2);
      mapear(91, 435, 1);
      mapear(105, 435, 2);
      mapear(113, 435, 1);
      mapear(127, 435, 2);
      mapear(135, 435, 1);
      mapear(149, 435, 2);
      mapear(157, 435, 1);
      mapear(16, 436, 2);
      mapear(26, 436, 2);
      mapear(38, 436, 2);
      mapear(48, 436, 2);
      mapear(61, 436, 2);
      mapear(69, 436, 1);
      mapear(83, 436, 2);
      mapear(91, 436, 1);
      mapear(105, 436, 2);
      mapear(113, 436, 1);
      mapear(127, 436, 2);
      mapear(135, 436, 1);
      mapear(149, 436, 2);
      mapear(157, 436, 1);
      mapear(17, 437, 2);
      mapear(25, 437, 2);
      mapear(39, 437, 2);
      mapear(47, 437, 2);
      mapear(61, 437, 2);
      mapear(69, 437, 1);
      mapear(83, 437, 2);
      mapear(91, 437, 1);
      mapear(105, 437, 2);
      mapear(113, 437, 1);
      mapear(127, 437, 2);
      mapear(135, 437, 1);
    }

    private void area_45() {
      mapear(149, 437, 2);
      mapear(157, 437, 1);
      mapear(18, 438, 2);
      mapear(24, 438, 2);
      mapear(40, 438, 2);
      mapear(46, 438, 2);
      mapear(62, 438, 2);
      mapear(68, 438, 1);
      mapear(84, 438, 2);
      mapear(90, 438, 1);
      mapear(106, 438, 2);
      mapear(112, 438, 1);
      mapear(128, 438, 2);
      mapear(134, 438, 1);
      mapear(150, 438, 2);
      mapear(156, 438, 1);
      mapear(19, 439, 6);
      mapear(41, 439, 6);
      mapear(63, 439, 5);
      mapear(85, 439, 5);
      mapear(107, 439, 5);
      mapear(129, 439, 5);
      mapear(151, 439, 5);
    }

    @Override
    public void escrevaCentralizado(int x, int y, String frase) {

        int largura = getLarguraDe(frase);

        escreva(x - (largura / 2), y, frase);

    }

    public     Renderizador getRenderizador(){return mRenderizador;}

    public Cor getCor(){return null;}
}