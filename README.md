
# Boston University's Metropolitan College  
## CS622 Class Project:

### Jic Jac Joe 

![Version](https://img.shields.io/badge/version-0.0.1-blue.svg?style=flat )
![Platform](https://img.shields.io/badge/platform-CLI-blue.svg?style=flat )
[![Build Status](https://www.travis-ci.com/gchriswill/JicJacJoe.svg?token=8efyA1QMsv1rNCzSQkn1&branch=master)](https://www.travis-ci.com/gchriswill/JicJacJoe)

A tic-tac-toe game written in Java. 
Players the game in PvC and PvP modes via IDE’s console, and/or CLI, and/or a client app. 
The game helps Java developers to release stress by allowing them to play a simple game, 
within the developer’s own workspace, against the program itself or against another player, 
to take a brake from work, engage with fellow teammates and have fun at the same time without ever leaving you workspace at all. 
The game’s title will be Jic-Jac-Joe.

### Overview Video:

**Overview video is coming soon...**  
An in-depth and 15 minutes-long for project overview and usage guide can be found [here](#).

---

## Repository:

This repo holds the source code of Jic-Jac-Joe project for [C.W. Gonzalez Melendez]'s 
MET-CS 622 "Advanced Programming Techniques" class of the Software Development Master's degree 
at Boston University's Metropolitan College.

The purposes of this repo are:

- Providing commit history/changelog of the project, for its evaluation by the instructor.
- Provide versioning control features to the project.
- Use Github's Project, Issues, WIKI and repository management tools.
- Host all the source code remotely on Github as remote repository.
- Host the project website with Github Pages.

---

# Project Details:

### _Project Info_:

**Term:** Spring-O1, 2021  
**Project:** Jic-Jac-Joe  
**Instructor:** [Eric J. Braude](https://www.bu.edu/met/profile/eric-j-braude/)  
**Facilitator:** [Kuang-Jung Huang, "Michael"](#)  
**Institution:** [Boston University's Metropolitan College](https://www.bu.edu/met/)  
**Course:** [CS622 - Advanced Programming Techniques](http://www.bu.edu/csmet/academic-programs/courses/cs622/)  
**Program:** [Software Development M.Sc. (MSSD)](https://www.bu.edu/met/degrees-certificates/ms-software-development/)  
**Student:** Christopher W. Gonzalez Melendez, D.K.A. "[@gchriswill](https://github.com/gchriswill)" : Student/Engineer/Developer  

**Website:** [Jic-Jac-Joe](#) (**Website is currently in the works and coming soon...**)  
**Github Repository:** [Jic-Jac-Joe's Repository](https://github.com/gchriswill/JicJacJoe)

### _Current Status_:

**Performing development for module 2 requirements**

### _Branch Structure/Strategy_:

- master: The released source code for grading
    - module1: All requirements for 622's Module 1 (GitHub Milestone 1)
    - module2: All requirements for 622's Module 2 (GitHub Milestone 2)
    - module3: All requirements for 622's Module 3 (GitHub Milestone 3)
    - module4: All requirements for 622's Module 4 (GitHub Milestone 4)
    - module5: All requirements for 622's Module 5 (GitHub Milestone 5)
    - module6: All requirements for 622's Module 6 (GitHub Milestone 6)
    - feature/*: Branches with feature prefix will contain specific individual and single feature
    - release/*: Branches with release prefix will contain the requirements of a specific module completed and ready for testing  
    

Each branch contains the specific requirements for the state module and completes the designated GitHub Milestone.  
Each GitHub Milestone track all the respective GitHub Issues/requirements for the module/branch.  

### _IDE Platform_:

- [IntelliJ IDEA Ultimate 2020.3.2](https://www.jetbrains.com/idea/)

### _Target Platforms_:

- CLI
- IDE's Console

### _CI/CD Platform_:

- [Travis-CI + Maven 3 + JUnit 5](https://www.travis-ci.com/github/gchriswill/JicJacJoe)
- [Github Actions coming soon...](#)

### _Test Devices_:

- MacBook Pro (15 Inch, 2017)

### _Development Devices_:

- MacBook Pro (15 Inch, 2017)

### _Google Drive Location_:

[Jic-Jac-Joe's Documents Folder Location](https://drive.google.com/drive/u/1/folders/0ABEHSS4VkZJqUk9PVA)  

The project's documents folder includes:

- Requirements/
- Resources/
- Diagrams/
- Flows/

---

# Feature Sets:

**Feature Sets are under works...**  

| Core              | Support           | Supplemental            |
| -------------     | -------------     | -------------           |
| Item 1            | Item 1            | Item 1                  |
| Item 2            | Item 2            | Item 2                  |
| Item 3            | Item 3            | Item 3                  |

---

# Milestones Breakdown for Modules:

**GitHub Milestones are under works...**  

Bellow, here are the Milestone cards on the and schedule for this project. 
Each milestone represents each module requirements goals for class CS 622.
For more information, please visit the Project and Wiki of this repository.

- [ ] [Milestone 1](#)
- [ ] [Milestone 2](https://github.com/gchriswill/JicJacJoe/milestone/1)
- [ ] [Milestone 3](#)
- [ ] [Milestone 4](#)
- [ ] [Milestone 5](#)
- [ ] [Milestone 6](#)

# Running Jic Jac Joe:

### IMPORTANT NOTES  

The project was implemented in IntelliJ IDEA and has 1 dependency, 
which is the Java annotations library is has been included in the repository 
and zipped folder in the assignment submission field.  

Also, JUnit lib has been already integrated to the Jic Jac Joe's project for module 2, 
but do not have any test implementations for module 1. 

The project has CI/CD integration already via Travis-CI using Apache-Ant build tool, this is for 
running and validating the project's test with JUnit in every commit pushed to the repository. 

### Running

Basically, the project should run as it is, without any additional configuration, in the CLI or IDE's console.  

To run the game in the CLI (a Unix Bash shell), navigate to the working directory and type this command in your unix terminal:  

`java -cp <LOCAL WORKING DIRECTORY>/out/production/JicJacJoe edu.bu.met.CS622.JicJacJoe.Main`

Don't forget to add your local path to the command.
Also, don't forget to build the project first...

Please note that player-VS-player mode will not be available for module 1.

There are 2 convenient commands available at almost every state of the game (Except in the menu, which only `exit` will be available).
The convenient commands are: `restart` and `exit` 




