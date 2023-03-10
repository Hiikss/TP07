/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
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
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;


/**
 * @author Eni Ecole
 * 
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String TYPE_STYLO = "STYLO";
	private static final String TYPE_RAMETTE = "RAMETTE";

	private static final String SELECT_BY_ID = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type " +
			" from articles where idArticle = ?";
	private static final String SELECT_ALL = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type " +  
			" from articles";
	private static final String UPDATE = "update articles set reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=? where idArticle=?";
	private static final String INSERT = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,type,grammage,couleur) values(?,?,?,?,?,?,?,?)";
	private static final String DELETE = "delete from articles where idArticle=?";
	private static final String SELECT_BY_MARQUE = "select reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type " + 
													" from articles where marque = ?";
	private static final String SELECT_BY_MOT_CLE = "select reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type " + 
													" from articles where marque like ? or designation like ?";
	

	@Override
	public Article selectById(Article article) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Article art = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(SELECT_BY_ID);
			rqt.setInt(1, article.getIdArticle());

			rs = rqt.executeQuery();
			if (rs.next()){

				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + article.getIdArticle() , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return art;
	}

	@Override
	public List<Article> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(SELECT_ALL);
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return liste;

	}



	@Override
	public void update(Article data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(UPDATE);
			rqt.setString(1, data.getReference());
			rqt.setString(2, data.getMarque());
			rqt.setString(3, data.getDesignation());
			rqt.setFloat(4, data.getPrixUnitaire());
			rqt.setInt(5, data.getQteStock());
			rqt.setInt(8, data.getIdArticle());
			if (data instanceof Ramette) {
				Ramette r = (Ramette) data;
				rqt.setInt(6, r.getGrammage());
				rqt.setNull(7, Types.VARCHAR);
			}
			if (data instanceof Stylo) {
				Stylo s = (Stylo) data;
				rqt.setNull(6, Types.INTEGER);
				rqt.setString(7, s.getCouleur());
			}

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update article failed - " + data, e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx !=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void insert(Article data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getReference());
			rqt.setString(2, data.getMarque());
			rqt.setString(3, data.getDesignation());
			rqt.setFloat(4, data.getPrixUnitaire());
			rqt.setInt(5, data.getQteStock());
			if (data instanceof Ramette) {
				Ramette r= (Ramette) data;
				rqt.setString(6, TYPE_RAMETTE);
				rqt.setInt(7, r.getGrammage());
				rqt.setNull(8, Types.VARCHAR);
			}
			if (data instanceof Stylo) {
				Stylo s = (Stylo) data;
				rqt.setString(6, TYPE_STYLO);
				rqt.setNull(7, Types.INTEGER);
				rqt.setString(8, s.getCouleur());
			}

			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					data.setIdArticle(rs.getInt(1));
				}

			}

		}catch(SQLException e){
			throw new DALException("Insert article failed - " + data, e);
		}
		finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}

		}
	}





	@Override
	public void delete(Article article) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {		
			cnx = JdbcTools.getConnection();
			//l'int??grit?? r??f??rentielle s'occupe d'invalider la suppression
			//si l'article est r??f??renc?? dans une ligne de commande
			rqt = cnx.prepareStatement(DELETE);
			rqt.setInt(1, article.getIdArticle());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + article.getIdArticle(), e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed " , e);
			}

		}		
	}

	@Override
	public List<Article> selectByMarque(String marque) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(SELECT_BY_MARQUE);
			rqt.setString(1, marque);
			rs = rqt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMarque failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed " , e);
			}
		}
		return liste;
	}

	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Article> liste = new ArrayList<Article>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(SELECT_BY_MOT_CLE);
			rqt.setString(1, motCle);
			rs = rqt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (TYPE_STYLO.equalsIgnoreCase(rs.getString("type").trim())){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (TYPE_RAMETTE.equalsIgnoreCase(rs.getString("type").trim())){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMotCle failed - " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed " , e);
			}
		}
		return liste;
	}
}
