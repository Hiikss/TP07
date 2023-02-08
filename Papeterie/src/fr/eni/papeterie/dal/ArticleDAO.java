/**
 * 
 */
package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

/**
 * @author Eni Ecole
 *
 */
public interface ArticleDAO extends DAO<Article>{
	
	List<Article> selectByMarque(String marque) throws DALException;
	
	List<Article> selectByMotCle(String motCle) throws DALException;

}
