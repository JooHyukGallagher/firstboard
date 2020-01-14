const boardList = {
    init: function () {
        let prevButton = document.querySelector("#ALL");
        let currentButton;

        const categoryList = document.querySelector("#category");
        categoryList.addEventListener("click", (evt) => {
            // 클릭시 이전 카테고리 active 삭제 및 해당 카테고리 active 표시
            prevButton.classList.remove("active");
            currentButton = evt.target;
            currentButton.classList.add("active");
            prevButton = evt.target;
            this.requestBoardList(evt);
        })
    },
    requestBoardList: async function (evt) {
        const categoryButton = evt.target;
        if (categoryButton.classList.contains("nav-link")) {
            const boarListResponseList = await getData("/api/board/list?boardType=" + categoryButton.id);
            boardList.setBindTemplate(boarListResponseList);
        }
    },
    setBindTemplate: function (boardListResponseList) {
        // 기존 리스트 삭제
        const boardListContainer = document.querySelector("#tbody");
        boardListContainer.innerHTML = "";

        boardListResponseList.forEach(boardListResponse => {
            const data = boardList.createBindingData(boardListResponse);
            const boardListTemplate = document.querySelector("#boardListTemplate").innerHTML;

            const bindTemplate = Handlebars.compile(boardListTemplate);
            const result = bindTemplate(data);
            boardListContainer.innerHTML += result;
        });
    },
    createBindingData: function (boardListResponse) {
        return {
            id: boardListResponse.id,
            title: boardListResponse.title,
            replyCount: boardListResponse.replyCount,
            boardType: boardListResponse.boardType,
            nickname: boardListResponse.nickname,
            viewCount: boardListResponse.viewCount,
            createDate: boardListResponse.createDate
        };
    }
};

document.addEventListener("DOMContentLoaded", () => {
    boardList.init();
});