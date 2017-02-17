package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CANTalon intakeMotor = new CANTalon(RobotMap.GEAR_INTAKE_MOTOR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntake(String direction, double speed) {
    	if(direction == "in") {
    		speed *= -1;
    	} else if(direction == "out") {
    		speed *= 1;
    	}
    }
    
    public void stopMotor() {
    	intakeMotor.set(0);
    }
    
}

