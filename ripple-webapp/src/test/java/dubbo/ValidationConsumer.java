package dubbo;

import java.util.Set;

import javax.validation.ConstraintViolation;
/** 
  * @description
  * @author   stone
  * @date     2017年12月20日
  */
import javax.validation.ConstraintViolationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.stone.ripple.facade.ValidationFacade;
import com.stone.ripple.facade.model.ValidationParameter;

public class ValidationConsumer {

	public static void main(String[] args) throws Exception {
		String config = ValidationConsumer.class.getPackage().getName().replace('.', '/') + "/dubbo-provider.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		ValidationFacade validationFacade = (ValidationFacade) context.getBean("validationService");
		// Error
		try {
			ValidationParameter parameter = new ValidationParameter();
			validationFacade.save(parameter);
			System.out.println("Validation ERROR");
		} catch (RpcException e) { // 抛出的是RpcException
			ConstraintViolationException ve = (ConstraintViolationException) e.getCause(); // 里面嵌了一个ConstraintViolationException
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations(); // 可以拿到一个验证错误详细信息的集合
			System.out.println(violations);
		}
	}

}
