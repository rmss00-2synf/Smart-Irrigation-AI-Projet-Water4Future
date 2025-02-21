package com.bankmisr.controller;

import java.util.List;

import com.bankmisr.service.PlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankmisr.controller.payload.PlotDto;
import com.bankmisr.data.model.Plot;
import com.bankmisr.service.PlotService;  

@RestController
@RequestMapping("/api/plot")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*",
		allowCredentials = "true")
public class PlotController {

	private final PlotServiceImpl plotService;
	@Autowired
	public PlotController(PlotServiceImpl plotService) {
		this.plotService = plotService;
	}

	@GetMapping
	public List<Plot> getAllPlots() {
		return plotService.getAllPlots();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Plot> getPlot(@PathVariable("id") Integer id) {
		Plot plot = plotService.getPlotById(id);
		if (plot == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(plot);
	}

	@PostMapping("/")
	public ResponseEntity<Plot> addNewPlot(@RequestBody PlotDto plotDto) {
		return ResponseEntity.ok(plotService.addNewPlot(plotDto));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Plot> editPlot(@PathVariable("id") Integer id, @RequestBody PlotDto plotDto) {
		plotDto.setId(id);
		return ResponseEntity.ok(plotService.editPlot(plotDto));
	}

	@PostMapping("/{id}/toggleSensor")
	public ResponseEntity<?> toggleSensor(@PathVariable("id") Integer id) {
		plotService.toggleSensor(id);
		return ResponseEntity.ok().build();
	}

}
