package arm.man.testsnake.framework;

import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ARM on 01/08/18.
 */

public interface FileIO {
    InputStream readFile(String file) throws IOException;

    OutputStream writeFile(String file) throws IOException;

    InputStream readAsset(String file) throws IOException;

    SharedPreferences getSharedPref();
}
