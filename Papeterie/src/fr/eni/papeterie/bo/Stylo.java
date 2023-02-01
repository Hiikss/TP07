package fr.eni.papeterie.bo;

public class Stylo extends Article {

	private String couleur;
	
	public Stylo(Integer idArticle, String marque, String ref, String designation, float pu, int qte, String couleur) {
		super(idArticle, marque, ref, designation, pu, qte);
		setCouleur(couleur);
	}
	
	public Stylo(String marque, String ref, String designation, float pu, int qte, String couleur) {
		this(null, marque, ref, designation, pu, qte, couleur);
	}
	
	@Override
	public String toString() {
		return super.toString() + " Stylo [Couleur=" + couleur + "]";
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

}
