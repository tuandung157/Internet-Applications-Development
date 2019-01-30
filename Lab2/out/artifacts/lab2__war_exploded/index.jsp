<%--
  Created by IntelliJ IDEA.
  User: DucTran
  Date: 11/25/2018
  Time: 3:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <style type="text/css">
    #body{
      background-repeat: no-repeat;
      background-size: cover;
    }
    #name{
      width : 70%;
      background : #EDE7FA;
      color : #010101;
      heigh : 300 px;
      float : left;
      font-size : 50px;
    }
    #variant{
      width : 30%;
      background : #B397F4;
      color : #010101;
      heigh : 300px;
      float : right;
      font-size : 50px;
    }

    .container{
      width : 1000px;
      height: 800px;
      margin : 0 auto;
      color : #FEFEFE;
      position : relative;
      border: 1px solid #333;
      padding : 10px;
      overflow : auto;
      text-align : center;
    }

    table.center{
      margin : 0px auto;
      margin-top : 10px;
    }
    #form1{
      color : #010101;
      font-size: 0.5cm;

    }
    #work{
      margin-top: 2%;
      width : 40%;
      float : right;
    }
    #image{
      margin-top : 2%;
      width : 60%;
      float : left;
    }

  </style>
</head>

<body>
<div id = body class="container">
  <div class = "header-style">
    <div id = name><b>TRAN TRUNG DUC P3202</b></div>
    <div id = variant> 20034 </div>
    <br>
  </div>
  <!-- radius = 150 -->
  <div id = "image">
    <svg id = "mysvg" width="400" height="400" style = "border:1px solid #010101;">
      <rect x="200" y="50" width="75" height="150" fill="rgb(179, 255, 255)" />
      <polygon points="50 200 200 200 200 275 " fill = "rgb(179, 255, 255)" />

      <path d="M 200 200 L 50 200 A 150 150 0 0 1 200 50 z"
            style="fill:rgb(179, 255, 255);fill-opacity: 1;"/>
      <!-- A rx ry x(xoay) 0/1 (0-kim dong ho ) 0/1(0-ngua mat) cx cy(point to x,y) -->

      <line x1 = "25" x2 = "375" y1 ="200" y2 ="200" stroke = "black"/>
      <line x1 = "200" x2 = "200" y1 ="25" y2 ="375" stroke = "black"/>
    </svg>
  </div>

  <div id = "work">
    <form id = "form1" action="control" method="GET" >
      Input X :<br>
      <input type="radio" class ="list" name="valueX" value=-2 required >-2
      <input type="radio" class ="list" name="valueX" value=-1.5>-1.5
      <input type="radio" class ="list" name="valueX" value=-1>-1
      <input type="radio" class ="list" name="valueX" value=-0.5>-0.5
      <input type="radio" class ="list" name="valueX" value=-0>0
      <input type="radio" class ="list" name="valueX" value=0.5>0.5
      <input type="radio" class ="list" name="valueX" value=1>1
      <input type="radio" class ="list" name="valueX" value=1.5>1.5
      <input type="radio" class ="list" name="valueX" value=2>2

      <label for="valueY">Input Y : </label><br>
      <input required type = "text"  id = "valueY" name ="valueY" pattern="-?(\d{1,2})(\.\d{0,3})?"  placeholder="{-5..3}"  oninput="checkInput(this)"> <br>

      Input  R: <br>
      <select name = "valueR">
        <option required  value = -4>1</option>
        <option value = -3>2</option>
        <option value = -2>3</option>
        <option value = -1>4</option>
        <option value = 0>5</option>
      </select>
      <br>

      <button type="submit">Submit </button>
    </form>

    <div style ="margin-top: 2%">
      <table id = "tableresult" class = "center" border="5" width="50%">
        <tr style="width: 300px">
          <th>Value X</th>
          <th>Value Y</th>
          <th>Value R</th>
          <th>Result</th>
          <th>Time current</th>
          <th>Time to do</th>
        </tr>
      </table>
    </div>
  </div>
  <script type="text/javascript">
      var form = document.querySelector('#form1');
      var text = document.querySelector("#valueX");

      form.addEventListener("submit",function(event){
          if (isNaN(text.value) || (text.value <-5) || (text.value >3)){
              text.style.boxShadow= "0 0 10px red";
              event.preventDefault();
          }
      });

  </script>
</div>
</body>
</html>
