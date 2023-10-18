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

    // 表單提交時檢查欄位
    $('form').on('submit', function (e) {
        e.preventDefault();
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
    });
})
