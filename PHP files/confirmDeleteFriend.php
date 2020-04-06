<?php
require "server_connection.php";

$user_email = $_POST["identifier_userEmail"];
$user_name = $_POST["identifier_userName"];
$friend_name = $_POST["identifier_friendName"];

$query = "DELETE FROM Friends WHERE user_name = '$user_name' and friend_name = '$friend_name'";
if(mysqli_query($conn,$query)){
	echo "<h2>Data Successfully Inserted!</h2>";
}
else{
	echo "<h2>Data was unable to be inserted into database :(.</h2>";
}

?>
