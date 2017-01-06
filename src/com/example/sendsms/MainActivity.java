package com.example.sendsms;

import socket.listener.ClienteService;
import socket.listener.OnReciveTranfer;
import socket.modelo.Transfer;
import socket.modelo.Transfer.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements OnClickListener,OnReciveTranfer {

	private Button button;
	private EditText ipServidor;
	private ClienteService clienteService;
	private TextView showSend;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
       
    }	
    
    private void init()
    {
    	clienteService = ClienteService.findCurrentConnection();
    	clienteService.setOnTransitActivity(this, this);
    	this.showSend = (TextView) findViewById(R.id.showSend);
    	ipServidor = (EditText) findViewById(R.id.ipServidor);
    	 button = (Button) findViewById(R.id.botao);
         
         button.setOnClickListener(this);
         
    }

	@Override
	public void onClick(View clique)
	{
		clienteService.setHost(this.ipServidor.getText().toString());
	}


	private void sendSms(String contacto, String message) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(contacto, null, message, null, null);
			String texto = showSend.getText().toString();
			texto = texto+"Fiscalização enviada ->"+contacto+"\n\n";
			showSend.setText(texto);
		  } catch (Exception e) {
			  String texto = showSend.getText().toString();
			  texto = texto+"Falha ao enviar ->"+contacto+"\n\n";
				showSend.setText(texto);
			
			Log.e("APP","error send sms message "+e.getMessage(), e);
		  }
	}


	@Override
	public void onProcessTranfer(Transfer transfer) 
	{
		if(transfer.getIntent() == Intent.SEND_SMS)
		{
			String matricula = transfer.getListMaps().get(0).get("MATRICULA");
			String infracoes = transfer.getListMaps().get(0).get("COUNT.INFRACAO");
			String multa = transfer.getListMaps().get(0).get("MULTA");
			String local = transfer.getListMaps().get(0).get("LOCAL");
			String codFiscalizacao = transfer.getListMaps().get(0).get("COD.FISCALIZACACO");
			String contacto = transfer.getListMaps().get(0).get("CONTACTO");
			
		    local = (local != null && !local.equals("null")) ? local : "";
			String message = "Salamon "+
			 codFiscalizacao+" - "+
			"VEICULO "+matricula+" - "+
			"MULTA "+multa.replace(" ", ".")+
			 local;
			
			if(message.length()>124)
				message = message.substring(0, 118)+"...";
		
			sendSms(contacto, message);		
		}
		
	}


	@Override
	public void onPosConnected() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onConnectLost() {
		// TODO Auto-generated method stub
		
	}
    
}
