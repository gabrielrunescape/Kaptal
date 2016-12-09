package kaptal.gabrielrunescape.com.br.adapter;

import java.util.List;
import android.view.View;
import java.util.Calendar;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import kaptal.gabrielrunescape.com.br.R;
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
public class TransactionAdapter extends ArrayAdapter<Transaction> {
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

        day.setText(cal.get(Calendar.DAY_OF_MONTH) + "");
        month.setText(cal.get(Calendar.MONTH) + "");
        amount.setText(transaction.getAmount() + "");
        description.setText(transaction.getDescription());

        return itemView;
    }
}
