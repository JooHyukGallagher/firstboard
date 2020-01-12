const boardRead = {
    init: function () {
        this.defaultCategoryClassify();
    },
    defaultCategoryClassify: async function () {
        const boardType = document.querySelector(".boardType").value;
        const categoryValue = document.querySelector("#" + boardType);
        categoryValue.classList.add("active");
    }
};

document.addEventListener("DOMContentLoaded", () => {
    boardRead.init();
});