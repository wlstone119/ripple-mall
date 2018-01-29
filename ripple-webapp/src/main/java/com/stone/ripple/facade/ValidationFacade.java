package com.stone.ripple.facade;

/**
 * @description
 * @author stone
 * @date 2017年12月20日
 */
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.stone.ripple.facade.model.ValidationParameter;

public interface ValidationFacade {

	@interface Save{} // 与方法同名接口，首字母大写，用于区分验证场景，如：@NotNull(groups = ValidationService.Save.class)，可选
	
	String save(@NotNull ValidationParameter parameter); // 验证参数不为空

	String update(@NotNull ValidationParameter parameter); // 直接对基本类型参数验证
	
	void delete(@Min(1) int id); // 直接对基本类型参数验证

}
