<h1 align="center">
    Theme-Batocera v1.0
</h1>

<h4 align="center">
  Sistema de conversão de layout para o sistema Batocera.
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

O sistema tem o objetivo de criar layout do emulationstation, convertendo do codigo Java para o XML. Existe outros sistemas que tem variações desse layout, por exemplo [Batocera](https://github.com/batocera-linux/batocera-emulationstation), [EmuELEC](https://github.com/EmuELEC/emuelec-emulationstation) e [RetroPie](https://github.com/RetroPie/EmulationStation) porém todos tem como base o modelo inicial de [Aloshi](https://github.com/Aloshi/EmulationStation). O projeto ainda não está concluido porém em breve finalizarei.

O projeto foi desenvolvido em Java utilizando as ferramentas:

<ul>
  <li>Spring Boot Test</li>
  <li>Maven - Gerenciador de dependências, compilação ... </li>
  <li>XStream- Conversão de Java para XML</li>
  <li>JaCoCo - Um gerador de relatórios de cobertura de código</li>
</ul>

Para a colaboração no projeto, é fundamental o conhecimento dessas ferramentas, de orientação objeto e padão de projeto.

## 🔖 Pre-requisitos (caso queira colaborar)

Para utilizar o sistema, faça um clone do mesmo para sua maquina.

| git clone https://github.com/marcelobojikian/Theme-Java.git

Inicia a IDE de sua preferência e baixe as dependências do maven, por exemplo:

| mvn build

Feito isso, você já pode colaborar com alguns codigos :)


## 🤔 Como usar

O sistema ainda não está completo porém já é possível realizar algumas conversão de Java para xml.

Para criar os elementos no padrão do Batocera, basta iniciar uma **Factory**, nesse caso **BatoceraFactory**.

```java
BatoceraFactory factory = new BatoceraFactory();
```

Com a nossa **Factory** pronta, podemos começar a criar nosso layout do Batocera.

```java
BatoceraTheme theme = factory.createTheme(4);
```

Podemos criar os elementos básicos vindo do projeto inicial e também do projeto especifico do Batocera, por exemplo:

```java
// Elementos do Batocera
factory.createFeatureCarousel();
factory.createFeatureVideo();

// Elementos básicos
factory.createImage("bezel", true);
factory.createRating("md_rating");
factory.createText("md_lbl_releasedate");
factory.createDatetime("md_releasedate");
```

Para criar o xml, basta utilizar a classe **BatoceraXmlConverter**.

```java
BatoceraXmlConverter xmlConverter = new BatoceraXmlConverter();
xmlConverter.toXML(theme);
```

É possível também criar um **BatoceraTheme** utilizando um XML.

```java
BatoceraTheme theme = xmlConverter.fromXML("myTheme.xml");
```


Veja um exemplo simples de um codigo completo:

```java
BatoceraFactory factory = new BatoceraFactory();

Map<String, String> attributes = new HashMap<>();
attributes.put("TESTE_123", "true");
		
BatoceraTheme theme = factory.createTheme(4)
  .addFeatures(
      factory.createFeatureCarousel(attributes)
        .addElements(
          factory.createLogoText(),
          factory.createImage("seperator"),
          factory.createSystemInfo()
        ),
    factory.createFeatureVideo()
  )
  .addViews(
    factory.createView("system, basic, detailed, video")
      .addElements(
          factory.createImage("background_all", true),
          factory.createHelpSystem("help")
      ),
    factory.createView("basic, detailed, video")
      .addElements(
          factory.createImage("logo"),
          factory.createImage("help_seperator", true)
      ),						
    factory.createView("basic")
      .addElements(
          factory.createTextList("gamelist")
      ),
    factory.createView("detailed, video")
      .addElements(
          factory.createTextList("gamelist"),
          factory.createImage("bezel", true),
          factory.createText("md_description, md_lbl_rating, md_lbl_releasedate"),
          factory.createText("md_lbl_rating"),
          factory.createRating("md_rating")
      )
  );

BatoceraXmlConverter xmlConverter = new BatoceraXmlConverter();
xmlConverter.toXML(theme);

System.out.println(xstream.toXML(theme));
```

O resultado será:

```xml
<theme>
  <formatVersion>4</formatVersion>
  <view name="system, basic, detailed, video">
    <image extra="true" name="background_all"/>
    <helpsystem name="help"/>
  </view>
  <view name="basic, detailed, video">
    <image name="logo"/>
    <image extra="true" name="help_seperator"/>
  </view>
  <view name="basic">
    <textlist name="gamelist"/>
  </view>
  <view name="detailed, video">
    <textlist name="gamelist"/>
    <image extra="true" name="bezel"/>
    <text name="md_description,md_lbl_rating, md_lbl_releasedate"/>
    <text name="md_lbl_rating"/>
    <rating name="md_rating"/>
  </view>
  <feature class="feature" supported="carousel">
    <view name="system">
      <carousel name="systemcarousel">
        <TESTE__123>true</TESTE__123>
      </carousel>
      <text name="logoText"/>
      <image name="seperator"/>
      <text name="systemInfo"/>
    </view>
  </feature>
  <feature class="feature" supported="video">
    <view name="video">
      <video name="md_video"/>
    </view>
  </feature>
</theme>
```


## :memo: Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
