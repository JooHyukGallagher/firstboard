const boardList = {
    init: function () {
        const categoryList = document.querySelector("#category");
        categoryList.addEventListener("click", (evt) => {
            const categoryButton = evt.target;
            if(categoryButton.classList.contains("nav-link")){
                console.log(categoryButton.id);
            }
        })
    }
};

document.addEventListener("DOMContentLoaded", () => {
    boardList.init();
});