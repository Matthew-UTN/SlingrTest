package com.web.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.DTO.MathDTO;
import com.web.Service.MathWebService1;
import com.web.Service.MathWebService2;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping(path = "api/v1/Math")
public class MathWebController {
	
	
	
	private MathWebService1 service1;
	private MathWebService2 service2;
		
	public MathWebController(MathWebService1 service1, MathWebService2 service2) {
		this.service1 = service1;
		this.service2 = service2;
	}
	
	

	@GetMapping("/{expression}")
	public ResponseEntity expressionGet(@PathVariable String expression) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service1.solve(expression));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error with the current expression. \"}");
			
		}

	}
	
	@PostMapping("/")
	public ResponseEntity post(@RequestBody MathDTO dto) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(service2.solve(dto));
			
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error with the current expression. \"}");

		}
	}
}
