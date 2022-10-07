package com.edson.communication;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.springframework.data.mongodb.core.aggregation.DateOperators.Millisecond;

import com.edson.exception.CommunicationException;
import com.edson.util.ViewConfigurationPathUtil;

import net.weg.wcomm.modbus.NegativeConfirmationException;
import net.weg.wcomm.modbus.Register;
import net.weg.wcomm.modbus.exception.ModbusExceptionResponseException;
import net.weg.wcomm.modbus.exception.ModbusUnexpectedResponseException;
import net.weg.wcomm.modbus.tcp.client.ModbusTCPHelper;
import net.weg.wcomm.modbus.tcp.client.ModbusTCPMaster;

public class IOLinkCommunication implements BaseCommunication {

    private ModbusTCPHelper ethernetIOLinkCommunication;

    private String ip;
    private int address;
    private int port;
    private int timeBetweenCommand;
    private int toggleReading = 525;
    private int toggleWriting = 258;
    private boolean toggle = false;

    public IOLinkCommunication(String ip, int port, int address, int timeBetweenCommand) {
        this.ip = ip;
        this.address = address;
        this.port = port;
        this.timeBetweenCommand = timeBetweenCommand;
    }
    
    @Override
    public void startConnection() throws CommunicationException {
        ModbusTCPMaster master = new ModbusTCPMaster.Builder().hostAddress(ViewConfigurationPathUtil.HOST_ADDRESS).port(ViewConfigurationPathUtil.PORT).build();
        try {
			ethernetIOLinkCommunication = new ModbusTCPHelper(master);
			ethernetIOLinkCommunication.connect();
			ethernetIOLinkCommunication.subscribeClient("Ethernet/Modbus-TCP/" + ip + ":" + port + "/@" + address);
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException | IOException ex) {
			throw new CommunicationException("Falha na conexão IOLink!");
		}
    }

    @Override
    public int readSingleRegister(int register) throws CommunicationException {
        Register[] registers;
		int read;
		try {
			registers = ethernetIOLinkCommunication.readHoldingRegisters((short) register, (short) 1);
			read = registers[0].intValue();
			
		} catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
			throw new CommunicationException("Falha na leitura dos registradores");
		}

		return read;
    }

    @Override
    public int[] readMultipleRegisters(int startingAddress, int quantityOfRegisters) throws CommunicationException {
        return readTargetRegister(startingAddress, quantityOfRegisters);
    }

    private int[] readTargetRegister(int address, int position) throws CommunicationException {
        short[] readingStructureRequest = { 1, (short) address, 0, (short) toggleReading, 0 };
        Register[] leituraRegister;
        int[] leitura;

        try {
            toggleCommandIdentifier();
            ethernetIOLinkCommunication.writeMultipleRegisters((short) 500, readingStructureRequest);
            TimeUnit.MILLISECONDS.sleep(timeBetweenCommand);
            leituraRegister = ethernetIOLinkCommunication.readHoldingRegisters((short) (position+5), (short) 1);
            leitura = new int[leituraRegister.length];
            for (int i = 0; i < leituraRegister.length; i++) {
                leitura[i] = invertByte(String.valueOf(leituraRegister[i].intValue()));
                System.out.println("----" + leituraRegister[i].intValue());
            }
        } catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException | InterruptedException e) {
            throw new CommunicationException("Falha na leitura dos registradores");
        }
        return leitura;
    }

    @Override
    public void writeSingleRegister(int registerAddress, int registerValue) throws CommunicationException {
        short[] writingStructure = {(short) port, (short) registerAddress, 0, (short) toggleWriting, 16, (short) registerValue};
        try {
            ethernetIOLinkCommunication.writeMultipleRegisters((short) 500, writingStructure);
        } catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
            throw new CommunicationException("Falha na leitura dos registradores");
        }
    }

    @Override
    public void writeMultipleRegister(int initialRegister, int[] registersValue) throws CommunicationException {
        short[] writingStructure = {(short) port, (short) initialRegister, 0, (short) toggleWriting, 16, (short) registersValue[0], (short) registersValue[0]};
        try {
            ethernetIOLinkCommunication.writeMultipleRegisters((short) 500, writingStructure);
        } catch (NegativeConfirmationException | ModbusExceptionResponseException | ModbusUnexpectedResponseException e) {
            throw new CommunicationException("Falha na leitura dos registradores");
        }
    }

    @Override
    public void closeCommunication() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeStringInRegister(int startingAddress, String stringToWrite) throws CommunicationException {
        int[] data = invertBytes(stringToWrite);
        writeMultipleRegister(startingAddress, data);
    }

    public int invertByte(String serial) {
        long serialNumber = Long.parseLong(serial);
        String binarySerialNumber = Long.toBinaryString(serialNumber);
        String dataToSendBuffer;
        int size = 16;

        binarySerialNumber = fillLeftZeros(binarySerialNumber, size);
        dataToSendBuffer = binarySerialNumber.substring(8) + binarySerialNumber.substring(0, 8);
        int dataToSend = Integer.parseInt(dataToSendBuffer, 2);

        return dataToSend;
    }

    public int[] invertBytes(String serial) {
        long serialNumber = Long.parseLong(serial);
        String binarySerialNumber = Long.toBinaryString(serialNumber);
        String[] dataToSendBuffer = new String[2];
        int size = 32;

        binarySerialNumber = fillLeftZeros(binarySerialNumber, size);
        dataToSendBuffer[0] = binarySerialNumber.substring(0, 16).substring(8) + binarySerialNumber.substring(0, 16).substring(0, 8);
        dataToSendBuffer[1] = binarySerialNumber.substring(16).substring(8) + binarySerialNumber.substring(16).substring(0, 8);
        int[] dataToSend = {Integer.parseInt(dataToSendBuffer[0], 2), Integer.parseInt(dataToSendBuffer[1], 2)};

        return dataToSend;
    }

    private String fillLeftZeros(String number, int size) {
        while(number.length() < size) {
            number = "0" + number;
        }
        return number;
    }

    private void toggleCommandIdentifier() {
        if(toggle) {
            toggleReading = 525;
            toggleWriting = 258;
            toggle = false;
        } else {
            toggleReading = 524;
            toggleWriting = 257;
            toggle = true;
        }
    }

}
