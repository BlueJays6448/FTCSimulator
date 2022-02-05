package org.ftc6448.simulator.webots;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Empty servo implementation.
 *
 */
public class WebotsServo implements Servo{

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
	public void setDirection(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(double position) {
		//System.out.println("Empty servo - set position "+position);
	}

	@Override
	public double getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void scaleRange(double min, double max) {
		// TODO Auto-generated method stub
		
	}

}
