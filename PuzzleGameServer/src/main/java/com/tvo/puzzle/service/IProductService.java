package com.tvo.puzzle.service;

import java.util.List;

import com.tvo.puzzle.dto.ProductDTO;

public interface IProductService{

	public ProductDTO getProductDTO(Integer id);
	public List<ProductDTO> getProductList(int limit , int offset);
	public int countAll();
	List<ProductDTO> getAll();
}

