package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.subsystems.Gear;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class gearIntake extends Command {
	
	String direction;
	double speed;
	double speedx;
	int i;

    public gearIntake(String Direction, double Speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    	direction = Direction;
    	speedx = Speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	i = 0;
    	speed = speedx;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gear.getCurrent() >= 5 && i > 20){
    		speed = 0;
    	}else{
    		speed = speed;
    	}
    	Robot.gear.setIntake(direction, speed);  
    	
    	i++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gear.stopIntakeMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
