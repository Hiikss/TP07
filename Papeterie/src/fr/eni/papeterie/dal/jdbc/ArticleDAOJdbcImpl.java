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
	
	private static final String sqlSelectAll = "select * from articles";
	private static final String sqlUpdate = "update articles set reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=? where idArticle=?";
	private static final String sqlInsert = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,type,grammage,couleur) values(?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from articles where reference=?";
	
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
		List<Article> articles = new ArrayList<Article>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelectAll);
			Article article = null;
			while (rs.next()) {
				if (TYPE_STYLO.equals(rs.getString("type"))) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(), rs.getString("designation"),
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())) {
					article = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference").trim(), rs.getString("designation"), 
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getInt("grammage"));
				}
				articles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			closeConnection();
		}
		return articles;
	}
	
	public void update(Article article) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(sqlUpdate);
			
			stmt.setString(1, article.getReference());
			stmt.setString(2, article.getMarque());
			stmt.setString(3, article.getDesignation());
			stmt.setFloat(4, article.getPrixUnitaire());
			stmt.setInt(5, article.getQteStock());
			//Ajoute les variables propres aux ramettes
			if(article instanceof Ramette) {
				Ramette r = (Ramette) article;
				stmt.setString(6, TYPE_RAMETTE);
				stmt.setInt(7, r.getGrammage());
				stmt.setNull(8, Types.VARCHAR);
			}
			//Ajoute les variables propres aux stylos
			else if(article instanceof Stylo) {
				Stylo s = (Stylo) article;
				stmt.setString(6, TYPE_STYLO);
				stmt.setNull(7, Types.INTEGER);
				stmt.setString(8, s.getCouleur());
			}
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			closeConnection();
		}
	}
	
	public void insert(Article article) throws DALException {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			//Récupération de la connection
			connection = getConnection();
			//Préparation de la requête d'insertion
			stmt = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			//Met des valeurs aux variables
			stmt.setString(1, article.getReference());
			stmt.setString(2, article.getMarque());
			stmt.setString(3, article.getDesignation());
			stmt.setFloat(4, article.getPrixUnitaire());
			stmt.setInt(5, article.getQteStock());
			//Ajoute les variables propres aux ramettes
			if(article instanceof Ramette) {
				Ramette r = (Ramette) article;
				stmt.setString(6, TYPE_RAMETTE);
				stmt.setInt(7, r.getGrammage());
				stmt.setNull(8, Types.VARCHAR);
			}
			//Ajoute les variables propres aux stylos
			else if(article instanceof Stylo) {
				Stylo s = (Stylo) article;
				stmt.setString(6, TYPE_STYLO);
				stmt.setNull(7, Types.INTEGER);
				stmt.setString(8, s.getCouleur());
			}

			int nbRows = stmt.executeUpdate();
			if(nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					article.setIdArticle(rs.getInt(1));
				}

			}

		} catch (SQLException e) {
			throw new DALException("Insert article failed - " + article, e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();

		}
	}
	
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(sqlDelete);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			closeConnection();
		}
	}

}
