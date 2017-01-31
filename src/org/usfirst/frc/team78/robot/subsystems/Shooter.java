package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
<<<<<<< HEAD
	
	//Motors
	CANTalon shooterLeft = new CANTalon(RobotMap.SHOOTER_LEFT);
	CANTalon shooterRight = new CANTalon(RobotMap.SHOOTER_RIGHT);
	
	//Sensors
	public final Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENCA, RobotMap.SHOOTER_ENCB, true, EncodingType.k4X);
=======

	CANTalon shooterLeft = new CANTalon(RobotMap.SHOOTERLEFT);
	CANTalon shooterRight = new CANTalon(RobotMap.SHOOTERRIGHT);
	
	public final Encoder shooterEnc = new Encoder(RobotMap.SHOOTERENCA, RobotMap.SHOOTERENCB, true, EncodingType.k4X);
>>>>>>> refs/remotes/origin/robot_skeleton

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

