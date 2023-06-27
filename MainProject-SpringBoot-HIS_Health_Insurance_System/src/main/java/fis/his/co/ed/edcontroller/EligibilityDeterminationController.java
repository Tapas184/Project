package fis.his.co.ed.edcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import fis.his.co.ed.model.Indvinfo;
import fis.his.co.ed.service.EligibilityInserface;

@Controller
public class EligibilityDeterminationController {

	@Autowired
	private EligibilityInserface edService;
	
	
	/**
	 * This method is used for save the data into eligibility table;
	 * @param info
	 * @param map
	 * @return
	 */
	@GetMapping("/dcFormSubmit")
	public String checkEligibility(Indvinfo info,
			                       Map<String, Object> map) {
		try {
			edService.saveEligibilityData(info);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String edMsg = "SuccessFully submit your form, Please wait 24hrs we will notify you by main "
				+ "of your status";
		map.put("edMsg", edMsg);
		return "edFormSubmit";
	}
}
