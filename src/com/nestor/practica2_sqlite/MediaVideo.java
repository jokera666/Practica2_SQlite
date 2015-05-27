package com.nestor.practica2_sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.VideoView;

public class MediaVideo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_video);
		
		VideoView reproductorVideo = (VideoView)findViewById(R.id.ventanaVideo);
		reproductorVideo.setKeepScreenOn(true); //Pantalla activa
		//reproductorVideo.setVideoPath("/sdcard/ejemplo.3gp"); //Fichero local
		reproductorVideo.setVideoPath("http://www.nasa.gov/multimedia/nasatv/NTV-Public-IPS.m3u8");//URL
		reproductorVideo.start();
	}
}
