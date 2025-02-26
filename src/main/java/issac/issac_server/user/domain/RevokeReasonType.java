package issac.issac_server.user.domain;

import issac.issac_server.common.domain.DescriptiveEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RevokeReasonType implements DescriptiveEnum {

    NOT_VISITING_APP("앱 방문을 잘 하지 않아요"),
    LACK_OF_INFORMATION("필요한 정보가 별로 없어요"),
    LACK_OF_FEATURES("기능이 부족해요"),
    TOO_MANY_BUGS("버그가 많아요"),
    OTHER("기타");

    private final String description;

    @Override
    public String getCode() {
        return name();
    }
}

