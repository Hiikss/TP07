package fr.eni.papeterie.bo;

public class Ramette extends Article {
	
	private int grammage;
	
	public Ramette(Integer idArticle, String marque, String ref, String designation, float pu, int qte, int grammage) {
		super(idArticle, marque, ref, designation, pu, qte);
		setGrammage(grammage);
	}

	public Ramette(String marque, String ref, String designation, float pu, int qte, int grammage) {
		this(null, marque, ref, designation, pu, qte, grammage);
	}
	
	@Override
	public String toString() {
		return super.toString() + " Ramette [grammage=" + grammage + "]";
	}

	public int getGrammage() {
		return grammage;
	}

	public void setGrammage(int grammage) {
		this.grammage = grammage;
	}

}
