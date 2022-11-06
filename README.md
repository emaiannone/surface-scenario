# Surface at Work - Usage Scenario

This repository contains:

- The JAR file of Surface with all the dependencies included (`surface-1.0.0.jar`).
- The configuration file needed to run Surface in FLEXIBLE mode on two projects (`config.yml`).
- This README file.

The following steps **reproduce an example of a usage scenario**:

1. Open the terminal and move into this directory:  
   `cd <PATH-TO-THIS-DIRECTORY>` (replace `<PATH-TO-THIS-DIRECTORY>` accordingly)
2. Run Surface:  
   `java -jar surface-1.0.0.jar -target config.yml -workDir /tmp -outFile results.json`

`config.yml` instructs Surface to analyze two projects, one that is locally stored inside this repository, and one that
will be automatically cloned from GitHub before analyzing it (for this reason you need a stable Internet connection).

Note that if you do not want to use `/tmp` directory, you can freely change it to another directory.

At the end of the execution, the results are printed in file `results.json` (already pre-computed).
