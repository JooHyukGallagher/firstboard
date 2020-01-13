const board = {
    init: function () {
        const removeButton = document.querySelector("#btn-remove");
        if (removeButton) {
            removeButton.addEventListener("click", this.removeBoard);
        }

    },
    removeBoard: async function () {
        const boardId = document.querySelector(".boardId").value;
        const url = "/api/board/" + boardId;

        const result = await removeData(url);
        if (result) {
            window.location.href = "/board/list";
        }
    },
    checkTitle: function (evt) {
        let title = evt.target.value;
        return (/^.{2,100}$/).test(title);
    },
    checkContent: function (evt) {
        let content = evt.target.value;
        return (/^.{5,1000}$/).test(content);
    },
    changeTitleContainerColor: function (titleContainer, isValid) {
        if (isValid) {
            titleContainer.classList.remove("is-invalid");
            titleContainer.classList.add("is-valid");
        } else {
            titleContainer.classList.remove("is-valid");
            titleContainer.classList.add("is-invalid");
        }
    },
    changeContentContainerColor: function (contentContainer, isValid) {
        if (isValid) {
            contentContainer.classList.remove("is-invalid");
            contentContainer.classList.add("is-valid");
        } else {
            contentContainer.classList.remove("is-valid");
            contentContainer.classList.add("is-invalid");
        }
    }
};

document.addEventListener("DOMContentLoaded", () => {
    board.init();
});