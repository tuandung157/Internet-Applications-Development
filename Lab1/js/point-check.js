"use strict"
let drawPlot = (function() {
    // Constants
    const CANVAS_NAME = "plot-canvas";
    const CANVAS_ELEMENT = document.getElementById(CANVAS_NAME);

    // Events
    // ------------

    document.getElementById("back-link").addEventListener(
        "click", () => backLinkClicked());

    window.addEventListener("resize", 
        () => limitHeight("last-results", CANVAS_NAME));
    window.addEventListener("load",
        () => limitHeight("last-results", CANVAS_NAME));

    // Event functions
    // ------------

    function backLinkClicked() {
        const EXPECTED_PAGE = "index.html";
        const prevPage = document.referrer;
        if (prevPage.slice(-EXPECTED_PAGE.length) === EXPECTED_PAGE) {
             window.history.back();
        }
    }

    function limitHeight(element, otherElement) {
        const el1 = document.getElementById(element);
        const el2 = document.getElementById(otherElement);
        el1.style.height = el2.clientHeight + "px";
    }


    // Drawing plot
    // ------------

    // Draws point on a plot. Assumes x, y, and r are valid numbers.
    function drawPoint(x, y, r) {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = "#EE0000";
        ctx.beginPath();
        ctx.arc(200 + 120 * x / r, 200 - 120 * y / r, 5, 0, 2 * Math.PI);
        ctx.fill();
    }

    function drawPlotLines() {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.strokeStyle = "#000000";
        ctx.lineWidth = 1;

        ctx.beginPath();

        // Y
        ctx.moveTo(200, 375);
        ctx.lineTo(200, 25);
        ctx.stroke();

        ctx.lineTo(205, 30);
        ctx.stroke();

        ctx.moveTo(200, 25);
        ctx.lineTo(195, 30);
        ctx.stroke();

        // X
        ctx.moveTo(25, 200);
        ctx.lineTo(375, 200);
        ctx.stroke();

        ctx.lineTo(370, 195);
        ctx.stroke();

        ctx.moveTo(375, 200);
        ctx.lineTo(370, 205);
        ctx.stroke();
    }

    function drawSquare() {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = "#3399FF";
        ctx.fillRect(200, 80, 60, 120);
    }

    function drawTriangle() {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = "#3399FF";
        ctx.strokeStyle = "#3399FF";
        ctx.beginPath();
        ctx.moveTo(200, 200);
        ctx.lineTo(320, 200);
        ctx.lineTo(200, 320);
        ctx.fill();
    }

    function drawQuarterCircle() {
        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.fillStyle = "#3399FF";

        ctx.beginPath();
        ctx.moveTo(200, 200);
        ctx.arc(200, 200, 120, 1 * Math.PI, 1.5 * Math.PI);
        ctx.fill();
        ctx.closePath();
    }

    function drawLabels(r = "R") {
        if (isNaN(r) && r !== "R")
            throw "Invalid argument 'r'.";

        const halfR = (r === "R") ? "R/2" : parseFloat(r) / 2.0;

        const ctx = CANVAS_ELEMENT.getContext("2d");
        ctx.strokeStyle = "#000000";
        ctx.font = "14px Monospace";
        ctx.fillStyle = "#000000";

        ctx.beginPath();

        // X
        ctx.moveTo(80, 196);
        ctx.lineTo(80, 204);
        ctx.stroke();

        ctx.fillText("-" + r, 72, 190);

        ctx.moveTo(140, 196);
        ctx.lineTo(140, 204);
        ctx.stroke();

        ctx.fillText("-" + halfR, 125, 190);

        ctx.moveTo(260, 196);
        ctx.lineTo(260, 204);
        ctx.stroke();

        ctx.fillText(halfR, 250, 190);

        ctx.moveTo(320, 196);
        ctx.lineTo(320, 204);
        ctx.stroke();

        ctx.fillText(r, 316, 190);

        ctx.fillText("X", 365, 190);

        // Y
        ctx.moveTo(196, 320);
        ctx.lineTo(204, 320);
        ctx.stroke();

        ctx.fillText("-" + r, 206, 324);

        ctx.moveTo(196, 260);
        ctx.lineTo(204, 260);
        ctx.stroke();

        ctx.fillText("-" + halfR, 206, 264);

        ctx.moveTo(196, 140);
        ctx.lineTo(204, 140);
        ctx.stroke();

        ctx.fillText(halfR, 206, 144);

        ctx.moveTo(196, 80);
        ctx.lineTo(204, 80);
        ctx.stroke();

        ctx.fillText(r, 206, 84);
        ctx.fillText("Y", 206, 29);
    }

    function drawPlot() {
        drawSquare()
        drawTriangle();
        drawQuarterCircle();
        drawPlotLines();
    }

    // Export
    var drawPlotValid = function(x, y, r) {
        if (isNaN(x))
            throw "Invalid argument 'x'.";
        if (isNaN(y))
            throw "Invalid argument 'y'.";
        if (isNaN(r))
            throw "Invalid argument 'r'.";

        drawPlot();
        drawPoint(x, y, r);
        drawLabels(r);
    }
    var drawPlotInvalid = function() {
        drawPlot();
        drawLabels();
    }
    return { drawPlotValid, drawPlotInvalid };
})();