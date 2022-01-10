
package Letrum.Fontes;
import Azzal.Renderizador;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Letra;


public class G20 implements Fonte {



	 public final int LARGURA = 360;
	 public final int ALTURA = 560;
	 public final int FONTE = 20;
	 private int COR_FONTE = new Color(0, 0, 0).getRGB();
	 private int COR_FUNDO = 0;


	 private BufferedImage mImagem;
	 private Renderizador mRenderizador;
	 private ArrayList<Letra> mLetras;
	private final Letra LETRA_00 = new Letra ("a",18,3,33,30);
	private final Letra LETRA_01 = new Letra ("b",48,3,64,30);
	private final Letra LETRA_02 = new Letra ("c",78,3,92,30);
	private final Letra LETRA_03 = new Letra ("d",108,3,124,30);
	private final Letra LETRA_04 = new Letra ("e",138,3,153,30);
	private final Letra LETRA_05 = new Letra ("f",168,3,179,30);
	private final Letra LETRA_06 = new Letra ("g",198,3,214,30);
	private final Letra LETRA_07 = new Letra ("h",228,3,244,30);
	private final Letra LETRA_08 = new Letra ("i",258,3,267,30);
	private final Letra LETRA_09 = new Letra ("j",288,3,297,30);
	private final Letra LETRA_10 = new Letra ("k",318,3,334,30);
	private final Letra LETRA_11 = new Letra ("l",18,43,27,70);
	private final Letra LETRA_12 = new Letra ("m",48,43,71,70);
	private final Letra LETRA_13 = new Letra ("n",78,43,94,70);
	private final Letra LETRA_14 = new Letra ("o",108,43,123,70);
	private final Letra LETRA_15 = new Letra ("p",138,43,154,70);
	private final Letra LETRA_16 = new Letra ("q",168,43,184,70);
	private final Letra LETRA_17 = new Letra ("r",198,43,211,70);
	private final Letra LETRA_18 = new Letra ("s",228,43,242,70);
	private final Letra LETRA_19 = new Letra ("t",258,43,269,70);
	private final Letra LETRA_20 = new Letra ("u",288,43,304,70);
	private final Letra LETRA_21 = new Letra ("v",318,43,332,70);
	private final Letra LETRA_22 = new Letra ("w",18,83,37,110);
	private final Letra LETRA_23 = new Letra ("x",48,83,62,110);
	private final Letra LETRA_24 = new Letra ("y",78,83,92,110);
	private final Letra LETRA_25 = new Letra ("z",108,83,121,110);
	private final Letra LETRA_26 = new Letra ("A",138,83,156,110);
	private final Letra LETRA_27 = new Letra ("B",168,83,187,110);
	private final Letra LETRA_28 = new Letra ("C",198,83,216,110);
	private final Letra LETRA_29 = new Letra ("D",228,83,248,110);
	private final Letra LETRA_30 = new Letra ("E",258,83,275,110);
	private final Letra LETRA_31 = new Letra ("F",288,83,304,110);
	private final Letra LETRA_32 = new Letra ("G",318,83,337,110);
	private final Letra LETRA_33 = new Letra ("H",18,123,39,150);
	private final Letra LETRA_34 = new Letra ("I",48,123,59,150);
	private final Letra LETRA_35 = new Letra ("J",78,123,89,150);
	private final Letra LETRA_36 = new Letra ("K",108,123,127,150);
	private final Letra LETRA_37 = new Letra ("L",138,123,154,150);
	private final Letra LETRA_38 = new Letra ("M",168,123,192,150);
	private final Letra LETRA_39 = new Letra ("N",198,123,218,150);
	private final Letra LETRA_40 = new Letra ("O",228,123,248,150);
	private final Letra LETRA_41 = new Letra ("P",258,123,275,150);
	private final Letra LETRA_42 = new Letra ("Q",288,123,308,150);
	private final Letra LETRA_43 = new Letra ("R",318,123,337,150);
	private final Letra LETRA_44 = new Letra ("S",18,163,33,190);
	private final Letra LETRA_45 = new Letra ("T",48,163,65,190);
	private final Letra LETRA_46 = new Letra ("U",78,163,98,190);
	private final Letra LETRA_47 = new Letra ("V",108,163,126,190);
	private final Letra LETRA_48 = new Letra ("W",138,163,162,190);
	private final Letra LETRA_49 = new Letra ("X",168,163,186,190);
	private final Letra LETRA_50 = new Letra ("Y",198,163,214,190);
	private final Letra LETRA_51 = new Letra ("Z",228,163,245,190);
	private final Letra LETRA_52 = new Letra ("_",258,163,270,190);
	private final Letra LETRA_53 = new Letra ("0",288,163,304,190);
	private final Letra LETRA_54 = new Letra ("1",318,163,334,190);
	private final Letra LETRA_55 = new Letra ("2",18,203,34,230);
	private final Letra LETRA_56 = new Letra ("3",48,203,64,230);
	private final Letra LETRA_57 = new Letra ("4",78,203,94,230);
	private final Letra LETRA_58 = new Letra ("5",108,203,124,230);
	private final Letra LETRA_59 = new Letra ("6",138,203,154,230);
	private final Letra LETRA_60 = new Letra ("7",168,203,184,230);
	private final Letra LETRA_61 = new Letra ("8",198,203,214,230);
	private final Letra LETRA_62 = new Letra ("9",228,203,244,230);
	private final Letra LETRA_63 = new Letra ("-",258,203,268,230);
	private final Letra LETRA_64 = new Letra ("<",288,203,307,230);
	private final Letra LETRA_65 = new Letra (">",318,203,337,230);
	private final Letra LETRA_66 = new Letra (".",18,243,28,270);
	private final Letra LETRA_67 = new Letra (",",48,243,57,270);
	private final Letra LETRA_68 = new Letra (":",78,243,87,270);
	private final Letra LETRA_69 = new Letra (";",108,243,117,270);
	private final Letra LETRA_70 = new Letra ("/",138,243,147,270);
	private final Letra LETRA_71 = new Letra ("\\",168,243,177,270);
	private final Letra LETRA_72 = new Letra ("+",198,243,217,270);
	private final Letra LETRA_73 = new Letra ("-",228,243,238,270);
	private final Letra LETRA_74 = new Letra ("*",258,243,270,270);
	private final Letra LETRA_75 = new Letra ("=",288,243,307,270);
	private final Letra LETRA_76 = new Letra ("(",318,243,329,270);
	private final Letra LETRA_77 = new Letra (")",18,283,29,310);
	private final Letra LETRA_78 = new Letra ("[",48,283,59,310);
	private final Letra LETRA_79 = new Letra ("]",78,283,89,310);
	private final Letra LETRA_80 = new Letra ("{",108,283,123,310);
	private final Letra LETRA_81 = new Letra ("}",138,283,153,310);
	private final Letra LETRA_82 = new Letra ("!",168,283,179,310);
	private final Letra LETRA_83 = new Letra ("@",198,283,220,310);
	private final Letra LETRA_84 = new Letra ("#",228,283,247,310);
	private final Letra LETRA_85 = new Letra ("$",258,283,274,310);
	private final Letra LETRA_86 = new Letra ("%",288,283,309,310);
	private final Letra LETRA_87 = new Letra ("ç",318,283,332,310);
	private final Letra LETRA_88 = new Letra ("Ç",18,323,36,350);
	private final Letra LETRA_89 = new Letra ("á",48,323,63,350);
	private final Letra LETRA_90 = new Letra ("à",78,323,93,350);
	private final Letra LETRA_91 = new Letra ("â",108,323,123,350);
	private final Letra LETRA_92 = new Letra ("ã",138,323,153,350);
	private final Letra LETRA_93 = new Letra ("ä",168,323,183,350);
	private final Letra LETRA_94 = new Letra ("é",198,323,213,350);
	private final Letra LETRA_95 = new Letra ("è",228,323,243,350);
	private final Letra LETRA_96 = new Letra ("ê",258,323,273,350);
	private final Letra LETRA_97 = new Letra ("ẽ",288,323,303,350);
	private final Letra LETRA_98 = new Letra ("ë",318,323,333,350);
	private final Letra LETRA_99 = new Letra ("í",18,363,27,390);
	private final Letra LETRA_100 = new Letra ("ì",48,363,57,390);
	private final Letra LETRA_101 = new Letra ("î",78,363,87,390);
	private final Letra LETRA_102 = new Letra ("ĩ",108,363,117,390);
	private final Letra LETRA_103 = new Letra ("ï",138,363,147,390);
	private final Letra LETRA_104 = new Letra ("ó",168,363,183,390);
	private final Letra LETRA_105 = new Letra ("ò",198,363,213,390);
	private final Letra LETRA_106 = new Letra ("ô",228,363,243,390);
	private final Letra LETRA_107 = new Letra ("õ",258,363,273,390);
	private final Letra LETRA_108 = new Letra ("ö",288,363,303,390);
	private final Letra LETRA_109 = new Letra ("ú",318,363,334,390);
	private final Letra LETRA_110 = new Letra ("ù",18,403,34,430);
	private final Letra LETRA_111 = new Letra ("û",48,403,64,430);
	private final Letra LETRA_112 = new Letra ("ũ",78,403,94,430);
	private final Letra LETRA_113 = new Letra ("ü",108,403,124,430);
	private final Letra LETRA_114 = new Letra ("Á",138,403,156,430);
	private final Letra LETRA_115 = new Letra ("À",168,403,186,430);
	private final Letra LETRA_116 = new Letra ("Â",198,403,216,430);
	private final Letra LETRA_117 = new Letra ("Ã",228,403,246,430);
	private final Letra LETRA_118 = new Letra ("Ä",258,403,276,430);
	private final Letra LETRA_119 = new Letra ("É",288,403,305,430);
	private final Letra LETRA_120 = new Letra ("È",318,403,335,430);
	private final Letra LETRA_121 = new Letra ("Ê",18,443,35,470);
	private final Letra LETRA_122 = new Letra ("Ẽ",48,443,65,470);
	private final Letra LETRA_123 = new Letra ("Ë",78,443,95,470);
	private final Letra LETRA_124 = new Letra ("Í",108,443,119,470);
	private final Letra LETRA_125 = new Letra ("Ì",138,443,149,470);
	private final Letra LETRA_126 = new Letra ("Î",168,443,179,470);
	private final Letra LETRA_127 = new Letra ("Ĩ",198,443,209,470);
	private final Letra LETRA_128 = new Letra ("Ï",228,443,239,470);
	private final Letra LETRA_129 = new Letra ("Ó",258,443,278,470);
	private final Letra LETRA_130 = new Letra ("Ò",288,443,308,470);
	private final Letra LETRA_131 = new Letra ("Ô",318,443,338,470);
	private final Letra LETRA_132 = new Letra ("Õ",18,483,38,510);
	private final Letra LETRA_133 = new Letra ("Ö",48,483,68,510);
	private final Letra LETRA_134 = new Letra ("Ú",78,483,98,510);
	private final Letra LETRA_135 = new Letra ("Ù",108,483,128,510);
	private final Letra LETRA_136 = new Letra ("Û",138,483,158,510);
	private final Letra LETRA_137 = new Letra ("Ũ",168,483,188,510);
	private final Letra LETRA_138 = new Letra ("Ü",198,483,218,510);


