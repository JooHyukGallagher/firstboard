const boardUpdate = {
    titleIsValid: true,
    contentIsValid: true,
    init: function () {
        this.defaultCategory();

        // 제목 유효성 검증
        const titleContainer = document.querySelector("#title");
        titleContainer.addEventListener("keyup", (evt) => {
            this.titleIsValid = board.checkTitle(evt);
            board.changeTitleContainerColor(evt.target, this.titleIsValid)
        });

        // 내용 유효성 검증
        const contentContainer = document.querySelector("#content");
        contentContainer.addEventListener("keyup", (evt) => {
            this.contentIsValid = board.checkContent(evt);
            board.changeContentContainerColor(evt.target, this.contentIsValid)
        });

        // 글 업데이트
        const updateButton = document.querySelector("#btn-update");
        updateButton.addEventListener("click", () => {
            if (this.titleIsValid && this.contentIsValid) {
                this.updateBoard();
            } else if (!this.titleIsValid) {
                alert("제목에 2~100사이 글자를 입력해주세요");
            } else {
                alert("내용에 5~1000사이 글자를 입력해주세요")
            }
        });
    },
    defaultCategory: async function () {
        const boardType = document.querySelector(".boardType").value;
        const categoryValue = document.querySelector("[value=" + boardType + "]");
        categoryValue.setAttribute("selected", "selected");
    },
    updateBoard: async function () {
        const boardId = document.querySelector(".boardId").value;
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
    }
};

document.addEventListener("DOMContentLoaded", () => {
    boardUpdate.init();
});