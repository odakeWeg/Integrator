package com.edson.communication;

import com.edson.exception.CommunicationException;

public interface BaseCommunication {
    public void startConnection() throws CommunicationException;
    public int readSingleRegister(int register) throws CommunicationException;
    public int[] readMultipleRegisters(int startingAddress, int quantityOfRegisters) throws CommunicationException;
    public void writeSingleRegister(int registerAddress, int registerValue) throws CommunicationException;
    public void writeMultipleRegister(int initialRegister, int[] registersValue) throws CommunicationException;
}
