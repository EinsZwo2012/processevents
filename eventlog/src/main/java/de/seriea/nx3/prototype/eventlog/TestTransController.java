package de.seriea.nx3.prototype.eventlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestTransController {

	@Autowired
	private Gateway gateway;
	
	@RequestMapping(method=RequestMethod.GET)
	public String handle() {
		return gateway.writeToKafka("Test Exception20126");
	}
	
}
