package org.usfirst.frc.team78.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class doNothing extends Command {
	
	long time;
	boolean endCommand = false;
	
    public doNothing(long Time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			wait(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			endCommand = true;
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return endCommand;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
