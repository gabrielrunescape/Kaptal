package kaptal.gabrielrunescape.com.br.view;

import java.util.List;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ListView;
import kaptal.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;
import kaptal.gabrielrunescape.com.br.dao.TransactionDAO;
import kaptal.gabrielrunescape.com.br.object.Transaction;
import kaptal.gabrielrunescape.com.br.adapter.TransactionAdapter;

/**
 * <h1>Classe Home Activity</h1>
 *
 * Classe de inicio da acitivity Home a principal. Nesta classe são carregados
 * todos os componentes necessários para execução da aplicação necessita.
 *
 * @author Gabriel Filipe
 * @version 0.2
 * @since 2016-12-08
 */
public class HomeActivity extends AppCompatActivity {
    private TransactionDAO dao;

    /**
     * Sobreescreve o método extendido <strong>onCreate()</strong>.
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dao = new TransactionDAO(this);
        dao.open(true);
    }

    /**
     * Sobreescreve o método extendido <strong>onResume()</strong>.
     */
    @Override
    protected void onResume() {
        dao.open(true);
        super.onResume();

        List<Transaction> transactions = dao.getAll();
        TransactionAdapter adapter = new TransactionAdapter(this, transactions);

        final ListView lista = (ListView) findViewById(R.id.listview_home);
        lista.setAdapter(adapter);
    }

    /**
     * Sobreescreve o método extendido <strong>onCreateOptionsMenu()</strong>.
     *
     * @param menu Toolbar da aplicação.
     * @return Verdadeiro somente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_home, menu);
        return true;
    }

    /**
     * Sobreeescreve o método extendido <strong>onOptionsItemSelected()</strong>.
     *
     * @param item Item no qual foi selecionado.
     * @return O item selecionado.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        switch (id) {
            case R.id.item_add:
                intent = new Intent(getApplicationContext(), TransactionActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "Função não programada!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
