package com.linjiahao.systemcontroler.compute;

/**
 * Created by Administrator on 2017/11/1.
 *
 * @author 林嘉豪
 * @version 1.0
 */
public final class MathSupply {
	/**
	 * 取余数，与%不同的是，此函数只返回大于0的数字
	 * @param x 被除数
	 * @param y 除数
	 * @return 大于0的余数
	 */
	public static int mod(int x, int y) {
		int z=x;
		while (z > y) {
			z -= y;
		}
		while (z < 0) {
			z += y;
		}
		return z;
	}
	/**
	 * 取余数，与%不同的是，此函数只返回大于0的数字
	 * @param x 被除数
	 * @param y 除数
	 * @return 大于0的余数
	 */
	public static float mod(float x, float y) {
		float z = x;
		while (z > y) {
			z -= y;
		}
		while (z < 0) {
			z += y;
		}
		return z;
	}
	/**
	 * 取余数，与%不同的是，此函数只返回大于0的数字
	 * @param x 被除数
	 * @param y 除数
	 * @return 大于0的余数
	 */
	public static double mod(double x, double y) {
		double z = x;
		while (z > y) {
			z -= y;
		}
		while (z < 0) {
			z += y;
		}
		return z;
	}
}
