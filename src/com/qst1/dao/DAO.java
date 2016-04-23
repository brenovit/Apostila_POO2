package com.qst1.dao;

public interface DAO {
	public <T> void Create (T o);
	public void Show ();
	public <T> void Find (T o);
	public <T> void Uptade (T o);
	public <T> void Delete (T o);	
	//sou um comnetario
}
