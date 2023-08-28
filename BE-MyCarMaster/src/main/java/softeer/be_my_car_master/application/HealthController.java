package softeer.be_my_car_master.application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HealthController {

	@GetMapping("/health")
	public String healthCheck() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			String hostname = ip.getHostName();
			return "Your current IP address : " + ip
				+ ", Hostname : " + hostname
				+ ", server time : " + LocalDateTime.now();
		} catch (UnknownHostException e) {
			return "Error retrieving IP and Hostname";
		}
	}

	@GetMapping("log/info")
	public void logInfo() {
		log.info("info log");
	}

	@GetMapping("log/error")
	public void logError() {
		log.error("error log");
	}
}
