package org.ftc6448.simulator;

import java.io.File;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import com.qualcomm.robotcore.util.ReadWriteFile;

/**
 * This class contains static methods that will be automatically swapped out for simulator 
 * specific versions if running in the simulator.
 * 
 */
public class PlatformSupport {

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static void waitForSimulatorTimeStep() {
		//does nothing in real world robot
	}
	
	public static boolean isSimulator() {
		return false;
	}

    public static File getSettingsFile(String filename) {
        return AppUtil.getInstance().getSettingsFile(filename);
    }

    public static String readFile(File file) {
        return ReadWriteFile.readFile(file);
    }
}
