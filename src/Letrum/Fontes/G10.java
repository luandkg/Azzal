
package Letrum.Fontes;
import Azzal.Renderizador;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Letra;


public class G10 implements Fonte {



	 public final int LARGURA = 180;
	 public final int ALTURA = 420;
	 public final int FONTE = 10;
	 private int COR_FONTE = new Color(0, 0, 0).getRGB();
	 private int COR_FUNDO = 0;


	 private BufferedImage mImagem;
	 private Renderizador mRenderizador;
	 private ArrayList<Letra> mLetras;
	private final Letra LETRA_00 = new Letra ("a",8,3,17,18);
	private final Letra LETRA_01 = new Letra ("b",23,3,32,18);
	private final Letra LETRA_02 = new Letra ("c",38,3,46,18);
	private final Letra LETRA_03 = new Letra ("d",53,3,62,18);
	private final Letra LETRA_04 = new Letra ("e",68,3,76,18);
	private final Letra LETRA_05 = new Letra ("f",83,3,89,18);
	private final Letra LETRA_06 = new Letra ("g",98,3,107,18);
	private final Letra LETRA_07 = new Letra ("h",113,3,122,18);
	private final Letra LETRA_08 = new Letra ("i",128,3,135,18);
	private final Letra LETRA_09 = new Letra ("j",143,3,150,18);
	private final Letra LETRA_10 = new Letra ("k",158,3,168,18);
	private final Letra LETRA_11 = new Letra ("l",8,33,15,48);
	private final Letra LETRA_12 = new Letra ("m",23,33,36,48);
	private final Letra LETRA_13 = new Letra ("n",38,33,47,48);
	private final Letra LETRA_14 = new Letra ("o",53,33,61,48);
	private final Letra LETRA_15 = new Letra ("p",68,33,76,48);
	private final Letra LETRA_16 = new Letra ("q",83,33,92,48);
	private final Letra LETRA_17 = new Letra ("r",98,33,106,48);
	private final Letra LETRA_18 = new Letra ("s",113,33,121,48);
	private final Letra LETRA_19 = new Letra ("t",128,33,135,48);
	private final Letra LETRA_20 = new Letra ("u",143,33,152,48);
	private final Letra LETRA_21 = new Letra ("v",158,33,166,48);
	private final Letra LETRA_22 = new Letra ("w",8,63,19,78);
	private final Letra LETRA_23 = new Letra ("x",23,63,31,78);
	private final Letra LETRA_24 = new Letra ("y",38,63,46,78);
	private final Letra LETRA_25 = new Letra ("z",53,63,61,78);
	private final Letra LETRA_26 = new Letra ("A",68,63,78,78);
	private final Letra LETRA_27 = new Letra ("B",83,63,94,78);
	private final Letra LETRA_28 = new Letra ("C",98,63,108,78);
	private final Letra LETRA_29 = new Letra ("D",113,63,124,78);
	private final Letra LETRA_30 = new Letra ("E",128,63,138,78);
	private final Letra LETRA_31 = new Letra ("F",143,63,153,78);
	private final Letra LETRA_32 = new Letra ("G",158,63,168,78);
	private final Letra LETRA_33 = new Letra ("H",8,93,20,108);
	private final Letra LETRA_34 = new Letra ("I",23,93,30,108);
	private final Letra LETRA_35 = new Letra ("J",38,93,45,108);
	private final Letra LETRA_36 = new Letra ("K",53,93,64,108);
	private final Letra LETRA_37 = new Letra ("L",68,93,77,108);
	private final Letra LETRA_38 = new Letra ("M",83,93,96,108);
	private final Letra LETRA_39 = new Letra ("N",98,93,109,108);
	private final Letra LETRA_40 = new Letra ("O",113,93,124,108);
	private final Letra LETRA_41 = new Letra ("P",128,93,138,108);
	private final Letra LETRA_42 = new Letra ("Q",143,93,154,108);
	private final Letra LETRA_43 = new Letra ("R",158,93,168,108);
	private final Letra LETRA_44 = new Letra ("S",8,123,17,138);
	private final Letra LETRA_45 = new Letra ("T",23,123,32,138);
	private final Letra LETRA_46 = new Letra ("U",38,123,49,138);
	private final Letra LETRA_47 = new Letra ("V",53,123,62,138);
	private final Letra LETRA_48 = new Letra ("W",68,123,80,138);
	private final Letra LETRA_49 = new Letra ("X",83,123,92,138);
	private final Letra LETRA_50 = new Letra ("Y",98,123,107,138);
	private final Letra LETRA_51 = new Letra ("Z",113,123,123,138);
	private final Letra LETRA_52 = new Letra ("_",128,123,135,138);
	private final Letra LETRA_53 = new Letra ("0",143,123,152,138);
	private final Letra LETRA_54 = new Letra ("1",158,123,167,138);
	private final Letra LETRA_55 = new Letra ("2",8,153,17,168);
	private final Letra LETRA_56 = new Letra ("3",23,153,32,168);
	private final Letra LETRA_57 = new Letra ("4",38,153,47,168);
	private final Letra LETRA_58 = new Letra ("5",53,153,62,168);
	private final Letra LETRA_59 = new Letra ("6",68,153,77,168);
	private final Letra LETRA_60 = new Letra ("7",83,153,92,168);
	private final Letra LETRA_61 = new Letra ("8",98,153,107,168);
	private final Letra LETRA_62 = new Letra ("9",113,153,122,168);
	private final Letra LETRA_63 = new Letra ("-",128,153,134,168);
	private final Letra LETRA_64 = new Letra ("<",143,153,153,168);
	private final Letra LETRA_65 = new Letra (">",158,153,168,168);
	private final Letra LETRA_66 = new Letra (".",8,183,14,198);
	private final Letra LETRA_67 = new Letra (",",23,183,28,198);
	private final Letra LETRA_68 = new Letra (":",38,183,44,198);
	private final Letra LETRA_69 = new Letra (";",53,183,59,198);
	private final Letra LETRA_70 = new Letra ("/",68,183,74,198);
	private final Letra LETRA_71 = new Letra ("\\",83,183,89,198);
	private final Letra LETRA_72 = new Letra ("+",98,183,108,198);
	private final Letra LETRA_73 = new Letra ("-",113,183,119,198);
	private final Letra LETRA_74 = new Letra ("*",128,183,135,198);
	private final Letra LETRA_75 = new Letra ("=",143,183,153,198);
	private final Letra LETRA_76 = new Letra ("(",158,183,165,198);
	private final Letra LETRA_77 = new Letra (")",8,213,15,228);
	private final Letra LETRA_78 = new Letra ("[",23,213,30,228);
	private final Letra LETRA_79 = new Letra ("]",38,213,45,228);
	private final Letra LETRA_80 = new Letra ("{",53,213,61,228);
	private final Letra LETRA_81 = new Letra ("}",68,213,76,228);
	private final Letra LETRA_82 = new Letra ("!",83,213,89,228);
	private final Letra LETRA_83 = new Letra ("@",98,213,110,228);
	private final Letra LETRA_84 = new Letra ("#",113,213,123,228);
	private final Letra LETRA_85 = new Letra ("$",128,213,137,228);
	private final Letra LETRA_86 = new Letra ("%",143,213,155,228);
	private final Letra LETRA_87 = new Letra ("ç",158,213,166,228);
	private final Letra LETRA_88 = new Letra ("Ç",8,243,18,258);
	private final Letra LETRA_89 = new Letra ("á",23,243,32,258);
	private final Letra LETRA_90 = new Letra ("à",38,243,47,258);
	private final Letra LETRA_91 = new Letra ("â",53,243,62,258);
	private final Letra LETRA_92 = new Letra ("ã",68,243,77,258);
	private final Letra LETRA_93 = new Letra ("ä",83,243,92,258);
	private final Letra LETRA_94 = new Letra ("é",98,243,106,258);
	private final Letra LETRA_95 = new Letra ("è",113,243,121,258);
	private final Letra LETRA_96 = new Letra ("ê",128,243,136,258);
	private final Letra LETRA_97 = new Letra ("ẽ",143,243,151,258);
	private final Letra LETRA_98 = new Letra ("ë",158,243,166,258);
	private final Letra LETRA_99 = new Letra ("í",8,273,15,288);
	private final Letra LETRA_100 = new Letra ("ì",23,273,30,288);
	private final Letra LETRA_101 = new Letra ("î",38,273,45,288);
	private final Letra LETRA_102 = new Letra ("ĩ",53,273,60,288);
	private final Letra LETRA_103 = new Letra ("ï",68,273,75,288);
	private final Letra LETRA_104 = new Letra ("ó",83,273,91,288);
	private final Letra LETRA_105 = new Letra ("ò",98,273,106,288);
	private final Letra LETRA_106 = new Letra ("ô",113,273,121,288);
	private final Letra LETRA_107 = new Letra ("õ",128,273,136,288);
	private final Letra LETRA_108 = new Letra ("ö",143,273,151,288);
	private final Letra LETRA_109 = new Letra ("ú",158,273,167,288);
	private final Letra LETRA_110 = new Letra ("ù",8,303,17,318);
	private final Letra LETRA_111 = new Letra ("û",23,303,32,318);
	private final Letra LETRA_112 = new Letra ("ũ",38,303,47,318);
	private final Letra LETRA_113 = new Letra ("ü",53,303,62,318);
	private final Letra LETRA_114 = new Letra ("Á",68,303,78,318);
	private final Letra LETRA_115 = new Letra ("À",83,303,93,318);
	private final Letra LETRA_116 = new Letra ("Â",98,303,108,318);
	private final Letra LETRA_117 = new Letra ("Ã",113,303,123,318);
	private final Letra LETRA_118 = new Letra ("Ä",128,303,138,318);
	private final Letra LETRA_119 = new Letra ("É",143,303,153,318);
	private final Letra LETRA_120 = new Letra ("È",158,303,168,318);
	private final Letra LETRA_121 = new Letra ("Ê",8,333,18,348);
	private final Letra LETRA_122 = new Letra ("Ẽ",23,333,33,348);
	private final Letra LETRA_123 = new Letra ("Ë",38,333,48,348);
	private final Letra LETRA_124 = new Letra ("Í",53,333,60,348);
	private final Letra LETRA_125 = new Letra ("Ì",68,333,75,348);
	private final Letra LETRA_126 = new Letra ("Î",83,333,90,348);
	private final Letra LETRA_127 = new Letra ("Ĩ",98,333,105,348);
	private final Letra LETRA_128 = new Letra ("Ï",113,333,120,348);
	private final Letra LETRA_129 = new Letra ("Ó",128,333,139,348);
	private final Letra LETRA_130 = new Letra ("Ò",143,333,154,348);
	private final Letra LETRA_131 = new Letra ("Ô",158,333,169,348);
	private final Letra LETRA_132 = new Letra ("Õ",8,363,19,378);
	private final Letra LETRA_133 = new Letra ("Ö",23,363,34,378);
	private final Letra LETRA_134 = new Letra ("Ú",38,363,49,378);
	private final Letra LETRA_135 = new Letra ("Ù",53,363,64,378);
	private final Letra LETRA_136 = new Letra ("Û",68,363,79,378);
	private final Letra LETRA_137 = new Letra ("Ũ",83,363,94,378);
	private final Letra LETRA_138 = new Letra ("Ü",98,363,109,378);


