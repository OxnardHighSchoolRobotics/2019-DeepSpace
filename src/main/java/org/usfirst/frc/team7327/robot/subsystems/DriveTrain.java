package org.usfirst.frc.team7327.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team7327.robot.commands.Drive;

public class DriveTrain extends Subsystem {
  public static TalonSRX drive; 
  public DriveTrain(){
    drive.set(ControlMode.PercentOutput, 1);
  }
  @Override public void initDefaultCommand() { setDefaultCommand(new Drive()); }
  
  public void updateDashboard(){}
}
