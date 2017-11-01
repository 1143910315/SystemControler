package com.linjiahao.systemcontroler;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.linjiahao.systemcontroler.control.BrightnessControl;

public class MainActivity extends AppCompatActivity {
	SeekBarControl seekBarControl = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBarControl = new SeekBarControl(findViewById(R.id.brightness));
		try {
			seekBarControl.setSeekBarProgress(BrightnessControl.getSystemBrightness(this));
		} catch (Settings.SettingNotFoundException e) {
			Toast.makeText(this,R.string.get_auto_brightness_error,Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		findViewById(R.id.confirm).setOnClickListener(v -> BrightnessControl.setSystemBrightness(this,seekBarControl.getSeekBarProgress()));
		try {
			((CheckBox)findViewById(R.id.isauto)).setChecked(BrightnessControl.isAutoBrightness(this));
		} catch (Settings.SettingNotFoundException e) {
			Toast.makeText(this,R.string.get_auto_brightness_error,Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		((CheckBox)findViewById(R.id.isauto)).setOnCheckedChangeListener((buttonView, isChecked) -> BrightnessControl.setAutoBrightness(this,isChecked));
	}
}

/**
 * 控制滑块条动作以及获取滑块条的进度
 *
 * @author 林嘉豪
 * @version 1.0
 */
class SeekBarControl {
	private SeekBar seekBar = null;

	/**
	 * 初始化一个滑块条
	 *
	 * @param seekBar 欲被操作的滑块条
	 */
	SeekBarControl(SeekBar seekBar) {
		this.seekBar = seekBar;
		this.seekBar.setOnSeekBarChangeListener(new SeekBarProgressChanged());
	}

	/**
	 * 获取滑块条的进度
	 *
	 * @return 返回滑块条的进度
	 */
	public int getSeekBarProgress() {
		return seekBar.getProgress();
	}

	/**
	 * 设置滑块条的进度
	 *
	 * @param progress 欲设置滑块条的进度值
	 */
	public void setSeekBarProgress(int progress) {
		seekBar.setProgress(progress);
	}

	class SeekBarProgressChanged implements SeekBar.OnSeekBarChangeListener {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	}
}

