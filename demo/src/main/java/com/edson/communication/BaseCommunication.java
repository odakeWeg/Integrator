package com.edson.communication;

import com.edson.exception.CommunicationException;

public interface BaseCommunication {
    public void startConnection() throws CommunicationException;
    public int readHoldingRegister(int register) throws CommunicationException;
    public int[] readHoldingRegisters(int startingAddress, int quantityOfRegisters) throws CommunicationException;
}
