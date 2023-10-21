// 獲取"新增"按鈕和表單的引用
var addButton = document.getElementById("addButton");
var selectContainers = document.getElementById("selectContainers");
var itemForm = document.getElementById("itemForm");

// 設置點擊事件
addButton.addEventListener("click", function() {
    // 創建新的下拉選單元素和刪除按鈕
    var memberSelect = document.createElement("select");
    var itemSelect = document.createElement("select");
    var deleteButton = document.createElement("button");

    // 設置刪除按鈕的屬性
    deleteButton.innerHTML = "刪除";
    deleteButton.type = "button";

    // 將選單和刪除按鈕添加到容器中
    selectContainers.appendChild(memberSelect);
    selectContainers.appendChild(itemSelect);
    selectContainers.appendChild(deleteButton);

    // 使用memberList和itemList數據填充下拉選單
    memberList.forEach(function(memberItem) {
        var option = document.createElement("option");
        option.value = memberItem.mbrId;
        option.text = memberItem.mbrName;
        memberSelect.appendChild(option);
    });

    itemList.forEach(function(itemItem) {
        var option = document.createElement("option");
        option.value = itemItem.itemId;
        option.text = itemItem.itemName;
        itemSelect.appendChild(option);
    });

    // 設置刪除按鈕的點擊事件
    deleteButton.addEventListener("click", function() {
        // 刪除這組選單
        selectContainers.removeChild(memberSelect);
        selectContainers.removeChild(itemSelect);
        selectContainers.removeChild(deleteButton);
    });
});

// 表單提交時的處理
itemForm.addEventListener("submit", function(event) {
    // 在這裡可以處理表單提交的邏輯，例如發送AJAX請求
    // 如果需要阻止表單提交，可以使用 event.preventDefault();
});
