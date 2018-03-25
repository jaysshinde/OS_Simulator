# OS Simulator
A simulation of operating system concepts using Java and JavaFX

## Get Started
1.  Get IntelliJ IDEA. [Download](https://www.jetbrains.com/idea/download/#section=linux)  
    Strongly Recommended since Eclipse is a pain to use and I'd rather die than use NetBeans (Have fun with Gradle integration hlpr98). Scene Builder is integrated into IntelliJ so you don't need to set up anything for that either.
2.  Clone this Repository and open it using IntelliJ.
4.  To build the project you first need to open the gradle tab on IntelliJ. (Hover on the bottom left cornor and select Gradle). Open the javafx tab under the Tasks menu in the Gradle toolbar and choose jfxJar to build the project and jfxRun to run the built jar.
5.  TC HF.


## Prerequisites

1.  Java JDK (prefarably Java SE than Default Java package) -version 1.8.0_161 and above

**Don't worry about these. Gradle would handle it.**
Dependencies have already been added to the `build.gradle` file. Since these packages are present in mavenCentral or in jCenter, we don't need to explicitly download the .jar files and add them manually. If you need a specific library, just add the file under dependencies in `build.gradle`. 

2.  Jfoenix -version 8.0.1 and above
3.  FontawesomeFx -version 8.9 and above
4.  JavaFX -version 2.0 and above


## How to contribute?
### Creating Pull Request
 * Fork the repository.
 * Push the work that you have completed into your local repository (i.e the forked repo).
 * Create Pull Request.
 
### How to work on the Project?
 * Clone your forked repository into your computer.
 * Go into the ```os-simulator``` directory.
 * Open the [OS Simulator](Simulator) project in IntelliJ.
 * Complete the simulation assigned to you. Controllers for the simulations would be found under `main/java/simulations`. Their respective fxml files would be found under `main/resources/simulations`. Gradle requires you to use these directories for building. 
 * Commit to your repository.
 * Create a Pull Request. hlpr or gurupunskill would review and merge your changes.

