package LetrumArkaica;

import java.util.ArrayList;

public class Fonte12Verde implements Fonte {


    private int mLargura;
    private int mAltura;


    private int[] mPixels;
    private ArrayList<Letra> mLetras;


    public Fonte12Verde() {


        mLargura = 1279;

        mAltura = 18;

        mPixels = new int[mLargura * mAltura];

        mLetras = new ArrayList<Letra>();

        carregarLetras();

        render();
    }


    public String getNome() {
        return "Fonte 12 - Verde";
    }

    private void carregarLetras() {
        this.adicionarLetra("A", 2, 19);
        this.adicionarLetra("B", 20, 36);
        this.adicionarLetra("C", 37, 53);
        this.adicionarLetra("D", 55, 72);
        this.adicionarLetra("E", 74, 88);
        this.adicionarLetra("F", 90, 104);
        this.adicionarLetra("G", 106, 122);
        this.adicionarLetra("H", 124, 140);
        this.adicionarLetra("I", 143, 152);
        this.adicionarLetra("J", 153, 164);
        this.adicionarLetra("K", 166, 183);
        this.adicionarLetra("L", 185, 199);
        this.adicionarLetra("M", 201, 220);
        this.adicionarLetra("N", 223, 238);
        this.adicionarLetra("O", 241, 258);
        this.adicionarLetra("P", 261, 273);
        this.adicionarLetra("Q", 275, 292);
        this.adicionarLetra("R", 294, 309);
        this.adicionarLetra("S", 311, 324);
        this.adicionarLetra("T", 326, 341);
        this.adicionarLetra("U", 343, 359);
        this.adicionarLetra("V", 361, 378);
        this.adicionarLetra("W", 379, 401);
        this.adicionarLetra("X", 401, 418);
        this.adicionarLetra("Y", 419, 436);
        this.adicionarLetra("Z", 437, 451);
        this.adicionarLetra("a", 453, 465);
        this.adicionarLetra("b", 467, 479);
        this.adicionarLetra("c", 481, 492);
        this.adicionarLetra("d", 494, 508);
        this.adicionarLetra("e", 509, 520);
        this.adicionarLetra("f", 523, 533);
        this.adicionarLetra("g", 534, 547);
        this.adicionarLetra("h", 548, 560);
        this.adicionarLetra("i", 563, 571);
        this.adicionarLetra("j", 572, 581);
        this.adicionarLetra("k", 583, 596);
        this.adicionarLetra("l", 597, 605);
        this.adicionarLetra("m", 607, 625);
        this.adicionarLetra("n", 627, 640);
        this.adicionarLetra("o", 641, 654);
        this.adicionarLetra("p", 656, 669);
        this.adicionarLetra("q", 670, 682);
        this.adicionarLetra("r", 685, 695);
        this.adicionarLetra("s", 696, 706);
        this.adicionarLetra("t", 708, 717);
        this.adicionarLetra("u", 718, 730);
        this.adicionarLetra("v", 733, 746);
        this.adicionarLetra("w", 747, 764);
        this.adicionarLetra("x", 765, 779);
        this.adicionarLetra("y", 780, 793);
        this.adicionarLetra("z", 794, 807);
        this.adicionarLetra("0", 807, 821);
        this.adicionarLetra("1", 822, 834);
        this.adicionarLetra("2", 836, 850);
        this.adicionarLetra("3", 851, 863);
        this.adicionarLetra("4", 864, 878);
        this.adicionarLetra("5", 880, 892);
        this.adicionarLetra("6", 894, 907);
        this.adicionarLetra("7", 909, 922);
        this.adicionarLetra("8", 923, 935);
        this.adicionarLetra("9", 936, 949);
        this.adicionarLetra(".", 1066, 1072);
        this.adicionarLetra(":", 1075, 1082);
        this.adicionarLetra(";", 1084, 1093);
        this.adicionarLetra("=", 1094, 1109);
        this.adicionarLetra("+", 1147, 1160);
        this.adicionarLetra("-", 1139, 1146);
        this.adicionarLetra("*", 1121, 1133);
        this.adicionarLetra("/", 1110, 1120);
        this.adicionarLetra("(", 952, 962);
        this.adicionarLetra(")", 962, 972);
        this.adicionarLetra("{", 974, 987);
        this.adicionarLetra("}", 987, 999);
        this.adicionarLetra("[", 1002, 1012);
        this.adicionarLetra("]", 1012, 1022);
        this.adicionarLetra("<", 1032, 1049);
        this.adicionarLetra(">", 1049, 1064);

    }

    private void render() {

        render_0();
        render_1();
        render_2();
        render_3();
        render_4();
        render_5();
        render_6();
        render_7();
        render_8();
        render_9();
        render_10();
        render_11();
        render_12();
        render_13();
        render_14();
        render_15();
        render_16();
        render_17();
        render_18();
        render_19();
        render_20();
        render_21();
        render_22();
        render_23();
        render_24();
        render_25();
        render_26();
        render_27();
        render_28();
        render_29();
        render_30();
        render_31();
        render_32();
        render_33();
        render_34();
        render_35();
        render_36();
        render_37();
        render_38();
        render_39();
        render_40();
        render_41();
        render_42();
        render_43();
        render_44();
        render_45();
        render_46();
        render_47();
        render_48();
        render_49();
        render_50();
        render_51();
        render_52();
        render_53();
        render_54();
        render_55();
        render_56();
        render_57();
        render_58();
        render_59();
        render_60();
        render_61();
        render_62();
        render_63();
        render_64();
        render_65();
        render_66();
        render_67();
        render_68();
        render_69();
        render_70();
        render_71();
        render_72();
        render_73();
        render_74();
        render_75();
        render_76();
        render_77();
        render_78();
        render_79();
        render_80();
        render_81();
        render_82();
        render_83();
        render_84();
        render_85();
        render_86();
        render_87();
        render_88();
        render_89();
        render_90();
        render_91();
        render_92();
        render_93();
        render_94();
        render_95();
        render_96();
        render_97();
        render_98();
        render_99();
        render_100();
        render_101();
        render_102();
        render_103();
        render_104();
        render_105();
        render_106();
        render_107();
        render_108();
        render_109();
        render_110();
        render_111();
        render_112();
        render_113();
        render_114();
        render_115();
        render_116();
        render_117();
        render_118();
        render_119();
        render_120();
        render_121();
        render_122();
        render_123();
        render_124();
        render_125();
        render_126();
        render_127();
        render_128();
        render_129();
        render_130();
        render_131();
        render_132();
        render_133();
        render_134();
        render_135();
        render_136();
        render_137();
        render_138();
        render_139();
        render_140();
        render_141();
        render_142();
        render_143();
        render_144();
        render_145();
        render_146();
        render_147();
        render_148();
        render_149();
        render_150();
        render_151();
        render_152();
        render_153();
        render_154();
        render_155();
        render_156();
        render_157();
        render_158();
        render_159();
        render_160();
        render_161();
        render_162();
        render_163();
        render_164();
        render_165();
        render_166();
        render_167();
        render_168();
        render_169();
        render_170();
        render_171();
        render_172();
        render_173();
        render_174();
        render_175();
        render_176();
        render_177();
        render_178();
        render_179();
        render_180();
        render_181();
        render_182();
        render_183();
        render_184();
        render_185();
        render_186();
        render_187();
        render_188();
        render_189();
        render_190();
        render_191();
        render_192();
        render_193();
        render_194();
        render_195();
        render_196();
        render_197();
        render_198();
        render_199();
        render_200();
        render_201();
        render_202();
        render_203();
        render_204();
        render_205();
        render_206();
        render_207();
        render_208();
        render_209();
        render_210();
        render_211();
        render_212();
        render_213();
        render_214();
        render_215();
        render_216();
        render_217();
        render_218();
        render_219();
        render_220();
        render_221();
        render_222();
        render_223();
        render_224();
        render_225();
        render_226();
        render_227();
        render_228();
        render_229();
        render_230();
    }

    public int getLargura() {

        return mLargura;

    }

    public int getAltura() {

        return mAltura;

    }

    public int[] getPixels() {
        return mPixels;
    }

    public void adicionarLetra(String eLetra, int eX1, int eX2) {

        mLetras.add(new Letra(eLetra, eX1, eX2));

    }

    public ArrayList<Letra> getLetras() {
        return mLetras;
    }

