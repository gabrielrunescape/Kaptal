package kaptal.gabrielrunescape.com.br.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import java.util.Calendar;
import android.widget.Toast;
import android.view.MenuItem;
import android.text.InputType;
import android.widget.EditText;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import kaptal.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;

/**
 * <h1>Classe Transaction Activity</h1>
 *
 * Classe de inicio da acitivity de transação (movimentação). Nesta classe são carregados
 * todos os componentes necessários para execução da activity.
 *
 * @author Gabriel Filipe
 * @version 0.1
 * @since 2016-12-09
 */
public class TransactionActivity extends AppCompatActivity {
    EditText et_date;
    private int mYear, mMonth, mDay;

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
            default:
                Toast.makeText(this, "Função não programada!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
