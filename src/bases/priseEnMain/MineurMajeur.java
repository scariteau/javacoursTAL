package bases.priseEnMain;

import java.util.Scanner;

public class MineurMajeur {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez votre age ");
		int age = sc.nextInt();
		if (age < 18) {
			System.out.println("Votre �ge est " + age + " ans, vous �tes mineur");
		} else {
			System.out.println("Votre �ge est " + age + " ans, vous �tes majeur");
		}
		sc.close();
	}
}