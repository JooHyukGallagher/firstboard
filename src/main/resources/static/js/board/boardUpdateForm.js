const boardUpdate = {
  init: function () {
        this.defaultCategory();
  },
  defaultCategory: async function () {
      const boardId = document.querySelector("#boardId").value;
      const boardResponse = await getData("/api/board/" + boardId);

      const boardType = boardResponse.boardType;
      const categoryValue = document.querySelector("[value=" + boardType + "]");
      categoryValue.setAttribute("selected", "selected");
  }
};

document.addEventListener("DOMContentLoaded", () => {
    boardUpdate.init();
});