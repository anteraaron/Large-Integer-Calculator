
public class LargeIntegerDriver {

	/**
	 * Driver for the LargeInteger class
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LargeInteger term1 = new LargeInteger("-102465123");
		LargeInteger term2 = new LargeInteger("512569864239675");
		
		/*
		System.out.println("The Sum of\n" + term1.toString());
		System.out.println("and");
		System.out.println(term2.toString()+ "\n");
		LargeInteger sum = term1.add(term2); //add
		System.out.println("is " + sum.toString()); //prints the answer using toString()
		
		System.out.println("\n\nThe Difference of\n" + term1.toString());
		System.out.println("and");
		System.out.println(term2.toString()+ "\n");
		LargeInteger minus = term1.minus(term2);//subtract
		System.out.println("is " + minus.toString());//prints answer using toString()
		
		
		if(term1.equals(term2)==true){ //checks if equal using .equals(obj)
			System.out.println("They are equal");
		}*/
		LargeInteger product = term1.times(term2); //<-- here sir!!
		System.out.println(product.toString());

	}

}
