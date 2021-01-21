package bases.priseEnMain;

public class SommeDes100PremiersEntiers {
	public static void main(String[] args) {
		int j = 0;
		for (int i = 1; i <= 100; i++) {
			j = j + i;
		}
		System.out.println("La somme des 100 premiers entiers est  : " + j);
	}
}
