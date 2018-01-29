package basics.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @description
 * @author stone
 * @date 2017年12月8日
 */
@SpringBootApplication
@EnableConfigurationProperties({ YmlConfig.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class ReadApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReadApplication.class, args);
	}
}
