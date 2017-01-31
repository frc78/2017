package org.usfirst.frc.team78.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	
	//Motors
		//Left Drive
		public static final int LEFT_FRONT = 1;  
		public static final int LEFT_REAR = 2;
		public static final int LEFT_TOP = 3;
		//Right Drive
		public static final int RIGHT_FRONT = 4;
		public static final int RIGHT_REAR = 5;
		public static final int RIGHT_TOP = 6;
		
		//shooter
		public static final int SHOOTERLEFT = 7;
		public static final int SHOOTERRIGHT = 8;
		
	//Sensors
		public static final int SHOOTERENCA = 0;
		public static final int SHOOTERENCB = 1;
		
}
