package bases.revisions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculerSalaireMensuel() {
		Employee ingenieur = new Engineer("sandyIngenieur", 1200);
		Manager manager = new Manager("sandyManager", 1200);
		manager.setBonus(2000);

		assertEquals(18720.0, ingenieur.calculerRevenuAnnuel());
		assertEquals(46080.0, manager.calculerRevenuAnnuel());
		System.out.println("Salaire ingenieur == " + ingenieur.calculerRevenuAnnuel());
		System.out.println("Salaire manager == " +  manager.calculerRevenuAnnuel());

	}

}
