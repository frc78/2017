package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.climb;
import org.usfirst.frc.team78.robot.commands.driveStraight;
import org.usfirst.frc.team78.robot.commands.gearIntake;
import org.usfirst.frc.team78.robot.commands.gearPixy;
import org.usfirst.frc.team78.robot.commands.intake;
import org.usfirst.frc.team78.robot.commands.shooterPixy;
import org.usfirst.frc.team78.robot.commands.shooterPosTest;
import org.usfirst.frc.team78.robot.commands.slowDriveWithJoysticks;
import org.usfirst.frc.team78.robot.commands.switchFronts;
import org.usfirst.frc.team78.robot.commands.turboDriveWithJoysticks;
import org.usfirst.frc.team78.robot.commands.turn;
import org.usfirst.frc.team78.robot.commands.gearUpDown;

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
		
		//VISION BUTTONS
//		driverY.whileHeld(new shooterPixy());
//		driverA.whileHeld(new gearPixy());
		
		//DRIVING BUTTONS
		driverLB.whileHeld(new slowDriveWithJoysticks());
		driverLT.whileHeld(new turboDriveWithJoysticks());
		driverStart.whenPressed(new switchFronts());
		
		//GEAR INTAKE BUTTONS
		manipulatorLB.whileHeld(new gearIntake("out", 0.65));
		if(manipulatorLT()) new gearIntake("in", 0.78);
		//GEAR ARM Buttons
		manipulatorB.whileHeld(new gearUpDown());
		
		
		//CLIBMER BUTTONS
		//manipulatorA.whileHeld(new climb(0.15));
		
		double climberSpeed = 0.15;
		if(manipulatorA.get()){
			if(manipulatorX.get()){
				//climberSpeed += 0.3;
				new climb(climberSpeed);
			}
			new climb(climberSpeed);
		}
		
		//FUL BUTNZ
		if(manipulatorRT()) {
			new shooterPosTest();
		}
		
		if(getManipulatorLeftStick() > 0) new intake("in", 0.78);
		else if(getManipulatorLeftStick() < 0) new intake("out", 0.78);
		
		
		driverLB.whileHeld(new intake("in", 0.65));
		
		if(!driverLB.get()){
			driverRB.whileHeld(new intake("out", 0.65));
		}
		
		driverX.whileHeld(new driveStraight(-9));
		
		driverA = new JoystickButton(driverStick, 2);
		driverA.whenPressed(new turn(90));
	}
	
	//sticks and TRIGGERs
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
		if(trigger < STICK_DEADZONE) return false;
		else return true;
	}
	
	public boolean manipulatorRT() {
		double trigger = manipulatorStick.getRawAxis(3);
		if(trigger < STICK_DEADZONE) return false;
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

/* CAPTAIN'S LOG 20:34:47 FEB 16 2017 B-80
 * As build season draws to a close, we work desperately to finalize designs for our shooter, tank, and gear manipulator.
 * Mechanical is growing restless and frustrated with our slow progress, stressed by the looming deadline that is bag day.
 * Food grows scarce.  The build season has been long and hard, and as it draws to a close the outcome is unclear.  Will
 * we survive?  Week 0 is coming quickly.  We must be prepared or else all hope is lost.  Kamen help us...
 */




