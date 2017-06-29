package com.app;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleDbConfig {

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Bean
	public DataSource dataSource() {

		URI dbUri;
		try {
			String url = "jdbc:postgresql://localhost:5432/collection";
			String dbProperty = System.getenv("DATABASE_URL");
			if (dbProperty != null) {
				dbUri = new URI(dbProperty);

				String[] credentials = dbUri.getUserInfo().split(":");
				username = credentials[0];
				if (credentials.length == 2)
					password = credentials[1];
				else
					password = "";
				url = "jdbc:postgresql://" + dbUri.getHost() + ':'
						+ dbUri.getPort() + dbUri.getPath();
			}

			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setUrl(url);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);

			return basicDataSource;

		} catch (URISyntaxException e) {
			return null;
		}
	}
}