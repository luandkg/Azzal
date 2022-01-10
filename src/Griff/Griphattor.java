package Griff;

public class Griphattor {

    private Griph ESPACO;
    private Griph A;
    private Griph B;
    private Griph C;
    private Griph D;
    private Griph E;
    private Griph F;
    private Griph G;
    private Griph H;
    private Griph I;
    private Griph J;
    private Griph K;
    private Griph L;
    private Griph M;
    private Griph N;
    private Griph O;
    private Griph P;
    private Griph Q;
    private Griph R;
    private Griph S;
    private Griph T;
    private Griph U;
    private Griph V;
    private Griph W;
    private Griph X;
    private Griph Y;
    private Griph Z;

    private Griph ESPECIAL_;
    private Griph HIFEN;
    private Griph PONTO;
    private Griph DOIS_PONTO;
    private Griph MAIOR;
    private Griph IGUAL;
    private Griph ORDEM;

    private Griph N0;
    private Griph N1;
    private Griph N2;
    private Griph N3;
    private Griph N4;
    private Griph N5;
    private Griph N6;
    private Griph N7;
    private Griph N8;
    private Griph N9;

    public Griphattor() {

        ESPACO = carregar_ESPACO();
        ESPECIAL_ = carregar__();
        HIFEN = carregar_Hifen();

        PONTO = carregar_Ponto();
        DOIS_PONTO = carregar_DOISPONTOS();

        MAIOR = carregar_Maior();
        IGUAL = carregar_Igual();
        ORDEM = carregar_Ordem();

        A = carregar_A();
        B = carregar_B();
        C = carregar_C();
        D = carregar_D();
        E = carregar_E();
        F = carregar_F();
        G = carregar_G();
        H = carregar_H();
        I = carregar_I();
        J = carregar_J();
        K = carregar_K();
        L = carregar_L();
        M = carregar_M();

        N = carregar_N();
        O = carregar_O();
        P = carregar_P();
        Q = carregar_Q();
        R = carregar_R();
        S = carregar_S();
        T = carregar_T();
        U = carregar_U();
        V = carregar_V();
        W = carregar_W();
        X = carregar_X();
        Y = carregar_Y();
        Z = carregar_Z();

        N0 = carregar_0();
        N1 = carregar_1();
        N2 = carregar_2();
        N3 = carregar_3();
        N4 = carregar_4();
        N5 = carregar_5();
        N6 = carregar_6();
        N7 = carregar_7();
        N8 = carregar_8();
        N9 = carregar_9();

    }


    private Griph carregar_ESPACO() {
        return new Griph();
    }

    public Griph getESPACO() {
        return ESPACO;
    }


    public Griph getA() {
        return A;
    }

    public Griph getB() {
        return B;
    }

    public Griph getC() {
        return C;
    }

    public Griph getD() {
        return D;
    }

    public Griph getE() {
        return E;
    }

    public Griph getF() {
        return F;
    }

    public Griph getG() {
        return G;
    }

    public Griph getH() {
        return H;
    }

    public Griph getI() {
        return I;
    }

    public Griph getJ() {
        return J;
    }

    public Griph getK() {
        return K;
    }

    public Griph getL() {
        return L;
    }

    public Griph getM() {
        return M;
    }

    public Griph getN() {
        return N;
    }

    public Griph getO() {
        return O;
    }

    public Griph getP() {
        return P;
    }

    public Griph getQ() {
        return Q;
    }

    public Griph getR() {
        return R;
    }

    public Griph getS() {
        return S;
    }

    public Griph getT() {
        return T;
    }

    public Griph getU() {
        return U;
    }

    public Griph getV() {
        return V;
    }

    public Griph getW() {
        return W;
    }

    public Griph getX() {
        return X;
    }

    public Griph getY() {
        return Y;
    }

    public Griph getZ() {
        return Z;
    }


    public Griph get_() {
        return ESPECIAL_;
    }

    public Griph get_Hifen() {
        return HIFEN;
    }

    public Griph get_Maior() {
        return MAIOR;
    }

    public Griph get_Ponto() {
        return PONTO;
    }

    public Griph get_DoisPonto() {
        return DOIS_PONTO;
    }

    public Griph getIgual() {
        return IGUAL;
    }

    public Griph getOrdem() {
        return ORDEM;
    }

    public Griph getN0() {
        return N0;
    }

