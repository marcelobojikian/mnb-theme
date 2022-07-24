<h1 align="center">
    MNB Theme v1.0
</h1>

<h4 align="center">
  Conversion layout system for emulationstation.
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
  <a href="#-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-structure">Structure</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-how-to-use">How to use</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-license">License</a>
</p>

<br>

## 💻 Project

Initially made for mapping screens from XML structures to Java, it was developed by [Aloshi](https://github.com/Aloshi/EmulationStation) and used as reference for other systems such as [Batocera](https://github.com/batocera-linux/batocera-emulationstation), [EmuELEC](https://github.com/EmuELEC/emuelec-emulationstation), [RetroPie](https://github.com/RetroPie/EmulationStation) and [Recalbox](https://gitlab.com/recalbox/recalbox).

Currently, only Batocera module has been developed, but the system is designed and made to be scalable, and in the future, differents XML can be coded and mapped for their respctive systems.

## 🔖 Structure

Every project was divided by modules and separated by their function or system. [Emulationstation](https://github.com/marcelobojikian/mnb-theme/tree/main/emulationstation) and [Batocera](https://github.com/marcelobojikian/mnb-theme/tree/main/batocera) are dependent on [Core](https://github.com/marcelobojikian/mnb-theme/tree/main/core), the main structure of the system, in it we can find all the common interfaces and classes of each system, regardless their customization.


```shell
  $ tree
  .
  ├── core
  │   ├── src/...
  │   ├── README.md
  │   └── pom.xml
  ├── emulationstation
  │   ├── src/...
  │   ├── README.md
  │   └── pom.xml
  ├── batocera
  │   ├── src/...
  │   ├── README.md
  │   └── pom.xml
  ├── reports
  │   └── pom.xml
  ├── README.md
  └── pom.xml
```

In each system contain a **pom.xml** file, the file used by Maven for managing dependencies, compiling projects, making automatized tests and other tasks.

For more details, enter into their respective system folder and check their **README.md** file.

## 🤔 How to use

To use the system, make a clone for your machine.

```shell
  $ git clone git@github.com:marcelobojikian/mnb-theme.git
```

Run your favority IDE of choice and use Maven for downloading the dependencies, managing the build and install all project on your machine, for example:

```shell
  $ mvn install
```

Once that's done, you can now collaborate with some codes :)

## :memo: License

This project is under the MIT license. See the [LICENSE](LICENSE) file for more details.