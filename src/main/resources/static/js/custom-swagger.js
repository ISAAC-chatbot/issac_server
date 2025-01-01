document.addEventListener("DOMContentLoaded", function () {
    document.title = "ISSAC API 명세서";

    // Swagger UI 렌더링이 완료된 후 실행
    setTimeout(() => {
        // `opblock` 클래스 중 `is-open`이 포함된 요소를 모두 선택
        const openBlocks = document.querySelectorAll(".opblock.is-open");

        // 선택된 모든 요소에서 `is-open` 클래스를 제거
        openBlocks.forEach((block) => {
            block.classList.remove("is-open");

            // 섹션 내용을 숨기기
            const opblockBody = block.querySelector(".opblock-body");
            if (opblockBody) {
                opblockBody.style.display = "none";
            }

            // 화살표 아이콘 방향 설정
            const arrowButton = block.querySelector(".opblock-control-arrow");
            if (arrowButton) {
                arrowButton.setAttribute("aria-expanded", "false");
                const arrowIcon = arrowButton.querySelector(".arrow");
                if (arrowIcon) {
                    arrowIcon.style.transform = "rotate(180deg)"; // 닫힌 상태로 변경
                }
            }
        });
    }, 100);
});