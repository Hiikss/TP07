package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {
	
	private ArticleDAO daoArticle;

	public CatalogueManager() throws BLLException {
		daoArticle = DAOFactory.getArticleDAO();
	}
	
	public List<Article> getCatalogue(){
		List<Article> articles = null;
		try {
			articles = daoArticle.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}
	
	public void addArticle(Article a) throws BLLException {
		if(a.getIdArticle()!=null)
			throw new BLLException("Article déjà existant.");
		try {
			validerArticle(a);
			daoArticle.insert(a);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateArticle(Article a) throws BLLException {
		try {
			validerArticle(a);
			daoArticle.update(a);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeArticle(Article a) throws BLLException {
		try {
			daoArticle.delete(a);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void validerArticle(Article a) throws BLLException {
			boolean valide = true;
			StringBuffer sb = new StringBuffer();
			
			if(a==null){
				throw new BLLException("Article null");
			}
			//Les attributs des articles sont obligatoires
			if(a.getReference()==null || a.getReference().trim().length()==0){
				sb.append("La reference article est obligatoire.\n");
				valide = false;
			}
			if(a.getMarque()==null || a.getMarque().trim().length()==0){
				sb.append("La marque  est obligatoire.\n");
				valide = false;
			}
			if(a.getDesignation()==null || a.getDesignation().trim().length()==0){
				sb.append("La designation  est obligatoire.\n");
				valide = false;
			}
			if(a instanceof Ramette && ((Ramette)a).getGrammage()<=0){
				sb.append("Le grammage doit avoir une valeur positive.\n");
				valide = false;
			}
			if(a instanceof Stylo ){
				Stylo s = (Stylo) a;
				if(s.getCouleur()==null || s.getCouleur().trim().length()==0){
					sb.append("La couleur pour un stylo  est obligatoire.\n");
					valide = false;
				}
			}
			
			if(!valide){
				throw new BLLException(sb.toString());
			}
	}
}
