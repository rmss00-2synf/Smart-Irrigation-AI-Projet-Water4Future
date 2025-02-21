package com.bankmisr.service;

import java.util.ArrayList;  
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import com.bankmisr.data.model.Crop;
import com.bankmisr.data.repositories.CropRepository;

@Service
public class CropServiceImpl  implements CropService{
	
	CropRepository cropRepository;
	@Autowired
	public CropServiceImpl(CropRepository cropRepository) {
		this.cropRepository = cropRepository;
	}
	
	
	 
	public List<Crop> getAllCropss()   
	{  
		List<Crop> crops = new ArrayList<Crop>();  
		cropRepository.findAll().forEach(crops::add);
		return crops;  
	}  

	public Crop getCropById(Integer id)
	{  
		return cropRepository.findById(id).get();  
	}  

}
