import java.io.File;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        //System.out.println(p.evaluate(3));
        System.out.println("Test 1");
        double [] c1 = {6,0,0,5};
        int [] e1 = {0,1,2,3};
        Polynomial p1 = new Polynomial(c1, e1);
        
        System.out.println("Test 2");
        double [] c2 = {0,-2,0,0,-9};
        int [] e2 = {0,1,2,3,4};
        Polynomial p2 = new Polynomial(c2, e2);
        
        System.out.println("Test 3");
        double [] c3 = {0,-2,0,0,-9,-1,10};
        int [] e3 = {0,1,2,3,4,1,2};
        Polynomial p3 = new Polynomial(c3, e3);
      
        System.out.println("Test 4");
        Polynomial s = p1.add(p2);
        System.out.println("Test 5");
        Polynomial s2 = p2.add(p3);
        
        System.out.println("Test 6");
        double [] tc = {2,3,5};
        int [] te = {0,1,2};
        Polynomial tp = new Polynomial(tc, te);
        
        
        System.out.println("Test 7");
        double [] tc2 = {-2,-3,-5};
        int [] te2 = {0,1,2};
        Polynomial tp2 = new Polynomial(tc2, te2);
        
        System.out.println("Test 8");
        Polynomial ts = tp2.add(tp);
        
        System.out.println("Test 9");
        Polynomial ts2 = tp2.multiply(tp);
        
        System.out.println("Test 10");
        double [] tc3 = {1,-1};
        int [] te3 = {1,0};
        Polynomial tp3 = new Polynomial(tc3, te3);
        
        double [] tc4 = {1,1,1};
        int [] te4 = {2,1,0};
        Polynomial tp4 = new Polynomial(tc4, te4);
        Polynomial ts4 = tp4.multiply(tp3);
        
        System.out.println("Test 11");
        File input = new File("input.txt");
        File output = new File("output.txt");
        Polynomial tp5 = new Polynomial(input);

        System.out.println("Test 12");
        tp5.saveToFile(output);
        
        
        
        /*
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        */
    }
}