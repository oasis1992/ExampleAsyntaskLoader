package example.com.loaderexample;

import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import example.com.loaderexample.loader.ExampleLoader;

/**
 * Created by Gabriel Gerardo Rodríguez Díaz (oasis1992) on 29/08/2016.
 */
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String STATE_MESSAGE = "state_message";
    private static final int LOADER_EXAMPLE = 0;
    TextView txvMessage;
    String mMessage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvMessage = (TextView) findViewById(R.id.txv_hello);
        if(savedInstanceState != null){
            mMessage = savedInstanceState.getString(STATE_MESSAGE);
            if(mMessage == null){
                txvMessage.setText(getString(R.string.hello));
            }else{
                txvMessage.setText(mMessage);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportLoaderManager().initLoader(LOADER_EXAMPLE, null /* args */, this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(STATE_MESSAGE, mMessage);

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new ExampleLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if(data != null){
            mMessage = data;
            txvMessage.setText(data);
        }else{
            mMessage = getString(R.string.error);
            txvMessage.setText(getString(R.string.error));
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        /* do nothing */
    }
}
