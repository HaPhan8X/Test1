package com.tvo.puzzle.cache;

public interface ICache {
	
	public void reset() throws Exception;
	
	public void load() throws Exception;
	
	public boolean exist(final Integer id) throws Exception;
}
