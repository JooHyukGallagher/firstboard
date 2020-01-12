const boardRead = {
    init: function () {
        this.defaultCategoryClassify();
    },
    defaultCategoryClassify: async function () {
        const boardId = document.querySelector("#boardId").value;
        const boardResponse = await getData("/api/board/" + boardId);

        const boardType = boardResponse.boardType;
        const categoryValue = document.querySelector("#" + boardType);
        categoryValue.classList.add("active");
    }
};

document.addEventListener("DOMContentLoaded", () => {
    boardRead.init();
});