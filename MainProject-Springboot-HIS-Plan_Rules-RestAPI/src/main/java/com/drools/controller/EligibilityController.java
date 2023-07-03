package com.drools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drools.constants.LogMsg;
import com.drools.models.Indvinfo;
import com.drools.models.PlanInfo;
import com.drools.service.DroolsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * This res controller class to handle request from end HIS application
 * 
 * @author Tapas Rout Sample Drools Rule controller
 */
@RestController
@Slf4j
public class EligibilityController {

	@Autowired
	private DroolsService droolsService;

	/**
	 * This POST API calculates total marks, percentage and passing grade based on
	 * the input provided
	 * 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "Get PlanInfo by Indvinfo", tags = "Eligibility checker")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved user"),
			                @ApiResponse(code = 404, message = "User not found"),
			                @ApiResponse(code = 500, message = "Internal server Error")
	                      }
	              )

	@PostMapping("/checkplan")
	public ResponseEntity<Object> planCheckEligibility(@RequestBody Indvinfo info) {
		log.info(LogMsg.METHOD_EXECUTION_STARTED + "-planCheckEligibility");
		PlanInfo planInfo = null;
		try {
			log.debug(LogMsg.DEBUG_EXECUTION_STARTED);
			planInfo = droolsService.checkEligibility(info);
			if (planInfo.getPlanStatus().equalsIgnoreCase("")) {
				return new ResponseEntity<>(planInfo, HttpStatus.NOT_FOUND);
			}
		log.debug(LogMsg.DEBUG_EXECUTION_ENDED);	
		} catch (Exception e) {
			log.error(LogMsg.ERROR_OCCOURED+" "+e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(LogMsg.METHOD_EXECUTION_ENDED);
		return ResponseEntity.ok(planInfo);
	}

}
