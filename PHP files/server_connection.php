<?php

$servername = "localhost";
$username = "id11003303_c16307271";
$dbname = "id11003303_finalyearproject2";
//password not revealed in this repository
$password = "";
//Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
//Check connection
if(mysqli_connect_errno())
 {
	echo "failed to connect to mySQL:" . mysqli_connect_error();
 }
else{
	echo "Connect success!";
}

?>
