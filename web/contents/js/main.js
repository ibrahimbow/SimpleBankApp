
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

// check in database if there is the same email as the client wants to register in order to avoid duplicates
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

// check in database if there is the same admin username as the client wants to register in order to avoid duplicates
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

// check in database if there is the same username as the client wants to register in order to avoid duplicates
function checkusername() {
	// var n = document.forms["myform"]["myuser"].value;
	var n = document.getElementById("myuser").value;
	if (document.getElementById("myuser").value !== "") {

		var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/checkuser.jsp", true);
		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		var params = "param2=" + n;
		http.send(params);
		http.onload = function () {
			var s = http.responseText.trim();
			if (s === '' || s === null) {
				//
			} else {
				swal("The client (" + n + ")  is Exists..!");
			}
		};
	}
}

// check the fields which are empty because its not allowed all information is
function checkMyFormEmpty() {

	if (document.getElementById("myuser").value === ""&&
		document.getElementById("fname").value === "" &&
		document.getElementById("lname").value === "" &&
		document.getElementById("password_register").value === "" &&
		document.getElementById("email").value === "") {
		swal("Empty failed");
		return false;
	}else{
		return true;
	}
}


// alert create new user
function congratesRegister() {
	swal({
		title: "Good job!",
		text: "Your account success created !",
		icon: "success",
		button: "YES!",
	}).then((result) => {
		if (result.value) {
			window.location.href = `/welcome.jsp`
		}
	});
}

// this alert for admin when he/she creates new client
function congratesNewClient() {
	swal({
		title: "Good job!",
		text: "Account success created !",
		icon: "success",
		button: "YES!",
	});
}


// var check_PWD = function() {
// 		if (document.getElementById('password_register').value ===
// 			document.getElementById('confirm_password').value) {
// 			document.getElementById('messageTrue').style.color = 'green';
// 			var popup =  document.getElementById('messageTrue');
// 			popup.classList.toggle("show");
//
// 		} else {
// 			document.getElementById('messageFalse').style.color = 'red';
// 			var popup1 = document.getElementById('messageFalse');
// 			popup1.classList.toggle("show");
// 		}
// 	}

// check if the account number is existed or not in case we need to update and delete the clients
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

// search for the account number of the client
// and return the information of the client and fill it in right the fields
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

//check if it's not enough amount
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

// Create new clients
function create_new_client() {


	var userNameUpdate = document.getElementById("myuser").value;
	var firstNameUpdate = document.getElementById("fname").value;
	var lastNameUpdate = document.getElementById("lname").value;
	var passwordUpdate = document.getElementById("password_register").value;
	var emailUpdate = document.getElementById("email").value;
	var amountUpdate = document.getElementById("amountCreate").value;

	var http = new XMLHttpRequest();

	http.onreadystatechange = function () {
		if (http.readyState === 4 || http.status === 200) {
			//
		}
	};

	if(checkMyFormEmpty()) {
		if (document.getElementById("myuser").value !== "") {
			http.open('POST', 'create_client', true);
			http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			http.send("myuser=" + userNameUpdate +
				"&fname=" + firstNameUpdate +
				"&lname=" + lastNameUpdate +
				"&password_register=" + passwordUpdate +
				"&email=" + emailUpdate +
				"&amountCreate=" + amountUpdate);

			http.onload = function () {
				var s = http.responseText.trim();
				if (s === '' || s === null) {
					swal("Not Created");
				} else {
					congratesNewClient();
					document.getElementById("myuser").value = '';
					document.getElementById("fname").value = '';
					document.getElementById("lname").value = '';
					document.getElementById("password_register").value = '';
					document.getElementById("email").value = '';
					document.getElementById("amountCreate").value = '';
				}
			};
			return false;
		}
	}
	return true;
}


/**
 * @return {boolean}
 */
