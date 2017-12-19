package org.nominis;

import org.ethereum.core.Transaction;
import org.ethereum.core.Account;
import org.ethereum.core.Block;
import org.ethereum.crypto.ECKey;
import org.ethereum.facade.Ethereum;
import org.ethereum.util.ByteUtil;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class txGenerator {

    public final List<Account> accounts;
    public final Block genesis;

    public transactionGenerator(List<Account> accs,Block gen){

        this.accounts = accs;
        this.genesis = gen;
    }

    private byte[] sendTx(byte[] senderPrivateKey, byte[] receiveAddress, byte[] data, Ethereum senderNode) throws InterruptedException {
        byte[] fromAddress = ECKey.fromPrivate(senderPrivateKey).getAddress();

        BigInteger nonce = senderNode.getRepository().getNonce(fromAddress);
        Integer chainId = senderNode.getChainIdForNextBlock();

        Transaction tx = new Transaction(
                ByteUtil.bigIntegerToBytes(nonce),
                ByteUtil.longToBytesNoLeadZeroes(senderNode.getGasPrice()),
                ByteUtil.longToBytesNoLeadZeroes(200000),
                receiveAddress,
                ByteUtil.bigIntegerToBytes(BigInteger.valueOf(1)),  // 1 gwei
                data,
                chainId);

        tx.sign(ECKey.fromPrivate(senderPrivateKey));
        System.out.println("Raw transaction: 0x{}", Hex.encodeHexString(tx.getEncodedRaw()));

        new Transaction(tx.getEncodedRaw()).verify(); // <-- this throws an exception

        senderNode.submitTransaction(tx);
        logger.info("<=== Sending transaction: " + tx);

        return tx.getHash();
    }

    public List<Transaction> generateRandomTx(){



    }

    public List<Transcation> generateValidTx(){

    }


}
