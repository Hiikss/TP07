package fr.eni.papeterie.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl;

public abstract class DAOFactory {
	
	private static ArticleDAO articleDAO;
	
	public static ArticleDAO getArticleDAO()  {
		if(articleDAO == null)
			articleDAO = new ArticleDAOJdbcImpl();
		return articleDAO; 
	}

}
