package com.prowidesoftware.swift.samples;

import java.math.BigDecimal;
import java.util.UUID;

import com.prowidesoftware.swift.model.mx.MxPacs00800109;
import com.prowidesoftware.swift.model.mx.dic.ActiveCurrencyAndAmount;
import com.prowidesoftware.swift.model.mx.dic.BranchAndFinancialInstitutionIdentification6;
import com.prowidesoftware.swift.model.mx.dic.ChargeBearerType1Code;
import com.prowidesoftware.swift.model.mx.dic.CreditTransferTransaction43;
import com.prowidesoftware.swift.model.mx.dic.FIToFICustomerCreditTransferV09;
import com.prowidesoftware.swift.model.mx.dic.FinancialInstitutionIdentification18;
import com.prowidesoftware.swift.model.mx.dic.GroupHeader93;
import com.prowidesoftware.swift.model.mx.dic.PartyIdentification135;
import com.prowidesoftware.swift.model.mx.dic.PaymentIdentification13;
import com.prowidesoftware.swift.model.mx.dic.PostalAddress24;
import com.prowidesoftware.swift.model.mx.dic.SettlementInstruction7;
import com.prowidesoftware.swift.model.mx.dic.SettlementMethod1Code;

/**
 * This is an example of creating a new MX payment pacs.008.001.009 message
 * using the Java Model to set its content.
 */
public class MxPacs00800109Creation {
    public static void main(String[] args) {
        MxPacs00800109 mx = new MxPacs00800109();

        // initialize Group Header
        mx.setFIToFICstmrCdtTrf(new FIToFICustomerCreditTransferV09().setGrpHdr(new GroupHeader93()));

        // general information
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setMsgId("TBEXO12345");
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setCreDtTm(util.getXMLGregorianCalendarNow());
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setNbOfTxs("1");

        // settlement information
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setSttlmInf(new SettlementInstruction7());
        mx.getFIToFICstmrCdtTrf().getGrpHdr().getSttlmInf().setSttlmMtd(SettlementMethod1Code.INDA);

        // create credit transfer transaction information
        CreditTransferTransaction43 ctt = new CreditTransferTransaction43();

        // transaction Identification
        ctt.setPmtId(new PaymentIdentification13());
        ctt.getPmtId().setUETR(UUID.randomUUID().toString());
        ctt.getPmtId().setEndToEndId("TBEXO12345");

        // transaction amount
        ActiveCurrencyAndAmount amount = new ActiveCurrencyAndAmount();
        amount.setCcy("EUR");
        amount.setValue(new BigDecimal(100));
        ctt.setIntrBkSttlmAmt(amount);

        // transaction charges
        ctt.setChrgBr(ChargeBearerType1Code.DEBT);

        // orderer name & address
        ctt.setDbtr(new PartyIdentification135());
        ctt.getDbtr().setNm("JOE DOE");
        ctt.getDbtr().setPstlAdr((new PostalAddress24()).addAdrLine("310 Field Road, NY"));

        // order Financial Institution
        ctt.setDbtrAgt((new BranchAndFinancialInstitutionIdentification6())
                .setFinInstnId(new FinancialInstitutionIdentification18().setBICFI("FOOBARC0XXX")));

        // beneficiary institution
        ctt.setCdtrAgt((new BranchAndFinancialInstitutionIdentification6())
                .setFinInstnId(new FinancialInstitutionIdentification18().setBICFI("BANKANC0XXX")));

        // beneficiary name & address
        ctt.setCdtr(new PartyIdentification135());
        ctt.getCdtr().setNm("TEST CORP");
        ctt.getCdtr().setPstlAdr((new PostalAddress24()).addAdrLine("Nellis ABC, NV"));

        // add credit transfer transaction information
        mx.getFIToFICstmrCdtTrf().addCdtTrfTxInf(ctt);

        // print the generated message in its XML format
        System.out.println(mx.message());
    }
}
