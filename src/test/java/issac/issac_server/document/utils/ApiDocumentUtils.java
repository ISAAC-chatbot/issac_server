package issac.issac_server.document.utils;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class ApiDocumentUtils {

    // 공통 Request Parameters 정의
    public static ParameterDescriptor pageParam() {
        return parameterWithName("page").description("페이지 번호 (0부터 시작)").optional();
    }

    public static ParameterDescriptor sizeParam() {
        return parameterWithName("size").description("페이지 당 항목 수").optional();
    }

    public static final FieldDescriptor[] PAGE_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("pageable").type(JsonFieldType.OBJECT).description("페이징 정보"),
            fieldWithPath("pageable.pageNumber").type(JsonFieldType.NUMBER).description("현재 페이지 번호 (0부터 시작)"),
            fieldWithPath("pageable.pageSize").type(JsonFieldType.NUMBER).description("페이지 당 항목 수"),
            fieldWithPath("pageable.sort").type(JsonFieldType.OBJECT).description("정렬 정보"),
            fieldWithPath("pageable.sort.empty").type(JsonFieldType.BOOLEAN).description("정렬 조건이 비어 있는지 여부"),
            fieldWithPath("pageable.sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되었는지 여부"),
            fieldWithPath("pageable.sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되지 않았는지 여부"),
            fieldWithPath("pageable.offset").type(JsonFieldType.NUMBER).description("현재 페이지의 시작 오프셋"),
            fieldWithPath("pageable.paged").type(JsonFieldType.BOOLEAN).description("페이징이 활성화되었는지 여부"),
            fieldWithPath("pageable.unpaged").type(JsonFieldType.BOOLEAN).description("페이징이 비활성화되었는지 여부"),

            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("현재 페이지가 마지막 페이지인지 여부"),
            fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 항목 수"),
            fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("현재 페이지 크기"),
            fieldWithPath("number").type(JsonFieldType.NUMBER).description("현재 페이지 번호"),
            fieldWithPath("sort").type(JsonFieldType.OBJECT).description("정렬 정보"),
            fieldWithPath("sort.empty").type(JsonFieldType.BOOLEAN).description("정렬 조건이 비어 있는지 여부"),
            fieldWithPath("sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되었는지 여부"),
            fieldWithPath("sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되지 않았는지 여부"),
            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("현재 페이지가 첫 번째 페이지인지 여부"),
            fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("현재 페이지에서 반환된 항목 수"),
            fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("현재 페이지가 비어 있는지 여부")
    };

    public static final FieldDescriptor[] SLICE_RESPONSE = new FieldDescriptor[]{
            fieldWithPath("pageable").type(JsonFieldType.OBJECT).description("페이징 정보"),
            fieldWithPath("pageable.pageNumber").type(JsonFieldType.NUMBER).description("현재 페이지 번호 (0부터 시작)"),
            fieldWithPath("pageable.pageSize").type(JsonFieldType.NUMBER).description("페이지 당 항목 수"),
            fieldWithPath("pageable.sort").type(JsonFieldType.OBJECT).description("정렬 정보"),
            fieldWithPath("pageable.sort.empty").type(JsonFieldType.BOOLEAN).description("정렬 조건이 비어 있는지 여부"),
            fieldWithPath("pageable.sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되었는지 여부"),
            fieldWithPath("pageable.sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되지 않았는지 여부"),
            fieldWithPath("pageable.offset").type(JsonFieldType.NUMBER).description("현재 페이지의 시작 오프셋"),
            fieldWithPath("pageable.paged").type(JsonFieldType.BOOLEAN).description("페이징이 활성화되었는지 여부"),
            fieldWithPath("pageable.unpaged").type(JsonFieldType.BOOLEAN).description("페이징이 비활성화되었는지 여부"),

            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("현재 페이지가 첫 번째 페이지인지 여부"),
            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("현재 페이지가 마지막 페이지인지 여부"),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("현재 페이지 크기"),
            fieldWithPath("number").type(JsonFieldType.NUMBER).description("현재 페이지 번호"),
            fieldWithPath("sort").type(JsonFieldType.OBJECT).description("정렬 정보"),
            fieldWithPath("sort.empty").type(JsonFieldType.BOOLEAN).description("정렬 조건이 비어 있는지 여부"),
            fieldWithPath("sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되었는지 여부"),
            fieldWithPath("sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬이 적용되지 않았는지 여부"),
            fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("현재 페이지에서 반환된 항목 수"),
            fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("현재 페이지가 비어 있는지 여부")
    };
}
