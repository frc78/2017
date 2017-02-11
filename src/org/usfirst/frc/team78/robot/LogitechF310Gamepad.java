package org.usfirst.frc.team78.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team78.robot.JoystickAnalogButton;;

public class LogitechF310Gamepad {

	private Joystick joystick;

	public JoystickButton leftTrigger; 
	public JoystickButton rightTrigger;
	public JoystickButton greenButton;
	public JoystickButton aButton;
	public JoystickButton blueButton; 
	public JoystickButton xButton;
	public JoystickButton redButton; 
	public JoystickButton bButton;
	public JoystickButton yellowButton; 
	public JoystickButton yButton;
	public JoystickButton backButton; 
	public JoystickButton startButton; 
	public JoystickButton leftBumper;
	public JoystickButton rightBumper; 
	public JoystickButton leftStickClick; 
	public JoystickButton rightStickClick; 
	public JoystickAnalogButton dPadDown;
	public JoystickAnalogButton dPadRight;
	public JoystickAnalogButton dPadUp;
	public JoystickAnalogButton dPadLeft;
	
	public LogitechF310Gamepad(int portNumber){
		this.joystick = new Joystick(portNumber);
		

		leftTrigger = new JoystickButton(joystick, 2); 
		rightTrigger = new JoystickButton(joystick, 3);
		greenButton = new JoystickButton(joystick, 1);
		aButton = new JoystickButton(joystick, 1);
		blueButton = new JoystickButton(joystick, 3); 
		xButton = new JoystickButton(joystick, 3);
		redButton = new JoystickButton(joystick, 2); 
		bButton = new JoystickButton(joystick, 2);
		yellowButton = new JoystickButton(joystick, 4); 
		yButton = new JoystickButton(joystick, 4);
		backButton = new JoystickButton(joystick, 7); 
		startButton = new JoystickButton(joystick, 8); 
		leftBumper = new JoystickButton(joystick, 5);
		rightBumper = new JoystickButton(joystick, 6); 
		leftStickClick = new JoystickButton(joystick, 9); 
		rightStickClick = new JoystickButton(joystick, 10); 


		dPadDown = new JoystickAnalogButton(joystick, 0, 0, true);
		dPadRight = new JoystickAnalogButton(joystick, 0, 90, true);
		dPadUp = new JoystickAnalogButton(joystick, 0, 180, true);
		dPadLeft = new JoystickAnalogButton(joystick, 0, 270, true);

	}
	

//The following methods return the values of each
//joystick button.
	
	public double getLeftStickX() {
        return this.joystick.getRawAxis(0);
    }
    
    public double getLeftStickY() {
        return -this.joystick.getRawAxis(1);
    }
    
    public double getLeftTrigger() {
    	return this.joystick.getRawAxis(2);
    }
    
    public double getRightTrigger(){
    	return this.joystick.getRawAxis(3);
    }
    
    public double getRightStickX() {
        return this.joystick.getRawAxis(4);
    }
    
    public double getRightStickY() {
        return -this.joystick.getRawAxis(5);
    }
    
    public boolean getButton(int btnNum) {
        return this.joystick.getRawButton(btnNum);
    }
    
    public boolean getGreenButton() {
        return this.joystick.getRawButton(1);
    }
    
    public boolean getAButton() {
        return this.joystick.getRawButton(1);
    }
    
    public boolean getBlueButton() {
        return this.joystick.getRawButton(3);
    }
    
    public boolean getXButton() {
        return this.joystick.getRawButton(3);
    }
    
    public boolean getRedButton() {
        return this.joystick.getRawButton(2);
    }
    
    public boolean getBButton() {
        return this.joystick.getRawButton(2);
    }
    
    public boolean getYellowButton() {
        return this.joystick.getRawButton(4);
    }
    
    public boolean getYButton() {
        return this.joystick.getRawButton(4);
    }
    
    public boolean getBackButton() {
        return this.joystick.getRawButton(7);
    }
    
    public boolean getStartButton() {
        return this.joystick.getRawButton(8);
    }
    
    public boolean getLeftBumper() {
        return this.joystick.getRawButton(5);
    }
    
    public boolean getRightBumper() {
        return this.joystick.getRawButton(6);
    }
    
    public boolean getLeftStickClick() {
        return this.joystick.getRawButton(9);
    }
    
    public boolean getRightStickClick() {
        return this.joystick.getRawButton(10);
    }
    
    public int getDPadVal(){
    	return this.joystick.getPOV(0);
    }
    
    public boolean getDPadDown(){
    	return (this.joystick.getPOV(0) == 180);
    }
    
    public boolean getDPadRight(){
    	return (this.joystick.getPOV(0) == 90);
    }
    
    public boolean getDPadUp(){
    	return (this.joystick.getPOV(0) == 0);
    }
    
    public boolean getDPadLeft(){
    	return (this.joystick.getPOV(0) == 270);
    }
	
}

