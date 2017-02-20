package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shooterPosTest extends Command {

	double targetRPM;
	
    public shooterPosTest(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	
    	targetRPM = target;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.shooterPort.changeControlMode(TalonControlMode.Speed);
    	Robot.shooter.shooterPort.configEncoderCodesPerRev(12);
//    	Robot.shooter.shooterLeft.setPosition(Robot.shooter.shooterLeft.getPosition());
    	Robot.shooter.shooterPort.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	Robot.shooter.shooterPort.reverseSensor(true);
    	Robot.shooter.shooterPort.setAllowableClosedLoopErr(0);
    	Robot.shooter.shooterPort.setProfile(0);
    	Robot.shooter.shooterPort.configMaxOutputVoltage(10);
    	
    	Robot.shooter.shooterPort.setP(0.5);
    	Robot.shooter.shooterPort.setI(0);
    	Robot.shooter.shooterPort.setD(0);
    	Robot.shooter.shooterPort.setF(1.06);
    	
    	Robot.shooter.shooterPort.enableControl();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.shooter.shooterLeft.changeControlMode(TalonControlMode.Speed);
    	Robot.shooter.shooterPort.set(targetRPM);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.shooterPort.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.shooter.shooterPort.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
