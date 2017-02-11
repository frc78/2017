package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.PigeonImu.CalibrationMode;
import com.kauailabs.navx.frc.AHRS;
 
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {

//Motors
	public CANTalon leftFront = new CANTalon(RobotMap.LEFT_FRONT);
		   CANTalon leftRear = new CANTalon(RobotMap.LEFT_REAR);
	       CANTalon leftTop = new CANTalon(RobotMap.LEFT_TOP);
	public CANTalon rightFront = new CANTalon(RobotMap.RIGHT_FRONT);
		   CANTalon rightRear = new CANTalon(RobotMap.RIGHT_REAR);
		   CANTalon rightTop = new CANTalon(RobotMap.RIGHT_TOP);
	
	public void motorInit(){
		leftRear.changeControlMode(TalonControlMode.Follower);
		leftRear.set(leftFront.getDeviceID());
		leftTop.changeControlMode(TalonControlMode.Follower);
		leftTop.set(leftFront.getDeviceID()); 
		
		rightRear.changeControlMode(TalonControlMode.Follower);
		rightRear.set(rightFront.getDeviceID());
		rightTop.changeControlMode(TalonControlMode.Follower);
		rightTop.set(rightFront.getDeviceID());	
		
		Robot.chassis.rightFront.configEncoderCodesPerRev(120);
		Robot.chassis.leftFront.configEncoderCodesPerRev(120);
	}
	 

//Sensors	
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
	public final Encoder leftEnc = new Encoder(RobotMap.LEFT_DRIVE_ENCA, RobotMap.LEFT_DRIVE_ENCB);
	public final Encoder rightEnc = new Encoder(RobotMap.RIGHT_DRIVE_ENCA, RobotMap.RIGHT_DRIVE_ENCB);
	
//Drive Methods	
	public void setSpeed(double left, double right){
    	leftFront.set(-left);
    	rightFront.set(right);
    	//other motors are set from the CANtalon following mode 
    }
	
	public void driveWithJoysticks(){
    	
    	double left = Robot.oi.getDriverLeftStick();
    	double right = Robot.oi.getDriverRightStick();
    	
    	setSpeed(-left, right);
    }
	
	public void stopAllDrive(){
    	setSpeed(0,0);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
//Sensor Methods
    public void resetDriveEncoders() {
    	leftEnc.reset();
    	rightEnc.reset();
    }
    
    
    
}

