package org.ftc6448.simulator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;

import com.cyberbotics.webots.controller.Supervisor;
import com.qualcomm.robotcore.robocol.TelemetryMessage;

public class PlatformSupport {
	
	public static Supervisor supervisor;
	public static int timeStep;
	private static final long baseTime=System.currentTimeMillis();
	
	public static volatile int timeStepResult;
	
	public static long getCurrentTimeMillis() {
		return baseTime+((long)(1000*supervisor.getTime()));
	}

	public static boolean isSimulator() {
		return true;
	}
	
	private static final Object simulatorLock=new Object();
	
	public static void waitForSimulatorTimeStep() {
		synchronized (simulatorLock) {
			simulatorLock.notifyAll();
			
			try {
				simulatorLock.wait();
			} catch (InterruptedException e) {
			}
		}
	}
	
	public static void signalSimulatorLock(boolean waitForSignal) {
		synchronized(simulatorLock){
			simulatorLock.notifyAll();
			if (waitForSignal) {
				try {
					simulatorLock.wait(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void showTelemetry(TelemetryMessage transmitter) {
		//send the telemtry message to Webots window
		supervisor.wwiSendText(formatTelemetryString(transmitter,true));
	}
	

	public static final String formatTelemetryString(TelemetryMessage message,boolean html) {
		StringBuilder builder=new StringBuilder(100);
		for (Map.Entry<String,String> entry : message.getDataStrings().entrySet()) {
			String line=entry.getValue();
			line=html?(line.replace("\n", " ")):line;
			builder.append(line);
			builder.append(html?"<br/>":System.lineSeparator());
		}
		for (Map.Entry<String,Float> entry : message.getDataNumbers().entrySet()) {
			Float value = entry.getValue();
			String line=(value==null)?"":value.toString();
			line=html?(line.replace("\n", " ")):line;
			builder.append(line);
			builder.append(html?"<br/>":System.lineSeparator());
		}
		final String telemetryText=builder.toString();
		return telemetryText;
	}
	
	public static final File getSettingsFile(String filename) {
		return new File(filename);
	}

	public static final String readFile(File file) {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(file.toPath());
			return new String(encoded, Charset.defaultCharset());
		} catch (IOException e) {
			throw new RuntimeException("Error reading file "+file.getAbsolutePath(),e);
		}
	}
	
}
