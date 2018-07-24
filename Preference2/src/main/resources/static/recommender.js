



btn1 = $("#name1")
btn2 = $("#name2")
btn3 = $("#name3")
btn4 = $("#name4")
btn5 = $("#name5")

btn1.click(function(){
    doPost(0, btn1.text().split(".")[1])
})
btn2.click(function(){
    doPost(1, btn2.text().split(".")[1])
})
btn3.click(function(){
    doPost(2, btn3.text().split(".")[1])
})
btn4.click(function(){
    doPost(3, btn4.text().split(".")[1])
})
btn5.click(function(){
    doPost(4, btn5.text().split(".")[1])
})

place = {1:"한라산@@@자연-숲", 2:"오름@@@자연-숲", 3: "성산일출봉@@@자연-숲", 4 : "섬(우도/마라도 등)@@@자연명소",
    5:"올레길@@@기타", 6: "폭포(정방폭포 등)@@@자연명소", 7: "동굴(만장굴 등)@@@자연명소", 8: "해수욕장@@@자연명소",
    9:"비자림@@@자연-숲",  10:"한라수목원@@@공원", 11: "서귀포자연휴양림@@@자연-숲", 12: "절물자연휴양림@@@자연-숲",
    13:"용두암@@@자연명소", 14:"주상절리대@@@자연명소", 15: "한림공원@@@공원", 16:"국립제주박물관@@@박물관", 17:"도립미술관@@@박물관", 18:"민속자연사박물관@@@박물관",
    19:"제주돌문화공원@@@공원", 20:"제주세계자연유산센터@@@박물관", 21:"이중섭미술관@@@박물관", 22:"서복전시관@@@박물관", 23:"제주4・3평화공원@@@공원",
    24:"동문시장@@@쇼핑", 25:"중앙로지하상가@@@쇼핑", 26:"바오젠거리@@@쇼핑",27:"제주5일장(제주/서귀포)@@@쇼핑",
    28:"서귀포매일올레시장@@@쇼핑", 29:"신라면세점@@@면세점", 30:"롯데면세점@@@면세점", 31:"제주관광공사 면세점@@@면세점", 32: "공항JDC면세점@@@면세점",
    33:"제주목관아@@@문화유적", 34:"항몽유적지@@@문화유적", 35:"성읍민속마을@@@문화유적",
    36:"삼양동선사유적@@@문화유적", 37:"제주추사관@@@문화유적", 38:"관덕정@@@문화유적", 39:"이중섭거주지@@@문화유적", 40:"하멜기념비@@@문화유적",
    41:"미로공원(김녕/메이지)@@@테마파크", 42:"에코랜드@@@테마파크", 43:"제주경마공원@@@체험", 44:"불교사찰@@@기타",
    45:"아쿠아플라넷@@@체험", 46:"테디베어박물관@@@테마파크", 47:"소인국테마파크@@@테마파크", 48:"잠수함관광(서귀포 등)@@@체험",
    49:"신비의도로@@@기타", 50:"생각하는정원@@@공원", 51:"드라마촬영지@@@기타", 52:"제주별빛누리공원@@@테마파크", 53:"유람선(요트투어포함)@@@체험",
    54:"제주바다체험장@@@체험", 55:"골프장@@@기타", 56: "카지노@@@기타"}

var dic = []
dic["자연-숲"] = ["한라산", "오름", "성산일출봉" , "비자림", "서귀포자연휴양림", "절물자연휴양림"]//6
dic["자연명소"] = ["섬(우도/마라도 등)", "폭포(정방폭포 등)", "동굴(만장굴 등)" , "해수욕장", "용두암", "주사절리대"]//6
dic["기타"] = ["올레길", "불교사찰", "신비의도로" , "드라마촬영지", "콜프장", "카지노"]//6
dic["박물관"] = ["국립제주박물관", "도립미술관", "민속자연사박물관" , "제주세계자연유산센터", "이중섭미술관", "서복전시관"]//6
dic["공원"] = ["한라수목원", "한림공원", "제주4・3평화공원" , "제주돌문화공원", "생각하는정원"]//4
dic["쇼핑"] = ["동문시장", "중앙로지하상가", "바오젠거리" , "제주5일장(제주/서귀포)", "서귀포매일올레시장"]//5
dic["면세점"] = ["신라면세점", "롯데면세점", "제주관광공사 면세점" , "공항JDC면세점"]//4
dic["테마파크"] = ["미로공원(김녕/메이지)", "에코랜드", "테디베어박물관" , "소인국테마파크", "제주별빛누리공원"]//5
dic["체험"] = ["제주경마공원", "아쿠아플라넷", "잠수함관광(서귀포 등)" , "유람선(요트투어포함)", "제주바다체험장"]////5
dic["문화유적"] = ["제주목관아", "항몽유적지", "성읍민속마을", "삼양동선사유적",  "제주추사관", "관덕정", "이중섭거주지", "하멜기념비" ]//8





