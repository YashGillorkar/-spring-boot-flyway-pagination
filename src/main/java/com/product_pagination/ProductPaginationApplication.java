package com.product_pagination;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class ProductPaginationApplication {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ProductPaginationApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logSwaggerUrl() {
		try {
			String port = environment.getProperty("server.port", "8080");
			String contextPath = environment.getProperty("server.servlet.context-path", "");
			String host = InetAddress.getLocalHost().getHostAddress();
			String swaggerUrl = String.format("http://%s:%s%s/swagger-ui.html", host, port, contextPath);
			log.info("Swagger UI available LAN IP:- {}", swaggerUrl);
			log.info("Swagger UI available at: http://localhost:{}{}/swagger-ui.html", port, contextPath);
		} catch (Exception e) {
			log.warn("Could not resolve host for swagger URL logging", e);
		}
	}

}
