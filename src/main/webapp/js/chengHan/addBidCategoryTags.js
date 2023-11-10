$(document).ready(function () {
    const categorySelect = $("#categorySelect");
    const selectedCategoryId = $("#selectedCategoryId");
    const categoryModal = new bootstrap.Modal(document.getElementById("categoryModal"));
    const categoryTreeContainer = $("#categoryTree");
    // 點擊或是獲得焦點，打開模態框
    categorySelect.on("focus click", function () {
        categoryModal.show();
    });

    // 資料結構categoryData改在html裡宣告

    // 根據選項的ID,獲得完整的路徑
    function getFullCategoryName(categoryId, data) {
        const category = data.find((item) => item.id === categoryId);
        if (category.parentId !== 1) {
            const parentCategory = getFullCategoryName(category.parentId, data);
            return `${parentCategory} ➽ ${category.name}`;
        }
        return category.name;
    }

    // 根據輸入的參數,動態生成樹狀結構
    function buildCategoryTree(data, parentId) {
        const tree = document.createElement("ul");
        data.forEach((category) => {
            if (category.parentId === parentId) {
                // 檢查該標籤是否是葉子(不存在子標籤)
                const isLeafCategory = !data.some((childCategory) => childCategory.parentId === category.id);
                const listItem = document.createElement("li");
                // 添加選項的底邊距離
                listItem.classList.add("mb-3");
                listItem.textContent = category.name;
                // 替可選跟不可選的標籤添加class(方便css改變樣式)
                if (isLeafCategory) {
                    listItem.classList.add("selectable");
                } else {
                    listItem.classList.add("non-selectable");
                }
                // 綁定點擊事件
                listItem.addEventListener("click", function () {
                    // 還有子標籤的標籤不能被選擇
                    if (isLeafCategory) {
                        // 隱藏框儲存標籤Id
                        selectedCategoryId.val(category.id);
                        // 文本框顯示完整路徑內容
                        categorySelect.val(getFullCategoryName(category.id, categoryData));
                        categoryModal.hide();
                    }
                });
                const childTree = buildCategoryTree(data, category.id);
                if (childTree.childElementCount > 0) {
                    listItem.appendChild(childTree);
                }
                tree.appendChild(listItem);
            }
        });
        return tree;
    }

    // 丟入參數 添加內容
    const categoryTree = buildCategoryTree(categoryData, 1);
    categoryTreeContainer.append(categoryTree);
});