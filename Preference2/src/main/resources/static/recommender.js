
btn1 = document.getElementById("0")
btn2 = document.getElementById("1")
btn3 = document.getElementById("2")
btn4 = document.getElementById("3")
btn5 = document.getElementById("4")

btn1.onclick = function (ev) {
    doPost(0)
};
btn2.onclick = function (ev) {
    doPost(1)
};
btn3.onclick = function (ev) {
    doPost(2)
};
btn4.onclick = function (ev) {
    doPost(3)
};
btn5.onclick = function (ev) {
    doPost(4)
};



function doPost(selectedNum) {
    btns = [btn1, btn2, btn3, btn4, btn5];
    var form = document.getElementById("form");
    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "selected");
    hiddenField.setAttribute("value", btns[selectedNum].innerHTML);
    form.appendChild(hiddenField);

    for (i = 0 ; i < 5 ; i ++) {
        if (i == selectedNum) {
            continue;
        }

        console.log(btns.length);
        hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        if (i > selectedNum) {
            hiddenField.setAttribute("name", "other" + i);
        }else{
            hiddenField.setAttribute("name", "other" + (i+1));
        }
        hiddenField.setAttribute("value", btns[i].innerHTML);
        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();

};
