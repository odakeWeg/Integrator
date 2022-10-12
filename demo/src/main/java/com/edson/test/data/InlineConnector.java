package com.edson.test.data;

import net.weg.soa.serviceclient.wdc.supervisoryevent.StatusRegister;
import net.weg.soa.serviceclient.wdc.supervisoryevent.SupervisoryEvent;
import net.weg.soa.serviceclient.wdc.supervisoryevent.TestResult;
import net.weg.wdc.sic.SicLibraryHelper;
import net.weg.wdc.sic.service.SicLibraryServices;

public class InlineConnector {

    public void saveInitialEvent(String serial) throws Exception {
	}

    public void saveApprovalEvent(String serial) throws Exception {  
	}

	public void saveDisapprovalEvent(String serial, String failureReason) throws Exception {
    }

    public void saveCancelEvent(String serial, String failureReason) throws Exception {
	}

    public boolean isTestAllowed(String serial) throws Exception {
        return true;
	}

    /* 
     public void saveInitialEvent(String serial) throws Exception {
        SupervisoryEvent supervisoryEvent = new SupervisoryEvent();
        supervisoryEvent.setWorkCenter(SicLibraryServices.getInstance().getWorkCenterConfiguration().getNumber());
        supervisoryEvent.setTestStation(SicLibraryServices.getInstance().getWorkCenterConfiguration().getTestStationNumber());
        supervisoryEvent.setOperation(SicLibraryServices.getInstance().getWorkCenterConfiguration().getProductionStep());
        supervisoryEvent.setPlant(SicLibraryServices.getInstance().getWorkCenterConfiguration().getPlant());
        supervisoryEvent.setTestResult(TestResult.EXECUTING);
        supervisoryEvent.setTestInitialDate(SicLibraryHelper.getXMLGregorianCalendarNow());
        supervisoryEvent.setTestInitialHour(SicLibraryHelper.getTimeNow());
        supervisoryEvent.setTestFinalDate(SicLibraryHelper.getXMLGregorianCalendarNow());
        supervisoryEvent.setTestFinalHour(SicLibraryHelper.getTimeNow());
        supervisoryEvent.setStatusRegister(StatusRegister.TEST_EXECUTING);
        SicLibraryServices.getInstance().SaveBeginSupervisoryEvent(supervisoryEvent);
	}

    public void saveApprovalEvent(String serial) throws Exception {
		SupervisoryEvent resultTest = null;
        resultTest = SicLibraryServices.getInstance().FindLastEventBySerialNumber(serial);
        resultTest.setStatusRegister(StatusRegister.TEST_FINISHED);
        resultTest.setTestResult(TestResult.APPROVED);
        resultTest.setTestFinalDate(SicLibraryHelper.getXMLGregorianCalendarNow());
        resultTest.setTestFinalHour(SicLibraryHelper.getTimeNow());
        SicLibraryServices.getInstance().SaveEndSupervisoryEvent(resultTest);   
	}

	public void saveDisapprovalEvent(String serial, String failureReason) throws Exception {
        SupervisoryEvent resultTest;
        resultTest = SicLibraryServices.getInstance().FindLastEventBySerialNumber(serial);
        resultTest.setFaultId(failureReason);
        resultTest.setStatusRegister(StatusRegister.TEST_FINISHED);
        resultTest.setTestResult(TestResult.FAILED);
        resultTest.setTestFinalDate(SicLibraryHelper.getXMLGregorianCalendarNow());
        resultTest.setTestFinalHour(SicLibraryHelper.getTimeNow());
        SicLibraryServices.getInstance().SaveEndSupervisoryEvent(resultTest);
    }

    public void saveCancelEvent(String serial, String failureReason) throws Exception {
        SupervisoryEvent resultTest;
        resultTest = SicLibraryServices.getInstance().FindLastEventBySerialNumber(serial);
        resultTest.setFaultId(failureReason);
        resultTest.setStatusRegister(StatusRegister.TEST_FINISHED);
        resultTest.setTestResult(TestResult.CANCELED);
        resultTest.setTestFinalDate(SicLibraryHelper.getXMLGregorianCalendarNow());
        resultTest.setTestFinalHour(SicLibraryHelper.getTimeNow());
        SicLibraryServices.getInstance().SaveEndSupervisoryEvent(resultTest);
	}

    public boolean isTestAllowed(String serial) throws Exception {
        return SicLibraryServices.getInstance().isWorkcenterOperationAllowed(serial);
	}
    */
}
