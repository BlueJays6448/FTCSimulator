package org.ftc6448.simulator.webots;

import java.util.Properties;

import org.ftc6448.simulator.Controller;
import org.ftc6448.simulator.PlatformSupport;

import com.cyberbotics.webots.controller.Device;
import com.cyberbotics.webots.controller.GPS;
import com.cyberbotics.webots.controller.Keyboard;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.PositionSensor;
import com.cyberbotics.webots.controller.Supervisor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.studiohartman.jamepad.ControllerManager;

public class OpModeController implements Controller {

	protected final OpMode opMode;
	protected final Supervisor supervisor;
	protected final Properties properties;
	
	public final int timeStep;
	protected Keyboard keyboard;
	protected GamepadSupport gamepadSupport;
	protected ControllerManager controllerManager;
	
	public OpModeController(Supervisor supervisor,OpMode opMode,Properties properties) {
		this.opMode = opMode;
		this.supervisor=supervisor;
		this.properties=properties;
		// get the time step of the current world.
		timeStep = (int) Math.round(supervisor.getBasicTimeStep());
		System.out.println("timeStep " + timeStep);
	
		opMode.gamepad1=new Gamepad();
		opMode.gamepad2=new Gamepad();
		
	}
	
	@Override
	public void initialize() {
		keyboard = new Keyboard();
		keyboard.enable(timeStep);
		
		controllerManager = new ControllerManager();
		controllerManager.initSDLGamepad();
		
		gamepadSupport=new GamepadSupport(properties, controllerManager);
		
		initializeDevices();
		opMode.internalPreInit();
		System.out.println("Calling OpMode init");
		opMode.init();
		opMode.internalPostInitLoop();
	}
	
	

	//this method iterates the Robot and the simulation properties file and sets up the hardware map
	private void initializeDevices() {
		final HardwareMap hardwareMap=new HardwareMap();
		opMode.hardwareMap=hardwareMap;
		
		//load all motors into the hardware motor map
		for (int i=0;i<supervisor.getNumberOfDevices();i++) {
			Device device=supervisor.getDeviceByIndex(i);
			
			System.out.println(device+" "+i);
			
			if (device instanceof Motor) {
				Motor motor=(Motor)device;
				
				//if there is an associated position sensor, enable it
				PositionSensor sensor=motor.getPositionSensor();
				if (sensor!=null) {
					sensor.enable(timeStep);
				}
				
				String mappedName=properties.getProperty(device.getName());
				if (mappedName!=null) {
					System.out.println("Loading webots motor " + device.getName()+" as "+mappedName);
				}
				else {
					mappedName=device.getName();
					System.out.println("Loading webots motor " + mappedName);
				}
				
				String type=properties.getProperty(mappedName+".type");
				if ("servo".equalsIgnoreCase(type)) {
					String baseRotationProperty=device.getName()+".baseRotation";
					String baseRotation=properties.getProperty(baseRotationProperty);
					if (baseRotation==null||baseRotation.trim().length()==0) {
						System.out.println("No property found for "+baseRotationProperty+", so using 0 as default");
						baseRotation="0";
					}
					System.out.println("Webots motor "+mappedName+" is a servo");
	    			WebotsServoImpl servo=new WebotsServoImpl(mappedName,motor);
	    			servo.setBaseRotation(Float.parseFloat(baseRotation));
	    			hardwareMap.put(mappedName, servo);
	    		}
				else {
					WebotsDcMotorImpl webotsMotor=new WebotsDcMotorImpl(mappedName,motor);
					
					String maxPowerProperty=device.getName()+".maxPower";
					String maxPower=properties.getProperty(maxPowerProperty);
					if (maxPower==null||maxPower.trim().length()==0) {
						System.out.println("No property found for "+maxPowerProperty+", so using 10 as default");
						maxPower="10";
					}
	
					System.out.println("Using "+maxPower+" for property "+maxPowerProperty);
					webotsMotor.setMaxPower(Float.parseFloat(maxPower));
					
					hardwareMap.dcMotor.put(mappedName, webotsMotor);	
				}
			}		
			
			
		}
		
		//add any missing motors
	    for (Object key:properties.keySet()) {
	    	String property=(String)key;
	    	HardwareDevice webotsDevice=null;
	    	if (property.endsWith(".type")) {
	    		String device=property.substring(0,property.length()-5);
	    		String type=properties.getProperty(property);
	    		if ("servo".equalsIgnoreCase(type)) {
	    			webotsDevice=new WebotsServo();
	    		}
	    		else if ("continousServo".equalsIgnoreCase(type)) {
	    			webotsDevice=new WebotsContinuousServo();
	    		}
	    		else if ("motor".equalsIgnoreCase(type)) {
	    			webotsDevice=new WebotsDcMotor(device);
	    		}
	    		if (webotsDevice!=null &&  !hardwareMap.hasDevice(device)) {
	    			System.out.println("Adding empty "+type+" implementation for "+device);
	    			hardwareMap.put(device, webotsDevice);
	    		}
	    	}
	    }
		
	}

