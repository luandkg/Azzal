package libs.DKG;

public class AutoDKG {

    public static DKG auto(String eTexto){
        DKG eDKC = new DKG();
        eDKC.parser(eTexto);
        return eDKC;
    }

}
