package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	//Motors
	CANTalon intakeMotor = new CANTalon(RobotMap.INTAKE_MOTOR);
	
	//Sensors
	public final Encoder intakeEnc = new Encoder(RobotMap.INTAKE_ENCA, RobotMap.INTAKE_ENCB);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

