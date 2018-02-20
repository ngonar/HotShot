/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotshot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author root
 */
public class HotShot {

    private final static String IP_SERVER = "localhost";
    private final static Integer PORT_SERVER = 2300;
    
    public static void main(String[] args) {
        try {
            tesKoneksi001();
            tesKoneksi002();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void tesKoneksi001() throws UnknownHostException, IOException {
        Socket clientSocket = new Socket(IP_SERVER, PORT_SERVER);
       
        String networkRequest = "2800001000008181000020171121122213074567898301SYMPHONI        0070000000";
        
        PrintWriter outgoing = new PrintWriter(clientSocket.getOutputStream());
        InputStreamReader incoming = new InputStreamReader(clientSocket.getInputStream());
       
        String trailer = new String(new char[] {10}); 
       
        //kirim
        outgoing.print(networkRequest+trailer);
        outgoing.flush();
        
        int data;
        StringBuffer sb = new StringBuffer();
       
        while((data = incoming.read()) != 10 ) {
            if (data==-1 || data==255 || data==65533)
                break;

            sb.append((char) data);
        }

        System.out.println("Rec. Msg ["+sb.toString()+"] len ["+sb.toString().length()+"]");

        outgoing.close();
        incoming.close();
        clientSocket.close();

    }
    
    public static void tesKoneksi002() throws UnknownHostException, IOException {
        Socket clientSocket = new Socket(IP_SERVER, PORT_SERVER);
       
        String networkRequest = "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n"+
                                    "210040300041808100000599501001806805977201505121114106012074510017074511014LktStressTest0010190000000411110006082\r\n";
        
        
                                    
        DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream is = new DataInputStream(clientSocket.getInputStream());
       
        String trailer = new String(new char[] {10});//new String(new char[] {10});//"\n"; //
        //kirim
        os.writeBytes(networkRequest + trailer );
        
        System.out.println("KIRIMAN : "+networkRequest);
        //terima
        int responseLine;
        StringBuffer sb = new StringBuffer();
        while ((responseLine = is.read()) != 10 ) {
            //System.out.println(">> "+responseLine);
            sb.append((char) responseLine);
            //System.out.println("Msg ["+sb.toString()+"] len ["+sb.toString().length()+"]");
        }
       
        System.out.println("Rec. Msg ["+sb.toString()+"] len ["+sb.toString().length()+"]");
       
        os.close();
        is.close();
        clientSocket.close();

    }
    
}
