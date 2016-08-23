import java.util.ArrayList;
import java.util.Collections;


/**
 * With Multiplication Method
 * Class that handles very large integer values which will not even fit into long. 
 * 
 * @author Anter Aaron M. Custodio(Student#:2011-42733) && Carl Zachery S. Viernes(Student#:2011-29912)
 *B.S Comsci
 *UP Manila
 *ComSci 123 - Data Structures
 */
public class LargeInteger {
	//Declare the variables
	LargeInteger bigInteger;
	LargeInteger RLink;
	LargeInteger LLink;
	int data;
	String term;
	
	/**
	 * create constructor. Left and right node to null and data to 0
	 */
	public LargeInteger() {
		RLink = null;
		LLink = null;
		data = 0;
	}
	/**
	 * Insert node to tail
	 * @param L the LinkedList in which the value will be inserted
	 * @param term the value to be inserted in the node
	 * @return return the list head node
	 */
	
	public LargeInteger insertTail(LargeInteger L, int term){
		LargeInteger tau = new LargeInteger();
		L.data = term;
		L.LLink.RLink = tau;
		tau.RLink = L;
		tau.LLink = L.LLink;
		L.LLink	= tau;
		return tau;
	}
	
	/**
	 * Constructs list head
	 * @param L the node to be constructed
	 * @return returns the newly constructed list head node
	 */
	public LargeInteger zeroInteger(LargeInteger L){
		LargeInteger tau = new LargeInteger();
		L = tau;
		tau.data = 0;
		tau.LLink = tau;
		tau.RLink = tau;
		return tau;

	}
	/**
	 * Converting the inputed String value to integer and inserted to separate nodes
	 * @param val the inputed string value which will be inserted to the nodes
	 * @return returns the list head in which its data is the nTerm value
	 */
	public LargeInteger readLongInteger(String val){
		
		int nTerms = 0;
		int radix = 4;
		int sign = 1;
		LargeInteger L = new LargeInteger();
		L = zeroInteger(L);
		
		if(val.charAt(0)=='-'){
			sign = -1;
			val = val.replace("-","");
		}
		
		
		int i = 0;
		if(val.length()%radix!=0){ //if the value is not divisible by the radix. inserting the string with the length of modulo, this will make the value divisible by 4
			i = val.length()%radix;
			int term = Integer.parseInt(val.substring(0,i));
			L = insertTail(L, term);
			nTerms = nTerms + 1;
		}
		for(i=val.length()%radix;i<val.length();i+=radix){ //insert the remaining value
			int term = Integer.parseInt(val.substring(i,i+radix));
			L = insertTail(L, term);
			nTerms = nTerms + 1;
		}
		
		L.data = sign * nTerms; //stores the data to the list head
		return L;
	}
	
	/**
	 * Creates a LargeInteger object with its value set to the parameter.
	 *
	 * @param val initial value of the LargeInteger object
	 */
	public LargeInteger(String val) {
		//TODO: Create the constructor
		RLink = null;
		LLink = null;	
		this.bigInteger = readLongInteger(val); //constructs this bigInteger's converted value
		this.term = val;
	}
	
	/**
	 * Find temporary working sign
	 * @param A this Big Integer's value
	 * @param B other's big integer's value
	 * @return true if m>n or m==n & this big integer > other big integer data
	 */
	public static boolean isGT(LargeInteger A, LargeInteger B){
	
		int m = Math.abs(A.data);
		int n = Math.abs(B.data);
		if(m>n){
			return true;
		}else if(m<n){
			return false;
		}else if(m==n){
			LargeInteger alpha = A.LLink;
			LargeInteger beta = B.LLink; //interchanged
			
			while(alpha!=A){
				if(alpha.data>beta.data){
					return true;
				}else if(alpha.data<beta.data){
					return false;
				}else if(alpha.data == beta.data){
					alpha = alpha.LLink;
					beta = beta.LLink;
				}
				
			}
			return false;
		}
		return false;
	}
	/**
	 * Delete the leading zeros of the values
	 * @param L LargeInteger object in which the zeros will be deleted
	 * @param nTerms the number of terms in the list. Acts as a counter
	 * @return returns the LargeInteger object with no leading zero
	 */
	
