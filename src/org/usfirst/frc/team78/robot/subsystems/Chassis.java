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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {

//Motors
	public CANTalon starboardFront = new CANTalon(RobotMap.STARBOARD_FRONT);
		   CANTalon starboardRear = new CANTalon(RobotMap.STARBOARD_REAR);
	       CANTalon starboardTop = new CANTalon(RobotMap.STARBOARD_TOP);
	public CANTalon portFront = new CANTalon(RobotMap.PORT_FRONT);
		   CANTalon portRear = new CANTalon(RobotMap.PORT_REAR);
		   CANTalon portTop = new CANTalon(RobotMap.PORT_TOP);
	
	public void motorInit(){
		starboardRear.changeControlMode(TalonControlMode.Follower);
		starboardRear.set(starboardFront.getDeviceID());
		starboardTop.changeControlMode(TalonControlMode.Follower);
		starboardTop.set(starboardFront.getDeviceID()); 
		
		starboardFront.reverseOutput(true);
		
		portRear.changeControlMode(TalonControlMode.Follower);
		portRear.set(portFront.getDeviceID());
		portTop.changeControlMode(TalonControlMode.Follower);
		portTop.set(portFront.getDeviceID());
		
		Robot.chassis.portFront.configEncoderCodesPerRev(120);
		Robot.chassis.starboardFront.configEncoderCodesPerRev(120);
		
		portFront.setEncPosition(0);
		starboardFront.setEncPosition(0);
		
		starboardFront.reverseSensor(true);
	}
	 

//Sensors	
	
	// The encoders aren't used here, they are programmed with the talons
	
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
//	public final Encoder leftEnc = new Encoder(RobotMap.LEFT_DRIVE_ENCA, RobotMap.LEFT_DRIVE_ENCB);
//	public final Encoder rightEnc = new Encoder(RobotMap.RIGHT_DRIVE_ENCA, RobotMap.RIGHT_DRIVE_ENCB);
	
	
//Variables
	public boolean timerStart = false;
	public boolean atTarget = false;
	final double GYRO_P = (0.023);	
	
//TIMER
	public Timer timer = new Timer();	
	
//Drive Methods	
	public void setSpeed(double port, double starboard){
		//other motors are set from the CANtalon following mode 
		
		// leading with intake
//    	leftFront.set(-left);
//    	rightFront.set(right);
		
		
		//leading with gear
		starboardFront.set(starboard);
    	portFront.set(-port);
		
    }
	
	public void driveWithJoysticks() {
    	double port = Robot.oi.getDriverLeftStick();
    	double starboard = Robot.oi.getDriverRightStick();

    	if(Robot.oi.driverStick.getRawButton(5) && Robot.oi.driverStick.getRawButton(6)){
    		starboard *= 0.3;
    		port *= 0.3;
    	}
    	
    	setSpeed(port, starboard);
    }
	
	public void setTurnSpeed(double speed){
	    	setSpeed(speed, -speed);
	}
	
	public void stopAllDrive(){
    	setSpeed(0,0);
    }
//end drive methods
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
//Logic Methods
    public double headingCorrection (double heading){
    	double driftError = heading - getAngle();
    	
    	if (driftError < -180){
    		driftError = driftError + 360;
    	}
    	else if (driftError > 180){
    		driftError = driftError - 360;
    	}
    	
    	
    	return ((GYRO_P)*driftError);
    	//setSpeed(((GYRO_P)*driftError), -((GYRO_P)*driftError));    	
    }
    
    public double turnAngleAdditional(double target){
    	double speed;

    	speed = headingCorrection(target);
    	
    	if (speed > .7){
    		speed = .7;
    	}
    	if(speed < -.7){ 
    		speed = -.7;
    	}
    	
    	if (speed < .13 && speed > 0){//real robot is .25 everywhere
    		speed = .13;
    	}
    	if(speed > -.13 && speed < 0){ 
    		speed = -.13;
    	}
    	
    	return speed;
    	//setTurnSpeed(speed);
    }   

    public boolean isAtTurnTarget(double target){
    	atTarget = false;
    	
    	double error = target - getAngle();
    	
    	if (error < -180){
    		error = error + 360;
    	}
    	else if (error > 180){
    		error = error - 360;
    	}

    	if ((error < 5) && (error > -5)){
    		if(timerStart == false){
   				timerStart = true;
   				timer.start();
   			}
    		
   		}
   	
   		else{
   		
   			if(timerStart == true){
    			timer.stop();
    			timer.reset();
    			timerStart = false;
   			}
   		}
    	
   		if(timer.get() >.25){
   			atTarget = true;
    	}
    	
    	return atTarget;
    	
    }
    
    public boolean isAtDistanceTarget(double target){
    	atTarget = false;
    	
    	double error = target - portFront.getPosition();

    	if ((error < 0.5) && (error > -0.5)){
    		if(timerStart == false){
   				timerStart = true;
   				timer.start();
   			}
    		
   		}
   	
   		else{
   		
   			if(timerStart == true){
    			timer.stop();
    			timer.reset();
    			timerStart = false;
   			}
   		}
    	
   		if(timer.get() >0.25){
   			atTarget = true;
    	}
    	
    	return atTarget;
    	
    }
//end logic methods
    
//Sensor Methods
    public void resetDriveEncoders() {
//    	leftEnc.reset();
//    	rightEnc.reset();
    }
    
    public double getAngle(){
    	return ahrs.getAngle();
    }
    
    public double getPitch(){
    	return ahrs.getPitch();//just look at all the different gets, figure out what is going on
    }
    
    public double getRoll(){
    	return ahrs.getRoll();//just look at all the different gets, figure out what is going on
    }
    
    
}

