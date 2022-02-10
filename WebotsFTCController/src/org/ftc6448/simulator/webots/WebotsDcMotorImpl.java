package org.ftc6448.simulator.webots;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.ftc6448.utils.NumberUtils;

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.PositionSensor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class WebotsDcMotorImpl implements DcMotorEx{
	
	protected final String name;
	protected final Motor motor;
	protected float maxPower;
	
	float power;
	Direction direction;
	ZeroPowerBehavior zeroPowerMode;
	
	//extra power that can be used to add drag on a motor to simulate uneven power
	float extraPower;
	
	public WebotsDcMotorImpl(String name, Motor motor) {
		this.motor=motor;
		this.name=name;
		
		direction=Direction.FORWARD;
		zeroPowerMode=ZeroPowerBehavior.BRAKE;
		maxPower=10;
	}


	@Override
	public void setDirection(Direction direction) {
		this.direction=direction;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setPower(double inputPower) {
		if (direction==Direction.REVERSE) {
			inputPower=-inputPower;
		}
		internalSetPower(inputPower);
	}
	
	private void internalSetPower(double inputPower) {
		if (inputPower==0) {
			power=0;
			motor.setPosition(Double.POSITIVE_INFINITY);
			motor.setVelocity(0);
		}
		else {
			//need to scale from -1 to 1 to actual values
			power=limitPower(inputPower);
			
			motor.setPosition(Double.POSITIVE_INFINITY);
			motor.setVelocity(extraPower+scalePower(power));
		}
	}

	public final double scalePower(double power) {
		//scales a value from -1 to 1 range to MAX_SPEED range
		return NumberUtils.scale(power, -1, 1, -maxPower, maxPower);
	}
	
	private static final float limitPower(double inputPower) {
		return (float)Math.min(1, Math.max(-1, inputPower));
	}

	 
	@Override
	public double getPower() {
		return power;
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
	public MotorConfigurationType getMotorType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMotorType(MotorConfigurationType motorType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DcMotorController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPortNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ZeroPowerBehavior getZeroPowerBehavior() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPowerFloat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getPowerFloat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTargetPosition(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTargetPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isBusy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurrentPosition() {
		PositionSensor sensor=motor.getPositionSensor();
		if (sensor==null) {
			System.out.println("No PositionSensor defined in joint for motor "+name);
			return 0;
		}
		//need a mapping probably
		return (int)sensor.getValue();
	}

	@Override
	public void setMode(RunMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RunMode getMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMotorEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMotorDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMotorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVelocity(double angularRate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelocity(double angularRate, AngleUnit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getVelocity(AngleUnit unit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPIDCoefficients(RunMode mode, PIDCoefficients pidCoefficients) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPIDFCoefficients(RunMode mode, PIDFCoefficients pidfCoefficients)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelocityPIDFCoefficients(double p, double i, double d, double f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPositionPIDFCoefficients(double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDCoefficients getPIDCoefficients(RunMode mode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PIDFCoefficients getPIDFCoefficients(RunMode mode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTargetPositionTolerance(int tolerance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTargetPositionTolerance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCurrent(CurrentUnit unit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCurrentAlert(CurrentUnit unit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCurrentAlert(double current, CurrentUnit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOverCurrent() {
		// TODO Auto-generated method stub
		return false;
	}


	public float getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(float maxPower) {
		this.maxPower = maxPower;
	}
}
