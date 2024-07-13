package algoritmos;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Int8;
import libs.arquivos.binario.Inteiro;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.Vetor;
import libs.luan.fmt;

public class FITParser {


    public static void init() {


        String arquivo = "/home/luan/assets/Zepp20240429053310.fit";


        Arquivador arq = new Arquivador(arquivo);

        long tamanho = arq.getLength();
        long ponteiro = 0;

        arq.setPonteiro(0);

        int cabecalho_tamanho = 14;
        int cabecalho_pt = 0;
        int dados_pt = 0;

        Vetor<Byte> cabecalho = new Vetor<Byte>(cabecalho_tamanho);
        Vetor<Byte> dados = new Vetor<Byte>((int) (tamanho - cabecalho_tamanho));

        while (ponteiro < tamanho) {

            byte byte_c = arq.get();
            int b = Inteiro.byteToInt(byte_c);

            // fmt.print("{} -->> {}", ponteiro, b);

            if (cabecalho_pt < cabecalho_tamanho) {
                cabecalho.set(cabecalho_pt, byte_c);
                cabecalho_pt += 1;
            } else {
                dados.set(dados_pt, byte_c);
                dados_pt += 1;
            }
            ponteiro += 1;
        }

        arq.encerrar();


        String s_cabecalho = "";
        for (Byte b : cabecalho) {
            s_cabecalho += Inteiro.byteToInt(b) + " ";
        }

        s_cabecalho=s_cabecalho.trim();

        fmt.print("---------- FIT -----------------");
        fmt.print("Cabecalho           = [{}]", s_cabecalho);
        fmt.print("Cabecalho Tamanho   = {}", Inteiro.byteToInt(cabecalho.get(0)));
        fmt.print("Cabecalho Protocolo = {}", Inteiro.byteToInt(cabecalho.get(1)));
        fmt.print("Cabecalho Versao    = {}", Inteiro.byteToInt(cabecalho.get(3)) + " " + Inteiro.byteToInt(cabecalho.get(4)));
        fmt.print("Dados Tamanho       = {}", Inteiro.get_u32(cabecalho, 4));
        fmt.print("Dados Tipo          = {}", Strings.getSlice(cabecalho, 8, 12));
        fmt.print("Cabecalho CRC       = {}", Inteiro.byteToInt(cabecalho.get(12)) + " " + Inteiro.byteToInt(cabecalho.get(13)));


        fmt.print("");
        fmt.print("Dados Tamanho       = {}", dados.getCapacidade());

        int i = 0;
        int segmentos = 0;

        Lista<FitMensagem> mensagens = new Lista<FitMensagem>();


        while (i < dados.getCapacidade()) {

            fmt.print("Tipo = {} :: {}", Inteiro.byteToInt(dados.get(i)), new Int8(Inteiro.byteToInt(dados.get(i))).getBits(0, 8));

            int RECORD_BIT_7 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(7);

            if (RECORD_BIT_7 ==1) {
                fmt.print(" >> COMPRESSED_TIMESTAMP - {}",RECORD_BIT_7);




            } else {
                fmt.print(" >> NORMAL - {}",RECORD_BIT_7);

                int RECORD_BIT_0 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(0);
                int RECORD_BIT_1 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(1);
                int RECORD_BIT_2 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(2);
                int RECORD_BIT_3 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(3);

                fmt.print("Bits [0123] :: {}{}{}{}",RECORD_BIT_0,RECORD_BIT_1,RECORD_BIT_2,RECORD_BIT_3);

                int localMessageType = dados.get(i) & 0x0F;
                boolean hasDeveloperData = false;
                boolean reservedBit = false;
                boolean definition_message = false;

                int RECORD_BIT_5 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(5);

                if (RECORD_BIT_5 > 0) {
                    hasDeveloperData = true;
                }

                int RECORD_BIT_4 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(4);

                // Reserved bit value - bit 4
                if (RECORD_BIT_4 > 0) {
                    reservedBit = true;
                }

                fmt.print(" ++ localMessageType         = {}", localMessageType);
                fmt.print(" ++ hasDeveloperData         = {}", hasDeveloperData);
                fmt.print(" ++ reservedBit              = {}", reservedBit);

int ino = i;

                int RECORD_BIT_6 = new Int8(Inteiro.byteToInt(dados.get(i))).getValorInverso(6);

                if (RECORD_BIT_6 == 1) {
                    definition_message = true;
                    fmt.print(" ++ definition_message       = {}", definition_message);

                    FitMensagem mensagem = new FitMensagem(localMessageType,FitMensagem.TIPO_NORMAL,hasDeveloperData);
                    mensagens.adicionar(mensagem);


                    byte reservado = dados.get(i + 1);
                    byte architecture = dados.get(i + 2);
                    int globalMessageNumber = 0;

                    if (architecture > 0) {
                        globalMessageNumber = Inteiro.get_u16_be(dados, i + 3);
                    } else {
                        globalMessageNumber = Inteiro.get_u16_le(dados, i + 3);
                    }

                    int numberOfDataFields =  Inteiro.byteToInt(dados.get(i+4));

                    fmt.print(" ++ reservado                = {}", reservado);
                    fmt.print(" ++ architecture             = {}", architecture);
                    fmt.print(" ++ globalMessageNumber      = {}", globalMessageNumber);
                    fmt.print(" ++ numberOfDataFields       = {}", numberOfDataFields);

                    int tracks=0;
                     ino = i+4;

                    while (tracks<numberOfDataFields)
                    {
                        int fieldDefinitionNumber   =Inteiro.byteToInt(dados.get(ino+1));
                        int size                    =Inteiro.byteToInt(dados.get(ino+2));
                        int baseType                =Inteiro.byteToInt(dados.get(ino+3));

                      //  record.addMessageField(globalMessageNumber, fieldDefinitionNumber, size, baseType);

                        fmt.print("Message.Field {} - {} : {} : {} : {}",tracks,fieldDefinitionNumber,size,baseType);

                        tracks+=1;
                        ino+=3;
                    }

                    ino+=1;
                    if(hasDeveloperData){

                        numberOfDataFields =  Inteiro.byteToInt(dados.get(ino));
                        tracks=0;
                        while (tracks<numberOfDataFields)
                        {
                            int fieldDefinitionNumber   =Inteiro.byteToInt(dados.get(ino+1));
                            int size                    =Inteiro.byteToInt(dados.get(ino+2));
                            int baseType                =Inteiro.byteToInt(dados.get(ino+3));

                            //  record.addMessageField(globalMessageNumber, fieldDefinitionNumber, size, baseType);

                            fmt.print("Developer.Field {} - {} : {} : {} : {}",tracks,fieldDefinitionNumber,size,baseType);

                            tracks+=1;
                            ino+=3;
                        }


                    }



                }else{

                    if (hasDeveloperData){
                        fmt.print("! ERROR - Bit 5 Illegal - hasDeveloperData");
                    }

                    if (reservedBit){
                        fmt.print("! ERROR - Bit 5 Illegal - reservedBit");
                    }

                    FitMensagem mensagemn = GET_MENSAGEM_POR_TIPO_LOCAL(mensagens,localMessageType);
                    if(mensagemn!=null){

                    }else{
                        fmt.print("! ERROR - Mensagem não encontrada {}",localMessageType);
                    }

                }

                i=ino;
            }


            if (segmentos == 10) {
                break;
            }
            segmentos+=1;
            i += 1;
        }


        for(FitMensagem mensagem : mensagens){
            fmt.print("Mensagem -->> {} - {}",mensagem.getTipo(),mensagem.getTipoLocal());
        }


    }

    public static FitMensagem GET_MENSAGEM_POR_TIPO_LOCAL(Lista<FitMensagem> mensagens,int tipolocal){
        for(FitMensagem m : mensagens){
            if(m.getTipoLocal()==tipolocal){
                return m;
            }
        }
        return null;
    }

}
