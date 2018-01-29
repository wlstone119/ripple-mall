package basics.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description
 * @author stone
 * @date 2017年12月8日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadApplication.class)
public class ReadApplicationYmlTests {

	@Autowired
	private YmlConfig ymlConfig;

	@Test
	public void testDisplayYmlValue() throws JsonProcessingException {
		System.out.println("simpleProp: " + ymlConfig.getSimpleProp());

		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("arrayProps: " + objectMapper.writeValueAsString(ymlConfig.getArrayProps()));
		System.out.println("listProp1: " + objectMapper.writeValueAsString(ymlConfig.getListProp1()));
		System.out.println("listProp2: " + objectMapper.writeValueAsString(ymlConfig.getListProp2()));
		System.out.println("mapProps: " + objectMapper.writeValueAsString(ymlConfig.getMapProps()));

	}
}