    public Griph getN1() {
        return N1;
    }

    public Griph getN2() {
        return N2;
    }

    public Griph getN3() {
        return N3;
    }

    public Griph getN4() {
        return N4;
    }

    public Griph getN5() {
        return N5;
    }

    public Griph getN6() {
        return N6;
    }

    public Griph getN7() {
        return N7;
    }

    public Griph getN8() {
        return N8;
    }

    public Griph getN9() {
        return N9;
    }


    private Griph carregar_DOISPONTOS() {
        Griph mLetra = new Griph();
        mLetra.set(6, 4, true);
        mLetra.set(7, 4, true);
        mLetra.set(6, 5, true);
        mLetra.set(7, 5, true);
        mLetra.set(6, 10, true);
        mLetra.set(7, 10, true);
        mLetra.set(6, 11, true);
        mLetra.set(7, 11, true);
        return mLetra;
    }


    private Griph carregar_A() {

        A = new Griph();

        A.set(2, 2, true);
        A.set(3, 2, true);
        A.set(4, 2, true);
        A.set(5, 2, true);
        A.set(6, 2, true);
        A.set(7, 2, true);
        A.set(8, 2, true);
        A.set(9, 2, true);
        A.set(10, 2, true);
        A.set(11, 2, true);
        A.set(12, 2, true);
        A.set(13, 2, true);
        A.set(2, 3, true);
        A.set(3, 3, true);
        A.set(4, 3, true);
        A.set(5, 3, true);
        A.set(6, 3, true);
        A.set(7, 3, true);
        A.set(8, 3, true);
        A.set(9, 3, true);
        A.set(10, 3, true);
        A.set(11, 3, true);
        A.set(12, 3, true);
        A.set(13, 3, true);
        A.set(2, 4, true);
        A.set(3, 4, true);
        A.set(12, 4, true);
        A.set(13, 4, true);
        A.set(2, 5, true);
        A.set(3, 5, true);
        A.set(12, 5, true);
        A.set(13, 5, true);
        A.set(2, 6, true);
        A.set(3, 6, true);
        A.set(12, 6, true);
        A.set(13, 6, true);
        A.set(2, 7, true);
        A.set(3, 7, true);
        A.set(4, 7, true);
        A.set(5, 7, true);
        A.set(6, 7, true);
        A.set(7, 7, true);
        A.set(8, 7, true);
        A.set(9, 7, true);
        A.set(10, 7, true);
        A.set(11, 7, true);
        A.set(12, 7, true);
        A.set(13, 7, true);
        A.set(2, 8, true);
        A.set(3, 8, true);
        A.set(4, 8, true);
        A.set(5, 8, true);
        A.set(6, 8, true);
        A.set(7, 8, true);
        A.set(8, 8, true);
        A.set(9, 8, true);
        A.set(10, 8, true);
        A.set(11, 8, true);
        A.set(12, 8, true);
        A.set(13, 8, true);
        A.set(2, 9, true);
        A.set(3, 9, true);
        A.set(12, 9, true);
        A.set(13, 9, true);
        A.set(2, 10, true);
        A.set(3, 10, true);
        A.set(12, 10, true);
        A.set(13, 10, true);
        A.set(2, 11, true);
        A.set(3, 11, true);
        A.set(12, 11, true);
        A.set(13, 11, true);
        A.set(2, 12, true);
        A.set(3, 12, true);
        A.set(12, 12, true);
        A.set(13, 12, true);
        A.set(2, 13, true);
        A.set(3, 13, true);
        A.set(12, 13, true);
        A.set(13, 13, true);

        return A;
    }

