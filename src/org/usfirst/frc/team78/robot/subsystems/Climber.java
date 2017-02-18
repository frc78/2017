package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public static CANTalon climberLeft = new CANTalon(RobotMap.CLIMBER_LEFT);
	public static CANTalon climberRight = new CANTalon(RobotMap.CLIMBER_RIGHT);
	
	public static void climberInit(){
		climberRight.changeControlMode(TalonControlMode.Follower);
		climberRight.set(climberLeft.getDeviceID());
		climberRight.reverseOutput(true);		
	}
	
	public static void setClimberSpeed(double speed){
		climberLeft.set(speed);
	} 
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

