const boardUpdate = {
  init: function () {
        this.defaultCategory();
  },
  defaultCategory: async function () {
      const boardType = document.querySelector(".boardType").value;
      const categoryValue = document.querySelector("[value=" + boardType + "]");
      categoryValue.setAttribute("selected", "selected");
  }
};

document.addEventListener("DOMContentLoaded", () => {
    boardUpdate.init();
});