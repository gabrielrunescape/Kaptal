package kaptal.gabrielrunescape.com.br.view;

import java.util.Date;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import java.util.Calendar;
import android.widget.Toast;
import android.view.MenuItem;
import android.text.InputType;
import android.widget.Spinner;
import android.widget.EditText;
import java.text.ParseException;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import android.app.DatePickerDialog;
import kaptal.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;
import kaptal.gabrielrunescape.com.br.dao.TransactionDAO;
import kaptal.gabrielrunescape.com.br.object.Transaction;

/**
 * <h1>Classe Transaction Activity</h1>
 *
 * Classe de inicio da acitivity de transação (movimentação). Nesta classe são carregados
 * todos os componentes necessários para execução da activity.
 *
 * @author Gabriel Filipe
 * @version 0.2
 * @since 2016-12-09
 */
public class TransactionActivity extends AppCompatActivity {
    private int mYear, mMonth, mDay;
    private Spinner spinner_category;
    EditText et_amount, et_date, et_description;

    /**
     * Sobreescreve o método extendido <strong>onCreate()</strong>.
     *
     * @param savedInstanceState Salva os dados da instância.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
    }

    /**
     * Sobreescreve o método extendido <strong>onResume()</strong>.
     */
    @Override
    protected void onResume() {
        super.onResume();

        et_date = (EditText) findViewById(R.id.et_date);
        et_amount = (EditText) findViewById(R.id.et_amount);
        et_description = (EditText) findViewById(R.id.et_description);
        spinner_category = (Spinner) findViewById(R.id.spinner_category);

        et_date.setInputType(InputType.TYPE_NULL);
        et_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            /**
             * Sobreescreve o método <strong>onFocusChange()</strong>.
             * Que ao mudar o foco realiza determinada ação.
             *
             * @param v View no qual é usada.
             * @param hasFocus Verdadeiro se tiver foco, falso para o contrário.
             */
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    // Launch Date Picker Dialog
                    DatePickerDialog dpd = new DatePickerDialog(TransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        /**
                         * Código copiado de:
                         * @link http://pt.androids.help/q8701
                         */
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String data = "";

                            if (dayOfMonth < 10) {
                                data += "0" + dayOfMonth;
                            } else {
                                data += dayOfMonth;
                            }

                            if (monthOfYear + 1 < 10) {
                                data += "/0" + (monthOfYear + 1) + "/" + year;
                            } else {
                                data += "/" + (monthOfYear + 1) + "/" + year;
                            }

                            et_date.setText(data);
                        }
                    }, mYear, mMonth, mDay);

                    dpd.show();
                }
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
        getMenuInflater().inflate(R.menu.toolbar_transaction, menu);
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
        int id = item.getItemId();

        switch (id) {
            case R.id.item_done:
                doneTransaction();
                break;
            default:
                Toast.makeText(this, "Função não programada!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método usado para implementar na inserção de uma transação fora
     * do método <strong>onOptionsItemSelected()</strong>.
     */
    private void doneTransaction() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(et_date.getText().toString());

            String description = et_description.getText().toString();
            int category = spinner_category.getSelectedItemPosition() + 1;
            double amount = Double.parseDouble(et_amount.getText().toString());

            Transaction transaction = new Transaction();
            transaction.setDate(date);
            transaction.setAmount(amount);
            transaction.setCategory(category);
            transaction.setDescription(description);

            TransactionDAO dao = new TransactionDAO(this);

            dao.open(true);
            transaction = dao.insert(transaction);
            dao.closeDatabase();

            if (transaction != null) {
                Toast.makeText(this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Houve um erro ao inserir", Toast.LENGTH_LONG).show();
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Foi encontrado um erro.", Toast.LENGTH_SHORT).show();
        }
    }
}
