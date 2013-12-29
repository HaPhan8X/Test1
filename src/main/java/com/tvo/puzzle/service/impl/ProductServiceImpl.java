package com.tvo.puzzle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvo.puzzle.dao.ProductDao;
import com.tvo.puzzle.dto.ProductDTO;
import com.tvo.puzzle.entity.Product;
import com.tvo.puzzle.service.IProductService;

@Service("productService")
public class ProductServiceImpl implements IProductService{

	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional
	public List<ProductDTO> getAll() {
		List<Product> listEntity = new ArrayList<Product>();
		listEntity = productDao.findAll();
		if(listEntity != null)
		{
			List<ProductDTO> listDTO = new ArrayList<ProductDTO>();
			for(Product p : listEntity)
			{
				ProductDTO pDTO = new ProductDTO();
				entityToDTO(p, pDTO);
				listDTO.add(pDTO);
			}
			return listDTO;
		}
		return null;
	}
	
	@Transactional
	public ProductDTO getProductDTO(Integer id) {
		Product  p = productDao.getProduct(id);
		ProductDTO pd = entityToDTO(p);
		return pd;
	}
	
	private void entityToDTO(Product entity, ProductDTO dto)
	{
		dto.setId(entity.getId());
		dto.setProductCode(entity.getProductCode());
		dto.setProductName(entity.getProductName());
		dto.setCreateDate(entity.getCreateDate());
		dto.setRequiredPoint(entity.getRequiredPoint());
		dto.setStartTime(entity.getStartTime());
		dto.setEndTime(entity.getEndTime());
		dto.setValid(entity.getValid());
	}
	public ProductDTO entityToDTO(Product p){
		ProductDTO pd = new ProductDTO();
		pd.setCreateDate(p.getCreateDate());
		pd.setEndTime(p.getEndTime());
		pd.setId(p.getId());
		pd.setProductCode(p.getProductCode());
		pd.setProductName(p.getProductName());
		pd.setRequiredPoint(p.getRequiredPoint());
		pd.setStartTime(p.getStartTime());
		pd.setValid(p.getValid());
		
		return pd;
	}

	@Override
	public List<ProductDTO> getProductList(int limit, int offset) {
		List <ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<Product> products = new ArrayList<Product>();
		ProductDTO pd;
		products = productDao.getListProduct(limit, offset);
		for(Product p :products){
			pd = entityToDTO(p);
			productDTOs.add(pd);
		}
		return productDTOs;
	}

	@Override
	public int countAll() {
		return productDao.countAll();
	}

}
