package oscarblancarte.ipd.templetemethod;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import oscarblancarte.ipd.templetemethod.impl.DrugstoreFileProcess;
import oscarblancarte.ipd.templetemethod.impl.GroceryFileProcess;
import oscarblancarte.ipd.templetemethod.impl.PaymentNew;

/**
 * @author Oscar Javier Blancarte Iturralde
 * @see http://www.oscarblancarteblog.com
 */
public class TempleteMethodMain extends TimerTask {
    
    private static final String[] PATHS = 
            
        new String[]{"src/files/drugstore", "src/files/grocery","src/files/payments"};
    private static final String LOG_DIR = "src/files/logs";
    private static final String PROCESS_DIR = "src/files/process";

    public static void main(String[] args) {
        new TempleteMethodMain().start();
    }

    public void start() {
        try {
            Timer timer = new Timer();
            timer.schedule(this, new Date(), (long) 1000 * 10);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("> Monitoring start");
        File f = new File(PATHS[0]);
        if(!f.exists()){
            throw new RuntimeException("El path '"+PATHS[0]+"' no existe");
        }
        File[] drugstoreFiles = f.listFiles();
        for (File file : drugstoreFiles) {
            try {
                System.out.println("> File found > " + file.getName());
                new DrugstoreFileProcess(file,LOG_DIR,PROCESS_DIR).execute();
                System.out.println("Processed file > " + file.getName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        f = new File(PATHS[1]);
        if(!f.exists()){
            throw new RuntimeException("El path '"+PATHS[1]+"' no existe");
        }
        System.out.println("> Read Path " + PATHS[1]);
        File[] groceryFiles = f.listFiles();
        for (File file : groceryFiles) {
            try {
                System.out.println("> File found > " + file.getName());
                new GroceryFileProcess(file,LOG_DIR,PROCESS_DIR).execute();
                System.out.println("Processed file > " + file.getName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(PATHS.length);
        System.out.println(PATHS[0]);
        System.out.println(PATHS[1]);

        f = new File(PATHS[2]);
        if(!f.exists()){
            throw new RuntimeException("El path '"+PATHS[2]+"' no existe");
        }
        System.out.println("> Read Path " + PATHS[2]);
        File[] payment = f.listFiles();
        for (File file : payment) {
            try {
                System.out.println("> File found > " + file.getName());
                new PaymentNew(file, LOG_DIR, PROCESS_DIR).execute();
                System.out.println("Processed file > " + file.getName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
    }
}