/*******************************************************************************
 * Copyright (c) 2008, 2014 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution. 
 *
 * The Eclipse Public License is available at 
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *    Ian Craggs - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.paho.mqttsn.gateway.messages.mqtt;


/**
 * This object represents a Mqtt PUBCOMP message.
 * 
 *
 */
public class MqttPubComp extends MqttMessage{

	//Mqtt PUBCOMP fields
	private int msgId;

	/**
	 * MqttPubcomp constructor.Sets the appropriate message type. 
	 */
	public MqttPubComp(int msgId) {
		msgType = MqttMessage.PUBCOMP;
		this.msgId = msgId;
	}

	/**
	 * MqttPubcomp constructor.Sets the appropriate message type and constructs
	 * a Mqtt PUBCOMP message from a received byte array.
	 * @param data: The buffer that contains the PUBCOMP message.
	 */
	public MqttPubComp(byte[] data) {
		msgType = MqttMessage.PUBCOMP;
		msgId = ((data[2] & 0xFF) << 8) + (data[3] & 0xFF);
	}

	/**
	 * Method to convert this message to a byte array for transmission.
	 * @return A byte array containing the PUBCOMP message as it should appear on the wire.
	 */
	public byte[] toBytes() {
		int length = 4;
		byte[] data = new byte[length];
		data [0] = (byte)((msgType << 4) & 0xF0);
		data [1] = (byte)0x02;//add Remaining length fields
		data [2] = (byte)((msgId >> 8) & 0xFF);
		data [3] = (byte) (msgId & 0xFF);			
		return data;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
}