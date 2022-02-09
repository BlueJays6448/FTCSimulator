package org.ftc6448.simulator.webots;

import org.ftc6448.utils.NumberUtils;

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.PositionSensor;
import com.github.javaparser.Position;
import com.qualcomm.robotcore.hardware.Servo;

public class WebotsServoImpl implements Servo {
	protected final String name;
	protected final Motor motor;
	
	protected float baseRotation;
	
	public WebotsServoImpl(String name, Motor motor) {
		this.motor=motor;
		this.name=name;
	}
	
	@Override
	public Manufacturer getManufacturer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeviceName() {
		return name;
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
		double max=motor.getMaxPosition();
		double min=motor.getMinPosition();

		double scaledPos= NumberUtils.scale(position, 0, 1, min, max);
		double finalRadians=scaledPos+baseRotation;
		motor.setPosition(finalRadians);
	}

	@Override
	public double getPosition() {
		PositionSensor sensor=motor.getPositionSensor();
		if (sensor==null) {
			System.out.println("No PositionSensor defined in joint for motor "+name);
			return Double.NaN;
		}
		double radians= motor.getPositionSensor().getValue()-baseRotation;
		double max=motor.getMaxPosition();
		double min=motor.getMinPosition();
			
		double scaledPos=NumberUtils.scale(radians,min, max, 0, 1);

		return scaledPos;
	}

	@Override
	public void scaleRange(double min, double max) {
		// TODO Auto-generated method stub
		
	}

	public void setBaseRotation(float rotRads) {
		this.baseRotation=rotRads;
	}

}
