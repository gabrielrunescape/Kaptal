package kaptal.gabrielrunescape.com.br.object;

import java.util.Date;
import java.io.Serializable;

/**
 * <h1>Objecto Transaction</h1>
 *
 * Objeto serializavél no qual estão armazenado todas as funcionalidades de
 * uma transação (movimentação) dentro da aplicação.
 *
 * @autor Gabriel Filipe
 * @version 0.1
 * @since 2016-12-09
 */
public class Transaction implements Serializable{
    private Date date;
    private int category;
    private double amount;
    private String description;

    /**
     * @return Obtem o valor da transação.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount Insere um valor na movimentação.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return Obtem o código da categoria.
     */
    public int getCategory() {
        return category;
    }

    /**
     * @param category Insere um código à categoria.
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * @return Obtem a data da movimentação.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date Insere a data da movimentação.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return Obtem a descrição da movimentação.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Insere uma descrição à movimentação.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
