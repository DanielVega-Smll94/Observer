/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oscarblancarte.ipd.state.states;

import oscarblancarte.ipd.state.Server;

/**
 *
 * @author dvega
 */
public class StopSafeServerState extends AbstractServerState {

    public StopSafeServerState(Server server){
        server.getMessageProcess().stopSafe();
    }
        
    @Override
    public void handleMessage(Server server, String message) {
        System.out.println("The server is stopped safetly");
    }
    
}
