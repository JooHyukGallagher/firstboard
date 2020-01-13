const boardList = {
    init: function () {
        const categoryList = document.querySelector("#category");
        categoryList.addEventListener("click", (evt) => {
            this.requestBoardList(evt);
        })
    },
    requestBoardList: async function (evt) {
            const categoryButton = evt.target;
            if(categoryButton.classList.contains("nav-link")){
                const data = await getData("/api/board/list?boardType=" + categoryButton.id);
                console.log(data);
            }
    }
};

document.addEventListener("DOMContentLoaded", () => {
    boardList.init();
});