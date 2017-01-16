$.extend($.validator.messages, {
    required: "這是必填字段",
    remote: "請修正此字段",
    email: "請輸入有效的電子郵件地址",
    url: "請輸入有效的網址",
    date: "請輸入有效的日期",
    dateISO: "請輸入有效的日期 (YYYY-MM-DD)",
    number: "請輸入有效的數字",
    digits: "只能輸入數字",
    creditcard: "請輸入有效的信用卡號碼",
    equalTo: "妳的輸入不相同",
    extension: "請輸入有效的後綴",
    maxlength: $.validator.format("最多可以輸入 {0} 個字符"),
    minlength: $.validator.format("最少要輸入 {0} 個字符"),
    rangelength: $.validator.format("請輸入長度在 {0} 到 {1} 之間的字符串"),
    range: $.validator.format("請輸入範圍在 {0} 到 {1} 之間的數值"),
    max: $.validator.format("請輸入不大于 {0} 的數值"),
    min: $.validator.format("請輸入不小于 {0} 的數值")
});


var messageStrings = {
    userNameExist: '用戶名已經存在，請重新輸入',
    mobileExist: '手機號已存在，請重新輸入',
    emailExist: '郵箱已存在，請重新輸入',
    validateFail: "驗證失敗，請重新輸入"
};