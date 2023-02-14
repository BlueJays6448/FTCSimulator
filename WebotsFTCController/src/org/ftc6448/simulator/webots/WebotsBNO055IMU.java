package org.ftc6448.simulator.webots;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.MagneticFlux;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.Temperature;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import com.cyberbotics.webots.controller.InertialUnit;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareDevice;

public class WebotsBNO055IMU implements HardwareDevice,BNO055IMU {
	InertialUnit imu;
	Parameters parms;
	
	public WebotsBNO055IMU(InertialUnit imu) {
		this.imu=imu;
	}

	@Override
	public boolean initialize(Parameters parameters) {
		this.parms=parameters;
		return false;
	}

	@Override
	public Parameters getParameters() {
		return parms;
	}

	@Override
	public Orientation getAngularOrientation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orientation getAngularOrientation(AxesReference reference, AxesOrder order,
			org.firstinspires.ftc.robotcore.external.navigation.AngleUnit angleUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Acceleration getOverallAcceleration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AngularVelocity getAngularVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Acceleration getLinearAcceleration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Acceleration getGravity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Temperature getTemperature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MagneticFlux getMagneticFieldStrength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quaternion getQuaternionOrientation() {
		double []values=imu.getQuaternion();
		Quaternion q= new Quaternion((float)values[3], (float)values[0],(float)values[1], (float)values[2], 0);
		return q;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Velocity getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Acceleration getAcceleration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startAccelerationIntegration(Position initialPosition, Velocity initialVelocity, int msPollInterval) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAccelerationIntegration() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SystemStatus getSystemStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemError getSystemError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalibrationStatus getCalibrationStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSystemCalibrated() {
		return true;
	}

	@Override
	public boolean isGyroCalibrated() {
		return true;
	}

	@Override
	public boolean isAccelerometerCalibrated() {
		return true;
	}

	@Override
	public boolean isMagnetometerCalibrated() {
		return true;
	}

	@Override
	public CalibrationData readCalibrationData() {
		return null;
	}

	@Override
	public void writeCalibrationData(CalibrationData data) {
		
	}

	@Override
	public byte read8(Register register) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] read(Register register, int cb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write8(Register register, int bVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Register register, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Manufacturer getManufacturer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeviceName() {
		return "imu";
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

}
