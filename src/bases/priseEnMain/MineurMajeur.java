package bases.priseEnMain;

import java.util.Scanner;

public class MineurMajeur {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez votre age ");
		int age = sc.nextInt();
		if (age < 18) {
			System.out.println("Votre âge est " + age + " ans, vous êtes mineur");
		} else {
			System.out.println("Votre âge est " + age + " ans, vous êtes majeur");
		}
		sc.close();
	}
}