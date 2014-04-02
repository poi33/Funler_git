package utils;

public class PseudoRandom {
	long seed;
	
	long prime1 = 573731603;
	long prime2 = 1921249157; // less than 4503599627370496
	
	public PseudoRandom(long seed){
		this.seed = seed;
	}
	
	public double nextDouble(){
		seed = (seed * prime1) % prime2;
		return (double) seed;
	}

}
