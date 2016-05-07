package main;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.stabla.ABSTStablo;

public class BSTStablo extends ABSTStablo {

	@Override
	public void ispisiRastuce(CvorStabla k) throws LabisException {
		if (k == null) {
			return;
		}
		ispisiRastuce(k.levo);
		System.out.println(k.podatak + " ");
		ispisiRastuce(k.desno);
	}
}
