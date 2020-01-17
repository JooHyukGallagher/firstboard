const PAGE_LIMIT = 5;

const boardCategory = {
    currentBoardType: "ALL",
    prevButton: document.querySelector("#ALL"),
    currentButton: "",
    init: function () {
        this.registCategoryButton();
    },
    registCategoryButton: function () {
        const categoryList = document.querySelector("#category");
        categoryList.addEventListener("click", (evt) => {
            boardPage.currentPageNum = 1;
            boardCategory.changeBoardTypeButton(evt.target);
            boardCategory.changeBoardType(evt.target);
            boardList.requestBoardList(evt.target);
        })
    },
    changeBoardTypeButton: function (clickecCategoryButton) {
        boardCategory.prevButton.classList.remove("active");
        boardCategory.currentButton = clickecCategoryButton;
        boardCategory.currentButton.classList.add("active");
        boardCategory.prevButton = clickecCategoryButton;
    },
    changeBoardType: function (clickedCategoryButton) {
        boardCategory.currentBoardType = clickedCategoryButton.id;
    }
};

const boardList = {
    init: async function (boardPageData) {
        this.initBoardListPage(boardPageData);
    },
    initBoardListPage: function (boardPageData) {
        boardList.createBoardList(boardPageData.content);
    },
    requestBoardList: async function (clickedCategoryButton) {
        if (clickedCategoryButton.classList.contains("nav-link")) {
            const boardPageData = await getData("/api/board/list?boardType=" + clickedCategoryButton.id);
            boardPage.init(boardPageData);
            boardList.createBoardList(boardPageData.content);
        }
    },
    createBoardList: function (boardListResponseList) {
        // 기존 리스트 삭제
        const boardListContainer = document.querySelector("#tbody");
        boardListContainer.innerHTML = "";

        boardListResponseList.forEach(boardListResponse => {
            const data = boardList.createBindingData(boardListResponse);
            const boardListTemplate = document.querySelector("#boardListTemplate").innerHTML;

            const bindTemplate = Handlebars.compile(boardListTemplate);
            const result = bindTemplate(data);
            boardListContainer.innerHTML += result;
        });
    },
    createBindingData: function (boardListResponse) {
        return {
            id: boardListResponse.id,
            title: boardListResponse.title,
            replyCount: boardListResponse.replyCount,
            boardType: boardListResponse.boardType,
            nickname: boardListResponse.nickname,
            viewCount: boardListResponse.viewCount,
            createDate: boardListResponse.createDate
        };
    }
};

