package com.edson.communication;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.edson.exception.CommunicationException;
import com.edson.util.ViewConfigurationPathUtil;

import net.weg.wcomm.modbus.NegativeConfirmationException;
import net.weg.wcomm.modbus.Register;
import net.weg.wcomm.modbus.exception.ModbusExceptionResponseException;
import net.weg.wcomm.modbus.exception.ModbusUnexpectedResponseException;
import net.weg.wcomm.modbus.tcp.client.ModbusTCPHelper;
import net.weg.wcomm.modbus.tcp.client.ModbusTCPMaster;

public class IOLinkCommunication implements BaseCommunication {

    private ModbusTCPHelper EthernetIOLinkCommunication;

    private String ip;
    private int address;
    private int port;

    public IOLinkCommunication(String ip, int port, int address) {
        this.ip = ip;
        this.address = address;
        this.port = port;
    }
    
    @Override
    public void startConnection() throws CommunicationException {
        ModbusTCPMaster master = new ModbusTCPMaster.Builder().hostAddress(ViewConfigurationPathUtil.HOST_ADDRESS).port(ViewConfigurationPathUtil.PORT).build();
        try {
			EthernetIOLinkCommunication = new ModbusTCPHelper(master);
			EthernetIOLinkCommunication.connect();
			EthernetIOLinkCommunication.subscribeClient("Ethernet/Modbus-TCP/" + ip + ":" + port + "/@" + address);
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException | IOException ex) {
			throw new CommunicationException("Falha na conexão IOLink!");
		}
    }

    @Override
    public int readSingleRegister(int register) throws CommunicationException {
        Register[] registers;
		int read;
		try {
			registers = EthernetIOLinkCommunication.readHoldingRegisters((short) register, (short) 1);
			read = registers[0].intValue();
			
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
			throw new CommunicationException("Falha na leitura dos registradores");
		}

		return read;
    }

    @Override
    public int[] readMultipleRegisters(int startingAddress, int quantityOfRegisters) throws CommunicationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeSingleRegister(int registerAddress, int registerValue) throws CommunicationException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeMultipleRegister(int initialRegister, int[] registersValue) throws CommunicationException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeCommunication() {
        // TODO Auto-generated method stub
        
    }
}
