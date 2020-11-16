import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.math3.linear.*;

class F1 implements Callable<RealMatrix> {
    public RealMatrix call() throws Exception {
        RealMatrix mo = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1}
        });

        RealMatrix me = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1}
        });

        RealMatrix a = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1},
            new double[] {1, 1},
            new double[] {1, 1},
            new double[] {1, 1}
        });

        RealMatrix b = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1},
            new double[] {1, 1},
            new double[] {1, 1},
            new double[] {1, 1}
        });

        RealMatrix firstMove = mo.multiply(me);
        RealMatrix secondMove = firstMove.multiply(b);
        RealMatrix thirdMove = a.add(secondMove);
        
        return thirdMove;
    }
}

class F2 implements Callable<RealMatrix> {
    public RealMatrix call() throws Exception {
        RealMatrix mk = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1}
        });

        RealMatrix ml = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1}
        });

        RealMatrix mg = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1}
        });

        RealMatrix firstMove = mk.multiply(ml);
        RealMatrix secondMove = firstMove.multiply(mg);
        RealMatrix thirdMove = secondMove.subtract(mk);
        
        return thirdMove;
    }
}

class F3 implements Callable<RealMatrix> {
    public RealMatrix call() throws Exception {
        RealMatrix mp = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1}
        });

        RealMatrix mr = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1},
            new double[] {1, 1, 1, 1}
        });

        RealMatrix t = new Array2DRowRealMatrix(new double[][] {
            new double[] {1, 1},
            new double[] {1, 1},
            new double[] {1, 1},
            new double[] {1, 1}
        });

        RealMatrix firstMove = mp.multiply(mr);
        RealMatrix secondMove = firstMove.transpose();
        RealMatrix thirdMove = secondMove.multiply(t);
        
        return thirdMove;
    }
}
  
class Math {
    public static void main(String[] args) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(3);

            List<Callable<RealMatrix>> callables = new ArrayList<Callable<RealMatrix>>();
            
            F1 f1 = new F1();
            F2 f2 = new F2();
            F3 f3 = new F3();

            callables.add(f1);
            callables.add(f2);
            callables.add(f3);

            List<Future<RealMatrix>> futures = executor.invokeAll(callables);

            List<RealMatrix> results = new ArrayList<RealMatrix>();
            for (Future<RealMatrix> future : futures) results.add(future.get());

            RealMatrixFormat TABLE_FORMAT = new RealMatrixFormat("[", "", "", "]\n", "[", ", ");

            System.out.println("F1 (C):");
            System.out.println(TABLE_FORMAT.format(results.get(0)));

            System.out.println("F2 (MF):");
            System.out.println(TABLE_FORMAT.format(results.get(1)));

            System.out.println("F3 (O):");
            System.out.println(TABLE_FORMAT.format(results.get(2)));

            System.out.println("\nExit the script with ENTER");

            System.in.read();
            System.exit(0);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    } 
}