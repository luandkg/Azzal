package apps.app_tozterum;

import apps.app_letrum.Fonte;
import libs.luan.Indexado;
import libs.luan.Indexamento;
import libs.luan.Strings;

public class TextoJustificado {

    public static int escrever(Fonte ft_branca_20, int px_original, int py_original, int linha_tamanho, String texto){

        int linhas_quantidade = 0;

        int py = py_original;
        int frase_tamanho = 0;
        String frase = "";

        for (Indexado<String> i_palavra : Indexamento.indexe( Strings.dividir_espacos(texto))){
            String palavra = i_palavra.get();

            int  t = frase_tamanho + ft_branca_20.getLarguraDe(palavra);
            if (t>linha_tamanho){

                int esp = Strings.contar_letra(frase," ");
                String palavras = frase.replace(" ","");

                int t2 = linha_tamanho;
                int t3 = ft_branca_20.getLarguraDe(palavras);

                int diff = t2 - t3;
                //  fmt.print("Diff :: {}",diff);
                // fmt.print("Esp :: {}",esp);

                if(esp>0){

                    int entre = diff / esp;

                    int px = px_original;
                    for(String pa : Strings.dividir_espacos(frase)){
                        ft_branca_20.escreva(px, py,pa);
                        px+=ft_branca_20.getLarguraDe(pa) + entre;
                    }

                    linhas_quantidade+=1;


                }else{
                    ft_branca_20.escreva(px_original, py,frase);
                    linhas_quantidade+=1;
                }



                frase=palavra;
                frase_tamanho=ft_branca_20.getLarguraDe(palavra);
                py+=30;
            }else{
                if(i_palavra.index()==0){
                    frase= palavra;
                }else{
                    frase+=" " + palavra;
                }
                frase_tamanho+=ft_branca_20.getLarguraDe(palavra);
            }

        }

        if(!frase.isEmpty()){
            ft_branca_20.escreva(px_original, py,frase);
            linhas_quantidade+=1;
        }

        return linhas_quantidade;
    }

}
