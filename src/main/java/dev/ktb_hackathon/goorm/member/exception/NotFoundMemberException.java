package dev.ktb_hackathon.goorm.member.exception;

import dev.ktb_hackathon.goorm.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends BaseException {

    public NotFoundMemberException() {
        super("NOT_FOUND_MEMBER", HttpStatus.NOT_FOUND);
    }
}
