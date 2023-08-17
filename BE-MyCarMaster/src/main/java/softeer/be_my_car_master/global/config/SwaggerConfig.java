package softeer.be_my_car_master.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Value("${api.server.url}")
	private String serverUrl;

	@Bean
	public OpenAPI openApi() {
		Info info = new Info()
			.title("My Car Master API Document")
			.version("v2.0.0")
			.description("My Car Master의 API 명세서입니다.");

		return new OpenAPI()
			.addServersItem(new Server().url(serverUrl).description("Server URL"))
			.components(new Components())
			.info(info);
	}
}
