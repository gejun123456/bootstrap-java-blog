var converter = new showdown.Converter();
$("#source").keyup(function () {
    refresh()
})

function refresh() {
    var text = $("#source").val();
    var html = converter.makeHtml(text);
    html = "<h2>" + $("#source_title").val() + "</h2>" + html;
    $("#output").html(html)
}

$("#source_title").keyup(function () {
    refresh()
})

$("#source").keydown(function (e) {
    var keyCode = e.keyCode || e.which;
    if (keyCode == 9) {
        e.preventDefault();
        var start = $(this).get(0).selectionStart;
        var end = $(this).get(0).selectionEnd;

        // set textarea value to: text before caret + tab + text after caret
        $(this).val($(this).val().substring(0, start)
            + "\t"
            + $(this).val().substring(end));

        // put caret at right position again
        $(this).get(0).selectionStart =
            $(this).get(0).selectionEnd = start + 1;
    }
})

$("#savebutton").click(function () {
    var title = $("#source_title").val();
    var text = $("#source").val();
    console.log(title)
    console.log(text);
//            send ajax request to save the content.
    $.ajax({
        url: "/addContent",
        data: {
            title: title,
            sourceContent: text
        },
        type: "POST",
        dataType: "json",
    }).done(function (json) {
        console.log(json);
        window.location.href = "/";
    })
})

function dealWithB(com, start, end) {
    if (start == end) {
        com.val(com.val().substring(0, start) + "****" + com.val().substring(end));
        com.prop("selectionStart", start + 2);
        com.prop("selectionEnd", start + 2);
    } else {
        com.val(com.val().substring(0, start) + "**" + com.val().substring(start, end) + "**" + com.val().substring(end));
        com.prop("selectionStart", start + 2);
        com.prop("selectionEnd", end + 2);
    }
}

function dealwithI(com, start, end) {
    if (start == end) {
        com.val(com.val().substring(0, start) + "**" + com.val().substring(end));
        com.prop("selectionStart", start + 1);
        com.prop("selectionEnd", start + 1);
    } else {
        com.val(com.val().substring(0, start) + "*" + com.val().substring(start, end) + "*" + com.val().substring(end));
        com.prop("selectionStart", start + 1);
        com.prop("selectionEnd", end + 1);
    }
}

function dealwithHead(com, start, end) {
    if (start == end) {
        com.val(com.val().substring(0, start) + "######" + com.val().substring(end));
        com.prop("selectionStart", start + 3);
        com.prop("selectionEnd", start + 3);
    } else {
        com.val(com.val().substring(0, start) + "###" + com.val().substring(start, end) + "###" + com.val().substring(end));
        com.prop("selectionStart", start + 3);
        com.prop("selectionEnd", end + 3);
    }
}

$("#link_url").keydown(function (e) {
    if (event.keyCode == 13) {
        dealWithLink();
    }
})

$("#link_save").click(function () {
    dealWithLink();
})

$('#myModal').on('hidden.bs.modal', function (e) {
    var com = $('#source');
    com.focus();
})

$('#imageModal').on('hidden.bs.modal', function (e) {
    var com = $('#source');
    com.focus();
})

$('#imageModal').on('shown.bs.modal', function (e) {
    $("#image_file").val("");
    $("#image_link").val("http://");
    $("#image_link").focus();
    $("#image_link").prop("selectionStart", 8);
})

$('#myModal').on('shown.bs.modal', function (e) {
    $("#link_url").val("http://");
    $("#link_url").focus();
    $("#link_url").prop("selectionStart", 8);
})

function dealWithImage(response) {
    var url = response;
    $("#imageModal").modal('hide');
    var com = $('#source');
    var start = com.prop("selectionStart");
    var end = com.prop("selectionEnd");
    var width = $('#image_width').val()
    var text = '![' + $("#image_title").val() + ']' + '(' + url;
    console.log(width);
    if (!isNaN(width) && width != "") {
        text += ' =' + width + 'x*' + ')';
    } else {
        text += ')';
    }
    var c = text.length;
    com.val(com.val().substring(0, end) + text + com.val().substring(end));
    com.prop("selectionStart", end);
    com.prop("selectionEnd", end + c);
    refresh();
}

