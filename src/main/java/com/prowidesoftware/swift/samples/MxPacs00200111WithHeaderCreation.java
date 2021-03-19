package com.prowidesoftware.swift.samples;

import com.prowidesoftware.swift.model.mx.MxPacs00200111;
import com.prowidesoftware.swift.model.mx.BusinessAppHdrV02;
import com.prowidesoftware.swift.model.mx.MxPacs00800109;
import com.prowidesoftware.swift.model.mx.dic.BranchAndFinancialInstitutionIdentification6;
import com.prowidesoftware.swift.model.mx.dic.FIToFIPaymentStatusReportV11;
import com.prowidesoftware.swift.model.mx.dic.FinancialInstitutionIdentification18;
import com.prowidesoftware.swift.model.mx.dic.GroupHeader91;
import com.prowidesoftware.swift.model.mx.dic.OriginalGroupHeader17;
import com.prowidesoftware.swift.model.mx.dic.Party44Choice;

/**
 * This is an example of creating a pacs.002.001.11 message for the
 * pacs.008.001.09 message in {@link MxPacs00800109WithHeaderCreation}.
 * 
 */
public class MxPacs00200111WithHeaderCreation {
    public static void main(String[] args) {
        MxPacs00200111 mx = createPacs00200111();

        BusinessAppHdrV02 header = new BusinessAppHdrV02();

        // from
        header.setFr((new Party44Choice()).setFIId((new BranchAndFinancialInstitutionIdentification6())
                .setFinInstnId((new FinancialInstitutionIdentification18()).setBICFI("ABCDUS33XXX"))));

        // to
        header.setTo((new Party44Choice()).setFIId((new BranchAndFinancialInstitutionIdentification6())
                .setFinInstnId((new FinancialInstitutionIdentification18()).setBICFI("EFGHUS33XXX"))));

        // business message identifier
        header.setBizMsgIdr("ABC1456456");

        // message definition identifier
        // the message instance that is being transported
        header.setMsgDefIdr(mx.getMxId().toString());

        // creation date
        header.setCreDt(util.getXMLGregorianCalendarNow());

        // set the header for the pacs008.001.09 message
        mx.setAppHdr(header);

        // print the whole message in its XML format
        System.out.println(mx.message());
    }

    private static MxPacs00200111 createPacs00200111() {
        MxPacs00200111 mx = new MxPacs00200111();

        // initialize group header
        mx.setFIToFIPmtStsRpt(new FIToFIPaymentStatusReportV11().setGrpHdr(new GroupHeader91()));

        // general infomration
        mx.getFIToFIPmtStsRpt().getGrpHdr().setMsgId("ABC12345");
        mx.getFIToFIPmtStsRpt().getGrpHdr().setCreDtTm(util.getXMLGregorianCalendarNow());

        // make this the status report for the
        // pacs.008.001.09 message from {@link MxPacs00800109WithHeaderCreation}
        MxPacs00800109 pacs = MxPacs00800109WithHeaderCreation.createPacs00800109();

        OriginalGroupHeader17 orgHeader = new OriginalGroupHeader17();
        orgHeader.setOrgnlMsgId(pacs.getFIToFICstmrCdtTrf().getGrpHdr().getMsgId());
        orgHeader.setOrgnlMsgNmId(pacs.getMxId().toString());

        mx.getFIToFIPmtStsRpt().addOrgnlGrpInfAndSts(orgHeader);

        return mx;
    }
}
