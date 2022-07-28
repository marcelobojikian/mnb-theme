<h1 align="center">
    MNB Theme - EmulationStation v1.0
</h1>

<h4 align="center">
    Layout conversion system for EmulationStation module.
</h4>

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT">
</p>

<p align="center">
  <img alt="Security Rating" src="https://sonarcloud.io/api/project_badges/measure?project=marcelobojikian_mnb-theme&metric=security_rating" />
  <img alt="Bugs" src="https://sonarcloud.io/api/project_badges/measure?project=marcelobojikian_mnb-theme&metric=bugs" />
  <img alt="Vulnerabilities" src="https://sonarcloud.io/api/project_badges/measure?project=marcelobojikian_mnb-theme&metric=vulnerabilities" />
  <img alt="Coverage" src="https://sonarcloud.io/api/project_badges/measure?project=marcelobojikian_mnb-theme&metric=coverage" />
</p>

<p align="center">

  <a href="https://github.com/marcelobojikian" target="_blank">
    <img alt="Made by Marcelo Nogueira" src="https://img.shields.io/badge/Made%20by-Marcelo_Nogueira-informational">
  </a>
  <a href="https://github.com/marcelobojikian" target="_blank" >
    <img alt="Github - Marcelo Nogueira" src="https://img.shields.io/badge/Github--%23F8952D?style=social&logo=github">
  </a>
  <a href="https://www.linkedin.com/in/marcelobojikian/" target="_blank" >
    <img alt="Linkedin - Marcelo Nogueira" src="https://img.shields.io/badge/Linkedin--%23F8952D?style=social&logo=linkedin">
  </a>

</p>

<p align="center">
  <a href="#-module">Module</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-structure">Structure</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-how-to-use">How to use</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-license">License</a>
</p>

<br>

## 💻 Module

This module is modeled on the XML conversion made specially for the **EmulationStation** system, it is recommended to check its [documentation](https://github.com/Aloshi/EmulationStation) to know how its XML structure and its particularities were designed.

Since all the modules of this project depend on the Core, it is also strongly recommended to know its structure and concepts behind each class and interface, this will help and guide you in the planning of future features and help maintain its code integrity and cohesiveness.

For your collaboration in this module, it is fundamental the knowledge of the concepts of Object Orientation and Design Patterns, and also of some tools that will help you in the automated tests such as Mockito.

## 🔖 Structure

In order to help and motivate future developers, regardless of their level of knowledge, a brief explanation of the division of the packages of this module will be made.

See below how that module is configured:

```java
package br.com.mnb.theme.emulationstation.xml.theme;
package br.com.mnb.theme.emulationstation.xml.element;
```

In these packages are located all the XML tags of the system that will be converted into Java.

```java
package br.com.mnb.theme.emulationstation.xml.configure;
```

In this package was made a manual configuration of XStream and it illustrates the possibility of customizing more complex configurations.

```java
package br.com.mnb.theme.emulationstation.xml.converter;
```

Here you will find converters from all over the system, see further on how you should use this package.

```java
package br.com.mnb.theme.emulationstation.factory;
```

In this package you will find classes that should simplify the creation and construction of objects in this system.

## 🤔 How to use

See that the implementation must be simple, just inform the tag signature in **@XStreamAlias** and say that it is an **AbstractElement**. See an example:

```java
@XStreamAlias("text")
public class Text extends AbstractElement {
    
}
```

That class will be converted into:

```xml
<text/>
```

Imagine that we have a tag with the signature **feature** for two different functionalities, what differentiates them is the **supported** information. In this case we must separate it in a different way with the **AbstractFeature**.

```java
@XStreamAlias("feature")
public class CarouselFeature extends AbstractFeature {
    @XStreamAlias("supported")
    @XStreamAsAttribute
    private String supported = "carousel";
}

@XStreamAlias("feature")
public class VideoFeature extends AbstractFeature {
    @XStreamAlias("supported")
    @XStreamAsAttribute
    private String supported = "video";
}
```

Those classes will be converted into:

```xml
<feature supported="carousel"/>
<feature supported="video"/>
```

After mapping the most used tags of the system, don't forget to map your root tag **<theme>**.

```java
@XStreamAlias("theme")
public class EmulationStationTheme extends AbstractTheme {

}
```

That class will be converted into:

```xml
<theme/>
```

These are the configurations that are already implemented, but I haven't researched all the tags that exist for this system. So let's see how simple is the conversion from Java to XML, first let's create a theme for this system using our factory, located in the **factory** package.

```java
EmulationStationFactory factory = new EmulationStationFactory();
EmulationStationTheme theme = factory.createTheme(4);
```

Notice that we pass parameter "4" that indicates which version of the theme we're going to use, something required in all systems. We can also create the basic elements of the system using the same factory, for example:

```java

// Basic Elements
factory.createImage("bezel", true);
factory.createRating("md_rating");
factory.createText("md_lbl_releasedate");
factory.createDatetime("md_releasedate");
```

If you want to pass some data to the content of each element, you can use a **java.util.Map** to insert this information.

```java
// Features
Map<String, String> attributes = new HashMap<>();
attributes.put("mykey", "myvalue");

factory.createText("md_lbl_releasedate", attributes);
```

Now that we know how to create elements and insert value in each one, let's see a complete example.

```java
EmulationStationFactory factory = new EmulationStationFactory();

Map<String, String> attributes = new HashMap<>();
attributes.put("mykey", "myvalue");

EmulationStationTheme theme = factory.createTheme(4)
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
	          factory.createText("md_description,md_lbl_rating, md_lbl_releasedate"),
	          factory.createText("md_lbl_rating"),
	          factory.createRating("md_rating")
	      )
	  );
```

Now that we have our class mapped, we want to transform it into XML, for that we will use our class responsible for simplifying this task.

```java
ESConverter converter = new ESConverter();
String xml = converter.toXML(theme);
```

The outcome will be:

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
</theme>
```

Our "Converter" class has another method called **fromXML()** that will serve to convert from XML to Java, doing the opposite task of the one we just did.

## :memo: License

This project is under the MIT license. See the [LICENSE](LICENSE) file for more details.