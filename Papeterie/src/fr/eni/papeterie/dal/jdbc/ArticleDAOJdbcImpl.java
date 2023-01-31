/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;

/**
 * @author Eni Ecole
 * 
 */
public class ArticleDAOJdbcImpl {

	private static final String TYPE_STYLO = "STYLO";
	private static final String TYPE_RAMETTE = "RAMETTE";
	
	private static final String sqlSelectAll = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type from articles";
	
	private Connection connection;
	
	public ArticleDAOJdbcImpl() {
		
	}
	
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			String urldb = "jdbc:sqlserver://127.0.0.1;databasename=PAPETERIE_DB;trustServerCertificate=true";
			connection = DriverManager.getConnection(urldb, "sa", "Pa$$w0rd");
		}

		return connection;
	}
	
	public void closeConnection(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection=null;
		}
	}
	
	public Article selectById(Integer id) {
		return null;
	}
	
	public List<Article> selectAll() {
		return null;
	}
	
	public void update(Article article) {
		
	}
	
	public void insert(Article article) {
		
	}
	
	public void delete(Integer id) {
		
	}

}
