package org.ftc6448.simulator.webots;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

public class WebotsDigitalChannel implements DigitalChannel {
	protected String name;
	
	public WebotsDigitalChannel(String name) {
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
	public Mode getMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMode(Mode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMode(com.qualcomm.robotcore.hardware.DigitalChannelController.Mode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setState(boolean state) {
		//System.out.println(name+" = "+state);
	}

}
