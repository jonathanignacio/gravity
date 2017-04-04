package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

    public static final double EGRAVITY = 9.80665; //gravitational constant on Earth
    public static final double JGRAVITY = 25.0; //gravitational constant on Earth
    public static void main (String [] args) {
    GravityModel g = new GravityConstant(EGRAVITY);
	NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);

	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta, g);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, g);
	RegularPendulum rpCoarse = 
	    new RegularPendulum (sLen, pMass, theta0, .1, g);

	// print out difference in displacement in 1 second intervals
	// for 20 seconds
	int iterations = (int) (1/delta);
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	for (int second = 1; second <= 20; second++) {
		if(second == 10){
			System.out.println("Whoa, did things get heavier?");
			sp.setGravitationalField(JGRAVITY);
			rp.setGravitationalField(JGRAVITY);
			rpCoarse.setGravitationalField(JGRAVITY);
		}
	    for (int i = 0; i < iterations; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
    }
}

