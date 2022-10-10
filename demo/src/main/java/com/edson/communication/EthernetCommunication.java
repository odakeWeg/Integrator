package com.edson.communication;

import java.io.IOException;

import com.edson.exception.CommunicationException;
import com.edson.util.ViewConfigurationPathUtil;

import net.weg.wcomm.modbus.NegativeConfirmationException;
import net.weg.wcomm.modbus.Register;
import net.weg.wcomm.modbus.exception.ModbusExceptionResponseException;
import net.weg.wcomm.modbus.exception.ModbusUnexpectedResponseException;
import net.weg.wcomm.modbus.tcp.client.ModbusTCPHelper;
import net.weg.wcomm.modbus.tcp.client.ModbusTCPMaster;

public class EthernetCommunication implements BaseCommunication {

    private ModbusTCPHelper ethernetCommunication;

    private String ip;
    private int address;
    private int port;
    private int timeBetweenCommand;

    public EthernetCommunication(String ip, int port, int address, int timeBetweenCommand) {
        this.ip = ip;
        this.address = address;
        this.port = port;
        this.timeBetweenCommand = timeBetweenCommand;
    }

    @Override
    public void startConnection() throws CommunicationException {
        ModbusTCPMaster master = new ModbusTCPMaster.Builder().hostAddress(ViewConfigurationPathUtil.HOST_ADDRESS).port(ViewConfigurationPathUtil.PORT).build();
        try {
			ethernetCommunication = new ModbusTCPHelper(master);
			ethernetCommunication.connect();
			ethernetCommunication.subscribeClient("Ethernet/Modbus-TCP/" + ip + ":" + port + "/@" + address);
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException | IOException ex) {
			throw new CommunicationException("Falha na conexão IOLink!");
		}
    }

    @Override
    public int readSingleRegister(int register) throws CommunicationException {
        Register[] registers;
		int read;
		try {
			registers = ethernetCommunication.readHoldingRegisters((short) register, (short) 1);
			read = registers[0].intValue();
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
			throw new CommunicationException("Falha na leitura dos registradores");
		}

		return read;
    }

    @Override
    public int[] readMultipleRegisters(int startingAddress, int quantityOfRegisters) throws CommunicationException {
        Register[] registers;
		int[] read = new int[quantityOfRegisters];
		try {
			registers = ethernetCommunication.readHoldingRegisters((short) startingAddress, (short) quantityOfRegisters);
			for (int i = 0; i < registers.length; i++) {
                read[i] = registers[i].intValue();
            }
            
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
			throw new CommunicationException("Falha na leitura dos registradores");
		}

		return read;
    }

    @Override
    public void writeSingleRegister(int registerAddress, int registerValue) throws CommunicationException {
        try {
			ethernetCommunication.writeSingleRegister((short) registerAddress, (short) registerValue);
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
			throw new CommunicationException("Falha na escrita dos registradores");
		}
    }

    @Override
    public void writeMultipleRegister(int initialRegister, int[] registersValue) throws CommunicationException {
        short[] values = new short[registersValue.length];
        for (int i = 0; i < registersValue.length; i++) {
            values[i] = (short) registersValue[i];
        }
        try {
			ethernetCommunication.writeMultipleRegisters((short) initialRegister, values);
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
			throw new CommunicationException("Falha na escrita dos registradores");
		}
    }

    @Override
    public void writeStringInRegister(int startingAddress, String stringToWrite) throws CommunicationException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeCommunication() {
        // TODO Auto-generated method stub
        
    }
    
}
