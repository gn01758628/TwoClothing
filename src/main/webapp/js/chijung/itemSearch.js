// yourScript.js
let clickedIdSubsList = [];
let clickedIdParentsList = [];
$(document).ready(function () {
    // Bootstrap初始化
    let collapseElementList = [];
//    let itemList = [];
//
//    $.get('/TwoClothing/front_end/itemsearchServlet?action=getCategoryTags', function (data) {
//        itemList = data;
//        for (let i = 0; i < data.length; i++) {
//            let currentElement = data[i];
//            let nextElement = data[i + 1];
//            let isAccordionStyle = nextElement && nextElement.superTagId === currentElement.tagId;
//
//            let generateAccordionItem = function (id, categoryName) {
//                let accordionItem = `
//                    <div class="accordion-item">
//                        <h5 class="accordion-header hovered">
//                            <a href="#" id="${id}">${categoryName}</a>
//                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
//                                    data-bs-target="#collapse${id}" aria-expanded="false" aria-controls="collapse${id}">  </button>
//                        </h5>
//                        <div id="collapse${id}" class="accordion-collapse collapse" aria-labelledby="heading${id}"
//                            data-bs-parent="#accordionExample">
//                            <div class="accordion-body" id="c${id}">
//                            </div>
//                        </div>
//                    </div>
//                `;
//                $(containerId).append(accordionItem);
//
//                let newCollapseElement = document.getElementById(`collapse${id}`);
//                if (newCollapseElement.classList.contains('collapse')) {
//                    collapseElementList.push(newCollapseElement);
//                }
//            };
//
//            let containerId = currentElement.superTagId === -1 ? ".itemSearch" : `#c${currentElement.superTagId}`;
//            if (isAccordionStyle) {
//                generateAccordionItem(currentElement.tagId, currentElement.categoryName);
//            } else {
//                $(containerId).append(`<div class="hovered"><a href="#" id="${currentElement.tagId}" >${currentElement.categoryName}</a></div>`);
//            }
//        }
//
//        let collapseList = collapseElementList.map(function (collapseEl) {
//            collapseEl.addEventListener('hidden.bs.collapse', function () {
//                let children = this.querySelectorAll('.collapse.show');
//                children.forEach((c) => {
//                    let collapse = bootstrap.Collapse.getInstance(c);
//                    collapse.hide();
//                });
//            });
//        });
//    }, 'json');

    

    // 使用 jQuery 綁定事件
    $('.itemSearch').on('click', 'a', function (event) {
        event.preventDefault();
        clickedIdSubsList = [];
        clickedIdParentsList = [];

        let clickedId = $(this).attr('id');
        clickedIdSubsList.push(clickedId);
        findAccordionBody(clickedId);

        clickedIdParentsList.unshift(clickedId);
        let accordionBodyId = $(this).closest('.accordion-body').attr('id');
        if (accordionBodyId) {
            $(this).parents('.accordion-body').each(function () {
                clickedIdParentsList.unshift(this.id.replace('c', ''));
            });
        }
        console.log("clickedIdSubsList:"+clickedIdSubsList);
        console.log("clickedIdParentsList:"+clickedIdParentsList);
//        let filteredData = filterItemListByIds(clickedIdSubsList, itemList);
        let filteredData = filterItemListByIds(clickedIdSubsList, myList);
        // 遍歷 filteredData 並印出每個對象的內容
		filteredData.forEach(function (item) {
		    console.log("Tag ID: " + item.tagId + ", Other Properties: " + JSON.stringify(item));
		});
    });
    
});
// 尋找對應的 div 並執行相應的操作
    function findAccordionBody(clickedId) {
        let accordionBodyId = 'c' + clickedId;
        let accordionBody = document.getElementById(accordionBodyId);

        if (accordionBody) {
            // Element with the corresponding id found
            let innerId;
            $(accordionBody).find('a').each(function () {
                innerId = $(this).attr('id');
                clickedIdSubsList.push(innerId);
            });
        } else {
            // Element with the corresponding id not found
        }
    }

    function filterItemListByIds(idList, itemList) {
        return itemList.filter(function (item) {
            return idList.includes(item.tagId.toString());
        });
    }
