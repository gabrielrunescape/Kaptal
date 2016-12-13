package kaptal.gabrielrunescape.com.br.dao;

import java.util.List;
import android.util.Log;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import java.text.SimpleDateFormat;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import kaptal.gabrielrunescape.com.br.util.VersionUtils;
import kaptal.gabrielrunescape.com.br.object.Transaction;
import kaptal.gabrielrunescape.com.br.database.CustomSQLiteOpenHelper;

/**
 * <h1>Classe TransactionDAO</h1>
 *
 * Classe responsável por todos os eventos de inserir, alterar, apagar e exibir
 * todas as transações.
 *
 * @author Gabriel Filipe
 * @version 0.2
 * @since 2016-12-13
 */
public class TransactionDAO {
    private Context context;
    private SQLiteDatabase database;
    private CustomSQLiteOpenHelper helper;
    private static String TAG = TransactionDAO.class.getSimpleName(); // Logging

    private String[] columns = {
            "ID", "Description", "Amount", "Category", "Date_transaction"
    };

    /**
     * Método construtor da classe.
     *
     * @param c Contexto da aplicação.
     */
    public TransactionDAO(Context c) {
        context = c;

        try {
            Log.d(TAG, "Abrindo banco de dados");
            helper = new CustomSQLiteOpenHelper(c, "dbKaptal", VersionUtils.getVersionCode(c));
        } catch (SQLException ex) {
            Log.d(TAG, "Erro ao abrir o banco de dados");
            ex.printStackTrace();
        }
    }

    /**
     * Abre o banco de dados.
     * @param writable Falso para somente leitura.
     */
    public void open(boolean writable) {
        try {
            if (writable) {
                database = helper.getWritableDatabase();
            } else {
                database = helper.getReadableDatabase();
            }
        } catch (SQLException ex) {
            Log.d(TAG, "Erro ao abrir o banco de dados");
            ex.printStackTrace();
        }
    }

    /**
     * Fecha o banco de dados se estiver em aberto.
     */
    @Override
    protected void finalize() {
        try {
            super.finalize();

            if (database != null && database.isOpen()) {
                Log.d(TAG, "Finalizando banco de dados");
                database.close();
            }
        } catch (Throwable ex) {
            Log.d(TAG, "Erro ao finalizar o banco de dados");
            ex.printStackTrace();
        }
    }

    /**
     * Fecha o banco de dados.
     */
    public void closeDatabase() {
        database.close();
    }

    public Transaction insert(Transaction trans) {
        ContentValues values = new ContentValues();

        try {
            values.put("Date_transaction", trans.getDateISO());
            values.put("Description", trans.getDescription());
            values.put("Amount", trans.getAmount());
            values.put("Category", trans.getCategory());

            Log.i(TAG, "Inserindo transação ... ");
            long id = database.insert("`Transaction`", null, values);

            Cursor cursor = database.rawQuery("SELECT * FROM `Transaction` WHERE ID = " + id, null);
            cursor.moveToFirst();

            Transaction transaction = new Transaction();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            transaction.setID(cursor.getLong(0));
            transaction.setDescription(cursor.getString(1));
            transaction.setAmount(cursor.getDouble(2));
            transaction.setCategory(cursor.getInt(3));
            transaction.setDate(format.parse(cursor.getString(4)));

            cursor.close();

            Log.i(TAG, "Transação inserida com sucessso!");

            return transaction;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());

            return null;
        }
    }

    /**
     * Obtem todos os registros de transações dentro do banco de dados.
     *
     * @return uma lista do tipo Transaction.
     */
    public List<Transaction> getAll() {
        List<Transaction> transaction = new ArrayList<Transaction>();
        String query = "SELECT * FROM `Transaction`";

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            while (!cursor.isAfterLast()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Transaction t = new Transaction();

                t.setDescription(cursor.getString(1));
                t.setAmount(cursor.getDouble(2));
                t.setCategory(cursor.getInt(3));
                t.setDate(format.parse(cursor.getString(4)));

                transaction.add(t);
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return transaction;
    }
}
