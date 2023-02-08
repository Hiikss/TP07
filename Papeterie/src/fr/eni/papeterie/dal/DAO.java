package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T> {

	T selectById(T obj) throws DALException;
	
	List<T> selectAll() throws DALException;
	
	void update(T obj) throws DALException;
	
	void insert(T obj) throws DALException;
	
	void delete(T obj) throws DALException;
	
}
