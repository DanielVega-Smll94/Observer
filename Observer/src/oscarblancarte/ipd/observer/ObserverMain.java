package oscarblancarte.ipd.observer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.Scanner;
import oscarblancarte.ipd.observer.impl.ConfigurationManager;
import oscarblancarte.ipd.observer.impl.observers.DateFormatObserver;
import oscarblancarte.ipd.observer.impl.observers.MoneyFormatObserver;
import oscarblancarte.ipd.observer.impl.observers.MoneyObserverClass;

/**
 * @author Oscar Javier Blancarte Iturralde
 * @see http://www.oscarblancarteblog.com
 */
public class ObserverMain {

    public static void main(String[] args) {       
        ConfigurationManager conf = ConfigurationManager.getInstance();
        
        //Se establecen los valores por default.
        conf.setDefaultDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
        conf.setMoneyFormat(new DecimalFormat("##.00"));
        System.out.println("Established configuration");
        
        //Se dan de alta lo observers
        DateFormatObserver dateFormatObserver = new DateFormatObserver();
        MoneyFormatObserver moneyFormatObserver = new MoneyFormatObserver();
        MoneyObserverClass moneySymbolObserver = new MoneyObserverClass();

        conf.addObserver(dateFormatObserver);
        conf.addObserver(moneyFormatObserver);
        conf.addObserver(moneySymbolObserver);
        System.out.println("");
        
        System.out.println("Ingrese número 1 o 2:\n"
                + "1. Dolar \n"
                + "2. Euro");
        Scanner yourVariable = new Scanner(System.in);
        String name;
        System.out.println("Ingrese número: ");
        name = yourVariable.nextLine();
        switch (name) {
            case "1":
            //Se cambia la fonfiguratión
            conf.setDefaultDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
            conf.setMoneyFormat(new DecimalFormat("###,#00.00"));
            conf.setSimbolMoney(new DecimalFormatSymbols(Locale.US));
            System.out.println("");
                break;
            case "2":              
            //Se realiza otro cambio en la configuración.
            conf.setDefaultDateFormat(new SimpleDateFormat("MM/yyyy/dd"));
            conf.setMoneyFormat(new DecimalFormat("###,#00"));
            conf.setSimbolMoney(new DecimalFormatSymbols(Locale.UK));
                break;
            default:
            System.out.println("Selección invalida!");
            break;
        }

        conf.removeObserver(dateFormatObserver);
        conf.removeObserver(moneyFormatObserver);
        conf.removeObserver(moneySymbolObserver);
        System.out.println("");
        
        //Se realiza otro cambio en la configuración.
        conf.setDefaultDateFormat(new SimpleDateFormat("MM/yyyy"));
        conf.setMoneyFormat(new DecimalFormat("###,##0.00"));
    }
}