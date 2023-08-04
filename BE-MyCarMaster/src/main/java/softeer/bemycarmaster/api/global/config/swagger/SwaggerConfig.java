package softeer.bemycarmaster.api.global.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openApi() {
		Info info = new Info()
			.title("My Car Master API Document")
			.version("v0.0.1")
			.description("My Car Master의 API 명세서입니다.");
		return new OpenAPI()
			.components(new Components())
			.info(info);
	}
}
