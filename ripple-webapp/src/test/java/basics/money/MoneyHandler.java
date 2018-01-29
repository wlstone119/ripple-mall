package basics.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @description
 * @author stone
 * @date 2017年12月29日
 */
public class MoneyHandler {

	public static void main(String[] args) {
		System.out.println(new BigDecimal("26.976").setScale(2, RoundingMode.HALF_UP));
		System.out.println(new BigDecimal("26.976").setScale(2, RoundingMode.DOWN));
		System.out.println(new BigDecimal("26.976").setScale(2, RoundingMode.FLOOR));
		System.out.println(new BigDecimal("26.976").setScale(2, RoundingMode.HALF_EVEN));
		System.out.println(new BigDecimal("26.976").setScale(2, RoundingMode.UP));
	}

}
