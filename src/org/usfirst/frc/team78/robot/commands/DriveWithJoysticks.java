package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoysticks extends Command {
	
	public static boolean heading_correction_toggled = false;
	
    public DriveWithJoysticks() {
    	requires(Robot.chassis);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.ahrs.reset();
    	Robot.chassis.motorInit();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.chassis.driveWithJoysticks(0.8);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopAllDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