const boardPage = {
    prevPageButton: "",
    totalPageCount: 0,
    currentPageNum: 1,
    init: function (boardPageData) {
        boardPage.totalPageCount = boardPageData.totalPages;
        this.createPageButtonList(boardPageData);
        this.registPageButton();

        boardPage.prevPageButton = document.getElementById(boardPage.currentPageNum);
        boardPage.prevPageButton.classList.add("active");
    },
    createPageButtonList: function () {
        const firstPage = PAGE_LIMIT * Math.floor((boardPage.currentPageNum - 1) / PAGE_LIMIT) + 1;
        let lastPage = firstPage + PAGE_LIMIT - 1;
        if (boardPage.totalPageCount < firstPage + PAGE_LIMIT - 1) {
            lastPage = boardPage.totalPageCount;
        }

        const pagination = document.querySelector(".pagination");
        pagination.innerHTML = "";
        const pageFirstButtonTemplate = document.querySelector("#pageFirstButtonTemplate").innerHTML;
        const pagePrevButtonTemplate = document.querySelector("#pagePrevButtonTemplate").innerHTML;
        pagination.insertAdjacentHTML("beforeend", pageFirstButtonTemplate);
        pagination.insertAdjacentHTML("beforeend", pagePrevButtonTemplate);

        boardPage.appendPageButtons(pagination, firstPage, lastPage);

        const pageNextButtonTemplate = document.querySelector("#pageNextButtonTemplate").innerHTML;
        const pageLastButtonTemplate = document.querySelector("#pageLastButtonTemplate").innerHTML;
        pagination.insertAdjacentHTML("beforeend", pageNextButtonTemplate);
        pagination.insertAdjacentHTML("beforeend", pageLastButtonTemplate);
    },
    appendPageButtons: function (paginationTemplate, firstPage, lastPage) {
        for (let i = firstPage; i <= lastPage; i++) {
            const pageButtonTemplate = document.querySelector("#pageButtonTemplate").innerHTML;
            const bindTemplate = Handlebars.compile(pageButtonTemplate);
            const data = {
                currentPage: i
            };
            const resultPageButtonTemplate = bindTemplate(data);
            paginationTemplate.insertAdjacentHTML("beforeend", resultPageButtonTemplate);
        }
    },
    registPageButton: function () {
        const paginationTemplate = document.querySelector(".pagination");
        paginationTemplate.addEventListener("click", boardPage.requestBoardListByPageNum);
    },
    requestBoardListByPageNum: async function (evt) {
        const clickedButtonText = evt.target.innerText;
        if (clickedButtonText === "처음") {
            boardPage.currentPageNum = 1;
            await boardPage.createBoardListByPageNum();
            boardPage.activeCurrentPageButton();
        } else if (clickedButtonText === "마지막") {
            boardPage.currentPageNum = boardPage.totalPageCount;
            await boardPage.createBoardListByPageNum();
            boardPage.activeCurrentPageButton();
        } else if (clickedButtonText === "«") {
            boardPage.currentPageNum = parseInt(boardPage.currentPageNum) - 1;
            await boardPage.createBoardListByPageNum();
            boardPage.activeCurrentPageButton();
        } else if (clickedButtonText === "»") {
            boardPage.currentPageNum = parseInt(boardPage.currentPageNum) + 1;
            await boardPage.createBoardListByPageNum();
            boardPage.activeCurrentPageButton();
        } else {
            boardPage.currentPageNum = parseInt(clickedButtonText);
            await boardPage.createBoardListByPageNum();
            boardPage.activeCurrentPageButton();
        }
        if (boardPage.currentPageNum === 1){
            boardPage.activeNextButton();
            boardPage.disablePrevButton();
        } else if (boardPage.currentPageNum === boardPage.totalPageCount){
            boardPage.activePrevButton();
            boardPage.disableNextButton();
        } else {
            boardPage.activePrevButton();
            boardPage.activeNextButton();
        }
    },
    createBoardListByPageNum: async function () {
        const page = parseInt(boardPage.currentPageNum) - 1;
        const requestUrl = "/api/board/list?page=" + page + "&boardType=" + boardCategory.currentBoardType;
        const boardPageData = await getData(requestUrl);
        boardPage.createPageButtonList(boardPageData);
        boardList.createBoardList(boardPageData.content);
    },
    activeCurrentPageButton: function () {
        // 이전 버튼 활성 없애기
        boardPage.prevPageButton.classList.remove("active");
        // 현재 버튼 활성 시키기
        const currentPageButton = document.getElementById(boardPage.currentPageNum);
        currentPageButton.classList.add("active");
        boardPage.prevPageButton = currentPageButton;
    },
    activePrevButton: function () {
        const prevButton = document.querySelector(".page-item > #prev").parentElement;
        prevButton.classList.remove("disabled");
    },
    disablePrevButton: function () {
        const prevButton = document.querySelector(".page-item > #prev").parentElement;
        prevButton.classList.add("disabled");
    },
    activeNextButton: function () {
        const nextButton = document.querySelector(".page-item > #next").parentElement;
        nextButton.classList.remove("disabled");
    },
    disableNextButton: function () {
        const nextButton = document.querySelector(".page-item > #next").parentElement;
        nextButton.classList.add("disabled");
    }
};


document.addEventListener("DOMContentLoaded", async () => {
    const boardPageData = await getData("/api/board/list?boardType=ALL");
    boardCategory.init();
    boardList.init(boardPageData);
    boardPage.init(boardPageData);
});