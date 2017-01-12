package org.usfirst.frc.team2850.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TakeBackHalf {
	Spark shooterMotor;
	Encoder shooterEncoder;
	TakeBackHalf(Spark motor, Encoder Enc)
	{
		shooterMotor = motor;
		shooterEncoder = Enc;
	}

    /**
     * The target speed, in RPM
     */
    private static double targetRpm = 0;
    
    /**
     * The ratio (0-1) of motor power that is being applied
     */
    private static double motorPower = 0;
    
    /**
     * Gain (G)
     */
    private static double gain = SmartDashboard.getNumber("targetVelocity",1E-5);
    		//1E-5;
    /**
     * The error encountered during the last loop
     */
    private static double lastError = 10;
    /**
     * The take-back-half variable (b)
     */
    private static double tbh = 0;

    
    /**
     * The approximate speed of the shooter wheels, in RPM,
     * at full motor power. This is used for spinup optimization.
     */
    private static final double kMaxRpm = 4000;


    protected void runSpeedControl() {

        double error = targetRpm - getActualRpm();
		
        motorPower += gain * error;

        motorPower = clamp(motorPower);

        //If the error has changed in sign since the last processing
        if (isPositive(lastError) != isPositive(error)) {
            motorPower = 0.5 * (motorPower + tbh);
            tbh = motorPower;

            lastError = error;
        }

        shooterMotor.set(motorPower);

    }

   
    public double getActualRpm() {
        return shooterEncoder.getRate();
    }

  
    public boolean isOnTarget() {
        double difference = getActualRpm() - targetRpm;

        return Math.abs(difference) < 100;
    }

   
    public void setTargetRpm(double newRpm) {
        
        //Set up values for optimized spinup to the target
        if(targetRpm < newRpm) {
            lastError = 1;
        }
        else if(targetRpm > newRpm) {
            lastError = -1;
        }
        tbh = (2 * (newRpm / kMaxRpm)) - 1;
        
        targetRpm = newRpm;
    }

    /*
     * Clamp a value to within +/- 1, for use with speed controllers
     */
    public double clamp(double input) {
        if (input > 1) {
            return 1;
        }
        if (input < -1) {
            return -1;
        }
        return input;
    }

    /*
     * Determine if a value is positive
     */
    public boolean isPositive(double input) {
        return input > 0;
    }

    protected void stopMotor() {
        shooterMotor.set(0);
    }
    public double getGain()
    {
    	return gain;
    }
    public void setGain(double value)
    {
    	gain = value;
    }
}
