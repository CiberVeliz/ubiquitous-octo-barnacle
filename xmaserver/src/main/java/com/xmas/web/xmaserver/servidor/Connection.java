/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xmas.web.xmaserver.servidor;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author pablunsky
 */
public class Connection 
{
    private String ip;
    private int port;
    
    public Connection(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }
    
    public void sendMsg(String msg)
    {
        try 
        {
            Socket s = new Socket(ip, port);
            
            OutputStream osw = new BufferedOutputStream(s.getOutputStream());
            BufferedWriter b = new BufferedWriter(new OutputStreamWriter(osw, "UTF-8"));
            b.write(msg);
            b.flush();
            b.close();
            osw.close();
            
            s.close();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
}
