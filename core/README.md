<h1 align="center">
    MNB Theme - Core v1.0
</h1>

<h4 align="center">
    Core of the layout conversion system.
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

This module will implement the abstraction of how the XML is built for each system, allowing each module to have its particular implementation characteristics, offering classes and interfaces that will simplify this codification and avoid duplicated codes.

The XML file that gave origin to all systems, the emulationstation developed by [Aloshi](https://github.com/Aloshi/EmulationStation), became the reference for all this module. Over time, other systems were created such as [Batocera](https://github.com/batocera-linux/batocera-emulationstation), [EmuELEC](https://github.com/EmuELEC/emuelec-emulationstation), [RetroPie](https://github.com/RetroPie/EmulationStation) and [Recalbox](https://gitlab.com/recalbox/recalbox). In these systems were made several improvements and each project followed a different path of development with new Tags in XML, methods of insertion of Video, screens with the Carousel format, among others.

For your collaboration in this module, it is fundamental the knowledge of the concepts of Object Orientation and Design Patterns, and also of some tools that will help you in the automated tests such as Mockito.

## 🔖 Structure

In order to help and motivate future developers, regardless of their level of knowledge, a brief explanation of the division of the packages of this module will be made.

See below how that module is configured:

```java
package br.com.mnb.theme.core.xml.theme;
package br.com.mnb.theme.core.xml.feature;
package br.com.mnb.theme.core.xml.view;
package br.com.mnb.theme.core.xml.element;
```

In these packages you will find the classes and interfaces that represent the existing tags in all systems, for example **AbstractElement.java** that in emulationstation would be the \<text\>, \<image\>, \<sound\> and others.

```java
package br.com.mnb.theme.core.xml.xstream;
```

In this package you have all the classes and settings for the XML conversion API called XStream. You can find the system's **Element**, **View** and **Feature** converters and the settings used to make those transformations at each level of the XML hierarchy.
Remember that everything developed **exclusively** for XStream is in this package.

```java
package br.com.mnb.theme.core.xml.tag;
```

This package, along with the classes of the previous package, define the objects that the other modules will use to build their XML, like for instance, the converters found in the sub-package **tag.converter**. Each module will use a converter that will provide methods that will allow that module to execute that function, not having the need to know which configuration to use.

```java
package br.com.mnb.theme.core.factory;
package br.com.mnb.theme.core.builder;
```

In these packages we have the abstractions of how to manufacture and build each component of this module, simplifying the realization of these tasks.

## 🤔 How to use

This module will be used to create other systems, it`s not allowed to develop other systems here, only common classes and abstractions amoung all modules. If you want to understand how each class of this module works, you can take a look at the automated tests, they are used only for testing but they can clarify many doubts.

To have a better experience of how the creation of each system works in practice, I recommend you to see the modules emulationstation and batocera.

## :memo: License

This project is under the MIT license. See the [LICENSE](LICENSE) file for more details.