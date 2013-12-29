package com.tvo.puzzle.dao;

import java.util.List;

import com.tvo.puzzle.entity.Product;

public interface ProductDao extends GenericDao<Product, Integer> {
	public Product getProduct(Integer id);
	public List<Product> getListProduct(int limit, int octet);
	public int countAll();
}
