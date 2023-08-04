package softeer.bemycarmaster.api.global.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://h5-my-car-master.vercel.app", "http://localhost:5173")
			.allowedMethods("GET", "POST")
			.maxAge(3000);
	}
}