	public LargeInteger deleteLeadingZero(LargeInteger L, int nTerms){
		
		LargeInteger alpha = L.RLink;
		while(alpha!=L){
			if(alpha.data==0){
				L.RLink = alpha.RLink;
				alpha.RLink.LLink = L;
				nTerms = nTerms - 1;
				alpha = L.RLink;
			}else{
	
				return L;			
			}
				
		}
		
		return L;
	}
	/**
	 * Inserts the value to the front
	 * @param L the LargeInteger object in which the value will be inserted
	 * @param term the value which will be inserted in the list L
	 * @return returns the list head
	 */
	public LargeInteger insertHead(LargeInteger L, int term){ //check this

		LargeInteger tau = new LargeInteger();
		tau.data = term;
		tau.RLink = L.RLink;
		tau.LLink = L;
		L.RLink.LLink = tau;
		L.RLink = tau;
		return L;
		
	}
	/**
	 * Reverses the linked list. this is needed in printing toString() because the Sum and Difference has reversed order in the LinkedList
	 * @param head list head of the LinkedList
	 * @return returns the reverse linked list
	 */
	public static LargeInteger reverse(LargeInteger head){
		
		LargeInteger curr = head;
		LargeInteger tmp = new LargeInteger();
		
	    do
	    {
	        tmp = curr.LLink;
	        curr.LLink = curr.RLink;
	        curr.RLink = tmp;
	        curr = tmp;
	    } while(curr != head);
			
	    return curr;
	}
	
	
	/**
	 * Adds the value of this BigInteger object to the other BigInteger object
	 * and returns the sum
	 *   
	 * @param other Other BigInteger object that is to be added to this BigInteger object
	 * @return Sum of this BigInteger with the other BigInteger
	 */
	public LargeInteger add(LargeInteger other) {
		//TODO: Create the add method for adding BigIntegers
		LargeInteger Sum = new LargeInteger();
		Sum = zeroInteger(Sum);
		int sA = 0;
		int sB = 0;
		
		if(isGT(this.bigInteger, other.bigInteger)){
			sA = 1;
			if(this.bigInteger.data*other.bigInteger.data > 0){
				sB = 1;
			}else{
				sB = -1;
			}
		}else{
			sB = 1;
	
			if(this.bigInteger.data * other.bigInteger.data > 0){
				sA = 1;
			}else{
				sA = -1;
			}
		}
		int sign = 0;
		if((this.bigInteger.data > 0 && other.bigInteger.data > 0)||(isGT(this.bigInteger, other.bigInteger)&& this.bigInteger.data > 0)||(isGT(other.bigInteger, this.bigInteger)&& other.bigInteger.data > 0)){
			sign = 1; //check this
		}else{
			sign = -1;
		}
		
		Sum = zeroInteger(Sum); //create list head for sum
		int k = 0; //number of terms in sum
		int c = 0; //carry
		int t = 0;
		int r = 10000; //radix
		LargeInteger alpha = this.bigInteger.RLink; //least significant in term this
		LargeInteger beta = other.bigInteger.RLink;//least significant in term other
		//interchanged
		while(true){
			if(alpha!=this.bigInteger && beta!=other.bigInteger){
				t = (sA*alpha.data) + (sB*beta.data) + c;
				//System.out.println(sA*alpha.data + " " + sB* beta.data + " " + t + " " + c);
				alpha =alpha.RLink;
				beta = beta.RLink;
			}else if (alpha!=this.bigInteger && beta == other.bigInteger){
				t = (sA * alpha.data) + c;
				alpha = alpha.RLink;
			}else if (alpha == this.bigInteger && beta != other.bigInteger){
				t = (sB * beta.data) + c;
				beta = beta.RLink;
			}else if(alpha == this.bigInteger && beta == other.bigInteger){
				if(c!=0){
					Sum = insertHead(Sum,c);//here
					k = k + 1;
				}else{
					Sum = deleteLeadingZero(Sum,k);
				}
				Sum.data = sign * k;
				Sum.bigInteger = reverse(Sum);
				return Sum;
			}
			int term = t % r;
			if(term<0){
				term = (r + term) % r;
			}
			float a = t; //assign float to read decimal places then type cast to int
			float b = r;
			c = (int)Math.floor(a/b);	
			Sum = insertHead(Sum, term);
			k = k+1;
		}
	
		
	}
	
