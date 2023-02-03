package fr.eni.papeterie.ihm;

import java.util.List;


import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

public class ArticleController {
	
	private EcranArticle ecranArticle ;
	
	//Attributs
	private int indexCatalogue;
	
	private CatalogueManager mger;
	
	private List<Article> catalogue;
	
	private static ArticleController instance;
	
	//Constructeur
	private ArticleController(){
		try {
			mger = new CatalogueManager();
			
			//Initialisation du catalogue en mémoire
			catalogue = mger.getCatalogue();
			
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Méthodes
	public static synchronized ArticleController get(){
		if(instance == null){
			instance = new ArticleController();
		}
		return instance;
	}
	
	public void startApp(){
		ecranArticle = new EcranArticle();
		
		afficherPremierArticle();
		ecranArticle.setVisible(true);
	}
	
	public void afficherPremierArticle(){
		if(catalogue.size()>0){
			indexCatalogue = 0;
			ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
		}else{
			indexCatalogue = -1;
			ecranArticle.afficherNouveau();
		}

	}

	public void precedent(){
		if(indexCatalogue > 0){
			indexCatalogue--;
			ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
		}
		
	}

	public void suivant() {
		if(indexCatalogue < catalogue.size()-1){
			indexCatalogue++;
			ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
		}

	}

	public void nouveau() {
		indexCatalogue = catalogue.size();
		ecranArticle.afficherNouveau();
		
	}

	public void sauvegarder() {
		Article articleAffiche = ecranArticle.getArticle();
		
		try {
			if(articleAffiche.getIdArticle()==null){
				mger.addArticle(articleAffiche);
				System.out.println("article: " + articleAffiche);
				catalogue.add(articleAffiche);
				ecranArticle.afficherArticle(articleAffiche);
			}else{
				mger.updateArticle( articleAffiche);
				catalogue.set(indexCatalogue, articleAffiche);
			}
		} catch (BLLException e1) {
			ecranArticle.infoErreur("Erreur enregistrement.");
			e1.printStackTrace();
		}
		
	}

	public void supprimer() {
		
		try {
			mger.removeArticle(catalogue.get(indexCatalogue));
			catalogue.remove(indexCatalogue);
		} catch (BLLException e) {
			ecranArticle.infoErreur("Erreur suppression.");
			e.printStackTrace();
		}		

		if (indexCatalogue < catalogue.size() ) {
			// Afficher l'article suivant
			ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
		} else if (indexCatalogue > 0) {
			indexCatalogue--;
			ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
		} else {
			ecranArticle.afficherNouveau();
		}
		
	}


}
