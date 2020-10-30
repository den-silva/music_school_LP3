package ftt.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IcrudPadrao<DomainObject> {
public void insert(DomainObject entidade) throws SQLException;
	
	public void update(DomainObject entidade) throws SQLException;
	
	public void delete(DomainObject entidade) throws SQLException;
	
	public DomainObject findForId(int id) throws SQLException;
	
	public ArrayList<DomainObject> findAll() throws SQLException;
	

}
