package example.com.loaderexample.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import example.com.loaderexample.R;

/**
 * Created by Gabriel Gerardo Rodríguez Díaz (oasis1992) on 29/08/2016.
 */
public class ExampleLoader extends AsyncTaskLoader<String> {

    Context mContext;
    String mMessage;
    public ExampleLoader(Context context) {
        super(context);
        mContext = context;

    }

    @Override
    public String loadInBackground() {
        try{
            Thread.sleep(4000);
            return mContext.getString(R.string.msg_data);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);

        if (isReset()) {
            // El loader se reinicio, ignorar los datos e invalidar los datos.
            mMessage = null;
            return;
        }

        mMessage = data;

        if (isStarted()) {
           // Si el loader se encuentra en un estado de iniciado, se entregan los datos,
            // deliverResult lo hace por nosotros.
            super.deliverResult(mMessage);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mMessage != null) {
            // entregar datos previamente cargados
            deliverResult(mMessage);
        }

       // Si los datos han cambiado desde la última vez que fue cargado
        // O no está disponible actualmente , forzar la carga.
        if (takeContentChanged() || mMessage == null) {
            forceLoad();
        }
    }

}
