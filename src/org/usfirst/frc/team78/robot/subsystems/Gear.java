package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class Gear extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CANTalon intakeMotor = new CANTalon(RobotMap.GEAR_INTAKE_MOTOR);
	DoubleSolenoid gearSolenoid = new DoubleSolenoid(RobotMap.GEAR_SOLENOID1,RobotMap.GEAR_SOLENOID2);
	
	Relay spike = new Relay(RobotMap.FLASHLIGHT);
	public boolean lightstate = false;
	
	public boolean solonoidVal = false;
	
	public boolean currentDraw = false;
	
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
		solonoidVal = true;
	}
	
	public void upGear(){
		gearSolenoid.set(DoubleSolenoid.Value.kReverse);
		solonoidVal = false;
	}
	
	public void gearSwitch(){
    	if(solonoidVal == true){
    		upGear();
    	}else if(solonoidVal == false){
    		downGear();
    	}else{
    		upGear();
    	}
    }
    
    public void stopMotor() {
    	intakeMotor.set(0);
    }
    public void gearInit(){ 
    	this.upGear();
    }
    
    public double getCurrent(){
    	return intakeMotor.getOutputCurrent();
    }
    
    public void lightOn(){
    	spike.set(Relay.Value.kForward);
    	lightstate = true;
    }
    
    public void lightOff(){
    	spike.set(Relay.Value.kOff);
    	Timer.delay(0.1);
    	spike.set(Relay.Value.kForward);
    	Timer.delay(0.1);
    	spike.set(Relay.Value.kOff);
    	Timer.delay(0.1);
    	spike.set(Relay.Value.kForward);
    	Timer.delay(0.1);
    	spike.set(Relay.Value.kOff);
    	lightstate = false;
    }
}

