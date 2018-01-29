package com.stone.ripple.util.tool;

import java.util.Random;

import org.springframework.util.Assert;

/**
 * 伪随机，索引递增不重复序列 @description.
 * 
 * @modificationHistory.
 * 
 * @author bailing 2017年5月10日下午8:49:28 TODO
 */
public class RandomUtils {
	private static char[] fixedCharArray = new char[] { 'c', 'a', 'm', 'C', '9', 's', 'p', 'E', 'd', 't', 'e', 'Z', 'N',
			'5', 'Q', 'J', 'T', 'x', 'f', 'j', 'B', 'l', 'y', 'I', 'R', 'w', 'U', '2', 'X', 'F', 'b', 'W', 'v', 'M',
			'q', 'V', '7', 'K', '4', '6', 'r', 'S', 'i', 'g', 'u', 'G', 'H', 'k', '0', 'L', '3', 'P', 'o', '1', 'h',
			'z', 'Y', 'n', 'D', 'A', '8', 'O' };

	public static NoRepeatIncrementSequence goOnWith(String randomKey) {
		Assert.notNull(randomKey, "randomKey cannot be null point");
		char[] currentKey = randomKey.toCharArray();
		int[] curChArrIndex = new int[currentKey.length];
		for (int i = 0; i < curChArrIndex.length; i++) {
			int index = find(fixedCharArray, currentKey[i]);
			assert index >= 0;
			curChArrIndex[i] = index;
		}
		NoRepeatIncrementSequence sequenceReader = new NoRepeatIncrementSequence(fixedCharArray, curChArrIndex);
		sequenceReader.getAndIncreasement();
		return sequenceReader;
	}

	private static int find(char[] array, char ch) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 实现N进制大数相加有序序列，数组索引有序，数组元素看似无序 @description.
	 * 
	 * @modificationHistory.
	 * 
	 * @author bailing 2017年5月10日下午8:09:10 TODO
	 */
	public static class NoRepeatIncrementSequence implements Sequence {
		private volatile int[] charArrayIndex = null;
		private char[] charArray = null;

		/**
		 * 
		 * 创建一个新的实例SequenceReader.
		 *
		 * @param charArray
		 *            字符数组，该数组长度为进位长度，每次 index 个位 +1，超过数组长度后进位十位 +1
		 *            以此类推，溢出抛出越界异常
		 * @param units
		 *            位数，若设置为3，则有个位，十位，百位，设置4
		 *            则存在个位，十位，百位，千位，每次进位根据charArray数组长度进行进位
		 * 
		 *            例如: charArray = {'0','1','2','3','4','5','6','7','8','9'}
		 *            units = 3; charArray长度为10，则为10进制，最大长度为999，超过则抛出越界异常
		 * 
		 */
		public NoRepeatIncrementSequence(char[] charArray, int units) {
			this.charArrayIndex = new int[units];
			this.charArray = charArray;
		}

		/**
		 * 
		 * 创建一个新的实例SequenceReader.
		 *
		 * @param charArray
		 * @param charArrayIndex
		 */
		public NoRepeatIncrementSequence(char[] charArray, int[] charArrayIndex) {
			this.charArrayIndex = charArrayIndex;
			this.charArray = charArray;
		}

		/**
		 * 获取索引当前数组索引数值并加1，保证索引递增唯一，保证每次获取值唯一
		 * 
		 * @author bailing @creationDate. 2017年5月10日 下午8:22:04 @description.
		 * 
		 * @return
		 */
		public synchronized String getAndIncreasement() {
			char[] curSeq = new char[charArrayIndex.length];
			synchronized (this) {
				for (int i = 0; i < curSeq.length; i++) {
					curSeq[i] = charArray[charArrayIndex[i]];
				}

				for (int i = charArrayIndex.length - 1; i >= 0; i--) {
					int k = 0;
					charArrayIndex[i] += 1; // 每次调用个位+1
					if (charArrayIndex[i] >= charArray.length) {
						// 如果大于数组长度则需要进位
						charArrayIndex[i] -= charArray.length;
						k = 1;
					}
					if (k == 1 && i == 0) {
						throw new ArrayIndexOutOfBoundsException("already maximum sequence! next times will repeat");
					}

					if (k == 0) {
						break;
					}
				}
			}
			return new String(curSeq);
		}

		@Override
		public String next() {
			return getAndIncreasement();
		}

		@Override
		public String next(int unit) {
			throw new RuntimeException("该方法不支持");
		}

	}

	public static void main(String[] args) {
		NoRepeatIncrementSequence sequence = new NoRepeatIncrementSequence(
				new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }, 4);
		for (int i = 0; i < 1000; i++) {
			System.out.println(sequence.getAndIncreasement());
		}
	}

	public static Sequence newNoRepeatIncrementSequence(int units) {
		assert units > 0;
		return new NoRepeatIncrementSequence(fixedCharArray, units);
	}

	public static class RandomSequence implements Sequence {
		private Random random = new Random();
		private int unit;
		private char[] randomArray;

		public RandomSequence(char[] fixedCharArray, int unit) {
			this.unit = unit;
			this.randomArray = fixedCharArray;
		}

		@Override
		public String next() {
			return next(unit);
		}

		@Override
		public String next(int unit) {
			char[] ch = new char[unit];
			for (int i = 0; i < ch.length; i++) {
				ch[i] = randomArray[random.nextInt(fixedCharArray.length)];
			}
			return new String(ch);
		}

	}

	public static Sequence newRandomSequence(int units) {
		assert units > 0;
		return new RandomSequence(fixedCharArray, units);
	}

	public interface Sequence {
		/**
		 * 生成随机数
		 * 
		 * @author bailing @creationDate. 2017年5月16日 下午2:55:43 @description.
		 * 
		 * @return
		 */
		String next();

		String next(int unit);

	}

}