    private Griph carregar_B() {

        B = new Griph();

        B.set(2, 1, true);
        B.set(3, 1, true);
        B.set(4, 1, true);
        B.set(5, 1, true);
        B.set(6, 1, true);
        B.set(7, 1, true);
        B.set(8, 1, true);
        B.set(9, 1, true);
        B.set(10, 1, true);
        B.set(2, 2, true);
        B.set(3, 2, true);
        B.set(4, 2, true);
        B.set(5, 2, true);
        B.set(6, 2, true);
        B.set(7, 2, true);
        B.set(8, 2, true);
        B.set(9, 2, true);
        B.set(10, 2, true);
        B.set(11, 2, true);
        B.set(4, 3, true);
        B.set(5, 3, true);
        B.set(11, 3, true);
        B.set(12, 3, true);
        B.set(4, 4, true);
        B.set(5, 4, true);
        B.set(11, 4, true);
        B.set(12, 4, true);
        B.set(4, 5, true);
        B.set(5, 5, true);
        B.set(11, 5, true);
        B.set(12, 5, true);
        B.set(4, 6, true);
        B.set(5, 6, true);
        B.set(11, 6, true);
        B.set(12, 6, true);
        B.set(4, 7, true);
        B.set(5, 7, true);
        B.set(6, 7, true);
        B.set(7, 7, true);
        B.set(8, 7, true);
        B.set(9, 7, true);
        B.set(10, 7, true);
        B.set(11, 7, true);
        B.set(4, 8, true);
        B.set(5, 8, true);
        B.set(6, 8, true);
        B.set(7, 8, true);
        B.set(8, 8, true);
        B.set(9, 8, true);
        B.set(10, 8, true);
        B.set(11, 8, true);
        B.set(4, 9, true);
        B.set(5, 9, true);
        B.set(11, 9, true);
        B.set(12, 9, true);
        B.set(4, 10, true);
        B.set(5, 10, true);
        B.set(11, 10, true);
        B.set(12, 10, true);
        B.set(4, 11, true);
        B.set(5, 11, true);
        B.set(11, 11, true);
        B.set(12, 11, true);
        B.set(2, 12, true);
        B.set(3, 12, true);
        B.set(4, 12, true);
        B.set(5, 12, true);
        B.set(6, 12, true);
        B.set(7, 12, true);
        B.set(8, 12, true);
        B.set(9, 12, true);
        B.set(10, 12, true);
        B.set(11, 12, true);
        B.set(2, 13, true);
        B.set(3, 13, true);
        B.set(4, 13, true);
        B.set(5, 13, true);
        B.set(6, 13, true);
        B.set(7, 13, true);
        B.set(8, 13, true);
        B.set(9, 13, true);
        B.set(10, 13, true);

        return B;
    }

    private Griph carregar_C() {

        C = new Griph();

        C.set(2, 2, true);
        C.set(3, 2, true);
        C.set(4, 2, true);
        C.set(5, 2, true);
        C.set(6, 2, true);
        C.set(7, 2, true);
        C.set(8, 2, true);
        C.set(9, 2, true);
        C.set(10, 2, true);
        C.set(11, 2, true);
        C.set(2, 3, true);
        C.set(3, 3, true);
        C.set(4, 3, true);
        C.set(5, 3, true);
        C.set(6, 3, true);
        C.set(7, 3, true);
        C.set(8, 3, true);
        C.set(9, 3, true);
        C.set(10, 3, true);
        C.set(2, 4, true);
        C.set(3, 4, true);
        C.set(2, 5, true);
        C.set(3, 5, true);
        C.set(2, 6, true);
        C.set(3, 6, true);
        C.set(2, 7, true);
        C.set(3, 7, true);
        C.set(2, 8, true);
        C.set(3, 8, true);
        C.set(2, 9, true);
        C.set(3, 9, true);
        C.set(2, 10, true);
        C.set(3, 10, true);
        C.set(2, 11, true);
        C.set(3, 11, true);
        C.set(2, 12, true);
        C.set(3, 12, true);
        C.set(4, 12, true);
        C.set(5, 12, true);
        C.set(6, 12, true);
        C.set(7, 12, true);
        C.set(8, 12, true);
        C.set(9, 12, true);
        C.set(10, 12, true);
        C.set(2, 13, true);
        C.set(3, 13, true);
        C.set(4, 13, true);
        C.set(5, 13, true);
        C.set(6, 13, true);
        C.set(7, 13, true);
        C.set(8, 13, true);
        C.set(9, 13, true);
        C.set(10, 13, true);
        C.set(11, 13, true);

        return C;
    }

    public Griph carregar_E() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(4, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);

