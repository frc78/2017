package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	
	//Motors
	public CANTalon shooterLeft = new CANTalon(RobotMap.SHOOTER_LEFT);
	public CANTalon shooterRight = new CANTalon(RobotMap.SHOOTER_RIGHT);
	
	public void shooterMotorInit() {
		shooterRight.changeControlMode(TalonControlMode.Follower);
		shooterRight.set(shooterLeft.getDeviceID());
		shooterRight.reverseOutput(true);
	}
	
	//Sensors
	public final Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENCA, RobotMap.SHOOTER_ENCB, true, EncodingType.k4X);

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

