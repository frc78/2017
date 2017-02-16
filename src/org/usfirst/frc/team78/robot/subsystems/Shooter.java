package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	
	//Motors
	CANTalon shooterLeft = new CANTalon(RobotMap.SHOOTER_LEFT);
	CANTalon shooterRight = new CANTalon(RobotMap.SHOOTER_RIGHT);
	
	//shooterLeft is the master motor, shooterRight is the follower (PID can only have 1 output)
	public void PIDShooterInit() {
		//set to follower
		shooterRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		//set to reverse output (opposite motors)
		shooterRight.reverseOutput(true);
		//set to follow shooterLeft? Does this work? I'm not sure.
		shooterRight.set(shooterLeft.getDeviceID());
	}
	
	//Sensors
	public final Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENCA, RobotMap.SHOOTER_ENCB, true, EncodingType.k4X);
	
	public static final double Kp = 0.1;
	public static final double Ki = 0.001;
	public static final double Kd = 0.0;
	public PIDController shooterControl = new PIDController(Kp, Ki, Kd, shooterEnc, shooterLeft);
	
	
	//Sensors
	
	public double getShooterRPM() {
		return shooterEnc.getRate();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    
    //------------------------------------------
    //PID STUFF
    //------------------------------------------
    
    //Declare and initiate constants
//    public static final double Kp = 0;
//    public static final double Ki = 0;
//    public static final double Kd = 0;
//    
//    //Methods for calculating P, I, and D values
//    private double P_Value(double error) {
//    	return Kp * error;
//    }
//    
//    private double I_Value(double error) {
//    	return 0;
//    }
//    
//    private double D_Value(double error) {
//    	return 0;
//    }
//    
//    double speed = 0;
//    
//    public void setShooterPID(double targetRPM) {
//    	double currentRPM = getShooterRPM();
//    	double error = targetRPM - currentRPM;
//    	double change = P_Value(error) + I_Value(error) + D_Value(error);
//    	speed += change;
//    	shooterLeft.set(-speed);
//    	shooterRight.set(speed);
//    }
    
}

