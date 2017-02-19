package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class Gear extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CANTalon intakeMotor = new CANTalon(RobotMap.GEAR_INTAKE_MOTOR);
	DoubleSolenoid gearSolenoid = new DoubleSolenoid(RobotMap.GEAR_SOLENOID1,RobotMap.GEAR_SOLENOID2);

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
    	this.intakeMotor.set(speed);
    }
    
    public void stopIntakeMotor() {
    	this.intakeMotor.set(0);
    }
    
	public void downGear(){
		gearSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void upGear(){
		gearSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
    
    public void stopMotor() {
    	intakeMotor.set(0);
    }
    public void gearInit(){
    	this.upGear();
    }
}

