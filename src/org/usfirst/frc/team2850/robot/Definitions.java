package org.usfirst.frc.team2850.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;

public class Definitions {

	public static Spark shooter;
	public static Encoder shooterEnc;
	public static PowerDistributionPanel pdp;
	public static Joystick xbox1;
	public static void initPerf()
	{
		shooter = new Spark(0);
		shooterEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		pdp = new PowerDistributionPanel();
		xbox1 = new Joystick(0);
		
	}
	
}
