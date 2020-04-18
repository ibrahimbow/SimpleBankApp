// Table of Transactions
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
function check_email() {
	var e = document.forms["myformReg"]["email"].value;
	if(document.getElementById("email").value !== ""){
		var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/checkemail.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var paramz = "param3=" + e;
		http.send(paramz);
		http.onload = function() {
			var s = http.responseText.trim();
			if (s === '' || s === null) {
				//
			} else {
				swal("The Email (" + e + ")  is Exists..!");
			}
		};
	}
}

// check email for update and delete form
function check_email_update_delete() {
	var e = document.forms["myform_update_delete"]["emailUD"].value;
	if(document.getElementById("emailUD").value !== ""){
		 var http = new XMLHttpRequest();
		http.open("POST", "http://localhost:8090/myweb_war_exploded/checkemail.jsp", true);
		http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var paramz = "param3=" + e;
		http.send(paramz);
		http.onload = function() {
			var s = http.responseText.trim();
			if (s === '' || s === null) {
				//
			} else {
				swal("The Email (" + e + ")  is Exists..!");
			}
		};
	}
}


// check in database if there is the same admin username as the client wants to register in order to avoid duplicates
function checkUserNameLogin() {
	var n = document.forms["loginId"]["loginUser"].value;

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
function check_username() {
	var n = document.forms["myformReg"]["myuserReg"].value;
	if (document.getElementById("myuserReg").value !== "") {

		var http = new XMLHttpRequest();
		http.open("post", "http://localhost:8090/myweb_war_exploded/checkuser.jsp", true);
		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		var params = "param2=" + n;
		http.send(params);
		http.onload = function () {
			var s = http.responseText.trim();
			if (s === '' || s === null) {
				//
				document.getElementById("resultReg").value = http.responseText;
			} else {
				swal("The client (" + n + ")  is Exists..!");
				document.getElementById("resultReg").value = http.responseText;
			}
		};
		return false;
	}
	return true;
}

// check username for update and delete form
function check_username_update_delete() {
	var n = document.forms["myform_update_delete"]["myuserUD"].value;
	if (document.getElementById("myuserUD").value !== "") {

		var http = new XMLHttpRequest();
		http.open("post", "http://localhost:8090/myweb_war_exploded/checkuser.jsp", true);
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
		return false;
	}
	return true;
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


// alert Register new user
function congratesRegister() {
	swal({
		title: "Good job!",
		text: "Your account is created !",
		icon: "success",
		button: true,
	}).then((result) => {
		if (result.value) {
		//
		}else {
			window.location.reload();
			return false;
		}
	});


}

// this alert for admin when he/she creates new client
function congratesNewClient() {
	swal({
		title: "Good job!",
		text: "Account is created !",
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

	var bt = document.getElementById('submit');
	var e =document.getElementById("bankAccountNumberId").value;

	if(document.getElementById("bankAccountNumberId").value !== ""){
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
				// document.getElementById("bankAccountNumberId").value='';
				bt.disabled = true;    // Disable the button.
			}
		};
		return false;
	}
	return true;
}

// search for the account number of the client in the admin side this function works
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

	var userNameNew = document.getElementById("myuserReg").value;
	var firstNameNew = document.getElementById("fname").value;
	var lastNameNew = document.getElementById("lname").value;
	var passwordNew = document.getElementById("password_register").value;
	var emailNew = document.getElementById("email").value;
	var amountNew = document.getElementById("amountCreate").value;

	var http = new XMLHttpRequest();

	http.onreadystatechange = function () {
		if (http.readyState === 4 || http.status === 200) {
			//
		}
	};

	if (userNameNew !== "" && firstNameNew!=="" && lastNameNew !=="" && passwordNew!=="" && emailNew!=="") {
		if (document.getElementById("myuserReg").value !== "") {
			http.open('POST', 'create_client', true);
			http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			http.send("myuser=" + userNameNew +
				"&fname=" + firstNameNew +
				"&lname=" + lastNameNew +
				"&password_register=" + passwordNew +
				"&email=" + emailNew +
				"&amountCreate=" + amountNew);

			http.onload = function () {
				var s = http.responseText.trim();
				if (s === '' || s === null) {
					swal("Not Created");
				} else {
					congratesNewClient();
					document.getElementById("myuserReg").value = '';
					document.getElementById("fname").value = '';
					document.getElementById("lname").value = '';
					document.getElementById("password_register").value = '';
					document.getElementById("email").value = '';
					document.getElementById("amountCreate").value = '';
				}
			};
			return false;
		}
	} else{
	swal("Empty Fields..!");
	return false;
	}
}


//Transaction
function transaction_money() {

	var amountToSend = document.getElementById("amountx").value;
	var to_BankAccount = document.getElementById("bankAccountNumberId").value;


	var xmlHttpRequest = new XMLHttpRequest();
	//
	xmlHttpRequest.onreadystatechange = function() {
		if (xmlHttpRequest.readyState === 4 || xmlHttpRequest.status === 200) {
			//
		}
	};

	// we use sweetalert in order to confirm sending money before you say ohhh :)
	if (to_BankAccount !== "") {
		swal({
			title: 'Are you sure?',
			text: " please click ok or cancel",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
			.then((result) => {
				if (result) {
					if (document.getElementById("bankAccountNumberId").value !== "") {
						xmlHttpRequest.open('POST', 'transactionServlet', true);
						xmlHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xmlHttpRequest.send("amountx=" + amountToSend +
							"&bankAccountNumberId=" + to_BankAccount);

						xmlHttpRequest.onload = function () {
							var s = xmlHttpRequest.responseText.trim();
							if (s === '' || s === null) {
								// swal("Not updated");
							} else {
								// swal("Client is Deleted");
								document.getElementById("amountx").value = '';
								document.getElementById("bankAccountNumberId").value = '';
							}
						};
						swal("The amount is sent successfully...!", {
							icon: "success",
						});
						return true
					} else {
						swal("please enter the Account Number..!");
					}
				} else {
					swal("Your Money is still with you ;)-");
					return false;
				}
			});
	}else{
		swal("please enter the Account Number..!");
	}
	return false;
}



/**
 * @return {boolean}
 */
// Register New Client
function Register_new_client() {

	var userNameReg = document.getElementById("myuserReg").value;
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
			http.send("myuserReg=" + userNameReg +
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
					document.getElementById("myuserReg").value = '';
					document.getElementById("fname").value = '';
					document.getElementById("lname").value = '';
					document.getElementById("password_register").value = '';
					document.getElementById("email").value = '';
					// setTimeout(window.location.reload,2000);
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
	var userNameUpdate = document.getElementById("myuserUD").value;
	var firstNameUpdate = document.getElementById("fnameUpdate").value;
	var lastNameUpdate = document.getElementById("lnameUpdate").value;
	var passwordUpdate = document.getElementById("pass1Update").value;
	var emailUpdate = document.getElementById("emailUD").value;
	var amountUpdate = document.getElementById("amountUpdate").value;

	var http = new XMLHttpRequest();
	//
	http.onreadystatechange = function() {
		if (http.readyState === 4 || http.status === 200) {
			//
		}
	};

	if (accountNumber !== "" && firstNameUpdate!=="" && lastNameUpdate !=="" && passwordUpdate!=="" && emailUpdate!=="") {
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
				document.getElementById("myuserUD").value='';
				document.getElementById("fnameUpdate"). value='';
				document.getElementById("lnameUpdate"). value='';
				document.getElementById("pass1Update"). value='';
				document.getElementById("emailUD"). value='';
				document.getElementById("amountUpdate"). value='';
			}
		};
		return false;
		}
	}else{
		swal("Empty Fields..!");
	return false;
	}
}

// Delete Clients
function delete_client() {

	var accountNumberDel = document.getElementById("number1").value;
	var userNameDel = document.getElementById("myuserUD").value;
	var firstNameDel = document.getElementById("fnameUpdate").value;
	var lastNameDel = document.getElementById("lnameUpdate").value;
	var passwordDel = document.getElementById("pass1Update").value;
	var emailDel = document.getElementById("emailUD").value;
	var amountDel = document.getElementById("amountUpdate").value;

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
					http.send("accountNumber=" + accountNumberDel +
						"&username=" + userNameDel +
						"&firstName=" + firstNameDel +
						"&lastName=" + lastNameDel +
						"&password=" + passwordDel +
						"&email=" + emailDel +
						"&amountUpdate=" + amountDel);

					http.onload = function () {
						var s = http.responseText.trim();
						if (s === '' || s === null) {
							// swal("Not updated");
						} else {
							// swal("Client is Deleted");
							document.getElementById("number1").value = '';
							document.getElementById("myuserUD").value = '';
							document.getElementById("fnameUpdate").value = '';
							document.getElementById("lnameUpdate").value = '';
							document.getElementById("pass1Update").value = '';
							document.getElementById("emailUD").value = '';
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


// disable button if the text fields are empty
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




// notifaction if you recieve money

// const Toast = Swal.mixin({
// 	toast: true,
// 	position: 'top-end',
// 	showConfirmButton: false,
// 	timer: 3000,
// 	timerProgressBar: true,
// 	onOpen: (toast) => {
// 		toast.addEventListener('mouseenter', Swal.stopTimer)
// 		toast.addEventListener('mouseleave', Swal.resumeTimer)
// 	}
// })
//
// Toast.fire({
// 	icon: 'success',
// 	title: 'Signed in successfully'
// })



//This is how to get the ip of users incase of transactions
//
// const ipAPI = '//api.ipify.org?format=json'
//
// Swal.queue([{
// 	title: 'Your public IP',
// 	confirmButtonText: 'Show my public IP',
// 	text:
// 		'Your public IP will be received ' +
// 		'via AJAX request',
// 	showLoaderOnConfirm: true,
// 	preConfirm: () => {
// 		return fetch(ipAPI)
// 			.then(response => response.json())
// 			.then(data => Swal.insertQueueStep(data.ip))
// 			.catch(() => {
// 				Swal.insertQueueStep({
// 					icon: 'error',
// 					title: 'Unable to get your public IP'
// 				})
// 			})
// 	}
// }])