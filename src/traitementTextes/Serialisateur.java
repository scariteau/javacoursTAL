package traitementTextes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialisateur {

	public static void serialiseObject(String fichierPourStockerObjet, Object objetAserialiser) {
		ObjectOutputStream oos = null;

		try {
			final FileOutputStream fichier = new FileOutputStream(fichierPourStockerObjet);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(objetAserialiser);
			oos.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static Object deSerialiseObject(String fichierStockantObjet) {
		ObjectInputStream iis = null;
		Object objetAserialiser = null;
		try {
			iis = new ObjectInputStream(new FileInputStream(fichierStockantObjet));
			objetAserialiser = iis.readObject();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (iis != null) {
					iis.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return objetAserialiser;
	}

}
