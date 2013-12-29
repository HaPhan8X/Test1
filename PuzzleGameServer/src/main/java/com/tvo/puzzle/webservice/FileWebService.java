package com.tvo.puzzle.webservice;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.tvo.puzzle.util.Constants;
import com.tvo.puzzle.util.PropertiesUtil;

@Path("/image")
public class FileWebService {
	@GET
	@Path("/getImages/{idGame}/{folderGame}/{image}")
	@Produces("application/octet-stream")
	public Response getImage(@PathParam("idGame") Integer idGame,
			@PathParam("folderGame") String folderGame,
			@PathParam("image") String image) {
		byte[] imageData = null;
		try {
			String fileName = image;
			image = PropertiesUtil.get(Constants.UPLOAD_SCREENPLAY) +  idGame
					+ "/" + folderGame + "/" + image;
			InputStream is = new BufferedInputStream(new FileInputStream(image));
			int size = 1024;
			if (null != is && is.available() > 0) {
				size = is.available();
				imageData = new byte[size];
				is.read(imageData, 0, size);
				ResponseBuilder response = Response.ok((imageData));
				response.header("Content-Disposition", "attachment; filename="
						+ fileName);
				return response.build();
			}
		} catch (IOException e) {
			return null;
		}
		return null;
	}
}
