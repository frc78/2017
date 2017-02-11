package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class allows you to trigger commands from an analog input on
 * a joystick (such as the triggers - Axis 3). 
 * 
 * 
 * The following example code placed in OI class turns axis 3 into two buttons:
 * ----------------------------------------------------------------------------
 * //Create an AnalogButton for each trigger
 * int joystickChannel = 1;
 * public JoystickAnalogButton TriggerR = new JoystickAnalogButton(joystickChannel, 3, -0.5),
 *                             TriggerL = new JoystickAnalogButton(joystickChannel, 3, 0.5)
 * 
 * //Link the buttons to commands
 * TriggerR.whenPressed(new ExampleCommand1());
 * TriggerL.whenPressed(new ExampleCommand2())
 * 
 * Note that since both buttons are on the same Axis channel, they cannot be
 * pressed simultaneously. One trigger will negate the other and neither will
 * look pressed. So plan your controls accordingly.
 * 
 * @author James@team2168.org
 *
 */
public class JoystickAnalogButton extends Button {

  GenericHID m_joystick;
  int m_axisNumber;
  boolean m_povHat;
  private double THRESHOLD = 0.5;

  /**
   * Create a button for triggering commands off a joystick's analog axis
   * 
   * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
   * @param axisNumber The axis or POV hat number
   * @param povHat Enter true if this is a dpad or POV Hat. Enter false if it is an analog stick
   */
  public JoystickAnalogButton(GenericHID joystick, int axisNumber, boolean povHat) {
      m_joystick = joystick;
      m_povHat = povHat;
      m_axisNumber = axisNumber;
  }

  /**
   * Create a button for triggering commands off a joystick's analog axis
   * 
   * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
   * @param axisNumber The axis number
   * @param povHat Enter true if this is a dpad or POV Hat. Enter false if it is an analog stick
   * @param threshold The threshold to trigger above (positive) or below (negative)
   */
  public JoystickAnalogButton(GenericHID joystick, int axisNumber, double threshold, boolean povHat) {
  	m_joystick = joystick;
      m_axisNumber = axisNumber;
      m_povHat = povHat;
      THRESHOLD = threshold;
  }

  /**
   * Set the value above which triggers should occur (for positive thresholds)
   *  or below which triggers should occur (for negative thresholds)
   * The default threshold value is 0.5
   *  
   * @param threshold the threshold value (1 to -1)
   */
  public void setThreshold(double threshold){
  	THRESHOLD = threshold;
  }
 
  /**
   * Get the defined threshold value.
   * @return the threshold value
   */
  public double getThreshold(){
  	return THRESHOLD;
  }
  

  public boolean get() {
	  if(m_povHat == false){
	    if(THRESHOLD < 0){
	      return m_joystick.getRawAxis(m_axisNumber) < -THRESHOLD;    //Return true if axis value is less than negative threshold
	    } else {
	      return m_joystick.getRawAxis(m_axisNumber) > THRESHOLD;    //Return true if axis value is greater than positive threshold
	    }
	  }else{
	    if(THRESHOLD == m_joystick.getPOV(m_axisNumber)){
		      return true;    //Return true if POV value is equal to threshold
		    } else {
		      return false;    //Return true if POV value is not equal to threshold
		    }
		  }
  }

}