	 public G20() {
		 mImagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB);
		 mLetras = new ArrayList<Letra>();
		   IMontadora();
		 init_data();
	 }
	   public G20(Cor eCor) {
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
	 area_46();
	 area_47();
	 area_48();
	 area_49();
	 area_50();
	 area_51();
	 area_52();
	 area_53();
	 area_54();
	 area_55();
	 area_56();
	 area_57();
	 area_58();
	 area_59();
	 area_60();
	 area_61();
	 area_62();
	}
	 private void area_0(){
		   mapear(51,10,4);
		   mapear(118,10,4);
		   mapear(174,10,6);
		   mapear(231,10,4);
		   mapear(321,10,4);
		   mapear(52,11,3);
		   mapear(119,11,3);
		   mapear(173,11,3);
		   mapear(178,11,2);
		   mapear(232,11,3);
		   mapear(262,11,3);
		   mapear(292,11,3);
		   mapear(322,11,3);
		   mapear(52,12,3);
		   mapear(119,12,3);
		   mapear(172,12,3);
		   mapear(179,12,1);
		   mapear(232,12,3);
		   mapear(262,12,3);
		   mapear(292,12,3);
		   mapear(322,12,3);
		   mapear(52,13,3);
		   mapear(119,13,3);
		   mapear(172,13,3);
		   mapear(232,13,3);
		   mapear(262,13,3);
		   mapear(292,13,3);
		   mapear(322,13,3);
		   mapear(52,14,3);
		   mapear(119,14,3);
		   mapear(172,14,3);
		   mapear(232,14,3);
		   mapear(322,14,3);
		   mapear(23,15,6);
		   mapear(52,15,3);
		   mapear(56,15,5);
		   mapear(84,15,6);
		   mapear(113,15,5);
		   mapear(119,15,3);
		   mapear(144,15,5);
		   mapear(170,15,8);
		   mapear(203,15,5);
		   mapear(209,15,4);
		   mapear(232,15,3);
		   mapear(236,15,5);
		   mapear(261,15,4);
		   mapear(291,15,4);
		   mapear(322,15,3);
		   mapear(328,15,5);
		   mapear(22,16,2);
	}
	 private void area_1(){
		   mapear(27,16,4);
		   mapear(52,16,4);
		   mapear(59,16,3);
		   mapear(82,16,3);
		   mapear(89,16,2);
		   mapear(112,16,3);
		   mapear(118,16,4);
		   mapear(142,16,3);
		   mapear(148,16,3);
		   mapear(172,16,3);
		   mapear(202,16,3);
		   mapear(208,16,4);
		   mapear(232,16,4);
		   mapear(239,16,3);
		   mapear(262,16,3);
		   mapear(292,16,3);
		   mapear(322,16,3);
		   mapear(329,16,1);
		   mapear(22,17,1);
		   mapear(28,17,3);
		   mapear(52,17,3);
		   mapear(60,17,2);
		   mapear(82,17,2);
		   mapear(90,17,1);
		   mapear(111,17,3);
		   mapear(119,17,3);
		   mapear(142,17,2);
		   mapear(149,17,2);
		   mapear(172,17,3);
		   mapear(201,17,3);
		   mapear(209,17,3);
		   mapear(232,17,3);
		   mapear(239,17,3);
		   mapear(262,17,3);
		   mapear(292,17,3);
		   mapear(322,17,3);
		   mapear(328,17,1);
		   mapear(23,18,8);
		   mapear(52,18,3);
		   mapear(60,18,3);
		   mapear(81,18,3);
		   mapear(90,18,1);
		   mapear(111,18,3);
		   mapear(119,18,3);
		   mapear(141,18,3);
		   mapear(149,18,3);
		   mapear(172,18,3);
		   mapear(201,18,3);
		   mapear(209,18,3);
		   mapear(232,18,3);
	}
	 private void area_2(){
		   mapear(239,18,3);
		   mapear(262,18,3);
		   mapear(292,18,3);
		   mapear(322,18,3);
		   mapear(326,18,2);
		   mapear(22,19,3);
		   mapear(28,19,3);
		   mapear(52,19,3);
		   mapear(60,19,3);
		   mapear(81,19,3);
		   mapear(111,19,3);
		   mapear(119,19,3);
		   mapear(141,19,11);
		   mapear(172,19,3);
		   mapear(201,19,3);
		   mapear(209,19,3);
		   mapear(232,19,3);
		   mapear(239,19,3);
		   mapear(262,19,3);
		   mapear(292,19,3);
		   mapear(322,19,7);
		   mapear(21,20,3);
		   mapear(28,20,3);
		   mapear(52,20,3);
		   mapear(60,20,3);
		   mapear(81,20,3);
		   mapear(111,20,3);
		   mapear(119,20,3);
		   mapear(141,20,3);
		   mapear(172,20,3);
		   mapear(201,20,3);
		   mapear(209,20,3);
		   mapear(232,20,3);
		   mapear(239,20,3);
		   mapear(262,20,3);
		   mapear(292,20,3);
		   mapear(322,20,8);
		   mapear(21,21,3);
		   mapear(28,21,3);
		   mapear(52,21,3);
		   mapear(60,21,3);
		   mapear(81,21,3);
		   mapear(111,21,3);
		   mapear(119,21,3);
		   mapear(141,21,3);
		   mapear(172,21,3);
		   mapear(201,21,3);
		   mapear(209,21,3);
		   mapear(232,21,3);
		   mapear(239,21,3);
	}
	 private void area_3(){
		   mapear(262,21,3);
		   mapear(292,21,3);
		   mapear(322,21,3);
		   mapear(327,21,4);
		   mapear(21,22,3);
		   mapear(28,22,3);
		   mapear(52,22,3);
		   mapear(60,22,2);
		   mapear(82,22,2);
		   mapear(89,22,2);
		   mapear(111,22,3);
		   mapear(119,22,3);
		   mapear(142,22,2);
		   mapear(150,22,2);
		   mapear(172,22,3);
		   mapear(201,22,3);
		   mapear(209,22,3);
		   mapear(232,22,3);
		   mapear(239,22,3);
		   mapear(262,22,3);
		   mapear(292,22,3);
		   mapear(322,22,3);
		   mapear(328,22,3);
		   mapear(21,23,4);
		   mapear(27,23,4);
		   mapear(52,23,4);
		   mapear(59,23,3);
		   mapear(82,23,3);
		   mapear(88,23,2);
		   mapear(112,23,3);
		   mapear(118,23,4);
		   mapear(142,23,3);
		   mapear(149,23,2);
		   mapear(172,23,3);
		   mapear(202,23,3);
		   mapear(208,23,4);
		   mapear(232,23,3);
		   mapear(239,23,3);
		   mapear(262,23,3);
		   mapear(292,23,3);
		   mapear(322,23,3);
		   mapear(328,23,4);
		   mapear(22,24,5);
		   mapear(28,24,4);
		   mapear(51,24,4);
		   mapear(56,24,5);
		   mapear(84,24,5);
		   mapear(113,24,5);
		   mapear(119,24,4);
		   mapear(144,24,6);
	}
	 private void area_4(){
		   mapear(171,24,6);
		   mapear(203,24,5);
		   mapear(209,24,3);
		   mapear(231,24,5);
		   mapear(238,24,5);
		   mapear(261,24,5);
		   mapear(292,24,3);
		   mapear(321,24,5);
		   mapear(327,24,7);
		   mapear(209,25,3);
		   mapear(292,25,3);
		   mapear(202,26,1);
		   mapear(209,26,3);
		   mapear(292,26,3);
		   mapear(202,27,2);
		   mapear(208,27,3);
		   mapear(288,27,1);
		   mapear(292,27,3);
		   mapear(203,28,6);
		   mapear(288,28,6);
		   mapear(21,50,4);
		   mapear(22,51,3);
		   mapear(22,52,3);
		   mapear(262,52,3);
		   mapear(22,53,3);
		   mapear(262,53,3);
		   mapear(22,54,3);
		   mapear(262,54,3);
		   mapear(22,55,3);
		   mapear(51,55,4);
		   mapear(56,55,5);
		   mapear(63,55,5);
		   mapear(81,55,4);
		   mapear(86,55,5);
		   mapear(114,55,5);
		   mapear(141,55,4);
		   mapear(146,55,5);
		   mapear(173,55,5);
		   mapear(179,55,4);
		   mapear(201,55,4);
		   mapear(206,55,5);
		   mapear(233,55,6);
		   mapear(260,55,8);
		   mapear(291,55,4);
		   mapear(298,55,4);
		   mapear(320,55,5);
		   mapear(328,55,4);
		   mapear(22,56,3);
		   mapear(52,56,4);
		   mapear(59,56,4);
	}
	 private void area_5(){
		   mapear(66,56,3);
		   mapear(82,56,4);
		   mapear(89,56,3);
		   mapear(112,56,3);
		   mapear(118,56,3);
		   mapear(142,56,4);
		   mapear(149,56,3);
		   mapear(172,56,3);
		   mapear(178,56,4);
		   mapear(202,56,4);
		   mapear(209,56,2);
		   mapear(231,56,3);
		   mapear(238,56,2);
		   mapear(262,56,3);
		   mapear(292,56,3);
		   mapear(299,56,3);
		   mapear(321,56,3);
		   mapear(330,56,1);
		   mapear(22,57,3);
		   mapear(52,57,3);
		   mapear(59,57,3);
		   mapear(66,57,3);
		   mapear(82,57,3);
		   mapear(89,57,3);
		   mapear(112,57,2);
		   mapear(119,57,2);
		   mapear(142,57,3);
		   mapear(150,57,2);
		   mapear(171,57,3);
		   mapear(179,57,3);
		   mapear(202,57,4);
		   mapear(210,57,1);
		   mapear(231,57,3);
		   mapear(239,57,1);
		   mapear(262,57,3);
		   mapear(292,57,3);
		   mapear(299,57,3);
		   mapear(321,57,4);
		   mapear(329,57,1);
		   mapear(22,58,3);
		   mapear(52,58,3);
		   mapear(59,58,3);
		   mapear(66,58,3);
		   mapear(82,58,3);
		   mapear(89,58,3);
		   mapear(111,58,3);
		   mapear(119,58,3);
		   mapear(142,58,3);
		   mapear(150,58,3);
		   mapear(171,58,3);
	}
	 private void area_6(){
		   mapear(179,58,3);
		   mapear(202,58,3);
		   mapear(231,58,5);
		   mapear(262,58,3);
		   mapear(292,58,3);
		   mapear(299,58,3);
		   mapear(322,58,3);
		   mapear(329,58,1);
		   mapear(22,59,3);
		   mapear(52,59,3);
		   mapear(59,59,3);
		   mapear(66,59,3);
		   mapear(82,59,3);
		   mapear(89,59,3);
		   mapear(111,59,3);
		   mapear(119,59,3);
		   mapear(142,59,3);
		   mapear(150,59,3);
		   mapear(171,59,3);
		   mapear(179,59,3);
		   mapear(202,59,3);
		   mapear(231,59,9);
		   mapear(262,59,3);
		   mapear(292,59,3);
		   mapear(299,59,3);
		   mapear(322,59,3);
		   mapear(328,59,1);
		   mapear(22,60,3);
		   mapear(52,60,3);
		   mapear(59,60,3);
		   mapear(66,60,3);
		   mapear(82,60,3);
		   mapear(89,60,3);
		   mapear(111,60,3);
		   mapear(119,60,3);
		   mapear(142,60,3);
		   mapear(150,60,3);
		   mapear(171,60,3);
		   mapear(179,60,3);
		   mapear(202,60,3);
		   mapear(232,60,9);
		   mapear(262,60,3);
		   mapear(292,60,3);
		   mapear(299,60,3);
		   mapear(323,60,3);
		   mapear(328,60,1);
		   mapear(22,61,3);
		   mapear(52,61,3);
		   mapear(59,61,3);
		   mapear(66,61,3);
	}
	 private void area_7(){
		   mapear(82,61,3);
		   mapear(89,61,3);
		   mapear(111,61,3);
		   mapear(119,61,3);
		   mapear(142,61,3);
		   mapear(150,61,3);
		   mapear(171,61,3);
		   mapear(179,61,3);
		   mapear(202,61,3);
		   mapear(236,61,5);
		   mapear(262,61,3);
		   mapear(292,61,3);
		   mapear(299,61,3);
		   mapear(323,61,3);
		   mapear(328,61,1);
		   mapear(22,62,3);
		   mapear(52,62,3);
		   mapear(59,62,3);
		   mapear(66,62,3);
		   mapear(82,62,3);
		   mapear(89,62,3);
		   mapear(112,62,2);
		   mapear(119,62,2);
		   mapear(142,62,3);
		   mapear(150,62,2);
		   mapear(171,62,3);
		   mapear(179,62,3);
		   mapear(202,62,3);
		   mapear(231,62,1);
		   mapear(238,62,3);
		   mapear(262,62,3);
		   mapear(268,62,1);
		   mapear(292,62,3);
		   mapear(299,62,3);
		   mapear(323,62,5);
		   mapear(22,63,3);
		   mapear(52,63,3);
		   mapear(59,63,3);
		   mapear(66,63,3);
		   mapear(82,63,3);
		   mapear(89,63,3);
		   mapear(112,63,3);
		   mapear(118,63,3);
		   mapear(142,63,4);
		   mapear(149,63,3);
		   mapear(172,63,3);
		   mapear(178,63,4);
		   mapear(202,63,3);
		   mapear(231,63,2);
		   mapear(238,63,3);
	}
	 private void area_8(){
		   mapear(262,63,3);
		   mapear(268,63,1);
		   mapear(292,63,3);
		   mapear(298,63,4);
		   mapear(324,63,4);
		   mapear(21,64,5);
		   mapear(51,64,5);
		   mapear(58,64,5);
		   mapear(65,64,5);
		   mapear(81,64,5);
		   mapear(88,64,5);
		   mapear(114,64,5);
		   mapear(142,64,3);
		   mapear(146,64,5);
		   mapear(173,64,5);
		   mapear(179,64,3);
		   mapear(201,64,5);
		   mapear(232,64,7);
		   mapear(263,64,5);
		   mapear(293,64,5);
		   mapear(299,64,4);
		   mapear(324,64,3);
		   mapear(142,65,3);
		   mapear(179,65,3);
		   mapear(142,66,3);
		   mapear(179,66,3);
		   mapear(142,67,3);
		   mapear(179,67,3);
		   mapear(141,68,5);
		   mapear(178,68,5);
		   mapear(146,90,3);
		   mapear(171,90,12);
		   mapear(206,90,7);
		   mapear(231,90,11);
		   mapear(261,90,13);
		   mapear(291,90,13);
		   mapear(326,90,7);
		   mapear(146,91,4);
		   mapear(173,91,3);
		   mapear(181,91,3);
		   mapear(204,91,3);
		   mapear(212,91,3);
		   mapear(233,91,3);
		   mapear(241,91,3);
		   mapear(263,91,3);
		   mapear(273,91,1);
		   mapear(293,91,3);
		   mapear(303,91,1);
		   mapear(324,91,3);
		   mapear(332,91,3);
	}
	 private void area_9(){
		   mapear(146,92,4);
		   mapear(173,92,3);
		   mapear(182,92,3);
		   mapear(203,92,2);
		   mapear(213,92,2);
		   mapear(233,92,3);
		   mapear(242,92,3);
		   mapear(263,92,3);
		   mapear(273,92,1);
		   mapear(293,92,3);
		   mapear(303,92,1);
		   mapear(323,92,2);
		   mapear(333,92,2);
		   mapear(145,93,1);
		   mapear(147,93,3);
		   mapear(173,93,3);
		   mapear(182,93,3);
		   mapear(202,93,3);
		   mapear(213,93,2);
		   mapear(233,93,3);
		   mapear(243,93,3);
		   mapear(263,93,3);
		   mapear(293,93,3);
		   mapear(322,93,3);
		   mapear(333,93,2);
		   mapear(145,94,1);
		   mapear(148,94,3);
		   mapear(173,94,3);
		   mapear(182,94,3);
		   mapear(202,94,2);
		   mapear(214,94,1);
		   mapear(233,94,3);
		   mapear(244,94,3);
		   mapear(263,94,3);
		   mapear(271,94,1);
		   mapear(293,94,3);
		   mapear(301,94,1);
		   mapear(321,94,3);
		   mapear(334,94,1);
		   mapear(20,95,5);
		   mapear(29,95,2);
		   mapear(35,95,3);
		   mapear(50,95,6);
		   mapear(58,95,4);
		   mapear(80,95,5);
		   mapear(88,95,4);
		   mapear(111,95,10);
		   mapear(144,95,2);
		   mapear(148,95,3);
		   mapear(173,95,3);
	}
	 private void area_10(){
		   mapear(181,95,3);
		   mapear(201,95,3);
		   mapear(233,95,3);
		   mapear(244,95,3);
		   mapear(263,95,3);
		   mapear(271,95,1);
		   mapear(293,95,3);
		   mapear(301,95,1);
		   mapear(321,95,3);
		   mapear(21,96,3);
		   mapear(28,96,4);
		   mapear(36,96,1);
		   mapear(52,96,3);
		   mapear(59,96,1);
		   mapear(81,96,3);
		   mapear(89,96,2);
		   mapear(111,96,1);
		   mapear(117,96,4);
		   mapear(144,96,1);
		   mapear(148,96,4);
		   mapear(173,96,10);
		   mapear(201,96,3);
		   mapear(233,96,3);
		   mapear(244,96,3);
		   mapear(263,96,9);
		   mapear(293,96,9);
		   mapear(321,96,3);
		   mapear(22,97,3);
		   mapear(28,97,4);
		   mapear(35,97,1);
		   mapear(52,97,4);
		   mapear(58,97,1);
		   mapear(82,97,3);
		   mapear(89,97,1);
		   mapear(111,97,1);
		   mapear(116,97,4);
		   mapear(144,97,1);
		   mapear(149,97,3);
		   mapear(173,97,3);
		   mapear(181,97,4);
		   mapear(201,97,3);
		   mapear(233,97,3);
		   mapear(244,97,3);
		   mapear(263,97,3);
		   mapear(271,97,1);
		   mapear(293,97,3);
		   mapear(301,97,1);
		   mapear(321,97,3);
		   mapear(331,97,5);
		   mapear(22,98,3);
	}
	 private void area_11(){
		   mapear(28,98,4);
		   mapear(35,98,1);
		   mapear(53,98,5);
		   mapear(82,98,3);
		   mapear(89,98,1);
		   mapear(115,98,4);
		   mapear(143,98,1);
		   mapear(149,98,3);
		   mapear(173,98,3);
		   mapear(182,98,4);
		   mapear(201,98,3);
		   mapear(233,98,3);
		   mapear(244,98,3);
		   mapear(263,98,3);
		   mapear(271,98,1);
		   mapear(293,98,3);
		   mapear(301,98,1);
		   mapear(321,98,3);
		   mapear(333,98,3);
		   mapear(22,99,3);
		   mapear(27,99,1);
		   mapear(29,99,4);
		   mapear(34,99,2);
		   mapear(54,99,4);
		   mapear(82,99,4);
		   mapear(88,99,1);
		   mapear(114,99,4);
		   mapear(143,99,1);
		   mapear(150,99,3);
		   mapear(173,99,3);
		   mapear(183,99,3);
		   mapear(201,99,3);
		   mapear(233,99,3);
		   mapear(244,99,3);
		   mapear(263,99,3);
		   mapear(293,99,3);
		   mapear(321,99,3);
		   mapear(333,99,3);
		   mapear(23,100,3);
		   mapear(27,100,1);
		   mapear(30,100,3);
		   mapear(34,100,1);
		   mapear(54,100,4);
		   mapear(83,100,3);
		   mapear(88,100,1);
		   mapear(114,100,4);
		   mapear(142,100,11);
		   mapear(173,100,3);
		   mapear(183,100,3);
		   mapear(201,100,3);
	}
	 private void area_12(){
		   mapear(233,100,3);
		   mapear(244,100,3);
		   mapear(263,100,3);
		   mapear(293,100,3);
		   mapear(322,100,2);
		   mapear(333,100,3);
		   mapear(23,101,3);
		   mapear(27,101,1);
		   mapear(30,101,3);
		   mapear(34,101,1);
		   mapear(53,101,1);
		   mapear(55,101,4);
		   mapear(83,101,3);
		   mapear(87,101,2);
		   mapear(113,101,4);
		   mapear(142,101,1);
		   mapear(150,101,4);
		   mapear(173,101,3);
		   mapear(183,101,3);
		   mapear(202,101,3);
		   mapear(213,101,2);
		   mapear(233,101,3);
		   mapear(243,101,3);
		   mapear(263,101,3);
		   mapear(293,101,3);
		   mapear(322,101,3);
		   mapear(333,101,3);
		   mapear(23,102,4);
		   mapear(31,102,3);
		   mapear(53,102,1);
		   mapear(56,102,4);
		   mapear(84,102,4);
		   mapear(112,102,4);
		   mapear(120,102,1);
		   mapear(142,102,1);
		   mapear(151,102,3);
		   mapear(173,102,3);
		   mapear(182,102,4);
		   mapear(203,102,2);
		   mapear(212,102,2);
		   mapear(233,102,3);
		   mapear(242,102,3);
		   mapear(263,102,3);
		   mapear(273,102,1);
		   mapear(293,102,3);
		   mapear(323,102,2);
		   mapear(333,102,3);
		   mapear(24,103,3);
		   mapear(31,103,3);
		   mapear(52,103,1);
	}
	 private void area_13(){
		   mapear(57,103,3);
		   mapear(84,103,4);
		   mapear(111,103,4);
		   mapear(120,103,1);
		   mapear(141,103,1);
		   mapear(151,103,3);
		   mapear(173,103,3);
		   mapear(181,103,4);
		   mapear(204,103,2);
		   mapear(211,103,3);
		   mapear(233,103,3);
		   mapear(241,103,3);
		   mapear(263,103,3);
		   mapear(273,103,1);
		   mapear(293,103,3);
		   mapear(324,103,3);
		   mapear(332,103,3);
		   mapear(24,104,2);
		   mapear(31,104,3);
		   mapear(50,104,4);
		   mapear(55,104,7);
		   mapear(84,104,3);
		   mapear(111,104,10);
		   mapear(140,104,4);
		   mapear(150,104,6);
		   mapear(171,104,12);
		   mapear(206,104,6);
		   mapear(231,104,11);
		   mapear(261,104,13);
		   mapear(291,104,7);
		   mapear(326,104,7);
		   mapear(85,105,2);
		   mapear(85,106,1);
		   mapear(81,107,1);
		   mapear(85,107,1);
		   mapear(81,108,4);
		   mapear(21,130,7);
		   mapear(31,130,7);
		   mapear(51,130,7);
		   mapear(81,130,7);
		   mapear(111,130,7);
		   mapear(120,130,7);
		   mapear(141,130,7);
		   mapear(171,130,6);
		   mapear(186,130,6);
		   mapear(201,130,4);
		   mapear(213,130,5);
		   mapear(236,130,6);
		   mapear(261,130,11);
		   mapear(296,130,6);
	}
	 private void area_14(){
		   mapear(321,130,11);
		   mapear(23,131,3);
		   mapear(33,131,3);
		   mapear(53,131,3);
		   mapear(83,131,3);
		   mapear(113,131,3);
		   mapear(122,131,2);
		   mapear(143,131,3);
		   mapear(173,131,5);
		   mapear(186,131,4);
		   mapear(203,131,3);
		   mapear(215,131,1);
		   mapear(234,131,3);
		   mapear(241,131,3);
		   mapear(263,131,3);
		   mapear(270,131,4);
		   mapear(294,131,3);
		   mapear(301,131,3);
		   mapear(323,131,3);
		   mapear(330,131,4);
		   mapear(23,132,3);
		   mapear(33,132,3);
		   mapear(53,132,3);
		   mapear(83,132,3);
		   mapear(113,132,3);
		   mapear(121,132,2);
		   mapear(143,132,3);
		   mapear(173,132,1);
		   mapear(175,132,3);
		   mapear(185,132,5);
		   mapear(203,132,4);
		   mapear(215,132,1);
		   mapear(233,132,2);
		   mapear(243,132,2);
		   mapear(263,132,3);
		   mapear(271,132,4);
		   mapear(293,132,2);
		   mapear(303,132,2);
		   mapear(323,132,3);
		   mapear(331,132,4);
		   mapear(23,133,3);
		   mapear(33,133,3);
		   mapear(53,133,3);
		   mapear(83,133,3);
		   mapear(113,133,3);
		   mapear(120,133,2);
		   mapear(143,133,3);
		   mapear(173,133,1);
		   mapear(175,133,4);
		   mapear(185,133,1);
	}
	 private void area_15(){
		   mapear(187,133,3);
		   mapear(203,133,5);
		   mapear(215,133,1);
		   mapear(232,133,3);
		   mapear(243,133,3);
		   mapear(263,133,3);
		   mapear(272,133,3);
		   mapear(292,133,3);
		   mapear(303,133,3);
		   mapear(323,133,3);
		   mapear(332,133,3);
		   mapear(23,134,3);
		   mapear(33,134,3);
		   mapear(53,134,3);
		   mapear(83,134,3);
		   mapear(113,134,3);
		   mapear(119,134,2);
		   mapear(143,134,3);
		   mapear(173,134,1);
		   mapear(176,134,3);
		   mapear(184,134,2);
		   mapear(187,134,3);
		   mapear(203,134,6);
		   mapear(215,134,1);
		   mapear(231,134,3);
		   mapear(244,134,3);
		   mapear(263,134,3);
		   mapear(272,134,3);
		   mapear(291,134,3);
		   mapear(304,134,3);
		   mapear(323,134,3);
		   mapear(332,134,3);
		   mapear(23,135,3);
		   mapear(33,135,3);
		   mapear(53,135,3);
		   mapear(83,135,3);
		   mapear(113,135,3);
		   mapear(118,135,1);
		   mapear(143,135,3);
		   mapear(173,135,1);
		   mapear(176,135,4);
		   mapear(184,135,1);
		   mapear(187,135,3);
		   mapear(203,135,1);
		   mapear(205,135,5);
		   mapear(215,135,1);
		   mapear(231,135,3);
		   mapear(244,135,3);
		   mapear(263,135,3);
		   mapear(272,135,3);
	}
	 private void area_16(){
		   mapear(291,135,3);
		   mapear(304,135,3);
		   mapear(323,135,3);
		   mapear(332,135,3);
		   mapear(23,136,13);
		   mapear(53,136,3);
		   mapear(83,136,3);
		   mapear(113,136,3);
		   mapear(117,136,3);
		   mapear(143,136,3);
		   mapear(173,136,1);
		   mapear(177,136,3);
		   mapear(183,136,2);
		   mapear(187,136,3);
		   mapear(203,136,1);
		   mapear(206,136,5);
		   mapear(215,136,1);
		   mapear(231,136,3);
		   mapear(244,136,3);
		   mapear(263,136,3);
		   mapear(271,136,3);
		   mapear(291,136,3);
		   mapear(304,136,3);
		   mapear(323,136,3);
		   mapear(331,136,4);
		   mapear(23,137,3);
		   mapear(33,137,3);
		   mapear(53,137,3);
		   mapear(83,137,3);
		   mapear(113,137,8);
		   mapear(143,137,3);
		   mapear(173,137,1);
		   mapear(177,137,4);
		   mapear(183,137,1);
		   mapear(187,137,3);
		   mapear(203,137,1);
		   mapear(207,137,5);
		   mapear(215,137,1);
		   mapear(231,137,3);
		   mapear(244,137,3);
		   mapear(263,137,3);
		   mapear(270,137,4);
		   mapear(291,137,3);
		   mapear(304,137,3);
		   mapear(323,137,3);
		   mapear(330,137,4);
		   mapear(23,138,3);
		   mapear(33,138,3);
		   mapear(53,138,3);
		   mapear(83,138,3);
	}
	 private void area_17(){
		   mapear(113,138,3);
		   mapear(117,138,5);
		   mapear(143,138,3);
		   mapear(173,138,1);
		   mapear(178,138,3);
		   mapear(182,138,2);
		   mapear(187,138,3);
		   mapear(203,138,1);
		   mapear(208,138,5);
		   mapear(215,138,1);
		   mapear(231,138,3);
		   mapear(244,138,3);
		   mapear(263,138,9);
		   mapear(291,138,3);
		   mapear(304,138,3);
		   mapear(323,138,9);
		   mapear(23,139,3);
		   mapear(33,139,3);
		   mapear(53,139,3);
		   mapear(83,139,3);
		   mapear(113,139,3);
		   mapear(118,139,5);
		   mapear(143,139,3);
		   mapear(173,139,1);
		   mapear(178,139,5);
		   mapear(187,139,3);
		   mapear(203,139,1);
		   mapear(209,139,5);
		   mapear(215,139,1);
		   mapear(231,139,3);
		   mapear(244,139,3);
		   mapear(263,139,3);
		   mapear(291,139,3);
		   mapear(304,139,3);
		   mapear(323,139,3);
		   mapear(330,139,3);
		   mapear(23,140,3);
		   mapear(33,140,3);
		   mapear(53,140,3);
		   mapear(83,140,3);
		   mapear(113,140,3);
		   mapear(119,140,5);
		   mapear(143,140,3);
		   mapear(173,140,1);
		   mapear(179,140,4);
		   mapear(187,140,3);
		   mapear(203,140,1);
		   mapear(210,140,6);
		   mapear(231,140,3);
		   mapear(244,140,3);
	}
	 private void area_18(){
		   mapear(263,140,3);
		   mapear(291,140,3);
		   mapear(304,140,3);
		   mapear(323,140,3);
		   mapear(331,140,3);
		   mapear(23,141,3);
		   mapear(33,141,3);
		   mapear(53,141,3);
		   mapear(83,141,3);
		   mapear(113,141,3);
		   mapear(120,141,5);
		   mapear(143,141,3);
		   mapear(173,141,1);
		   mapear(179,141,3);
		   mapear(187,141,3);
		   mapear(203,141,1);
		   mapear(211,141,5);
		   mapear(232,141,3);
		   mapear(243,141,3);
		   mapear(263,141,3);
		   mapear(292,141,3);
		   mapear(303,141,3);
		   mapear(323,141,3);
		   mapear(331,141,3);
		   mapear(23,142,3);
		   mapear(33,142,3);
		   mapear(53,142,3);
		   mapear(83,142,3);
		   mapear(113,142,3);
		   mapear(121,142,5);
		   mapear(143,142,3);
		   mapear(153,142,1);
		   mapear(173,142,1);
		   mapear(180,142,2);
		   mapear(187,142,3);
		   mapear(203,142,1);
		   mapear(212,142,4);
		   mapear(233,142,2);
		   mapear(243,142,2);
		   mapear(263,142,3);
		   mapear(293,142,2);
		   mapear(303,142,3);
		   mapear(323,142,3);
		   mapear(332,142,3);
		   mapear(23,143,3);
		   mapear(33,143,3);
		   mapear(53,143,3);
		   mapear(83,143,3);
		   mapear(113,143,3);
		   mapear(122,143,5);
	}
	 private void area_19(){
		   mapear(143,143,3);
		   mapear(153,143,1);
		   mapear(173,143,1);
		   mapear(187,143,3);
		   mapear(203,143,1);
		   mapear(213,143,3);
		   mapear(234,143,3);
		   mapear(241,143,3);
		   mapear(263,143,3);
		   mapear(294,143,3);
		   mapear(301,143,4);
		   mapear(323,143,3);
		   mapear(332,143,3);
		   mapear(21,144,7);
		   mapear(31,144,7);
		   mapear(51,144,7);
		   mapear(83,144,3);
		   mapear(111,144,7);
		   mapear(123,144,6);
		   mapear(141,144,13);
		   mapear(171,144,5);
		   mapear(185,144,7);
		   mapear(201,144,5);
		   mapear(214,144,2);
		   mapear(236,144,6);
		   mapear(261,144,7);
		   mapear(296,144,7);
		   mapear(321,144,7);
		   mapear(333,144,4);
		   mapear(83,145,3);
		   mapear(299,145,3);
		   mapear(78,146,1);
		   mapear(83,146,3);
		   mapear(300,146,3);
		   mapear(78,147,2);
		   mapear(82,147,3);
		   mapear(301,147,4);
		   mapear(79,148,5);
		   mapear(302,148,3);
		   mapear(24,170,6);
		   mapear(51,170,13);
		   mapear(81,170,7);
		   mapear(93,170,5);
		   mapear(110,170,6);
		   mapear(122,170,4);
		   mapear(140,170,6);
		   mapear(151,170,3);
		   mapear(159,170,4);
		   mapear(170,170,7);
		   mapear(179,170,5);
	}
	 private void area_20(){
		   mapear(200,170,6);
		   mapear(210,170,4);
		   mapear(231,170,13);
		   mapear(294,170,6);
		   mapear(325,170,3);
		   mapear(22,171,3);
		   mapear(29,171,3);
		   mapear(51,171,1);
		   mapear(56,171,3);
		   mapear(63,171,1);
		   mapear(83,171,3);
		   mapear(95,171,1);
		   mapear(111,171,4);
		   mapear(123,171,2);
		   mapear(141,171,4);
		   mapear(151,171,4);
		   mapear(160,171,1);
		   mapear(172,171,3);
		   mapear(181,171,1);
		   mapear(202,171,3);
		   mapear(211,171,2);
		   mapear(231,171,1);
		   mapear(240,171,4);
		   mapear(293,171,2);
		   mapear(299,171,2);
		   mapear(323,171,5);
		   mapear(21,172,3);
		   mapear(30,172,2);
		   mapear(51,172,1);
		   mapear(56,172,3);
		   mapear(63,172,1);
		   mapear(83,172,3);
		   mapear(95,172,1);
		   mapear(112,172,3);
		   mapear(123,172,1);
		   mapear(142,172,3);
		   mapear(150,172,5);
		   mapear(160,172,1);
		   mapear(172,172,4);
		   mapear(180,172,2);
		   mapear(202,172,4);
		   mapear(211,172,1);
		   mapear(231,172,1);
		   mapear(239,172,4);
		   mapear(292,172,2);
		   mapear(300,172,2);
		   mapear(322,172,1);
		   mapear(325,172,3);
		   mapear(21,173,3);
		   mapear(31,173,1);
	}
	 private void area_21(){
		   mapear(56,173,3);
		   mapear(83,173,3);
		   mapear(95,173,1);
		   mapear(112,173,4);
		   mapear(123,173,1);
		   mapear(142,173,3);
		   mapear(150,173,1);
		   mapear(152,173,3);
		   mapear(160,173,1);
		   mapear(173,173,3);
		   mapear(180,173,1);
		   mapear(203,173,3);
		   mapear(210,173,2);
		   mapear(239,173,3);
		   mapear(292,173,2);
		   mapear(300,173,2);
		   mapear(325,173,3);
		   mapear(21,174,3);
		   mapear(56,174,3);
		   mapear(83,174,3);
		   mapear(95,174,1);
		   mapear(113,174,3);
		   mapear(122,174,1);
		   mapear(142,174,3);
		   mapear(150,174,1);
		   mapear(152,174,3);
		   mapear(159,174,1);
		   mapear(174,174,3);
		   mapear(179,174,1);
		   mapear(203,174,4);
		   mapear(210,174,1);
		   mapear(238,174,4);
		   mapear(291,174,3);
		   mapear(300,174,3);
		   mapear(325,174,3);
		   mapear(21,175,5);
		   mapear(56,175,3);
		   mapear(83,175,3);
		   mapear(95,175,1);
		   mapear(113,175,3);
		   mapear(122,175,1);
		   mapear(143,175,3);
		   mapear(150,175,1);
		   mapear(153,175,3);
		   mapear(159,175,1);
		   mapear(174,175,6);
		   mapear(204,175,4);
		   mapear(209,175,1);
		   mapear(237,175,4);
		   mapear(291,175,3);
	}
	 private void area_22(){
		   mapear(300,175,3);
		   mapear(325,175,3);
		   mapear(22,176,8);
		   mapear(56,176,3);
		   mapear(83,176,3);
		   mapear(95,176,1);
		   mapear(113,176,4);
		   mapear(122,176,1);
		   mapear(143,176,3);
		   mapear(149,176,1);
		   mapear(153,176,3);
		   mapear(159,176,1);
		   mapear(175,176,4);
		   mapear(205,176,3);
		   mapear(209,176,1);
		   mapear(236,176,4);
		   mapear(291,176,3);
		   mapear(300,176,3);
		   mapear(325,176,3);
		   mapear(23,177,9);
		   mapear(56,177,3);
		   mapear(83,177,3);
		   mapear(95,177,1);
		   mapear(114,177,3);
		   mapear(121,177,1);
		   mapear(143,177,3);
		   mapear(149,177,1);
		   mapear(153,177,3);
		   mapear(158,177,2);
		   mapear(175,177,5);
		   mapear(205,177,4);
		   mapear(236,177,3);
		   mapear(291,177,3);
		   mapear(300,177,3);
		   mapear(325,177,3);
		   mapear(26,178,7);
		   mapear(56,178,3);
		   mapear(83,178,3);
		   mapear(95,178,1);
		   mapear(114,178,3);
		   mapear(121,178,1);
		   mapear(144,178,3);
		   mapear(149,178,1);
		   mapear(154,178,3);
		   mapear(158,178,1);
		   mapear(175,178,5);
		   mapear(206,178,3);
		   mapear(235,178,4);
		   mapear(291,178,3);
		   mapear(300,178,3);
	}
	 private void area_23(){
		   mapear(325,178,3);
		   mapear(29,179,4);
		   mapear(56,179,3);
		   mapear(83,179,3);
		   mapear(95,179,1);
		   mapear(115,179,3);
		   mapear(120,179,1);
		   mapear(144,179,3);
		   mapear(148,179,1);
		   mapear(154,179,3);
		   mapear(158,179,1);
		   mapear(175,179,1);
		   mapear(177,179,4);
		   mapear(206,179,3);
		   mapear(234,179,4);
		   mapear(291,179,3);
		   mapear(300,179,3);
		   mapear(325,179,3);
		   mapear(30,180,3);
		   mapear(56,180,3);
		   mapear(83,180,3);
		   mapear(95,180,1);
		   mapear(115,180,3);
		   mapear(120,180,1);
		   mapear(144,180,3);
		   mapear(148,180,1);
		   mapear(154,180,3);
		   mapear(158,180,1);
		   mapear(174,180,1);
		   mapear(178,180,3);
		   mapear(206,180,3);
		   mapear(233,180,4);
		   mapear(291,180,3);
		   mapear(300,180,3);
		   mapear(325,180,3);
		   mapear(21,181,1);
		   mapear(30,181,3);
		   mapear(56,181,3);
		   mapear(83,181,3);
		   mapear(95,181,1);
		   mapear(115,181,4);
		   mapear(120,181,1);
		   mapear(144,181,5);
		   mapear(154,181,4);
		   mapear(174,181,1);
		   mapear(179,181,3);
		   mapear(206,181,3);
		   mapear(233,181,3);
		   mapear(292,181,2);
		   mapear(300,181,2);
	}
	 private void area_24(){
		   mapear(325,181,3);
		   mapear(21,182,2);
		   mapear(30,182,3);
		   mapear(56,182,3);
		   mapear(84,182,3);
		   mapear(94,182,1);
		   mapear(116,182,4);
		   mapear(145,182,3);
		   mapear(155,182,3);
		   mapear(173,182,1);
		   mapear(179,182,4);
		   mapear(206,182,3);
		   mapear(232,182,4);
		   mapear(243,182,1);
		   mapear(292,182,2);
		   mapear(300,182,2);
		   mapear(325,182,3);
		   mapear(21,183,3);
		   mapear(29,183,3);
		   mapear(56,183,3);
		   mapear(84,183,4);
		   mapear(93,183,2);
		   mapear(116,183,4);
		   mapear(145,183,3);
		   mapear(155,183,3);
		   mapear(172,183,1);
		   mapear(180,183,3);
		   mapear(206,183,3);
		   mapear(231,183,4);
		   mapear(243,183,1);
		   mapear(293,183,2);
		   mapear(299,183,2);
		   mapear(325,183,3);
		   mapear(23,184,7);
		   mapear(54,184,7);
		   mapear(86,184,7);
		   mapear(117,184,2);
		   mapear(145,184,3);
		   mapear(155,184,2);
		   mapear(170,184,5);
		   mapear(178,184,7);
		   mapear(204,184,7);
		   mapear(231,184,13);
		   mapear(294,184,6);
		   mapear(322,184,9);
		   mapear(260,188,10);
		   mapear(260,189,10);
		   mapear(22,210,7);
		   mapear(52,210,7);
		   mapear(87,210,4);
	}
	 private void area_25(){
		   mapear(112,210,9);
		   mapear(145,210,6);
		   mapear(171,210,11);
		   mapear(204,210,6);
		   mapear(234,210,6);
		   mapear(21,211,2);
		   mapear(28,211,3);
		   mapear(51,211,2);
		   mapear(57,211,3);
		   mapear(86,211,1);
		   mapear(88,211,3);
		   mapear(112,211,9);
		   mapear(143,211,3);
		   mapear(150,211,2);
		   mapear(171,211,11);
		   mapear(203,211,2);
		   mapear(209,211,2);
		   mapear(232,211,3);
		   mapear(239,211,2);
		   mapear(21,212,2);
		   mapear(29,212,3);
		   mapear(51,212,1);
		   mapear(58,212,3);
		   mapear(86,212,1);
		   mapear(88,212,3);
		   mapear(112,212,9);
		   mapear(142,212,3);
		   mapear(151,212,1);
		   mapear(171,212,11);
		   mapear(202,212,3);
		   mapear(209,212,3);
		   mapear(232,212,2);
		   mapear(240,212,2);
		   mapear(21,213,1);
		   mapear(29,213,3);
		   mapear(58,213,3);
		   mapear(85,213,1);
		   mapear(88,213,3);
		   mapear(112,213,1);
		   mapear(142,213,2);
		   mapear(171,213,1);
		   mapear(180,213,2);
		   mapear(202,213,3);
		   mapear(209,213,3);
		   mapear(231,213,3);
		   mapear(240,213,2);
		   mapear(304,213,1);
		   mapear(322,213,1);
		   mapear(29,214,3);
		   mapear(58,214,3);
	}
	 private void area_26(){
		   mapear(84,214,2);
		   mapear(88,214,3);
		   mapear(112,214,1);
		   mapear(141,214,3);
		   mapear(171,214,1);
		   mapear(179,214,2);
		   mapear(202,214,3);
		   mapear(209,214,3);
		   mapear(231,214,3);
		   mapear(240,214,3);
		   mapear(301,214,4);
		   mapear(322,214,4);
		   mapear(29,215,3);
		   mapear(57,215,3);
		   mapear(84,215,1);
		   mapear(88,215,3);
		   mapear(112,215,1);
		   mapear(114,215,5);
		   mapear(141,215,3);
		   mapear(145,215,5);
		   mapear(178,215,2);
		   mapear(203,215,2);
		   mapear(209,215,2);
		   mapear(231,215,3);
		   mapear(240,215,3);
		   mapear(298,215,6);
		   mapear(323,215,6);
		   mapear(28,216,3);
		   mapear(55,216,4);
		   mapear(83,216,1);
		   mapear(88,216,3);
		   mapear(112,216,2);
		   mapear(118,216,3);
		   mapear(141,216,4);
		   mapear(149,216,3);
		   mapear(178,216,2);
		   mapear(204,216,6);
		   mapear(231,216,3);
		   mapear(240,216,3);
		   mapear(295,216,6);
		   mapear(326,216,6);
		   mapear(28,217,3);
		   mapear(58,217,3);
		   mapear(83,217,1);
		   mapear(88,217,3);
		   mapear(119,217,2);
		   mapear(141,217,3);
		   mapear(150,217,2);
		   mapear(177,217,2);
		   mapear(202,217,3);
	}
	 private void area_27(){
		   mapear(209,217,3);
		   mapear(232,217,2);
		   mapear(240,217,3);
		   mapear(292,217,6);
		   mapear(329,217,6);
		   mapear(26,218,3);
		   mapear(59,218,3);
		   mapear(82,218,1);
		   mapear(88,218,3);
		   mapear(119,218,3);
		   mapear(141,218,3);
		   mapear(150,218,3);
		   mapear(177,218,2);
		   mapear(201,218,3);
		   mapear(210,218,3);
		   mapear(232,218,3);
		   mapear(239,218,4);
		   mapear(261,218,6);
		   mapear(292,218,3);
		   mapear(332,218,3);
		   mapear(25,219,3);
		   mapear(59,219,3);
		   mapear(81,219,1);
		   mapear(88,219,3);
		   mapear(119,219,3);
		   mapear(141,219,3);
		   mapear(150,219,3);
		   mapear(176,219,2);
		   mapear(201,219,3);
		   mapear(210,219,3);
		   mapear(234,219,5);
		   mapear(240,219,3);
		   mapear(261,219,6);
		   mapear(292,219,6);
		   mapear(329,219,6);
		   mapear(23,220,3);
		   mapear(31,220,1);
		   mapear(59,220,3);
		   mapear(81,220,13);
		   mapear(119,220,3);
		   mapear(141,220,3);
		   mapear(150,220,3);
		   mapear(176,220,2);
		   mapear(201,220,3);
		   mapear(210,220,3);
		   mapear(240,220,3);
		   mapear(261,220,6);
		   mapear(295,220,6);
		   mapear(326,220,6);
		   mapear(22,221,2);
	}
	 private void area_28(){
		   mapear(31,221,1);
		   mapear(51,221,1);
		   mapear(59,221,3);
		   mapear(88,221,3);
		   mapear(111,221,1);
		   mapear(119,221,3);
		   mapear(142,221,2);
		   mapear(150,221,3);
		   mapear(175,221,2);
		   mapear(201,221,3);
		   mapear(210,221,3);
		   mapear(240,221,2);
		   mapear(298,221,6);
		   mapear(323,221,6);
		   mapear(21,222,11);
		   mapear(51,222,1);
		   mapear(59,222,3);
		   mapear(88,222,3);
		   mapear(111,222,1);
		   mapear(118,222,3);
		   mapear(142,222,2);
		   mapear(150,222,2);
		   mapear(175,222,2);
		   mapear(201,222,3);
		   mapear(210,222,3);
		   mapear(232,222,1);
		   mapear(239,222,3);
		   mapear(301,222,4);
		   mapear(322,222,4);
		   mapear(21,223,11);
		   mapear(51,223,2);
		   mapear(58,223,3);
		   mapear(88,223,3);
		   mapear(111,223,2);
		   mapear(118,223,3);
		   mapear(143,223,2);
		   mapear(149,223,3);
		   mapear(174,223,2);
		   mapear(202,223,3);
		   mapear(209,223,3);
		   mapear(232,223,2);
		   mapear(238,223,3);
		   mapear(304,223,1);
		   mapear(322,223,1);
		   mapear(21,224,11);
		   mapear(52,224,7);
		   mapear(86,224,7);
		   mapear(112,224,7);
		   mapear(144,224,6);
		   mapear(174,224,2);
	}
	 private void area_29(){
		   mapear(204,224,6);
		   mapear(233,224,6);
		   mapear(145,250,2);
		   mapear(170,250,2);
		   mapear(265,250,1);
		   mapear(327,250,1);
		   mapear(145,251,2);
		   mapear(170,251,3);
		   mapear(265,251,1);
		   mapear(325,251,2);
		   mapear(144,252,2);
		   mapear(171,252,2);
		   mapear(262,252,1);
		   mapear(265,252,1);
		   mapear(268,252,1);
		   mapear(324,252,2);
		   mapear(144,253,2);
		   mapear(171,253,2);
		   mapear(207,253,2);
		   mapear(261,253,3);
		   mapear(265,253,1);
		   mapear(267,253,3);
		   mapear(323,253,3);
		   mapear(144,254,2);
		   mapear(171,254,3);
		   mapear(207,254,2);
		   mapear(263,254,5);
		   mapear(323,254,2);
		   mapear(83,255,2);
		   mapear(113,255,2);
		   mapear(143,255,2);
		   mapear(172,255,2);
		   mapear(207,255,2);
		   mapear(263,255,5);
		   mapear(323,255,2);
		   mapear(82,256,4);
		   mapear(112,256,4);
		   mapear(143,256,2);
		   mapear(172,256,2);
		   mapear(207,256,2);
		   mapear(261,256,3);
		   mapear(265,256,1);
		   mapear(267,256,3);
		   mapear(292,256,13);
		   mapear(322,256,3);
		   mapear(82,257,4);
		   mapear(112,257,4);
		   mapear(143,257,2);
		   mapear(172,257,2);
		   mapear(207,257,2);
	}
	 private void area_30(){
		   mapear(262,257,1);
		   mapear(265,257,1);
		   mapear(268,257,1);
		   mapear(292,257,13);
		   mapear(322,257,3);
		   mapear(83,258,2);
		   mapear(113,258,2);
		   mapear(142,258,2);
		   mapear(173,258,2);
		   mapear(202,258,12);
		   mapear(231,258,6);
		   mapear(265,258,1);
		   mapear(322,258,3);
		   mapear(142,259,2);
		   mapear(173,259,2);
		   mapear(202,259,12);
		   mapear(231,259,6);
		   mapear(265,259,1);
		   mapear(322,259,3);
		   mapear(142,260,2);
		   mapear(173,260,2);
		   mapear(207,260,2);
		   mapear(231,260,6);
		   mapear(292,260,13);
		   mapear(322,260,3);
		   mapear(23,261,2);
		   mapear(83,261,2);
		   mapear(141,261,3);
		   mapear(174,261,2);
		   mapear(207,261,2);
		   mapear(292,261,13);
		   mapear(322,261,3);
		   mapear(22,262,4);
		   mapear(52,262,3);
		   mapear(82,262,4);
		   mapear(113,262,3);
		   mapear(141,262,2);
		   mapear(174,262,2);
		   mapear(207,262,2);
		   mapear(323,262,2);
		   mapear(22,263,4);
		   mapear(52,263,3);
		   mapear(82,263,4);
		   mapear(113,263,3);
		   mapear(141,263,2);
		   mapear(174,263,2);
		   mapear(207,263,2);
		   mapear(323,263,2);
		   mapear(23,264,2);
		   mapear(52,264,3);
	}
	 private void area_31(){
		   mapear(83,264,2);
		   mapear(113,264,3);
		   mapear(140,264,3);
		   mapear(175,264,2);
		   mapear(207,264,2);
		   mapear(323,264,3);
		   mapear(51,265,3);
		   mapear(112,265,3);
		   mapear(140,265,2);
		   mapear(175,265,2);
		   mapear(324,265,2);
		   mapear(51,266,2);
		   mapear(112,266,2);
		   mapear(325,266,2);
		   mapear(50,267,2);
		   mapear(111,267,2);
		   mapear(327,267,1);
		   mapear(266,289,1);
		   mapear(21,290,1);
		   mapear(53,290,5);
		   mapear(82,290,5);
		   mapear(116,290,5);
		   mapear(142,290,5);
		   mapear(173,290,4);
		   mapear(266,290,1);
		   mapear(292,290,4);
		   mapear(302,290,2);
		   mapear(22,291,2);
		   mapear(53,291,3);
		   mapear(84,291,3);
		   mapear(115,291,3);
		   mapear(145,291,3);
		   mapear(173,291,4);
		   mapear(207,291,6);
		   mapear(237,291,2);
		   mapear(241,291,2);
		   mapear(266,291,1);
		   mapear(291,291,2);
		   mapear(295,291,2);
		   mapear(301,291,2);
		   mapear(23,292,2);
		   mapear(53,292,3);
		   mapear(84,292,3);
		   mapear(115,292,3);
		   mapear(145,292,3);
		   mapear(173,292,4);
		   mapear(205,292,2);
		   mapear(213,292,2);
		   mapear(236,292,3);
		   mapear(241,292,2);
	}
	 private void area_32(){
		   mapear(263,292,7);
		   mapear(290,292,3);
		   mapear(295,292,3);
		   mapear(301,292,2);
		   mapear(23,293,3);
		   mapear(53,293,3);
		   mapear(84,293,3);
		   mapear(115,293,3);
		   mapear(145,293,3);
		   mapear(173,293,4);
		   mapear(203,293,3);
		   mapear(215,293,1);
		   mapear(236,293,2);
		   mapear(241,293,2);
		   mapear(262,293,3);
		   mapear(266,293,1);
		   mapear(269,293,2);
		   mapear(290,293,3);
		   mapear(295,293,3);
		   mapear(300,293,2);
		   mapear(24,294,2);
		   mapear(53,294,3);
		   mapear(84,294,3);
		   mapear(115,294,3);
		   mapear(145,294,3);
		   mapear(174,294,2);
		   mapear(203,294,2);
		   mapear(215,294,2);
		   mapear(236,294,2);
		   mapear(241,294,2);
		   mapear(261,294,3);
		   mapear(266,294,1);
		   mapear(270,294,1);
		   mapear(290,294,3);
		   mapear(295,294,3);
		   mapear(300,294,2);
		   mapear(24,295,2);
		   mapear(53,295,3);
		   mapear(84,295,3);
		   mapear(115,295,3);
		   mapear(145,295,3);
		   mapear(174,295,2);
		   mapear(202,295,2);
		   mapear(207,295,4);
		   mapear(212,295,2);
		   mapear(216,295,1);
		   mapear(232,295,13);
		   mapear(261,295,3);
		   mapear(266,295,1);
		   mapear(270,295,1);
	}
	 private void area_33(){
		   mapear(290,295,3);
		   mapear(295,295,3);
		   mapear(299,295,2);
		   mapear(324,295,6);
		   mapear(24,296,3);
		   mapear(53,296,3);
		   mapear(84,296,3);
		   mapear(115,296,3);
		   mapear(145,296,3);
		   mapear(174,296,2);
		   mapear(202,296,2);
		   mapear(206,296,2);
		   mapear(211,296,3);
		   mapear(217,296,1);
		   mapear(232,296,13);
		   mapear(261,296,4);
		   mapear(266,296,1);
		   mapear(291,296,2);
		   mapear(295,296,2);
		   mapear(299,296,1);
		   mapear(322,296,3);
		   mapear(329,296,2);
		   mapear(24,297,3);
		   mapear(53,297,3);
		   mapear(84,297,3);
		   mapear(115,297,3);
		   mapear(145,297,3);
		   mapear(174,297,2);
		   mapear(201,297,2);
		   mapear(205,297,2);
		   mapear(212,297,2);
		   mapear(217,297,1);
		   mapear(235,297,2);
		   mapear(240,297,2);
		   mapear(261,297,8);
		   mapear(292,297,4);
		   mapear(298,297,2);
		   mapear(302,297,4);
		   mapear(322,297,2);
		   mapear(330,297,1);
		   mapear(24,298,3);
		   mapear(53,298,3);
		   mapear(84,298,3);
		   mapear(114,298,4);
		   mapear(145,298,4);
		   mapear(174,298,2);
		   mapear(201,298,2);
		   mapear(205,298,2);
		   mapear(212,298,2);
		   mapear(217,298,1);
	}
	 private void area_34(){
		   mapear(235,298,2);
		   mapear(239,298,3);
		   mapear(262,298,9);
		   mapear(298,298,1);
		   mapear(301,298,2);
		   mapear(305,298,2);
		   mapear(321,298,3);
		   mapear(330,298,1);
		   mapear(24,299,3);
		   mapear(53,299,3);
		   mapear(84,299,3);
		   mapear(112,299,4);
		   mapear(147,299,4);
		   mapear(174,299,2);
		   mapear(201,299,2);
		   mapear(205,299,2);
		   mapear(212,299,2);
		   mapear(217,299,1);
		   mapear(231,299,13);
		   mapear(265,299,7);
		   mapear(297,299,2);
		   mapear(300,299,3);
		   mapear(305,299,3);
		   mapear(321,299,3);
		   mapear(24,300,3);
		   mapear(53,300,3);
		   mapear(84,300,3);
		   mapear(114,300,4);
		   mapear(145,300,4);
		   mapear(201,300,2);
		   mapear(205,300,2);
		   mapear(212,300,2);
		   mapear(217,300,1);
		   mapear(231,300,13);
		   mapear(266,300,1);
		   mapear(268,300,4);
		   mapear(296,300,2);
		   mapear(300,300,3);
		   mapear(305,300,3);
		   mapear(321,300,3);
		   mapear(24,301,3);
		   mapear(53,301,3);
		   mapear(84,301,3);
		   mapear(115,301,3);
		   mapear(145,301,3);
		   mapear(174,301,2);
		   mapear(201,301,2);
		   mapear(205,301,2);
		   mapear(212,301,2);
		   mapear(217,301,1);
	}
	 private void area_35(){
		   mapear(234,301,2);
		   mapear(239,301,2);
		   mapear(261,301,1);
		   mapear(266,301,1);
		   mapear(269,301,3);
		   mapear(296,301,2);
		   mapear(300,301,3);
		   mapear(305,301,3);
		   mapear(321,301,3);
		   mapear(24,302,2);
		   mapear(53,302,3);
		   mapear(84,302,3);
		   mapear(115,302,3);
		   mapear(145,302,3);
		   mapear(173,302,4);
		   mapear(201,302,2);
		   mapear(205,302,2);
		   mapear(212,302,2);
		   mapear(216,302,1);
		   mapear(234,302,2);
		   mapear(239,302,2);
		   mapear(261,302,1);
		   mapear(266,302,1);
		   mapear(269,302,3);
		   mapear(295,302,2);
		   mapear(300,302,3);
		   mapear(305,302,3);
		   mapear(322,302,2);
		   mapear(329,302,2);
		   mapear(24,303,2);
		   mapear(53,303,3);
		   mapear(84,303,3);
		   mapear(115,303,3);
		   mapear(145,303,3);
		   mapear(173,303,4);
		   mapear(202,303,2);
		   mapear(206,303,2);
		   mapear(211,303,3);
		   mapear(215,303,1);
		   mapear(233,303,3);
		   mapear(238,303,2);
		   mapear(261,303,3);
		   mapear(266,303,1);
		   mapear(268,303,3);
		   mapear(295,303,2);
		   mapear(301,303,2);
		   mapear(305,303,2);
		   mapear(322,303,3);
		   mapear(328,303,2);
		   mapear(23,304,3);
	}
	 private void area_36(){
		   mapear(53,304,3);
		   mapear(84,304,3);
		   mapear(115,304,3);
		   mapear(145,304,3);
		   mapear(174,304,2);
		   mapear(202,304,2);
		   mapear(207,304,4);
		   mapear(212,304,3);
		   mapear(233,304,2);
		   mapear(238,304,2);
		   mapear(263,304,7);
		   mapear(294,304,2);
		   mapear(302,304,4);
		   mapear(324,304,5);
		   mapear(23,305,2);
		   mapear(53,305,3);
		   mapear(84,305,3);
		   mapear(115,305,3);
		   mapear(145,305,3);
		   mapear(203,305,2);
		   mapear(266,305,1);
		   mapear(325,305,2);
		   mapear(22,306,2);
		   mapear(53,306,3);
		   mapear(84,306,3);
		   mapear(115,306,3);
		   mapear(145,306,3);
		   mapear(204,306,2);
		   mapear(214,306,1);
		   mapear(266,306,1);
		   mapear(326,306,2);
		   mapear(21,307,1);
		   mapear(53,307,5);
		   mapear(82,307,5);
		   mapear(115,307,3);
		   mapear(145,307,3);
		   mapear(205,307,2);
		   mapear(212,307,3);
		   mapear(266,307,1);
		   mapear(326,307,2);
		   mapear(116,308,5);
		   mapear(142,308,5);
		   mapear(207,308,6);
		   mapear(323,308,4);
		   mapear(144,328,2);
		   mapear(149,328,1);
		   mapear(294,328,2);
		   mapear(299,328,1);
		   mapear(56,329,3);
		   mapear(82,329,3);
	}
	 private void area_37(){
		   mapear(114,329,3);
		   mapear(143,329,4);
		   mapear(149,329,1);
		   mapear(172,329,3);
		   mapear(177,329,3);
		   mapear(206,329,3);
		   mapear(232,329,3);
		   mapear(264,329,3);
		   mapear(293,329,4);
		   mapear(299,329,1);
		   mapear(322,329,3);
		   mapear(327,329,3);
		   mapear(26,330,7);
		   mapear(56,330,2);
		   mapear(83,330,2);
		   mapear(113,330,5);
		   mapear(143,330,1);
		   mapear(146,330,4);
		   mapear(172,330,3);
		   mapear(177,330,3);
		   mapear(206,330,2);
		   mapear(233,330,2);
		   mapear(263,330,5);
		   mapear(293,330,1);
		   mapear(296,330,4);
		   mapear(322,330,3);
		   mapear(327,330,3);
		   mapear(24,331,3);
		   mapear(32,331,3);
		   mapear(55,331,2);
		   mapear(84,331,2);
		   mapear(113,331,2);
		   mapear(116,331,2);
		   mapear(143,331,1);
		   mapear(147,331,2);
		   mapear(172,331,3);
		   mapear(177,331,3);
		   mapear(205,331,2);
		   mapear(234,331,2);
		   mapear(263,331,2);
		   mapear(266,331,2);
		   mapear(293,331,1);
		   mapear(297,331,2);
		   mapear(322,331,3);
		   mapear(327,331,3);
		   mapear(23,332,2);
		   mapear(33,332,2);
		   mapear(54,332,2);
		   mapear(85,332,2);
		   mapear(112,332,2);
	}
	 private void area_38(){
		   mapear(117,332,2);
		   mapear(204,332,2);
		   mapear(235,332,2);
		   mapear(262,332,2);
		   mapear(267,332,2);
		   mapear(22,333,3);
		   mapear(33,333,2);
		   mapear(22,334,2);
		   mapear(34,334,1);
		   mapear(21,335,3);
		   mapear(53,335,6);
		   mapear(83,335,6);
		   mapear(113,335,6);
		   mapear(143,335,6);
		   mapear(173,335,6);
		   mapear(204,335,5);
		   mapear(234,335,5);
		   mapear(264,335,5);
		   mapear(294,335,5);
		   mapear(324,335,5);
		   mapear(21,336,3);
		   mapear(52,336,2);
		   mapear(57,336,4);
		   mapear(82,336,2);
		   mapear(87,336,4);
		   mapear(112,336,2);
		   mapear(117,336,4);
		   mapear(142,336,2);
		   mapear(147,336,4);
		   mapear(172,336,2);
		   mapear(177,336,4);
		   mapear(202,336,3);
		   mapear(208,336,3);
		   mapear(232,336,3);
		   mapear(238,336,3);
		   mapear(262,336,3);
		   mapear(268,336,3);
		   mapear(292,336,3);
		   mapear(298,336,3);
		   mapear(322,336,3);
		   mapear(328,336,3);
		   mapear(21,337,3);
		   mapear(52,337,1);
		   mapear(58,337,3);
		   mapear(82,337,1);
		   mapear(88,337,3);
		   mapear(112,337,1);
		   mapear(118,337,3);
		   mapear(142,337,1);
		   mapear(148,337,3);
	}
	 private void area_39(){
		   mapear(172,337,1);
		   mapear(178,337,3);
		   mapear(202,337,2);
		   mapear(209,337,2);
		   mapear(232,337,2);
		   mapear(239,337,2);
		   mapear(262,337,2);
		   mapear(269,337,2);
		   mapear(292,337,2);
		   mapear(299,337,2);
		   mapear(322,337,2);
		   mapear(329,337,2);
		   mapear(21,338,3);
		   mapear(53,338,8);
		   mapear(83,338,8);
		   mapear(113,338,8);
		   mapear(143,338,8);
		   mapear(173,338,8);
		   mapear(201,338,3);
		   mapear(209,338,3);
		   mapear(231,338,3);
		   mapear(239,338,3);
		   mapear(261,338,3);
		   mapear(269,338,3);
		   mapear(291,338,3);
		   mapear(299,338,3);
		   mapear(321,338,3);
		   mapear(329,338,3);
		   mapear(21,339,3);
		   mapear(52,339,3);
		   mapear(58,339,3);
		   mapear(82,339,3);
		   mapear(88,339,3);
		   mapear(112,339,3);
		   mapear(118,339,3);
		   mapear(142,339,3);
		   mapear(148,339,3);
		   mapear(172,339,3);
		   mapear(178,339,3);
		   mapear(201,339,11);
		   mapear(231,339,11);
		   mapear(261,339,11);
		   mapear(291,339,11);
		   mapear(321,339,11);
		   mapear(21,340,3);
		   mapear(51,340,3);
		   mapear(58,340,3);
		   mapear(81,340,3);
		   mapear(88,340,3);
		   mapear(111,340,3);
	}
	 private void area_40(){
		   mapear(118,340,3);
		   mapear(141,340,3);
		   mapear(148,340,3);
		   mapear(171,340,3);
		   mapear(178,340,3);
		   mapear(201,340,3);
		   mapear(231,340,3);
		   mapear(261,340,3);
		   mapear(291,340,3);
		   mapear(321,340,3);
		   mapear(22,341,3);
		   mapear(33,341,2);
		   mapear(51,341,3);
		   mapear(58,341,3);
		   mapear(81,341,3);
		   mapear(88,341,3);
		   mapear(111,341,3);
		   mapear(118,341,3);
		   mapear(141,341,3);
		   mapear(148,341,3);
		   mapear(171,341,3);
		   mapear(178,341,3);
		   mapear(201,341,3);
		   mapear(231,341,3);
		   mapear(261,341,3);
		   mapear(291,341,3);
		   mapear(321,341,3);
		   mapear(23,342,2);
		   mapear(32,342,2);
		   mapear(51,342,3);
		   mapear(58,342,3);
		   mapear(81,342,3);
		   mapear(88,342,3);
		   mapear(111,342,3);
		   mapear(118,342,3);
		   mapear(141,342,3);
		   mapear(148,342,3);
		   mapear(171,342,3);
		   mapear(178,342,3);
		   mapear(202,342,2);
		   mapear(210,342,2);
		   mapear(232,342,2);
		   mapear(240,342,2);
		   mapear(262,342,2);
		   mapear(270,342,2);
		   mapear(292,342,2);
		   mapear(300,342,2);
		   mapear(322,342,2);
		   mapear(330,342,2);
		   mapear(24,343,2);
	}
	 private void area_41(){
		   mapear(31,343,3);
		   mapear(51,343,4);
		   mapear(57,343,4);
		   mapear(81,343,4);
		   mapear(87,343,4);
		   mapear(111,343,4);
		   mapear(117,343,4);
		   mapear(141,343,4);
		   mapear(147,343,4);
		   mapear(171,343,4);
		   mapear(177,343,4);
		   mapear(202,343,3);
		   mapear(209,343,2);
		   mapear(232,343,3);
		   mapear(239,343,2);
		   mapear(262,343,3);
		   mapear(269,343,2);
		   mapear(292,343,3);
		   mapear(299,343,2);
		   mapear(322,343,3);
		   mapear(329,343,2);
		   mapear(26,344,6);
		   mapear(52,344,5);
		   mapear(58,344,4);
		   mapear(82,344,5);
		   mapear(88,344,4);
		   mapear(112,344,5);
		   mapear(118,344,4);
		   mapear(142,344,5);
		   mapear(148,344,4);
		   mapear(172,344,5);
		   mapear(178,344,4);
		   mapear(204,344,6);
		   mapear(234,344,6);
		   mapear(264,344,6);
		   mapear(294,344,6);
		   mapear(324,344,6);
		   mapear(28,345,2);
		   mapear(29,346,2);
		   mapear(29,347,2);
		   mapear(26,348,4);
		   mapear(112,368,2);
		   mapear(117,368,1);
		   mapear(265,368,2);
		   mapear(270,368,1);
		   mapear(24,369,3);
		   mapear(50,369,3);
		   mapear(82,369,3);
		   mapear(111,369,4);
		   mapear(117,369,1);
	}
	 private void area_42(){
		   mapear(140,369,3);
		   mapear(145,369,3);
		   mapear(177,369,3);
		   mapear(203,369,3);
		   mapear(235,369,3);
		   mapear(264,369,4);
		   mapear(270,369,1);
		   mapear(293,369,3);
		   mapear(298,369,3);
		   mapear(327,369,3);
		   mapear(24,370,2);
		   mapear(51,370,2);
		   mapear(81,370,5);
		   mapear(111,370,1);
		   mapear(114,370,4);
		   mapear(140,370,3);
		   mapear(145,370,3);
		   mapear(177,370,2);
		   mapear(204,370,2);
		   mapear(234,370,5);
		   mapear(264,370,1);
		   mapear(267,370,4);
		   mapear(293,370,3);
		   mapear(298,370,3);
		   mapear(327,370,2);
		   mapear(23,371,2);
		   mapear(52,371,2);
		   mapear(81,371,2);
		   mapear(84,371,2);
		   mapear(111,371,1);
		   mapear(115,371,2);
		   mapear(140,371,3);
		   mapear(145,371,3);
		   mapear(176,371,2);
		   mapear(205,371,2);
		   mapear(234,371,2);
		   mapear(237,371,2);
		   mapear(264,371,1);
		   mapear(268,371,2);
		   mapear(293,371,3);
		   mapear(298,371,3);
		   mapear(326,371,2);
		   mapear(22,372,2);
		   mapear(53,372,2);
		   mapear(80,372,2);
		   mapear(85,372,2);
		   mapear(175,372,2);
		   mapear(206,372,2);
		   mapear(233,372,2);
		   mapear(238,372,2);
	}
	 private void area_43(){
		   mapear(325,372,2);
		   mapear(21,375,4);
		   mapear(51,375,4);
		   mapear(81,375,4);
		   mapear(111,375,4);
		   mapear(141,375,4);
		   mapear(174,375,5);
		   mapear(204,375,5);
		   mapear(234,375,5);
		   mapear(264,375,5);
		   mapear(294,375,5);
		   mapear(321,375,4);
		   mapear(328,375,4);
		   mapear(22,376,3);
		   mapear(52,376,3);
		   mapear(82,376,3);
		   mapear(112,376,3);
		   mapear(142,376,3);
		   mapear(172,376,3);
		   mapear(178,376,3);
		   mapear(202,376,3);
		   mapear(208,376,3);
		   mapear(232,376,3);
		   mapear(238,376,3);
		   mapear(262,376,3);
		   mapear(268,376,3);
		   mapear(292,376,3);
		   mapear(298,376,3);
		   mapear(322,376,3);
		   mapear(329,376,3);
		   mapear(22,377,3);
		   mapear(52,377,3);
		   mapear(82,377,3);
		   mapear(112,377,3);
		   mapear(142,377,3);
		   mapear(172,377,2);
		   mapear(179,377,2);
		   mapear(202,377,2);
		   mapear(209,377,2);
		   mapear(232,377,2);
		   mapear(239,377,2);
		   mapear(262,377,2);
		   mapear(269,377,2);
		   mapear(292,377,2);
		   mapear(299,377,2);
		   mapear(322,377,3);
		   mapear(329,377,3);
		   mapear(22,378,3);
		   mapear(52,378,3);
		   mapear(82,378,3);
	}
	 private void area_44(){
		   mapear(112,378,3);
		   mapear(142,378,3);
		   mapear(171,378,3);
		   mapear(179,378,3);
		   mapear(201,378,3);
		   mapear(209,378,3);
		   mapear(231,378,3);
		   mapear(239,378,3);
		   mapear(261,378,3);
		   mapear(269,378,3);
		   mapear(291,378,3);
		   mapear(299,378,3);
		   mapear(322,378,3);
		   mapear(329,378,3);
		   mapear(22,379,3);
		   mapear(52,379,3);
		   mapear(82,379,3);
		   mapear(112,379,3);
		   mapear(142,379,3);
		   mapear(171,379,3);
		   mapear(179,379,3);
		   mapear(201,379,3);
		   mapear(209,379,3);
		   mapear(231,379,3);
		   mapear(239,379,3);
		   mapear(261,379,3);
		   mapear(269,379,3);
		   mapear(291,379,3);
		   mapear(299,379,3);
		   mapear(322,379,3);
		   mapear(329,379,3);
		   mapear(22,380,3);
		   mapear(52,380,3);
		   mapear(82,380,3);
		   mapear(112,380,3);
		   mapear(142,380,3);
		   mapear(171,380,3);
		   mapear(179,380,3);
		   mapear(201,380,3);
		   mapear(209,380,3);
		   mapear(231,380,3);
		   mapear(239,380,3);
		   mapear(261,380,3);
		   mapear(269,380,3);
		   mapear(291,380,3);
		   mapear(299,380,3);
		   mapear(322,380,3);
		   mapear(329,380,3);
		   mapear(22,381,3);
		   mapear(52,381,3);
	}
	 private void area_45(){
		   mapear(82,381,3);
		   mapear(112,381,3);
		   mapear(142,381,3);
		   mapear(171,381,3);
		   mapear(179,381,3);
		   mapear(201,381,3);
		   mapear(209,381,3);
		   mapear(231,381,3);
		   mapear(239,381,3);
		   mapear(261,381,3);
		   mapear(269,381,3);
		   mapear(291,381,3);
		   mapear(299,381,3);
		   mapear(322,381,3);
		   mapear(329,381,3);
		   mapear(22,382,3);
		   mapear(52,382,3);
		   mapear(82,382,3);
		   mapear(112,382,3);
		   mapear(142,382,3);
		   mapear(172,382,2);
		   mapear(179,382,2);
		   mapear(202,382,2);
		   mapear(209,382,2);
		   mapear(232,382,2);
		   mapear(239,382,2);
		   mapear(262,382,2);
		   mapear(269,382,2);
		   mapear(292,382,2);
		   mapear(299,382,2);
		   mapear(322,382,3);
		   mapear(329,382,3);
		   mapear(22,383,3);
		   mapear(52,383,3);
		   mapear(82,383,3);
		   mapear(112,383,3);
		   mapear(142,383,3);
		   mapear(172,383,3);
		   mapear(178,383,3);
		   mapear(202,383,3);
		   mapear(208,383,3);
		   mapear(232,383,3);
		   mapear(238,383,3);
		   mapear(262,383,3);
		   mapear(268,383,3);
		   mapear(292,383,3);
		   mapear(298,383,3);
		   mapear(322,383,3);
		   mapear(328,383,4);
		   mapear(21,384,5);
	}
	 private void area_46(){
		   mapear(51,384,5);
		   mapear(81,384,5);
		   mapear(111,384,5);
		   mapear(141,384,5);
		   mapear(174,384,5);
		   mapear(204,384,5);
		   mapear(234,384,5);
		   mapear(264,384,5);
		   mapear(294,384,5);
		   mapear(323,384,5);
		   mapear(329,384,4);
		   mapear(148,406,2);
		   mapear(176,406,2);
		   mapear(206,406,3);
		   mapear(235,406,3);
		   mapear(240,406,1);
		   mapear(264,406,3);
		   mapear(269,406,3);
		   mapear(298,406,2);
		   mapear(326,406,2);
		   mapear(147,407,2);
		   mapear(177,407,2);
		   mapear(205,407,2);
		   mapear(208,407,2);
		   mapear(234,407,7);
		   mapear(264,407,3);
		   mapear(269,407,3);
		   mapear(297,407,2);
		   mapear(327,407,2);
		   mapear(85,408,2);
		   mapear(90,408,1);
		   mapear(146,408,2);
		   mapear(178,408,2);
		   mapear(204,408,2);
		   mapear(209,408,2);
		   mapear(234,408,1);
		   mapear(237,408,3);
		   mapear(264,408,3);
		   mapear(269,408,3);
		   mapear(296,408,2);
		   mapear(328,408,2);
		   mapear(23,409,3);
		   mapear(55,409,3);
		   mapear(84,409,4);
		   mapear(90,409,1);
		   mapear(113,409,3);
		   mapear(118,409,3);
		   mapear(24,410,2);
		   mapear(54,410,5);
		   mapear(84,410,1);
	}
	 private void area_47(){
		   mapear(87,410,4);
		   mapear(113,410,3);
		   mapear(118,410,3);
		   mapear(146,410,3);
		   mapear(176,410,3);
		   mapear(206,410,3);
		   mapear(236,410,3);
		   mapear(266,410,3);
		   mapear(291,410,13);
		   mapear(321,410,13);
		   mapear(25,411,2);
		   mapear(54,411,2);
		   mapear(57,411,2);
		   mapear(84,411,1);
		   mapear(88,411,2);
		   mapear(113,411,3);
		   mapear(118,411,3);
		   mapear(146,411,4);
		   mapear(176,411,4);
		   mapear(206,411,4);
		   mapear(236,411,4);
		   mapear(266,411,4);
		   mapear(293,411,3);
		   mapear(303,411,1);
		   mapear(323,411,3);
		   mapear(333,411,1);
		   mapear(26,412,2);
		   mapear(53,412,2);
		   mapear(58,412,2);
		   mapear(146,412,4);
		   mapear(176,412,4);
		   mapear(206,412,4);
		   mapear(236,412,4);
		   mapear(266,412,4);
		   mapear(293,412,3);
		   mapear(303,412,1);
		   mapear(323,412,3);
		   mapear(333,412,1);
		   mapear(145,413,1);
		   mapear(147,413,3);
		   mapear(175,413,1);
		   mapear(177,413,3);
		   mapear(205,413,1);
		   mapear(207,413,3);
		   mapear(235,413,1);
		   mapear(237,413,3);
		   mapear(265,413,1);
		   mapear(267,413,3);
		   mapear(293,413,3);
		   mapear(323,413,3);
	}
	 private void area_48(){
		   mapear(145,414,1);
		   mapear(148,414,3);
		   mapear(175,414,1);
		   mapear(178,414,3);
		   mapear(205,414,1);
		   mapear(208,414,3);
		   mapear(235,414,1);
		   mapear(238,414,3);
		   mapear(265,414,1);
		   mapear(268,414,3);
		   mapear(293,414,3);
		   mapear(301,414,1);
		   mapear(323,414,3);
		   mapear(331,414,1);
		   mapear(21,415,4);
		   mapear(28,415,4);
		   mapear(51,415,4);
		   mapear(58,415,4);
		   mapear(81,415,4);
		   mapear(88,415,4);
		   mapear(111,415,4);
		   mapear(118,415,4);
		   mapear(144,415,2);
		   mapear(148,415,3);
		   mapear(174,415,2);
		   mapear(178,415,3);
		   mapear(204,415,2);
		   mapear(208,415,3);
		   mapear(234,415,2);
		   mapear(238,415,3);
		   mapear(264,415,2);
		   mapear(268,415,3);
		   mapear(293,415,3);
		   mapear(301,415,1);
		   mapear(323,415,3);
		   mapear(331,415,1);
		   mapear(22,416,3);
		   mapear(29,416,3);
		   mapear(52,416,3);
		   mapear(59,416,3);
		   mapear(82,416,3);
		   mapear(89,416,3);
		   mapear(112,416,3);
		   mapear(119,416,3);
		   mapear(144,416,1);
		   mapear(148,416,4);
		   mapear(174,416,1);
		   mapear(178,416,4);
		   mapear(204,416,1);
		   mapear(208,416,4);
	}
	 private void area_49(){
		   mapear(234,416,1);
		   mapear(238,416,4);
		   mapear(264,416,1);
		   mapear(268,416,4);
		   mapear(293,416,9);
		   mapear(323,416,9);
		   mapear(22,417,3);
		   mapear(29,417,3);
		   mapear(52,417,3);
		   mapear(59,417,3);
		   mapear(82,417,3);
		   mapear(89,417,3);
		   mapear(112,417,3);
		   mapear(119,417,3);
		   mapear(144,417,1);
		   mapear(149,417,3);
		   mapear(174,417,1);
		   mapear(179,417,3);
		   mapear(204,417,1);
		   mapear(209,417,3);
		   mapear(234,417,1);
		   mapear(239,417,3);
		   mapear(264,417,1);
		   mapear(269,417,3);
		   mapear(293,417,3);
		   mapear(301,417,1);
		   mapear(323,417,3);
		   mapear(331,417,1);
		   mapear(22,418,3);
		   mapear(29,418,3);
		   mapear(52,418,3);
		   mapear(59,418,3);
		   mapear(82,418,3);
		   mapear(89,418,3);
		   mapear(112,418,3);
		   mapear(119,418,3);
		   mapear(143,418,1);
		   mapear(149,418,3);
		   mapear(173,418,1);
		   mapear(179,418,3);
		   mapear(203,418,1);
		   mapear(209,418,3);
		   mapear(233,418,1);
		   mapear(239,418,3);
		   mapear(263,418,1);
		   mapear(269,418,3);
		   mapear(293,418,3);
		   mapear(301,418,1);
		   mapear(323,418,3);
		   mapear(331,418,1);
	}
	 private void area_50(){
		   mapear(22,419,3);
		   mapear(29,419,3);
		   mapear(52,419,3);
		   mapear(59,419,3);
		   mapear(82,419,3);
		   mapear(89,419,3);
		   mapear(112,419,3);
		   mapear(119,419,3);
		   mapear(143,419,1);
		   mapear(150,419,3);
		   mapear(173,419,1);
		   mapear(180,419,3);
		   mapear(203,419,1);
		   mapear(210,419,3);
		   mapear(233,419,1);
		   mapear(240,419,3);
		   mapear(263,419,1);
		   mapear(270,419,3);
		   mapear(293,419,3);
		   mapear(323,419,3);
		   mapear(22,420,3);
		   mapear(29,420,3);
		   mapear(52,420,3);
		   mapear(59,420,3);
		   mapear(82,420,3);
		   mapear(89,420,3);
		   mapear(112,420,3);
		   mapear(119,420,3);
		   mapear(142,420,11);
		   mapear(172,420,11);
		   mapear(202,420,11);
		   mapear(232,420,11);
		   mapear(262,420,11);
		   mapear(293,420,3);
		   mapear(323,420,3);
		   mapear(22,421,3);
		   mapear(29,421,3);
		   mapear(52,421,3);
		   mapear(59,421,3);
		   mapear(82,421,3);
		   mapear(89,421,3);
		   mapear(112,421,3);
		   mapear(119,421,3);
		   mapear(142,421,1);
		   mapear(150,421,4);
		   mapear(172,421,1);
		   mapear(180,421,4);
		   mapear(202,421,1);
		   mapear(210,421,4);
		   mapear(232,421,1);
	}
	 private void area_51(){
		   mapear(240,421,4);
		   mapear(262,421,1);
		   mapear(270,421,4);
		   mapear(293,421,3);
		   mapear(323,421,3);
		   mapear(22,422,3);
		   mapear(29,422,3);
		   mapear(52,422,3);
		   mapear(59,422,3);
		   mapear(82,422,3);
		   mapear(89,422,3);
		   mapear(112,422,3);
		   mapear(119,422,3);
		   mapear(142,422,1);
		   mapear(151,422,3);
		   mapear(172,422,1);
		   mapear(181,422,3);
		   mapear(202,422,1);
		   mapear(211,422,3);
		   mapear(232,422,1);
		   mapear(241,422,3);
		   mapear(262,422,1);
		   mapear(271,422,3);
		   mapear(293,422,3);
		   mapear(303,422,1);
		   mapear(323,422,3);
		   mapear(333,422,1);
		   mapear(22,423,3);
		   mapear(28,423,4);
		   mapear(52,423,3);
		   mapear(58,423,4);
		   mapear(82,423,3);
		   mapear(88,423,4);
		   mapear(112,423,3);
		   mapear(118,423,4);
		   mapear(141,423,1);
		   mapear(151,423,3);
		   mapear(171,423,1);
		   mapear(181,423,3);
		   mapear(201,423,1);
		   mapear(211,423,3);
		   mapear(231,423,1);
		   mapear(241,423,3);
		   mapear(261,423,1);
		   mapear(271,423,3);
		   mapear(293,423,3);
		   mapear(303,423,1);
		   mapear(323,423,3);
		   mapear(333,423,1);
		   mapear(23,424,5);
	}
	 private void area_52(){
		   mapear(29,424,4);
		   mapear(53,424,5);
		   mapear(59,424,4);
		   mapear(83,424,5);
		   mapear(89,424,4);
		   mapear(113,424,5);
		   mapear(119,424,4);
		   mapear(140,424,4);
		   mapear(150,424,6);
		   mapear(170,424,4);
		   mapear(180,424,6);
		   mapear(200,424,4);
		   mapear(210,424,6);
		   mapear(230,424,4);
		   mapear(240,424,6);
		   mapear(260,424,4);
		   mapear(270,424,6);
		   mapear(291,424,13);
		   mapear(321,424,13);
		   mapear(26,446,3);
		   mapear(55,446,3);
		   mapear(60,446,1);
		   mapear(84,446,3);
		   mapear(89,446,3);
		   mapear(115,446,2);
		   mapear(143,446,2);
		   mapear(173,446,3);
		   mapear(202,446,3);
		   mapear(207,446,1);
		   mapear(231,446,3);
		   mapear(236,446,3);
		   mapear(269,446,2);
		   mapear(297,446,2);
		   mapear(327,446,3);
		   mapear(25,447,2);
		   mapear(28,447,2);
		   mapear(54,447,7);
		   mapear(84,447,3);
		   mapear(89,447,3);
		   mapear(114,447,2);
		   mapear(144,447,2);
		   mapear(172,447,2);
		   mapear(175,447,2);
		   mapear(201,447,7);
		   mapear(231,447,3);
		   mapear(236,447,3);
		   mapear(268,447,2);
		   mapear(298,447,2);
		   mapear(326,447,2);
		   mapear(329,447,2);
	}
	 private void area_53(){
		   mapear(24,448,2);
		   mapear(29,448,2);
		   mapear(54,448,1);
		   mapear(57,448,3);
		   mapear(84,448,3);
		   mapear(89,448,3);
		   mapear(113,448,2);
		   mapear(145,448,2);
		   mapear(171,448,2);
		   mapear(176,448,2);
		   mapear(201,448,1);
		   mapear(204,448,3);
		   mapear(231,448,3);
		   mapear(236,448,3);
		   mapear(267,448,2);
		   mapear(299,448,2);
		   mapear(325,448,2);
		   mapear(330,448,2);
		   mapear(21,450,13);
		   mapear(51,450,13);
		   mapear(81,450,13);
		   mapear(111,450,7);
		   mapear(141,450,7);
		   mapear(171,450,7);
		   mapear(201,450,7);
		   mapear(231,450,7);
		   mapear(266,450,6);
		   mapear(296,450,6);
		   mapear(326,450,6);
		   mapear(23,451,3);
		   mapear(33,451,1);
		   mapear(53,451,3);
		   mapear(63,451,1);
		   mapear(83,451,3);
		   mapear(93,451,1);
		   mapear(113,451,3);
		   mapear(143,451,3);
		   mapear(173,451,3);
		   mapear(203,451,3);
		   mapear(233,451,3);
		   mapear(264,451,3);
		   mapear(271,451,3);
		   mapear(294,451,3);
		   mapear(301,451,3);
		   mapear(324,451,3);
		   mapear(331,451,3);
		   mapear(23,452,3);
		   mapear(33,452,1);
		   mapear(53,452,3);
		   mapear(63,452,1);
	}
	 private void area_54(){
		   mapear(83,452,3);
		   mapear(93,452,1);
		   mapear(113,452,3);
		   mapear(143,452,3);
		   mapear(173,452,3);
		   mapear(203,452,3);
		   mapear(233,452,3);
		   mapear(263,452,2);
		   mapear(273,452,2);
		   mapear(293,452,2);
		   mapear(303,452,2);
		   mapear(323,452,2);
		   mapear(333,452,2);
		   mapear(23,453,3);
		   mapear(53,453,3);
		   mapear(83,453,3);
		   mapear(113,453,3);
		   mapear(143,453,3);
		   mapear(173,453,3);
		   mapear(203,453,3);
		   mapear(233,453,3);
		   mapear(262,453,3);
		   mapear(273,453,3);
		   mapear(292,453,3);
		   mapear(303,453,3);
		   mapear(322,453,3);
		   mapear(333,453,3);
		   mapear(23,454,3);
		   mapear(31,454,1);
		   mapear(53,454,3);
		   mapear(61,454,1);
		   mapear(83,454,3);
		   mapear(91,454,1);
		   mapear(113,454,3);
		   mapear(143,454,3);
		   mapear(173,454,3);
		   mapear(203,454,3);
		   mapear(233,454,3);
		   mapear(261,454,3);
		   mapear(274,454,3);
		   mapear(291,454,3);
		   mapear(304,454,3);
		   mapear(321,454,3);
		   mapear(334,454,3);
		   mapear(23,455,3);
		   mapear(31,455,1);
		   mapear(53,455,3);
		   mapear(61,455,1);
		   mapear(83,455,3);
		   mapear(91,455,1);
	}
	 private void area_55(){
		   mapear(113,455,3);
		   mapear(143,455,3);
		   mapear(173,455,3);
		   mapear(203,455,3);
		   mapear(233,455,3);
		   mapear(261,455,3);
		   mapear(274,455,3);
		   mapear(291,455,3);
		   mapear(304,455,3);
		   mapear(321,455,3);
		   mapear(334,455,3);
		   mapear(23,456,9);
		   mapear(53,456,9);
		   mapear(83,456,9);
		   mapear(113,456,3);
		   mapear(143,456,3);
		   mapear(173,456,3);
		   mapear(203,456,3);
		   mapear(233,456,3);
		   mapear(261,456,3);
		   mapear(274,456,3);
		   mapear(291,456,3);
		   mapear(304,456,3);
		   mapear(321,456,3);
		   mapear(334,456,3);
		   mapear(23,457,3);
		   mapear(31,457,1);
		   mapear(53,457,3);
		   mapear(61,457,1);
		   mapear(83,457,3);
		   mapear(91,457,1);
		   mapear(113,457,3);
		   mapear(143,457,3);
		   mapear(173,457,3);
		   mapear(203,457,3);
		   mapear(233,457,3);
		   mapear(261,457,3);
		   mapear(274,457,3);
		   mapear(291,457,3);
		   mapear(304,457,3);
		   mapear(321,457,3);
		   mapear(334,457,3);
		   mapear(23,458,3);
		   mapear(31,458,1);
		   mapear(53,458,3);
		   mapear(61,458,1);
		   mapear(83,458,3);
		   mapear(91,458,1);
		   mapear(113,458,3);
		   mapear(143,458,3);
	}
	 private void area_56(){
		   mapear(173,458,3);
		   mapear(203,458,3);
		   mapear(233,458,3);
		   mapear(261,458,3);
		   mapear(274,458,3);
		   mapear(291,458,3);
		   mapear(304,458,3);
		   mapear(321,458,3);
		   mapear(334,458,3);
		   mapear(23,459,3);
		   mapear(53,459,3);
		   mapear(83,459,3);
		   mapear(113,459,3);
		   mapear(143,459,3);
		   mapear(173,459,3);
		   mapear(203,459,3);
		   mapear(233,459,3);
		   mapear(261,459,3);
		   mapear(274,459,3);
		   mapear(291,459,3);
		   mapear(304,459,3);
		   mapear(321,459,3);
		   mapear(334,459,3);
		   mapear(23,460,3);
		   mapear(53,460,3);
		   mapear(83,460,3);
		   mapear(113,460,3);
		   mapear(143,460,3);
		   mapear(173,460,3);
		   mapear(203,460,3);
		   mapear(233,460,3);
		   mapear(261,460,3);
		   mapear(274,460,3);
		   mapear(291,460,3);
		   mapear(304,460,3);
		   mapear(321,460,3);
		   mapear(334,460,3);
		   mapear(23,461,3);
		   mapear(53,461,3);
		   mapear(83,461,3);
		   mapear(113,461,3);
		   mapear(143,461,3);
		   mapear(173,461,3);
		   mapear(203,461,3);
		   mapear(233,461,3);
		   mapear(262,461,3);
		   mapear(273,461,3);
		   mapear(292,461,3);
		   mapear(303,461,3);
		   mapear(322,461,3);
	}
	 private void area_57(){
		   mapear(333,461,3);
		   mapear(23,462,3);
		   mapear(33,462,1);
		   mapear(53,462,3);
		   mapear(63,462,1);
		   mapear(83,462,3);
		   mapear(93,462,1);
		   mapear(113,462,3);
		   mapear(143,462,3);
		   mapear(173,462,3);
		   mapear(203,462,3);
		   mapear(233,462,3);
		   mapear(263,462,2);
		   mapear(273,462,2);
		   mapear(293,462,2);
		   mapear(303,462,2);
		   mapear(323,462,2);
		   mapear(333,462,2);
		   mapear(23,463,3);
		   mapear(33,463,1);
		   mapear(53,463,3);
		   mapear(63,463,1);
		   mapear(83,463,3);
		   mapear(93,463,1);
		   mapear(113,463,3);
		   mapear(143,463,3);
		   mapear(173,463,3);
		   mapear(203,463,3);
		   mapear(233,463,3);
		   mapear(264,463,3);
		   mapear(271,463,3);
		   mapear(294,463,3);
		   mapear(301,463,3);
		   mapear(324,463,3);
		   mapear(331,463,3);
		   mapear(21,464,13);
		   mapear(51,464,13);
		   mapear(81,464,13);
		   mapear(111,464,7);
		   mapear(141,464,7);
		   mapear(171,464,7);
		   mapear(201,464,7);
		   mapear(231,464,7);
		   mapear(266,464,6);
		   mapear(296,464,6);
		   mapear(326,464,6);
		   mapear(26,486,3);
		   mapear(31,486,1);
		   mapear(55,486,3);
		   mapear(60,486,3);
	}
	 private void area_58(){
		   mapear(89,486,2);
		   mapear(117,486,2);
		   mapear(147,486,3);
		   mapear(176,486,3);
		   mapear(181,486,1);
		   mapear(205,486,3);
		   mapear(210,486,3);
		   mapear(25,487,7);
		   mapear(55,487,3);
		   mapear(60,487,3);
		   mapear(88,487,2);
		   mapear(118,487,2);
		   mapear(146,487,2);
		   mapear(149,487,2);
		   mapear(175,487,7);
		   mapear(205,487,3);
		   mapear(210,487,3);
		   mapear(25,488,1);
		   mapear(28,488,3);
		   mapear(55,488,3);
		   mapear(60,488,3);
		   mapear(87,488,2);
		   mapear(119,488,2);
		   mapear(145,488,2);
		   mapear(150,488,2);
		   mapear(175,488,1);
		   mapear(178,488,3);
		   mapear(205,488,3);
		   mapear(210,488,3);
		   mapear(26,490,6);
		   mapear(56,490,6);
		   mapear(81,490,7);
		   mapear(93,490,5);
		   mapear(111,490,7);
		   mapear(123,490,5);
		   mapear(141,490,7);
		   mapear(153,490,5);
		   mapear(171,490,7);
		   mapear(183,490,5);
		   mapear(201,490,7);
		   mapear(213,490,5);
		   mapear(24,491,3);
		   mapear(31,491,3);
		   mapear(54,491,3);
		   mapear(61,491,3);
		   mapear(83,491,3);
		   mapear(95,491,1);
		   mapear(113,491,3);
		   mapear(125,491,1);
		   mapear(143,491,3);
	}
	 private void area_59(){
		   mapear(155,491,1);
		   mapear(173,491,3);
		   mapear(185,491,1);
		   mapear(203,491,3);
		   mapear(215,491,1);
		   mapear(23,492,2);
		   mapear(33,492,2);
		   mapear(53,492,2);
		   mapear(63,492,2);
		   mapear(83,492,3);
		   mapear(95,492,1);
		   mapear(113,492,3);
		   mapear(125,492,1);
		   mapear(143,492,3);
		   mapear(155,492,1);
		   mapear(173,492,3);
		   mapear(185,492,1);
		   mapear(203,492,3);
		   mapear(215,492,1);
		   mapear(22,493,3);
		   mapear(33,493,3);
		   mapear(52,493,3);
		   mapear(63,493,3);
		   mapear(83,493,3);
		   mapear(95,493,1);
		   mapear(113,493,3);
		   mapear(125,493,1);
		   mapear(143,493,3);
		   mapear(155,493,1);
		   mapear(173,493,3);
		   mapear(185,493,1);
		   mapear(203,493,3);
		   mapear(215,493,1);
		   mapear(21,494,3);
		   mapear(34,494,3);
		   mapear(51,494,3);
		   mapear(64,494,3);
		   mapear(83,494,3);
		   mapear(95,494,1);
		   mapear(113,494,3);
		   mapear(125,494,1);
		   mapear(143,494,3);
		   mapear(155,494,1);
		   mapear(173,494,3);
		   mapear(185,494,1);
		   mapear(203,494,3);
		   mapear(215,494,1);
		   mapear(21,495,3);
		   mapear(34,495,3);
		   mapear(51,495,3);
	}
	 private void area_60(){
		   mapear(64,495,3);
		   mapear(83,495,3);
		   mapear(95,495,1);
		   mapear(113,495,3);
		   mapear(125,495,1);
		   mapear(143,495,3);
		   mapear(155,495,1);
		   mapear(173,495,3);
		   mapear(185,495,1);
		   mapear(203,495,3);
		   mapear(215,495,1);
		   mapear(21,496,3);
		   mapear(34,496,3);
		   mapear(51,496,3);
		   mapear(64,496,3);
		   mapear(83,496,3);
		   mapear(95,496,1);
		   mapear(113,496,3);
		   mapear(125,496,1);
		   mapear(143,496,3);
		   mapear(155,496,1);
		   mapear(173,496,3);
		   mapear(185,496,1);
		   mapear(203,496,3);
		   mapear(215,496,1);
		   mapear(21,497,3);
		   mapear(34,497,3);
		   mapear(51,497,3);
		   mapear(64,497,3);
		   mapear(83,497,3);
		   mapear(95,497,1);
		   mapear(113,497,3);
		   mapear(125,497,1);
		   mapear(143,497,3);
		   mapear(155,497,1);
		   mapear(173,497,3);
		   mapear(185,497,1);
		   mapear(203,497,3);
		   mapear(215,497,1);
		   mapear(21,498,3);
		   mapear(34,498,3);
		   mapear(51,498,3);
		   mapear(64,498,3);
		   mapear(83,498,3);
		   mapear(95,498,1);
		   mapear(113,498,3);
		   mapear(125,498,1);
		   mapear(143,498,3);
		   mapear(155,498,1);
		   mapear(173,498,3);
	}
	 private void area_61(){
		   mapear(185,498,1);
		   mapear(203,498,3);
		   mapear(215,498,1);
		   mapear(21,499,3);
		   mapear(34,499,3);
		   mapear(51,499,3);
		   mapear(64,499,3);
		   mapear(83,499,3);
		   mapear(95,499,1);
		   mapear(113,499,3);
		   mapear(125,499,1);
		   mapear(143,499,3);
		   mapear(155,499,1);
		   mapear(173,499,3);
		   mapear(185,499,1);
		   mapear(203,499,3);
		   mapear(215,499,1);
		   mapear(21,500,3);
		   mapear(34,500,3);
		   mapear(51,500,3);
		   mapear(64,500,3);
		   mapear(83,500,3);
		   mapear(95,500,1);
		   mapear(113,500,3);
		   mapear(125,500,1);
		   mapear(143,500,3);
		   mapear(155,500,1);
		   mapear(173,500,3);
		   mapear(185,500,1);
		   mapear(203,500,3);
		   mapear(215,500,1);
		   mapear(22,501,3);
		   mapear(33,501,3);
		   mapear(52,501,3);
		   mapear(63,501,3);
		   mapear(83,501,3);
		   mapear(95,501,1);
		   mapear(113,501,3);
		   mapear(125,501,1);
		   mapear(143,501,3);
		   mapear(155,501,1);
		   mapear(173,501,3);
		   mapear(185,501,1);
		   mapear(203,501,3);
		   mapear(215,501,1);
		   mapear(23,502,2);
		   mapear(33,502,2);
		   mapear(53,502,2);
		   mapear(63,502,2);
		   mapear(84,502,3);
	}
	 private void area_62(){
		   mapear(94,502,1);
		   mapear(114,502,3);
		   mapear(124,502,1);
		   mapear(144,502,3);
		   mapear(154,502,1);
		   mapear(174,502,3);
		   mapear(184,502,1);
		   mapear(204,502,3);
		   mapear(214,502,1);
		   mapear(24,503,3);
		   mapear(31,503,3);
		   mapear(54,503,3);
		   mapear(61,503,3);
		   mapear(84,503,4);
		   mapear(93,503,2);
		   mapear(114,503,4);
		   mapear(123,503,2);
		   mapear(144,503,4);
		   mapear(153,503,2);
		   mapear(174,503,4);
		   mapear(183,503,2);
		   mapear(204,503,4);
		   mapear(213,503,2);
		   mapear(26,504,6);
		   mapear(56,504,6);
		   mapear(86,504,7);
		   mapear(116,504,7);
		   mapear(146,504,7);
		   mapear(176,504,7);
		   mapear(206,504,7);
	}
}