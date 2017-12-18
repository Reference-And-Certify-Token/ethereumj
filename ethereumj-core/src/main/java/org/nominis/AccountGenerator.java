package org.nominis;

import org.ethereum.core.Account;
import java.util.ArrayList;


public class AccountGenerator {

    private  int  numberOfAccount = 10;



    /**-----------------------------------------
     * set number of generated accounts
     * @param int numberOfAccount
    -----------------------------------------**/

    public int getNumberOfAccount() {
        return numberOfAccount;
    }

    public void setNumberOfAccount(int numberOfAccount){
        this.numberOfAccount = numberOfAccount;
    }


    /**-----------------------------------------
     * generate a series of random accounts
     * with address and private key
     * ArrayList would be for containing the info
     * tuple would be for each pair of account
     *
     *k;lk;lk
     * ------------
     * The getGeneratedAccounts is able to provide a list of accounts.
     * However, you would not be able to get balance.
     * These accounts may only contain private keys, public key and addresses
    -----------------------------------------**/

    public ArrayList<Account> getGeneratedAccounts(){

        ArrayList<Account> listOfAccount = new ArrayList<Account>();
        for (int i=0; i<numberOfAccount; i++) {
            Account itemAccount = new Account();
            itemAccount.init();
            listOfAccount.add(itemAccount);
        }
        return listOfAccount;

    }

}
