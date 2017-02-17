package org.usfirst.frc.team78.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//I2C DEVICES
			public static I2C shooterPixy = new I2C(Port.kOnboard, 0x54);
			public static I2C gearPixy = new I2C(Port.kMXP, 0x55);
			public static char shooterXPos;
			public static char shooterYPos;
			public static char gearXPos;
			public static char gearYPos;
			
	//Variables
			public boolean timerStart = false;
			public boolean atTarget = false;	
			
	//TIMER
			public Timer timer = new Timer();		
			
			
	    public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	        //setDefaultCommand(new MySpecialCommand());
	    	//setDefaultCommand();
	    }
	    
	    public boolean isAtVisionTarget(double target, double xPos){
	    	atTarget = false;
	    	
	    	double error = target - xPos;

	    	if ((error < 10) && (error > -10)){
	    		if(timerStart == false){
	   				timerStart = true;
	   				timer.start();
	   			}
	    		
	   		}
	   	
	   		else{
	   		
	   			if(timerStart == true){
	    			timer.stop();
	    			timer.reset();
	    			timerStart = false;
	   			}
	   		}
	    	
	   		if(timer.get() >0.25){
	   			atTarget = true;
	    	}
	    	
	    	return atTarget;
	    	
	    }
	    
	    
	   
	    public static void shooterPixy(){
	    	byte[] pixyValues = new byte[64];
	    	pixyValues[0] = (byte) 0b01010101;
	    	pixyValues[1] = (byte) 0b10101010;

	    	shooterPixy.readOnly(pixyValues, 64);
	    	if (pixyValues != null) {
	    		int i = 0;
	    		while (!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50) {
	    			i++;
	    		}
	    		i++;
	    		if (i > 50)
	    			i = 49;
	    		while (!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50) {
	    			i++;
	    		}
	    		shooterXPos = (char) (((pixyValues[i + 7] & 0xff) << 8) | (pixyValues[i + 6] & 0xff));
	    		shooterYPos = (char) ((pixyValues[i + 9] & 0xff << 8) | pixyValues[i + 8] & 0xff);
//	    		char width = (char) ((pixyValues[i + 11] & 0xff << 8) | pixyValues[i + 10] & 0xff);
//	    		char height = (char) ((pixyValues[i + 13] & 0xff << 8) | pixyValues[i + 12] & 0xff);
	    		
	    		SmartDashboard.putNumber("xPosition", shooterXPos);
	    		SmartDashboard.putNumber("yPosition", shooterYPos);
//	    		SmartDashboard.putNumber("width", width);
//				SmartDashboard.putNumber("height", height);
//				SmartDashboard.putNumber("Raw 5", pixyValues[5]);
			}
		
	    }
	    
	    public static void gearPixy(){
	    	byte[] pixyValues = new byte[64];
	    	pixyValues[0] = (byte) 0b01010101;
	    	pixyValues[1] = (byte) 0b10101010;

	    	gearPixy.readOnly(pixyValues, 64);
	    	if (pixyValues != null) {
	    		int i = 0;
	    		while (!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50) {
	    			i++;
	    		}
	    		i++;
	    		if (i > 50)
	    			i = 49;
	    		while (!(pixyValues[i] == 85 && pixyValues[i + 1] == -86) && i < 50) {
	    			i++;
	    		}
	    		gearXPos = (char) (((pixyValues[i + 7] & 0xff) << 8) | (pixyValues[i + 6] & 0xff));
	    		gearYPos = (char) ((pixyValues[i + 9] & 0xff << 8) | pixyValues[i + 8] & 0xff);
//	    		char width = (char) ((pixyValues[i + 11] & 0xff << 8) | pixyValues[i + 10] & 0xff);
//	    		char height = (char) ((pixyValues[i + 13] & 0xff << 8) | pixyValues[i + 12] & 0xff);
	    		
	    		SmartDashboard.putNumber("xPosition", gearXPos);
	    		SmartDashboard.putNumber("yPosition", gearYPos);
//	    		SmartDashboard.putNumber("width", width);
//				SmartDashboard.putNumber("height", height);
//				SmartDashboard.putNumber("Raw 5", pixyValues[5]);
			}
		
	    }
	
}

