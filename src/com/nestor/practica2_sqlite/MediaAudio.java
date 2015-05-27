package com.nestor.practica2_sqlite;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MediaAudio extends Activity {

	MediaPlayer reproductorAudio;
	boolean aux=true;
	private SeekBar barraVolumen = null;
	private AudioManager audioManager = null;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_audio);
		
		// Fichero local (en la tarjeta de memoria)
		reproductorAudio = MediaPlayer.create(getApplicationContext(),Uri.parse(Environment.getExternalStorageDirectory()+"/Music/"+"Redneck.mp3"));
		

		
        Button btnPlay = (Button) findViewById(R.id.play);
        btnPlay.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				reproductorAudio.start();
				//reproductorAudio.setVolume(0.5f, 0.5f);
			}
		});
        
        Button btnPause = (Button) findViewById(R.id.pause);
        btnPause.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				if(aux==true)
				{
					reproductorAudio.pause();
					aux=false;
				}
				
				else
				{
					reproductorAudio.start();
					aux=true;
				}
				
			}
		});
        
        Button btnStop = (Button) findViewById(R.id.stop);
        btnStop.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				reproductorAudio.stop();
			}
		});
        
        
	     audioManager = (AudioManager)getSystemService(this.AUDIO_SERVICE); // toma el control del audio del dispositivo
	     int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//obtiene el valor maximo del dispositivo
	     int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//posicion del volumen
	     barraVolumen = (SeekBar) findViewById(R.id.barraVol);//la barma del volmen
	     barraVolumen.setMax(maxVolume);//establece el maximo de la barra del volumen
	     barraVolumen.setProgress(curVolume);//establece el avanze de la barra
	     
	     barraVolumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        	 @Override
        	 public void onStopTrackingTouch(SeekBar arg0) {
        	  // TODO Auto-generated method stub

        	 }

        	 @Override
        	 public void onStartTrackingTouch(SeekBar arg0) {
        	  // TODO Auto-generated method stub

        	 }

        	 @Override
        	 public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
        	  // TODO Auto-generated method stub
        	  audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
        	 }
        	});
	}
	
	
	
}
