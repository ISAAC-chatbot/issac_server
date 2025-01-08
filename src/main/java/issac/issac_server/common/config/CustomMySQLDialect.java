package issac.issac_server.common.config;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.query.sqm.produce.function.FunctionParameterType;
import org.hibernate.type.StandardBasicTypes;

public class CustomMySQLDialect implements FunctionContributor {
    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions.getFunctionRegistry()
                .patternDescriptorBuilder("match",
                        "MATCH(?1) AGAINST(?2 IN BOOLEAN MODE)") // 함수의 SQL 정의
                .setExactArgumentCount(2) // 두 개의 인자를 받음 (컬럼, 검색어)
                .setParameterTypes(
                        FunctionParameterType.STRING, // 첫 번째 인자는 문자열(컬럼)
                        FunctionParameterType.STRING  // 두 번째 인자는 검색어
                )
                .setInvariantType(functionContributions.getTypeConfiguration()
                        .getBasicTypeRegistry().resolve(StandardBasicTypes.DOUBLE)) // 반환 타입은 DOUBLE
                .register();
    }
}
