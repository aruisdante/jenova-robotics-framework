package jenova.hardwareabstraction;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import jenova.console.JenovaError;

/**
 * Interface for defining a simple hardware abstraction layer. Provides two methods: sendMessageTo(), which sends a message to the hardware,
 * and getMessageFromHarware(), which gets a message back from the hardware
 * @author Adam Panzica
 *
 * @param <T> Type of the message to be sent to the hardware
 * @param <F> Type of the message to be received from the hardware
 */
public abstract class JenovaSimpleHardwareAbstractionLayer <T,F>{
	private Queue<T> transmit;
	private Queue<F> receive;
	
	protected JenovaSimpleHardwareAbstractionLayer(int size){
		this.transmit = new ArrayBlockingQueue<T>(size);
		this.receive = new ArrayBlockingQueue<F>(size);
	}
	
	/**
	 * Adds a message to the hardware's transmit queue. Returns false if there was no more space in the queue
	 * @param message Message to be added to the transmit queue
	 * @return True if the message was sucessfully added, else false
	 */
	public boolean enqueueMessageToHardware(T message){
		return this.transmit.add(message);
	}
	
	/**
	 * Reads the next message from the hardware's receive queue
	 * @return The next message on the receive queue
	 */
	public F dequeueMessageFromHardware(){
		return receive.poll();
	}
	
	/**
	 * Sends a message to the hardware
	 * @param message Message to be sent to the hardware
	 * @throws JenovaError If the message was not successfully transmitted, throws an error explaining why
	 */
	protected abstract void sendMessageToHardware(T message) throws JenovaError;
	
	/**
	 * Sends a message to the hardware. Throws a JenovaError if message sending fails. Should be used where failure is not expected
	 * Should be overridden in sub-classes with implementation method for communicating a message to the hardware.
	 * @param message Message to send to the hardware
	 * @throws JenovaError explaining why the message was not successfully sent
	 */
	public void sendNextMessageToHardware() throws JenovaError{
		T message = this.transmit.poll();
		if(message!= null)sendMessageToHardware(message);
		else throw new JenovaError("No more messages to transmit to hardware");
	}
	
	/**
	 * Sends a message to the hardware. To be used where failure is expected behavior.
	 * By default, simply calls sendMessageToHardware and returns false if an exception is thrown.
	 * @param message Message to be sent to the hardware
	 * @return True if the message was successfully sent, else false
	 */
	public boolean sendNextMessageToHardwareFT(){
		try {sendNextMessageToHardware();}
		catch(JenovaError e){ return false;}
		return true;
	}
	
	/**
	 * Gets a message from the hardware. Throws a JenovaError if there was a problem receiving the message
	 * Should be overridden in sub classes with implementation method for receiving a message from the hardware
	 * @return The message received from the hardware
	 * @throws JenovaError explaining why a message was not successfully received
	 */
	public F getNextMessageFormHardware() throws JenovaError{
		F message = this.receive.poll();
		if(message != null) return message;
		else throw new JenovaError("No messages left on the queue");
	}
}
