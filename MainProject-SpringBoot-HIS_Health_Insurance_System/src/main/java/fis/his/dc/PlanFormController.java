package fis.his.dc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import fis.his.co.ed.model.Indvinfo;
import fis.his.co.ed.service.EligibilityInserface;
import fis.his.dc.snap.model.SnapModel;
import fis.his.dc.snap.service.InterfaceSnap;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = {"/snapPlan"})
@Slf4j
public class PlanFormController {

	@Autowired
	private EligibilityInserface edService;
	
	@Autowired
	private InterfaceSnap snapservice;
	
	/**
	 * This method is used for get the data from snap plan form and process
	 * @param model
	 * @param attributes
	 * @return
	 */
	@PostMapping("/snappost")
	public String snapDataProcess(@ModelAttribute("snapM") SnapModel model,
			               RedirectAttributes attributes) {
		log.info(LogConstant.METHOD_EXECUTION_STARTED+"-snapDataProcess");
		try {
			log.debug(LogConstant.DEBUGING_EXECUTION_STARTED);
		String saveSnap = snapservice.saveSnap(model);
		attributes.addFlashAttribute("saveSnap", saveSnap);
		Indvinfo info = new Indvinfo();
		info.setPlanName(model.getPlanName());
		info.setCaseNumber(model.getCaseId());
		info.setIsEmployee(model.getIsEmployee());
		info.setSalary(model.getSalary());
		edService.saveEligibilityData(info);
		} catch (JsonProcessingException e) {
			log.error(LogConstant.ERROR+"-snapDataProcess");
			e.printStackTrace();
		}
		log.debug(LogConstant.DEBUGING_EXECUTION_ENDED);
		return "redirect:snapScrn-2";
	}
	
	@GetMapping("/snapScrn-2")
	public String snapScrn2(RedirectAttributes attributes) {
		return "DCModule/SNAP/formsubmission";
	}
	
}
