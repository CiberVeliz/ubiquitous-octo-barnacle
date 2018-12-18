/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xmas.web.xmaserver;

import com.xmas.web.xmaserver.servidor.Config;
import com.xmas.web.xmaserver.servidor.Connection;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author pablunsky
 */
@Path("Animacion")
public class GenericResource 
{
    private static boolean encendido = false;
    private static boolean configurado = false;
    private static int port = 0;
    private static String ip = null;
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() 
    {
    }

    /**
     * Retrieves representation of an instance of com.xmas.web.xmaserver.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("UD")
    public Response getRecorridoUD() 
    {
        if(!encendido)
        {
            return Response.status(Response.Status.OK).entity("Dispositivo Apagado").build();
        }
        
        if(!configurado)
        {
            return Response.status(Response.Status.OK).entity("Host/Port sin configurar").build();
        }
        
        send("UD");
        
        return Response.status(Response.Status.OK).entity("Recorrido UD").build();
    }

    @GET
    @Path("DU")
    public Response getRecorridoDU() 
    {
        if(!encendido)
        {
            return Response.status(Response.Status.OK).entity("Dispositivo Apagado").build();
        }
        
        if(!configurado)
        {
            return Response.status(Response.Status.OK).entity("Host/Port sin configurar").build();
        }
        
        send("DU");
        
        return Response.status(Response.Status.OK).entity("Recorrido DU").build();
    }
    
    @GET
    @Path("LR")
    public Response getRecorridoLR() 
    {
        if(!encendido)
        {
            return Response.status(Response.Status.OK).entity("Dispositivo Apagado").build();
        }
        
        if(!configurado)
        {
            return Response.status(Response.Status.OK).entity("Host/Port sin configurar").build();
        }
        
        send("LR");
        
        return Response.status(Response.Status.OK).entity("Recorrido LR").build();
    }
    
    @GET
    @Path("RL")
    public Response getRecorridoRL() 
    {
        if(!encendido)
        {
            return Response.status(Response.Status.OK).entity("Dispositivo Apagado").build();
        }
        
        if(!configurado)
        {
            return Response.status(Response.Status.OK).entity("Host/Port sin configurar").build();
        }
        
        send("RL");
        
        return Response.status(Response.Status.OK).entity("Recorrido RL").build();
    }
    
    @GET
    @Path("ApagarLED")
    public Response apagarLED() 
    {
        if(!encendido)
        {
            return Response.status(Response.Status.OK).entity("Dispositivo Apagado").build();
        }
        
        if(!configurado)
        {
            return Response.status(Response.Status.OK).entity("Host/Port sin configurar").build();
        }
        
        send("ALL_LEDS_OFF");
        
        return Response.status(Response.Status.OK).entity("Se han apagado todos los LEDs").build();
    }
    
    @GET
    @Path("EncenderLED")
    public Response encenderLED() 
    {
        if(!encendido)
        {
            return Response.status(Response.Status.OK).entity("Dispositivo Apagado").build();
        }
        
        if(!configurado)
        {
            return Response.status(Response.Status.OK).entity("Host/Port sin configurar").build();
        }
        
        send("ALL_LEDS_ON");
        
        return Response.status(Response.Status.OK).entity("Se han encendido todos los LEDs").build();
    }
    @GET
    @Path("ApagarDispositivo")
    public Response apagarDispositivo() 
    {
        encendido = false;
        send("ALL_LEDS_OFF");
        
        return Response.status(Response.Status.OK).entity("Se ha apagado el dispositivo").build();
    }
    
    @GET
    @Path("EncenderDispositivo")
    public Response encenderDispositivo() 
    {
        encendido = true;
        
        return Response.status(Response.Status.OK).entity("Se ha encendido el dispositivo").build();
    }
    
    @GET
    @Path("Dibujar")
    public Response dibujar() 
    {
        if(!encendido)
        {
            return Response.status(Response.Status.OK).entity("Dispositivo Apagado").build();
        }
        
        if(!configurado)
        {
            return Response.status(Response.Status.OK).entity("Host/Port sin configurar").build();
        }
        
        send("TREE_NO_ANIM");
        
        return Response.status(Response.Status.OK).entity("Arbol Dibujado").build();
    }
   
    @POST
    @Path("Config")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setConfig(Config c)
    {
        if(c.getIp().split("\\.").length == 4 && c.getPort() > 0)
        {
            ip = c.getIp();
            port = c.getPort();
            configurado = true;
            return Response.status(Response.Status.OK).entity("Host y puerto configurados").build();
        }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Host y puerto mal formulados").build();
    }
    
    private void send(String msg)
    {
        Connection c = new Connection(ip, port);
        c.sendMsg(msg);
    }
    
    @POST
    @Path("Manual")
    public Response setCodigoManual(String code)
    {
        if(!code.isEmpty())
        {
            send(code);
            return Response.status(Response.Status.OK).entity("Codigo correcto").build();
        }
        
        return Response.status(Response.Status.OK).entity("Codigo Incorrecto").build();
    }
}
