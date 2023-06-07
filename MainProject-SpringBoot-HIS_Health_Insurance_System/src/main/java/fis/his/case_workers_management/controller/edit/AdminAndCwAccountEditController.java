package fis.his.case_workers_management.controller.edit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fis.his.case_workers_management.model.CwAndAdPojo;
import fis.his.case_workers_management.service.adminandcw.AdminAndCwServiceInterface;

@Controller
@RequestMapping("/admin$cw$edit")
public class AdminAndCwAccountEditController {
	
	@Autowired
	private AdminAndCwServiceInterface service;
	
	@GetMapping("/getalldetails")
	public String getAllUser(Map<String, Object> map) {
		List<CwAndAdPojo> userList = service.getAllData();
		map.put("userlist", userList);
		return "case_workers_management/editjsp/getalldata";
	}

}
