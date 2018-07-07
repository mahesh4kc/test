<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
<!--
.styling{
background-color:black;
color:lime;
font: bold 16px MS Sans Serif;
padding: 3px;
text-align: right;
}
-->
</style>
</head>
<body>
<span id="digitalclock" class="styling" ></span>

<script>
<!--

//LCD Clock script- by javascriptkit.com
//Visit JavaScript Kit (http://javascriptkit.com) for script
//Credit must stay intact for use

var alternate=0
var standardbrowser=!document.all&&!document.getElementById

if (standardbrowser)
document.write('<form name="tick"><input type="text" name="tock" size="11"></form>')

function show(){
if (!standardbrowser)
var clockobj=document.getElementById? document.getElementById("digitalclock") : document.all.digitalclock
var Digital=new Date()
var hours=Digital.getHours()
var minutes=Digital.getMinutes()
var dn="AM"

if (hours==12) dn="PM" 
if (hours>12){
dn="PM"
hours=hours-12
}
if (hours==0) hours=12
if (hours.toString().length==1)
hours="0"+hours
if (minutes<=9)
minutes="0"+minutes

if (standardbrowser){
if (alternate==0)
document.tick.tock.value=hours+" : "+minutes+" "+dn
else
document.tick.tock.value=hours+"   "+minutes+" "+dn
}
else{
if (alternate==0)
clockobj.innerHTML=hours+" : "+minutes+" "+"<sup style='font-size:1px'>"+dn+"</sup>"
else
clockobj.innerHTML=hours+" : "+minutes+" "+"<sup style='font-size:1px'>"+dn+"</sup>"
}
alternate=(alternate==0)? 1 : 0
setTimeout("show()",1000)
}
window.onload=show

//-->
</script>


</body>
</html>