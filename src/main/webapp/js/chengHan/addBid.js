$(document).ready(function () {
    // 商品簡述字串檢查及轉換
    const textarea = $("#fakedetail");
    const hiddenInput = $("#detail");
    // 設置每行的最大字數(方便之後輸出時排版)
    const maxLengthPerLine = 40;
    // 監聽輸入，行數超過自動折行
    textarea.on("input", function () {
        const lines = $(this).val().split("\n");
        for (let i = 0; i < lines.length; i++) {
            if (lines[i].length > maxLengthPerLine) {
                const remainder = lines[i].substring(maxLengthPerLine);
                lines[i] = lines[i].substring(0, maxLengthPerLine);
                if (i === lines.length - 1) {
                    lines.push(remainder);
                } else {
                    lines[i + 1] = remainder + (lines[i + 1] || "");
                }
            }
        }
        $(this).val(lines.join("\n"));
        // 將文本格式化(跨行換<br>)後，複製到隱藏的input
        const formattedText = $(this).val().replace(/\n/g, "<br>");
        hiddenInput.val(formattedText);
    });


    // 判斷價格,即時顯示錯誤訊息
    const moneyInputs = $('.money');
    moneyInputs.each(function () {
        const input = $(this);
        const errorMessage = $('<div class="error-message text-danger"></div>');
        input.parent().append(errorMessage);
        input.on('blur', function () {
            const value = input.val();
            if (value.trim() !== '' && !/^(0|[1-9]\d*)$/.test(value)) {
                errorMessage.text('請填寫整數數字');
                input.addClass('is-invalid');
            } else {
                errorMessage.text('');
                input.removeClass('is-invalid');
            }
        });
    });


    // 刊登時間
    const $radioButton1 = $("#btnradio1");
    const $radioButton2 = $("#btnradio2");
    const $datePickerContainer = $("#datePickerContainer");
    const $dateInput = $("#starttime");
    const $description = $("#timeDesc");

    // 計算可選的最小日期（當天+2）
    const minDate = new Date();
    minDate.setDate(minDate.getDate() + 2);
    // 計算可選的最大日期（當天+10）
    const maxDate = new Date();
    maxDate.setDate(maxDate.getDate() + 10);
    // 限制可選的日期範圍
    $dateInput.attr("min", formatDate(minDate));
    $dateInput.attr("max", formatDate(maxDate));

    // 格式化date
    function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");
        return `${year}-${month}-${day}`;
    }

    // 根據當前單選按鈕的選中狀態來設置日期選擇容器的是否隱藏
    function updateUI() {
        const isReservationSelected = $radioButton2.prop("checked");
        $datePickerContainer.css("display", isReservationSelected ? "block" : "none");
        $dateInput.prop("required", isReservationSelected);

        if (isReservationSelected) {
            $dateInput.trigger("focus");
            $description.text("通過審核後，競標將於所選日期的中午12點開始，並於該日期的7天後中午12點5分結束。");
        } else {
            $description.text("通過審核後，競標將於當天中午12點開始，並於7天後中午12點5分結束。");
            $dateInput.val("");
        }
    }

    // 監聽按鈕變化
    $radioButton1.change(updateUI);
    $radioButton2.change(updateUI);
    updateUI();


    // 表單提交時檢查欄位
    $('form').on('submit', function (e) {
        e.preventDefault();

        if (window.confirm("您確定要提交申請了嗎?")) {
            let firstInvalidInput = null;

            // 檢查類別標籤有無選擇
            const categorySelect = $('#categorySelect');
            if (categorySelect.val().trim() === '') {
                firstInvalidInput = categorySelect;
            }

            // 檢查價格欄位是否錯誤
            const priceInputs = $('.money');
            for (let i = 0; i < priceInputs.length; i++) {
                const input = $(priceInputs[i]);
                const value = input.val();
                if (value.trim() !== '' && !/^(0|[1-9]\d*)$/.test(value)) {
                    if (firstInvalidInput === null) {
                        firstInvalidInput = input;
                    }
                }
            }

            // 根據第一個錯誤的欄位alert
            if (firstInvalidInput !== null) {
                const errorMessage = firstInvalidInput === categorySelect
                    ? '請選擇商品類別標籤'
                    : '銷售資訊裡的價格欄位，請填有效數字(整數)';
                alert(errorMessage);
                firstInvalidInput.focus();

                // 滾動視窗到第一個錯誤欄位
                const position = firstInvalidInput.offset().top - 100;
                $('html, body').animate({scrollTop: position}, 500);
            } else {
                this.submit();
            }
        }
    });

})
