package org.usfirst.frc.team78.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	
	//Motors and Solenoids
		//Left Drive 
		public static final int STARBOARD_FRONT = 3;  
		public static final int STARBOARD_REAR = 1;
		public static final int STARBOARD_TOP = 2;
		//Right Drive
		public static final int PORT_FRONT = 4;
		public static final int PORT_REAR = 6;
		public static final int PORT_TOP = 5;
		
		//Shooter
		public static final int SHOOTER_PORT = 10; 
		public static final int SHOOTER_STARBOARD = 14;
		public static final int SHOOTER_FEED = 9;
		public static final int LIVE_FLOOR = 15;
		
		//Intake
		public static final int INTAKE_MOTOR = 7;
		
		//Gears
		public static final int GEAR_INTAKE_MOTOR = 8; 
		public static final int GEAR_SOLENOID1 = 0;
		public static final int GEAR_SOLENOID2 = 1;
		
		//CLIMBER
		public static final int CLIMBER_STARBOARD = 13;
		public static final int CLIMBER_PORT = 11;
		
	//Sensors
		
		//These aren't needed because the encoders are plugged into the talons
		
		//Shooter
//		public static final int SHOOTER_ENCA = 0;
//		public static final int SHOOTER_ENCB = 1;
		 
		//Chassis
//		public static final int LEFT_DRIVE_ENCA = 2;	 
//		public static final int LEFT_DRIVE_ENCB = 3;	 
//		public static final int RIGHT_DRIVE_ENCA = 4;
//		public static final int RIGHT_DRIVE_ENCB = 5;
		
		//Intake
//		public static final int INTAKE_ENCA = 6;
//		public static final int INTAKE_ENCB = 7;
		
		//Gear
		public static final int ULTRA = 0;
}
