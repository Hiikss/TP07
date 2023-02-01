package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private List<Ligne> lignesPanier;
	
	public Panier() {
		lignesPanier = new ArrayList<Ligne>();
	}

	public float getMontant() {
		float montant = 0f;
		for(Ligne ligne : lignesPanier) {
			montant += ligne.getPrix();
		}
		return montant;
	}
	
	public Ligne getLigne(int index) {
		return lignesPanier.get(index);
	}
	
	public void addLigne(Article article, int qte) {
		lignesPanier.add(new Ligne(article, qte));
	}
	
	public void updateLigne(int index, int newQte) {
		this.getLigne(index).setQte(newQte);
	}
	
	public void removeLigne(int index) {
		lignesPanier.remove(index);
	}
	
	public List<Ligne> getLignePanier() {
		return lignesPanier;
	}
	
	@Override
	public String toString() {
		String string = "Panier : \n\n";
		for(Ligne ligne : lignesPanier) {
			string += "ligne " + lignesPanier.indexOf(ligne) + " :\t" + ligne.toString() + "\n";
		}
		string += "\nValeur du panier : " + getMontant() + "\n\n";
		return string;
	}


}
