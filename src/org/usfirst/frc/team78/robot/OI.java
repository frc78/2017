package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

public static Joystick driverStick;
	
	final static double STICK_DEADZONE = 0.05;
	
	public Button btn1;
	public Button btn2;
	public Button btn3;
	public Button btn4;
	public Button btn5;
	public Button btn6;
	public Button btn7;
	public Button btn8;
	public Button leftStickBtn11;
	public Button rightStickBtn12;
	
	public OI(){
		driverStick = new Joystick(0);
		
		}
	
	
	
	public double getDriverLeftStick(){
		double stick = driverStick.getY();
		if(Math.abs(stick) < STICK_DEADZONE){
			return 0;
		}else{
			return -stick;
		}
	}
	
	public double getDriverRightStick() {
		double stick = driverStick.getThrottle();
		if (Math.abs(stick) < STICK_DEADZONE){
			return 0;
		}else
			return -stick;
	}
}
