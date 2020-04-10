
(function ($) {
	"use strict";
	$('.column100').on('mouseover',function(){
		var table1 = $(this).parent().parent().parent();
		var table2 = $(this).parent().parent();
		var verTable = $(table1).data('vertable')+"";
		var column = $(this).data('column') + ""; 

		$(table2).find("."+column).addClass('hov-column-'+ verTable);
		$(table1).find(".row100.head ."+column).addClass('hov-column-head-'+ verTable);
	});

	$('.column100').on('mouseout',function(){
		var table1 = $(this).parent().parent().parent();
		var table2 = $(this).parent().parent();
		var verTable = $(table1).data('vertable')+"";
		var column = $(this).data('column') + ""; 

		$(table2).find("."+column).removeClass('hov-column-'+ verTable);
		$(table1).find(".row100.head ."+column).removeClass('hov-column-head-'+ verTable);
	});
    

})(jQuery);


function checkemail() {
	var e = document.forms["myform"]["email"].value;
	if(document.getElementById("email").value !== ""){
		var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/checkemail.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var paramz = "param3=" + e;
		http.send(paramz);
		http.onload = function() {
			// alert(http.responseText);
			document.getElementById('result1').innerHTML=(http.responseText);
			// swal("username is already Exist..!");
		}
		return false;
	}

	return true;
}

function checkUserNameLogin() {
	var n = document.forms["loginId"]["loginUser"].value;
	// var n = document.getElementById("myuser1")
	if(document.getElementById("loginUser").value !== ""){
		var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/checkuser.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var params = "param2=" + n;
		http.send(params);
		http.onload = function() {
			var s = http.responseText.trim();
			if(s === '' || s===null){
				swal("The user (" + n + ")  is not Exists..!");
			}else{
				//
			}
		};
		return false;
	}
	return true;
}

function checkusername() {
	// var n = document.forms["myform"]["myuser"].value;
	var n = document.getElementById("myuser").value;
	if(document.getElementById("myuser").value !== ""){

		var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/checkuser.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var params = "param2=" + n;
		http.send(params);
		http.onload = function() {
			var s = http.responseText.trim();
			if(s === '' || s===null){
				//
			}else {
				swal("The client (" + n + ")  is Exists..!");
			}

		};

		return false;
	}
		return true;
}

// function checkMyFormEmpty() {
// 	// var usr = document.getElementById("result").value;
// 	// var email =  document.getElementById("result1").value;
// 		if (document.getElementById("username").value === ""&&
// 			document.getElementById("lname").value === "" &&
// 			document.getElementById("fname").value === "" &&
// 			document.getElementById("password_register").value === "" &&
// 			document.getElementById("confirm_password").value === "" &&
// 			document.getElementById("email").value === "") {
// 			swal("Empty failed");
// 			return false;
// 		}else{
// 			congrates();
// 			return true;
// 		}
// 	}


function congrates() {
		swal({
			title: "Good job!",
			text: "you account success created !",
			icon: "success",
			button: "YES!",
		});

	}

var check_PWD = function() {
		if (document.getElementById('password_register').value ===
			document.getElementById('confirm_password').value) {
			document.getElementById('messageTrue').style.color = 'green';
			var popup =  document.getElementById('messageTrue');
			popup.classList.toggle("show");

		} else {
			document.getElementById('messageFalse').style.color = 'red';
			var popup1 = document.getElementById('messageFalse');
			popup1.classList.toggle("show");
		}
	}


function checkAccountNumber() {
	var e = document.forms["sendMoney"]["number"].value;
	if(document.getElementById("number").value !== ""){
		var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/checkaccountnumber.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var paramz = "param4=" + e;
		http.send(paramz);
		http.onload = function () {
			// alert(http.responseText);
			// document.getElementById("result4").innerHTML = (http.responseText);
			var s = http.responseText.trim();
			if(s === '' || s===null){
				//
			}else{
				swal("Account Number (" + e + ")  is Not Exists..!");
				document.getElementById("number").value='';
			}
		};
		return false;
	}
	return true;
}

function searchAccountNumber() {
	// var res = document.forms["updateuser"]["number1"].value;
	var res = document.getElementById("number1").value;
	if(document.getElementById("number1").value !== ""){
		var http = new XMLHttpRequest();
		// http.open("POST", "/Search", true); why it doesn't work ?????
		http.open("POST", "http://localhost:8090/myweb_war_exploded/updateCode.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var paramz = "param4=" + res;
		http.send(paramz);
		http.onload = function () {
			var s = http.responseText.trim();
			if(s === '' || s===null){
				$('#refresh1').load(location.href + ' #up1');
				$('#refresh2').load(location.href + ' #up2');
				$('#refresh3').load(location.href + ' #up3');
				$('#refresh4').load(location.href + ' #up4');
				$('#refresh5').load(location.href + ' #up5');
				$('#refresh6').load(location.href + ' #up6');
			}else{
				swal("Account Number (" + res + ")  is Not Exists..!");
				document.getElementById("number1").value='';
			}

		};
		return false;
	}
	return true;
}

function checkAmount() {
	var currentAmount = document.getElementById("currentmoney").value;
	var transferAmount = document.getElementById("amountx").value;
	if(parseFloat(transferAmount) <= parseFloat(currentAmount)){
		// swal("ok" + currentAmount);
	}else{
		swal("your amount (" + currentAmount + ") is not enough..!");
		document.getElementById("amountx").value='';
	}

}


function clearAfterUpdate() {

	var res = document.getElementById("number1").value;

	if(document.getElementById("number1").value !== ""){
		var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/updateCode.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var paramz = "param4=" + res;
		http.send(paramz);
		http.onload = function () {
			var s = http.responseText.trim();
			if(s === '' || s===null){
				swal("Not updated");
			}else{
				swal("Account  "+ res + " is " + s);
				document.getElementById("number1").value='';
				document.getElementById("myuser").value='';
				document.getElementById("fnameUpdate"). value='';
				document.getElementById("lnameUpdate"). value='';
				document.getElementById("pass1Update"). value='';
				document.getElementById("emailUpdate"). value='';
				document.getElementById("amountUpdate"). value='';
			}
		};
		return false;
	}
	return true;

}


	{/*// When the user clicks on div, open the popup*/}
	function myFunctionPopup() {
		var popup = document.getElementById("myPopup");
		popup.classList.toggle("show");
	}


// disable button if the text is empty
function manage(txt) {
	var bt = document.getElementById('submit');
	var ele = document.getElementsByTagName('input');
	// LOOP THROUGH EACH ELEMENT.

	for (var i = 0; i < ele.length; i++) {
		// CHECK THE ELEMENT TYPE.
		if (ele[i].type === 'text' && ele[i].value === '') {
			bt.disabled = true;    // Disable the button.
			return false;
		} else{
			bt.disabled = false;   // Enable the button.
		}

	}
}

//only except numbers
function isNumber(evt) {
	var iKeyCode = (evt.which) ? evt.which : evt.keyCode
	return !(iKeyCode !== 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57));
}