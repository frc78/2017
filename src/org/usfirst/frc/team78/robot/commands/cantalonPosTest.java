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
public class cantalonPosTest extends Command {

	
    public cantalonPosTest() {
        // Use requires() here to declare subsystem dependencies
       // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.chassis.rightFront.configEncoderCodesPerRev(120);
    	Robot.chassis.rightFront.setPosition(Robot.chassis.rightFront.getPosition());
    	Robot.chassis.rightFront.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	Robot.chassis.rightFront.reverseSensor(true);
    	Robot.chassis.rightFront.setAllowableClosedLoopErr(0);
    	Robot.chassis.rightFront.setProfile(0);
    	
    	Robot.chassis.rightFront.setP(0.6);
    	Robot.chassis.rightFront.setI(0.00078);
    	Robot.chassis.rightFront.setD(0.3);
    	
    	
    	Robot.chassis.rightFront.enableControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.rightFront.changeControlMode(TalonControlMode.Position);
    	Robot.chassis.rightFront.set(10);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(Robot.chassis.rightFront.getPosition() == 10){
    	   return true;
       }else{return false;}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.rightFront.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.chassis.stopAllDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
