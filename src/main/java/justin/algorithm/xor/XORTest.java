package justin.algorithm.xor;


public class XORTest {

	public static void main(String[] args) {
		swap2NumberWithOutTempParameter(4,8);
		System.out.println();
		//XorEncodeString();
		XorEncodeString("PTC_User_JLuo_login_2019-11-21", 'p');


		//XorEncodeString(new Date().toString()+"123kasd");
	}
	
	
	
	/**
	 * 实现两个值的交换，而不必使用临时变量
	 *    0^0 = 0， 
	      1^0 = 1， 
	      0^1 = 1， 
	      1^1 = 0
	 */
	public static void swap2NumberWithOutTempParameter(int a, int b){
		System.out.println("未调换之前 a= "+a+" b= "+ b);
		
		a = a ^ b;   // a= 0100 ^ 1000 = 1100 > 12
		b = b ^ a;   // b= 1000 ^ 1100 = 0100 > 4 
		a = a ^ b;   // a= 1100 ^ 0100 = 1000 > 8 
		
		System.out.println("异或交换后 a= "+a+" b= "+ b);
	}
	
	/** 
	 * 字符加密
	 */
	public static void XorEncodeString(){
		char  a1='十' ,  a2='点' ,  a3='进' ,  a4='攻' ; 
		  char secret='a' ;
		  a1=(char) (a1^secret); 
		  a2=(char) (a2^secret); 
		  a3=(char) (a3^secret); 
		  a4=(char) (a4^secret); 
		  System.out.println("密文:"+a1+a2+a3+a4); 
		  a1=(char) (a1^secret); 
		  a2=(char) (a2^secret); 
		  a3=(char) (a3^secret); 
		  a4=(char) (a4^secret); 
		  System.out.println("原文:"+a1+a2+a3+a4); 
	}


	/**
	 * 字符加密
	 */
	public static void XorEncodeString(String token, char secret){

		char[] cArr = token.toCharArray();
		for(int i=0; i< cArr.length; i++){
			cArr[i] = (char) (cArr[i]^secret);
		}
		System.out.println("密文: "+String.valueOf(cArr));

		for(int i=0; i< cArr.length; i++){
			cArr[i] = (char) (cArr[i]^secret);
		}
		System.out.println("原文: "+String.valueOf(cArr));
	}
}
