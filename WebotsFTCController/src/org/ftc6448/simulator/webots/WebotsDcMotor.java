package org.ftc6448.simulator.webots;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

/**
 * Empty motor implementation.
 *
 */
public class WebotsDcMotor implements DcMotorEx{
	
	protected final String name;
	
	public WebotsDcMotor(String name) {
		this.name=name;
		
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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		
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

}