    public void render_0() {

        renderGrupo(0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(50, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(70, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(80, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(90, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_1() {

        renderGrupo(100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_2() {

        renderGrupo(200, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(260, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(270, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_3() {

        renderGrupo(300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(360, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_4() {

        renderGrupo(400, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(420, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(430, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_5() {

        renderGrupo(500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(510, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(520, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_6() {

        renderGrupo(600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(690, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_7() {

        renderGrupo(700, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(710, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(740, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_8() {

        renderGrupo(800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(830, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_9() {

        renderGrupo(900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(920, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(990, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_10() {

        renderGrupo(1000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1020, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1030, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1040, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1080, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_11() {

        renderGrupo(1100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_12() {

        renderGrupo(1200, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1260, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1270, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_13() {

        renderGrupo(1300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1360, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_14() {

        renderGrupo(1400, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1420, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1430, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_15() {

        renderGrupo(1500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1510, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1520, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_16() {

        renderGrupo(1600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1690, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_17() {

        renderGrupo(1700, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1710, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1740, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_18() {

        renderGrupo(1800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1830, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_19() {

        renderGrupo(1900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1920, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(1990, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_20() {

        renderGrupo(2000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2020, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2030, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2040, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2080, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_21() {

        renderGrupo(2100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_22() {

        renderGrupo(2200, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2260, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2270, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_23() {

        renderGrupo(2300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2360, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_24() {

        renderGrupo(2400, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2420, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2430, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_25() {

        renderGrupo(2500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2510, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2520, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_26() {

        renderGrupo(2600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2690, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_27() {

        renderGrupo(2700, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2710, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2740, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_28() {

        renderGrupo(2800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2830, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_29() {

        renderGrupo(2900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2920, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(2990, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_30() {

        renderGrupo(3000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3020, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3030, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3040, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3080, -1, -1, -1, -12, -3024505, -11357090, -13128353, -8329477, -1, -1);
        renderGrupo(3090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_31() {

        renderGrupo(3100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_32() {

        renderGrupo(3200, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3260, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3270, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_33() {

        renderGrupo(3300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3360, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_34() {

        renderGrupo(3400, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3420, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3430, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_35() {

        renderGrupo(3500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3510, -1, -1, -1, -1, -1, -1, -591115, -196609, -1, -1);
        renderGrupo(3520, -1, -1, -197900, -589825, -1, -1, -1, -1, -1, -1);
        renderGrupo(3530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_36() {

        renderGrupo(3600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3690, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_37() {

        renderGrupo(3700, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3710, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3740, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_38() {

        renderGrupo(3800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3830, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(3840, -1, -1, -1, -1, -2, -1188750, -15295953, -15879517, -2097409, -1);
        renderGrupo(3850, -1, -1, -1, -1, -1, -1, -2, -1120346, -9388443, -13983953);
        renderGrupo(3860, -16278468, -14308527, -13587632, -13785017, -14834615, -13191811, -8003616, -655361, -1, -1);
        renderGrupo(3870, -1, -1, -1, -1, -1, -1, -1, -2, -921923, -7551889);
        renderGrupo(3880, -12604075, -12930720, -12209314, -12866224, -13784228, -11945088, -6952459, -1, -1, -1);
        renderGrupo(3890, -1, -1, -2, -1120346, -9388443, -13983953, -16278468, -14308527, -13587635, -14179007);
    }

    public void render_39() {

        renderGrupo(3900, -15097273, -13585808, -9646152, -3147779, -1, -1, -1, -1, -1, -1);
        renderGrupo(3910, -2, -1120346, -9388443, -13983953, -16278468, -14308527, -13587631, -13653425, -13981882, -14966732);
        renderGrupo(3920, -16012914, -3604993, -1, -1, -1, -1, -1, -2, -1120346, -9388443);
        renderGrupo(3930, -13983953, -16278468, -14308527, -13587631, -13653425, -13916089, -14835403, -15947378, -3604993, -1);
        renderGrupo(3940, -1, -1, -1, -1, -1, -2, -987460, -7683218, -12604075, -12996255);
        renderGrupo(3950, -12208794, -12209835, -13653157, -11879293, -8003355, -131073, -1, -1, -1, -1);
        renderGrupo(3960, -1, -2, -1251939, -10241701, -14442961, -16278462, -13191303, -8396569, -131073, -20);
        renderGrupo(3970, -4469125, -11423931, -15820241, -15882919, -11550825, -4721155, -1, -1, -1, -1);
        renderGrupo(3980, -1, -266573, -9257366, -13458896, -16344258, -13518984, -9381414, -262145, -1, -1);
        renderGrupo(3990, -1, -1, -1, -17, -4074877, -10767542, -15689169, -15817121, -10697296, -2884097);
    }

    public void render_40() {

        renderGrupo(4000, -1, -1, -1, -2, -1186143, -9847968, -14246353, -16278460, -12862850, -8134168);
        renderGrupo(4010, -131073, -1, -332628, -9980853, -15688642, -13453703, -8462106, -131073, -1, -1);
        renderGrupo(4020, -1, -1, -2, -1186143, -9847968, -14246353, -16278460, -12928905, -9777470, -1704193);
        renderGrupo(4030, -1, -1, -1, -1, -1, -1, -1, -1, -2, -1186143);
        renderGrupo(4040, -9847968, -14246353, -16344527, -13513770, -262145, -1, -1, -1, -1, -1);
        renderGrupo(4050, -3, -2305441, -15754706, -16211631, -11813498, -6296325, -1, -1, -1, -2);
        renderGrupo(4060, -1186143, -9847968, -14246353, -16344528, -14565443, -1376257, -1, -1, -290, -5715845);
        renderGrupo(4070, -11292861, -15883435, -11485556, -5705475, -1, -1, -1, -1, -1, -1);
        renderGrupo(4080, -1, -8, -2038618, -9192608, -13193894, -12471454, -12275109, -13128353, -11090267, -4394248);
        renderGrupo(4090, -1, -1, -1, -1, -1, -1, -1, -2, -1120346, -9388443);
    }

    public void render_41() {

        renderGrupo(4100, -13983953, -16278468, -14308528, -13785017, -14703023, -12206444, -5640460, -65537, -1, -1);
        renderGrupo(4110, -1, -1, -1, -1, -1, -8, -2038618, -9192608, -13193894, -12471454);
        renderGrupo(4120, -12275109, -13128096, -11024730, -4328456, -1, -1, -1, -1, -1, -1);
        renderGrupo(4130, -2, -1120346, -9388443, -13983953, -16278468, -14308527, -13587633, -14113470, -14636707, -11155541);
        renderGrupo(4140, -3606531, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4150, -3, -1316437, -9193124, -13390503, -12799909, -13129141, -13980318, -11088181, -917505, -1);
        renderGrupo(4160, -1, -1, -1, -7, -5259977, -16015551, -14309039, -13390505, -13128633, -15492306);
        renderGrupo(4170, -16211638, -13193640, -13062568, -13062572, -14245570, -10296839, -1, -1, -1, -3);
        renderGrupo(4180, -2236782, -10044839, -14836946, -16211888, -11813241, -6493192, -1, -1, -285, -5256321);
        renderGrupo(4190, -10964665, -15752620, -11550838, -5968132, -1, -1, -1, -15, -4009342, -11162047);
    }

    public void render_42() {

        renderGrupo(4200, -16147921, -16013988, -10894698, -5049603, -1, -1, -3, -1711462, -9847716, -14574275);
        renderGrupo(4210, -13518978, -7871766, -131073, -1, -1, -549, -6175897, -13983953, -16278461, -12928389);
        renderGrupo(4220, -9382449, -983041, -1, -1, -3, -722184, -65537, -1, -1, -134207);
        renderGrupo(4230, -7945095, -11292862, -16015021, -11286083, -1966337, -1, -1, -1, -1, -549);
        renderGrupo(4240, -6175630, -12605898, -16278993, -16014246, -11091298, -4261890, -3, -1645927, -10176427, -15033287);
        renderGrupo(4250, -13978506, -8658972, -131073, -1, -1, -1, -16, -4075139, -11555520, -16147922);
        renderGrupo(4260, -16277942, -12338046, -7412241, -65537, -1, -19, -4337537, -11096253, -15950005, -12338032);
        renderGrupo(4270, -5311747, -1, -1, -1, -1, -4, -3224751, -15950531, -14636977, -13521578);
        renderGrupo(4280, -13128104, -13062568, -13063357, -15689170, -16211072, -4522497, -1, -1, -1, -1);
        renderGrupo(4290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_43() {

        renderGrupo(4300, -1, -1, -1, -1, -286, -4861816, -12016336, -16011621, -2621697, -1);
        renderGrupo(4310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4330, -1, -1, -1, -1, -1, -1, -15, -3351910, -9062581, -16081840);
        renderGrupo(4340, -8524804, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4360, -1, -2, -1121922, -14308726, -3933187, -1780115, -10166535, -1, -1, -1);
        renderGrupo(4370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4380, -1, -1, -1, -1, -1, -15, -3417450, -10244291, -16212626, -5899777);
        renderGrupo(4390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_44() {

        renderGrupo(4400, -2, -1121917, -13917876, -9903889, -65537, -1, -1, -1, -1, -1);
        renderGrupo(4410, -1, -267107, -12541118, -11807264, -196609, -1, -1, -1, -1, -3);
        renderGrupo(4420, -1973339, -8471987, -16081840, -8524804, -1, -1, -1, -1, -1, -1);
        renderGrupo(4430, -1, -1, -1, -1, -3, -2039134, -8667300, -14968012, -12003605, -65537);
        renderGrupo(4440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_45() {

        renderGrupo(4500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4510, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4520, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_46() {

        renderGrupo(4600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4640, -1, -1, -1, -1, -1, -1, -1, -3, -1316701, -10177703);
        renderGrupo(4650, -12864929, -13063080, -11482694, -2360321, -1, -1, -1, -1, -1, -1);
        renderGrupo(4660, -1, -1, -1, -1055, -5125012, -13847908, -2621697, -1, -1, -1);
        renderGrupo(4670, -1, -1, -1, -1, -14, -4338580, -12735668, -14046379, -12996781, -13849762);
        renderGrupo(4680, -10892615, -2622465, -1, -1, -1, -1, -1, -1, -1, -8);
        renderGrupo(4690, -3879061, -12998328, -14177707, -12996780, -13784487, -11417938, -3278594, -1, -1, -1);
    }

    public void render_47() {

        renderGrupo(4700, -1, -1, -1, -1, -1, -1, -1, -1, -1330, -8934602);
        renderGrupo(4710, -13579306, -262145, -1, -1, -1, -1, -1, -1, -3, -2699689);
        renderGrupo(4720, -16016850, -16344530, -16344530, -16344530, -16344530, -16011880, -2883841, -1, -1, -1);
        renderGrupo(4730, -1, -1, -1, -1, -538, -4337282, -12210343, -12734375, -13457336, -13848450);
        renderGrupo(4740, -5901571, -1, -1, -1, -1, -1, -279, -6835149, -16344530, -16344530);
        renderGrupo(4750, -16344530, -16344530, -16344530, -16344530, -16211070, -4391425, -1, -1, -1, -1);
        renderGrupo(4760, -1, -1, -15, -3418493, -11948716, -12996000, -12800943, -12731241, -4787462, -1);
        renderGrupo(4770, -1, -1, -1, -1, -1, -1, -798, -5059467, -12604074, -12536734);
        renderGrupo(4780, -12931751, -11417417, -2622465, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4790, -1, -1, -1, -538, -4731791, -11810102, -720897, -1, -1, -1);
    }

    public void render_48() {

        renderGrupo(4800, -1, -793199, -11748456, -4459269, -1, -1, -1, -1, -1, -1);
        renderGrupo(4810, -1, -1, -1, -1, -1, -11, -3353740, -13193878, -9384285, -5116168);
        renderGrupo(4820, -1, -1, -1, -1, -1, -1, -1, -134201, -6696817, -10899373);
        renderGrupo(4830, -11875386, -1441793, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4840, -6, -4340926, -16147391, -13519763, -10632565, -7872287, -196609, -1, -1, -1);
        renderGrupo(4850, -1, -200515, -7944319, -10635166, -13524173, -16145276, -4194817, -1, -1, -1);
        renderGrupo(4860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_49() {

        renderGrupo(4900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4920, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4950, -1, -1, -287, -7490718, -7212291, -1, -1, -1, -1, -1);
        renderGrupo(4960, -1, -1, -561, -9132235, -12200729, -131073, -1, -1, -1, -1);
        renderGrupo(4970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(4990, -1, -1, -1, -1, -1, -1, -1, -1, -1, -551);
    }

    public void render_50() {

        renderGrupo(5000, -8146838, -6621955, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5010, -1, -1, -1, -1, -1, -1, -294, -8082126, -16343975, -7671811);
        renderGrupo(5020, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5030, -1, -12, -2563674, -8929689, -12471970, -12471714, -12668839, -13193896, -12206965, -6954270);
        renderGrupo(5040, -720897, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5050, -1, -5, -3815601, -11872534, -65537, -17, -6178742, -9574918, -1, -1);
        renderGrupo(5060, -1, -1, -1, -2, -1317218, -10768037, -12208022, -12472739, -10759987, -1310721);
        renderGrupo(5070, -1, -1, -1, -7, -3027104, -12924723, -851969, -1, -1, -1);
        renderGrupo(5080, -1, -1, -1, -1, -1, -1, -12, -3090041, -11751853, -13061791);
        renderGrupo(5090, -12604080, -13256824, -5968138, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_51() {

        renderGrupo(5100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5120, -1, -1, -1, -17, -5850567, -16278994, -16278437, -7540483, -1, -1);
        renderGrupo(5130, -1, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(5140, -4456961, -1, -1, -395827, -7752385, -16213182, -10887957, -65537, -1, -1);
        renderGrupo(5150, -1, -1, -1, -1, -1, -7, -2631564, -14180802, -12597062, -2622979);
        renderGrupo(5160, -1, -1, -2, -658228, -7030196, -11806997, -65537, -1, -1, -1);
        renderGrupo(5170, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -2, -526632);
        renderGrupo(5180, -5124235, -13656272, -16080029, -8001551, -65537, -1, -1, -1, -1, -1);
        renderGrupo(5190, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -833, -10178673);
    }

    public void render_52() {

        renderGrupo(5200, -3604993, -1, -1, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(5210, -16211071, -4456961, -1, -1, -1, -831, -10047601, -3604993, -1, -1);
        renderGrupo(5220, -1, -1, -1, -8, -2828688, -14312132, -12794184, -2819587, -1, -1);
        renderGrupo(5230, -2, -592690, -6569901, -14236729, -393217, -1, -1, -1, -1, -1);
        renderGrupo(5240, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1);
        renderGrupo(5250, -333685, -14116048, -14301490, -327681, -1, -1, -1, -1, -1, -1);
        renderGrupo(5260, -1, -17, -6310092, -16343704, -6424834, -1, -1, -1, -1, -1);
        renderGrupo(5270, -1, -1, -1, -1, -267633, -13919184, -14498357, -327681, -1, -1);
        renderGrupo(5280, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
        renderGrupo(5290, -1, -67119, -8015024, -11414052, -589825, -1, -1, -1, -1, -1);
    }

    public void render_53() {

        renderGrupo(5300, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1);
        renderGrupo(5310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(5320, -8607182, -16213201, -16144764, -4260865, -1, -1, -1, -1, -1, -817);
        renderGrupo(5330, -9000654, -16213458, -15878485, -1441793, -1, -1, -1, -1, -1, -1);
        renderGrupo(5340, -556, -8606905, -13850311, -16213179, -10362899, -65537, -1, -1, -1, -400770);
        renderGrupo(5350, -14566731, -786433, -1, -1, -1, -1, -1, -1, -1, -18);
        renderGrupo(5360, -4405159, -15360433, -10364713, -1179905, -1, -1, -132645, -5914029, -15556519, -8985619);
        renderGrupo(5370, -65537, -1, -1, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(5380, -16211071, -4456961, -1, -592956, -8670918, -16146336, -7410182, -1, -1, -1);
        renderGrupo(5390, -1, -1, -1, -18, -4339623, -15294899, -10496043, -1179905, -1, -1);
    }

    public void render_54() {

        renderGrupo(5400, -132647, -6045358, -15556519, -8919826, -65537, -1, -1, -1, -1, -1);
        renderGrupo(5410, -1, -556, -8607183, -16211071, -4456961, -1, -7, -1710698, -12279504, -15749743);
        renderGrupo(5420, -3671041, -1, -1, -1, -1, -1, -1, -1, -1, -661358);
        renderGrupo(5430, -13263033, -10823203, -786433, -1, -11, -2499977, -13977685, -1441793, -1, -1);
        renderGrupo(5440, -1, -1, -7, -5259682, -7934211, -1, -1, -133978, -12215250, -15811915);
        renderGrupo(5450, -786433, -1, -4, -3290274, -10034695, -1, -1, -1, -1, -1);
        renderGrupo(5460, -838, -10904274, -15945310, -2162945, -1, -1, -1, -1, -1, -399995);
        renderGrupo(5470, -14174549, -1441793, -1, -1, -1, -1, -1, -1, -134231, -12083665);
        renderGrupo(5480, -16078451, -3670529, -1, -1, -1, -1, -1, -398961, -13586794, -3015169);
        renderGrupo(5490, -1, -1, -1, -1, -1, -12, -5062849, -16212912, -8459268, -1);
    }

    public void render_55() {

        renderGrupo(5500, -1, -1, -1, -1603, -10309493, -3867393, -1, -1, -1, -4);
        renderGrupo(5510, -3224749, -13973811, -393217, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5520, -332130, -12607697, -16211345, -6032131, -1, -1, -8, -3289765, -14041929, -1638657);
        renderGrupo(5530, -1, -1, -1, -1, -1, -1, -1, -67390, -9984464, -16278451);
        renderGrupo(5540, -9181451, -1, -1, -1, -1, -17, -5259701, -12529443, -262145, -1);
        renderGrupo(5550, -1, -1, -1, -1, -4, -3224742, -10691082, -1, -1, -1);
        renderGrupo(5560, -9, -3749551, -15951308, -13382957, -589825, -1, -1, -1, -1, -1);
        renderGrupo(5570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5580, -1, -1, -1, -1, -20, -6572492, -16011621, -2621697, -1, -1);
        renderGrupo(5590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_56() {

        renderGrupo(5600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5610, -1, -1, -1, -1, -1, -1, -1, -664206, -15819696, -8524804);
        renderGrupo(5620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5640, -15, -5785031, -15746120, -655361, -66844, -2164995, -1, -1, -1, -1);
        renderGrupo(5650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5660, -1, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1);
        renderGrupo(5670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2);
        renderGrupo(5680, -1253248, -14180280, -10166290, -65537, -1, -1, -1, -1, -1, -1);
        renderGrupo(5690, -267109, -12803777, -12069922, -196609, -1, -1, -1, -1, -1, -1);
    }

    public void render_57() {

        renderGrupo(5700, -664206, -15819696, -8524804, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5710, -1, -1, -1, -1, -1, -200803, -12936140, -12003605, -65537, -1);
        renderGrupo(5720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5740, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_58() {

        renderGrupo(5800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5830, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_59() {

        renderGrupo(5900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(5920, -1, -1, -1, -1, -1, -1, -332129, -12476085, -10034963, -131073);
        renderGrupo(5930, -67380, -8934346, -14237248, -1376257, -1, -1, -1, -1, -1, -1);
        renderGrupo(5940, -11, -2564463, -11555765, -14770128, -16011621, -2621697, -1, -1, -1, -1);
        renderGrupo(5950, -1, -1, -1, -279, -6834600, -8919054, -131073, -1, -725598, -12017359);
        renderGrupo(5960, -14828618, -1638657, -1, -1, -1, -1, -1, -1, -14, -6047152);
        renderGrupo(5970, -9706770, -262145, -1, -528213, -11427280, -15157332, -2031873, -1, -1, -1);
        renderGrupo(5980, -1, -1, -1, -1, -1, -1, -12, -4012208, -15951311, -13579306);
        renderGrupo(5990, -262145, -1, -1, -1, -1, -1, -1, -3, -2699685, -13123423);
    }

    public void render_60() {

        renderGrupo(6000, -6892378, -6892378, -6892378, -6892378, -6824492, -1245185, -1, -1, -1, -1);
        renderGrupo(6010, -1, -1, -284, -6047163, -15224428, -3934211, -1, -534, -6440611, -8196868);
        renderGrupo(6020, -1, -1, -1, -1, -1, -279, -6834867, -11219802, -6892378, -6892378);
        renderGrupo(6030, -6892378, -6892379, -8472504, -14302266, -786433, -1, -1, -1, -1, -1);
        renderGrupo(6040, -3, -1910676, -15098800, -9181454, -65537, -802, -7162822, -15881330, -3802113, -1);
        renderGrupo(6050, -1, -1, -1, -1, -21, -5390778, -15948427, -5770245, -1, -133182);
        renderGrupo(6060, -9787341, -14565701, -1441793, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6070, -1, -1322, -7227569, -12333617, -1048577, -1, -1, -1, -1, -1);
        renderGrupo(6080, -2, -1054571, -12342419, -6885897, -1, -1, -1, -1, -1, -1);
        renderGrupo(6090, -1, -1, -1, -1, -134492, -12411594, -11609618, -65537, -1, -1);
    }

    public void render_61() {

        renderGrupo(6100, -1, -1, -1, -1, -1, -1, -1, -1, -531325, -14574518);
        renderGrupo(6110, -9115397, -1, -1, -1, -1, -1, -1, -1, -1, -6);
        renderGrupo(6120, -4340926, -16013429, -3801601, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6130, -1, -1, -5, -3881400, -16014204, -4194817, -1, -1, -1, -1);
        renderGrupo(6140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_62() {

        renderGrupo(6200, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6230, -1, -200546, -12405598, -2162945, -1, -1, -1, -1, -10, -3877480);
        renderGrupo(6240, -5837324, -65817, -6966461, -10034439, -531, -3680350, -5114883, -1, -1, -1);
        renderGrupo(6250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6260, -1, -1, -1, -1, -1, -1, -1, -3, -393731, -65537);
        renderGrupo(6270, -1, -1, -1, -1, -1, -1, -1, -1, -3, -2962084);
        renderGrupo(6280, -11741464, -131073, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6290, -1, -1, -1, -1, -1, -282, -6966477, -16343706, -6687234, -1);
    }

    public void render_63() {

        renderGrupo(6300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -725066);
        renderGrupo(6310, -9457070, -12533086, -4854033, -327681, -1, -1, -268, -2300763, -10179262, -13779280);
        renderGrupo(6320, -2556673, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6330, -20, -6506675, -9312517, -1, -561, -9131417, -6752770, -1, -1, -1);
        renderGrupo(6340, -1, -1, -67138, -10312647, -11872280, -131073, -265821, -12345790, -10362638, -65537);
        renderGrupo(6350, -1, -1, -529255, -12472433, -3802369, -1, -1, -1, -1, -1);
        renderGrupo(6360, -1, -1, -1, -1, -2, -990850, -14640061, -10494227, -65537, -538);
        renderGrupo(6370, -6637766, -16078710, -3932929, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_64() {

        renderGrupo(6400, -1, -1, -67662, -11361699, -10831288, -16147918, -13185573, -196609, -1, -1);
        renderGrupo(6410, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(6420, -1, -1, -1, -333683, -14050513, -15746896, -1310721, -1, -1, -1);
        renderGrupo(6430, -1, -1, -1, -1, -661361, -13591504, -14434111, -1179649, -1, -1);
        renderGrupo(6440, -1, -1, -1, -532096, -11086360, -131073, -1, -1, -1, -1);
        renderGrupo(6450, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -2);
        renderGrupo(6460, -989036, -12870097, -16210828, -5573122, -1, -1, -1, -1, -1, -1);
        renderGrupo(6470, -556, -8607183, -16211071, -4456961, -1, -1, -1, -554, -8275567, -3604993);
        renderGrupo(6480, -1, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(6490, -4456961, -1, -1, -1, -551, -7947375, -3604993, -1, -1, -1);
    }

    public void render_65() {

        renderGrupo(6500, -1, -1, -792950, -13853648, -14565443, -1310721, -1, -1, -1, -1);
        renderGrupo(6510, -1, -201061, -11614777, -393217, -1, -1, -1, -1, -1, -1);
        renderGrupo(6520, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -333685);
        renderGrupo(6530, -14116048, -14301490, -327681, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6540, -17, -6310092, -16343704, -6424834, -1, -1, -1, -1, -1, -1);
        renderGrupo(6550, -1, -1, -1, -267633, -13919184, -14498357, -327681, -1, -1, -1);
        renderGrupo(6560, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1324);
        renderGrupo(6570, -7555759, -11610919, -655361, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6580, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1);
        renderGrupo(6590, -1, -1, -1, -1, -1, -1, -1, -1, -556, -8606635);
    }

    public void render_66() {

        renderGrupo(6600, -12012478, -16147650, -10822160, -65537, -1, -1, -1, -2, -1254027, -13846677);
        renderGrupo(6610, -12739538, -15878485, -1441793, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(6620, -8606625, -7741022, -12214225, -16145030, -5113858, -1, -1, -1, -400770, -14566731);
        renderGrupo(6630, -786433, -1, -1, -1, -1, -1, -1, -2, -1385099, -14836939);
        renderGrupo(6640, -12988711, -327681, -1, -1, -1, -1, -544, -7228873, -16145550, -5769730);
        renderGrupo(6650, -1, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(6660, -4456961, -1, -1, -465273, -14378192, -14892605, -589825, -1, -1, -1);
        renderGrupo(6670, -1, -2, -1385098, -14836940, -13120040, -393217, -1, -1, -1, -1);
        renderGrupo(6680, -545, -7294409, -16145549, -5704194, -1, -1, -1, -1, -1, -1);
        renderGrupo(6690, -556, -8607183, -16211071, -4456961, -1, -1, -6, -3750069, -16147907, -10493963);
    }

    public void render_67() {

        renderGrupo(6700, -1, -1, -1, -1, -1, -1, -1, -8, -4931779, -16012912);
        renderGrupo(6710, -3473921, -1, -1, -1, -1089, -9978708, -1441793, -1, -1, -1);
        renderGrupo(6720, -1, -7, -5259147, -5965313, -1, -1, -133978, -12215250, -15811915, -786433);
        renderGrupo(6730, -1, -2, -1517708, -9903623, -1, -1, -1, -1, -1, -838);
        renderGrupo(6740, -10904274, -15945310, -2162945, -1, -1, -1, -1, -1, -399995, -14174549);
        renderGrupo(6750, -1441793, -1, -1, -1, -1, -1, -1, -16, -5522370, -16213181);
        renderGrupo(6760, -9968907, -1, -1, -1, -1, -10, -4537780, -12135195, -131073, -1);
        renderGrupo(6770, -1, -1, -1, -1, -1, -794501, -14968015, -13579306, -262145, -1);
        renderGrupo(6780, -1, -2, -1385616, -15361210, -9575176, -1, -1, -1, -551, -8213172);
        renderGrupo(6790, -9181190, -1, -1, -1, -1, -1, -1, -1, -1, -7);
    }

    public void render_68() {

        renderGrupo(6800, -3027109, -15689168, -14828361, -1573121, -1, -858227, -13260159, -4786178, -1, -1);
        renderGrupo(6810, -1, -1, -1, -1, -1, -1, -2, -1516428, -14902481, -15815016);
        renderGrupo(6820, -3080705, -1, -1, -1, -661363, -13456500, -3802113, -1, -1, -1);
        renderGrupo(6830, -1, -1, -1, -4, -3224463, -8459524, -1, -1, -1, -463719);
        renderGrupo(6840, -13001169, -16078456, -4130049, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6860, -1, -1, -1, -20, -6572492, -16011364, -2621697, -1, -1, -1);
        renderGrupo(6870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6890, -1, -1, -1, -1, -1, -1, -664206, -15819696, -8524804, -1);
    }

    public void render_69() {

        renderGrupo(6900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(6920, -8607182, -15220799, -458753, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6940, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1, -1);
        renderGrupo(6950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -664206);
        renderGrupo(6980, -15819696, -8524804, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(6990, -1, -1, -1, -1, -200803, -12936140, -12003605, -65537, -1, -1);
    }

    public void render_70() {

        renderGrupo(7000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7020, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7030, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7040, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7080, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_71() {

        renderGrupo(7100, -1, -1, -1, -1, -1, -1598, -9783917, -3342849, -1, -1);
        renderGrupo(7110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_72() {

        renderGrupo(7200, -1, -1, -1, -1, -15, -5325505, -15946085, -2687233, -1, -2);
        renderGrupo(7210, -1386132, -15557546, -7934211, -1, -1, -1, -1, -1, -1, -200770);
        renderGrupo(7220, -6759217, -1836318, -7294669, -16011621, -2621697, -1, -1, -1, -1, -1);
        renderGrupo(7230, -1, -1, -20, -5781867, -3998209, -1, -1, -9, -5063108, -16278174);
        renderGrupo(7240, -6884099, -1, -1, -1, -1, -1, -1, -13, -5717888, -5178625);
        renderGrupo(7250, -1, -1, -8, -4800450, -16212894, -6949891, -1, -1, -1, -1);
        renderGrupo(7260, -1, -1, -1, -1, -1, -858485, -12800427, -14181327, -13579306, -262145);
        renderGrupo(7270, -1, -1, -1, -1, -1, -1, -3, -2699682, -11019276, -1);
        renderGrupo(7280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7290, -2, -1385614, -15098795, -8196869, -1, -1, -2, -1250619, -3475458, -1);
    }

    public void render_73() {

        renderGrupo(7300, -1, -1, -1, -1, -279, -6767744, -4850689, -1, -1, -1);
        renderGrupo(7310, -555, -8409508, -7868675, -1, -1, -1, -1, -1, -1, -283);
        renderGrupo(7320, -7163341, -16078192, -3408385, -1, -2, -1189268, -15754431, -9903111, -1, -1);
        renderGrupo(7330, -1, -1, -1, -201061, -13067215, -13513769, -262145, -1, -3, -2108576);
        renderGrupo(7340, -15885226, -7999747, -1, -1, -1, -1, -1, -1, -1, -19);
        renderGrupo(7350, -5128121, -15223384, -2097409, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7360, -3, -2239388, -15227010, -4917250, -1, -1, -1, -1, -1, -1);
        renderGrupo(7370, -1, -1, -1, -400511, -14902207, -9771782, -1, -1, -1, -1);
        renderGrupo(7380, -1, -1, -1, -1, -1, -1, -1, -200804, -13001676, -11806738);
        renderGrupo(7390, -65537, -1, -1, -1, -1, -1, -1, -1, -6, -4340926);
    }

    public void render_74() {

        renderGrupo(7400, -16013429, -3801601, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7410, -1, -5, -3881400, -16014204, -4194817, -1, -1, -1, -1, -1);
        renderGrupo(7420, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7430, -1, -1, -1, -1, -1, -1, -1, -269, -2695270, -10238542);
        renderGrupo(7440, -1900801, -1, -1, -1, -1, -1, -1345, -8733550, -5837841, -262145);
        renderGrupo(7450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_75() {

        renderGrupo(7500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -3);
        renderGrupo(7510, -2437024, -12200989, -131073, -1, -1, -1, -1, -553, -7096240, -15098041);
        renderGrupo(7520, -12006988, -7422878, -9119322, -9982146, -15621033, -9642261, -131073, -1, -1, -1);
        renderGrupo(7530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7540, -1, -1, -1, -1, -1, -1, -200288, -12472424, -2883841, -1);
        renderGrupo(7550, -1, -1, -1, -1, -1, -1, -1, -1, -201321, -12798295);
        renderGrupo(7560, -1769729, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7570, -1, -1, -1, -1, -13, -5982155, -16277902, -5571585, -1, -1);
        renderGrupo(7580, -1, -1, -1, -1, -1, -1, -2, -1054569, -12343458, -8723478);
        renderGrupo(7590, -262145, -1, -1, -1, -1, -1, -1, -1322, -7753154, -13711669);
    }

    public void render_76() {

        renderGrupo(7600, -851969, -1, -1, -1, -1, -1, -1, -1, -1, -565);
        renderGrupo(7610, -9459605, -6424834, -1, -67924, -11687544, -3998209, -1, -1, -1, -1);
        renderGrupo(7620, -1, -400254, -14836392, -7802883, -1, -293, -8016589, -14957882, -393217, -1);
        renderGrupo(7630, -811, -8081061, -8591114, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7640, -1, -1, -1, -5, -4078265, -16147093, -6227970, -1, -3, -2568359);
        renderGrupo(7650, -16016295, -7737347, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7680, -2, -1451669, -15026775, -2168432, -13788114, -16077932, -3146241, -1, -1, -1);
        renderGrupo(7690, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
    }

    public void render_77() {

        renderGrupo(7700, -1, -1, -200545, -12805074, -15944795, -1966337, -1, -1, -1, -1);
        renderGrupo(7710, -1, -1, -20, -6178759, -16278443, -7999747, -1, -1, -1, -1);
        renderGrupo(7720, -1, -1, -66842, -2230791, -1, -1, -1, -1, -1, -1);
        renderGrupo(7730, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -7);
        renderGrupo(7740, -3552945, -16082382, -13185573, -196609, -1, -1, -1, -1, -1, -556);
        renderGrupo(7750, -8607183, -16211071, -4456961, -1, -1, -1, -12, -3153204, -1638657, -1);
        renderGrupo(7760, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(7770, -1, -1, -1, -6, -1576732, -917505, -1, -1, -1, -1);
        renderGrupo(7780, -21, -6375624, -16278444, -8196612, -1, -1, -1, -1, -1, -1);
        renderGrupo(7790, -533, -2625296, -65537, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_78() {

        renderGrupo(7800, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -333685, -14116048);
        renderGrupo(7810, -14301490, -327681, -1, -1, -1, -1, -1, -1, -1, -17);
        renderGrupo(7820, -6310092, -16343704, -6424834, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7830, -1, -1, -267633, -13919184, -14498357, -327681, -1, -1, -1, -1);
        renderGrupo(7840, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1064, -7096494, -11742504);
        renderGrupo(7850, -720897, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(7860, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1);
        renderGrupo(7870, -1, -1, -1, -1, -1, -1, -1, -556, -8606625, -7808113);
        renderGrupo(7880, -13722577, -15616599, -1835265, -1, -1, -1, -287, -7359660, -8920661, -11625170);
        renderGrupo(7890, -15878485, -1441793, -1, -1, -1, -1, -1, -1, -556, -8606625);
    }

    public void render_79() {

        renderGrupo(7900, -7409416, -2567582, -15427024, -14499907, -1376257, -1, -1, -400770, -14566731, -786433);
        renderGrupo(7910, -1, -1, -1, -1, -1, -1, -286, -7360205, -16278171, -6753027);
        renderGrupo(7920, -1, -1, -1, -1, -1, -2, -1320338, -15492558, -13054243, -196609);
        renderGrupo(7930, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(7940, -1, -1, -68181, -11952850, -15945310, -2162945, -1, -1, -1, -1);
        renderGrupo(7950, -286, -7294668, -16278173, -6818563, -1, -1, -1, -1, -1, -2);
        renderGrupo(7960, -1320338, -15492558, -12988451, -196609, -1, -1, -1, -1, -1, -556);
        renderGrupo(7970, -8607183, -16211071, -4456961, -1, -1, -2, -1255062, -15885774, -12725791, -196609);
        renderGrupo(7980, -1, -1, -1, -1, -1, -1, -14, -6047691, -16211600, -5900546);
        renderGrupo(7990, -1, -1, -1, -2, -197123, -1, -1, -1, -1, -1);
    }

    public void render_80() {

        renderGrupo(8000, -1, -131588, -131073, -1, -1, -133978, -12215250, -15811915, -786433, -1);
        renderGrupo(8010, -1, -260, -262401, -1, -1, -1, -1, -1, -838, -10904274);
        renderGrupo(8020, -15945310, -2162945, -1, -1, -1, -1, -1, -399995, -14174549, -1441793);
        renderGrupo(8030, -1, -1, -1, -1, -1, -1, -1, -662395, -14378193, -15287627);
        renderGrupo(8040, -1310721, -1, -1, -1, -67657, -10901646, -5769218, -1, -1, -1);
        renderGrupo(8050, -1, -1, -1, -1, -1345, -10313168, -16077932, -3146241, -1, -1);
        renderGrupo(8060, -280, -6703818, -16278992, -14827326, -786433, -1, -1, -201062, -12932727, -3932673);
        renderGrupo(8070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -810);
        renderGrupo(8080, -8147147, -16278716, -10493971, -198460, -9721003, -9050380, -65537, -1, -1, -1);
        renderGrupo(8090, -1, -1, -1, -1, -1, -1, -283, -6506692, -16213447, -12200735);
    }

    public void render_81() {

        renderGrupo(8100, -196609, -1, -289, -7294132, -10297105, -65537, -1, -1, -1, -1);
        renderGrupo(8110, -1, -1, -1, -131591, -327681, -1, -1, -545, -7228616, -16278714);
        renderGrupo(8120, -10100496, -65537, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8140, -1, -1, -20, -6572492, -16011106, -2425089, -1, -1, -1, -1);
        renderGrupo(8150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8170, -1, -1, -1, -1, -1, -532876, -15754160, -8524804, -1, -1);
        renderGrupo(8180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8190, -1, -1, -1, -1, -1, -1, -1, -1, -565, -9394639);
    }

    public void render_82() {

        renderGrupo(8200, -15155006, -458753, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8210, -1, -1, -1, -1, -270, -2892391, -8792102, -262145, -1, -1);
        renderGrupo(8220, -1, -1, -1, -4, -3224751, -16081553, -5899777, -1, -1, -1);
        renderGrupo(8230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8250, -1, -1, -1, -1, -1, -1, -1, -1, -664206, -15819696);
        renderGrupo(8260, -8524804, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8270, -1, -1, -1, -200803, -12936140, -12003605, -65537, -1, -1, -1);
        renderGrupo(8280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_83() {

        renderGrupo(8300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8360, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8380, -1, -1, -1, -3, -2108317, -15422831, -3342849, -1, -1, -1);
        renderGrupo(8390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_84() {

        renderGrupo(8400, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8420, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8430, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8480, -1, -1, -1, -1088, -10313168, -14958140, -393217, -1, -1, -200803);
        renderGrupo(8490, -12936142, -12857120, -196609, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_85() {

        renderGrupo(8500, -285, -7294669, -16011621, -2621697, -1, -1, -1, -1, -1, -1);
        renderGrupo(8510, -1, -1, -1, -1, -1, -1, -5, -3815607, -16147376, -8459268);
        renderGrupo(8520, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8530, -1, -5, -3618740, -16147371, -8131075, -1, -1, -1, -1, -1);
        renderGrupo(8540, -1, -1, -1, -67125, -9195413, -6822234, -12083919, -13579306, -262145, -1);
        renderGrupo(8550, -1, -1, -1, -1, -1, -3, -2699682, -11019276, -1, -1);
        renderGrupo(8560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -282);
        renderGrupo(8570, -6835147, -16011880, -2883841, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8580, -1, -1, -1, -1, -65794, -1, -1, -1, -2, -1188232);
        renderGrupo(8590, -13911373, -1441793, -1, -1, -1, -1, -1, -1, -558, -8869584);
    }

    public void render_86() {

        renderGrupo(8600, -16011104, -2294017, -1, -1, -466564, -15230411, -11478286, -1, -1, -1);
        renderGrupo(8610, -1, -1, -729742, -15754699, -11084554, -1, -1, -1, -267633, -13919181);
        renderGrupo(8620, -12463131, -131073, -1, -1, -1, -1, -1, -1, -596343, -14115772);
        renderGrupo(8630, -9837578, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8640, -1085, -9919438, -13973554, -589825, -1, -1, -1, -1, -1, -1);
        renderGrupo(8650, -1, -1, -400770, -15033534, -9771782, -1, -1, -1, -1, -1);
        renderGrupo(8660, -1, -1, -1, -1, -1, -1, -200803, -12936140, -12003605, -65537);
        renderGrupo(8670, -1, -1, -1, -1, -1, -1, -1, -6, -4340926, -16013429);
        renderGrupo(8680, -3801601, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8690, -5, -3881400, -16014204, -4194817, -1, -1, -1, -1, -1, -1);
    }

    public void render_87() {

        renderGrupo(8700, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8710, -1, -1, -1, -1, -270, -2761063, -10703024, -12730725, -5116173, -131073);
        renderGrupo(8720, -1, -1, -1, -1, -1, -9, -2169948, -9718960, -13322096, -6034707);
        renderGrupo(8730, -262145, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8740, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8780, -1, -1, -1, -1, -1, -1, -1, -1, -289, -7622044);
        renderGrupo(8790, -7146755, -1, -1, -1, -1, -1, -1, -1, -659009, -8341686);
    }

    public void render_88() {

        renderGrupo(8800, -15688912, -16080295, -10038065, -1507841, -1, -1, -1, -1, -1, -1);
        renderGrupo(8810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8820, -1, -1, -1, -1, -1, -200288, -12472424, -2883841, -1, -1);
        renderGrupo(8830, -1, -1, -1, -1, -1, -1, -1, -294, -8081304, -6818563);
        renderGrupo(8840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8850, -1, -1, -1, -7, -4997318, -16211330, -4588033, -1, -1, -1);
        renderGrupo(8860, -1, -1, -1, -1, -1, -134222, -11164081, -9575441, -65537, -1);
        renderGrupo(8870, -1, -1, -1, -1, -1, -1, -1, -332393, -13262747, -6753283);
        renderGrupo(8880, -1, -1, -1, -1, -1, -1, -1, -1, -133976, -11949428);
        renderGrupo(8890, -3736065, -1, -334201, -14043219, -1376257, -1, -1, -1, -1, -1);
    }

    public void render_89() {

        renderGrupo(8900, -532876, -15688607, -7015427, -1, -283, -7163341, -15746119, -589825, -8, -3355554);
        renderGrupo(8910, -12661805, -655361, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8920, -1, -1, -5, -3618740, -16147370, -7934211, -1, -5, -3881399, -16146836);
        renderGrupo(8930, -6162434, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(8950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -21);
        renderGrupo(8960, -6441153, -11806742, -66087, -8213455, -16343987, -8852997, -1, -1, -1, -1);
        renderGrupo(8970, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
        renderGrupo(8980, -1, -794500, -14968015, -13710636, -262145, -1, -1, -1, -1, -1);
        renderGrupo(8990, -1, -1351, -10837969, -16144761, -4063745, -1, -1, -1, -1, -1);
    }

    public void render_90() {

        renderGrupo(9000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9010, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -267632);
        renderGrupo(9020, -13853650, -15945052, -1966337, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(9030, -16211071, -4456961, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9040, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
        renderGrupo(9050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1608);
        renderGrupo(9060, -10969297, -16145017, -4063745, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(9080, -8607183, -16211071, -4456961, -1, -1, -1, -1, -333685, -14116048, -14301490);
        renderGrupo(9090, -327681, -1, -1, -1, -1, -1, -1, -1, -17, -6310092);
    }

    public void render_91() {

        renderGrupo(9100, -16343704, -6424834, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9110, -1, -267633, -13919184, -14498357, -327681, -1, -1, -1, -1, -1);
        renderGrupo(9120, -1, -556, -8607183, -16211071, -4456961, -804, -6768301, -11939371, -786433, -1);
        renderGrupo(9130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9140, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -1);
        renderGrupo(9150, -1, -1, -1, -1, -1, -1, -556, -8606625, -7409442, -7425740);
        renderGrupo(9160, -16278441, -7934211, -1, -1, -1, -530549, -13519708, -2230352, -11625170, -15878485);
        renderGrupo(9170, -1441793, -1, -1, -1, -1, -1, -1, -556, -8606625, -7409155);
        renderGrupo(9180, -547, -7359943, -16278714, -10362898, -65537, -1, -400770, -14566731, -786433, -1);
        renderGrupo(9190, -1, -1, -1, -1, -1, -67664, -11559377, -16078448, -3408385, -1);
    }

    public void render_92() {

        renderGrupo(9200, -1, -1, -1, -1, -1, -201062, -13067218, -15944537, -1769729, -1);
        renderGrupo(9210, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
        renderGrupo(9220, -1, -133978, -12215250, -15944537, -1769729, -1, -1, -1, -1, -67663);
        renderGrupo(9230, -11493841, -16078448, -3473921, -1, -1, -1, -1, -1, -1, -201062);
        renderGrupo(9240, -13132753, -15878999, -1704193, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(9250, -16211071, -4456961, -1, -1, -2, -1386649, -15885773, -12134935, -131073, -1);
        renderGrupo(9260, -1, -1, -1, -1, -1, -4, -3093421, -16016848, -15290750, -6559767);
        renderGrupo(9270, -524289, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9280, -1, -1, -1, -1, -133978, -12215250, -15811915, -786433, -1, -1);
        renderGrupo(9290, -1, -1, -1, -1, -1, -1, -1, -838, -10904274, -15945310);
    }

    public void render_93() {

        renderGrupo(9300, -2162945, -1, -1, -1, -1, -1, -399995, -14174549, -1441793, -1);
        renderGrupo(9310, -1, -1, -1, -1, -1, -1, -552, -8278991, -16277917, -6818819);
        renderGrupo(9320, -1, -1, -3, -1845657, -13974844, -786433, -1, -1, -1, -1);
        renderGrupo(9330, -1, -1, -1, -11, -4931520, -16212910, -8393476, -1, -1, -200287);
        renderGrupo(9340, -12475048, -12999120, -16211337, -5309953, -1, -3, -2437027, -13973812, -393217, -1);
        renderGrupo(9350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -595308);
        renderGrupo(9360, -13263569, -16145041, -9782964, -12858156, -655361, -1, -1, -1, -1, -1);
        renderGrupo(9370, -1, -1, -1, -1, -1, -1, -266336, -12476881, -16211344, -5900803);
        renderGrupo(9380, -2, -1450892, -13977685, -1966337, -1, -1, -1, -1, -1, -1);
        renderGrupo(9390, -1, -1, -1, -1, -1, -3, -1976470, -15230160, -14894154, -1572865);
    }

    public void render_94() {

        renderGrupo(9400, -1, -1, -1, -1, -1, -1, -1, -1, -333671, -12145584);
        renderGrupo(9410, -13455262, -12144303, -14177431, -8789786, -196609, -1, -1, -1, -1, -1);
        renderGrupo(9420, -1, -20, -6572492, -16078482, -9848231, -14507980, -15950274, -14044548, -7215122, -131073);
        renderGrupo(9430, -1, -1, -1, -1, -1, -1, -12, -3024245, -11292587, -13061792);
        renderGrupo(9440, -12537773, -13981613, -12071216, -327681, -1, -1, -1, -1, -1, -277);
        renderGrupo(9450, -4075139, -12276914, -13455262, -11945875, -11555004, -16081840, -8524804, -1, -1, -1);
        renderGrupo(9460, -1, -1, -1, -1, -538, -4797325, -12866731, -12668328, -13587353, -9315363);
        renderGrupo(9470, -589825, -1, -1, -1, -1, -1, -857430, -9520551, -14705361, -16080306);
        renderGrupo(9480, -12865445, -12732774, -3670529, -1, -1, -1, -1, -1, -798, -5191055);
        renderGrupo(9490, -12603810, -11946134, -12210349, -13720004, -15290480, -4065026, -1, -1, -1, -1);
    }

    public void render_95() {

        renderGrupo(9500, -1, -1, -4, -3224751, -16081304, -8135779, -9717931, -14573516, -15752363, -10496036);
        renderGrupo(9510, -524289, -1, -1, -1, -1, -1, -266312, -8602021, -14968013, -12200472);
        renderGrupo(9520, -131073, -1, -1, -1, -1, -1, -200000, -7813784, -14181328, -14038832);
        renderGrupo(9530, -327681, -1, -1, -1, -1, -1, -1, -664206, -15819696, -8524804);
        renderGrupo(9540, -1, -67380, -7620793, -15751838, -9908800, -2097409, -1, -1, -1, -1);
        renderGrupo(9550, -1, -1, -200803, -12936140, -12003605, -65537, -1, -1, -1, -1);
        renderGrupo(9560, -3, -2499966, -12277962, -16212645, -10172038, -12145595, -15295181, -15686571, -10627893, -3350365);
        renderGrupo(9570, -9258146, -13983178, -15884474, -12466497, -1704193, -1, -1, -1, -1, -1);
        renderGrupo(9580, -3, -2171771, -12409291, -16212130, -9909895, -12211130, -15295182, -15686568, -10102048, -393217);
        renderGrupo(9590, -1, -1, -1, -1, -1, -1, -275, -3878016, -12013993, -12668064);
    }

    public void render_96() {

        renderGrupo(9600, -12866479, -12928369, -5640458, -1, -1, -1, -1, -1, -1, -3);
        renderGrupo(9610, -2434691, -13130958, -16145566, -10568351, -13589187, -15688132, -14241413, -7083793, -65537, -1);
        renderGrupo(9620, -1, -1, -1, -1, -1, -277, -4075140, -12342451, -13455262, -11945875);
        renderGrupo(9630, -11488170, -14639022, -8459268, -1, -1, -1, -1, -3, -2433651, -11359431);
        renderGrupo(9640, -16145795, -5378364, -7224471, -13523875, -7803139, -1, -1, -1, -1, -1);
        renderGrupo(9650, -3, -1645672, -11095979, -12864926, -12472495, -13652088, -4982530, -1, -1, -1);
        renderGrupo(9660, -1, -16, -3943554, -12737488, -16277949, -13521061, -12600151, -2556161, -1, -1);
        renderGrupo(9670, -1, -10, -3221107, -11622350, -16144761, -4063745, -13, -3417711, -10375105, -16212635);
        renderGrupo(9680, -6687234, -1, -1, -1, -1, -290, -5650843, -14509265, -16145314, -10105931);
        renderGrupo(9690, -2950145, -9, -3023980, -9915836, -15487882, -7018513, -65537, -1, -1, -1);
    }

    public void render_97() {

        renderGrupo(9700, -287, -5322127, -13590737, -16211108, -10368598, -3868674, -1, -524, -1509386, -65537);
        renderGrupo(9710, -5, -2564457, -9390260, -15357069, -8199967, -196609, -1, -1, -3, -1448543);
        renderGrupo(9720, -9915837, -16147920, -14568571, -6558988, -265795, -8340653, -15227034, -9448243, -1310721, -1);
        renderGrupo(9730, -1, -1, -1, -12, -3418498, -12869072, -16278453, -11745892, -4787459, -17);
        renderGrupo(9740, -3811697, -10769090, -14699642, -6231051, -1, -1, -1, -1, -200803, -12935621);
        renderGrupo(9750, -14439591, -12536990, -12274853, -14049232, -16343987, -8787460, -1, -1, -1, -1);
        renderGrupo(9760, -1, -1, -200805, -13067215, -13447976, -262145, -1, -1, -1355, -11231697);
        renderGrupo(9770, -15352131, -589825, -1, -1, -1, -1, -1, -1, -1, -285);
        renderGrupo(9780, -7294669, -16011621, -2621697, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9790, -1, -1, -1, -1, -1, -8, -4931780, -16278169, -6621698, -1);
    }

    public void render_98() {

        renderGrupo(9800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9810, -11, -5259973, -16211593, -5244417, -1, -1, -1, -1, -1, -1);
        renderGrupo(9820, -1, -13, -4209060, -11544862, -330326, -12083919, -13579306, -262145, -1, -1);
        renderGrupo(9830, -1, -1, -1, -1, -3, -2699682, -11019276, -1, -1, -1);
        renderGrupo(9840, -1, -1, -1, -1, -1, -1, -1, -1, -1351, -10903760);
        renderGrupo(9850, -15352131, -589825, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9860, -1, -1, -1, -1, -1, -1, -1, -289, -7491249, -9378313);
        renderGrupo(9870, -1, -1, -1, -1, -1, -1, -1, -279, -6703819, -16078450);
        renderGrupo(9880, -3604993, -1, -2, -1320854, -15819961, -9312517, -1, -1, -1, -1);
        renderGrupo(9890, -1, -926865, -15820234, -10887432, -1, -1, -1, -68182, -12083920, -14826810);
    }

    public void render_99() {

        renderGrupo(9900, -393217, -1, -1, -1, -1, -1, -11, -5062850, -16145798, -4981761);
        renderGrupo(9910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -10);
        renderGrupo(9920, -5063107, -16145796, -4916481, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9930, -1, -400770, -15033534, -9771782, -1, -1, -1, -1, -1, -1);
        renderGrupo(9940, -1, -1, -1, -1, -1, -200803, -12936140, -12003605, -65537, -1);
        renderGrupo(9950, -1, -1, -1, -1, -1, -1, -6, -4340926, -16013429, -3801601);
        renderGrupo(9960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -5);
        renderGrupo(9970, -3881400, -16014204, -4194817, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(9990, -1, -271, -2892393, -10834094, -12336478, -4656651, -65537, -1, -1, -1);
    }

    public void render_100() {

        renderGrupo(10000, -1, -1, -1, -1, -1, -1, -8, -1841494, -9324972, -13322099);
        renderGrupo(10010, -6231572, -327681, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10020, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10030, -924275, -13327806, -12201512, -262145, -1, -1, -1, -1, -1, -1);
        renderGrupo(10040, -924275, -13327806, -12201512, -262145, -1, -1, -1, -1, -1, -67404);
        renderGrupo(10050, -11099849, -15622601, -15622601, -15622601, -15622601, -15622601, -15622601, -15622601, -15223386, -2031873);
        renderGrupo(10060, -1, -1, -1, -1, -1, -1, -1, -200803, -12536669, -2097409);
        renderGrupo(10070, -1, -1, -1, -1, -1, -553, -7227314, -15163577, -11875658, -7357342);
        renderGrupo(10080, -9250910, -10244802, -15621032, -9576725, -131073, -1, -1, -1, -1, -1);
        renderGrupo(10090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_101() {

        renderGrupo(10100, -1, -1, -1, -1, -200288, -12472424, -2883841, -1, -1, -1);
        renderGrupo(10110, -1, -1, -1, -1, -1, -1, -3, -2896549, -11938330, -131073);
        renderGrupo(10120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10130, -1, -1, -5, -4078265, -16013172, -3736065, -1, -1, -1, -1);
        renderGrupo(10140, -1, -1, -1, -21, -5653438, -14170937, -851969, -1, -1, -133165);
        renderGrupo(10150, -6502804, -13195714, -15557584, -16145276, -4325889, -1, -295, -8147654, -11215886, -1);
        renderGrupo(10160, -1, -1, -1, -1, -7, -4997056, -15557065, -15622863, -16278475, -15622601);
        renderGrupo(10170, -15622601, -15754192, -16147146, -15622050, -7606275, -1, -1, -1, -1, -333943);
        renderGrupo(10180, -14312107, -8131076, -1, -554, -8475853, -14432821, -327681, -660844, -12668523, -3343361);
        renderGrupo(10190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_102() {

        renderGrupo(10200, -1, -1, -662396, -14443728, -14236990, -1245185, -659802, -11689156, -12726830, -786433);
        renderGrupo(10210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10230, -1, -1, -1, -1, -1, -1, -1, -1, -133973, -11885973);
        renderGrupo(10240, -6228226, -3, -2568359, -16016848, -14235956, -458753, -1, -1, -1, -1);
        renderGrupo(10250, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -6, -1381455);
        renderGrupo(10260, -10048712, -14961767, -3802626, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10270, -201063, -13198290, -15945053, -2031873, -1, -1, -1, -1, -1, -1);
        renderGrupo(10280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(10290, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -1613, -11363026);
    }

    public void render_103() {

        renderGrupo(10300, -16144761, -4063745, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(10310, -4456961, -1, -5, -3749520, -8065796, -1, -1, -1, -1, -1);
        renderGrupo(10320, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
        renderGrupo(10330, -399733, -10824990, -131073, -1, -1, -1, -1, -1, -201063, -13263826);
        renderGrupo(10340, -15945053, -2031873, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10350, -1, -1, -1, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(10360, -16211071, -4456961, -1, -1, -1, -1, -333685, -14116048, -14301490, -327681);
        renderGrupo(10370, -1, -1, -1, -1, -1, -1, -1, -17, -6310092, -16343704);
        renderGrupo(10380, -6424834, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10390, -267633, -13919184, -14498357, -327681, -1, -1, -1, -1, -1, -1);
    }

    public void render_104() {

        renderGrupo(10400, -556, -8607183, -16211071, -4523297, -6374843, -16081318, -8066056, -1, -1, -1);
        renderGrupo(10410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(10420, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10430, -1, -1, -1, -1, -1, -556, -8606625, -7409156, -1582741, -15492560);
        renderGrupo(10440, -14104882, -458753, -1, -17, -5718962, -10559760, -132944, -11625170, -15878485, -1441793);
        renderGrupo(10450, -1, -1, -1, -1, -1, -1, -556, -8606625, -7409155, -1);
        renderGrupo(10460, -332130, -12542161, -16145029, -5113858, -1, -400770, -14566731, -786433, -1, -1);
        renderGrupo(10470, -1, -1, -1, -1, -267115, -13460434, -15944794, -1835265, -1, -1);
        renderGrupo(10480, -1, -1, -1, -1, -67407, -11559634, -16144501, -3801601, -1, -1);
        renderGrupo(10490, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
    }

    public void render_105() {

        renderGrupo(10500, -991368, -15033551, -13710636, -262145, -1, -1, -1, -1, -267115, -13460434);
        renderGrupo(10510, -15944794, -1835265, -1, -1, -1, -1, -1, -1, -67408, -11559634);
        renderGrupo(10520, -16144501, -3801601, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(10530, -4456961, -1, -1, -8, -4340923, -16147370, -8000003, -1, -1, -1);
        renderGrupo(10540, -1, -1, -1, -1, -1, -67383, -8802759, -16278994, -16344527, -15292058);
        renderGrupo(10550, -9908295, -3147780, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10560, -1, -1, -1, -133978, -12215250, -15811915, -786433, -1, -1, -1);
        renderGrupo(10570, -1, -1, -1, -1, -1, -1, -838, -10904274, -15945310, -2162945);
        renderGrupo(10580, -1, -1, -1, -1, -1, -399995, -14174549, -1441793, -1, -1);
        renderGrupo(10590, -1, -1, -1, -1, -1, -3, -2042782, -15754702, -13185573, -196609);
    }

    public void render_106() {

        renderGrupo(10600, -1, -291, -7819181, -8721669, -1, -1, -1, -1, -1, -1);
        renderGrupo(10610, -1, -1, -1, -794500, -14968015, -13513513, -262145, -3, -2896553, -13842485);
        renderGrupo(10620, -3552173, -16082374, -11150353, -65537, -285, -7294387, -9312519, -1, -1, -1);
        renderGrupo(10630, -1, -1, -1, -1, -1, -1, -1, -1, -9, -3683757);
        renderGrupo(10640, -15885778, -16078193, -3605249, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10650, -1, -1, -1, -1, -1, -8, -3290026, -15885776, -14433854, -1180982);
        renderGrupo(10660, -9327781, -7934469, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10670, -1, -1, -1, -1, -68169, -10902736, -16211607, -6556931, -1, -1);
        renderGrupo(10680, -1, -1, -1, -1, -1, -1, -1, -467335, -12004897, -393217);
        renderGrupo(10690, -17, -5653699, -16145538, -4719617, -1, -1, -1, -1, -1, -1);
    }

    public void render_107() {

        renderGrupo(10700, -20, -6572492, -16078458, -4721678, -459011, -789818, -8145858, -16146593, -7278596, -1);
        renderGrupo(10710, -1, -1, -1, -1, -5, -2698654, -15294887, -8656912, -131073, -1);
        renderGrupo(10720, -727157, -12992321, -458753, -1, -1, -1, -1, -8, -3552427, -15687845);
        renderGrupo(10730, -8394510, -65537, -1, -664206, -15819696, -8524804, -1, -1, -1, -1);
        renderGrupo(10740, -1, -1, -10, -3880880, -15818132, -6491397, -3, -1450634, -14771124, -9115656);
        renderGrupo(10750, -1, -1, -1, -1, -1, -1, -565, -9394639, -15155006, -458753);
        renderGrupo(10760, -1, -1, -1, -1, -1, -1, -7, -3618480, -15883923, -6228739);
        renderGrupo(10770, -1, -1066, -8344270, -15748705, -2556161, -1, -1, -1, -1, -1);
        renderGrupo(10780, -1, -4, -3224751, -16081831, -9052207, -2821658, -1904698, -8080324, -16211856, -5900290);
        renderGrupo(10790, -1, -1, -1, -1, -1, -1, -134493, -12477389, -12200472, -131073);
    }

    public void render_108() {

        renderGrupo(10800, -1, -1, -1, -1, -1, -1, -835, -10641615, -14038832, -327681);
        renderGrupo(10810, -1, -1, -1, -1, -1, -1, -664206, -15819696, -8524804, -1);
        renderGrupo(10820, -133699, -9915532, -6360839, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(10830, -1, -200803, -12936140, -12003605, -65537, -1, -1, -1, -1, -1);
        renderGrupo(10840, -4, -2962091, -16081835, -9643064, -3478050, -2692422, -9130183, -16278452, -10628165, -4331048);
        renderGrupo(10850, -2691380, -6701751, -16147636, -8852997, -1, -1, -1, -1, -1, -1);
        renderGrupo(10860, -4, -3224751, -16081831, -8986414, -2690328, -1904961, -9064649, -16211594, -5309953, -1);
        renderGrupo(10870, -1, -1, -1, -1, -8, -3355560, -15555731, -6557447, -1, -535);
        renderGrupo(10880, -5193654, -16014222, -5835523, -1, -1, -1, -1, -1, -1, -6);
        renderGrupo(10890, -4340926, -16146064, -6491661, -327683, -789819, -8408516, -16146072, -6556931, -1, -1);
    }

    public void render_109() {

        renderGrupo(10900, -1, -1, -1, -9, -3552427, -15687843, -8197645, -65537, -1, -664206);
        renderGrupo(10910, -15819696, -8524804, -1, -1, -1, -1, -1, -4, -3224751, -16081846);
        renderGrupo(10920, -11745121, -6234175, -7423129, -7868675, -1, -1, -1, -1, -1, -67916);
        renderGrupo(10930, -11165375, -10691090, -65537, -14, -5062023, -6621698, -1, -1, -1, -1);
        renderGrupo(10940, -1, -8, -5522635, -16078191, -3342849, -1, -1, -1, -1, -1);
        renderGrupo(10950, -1, -7, -4997575, -16144761, -4063745, -1, -3, -2240163, -15950491, -6687234);
        renderGrupo(10960, -1, -1, -1, -1, -1, -282, -6834889, -16211857, -5966082, -1);
        renderGrupo(10970, -1, -17, -5718961, -10166028, -1, -1, -1, -1, -1, -1);
        renderGrupo(10980, -13, -5194178, -16146060, -5572354, -1, -2, -1188492, -15030134, -3932929, -1);
        renderGrupo(10990, -4, -3356070, -11019281, -65537, -1, -1, -1, -1, -1, -67388);
    }

    public void render_110() {

        renderGrupo(11000, -9787599, -15617892, -2949633, -284, -6374295, -8131850, -1, -1, -1, -1);
        renderGrupo(11010, -1, -1, -1, -5, -3552946, -16082100, -9049862, -1, -1, -283);
        renderGrupo(11020, -7031463, -8196868, -1, -1, -1, -1, -1, -200800, -9908262, -262145);
        renderGrupo(11030, -1, -463720, -13001424, -14696773, -1376257, -1, -1, -1, -1, -1);
        renderGrupo(11040, -1, -334201, -14443726, -12660255, -131073, -1, -1, -830, -10247889, -15878741);
        renderGrupo(11050, -1507329, -1, -1, -1, -1, -1, -1, -1, -285, -7294669);
        renderGrupo(11060, -16011621, -2621697, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11070, -1, -1, -1, -1, -555, -8541391, -15616599, -1966337, -1, -1);
        renderGrupo(11080, -1, -1, -1, -1, -1, -1, -1, -1, -5, -1448300);
        renderGrupo(11090, -12607164, -11545123, -393217, -1, -1, -1, -1, -1, -1, -2);
    }

    public void render_111() {

        renderGrupo(11100, -990072, -12797781, -2162945, -68182, -12083919, -13579306, -262145, -1, -1, -1);
        renderGrupo(11110, -1, -1, -1, -3, -2699687, -14570401, -12800166, -12800171, -13455776, -11221857);
        renderGrupo(11120, -4787720, -1, -1, -1, -1, -1, -1, -200547, -12870608, -14697573);
        renderGrupo(11130, -6829447, -12013739, -13850811, -14242457, -9644333, -1114113, -1, -1, -1, -1);
        renderGrupo(11140, -1, -1, -1, -1, -1, -1, -793469, -13979233, -2490625, -1);
        renderGrupo(11150, -1, -1, -1, -1, -1, -1, -2, -1055349, -13525431, -10297882);
        renderGrupo(11160, -458753, -198705, -8409032, -14960725, -2294017, -1, -1, -1, -1, -1);
        renderGrupo(11170, -267632, -13788110, -13251109, -196609, -1, -1, -1096, -11035346, -15811913, -720897);
        renderGrupo(11180, -1, -1, -1, -1, -1, -564, -9329104, -15945311, -2228481, -1);
        renderGrupo(11190, -1, -1, -1, -1, -1, -1, -1, -1, -3, -2371236);
    }

    public void render_112() {

        renderGrupo(11200, -16016568, -9181189, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11210, -532359, -15360953, -9312517, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11220, -1, -1, -1, -1, -134493, -12477389, -12266266, -131073, -1, -1);
        renderGrupo(11230, -1, -1, -1, -1, -1, -6, -4340926, -16013429, -3801601, -1);
        renderGrupo(11240, -1, -1, -1, -1, -1, -1, -1, -1, -5, -3881400);
        renderGrupo(11250, -16014204, -4194817, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11260, -1, -1, -1, -1, -1, -1, -1, -1, -272, -3023467);
        renderGrupo(11270, -10965163, -11942745, -4197384, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11280, -1, -1, -1, -1, -1, -1, -1, -5, -1513040, -8865705);
        renderGrupo(11290, -13191285, -6428437, -393217, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_113() {

        renderGrupo(11300, -1, -1, -1, -1, -1, -1, -1, -1, -2, -1582997);
        renderGrupo(11310, -15427024, -14827070, -655361, -1, -1, -1, -1, -1, -2, -1582997);
        renderGrupo(11320, -15427024, -14827070, -655361, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11340, -1, -1, -1, -1, -1, -3, -2633890, -12135452, -131073, -1);
        renderGrupo(11350, -1, -1, -1, -1, -10, -3877480, -5837324, -65818, -6966461, -10034439);
        renderGrupo(11360, -532, -3811678, -5180419, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11380, -1, -1, -1, -200288, -12472424, -2883841, -1, -1, -1, -1);
        renderGrupo(11390, -1, -1, -1, -1, -1, -1, -201320, -12798553, -1900801, -1);
    }

    public void render_114() {

        renderGrupo(11400, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11410, -1, -4, -3093421, -15749479, -2752769, -1, -1, -1, -1, -1);
        renderGrupo(11420, -1, -1, -266856, -13263007, -7081219, -1, -1, -265547, -10177162, -7346977);
        renderGrupo(11430, -1180165, -3487410, -15682391, -1638401, -1, -12, -5850824, -13053987, -196609, -1);
        renderGrupo(11440, -1, -1, -1, -1, -1, -3, -2043038, -13448492, -262145, -6);
        renderGrupo(11450, -4340919, -11347472, -65537, -1, -1, -1, -1, -1, -816, -8869320);
        renderGrupo(11460, -12792102, -524289, -726382, -13263281, -8918792, -66607, -8540066, -8066056, -1, -1);
        renderGrupo(11470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11480, -1, -280, -5653435, -16147653, -13388445, -13061771, -8134689, -720897, -332107, -8336752);
        renderGrupo(11490, -8730224, -8730224, -8597054, -1900801, -1, -1, -1, -1, -1, -1);
    }

    public void render_115() {

        renderGrupo(11500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11510, -1, -1, -1, -1, -1, -1, -3, -1845915, -14960205, -1376257);
        renderGrupo(11520, -1, -134494, -12477137, -16145021, -4326401, -1, -1, -1, -1, -1);
        renderGrupo(11530, -1, -1, -556, -8607183, -16278469, -14505650, -13850291, -14179269, -15885520, -15488657);
        renderGrupo(11540, -8529460, -1967105, -1, -1, -1, -1, -1, -1, -1, -333426);
        renderGrupo(11550, -13984978, -15878484, -1376257, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11560, -1, -1, -1, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(11570, -16211071, -4456961, -1, -1, -1, -1, -1, -837, -10838481, -16211329);
        renderGrupo(11580, -4588033, -1, -1, -1, -1, -1, -556, -8607183, -16278469, -14505650);
        renderGrupo(11590, -13850290, -13850292, -14704555, -8262404, -1, -1, -1, -1, -1, -1);
    }

    public void render_116() {

        renderGrupo(11600, -1, -1, -1, -556, -8607183, -16278469, -14505650, -13850290, -13850290, -14048197);
        renderGrupo(11610, -12594718, -131073, -1, -1, -1, -1, -1, -333426, -13984978, -15878484);
        renderGrupo(11620, -1376257, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11630, -1, -1, -1, -1, -1, -1, -1, -556, -8607183, -16278469);
        renderGrupo(11640, -14505650, -13850290, -13850290, -13850290, -13850290, -13851075, -15951312, -14301490, -327681, -1);
        renderGrupo(11650, -1, -1, -1, -1, -1, -1, -17, -6310092, -16343704, -6424834);
        renderGrupo(11660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -267633);
        renderGrupo(11670, -13919184, -14498357, -327681, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(11680, -8607183, -16211611, -10372263, -12404383, -14771409, -15881334, -4130306, -1, -1, -1);
        renderGrupo(11690, -1, -1, -1, -1, -1, -1, -1, -1, -556, -8607183);
    }

    public void render_117() {

        renderGrupo(11700, -16211071, -4456961, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11710, -1, -1, -1, -1, -556, -8606625, -7409155, -1602, -10378448, -16211077);
        renderGrupo(11720, -5047809, -1, -200544, -12473203, -3670785, -67408, -11625170, -15878485, -1441793, -1);
        renderGrupo(11730, -1, -1, -1, -1, -1, -556, -8606625, -7409155, -1, -6);
        renderGrupo(11740, -2896035, -15623632, -14499907, -1376257, -400770, -14566731, -786433, -1, -1, -1);
        renderGrupo(11750, -1, -1, -1, -333427, -14050514, -15878483, -1376257, -1, -1, -1);
        renderGrupo(11760, -1, -1, -1, -1096, -11035346, -16210814, -4325889, -1, -1, -1);
        renderGrupo(11770, -1, -1, -1, -556, -8607183, -16211071, -4456961, -8, -1775449, -10835917);
        renderGrupo(11780, -15947906, -5114371, -1, -1, -1, -1, -1, -333427, -14050514, -15878483);
        renderGrupo(11790, -1376257, -1, -1, -1, -1, -1, -1, -1096, -11100882, -16210813);
    }

    public void render_118() {

        renderGrupo(11800, -4325889, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(11810, -1, -530, -3155070, -13394379, -13646394, -1179649, -1, -1, -1, -1);
        renderGrupo(11820, -1, -1, -1, -1, -1, -1054, -4664701, -11884230, -16147922, -16344529);
        renderGrupo(11830, -16211623, -9116949, -131073, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11840, -1, -1, -133978, -12215250, -15811915, -786433, -1, -1, -1, -1);
        renderGrupo(11850, -1, -1, -1, -1, -1, -838, -10904274, -15945310, -2162945, -1);
        renderGrupo(11860, -1, -1, -1, -1, -399995, -14174549, -1441793, -1, -1, -1);
        renderGrupo(11870, -1, -1, -1, -1, -1, -67658, -11034320, -16144245, -3867393, -1);
        renderGrupo(11880, -399219, -13520481, -2425089, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11890, -1, -1, -1343, -10181840, -16077676, -3080705, -559, -8869035, -8327940, -200804);
    }

    public void render_119() {

        renderGrupo(11900, -13001681, -15550546, -1507329, -134235, -12211832, -3998465, -1, -1, -1, -1);
        renderGrupo(11910, -1, -1, -1, -1, -1, -1, -1, -1, -398187, -13394898);
        renderGrupo(11920, -16278452, -9509645, -65537, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11930, -1, -1, -1, -1, -1, -1332, -9197262, -16278451, -11289767, -13515062);
        renderGrupo(11940, -786433, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(11950, -1, -1, -16, -4865977, -16147912, -12463651, -262145, -1, -1, -1);
        renderGrupo(11960, -1, -1, -1, -1, -1, -1, -199725, -3410435, -1, -3);
        renderGrupo(11970, -2568359, -16016285, -6818563, -1, -1, -1, -1, -1, -1, -20);
        renderGrupo(11980, -6572492, -16011621, -2621697, -1, -1, -201063, -13198288, -14038832, -327681, -1);
        renderGrupo(11990, -1, -1, -1, -1343, -10181840, -15155780, -917505, -1, -1, -556);
    }

    public void render_120() {

        renderGrupo(12000, -6498348, -327681, -1, -1, -1, -1, -67143, -10837712, -15024193, -786433);
        renderGrupo(12010, -1, -1, -664206, -15819696, -8524804, -1, -1, -1, -1, -1);
        renderGrupo(12020, -1, -1605, -10641104, -14958141, -589825, -1, -1090, -10444496, -14826810, -458753);
        renderGrupo(12030, -1, -1, -1, -1, -1, -565, -9394639, -15155006, -458753, -1);
        renderGrupo(12040, -1, -1, -1, -1, -1, -551, -8213455, -15944794, -1900801, -1);
        renderGrupo(12050, -5, -4078266, -16147093, -6162178, -1, -1, -1, -1, -1, -1);
        renderGrupo(12060, -4, -3224751, -16081554, -5899777, -1, -1, -1058195, -15819693, -8327940, -1);
        renderGrupo(12070, -1, -1, -1, -1, -1, -134493, -12477389, -12200472, -131073, -1);
        renderGrupo(12080, -1, -1, -1, -1, -1, -835, -10641615, -14038832, -327681, -1);
        renderGrupo(12090, -1, -1, -1, -1, -1, -664206, -15819696, -8524804, -67380, -8538255);
    }

    public void render_121() {

        renderGrupo(12100, -7082761, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12110, -200803, -12936140, -12003605, -65537, -1, -1, -1, -1, -1, -4);
        renderGrupo(12120, -2962091, -16081557, -6162178, -1, -2, -1123989, -15885229, -8327940, -1, -1);
        renderGrupo(12130, -333684, -14050507, -11084554, -1, -1, -1, -1, -1, -1, -4);
        renderGrupo(12140, -3224751, -16081554, -5899777, -1, -3, -1780381, -15950759, -7737347, -1, -1);
        renderGrupo(12150, -1, -1, -1, -1604, -10641104, -14695481, -458753, -1, -1, -200545);
        renderGrupo(12160, -12739534, -13251109, -196609, -1, -1, -1, -1, -1, -6, -4340926);
        renderGrupo(12170, -16079999, -4456961, -1, -1, -332910, -13657039, -13382440, -262145, -1, -1);
        renderGrupo(12180, -1, -1, -67399, -10903248, -14958399, -720897, -1, -1, -664206, -15819696);
        renderGrupo(12190, -8524804, -1, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777);
    }

    public void render_122() {

        renderGrupo(12200, -1, -264997, -2622978, -1, -1, -1, -1, -1, -267631, -13788090);
        renderGrupo(12210, -9706506, -1, -1, -198161, -1049089, -1, -1, -1, -1, -1);
        renderGrupo(12220, -8, -5522635, -16078191, -3342849, -1, -1, -1, -1, -1, -1);
        renderGrupo(12230, -7, -4997575, -16144761, -4063745, -1, -3, -2240163, -15950491, -6687234, -1);
        renderGrupo(12240, -1, -1, -1, -1, -2, -1056903, -14968011, -12463390, -131073, -1);
        renderGrupo(12250, -133975, -11817587, -3670785, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12260, -728450, -14836934, -11084817, -65537, -19, -6113223, -16278714, -9640712, -1, -556);
        renderGrupo(12270, -8540043, -5703426, -1, -1, -1, -1, -1, -1, -2, -1187200);
        renderGrupo(12280, -14312392, -12529450, -3223186, -11152162, -327681, -1, -1, -1, -1, -1);
        renderGrupo(12290, -1, -1, -1, -200803, -12936144, -14564666, -655361, -1, -200546, -12340318);
    }

    public void render_123() {

        renderGrupo(12300, -2294017, -1, -1, -1, -1, -1, -67107, -2821123, -1, -544);
        renderGrupo(12310, -7163079, -16145810, -6097667, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12320, -400511, -14902477, -12463132, -131073, -1, -1, -572, -9985488, -15945052, -1966337);
        renderGrupo(12330, -1, -1, -1, -1, -1, -1, -1, -285, -7294669, -16011621);
        renderGrupo(12340, -2621697, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12350, -1, -1, -2, -1121922, -14508459, -8459785, -1, -1, -1, -1);
        renderGrupo(12360, -1, -1, -1, -1, -1, -558, -8212151, -14441414, -15753136, -11022391);
        renderGrupo(12370, -1638913, -1, -1, -1, -1, -1, -1, -1, -67130, -9457555);
        renderGrupo(12380, -6491395, -1, -68182, -12083919, -13579306, -262145, -1, -1, -1, -1);
        renderGrupo(12390, -1, -1, -1, -329496, -2099985, -590081, -1, -526899, -7489726, -16080280);
    }

    public void render_124() {

        renderGrupo(12400, -6754051, -1, -1, -1, -1, -1, -267116, -13525968, -14894945, -4329749);
        renderGrupo(12410, -655617, -271, -3354266, -15099075, -11544346, -131073, -1, -1, -1, -1);
        renderGrupo(12420, -1, -1, -1, -1, -281, -6638009, -10887953, -65537, -1, -1);
        renderGrupo(12430, -1, -1, -1, -1, -1, -1, -14, -3945895, -15557835, -14964916);
        renderGrupo(12440, -14770640, -16079502, -6098437, -1, -1, -1, -1, -1, -1, -287);
        renderGrupo(12450, -6834885, -16079763, -7017745, -262145, -197653, -2956916, -12870609, -15812430, -1048577, -1);
        renderGrupo(12460, -1, -1, -1, -1, -67923, -11821778, -15811915, -851969, -1, -1);
        renderGrupo(12470, -1, -1, -1, -1, -1, -1, -1, -1, -861329, -15820236);
        renderGrupo(12480, -11740945, -65537, -1, -1, -1, -1, -1, -1, -18, -4997049);
        renderGrupo(12490, -15684981, -4064513, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_125() {

        renderGrupo(12500, -1, -1, -1, -288, -6834883, -15288921, -2294273, -1, -1, -1);
        renderGrupo(12510, -1, -1, -1, -1, -6, -4340926, -16013429, -3801601, -1, -1);
        renderGrupo(12520, -1, -1, -1, -1, -1, -1, -1, -5, -3881400, -16014204);
        renderGrupo(12530, -4194817, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12540, -1, -1, -1, -1, -1, -1, -1077, -8606403, -14895976, -4196870);
        renderGrupo(12550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12560, -1, -1, -1, -1, -1, -1, -1, -1, -3, -1316187);
        renderGrupo(12570, -11164102, -13975108, -1441793, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12580, -1, -1, -1, -1, -1, -1, -1, -1, -12, -2102311);
        renderGrupo(12590, -1770497, -1, -1, -1, -1, -1, -1, -1, -12, -2102311);
    }

    public void render_126() {

        renderGrupo(12600, -1770497, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12620, -1, -1, -1, -1, -291, -7818908, -7081219, -1, -1, -1);
        renderGrupo(12630, -1, -1, -1, -1, -1, -1, -561, -9132235, -12200729, -131073);
        renderGrupo(12640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12650, -1, -1, -1, -1, -1, -1, -1, -1, -67404, -11099849);
        renderGrupo(12660, -15622601, -15622601, -15622604, -16147661, -15688137, -15622601, -15622601, -15223386, -2031873, -1);
        renderGrupo(12670, -1, -1, -1, -1, -1, -294, -8081306, -6949891, -1, -1);
        renderGrupo(12680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12690, -3, -2108834, -15551577, -1769729, -1, -1, -1, -1, -1, -1);
    }

    public void render_127() {

        renderGrupo(12700, -4, -3224751, -15551062, -1704193, -1, -547, -7293858, -8328198, -1, -19);
        renderGrupo(12710, -6375626, -14104625, -327681, -1, -13, -5981895, -12791327, -196609, -1, -1);
        renderGrupo(12720, -1, -1, -1, -1, -7, -4603321, -11150349, -1, -282, -7031726);
        renderGrupo(12730, -8721668, -1, -1, -1, -1, -1, -1, -1, -462402, -8142993);
        renderGrupo(12740, -11355280, -11486852, -8003357, -458762, -3749285, -12398889, -524561, -3680890, -11093907, -11225239);
        renderGrupo(12750, -11221591, -3540994, -1, -1, -1, -1, -1, -1, -1, -271);
        renderGrupo(12760, -3155314, -11358896, -13391297, -16147915, -13778762, -2294529, -1, -4, -1053543, -12673999);
        renderGrupo(12770, -13449273, -1639427, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12790, -1, -1, -1, -1, -1, -282, -6966463, -11019024, -65537, -1);
    }

    public void render_128() {

        renderGrupo(12800, -281, -6769355, -16278718, -10034442, -1, -1, -1, -1, -1, -1);
        renderGrupo(12810, -1, -556, -8607183, -16211071, -4456961, -1, -1, -132118, -3154026, -11819726);
        renderGrupo(12820, -15619456, -5245699, -1, -1, -1, -1, -1, -1, -267114, -13394898);
        renderGrupo(12830, -15944795, -1966337, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12840, -1, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(12850, -4456961, -1, -1, -1, -1, -1, -67407, -11559634, -16144502, -3867137);
        renderGrupo(12860, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
        renderGrupo(12870, -7, -4668829, -8131332, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12880, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -663684, -11808030);
        renderGrupo(12890, -131073, -1, -1, -1, -1, -1, -267115, -13460434, -15944795, -1966337);
    }

    public void render_129() {

        renderGrupo(12900, -1, -1, -1, -2, -1317473, -9913237, -11946651, -12077721, -11354211, -4196354);
        renderGrupo(12910, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(12920, -1, -1, -1, -1, -333685, -14116048, -14301490, -327681, -1, -1);
        renderGrupo(12930, -1, -1, -1, -1, -1, -17, -6310092, -16343704, -6424834, -1);
        renderGrupo(12940, -1, -1, -1, -1, -1, -1, -1, -1, -267633, -13919184);
        renderGrupo(12950, -14498357, -327681, -1, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(12960, -16278452, -10495265, -590096, -4537267, -16016848, -14434629, -1573121, -1, -1, -1);
        renderGrupo(12970, -1, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(12980, -4456961, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(12990, -1, -1, -1, -556, -8606625, -7409155, -8, -4012470, -16147911, -11544085);
    }

    public void render_130() {

        renderGrupo(13000, -65544, -4077999, -12200990, -131073, -67408, -11625170, -15878485, -1441793, -1, -1);
        renderGrupo(13010, -1, -1, -1, -1, -556, -8606625, -7409155, -1, -1, -808);
        renderGrupo(13020, -7819210, -16278714, -10231570, -531842, -14566731, -786433, -1, -1, -1, -1);
        renderGrupo(13030, -1, -1, -266857, -13329362, -15944794, -1900801, -1, -1, -1, -1);
        renderGrupo(13040, -1, -1, -67408, -11625170, -16144244, -3736065, -1, -1, -1, -1);
        renderGrupo(13050, -1, -1, -556, -8607183, -16278469, -14505651, -14047416, -14045854, -10762068, -3672323);
        renderGrupo(13060, -1, -1, -1, -1, -1, -1, -266857, -13329362, -15944794, -1900801);
        renderGrupo(13070, -1, -1, -1, -1, -1, -1, -67408, -11625170, -16144243, -3670529);
        renderGrupo(13080, -1, -1, -1, -1, -1, -556, -8607183, -16278469, -14505650, -13850295);
        renderGrupo(13090, -14967248, -16278454, -10955310, -1310977, -1, -1, -1, -1, -1, -1);
    }

    public void render_131() {

        renderGrupo(13100, -1, -1, -1, -1, -1, -1, -8, -1709642, -8144048, -15492306);
        renderGrupo(13110, -16211341, -5703938, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13120, -1, -133978, -12215250, -15811915, -786433, -1, -1, -1, -1, -1);
        renderGrupo(13130, -1, -1, -1, -1, -838, -10904274, -15945310, -2162945, -1, -1);
        renderGrupo(13140, -1, -1, -1, -399995, -14174549, -1441793, -1, -1, -1, -1);
        renderGrupo(13150, -1, -1, -1, -1, -10, -4471995, -16147646, -10231564, -11, -4668851);
        renderGrupo(13160, -11478805, -65537, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13170, -1, -10, -4800447, -16212909, -8262404, -596860, -14110563, -2490625, -285, -7163339);
        renderGrupo(13180, -16277916, -6818820, -1780377, -13842997, -458753, -1, -1, -1, -1, -1);
        renderGrupo(13190, -1, -1, -1, -1, -1, -1, -808, -7818932, -11287177, -13984721);
    }

    public void render_132() {

        renderGrupo(13200, -16012919, -4064513, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13210, -1, -1, -1, -1, -2, -1121665, -14378194, -16211084, -5572866, -1);
        renderGrupo(13220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13230, -1, -858745, -13984721, -15815018, -3212033, -1, -1, -1, -1, -1);
        renderGrupo(13240, -1, -1, -1, -1, -1, -1, -1, -1, -3, -2240163);
        renderGrupo(13250, -15950750, -6884099, -1, -1, -1, -1, -1, -1, -20, -6572492);
        renderGrupo(13260, -16011621, -2621697, -1, -1, -571, -9919952, -15878483, -1376257, -1, -1);
        renderGrupo(13270, -1, -1, -267632, -13853645, -12594462, -131073, -1, -1, -1, -1);
        renderGrupo(13280, -1, -1, -1, -1, -1, -333941, -14247117, -12266266, -131073, -1);
        renderGrupo(13290, -1, -664206, -15819696, -8524804, -1, -1, -1, -1, -1, -1);
    }

    public void render_133() {

        renderGrupo(13300, -267373, -13657038, -12857120, -196609, -1, -557, -8738511, -15878743, -1638401, -1);
        renderGrupo(13310, -1, -1, -1, -1, -565, -9394639, -15155006, -458753, -1, -1);
        renderGrupo(13320, -1, -1, -1, -1, -292, -7950798, -15945310, -2162945, -1, -6);
        renderGrupo(13330, -4340926, -16146830, -5571841, -1, -1, -1, -1, -1, -1, -4);
        renderGrupo(13340, -3224751, -16081554, -5899777, -1, -1, -664206, -15819696, -8524804, -1, -1);
        renderGrupo(13350, -1, -1, -1, -1, -134493, -12477389, -12200472, -131073, -1, -1);
        renderGrupo(13360, -1, -1, -1, -1, -835, -10641615, -14038832, -327681, -1, -1);
        renderGrupo(13370, -1, -1, -1, -1, -664206, -15819696, -8525866, -7097023, -13908018, -524289);
        renderGrupo(13380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -200803);
        renderGrupo(13390, -12936140, -12003605, -65537, -1, -1, -1, -1, -1, -4, -2962091);
    }

    public void render_134() {

        renderGrupo(13400, -16081557, -6162178, -1, -1, -664206, -15819696, -8524804, -1, -1, -267374);
        renderGrupo(13410, -13657035, -11281420, -1, -1, -1, -1, -1, -1, -4, -3224751);
        renderGrupo(13420, -16081554, -5899777, -1, -2, -1386391, -15885226, -7934211, -1, -1, -1);
        renderGrupo(13430, -1, -1, -267889, -13919181, -12397595, -131073, -1, -1, -572, -9985488);
        renderGrupo(13440, -15812431, -1179649, -1, -1, -1, -1, -1, -6, -4340926, -16079999);
        renderGrupo(13450, -4456961, -1, -1, -832, -10379217, -15812429, -1048577, -1, -1, -1);
        renderGrupo(13460, -1, -333942, -14312653, -12200729, -131073, -1, -1, -664206, -15819696, -8524804);
        renderGrupo(13470, -1, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1);
        renderGrupo(13480, -1, -1, -1, -1, -1, -1, -1, -1598, -10115792, -16145830);
        renderGrupo(13490, -10170443, -3869963, -131073, -1, -1, -1, -1, -1, -1, -8);
    }

    public void render_135() {

        renderGrupo(13500, -5522635, -16078191, -3342849, -1, -1, -1, -1, -1, -1, -7);
        renderGrupo(13510, -4997575, -16144761, -4063745, -1, -3, -2240163, -15950491, -6687234, -1, -1);
        renderGrupo(13520, -1, -1, -1, -1, -817, -9000655, -16078191, -3342849, -3, -2699426);
        renderGrupo(13530, -12463905, -196609, -1, -1, -1, -1, -1, -1, -1, -1081);
        renderGrupo(13540, -9657040, -15418957, -1376257, -133973, -11488927, -12804048, -14827070, -720897, -267373, -12534087);
        renderGrupo(13550, -1048577, -1, -1, -1, -1, -1, -1, -1, -17, -4865721);
        renderGrupo(13560, -16081349, -13910604, -1769729, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13570, -1, -1, -280, -6703817, -16211335, -5178881, -3, -2962082, -11479062, -131073);
        renderGrupo(13580, -1, -1, -1, -1, -1, -1, -1, -3, -1910677, -15164615);
        renderGrupo(13590, -12135199, -196609, -1, -1, -1, -1, -1, -1, -1, -334200);
    }

    public void render_136() {

        renderGrupo(13600, -14443726, -12725791, -196609, -1, -1, -831, -10313681, -15878741, -1441793, -1);
        renderGrupo(13610, -1, -1, -1, -1, -1, -1, -285, -7294669, -16011621, -2621697);
        renderGrupo(13620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13630, -1, -133699, -10311609, -11282465, -393217, -1, -1, -1, -1, -1);
        renderGrupo(13640, -1, -1, -1, -1, -1, -1, -2, -461360, -6964664, -15357058);
        renderGrupo(13650, -5442563, -1, -1, -1, -1, -1, -14, -4471717, -11347739, -131073);
        renderGrupo(13660, -1, -68182, -12083919, -13579306, -262145, -1, -1, -1, -1, -1);
        renderGrupo(13670, -1, -1, -1, -1, -1, -1, -1, -200805, -13067216, -14301491);
        renderGrupo(13680, -327681, -1, -1, -1, -1, -200804, -13001680, -14235954, -327681, -1);
        renderGrupo(13690, -1, -826, -9853904, -15945310, -2228481, -1, -1, -1, -1, -1);
    }

    public void render_137() {

        renderGrupo(13700, -1, -1, -1, -464497, -13587829, -3801857, -1, -1, -1, -1);
        renderGrupo(13710, -1, -1, -1, -1, -14, -4143019, -15423625, -6098956, -131073, -66588);
        renderGrupo(13720, -4995755, -15489679, -6098179, -1, -1, -1, -1, -1, -1, -67116);
        renderGrupo(13730, -6503318, -13260989, -14440107, -12798598, -8399735, -12608465, -15680325, -524289, -1, -1);
        renderGrupo(13740, -1, -1, -1, -200804, -13001681, -15417665, -458753, -1, -1, -1);
        renderGrupo(13750, -1, -1, -1, -1, -1, -1, -1, -467080, -15492558, -12857121);
        renderGrupo(13760, -196609, -1, -1, -1, -1, -1, -200000, -8406964, -15358352, -6426629);
        renderGrupo(13770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13780, -1, -1, -1, -133694, -9392836, -14699899, -6033928, -1, -1, -1);
        renderGrupo(13790, -1, -1, -1, -6, -4340926, -16013429, -3801601, -1, -1, -1);
    }

    public void render_138() {

        renderGrupo(13800, -1, -1, -1, -1, -1, -1, -5, -3881400, -16014204, -4194817);
        renderGrupo(13810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13820, -1, -1, -1, -1, -1, -3, -1052998, -8077988, -13191803, -6887962);
        renderGrupo(13830, -589825, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13840, -1, -1, -1, -1, -1, -1, -533, -3679859, -11424168, -11417424);
        renderGrupo(13850, -3475717, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_139() {

        renderGrupo(13900, -1, -1, -1, -201061, -12601947, -2031873, -1, -1, -1, -1);
        renderGrupo(13910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13920, -1, -1, -1, -1, -1, -1, -1041, -1838107, -2034971, -2034971);
        renderGrupo(13930, -2034970, -1902600, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(13940, -1, -200288, -12472424, -2883841, -1, -1, -1, -1, -1, -1);
        renderGrupo(13950, -1, -1, -1, -1, -3, -2896549, -12069658, -131073, -1, -1);
        renderGrupo(13960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2);
        renderGrupo(13970, -1255061, -15353420, -917505, -1, -1, -1, -1, -1, -1, -282);
        renderGrupo(13980, -6966474, -13251110, -196609, -1, -793985, -14108757, -1638401, -1, -566, -9525959);
        renderGrupo(13990, -11215886, -1, -1, -292, -7950786, -10559498, -1, -1, -1, -1);
    }

    public void render_140() {

        renderGrupo(14000, -1, -1, -1, -286, -7294123, -8459268, -1, -570, -9853072, -5899777);
        renderGrupo(14010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14020, -1, -1, -792690, -12864611, -2949635, -1910934, -15161732, -5048322, -280, -5981631);
        renderGrupo(14030, -15091537, -1769729, -1, -1, -1, -1, -1, -13, -4077483, -15622570);
        renderGrupo(14040, -8853776, -131872, -5980596, -15820239, -14698590, -3343874, -1, -399737, -14377642, -7934211);
        renderGrupo(14050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14070, -1, -1, -1, -1, -134493, -12411332, -14308523, -13324971, -13324971, -13324971);
        renderGrupo(14080, -13654727, -16082384, -15024451, -917505, -1, -1, -1, -1, -1, -1);
        renderGrupo(14090, -556, -8607183, -16211071, -4456961, -1, -1, -1, -2, -1188750, -15295952);
    }

    public void render_141() {

        renderGrupo(14100, -14564151, -458753, -1, -1, -1, -1, -1, -1610, -11165905, -16144760);
        renderGrupo(14110, -3998209, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14120, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(14130, -1, -1, -1, -1, -1, -267116, -13525969, -15878742, -1638401, -1);
        renderGrupo(14140, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -3);
        renderGrupo(14150, -2238317, -6622979, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14160, -1, -556, -8607183, -16211071, -4456961, -1, -1, -200791, -8267033, -131073);
        renderGrupo(14170, -1, -1, -1, -1, -1, -1611, -11166161, -16144502, -3867137, -1);
        renderGrupo(14180, -1, -1, -1, -1, -559, -8935376, -16144761, -4063745, -1, -1);
        renderGrupo(14190, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
    }

    public void render_142() {

        renderGrupo(14200, -1, -1, -1, -333685, -14116048, -14301490, -327681, -1, -1, -1);
        renderGrupo(14210, -1, -1, -1, -1, -17, -6310092, -16343704, -6424834, -1, -1);
        renderGrupo(14220, -1, -1, -1, -1, -1, -1, -1, -267633, -13919184, -14498357);
        renderGrupo(14230, -327681, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(14240, -4456961, -1, -1071, -8475083, -16278722, -11675679, -262145, -1, -1, -1);
        renderGrupo(14250, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(14260, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14270, -1, -1, -556, -8606625, -7409155, -1, -201320, -13263825, -15879776, -2493003);
        renderGrupo(14280, -10966665, -5375746, -1, -67408, -11625170, -15878485, -1441793, -1, -1, -1);
        renderGrupo(14290, -1, -1, -1, -556, -8606625, -7409155, -1, -1, -1, -463719);
    }

    public void render_143() {

        renderGrupo(14300, -12935633, -16145028, -5514626, -14566731, -786433, -1, -1, -1, -1, -1);
        renderGrupo(14310, -1, -1612, -11297233, -16143986, -3604993, -1, -1, -1, -1, -1);
        renderGrupo(14320, -1, -201063, -13263825, -15878741, -1572865, -1, -1, -1, -1, -1);
        renderGrupo(14330, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1);
        renderGrupo(14340, -1, -1, -1, -1, -1, -1612, -11231697, -16143986, -3539457, -1);
        renderGrupo(14350, -1, -1, -1, -1, -1, -201063, -13198289, -15813205, -1507329, -1);
        renderGrupo(14360, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -4, -2501788);
        renderGrupo(14370, -15295692, -13449011, -851969, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14380, -1, -1, -1, -1, -1, -1, -1, -13, -4078000, -16082375);
        renderGrupo(14390, -10690568, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_144() {

        renderGrupo(14400, -133978, -12215250, -15811915, -786433, -1, -1, -1, -1, -1, -1);
        renderGrupo(14410, -1, -1, -1, -837, -10772945, -15945312, -2294017, -1, -1, -1);
        renderGrupo(14420, -1, -1, -400253, -14305105, -1310721, -1, -1, -1, -1, -1);
        renderGrupo(14430, -1, -1, -1, -1, -332909, -13591505, -15353422, -1509707, -10966662, -5113345);
        renderGrupo(14440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14450, -1, -728706, -14902479, -13447987, -5324985, -12069402, -131073, -3, -1780121, -15689164);
        renderGrupo(14460, -12594739, -6703280, -9378055, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14470, -1, -1, -1, -1, -11, -3749289, -13712703, -1310989, -4340660, -16082381);
        renderGrupo(14480, -13645619, -786433, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14490, -1, -1, -1, -1, -1097, -11100882, -15945310, -2162945, -1, -1);
    }

    public void render_145() {

        renderGrupo(14500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1069);
        renderGrupo(14510, -8541132, -16278449, -9050122, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14520, -1, -1, -1, -3, -1448022, -8995220, -11749262, -10830732, -11555522, -16212894);
        renderGrupo(14530, -6884099, -1, -1, -1, -1, -1, -1, -20, -6572492, -16011621);
        renderGrupo(14540, -2621697, -1, -1, -564, -9329104, -15944794, -1835265, -1, -1, -1);
        renderGrupo(14550, -1, -400254, -14836940, -11937812, -65537, -1, -1, -1, -1, -1);
        renderGrupo(14560, -1, -1, -1, -1, -466565, -15230411, -11412750, -1, -1, -1);
        renderGrupo(14570, -664206, -15819696, -8524804, -1, -1, -1, -1, -1, -1, -334200);
        renderGrupo(14580, -14378192, -15292068, -12274846, -12274846, -12274851, -13326008, -14042194, -1835265, -1, -1);
        renderGrupo(14590, -1, -1, -1, -565, -9394639, -15155006, -458753, -1, -1, -1);
    }

    public void render_146() {

        renderGrupo(14600, -1, -1, -1, -5, -3093162, -15753634, -7869194, -1, -198978, -10115278);
        renderGrupo(14610, -14696773, -1376257, -1, -1, -1, -1, -1, -1, -4, -3224751);
        renderGrupo(14620, -16081554, -5899777, -1, -1, -664206, -15819696, -8524804, -1, -1, -1);
        renderGrupo(14630, -1, -1, -1, -134493, -12477389, -12200472, -131073, -1, -1, -1);
        renderGrupo(14640, -1, -1, -1, -835, -10641615, -14038832, -327681, -1, -1, -1);
        renderGrupo(14650, -1, -1, -1, -664206, -15819968, -13389984, -12538302, -16146850, -7475461, -1);
        renderGrupo(14660, -1, -1, -1, -1, -1, -1, -1, -1, -200803, -12936140);
        renderGrupo(14670, -12003605, -65537, -1, -1, -1, -1, -1, -4, -2962091, -16081557);
        renderGrupo(14680, -6162178, -1, -1, -664206, -15819696, -8524804, -1, -1, -267374, -13657035);
        renderGrupo(14690, -11281420, -1, -1, -1, -1, -1, -1, -4, -3224751, -16081554);
    }

    public void render_147() {

        renderGrupo(14700, -5899777, -1, -2, -1386391, -15885226, -7934211, -1, -1, -1, -1);
        renderGrupo(14710, -1, -400254, -14836940, -11872276, -65537, -1, -1, -564, -9329104, -15944794);
        renderGrupo(14720, -1900801, -1, -1, -1, -1, -1, -6, -4340926, -16079999, -4456961);
        renderGrupo(14730, -1, -1, -566, -9525968, -15944536, -1704193, -1, -1, -1, -1);
        renderGrupo(14740, -466565, -15295947, -11412750, -1, -1, -1, -664206, -15819696, -8524804, -1);
        renderGrupo(14750, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1, -1);
        renderGrupo(14760, -1, -1, -1, -1, -1, -1, -2, -790860, -8931246, -15098833);
        renderGrupo(14770, -16278726, -13714268, -3474946, -1, -1, -1, -1, -1, -8, -5522635);
        renderGrupo(14780, -16078191, -3342849, -1, -1, -1, -1, -1, -1, -7, -4997575);
        renderGrupo(14790, -16144761, -4063745, -1, -3, -2240163, -15950491, -6687234, -1, -1, -1);
    }

    public void render_148() {

        renderGrupo(14800, -1, -1, -1, -3, -2436771, -15885500, -9903371, -559, -8802706, -6294018);
        renderGrupo(14810, -1, -1, -1, -1, -1, -1, -1, -1, -7, -3946934);
        renderGrupo(14820, -16081044, -6162692, -2108567, -11282466, -5128384, -16145800, -5244421, -3224736, -10297356, -1);
        renderGrupo(14830, -1, -1, -1, -1, -1, -1, -1, -1, -200545, -12739536);
        renderGrupo(14840, -15354458, -2359553, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14850, -1, -2, -1188492, -15164615, -11281682, -66093, -8605322, -5572354, -1, -1);
        renderGrupo(14860, -1, -1, -1, -1, -1, -1, -67911, -10640336, -15683428, -2884097);
        renderGrupo(14870, -1, -1, -1, -1, -1, -1, -1, -1, -200803, -12936143);
        renderGrupo(14880, -13579306, -262145, -1, -1, -1612, -11363025, -15352130, -589825, -1, -1);
        renderGrupo(14890, -1, -1, -1, -1, -1, -285, -7294669, -16011621, -2621697, -1);
    }

    public void render_149() {

        renderGrupo(14900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -67639);
        renderGrupo(14910, -8867760, -11151652, -589825, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(14920, -1, -1, -1, -1, -1, -1, -1, -133976, -12214992, -14629945);
        renderGrupo(14930, -524289, -1, -1, -1, -2, -1121404, -13125982, -3016462, -1050126, -1050126);
        renderGrupo(14940, -1118302, -12280783, -13776692, -1312268, -524545, -1, -1, -1, -1, -1);
        renderGrupo(14950, -1, -1, -1, -1, -1, -1, -835, -10641617, -15878485, -1441793);
        renderGrupo(14960, -1, -1, -1, -1, -67407, -11494097, -15286336, -458753, -1, -1);
        renderGrupo(14970, -289, -7622862, -16210554, -4129281, -1, -1, -1, -1, -1, -1);
        renderGrupo(14980, -1, -18, -5653435, -12266526, -131073, -1, -1, -1, -1, -1);
        renderGrupo(14990, -1, -1, -1, -134493, -12477135, -13447976, -262145, -1, -1, -67404);
    }

    public void render_150() {

        renderGrupo(15000, -11231440, -14695481, -524289, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15010, -1, -1, -1, -134494, -12608463, -13841965, -262145, -1, -1, -1);
        renderGrupo(15020, -1, -1, -201320, -13329361, -15286336, -458753, -1, -1, -1, -1);
        renderGrupo(15030, -1, -1, -1, -1, -1, -1, -466823, -15361486, -13185573, -196609);
        renderGrupo(15040, -1, -1, -1, -1, -1, -1, -277, -5587901, -15552873, -3277569);
        renderGrupo(15050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15060, -1, -283, -6441154, -15551842, -2818817, -1, -1, -1, -1, -1);
        renderGrupo(15070, -1, -1, -6, -4340926, -16013429, -3801601, -1, -1, -1, -1);
        renderGrupo(15080, -1, -1, -1, -1, -1, -5, -3881400, -16014204, -4194817, -1);
        renderGrupo(15090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_151() {

        renderGrupo(15100, -1, -1, -1, -1, -1, -1, -3, -987459, -7946660, -13454464);
        renderGrupo(15110, -7281951, -720897, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15120, -1, -1, -1, -792, -4008312, -11817641, -11285837, -3344388, -1, -1);
        renderGrupo(15130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15160, -1, -1, -1, -1, -1, -67403, -10902726, -15359942, -15359942, -15359942);
        renderGrupo(15170, -15359942, -15359942, -15359942, -15359942, -15026521, -2031873, -1, -1, -1, -1);
        renderGrupo(15180, -1, -3, -2765219, -12069914, -131073, -1, -1, -1, -1, -1);
        renderGrupo(15190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_152() {

        renderGrupo(15200, -1, -1, -1, -1, -1, -466565, -15230418, -16344530, -16344530, -16344529);
        renderGrupo(15210, -15417665, -458753, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15220, -200288, -12472424, -2883841, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15230, -1, -1, -1, -1, -201063, -12798811, -2031873, -1, -1, -1);
        renderGrupo(15240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -467080);
        renderGrupo(15250, -14499647, -458753, -1, -1, -1, -1, -1, -1, -565, -9460425);
        renderGrupo(15260, -11150091, -1, -8, -4537787, -12857379, -196609, -1, -134493, -12476843, -7999747);
        renderGrupo(15270, -1, -1, -68181, -11819917, -5638146, -1, -1, -1, -1, -1);
        renderGrupo(15280, -7, -4931262, -15294409, -15951053, -15687622, -15359942, -15360203, -16082123, -15491014, -15359392);
        renderGrupo(15290, -7475203, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_153() {

        renderGrupo(15300, -1332, -9064606, -7540998, -279, -6835148, -15352131, -589825, -1, -861071, -15688345);
        renderGrupo(15310, -6490370, -1, -1, -1, -1, -1, -134491, -12346065, -15484234, -1114113);
        renderGrupo(15320, -1, -277, -4601767, -15361232, -15356020, -4590089, -3815604, -15288917, -1900801, -1);
        renderGrupo(15330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15350, -1, -1, -3, -2305698, -14630978, -917505, -1, -1, -1, -67660);
        renderGrupo(15360, -11165649, -16277132, -5572354, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(15370, -8607183, -16211071, -4456961, -1, -1, -1, -1, -267631, -13722578, -15878226);
        renderGrupo(15380, -1376257, -1, -1, -1, -1, -1, -21, -6178759, -16278445, -8262404);
        renderGrupo(15390, -1, -1, -1, -1, -1, -1, -1068, -5906979, -196609, -1);
    }

    public void render_154() {

        renderGrupo(15400, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
        renderGrupo(15410, -1, -1, -1, -3, -2174369, -15885772, -12725792, -196609, -1, -1);
        renderGrupo(15420, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1);
        renderGrupo(15430, -1, -8, -1050120, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15440, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -1);
        renderGrupo(15450, -1, -1, -1, -1, -21, -6375623, -16278443, -7999747, -1, -1);
        renderGrupo(15460, -1, -1, -1, -559, -8869584, -16144761, -4063745, -1, -1, -1);
        renderGrupo(15470, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
        renderGrupo(15480, -1, -1, -333685, -14116048, -14301490, -327681, -1, -1, -1, -1);
        renderGrupo(15490, -1, -1, -1, -17, -6310092, -16343704, -6424834, -1, -1, -1);
    }

    public void render_155() {

        renderGrupo(15500, -1, -1, -1, -1, -1, -1, -267633, -13919183, -13710636, -262145);
        renderGrupo(15510, -1, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961);
        renderGrupo(15520, -1, -1, -397662, -12214224, -16277667, -7869192, -1, -1, -1, -1);
        renderGrupo(15530, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
        renderGrupo(15540, -1, -1, -1, -200785, -7479317, -131073, -1, -1, -1, -1);
        renderGrupo(15550, -1, -556, -8606625, -7409155, -1, -282, -6835146, -16278452, -11158695, -13514545);
        renderGrupo(15560, -458753, -1, -67408, -11625170, -15878485, -1441793, -1, -1, -1, -1);
        renderGrupo(15570, -1, -1, -556, -8606625, -7409155, -1, -1, -1, -8, -3290024);
        renderGrupo(15580, -15754704, -14833849, -15222091, -786433, -1, -1, -1, -1, -1, -1);
        renderGrupo(15590, -282, -6835147, -16278174, -6949891, -1, -1, -1, -1, -1, -2);
    }

    public void render_156() {

        renderGrupo(15600, -1386133, -15623629, -12791585, -196609, -1, -1, -1, -1, -1, -1);
        renderGrupo(15610, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -1);
        renderGrupo(15620, -1, -1, -1, -1, -281, -6834891, -16278174, -6884355, -1, -1);
        renderGrupo(15630, -1, -1, -1, -2, -1386132, -15558092, -12660255, -196609, -1, -1);
        renderGrupo(15640, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -543, -6769091);
        renderGrupo(15650, -16212916, -9640976, -65537, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15660, -8, -3548489, -2687233, -1, -1, -1, -1, -333685, -14116043, -11281420);
        renderGrupo(15670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -133978);
        renderGrupo(15680, -12215250, -15811915, -786433, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15690, -1, -1, -565, -9525968, -16144245, -3736065, -1, -1, -1, -1);
    }

    public void render_157() {

        renderGrupo(15700, -2, -1189266, -14696511, -458753, -1, -1, -1, -1, -1, -1);
        renderGrupo(15710, -1, -1, -1, -286, -7228875, -16278178, -9123740, -13580596, -524289, -1);
        renderGrupo(15720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15730, -1086, -10116304, -16079021, -13589395, -6162690, -1, -1, -67919, -11428049, -16078767);
        renderGrupo(15740, -13653625, -4064001, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15750, -1, -1, -2, -1055609, -13455990, -4064770, -1, -67388, -9787600, -16278446);
        renderGrupo(15760, -8722186, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15770, -1, -1, -1, -1097, -11100882, -15945310, -2162945, -1, -1, -1);
        renderGrupo(15780, -1, -1, -1, -1, -1, -1, -1, -6, -2896037, -15689168);
        renderGrupo(15790, -14302524, -1048577, -1, -1, -1, -133692, -6496285, -196609, -1, -1);
    }

    public void render_158() {

        renderGrupo(15800, -1, -1, -134488, -12083403, -12726824, -720897, -3, -2240163, -15950750, -6884099);
        renderGrupo(15810, -1, -1, -1, -1, -1, -1, -20, -6572492, -16011621, -2621697);
        renderGrupo(15820, -1, -1, -832, -10379216, -15615049, -851969, -1, -1, -1, -1);
        renderGrupo(15830, -267632, -13853646, -12922913, -196609, -1, -1, -1, -1, -1, -1);
        renderGrupo(15840, -1, -1, -1, -400252, -14705869, -12134935, -131073, -1, -1, -664206);
        renderGrupo(15850, -15819696, -8524804, -1, -1, -1, -1, -1, -1, -267372, -13591501);
        renderGrupo(15860, -12397595, -131073, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(15870, -1, -1, -565, -9394639, -15155006, -458753, -1, -1, -1, -1);
        renderGrupo(15880, -1, -1, -1, -539, -6375092, -13848220, -11683476, -11815313, -9908290, -2425857);
        renderGrupo(15890, -1, -1, -1, -1, -1, -1, -1, -4, -3224751, -16081554);
    }

    public void render_159() {

        renderGrupo(15900, -5899777, -1, -1, -664206, -15819696, -8524804, -1, -1, -1, -1);
        renderGrupo(15910, -1, -1, -134493, -12477389, -12200472, -131073, -1, -1, -1, -1);
        renderGrupo(15920, -1, -1, -835, -10641615, -14038832, -327681, -1, -1, -1, -1);
        renderGrupo(15930, -1, -1, -664206, -15819973, -12069923, -592717, -11165136, -15354458, -2294017, -1);
        renderGrupo(15940, -1, -1, -1, -1, -1, -1, -1, -200803, -12936140, -12003605);
        renderGrupo(15950, -65537, -1, -1, -1, -1, -1, -4, -2962091, -16081557, -6162178);
        renderGrupo(15960, -1, -1, -664206, -15819696, -8524804, -1, -1, -267374, -13657035, -11281420);
        renderGrupo(15970, -1, -1, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777);
        renderGrupo(15980, -1, -2, -1386391, -15885226, -7934211, -1, -1, -1, -1, -1);
        renderGrupo(15990, -267632, -13853645, -12463131, -131073, -1, -1, -572, -10051024, -15746893, -1114113);
    }

    public void render_160() {

        renderGrupo(16000, -1, -1, -1, -1, -1, -6, -4340926, -16079999, -4456961, -1);
        renderGrupo(16010, -1, -833, -10444752, -15615049, -851969, -1, -1, -1, -1, -399995);
        renderGrupo(16020, -14640333, -12200472, -131073, -1, -1, -664206, -15819696, -8524804, -1, -1);
        renderGrupo(16030, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1, -1, -1);
        renderGrupo(16040, -1, -1, -1, -1, -1, -1, -1, -1, -329765, -5124760);
        renderGrupo(16050, -14640335, -13841965, -262145, -1, -1, -1, -1, -8, -5522635, -16078191);
        renderGrupo(16060, -3342849, -1, -1, -1, -1, -1, -1, -7, -4997575, -16144761);
        renderGrupo(16070, -4063745, -1, -3, -2240163, -15950491, -6687234, -1, -1, -1, -1);
        renderGrupo(16080, -1, -1, -1, -67916, -11165648, -15287629, -2104702, -13123392, -917505, -1);
        renderGrupo(16090, -1, -1, -1, -1, -1, -1, -1, -1, -333168, -13853641);
    }

    public void render_161() {

        renderGrupo(16100, -11741494, -7752333, -5965826, -662398, -14574789, -11019578, -8473730, -4982017, -1, -1);
        renderGrupo(16110, -1, -1, -1, -1, -1, -1, -280, -5718426, -9975696, -14705604);
        renderGrupo(16120, -11741212, -196609, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16130, -1, -1083, -9788112, -15682135, -2365813, -12598590, -786433, -1, -1, -1);
        renderGrupo(16140, -1, -1, -1, -1, -14, -4537527, -16147116, -8590856, -1, -1);
        renderGrupo(16150, -328712, -327681, -1, -1, -1, -1, -1, -1087, -10182096, -15089470);
        renderGrupo(16160, -458753, -1, -1, -201063, -13132750, -12857120, -196609, -1, -1, -1);
        renderGrupo(16170, -1, -1, -1, -1, -285, -7294669, -16011621, -2621697, -1, -1);
        renderGrupo(16180, -1, -1, -1, -1, -1, -1, -1, -133692, -9129381, -9970205);
        renderGrupo(16190, -393217, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_162() {

        renderGrupo(16200, -1, -1, -1, -1, -1, -1, -833, -10379217, -15878742, -1572865);
        renderGrupo(16210, -1, -1, -1, -6, -4800451, -16278994, -16344530, -16344530, -16344530, -16344530);
        renderGrupo(16220, -16344530, -16344530, -16343987, -8787204, -1, -1, -1, -1, -1, -9);
        renderGrupo(16230, -1444367, -262145, -1, -1, -1, -835, -10576081, -15878225, -1245185, -1);
        renderGrupo(16240, -1, -1, -1, -552, -8344783, -15945310, -2162945, -1, -1, -289);
        renderGrupo(16250, -7622862, -16144245, -3736065, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16260, -266597, -12933768, -5244674, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16270, -1, -1, -400254, -14836940, -11937812, -65537, -1, -1, -565, -9394640);
        renderGrupo(16280, -15944794, -1835265, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16290, -1, -1, -728967, -15099073, -10362635, -1, -1, -1, -1, -1);
    }

    public void render_163() {

        renderGrupo(16300, -1, -200546, -12805073, -15745862, -524289, -1, -1, -1, -1, -1);
        renderGrupo(16310, -1, -1, -1, -1, -1, -598669, -15754702, -12725791, -196609, -1);
        renderGrupo(16320, -1, -1, -1, -1, -1, -1, -663947, -15492020, -8918533, -1);
        renderGrupo(16330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16340, -133978, -12215245, -12594461, -131073, -1, -1, -1, -1, -1, -1);
        renderGrupo(16350, -1, -6, -4340926, -16013429, -3801601, -1, -1, -1, -1, -1);
        renderGrupo(16360, -1, -1, -1, -1, -5, -3881400, -16014204, -4194817, -1, -1);
        renderGrupo(16370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16380, -1, -1, -1, -1, -1, -1, -1, -3, -921666, -7750052);
        renderGrupo(16390, -13586053, -7675683, -917761, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_164() {

        renderGrupo(16400, -1052, -4402301, -12211369, -11154764, -3213316, -1, -1, -1, -1, -1);
        renderGrupo(16410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16420, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16430, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16460, -292, -7949978, -6949891, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16480, -1, -1, -1, -1, -133418, -4726595, -5120067, -5120067, -5120066, -4789269);
        renderGrupo(16490, -131073, -1, -1, -1, -1, -1, -1, -1, -1, -200288);
    }

    public void render_165() {

        renderGrupo(16500, -12472424, -2883841, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16510, -1, -1, -1, -293, -8015772, -7081219, -1, -1, -1, -1);
        renderGrupo(16520, -1, -1, -1, -1, -1, -1, -1, -1, -523, -1049605);
        renderGrupo(16530, -1, -1, -1, -1, -1, -1, -1, -832, -10379203, -10231303);
        renderGrupo(16540, -1, -291, -7753927, -11084554, -1, -1, -597893, -15097478, -4916225, -1);
        renderGrupo(16550, -3, -2436766, -13121071, -524289, -1, -1, -1, -1, -1, -1);
        renderGrupo(16560, -1, -201063, -12930916, -2621697, -1, -663687, -14434627, -655361, -1, -1);
        renderGrupo(16570, -1, -1, -1, -1, -1, -1, -1, -1, -12, -4077991);
        renderGrupo(16580, -12135971, -327681, -552, -8344781, -14629687, -393217, -1, -400512, -14967722, -7934211);
        renderGrupo(16590, -1, -1, -1, -1, -1, -400252, -14705872, -14301491, -327681, -1);
    }

    public void render_166() {

        renderGrupo(16600, -1, -13, -3419799, -14771153, -15816875, -13589680, -9050121, -1, -1, -1);
        renderGrupo(16610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16630, -1, -288, -7556792, -9772041, -1, -1, -1, -1, -14, -5391043);
        renderGrupo(16640, -16213447, -11281682, -65537, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(16650, -16211071, -4456961, -1, -1, -1, -2, -1189008, -15427024, -14432821, -393217);
        renderGrupo(16660, -1, -1, -1, -1, -1, -1, -595565, -13329104, -14762823, -1507329);
        renderGrupo(16670, -1, -1, -1, -1, -1, -267115, -12139322, -393217, -1, -1);
        renderGrupo(16680, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
        renderGrupo(16690, -1, -1, -67388, -9787600, -16145029, -5113858, -1, -1, -1, -1);
    }

    public void render_167() {

        renderGrupo(16700, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1);
        renderGrupo(16710, -134491, -11287870, -393217, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(16720, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16730, -1, -1, -1, -1, -661360, -13460176, -14565443, -1376257, -1, -1);
        renderGrupo(16740, -1, -1, -559, -8869584, -16144761, -4063745, -1, -1, -1, -1);
        renderGrupo(16750, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1);
        renderGrupo(16760, -1, -333685, -14116048, -14301490, -327681, -1, -1, -1, -1, -1);
        renderGrupo(16770, -1, -1, -17, -6310092, -16343704, -6424834, -1, -1, -1, -1);
        renderGrupo(16780, -1, -3, -2630482, -4064770, -1, -333685, -14181317, -10756364, -1, -1);
        renderGrupo(16790, -1, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1);
    }

    public void render_168() {

        renderGrupo(16800, -1, -3, -1975953, -14902481, -15881334, -4130306, -1, -1, -1, -1);
        renderGrupo(16810, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
        renderGrupo(16820, -1, -2, -1452180, -11610390, -65537, -1, -1, -1, -1, -1);
        renderGrupo(16830, -556, -8606625, -7409155, -1, -2, -1319822, -15230162, -16277920, -7147011, -1);
        renderGrupo(16840, -1, -67408, -11625170, -15878485, -1441793, -1, -1, -1, -1, -1);
        renderGrupo(16850, -1, -556, -8606625, -7409155, -1, -1, -1, -1, -1068, -8344267);
        renderGrupo(16860, -16278994, -15811915, -786433, -1, -1, -1, -1, -1, -1, -2);
        renderGrupo(16870, -1121922, -14509260, -13251369, -458753, -1, -1, -1, -1, -547, -7556810);
        renderGrupo(16880, -16145290, -5441794, -1, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(16890, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_169() {

        renderGrupo(16900, -1, -1, -1, -2, -1056128, -14443724, -13120040, -393217, -1, -1);
        renderGrupo(16910, -1, -1, -546, -7360202, -16145287, -5244930, -1, -1, -1, -1);
        renderGrupo(16920, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -200019, -11558352);
        renderGrupo(16930, -16145032, -5376515, -1, -1, -1, -1, -1, -1, -1, -14);
        renderGrupo(16940, -6047129, -7015427, -1, -1, -1, -1, -794759, -15098800, -8459268, -1);
        renderGrupo(16950, -1, -1, -1, -1, -1, -1, -1, -1, -133978, -12215250);
        renderGrupo(16960, -15811915, -786433, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16970, -1, -13, -5128640, -16212915, -9181450, -1, -1, -1, -1, -544);
        renderGrupo(16980, -7162812, -11216147, -65537, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(16990, -1, -1, -2, -1385872, -15295953, -16278443, -8065539, -1, -1, -1);
    }

    public void render_170() {

        renderGrupo(17000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -10);
        renderGrupo(17010, -4734654, -16213456, -15090503, -1114113, -1, -1, -15, -5522372, -16213456, -14367285);
        renderGrupo(17020, -458753, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17030, -1, -67906, -10180006, -8328457, -1, -1, -2, -1252994, -14378193, -15815277);
        renderGrupo(17040, -3474433, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17050, -1, -1, -1097, -11100882, -15945310, -2162945, -1, -1, -1, -1);
        renderGrupo(17060, -1, -1, -1, -1, -1, -1, -200282, -12148689, -16210825, -5441794);
        renderGrupo(17070, -1, -1, -1, -1, -794758, -12202019, -196609, -1, -1, -1);
        renderGrupo(17080, -1, -466822, -15360951, -9181189, -1, -3, -2240163, -15950750, -6884099, -1);
        renderGrupo(17090, -1, -1, -1, -1, -1, -20, -6572492, -16011621, -2621697, -1);
    }

    public void render_171() {

        renderGrupo(17100, -1, -267116, -13525963, -12332060, -131073, -1, -1, -1, -1, -1346);
        renderGrupo(17110, -10378704, -15616597, -1769729, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17120, -1, -1, -133974, -12018128, -14958658, -851969, -1, -1, -664206, -15819696);
        renderGrupo(17130, -8524804, -1, -1, -1, -1, -1, -1, -1606, -10772432, -14892863);
        renderGrupo(17140, -786433, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17150, -1, -565, -9394639, -15155006, -458753, -1, -1, -1, -1, -1);
        renderGrupo(17160, -1, -2, -1055091, -12996199, -2884097, -1, -1, -1, -1, -1);
        renderGrupo(17170, -1, -1, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777);
        renderGrupo(17180, -1, -1, -664206, -15819696, -8524804, -1, -1, -1, -1, -1);
        renderGrupo(17190, -1, -134493, -12477389, -12200472, -131073, -1, -1, -1, -1, -1);
    }

    public void render_172() {

        renderGrupo(17200, -1, -835, -10641615, -14038832, -327681, -1, -1, -1, -1, -1);
        renderGrupo(17210, -1, -664206, -15819696, -8524804, -3, -2173593, -15295682, -11544346, -131073, -1);
        renderGrupo(17220, -1, -1, -1, -1, -1, -1, -200803, -12936140, -12003605, -65537);
        renderGrupo(17230, -1, -1, -1, -1, -1, -4, -2962091, -16081557, -6162178, -1);
        renderGrupo(17240, -1, -664206, -15819696, -8524804, -1, -1, -267374, -13657035, -11281420, -1);
        renderGrupo(17250, -1, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1);
        renderGrupo(17260, -2, -1386391, -15885226, -7934211, -1, -1, -1, -1, -1, -1347);
        renderGrupo(17270, -10509776, -14761274, -458753, -1, -1, -200545, -12739534, -13119780, -196609, -1);
        renderGrupo(17280, -1, -1, -1, -1, -6, -4340926, -16079999, -4456961, -1, -1);
        renderGrupo(17290, -266858, -13394892, -12528926, -131073, -1, -1, -1, -1, -68181, -11887056);
    }

    public void render_173() {

        renderGrupo(17300, -15024451, -917505, -1, -1, -664206, -15819696, -8524804, -1, -1, -1);
        renderGrupo(17310, -1, -1, -4, -3224751, -16081554, -5899777, -1, -1, -1, -1);
        renderGrupo(17320, -1, -1, -1, -1, -133943, -4460806, -1, -1, -283, -7163341);
        renderGrupo(17330, -15811913, -720897, -1, -1, -1, -1, -8, -5457099, -16078192, -3408385);
        renderGrupo(17340, -1, -1, -1, -1, -1, -1, -7, -4866244, -16145278, -4325889);
        renderGrupo(17350, -1, -3, -2240163, -15950491, -6687234, -1, -1, -1, -1, -1);
        renderGrupo(17360, -1, -1, -9, -4340665, -16147116, -12471975, -8853255, -1, -1, -1);
        renderGrupo(17370, -1, -1, -1, -1, -1, -1, -1, -552, -8278990, -15882420);
        renderGrupo(17380, -13320519, -1114113, -819, -9197775, -15751089, -12926270, -720897, -1, -1, -1);
        renderGrupo(17390, -1, -1, -1, -1, -4, -2370191, -11612201, -524567, -5916096, -16080275);
    }

    public void render_174() {

        renderGrupo(17400, -6228739, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17410, -5, -3552946, -16081835, -11946658, -9050119, -1, -1, -1, -1, -1);
        renderGrupo(17420, -1, -1, -1, -661363, -13657039, -13974070, -851969, -1, -13, -5452900);
        renderGrupo(17430, -3342849, -1, -1, -1, -1, -1, -14, -5259713, -15946345, -2949633);
        renderGrupo(17440, -1, -2, -1517462, -15688618, -8000003, -1, -1, -1, -1, -1);
        renderGrupo(17450, -1, -1, -1, -285, -7294669, -16011621, -2621697, -1, -1, -1);
        renderGrupo(17460, -1, -1, -1, -1, -1, -331079, -9981597, -8592147, -131073, -1);
        renderGrupo(17470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -67921);
        renderGrupo(17480, -10238781, -589825, -1, -1, -1, -134235, -12346320, -14958141, -589825, -1);
        renderGrupo(17490, -1, -1, -2, -1183793, -4069685, -4069685, -4069685, -4069685, -4137333, -13132752);
    }

    public void render_175() {

        renderGrupo(17500, -14303316, -4266285, -2163970, -1, -1, -1, -1, -1, -557, -8537966);
        renderGrupo(17510, -3473921, -1, -1, -1, -201063, -13132750, -13316647, -196609, -1, -1);
        renderGrupo(17520, -1, -1, -4, -3027883, -15949973, -6228226, -1, -1, -824, -9657296);
        renderGrupo(17530, -15418700, -1245185, -1, -1, -1, -1, -1, -1, -12, -4734649);
        renderGrupo(17540, -13645357, -393217, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17550, -1, -267116, -13460430, -12857121, -196609, -1, -1, -1091, -10575824, -15483975);
        renderGrupo(17560, -917505, -1, -1, -1, -1, -278, -3939368, -851969, -1, -1);
        renderGrupo(17570, -16, -5325504, -16013433, -4064257, -1, -1, -1, -1, -1, -1);
        renderGrupo(17580, -1613, -11428561, -15878224, -1179649, -1, -1, -1, -1, -1, -1);
        renderGrupo(17590, -1, -1, -1, -2, -1320854, -15885771, -11346957, -1, -1, -1);
    }

    public void render_176() {

        renderGrupo(17600, -1, -1, -1, -1, -1, -400770, -15033534, -9771782, -1, -1);
        renderGrupo(17610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -200803);
        renderGrupo(17620, -12936140, -12003605, -65537, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17630, -6, -4340926, -16013429, -3801601, -1, -1, -1, -1, -1, -1);
        renderGrupo(17640, -1, -1, -1, -5, -3881400, -16014204, -4194817, -1, -1, -1);
        renderGrupo(17650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17660, -1, -1, -1, -1, -1, -1, -1, -1, -2, -855872);
        renderGrupo(17670, -7618723, -13517388, -1572865, -1, -1, -1, -1, -1, -1084, -9524137);
        renderGrupo(17680, -11023177, -3081987, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17690, -1, -1, -1, -1, -789, -2691610, -786433, -1, -1, -1);
    }

    public void render_177() {

        renderGrupo(17700, -1, -1, -1, -1, -11, -1905187, -1573633, -1, -1, -1);
        renderGrupo(17710, -1, -1, -1, -1, -529, -2363934, -1114625, -1, -1, -1);
        renderGrupo(17720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -201063);
        renderGrupo(17740, -12733018, -1966337, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17770, -1, -1, -1, -1, -1, -1, -1, -1, -134750, -12144229);
        renderGrupo(17780, -2752769, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17790, -1, -1, -3, -2831013, -12200988, -131073, -1, -1, -1, -1);
    }

    public void render_178() {

        renderGrupo(17800, -1, -1, -1, -1, -1, -1, -1, -264223, -3083792, -196609);
        renderGrupo(17810, -1, -1, -1, -1, -1, -1, -571, -9919687, -10756360, -1);
        renderGrupo(17820, -565, -9460424, -10887688, -1, -16, -4406196, -15815014, -2687233, -1, -331348);
        renderGrupo(17830, -10897772, -3736833, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17840, -795276, -14434112, -524289, -3, -2831015, -12594977, -196609, -1, -1, -1);
        renderGrupo(17850, -1, -1, -1, -1, -1, -1, -2, -989816, -12995166, -2621953);
        renderGrupo(17860, -1, -283, -7163340, -15220543, -458753, -1, -598411, -15557276, -6818563, -1);
        renderGrupo(17870, -1, -1, -1, -1, -201062, -13132753, -15681876, -1572865, -1, -1);
        renderGrupo(17880, -1, -7, -2434702, -14574802, -16211872, -7935501, -65537, -1, -1, -1);
        renderGrupo(17890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_179() {

        renderGrupo(17900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(17910, -200804, -12801655, -3998465, -1, -1, -1, -1, -1, -860037, -14968017);
        renderGrupo(17920, -15616083, -1572865, -1, -1, -1, -1, -1, -556, -8607183, -16211071);
        renderGrupo(17930, -4456961, -1, -1, -5, -1381719, -10836174, -16145300, -6426115, -1, -1);
        renderGrupo(17940, -1, -1, -1, -1, -1, -5, -2171520, -13459398, -13056842, -2819587);
        renderGrupo(17950, -1, -1, -1, -526894, -6242222, -14170934, -327681, -1, -1, -1);
        renderGrupo(17960, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -8);
        renderGrupo(17970, -1644112, -9523396, -16080286, -7935758, -65537, -1, -1, -1, -1, -1);
        renderGrupo(17980, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1, -1057677);
        renderGrupo(17990, -13580595, -327681, -1, -1, -1, -1, -1, -1, -556, -8607183);
    }

    public void render_180() {

        renderGrupo(18000, -16211071, -4456961, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18010, -1, -1, -1, -6, -2368642, -13590724, -12794182, -2557186, -1, -1);
        renderGrupo(18020, -1, -527179, -10574800, -16144761, -4063745, -1, -1, -1, -1, -1);
        renderGrupo(18030, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1, -1);
        renderGrupo(18040, -333685, -14116048, -14301490, -327681, -1, -1, -1, -1, -1, -1);
        renderGrupo(18050, -1, -17, -6310092, -16343704, -6424834, -1, -1, -1, -1, -1);
        renderGrupo(18060, -6, -4340648, -9903372, -5, -2436769, -15488373, -3998721, -1, -1, -1);
        renderGrupo(18070, -1, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1);
        renderGrupo(18080, -1, -19, -4997048, -16082384, -14434628, -1507585, -1, -1, -1, -1);
        renderGrupo(18090, -1, -1, -1, -556, -8607183, -16211071, -4456961, -1, -1, -1);
    }

    public void render_181() {

        renderGrupo(18100, -7, -4603578, -11281421, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(18110, -8606625, -7409155, -1, -1, -1083, -9853647, -15024967, -1245185, -1, -1);
        renderGrupo(18120, -67408, -11625170, -15878485, -1441793, -1, -1, -1, -1, -1, -1);
        renderGrupo(18130, -556, -8606625, -7409155, -1, -1, -1, -1, -1, -595566, -13329105);
        renderGrupo(18140, -15811915, -786433, -1, -1, -1, -1, -1, -1, -1, -14);
        renderGrupo(18150, -3814048, -15032756, -10627115, -1245441, -1, -1, -132647, -6111151, -15621797, -8591889);
        renderGrupo(18160, -65537, -1, -1, -1, -1, -1, -1, -1, -556, -8607183);
        renderGrupo(18170, -16211071, -4456961, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18180, -1, -1, -1, -13, -3748255, -14967217, -10430506, -1179905, -1, -1);
        renderGrupo(18190, -132646, -5979822, -15556000, -8198159, -65537, -1, -1, -1, -1, -1);
    }

    public void render_182() {

        renderGrupo(18200, -1, -556, -8607183, -16211071, -4456961, -1, -1, -3, -1844624, -14902480);
        renderGrupo(18210, -14960209, -2031873, -1, -1, -1, -1, -1, -1, -14, -6047685);
        renderGrupo(18220, -13647956, -3475718, -1, -1, -330299, -9064903, -14763340, -1835265, -1, -1);
        renderGrupo(18230, -1, -1, -1, -1, -1, -1, -1, -133978, -12215250, -15811915);
        renderGrupo(18240, -786433, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18250, -1, -265811, -11492304, -16013977, -8463152, -2361871, -1050648, -2693452, -8472759, -14896224);
        renderGrupo(18260, -3015425, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18270, -1, -1, -1341, -9984976, -15682393, -1966337, -1, -1, -1, -1);
        renderGrupo(18280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -728450);
        renderGrupo(18290, -14836670, -10034443, -1, -1, -1, -1, -860037, -14967737, -9443847, -1);
    }

    public void render_183() {

        renderGrupo(18300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -280);
        renderGrupo(18310, -5850291, -12267045, -393217, -1, -1, -1, -18, -5128379, -16147915, -13054507);
        renderGrupo(18320, -524289, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18330, -1, -1097, -11100882, -15945310, -2162945, -1, -1, -1, -1, -1);
        renderGrupo(18340, -1, -1, -1, -1, -281, -6178754, -16213186, -11478810, -131073, -1);
        renderGrupo(18350, -1, -1, -9, -4012463, -11938326, -65537, -1, -1, -1, -1);
        renderGrupo(18360, -267889, -13919180, -13186873, -1836556, -1380147, -6767286, -16147358, -6884099, -1, -1);
        renderGrupo(18370, -1, -1, -1, -1, -20, -6572492, -16011621, -2621697, -1, -1057);
        renderGrupo(18380, -6571710, -15816316, -4523778, -1, -1, -1, -1, -1, -7, -3027107);
        renderGrupo(18390, -15492289, -12268868, -2951432, -65537, -66061, -1707285, -720897, -1, -1, -1);
    }

    public void render_184() {

        renderGrupo(18400, -1, -19, -5456574, -16147118, -9839395, -983297, -197653, -3420832, -15950768, -8524804);
        renderGrupo(18410, -1, -1, -1, -1, -1, -1, -12, -4143796, -16016050, -10299180);
        renderGrupo(18420, -1442562, -3, -591639, -1640197, -1, -1, -1, -1, -1, -1);
        renderGrupo(18430, -565, -9394639, -15155006, -458753, -1, -1, -1, -1, -1, -1);
        renderGrupo(18440, -287, -7425997, -16079518, -9844072, -7942503, -7942502, -7811166, -6562094, -1704705, -1);
        renderGrupo(18450, -1, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1);
        renderGrupo(18460, -1, -664206, -15819696, -8524804, -1, -1, -1, -1, -1, -1);
        renderGrupo(18470, -134493, -12477389, -12200472, -131073, -1, -1, -1, -1, -1, -1);
        renderGrupo(18480, -835, -10641615, -14038832, -327681, -1, -1, -1, -1, -1, -1);
        renderGrupo(18490, -664206, -15819696, -8524804, -1, -547, -7491273, -16145550, -5769731, -1, -1);
    }

    public void render_185() {

        renderGrupo(18500, -1, -1, -1, -1, -1, -200803, -12936140, -12003605, -65537, -1);
        renderGrupo(18510, -1, -1, -1, -1, -4, -2962091, -16081557, -6162178, -1, -1);
        renderGrupo(18520, -664206, -15819696, -8524804, -1, -1, -267374, -13657035, -11281420, -1, -1);
        renderGrupo(18530, -1, -1, -1, -1, -4, -3224751, -16081554, -5899777, -1, -2);
        renderGrupo(18540, -1386391, -15885226, -7934211, -1, -1, -1, -1, -1, -8, -3289768);
        renderGrupo(18550, -15555988, -6557191, -1, -534, -5062325, -15948684, -5638659, -1, -1, -1);
        renderGrupo(18560, -1, -1, -1, -6, -4340926, -16079999, -4456961, -1, -538, -5784507);
        renderGrupo(18570, -15948163, -4982786, -1, -1, -1, -1, -1, -18, -5259709, -16147120);
        renderGrupo(18580, -9970724, -1048833, -66062, -2502043, -15885232, -8524804, -1, -1, -1, -1);
        renderGrupo(18590, -1, -4, -3224751, -16081554, -5899777, -1, -1, -1, -1, -1);
    }

    public void render_186() {

        renderGrupo(18600, -1, -1, -1, -267632, -12403275, -1704449, -1, -67129, -9590730, -12922915);
        renderGrupo(18610, -196609, -1, -1, -1, -1, -6, -4603585, -16146328, -6753542, -131594);
        renderGrupo(18620, -590337, -1, -1, -1, -1, -3, -2830762, -16016568, -10626858, -1837591);
        renderGrupo(18630, -2101553, -6176691, -16081563, -6687234, -1, -1, -1, -1, -1, -1);
        renderGrupo(18640, -1, -1, -266856, -13263825, -15880034, -2490625, -1, -1, -1, -1);
        renderGrupo(18650, -1, -1, -1, -1, -1, -1, -3, -2699432, -16016575, -10231564);
        renderGrupo(18660, -1, -5, -3356080, -16082109, -9837577, -1, -1, -1, -1, -1);
        renderGrupo(18670, -1, -1, -1, -529253, -11683160, -2425345, -1, -134484, -11689680, -14894156);
        renderGrupo(18680, -1704193, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18690, -200803, -12936145, -16077676, -3080705, -1, -1, -1, -1, -1, -1);
    }

    public void render_187() {

        renderGrupo(18700, -1, -552, -8016075, -16145027, -4917250, -1, -1, -1598, -9782366, -2228481);
        renderGrupo(18710, -1, -1, -1, -1, -1, -1, -332386, -12541880, -10231829, -131073);
        renderGrupo(18720, -67125, -9131211, -14303041, -1376257, -1, -1, -1, -1, -1, -1);
        renderGrupo(18730, -1, -1, -285, -7294669, -16011621, -2621697, -1, -1, -1, -1);
        renderGrupo(18740, -1, -1, -1, -67907, -10180804, -13320552, -7220317, -7154781, -7154781, -7154781);
        renderGrupo(18750, -7154778, -5510157, -65537, -1, -1, -1, -1, -1, -1612, -11163006);
        renderGrupo(18760, -4786180, -1, -1, -1055, -5718196, -15949728, -7475718, -1, -1, -1);
        renderGrupo(18770, -1, -1, -1, -1, -1, -1, -1, -68182, -12083919, -13579306);
        renderGrupo(18780, -262145, -1, -1, -1, -1, -1, -1, -551, -8212900, -8328461);
        renderGrupo(18790, -131073, -1, -66853, -6636988, -15882371, -5114114, -1, -1, -1, -1);
    }

    public void render_188() {

        renderGrupo(18800, -1, -1, -67644, -9590473, -13777724, -1376513, -3, -1647241, -14639793, -9181452);
        renderGrupo(18810, -1, -1, -1, -1, -1, -1, -1, -134490, -12148123, -6818819);
        renderGrupo(18820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18830, -548, -7491272, -15880815, -3606018, -1, -8, -2566806, -15098809, -9969167, -65537);
        renderGrupo(18840, -1, -1, -1, -1, -561, -8998786, -4850945, -1, -11, -2697100);
        renderGrupo(18850, -14311599, -9444369, -65537, -1, -1, -1, -1, -1, -1, -556);
        renderGrupo(18860, -8607183, -16077675, -3080705, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18870, -1, -1, -4, -3224751, -16081839, -8459268, -1, -1, -1, -1);
        renderGrupo(18880, -1, -1, -1, -1, -400770, -15033534, -9771782, -1, -1, -1);
        renderGrupo(18890, -1, -1, -1, -1, -1, -1, -1, -1, -200803, -12936140);
    }

    public void render_189() {

        renderGrupo(18900, -12003605, -65537, -1, -1, -1, -1, -1, -1, -1, -6);
        renderGrupo(18910, -4340926, -16013429, -3801601, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18920, -1, -1, -5, -3881400, -16014204, -4194817, -1, -1, -1, -1);
        renderGrupo(18930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2);
        renderGrupo(18950, -657166, -458753, -1, -1, -1, -1, -1, -269, -1115651, -1);
        renderGrupo(18960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(18970, -1, -1, -11, -4865983, -16213446, -11413013, -65537, -1, -1, -1);
        renderGrupo(18980, -1, -1, -2, -1582739, -15361488, -14761532, -655361, -1, -1, -1);
        renderGrupo(18990, -1, -1, -5, -3618740, -16147919, -14433597, -917505, -1, -1, -1);
    }

    public void render_190() {

        renderGrupo(19000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19010, -1, -1, -1, -1, -1, -1, -1, -3, -2896549, -12004122);
        renderGrupo(19020, -131073, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19030, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19040, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19070, -1, -1, -201063, -12799069, -2162945, -1, -1, -1, -1, -1);
        renderGrupo(19080, -1, -1, -1, -1, -1, -554, -8344781, -16278186, -8000004, -1);
        renderGrupo(19090, -1, -1, -1, -1, -1, -288, -7557322, -12988451, -196609, -561);
    }

    public void render_191() {

        renderGrupo(19100, -9066701, -14500956, -5120602, -9127060, -12145349, -15946610, -4263717, -4007278, -10633834, -4393475);
        renderGrupo(19110, -1, -1, -1, -1, -1, -1, -1, -1, -4, -3159211);
        renderGrupo(19120, -12398110, -131073, -11, -5522360, -10099975, -1, -1, -1, -1, -1);
        renderGrupo(19130, -1, -1, -1, -1, -1, -67130, -9523609, -7016196, -1, -1);
        renderGrupo(19140, -3, -2830503, -15619184, -3474177, -10, -4209590, -15552096, -2490625, -1, -1);
        renderGrupo(19150, -1, -1, -1, -281, -5981630, -16147386, -11217708, -1179905, -1, -5);
        renderGrupo(19160, -1381450, -8932011, -12074893, -13394129, -16211878, -8920085, -131073, -1, -1, -1);
        renderGrupo(19170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19180, -1, -1, -1, -1, -1, -1, -1, -17, -4074619, -10899647);
        renderGrupo(19190, -15948448, -10369640, -5115395, -1, -1, -4, -2696047, -9585574, -14902482, -16278458);
    }

    public void render_192() {

        renderGrupo(19200, -12599932, -7937562, -131073, -1, -2, -1251937, -10044834, -14311889, -16278469, -14505650);
        renderGrupo(19210, -13850290, -13850291, -14113470, -14636709, -11418462, -4459527, -1, -1, -1, -1);
        renderGrupo(19220, -1, -1, -1, -1, -1, -1, -658746, -6895756, -12210343, -12470937);
        renderGrupo(19230, -11815063, -12012969, -13587362, -11485302, -7215895, -131073, -1, -1, -1, -1);
        renderGrupo(19240, -2, -1186142, -9782176, -14180817, -16278727, -14702517, -14112693, -14112952, -14506684, -14176922);
        renderGrupo(19250, -10368337, -3672581, -1, -1, -1, -1, -1, -1, -2, -1186140);
        renderGrupo(19260, -9519773, -14049745, -16278469, -14505650, -13850290, -13850290, -13850292, -14178750, -15229388, -13513513);
        renderGrupo(19270, -262145, -1, -1, -1, -1, -1, -266574, -9257372, -14049745, -16278459);
        renderGrupo(19280, -12797319, -9779020, -2818561, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19290, -1, -1, -1, -1, -724282, -6961548, -12210342, -12405401, -11946912, -12800170);
    }

    public void render_193() {

        renderGrupo(19300, -13127320, -10565988, -6232863, -786433, -1, -1, -1, -1, -1, -2);
        renderGrupo(19310, -1186140, -9519773, -14049745, -16278458, -12600190, -7871767, -131073, -18, -4206205, -10702007);
        renderGrupo(19320, -15754705, -15751328, -10763363, -4393475, -1, -1, -1, -1, -1, -266574);
        renderGrupo(19330, -9323160, -13590224, -16344259, -13650313, -9381414, -262145, -1, -1, -1, -4);
        renderGrupo(19340, -3355042, -14704062, -13914792, -12666480, -5246471, -1, -1, -1, -1, -1);
        renderGrupo(19350, -1, -2, -1186143, -9913506, -14311889, -16278461, -13059975, -9513260, -655361, -1);
        renderGrupo(19360, -1, -67124, -9000140, -16278726, -13912712, -8331033, -131073, -1, -1, -1);
        renderGrupo(19370, -2, -1120346, -9322650, -13983953, -16278466, -13980072, -13062568, -13062568, -13194160, -14047939);
        renderGrupo(19380, -15754440, -10625031, -1, -1, -1, -1, -2, -1186143, -9913506, -14311878);
        renderGrupo(19390, -13978764, -9841973, -1179649, -5, -3421331, -8853256, -1, -8, -3287163, -10701231);
    }

    public void render_194() {

        renderGrupo(19400, -15099090, -16211632, -11879035, -6361861, -1, -1, -1, -2, -1186143, -9913506);
        renderGrupo(19410, -14311878, -13978764, -9841973, -1179649, -1, -1, -1, -10, -3683757, -15418699);
        renderGrupo(19420, -786433, -1, -1, -1, -1, -1, -1, -1, -1, -6);
        renderGrupo(19430, -1710421, -8930207, -13193894, -12471454, -12275110, -13259425, -11024730, -4197383, -1, -1);
        renderGrupo(19440, -1, -1, -1, -1, -1, -1, -266832, -9520031, -14180817, -16278460);
        renderGrupo(19450, -12928905, -9777987, -2097409, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19460, -1, -1, -1, -6, -1644629, -8930206, -13128358, -12471454, -12275632, -14836177);
        renderGrupo(19470, -16144773, -5376774, -1, -1, -1, -1, -1, -1, -1, -200775);
        renderGrupo(19480, -8403858, -13590480, -16278197, -12074873, -8529969, -1245185, -1, -278, -5718974, -16213191);
        renderGrupo(19490, -13584253, -8266015, -196609, -1, -1, -1, -1, -5, -2433131, -9914017);
    }

    public void render_195() {

        renderGrupo(19500, -13391278, -12995998, -12275110, -13324958, -10498626, -2294785, -1, -1, -1, -1);
        renderGrupo(19510, -1, -1, -1, -1, -1328, -6830726, -11160754, -15295698, -16211374, -11879556);
        renderGrupo(19520, -9250343, -262145, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19530, -1, -396346, -7290005, -12998849, -15557327, -16015813, -14570395, -10236227, -2425857, -1);
        renderGrupo(19540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(19550, -1, -6, -3487139, -10756881, -65537, -1, -1, -1, -1, -1);
        renderGrupo(19560, -1, -1, -1, -1, -1, -1, -1, -1, -1085, -9850743);
        renderGrupo(19570, -4064001, -1, -1, -1, -1, -1082, -9588601, -4129537, -1, -1);
        renderGrupo(19580, -1, -1, -1, -1, -1, -1, -3, -2236785, -10702004, -15426496);
        renderGrupo(19590, -13190786, -7674643, -65537, -1, -12, -3746686, -10964665, -15885778, -16278203, -12797311);
    }

    public void render_196() {

        renderGrupo(19600, -6755592, -1, -1, -1, -1, -1, -1, -1, -548, -5978243);
        renderGrupo(19610, -10963374, -15033554, -16211892, -12273030, -9513520, -917505, -1, -1, -1, -1);
        renderGrupo(19620, -1, -1, -1, -133977, -12215250, -16278722, -13979813, -12799909, -12799911, -13194160);
        renderGrupo(19630, -14047421, -15163856, -16278987, -11084554, -1, -1, -1, -1, -1, -280);
        renderGrupo(19640, -5126562, -14574029, -15884217, -12862312, -5254026, -15230149, -13649773, -4786691, -1, -1);
        renderGrupo(19650, -1, -1, -1, -8, -2827376, -10373020, -12340642, -12800169, -13588144, -12599654);
        renderGrupo(19660, -4590597, -1, -1, -1, -1, -1, -1, -1, -16, -3418233);
        renderGrupo(19670, -11555514, -15295181, -15621561, -13914521, -9972528, -1114113, -1, -1, -1, -1);
        renderGrupo(19680, -1, -67114, -6503581, -13917642, -15950535, -14767259, -10174881, -15164618, -14306429, -5902083);
        renderGrupo(19690, -1, -1, -1, -1, -1, -1, -798, -5125261, -12867779, -15753927);
    }

    public void render_197() {

        renderGrupo(19700, -14833838, -12469621, -5902601, -1, -1, -1, -1, -1, -133690, -7552411);
        renderGrupo(19710, -14181073, -16013988, -10894706, -6887699, -65537, -1, -1, -1, -1, -2);
        renderGrupo(19720, -1383277, -12475856, -16344530, -16344530, -16344530, -16344530, -16344528, -15092054, -2294017, -1);
        renderGrupo(19730, -1, -1, -1, -3, -2170728, -10572229, -16213177, -12073564, -4131331, -1316952);
        renderGrupo(19740, -9193912, -16147653, -13123940, -4590339, -1, -1, -1, -3, -1973601, -9060774);
        renderGrupo(19750, -14968016, -14897031, -9055804, -1900801, -1, -1, -1, -1, -1, -835);
        renderGrupo(19760, -10641615, -14038832, -327681, -1, -1, -1, -1, -2, -1054293, -9325241);
        renderGrupo(19770, -16147654, -13255002, -3737090, -1068, -6965177, -16147920, -14962562, -7149842, -65537, -1);
        renderGrupo(19780, -1, -1, -3, -2039134, -8732837, -15033552, -14634111, -8464950, -1638657, -1);
        renderGrupo(19790, -1, -1, -3, -1908069, -10440899, -16213179, -12205162, -5312263, -2301798, -9456312);
    }

    public void render_198() {

        renderGrupo(19800, -16147653, -13255537, -6625042, -1316693, -8799147, -15295696, -14436983, -6690576, -65537, -1);
        renderGrupo(19810, -1, -1, -3, -1710946, -10441157, -16213177, -12073564, -4131332, -1579614, -9522363);
        renderGrupo(19820, -16147650, -12926816, -4327939, -1, -1, -1, -1, -1, -276, -4009346);
        renderGrupo(19830, -12210601, -12536732, -12472748, -12731759, -5377801, -1, -1, -1, -1, -1);
        renderGrupo(19840, -1, -1, -6, -4340926, -16146872, -12667030, -11815580, -12604080, -12993904, -5312264);
        renderGrupo(19850, -1, -1, -1, -1, -1, -1, -1, -66856, -6372508, -13917642);
        renderGrupo(19860, -15950534, -14635926, -9584802, -15492016, -8524804, -1, -1, -1, -1, -3);
        renderGrupo(19870, -2170728, -10572229, -16213178, -12205422, -6625298, -65537, -1, -1, -1, -1);
        renderGrupo(19880, -1, -1, -134474, -9653421, -13783972, -12274590, -12669351, -11548487, -2425601, -1);
        renderGrupo(19890, -1, -1, -1, -1, -1, -660835, -12016330, -15884221, -13716598, -5048835);
    }

    public void render_199() {

        renderGrupo(19900, -1, -1, -1, -1, -1, -67641, -8473528, -15492043, -15227047, -11418466);
        renderGrupo(19910, -6568100, -15951043, -13452651, -4655619, -1, -1, -1, -1, -1, -1);
        renderGrupo(19920, -1, -280, -6572222, -11544085, -65537, -1, -1, -1, -1, -1);
        renderGrupo(19930, -1, -1, -1, -1, -1, -1, -134751, -12474494, -4391937, -1);
        renderGrupo(19940, -1, -201063, -13064316, -4260609, -1, -1, -1, -1, -1, -1);
        renderGrupo(19950, -17, -3811699, -11491010, -13255271, -4984323, -5, -2761585, -11425484, -16278720, -12730214);
        renderGrupo(19960, -4787459, -1, -1, -1, -1, -1, -1, -1, -1, -280);
        renderGrupo(19970, -6703812, -12594719, -196609, -1, -1, -1, -1, -1, -1, -2);
        renderGrupo(19980, -1648533, -15427025, -15817387, -12340382, -12274846, -12538288, -14639311, -15812431, -1114113, -1);
        renderGrupo(19990, -1, -1, -1, -1, -1, -3, -1382751, -10440105, -12864670, -12800679);
    }

    public void render_200() {

        renderGrupo(20000, -11417158, -2360321, -1, -1, -1, -1, -1, -1, -1, -266572);
        renderGrupo(20010, -8928388, -10765983, -13983696, -16212149, -12470156, -10304884, -6230791, -1, -1, -1);
        renderGrupo(20020, -1, -1, -267115, -13460434, -16344530, -16344530, -16344530, -16344530, -16344530, -16344525);
        renderGrupo(20030, -12660254, -131073, -1, -1, -1, -1, -1, -556, -7095198, -13260466);
        renderGrupo(20040, -13389988, -12734634, -13653678, -12403309, -5640460, -65537, -1, -1, -1, -1);
        renderGrupo(20050, -1, -1, -1, -1, -1, -1, -68182, -12083919, -13579306, -262145);
        renderGrupo(20060, -1, -1, -1, -1, -1, -1, -277, -5060762, -13260729, -14243243);
        renderGrupo(20070, -12996778, -13587882, -11877726, -4197125, -1, -1, -1, -1, -1, -1);
        renderGrupo(20080, -1, -1, -396092, -7881377, -13127838, -12144039, -13258380, -8134170, -327681, -1);
        renderGrupo(20090, -1, -1, -1, -1, -1, -8, -3881139, -14565184, -983041, -1);
    }

    public void render_201() {

        renderGrupo(20100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20110, -133426, -6962585, -13128615, -12340123, -12341421, -13389195, -8265759, -589825, -1, -1);
        renderGrupo(20120, -1, -1, -1, -291, -6767786, -14047411, -13390247, -12997551, -13060217, -6690321);
        renderGrupo(20130, -196609, -1, -1, -1, -1, -1, -1, -1, -7, -4012472);
        renderGrupo(20140, -16146576, -5768962, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20150, -1, -16, -5981897, -16144502, -3932673, -1, -1, -1, -1, -1);
        renderGrupo(20160, -1, -1, -1, -400769, -15033534, -9771782, -1, -1, -1, -1);
        renderGrupo(20170, -1, -1, -1, -1, -1, -1, -1, -200803, -12936140, -11938069);
        renderGrupo(20180, -65537, -1, -1, -1, -1, -1, -1, -1, -6, -4340926);
        renderGrupo(20190, -16013429, -3801601, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_202() {

        renderGrupo(20200, -1, -5, -3881400, -16014204, -4194817, -1, -1, -1, -1, -1);
        renderGrupo(20210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20250, -1, -6, -3289250, -14966445, -9050892, -1, -1, -1, -1, -1);
        renderGrupo(20260, -1, -1, -924533, -13459136, -12398376, -327681, -1, -1, -1, -1);
        renderGrupo(20270, -1, -3, -1515126, -13000400, -16011363, -2490625, -1, -1, -1, -1);
        renderGrupo(20280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20290, -1, -1, -1, -1, -1, -1, -294, -8081305, -6884355, -1);
    }

    public void render_203() {

        renderGrupo(20300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20350, -1, -293, -8015774, -7212291, -1, -1, -1, -1, -1, -1);
        renderGrupo(20360, -1, -1, -1, -1, -283, -6177978, -15358346, -5835523, -1, -1);
        renderGrupo(20370, -1, -1, -1, -1, -4, -3421617, -15617116, -2097409, -7, -3157660);
        renderGrupo(20380, -14770370, -13846902, -6033937, -4078006, -15556024, -13388679, -8135465, -1311233, -1, -1);
        renderGrupo(20390, -1, -1, -1, -1, -1, -1, -1, -14, -5850553, -9903111);
    }

    public void render_204() {

        renderGrupo(20400, -1, -554, -8409758, -7081219, -1, -1, -1, -1, -1, -1);
        renderGrupo(20410, -1, -1, -1, -15, -4537256, -11741727, -262145, -1, -1, -1);
        renderGrupo(20420, -1059, -5913497, -12536204, -10437788, -12601718, -5443079, -1, -1, -1, -1);
        renderGrupo(20430, -1, -1, -1, -796, -4730752, -11751862, -14571953, -13258918, -12800162, -11944301);
        renderGrupo(20440, -6034706, -262147, -1514873, -13394385, -16278210, -13914007, -8723985, -65537, -1, -1);
        renderGrupo(20450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_205() {

        renderGrupo(20500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20510, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20520, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_206() {

        renderGrupo(20600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20690, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_207() {

        renderGrupo(20700, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20710, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20740, -1, -1, -1, -1, -1, -1, -1, -67904, -9983950, -16278724);
        renderGrupo(20750, -12201511, -524289, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_208() {

        renderGrupo(20800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20830, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_209() {

        renderGrupo(20900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20920, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(20990, -1, -1, -1, -1, -1, -1, -1, -1, -9, -3025810);
    }

    public void render_210() {

        renderGrupo(21000, -12204346, -1179649, -1, -1, -530, -4143278, -16081824, -7080963, -1, -1);
        renderGrupo(21010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21020, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21030, -1, -1, -1, -1, -1, -1, -1, -1, -835, -10641871);
        renderGrupo(21040, -13841708, -262145, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21080, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_211() {

        renderGrupo(21100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21120, -1, -6, -4340926, -16079741, -4260353, -1, -1, -1, -1, -1);
        renderGrupo(21130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21140, -1, -532876, -15754160, -8524804, -1, -1, -1, -1, -1, -1);
        renderGrupo(21150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_212() {

        renderGrupo(21200, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21240, -1, -1, -1, -1, -1, -1, -1, -1, -285, -7162001);
        renderGrupo(21250, -6425347, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21260, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21270, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21280, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_213() {

        renderGrupo(21300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21310, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21320, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21360, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_214() {

        renderGrupo(21400, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21410, -1, -1, -1, -1, -1, -1, -1, -1, -200802, -12739012);
        renderGrupo(21420, -11019025, -65537, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21430, -67660, -11231178, -12660258, -196609, -1, -1, -1, -1, -1, -1);
        renderGrupo(21440, -1, -1, -333685, -14246850, -10099974, -1, -1, -1, -1, -1);
        renderGrupo(21450, -1, -1, -1, -1, -1, -1, -201320, -13263817, -11084556, -1);
        renderGrupo(21460, -1, -1, -1, -1, -1, -1, -1, -6, -4340926, -16013429);
        renderGrupo(21470, -3801601, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21480, -5, -3881400, -16014204, -4194817, -1, -1, -1, -1, -1, -1);
        renderGrupo(21490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_215() {

        renderGrupo(21500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21510, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21520, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21550, -1, -3, -1977502, -14302522, -589825, -1, -1, -1, -1, -1);
        renderGrupo(21560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_216() {

        renderGrupo(21600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21650, -1, -1, -1, -1, -200283, -12279732, -9443852, -1, -1, -1);
        renderGrupo(21660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21690, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_217() {

        renderGrupo(21700, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21710, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21720, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21730, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21740, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21760, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_218() {

        renderGrupo(21800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21830, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_219() {

        renderGrupo(21900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21920, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21930, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21940, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(21990, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_220() {

        renderGrupo(22000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22020, -1, -1, -1, -1, -1, -1, -1, -726116, -12476368, -16278459);
        renderGrupo(22030, -11415090, -1573377, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22040, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22050, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22060, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22070, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22080, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22090, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_221() {

        renderGrupo(22100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22130, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22140, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22150, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22160, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22170, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22180, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22190, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_222() {

        renderGrupo(22200, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22210, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22220, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22230, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22260, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22270, -1, -1, -1, -1, -1, -1, -1, -133976, -12148910, -8327940);
        renderGrupo(22280, -1, -1, -1, -2, -1255061, -15819155, -6096898, -1, -1, -1);
        renderGrupo(22290, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_223() {

        renderGrupo(22300, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22310, -1, -1, -1, -1, -1, -65795, -65537, -1612, -11363021, -12332058);
        renderGrupo(22320, -131073, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22330, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22340, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22350, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22360, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22370, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22380, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22390, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_224() {

        renderGrupo(22400, -6, -4340926, -16079999, -4456961, -1, -1, -1, -1, -1, -1);
        renderGrupo(22410, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22420, -664206, -15819696, -8524804, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22430, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22440, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22450, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22460, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22470, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22480, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22490, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_225() {

        renderGrupo(22500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22510, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22520, -1, -1, -1, -1, -262920, -262145, -1, -398701, -12205891, -1048577);
        renderGrupo(22530, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22540, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22550, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22560, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22570, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22580, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22590, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_226() {

        renderGrupo(22600, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22610, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22620, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22630, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22640, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22650, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22660, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22670, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22680, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22690, -1, -1, -1, -1, -1, -1, -1, -8, -3355301, -15160176);
    }

    public void render_227() {

        renderGrupo(22700, -3605505, -1, -1, -1, -1, -1, -1, -1, -10, -3749806);
        renderGrupo(22710, -15158886, -3212289, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22720, -1, -1081, -9591244, -13382698, -458753, -1, -1, -1, -1, -1);
        renderGrupo(22730, -1, -1, -1, -1, -3, -1845400, -15425171, -6163203, -1, -1);
        renderGrupo(22740, -1, -1, -1, -1, -1, -1, -6, -4340926, -16013429, -3801601);
        renderGrupo(22750, -1, -1, -1, -1, -1, -1, -1, -1, -1, -5);
        renderGrupo(22760, -3881400, -16014204, -4194817, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22770, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22780, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22790, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_228() {

        renderGrupo(22800, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22810, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22820, -1, -1, -1, -1, -1, -1, -1, -1, -1, -9);
        renderGrupo(22830, -1972829, -10570112, -5376771, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22840, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22850, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22860, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22870, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22880, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22890, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_229() {

        renderGrupo(22900, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22910, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22920, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22930, -1, -1, -1, -7, -2961055, -15030410, -5901830, -1, -1, -1);
        renderGrupo(22940, -1, -1, -1, -1, -198696, -3345928, -1, -1, -1, -1);
        renderGrupo(22950, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22960, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22970, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22980, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(22990, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
    }

    public void render_230() {

        renderGrupo(23000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        renderGrupo(23010, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        mPixels[23020] = -1;
        mPixels[23020] = -1;

    }

    private void renderGrupo(int ePos, int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {

        mPixels[ePos + 0] = a;

        mPixels[ePos + 1] = b;

        mPixels[ePos + 2] = c;

        mPixels[ePos + 3] = d;

        mPixels[ePos + 4] = e;

        mPixels[ePos + 5] = f;

        mPixels[ePos + 6] = g;

        mPixels[ePos + 7] = h;

        mPixels[ePos + 8] = i;

        mPixels[ePos + 9] = j;

    }
}
