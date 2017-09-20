package co.edu.udea.estructuras.landresperez.citophoneapp.Model;

/**
 * Created by landres.perez on 18/09/17.
 */

public class BlockData {

    private String blockNumber;


    public BlockData() {
        // Void constructor required for calls to DataSnapshopt.
    }


    public BlockData(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }
}
