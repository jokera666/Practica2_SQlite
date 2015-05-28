package com.nestor.practica2_sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MediaVideo extends Activity {

	VideoView reproductorVideo;
	boolean aux=true;
	int pos;
	int duracion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_video);
		
		reproductorVideo = (VideoView)findViewById(R.id.ventanaVideo);
		reproductorVideo.setKeepScreenOn(true); //Pantalla activa
		String ruta = Environment.getExternalStorageDirectory()+"/pollo.mp4";
		reproductorVideo.setVideoPath(ruta); //Fichero local
		reproductorVideo.requestFocus();
		
		pos = reproductorVideo.getCurrentPosition();
		duracion = reproductorVideo.getDuration();
		reproductorVideo.seekTo(pos + (duracion-pos)/10);
		
        Button btnPlay = (Button) findViewById(R.id.play);
        btnPlay.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v)
			{
				reproductorVideo.start();
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
					reproductorVideo.pause();
					aux=false;
				}
				
				else
				{
					reproductorVideo.start();
					aux=true;
				}
			}
		});		
	}
}
