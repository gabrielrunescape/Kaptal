package kaptal.gabrielrunescape.com.br.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import kaptal.gabrielrunescape.com.br.R;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * <h1>Classe Home Activity</h1>
 *
 * Classe de inicio da acitivity Home a principal. Nesta classe são carregados
 * todos os componentes necessários para execução da aplicação necessita.
 *
 * @author Gabriel Filipe
 * @version 0.1
 * @since 2016-12-08
 */
public class HomeActivity extends AppCompatActivity {
    /**
     * Sobreescreve o método extendido <strong>onCreate()</strong>.
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     * Sobreescreve o método extendido <strong>onResume()</strong>.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Sobreescreve o método extendido <strong>onCreateOptionsMenu()</strong>.
     * @param menu Toolbar da aplicação.
     * @return Verdadeiro somente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_home, menu);
        return true;
    }
}
