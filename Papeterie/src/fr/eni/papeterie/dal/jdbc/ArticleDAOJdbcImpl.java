/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
	
	//private static final String sqlSelectAll = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type from articles";
	private static final String sqlInsert = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,type,grammage,couleur) values(?,?,?,?,?,?,?,?)";
	
	private Connection connection;
	
	public ArticleDAOJdbcImpl() {
		
	}
	
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			String url = "jdbc:sqlserver://localhost:1433;databasename=PAPETERIE_DB;trustServerCertificate=true";
			connection = DriverManager.getConnection(url, "sa", "Pa$$w0rd");
		}
		return connection;
	}
	
	public void closeConnection(){
		if(connection != null){
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
	
	public void insert(Article article) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, article.getReference());
			rqt.setString(2, article.getMarque());
			rqt.setString(3, article.getDesignation());
			rqt.setFloat(4, article.getPrixUnitaire());
			rqt.setInt(5, article.getQteStock());
			if (article instanceof Ramette) {
				Ramette r = (Ramette) article;
				rqt.setString(6, TYPE_RAMETTE);
				rqt.setInt(7, r.getGrammage());
				rqt.setNull(8, Types.VARCHAR);
			}
			if (article instanceof Stylo) {
				Stylo s = (Stylo) article;
				rqt.setString(6, TYPE_STYLO);
				rqt.setNull(7, Types.INTEGER);
				rqt.setString(8, s.getCouleur());
			}

			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					article.setIdArticle(rs.getInt(1));
				}

			}

		} catch (SQLException e) {
			throw new DALException("Insert article failed - " + article, e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
				
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();

		}
	}
	
	public void delete(Integer id) {
		
	}

}