// Register New Client
function Register_new_client() {

	var userNameReg = document.getElementById("myuser").value;
	var firstNameReg = document.getElementById("fname").value;
	var lastNameReg = document.getElementById("lname").value;
	var passwordReg = document.getElementById("password_register").value;
	var emailReg = document.getElementById("email").value;

	var checkEmailReg = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

	var http = new XMLHttpRequest();
	//
	http.onreadystatechange = function () {
		if (http.readyState === 4 || http.status === 200) {
			//
		}
	};

	// It can not be registered if one of the fields is empty
	if (userNameReg !== "" && firstNameReg!=="" && lastNameReg !=="" && passwordReg!=="" && emailReg!=="") {
		if(!checkEmailReg.test(emailReg)) {
			swal("please Type you email correctly..! ");
			return false;
		}else {
			http.open('POST', 'register_client', true);
			http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			http.send("myuser=" + userNameReg +
				"&fname=" + firstNameReg +
				"&lname=" + lastNameReg +
				"&password_register=" + passwordReg +
				"&email=" + emailReg);

			http.onload = function () {
				var s = http.responseText.trim();
				if (s === '' || s === null) {
					swal("Not Created");
				} else {
					congratesRegister();
					document.getElementById("myuser").value = '';
					document.getElementById("fname").value = '';
					document.getElementById("lname").value = '';
					document.getElementById("password_register").value = '';
					document.getElementById("email").value = '';
					return true;
				}
			};
			return false;
		}
	}else{
		swal("Empty Fields..!");
		return false;
	}
}

// update Clients
function update_client() {

	var accountNumber = document.getElementById("number1").value;
	var userNameUpdate = document.getElementById("username_Update").value;
	var firstNameUpdate = document.getElementById("fnameUpdate").value;
	var lastNameUpdate = document.getElementById("lnameUpdate").value;
	var passwordUpdate = document.getElementById("pass1Update").value;
	var emailUpdate = document.getElementById("emailUpdate").value;
	var amountUpdate = document.getElementById("amountUpdate").value;

	var http = new XMLHttpRequest();
	//
	http.onreadystatechange = function() {
		if (http.readyState === 4 || http.status === 200) {
			//
		}
	};

	if(document.getElementById("number1").value !== ""){
		http.open('POST', 'updateServlet', true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		http.send("accountNumber="+accountNumber+
			"&username="+userNameUpdate+
			"&firstName="+firstNameUpdate+
			"&lastName="+lastNameUpdate+
			"&password="+passwordUpdate+
			"&email="+emailUpdate+
			"&amountUpdate="+amountUpdate);

		http.onload = function () {
			var s = http.responseText.trim();
			if(s === '' || s===null){
				swal("Not updated");
			}else{
				swal("Account No: "+ accountNumber + " is " + s);
				document.getElementById("number1").value='';
				document.getElementById("username_Update").value='';
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

// Delete Clients
function delete_client() {

	var accountNumber = document.getElementById("number1").value;
	var userNameUpdate = document.getElementById("username_Update").value;
	var firstNameUpdate = document.getElementById("fnameUpdate").value;
	var lastNameUpdate = document.getElementById("lnameUpdate").value;
	var passwordUpdate = document.getElementById("pass1Update").value;
	var emailUpdate = document.getElementById("emailUpdate").value;
	var amountUpdate = document.getElementById("amountUpdate").value;

	var http = new XMLHttpRequest();
	//
	http.onreadystatechange = function() {
		if (http.readyState === 4 || http.status === 200) {
			//
		}
	};
	// we user alert sweet to confirm before you delete the client
	swal({
		title: "Are you sure?",
		text: "Once deleted, you will not be able to recover this Client ..!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				if (document.getElementById("number1").value !== "") {
					http.open('POST', 'delete_client', true);
					http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					http.send("accountNumber=" + accountNumber +
						"&username=" + userNameUpdate +
						"&firstName=" + firstNameUpdate +
						"&lastName=" + lastNameUpdate +
						"&password=" + passwordUpdate +
						"&email=" + emailUpdate +
						"&amountUpdate=" + amountUpdate);

					http.onload = function () {
						var s = http.responseText.trim();
						if (s === '' || s === null) {
							// swal("Not updated");
						} else {
							// swal("Client is Deleted");
							document.getElementById("number1").value = '';
							document.getElementById("username_Update").value = '';
							document.getElementById("fnameUpdate").value = '';
							document.getElementById("lnameUpdate").value = '';
							document.getElementById("pass1Update").value = '';
							document.getElementById("emailUpdate").value = '';
							document.getElementById("amountUpdate").value = '';
						}
					};
					swal("Your client is Deleted ...!", {
						icon: "success",
					});
					return true
				}else{
					swal("Client is Not Exists");
				}
			} else {
				swal("Your Client is still alive ;)-");
				return false;
			}
		});
	return false;
}


/*// When the user clicks on div, open the popup*/
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




