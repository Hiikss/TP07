package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private float montant;
	private List<Ligne> lignesPanier;
	
	public Panier() {
		lignesPanier = new ArrayList<Ligne>();
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
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
		StringBuffer string = new StringBuffer();
		for(Ligne ligne : lignesPanier) {
			string.append("ligne " + lignesPanier.indexOf(ligne) + " :\t");
			string.append(ligne.toString());
			string.append("\n");
		}
		string.append("\nValeur du panier : " + getMontant());
		string.append("\n\n");
		return string.toString();
	}


}