function doPost(selectedNum, seletedItem) {
    btns = [btn1, btn2, btn3, btn4, btn5];
    var form = document.getElementById("form");
    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "selected");
    hiddenField.setAttribute("value", seletedItem);
    form.appendChild(hiddenField);

    for (i = 0 ; i < 5 ; i ++) {
        if (i === selectedNum) {
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
        hiddenField.setAttribute("value", btns[i].text().split(".")[1]);

        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();

};

function pass() {

}
$("#xxx").hover(function () {
    console.log("xxxx")
})


cat1 = $("#cat1")
cat2 = $("#cat2")
cat3 = $("#cat3")
cat4 = $("#cat4")
cat5 = $("#cat5")
prefix = "(";
postfix = ")";

str1 = "<li class='midText'>" + cat1.text().split(prefix)[1].split(postfix)[0] + "에 속한 다른 여행지</li>"
arr1 = dic[cat1.text().split(prefix)[1].split(postfix)[0]];
for(var i = 0 ; i < arr1.length ; i++){
    str1 += "<li><a class='smallText' onclick=cat_list_item_click(this,1)>" + arr1[i]  + "</a></li>"
}

str2 = "<li class='midText'>" + cat2.text().split(prefix)[1].split(postfix)[0] + "에 속한 다른 여행지</li>"

arr2 = dic[cat2.text().split(prefix)[1].split(postfix)[0]];
for(var i = 0 ; i < arr2.length ; i++){
    str2 += "<li><a class='smallText' onclick=cat_list_item_click(this,2)>" + arr2[i]  + "</a></li>"
}

str3 = "<li class='midText'>" + cat3.text().split(prefix)[1].split(postfix)[0] + "에 속한 다른 여행지</li>"

arr3 = dic[cat3.text().split(prefix)[1].split(postfix)[0]];
for(var i = 0 ; i < arr3.length ; i++){
    str3 += "<li><a class='smallText' onclick=cat_list_item_click(this,3)>" + arr3[i]  + "</a></li>"
}

str4 = "<li class='midText'>" + cat4.text().split(prefix)[1].split(postfix)[0] + "에 속한 다른 여행지</li>"

arr4 = dic[cat4.text().split(prefix)[1].split(postfix)[0]];
for(var i = 0 ; i < arr4.length ; i++){
    str4 += "<li><a class='smallText' onclick=cat_list_item_click(this,4)>" + arr4[i]  + "</a></li>"
}

str5 = "<li class='midText'>" + cat5.text().split(prefix)[1].split(postfix)[0] + "에 속한 다른 여행지</li>"

arr5 = dic[cat5.text().split(prefix)[1].split(postfix)[0]];
for(var i = 0 ; i < arr5.length ; i++){
    str5 += "<li><a class='smallText' onclick=cat_list_item_click(this,5)>" + arr5[i]  + "</a></li>"
}

$("#cat_list1").html(str1);
$("#cat_list2").html(str2);
$("#cat_list3").html(str3);
$("#cat_list4").html(str4);
$("#cat_list5").html(str5);
cat1.focus(function () {
    $("#cat_list1").show(300)
})
cat1.blur(function () {
        $("#cat_list1").hide(1000)
})

cat2.focus(function () {
    $("#cat_list2").show(300)
})


cat2.blur(function () {
    $("#cat_list2").hide(1000)
})


cat3.focus(function () {
    $("#cat_list3").show(300)
})


cat3.blur(function () {
    $("#cat_list3").hide(1000)
})





cat4.focus(function () {
    $("#cat_list4").show(300)
})
cat4.blur(function () {
    $("#cat_list4").hide(1000)
})


cat5.focus(function () {
    $("#cat_list5").show(300)
})


cat5.blur(function () {
    $("#cat_list5").hide(1000)
})


function cat_list_item_click(target, selectedNum){

    console.log(target.innerHTML)
    doPost(selectedNum-1,target.innerHTML)

}










