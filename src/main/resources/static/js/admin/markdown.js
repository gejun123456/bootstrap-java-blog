/**
 * Created by bruce.ge on 2017/1/19.
 */

function getMarkdownContent(title, source) {
    var html = converter.makeHtml(source);
    return "<h2>" + title + "</h2>" + html;
}

var converter = new showdown.Converter();
converter.setFlavor('github')
var thisConverterSpecificOptions = converter.getOptions();
// console.log(thisConverterSpecificOptions);
converter.setOption("prefixHeaderId",true);

function markdownRefresh(title, source, output) {
    var html = converter.makeHtml(source);
    output.html("<h2>" + title + "</h2>" + html);
}


function dealWithTableAndMarkDownShortCut(source, e, linkModal, imageModal) {
    var keyCode = e.keyCode || e.which;
    if (keyCode == 9) {
        e.preventDefault();
        var start = source.get(0).selectionStart;
        var end = source.get(0).selectionEnd;

        // set textarea value to: text before caret + tab + text after caret
        source.val(source.val().substring(0, start)
            + "\t"
            + source.val().substring(end));

        // put caret at right position again
        source.get(0).selectionStart =
            source.get(0).selectionEnd = start + 1;
    } else if (e.ctrlKey) {
        var com = source;
        var start = com.prop("selectionStart");
        var end = com.prop("selectionEnd");
        if (keyCode == 66) {
            dealWithB(com, start, end);
        } else if (keyCode == 73) {
            dealwithI(com, start, end);
        } else if (keyCode == 72) {
            e.preventDefault();
            dealwithHead(com, start, end);
        } else if (keyCode == 76) {
            e.preventDefault();
            linkModal.modal({
                keyboard: true
            })
        } else if (keyCode == 71) {
            e.preventDefault();
            imageModal.modal({
                keyboard: true
            })
        } else if (keyCode == 85) {
            e.preventDefault();
            dealWithUnOrder(com, start, end);
        } else if (keyCode == 79) {
            e.preventDefault();
            dealWithOrder(com, start, end);
        } else if (keyCode == 75) {
            e.preventDefault();
            dealWithCode(com, start, end);
        } else if (keyCode == 81) {
            dealWithQuote(com, start, end);
        } else if (keyCode == 77) {
            dealWithMore(com, start, end);
        }
    }
}


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


function start(title, source, output) {
    // var converter = new showdown.Converter();
    // converter.setFlavor('github')
    // var thisConverterSpecificOptions = converter.getOptions();
    // // console.log(thisConverterSpecificOptions);
    // converter.setOption("prefixHeaderId",true);
    function refresh() {
        var text = source.val();
        text = text.replace("<!-more->","");
        console.log("source value is:"+text);
        var html = converter.makeHtml(text);
        var cleanSource = filterXSS(html);
        console.log(cleanSource);
        output.html("<h2>" + title.val() + "</h2>" + cleanSource);
    }

    refresh();

    title.bind("keyup",function () {
        refresh();
    });

    source.bind("keyup",function () {
        refresh();
    });

    source.bind("keydown",function (e) {
        dealWithTableAndMarkDownShortCut($(this), e, $("#myModal"), $("#imageModal"));
    })

    $(".markdownbutton").bind("click",function () {
        var message = $(this).attr('tabindex');
        var com = source;
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
}