	 public G10() {
		 mImagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
		 mLetras = new ArrayList<Letra>();
		  IMontadora();
		 init_data();
	 }
	   public G10(Cor eCor) {
	     mImagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
	    mLetras = new ArrayList<Letra>();
	    COR_FONTE = new Color(eCor.getRed(), eCor.getGreen(), eCor.getBlue()).getRGB();
	       IMontadora( );
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


	 public int getLarguraDe( String frase) {
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
	private void init_data () {
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
	}
	 private void area_0(){
		  mapear(26,7,3);
		  mapear(58,7,3);
		  mapear(87,7,3);
		  mapear(115,7,3);
		  mapear(132,7,2);
		  mapear(147,7,2);
		  mapear(161,7,3);
		  mapear(27,8,2);
		  mapear(59,8,2);
		  mapear(86,8,2);
		  mapear(116,8,2);
		  mapear(132,8,2);
		  mapear(147,8,2);
		  mapear(162,8,2);
		  mapear(27,9,2);
		  mapear(59,9,2);
		  mapear(86,9,2);
		  mapear(116,9,2);
		  mapear(162,9,2);
		  mapear(11,10,4);
		  mapear(27,10,4);
		  mapear(42,10,4);
		  mapear(57,10,4);
		  mapear(72,10,3);
		  mapear(85,10,4);
		  mapear(102,10,5);
		  mapear(116,10,5);
		  mapear(131,10,3);
		  mapear(146,10,3);
		  mapear(162,10,2);
		  mapear(165,10,3);
		  mapear(14,11,2);
		  mapear(27,11,2);
		  mapear(30,11,2);
		  mapear(41,11,2);
		  mapear(45,11,1);
		  mapear(56,11,2);
		  mapear(59,11,2);
		  mapear(71,11,2);
		  mapear(74,11,2);
		  mapear(86,11,2);
		  mapear(101,11,2);
		  mapear(104,11,2);
		  mapear(116,11,2);
		  mapear(119,11,2);
		  mapear(132,11,2);
		  mapear(147,11,2);
		  mapear(162,11,2);
		  mapear(166,11,1);
		  mapear(11,12,5);
	}
	 private void area_1(){
		  mapear(27,12,2);
		  mapear(30,12,2);
		  mapear(41,12,2);
		  mapear(56,12,2);
		  mapear(59,12,2);
		  mapear(71,12,5);
		  mapear(86,12,2);
		  mapear(101,12,2);
		  mapear(104,12,2);
		  mapear(116,12,2);
		  mapear(119,12,2);
		  mapear(132,12,2);
		  mapear(147,12,2);
		  mapear(162,12,4);
		  mapear(11,13,2);
		  mapear(14,13,2);
		  mapear(27,13,2);
		  mapear(30,13,2);
		  mapear(41,13,2);
		  mapear(45,13,1);
		  mapear(56,13,2);
		  mapear(59,13,2);
		  mapear(71,13,2);
		  mapear(86,13,2);
		  mapear(101,13,2);
		  mapear(104,13,2);
		  mapear(116,13,2);
		  mapear(119,13,2);
		  mapear(132,13,2);
		  mapear(147,13,2);
		  mapear(162,13,2);
		  mapear(165,13,2);
		  mapear(11,14,6);
		  mapear(26,14,5);
		  mapear(42,14,3);
		  mapear(57,14,5);
		  mapear(72,14,4);
		  mapear(85,14,4);
		  mapear(102,14,4);
		  mapear(116,14,2);
		  mapear(119,14,3);
		  mapear(131,14,4);
		  mapear(147,14,2);
		  mapear(161,14,3);
		  mapear(165,14,3);
		  mapear(104,15,2);
		  mapear(147,15,2);
		  mapear(101,16,4);
		  mapear(145,16,4);
		  mapear(11,37,3);
	}
	 private void area_2(){
		  mapear(12,38,2);
		  mapear(131,38,2);
		  mapear(12,39,2);
		  mapear(131,39,2);
		  mapear(12,40,2);
		  mapear(26,40,9);
		  mapear(40,40,6);
		  mapear(57,40,3);
		  mapear(70,40,5);
		  mapear(87,40,5);
		  mapear(101,40,5);
		  mapear(117,40,4);
		  mapear(130,40,5);
		  mapear(145,40,3);
		  mapear(149,40,2);
		  mapear(160,40,3);
		  mapear(164,40,2);
		  mapear(12,41,2);
		  mapear(27,41,2);
		  mapear(30,41,2);
		  mapear(33,41,2);
		  mapear(41,41,2);
		  mapear(44,41,2);
		  mapear(56,41,2);
		  mapear(59,41,2);
		  mapear(71,41,2);
		  mapear(74,41,2);
		  mapear(86,41,2);
		  mapear(89,41,2);
		  mapear(102,41,2);
		  mapear(105,41,1);
		  mapear(116,41,3);
		  mapear(131,41,2);
		  mapear(146,41,2);
		  mapear(149,41,2);
		  mapear(161,41,2);
		  mapear(164,41,1);
		  mapear(12,42,2);
		  mapear(27,42,2);
		  mapear(30,42,2);
		  mapear(33,42,2);
		  mapear(41,42,2);
		  mapear(44,42,2);
		  mapear(56,42,2);
		  mapear(59,42,2);
		  mapear(71,42,2);
		  mapear(74,42,2);
		  mapear(86,42,2);
		  mapear(89,42,2);
		  mapear(102,42,2);
	}
	 private void area_3(){
		  mapear(116,42,5);
		  mapear(131,42,2);
		  mapear(146,42,2);
		  mapear(149,42,2);
		  mapear(161,42,2);
		  mapear(164,42,1);
		  mapear(12,43,2);
		  mapear(27,43,2);
		  mapear(30,43,2);
		  mapear(33,43,2);
		  mapear(41,43,2);
		  mapear(44,43,2);
		  mapear(56,43,2);
		  mapear(59,43,2);
		  mapear(71,43,2);
		  mapear(74,43,2);
		  mapear(86,43,2);
		  mapear(89,43,2);
		  mapear(102,43,2);
		  mapear(118,43,3);
		  mapear(131,43,2);
		  mapear(146,43,2);
		  mapear(149,43,2);
		  mapear(162,43,2);
		  mapear(11,44,4);
		  mapear(26,44,3);
		  mapear(30,44,2);
		  mapear(33,44,3);
		  mapear(41,44,2);
		  mapear(44,44,3);
		  mapear(57,44,3);
		  mapear(71,44,4);
		  mapear(87,44,4);
		  mapear(101,44,4);
		  mapear(116,44,4);
		  mapear(131,44,4);
		  mapear(146,44,6);
		  mapear(162,44,2);
		  mapear(71,45,2);
		  mapear(89,45,2);
		  mapear(70,46,4);
		  mapear(88,46,4);
		  mapear(73,68,2);
		  mapear(86,68,7);
		  mapear(103,68,4);
		  mapear(116,68,6);
		  mapear(131,68,7);
		  mapear(146,68,7);
		  mapear(163,68,4);
		  mapear(73,69,2);
	}
	 private void area_4(){
		  mapear(87,69,2);
		  mapear(92,69,2);
		  mapear(102,69,2);
		  mapear(107,69,1);
		  mapear(117,69,2);
		  mapear(121,69,2);
		  mapear(132,69,2);
		  mapear(137,69,1);
		  mapear(147,69,2);
		  mapear(152,69,1);
		  mapear(162,69,2);
		  mapear(167,69,1);
		  mapear(10,70,3);
		  mapear(14,70,2);
		  mapear(17,70,2);
		  mapear(25,70,3);
		  mapear(29,70,2);
		  mapear(40,70,3);
		  mapear(44,70,2);
		  mapear(56,70,5);
		  mapear(72,70,4);
		  mapear(87,70,2);
		  mapear(92,70,2);
		  mapear(101,70,2);
		  mapear(117,70,2);
		  mapear(122,70,2);
		  mapear(132,70,2);
		  mapear(147,70,2);
		  mapear(161,70,2);
		  mapear(11,71,2);
		  mapear(14,71,2);
		  mapear(17,71,1);
		  mapear(26,71,4);
		  mapear(41,71,2);
		  mapear(44,71,1);
		  mapear(58,71,2);
		  mapear(72,71,1);
		  mapear(74,71,2);
		  mapear(87,71,5);
		  mapear(101,71,2);
		  mapear(117,71,2);
		  mapear(122,71,2);
		  mapear(132,71,5);
		  mapear(147,71,5);
		  mapear(161,71,2);
		  mapear(165,71,3);
		  mapear(11,72,2);
		  mapear(14,72,2);
		  mapear(17,72,1);
		  mapear(27,72,2);
	}
	 private void area_5(){
		  mapear(42,72,3);
		  mapear(57,72,3);
		  mapear(71,72,6);
		  mapear(87,72,2);
		  mapear(92,72,2);
		  mapear(101,72,2);
		  mapear(117,72,2);
		  mapear(122,72,2);
		  mapear(132,72,2);
		  mapear(147,72,2);
		  mapear(161,72,2);
		  mapear(166,72,2);
		  mapear(12,73,2);
		  mapear(15,73,2);
		  mapear(26,73,4);
		  mapear(42,73,2);
		  mapear(57,73,2);
		  mapear(71,73,1);
		  mapear(75,73,2);
		  mapear(87,73,2);
		  mapear(92,73,2);
		  mapear(102,73,2);
		  mapear(107,73,1);
		  mapear(117,73,2);
		  mapear(121,73,2);
		  mapear(132,73,2);
		  mapear(137,73,1);
		  mapear(147,73,2);
		  mapear(162,73,2);
		  mapear(166,73,2);
		  mapear(12,74,2);
		  mapear(15,74,2);
		  mapear(25,74,2);
		  mapear(28,74,3);
		  mapear(43,74,1);
		  mapear(56,74,5);
		  mapear(70,74,2);
		  mapear(75,74,3);
		  mapear(86,74,7);
		  mapear(103,74,4);
		  mapear(116,74,6);
		  mapear(131,74,7);
		  mapear(146,74,4);
		  mapear(163,74,4);
		  mapear(41,75,1);
		  mapear(43,75,1);
		  mapear(41,76,2);
		  mapear(11,98,4);
		  mapear(16,98,4);
		  mapear(26,98,4);
	}
	 private void area_6(){
		  mapear(41,98,4);
		  mapear(56,98,4);
		  mapear(61,98,2);
		  mapear(71,98,4);
		  mapear(86,98,3);
		  mapear(93,98,3);
		  mapear(101,98,3);
		  mapear(106,98,3);
		  mapear(118,98,4);
		  mapear(131,98,6);
		  mapear(148,98,4);
		  mapear(161,98,6);
		  mapear(12,99,2);
		  mapear(17,99,2);
		  mapear(27,99,2);
		  mapear(42,99,2);
		  mapear(57,99,2);
		  mapear(61,99,1);
		  mapear(72,99,2);
		  mapear(87,99,3);
		  mapear(92,99,3);
		  mapear(102,99,3);
		  mapear(107,99,1);
		  mapear(117,99,2);
		  mapear(121,99,2);
		  mapear(132,99,2);
		  mapear(136,99,2);
		  mapear(147,99,2);
		  mapear(151,99,2);
		  mapear(162,99,2);
		  mapear(166,99,2);
		  mapear(12,100,2);
		  mapear(17,100,2);
		  mapear(27,100,2);
		  mapear(42,100,2);
		  mapear(57,100,2);
		  mapear(60,100,1);
		  mapear(72,100,2);
		  mapear(87,100,3);
		  mapear(92,100,3);
		  mapear(102,100,3);
		  mapear(107,100,1);
		  mapear(116,100,2);
		  mapear(122,100,2);
		  mapear(132,100,2);
		  mapear(136,100,2);
		  mapear(146,100,2);
		  mapear(152,100,2);
		  mapear(162,100,2);
		  mapear(166,100,2);
	}
	 private void area_7(){
		  mapear(12,101,7);
		  mapear(27,101,2);
		  mapear(42,101,2);
		  mapear(57,101,4);
		  mapear(72,101,2);
		  mapear(87,101,1);
		  mapear(89,101,3);
		  mapear(93,101,2);
		  mapear(102,101,1);
		  mapear(104,101,2);
		  mapear(107,101,1);
		  mapear(116,101,2);
		  mapear(122,101,2);
		  mapear(132,101,5);
		  mapear(146,101,2);
		  mapear(152,101,2);
		  mapear(162,101,4);
		  mapear(12,102,2);
		  mapear(17,102,2);
		  mapear(27,102,2);
		  mapear(42,102,2);
		  mapear(57,102,2);
		  mapear(60,102,2);
		  mapear(72,102,2);
		  mapear(87,102,1);
		  mapear(89,102,3);
		  mapear(93,102,2);
		  mapear(102,102,1);
		  mapear(105,102,3);
		  mapear(116,102,2);
		  mapear(122,102,2);
		  mapear(132,102,2);
		  mapear(146,102,2);
		  mapear(152,102,2);
		  mapear(162,102,2);
		  mapear(165,102,2);
		  mapear(12,103,2);
		  mapear(17,103,2);
		  mapear(27,103,2);
		  mapear(42,103,2);
		  mapear(57,103,2);
		  mapear(61,103,2);
		  mapear(72,103,2);
		  mapear(76,103,1);
		  mapear(87,103,1);
		  mapear(90,103,1);
		  mapear(93,103,2);
		  mapear(102,103,1);
		  mapear(105,103,3);
		  mapear(117,103,2);
	}
	 private void area_8(){
		  mapear(121,103,2);
		  mapear(132,103,2);
		  mapear(147,103,2);
		  mapear(151,103,2);
		  mapear(162,103,2);
		  mapear(166,103,2);
		  mapear(11,104,4);
		  mapear(16,104,4);
		  mapear(26,104,4);
		  mapear(42,104,2);
		  mapear(56,104,4);
		  mapear(62,104,3);
		  mapear(71,104,6);
		  mapear(86,104,3);
		  mapear(92,104,4);
		  mapear(101,104,3);
		  mapear(106,104,2);
		  mapear(118,104,4);
		  mapear(131,104,4);
		  mapear(148,104,4);
		  mapear(161,104,4);
		  mapear(166,104,3);
		  mapear(42,105,2);
		  mapear(150,105,1);
		  mapear(40,106,4);
		  mapear(151,106,2);
		  mapear(12,128,5);
		  mapear(25,128,8);
		  mapear(41,128,4);
		  mapear(46,128,3);
		  mapear(55,128,3);
		  mapear(60,128,3);
		  mapear(70,128,4);
		  mapear(75,128,2);
		  mapear(78,128,3);
		  mapear(85,128,4);
		  mapear(90,128,3);
		  mapear(100,128,4);
		  mapear(105,128,3);
		  mapear(116,128,7);
		  mapear(147,128,4);
		  mapear(162,128,3);
		  mapear(11,129,2);
		  mapear(16,129,1);
		  mapear(25,129,1);
		  mapear(28,129,2);
		  mapear(32,129,1);
		  mapear(42,129,2);
		  mapear(47,129,1);
		  mapear(56,129,2);
	}
	 private void area_9(){
		  mapear(61,129,1);
		  mapear(71,129,2);
		  mapear(75,129,2);
		  mapear(79,129,1);
		  mapear(86,129,2);
		  mapear(91,129,1);
		  mapear(101,129,2);
		  mapear(106,129,1);
		  mapear(116,129,1);
		  mapear(120,129,2);
		  mapear(146,129,2);
		  mapear(150,129,2);
		  mapear(161,129,1);
		  mapear(163,129,2);
		  mapear(11,130,4);
		  mapear(28,130,2);
		  mapear(42,130,2);
		  mapear(47,130,1);
		  mapear(56,130,3);
		  mapear(61,130,1);
		  mapear(71,130,2);
		  mapear(75,130,3);
		  mapear(79,130,1);
		  mapear(87,130,2);
		  mapear(90,130,1);
		  mapear(102,130,2);
		  mapear(105,130,1);
		  mapear(119,130,3);
		  mapear(146,130,2);
		  mapear(150,130,2);
		  mapear(163,130,2);
		  mapear(12,131,5);
		  mapear(28,131,2);
		  mapear(42,131,2);
		  mapear(47,131,1);
		  mapear(57,131,2);
		  mapear(60,131,1);
		  mapear(71,131,2);
		  mapear(74,131,1);
		  mapear(76,131,2);
		  mapear(79,131,1);
		  mapear(88,131,2);
		  mapear(103,131,2);
		  mapear(118,131,3);
		  mapear(146,131,2);
		  mapear(150,131,2);
		  mapear(163,131,2);
		  mapear(14,132,3);
		  mapear(28,132,2);
		  mapear(42,132,2);
	}
	 private void area_10(){
		  mapear(47,132,1);
		  mapear(57,132,2);
		  mapear(60,132,1);
		  mapear(72,132,3);
		  mapear(76,132,3);
		  mapear(87,132,1);
		  mapear(89,132,2);
		  mapear(103,132,2);
		  mapear(117,132,3);
		  mapear(146,132,2);
		  mapear(150,132,2);
		  mapear(163,132,2);
		  mapear(11,133,1);
		  mapear(15,133,2);
		  mapear(28,133,2);
		  mapear(42,133,2);
		  mapear(47,133,1);
		  mapear(58,133,2);
		  mapear(72,133,2);
		  mapear(77,133,2);
		  mapear(86,133,1);
		  mapear(90,133,2);
		  mapear(103,133,2);
		  mapear(117,133,2);
		  mapear(122,133,1);
		  mapear(146,133,2);
		  mapear(150,133,2);
		  mapear(163,133,2);
		  mapear(12,134,4);
		  mapear(27,134,4);
		  mapear(43,134,4);
		  mapear(58,134,2);
		  mapear(72,134,2);
		  mapear(77,134,2);
		  mapear(85,134,3);
		  mapear(89,134,4);
		  mapear(102,134,4);
		  mapear(116,134,7);
		  mapear(147,134,4);
		  mapear(162,134,4);
		  mapear(130,136,5);
		  mapear(11,158,4);
		  mapear(26,158,5);
		  mapear(44,158,2);
		  mapear(56,158,4);
		  mapear(73,158,4);
		  mapear(86,158,5);
		  mapear(102,158,4);
		  mapear(117,158,4);
		  mapear(11,159,1);
	}
	 private void area_11(){
		  mapear(14,159,2);
		  mapear(26,159,1);
		  mapear(30,159,2);
		  mapear(43,159,3);
		  mapear(56,159,1);
		  mapear(72,159,1);
		  mapear(76,159,1);
		  mapear(86,159,1);
		  mapear(90,159,1);
		  mapear(101,159,2);
		  mapear(105,159,2);
		  mapear(116,159,2);
		  mapear(120,159,2);
		  mapear(151,159,1);
		  mapear(161,159,1);
		  mapear(14,160,2);
		  mapear(30,160,2);
		  mapear(42,160,4);
		  mapear(56,160,4);
		  mapear(71,160,5);
		  mapear(89,160,1);
		  mapear(101,160,2);
		  mapear(105,160,2);
		  mapear(116,160,2);
		  mapear(120,160,2);
		  mapear(148,160,3);
		  mapear(162,160,3);
		  mapear(14,161,2);
		  mapear(28,161,2);
		  mapear(42,161,1);
		  mapear(44,161,2);
		  mapear(56,161,1);
		  mapear(59,161,2);
		  mapear(71,161,2);
		  mapear(75,161,2);
		  mapear(89,161,1);
		  mapear(103,161,2);
		  mapear(116,161,2);
		  mapear(120,161,2);
		  mapear(146,161,3);
		  mapear(164,161,3);
		  mapear(13,162,2);
		  mapear(30,162,2);
		  mapear(41,162,6);
		  mapear(59,162,2);
		  mapear(71,162,2);
		  mapear(75,162,2);
		  mapear(88,162,2);
		  mapear(101,162,2);
		  mapear(105,162,2);
	}
	 private void area_12(){
		  mapear(117,162,5);
		  mapear(131,162,3);
		  mapear(146,162,3);
		  mapear(164,162,3);
		  mapear(12,163,2);
		  mapear(15,163,1);
		  mapear(26,163,1);
		  mapear(30,163,2);
		  mapear(44,163,2);
		  mapear(56,163,1);
		  mapear(59,163,2);
		  mapear(71,163,2);
		  mapear(75,163,2);
		  mapear(88,163,1);
		  mapear(101,163,2);
		  mapear(105,163,2);
		  mapear(116,163,1);
		  mapear(120,163,1);
		  mapear(148,163,3);
		  mapear(162,163,3);
		  mapear(11,164,5);
		  mapear(26,164,5);
		  mapear(43,164,4);
		  mapear(56,164,4);
		  mapear(72,164,4);
		  mapear(88,164,1);
		  mapear(102,164,4);
		  mapear(116,164,4);
		  mapear(151,164,1);
		  mapear(161,164,1);
		  mapear(163,187,1);
		  mapear(73,188,1);
		  mapear(85,188,1);
		  mapear(104,188,1);
		  mapear(130,188,1);
		  mapear(132,188,1);
		  mapear(134,188,1);
		  mapear(162,188,2);
		  mapear(72,189,2);
		  mapear(86,189,1);
		  mapear(104,189,1);
		  mapear(131,189,3);
		  mapear(161,189,2);
		  mapear(41,190,2);
		  mapear(56,190,2);
		  mapear(72,190,1);
		  mapear(86,190,1);
		  mapear(104,190,1);
		  mapear(131,190,3);
		  mapear(146,190,6);
	}
	 private void area_13(){
		  mapear(161,190,2);
		  mapear(41,191,2);
		  mapear(56,191,2);
		  mapear(72,191,1);
		  mapear(86,191,1);
		  mapear(101,191,7);
		  mapear(130,191,1);
		  mapear(132,191,1);
		  mapear(134,191,1);
		  mapear(161,191,2);
		  mapear(71,192,1);
		  mapear(87,192,1);
		  mapear(104,192,1);
		  mapear(116,192,3);
		  mapear(146,192,6);
		  mapear(161,192,2);
		  mapear(11,193,2);
		  mapear(26,193,2);
		  mapear(41,193,2);
		  mapear(56,193,2);
		  mapear(71,193,1);
		  mapear(87,193,1);
		  mapear(104,193,1);
		  mapear(161,193,2);
		  mapear(11,194,2);
		  mapear(26,194,2);
		  mapear(41,194,2);
		  mapear(56,194,2);
		  mapear(71,194,1);
		  mapear(87,194,2);
		  mapear(104,194,1);
		  mapear(162,194,2);
		  mapear(25,195,2);
		  mapear(55,195,2);
		  mapear(70,195,1);
		  mapear(88,195,1);
		  mapear(163,195,1);
		  mapear(11,217,1);
		  mapear(26,217,3);
		  mapear(41,217,3);
		  mapear(57,217,3);
		  mapear(71,217,3);
		  mapear(11,218,2);
		  mapear(26,218,2);
		  mapear(42,218,2);
		  mapear(57,218,2);
		  mapear(72,218,2);
		  mapear(86,218,2);
		  mapear(103,218,5);
		  mapear(118,218,1);
	}
	 private void area_14(){
		  mapear(121,218,1);
		  mapear(133,218,1);
		  mapear(146,218,2);
		  mapear(151,218,1);
		  mapear(12,219,2);
		  mapear(26,219,2);
		  mapear(42,219,2);
		  mapear(57,219,2);
		  mapear(72,219,2);
		  mapear(86,219,2);
		  mapear(102,219,2);
		  mapear(108,219,1);
		  mapear(118,219,1);
		  mapear(121,219,1);
		  mapear(132,219,4);
		  mapear(145,219,1);
		  mapear(148,219,1);
		  mapear(150,219,1);
		  mapear(12,220,2);
		  mapear(26,220,2);
		  mapear(42,220,2);
		  mapear(57,220,2);
		  mapear(72,220,2);
		  mapear(86,220,2);
		  mapear(101,220,2);
		  mapear(104,220,4);
		  mapear(109,220,1);
		  mapear(117,220,6);
		  mapear(131,220,1);
		  mapear(133,220,1);
		  mapear(135,220,1);
		  mapear(145,220,1);
		  mapear(148,220,3);
		  mapear(162,220,4);
		  mapear(12,221,2);
		  mapear(26,221,2);
		  mapear(42,221,2);
		  mapear(55,221,3);
		  mapear(73,221,3);
		  mapear(86,221,2);
		  mapear(101,221,1);
		  mapear(103,221,1);
		  mapear(107,221,1);
		  mapear(109,221,1);
		  mapear(118,221,1);
		  mapear(120,221,1);
		  mapear(131,221,3);
		  mapear(146,221,2);
		  mapear(149,221,1);
		  mapear(151,221,2);
	}
	 private void area_15(){
		  mapear(161,221,2);
		  mapear(165,221,1);
		  mapear(12,222,2);
		  mapear(26,222,2);
		  mapear(42,222,2);
		  mapear(57,222,2);
		  mapear(72,222,2);
		  mapear(101,222,1);
		  mapear(103,222,1);
		  mapear(107,222,1);
		  mapear(109,222,1);
		  mapear(116,222,6);
		  mapear(133,222,3);
		  mapear(148,222,3);
		  mapear(153,222,1);
		  mapear(161,222,2);
		  mapear(12,223,2);
		  mapear(26,223,2);
		  mapear(42,223,2);
		  mapear(57,223,2);
		  mapear(72,223,2);
		  mapear(86,223,2);
		  mapear(101,223,1);
		  mapear(103,223,1);
		  mapear(107,223,1);
		  mapear(109,223,1);
		  mapear(117,223,1);
		  mapear(119,223,2);
		  mapear(131,223,1);
		  mapear(133,223,1);
		  mapear(135,223,1);
		  mapear(148,223,1);
		  mapear(150,223,1);
		  mapear(153,223,1);
		  mapear(161,223,2);
		  mapear(165,223,1);
		  mapear(11,224,2);
		  mapear(26,224,2);
		  mapear(42,224,2);
		  mapear(57,224,2);
		  mapear(72,224,2);
		  mapear(86,224,2);
		  mapear(101,224,2);
		  mapear(104,224,5);
		  mapear(117,224,1);
		  mapear(119,224,1);
		  mapear(131,224,5);
		  mapear(147,224,1);
		  mapear(151,224,2);
		  mapear(162,224,3);
	}
	 private void area_16(){
		  mapear(11,225,1);
		  mapear(26,225,3);
		  mapear(41,225,3);
		  mapear(57,225,3);
		  mapear(71,225,3);
		  mapear(102,225,2);
		  mapear(133,225,1);
		  mapear(164,225,1);
		  mapear(103,226,5);
		  mapear(163,226,2);
		  mapear(28,247,1);
		  mapear(42,247,1);
		  mapear(57,247,2);
		  mapear(71,247,2);
		  mapear(74,247,1);
		  mapear(104,247,1);
		  mapear(118,247,1);
		  mapear(133,247,2);
		  mapear(147,247,2);
		  mapear(150,247,1);
		  mapear(13,248,4);
		  mapear(27,248,1);
		  mapear(43,248,1);
		  mapear(56,248,1);
		  mapear(59,248,1);
		  mapear(71,248,1);
		  mapear(73,248,2);
		  mapear(86,248,1);
		  mapear(88,248,1);
		  mapear(103,248,1);
		  mapear(119,248,1);
		  mapear(132,248,1);
		  mapear(135,248,1);
		  mapear(147,248,1);
		  mapear(149,248,2);
		  mapear(162,248,1);
		  mapear(164,248,1);
		  mapear(12,249,2);
		  mapear(17,249,1);
		  mapear(11,250,2);
		  mapear(26,250,4);
		  mapear(41,250,4);
		  mapear(56,250,4);
		  mapear(71,250,4);
		  mapear(86,250,4);
		  mapear(102,250,3);
		  mapear(117,250,3);
		  mapear(132,250,3);
		  mapear(147,250,3);
		  mapear(162,250,3);
	}
	 private void area_17(){
		  mapear(11,251,2);
		  mapear(29,251,2);
		  mapear(44,251,2);
		  mapear(59,251,2);
		  mapear(74,251,2);
		  mapear(89,251,2);
		  mapear(101,251,2);
		  mapear(104,251,2);
		  mapear(116,251,2);
		  mapear(119,251,2);
		  mapear(131,251,2);
		  mapear(134,251,2);
		  mapear(146,251,2);
		  mapear(149,251,2);
		  mapear(161,251,2);
		  mapear(164,251,2);
		  mapear(11,252,2);
		  mapear(26,252,5);
		  mapear(41,252,5);
		  mapear(56,252,5);
		  mapear(71,252,5);
		  mapear(86,252,5);
		  mapear(101,252,5);
		  mapear(116,252,5);
		  mapear(131,252,5);
		  mapear(146,252,5);
		  mapear(161,252,5);
		  mapear(12,253,2);
		  mapear(17,253,1);
		  mapear(26,253,2);
		  mapear(29,253,2);
		  mapear(41,253,2);
		  mapear(44,253,2);
		  mapear(56,253,2);
		  mapear(59,253,2);
		  mapear(71,253,2);
		  mapear(74,253,2);
		  mapear(86,253,2);
		  mapear(89,253,2);
		  mapear(101,253,2);
		  mapear(116,253,2);
		  mapear(131,253,2);
		  mapear(146,253,2);
		  mapear(161,253,2);
		  mapear(13,254,4);
		  mapear(26,254,6);
		  mapear(41,254,6);
		  mapear(56,254,6);
		  mapear(71,254,6);
		  mapear(86,254,6);
	}
	 private void area_18(){
		  mapear(102,254,4);
		  mapear(117,254,4);
		  mapear(132,254,4);
		  mapear(147,254,4);
		  mapear(162,254,4);
		  mapear(15,255,1);
		  mapear(14,256,2);
		  mapear(12,277,1);
		  mapear(26,277,1);
		  mapear(41,277,2);
		  mapear(55,277,2);
		  mapear(58,277,1);
		  mapear(89,277,1);
		  mapear(103,277,1);
		  mapear(118,277,2);
		  mapear(132,277,2);
		  mapear(135,277,1);
		  mapear(164,277,1);
		  mapear(11,278,1);
		  mapear(27,278,1);
		  mapear(40,278,1);
		  mapear(43,278,1);
		  mapear(55,278,1);
		  mapear(57,278,2);
		  mapear(70,278,1);
		  mapear(72,278,1);
		  mapear(88,278,1);
		  mapear(104,278,1);
		  mapear(117,278,1);
		  mapear(120,278,1);
		  mapear(132,278,1);
		  mapear(134,278,2);
		  mapear(147,278,1);
		  mapear(149,278,1);
		  mapear(163,278,1);
		  mapear(11,280,3);
		  mapear(26,280,3);
		  mapear(41,280,3);
		  mapear(56,280,3);
		  mapear(71,280,3);
		  mapear(87,280,3);
		  mapear(102,280,3);
		  mapear(117,280,3);
		  mapear(132,280,3);
		  mapear(147,280,3);
		  mapear(160,280,3);
		  mapear(164,280,2);
		  mapear(12,281,2);
		  mapear(27,281,2);
		  mapear(42,281,2);
	}
	 private void area_19(){
		  mapear(57,281,2);
		  mapear(72,281,2);
		  mapear(86,281,2);
		  mapear(89,281,2);
		  mapear(101,281,2);
		  mapear(104,281,2);
		  mapear(116,281,2);
		  mapear(119,281,2);
		  mapear(131,281,2);
		  mapear(134,281,2);
		  mapear(146,281,2);
		  mapear(149,281,2);
		  mapear(161,281,2);
		  mapear(164,281,2);
		  mapear(12,282,2);
		  mapear(27,282,2);
		  mapear(42,282,2);
		  mapear(57,282,2);
		  mapear(72,282,2);
		  mapear(86,282,2);
		  mapear(89,282,2);
		  mapear(101,282,2);
		  mapear(104,282,2);
		  mapear(116,282,2);
		  mapear(119,282,2);
		  mapear(131,282,2);
		  mapear(134,282,2);
		  mapear(146,282,2);
		  mapear(149,282,2);
		  mapear(161,282,2);
		  mapear(164,282,2);
		  mapear(12,283,2);
		  mapear(27,283,2);
		  mapear(42,283,2);
		  mapear(57,283,2);
		  mapear(72,283,2);
		  mapear(86,283,2);
		  mapear(89,283,2);
		  mapear(101,283,2);
		  mapear(104,283,2);
		  mapear(116,283,2);
		  mapear(119,283,2);
		  mapear(131,283,2);
		  mapear(134,283,2);
		  mapear(146,283,2);
		  mapear(149,283,2);
		  mapear(161,283,2);
		  mapear(164,283,2);
		  mapear(11,284,4);
		  mapear(26,284,4);
	}
	 private void area_20(){
		  mapear(41,284,4);
		  mapear(56,284,4);
		  mapear(71,284,4);
		  mapear(87,284,3);
		  mapear(102,284,3);
		  mapear(117,284,3);
		  mapear(132,284,3);
		  mapear(147,284,3);
		  mapear(161,284,6);
		  mapear(74,305,1);
		  mapear(87,305,1);
		  mapear(103,305,2);
		  mapear(117,305,2);
		  mapear(120,305,1);
		  mapear(149,305,1);
		  mapear(162,305,1);
		  mapear(73,306,1);
		  mapear(88,306,1);
		  mapear(102,306,1);
		  mapear(105,306,1);
		  mapear(117,306,1);
		  mapear(119,306,2);
		  mapear(132,306,1);
		  mapear(134,306,1);
		  mapear(148,306,1);
		  mapear(163,306,1);
		  mapear(13,307,1);
		  mapear(28,307,2);
		  mapear(42,307,2);
		  mapear(45,307,1);
		  mapear(14,308,1);
		  mapear(27,308,1);
		  mapear(30,308,1);
		  mapear(42,308,1);
		  mapear(44,308,2);
		  mapear(57,308,1);
		  mapear(59,308,1);
		  mapear(73,308,2);
		  mapear(88,308,2);
		  mapear(103,308,2);
		  mapear(118,308,2);
		  mapear(133,308,2);
		  mapear(146,308,7);
		  mapear(161,308,7);
		  mapear(73,309,2);
		  mapear(88,309,2);
		  mapear(103,309,2);
		  mapear(118,309,2);
		  mapear(133,309,2);
		  mapear(147,309,2);
	}
	 private void area_21(){
		  mapear(152,309,1);
		  mapear(162,309,2);
		  mapear(167,309,1);
		  mapear(10,310,3);
		  mapear(14,310,2);
		  mapear(25,310,3);
		  mapear(29,310,2);
		  mapear(40,310,3);
		  mapear(44,310,2);
		  mapear(55,310,3);
		  mapear(59,310,2);
		  mapear(72,310,4);
		  mapear(87,310,4);
		  mapear(102,310,4);
		  mapear(117,310,4);
		  mapear(132,310,4);
		  mapear(147,310,2);
		  mapear(162,310,2);
		  mapear(11,311,2);
		  mapear(14,311,2);
		  mapear(26,311,2);
		  mapear(29,311,2);
		  mapear(41,311,2);
		  mapear(44,311,2);
		  mapear(56,311,2);
		  mapear(59,311,2);
		  mapear(72,311,1);
		  mapear(74,311,2);
		  mapear(87,311,1);
		  mapear(89,311,2);
		  mapear(102,311,1);
		  mapear(104,311,2);
		  mapear(117,311,1);
		  mapear(119,311,2);
		  mapear(132,311,1);
		  mapear(134,311,2);
		  mapear(147,311,5);
		  mapear(162,311,5);
		  mapear(11,312,2);
		  mapear(14,312,2);
		  mapear(26,312,2);
		  mapear(29,312,2);
		  mapear(41,312,2);
		  mapear(44,312,2);
		  mapear(56,312,2);
		  mapear(59,312,2);
		  mapear(71,312,6);
		  mapear(86,312,6);
		  mapear(101,312,6);
		  mapear(116,312,6);
	}
	 private void area_22(){
		  mapear(131,312,6);
		  mapear(147,312,2);
		  mapear(162,312,2);
		  mapear(11,313,2);
		  mapear(14,313,2);
		  mapear(26,313,2);
		  mapear(29,313,2);
		  mapear(41,313,2);
		  mapear(44,313,2);
		  mapear(56,313,2);
		  mapear(59,313,2);
		  mapear(71,313,1);
		  mapear(75,313,2);
		  mapear(86,313,1);
		  mapear(90,313,2);
		  mapear(101,313,1);
		  mapear(105,313,2);
		  mapear(116,313,1);
		  mapear(120,313,2);
		  mapear(131,313,1);
		  mapear(135,313,2);
		  mapear(147,313,2);
		  mapear(152,313,1);
		  mapear(162,313,2);
		  mapear(167,313,1);
		  mapear(11,314,6);
		  mapear(26,314,6);
		  mapear(41,314,6);
		  mapear(56,314,6);
		  mapear(70,314,2);
		  mapear(75,314,3);
		  mapear(85,314,2);
		  mapear(90,314,3);
		  mapear(100,314,2);
		  mapear(105,314,3);
		  mapear(115,314,2);
		  mapear(120,314,3);
		  mapear(130,314,2);
		  mapear(135,314,3);
		  mapear(146,314,7);
		  mapear(161,314,7);
		  mapear(13,335,2);
		  mapear(27,335,2);
		  mapear(30,335,1);
		  mapear(58,335,1);
		  mapear(71,335,1);
		  mapear(87,335,2);
		  mapear(101,335,2);
		  mapear(104,335,1);
		  mapear(135,335,1);
	}
	 private void area_23(){
		  mapear(148,335,1);
		  mapear(164,335,2);
		  mapear(12,336,1);
		  mapear(15,336,1);
		  mapear(27,336,1);
		  mapear(29,336,2);
		  mapear(42,336,1);
		  mapear(44,336,1);
		  mapear(57,336,1);
		  mapear(72,336,1);
		  mapear(86,336,1);
		  mapear(89,336,1);
		  mapear(101,336,1);
		  mapear(103,336,2);
		  mapear(116,336,1);
		  mapear(118,336,1);
		  mapear(134,336,1);
		  mapear(149,336,1);
		  mapear(163,336,1);
		  mapear(166,336,1);
		  mapear(11,338,7);
		  mapear(26,338,7);
		  mapear(41,338,7);
		  mapear(56,338,4);
		  mapear(71,338,4);
		  mapear(86,338,4);
		  mapear(101,338,4);
		  mapear(116,338,4);
		  mapear(133,338,4);
		  mapear(148,338,4);
		  mapear(163,338,4);
		  mapear(12,339,2);
		  mapear(17,339,1);
		  mapear(27,339,2);
		  mapear(32,339,1);
		  mapear(42,339,2);
		  mapear(47,339,1);
		  mapear(57,339,2);
		  mapear(72,339,2);
		  mapear(87,339,2);
		  mapear(102,339,2);
		  mapear(117,339,2);
		  mapear(132,339,2);
		  mapear(136,339,2);
		  mapear(147,339,2);
		  mapear(151,339,2);
		  mapear(162,339,2);
		  mapear(166,339,2);
		  mapear(12,340,2);
		  mapear(27,340,2);
	}
	 private void area_24(){
		  mapear(42,340,2);
		  mapear(57,340,2);
		  mapear(72,340,2);
		  mapear(87,340,2);
		  mapear(102,340,2);
		  mapear(117,340,2);
		  mapear(131,340,2);
		  mapear(137,340,2);
		  mapear(146,340,2);
		  mapear(152,340,2);
		  mapear(161,340,2);
		  mapear(167,340,2);
		  mapear(12,341,5);
		  mapear(27,341,5);
		  mapear(42,341,5);
		  mapear(57,341,2);
		  mapear(72,341,2);
		  mapear(87,341,2);
		  mapear(102,341,2);
		  mapear(117,341,2);
		  mapear(131,341,2);
		  mapear(137,341,2);
		  mapear(146,341,2);
		  mapear(152,341,2);
		  mapear(161,341,2);
		  mapear(167,341,2);
		  mapear(12,342,2);
		  mapear(27,342,2);
		  mapear(42,342,2);
		  mapear(57,342,2);
		  mapear(72,342,2);
		  mapear(87,342,2);
		  mapear(102,342,2);
		  mapear(117,342,2);
		  mapear(131,342,2);
		  mapear(137,342,2);
		  mapear(146,342,2);
		  mapear(152,342,2);
		  mapear(161,342,2);
		  mapear(167,342,2);
		  mapear(12,343,2);
		  mapear(17,343,1);
		  mapear(27,343,2);
		  mapear(32,343,1);
		  mapear(42,343,2);
		  mapear(47,343,1);
		  mapear(57,343,2);
		  mapear(72,343,2);
		  mapear(87,343,2);
		  mapear(102,343,2);
	}
	 private void area_25(){
		  mapear(117,343,2);
		  mapear(132,343,2);
		  mapear(136,343,2);
		  mapear(147,343,2);
		  mapear(151,343,2);
		  mapear(162,343,2);
		  mapear(166,343,2);
		  mapear(11,344,7);
		  mapear(26,344,7);
		  mapear(41,344,7);
		  mapear(56,344,4);
		  mapear(71,344,4);
		  mapear(86,344,4);
		  mapear(101,344,4);
		  mapear(116,344,4);
		  mapear(133,344,4);
		  mapear(148,344,4);
		  mapear(163,344,4);
		  mapear(13,365,2);
		  mapear(16,365,1);
		  mapear(45,365,1);
		  mapear(58,365,1);
		  mapear(74,365,2);
		  mapear(88,365,2);
		  mapear(91,365,1);
		  mapear(13,366,1);
		  mapear(15,366,2);
		  mapear(28,366,1);
		  mapear(30,366,1);
		  mapear(44,366,1);
		  mapear(59,366,1);
		  mapear(73,366,1);
		  mapear(76,366,1);
		  mapear(88,366,1);
		  mapear(90,366,2);
		  mapear(103,366,1);
		  mapear(105,366,1);
		  mapear(13,368,4);
		  mapear(28,368,4);
		  mapear(41,368,4);
		  mapear(46,368,3);
		  mapear(56,368,4);
		  mapear(61,368,3);
		  mapear(71,368,4);
		  mapear(76,368,3);
		  mapear(86,368,4);
		  mapear(91,368,3);
		  mapear(101,368,4);
		  mapear(106,368,3);
		  mapear(12,369,2);
	}
	 private void area_26(){
		  mapear(16,369,2);
		  mapear(27,369,2);
		  mapear(31,369,2);
		  mapear(42,369,2);
		  mapear(47,369,1);
		  mapear(57,369,2);
		  mapear(62,369,1);
		  mapear(72,369,2);
		  mapear(77,369,1);
		  mapear(87,369,2);
		  mapear(92,369,1);
		  mapear(102,369,2);
		  mapear(107,369,1);
		  mapear(11,370,2);
		  mapear(17,370,2);
		  mapear(26,370,2);
		  mapear(32,370,2);
		  mapear(42,370,2);
		  mapear(47,370,1);
		  mapear(57,370,2);
		  mapear(62,370,1);
		  mapear(72,370,2);
		  mapear(77,370,1);
		  mapear(87,370,2);
		  mapear(92,370,1);
		  mapear(102,370,2);
		  mapear(107,370,1);
		  mapear(11,371,2);
		  mapear(17,371,2);
		  mapear(26,371,2);
		  mapear(32,371,2);
		  mapear(42,371,2);
		  mapear(47,371,1);
		  mapear(57,371,2);
		  mapear(62,371,1);
		  mapear(72,371,2);
		  mapear(77,371,1);
		  mapear(87,371,2);
		  mapear(92,371,1);
		  mapear(102,371,2);
		  mapear(107,371,1);
		  mapear(11,372,2);
		  mapear(17,372,2);
		  mapear(26,372,2);
		  mapear(32,372,2);
		  mapear(42,372,2);
		  mapear(47,372,1);
		  mapear(57,372,2);
		  mapear(62,372,1);
		  mapear(72,372,2);
	}
	 private void area_27(){
		  mapear(77,372,1);
		  mapear(87,372,2);
		  mapear(92,372,1);
		  mapear(102,372,2);
		  mapear(107,372,1);
		  mapear(12,373,2);
		  mapear(16,373,2);
		  mapear(27,373,2);
		  mapear(31,373,2);
		  mapear(42,373,2);
		  mapear(47,373,1);
		  mapear(57,373,2);
		  mapear(62,373,1);
		  mapear(72,373,2);
		  mapear(77,373,1);
		  mapear(87,373,2);
		  mapear(92,373,1);
		  mapear(102,373,2);
		  mapear(107,373,1);
		  mapear(13,374,4);
		  mapear(28,374,4);
		  mapear(43,374,4);
		  mapear(58,374,4);
		  mapear(73,374,4);
		  mapear(88,374,4);
		  mapear(103,374,4);
	}

	@Override
	public void escrevaCentralizado(int x, int y, String frase) {

		int largura = getLarguraDe(frase);

		escreva(x - (largura / 2), y, frase);

	}

	public     Renderizador getRenderizador(){return mRenderizador;}

	public Cor getCor(){return null;}
}