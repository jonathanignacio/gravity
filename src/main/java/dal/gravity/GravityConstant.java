package dal.gravity;

public class GravityConstant implements GravityModel {

	private double GravitationalField;
	
	public GravityConstant(double g){
		GravitationalField = g;
	}
	
	public double getGravitationalField() {
		return GravitationalField;
	}

	public void setGravitationalField(double g) {
		GravitationalField = g;
	}

}
