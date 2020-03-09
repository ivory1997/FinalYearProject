<?php
require "server_connection.php";
$user_email = $_POST["identifier_loginEmail"];
$user_pass = $_POST["identifier_loginPassword"];

$mysql_query = "SELECT * FROM users WHERE email = '$user_email'
AND password = '$user_pass'";
$result = mysqli_query($conn,$mysql_query);

if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$name = $row["name"];
	$email = $row["email"];
	$picture = $row["picture"];
	//$picture2 = base64_encode($picture);
	echo "true,".$email.",".$name.",".$picture;
}
else{
	echo "Login was not successful... :(";
}
?>
