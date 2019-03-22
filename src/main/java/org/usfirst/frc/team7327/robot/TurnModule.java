package org.usfirst.frc.team7327.robot;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnModule{
    private Notifier TurningPID; 
    private double error, sumError, diffError, lastError;
    public static double PIDOutput = 0;
    private double navTo; 
    private boolean on; 
    
    public TurnModule(double kP, double kI, double kD) {
    	sumError = 0; 
    	lastError = getError(); 
    	error = lastError; 
    	TurningPID = new Notifier(() ->  {
    		//SmartDashboard.putNumber("navTo: ", navTo);
    		//SmartDashboard.putNumber("NavAngle: ", Robot.NavAngle());
    		error = getError(); 
    		diffError = lastError - getError(); 
    		sumError += getError(); 
            PIDOutput = kP * getError() + kI * sumError + kD * diffError; 
            lastError = error;
    	}); 
    	TurningPID.startPeriodic(0.01);
    	
    }

    public double getError(){
		//Why does subtracting the Robot.NavAngle() crash the driver station. 
		//Who cares we can just subtract Robot.NavAngle() while setting Yaw in Drive. 
    	double navFinal = boundHalfDegrees(navTo)/180; // - Robot.NavAngle(); 
        return navFinal;
    }
	
	public static double boundHalfDegrees(double angle_degrees) {
        while (angle_degrees >= 180.0) angle_degrees -= 360.0;
        while (angle_degrees < -180.0) angle_degrees += 360.0;
        return angle_degrees;
    }

    public void setYaw(double degree){ navTo = degree; }
    
    public boolean setOn(boolean flipOn) { 
    	on = flipOn; 
    	return on; 
    }

    public double getPIDOutput(){ return PIDOutput; }

}