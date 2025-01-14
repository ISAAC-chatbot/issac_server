package issac.issac_server.report.exception;

import issac.issac_server.common.exception.IssacException;

public class ReportException extends IssacException {

    public ReportException(ReportErrorCode code) {
        super(code);
    }
}
