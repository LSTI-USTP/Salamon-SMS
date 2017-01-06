package socket.listener;

import socket.modelo.Transfer;


public interface OnReciveTranfer 
{
	public void onProcessTranfer(Transfer transfer);

	public void onPosConnected();
	
	public void onConnectLost();
}
