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
import fis.his.dc.ccap.model.CcapModel;
import fis.his.dc.ccap.service.CcapInterface;
import fis.his.dc.planchoose.PlanModel;
import fis.his.dc.snap.LogConstant;
import fis.his.dc.snap.model.SnapModel;
import fis.his.dc.snap.service.InterfaceSnap;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = { "/snapPlan" })
@Slf4j
public class PlanFormController {

	@Autowired
	private EligibilityInserface edService;

	@Autowired
	private InterfaceSnap snapservice;

	@Autowired
	private CcapInterface ccapService;

	/**
	 * This method is used for get the data from snap plan form and process
	 * 
	 * @param model
	 * @param attributes
	 * @return
	 */
	@PostMapping("/snappost")
	public String snapDataProcess(@ModelAttribute("model") PlanModel mdl, RedirectAttributes attributes) {
		log.info(LogConstant.METHOD_EXECUTION_STARTED + "-snapDataProcess");
		try {
			log.debug(LogConstant.DEBUGING_EXECUTION_STARTED);
			SnapModel model = new SnapModel();
			model.setApplicationId(mdl.getApplicationId());
			model.setCaseId(Long.parseLong(mdl.getCaseId().toString()));
			model.setIsEmployee(mdl.getIsEmployee());
			model.setPlanName(mdl.getPlanName());
			model.setSalary(mdl.getSalary());
			String saveSnap = snapservice.saveSnap(model);
			attributes.addFlashAttribute("saveSnap", saveSnap);
			Indvinfo info = new Indvinfo();
			info.setPlanName(model.getPlanName());
			info.setCaseNumber(model.getCaseId());
			info.setIsEmployee(model.getIsEmployee());
			info.setSalary(model.getSalary());
			edService.saveEligibilityData(info);
		} catch (JsonProcessingException e) {
			log.error(LogConstant.ERROR + "-snapDataProcess");
			e.printStackTrace();
		}
		log.debug(LogConstant.DEBUGING_EXECUTION_ENDED);
		return "redirect:snapScrn-2";
	}

	@GetMapping("/snapScrn-2")
	public String snapScrn2(RedirectAttributes attributes) {
		return "DCModule/SNAP/formsubmission";
	}

	@PostMapping("/ccapPost")
	public String ccapDataProcess(@ModelAttribute("model") PlanModel mdl, RedirectAttributes attribute) {

		log.info(LogConstant.METHOD_EXECUTION_STARTED + "-ccapDataProcess");
		try {
			log.debug(LogConstant.DEBUGING_EXECUTION_STARTED);
			CcapModel model = new CcapModel();
			model.setApplicationId(mdl.getApplicationId());
			model.setCaseId(mdl.getCaseId());
			model.setChildAge(mdl.getChildAge());
			model.setChildCount(mdl.getChildCount());
			model.setChildStatus(mdl.getChildStatus());
			model.setMarriedStatus(mdl.getMarriedStatus());
			model.setPlanName(mdl.getPlanName());
			boolean saveCcapPlan = ccapService.saveCcapPlan(model);
			if (saveCcapPlan) {
				String msg = "Successfully save your data please wait 24hours we will notify you about plan status";
				attribute.addFlashAttribute("saveSnap", msg);
				Indvinfo info = new Indvinfo();
				info.setCaseNumber(Long.parseLong(model.getCaseId().toString()));
				info.setMarriedStatus(model.getMarriedStatus());
				info.setChildCount(model.getChildCount());
				info.setChildStatus(model.getChildStatus());
				info.setPlanName(model.getPlanName());
				info.setChildAge(model.getChildAge());
				edService.saveEligibilityData(info);

			}
		} catch (JsonProcessingException e) {

			log.error(e.getMessage());
			e.printStackTrace();
		}
		log.debug(LogConstant.DEBUGING_EXECUTION_ENDED);
		return "redirect:snapScrn-2";
	}

}
