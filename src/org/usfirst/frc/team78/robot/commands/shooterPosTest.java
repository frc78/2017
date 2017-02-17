package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shooterPosTest extends Command {

    public shooterPosTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.shooterLeft.configEncoderCodesPerRev(12);
    	//Robot.shooter.shooterLeft.setPosition(Robot.shooter.shooterLeft.getPosition());
    	Robot.shooter.shooterLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	//Robot.shooter.shooterLeft.reverseSensor(true);
    	Robot.shooter.shooterLeft.setAllowableClosedLoopErr(0);
    	Robot.shooter.shooterLeft.setProfile(0);
    	
    	Robot.shooter.shooterLeft.setP(0.5);
    	Robot.shooter.shooterLeft.setI(0);
    	Robot.shooter.shooterLeft.setD(0);
    	Robot.shooter.shooterLeft.setF(0);
    	
    	Robot.shooter.shooterLeft.enableControl();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.shooterLeft.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
