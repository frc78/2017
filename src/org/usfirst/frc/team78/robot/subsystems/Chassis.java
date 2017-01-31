package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {

//Motors
	CANTalon leftFront = new CANTalon(RobotMap.LEFT_FRONT);
	CANTalon leftRear = new CANTalon(RobotMap.LEFT_REAR);
	CANTalon leftTop = new CANTalon(RobotMap.LEFT_TOP);
	CANTalon rightFront = new CANTalon(RobotMap.RIGHT_FRONT);
	CANTalon rightRear = new CANTalon(RobotMap.RIGHT_REAR);
	CANTalon rightTop = new CANTalon(RobotMap.RIGHT_TOP);

//Sensors	
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
	
//Drive Methods	
	public void setSpeed(double left, double right){
    	leftFront.set(left);
    	leftRear.set(left);
    	leftTop.set(left);
    	rightFront.set(right);
    	rightRear.set(right);
    	rightTop.set(right);
    }
	
	public void driveWithJoysticks(){
    	
    	double left = Robot.oi.getDriverLeftStick();
    	double right = Robot.oi.getDriverRightStick();
    	
    	setSpeed(-left,right);
    }
	
	public void stopAllDrive(){
    	setSpeed(0,0);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
}