        return mLetra;
    }

    private Griph carregar_D() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(9, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);

        return mLetra;
    }

    private Griph carregar_F() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);

        return mLetra;
    }

    private Griph carregar_G() {

        Griph mLetra = new Griph();

        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(13, 2, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(13, 3, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(3, 7, true);
        mLetra.set(4, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        mLetra.set(3, 8, true);
        mLetra.set(4, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(13, 8, true);
        mLetra.set(3, 9, true);
        mLetra.set(4, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(13, 9, true);
        mLetra.set(3, 10, true);
        mLetra.set(4, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(13, 10, true);
        mLetra.set(3, 11, true);
        mLetra.set(4, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(13, 11, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);

        return mLetra;

    }

    private Griph carregar_H() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(13, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(13, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(13, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(13, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(13, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(4, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(13, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(13, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(13, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(13, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);

        return mLetra;
    }


    private Griph carregar_I() {

        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(7, 4, true);
        mLetra.set(8, 4, true);
        mLetra.set(7, 5, true);
        mLetra.set(8, 5, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(7, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(7, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        return mLetra;
    }


    private Griph carregar_J() {

        Griph mLetra = new Griph();

        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(7, 4, true);
        mLetra.set(8, 4, true);
        mLetra.set(7, 5, true);
        mLetra.set(8, 5, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(3, 8, true);
        mLetra.set(4, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(3, 9, true);
        mLetra.set(4, 9, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(3, 10, true);
        mLetra.set(4, 10, true);
        mLetra.set(7, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(3, 11, true);
        mLetra.set(4, 11, true);
        mLetra.set(7, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);

        return mLetra;
    }

    private Griph carregar_K() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(6, 4, true);
        mLetra.set(7, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(6, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(6, 9, true);
        mLetra.set(7, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(7, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);

        return mLetra;
    }

    private Griph carregar_L() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);

        return mLetra;
    }

    private Griph carregar_M() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(13, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(13, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(13, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(6, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(13, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(13, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(13, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(13, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(13, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(13, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);

        return mLetra;
    }

    private Griph carregar_N() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(6, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);


        return mLetra;

    }

    private Griph carregar_O() {

        Griph mLetra = new Griph();


        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(5, 4, true);
        mLetra.set(6, 4, true);
        mLetra.set(7, 4, true);
        mLetra.set(8, 4, true);
        mLetra.set(9, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(6, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(4, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(4, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(13, 8, true);
        mLetra.set(3, 9, true);
        mLetra.set(4, 9, true);
        mLetra.set(5, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(6, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(5, 11, true);
        mLetra.set(6, 11, true);
        mLetra.set(7, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);

        return mLetra;

    }


    private Griph carregar_P() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(9, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);

        return mLetra;

    }

    private Griph carregar_Q() {

        Griph mLetra = new Griph();


        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);
        mLetra.set(12, 14, true);
        mLetra.set(13, 14, true);
        mLetra.set(14, 14, true);
        mLetra.set(13, 15, true);
        mLetra.set(14, 15, true);

        return mLetra;

    }

    private Griph carregar_R() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(4, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(4, 9, true);
        mLetra.set(5, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);

        return mLetra;

    }

    private Griph carregar_S() {

        Griph mLetra = new Griph();

        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(3, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(3, 11, true);
        mLetra.set(4, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(5, 14, true);
        mLetra.set(6, 14, true);
        mLetra.set(7, 14, true);
        mLetra.set(8, 14, true);
        mLetra.set(9, 14, true);
        mLetra.set(10, 14, true);

        return mLetra;

    }

    private Griph carregar_T() {

        Griph mLetra = new Griph();


        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(7, 4, true);
        mLetra.set(8, 4, true);
        mLetra.set(7, 5, true);
        mLetra.set(8, 5, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(7, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(7, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);

        return mLetra;

    }

    private Griph carregar_U() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(13, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(13, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(13, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(13, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(13, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(13, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(13, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(13, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(13, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);

        return mLetra;

    }

    private Griph carregar_V() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(4, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(5, 9, true);
        mLetra.set(6, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(5, 10, true);
        mLetra.set(6, 10, true);
        mLetra.set(7, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(6, 11, true);
        mLetra.set(7, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(7, 12, true);

        return mLetra;

    }

    private Griph carregar_W() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(13, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(13, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(13, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(13, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(13, 6, true);
        mLetra.set(2, 7, true);
        mLetra.set(3, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        mLetra.set(2, 8, true);
        mLetra.set(3, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(13, 8, true);
        mLetra.set(2, 9, true);
        mLetra.set(3, 9, true);
        mLetra.set(5, 9, true);
        mLetra.set(6, 9, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(13, 9, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(6, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(13, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(13, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);

        return mLetra;

    }

    private Griph carregar_X() {

        Griph mLetra = new Griph();


        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(5, 9, true);
        mLetra.set(6, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(3, 11, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(2, 14, true);
        mLetra.set(3, 14, true);
        mLetra.set(11, 14, true);
        mLetra.set(12, 14, true);

        return mLetra;

    }

    private Griph carregar_Y() {

        Griph mLetra = new Griph();

        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(8, 4, true);
        mLetra.set(9, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(8, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(6, 9, true);
        mLetra.set(7, 9, true);
        mLetra.set(6, 10, true);
        mLetra.set(7, 10, true);
        mLetra.set(6, 11, true);
        mLetra.set(7, 11, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);

        return mLetra;

    }

    private Griph carregar_Z() {

        Griph mLetra = new Griph();

        mLetra.set(2, 2, true);
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(2, 3, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(2, 4, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(5, 9, true);
        mLetra.set(6, 9, true);
        mLetra.set(7, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(6, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(3, 11, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);

        return mLetra;

    }

    private Griph carregar__() {

        Griph mLetra = new Griph();

        mLetra.set(2, 12, true);
        mLetra.set(3, 12, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(2, 13, true);
        mLetra.set(3, 13, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);

        return mLetra;

    }

    private Griph carregar_Hifen() {

        Griph mLetra = new Griph();

        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(4, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        return mLetra;

    }

    private Griph carregar_Ponto() {

        Griph mLetra = new Griph();
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(13, 11, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(13, 12, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        mLetra.set(13, 13, true);
        return mLetra;

    }

    private Griph carregar_Igual(){
        Griph mLetra = new Griph();
        mLetra.set(2, 5, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(6, 5, true);
        mLetra.set(7, 5, true);
        mLetra.set(8, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(13, 5, true);
        mLetra.set(2, 6, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(13, 6, true);
        mLetra.set(2, 10, true);
        mLetra.set(3, 10, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(6, 10, true);
        mLetra.set(7, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(13, 10, true);
        mLetra.set(2, 11, true);
        mLetra.set(3, 11, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(6, 11, true);
        mLetra.set(7, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(13, 11, true);
        return mLetra;
    }


    private Griph carregar_Maior() {

        Griph mLetra = new Griph();

        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(8, 4, true);
        mLetra.set(9, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        mLetra.set(12, 8, true);
        mLetra.set(13, 8, true);
        mLetra.set(14, 8, true);
        mLetra.set(11, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(13, 9, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(7, 14, true);
        mLetra.set(8, 14, true);

        return mLetra;

    }


    private Griph carregar_0() {
        Griph mLetra = new Griph();
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(5, 4, true);
        mLetra.set(6, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(5, 5, true);
        mLetra.set(6, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(5, 9, true);
        mLetra.set(6, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(5, 10, true);
        mLetra.set(6, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(5, 11, true);
        mLetra.set(6, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        return mLetra;
    }

    private Griph carregar_1() {

        Griph mLetra = new Griph();

        mLetra.set(7, 1, true);
        mLetra.set(8, 1, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(6, 4, true);
        mLetra.set(7, 4, true);
        mLetra.set(8, 4, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(7, 5, true);
        mLetra.set(8, 5, true);
        mLetra.set(3, 6, true);
        mLetra.set(4, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(7, 10, true);
        mLetra.set(8, 10, true);
        mLetra.set(7, 11, true);
        mLetra.set(8, 11, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);

        return mLetra;

    }

    private Griph carregar_2() {
        Griph mLetra = new Griph();
        mLetra.set(3, 2, true);
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(3, 3, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(3, 4, true);
        mLetra.set(4, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(3, 5, true);
        mLetra.set(4, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(6, 9, true);
        mLetra.set(7, 9, true);
        mLetra.set(8, 9, true);
        mLetra.set(5, 10, true);
        mLetra.set(6, 10, true);
        mLetra.set(7, 10, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(6, 11, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        return mLetra;
    }


    private Griph carregar_3() {
        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(11, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(11, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(11, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        return mLetra;
    }


    private Griph carregar_4() {
        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(9, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(9, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(9, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(9, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        return mLetra;

    }

    private Griph carregar_5() {
        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(11, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        return mLetra;

    }

    private Griph carregar_6() {
        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(4, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(12, 8, true);
        mLetra.set(4, 9, true);
        mLetra.set(5, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(12, 9, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(12, 10, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(12, 11, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(12, 12, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        mLetra.set(12, 13, true);
        return mLetra;

    }

    private Griph carregar_7() {
        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(13, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(13, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(13, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(13, 5, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(13, 6, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(8, 9, true);
        mLetra.set(9, 9, true);
        mLetra.set(8, 10, true);
        mLetra.set(9, 10, true);
        mLetra.set(8, 11, true);
        mLetra.set(9, 11, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        return mLetra;
    }

    private Griph carregar_8() {
        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(4, 8, true);
        mLetra.set(5, 8, true);
        mLetra.set(6, 8, true);
        mLetra.set(7, 8, true);
        mLetra.set(8, 8, true);
        mLetra.set(9, 8, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(4, 9, true);
        mLetra.set(5, 9, true);
        mLetra.set(10, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(4, 10, true);
        mLetra.set(5, 10, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(4, 11, true);
        mLetra.set(5, 11, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(4, 12, true);
        mLetra.set(5, 12, true);
        mLetra.set(6, 12, true);
        mLetra.set(7, 12, true);
        mLetra.set(8, 12, true);
        mLetra.set(9, 12, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(4, 13, true);
        mLetra.set(5, 13, true);
        mLetra.set(6, 13, true);
        mLetra.set(7, 13, true);
        mLetra.set(8, 13, true);
        mLetra.set(9, 13, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        return mLetra;
    }

    private Griph carregar_9() {
        Griph mLetra = new Griph();
        mLetra.set(4, 2, true);
        mLetra.set(5, 2, true);
        mLetra.set(6, 2, true);
        mLetra.set(7, 2, true);
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(4, 3, true);
        mLetra.set(5, 3, true);
        mLetra.set(6, 3, true);
        mLetra.set(7, 3, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(4, 4, true);
        mLetra.set(5, 4, true);
        mLetra.set(10, 4, true);
        mLetra.set(11, 4, true);
        mLetra.set(4, 5, true);
        mLetra.set(5, 5, true);
        mLetra.set(10, 5, true);
        mLetra.set(11, 5, true);
        mLetra.set(4, 6, true);
        mLetra.set(5, 6, true);
        mLetra.set(6, 6, true);
        mLetra.set(7, 6, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(4, 7, true);
        mLetra.set(5, 7, true);
        mLetra.set(6, 7, true);
        mLetra.set(7, 7, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(10, 8, true);
        mLetra.set(11, 8, true);
        mLetra.set(10, 9, true);
        mLetra.set(11, 9, true);
        mLetra.set(10, 10, true);
        mLetra.set(11, 10, true);
        mLetra.set(10, 11, true);
        mLetra.set(11, 11, true);
        mLetra.set(10, 12, true);
        mLetra.set(11, 12, true);
        mLetra.set(10, 13, true);
        mLetra.set(11, 13, true);
        return mLetra;
    }

    private Griph carregar_Ordem(){
        Griph mLetra = new Griph();
        mLetra.set(8, 2, true);
        mLetra.set(9, 2, true);
        mLetra.set(10, 2, true);
        mLetra.set(11, 2, true);
        mLetra.set(12, 2, true);
        mLetra.set(13, 2, true);
        mLetra.set(8, 3, true);
        mLetra.set(9, 3, true);
        mLetra.set(10, 3, true);
        mLetra.set(11, 3, true);
        mLetra.set(12, 3, true);
        mLetra.set(13, 3, true);
        mLetra.set(8, 4, true);
        mLetra.set(9, 4, true);
        mLetra.set(12, 4, true);
        mLetra.set(13, 4, true);
        mLetra.set(8, 5, true);
        mLetra.set(9, 5, true);
        mLetra.set(12, 5, true);
        mLetra.set(13, 5, true);
        mLetra.set(8, 6, true);
        mLetra.set(9, 6, true);
        mLetra.set(10, 6, true);
        mLetra.set(11, 6, true);
        mLetra.set(12, 6, true);
        mLetra.set(13, 6, true);
        mLetra.set(8, 7, true);
        mLetra.set(9, 7, true);
        mLetra.set(10, 7, true);
        mLetra.set(11, 7, true);
        mLetra.set(12, 7, true);
        mLetra.set(13, 7, true);
        return mLetra;
    }
}
