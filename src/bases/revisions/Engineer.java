package bases.revisions;

public class Engineer extends Employee{
	
	private String skill;

	public Engineer(String name, double salary) {
		super(name, salary);
	}

	public Engineer(String name, double salary, String skill) {
		super(name, salary);
		this.skill=skill;
	}
	
	double calculerSalaireMensuel() {
		return salary*1.3;
	}
	
}
