package issac.issac_server.document.utils;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class DocumentFormatGenerator {
    // 기본 generateFields 메서드: 기존 optional 포함
    public static FieldDescriptor[] generateFields(String prefix, FieldDescriptor[] fields) {
        return generateFields(prefix, fields, new HashSet<>());
    }

    // 선택적 필드 optional 처리 포함
    public static FieldDescriptor[] generateFields(String prefix, FieldDescriptor[] fields, Set<String> optionalFields) {
        return Arrays.stream(fields)
                .map(field -> {
                    FieldDescriptor descriptor = fieldWithPath(prefix + field.getPath())
                            .type(field.getType())
                            .description(field.getDescription());

                    field.getAttributes().forEach((key, value) -> descriptor.attributes(Attributes.key(key).value(value)));

                    // 기본 optional 필드와 추가로 지정한 필드를 처리
                    if (field.isOptional() || optionalFields.contains(field.getPath())) {
                        descriptor.optional();
                    }
                    return descriptor;
                })
                .toArray(FieldDescriptor[]::new);
    }

    // 모든 필드를 optional로 만드는 메서드
    public static FieldDescriptor[] generateOptionalFields(String prefix, FieldDescriptor[] fields) {
        return Arrays.stream(fields)
                .map(field -> {
                    FieldDescriptor descriptor = fieldWithPath(prefix + field.getPath())
                            .type(field.getType())
                            .description(field.getDescription())
                            .optional();

                    field.getAttributes().forEach((key, value) -> descriptor.attributes(Attributes.key(key).value(value)));
                    return descriptor;
                })
                .toArray(FieldDescriptor[]::new);
    }

    public static FieldDescriptor[] generateFieldsWithRequired(String prefix, FieldDescriptor[] fields, Set<String> requiredFields) {
        return Arrays.stream(fields)
                .map(field -> {
                    FieldDescriptor descriptor = fieldWithPath(prefix + field.getPath())
                            .type(field.getType())
                            .description(field.getDescription());

                    field.getAttributes().forEach((key, value) -> descriptor.attributes(Attributes.key(key).value(value)));

                    if (!requiredFields.contains(field.getPath())) {
                        descriptor.optional();
                    }
                    return descriptor;
                })
                .toArray(FieldDescriptor[]::new);
    }

    public static FieldDescriptor[] mergeFields(FieldDescriptor[]... fieldArrays) {
        return Arrays.stream(fieldArrays)
                .flatMap(Arrays::stream)
                .toArray(FieldDescriptor[]::new);
    }

    public static ParameterDescriptor[] generateParameters(String prefix, ParameterDescriptor[] parameters) {
        return generateParameters(prefix, parameters, new HashSet<>());
    }

    // 선택적 파라미터 optional 처리 포함
    public static ParameterDescriptor[] generateParameters(String prefix, ParameterDescriptor[] parameters, Set<String> optionalParameters) {
        return Arrays.stream(parameters)
                .map(parameter -> {
                    ParameterDescriptor descriptor = parameterWithName(prefix + parameter.getName())
                            .description(parameter.getDescription());

                    parameter.getAttributes().forEach((key, value) -> descriptor.attributes(Attributes.key(key).value(value)));

                    // 기본 optional 파라미터와 추가로 지정한 파라미터 처리
                    if (parameter.isOptional() || optionalParameters.contains(parameter.getName())) {
                        descriptor.optional();
                    }
                    return descriptor;
                })
                .toArray(ParameterDescriptor[]::new);
    }

    // 모든 필드를 optional로 만드는 메서드
    public static ParameterDescriptor[] generateOptionalParameters(String prefix, ParameterDescriptor[] parameters) {
        return Arrays.stream(parameters)
                .map(parameter -> {
                    ParameterDescriptor descriptor = parameterWithName(prefix + parameter.getName())
                            .description(parameter.getDescription())
                            .optional();

                    parameter.getAttributes().forEach((key, value) -> descriptor.attributes(Attributes.key(key).value(value)));

                    return descriptor;
                })
                .toArray(ParameterDescriptor[]::new);
    }

    public static ParameterDescriptor[] mergeParameters(ParameterDescriptor[]... parameterArrays) {
        return Arrays.stream(parameterArrays)
                .flatMap(Arrays::stream)
                .toArray(ParameterDescriptor[]::new);
    }
}
