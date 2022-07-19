<h1 align="center">
    Theme-Core v1.0
</h1>

<h4 align="center">
  Sistema de conversão de layout para o sistema emulationstation.
</h4>

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT">
</p>

<p align="center">

  <a href="https://github.com/marcelobojikian" target="_blank">
    <img alt="Feito por Marcelo Nogueira" src="https://img.shields.io/badge/Feito%20por-Marcelo_Nogueira-informational">
  </a>
  <a href="https://github.com/marcelobojikian" target="_blank" >
    <img alt="Github - Marcelo Nogueira" src="https://img.shields.io/badge/Github--%23F8952D?style=social&logo=github">
  </a>
  <a href="https://www.linkedin.com/in/marcelobojikian/" target="_blank" >
    <img alt="Linkedin - Marcelo Nogueira" src="https://img.shields.io/badge/Linkedin--%23F8952D?style=social&logo=linkedin">
  </a>

</p>

<p align="center">
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-pre-requisitos">Pré-requisitos</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-como-usar">Como usar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-licença">Licença</a>
</p>

<br>

## 💻 Projeto

Essa parte do projeto está focado na camada intermediária entre os sistemas emulationstation e o xml que é usado para definir as telas do mesmo, definindo qual a estrutura de xml será usado por padrão e utilizando APIs de conversão de XML.

Esse módulo foi baseado no projeto inicial do [Aloshi](https://github.com/Aloshi/EmulationStation) que e utilizado pelas outras implementações do sistema emulationstation como [Batocera](https://github.com/batocera-linux/batocera-emulationstation), [EmuELEC](https://github.com/EmuELEC/emuelec-emulationstation) e [RetroPie](https://github.com/RetroPie/EmulationStation).

O projeto foi desenvolvido em Java utilizando as ferramentas:

<ul>
  <li>Spring Boot Test</li>
  <li>Maven - Gerenciador de dependências, compilação, automação dos testes, ... </li>
  <li>XStream- Conversão de Java para XML</li>
  <li>JaCoCo - Um gerador de relatórios de cobertura de código</li>
</ul>

Para a colaboração no projeto, é fundamental o conhecimento dessas ferramentas, orientação objeto e padrão de projetos.

## 🔖 Pre-requisitos (caso queira colaborar)

Para utilizar o sistema, faça um clone do mesmo para sua máquina.

| git clone https://github.com/marcelobojikian/Theme-Java.git

Inicia a IDE de sua preferência e baixe as dependências do maven, por exemplo:

| mvn build

Feito isso, você já pode colaborar com alguns códigos :)


## 🤔 Como usar

Cada pacote do sistema define qual a implementação utilizada, todas tendo como pacote principal **br.com.mnb.theme.core**.

No pacote **xml** serão encontrados todos o código referente a conversão de Java para XML e vice-verso em **view**, **element**, **theme**, **Content.java** e no pacote **converter**, as configurações de conversão utilizadas pelo XStream.

No pacote **factory** foi utilizado um padrão de projeto de criação, que instancia as classes dos XML mapeados. Nesse caso usando métodos da própria classe, podendo ser utilizada pelo Spring também, bastando apenas implementar **InstanceFactory.java**.

No pacote **builder** também foi utilizado outro padrão de projeto de criação, para simplificar toda a construção de cada elemento XML.

Para a criação de um novo sistema, podemos seguir 3 passos.

1. Definição dos modelos XML
2. Implementação de um conversor de Tag Xml
3. Configuração do XStream.

Veja abaixo como posso definir um modelo XML

```java
@XStreamAlias("field")
public class Field extends AbstractElement {
	private static final long serialVersionUID = 1L;
}
```

Essa classe representa uma Tag xml.

```xml
<field name="" extra="">
	...
</field>
```

Para a criação de suas Tag personalizada, é necessário a implementação de um conversor ( uma classe que implemente a interface **TagConverter**).


```java
public class ElementConverter implements TagConverter<AbstractElement, String>{

	@Override
	public String toString(AbstractElement element) {
		if(element instanceof Field) {
			return "field";
		}
	}

	@Override
	public AbstractElement toComponent(String tagName) {
		if("field".equals(tagName)) {
			return instaceFactory.createElement(Field.class);
		}
	}

}
```

Depois de feito esses passos, basta configurar o XStream que é utilizado para fazer a conversão de XML para Java ou vice-versa.

```java

XStream xstream = new XStream();

xstream.autodetectAnnotations(true);
xstream.ignoreUnknownElements();

xstream.registerConverter(new ContentXmlConverter());
xstream.registerConverter(new ElementXmlConverter());

xstream.processAnnotations(Field.class);
xstream.processAnnotations(Content.class);

xstream.addPermission(NoTypePermission.NONE);

xstream.allowTypes(new Class[] {
		Field.class,
		Content.class });
		
```


É possível encapsular esse código utilizando **XmlConverter.class** no pacote **xml.converter**, essa classe cria uma única instância da seu XStream, sempre que for requisitada.

Recomendo a leitura da documentação do XStream para mais detalhes.


## :memo: Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
