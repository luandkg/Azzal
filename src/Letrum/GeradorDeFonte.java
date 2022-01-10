package Letrum;

import LuanDKG.Texto;

import java.util.ArrayList;


public class GeradorDeFonte {

    public void gerar(int[] mPixels, int eLargura, int eAltura, String eNome, String eArquivo) {

        int eTam = mPixels.length;


        String eFonte = "";

        eFonte += "package Letrum;\n";

        eFonte += "import java.util.ArrayList;\n";


        eFonte += "public class " + eNome + " implements  Fonte {\n";
        eFonte += "\n\n";

        eFonte += "\tprivate int mLargura;\n";
        eFonte += "\tprivate int mAltura;\n";

        eFonte += "\n\n";

        eFonte += "\tprivate int[] mPixels;\n";
        eFonte += "\t\tprivate ArrayList<Letra> mLetras;\n";

        eFonte += "\n\n";

        eFonte += "\tpublic " + eNome + "(){\n";
        eFonte += "\n";

        eFonte += "\n\t\tmLargura = " + eLargura + ";\n";
        eFonte += "\n\t\tmAltura = " + eAltura + ";\n";

        eFonte += "\n\t\tmPixels = new int[mLargura*mAltura];\n";
        eFonte += "\n\t\tmLetras = new ArrayList<Letra>();\n";

        eFonte += "\n\t\t carregarLetras ();";

        eFonte += "\n\t\t render ();";

        eFonte += "\t\t\n}";


        eFonte += "\n\tprivate void carregarLetras(){\n";


        eFonte += "\t\t this.adicionarLetra(\"A\", 2, 19);\n";
        eFonte += "\t\t  this.adicionarLetra(\"B\", 20, 36);\n";
        eFonte += "\t\t  this.adicionarLetra(\"C\", 37, 53);\n";
        eFonte += "\t\t  this.adicionarLetra(\"D\", 55, 72);\n";
        eFonte += "\t\t this.adicionarLetra(\"E\", 74, 88);\n";
        eFonte += "\t\t  this.adicionarLetra(\"F\", 90, 104);\n";
        eFonte += "\t\t this.adicionarLetra(\"G\", 106, 122);\n";
        eFonte += "\t\t  this.adicionarLetra(\"H\", 124, 140);\n";
        eFonte += "\t\t this.adicionarLetra(\"I\", 143, 152);\n";
        eFonte += "\t\t this.adicionarLetra(\"J\", 753, 164);\n";

        eFonte += "\t\t this.adicionarLetra(\"K\", 166, 183);\n";
        eFonte += "\t\t this.adicionarLetra(\"L\", 185, 199);\n";
        eFonte += "\t\t this.adicionarLetra(\"M\", 201, 220);\n";
        eFonte += "\t\t this.adicionarLetra(\"N\", 223, 238);\n";
        eFonte += "\t\t  this.adicionarLetra(\"O\", 241, 258);\n";
        eFonte += "\t\t this.adicionarLetra(\"P\", 261, 273);\n";
        eFonte += "\t\t  this.adicionarLetra(\"Q\", 275, 292);\n";


        eFonte += "\t\t  this.adicionarLetra(\"R\", 294, 309);\n";
        eFonte += "\t\t  this.adicionarLetra(\"S\", 311, 324);\n";
        eFonte += "\t\t  this.adicionarLetra(\"T\", 326, 341);\n";
        eFonte += "\t\t  this.adicionarLetra(\"U\", 343, 359);\n";
        eFonte += "\t\t  this.adicionarLetra(\"V\", 361, 378);\n";
        eFonte += "\t\t  this.adicionarLetra(\"W\", 379, 401);\n";

        eFonte += "\t\t  this.adicionarLetra(\"X\", 401, 418);\n";
        eFonte += "\t\t  this.adicionarLetra(\"Y\", 419, 436);\n";
        eFonte += "\t\t  this.adicionarLetra(\"Z\", 437, 451);\n";

        eFonte += "\t\t  this.adicionarLetra(\"a\", 453, 465);\n";
        eFonte += "\t\t  this.adicionarLetra(\"b\", 467, 479);\n";
        eFonte += "\t\t  this.adicionarLetra(\"c\", 481, 492);\n";
        eFonte += "\t\t  this.adicionarLetra(\"d\", 494, 508);\n";
        eFonte += "\t\t  this.adicionarLetra(\"e\", 509, 520);\n";

        eFonte += "\t\t  this.adicionarLetra(\"f\", 523, 533);\n";
        eFonte += "\t\t  this.adicionarLetra(\"g\", 534, 547);\n";
        eFonte += "\t\t  this.adicionarLetra(\"h\", 548, 560);\n";
        eFonte += "\t\t  this.adicionarLetra(\"i\", 563, 571);\n";

        eFonte += "\t\t  this.adicionarLetra(\"j\", 572, 581);\n";
        eFonte += "\t\t  this.adicionarLetra(\"k\", 583, 596);\n";
        eFonte += "\t\t  this.adicionarLetra(\"l\", 597, 605);\n";
        eFonte += "\t\t  this.adicionarLetra(\"m\", 607, 625);\n";
        eFonte += "\t\t  this.adicionarLetra(\"n\", 627, 640);\n";

        eFonte += "\t\t  this.adicionarLetra(\"o\", 641, 654);\n";
        eFonte += "\t\t   this.adicionarLetra(\"p\", 656, 669);\n";
        eFonte += "\t\t   this.adicionarLetra(\"q\", 670, 682);\n";
        eFonte += "\t\t   this.adicionarLetra(\"r\", 685, 695);\n";
        eFonte += "\t\t   this.adicionarLetra(\"s\", 696, 706);\n";
        eFonte += "\t\t  this.adicionarLetra(\"t\", 708, 717);\n";

        eFonte += "\t\t   this.adicionarLetra(\"u\", 718, 730);\n";
        eFonte += "\t\t   this.adicionarLetra(\"v\", 733, 746);\n";
        eFonte += "\t\t   this.adicionarLetra(\"w\", 747, 764);\n";
        eFonte += "\t\t   this.adicionarLetra(\"x\", 765, 779);\n";
        eFonte += "\t\t   this.adicionarLetra(\"y\", 780, 793);\n";
        eFonte += "\t\t   this.adicionarLetra(\"z\", 794, 807);\n";


        eFonte += "\t\t    this.adicionarLetra(\"0\", 807, 821);\n";
        eFonte += "\t\t   this.adicionarLetra(\"1\", 822, 834);\n";
        eFonte += "\t\t   this.adicionarLetra(\"2\", 836, 850);\n";
        eFonte += "\t\t   this.adicionarLetra(\"3\", 851, 863);\n";
        eFonte += "\t\t    this.adicionarLetra(\"4\", 864, 878);\n";
        eFonte += "\t\t    this.adicionarLetra(\"5\", 880, 892);\n";
        eFonte += "\t\t    this.adicionarLetra(\"6\", 894, 907);\n";
        eFonte += "\t\t    this.adicionarLetra(\"7\", 909, 922);\n";
        eFonte += "\t\t this.adicionarLetra(\"8\", 923, 935);\n";
        eFonte += "\t\t  this.adicionarLetra(\"9\", 936, 949);\n";


        eFonte += "\t\t  this.adicionarLetra(\".\", 1066, 1072);\n";

        eFonte += "\t\t  this.adicionarLetra(\":\", 1075, 1082);\n";
        eFonte += "\t\t  this.adicionarLetra(\";\", 1084, 1093);\n";

        eFonte += "\t\t  this.adicionarLetra(\"=\", 1094, 1109);\n";

        eFonte += "\t\t   this.adicionarLetra(\"+\", 1147, 1160);\n";
        eFonte += "\t\t   this.adicionarLetra(\"-\", 1135, 1149);\n";

        eFonte += "\t\t  this.adicionarLetra(\"(\", 952, 962);\n";
        eFonte += "\t\t  this.adicionarLetra(\")\", 962, 972);\n";
        eFonte += "\t\t  this.adicionarLetra(\"{\", 974, 987);\n";
        eFonte += "\t\t   this.adicionarLetra(\"}\", 987, 999);\n";
        eFonte += "\t\t  this.adicionarLetra(\"[\", 1002, 1012);\n";
        eFonte += "\t\t  this.adicionarLetra(\"]\", 1012, 1022);\n";
        eFonte += "\t\t  this.adicionarLetra(\"<\", 1032, 1049);\n";
        eFonte += "\t\t   this.adicionarLetra(\">\", 1049, 1064);\n";

        eFonte += "\t\t\n}";





        eFonte += "\n\tprivate void render(){\n";


        int alglomerando = 0;
        int n = 0;

        for (int i = 0; i < eTam; i++) {

            // eFonte += "\t\t\tmPixels[" + i + "] = " + mPixels[i] + ";\n";

            if (alglomerando == 100) {

                eFonte += "\n\t\t render_" + n + "();";
                n += 1;
                alglomerando = 0;
            }

            alglomerando += 1;
        }

        eFonte += "\n\t\t render_" + n + "();";

        eFonte += "\t\t\n}";


        eFonte += "\t\npublic int getLargura(){\n";
        eFonte += "\n\t\t return mLargura;";
        eFonte += "\n\t\n}";

        eFonte += "\n\tpublic int getAltura(){\n";
        eFonte += "\n\t\t return mAltura;";
        eFonte += "\n\t\n}";

        eFonte += "\n\t\npublic int[] getPixels(){return mPixels;}\n";

        eFonte += "\t\npublic void adicionarLetra(String eLetra, int eX1, int eX2) {\n";
        eFonte += "\t\nmLetras.add(new Letra(eLetra, eX1, eX2));\n";
        eFonte += "\t\n }\n";

        eFonte += "\t\n public ArrayList<Letra> getLetras(){return mLetras;}\n";


        alglomerando = 0;
        n = 0;


        String mJuntando = "";
        int mJuntador = 0;
        int ePrimeiro = 0;

        ArrayList<Integer> mNumeros = new ArrayList<>();

        eFonte += "\n\tprivate void render_0 (){\n";

        for (int i = 0; i < eTam; i++) {

            if (mNumeros.size() < 10) {
                mNumeros.add(mPixels[i]);
            } else {

                int n1 = mNumeros.get(0);
                int n2 = mNumeros.get(1);
                int n3 = mNumeros.get(2);
                int n4 = mNumeros.get(3);
                int n5 = mNumeros.get(4);
                int n6 = mNumeros.get(5);
                int n7 = mNumeros.get(6);
                int n8 = mNumeros.get(7);
                int n9 = mNumeros.get(8);
                int n10 = mNumeros.get(9);

                eFonte += "\n\t\t renderGrupo(" + ePrimeiro + "," + n1 + "," + n2 + "," + n3 + "," + n4 + "," + n5 + "," + n6 + "," + n7 + "," + n8 + "," + n9 + "," + n10 + ");";
                mNumeros.clear();

                ePrimeiro = i;
                mNumeros.add(mPixels[i]);


            }

            if (alglomerando == 100) {


                n += 1;
                alglomerando = 0;

                eFonte += "\n\t }";
                eFonte += "\n\tprivate void render_" + n + " (){\n";

                //  eFonte += "\t\t\tmPixels[" + i + "] = " + mPixels[i] + ";\n";

            } else {

                //   eFonte += "\t\t\tmPixels[" + i + "] = " + mPixels[i] + ";\n";

            }

            alglomerando += 1;
        }

        eFonte += "\n";

        for (int num : mNumeros) {

            eFonte += "\t\t\tmPixels[" + ePrimeiro + "] = " + num + ";\n";

        }

        eFonte += "\n\t }";

        eFonte += "\n\tprivate void renderGrupo(int ePos,int a,int b,int c,int d,int e,int f,int g,int h,int i, int j){\n";

        eFonte += "\n\t   mPixels[ePos+0] = a;\n";
        eFonte += "\n\t   mPixels[ePos+1] = b;\n";
        eFonte += "\n\t   mPixels[ePos+2] = c;\n";
        eFonte += "\n\t    mPixels[ePos+3] = d;\n";
        eFonte += "\n\t    mPixels[ePos+4] = e;\n";
        eFonte += "\n\t    mPixels[ePos+5] = f;\n";
        eFonte += "\n\t    mPixels[ePos+6] = g;\n";
        eFonte += "\n\t    mPixels[ePos+7] = h;\n";
        eFonte += "\n\t    mPixels[ePos+8] = i;\n";
        eFonte += "\n\t    mPixels[ePos+9] = j;\n";

        eFonte += "\n\t }";


        eFonte += "\n}";

        Texto.Escrever(eArquivo, eFonte);


    }

}