	/**
	 * Minus the value of the other BigInteger object from this BigInteger object
	 * and returns the difference
	 *   
	 * @param other Other BigInteger object that is to be subtracted to this BigInteger object
	 * @return Difference of this BigInteger with the other BigInteger
	 */
	public LargeInteger minus(LargeInteger other) {
		//TODO: Create the minus method for adding BigIntegers
		other.bigInteger.data = other.bigInteger.data * -1; //multiply by -1 to the other bigInteger to make the addition method a subtraction
		LargeInteger Difference = new LargeInteger();
		Difference = zeroInteger(Difference);
		int sA = 0;
		int sB = 0;

		if(isGT(this.bigInteger, other.bigInteger)){
			sA = 1;
			if(this.bigInteger.data*other.bigInteger.data > 0){
				sB = 1;
			}else{
				sB = -1;
			}
		}else{
			sB = 1;
	
			if(this.bigInteger.data * other.bigInteger.data > 0){
				sA = 1;
			}else{
				sA = -1;
			}
		}
		int sign = 0;
		if((this.bigInteger.data > 0 && other.bigInteger.data > 0)||(isGT(this.bigInteger, other.bigInteger)&& this.bigInteger.data > 0)||(isGT(other.bigInteger, this.bigInteger)&& other.bigInteger.data > 0)){
			sign = 1; 
		}else{
			sign = -1;
		}
		
		int k = 0; //number of terms in sum
		int c = 0; //carry
		int t = 0;
		int r = 10000; //radix
		LargeInteger alpha = this.bigInteger.RLink; //least significant in term this
		LargeInteger beta = other.bigInteger.RLink;//least significant in term other
		//interchanged
		while(true){
			if(alpha!=this.bigInteger && beta!=other.bigInteger){
				t = (sA*alpha.data) + (sB*beta.data) + c;
				//System.out.println(sA*alpha.data + " " + sB* beta.data + " " + t + " " + c);
				alpha =alpha.RLink;
				beta = beta.RLink;
			}else if (alpha!=this.bigInteger && beta == other.bigInteger){
				t = (sA * alpha.data) + c;
				alpha = alpha.RLink;
			}else if (alpha == this.bigInteger && beta != other.bigInteger){
				t = (sB * beta.data) + c;
				beta = beta.RLink;
			}else if(alpha == this.bigInteger && beta == other.bigInteger){
				if(c!=0){
					Difference = insertHead(Difference,c);//here
					k = k + 1;
				}else{
					Difference = deleteLeadingZero(Difference,k);
				}
				Difference.data = sign * k;
				Difference.bigInteger = reverse(Difference);
				return Difference;
			}
			int term = t % r;
			if(term<0){
				term = (r + term) % r;
			}
			float a = t;
			float b = r;
			c = (int)Math.floor(a/b);		
			Difference = insertHead(Difference, term);
			k = k+1;
		}
	
		
		
	}
	
