/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtps;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class jTPS_Projects {
    private ArrayList<jTPS_Transaction> transactions = new ArrayList();
    private int mostRecentTransaction = -1;
    
    public jTPS_Projects() {}
    
    public void addTransaction(jTPS_Transaction transaction) {
        // IS THIS THE FIRST TRANSACTION?
        if (mostRecentTransaction < 0) {
            // DO WE HAVE TO CHOP THE LIST?
            if (transactions.size() > 0) {
                transactions = new ArrayList();
            }
            transactions.add(transaction);
        }
        // ARE WE ERASING ALL THE REDO TRANSACTIONS?
        else if (mostRecentTransaction < (transactions.size()-1)) {
            transactions.set(mostRecentTransaction+1, transaction);
            transactions = new ArrayList(transactions.subList(0, mostRecentTransaction+2));
        }
        // IS IT JUST A TRANSACTION TO APPEND TO THE END?
        else {
            transactions.add(transaction);
        }
        //doTransaction();
            transaction = transactions.get(mostRecentTransaction+1);
            mostRecentTransaction++;
    }
    
    public void doTransaction() {
        if (mostRecentTransaction < (transactions.size()-1)) {
            jTPS_Transaction transaction = transactions.get(mostRecentTransaction+1);
            transaction.doTransaction();//doTransaction() is the do method for that SPECIFIC action. NOT THIS METHOD, because
                                        //it is being called by the transaction object, not the jTPS object.
            mostRecentTransaction++;
        }
    }
    
    public void undoTransaction() {
        if (mostRecentTransaction >= 0) {
            jTPS_Transaction transaction = transactions.get(mostRecentTransaction);
            transaction.undoTransaction(); //undoTransaction() is the undo method for that SPECIFIC action. NOT THIS METHOD, because
                                           //it is being called by the transaction object, not the jTPS object.
            mostRecentTransaction--;
        }
    }
    
    public String toString() {
        String text = "--Number of Transactions: " + transactions.size() + "\n";
        text += "--Current Index on Stack: " + mostRecentTransaction + "\n";
        text += "--Current Transaction Stack:\n";
        for (int i = 0; i <= mostRecentTransaction; i++) {
            jTPS_Transaction jT = transactions.get(i);
            text += "----" + jT.toString() + "\n";
        }
        return text;
    }
    
    public void clearTransactions(){
        transactions.clear();
        mostRecentTransaction = -1;
    }
}
