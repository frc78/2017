package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class runLiveFloor extends Command {
	
	double m_speed;
	
    public runLiveFloor(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setLiveFloor(this.m_speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.setLiveFloor(this.m_speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.setLiveFloor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.setLiveFloor(0);
    }
}
