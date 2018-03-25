# Contributing

When contributing to this repository, please follow the following instructions.


## Brief note on the file structure

* In this repository the souce code is in [src](Simulator/src).
* The [src](Simulator/src) directory contains
  * [home](Simulator/src/home) :- This directory contains info about the Home Page.
  * [NewSimulation](Simulator/src/newSimulation) :- This directory contains info about a page, that lets you choose a New Simulation.
  * [start](Simulator/src/start) :- This directory contains info about the Welcome Page. This directory contains the main class.
  * [Simulations](Simulator/src/simulations) :-  This directory contains all the simulations.
  * [Styles](Simulator/src/styles) :- This directory contains a CSS style sheet.

* In [Simulations](OS%20Simulator/src/simulations) directory there are multiple sub-directories which are name with a common conventions<br/>
i.e ```<classs of the simulations>_<the simulation>``` For example,If the simulation is Process Scheduling, then, it can be found in the directory ``` processes_Schedulling ```

* Each subdirectory in the [Simulations](OS%20Simulator/src/simulations) directory have two files, namely
  * a Controller.java file :- This is the file which controles the all the features on the ```scene``` of that perticular simulation including all the animations and transitions.
  * a .fxml file :- This is the file which contains the information on, how your ```scene``` looks like , i.e the info on graphics.
  


 ## Creating Pull Request
 
 * Fork the repository.
 * Push the work that you have completed into your local repository (i.e the forked repo).
 * Create Pull Request.
 
 ## How to work on the Project?
 
 * Clone your forked repository into your computer.
 * Go into the ```os-simulator``` directory.
 * Open the [OS Simulator](OS%20Simulator) project in your preferred IDE ( I have used NetBeans ).
 * Complete the simulation (i.e the complete Controller.java and the .fxml files w.r.t your simulation in [Simulations](OS%20Simulator/src/simulations) directory) that is assigned to you.
 * Check if it works.
 * Commit to your repository.
 * Create Pull Request.
