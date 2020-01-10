const board = {
    init: function () {
        // const boardList = getData("/board");
        // console.log(boardList);

        const saveButton = document.querySelector("#btn-save");
        saveButton.addEventListener("click", function (event) {
            const url = "/api/board";
            const data = {
                boardType: document.querySelector("#boardType").value,
                boardTitle: document.querySelector("#title").value,
                boardContent: document.querySelector("#content").value,
                viewCount: 0
            };

            const response = postData(url, data);
            console.log(response);
        });
    }
};

document.addEventListener("DOMContentLoaded", () => {
    board.init();
});