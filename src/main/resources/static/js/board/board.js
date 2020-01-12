const board = {
    init: function () {
        const saveButton = document.querySelector("#btn-save");
        if (saveButton) {
            saveButton.addEventListener("click", this.saveBoard);
        }

        const updateButton = document.querySelector("#btn-update");
        if (updateButton) {
            updateButton.addEventListener("click", this.updateBoard);
        }

        const removeButton = document.querySelector("#btn-remove");
        if (removeButton) {
            removeButton.addEventListener("click", this.removeBoard);
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

        const result = await postData(url, data);
        if (result) {
            window.location.href = "/board/list";
        }
    },
    updateBoard: async function () {
        const boardId = document.querySelector("#boardId").value;
        const url = "/api/board/" + boardId;
        const data = {
            boardType: document.querySelector("#boardType").value,
            boardTitle: document.querySelector("#title").value,
            boardContent: document.querySelector("#content").value,
        };

        const result = await putData(url, data);
        if (result) {
            window.location.href = "/board/list";
        }
    },
    removeBoard: async function () {
        const boardId = document.querySelector("#boardId").value;
        const url = "/api/board/" + boardId;

        const result = await removeData(url);
        if (result) {
            window.location.href = "/board/list";
        }
    }
};

document.addEventListener("DOMContentLoaded", () => {
    board.init();
});