	@Override
	public void run() {
		System.out.println("Starting OpMode");
		opMode.start();
				
		if (opMode instanceof LinearOpMode) {
			LinearOpMode linearOpMode=(LinearOpMode)opMode;
			
			//Autonomous opmodes need special coordination between the OpMode loop and the simulator loop
			System.out.println("Running LinearOpMode");
		

			//if sleep time is 0, then we signal the simulator lock and wait to be signaled back
			//this effectively locks the OpMode frequency to the simulator
			//if sleep time is not 0, then the simulator lock is signaled and then the simulator will wait the associated time
			
			long sleepTime=0;
			String simSleepTimeStr=properties.getProperty("simulatorLoopSleepTime");
			if (simSleepTimeStr!=null&&simSleepTimeStr.trim().length()>0) {
				sleepTime=Long.parseLong(simSleepTimeStr);
			}
			
			while (supervisor.step(timeStep) != -1) {
				linearOpMode.loop();
				linearOpMode.internalPostLoop();
				
				//signal any threads that are waiting for a simulator tick
				PlatformSupport.signalSimulatorLock(sleepTime==0);
				if (linearOpMode.isStopped()) {
					System.out.println("OpMode stopped");
				}
				if (sleepTime>0) { 
					try {
						//we want to sleep a little bit to allow the other code to keep up with us
						//if this thread is running faster than the OpMode, the OpMode will not be running at the proper frequency
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else {
			//TeleOp opmodes just loop and call the OpMode loop method along with the simulator step
			System.out.println("Running OpMode");
			while (supervisor.step(timeStep) != -1) {
				handleGamepads();
				opMode.loop();
				opMode.internalPostLoop();

				//signal any threads that are waiting for a simulator tick (TeleOp opmodes should not, but do it just in case)
				PlatformSupport.signalSimulatorLock(false);				
			}
		}
	}

	private void handleGamepads() {
		boolean useGamepads=true;
		
		if (useGamepads) {
			gamepadSupport.processJoystick(opMode.gamepad1, opMode.gamepad2);
		}
		else {
			
			//if we are using virtual gamepad, poll the keyboard
			int key=keyboard.getKey();
			while (key!=-1) {
				switch (key) {
				case Keyboard.UP:
					opMode.gamepad1.left_stick_y++;
					break;
				case Keyboard.DOWN:
					opMode.gamepad1.left_stick_y--;
					break;
				case Keyboard.LEFT:
					opMode.gamepad1.left_stick_x--;
					break;
				case Keyboard.RIGHT:
					opMode.gamepad1.left_stick_x++;
					break;
				}
				key=keyboard.getKey();
			}
		}
		
	}

	@Override
	public void cleanup() {
		opMode.stop();
		controllerManager.quitSDLGamepad();
	}

}
