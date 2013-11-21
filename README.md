# Scala - RESTful Bookmarks Jersey + Spring

Exemplo de aplicação para salvar links, onde a interface utiliza AngularJS + Twitter Bootstrap e o lado do servidor utilizado o Jersey integrado com o Spring para disponibilizar um serviço de dados RESTful, que aceita uma comunicação JSON ou XML.

Este projeto foi a reescrita do projeto [RESTful Bookmarks Jersey + Spring](https://github.com/erkobridee/restful-bookmarks-jerseyspring) utilizando a linguagem funcional Scala.

* [Histório / Alterações](https://github.com/erkobridee/restful-bookmarks-scala-jerseyspring/releases)


## Guia de Instalação

### Clone

```bash
$ git clone https://github.com/erkobridee/restful-bookmarks-scala-jerseyspring.git
$ cd restful-bookmarks-scala-jerseyspring/
```

### Montando o ambiente local para uso desse projeto

> O projeto disponibilizado no github, não possui nenhum arquivo de projeto referente ao Eclipse.

Execute os comandos a seguir dentro do diretório do projeto:

1. Execute os comandos em sequência:
	
	`mvn compile` 
	
	`mvn eclipse:eclipse`
	
2. Importe o projeto no Eclipse

	**Atenção:** (caso não esteja utilizando o plugin do Maven no Eclipse)

	```
	É necessário ter a variável M2_REPO configurada nas 
	variáveis do ClassPath, apontando para o diretório 
	do .m2/repository do Maven
	
	Lembre-se:
	- adicionar o Apache Tomcat 6.x
	ao Runtime Environments nas preferencias do seu Eclipse
	- é necessário o plugin Scala IDE
	```

### Comandos úteis do Maven

* Gerar o .war do projeto

	`mvn clean install`

* Executar o projeto diretamente pelo Maven:

	`mvn jetty:run`

> Acesse a aplicação na URL: `http://localhost:9090`


## Licença

MIT : [erkobridee.mit-license.org](http://erkobridee.mit-license.org)


## Utilizado neste projeto

* Ambiente de desenvolvimento

	* [Maven](http://maven.apache.org/) 3

	* [Eclipse](http://eclipse.org/) Juno JEE

	* [Scala IDE](http://scala-ide.org/download/nightly.html) versão nightly para Eclipse (4.2) Juno

	* [Apache Tomcat](http://tomcat.apache.org/) 6.x

	* [Java](http://www.java.com/) 1.6+

* Cliente

	* [AngularJS](http://angularjs.org/) 1.0.7

	* [Twitter Bootstrap](http://getbootstrap.com/) 2.3.2

* Servidor

	* [Scala](http://www.scala-lang.org/) 2.9.1 

	* [Jersey](http://jersey.java.net/) ([User Guide](http://jersey.java.net/nonav/documentation/latest/user-guide.html))

	* [Spring](http://spring.io/)

	* [Hibernate](http://www.hibernate.org/)

	* [HSQLDB](http://hsqldb.org/)

	* [Jetty](http://www.eclipse.org/jetty/) para testes, gerenciado pelo Maven


Quanto as versões no Servidor: `Verificar o arquivo pom.xml`
	
Segue o link do post [How to Create a Webapp with Scala, Spring, Hibernate and Maven in Late 2011](http://grahamhackingscala.blogspot.com.br/2011/08/scala-spring-hibernate-maven-webapp.html) ([github](https://github.com/GrahamLea/scala-spring-hibernate-maven-webapp)) que auxiliou para criar este projeto.

**Observação:** 

* o conhecimento inicial de como trabalhar com Scala via Maven, foi adquirido através do projeto [maven-scala](https://github.com/erkobridee/maven-scala)
* Importante observar a classe ***Bookmarks.scala*** a forma de definir que é Serializable (*@scala.serializable*) e nos respectivos atributos são propriedades do genero JavaBean (*@BeanProperty*), necessário para a serialização/deserialização dos objetos JSON/XML <-> Object


## Quanto ao RESTful do projeto

A definição do método a ser executado é definido no cabeçalho da requisição enviada para o servidor.

* **GET** - recupera 1 ou mais bookmarks

	* [.../api/bookmarks/]() - lista todos os bookmarks

	* [.../api/bookmarks/{id}]() - retorna o respectivo bookmark pelo seu ID

	* [.../api/bookmarks/search/{name}]() - retorna uma lista dos bookmarks que contém o respectivo nome

* **POST** - insere um novo bookmark

	* [.../api/bookmarks/]() - enviado via post

* **PUT** - atualiza um bookmark existente

	* [.../api/bookmarks/{id}]() - enviado via post 

* **DELETE** - remove 1 bookmark pelo ID
	* [.../api/bookmarks/{id}]() 


## Archetype do Maven que gerou a estrutura inicial do projeto

```bash
mvn archetype:generate \
  -DarchetypeGroupId=org.scala-tools.archetypes \
  -DarchetypeArtifactId=scala-archetype-simple \
  -Dversion=1.0 \
  -DgroupId=com.erkobridee.restful.bookmarks.scala.jerseyspring \
  -DartifactId=restful-bookmarks-scala-jerseyspring
```

