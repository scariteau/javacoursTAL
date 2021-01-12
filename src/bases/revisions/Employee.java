package bases.revisions;

public class Employee {
	String name;
	double salary;
	double salaireMensuel=0;
	
	public Employee(String name,double salary) {
		this.name=name.toLowerCase();
		this.salary= salary;
	}
	
	double calculerRevenuAnnuel(){
		return 12 * calculerSalaireMensuel();
	}
	
	double calculerSalaireMensuel() {
		return salary;
	}

}
