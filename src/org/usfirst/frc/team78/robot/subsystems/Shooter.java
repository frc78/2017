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
	public CANTalon shooterPort = new CANTalon(RobotMap.SHOOTER_PORT);
	public CANTalon shooterStarboard = new CANTalon(RobotMap.SHOOTER_STARBOARD);
	public CANTalon liveFloor = new CANTalon(RobotMap.LIVE_FLOOR);
	public CANTalon shooterFeed = new CANTalon(RobotMap.SHOOTER_FEED);
	
	double m_FloorSpeed;
	double m_ShooterFeed;
	
	public void shooterMotorInit() {
		shooterStarboard.changeControlMode(TalonControlMode.Follower);
		shooterStarboard.set(shooterPort.getDeviceID());
		shooterStarboard.reverseOutput(true);
		
		shooterPort.configEncoderCodesPerRev(12);
		shooterPort.setEncPosition(0);
		
		m_FloorSpeed = 0;
		m_ShooterFeed = 0;
	}
	
	//Sensors
	//this encoder may get plugged into the talon
//	public final Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENCA, RobotMap.SHOOTER_ENCB, true, EncodingType.k4X);
	
	//Motor methods
	public void setLiveFloor(double speed) {
		m_FloorSpeed = speed;
		liveFloor.set(m_FloorSpeed);
	}
	
	public void setShooterFeed(double speed) {
		m_ShooterFeed = speed;
		shooterFeed.set(m_ShooterFeed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

