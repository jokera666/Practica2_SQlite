package com.nestor.practica2_sqlite;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

//Implementamos el OnGesturePerformedListener para que el codigo no sea muy pesado
public class MainActivity extends Activity implements
		OnGesturePerformedListener {
	// Creamos el componente para relacionarlo con el XML
	// Y el gesture library para rescatar el archivo que hemos
	// creado anteriormente
	private GestureOverlayView gesture;
	private GestureLibrary gLibrary;
	private TextView salida;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		salida = (TextView) findViewById(R.id.salida);
		// Relacionamos con el XML
		gesture = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		// Le añadimos el listener
		gesture.addOnGesturePerformedListener(this);
		// Creamos la carpeta res/raw y añadimos el archivo gestures
		// añadimos el raw al gLibrary
		gLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		// lo cargamos...
		gLibrary.load();

	}

	// Al obtener algun gesto...
	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		// añadimos los diferentes resultados obtenidos
		// ya comparados con nuestro archivo y ordenados de mas fiable a menos
		ArrayList<Prediction> predictions = gLibrary.recognize(gesture);
		// Si a encontraod algun resultado
		if (predictions.size() > 0) {
			// En este caso solos nos interesa el gesto que más se parezca
			Prediction prediccion = predictions.get(0);
			// Miramos que tengo un parecido mínimo
			if (prediccion.score > 1.5) {
				// Decimos lo que ha escrito
				Toast.makeText(this, prediccion.name, Toast.LENGTH_SHORT)
						.show();
			} else {
				// Si no supera el 1.5 de fiabilidad..
				Toast.makeText(this, "No se ha reconocido.", Toast.LENGTH_SHORT)
						.show();
			}

			// Recorremos las puntuaciones de las predicciones y las mostramos
			// en el TextView
			salida.setText("Prediccion de los diversos gestos\n");
			for (Prediction prediction : predictions) {
				salida.append(prediction.name + " " + prediction.score + "\n");
			}

		}
	}
}
