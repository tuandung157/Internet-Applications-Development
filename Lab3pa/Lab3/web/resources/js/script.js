
var r = 1;
var x = 3;
var buttoncur;
function buttonX_pressed(button) {
    if(buttoncur)
        buttoncur.style.backgroundColor='aliceblue';
    buttoncur = button;
    button.style.backgroundColor ='rgb(255,102,0)';
}
function changeX(X){
    x=X;
    $("#atrX").val(x);

}

function changeY() {
    $("#atrY").val($(".y_input").val());

}
function init(){
    drawCanvas('canvas',r);
}

function drawCanvas(id, r){
    this.r = r;
    var canvas = document.getElementById(id),
        context = canvas.getContext("2d");
//clean
    context.clearRect(0, 0, canvas.width, canvas.height);
//tamgiac
    context.beginPath();
    // context.moveTo(150, 150);
    // context.lineTo(150, 85);
    // context.lineTo(280,150);
    // context.lineTo(150, 150);
    context.moveTo(150, 150);
    context.lineTo(150, 280);
    context.lineTo(280,150);
    context.lineTo(150, 150);
    context.closePath();
    context.strokeStyle = "cyan";
    context.fillStyle = "cyan";
    context.fill();
    context.stroke();

//hinhchunhat
    context.beginPath();
    // context.rect(20, 150, 130, 130);
    // context.rect(150, 150, 65, 130);
    context.moveTo(150, 150); context.lineTo(85, 150);
    context.lineTo(85, 20); context.lineTo(150, 20);
    context.closePath();
    context.strokeStyle = "cyan";
    context.fillStyle = "cyan";
    context.fill();
    context.stroke();

//tron
    context.beginPath();
    context.moveTo(150, 150);
    context.arc(150, 150, 65, 1.5*Math.PI, Math.PI*2, false);
    context.closePath();
    context.strokeStyle = "cyan";
    context.fillStyle = "cyan";
    context.fill();
    context.stroke();

//vetruc
    context.beginPath();
    context.font = "12px Verdana";
    context.moveTo(150, 0); context.lineTo(150, 300);
    context.moveTo(150, 0); context.lineTo(145, 12);
    context.moveTo(150, 0); context.lineTo(155, 12);
    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();

    context.beginPath();
    context.fillText("Y", 160, 10);
    context.moveTo(0, 150);
    context.lineTo(300, 150);
    context.moveTo(300, 150);
    context.lineTo(288, 145);
    context.moveTo(300, 150);
    context.lineTo(288, 155);
    context.fillText("X", 290, 135);
    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();

//duongx X
    context.beginPath();
    context.moveTo(145, 20);
    context.lineTo(155, 20);
    context.moveTo(145, 85);
    context.lineTo(155, 85);
    context.moveTo(145, 215);
    context.lineTo(155, 215);
    context.moveTo(145, 280);
    context.lineTo(155, 280);
    if (r === 0){
        context.fillText("R", 160, 25);
        context.fillText("R/2", 160, 90);
        context.fillText("-R/2", 160, 220);
        context.fillText("-R", 160, 285);
    } else {
        context.fillText(r, 160, 25);
        context.fillText((r / 2), 160, 90);
        context.fillText(-(r / 2), 160, 220);
        context.fillText(-r, 160, 285);
    }

//duong y
    context.moveTo(20, 145);
    context.lineTo(20, 155);
    context.moveTo(85, 145);
    context.lineTo(85, 155);
    context.moveTo(215, 145);
    context.lineTo(215, 155);
    context.moveTo(280, 145);
    context.lineTo(280, 155);
    if (r===0){
        context.fillText("-R", 12, 140);
        context.fillText("-R/2", 70, 140);
        context.fillText("R/2", 205, 140);
        context.fillText("R", 275, 140);
    } else {
        context.fillText(-r, 12, 140);
        context.fillText(-(r / 2), 70, 140);
        context.fillText((r / 2), 205, 140);
        context.fillText(r, 275, 140);
    }

    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();
}

function clickCanvas(){
    var canvas = document.getElementById('canvas');
    var br = canvas.getBoundingClientRect();
    var left = br.left;
    var top = br.top;
    var event = window.event;
    var x = event.clientX-left;
    var y = event.clientY-top;
    var size = canvas.height;
    if (r>0) {
        x = Math.round((x - size / 2) * r * 10 / 2 / 65) / 10;
        y = Math.round((-y + size / 2) * r * 10 / 2 / 65) / 10;
        drawCanvas('canvas', r);
        //     document.getElementById("form:x_hidden").value = x;
        //     document.getElementById("form:Y").value = y;
        //     yVal = y;
        //     alert(x);
        drawPoint(x, y, r);
        //     document.getElementById('form:validationButton').click();
        document.getElementById('newXYR:y').value=y;
        document.getElementById('newXYR:x').value=x;

        document.getElementById("newXYR:check").click();

    }
}

function drawPoint(x,y,r){
    var color;
    var canvas = document.getElementById('canvas'),
        ctx = canvas.getContext("2d");
    if (isArea(x,y,r)) {
        color = 'yellow';
    } else {
        color = 'red';
    }
    ctx.beginPath();
    ctx.arc(150+x*130/r,150-y*130/r,2,0,2*Math.PI);
    ctx.fillStyle = color;
    ctx.fill();
    ctx.closePath();
}

function isArea(x, y, r) {
    if((y <= r)&&(y >= 0)&&(x >= -r/2)&&(x <= 0))
        return true;
    else if((x >= 0)&&(y >= 0)&&(Math.sqrt(x*x +y*y) <=r/2))
        return true;
    else if((x >= 0)&&y <= 0&&((Math.abs(x)+Math.abs(y)) <= r))
        return true;
    return false;
}