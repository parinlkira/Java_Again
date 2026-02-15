package assignments;



class MaxOf2Nums {
    int a, b;  // make variables private for better encapsulation

    
    MaxOf2Nums(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int max() {
        return (a > b ? a : b);
    }
}

class Arith extends MaxOf2Nums{

	Arith(int a, int b) {
		super(a, b);
	}
	
	int add() {
		int sum = a+b;
		
		return sum;
	}
	
	int sub() {
		int diff = a - b;
		return diff;
	}
	
    int mul() {
    	int prod = a * b;
    	return prod;
    }

    int div() {
    	int div = a / b;
    	return div;
    }
}


class Functions{

    public int arraySum(int[] arr){
        int s = 0;

        for(int i : arr) {
            s = s + i;
        }
        return s;
    }

 public static void main(String[] args) {
      
    Functions f = new Functions();
    int[] a = {1,3,4,5,6};
    int sum = f.arraySum(a);
    System.out.println("Sum is : " + sum);


    MaxOf2Nums mx = new MaxOf2Nums(4,7);
    
    int max = mx.max();
    System.out.println("Max is : " + max);

    Arith ar = new Arith(46,22);
    System.out.println("Addition is : " + ar.add());
    System.out.println("Subtraction is : " + ar.sub());
    System.out.println("Multiplication is : " + ar.mul());
    System.out.println("Division is : " + ar.div());
    }   
}
