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

	public static CANTalon climberStarboard = new CANTalon(RobotMap.CLIMBER_STARBOARD);
	public static CANTalon climberPort = new CANTalon(RobotMap.CLIMBER_PORT);
	
	public static void climberInit(){
		climberPort.changeControlMode(TalonControlMode.Follower);
		climberPort.set(climberStarboard.getDeviceID());
		climberPort.reverseOutput(true);		
	}
	
	public static void setClimberSpeed(double speed){
		climberStarboard.set(speed);
	} 
	
	public static void stopClimber(){
		climberStarboard.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

