const boardWrite = {
    titleIsValid: false,
    contentIsValid: false,
    init: function () {
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

        // 글 등록록
       const saveButton = document.querySelector("#btn-save");
        saveButton.addEventListener("click", () => {
            if (this.titleIsValid && this.contentIsValid) {
                this.saveBoard()
            } else if (!this.titleIsValid){
                alert("제목에 2~100사이 글자를 입력해주세요");
            } else {
                alert("내용에 5~1000사이 글자를 입력해주세요")
            }
        });
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
    }
};

document.addEventListener("DOMContentLoaded", () => {
    boardWrite.init();
});
