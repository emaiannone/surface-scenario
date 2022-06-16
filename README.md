# Surface at Work - Usage Scenario

This package contains:
- The JAR package of Surface with all the dependencies included (`surface.jar`).
- The configuration file needed to run Surface in FLEXIBLE mode on two projects (`config.yml`).
- This README file.

The following steps **reproduce the entire usage scenario** described in the manuscrips.
1. Open the terminal and move into this directory (extract the package if you have not done already):  
`cd <PATH-TO-THIS-DIRECTORY>` (replace `<PATH-TO-THIS-DIRECTORY>` accordingly)
2. Run Surface:  
`java -jar surface.jar -target config.yml -workDir /tmp -outFile /tmp/results.json`

`config.yml` instructs Surface to analyze two projects, one that is locally stored inside this package, and one that will be automatically cloned from GitHub before analyzing it (for this reason you need a stable Internet connection).

Note that if you do not want to use `/tmp` directory, you can freely change it to another directory.

At the end of the execution, the results are pretty printed in file `/tmp/results.json`
