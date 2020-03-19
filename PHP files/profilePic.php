<?php
require "server_connection.php";


$user_email = $_POST["identifier_loginEmail"];
$user_name = $_POST["identifier_loginName"];
$password = "jack3";
$profile = $_POST["profile_picture"];
//$query = "UPDATE users 
//SET picture = '$profile'
//WHERE email = '$user_email'";
$query = "UPDATE users 
SET picture = '$profile'
WHERE email = '$user_email'";
if(mysqli_query($conn,$query)){
	echo "<h2>Data updated.</h2>";
}
else{
	echo "<h2>Data was unable to be updated.</h2>";
}

?>