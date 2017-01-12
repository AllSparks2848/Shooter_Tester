package org.usfirst.frc.team2850.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	

	public static TakeBackHalf TBH;
	public void robotInit() {
		Definitions.initPerf();
		TakeBackHalf TBH = new TakeBackHalf(Definitions.shooter,Definitions.shooterEnc);
		TBH.setTargetRpm(4000);
	}


	@Override
	public void autonomousInit() {

	}


	@Override
	public void autonomousPeriodic() {
	
	}

	
	public void teleopPeriodic() {
		//take back half control
		if(Definitions.xbox1.getRawButton(1))// a button
		{
			TBH.runSpeedControl();
			SmartDashboard.putNumber("Speed", TBH.getActualRpm());
			SmartDashboard.putNumber("Amps", Definitions.pdp.getCurrent(2));
			System.out.println(Definitions.pdp.getCurrent(2)+TBH.getActualRpm()+" "+ TBH.getGain());
		}
		else
		{
			TBH.stopMotor();
		}
	//straight power	
		if(Definitions.xbox1.getRawButton(2)) //b button
		{
			Definitions.shooter.set(SmartDashboard.getNumber("Power",0));
			SmartDashboard.putNumber("Speed", TBH.getActualRpm());
			SmartDashboard.putNumber("Amps", Definitions.pdp.getCurrent(2));
			System.out.println(Definitions.pdp.getCurrent(2)+TBH.getActualRpm()+" "+ TBH.getGain());
		}
		else
		{
			Definitions.shooter.set(0);
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

