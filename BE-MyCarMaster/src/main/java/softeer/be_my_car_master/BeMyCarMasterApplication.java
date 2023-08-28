package softeer.be_my_car_master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class BeMyCarMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeMyCarMasterApplication.class, args);
	}
}