function dealWithLink() {
    var url = $("#link_url").val();
    $("#myModal").modal('hide');
    var com = $('#source');
    var start = com.prop("selectionStart");
    var end = com.prop("selectionEnd");
    var text = '[' + url + ']' + '(' + url + ')';
    var c = text.length;
    com.val(com.val().substring(0, end) + text + com.val().substring(end));
    com.prop("selectionStart", end);
    com.prop("selectionEnd", end + c);
    refresh();
}

function dealWithUnOrder(com, start, end) {
    if (end == 0) {
        com.val("- ");
        com.prop("selectionStart", end + 2);
        com.prop("selectionEnd", end + 2);
    } else {
        com.val(com.val().substring(0, end) + "\n-" + " " + com.val().substring(end));
        com.prop("selectionStart", end + 3);
        com.prop("selectionEnd", end + 3);
    }
}

function dealWithOrder(com, start, end) {
    if (end == 0) {
        com.val("1. ");
        com.prop("selectionStart", end + 3);
        com.prop("selectionEnd", end + 3);
    } else {
        com.val(com.val().substring(0, end) + "\n1." + " " + com.val().substring(end));
        com.prop("selectionStart", end + 4);
        com.prop("selectionEnd", end + 4);
    }
}

function dealWithQuote(com, start, end) {
    if (end == 0) {
        com.val("> ");
        com.prop("selectionStart", end + 2);
        com.prop("selectionEnd", end + 2);
    } else {
        com.val(com.val().substring(0, end) + "\n>" + " " + com.val().substring(end));
        com.prop("selectionStart", end + 3);
        com.prop("selectionEnd", end + 3);
    }
}

function dealWithCode(com, start, end) {
    if (start == end) {
        com.val(com.val().substring(0, start) + "``" + com.val().substring(end));
        com.prop("selectionStart", start + 1);
        com.prop("selectionEnd", start + 1);
        return;
    } else {
        if (start != 0) {
            var c = com.val().charAt(start - 1);
            if (c != " " && c != "\n" && c != "\t") {
                com.val(com.val().substring(0, start) + "`" + com.val().substring(start, end) + "`" + com.val().substring(end));
                com.prop("selectionStart", start + 1);
                com.prop("selectionEnd", end + 1);
                return;
            }
        }
        //other situation just use \t.
        var begin = com.val().substring(0, start);
        var endtext = com.val().substring(end);
        var ss = com.val().substring(start, end)
        var sslater = "\n\t";
//                最好ss的开头是一个字符
        for (var x = 0; x < ss.length; x++) {
            sslater += ss[x];
            if (ss[x] == '\n' && x != ss.length - 1) {
                sslater += "\t"
            }
        }
        com.val(begin + sslater + endtext);
        com.prop("selectionStart", start + sslater.length);
        com.prop("selectionEnd", start + sslater.length);

    }

}

function dealWithMore(com, start, end) {
    com.val(com.val().substring(0, end) + "<!-more->" + com.val().substring(end));
    com.prop("selectionStart", end + 9);
    com.prop("selectionEnd", end + 9);
};
$(".btn-default.btn-sm.btn").click(function () {
    var message = $(this).attr('tabindex');
    var com = $('#source');
    var start = com.prop("selectionStart");
    var end = com.prop("selectionEnd");
    if (message == 1) {
        //the rest is much the same.
        dealWithB(com, start, end);
    } else if (message == 2) {
        dealwithI(com, start, end);
    } else if (message == 3) {
        dealwithHead(com, start, end);
    } else if (message == 4) {
        //the link //need to output the modal.
//                dealWithLink(com,start,end);
        $('#myModal').modal({
            keyboard: true
        })
        return;
    } else if (message == 5) {
        $('#imageModal').modal({
            keyboard: true
        })
        return;
    } else if (message == 6) {
        dealWithUnOrder(com, start, end);
    } else if (message == 7) {
        dealWithOrder(com, start, end);
    } else if (message == 9) {
        dealWithQuote(com, start, end);
    } else if (message == 8) {
        dealWithCode(com, start, end);
    } else if (message == 10) {
        dealWithMore(com, start, end);
    }
    com.focus();
    refresh();
})



