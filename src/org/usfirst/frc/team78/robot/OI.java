package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.JoystickAnalogButton;
import org.usfirst.frc.team78.robot.commands.climb;
import org.usfirst.frc.team78.robot.commands.gearIntake;
import org.usfirst.frc.team78.robot.commands.gearOuttake;
import org.usfirst.frc.team78.robot.commands.gearPixy;
import org.usfirst.frc.team78.robot.commands.gearSwitch;
import org.usfirst.frc.team78.robot.commands.gearUp;
import org.usfirst.frc.team78.robot.commands.intake;
import org.usfirst.frc.team78.robot.commands.liveFloorStop;
import org.usfirst.frc.team78.robot.commands.shooterPosTest;
import org.usfirst.frc.team78.robot.commands.stopClimber;
import org.usfirst.frc.team78.robot.commands.turn;
import org.usfirst.frc.team78.robot.commands.gearDown;
import org.usfirst.frc.team78.robot.commands.runLiveFloor;
import org.usfirst.frc.team78.robot.commands.runShooterFeed;
import org.usfirst.frc.team78.robot.commands.setShooterSpeed;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick driverStick;
	public static Joystick manipulatorStick;
	
	final static double STICK_DEADZONE = 0.05;
	
	public Button driverX;
	public Button driverA;
	public Button driverB;
	public Button driverY;
	public Button driverLB;
	public Button driverRB;
	public Button driverLT;
	public Button driverRT;
	public Button driverBack;
	public Button driverStart;
	public Button leftStickBtn11;
	public Button rightStickBtn12;
	
	public Button manipulatorA;
	public Button manipulatorB;
	public Button manipulatorX;
	public Button manipulatorY;
	public Button manipulatorLB;
	public Button manipulatorRB;
	public Button manipulatorBack;
	public Button manipulatorStart;
	public JoystickAnalogButton manipulatorLT;
	public JoystickAnalogButton manipulatorRT;
	public JoystickAnalogButton manipulatorLeftStickUp;
	public JoystickAnalogButton manipulatorLeftStickDown;
	public JoystickAnalogButton manipulatorRightStickUp;
	public JoystickAnalogButton manipulatorRightStickDown;
	
	public boolean gearIntakeBool = true;
	 
	public OI(){
		driverStick = new Joystick(0);
		manipulatorStick = new Joystick(1);
		
		driverX = new JoystickButton(driverStick, 1);
		driverA = new JoystickButton(driverStick, 2);
		driverB = new JoystickButton(driverStick, 3);
		driverY = new JoystickButton(driverStick, 4);
		driverLB = new JoystickButton(driverStick, 5);
		driverRB = new JoystickButton(driverStick, 6);
		driverLT = new JoystickButton(driverStick, 7);
		driverRT = new JoystickButton(driverStick, 8);
		driverBack = new JoystickButton(driverStick, 9);
		driverStart = new JoystickButton(driverStick, 10);
		
		manipulatorA = new JoystickButton(manipulatorStick, 1);
		manipulatorB = new JoystickButton(manipulatorStick, 2);
		manipulatorX = new JoystickButton(manipulatorStick, 3);
		manipulatorY = new JoystickButton(manipulatorStick, 4);
		manipulatorLB = new JoystickButton(manipulatorStick, 5);
		manipulatorRB = new JoystickButton(manipulatorStick, 6);
		manipulatorBack = new JoystickButton(manipulatorStick, 7);
		manipulatorStart = new JoystickButton(manipulatorStick, 8);
		manipulatorLT = new JoystickAnalogButton(manipulatorStick, 2, STICK_DEADZONE);
		manipulatorLT = new JoystickAnalogButton(manipulatorStick, 3, STICK_DEADZONE);
		manipulatorLeftStickUp = new JoystickAnalogButton(manipulatorStick, 1, -3 * STICK_DEADZONE);
		manipulatorLeftStickDown = new JoystickAnalogButton(manipulatorStick, 1, STICK_DEADZONE);
		manipulatorRightStickUp = new JoystickAnalogButton(manipulatorStick, 5, -STICK_DEADZONE);
		manipulatorRightStickDown = new JoystickAnalogButton(manipulatorStick, 5, STICK_DEADZONE);
		
		//VISION BUTTONS

		
		//DRIVING BUTTONS 

		 		  
		//GEAR BUTTONS
		//temporarily commented out for shooter testing 20170219 JRC
		manipulatorLB.whileHeld(new gearOuttake("out", 0.65));
		manipulatorLB.whenReleased(new gearOuttake("out", 0));
		manipulatorRB.whenPressed(new gearIntake("in", 0.78));
		manipulatorLT.whileHeld(new gearOuttake("in", 0.78));
		manipulatorLT.whenReleased(new gearOuttake("in", 0));
		
		manipulatorA.whileHeld(new gearDown());
		manipulatorA.whenReleased(new gearUp());
		
//		manipulatorB.whileHeld(new gearDown());
//		manipulatorB.whenReleased(new gearUp());
		manipulatorB.whenPressed(new gearSwitch());
		 
		//CLIBMER BUTTONS		
		driverRT.whileHeld(new climb(1)); 
		driverRT.whenReleased(new stopClimber());
		driverLT.whileHeld(new climb(0.4));
		driverLT.whenReleased(new stopClimber());  
		
		
		
		//INTAKE BUTTONS
		manipulatorLeftStickDown.whileHeld(new intake("in", 0.6));
		manipulatorLeftStickUp.whileHeld(new intake("out", 0.6));
//		manipulatorX.whileHeld(new intake("in", 0.6));
//		manipulatorA.whileHeld(new intake ("out", 0.6));
		 
		//SHOOTER BUTTONS
//		manipulatorY.whileHeld(new setShooterSpeed(1));
//		manipulatorY.whenReleased(new setShooterSpeed(0));
//		manipulatorBack.whileHeld(new runShooterFeed(1));
//		manipulatorX.whileHeld(new runLiveFloor(0.6));
//		manipulatorX.whenReleased(new liveFloorStop());
		
	}
	
	//STICKS AND TRIGGERS SETUP	
	public double getManipulatorLeftStick() {
		double stick = manipulatorStick.getY();
		if(Math.abs(stick) < STICK_DEADZONE) return 0;
		else return -stick;
	}
	
	public double getManipulatorRightStick() {
		double stick = manipulatorStick.getRawAxis(5);
		if(Math.abs(stick) < STICK_DEADZONE) return 0;
		else return -stick;
	}
	
	public boolean manipulatorLT() {
		double trigger = manipulatorStick.getRawAxis(2);
		if(Math.abs(trigger) < STICK_DEADZONE) return false;
		else return true;
	}
	
	public boolean manipulatorRT() {
		double trigger = manipulatorStick.getRawAxis(3);
		if(Math.abs(trigger) < STICK_DEADZONE) return false;
		else return true;
	}
	
	public double getDriverLeftStick(){
		double stick = driverStick.getY();
		if(Math.abs(stick) < STICK_DEADZONE) return 0;
		else return -stick;
	}
	
	public double getDriverRightStick() {
		double stick = driverStick.getThrottle();
		if (Math.abs(stick) < STICK_DEADZONE) return 0;
		else return -stick;
	}
}





