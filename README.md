#### ATTZ

> É um local de criatividade no qual são implementados vários devaneios em JAVA que se passam em minha mente.

![AZZAL UI](https://github.com/luandkg/Azzal/blob/master/res/app_ui.png)

## Sistema de Renderização de Interface Gráfica: Uma Visão Completa

Um sistema de renderização de interface gráfica é responsável por criar e atualizar visualmente os elementos que o usuário interage em um aplicativo. Ele transforma dados abstratos em representações visuais na tela.

### Componentes Principais:

* **Interface:**
    * **Janela:** A área principal onde a aplicação é exibida. Define o tamanho, posição e propriedades básicas da interface.
    * **Cena:** O espaço virtual onde os objetos 3D são posicionados e renderizados. É como um palco onde a ação acontece.
    * **AppGlobal:** Um objeto que gerencia o estado global da aplicação, como as diferentes cenas, transições entre elas e outros dados compartilhados.

* **Entrada de Dados:**
    * **Mouse:** Permite ao usuário interagir com a interface, selecionando objetos, movendo-os e realizando outras ações.
    * **Teclado:** Utilizado para digitar texto, comandos e atalhos.

* **Formas Geométricas:**
    * **Primitivas:** Os blocos de construção básicos da interface gráfica.
        * **Ponto:** A unidade fundamental, definindo uma posição no espaço.
        * **Linha:** Conecta dois pontos, formando segmentos de reta.
        * **Círculo:** Uma curva fechada onde todos os pontos estão à mesma distância de um ponto central.
        * **Triângulo:** Um polígono de três lados.
        * **Quadrado:** Um polígono de quatro lados com todos os lados iguais e ângulos retos.
        * **Retângulo:** Um polígono de quatro lados com lados opostos iguais e ângulos retos.

* **Utilitários:**
    * **Tempo:** Permite medir o tempo de execução de tarefas, criar animações e efeitos visuais.
    * **Cronômetro:** Um contador de tempo específico para medir a duração de eventos.
    * **Cor:** Define a tonalidade, saturação e brilho das cores utilizadas na interface.
    * **Luz:** Simula a iluminação em cenas 3D, afetando a aparência dos objetos.
    * **Paleta:** Um conjunto de cores predefinidas para facilitar a seleção e garantir a consistência visual.
    * **Posicionador:** Define a posição de elementos na tela, utilizando coordenadas ou outros sistemas de posicionamento.
    * **Transformador de Cor:** Permite modificar as cores de um objeto, como ajustar o brilho, contraste ou aplicar filtros.

### Funcionamento Básico:

* **Criação da Cena:** A aplicação define a cena, adicionando objetos e luzes.
* **Entrada do Usuário:** O sistema captura eventos do mouse e teclado.
* **Atualização:** A cena é atualizada com base nas entradas do usuário e no tempo.
* **Renderização:** Os objetos na cena são transformados em pixels na tela, levando em conta a iluminação, as câmeras e as propriedades dos materiais.
* **Exibição:** A imagem renderizada é exibida na janela.

# ENTT

Imagine um sistema capaz de compreender e manipular dados complexos, como se fossem objetos do mundo real. Esse é o papel de um organizador de entidades abstratas. Essa ferramenta revolucionária oferece um conjunto de recursos avançados para analisar, agrupar, classificar e ordenar grandes volumes de dados de forma eficiente e intuitiva.

* **Entidades:** Representam os elementos fundamentais dos seus dados. Podem ser desde pessoas e produtos até eventos e conceitos abstratos.
* **Atributos:** São as características que descrevem cada entidade. Por exemplo, para uma entidade "Pessoa", os atributos poderiam ser "nome", "idade", "profissão", etc.
* **Tags de Chave/Valor:** Uma forma flexível de representar os atributos. A chave indica o nome do atributo, e o valor, o seu conteúdo.
* **Tipos de Chaves:**
    * **Texto:** Para armazenar informações textuais, como nomes e descrições.
    * **Inteiro:** Para números inteiros, como idades e quantidades.
    * **Long:** Para números inteiros de grande precisão.
    * **Double:** Para números de ponto flutuante, como valores monetários e medidas.


# libs.luan - Estruturas de Dados

> Estruturas de dados implementadas no segundo semestre do curso de ciências das computação na disciplina de Estrutura
de Dados da Universidade de Brasília

1. Vetor
2. Lista
3. Dicionário
4. Par
5. Iterador
6. String
7. Unico
8. TabelaHash

> Algoritmos implementados 

1. Ordenador
2. Opcional
3. Iteradores : Vetor, Lista, Unico e TabelaHash

> Classes Essenciais para Programação

1. Inteiro
2. Strings
3. Matematica
4. Texto 
5. TextoDocumento

> Classes referenciáveis

1. RefInt
2. RefBool
3. RefLong
4. RefString

> fmt : Essa classe é muito top e foi desenvolvida para facilitar a saida de strings no terminal

> Tempo : Classe responsável por trabalhar com datas e horarios, ela será atualizada com outra versão que desenvolvi no projeto appEscola


# Tronarko

O sistema de contagem temporal proposto, denominado Tronarko, apresenta uma estrutura única e complexa, divergindo dos sistemas calendáricos mais comuns. Vamos destrinchar cada componente desse sistema para uma melhor compreensão:

### O Tronarko: Uma Nova Contagem do Tempo

* Tronarko: A unidade de tempo fundamental, equivalente ao que chamamos de "ano" em calendários tradicionais.
* Superarko: A subdivisão do Tronarko, representando um dia. Um Tronarko possui 500 Superarkos.
* Hiperarko: Um conjunto de 50 Superarkos, dividindo o Tronarko em 10 faixas de 50 dias cada.
* Arco: Uma unidade de tempo dentro do Tronarko, representando similarmente a hora. Cada Superarko (dia) possui 10 Arcos.

![TRONARKO](https://github.com/luandkg/Azzal/blob/master/res/app_tronarko.png)

## **Atzum - Construtor de Mundos**

**Dê vida aos seus mundos imaginários com Atzum!**

Atzum é uma ferramenta poderosa e intuitiva para criadores de histórias, designers de jogos e entusiastas de worldbuilding. Com Atzum, você pode gerar mapas detalhados e realistas, personalizando cada aspecto de seus mundos imaginários.

**Funcionalidades Principais:**

* **Geração Procedural de Mapas:**
    * **Algoritmos avançados:** Utilize algoritmos de ruído Perlin e Simplex para criar paisagens únicas e diversas.
    * **Personalização:** Ajuste parâmetros como nível de detalhe, escala e semente para gerar mapas infinitamente variados.
    * **Características geográficas:** Gere automaticamente mapas com relevos complexos, rios, lagos, cadeias de montanhas e muito mais.

* **Criação de Atmosferas:**
    * **Biomas:** Gere biomas realistas com base em fatores como clima, altitude e tipo de solo.
    * **Clima:** Simule diferentes tipos de clima, como tropical, temperado, árido e polar.
    * **Ciclos diurnos e sazonais:** Crie ciclos de dia e noite, estações do ano e eventos climáticos dinâmicos.

* **Compartilhamento e Colaboração:**
    * **Exportação:** Exporte seus mapas em diversos formatos para uso em seus projetos.
    * **Comunidade:** Compartilhe seus mundos com outros usuários e explore mapas criados por outros.

**Com Atzum, você pode:**

* **Criar mundos para seus jogos de RPG:** Gere mapas detalhados para suas aventuras.
* **Desenvolver cenários para suas histórias:** Crie paisagens únicas para seus romances e contos.
* **Visualizar suas ideias:** Transforme suas visões em mapas tangíveis e interativos.


# libs.dkg - Arquivo DKG

## DKG: Uma Abordagem Flexível para Estruturação de Dados

O formato DKG (possivelmente uma abreviação de "Dados em Grafo") é uma estrutura de dados textual projetada para oferecer uma maneira flexível e intuitiva de organizar informações. Ao combinar a simplicidade de um formato de texto com a complexidade de uma estrutura em árvore, o DKG permite representar dados de forma hierárquica e relacionar diferentes elementos.

### Conceitos Fundamentais:

* **Objeto:** A unidade básica de informação no DKG. Cada objeto pode ter um nome único e armazenar uma variedade de atributos.
* **Atributo:** Uma propriedade associada a um objeto, definindo suas características. Os atributos podem ser de diversos tipos, como texto, números, datas ou até mesmo outros objetos.
* **Estrutura em Árvore:** O DKG organiza os objetos em uma estrutura hierárquica, similar a uma árvore. Cada objeto pode ter um ou mais objetos filhos, criando relações pai-filho entre eles.

### Características Principais:

* **Flexibilidade:** A estrutura em árvore permite representar dados complexos e hierárquicos de forma natural.
* **Extensibilidade:** Novos tipos de objetos e atributos podem ser definidos facilmente, adaptando o DKG a diferentes domínios.
* **Legibilidade:** A sintaxe textual do DKG torna os dados fáceis de ler e entender, facilitando a manutenção e depuração.
* **Versatilidade:** O DKG pode ser utilizado para representar uma ampla variedade de dados, desde configurações de software até modelos de dados complexos.

~~~
!Alunos :: {
    !Aluno :: {  @Turma = "A" @Nome = "BELTRANO" @Grupo = "AZUL"     @Status = "PRESENTE" @Visibilidade = "SIM" }
    !Aluno :: {  @Turma = "B" @Nome = "FULANO"   @Grupo = "VERMELHO" @Status = "AUSENTE"  @Visibilidade = "SIM" }
}
~~~

~~~
!Configuracoes :: {
    !Permissoes :: {
        !Permissao :: {  @Nome = "Acessar" @Valor = "SIM" }
        !Permissao :: {  @Nome = "Publicar" @Valor = "NAO" }
        !Permissao :: {  @Nome = "Listar" @Valor = "SIM" }
    }
}

~~~

# Biblioteca Arquivos - Meus formatos de arquivos

> Pacote com formatos de arquivados criados por eu mesmo ( hehehe )

    - IM : Formato de Imagem
    - AI : Formato de Álbum de Imagens
    - TX : Formato de Texto
    - BZ : Formato de Blocos de Textos Indexados
    - HZ : Formato de Áudio : @ESTOU_ESTUDANDO
    - VI : Formato de Vídeo : @NAO_TEM_AUDIO
    - BZ : Formato de seção pre-alocada ( BZ2 = 10Kb | BZ3 = 100Kb )
    - AN : Formato de Animações : Sequência de Imagens IM com taxa de reprodução

    Formatos com ideias mais complexas de proposito geral :

    - CacheIndexado : Formato de seção pre-alocada expandível
    - Sumario       : Formato de indexamento pela primeira letra
    - QTT           : Formato de acesso direto em 2 dimenções
    - DS            : Formato de guarda de blocos nomeados de bytes contínuos

    - Arquivador : Classe para abrir/escrever arquivos

    - Int8 : Mapeador de 8 bits
    - Int6 : Mapeador de 6 bits

# Biblioteca Zetta: Um Ecossistema Completo para Gestão de Dados

A Biblioteca Zetta é uma poderosa ferramenta para o desenvolvimento de aplicações que demandam um alto nível de organização e gerenciamento de dados. Pensada para ser flexível e escalável, ela oferece um conjunto de recursos robustos para a construção de coleções de dados altamente personalizadas e eficientes.

### Conceitos Fundamentais:

    Entidade: A unidade básica de informação na Zetta. Uma entidade representa um objeto do mundo real, como um cliente, um produto ou um evento.
    Coleção: Um agrupamento de entidades relacionadas, formando um conjunto de dados com um propósito específico.
    ZettaColeção: Uma coleção de entidades, similar a uma tabela em um banco de dados relacional, mas com maior flexibilidade e capacidade de armazenar dados complexos.
    ZettaPastas: Uma estrutura hierárquica para organizar as coleções, permitindo uma melhor organização e acesso aos dados.
    ZettaPasta: Uma unidade de organização dentro da estrutura de pastas da Zetta.

### Características Principais:

    Flexibilidade: A Zetta permite a criação de esquemas de dados personalizados, adaptando-se a diferentes necessidades.
    Escalabilidade: A biblioteca é projetada para lidar com grandes volumes de dados e pode ser facilmente escalada para atender a demandas crescentes.
    Consistência: O mecanismo de consenso ZettaQuorum garante a integridade dos dados, mesmo em ambientes distribuídos.
    Performance: A Zetta oferece um alto desempenho para operações de leitura e escrita, otimizando a utilização de recursos.
    Facilidade de uso: A API da Zetta é intuitiva e fácil de aprender, permitindo que desenvolvedores rapidamente criem e gerenciem suas coleções de dados.


# Gráficos

> Pacote GrafiqueBarras para desenhos de gráficos de barras e pontos.

![ZETTA BARRAS](https://github.com/luandkg/Azzal/blob/master/res/app_zetta.png)

# libs.imagem

> Coleção de códigos para facilitar abertura de imagens, além de aplicação de efeitos.


# Biblioteca LLCripto 

> Algoritmo para realizar criptografica de dados
![LLCripto](https://github.com/luandkg/Azzal/blob/master/res/llcripto.png)

# Partículas

> Aplicação de renderização de partículas AREIA e LÍQUIDO.

# libs.organizadorq2d

> Mapeador de regiões do espaço 2D com algoritmo para algilizar detecção de colisão de corpos.

![Organizador Espacial](https://github.com/luandkg/Azzal/blob/master/res/app_oe.png)


# libs.movimentador

> Algoritmos para realizar movimento de corpos no espaço 2D de forma mais harmônica e real.

    - libs.movimentador Burro
    - libs.movimentador Linear
    - libs.movimentador Vertical
    - libs.movimentador Inteligente

