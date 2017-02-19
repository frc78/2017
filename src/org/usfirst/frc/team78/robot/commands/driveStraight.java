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
    	
    	Robot.chassis.rightFront.configEncoderCodesPerRev(120);
    	Robot.chassis.rightFront.setPosition(Robot.chassis.rightFront.getPosition());
    	Robot.chassis.rightFront.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	Robot.chassis.rightFront.reverseSensor(true);
    	Robot.chassis.rightFront.setAllowableClosedLoopErr(0);
    	Robot.chassis.rightFront.setProfile(0);
    	Robot.chassis.rightFront.configMaxOutputVoltage(8);
    	
    	Robot.chassis.rightFront.setP(0.95);
    	Robot.chassis.rightFront.setI(0.00005);
    	Robot.chassis.rightFront.setD(1.15);
    	
    	Robot.chassis.leftFront.setP(0.95);
    	Robot.chassis.leftFront.setI(0.00005);
    	Robot.chassis.leftFront.setD(1.15);
    	
    	Robot.chassis.leftFront.configEncoderCodesPerRev(120);
    	Robot.chassis.leftFront.setPosition(Robot.chassis.rightFront.getPosition());
    	Robot.chassis.leftFront.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	Robot.chassis.leftFront.reverseSensor(true);
    	Robot.chassis.leftFront.setAllowableClosedLoopErr(0);
    	Robot.chassis.leftFront.setProfile(0);
    	Robot.chassis.leftFront.configMaxOutputVoltage(8);
    	
//    	Robot.chassis.leftFront.changeControlMode(TalonControlMode.Follower);
//    	Robot.chassis.leftFront.set(Robot.chassis.rightFront.getDeviceID());
//    	Robot.chassis.leftFront.reverseOutput(true);
    	
    	Robot.chassis.rightFront.enableControl();
    	Robot.chassis.leftFront.enableControl();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.rightFront.changeControlMode(TalonControlMode.Position);
    	Robot.chassis.rightFront.set(desiredRots);
    	
    	Robot.chassis.leftFront.changeControlMode(TalonControlMode.Position);
    	Robot.chassis.leftFront.set(desiredRots);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 return Robot.chassis.isAtDistanceTarget(desiredRots);
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.leftFront.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.chassis.rightFront.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.chassis.leftFront.set(0);
    	Robot.chassis.rightFront.set(0);
    	Robot.chassis.stopAllDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
