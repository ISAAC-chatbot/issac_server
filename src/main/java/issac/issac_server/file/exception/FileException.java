package issac.issac_server.file.exception;

import issac.issac_server.common.exception.IssacException;

public class FileException extends IssacException {

    public FileException(FileErrorCode code) {
        super(code);
    }
}
