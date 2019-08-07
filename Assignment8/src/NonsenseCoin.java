import java.util.ArrayList;
public class NonsenseCoin {
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	public static void main(String[] args) {
	//add our blocks to the BlockChain ArrayList:
		
		addBlock(new Block("first block", "0"));
		addBlock(new Block("second block",blockchain.get(blockchain.size()-1).hash));
		addBlock(new Block("third block",blockchain.get(blockchain.size()-1).hash));
		updateBlock(new Block("updated block",blockchain.get(blockchain.size()-2).hash), 2);
		
		
		
		String blockchainJson = StringHash.getJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		//System.out.println("\nBlockchain is Valid: " + isChainValid());
	}
	//Check If BlockChain is Valid Or Not
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through BlockChain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
	public static void updateBlock(Block newBlock,int index) {
		//newBlock.mineBlock(difficulty);
		String updatePrevioushash = blockchain.get(index -1).hash;
		blockchain.remove(index - 1);
		blockchain.add(index - 1,newBlock);
		blockchain.get(index - 1).hash = updatePrevioushash;
		blockchain.get(index - 1).previousHash = blockchain.get(index - 2).hash;
		blockchain.get(index).previousHash = blockchain.get(index - 1).hash;
		blockchain.get(index).calculateHash();
		
	}
}
