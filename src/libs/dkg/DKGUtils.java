package libs.dkg;

public class DKGUtils {


    public static DKGObjeto criar(String nome) {
        return new DKGObjeto(nome);
    }

    public static DKGObjeto criar(String nome, String c1, String v1) {

        DKGObjeto o = new DKGObjeto(nome);
        o.identifique(c1, v1);

        return o;
    }

    public static DKGObjeto criar(String nome, String c1, String v1,String c2,String v2) {

        DKGObjeto o = new DKGObjeto(nome);
        o.identifique(c1, v1);
        o.identifique(c2, v2);

        return o;
    }

    public static DKGObjeto criar(String nome, String c1, String v1,String c2,String v2,String c3,String v3) {

        DKGObjeto o = new DKGObjeto(nome);
        o.identifique(c1, v1);
        o.identifique(c2, v2);
        o.identifique(c3, v3);

        return o;
    }

}
