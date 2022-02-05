package org.ftc6448.simulator.webots;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ServoController;

/**
 * Empty continuous servo implementation.
 *
 */
public class WebotsContinuousServo implements CRServo{

	@Override
	public void setDirection(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPower(double power) {
		//System.out.println("Empty continuous servo - set power "+power);
		
	}

	@Override
	public double getPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Manufacturer getManufacturer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeviceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConnectionInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetDeviceConfigurationForOpMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServoController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPortNumber() {
		// TODO Auto-generated method stub
		return 0;
	}


}
