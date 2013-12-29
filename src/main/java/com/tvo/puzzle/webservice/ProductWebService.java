package com.tvo.puzzle.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tvo.puzzle.dto.ProductDTO;
import com.tvo.puzzle.service.IProductService;
import com.tvo.puzzle.util.CommonUtils;
import com.tvo.puzzle.webservice.response.ProductResponse;

@Path("/product")
public class ProductWebService extends BaseWebService{

	IProductService productService;
	@GET
	@Path("/getProduct/{limit}/{offset}")
	@Produces("application/xml")
	public ProductResponse getProduct(@PathParam("limit") Integer limit, @PathParam("offset") int offset){
		
		productService = (IProductService)getService("productService");
		ProductResponse productResponse = new ProductResponse();
		
		List<ProductDTO> p = productService.getProductList(limit, offset);
		productResponse.setProductDTOs(p);
		productResponse.setMessage("product list is gotten");
		productResponse.setResult("success");
		int count = productService.countAll();
		productResponse.setIsMore(CommonUtils.getMore(count, offset, limit));
		return productResponse;
		
	} 
	
}
