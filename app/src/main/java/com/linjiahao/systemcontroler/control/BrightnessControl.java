package com.linjiahao.systemcontroler.control;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;

/**
 * 用于获取、控制应用和系统的屏幕亮度
 *
 * @author 林嘉豪
 * @version 1.0
 */
public class BrightnessControl {
	/**
	 * 获取是否设置为自动调整屏幕亮度
	 *
	 * @param context 提供上下文用以获取系统的屏幕亮度
	 * @return 如果为自动调整亮度返回true，否则返回false
	 * @throws Settings.SettingNotFoundException 如果无权获取系统信息则抛出异常
	 */
	public static boolean isAutoBrightness(Context context) throws Settings.SettingNotFoundException {
		return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
	}

	/**
	 * 设置系统是否自动调节亮度
	 *
	 * @param context 提供上下文用以设置系统是否自动调节亮度
	 * @param isAuto  如果提供true则设置系统自动调节亮度，否则设置系统关闭自动调节亮度
	 * @return 如果设置成功，返回true，否则返回false
	 */
	public static boolean setAutoBrightness(Context context, boolean isAuto) {
		return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, isAuto ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	}

	/**
	 * 获取系统的屏幕亮度
	 *
	 * @param context 提供上下文用以获取系统的屏幕亮度
	 * @return 返回系统的屏幕亮度值（0-255）
	 * @throws Settings.SettingNotFoundException 如果无权获取系统信息则抛出异常
	 */
	public static int getSystemBrightness(Context context) throws Settings.SettingNotFoundException {
		ContentResolver contentResolver = context.getContentResolver();
		//////////////////////适用于API level 24///////////////////////////////////////////////////////////////////////////
		////return Math.floorMod(Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS), 256);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		return ((Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS) % 256) + 256) % 256;///////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	/**
	 * 设置系统的屏幕亮度
	 *
	 * @param context    提供上下文用以设置系统的屏幕亮度
	 * @param brightness 欲设置系统的屏幕亮度值
	 * @return 如果设置系统屏幕亮度成功，返回true，否则返回false
	 */
	public static boolean setSystemBrightness(Context context, int brightness) {
		ContentResolver contentResolver = context.getContentResolver();
		//////////////////////适用于API level 24////////////////////////////////////////////////////////////////////////////////////////
		////if (Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, Math.floorMod(brightness,256))) {
		////	//通知其他observer屏幕亮度改变了
		////	contentResolver.notifyChange(Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS), null);
		////	return true;
		////}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, ((brightness % 256) + 256) % 256)) {/////
			//通知其他observer屏幕亮度改变了                                                                                      /////
			contentResolver.notifyChange(Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS), null);           /////
			return true;                                                                                                          /////
		}                                                                                                                         /////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		return false;
	}
}
