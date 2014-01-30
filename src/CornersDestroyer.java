package sample;
import robocode.*;


/*******************************************************************
 * Authors: Conner Fallone & Corey Alberda
 * Class: CIS-365
 * Winter 2014
 * CornersDestroyer. This robot destroys the sample robot corners.
 *******************************************************************/
public class CornersDestroyer extends AdvancedRobot
{
	/**
	 * run: NewAdvancedRobot's default behavior
	 */
	
	double bearing;
	double distance;
	double enemyHeading;
	
	boolean inCorner = false;
	boolean justSpotted = true;
	
	public void run() {

		while(true) {
			// Opponent is not in a corner. Just keep scanning enemy
			if (!inCorner){
				turnGunRight(360);
			}
			// Opponent is in a corner, strafe back and fourth and fire
			else{
				// Special case. We want to turn our gun ONCE to aim at him in the corner.
				if (justSpotted){
					turnGunLeft(90);
					justSpotted = false;
				}
					
				fire(5);
				ahead(200);
				back(200);
			}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		
		// Get enemies bearing.
		bearing = e.getBearing();
		
		// Square off against enemy : Turn 90 degrees from him so we will be able to strafe
		setTurnRight(bearing + 90);
		execute();
		
		System.out.println("Bearing: " + bearing);
		System.out.println(inCorner);
		
		// Enemy is in corner. We have squared off with him and he is pretty much 90 degrees from us.
		if (((bearing <= -89.9 && bearing >= -90.1) || (bearing <= 90.1 && bearing >= 89.9)) && inCorner == false){
		//if ((bearing == -90 || bearing == 90) && inCorner == false){
			inCorner = true;
		// Enemy isn't in corner yet. Keep waiting
		}else{
			// Do Nothing
		}
	}
}