package softeer.be_my_car_master.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(
				"http://my-car-master.shop",
				"http://*.my-car-master.shop",
				"https://my-car-master.shop",
				"https://*.my-car-master.shop",
				"http://localhost:5173")
			.allowedMethods("GET", "POST")
			.maxAge(3000);
	}
}
