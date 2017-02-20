package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.Vision;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class driveStraight extends Command {

	
	public double desiredRots;
	
    public driveStraight(double desiredFeet) {
        // Use requires() here to declare subsystem dependencies
       // eg. requires(chassis);
    	requires(Robot.chassis);
    	
    	desiredRots = (12 * desiredFeet) / (3.5 * Math.PI);  //Desired distance (in) / Circumference (in/rot)
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.chassis.portFront.configEncoderCodesPerRev(120);
    	Robot.chassis.portFront.setPosition(Robot.chassis.portFront.getPosition());
    	Robot.chassis.portFront.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	Robot.chassis.portFront.reverseSensor(true);
    	Robot.chassis.portFront.setAllowableClosedLoopErr(0);
    	Robot.chassis.portFront.setProfile(0);
    	Robot.chassis.portFront.configMaxOutputVoltage(4);
    	Robot.chassis.portFront.setEncPosition(0);
		
    	
    	Robot.chassis.portFront.setP(0.95);
    	Robot.chassis.portFront.setI(0.00005);
    	Robot.chassis.portFront.setD(1.15);
    	
    	Robot.chassis.starboardFront.configEncoderCodesPerRev(120);
    	Robot.chassis.starboardFront.setPosition(Robot.chassis.starboardFront.getPosition());
    	Robot.chassis.starboardFront.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	Robot.chassis.starboardFront.reverseSensor(false);
    	Robot.chassis.starboardFront.setAllowableClosedLoopErr(0);
    	Robot.chassis.starboardFront.setProfile(1);
    	Robot.chassis.starboardFront.configMaxOutputVoltage(4);
    	Robot.chassis.starboardFront.setEncPosition(0);
    	
    	Robot.chassis.starboardFront.setP(0.95);
    	Robot.chassis.starboardFront.setI(0.00005);
    	Robot.chassis.starboardFront.setD(1.15);
    	
//    	Robot.chassis.leftFront.changeControlMode(TalonControlMode.Follower);
//    	Robot.chassis.leftFront.set(Robot.chassis.rightFront.getDeviceID());
//    	Robot.chassis.leftFront.reverseOutput(true);
    	
    	Robot.chassis.portFront.enableControl();
    	Robot.chassis.starboardFront.enableControl();
    	
    	Robot.chassis.portFront.changeControlMode(TalonControlMode.Position);
    	Robot.chassis.starboardFront.changeControlMode(TalonControlMode.Position);

    	try {
			wait(1000);
		} catch (InterruptedException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	    	 
    	
    	Robot.chassis.portFront.set(desiredRots);
//    	try {
//			wait((long) 235);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	Robot.chassis.starboardFront.set(desiredRots);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 return Robot.chassis.isAtDistanceTarget(desiredRots);
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.starboardFront.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.chassis.portFront.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.chassis.starboardFront.set(0);
    	Robot.chassis.portFront.set(0);
    	Robot.chassis.stopAllDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
