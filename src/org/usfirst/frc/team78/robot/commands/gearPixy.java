package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class gearPixy extends Command {

	double speed;
	double scale = 0.005;
	double output;
	
    public gearPixy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Vision.gearXPos > 165){
    		speed = (Vision.gearXPos - 165) * scale;
    		Robot.chassis.setSpeed(-speed, -speed);
    	}else if(Vision.gearXPos < 155){
    		speed = (155 - Vision.gearXPos) * scale;
    		Robot.chassis.setSpeed(speed, speed);
    	}else{
    		Robot.chassis.stopAllDrive();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
