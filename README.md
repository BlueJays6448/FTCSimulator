# FTCSimulator
This is a simulator for First Tech Challenge robots created by Team 6448 (Jesuit Blue Jays).  The FTC Simulator acts as a bridge between the Qualcomm API used by the FTC Android app and the Webots open source simulator.

# Getting Started
1. Download FTCController.jar and SimulatorSupport.jar from the release (https://github.com/BlueJays6448/FTCSimulator/releases/tag/0.1)
2. Download the sample webots project
3. Add the FTCController.jar to the controllers/FTCController directory
5. Update the properties file in the controllers directory to point to the output directory from Android Studio and the OpMode class to use.
6. Launch your Webots world.

# Advanced
- To make your simulated robot behave as close as possible to a real robot, add the SimulatorSupport.jar to your Android Studio project and use its methods to make the simulator behave similar to the real world.
- Use the properties file to map motors and convert Webots motors to Servos and Continuos Rotation Servos.
