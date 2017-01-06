/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Servidor
 */
public class Transfer
{
    String sender;
    String reciver;
    int type;
    Intent intent;
    String message;
    private String espera;
    
    public String getEspera() {
		return espera;
	}

	public void setEspera(String espera) {
		this.espera = espera;
	}

	ArrayList<HashMap<String,String>> listMaps;

    public Transfer(String sender, String receiver, int type, Intent intent, String message)
    {
        this.sender = sender;
        this.reciver = receiver;
        this.type = type;
        this.intent = intent;
        this.message = message;
        this.listMaps = new ArrayList<HashMap<String,String>>();
    }
    
    public Transfer(String sender, String receiver,Intent intent)
    {
        this.sender = sender;
        this.reciver = receiver;
        this.intent = intent;
        this.message = message;
        this.listMaps = new ArrayList<HashMap<String,String>>();
    }
    
    public Transfer() {
        listMaps = new ArrayList<HashMap<String,String>>();
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public void setListMaps(ArrayList< HashMap<String,String>> map) {
        this.listMaps = map;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
    public String getSender() {
        return sender;
    }

    public String getReciver() {
        return reciver;
    }

    public int getType() {
        return type;
    }

    public Intent getIntent() {
        return intent;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<HashMap<String,String>> getListMaps() {
        return listMaps;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
   
    @Override
	public String toString() {
		return "Transfer [sender=" + sender + ", reciver=" + reciver
				+ ", type=" + type + ", intent=" + intent + ", message="
				+ message + ", listMaps=" + listMaps + "]";
	}

    /**
     * Efetura uma troca entre o destino e o rementent
     */
    public void redirect() 
    {
        String auxSender = this.sender;
        this.sender = this.reciver;
        this.reciver = auxSender;
    }

    public static enum Intent
    {
        VALIDATE,
        GET,
        SEND,
        REG,
        RESPONSE,
        CONNECT,
        DISCONNECT,
        SEND_SMS
    }
}
