const board = {
    init: async function () {
        const saveButton = document.querySelector("#btn-save");
        if (saveButton) {
            saveButton.addEventListener("click", this.saveBoard);
        }
    },
    saveBoard: async function () {
        const url = "/api/board";
        const data = {
            boardType: document.querySelector("#boardType").value,
            boardTitle: document.querySelector("#title").value,
            boardContent: document.querySelector("#content").value,
            viewCount: 0
        };

        const response = await postData(url, data);
        if (response) {
            window.location.href = "/board/list";
        }
    }
};

document.addEventListener("DOMContentLoaded", () => {
    board.init();
});