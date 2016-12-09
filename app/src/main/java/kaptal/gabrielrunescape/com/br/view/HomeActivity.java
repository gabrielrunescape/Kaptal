package kaptal.gabrielrunescape.com.br.view;

import java.util.Date;
import java.util.List;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import java.util.ArrayList;
import android.widget.Toast;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.DialogInterface;
import kaptal.gabrielrunescape.com.br.R;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import kaptal.gabrielrunescape.com.br.object.Transaction;
import kaptal.gabrielrunescape.com.br.adapter.TransactionAdapter;

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

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Transaction t = new Transaction();
            t.setAmount(-12.6 * (-i));
            t.setCategory(2 * i);
            t.setDescription("Descrição número " + i + ", só tem otário.");
            t.setDate(new Date(116, i + 1, i * 3));

            transactions.add(t);
        }

        TransactionAdapter adapter = new TransactionAdapter(this, transactions);

        final ListView lista = (ListView) findViewById(R.id.listview_home);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /**
             * Sobreescreve o método <strong>onItemClick()</strong>.
             * Ao clicar no item, encaminha para uma tela permitindo sua visualização.
             *
             * @param parent ListView.
             * @param view ItemAdapter clicado.
             * @param position posição do ItemAdapter.
             * @param id Identificador do ListView.
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < lista.getChildCount(); i++) {
                    if(position == i ){
                        lista.getChildAt(i).setBackgroundResource(R.color.colourPrimaryLight);
                    }else{
                        lista.getChildAt(i).setBackgroundResource(R.color.colourIcons);
                    }
                }
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            /**
             * Sobreescreve o método <strong>onItemLongClick()</strong>.
             * Ao pressionar o item, exibe um AlertDialog.
             *
             * @param parent ListView.
             * @param view ItemAdapter clicado.
             * @param position posição do ItemAdapter.
             * @param id Identificador do ListView.
             * @return Verdaeiro.
             */
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                choiceAlertDialog(view);

                return true;
            }
        });
    }

    /**
     * Cria método quando pressionado o ItemAdapter exibe um AlertDialog com algumas opções.
     */
    final void choiceAlertDialog(final View parent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha uma ação");

        parent.setBackgroundResource(R.color.colourPrimaryLight);
        String[] options = new String[] { "Apagar", "Editar", "Inserir", "Visualizar" };

        builder.setItems(options, new DialogInterface.OnClickListener() {
            /**
             * Ao clicar em um dos itens, encaminha para uma Intent especifica da ação.
             *
             * @param dialog AlertDialog.Builder utilizado.
             * @param which Posição do item clicado.
             */
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        Toast.makeText(getApplicationContext(), "Função não programada!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        builder.show().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            /**
             * Sobreescreve um método interno de <strong>DialogInterface.OnDismissListener()</strong>.
             * Ao perder o foco retorna o ItemAdapter com padrão.
             *
             * @param dialog AlertDialog.Builder utilizado.
             */
            public void onDismiss(DialogInterface dialog) {
                parent.setBackgroundResource(R.color.colourIcons);
            }
        });
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
