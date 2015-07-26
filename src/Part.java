// TODO: Auto-generated Javadoc
/**
 * The Class Part.
 * 
 * @author Ricky Wu
 */
public class Part {

	/** The product code. */
	private String productCode;

	/** The part description. */
	private String partDescript;

	/** The unit price. */
	private double unitPrice;

	/** The stock level. */
	private int stockLevel;

	/**
	 * The Constructor of the Part class.
	 *
	 * @param pc the product code
	 * @param pd the part description
	 * @param up the unit price
	 * @param sl the stock level
	 */
	public Part(String pc, String pd, double up, int sl) {
		productCode = pc;
		partDescript = pd;
		unitPrice = up;
		stockLevel = sl;
	}

	/**
	 * Gets the product code.
	 *
	 * @return the product code as a String
	 */
	public String getProdCode() {
		return this.productCode;
	}

	/**
	 * Gets the stock level.
	 *
	 * @return the stock level as an integer
	 */
	public int getStockLevel() {
		return this.stockLevel;
	}

	/**
	 * Make sale.
	 *
	 * @param quantity the quantity to sale
	 * @return the total price of amount of parts
	 * 	returns Double.NaN if stock level less than quantity
	 */
	public double makeSale(int quantity) {
		if (quantity > this.stockLevel) {
			return Double.NaN;
		}
		this.stockLevel -= quantity;
		return this.unitPrice * quantity;
	}

	/**
	 * Restock.
	 *
	 * @param quantity the quantity to restock
	 * 	increases stock level with quantity
	 */
	public void restock(int quantity) {
		this.stockLevel += quantity;
	}

	/* (non-Javadoc)
	 * Returns String in following format
	 * 
	 * ASR9290XOC    Asus 4GB R9 290X OC                           455.00          11
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("%-14s%-40s%12.2f%12d", 
				productCode, partDescript,unitPrice, stockLevel);
	}
}
