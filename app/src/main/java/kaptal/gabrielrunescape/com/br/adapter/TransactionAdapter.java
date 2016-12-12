package kaptal.gabrielrunescape.com.br.adapter;

import java.util.List;
import android.view.View;
import java.util.Calendar;
import android.widget.Toast;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.DialogInterface;
import kaptal.gabrielrunescape.com.br.R;
import android.support.v7.app.AlertDialog;
import kaptal.gabrielrunescape.com.br.object.Transaction;

/**
 * <h1>Classe TransactionAdapter</h1>
 *
 * A classe extende um ArrayAdapter no qual é utilizado para inserir
 * informações de um objeto a ele exibindo em uma ListView.
 *
 * @author Gabriel Filipe
 * @version 0.1
 * @since 2016-08-09
 */
public class TransactionAdapter extends ArrayAdapter<Transaction> implements View.OnLongClickListener {
    /**
     * Metódo construtor da classe.
     *
     * @param context Contexto da aplicação.
     * @param lista ArrayList serializado com Transaction.
     */
    public TransactionAdapter(Context context, List<Transaction> lista) {
        super(context, 0, lista);
    }

    /**
     * Sobreescreve o método <strong>getView()</strong>.
     *
     * @param position Posição do elemento clicado.
     * @param convertView View a ser convertida.
     * @param parent Contexto no qual a view se encontra.
     * @return A view convertida com as informações.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_transaction, parent, false);
        }

        Transaction transaction = getItem(position);

        TextView day = (TextView) itemView.findViewById(R.id.txt_day);
        TextView month = (TextView) itemView.findViewById(R.id.txt_month);
        TextView amount = (TextView) itemView.findViewById(R.id.txt_amount);
        TextView category = (TextView) itemView.findViewById(R.id.txt_category);
        TextView description = (TextView) itemView.findViewById(R.id.txt_description);

        Calendar cal = Calendar.getInstance();
        cal.setTime(transaction.getDate());

        month.setText(transaction.getMonth());
        amount.setText(transaction.getAmount() + "");
        category.setText(transaction.getCategory() + "");
        day.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        description.setText(transaction.getDescription());

        itemView.setOnLongClickListener(this);

        return itemView;
    }

    @Override
    /**
     * Ao pressionar o item, exibe um AlertDialog.
     *
     * @param view ItemAdapter pressionado.
     * @return Se está ativo ou não o AlertDialog.
     */
    public boolean onLongClick(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Escolha uma ação");

        view.setPressed(true);
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
                        Toast.makeText(view.getContext(), "Função não programada!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        return builder.show().isShowing();
    }
}
