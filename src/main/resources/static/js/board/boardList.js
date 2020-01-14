const boardList = {
    init: async function () {
        let tbody = document.querySelector("#tbody");
        let boarListResponseList = await getData("/api/board/list?boardType=ALL");
        boarListResponseList.forEach(boardListResponse => {
            const data = {
                id: boardListResponse.id,
                title: boardListResponse.title,
                replyCount: boardListResponse.replyCount,
                boardType: boardListResponse.boardType,
                nickname: boardListResponse.nickname,
                viewCount: boardListResponse.viewCount,
                createDate: boardListResponse.createDate
            };

            const boardListTemplate = document.querySelector("#boardListTemplate").innerHTML;

            const bindTemplate = Handlebars.compile(boardListTemplate);
            const result = bindTemplate(data);
            tbody.innerHTML += result;
        });

        const categoryList = document.querySelector("#category");
        categoryList.addEventListener("click", (evt) => {
            this.requestBoardList(evt);
        })
    },
    // requestBoardList: async function (evt) {
    //         const categoryButton = evt.target;
    //         if(categoryButton.classList.contains("nav-link")){
    //             const data = await getData("/api/board/list?boardType=" + categoryButton.id);
    //             const template = document.querySelector("#boardListTemplate").innerHTML;
    //             console.log(template);
    //         }
    // }
};

document.addEventListener("DOMContentLoaded", () => {
    boardList.init();
});