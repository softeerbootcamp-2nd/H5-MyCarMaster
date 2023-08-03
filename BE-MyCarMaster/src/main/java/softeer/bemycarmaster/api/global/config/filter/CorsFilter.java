package softeer.bemycarmaster.api.global.config.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {

	private final List<String> allowedOrigins = Arrays.asList("http://h5-my-car-master.vercel.app");

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
		IOException,
		ServletException {

		HttpServletResponse response = (HttpServletResponse)res;
		HttpServletRequest request = (HttpServletRequest)req;
		String origin = request.getHeader("Origin");

		setCorsHeader(response, origin);

		chain.doFilter(req, res);
	}

	private void setCorsHeader(HttpServletResponse response, String origin) {
		if (allowedOrigins.contains(origin)) {
			response.setHeader("Access-Control-Allow-Origin", origin);
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}
	}
}
