// noinspection JSUnresolvedReference

$(document).ready(function () {
    // 錯誤訊息模態框
    if (messages.length > 0) {
        // 初始化模態框，防止通過點擊模態框外部區域來關閉
        $('#messageModal').modal({
            backdrop: 'static',
            keyboard: false
        });

        // 將內容動態添加
        let listGroup = $(".list-group");
        messages.forEach(function (message) {
            let messageLink = $('<a href="#" class="list-group-item list-group-item-action">' + message + '</a>');
            listGroup.append(messageLink);
        });

        // 顯示模態框
        $("#messageModal").modal("show");
    }
    // 按鈕關閉模態框
    $("#closeMessageBtn").click(function () {
        $("#messageModal").modal("hide");
    });


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

    // 圖片預覽
    function readURL(input, previewId, cancelBtnId) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();

            reader.onload = function (e) {
                $(previewId).attr('src', e.target.result).show();
                $(cancelBtnId).show();
            }

            reader.readAsDataURL(input.files[0]);
        } else {
            $(previewId).hide();
            $(cancelBtnId).hide();
        }
    }

    function clearImage(inputId, previewId, cancelBtnId) {
        $(inputId).val('');
        $(previewId).hide();
        $(cancelBtnId).hide();
    }

    $("#image01").change(function () {
        readURL(this, '#previewImage01', '#cancelImage01');
    });

    $("#cancelImage01").click(function () {
        clearImage('#image01', '#previewImage01', '#cancelImage01');
    });

    $("#image02").change(function () {
        readURL(this, '#previewImage02', '#cancelImage02');
    });

    $("#cancelImage02").click(function () {
        clearImage('#image02', '#previewImage02', '#cancelImage02');
    });


    // 判斷價格,即時顯示錯誤訊息
    const moneyInputs = $('.money');
    moneyInputs.each(function () {
        const input = $(this);
        const errorMessageDiv = $('<div class="error-message text-danger"></div>');
        input.parent().append(errorMessageDiv);

        input.on('blur', function () {
            const value = input.val();
            if (value === '') {
                errorMessageDiv.text('');
                input.removeClass('is-invalid');
            } else if (value === '0' || !/^[1-9]\d*$/.test(value)) {
                errorMessageDiv.text('請填寫整數數字');
                input.addClass('is-invalid');
            } else {
                errorMessageDiv.text('');
                input.removeClass('is-invalid');

                const startPrice = parseInt($('#startprice').val(), 10);
                const reserverPrice = parseInt($('#reserverprice').val(), 10);
                const directPrice = parseInt($('#directprice').val(), 10);

                if (reserverPrice > 0 && reserverPrice < startPrice) {
                    errorMessageDiv.text('拍賣底價不能小於起標價格');
                    input.addClass('is-invalid');
                } else if (directPrice > 0) {
                    if (!reserverPrice) {
                        // 2.1 如果沒有拍賣底價，則應該大於起標價格
                        if (directPrice <= startPrice) {
                            errorMessageDiv.text('立即結標價應該大於起標價格');
                            input.addClass('is-invalid');
                        }
                    } else if (directPrice <= reserverPrice) {
                        // 2.2 如果有拍賣底價，則應該大於拍賣底價
                        errorMessageDiv.text('立即結標價應該大於拍賣底價');
                        input.addClass('is-invalid');
                    }
                }
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

        Swal.fire({
            title: "您確定要提交申請了嗎?",
            text: "競標案需經過管理員審核",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "確認",
            cancelButtonText: "取消"
        }).then((result) => {
            if (result.isConfirmed) {
                let firstInvalidInput = null;
                let errorMessage = '';

                // 檢查類別標籤有無選擇
                const categorySelect = $('#categorySelect');
                if (categorySelect.val().trim() === '') {
                    firstInvalidInput = categorySelect;
                    errorMessage = '請選擇商品類別標籤';
                }

                // 檢查價格欄位是否錯誤
                const priceInputs = $('.money');
                for (let i = 0; i < priceInputs.length; i++) {
                    const input = $(priceInputs[i]);
                    const value = input.val();
                    if (value.trim() !== '' && !/^(0|[1-9]\d*)$/.test(value)) {
                        if (firstInvalidInput === null) {
                            firstInvalidInput = input;
                            errorMessage = '銷售資訊裡的價格欄位，請填有效數字(整數)';
                        }
                    }
                }

                // 檢查價格關係
                const startPrice = parseInt($('#startprice').val(), 10);
                const reserverPrice = parseInt($('#reserverprice').val(), 10);
                const directPrice = parseInt($('#directprice').val(), 10);

                if (!isNaN(reserverPrice) && reserverPrice < startPrice) {
                    firstInvalidInput = $('#reserverprice');
                    errorMessage = '拍賣底價不可低於起標價格';
                }

                if (!isNaN(directPrice)) {
                    if (!isNaN(reserverPrice) && directPrice <= reserverPrice) {
                        firstInvalidInput = $('#directprice');
                        errorMessage = '立即結標價必須高於拍賣底價';
                    } else if (directPrice <= startPrice) {
                        firstInvalidInput = $('#directprice');
                        errorMessage = '立即結標價必須高於起標價格';
                    }
                }

                // 根據第一個錯誤的欄位alert和滾動視窗
                if (firstInvalidInput !== null) {
                    Swal.fire({
                        title: "您提交的資料存在以下問題",
                        text: errorMessage,
                        icon: "error",
                        confirmButtonColor: "#d33",
                        confirmButtonText: "我知道了",
                        allowOutsideClick: false
                    }).then(() => {
                        firstInvalidInput.focus();
                        // 滾動視窗到第一個錯誤欄位
                        const position = firstInvalidInput.offset().top - 100;
                        $('html, body').animate({scrollTop: position}, 500);
                    })

                } else {
                    Swal.fire({
                        title: "提交成功",
                        text: "競標案審核將會在兩天內完成審核",
                        icon: "success"
                    }).then(() => {
                        this.submit();
                    })
                }
            }
        });
    });
})
