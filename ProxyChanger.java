package proxychanger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProxyChanger {
    
    private int count  ;
    private int counter ;
    private String[] proxy ;
    private String[] port ;
    
    public ProxyChanger() {
        
        proxy = new String[80] ;
        port = new String[80] ;
        counter = -1 ;
        count = -1 ;
        
        try{
            FileReader f = new FileReader("proxy_port.txt") ;
            BufferedReader bf = new BufferedReader(f) ;
            String str ;
            
            while( (str = bf.readLine()) != null ){
                proxy[++count] = str.substring( 0, str.indexOf(" ") ).trim() ;
                port[count] = str.substring( str.indexOf(" ") ).trim() ;
            }
            
            f.close();
            bf.close();
            
        }catch( IOException e ){
            System.out.println( e );
        }
        
    }
    
    public void changeProxy(){
        
        if( counter >= count )
            counter = -1 ;
        
        System.setProperty("https.setProxy", proxy[++counter]) ;
        System.setProperty("https.setPort", port[counter] ) ;
        
    }
    
    public String whatIsMyProxy(){
        return proxy[counter] + " " + port[counter] ;
    }
    
    
}
