package kaptal.gabrielrunescape.com.br.view;

import android.os.Bundle;
import kaptal.gabrielrunescape.com.br.R;
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
}
