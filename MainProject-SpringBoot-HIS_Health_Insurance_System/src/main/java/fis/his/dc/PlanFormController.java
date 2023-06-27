package fis.his.dc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fis.his.dc.snap.model.SnapModel;
import fis.his.dc.snap.service.InterfaceSnap;

@Controller
@RequestMapping(value = {"/snapPlan"})
public class PlanFormController {

	@Autowired
	private InterfaceSnap snapservice;
	
	@PostMapping("/snappost")
	public String snapData(@ModelAttribute("snapM") SnapModel model,
			               RedirectAttributes attributes) {
		String saveSnap = snapservice.saveSnap(model);
		attributes.addAttribute("snapid", attributes);
		attributes.addFlashAttribute("saveSnap", saveSnap);
		return "redirect:snapScrn-2";
	}
	
	public String snapScrn2(RedirectAttributes attributes) {
		
		Object attribute = attributes.getAttribute("snapid");
		
		return null;
	}
	
}
