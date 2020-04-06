<?php
require "server_connection.php";

$old_email = $_POST["identifier_oldEmail"];
$user_email = $_POST["identifier_updateEmail"];
$user_pass = $_POST["identifier_updatePassword"];
$user_name = $_POST["identifier_updateName"];

$mysql_query2 = "SELECT * FROM users WHERE email = '$user_email'";
$mysql_query3 = "SELECT * FROM users WHERE name = '$user_name'";
$result2 = mysqli_query($conn,$mysql_query2);
$result3 = mysqli_query($conn,$mysql_query3);

$query = "UPDATE users 
SET  email = '$user_email', password = '$user_pass', name = '$user_name'
WHERE email = '$old_email'";
$query2 = "UPDATE countries
SET  email = '$user_email'
WHERE email = '$old_email'";
if(mysqli_num_rows($result2) = 0 && mysqli_num_rows($result3) = 0)
{
if(mysqli_query($conn,$query)){
	echo "<h2>Data Successfully Updated!</h2>";
}
else{
	echo "<h2>Data was unable to be Updated.</h2>";
}
if(mysqli_query($conn,$query2)){
	echo "<h2>Data Successfully Updated!</h2>";
}
else{
	echo "<h2>Data was unable to be updated.</h2>";
}
}
?>
