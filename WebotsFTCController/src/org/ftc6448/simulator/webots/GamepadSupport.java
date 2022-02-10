package org.ftc6448.simulator.webots;

import java.util.Properties;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import com.studiohartman.jamepad.ControllerUnpluggedException;

public class GamepadSupport {
	final ControllerManager controllerManager;
	
	ControllerIndex gamepad1Controller;
	ControllerIndex gamepad2Controller;
	
	public GamepadSupport(Properties properties,ControllerManager controllerManager) {
		this.controllerManager=controllerManager;

		String name=properties.getProperty("gamepad2");
		gamepad1Controller=loadControllerIndex(controllerManager,name,true);
		if (gamepad1Controller!=null) {
			try {
				System.out.println("Gamepad 1 "+gamepad1Controller.getName()+" at index "+gamepad1Controller.getIndex());
			} catch (ControllerUnpluggedException e) {
				gamepad1Controller=null;
				System.out.println("Could not connect to gamepad controller 1: '"+name+"' due to exception: "+e);
			}
		}
		else {
			if (name==null) {
				name="";
			}
			System.out.println("Could not connect to gamepad controller 1: '"+name+"'");
		}
		name=properties.getProperty("gamepad2");
		gamepad2Controller=loadControllerIndex(controllerManager,name,false);	
		if (gamepad2Controller!=null) {
			try {
				System.out.println("Gamepad 3 "+gamepad2Controller.getName()+" at index "+gamepad2Controller.getIndex());
			} catch (ControllerUnpluggedException e) {
				gamepad2Controller=null;
				System.out.println("Could not connect to gamepad controller 2: '"+name+"' due to exception: "+e);
			}
		}
		else {
			if (name==null) {
				name="";
			}
			System.out.println("Could not connect to gamepad controller 2: '"+name+"'");
		}
	}
	
	private static ControllerIndex loadControllerIndex(ControllerManager controllerManager,String gamepadName,boolean gamepad1) {

		if (gamepadName==null||gamepadName.trim().length()==0) {
			int numControllers=controllerManager.getNumControllers();
			if (gamepad1) {
				if (numControllers>=1) {
					return controllerManager.getControllerIndex(0);
				}
			}
			else if (numControllers>=2) {
				return controllerManager.getControllerIndex(1);
			}
		}
		else {
			for (int i=0;i<controllerManager.getNumControllers();i++) {
				ControllerIndex curController=controllerManager.getControllerIndex(i);
				try {
					//try by name first
					if (gamepadName.equalsIgnoreCase(curController.getName())) {
						return curController;
					}
					//else try by index
					else if (Integer.toString(i+1).equals(gamepadName)) {
						return curController;
					}
				} catch (ControllerUnpluggedException e) {
					System.out.println("Error checking controller: "+e);
					return null;
				}
			}
		}
		return null;
	}
	

	public void processJoystick(Gamepad gamepad1, Gamepad gamepad2) {
		if (gamepad1Controller!=null) {
			ControllerState currentState=controllerManager.getState(gamepad1Controller.getIndex());
			gamepad1.left_stick_x=getAnalogValue(currentState.leftStickX);
			gamepad1.left_stick_y=getAnalogValue(currentState.leftStickY);
			gamepad1.right_stick_x=getAnalogValue(currentState.rightStickX);
			gamepad1.right_stick_y=getAnalogValue(currentState.rightStickY);
			
			gamepad1.a=currentState.a;
			gamepad1.b=currentState.b;
			gamepad1.x=currentState.x;
			gamepad1.y=currentState.y;
			
			gamepad1.dpad_left=currentState.dpadLeft;
			gamepad1.dpad_right=currentState.dpadRight;
			gamepad1.dpad_up=currentState.dpadUp;
			gamepad1.dpad_down=currentState.dpadDown;
		}
		
		if (gamepad2Controller!=null) {
			ControllerState currentState=controllerManager.getState(gamepad2Controller.getIndex());
			gamepad2.left_stick_x=getAnalogValue(currentState.leftStickX);
			gamepad2.left_stick_y=getAnalogValue(currentState.leftStickY);
			gamepad2.right_stick_x=getAnalogValue(currentState.rightStickX);
			gamepad2.right_stick_y=getAnalogValue(currentState.rightStickY);
			
			gamepad2.a=currentState.a;
			gamepad2.b=currentState.b;
			gamepad2.x=currentState.x;
			gamepad2.y=currentState.y;
			
			gamepad2.dpad_left=currentState.dpadLeft;
			gamepad2.dpad_right=currentState.dpadRight;
			gamepad2.dpad_up=currentState.dpadUp;
			gamepad2.dpad_down=currentState.dpadDown;
		}
		
	}
	
	private static final float deadzone=0.05f;
	
	private static final float getAnalogValue(float value) {
		//deadzone in middle
		if (Math.abs(value)<deadzone) {
			return 0;
		}
		return Range.clip(value, -1, 1);
	}
	
}
