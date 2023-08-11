# Agenda Pessoal JavaFX

![agenda 2](https://user-images.githubusercontent.com/17755195/187983261-4128c119-a609-4c9a-8d14-540ef1bc8ff8.png)

## Por que devo aprender a trabalhar com a biblioteca javafx?

A biblioteca JavaFX é uma plataforma gráfica rica e poderosa para a criação de interfaces de usuário em aplicativos Java. Aprender a trabalhar com o JavaFX pode trazer vários benefícios, como:

* Desenvolvimento de aplicativos de desktop modernos: Com a interface gráfica do JavaFX, é possível criar aplicativos de desktop modernos e atraentes que oferecem aos usuários uma experiência rica e envolvente.

* Integração com outras tecnologias Java: A biblioteca JavaFX é uma biblioteca Java padrão, o que significa que ela se integra facilmente com outras tecnologias Java, como o Java SE, Spring e outros frameworks.

* Plataforma multiplataforma: O JavaFX é suportado em várias plataformas, incluindo Windows, macOS e Linux, o que permite que você crie aplicativos que funcionam em diferentes sistemas operacionais.

* Desenvolvimento rápido: O JavaFX inclui vários recursos, como o Scene Builder, que permite criar interfaces gráficas facilmente, sem precisar escrever código.

* Comunidade: A biblioteca JavaFX tem uma grande comunidade de usuários e desenvolvedores, o que significa que você pode encontrar muitos recursos, tutoriais e exemplos online para ajudá-lo a aprender e desenvolver aplicativos.

Em resumo, aprender a trabalhar com a biblioteca JavaFX pode ajudá-lo a criar interfaces de usuário modernas e envolventes para seus aplicativos Java, permitindo que você desenvolva aplicativos de desktop multiplataforma de maneira rápida e fácil.

Vejamos esse projeto pelo qual foi desenvolvido um aplicativo desktop para o cadastro, atualização, leitura e exclusão de contatos, sendo basicamente uma agenda de uso pessoal. Foi elaborado e seguindo as instruções doProfessor Crenilson no seu canal do YouTube. Porém, tive que fazer algumas adaptações durante a sua construção devido as atualizações das dependências e plug-ins, como também as diferenças existente na IDE Intellij. Mas mesmo assim, conseguir fazer com que a aplicações funcionasse da mesmo forma aplicando os conhecimentos adquiridos durante os curso da Universidade, Udemy e da DIO. E por fim, fazendo a sua <a href="https://adriano1976.github.io/agenda-javadoc/com.projetos.agenda/com/projetos/agenda/package-summary.html" target="_blank">Documentação com Javadoc</a> e com o auxílio da IDE Intellij.

## 🛠️ Ferramentas utilizadas na criação deste projeto:

* Intellij IDE - Ambiente de desenvolvimento.
* Maven - Gerenciador de Dependência.
* Scene Builder - Utilizado na criação das interfaces.
* MySQL - Banco de dados da aplicação.
* Hibernate - Automatização na construção das tabelas.

## Por que devo trabalhar com o Framework Hibernate?

![hibernate](https://user-images.githubusercontent.com/17755195/227721558-e3000857-ee41-4ad0-8b34-97c2a0cf4c3e.png)

O Hibernate é um dos frameworks mais populares para mapeamento objeto-relacional em Java. Ele simplifica a tarefa de persistir objetos Java em bancos de dados relacionais, permitindo que você trabalhe com objetos Java em vez de SQL.

Existem várias razões pelas quais você deve aprender a trabalhar com o Hibernate:

* Produtividade: O Hibernate torna o desenvolvimento de aplicativos Java mais rápido e fácil, reduzindo a quantidade de código que você precisa escrever e facilitando a manutenção do código.

* Flexibilidade: O Hibernate permite que você trabalhe com diferentes bancos de dados relacionais sem precisar mudar o código. Isso significa que você pode mudar de banco de dados facilmente, se necessário, sem ter que reescrever o código.

* Segurança: O Hibernate é projetado para ajudar a evitar vulnerabilidades de segurança, como injeção de SQL.

* Desempenho: O Hibernate é altamente otimizado para desempenho e pode ajudar a melhorar a escalabilidade e a velocidade do seu aplicativo.

* Comunidade: O Hibernate é um framework de código aberto com uma grande comunidade de usuários. Isso significa que você pode encontrar muitos recursos, documentação e suporte disponíveis online.

Em resumo, aprender a trabalhar com o Hibernate pode ajudar a tornar o desenvolvimento de aplicativos Java mais fácil, rápido, seguro e eficiente.

## 🛠️ Etapas da construção do projeto:

* Etapa 1: Organizando o projeto - Padrão MVC.
* Etapa 2: Limpando a View e o Controller, Inserindo um título na Janela e Maximizando a tela principal.
* Etapa 3: Obtendo imagens e ícones para colocar na tela principal.
* Etapa 4: Inserindo um ícone na janela principal.
* Etapa 5: Inserindo uma imagem de fundo na tela principal.
* Etapa 6: Tela Principal - Inserindo Imagem (Logotipo).
* Etapa 7: Tela Principal - Criando o Menu de opções.
* Etapa 8: Tela principal - Inserindo teclas de atalho no menu.
* Etapa 9: Estilizando o Menu com CSS.
* Etapa 10: Criando as classes Model.
* Etapa 11: Criando o formulário padrão para cadastro.
* Etapa 12: Criando os formulários Tipo de Contato, cidade e contato.
* Etapa 13: Abrindo os formulários via Menu.
* Etapa 14: Criando o banco de dados para o sistema Agenda e integra-lo com o Hibernate.
* Etapa 15: Configurando o arquivo Hibernate.cfg
* Etapa 16: Inserindo dependência para Hibernate e MySQL.
* Etapa 17: Mapeando as Classes TipoContato, Cidade e Contato.
* Etapa 18: Criando a Classe ConexaoBanco.
* Etapa 19: Criando TipoContatoDao - Método Salvar()
* Etapa 20: TipoContatoController - Titulo da Janela e Salvando os Dados.
* Etapa 21: Criando a interface ICadastro.
* Etapa 22: Criando as colunas da tabela.
* Etapa 23: Criando o método consulta()
* Etapa 24: Criando o método atualizarTabela()
* Etapa 25: Redimensionando as colunas da tabela.
* Etapa 26: Preenchendo o formulário ao clicar na tabela.
* Etapa 27: Preenchendo o formulário ao mover setas no teclado.
* Epata 28: Limpando os campos do formulário.
* Etapa 29: Excluindo registros.
* Etapa 30: Alterando registros.
* Etapa 31: Criando Mensagens de Alerta.
* Etapa 32: Criando Mensagens de Alerta - Exclusão.
* Etapa 33: Preenchendo ComboBox de UF - Formulário de Cidade.
* Etapa 34: ComboBox versus Banco de Dados.
* Etapa 35: Criando um ComboBox Genérico.
* Etapa 36: Transformando as Classes DAO em Genéricas.
* Etapa 37: Cadastro de Cidades.
* Etapa 38: Formulário Contato: Novos componentes.
* Etapa 39: Salvando o Contato Parte 1 - DatePicker RadionButton Checkbox.
* Epata 40: Contato: Ação para ComboBox de Cidade atualizar UF e CEP.
* Etapa 41: Criando mecanismo de alteração e excluindo dados do Contato.
* Etapa 42: Regra de Negócio: Verificação para Liberação de Exclusão.
* Etapa 43: Tooltip: Validando os campos.
* Etapa 44: Criação de Logs.
* Etapa 45: Escrevendo a documentação usando Javadoc.

## 🎯 Documentação Navegável Javadoc

Essa <a href="https://adriano1976.github.io/agenda-javadoc/com.projetos.agenda/com/projetos/agenda/package-summary.html" target="_blank">Documentação JavaDoc</a>,  tem por objetivo facilitar o entendimento dos componentes do projeto e navegando por dentro dele entre as classes, métodos e pacotes.

A Documentação dos códigos fontes, é uma necessidade universal em vários paradigmas ou plataformas de desenvolvimento. Dessa forma, precisamos gerar e manipular de uma forma eficiente todos os comentários em formato de documentação de códigos, visando com isso, facilitar a reutilização futura desses comentários como fonte geradora de conhecimentos acerca das classes, atributos e métodos.

Seu funcionamento baseia-se na inserção de textos explicativos em forma de um comentário especial, que antecedem um escopo de uma classe ou método, tendo assim, a responsabilidade de apresentar o mesmo.

Seu funcionamento é através do uso de marcação de documentos com doclets, gerando arquivos nos formatos HTML, SGML, XML ou RTF. Tais marcações são feitas através de comentários, contendo tags especiais que especificam quais informações serão inseridas, com objetivo de manter uma massa de conhecimento reutilizável em qualquer projeto que faça uso da classe em questão.

Vale ressaltar, que podemos combinar tags da própria especificação HTML, com as tags oferecidas pelo JavaDoc, tornando possível à criação de documentos completos gerados a partir dos comentários do próprio código. Portanto, veja e conheça a anatomia do projeto por meio da <a href="https://adriano1976.github.io/agenda-javadoc/com.projetos.agenda/com/projetos/agenda/package-summary.html" target="_blank">Documentação JavaDoc</a>.


## 📉 Gráficos UML do projeto.

Nesse gráfico mostra as classes, métodos e suas dependências entre eles.

![Agenda - diagrama UML](https://user-images.githubusercontent.com/17755195/187984100-2eaa2eef-35ee-42fa-83c7-e32400bd7df6.png)

## 🎁 Expressões de gratidão

Gostaria de agradecer ao [Professor Crenilson](https://www.youtube.com/c/ProfessorCrenilson) por ter disponibilizado este curso de JavaFX de forma gratuita no YouTube, bem como pelo seu empenho e dedicação em cada aula ministrada.

<div align="center">
<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=87CEFA&height=120&section=footer"/>**** 
</div>
