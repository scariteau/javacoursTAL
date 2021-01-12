package bases.revisions;

public class Manager extends Employee {
	
	private double bonus;

	public Manager(String name, double salary) {
		super(name, salary);
		bonus=0;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	double calculerSalaireMensuel() {
		return (salary+bonus)*1.2;
	}

}
