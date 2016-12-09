package kaptal.gabrielrunescape.com.br.object;

import java.util.Date;
import java.util.Calendar;
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

    /**
     * Converte o número do mês nas abreviaçõs do mês em pt_BR.
     * @return Abreviação do mês com 3 caracteres.
     */
    public String getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        switch (cal.get(Calendar.MONTH)) {
            case 1:
                return "JAN";
            case 2:
                return "FEV";
            case 3:
                return "MAR";
            case 4:
                return "ABR";
            case 5:
                return "MAI";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AGO";
            case 9:
                return "SET";
            case 10:
                return "OUT";
            case 11:
                return "NOV";
            case 12:
                return "DEZ";
            default:
                return "N/D";
        }
    }
}
