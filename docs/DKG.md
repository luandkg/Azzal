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

## Exemplos :

```
!Alunos :: {
    !Aluno :: {  @Turma = "A" @Nome = "BELTRANO" @Grupo = "AZUL"     @Status = "PRESENTE" @Visibilidade = "SIM" }
    !Aluno :: {  @Turma = "B" @Nome = "FULANO"   @Grupo = "VERMELHO" @Status = "AUSENTE"  @Visibilidade = "SIM" }
}
```

```
!Configuracoes :: {
    !Permissoes :: {
        !Permissao :: {  @Nome = "Acessar" @Valor = "SIM" }
        !Permissao :: {  @Nome = "Publicar" @Valor = "NAO" }
        !Permissao :: {  @Nome = "Listar" @Valor = "SIM" }
    }
}
```