	/**
	 * Multiplies the value of this BigInteger to object to the other BigInteger object
	 * and returns the product
	 *   
	 * @param other Other BigInteger object that is to be multiplied to this BigInteger object
	 * @return Product of this BigInteger with the other BigInteger
	 */
	public LargeInteger times(LargeInteger other) {
		LargeInteger product = new LargeInteger();
		product = zeroInteger(product);
		int signA = 1;
		int signB = 1;
		int overAllSign = 1;
		int z = 0;
		int radix = 4;
		int k, t, c;
		ArrayList<Integer> a = new ArrayList<Integer>();//this term
		ArrayList<Integer>b = new ArrayList<Integer>();//other term
		ArrayList<Integer> p = new ArrayList<Integer>();
		
		if(this.term.charAt(0)=='-'){
			this.term = this.term.replace("-", "");
			signA = -1;
		}
		if(other.term.charAt(0)=='-'){
			other.term = other.term.replace("-", "");
			signB = -1;
		}
		
		if(this.term.length()%radix!=0){ //if the value is not divisible by the radix. inserting the string with the length of modulo, this will make the value divisible by 4
			z =this.term.length()%radix;
			int value = Integer.parseInt(this.term.substring(0,z));
			a.add(value);
		}
		for(z=this.term.length()%radix;z<this.term.length();z+=radix){ //insert the remaining value
			int value = Integer.parseInt(this.term.substring(z,z+radix));
			a.add(value);
		}
		
		if(other.term.length()%radix!=0){ //if the value is not divisible by the radix. inserting the string with the length of modulo, this will make the value divisible by 4
			z=other.term.length()%radix;
			int value = Integer.parseInt(other.term.substring(0,z));
			b.add(value);
		}
		for(z=other.term.length()%radix;z<other.term.length();z+=radix){ //insert the remaining value
			int value = Integer.parseInt(other.term.substring(z,z+radix));
			b.add(value);
		}
		Collections.reverse(a);
		Collections.reverse(b);
		k = a.size() + b.size();
	
		for(int j = 0; j<k;j++){//assign to p
			p.add(0);
		}
	
		
		for(int i = 0; i<a.size(); i++){
			c = 0;
			for(int j = 0; j<b.size(); j++){
				t = a.get(i)*b.get(j)+p.get(i+j)+c;
				p.set(i+j,t%10000);
				double temp = Math.floor(t/10000);
				c = (int)temp;
			}
			p.set(i+b.size(), c);
		}
		
		for(int i = 0; i<p.size();i++){
			if(p.get(i)==0){ //remove trailing zero
				p.remove(i);
			}
		}
		
		overAllSign = (signA * signB)*p.size();
		
		product.data = overAllSign;
		for(int i = 0; i<p.size();i++){
			product = insertHead(product, p.get(i));
		}

		product.bigInteger = zeroInteger(product.bigInteger);
		product.bigInteger.data = overAllSign;
		Collections.reverse(p);
		for(int i = 0; i<p.size();i++){
			product.bigInteger = insertHead(product.bigInteger, p.get(i));
		}

		return product;
	}	
	
	/**
	 * Compares this LargeInteger object with the specified object for equality
	 * @param x Object to which LargeInteger is to be compared
	 * @return true if and only if the specified Object is a LargeInteger object whose value
	 * 		   is numerically equal to this LargeInteger
	 */
	@Override	
	public boolean equals(Object x) {
		//TODO: Create the equals method for comparing the equality of this object with other object
		String a = x.toString();
		String b = this.toString();
		//gets the data in between {} because this will give the degree of the value
		a = a.substring(a.indexOf("{") + 1);
		a = a.substring(0,a.indexOf("}")); 
		
		b = b.substring(b.indexOf("{") + 1);
		b = b.substring(0,b.indexOf("}"));
		//if the degree are equal, get their most significant value
		if(Integer.parseInt(a)==Integer.parseInt(b)){
			
			a = x.toString().replaceAll(" ", "");
			a = a.substring(0,a.indexOf("x"));
			
			b = this.toString().replaceAll(" ", "");
			b = b.substring(0,b.indexOf("x"));
			//if they are still equal
			if(Integer.parseInt(a)==Integer.parseInt(b)){
				return true;
			}else if(Integer.parseInt(a)>Integer.parseInt(b)){
				return false;
			}else if(Integer.parseInt(a)<Integer.parseInt(b)){
				return false;
			}
			
		}else if(Integer.parseInt(a)>Integer.parseInt(b)){
			return false;
		}else if(Integer.parseInt(a)<Integer.parseInt(b)){
			return false;
		}
		
		return false;
	}
	
	/**
	 * Returns the string decimal representation of this LargeInteger
	 * @return String "represent" decimal representation of this LargeInteger
	 */
	@Override
	public String toString() {
		//TODO: Create the toString method for getting the decimal representation
		String represent = "";
		int i = 1;
		LargeInteger alpha =this.bigInteger.LLink;
		int sign = 0;

		while(alpha!=this.bigInteger){
			if(alpha.RLink.data<0){ //if the value to be printed is negative
				sign = -1;
			}
			StringBuilder builder = new StringBuilder(alpha.data);
			builder.append(alpha.data);
			if(sign>=0)
				represent += " " + builder.toString() + " x 10000^{" + (this.bigInteger.data - i )+"} +";
			else // if the sign < 0, which means it is negative, this is the method of printing
				represent += "- " + builder.toString() + " x 10000^{" + Math.abs(this.bigInteger.data + i )+"} ";
				
			alpha = alpha.LLink;
			i++;
		}
		
		if(represent.length()==0){
			represent = "0";
		}
		else{
		represent = represent.substring(0,represent.length()-1); //save in the represent String and then return
		return represent;
		}
		return represent;
	}
}