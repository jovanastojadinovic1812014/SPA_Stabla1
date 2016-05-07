package main;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.stabla.ABinarnoStablo;

/**
 * 
 * @author Jovana Stojadinovic
 *
 */

public class BinarnoStablo extends ABinarnoStablo {
	@Override
	public int vratiBrojCvorovaVecihOdSvojihSledbenika(CvorStabla k) throws LabisException {
		if (k == null) {
			return 0; // ne brojimo, pok na null
		}

		if (k.podatak > maxVrednost(k.levo) && k.podatak > maxVrednost(k.desno)) {
			return 1 + vratiBrojCvorovaVecihOdSvojihSledbenika(k.levo)
					+ vratiBrojCvorovaVecihOdSvojihSledbenika(k.desno);
		}

		return vratiBrojCvorovaVecihOdSvojihSledbenika(k.levo) + vratiBrojCvorovaVecihOdSvojihSledbenika(k.desno);
	}

	private int maxVrednost(CvorStabla k) {
		if (k == null) {
			return Integer.MIN_VALUE;
		}
		return Math.max(k.podatak, Math.max(maxVrednost(k.levo), maxVrednost(k.desno)));
	}

	private int minVrednost(CvorStabla k) {
		if (k == null) {
			return Integer.MAX_VALUE;
		}
		return Math.min(k.podatak, Math.min(minVrednost(k.levo), minVrednost(k.desno)));
	}
	private int visina(CvorStabla k){
		
		if(k==null){
			return 0; // necemo da brojimo nema visinu
		}
		
		return 1+ Math.max(visina(k.levo), visina(k.desno));
	}
	
	@Override
	public boolean daLiJeAVL(CvorStabla k) throws LabisException {
		boolean levaStrana;
		boolean desnaStrana;
		if(k==null){
			return true; //ako provucemo jedno false, provuci ce se do kraja
						// nikad nece biti true
			
		}
		
		boolean razlikaVisina = Math.abs(visina(k.levo)-visina(k.desno))<=1;
		
		if(k.levo!=null && k.podatak > maxVrednost(k.levo) && razlikaVisina){
			levaStrana = daLiJeAVL(k.levo);
		}else{
			if(k.levo==null){
				levaStrana = true; // provera da li je doslo do kraja- listovi,
									//pa uslov nije zadovoljen
			}else{
				return false; // nema potrebe da se desna proverava 
								//ako smo naleteli na uslov da nije u levom
			}
		}
		
		if(k.desno!=null && k.podatak<minVrednost(k.desno)&& razlikaVisina){
			desnaStrana = daLiJeAVL(k.desno);
		}else{
			if(k.desno == null){
				desnaStrana=true;
			}else{
				return false;
			}
		}
		return levaStrana && desnaStrana;
	}
}
